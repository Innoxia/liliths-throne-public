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
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.2.5
 * @version 0.3.5.1
 * @author Innoxia
 */
public class MilkingRoom implements XMLSaving {
	
	private AbstractWorldType worldType;
	private Vector2i location;
	
	private List<FluidStored> fluidsStored;
	
	private static GameCharacter targetedCharacter = Main.game.getPlayer();
	
	public static final int INGESTION_AMOUNT = 100;

	public static final int ARTISAN_MILKING_AMOUNT = 2000;
	public static final int BASE_MILKING_AMOUNT = 2500;
	public static final int INDUSTRIAL_MILKING_AMOUNT = 5000;

	public static final int ARTISAN_CUM_MILKING_AMOUNT = 200;
	public static final int BASE_CUM_MILKING_AMOUNT = 250;
	public static final int INDUSTRIAL_CUM_MILKING_AMOUNT = 500;

	public static final int ARTISAN_GIRLCUM_MILKING_AMOUNT = 40;
	public static final int BASE_GIRLCUM_MILKING_AMOUNT = 50;
	public static final int INDUSTRIAL_GIRLCUM_MILKING_AMOUNT = 100;
	
	public MilkingRoom(AbstractWorldType worldType, Vector2i location) {
		this.worldType = worldType;
		this.location = new Vector2i(location.getX(), location.getY());
		
		this.fluidsStored = new ArrayList<>();
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("milkingRoom");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "worldType", WorldType.getIdFromWorldType(this.getWorldType()));
		CharacterUtils.addAttribute(doc, element, "x", String.valueOf(this.getLocation().getX()));
		CharacterUtils.addAttribute(doc, element, "y", String.valueOf(this.getLocation().getY()));

		for(FluidStored fluid : fluidsStored) {
			fluid.saveAsXML(element, doc);
		}
		
