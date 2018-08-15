package com.lilithsthrone.game.occupantManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.2.5
 * @version 0.2.5
 * @author Innoxia
 */
public class MilkingRoom implements XMLSaving {
	
	private boolean autoSellMilk;
	private boolean autoSellCum;
	private boolean autoSellGirlcum;
	
	private WorldType worldType;
	private Vector2i location;
	
	private Map<FluidMilk, Float> milkStorage;
	private Map<FluidCum, Float> cumStorage;
	private Map<FluidGirlCum, Float> girlcumStorage;
	
	public MilkingRoom(WorldType worldType, Vector2i location) {
		autoSellMilk = false;
		autoSellCum = false;
		
		this.worldType = worldType;
		this.location = new Vector2i(location.getX(), location.getY());
		
		this.milkStorage = new HashMap<>();
		this.cumStorage = new HashMap<>();
		this.girlcumStorage = new HashMap<>();
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("milkingRoom");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "worldType", this.getWorldType().toString());
		CharacterUtils.addAttribute(doc, element, "x", String.valueOf(this.getLocation().getX()));
		CharacterUtils.addAttribute(doc, element, "y", String.valueOf(this.getLocation().getY()));
		
		CharacterUtils.addAttribute(doc, element, "autoSellMilk", String.valueOf(this.isAutoSellMilk()));
		CharacterUtils.addAttribute(doc, element, "autoSellCum", String.valueOf(this.isAutoSellCum()));
		CharacterUtils.addAttribute(doc, element, "autoSellGirlcum", String.valueOf(this.isAutoSellGirlcum()));
		
		for(Entry<FluidMilk, Float> entry : milkStorage.entrySet()) {
			Element milkElement = doc.createElement("milkStorage");
			element.appendChild(milkElement);

			CharacterUtils.addAttribute(doc, milkElement, "milkQuantity", entry.getValue().toString());
			entry.getKey().saveAsXML(milkElement, doc);
		}
		
		for(Entry<FluidCum, Float> entry : cumStorage.entrySet()) {
			Element cumElement = doc.createElement("cumStorage");
			element.appendChild(cumElement);

			CharacterUtils.addAttribute(doc, cumElement, "cumQuantity", entry.getValue().toString());
			entry.getKey().saveAsXML(cumElement, doc);
		}

		for(Entry<FluidGirlCum, Float> entry : girlcumStorage.entrySet()) {
			Element cumElement = doc.createElement("girlcumStorage");
			element.appendChild(cumElement);

			CharacterUtils.addAttribute(doc, cumElement, "girlcumQuantity", entry.getValue().toString());
			entry.getKey().saveAsXML(cumElement, doc);
		}
		
