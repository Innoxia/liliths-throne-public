package com.lilithsthrone.game.occupantManagement;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.utils.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidInterface;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.2.5
 * @version 0.2.11
 * @author Innoxia
 */
public class MilkingRoom implements XMLSaving {
	
	private boolean autoSellMilk;
	private boolean autoSellCum;
	private boolean autoSellGirlcum;
	
	private WorldType worldType;
	private Vector2i location;
	
	private List<FluidStored> fluidsStored;
	
	public static final int INGESTION_AMOUNT = 100;
	
	public MilkingRoom(WorldType worldType, Vector2i location) {
		autoSellMilk = false;
		autoSellCum = false;
		
		this.worldType = worldType;
		this.location = new Vector2i(location.getX(), location.getY());
		
		this.fluidsStored = new ArrayList<>();
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

		for(FluidStored fluid : fluidsStored) {
			fluid.saveAsXML(element, doc);
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

			try {
				NodeList fluidStoredElements = parentElement.getElementsByTagName("fluidStored");
				for(int i=0; i<fluidStoredElements.getLength(); i++){
					Element fluidElement = (Element)fluidStoredElements.item(i);
					room.getFluidsStored().add(FluidStored.loadFromXML(null, fluidElement, doc));
				}
			} catch(Exception ex) {
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
			
			if(character.hasSlaveJobSetting(SlaveJobSetting.MILKING_INDUSTRIAL)
					&& c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)
					&& (needFreeCell?charactersPresent<8:charactersPresent<=8)) {
				return c;
				
			} else if(character.hasSlaveJobSetting(SlaveJobSetting.MILKING_ARTISAN)
					&& c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)
					&& (needFreeCell?charactersPresent<8:charactersPresent<=8)) {
				return c;
				
			} else if(character.hasSlaveJobSetting(SlaveJobSetting.MILKING_REGULAR)
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
		return (int) Math.min(getMaximumMilkPerHour(character), (character.getBreastLactationRegeneration().getPercentageRegen() * character.getBreastRawMilkStorageValue() * 60 * 60));
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
		return (int) Math.min(getMaximumCumPerHour(character), (character.getPenisCumProductionRegeneration().getPercentageRegen() * character.getPenisRawCumStorageValue() * 60 * 60));
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

	public List<FluidStored> getFluidsStored() {
		return fluidsStored;
	}

	public List<FluidStored> getMilkFluidsStored() {
		List<FluidStored> milkFluids = new ArrayList<>(fluidsStored);
		milkFluids.removeIf((fluid)->!fluid.isMilk());
		return milkFluids;
	}

	public List<FluidStored> getCumFluidsStored() {
		List<FluidStored> cumFluids = new ArrayList<>(fluidsStored);
		cumFluids.removeIf((fluid)->!fluid.isCum());
		return cumFluids;
	}

	public List<FluidStored> getGirlcumFluidsStored() {
		List<FluidStored> girlcumFluids = new ArrayList<>(fluidsStored);
		girlcumFluids.removeIf((fluid)->!fluid.isGirlCum());
		return girlcumFluids;
	}
	
	public void incrementFluidStored(GameCharacter character, FluidInterface fluidToStore, float quantity) {
		boolean fluidIncremented = false;
		
		FluidStored newFluid;
		if(fluidToStore instanceof FluidCum) {
			newFluid = new FluidStored(character, ((FluidCum) fluidToStore), quantity);
		} else if(fluidToStore instanceof FluidMilk) {
			newFluid = new FluidStored(character.getId(), ((FluidMilk)fluidToStore), quantity);
		} else {
			newFluid = new FluidStored(character.getId(), ((FluidGirlCum)fluidToStore), quantity);
		}
		
		for(FluidStored fluid : getFluidsStored()) {
			if(fluid.getCharactersFluidID().equals(character.getId()) && fluid.equals(newFluid)) {
				fluid.incrementMillilitres((int) quantity);
				fluidIncremented = true;
				break;
			}
		}
		if(!fluidIncremented) {
			switch(fluidToStore.getType().getBaseType()) {
				case CUM:
					getFluidsStored().add(new FluidStored(character.getId(), character.getSubspecies(), (FluidCum) fluidToStore, (int) quantity));
					break;
				case MILK:
					getFluidsStored().add(new FluidStored(character.getId(), (FluidMilk) fluidToStore, (int) quantity));
					break;
				case GIRLCUM:
					getFluidsStored().add(new FluidStored(character.getId(), (FluidGirlCum) fluidToStore, (int) quantity));
					break;
			}
		}
		
		getFluidsStored().removeIf((fs) -> fs.getMillilitres()<=0);
	}
	
	public String getRoomDescription() {
		StringBuilder milkyMilknessSB = new StringBuilder();
		
		milkyMilknessSB.append(getFluidEntries(this.getMilkFluidsStored(), Colour.MILK, "Milk Stored"));
		milkyMilknessSB.append(getFluidEntries(this.getCumFluidsStored(), Colour.CUM, "Cum Stored"));
		milkyMilknessSB.append(getFluidEntries(this.getGirlcumFluidsStored(), Colour.GIRLCUM, "Girlcum Stored"));
		
		return milkyMilknessSB.toString();
	}
	
	private String getFluidEntries(List<FluidStored> fluids, Colour colour, String title) {
		StringBuilder milkyMilknessSB = new StringBuilder();
		
		
		boolean fluidsFound = false;

		milkyMilknessSB.append("<div class='container-full-width' style='margin-bottom:2px; text-align:center;'><b style='color:"+colour.toWebHexString()+";'>"+title+"</b>");
		
			for(FluidStored fluid : fluids) {
				
				String idModifier = "";
				FluidType type = null;
				
				if(fluid.isMilk()) {
					idModifier = "MILK";
					type = ((FluidMilk) fluid.getFluid()).getType();
					
				} else if(fluid.isCum()) {
					idModifier = "CUM";
					type = ((FluidCum) fluid.getFluid()).getType();
					
				} else if(fluid.isGirlCum()) {
					idModifier = "GIRLCUM";
					type = ((FluidGirlCum) fluid.getFluid()).getType();
				}
				
				fluidsFound = true;
				
				milkyMilknessSB.append("<div class='container-full-width' style='margin-top:2px; background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'>");
				
					milkyMilknessSB.append(
							"<div class='container-half-width' style='margin:0; padding:2px; width:15%; background:transparent;'>"
								+ "[style.colourExcellent("+Units.fluid(fluid.getMillilitres())+")]");
					if(fluid.isCum()) {
						milkyMilknessSB.append("<br/><span style='color:"+Attribute.VIRILITY.getColour().toWebHexString()+";'>Virility:</span> "+Units.adaptiveRound(fluid.getVirility()));
					}
					milkyMilknessSB.append("</div>");
				
					milkyMilknessSB.append("<div class='container-half-width' style='margin:0; padding:2px; width:25%; background:transparent;'>");
					GameCharacter fluidOwner = null;
					try {
						fluidOwner = fluid.getFluidCharacter();
						milkyMilknessSB.append(UtilText.parse(fluidOwner, "<span style='color:"+fluidOwner.getFemininity().getColour().toWebHexString()+";'>[npc.NamePos]</span>"));
						
					} catch(Exception ex) {
						milkyMilknessSB.append("[style.colourDisabled(Unknown's)]");
					}
					milkyMilknessSB.append("<br/>"
								+ "<span style='color:"+type.getRace().getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(type.getRace().getName(fluid.isBestial()))+" "+type.getName(fluidOwner)
								+"</span>"
							+ "</div>");
	
					milkyMilknessSB.append("<div class='container-half-width' style='margin:0; padding:2px; width:35%; background:transparent;'>");
					FluidFlavour flavour = fluid.getFluid().getFlavour();
					milkyMilknessSB.append("<span style='color:"+flavour.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(flavour.getName())+"-flavoured</span>.<br/>");
						if(!fluid.getFluid().getFluidModifiers().isEmpty()) {
							int i=0;
							for(FluidModifier mod : fluid.getFluid().getFluidModifiers()) {
								if(i>0) {
									milkyMilknessSB.append(", ");
								}
								milkyMilknessSB.append(Util.capitaliseSentence(mod.getName()));
								i++;
							}
							milkyMilknessSB.append(".");
							
						} else {
							milkyMilknessSB.append("[style.colourDisabled(No Modifiers)]");
						}
					milkyMilknessSB.append("</div>");
	
					milkyMilknessSB.append(
							"<div class='container-half-width' style='margin:0; padding:2px; width:10%; background:transparent;'>"
								+ UtilText.formatAsMoney((int)(fluid.getMillilitres()*fluid.getFluid().getValuePerMl()), "span")
							+ "</div>");
					
					milkyMilknessSB.append("<div style='float:left; width:15%; margin:0 auto; padding:0; display:inline-block; text-align:center; background:transparent;'>"
							+ "<div id='"+idModifier+"_"+CoverableArea.MOUTH+"_"+fluid.hashCode()+"' "
									+(isAbleToIngestThroughArea(Main.game.getPlayer(), CoverableArea.MOUTH, fluid.getMillilitres())
											?"class='square-button big'"
											:"class='square-button big disabled'")+">"
									+ "<div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth()+"</div></div>");
					
					milkyMilknessSB.append("<div id='"+idModifier+"_"+CoverableArea.VAGINA+"_"+fluid.hashCode()+"' "
									+(isAbleToIngestThroughArea(Main.game.getPlayer(), CoverableArea.VAGINA, fluid.getMillilitres())
											?"class='square-button big'"
											:"class='square-button big disabled'")+">"
									+ "<div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina()+"</div></div>");
					
					milkyMilknessSB.append("<div id='"+idModifier+"_"+CoverableArea.ANUS+"_"+fluid.hashCode()+"' "
								+(isAbleToIngestThroughArea(Main.game.getPlayer(), CoverableArea.ANUS, fluid.getMillilitres())
											?"class='square-button big'"
											:"class='square-button big disabled'")+">"
									+ "<div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div></div>");
					
					milkyMilknessSB.append("<div id='"+idModifier+"_SELL_"+fluid.hashCode()+"' class='square-button big'>"
									+ "<div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionSell()+"</div></div>");
					
					milkyMilknessSB.append("</div>");
					
				milkyMilknessSB.append("</div>");
			}
			
			if(!fluidsFound) {
				milkyMilknessSB.append("<div class='container-full-width' style='margin-bottom:2px; text-align:center; background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'>[style.colourDisabled(None...)]</div>");
			}

		milkyMilknessSB.append("</div>");
		
		return milkyMilknessSB.toString();
	}
	
	public boolean isAbleToIngestThroughArea(GameCharacter ingestingCharacter, CoverableArea area, float millilitres) {
		return getAreaIngestionBlockedDescription(ingestingCharacter, area, millilitres).isEmpty();
	}
	
	public String getAreaIngestionBlockedDescription(GameCharacter ingestingCharacter, CoverableArea area, float millilitres) {
		StringBuilder sb = new StringBuilder();
		
		if(millilitres<5) {
			sb.append("There needs to be at least "+Units.fluid(5)+" of fluid for [npc.name] to ingest it!<br/>");
		}
		
		switch(area) {
			case ANUS:
				if(!ingestingCharacter.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					sb.append("[npc.NameIsFull] not able to access [npc.her] asshole!");
				}
				break;
			case MOUTH:
				if(!ingestingCharacter.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					sb.append("[npc.NameIsFull] not able to access [npc.her] mouth!");
				}
				break;
			case VAGINA:
				if(!ingestingCharacter.hasVagina()) {
					sb.append("[npc.Name] [npc.does] not have a vagina!");
					
				} else if(!ingestingCharacter.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					sb.append("[npc.NameIsFull] not able to access [npc.her] pussy!");
				}
				break;
			default:
				break;
		}
		if(sb.length()==0) {
			return "";
		}
		return UtilText.parse(ingestingCharacter, sb.toString());
	}
	
}