		return element;
	}
	
	public static MilkingRoom loadFromXML(Element parentElement, Document doc) {
		try {
			MilkingRoom room = new MilkingRoom(
					WorldType.getWorldTypeFromId(parentElement.getAttribute("worldType")),
					new Vector2i(
							Integer.valueOf(parentElement.getAttribute("x")),
							Integer.valueOf(parentElement.getAttribute("y"))));

			try {
				NodeList fluidStoredElements = parentElement.getElementsByTagName("fluidStored");
				for(int i=0; i<fluidStoredElements.getLength(); i++){
					Element fluidElement = (Element)fluidStoredElements.item(i);
					room.getFluidsStored().add(FluidStored.loadFromXML(null, fluidElement, doc));
				}
			} catch(Exception ex) {
			}

			Map<FluidStored, Float> uniqueFluids = new HashMap<>();
			
			for(FluidStored fluid : room.getFluidsStored()) {
				if(uniqueFluids.containsKey(fluid)) {
					uniqueFluids.put(fluid, fluid.getMillilitres()+uniqueFluids.get(fluid));
				} else {
					uniqueFluids.put(fluid, fluid.getMillilitres());
				}
			}

			room.fluidsStored = new ArrayList<>();
			for(Entry<FluidStored, Float> entry : uniqueFluids.entrySet()) {
				entry.getKey().setMillilitres(entry.getValue());
				room.fluidsStored.add(entry.getKey());
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
			
			if(character.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_INDUSTRIAL)
					&& c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)
					&& (needFreeCell?charactersPresent<8:charactersPresent<=8)) {
				return c;
				
			} else if(character.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_ARTISAN)
					&& c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)
					&& (needFreeCell?charactersPresent<8:charactersPresent<=8)) {
				return c;
				
			} else if(character.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_REGULAR)
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
		int milked = MilkingRoom.BASE_MILKING_AMOUNT;

		if(c==null) {
			return milked;
		}
		
		if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
			milked = MilkingRoom.ARTISAN_MILKING_AMOUNT;
			
		} else if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
			milked = MilkingRoom.INDUSTRIAL_MILKING_AMOUNT;
		}
		
		if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_MILK_EFFICIENCY)) {
			milked*=2;
		}
		
		return milked;
	}

	public static int getActualMilkPerHour(GameCharacter character) {
		return (int) Math.min(getMaximumMilkPerHour(character), (character.getLactationRegenerationPerSecond(true) * 60 * 60));
	}
	
	public static int getActualCrotchMilkPerHour(GameCharacter character) {
		return (int) Math.min(getMaximumMilkPerHour(character), (character.getCrotchLactationRegenerationPerSecond(true) * 60 * 60));
	}
	
	public static int getMaximumCumPerHour(GameCharacter character) {
		Cell c = getMilkingCell(character, false);
		int milked = MilkingRoom.BASE_CUM_MILKING_AMOUNT;

		if(c==null) {
			return milked;
		}
		
		if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
			milked = MilkingRoom.ARTISAN_CUM_MILKING_AMOUNT;
			
		} else if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
			milked = MilkingRoom.INDUSTRIAL_CUM_MILKING_AMOUNT;
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
		return (int) Math.min(getMaximumCumPerHour(character), (character.getCumRegenerationPerSecond() * 60 * 60));
	}
	
	public static int getMaximumGirlcumPerHour(GameCharacter character) {
		Cell c = getMilkingCell(character, false);
		int milked = MilkingRoom.BASE_GIRLCUM_MILKING_AMOUNT;
		
		if(c==null) {
			return milked;
		}
		
		if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
			milked = MilkingRoom.ARTISAN_GIRLCUM_MILKING_AMOUNT;
			
		} else if(c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
			milked = MilkingRoom.INDUSTRIAL_GIRLCUM_MILKING_AMOUNT;
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

	public AbstractWorldType getWorldType() {
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
	
	public void incrementFluidStored(FluidStored fluid, float millilitres) {
		boolean fluidIncremented = false;
		
		for(FluidStored f : getFluidsStored()) {
			if(fluid.equals(f)) {
				f.incrementMillilitres(millilitres);
				fluidIncremented = true;
				break;
			}
		}
		if(!fluidIncremented) {
			getFluidsStored().add(fluid);
		}
		
		getFluidsStored().removeIf((fs) -> fs.getMillilitres()<=0);
	}
	
	public String getRoomDescription() {
		StringBuilder milkyMilknessSB = new StringBuilder();
		
		milkyMilknessSB.append(getFluidEntries(this.getMilkFluidsStored(), PresetColour.MILK, "Milk Stored"));
		milkyMilknessSB.append(getFluidEntries(this.getCumFluidsStored(), PresetColour.CUM, "Cum Stored"));
		milkyMilknessSB.append(getFluidEntries(this.getGirlcumFluidsStored(), PresetColour.GIRLCUM, "Girlcum Stored"));
		
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
				
				milkyMilknessSB.append("<div class='container-full-width' style='margin-top:2px; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>");
				
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
									+(isAbleToIngestThroughArea(fluid.getFluid().getType().getBaseType(), getTargetedCharacter(), CoverableArea.MOUTH, fluid.getMillilitres())
											?"class='square-button big'"
											:"class='square-button big disabled'")+">"
									+ "<div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth()+"</div></div>");
					
					milkyMilknessSB.append("<div id='"+idModifier+"_"+CoverableArea.VAGINA+"_"+fluid.hashCode()+"' "
									+(isAbleToIngestThroughArea(fluid.getFluid().getType().getBaseType(), getTargetedCharacter(), CoverableArea.VAGINA, fluid.getMillilitres())
											?"class='square-button big'"
											:"class='square-button big disabled'")+">"
									+ "<div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina()+"</div></div>");
					
					milkyMilknessSB.append("<div id='"+idModifier+"_"+CoverableArea.ANUS+"_"+fluid.hashCode()+"' "
								+(isAbleToIngestThroughArea(fluid.getFluid().getType().getBaseType(), getTargetedCharacter(), CoverableArea.ANUS, fluid.getMillilitres())
											?"class='square-button big'"
											:"class='square-button big disabled'")+">"
									+ "<div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div></div>");
					
					milkyMilknessSB.append("<div id='"+idModifier+"_SELL_"+fluid.hashCode()+"' class='square-button big'>"
									+ "<div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionSell()+"</div></div>");
					
					milkyMilknessSB.append("</div>");
					
				milkyMilknessSB.append("</div>");
			}
			
			if(!fluidsFound) {
				milkyMilknessSB.append("<div class='container-full-width' style='margin-bottom:2px; text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>[style.colourDisabled(None...)]</div>");
			}

		milkyMilknessSB.append("</div>");
		
		return milkyMilknessSB.toString();
	}
	
	public boolean isAbleToIngestThroughArea(FluidTypeBase fluidType, GameCharacter ingestingCharacter, CoverableArea area, float millilitres) {
		return getAreaIngestionBlockedDescription(fluidType, ingestingCharacter, area, millilitres).isEmpty();
	}
	
	public String getAreaIngestionBlockedDescription(FluidTypeBase fluidType, GameCharacter ingestingCharacter, CoverableArea area, float millilitres) {
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
		if(sb.length()>0) {
			return UtilText.parse(ingestingCharacter, sb.toString());
		}
		
		if(!ingestingCharacter.isPlayer() && !ingestingCharacter.isSlave()) {
			if(ingestingCharacter.getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
				 sb.append(UtilText.parse(ingestingCharacter,
							"As [npc.sheIs] not your slave, [npc.name] will only let you do this if [npc.she]"
							+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> you.<br/>"));
				
			} else if(fluidType==FluidTypeBase.CUM
					&& (area==CoverableArea.VAGINA
						?!ingestingCharacter.getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
						:!ingestingCharacter.getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive())) {
				 sb.append(UtilText.parse(ingestingCharacter,
						 area==CoverableArea.VAGINA
						 	?"[npc.Name] requires a positive desire for the "+Fetish.FETISH_PREGNANCY.getName(ingestingCharacter)+" fetish.<br/>"
							:"[npc.Name] requires a positive desire for the "+Fetish.FETISH_CUM_ADDICT.getName(ingestingCharacter)+" fetish.<br/>"));
				
			} else if(fluidType==FluidTypeBase.MILK && !ingestingCharacter.getFetishDesire(Fetish.FETISH_LACTATION_OTHERS).isPositive()) {
				 sb.append(UtilText.parse(ingestingCharacter,
						"[npc.Name] requires a positive desire for the "+Fetish.FETISH_LACTATION_OTHERS.getName(ingestingCharacter)+" fetish.<br/>"));
				
			} else if(fluidType==FluidTypeBase.GIRLCUM && !ingestingCharacter.getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
				 sb.append(UtilText.parse(ingestingCharacter,
						"[npc.Name] requires a positive desire for the "+Fetish.FETISH_VAGINAL_GIVING.getName(ingestingCharacter)+" fetish.<br/>"));
			}
			switch(area) {
				case ANUS:
					if(!ingestingCharacter.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
						 sb.append(UtilText.parse(ingestingCharacter,
									"[npc.Name] requires a positive desire for the "+Fetish.FETISH_ANAL_RECEIVING.getName(ingestingCharacter)+" fetish."));
					}
					break;
				case VAGINA:
					if(!ingestingCharacter.getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
						 sb.append(UtilText.parse(ingestingCharacter,
									"[npc.Name] requires a non-negative desire for the "+Fetish.FETISH_VAGINAL_RECEIVING.getName(ingestingCharacter)+" fetish."));
					}
					break;
				default:
					break;
			}
		}
		
		return sb.toString();
	}

	public static GameCharacter getTargetedCharacter() {
		if(MilkingRoom.targetedCharacter==null || (!MilkingRoom.targetedCharacter.isPlayer() && !Main.game.getCharactersPresent().contains(MilkingRoom.targetedCharacter))) {
			MilkingRoom.targetedCharacter = Main.game.getPlayer();
		}
		return targetedCharacter;
	}

	public static void setTargetedCharacter(GameCharacter targetedCharacter) {
		MilkingRoom.targetedCharacter = targetedCharacter;
	}
	
}