		return element;
	}
	
	public static MilkingRoom loadFromXML(Element parentElement, Document doc) {
		try {
			
			MilkingRoom room = new MilkingRoom(
					WorldType.valueOf(parentElement.getAttribute("worldType")),
					new Vector2i(
							Integer.valueOf(parentElement.getAttribute("x")),
							Integer.valueOf(parentElement.getAttribute("y"))));

			try {
				room.setAutoSellMilk(Boolean.valueOf(parentElement.getAttribute("autoSellMilk")));
				room.setAutoSellCum(Boolean.valueOf(parentElement.getAttribute("autoSellCum")));
				room.setAutoSellGirlcum(Boolean.valueOf(parentElement.getAttribute("autoSellGirlcum")));
			} catch(Exception ex) {
			}

			NodeList milkStorageElements = parentElement.getElementsByTagName("milkStorage");
			for(int i=0; i<milkStorageElements.getLength(); i++){
				Element milkStorageElement = (Element)milkStorageElements.item(i);
				Float quantity = Float.valueOf(milkStorageElement.getAttribute("milkQuantity"));
				FluidMilk milk = FluidMilk.loadFromXML(milkStorageElement, doc);
				room.incrementMilkStorage(milk, quantity);
			}

			NodeList cumStorageElements = parentElement.getElementsByTagName("cumStorage");
			for(int i=0; i<cumStorageElements.getLength(); i++){
				Element cumStorageElement = (Element)cumStorageElements.item(i);
				Float quantity = Float.valueOf(cumStorageElement.getAttribute("cumQuantity"));
				FluidCum cum = FluidCum.loadFromXML(cumStorageElement, doc);
				room.incrementCumStorage(cum, quantity);
			}

			NodeList girlCumStorageElements = parentElement.getElementsByTagName("girlcumStorage");
			for(int i=0; i<girlCumStorageElements.getLength(); i++){
				Element cumStorageElement = (Element)girlCumStorageElements.item(i);
				Float quantity = Float.valueOf(cumStorageElement.getAttribute("girlcumQuantity"));
				FluidGirlCum cum = FluidGirlCum.loadFromXML(cumStorageElement, doc);
				room.incrementGirlcumStorage(cum, quantity);
			}
			
			return room;
			
		} catch(Exception ex) {
			System.err.println("Warning: MilkingRoom failed to import!");
			return null;
		}
	}
	
	public static Cell getMilkingCell(GameCharacter character, boolean needFreeCell) {
		List<Cell> milkingCells = new ArrayList<>();
		
		for(MilkingRoom room : Main.game.getOccupancyUtil().getMilkingRooms()) {
			Cell c = Main.game.getWorlds().get(room.getWorldType()).getCell(room.getLocation());
		
			int charactersPresent = Main.game.getCharactersPresent(c).size();
			
			if(character.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_INDUSTRIAL)
					&& c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)
					&& (needFreeCell?charactersPresent<8:charactersPresent<=8)) {
				return c;
				
			} else if(character.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_ARTISAN)
					&& c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)
					&& (needFreeCell?charactersPresent<8:charactersPresent<=8)) {
				return c;
				
			} else if(character.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_REGULAR)
					&& !c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)
					&& !c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)
					&& (needFreeCell?charactersPresent<8:charactersPresent<=8)) {
				return c;
			}
			
			if((needFreeCell?charactersPresent<8:charactersPresent<=8)) {
				milkingCells.add(c);
			}
		}
		if(milkingCells.isEmpty()) {
			return null;
		}
		return milkingCells.get(0);
	}
	
	public static int getMaximumMilkPerHour(GameCharacter character) {
		Cell c = getMilkingCell(character, false);
		int milked = 500;

		if(c==null) {
			return milked;
		}
		
		if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
			milked = 250;
			
		} else if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
			milked = 1000;
		}
		
		if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_MILK_EFFICIENCY)) {
			milked*=2;
		}
		
		return milked;
	}

	public static int getActualMilkPerHour(GameCharacter character) {
		return (int) Math.min(getMaximumMilkPerHour(character), (character.getBreastLactationRegeneration().getPercentageRegen()*character.getBreastRawMilkStorageValue()*60));
	}
	
	public static int getMaximumCumPerHour(GameCharacter character) {
		Cell c = getMilkingCell(character, false);
		int milked = 50;

		if(c==null) {
			return milked;
		}
		
		if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
			milked = 25;
			
		} else if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
			milked = 250;
		}
		
		if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_CUM_EFFICIENCY)) {
			milked*=2;
		}
		
		return milked;
	}

	public static int getActualCumPerHour(GameCharacter character) {
		if(!character.hasPenisIgnoreDildo()) {
			return 0;
		}
		return (int) Math.min(getMaximumCumPerHour(character), (character.getPenisCumProductionRegeneration().getPercentageRegen()*character.getPenisRawCumStorageValue()*60));
	}
	
	public static int getMaximumGirlcumPerHour(GameCharacter character) {
		Cell c = getMilkingCell(character, false);
		int milked = 10;
		
		if(c==null) {
			return milked;
		}
		
		if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
			milked = 5;
			
		} else if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
			milked = 50;
		}
		
		if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_GIRLCUM_EFFICIENCY)) {
			milked*=2;
		}
		
		return milked;
	}

	public static int getActualGirlcumPerHour(GameCharacter character) {
		if(!character.hasVagina()) {
			return 0;
		}
		return Math.min(getMaximumGirlcumPerHour(character), character.getVaginaWetness().getValue()*(character.isVaginaSquirter()?2:1));
	}
	
	public boolean isAutoSellMilk() {
		return autoSellMilk;
	}

	public void setAutoSellMilk(boolean autoSellMilk) {
		this.autoSellMilk = autoSellMilk;
	}

	public boolean isAutoSellCum() {
		return autoSellCum;
	}

	public void setAutoSellCum(boolean autoSellCum) {
		this.autoSellCum = autoSellCum;
	}

	public boolean isAutoSellGirlcum() {
		return autoSellGirlcum;
	}

	public void setAutoSellGirlcum(boolean autoSellGirlcum) {
		this.autoSellGirlcum = autoSellGirlcum;
	}

	public WorldType getWorldType() {
		return worldType;
	}

	public Vector2i getLocation() {
		return location;
	}

	public Map<FluidMilk, Float> getMilkStorage() {
		return milkStorage;
	}
	
	public void incrementMilkStorage(FluidMilk milk, float quantity) {
		milkStorage.putIfAbsent(milk, 0f);
		milkStorage.put(milk, milkStorage.get(milk)+quantity);
		if(milkStorage.get(milk)==0) {
			milkStorage.remove(milk);
		}
	}
	
	public Map<FluidCum, Float> getCumStorage() {
		return cumStorage;
	}

	public void incrementCumStorage(FluidCum cum, float quantity) {
		cumStorage.putIfAbsent(cum, 0f);
		cumStorage.put(cum, cumStorage.get(cum)+quantity);
		if(cumStorage.get(cum)==0) {
			cumStorage.remove(cum);
		}
	}
	
	public Map<FluidGirlCum, Float> getGirlcumStorage() {
		return girlcumStorage;
	}

	public void incrementGirlcumStorage(FluidGirlCum girlcum, float quantity) {
		girlcumStorage.putIfAbsent(girlcum, 0f);
		girlcumStorage.put(girlcum, girlcumStorage.get(girlcum)+quantity);
		if(girlcumStorage.get(girlcum)==0) {
			girlcumStorage.remove(girlcum);
		}
	}
	
}
