package com.lilithsthrone.game.dialogue;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Hair;
import com.lilithsthrone.game.character.body.Skin;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.SlaveJob;
import com.lilithsthrone.game.occupantManagement.SlaveJobHours;
import com.lilithsthrone.game.occupantManagement.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.SlavePermission;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.8?
 * @version 0.2.10
 * @author Innoxia
 */
public class OccupantManagementDialogue {
	
	private static StringBuilder miscDialogueSB = new StringBuilder();
	private static int dayNumber = 1;
	private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	private static boolean slaveListManagementOverview = false;
	
	static {
		decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
	}
	
	public static NPC characterSelected() {
		return Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected();
	}
	
	public static DialogueNodeOld getSlaveryOverviewDialogue() {
		dayNumber = Main.game.getDayNumber();
		return OCCUPANT_OVERVIEW;
	}
	
	public static DialogueNodeOld getSlaveryManagementInspectSlaveDialogue(NPC slave) {
		Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(slave);
		CharactersPresentDialogue.resetContent(slave);
		return SLAVE_MANAGEMENT_INSPECT;
	}
	
	public static DialogueNodeOld getSlaveryManagementSlaveJobsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(slave);
		CharactersPresentDialogue.resetContent(slave);
		return SLAVE_MANAGEMENT_JOBS;
	}
	
	public static DialogueNodeOld getSlaveryManagementSlavePermissionsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(slave);
		CharactersPresentDialogue.resetContent(slave);
		return SLAVE_MANAGEMENT_PERMISSIONS;
	}
	
	public static DialogueNodeOld getSlaveryManagementDialogue(NPC slaveTrader) {
		Main.game.getDialogueFlags().setSlaveTrader(slaveTrader);
		slaveListManagementOverview = true;
		return SLAVE_LIST_MANAGEMENT;
	}
	
	public static DialogueNodeOld getSlaveryRoomListDialogue(NPC slaveTrader) {
		Main.game.getDialogueFlags().setSlaveTrader(slaveTrader);
		slaveListManagementOverview = false;
		return SLAVE_LIST;
	}
	
	public static int getDayNumber() {
		return dayNumber;
	}

	public static void incrementDayNumber(int increment) {
		dayNumber += increment;
		dayNumber = Math.max(1,
				dayNumber);
	}
	
	private static Response getSlaveryResponse(int index) {
		if (index == 1) {
			return new Response("Room List", "View the room management screen.", ROOM_MANAGEMENT) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
				}
			};
			
		} else if (index == 2) {
			return new Response("Slave List", "Enter the slave management screen.", SLAVE_LIST_MANAGEMENT) {
				@Override
				public DialogueNodeOld getNextDialogue() {
					return OccupantManagementDialogue.getSlaveryManagementDialogue(Main.game.getDialogueFlags().getSlaveTrader());
				}
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
				}
			};
			
		} else if (index == 5) {
			if(Main.game.getOccupancyUtil().getGeneratedBalance()==0) {
				return new Response("Collect: "+UtilText.formatAsMoneyUncoloured(Main.game.getOccupancyUtil().getGeneratedBalance(), "span"), "Your current balance is 0...",  null);
				
			} else if(Main.game.getOccupancyUtil().getGeneratedBalance()>0) {
				return new Response("Collect: "+UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedBalance(), "span"), "Collect the money that you've earned through your slaves' activities.",  OCCUPANT_OVERVIEW) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getCurrentDialogueNode();
					}
					@Override
					public void effects() {
						Main.game.getOccupancyUtil().payOutBalance();
					}
				};
				
			} else {
				if(Main.game.getPlayer().getMoney()<Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance())) {
					return new Response("Pay: "+UtilText.formatAsMoneyUncoloured(Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance()), "span"),
							"You don't have enough money to pay off the accumulated debt from the upkeep of your slaves and rooms.",  null);
				}
				
				return new Response("Pay: "+UtilText.formatAsMoney(Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance()), "span", Colour.GENERIC_BAD), "Pay off the accumulated debt from the upkeep of your slaves and rooms.",  OCCUPANT_OVERVIEW) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getCurrentDialogueNode();
					}
					@Override
					public void effects() {
						Main.game.getOccupancyUtil().payOutBalance();
					}
				};
			}
			
		} else if (index == 0) {
			return new Response("Back", "Exit the room upgrades screen.", OCCUPANT_OVERVIEW) {
				@Override
				public DialogueNodeOld getNextDialogue() {
					return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
				}
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
					Main.game.getDialogueFlags().setSlaveTrader(null);
				}
			};

		} else {
			return null;
		}
	}
	
	public static final DialogueNodeOld OCCUPANT_OVERVIEW = new DialogueNodeOld("Slavery Overview", ".", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			int income = Main.game.getPlayer().getSlaveryTotalDailyIncome();
			int upkeep = Main.game.getPlayer().getSlaveryTotalDailyUpkeep();
			
			// Overview:
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>Totals</h6>"
						+ "<div class='container-full-width' style='text-align:center; margin-bottom:0;'>"
							+ "<div style='width:10%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Slaves"
							+ "</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Income</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Upkeep</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Profit</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Funds</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Payments</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Balance</b>"
							+"</div>"
						+ "</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "<div style='width:10%; float:left; margin:0; padding:0;'>"
								+ Main.game.getPlayer().getSlavesOwned().size()
							+ "</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(income)+"/day"
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(upkeep, "b", Colour.GENERIC_BAD)+"/day"
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(income-upkeep, "b", (income-upkeep<0?Colour.GENERIC_BAD:Colour.TEXT))+"/day"
							+"</div>"
							+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedIncome(), "b")
							+ "</div>"
							+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedUpkeep(), "b", Colour.GENERIC_BAD)
							+ "</div>"
							+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ (Main.game.getOccupancyUtil().getGeneratedBalance()<0
										? UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedBalance(), "b", Colour.GENERIC_BAD)
										: UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedBalance(), "b"))
							+ "</div>"
						+ "</div>"
					+"</div>");
			
			// Logs:
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Activity Log</h6>"
									+ "<div id='PREVIOUS_DAY' class='normal-button' style='width:15%; margin-right:8px;'>Previous</div>"
									+ "<b>Day: "+dayNumber+(dayNumber==Main.game.getDayNumber()?" (Today)":"")+(dayNumber==Main.game.getDayNumber()-1?" (Yesterday)":"")+"</b>"
									+ "<div id='NEXT_DAY' class='normal-button' style='width:15%; margin-left:8px;'>Next</div>"

						
						+ "<div class='container-full-width' style='text-align:center; margin-bottom:0;'>"
							+ "<div style='width:10%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Time"
							+ "</div>"
							+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Slave"
							+ "</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "Event"
							+"</div>"
							+ "<div style='float:left; width:40%; font-weight:bold; margin:0; padding:0;'>"
								+ "Description"
							+"</div>"
							+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
								+ "Effects"
							+"</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='text-align:center; margin-bottom:0;'>");
			
			int count=0;
			if(Main.game.getSlaveryEventLog().get(dayNumber)!=null) {
				for(SlaveryEventLogEntry entry : Main.game.getSlaveryEventLog().get(dayNumber)) {
					if(count%2==0) {
						UtilText.nodeContentSB.append("<div class='container-full-width inner' style='background:"+Colour.BACKGROUND.toWebHexString()+";'>");
					} else {
						UtilText.nodeContentSB.append("<div class='container-full-width inner' style='background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'>");
					}
					
					UtilText.nodeContentSB.append(
								"<div style='width:10%; float:left; margin:0; padding:0;'>"
									+ String.format("%02d", entry.getTime()) + ":00<br/>"
								+ "</div>"
								+ "<div style='width:15%; float:left; margin:0; padding:0;'>"
									 + entry.getSlaveName()
								+ "</div>"
								+ "<div style='width:15%; float:left; margin:0; padding:0;'>"
									+ entry.getName()
								+ "</div>"
								+ "<div style='width:60%; float:left;  margin:0; padding:0;'>"
									+ entry.getDescription()
								+ "</div>"
								+ "</div>");
//								+ "<div style='width:20%; float:left;  margin:0; padding:0;'>");
//					
//					boolean effectsAdded = false;
//					if(entry.getEffects()!=null) {
//						for(String s : entry.getEffects()) {
//							if(!s.isEmpty()) {
//								UtilText.nodeContentSB.append(s+"<br/>");
//								effectsAdded = true;
//							}
//						}
//					}
//					if(!effectsAdded) {
//						UtilText.nodeContentSB.append("[style.colourDisabled(-)]");
//					}
//					
//					UtilText.nodeContentSB.append("</div>"
//							+"</div>");
					count++;
				}
			}
			if(count==0) {
				UtilText.nodeContentSB.append("<div class='container-full-width inner' style='background:"+Colour.BACKGROUND.toWebHexString()+";'>[style.colourDisabled(No events for this day...)]</div>");
			}
			
			UtilText.nodeContentSB.append("</div>"
					+ "</div>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getSlaveryResponse(index);
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
	};
	
	
	public static final DialogueNodeOld ROOM_MANAGEMENT = new DialogueNodeOld("Room Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			Cell cell = Main.game.getPlayerCell();
			GenericPlace place = cell.getPlace();
			List<NPC> charactersPresent = Main.game.getCharactersTreatingCellAsHome(cell);
			float affectionChange = place.getHourlyAffectionChange();
			float obedienceChange = place.getHourlyObedienceChange();
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+Colour.GENERIC_GOOD.toWebHexString()+"; text-align:center;'>Current Location</h6>"
						+ getRoomHeader()
						+ getRoomEntry(!place.isAbleToBeUpgraded(), true, cell, charactersPresent, affectionChange, obedienceChange)
					+"</div>");

			// Lilaya's home:
			UtilText.nodeContentSB.append(getWorldRooms(WorldType.LILAYAS_HOUSE_GROUND_FLOOR));
			UtilText.nodeContentSB.append(getWorldRooms(WorldType.LILAYAS_HOUSE_FIRST_FLOOR));

			// Slaver alley:
			UtilText.nodeContentSB.append(getWorldRooms(WorldType.SLAVER_ALLEY));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Return to the previous screen.", OCCUPANT_OVERVIEW);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
	};
	
	private static String getRoomHeader() {
		return "<div class='container-full-width' style='margin-bottom:0;'>"
					+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Room Name"
					+ "</div>"
					+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Occupants"
					+ "</div>"
					+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
						+ "<b>Capacity</b>"
					+"</div>"
					+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
						+ "<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection</b>"
					+"</div>"
					+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
						+ "<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
					+"</div>"
					+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
						+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Upkeep</b>"
					+"</div>"
					+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
						+ "Actions"
					+"</div>"
				+ "</div>";
	}
	
	private static String getRoomEntry(boolean disabled, boolean currentLocation, Cell cell, List<NPC> occupants, float affectionChange, float obedienceChange) {
		miscDialogueSB.setLength(0);
		
		GenericPlace place = cell.getPlace();
		
		miscDialogueSB.append(
				"<div class='container-full-width inner' style='margin-bottom:4px; margin-top:4px; "+(!occupants.isEmpty()?"background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
						+ "<div style='width:15%; float:left; margin:0; padding:0;'>"
							+ "<span style='color:"+place.getColour().toWebHexString()+";'>"+place.getName()+"</span><br/>"
						+ "</div>"
						+ "<div style='width:20%; float:left; margin:0; padding:0;'>");
		
		int i=0;
		for(NPC occupant : occupants) {
			if(occupant.isSlave()) {
				miscDialogueSB.append("<b style='color:"+occupant.getFemininity().getColour().toWebHexString()+";'>"+occupant.getName()+"</b>"+(i+1==occupants.size()?"":"<br/>"));
				i++;
			}
		}
		if(i==0) {
			miscDialogueSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Empty</b>");
		}
		
		miscDialogueSB.append("</div>"
						+ "<div style='float:left; width:10%; margin:0; padding:0;'>"
							+ i+"/"+place.getCapacity()
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<span style='color:"+(affectionChange==0?Colour.BASE_GREY:(affectionChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/hour"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<span style='color:"+(obedienceChange==0?Colour.BASE_GREY:(obedienceChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
								+decimalFormat.format(obedienceChange)+"</span>/hour"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ (disabled
									?"[style.colourDisabled(N/A)]"
									:(place.getUpkeep()>0
										?UtilText.formatAsMoney(-place.getUpkeep(), "span", Colour.GENERIC_BAD)
										:(place.getUpkeep()==0
											?UtilText.formatAsMoney(-place.getUpkeep(), "span", Colour.TEXT_GREY)
											:UtilText.formatAsMoney(-place.getUpkeep(), "span", Colour.GENERIC_GOOD))))+"/day"
						+"</div>"
						+ "<div style='float:left; width:10%; margin:0 auto; padding:0; display:inline-block; text-align:center;'>"
							+ (disabled
									?"<div id='"+cell.getId()+(currentLocation?"_PRESENT":"")+"_DISABLED' class='square-button solo disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveInspectDisabled()+"</div></div>"
									:"<div id='"+cell.getId()+(currentLocation?"_PRESENT":"")+"' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveInspect()+"</div></div>")
						+"</div>"
					+ "</div>");
		
		return miscDialogueSB.toString();
	}
	
	private static List<Cell> importantCells = new ArrayList<>();
	
	public static void resetImportantCells() {
		importantCells = new ArrayList<>();
	}
	
	public static List<Cell> getImportantCells() {
		if(importantCells.isEmpty()) {
			WorldType[] importantWorlds = new WorldType[] {WorldType.LILAYAS_HOUSE_GROUND_FLOOR, WorldType.LILAYAS_HOUSE_FIRST_FLOOR};
			for(WorldType wt : importantWorlds) {
				Cell[][] cellGrid = Main.game.getWorlds().get(wt).getCellGrid();
				for(int i = 0; i< cellGrid.length; i++) {
					for(int j = 0; j < cellGrid[0].length; j++) {
						if(cellGrid[i][j].getPlace().getPlaceType()!=PlaceType.LILAYA_HOME_CORRIDOR
								&& cellGrid[i][j].getPlace().getPlaceType()!=PlaceType.GENERIC_IMPASSABLE) {
							importantCells.add(cellGrid[i][j]);
						}
					}
				}
			}
		}
		
		return importantCells;
	}
	
	
	
	private static String getWorldRooms(WorldType worldType) {
		StringBuilder worldRoomSB = new StringBuilder();
		
		worldRoomSB.append(
				"<div class='container-full-width' style='text-align:center;'>"
					+ "<h6 style='color:"+worldType.getColour().toWebHexString()+"; text-align:center;'>"+worldType.getName()+"</h6>"
					+ getRoomHeader());
		
		Cell[][] cellGrid = Main.game.getWorlds().get(worldType).getCellGrid();
		List<Cell> sortingCells = new ArrayList<>();
		for(int i = 0; i< cellGrid.length; i++) {
			for(int j = 0; j < cellGrid[0].length; j++) {
				if(!cellGrid[i][j].getPlace().getPlaceUpgrades().isEmpty()) {
//					importantCells.add(cellGrid[i][j]);
					sortingCells.add(cellGrid[i][j]);
				}
			}
		}
		
		sortingCells.sort(Comparator.comparing(Cell::getPlaceName));

		for(Cell c : sortingCells) {
			GenericPlace place = c.getPlace();
			worldRoomSB.append(getRoomEntry(!place.isAbleToBeUpgraded(), false, c, Main.game.getCharactersTreatingCellAsHome(c), place.getHourlyAffectionChange(), place.getHourlyObedienceChange()));
		}
		
		worldRoomSB.append("</div>");
		
		return worldRoomSB.toString();
	}
	
	
	public static Cell cellToInspect;
	
	public static final DialogueNodeOld ROOM_UPGRADES = new DialogueNodeOld("Room Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return cellToInspect.getPlace().getName()+" Management";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
							+ "<h6 style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>Overview (Total Values for this Room)</h6>"
							+"<div class='container-full-width' style='margin-bottom:0;'>"
								+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
									+ "Name"
								+ "</div>"
								+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
									+ "Occupants"
								+ "</div>"
								+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
									+ "<b>Capacity</b>"
								+"</div>"
								+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
									+ "<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection</b>"
								+"</div>"
								+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
									+ "<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
								+"</div>"
								+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
									+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Upkeep</b>"
								+"</div>"
							+ "</div>");
			
			
			GenericPlace place = cellToInspect.getPlace();
			float affectionChange = place.getHourlyAffectionChange();
			float obedienceChange = place.getHourlyObedienceChange();
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width inner' style='margin-bottom:0;'>"
							+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "<form style='float:left; width:85%; margin:0; padding:0;'><input type='text' id='nameInput' value='"+ UtilText.parseForHTMLDisplay(cellToInspect.getPlace().getName())+ "' style='width:100%; margin:0; padding:0;'></form>"
								+ "<div class='SM-button' id='rename_room_button' style='float:left; width:15%; height:28px; line-height:28px; margin:0; padding:0;'>"
									+ "&#10003;"
								+ "</div>"
							+ "</div>"
							+ "<div style='width:20%; float:left; margin:0; padding:0;'>");
			
			int i=0;
			List<NPC> occupants = Main.game.getCharactersTreatingCellAsHome(cellToInspect);
			for(NPC occupant : occupants) {
				if(occupant.isSlave()) {
					UtilText.nodeContentSB.append("<b style='color:"+occupant.getFemininity().getColour().toWebHexString()+";'>"+occupant.getName()+"</b>"+(i+1==occupants.size()?"":"<br/>"));
					i++;
				}
			}
			if(i==0) {
				UtilText.nodeContentSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Empty</b>");
			}
			
			
			UtilText.nodeContentSB.append(
					"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ i+"/"+place.getCapacity()
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ "<span style='color:"+(affectionChange==0?Colour.BASE_GREY:(affectionChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
									+decimalFormat.format(affectionChange)+"</span>/hour"
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ "<span style='color:"+(obedienceChange==0?Colour.BASE_GREY:(obedienceChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
									+decimalFormat.format(obedienceChange)+"</span>/hour"
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ (place.getUpkeep()>0
											?UtilText.formatAsMoney(-place.getUpkeep(), "span", Colour.GENERIC_BAD)
											:UtilText.formatAsMoney(-place.getUpkeep(), "span", Colour.GENERIC_GOOD))+"/day"
							+"</div>"
						+ "</div>"
						+ "</div>");
			
			
			// Normal upgrades:
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
											+ "<h6 style='color:"+Colour.GENERIC_GOOD.toWebHexString()+"; text-align:center;'>Modifications</h6>"
											+ getRoomUpgradeHeader());
			
			List<PlaceUpgrade> coreUpgrades = new ArrayList<>();
			for(PlaceUpgrade upgrade : place.getPlaceType().getAvailablePlaceUpgrades(place.getPlaceUpgrades())) {
				if(upgrade.isCoreRoomUpgrade()) {
					coreUpgrades.add(upgrade);
				} else {
					UtilText.nodeContentSB.append(getUpgradeEntry(cellToInspect, upgrade));
					i++;
				}
			}
			if(i==0) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width inner' style='background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'>"
								+ "<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No Modifications Available</b>"
						+ "</div>");
			}
			
			UtilText.nodeContentSB.append("</div>");
			
			// Core upgrades:
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<h6 style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Core Modifications</h6>"
					+"<p><i>Purchasing a [style.boldArcane(core modification)] will remove [style.boldBad(all)] other modifications in this room!</i></p>"
					+ getRoomUpgradeHeader());

			
//			for (PlaceUpgrade upgrade : place.getPlaceUpgrades()) {
//				if(upgrade.isCoreRoomUpgrade()) {
//					UtilText.nodeContentSB.append(getUpgradeEntry(cellToInspect, upgrade));
//				}
//			}
			
			i = 0;
			for (PlaceUpgrade upgrade : coreUpgrades) {
				UtilText.nodeContentSB.append(getUpgradeEntry(cellToInspect, upgrade));
				i++;
			}
			if(i==0) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width inner' style='background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'>"
								+ "<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No Core Modifications Available</b>"
						+ "</div>");
			}
			
			UtilText.nodeContentSB.append("</div>"
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 5) {
				if(Main.game.getOccupancyUtil().getGeneratedBalance()==0) {
					return new Response("Collect: "+UtilText.formatAsMoneyUncoloured(Main.game.getOccupancyUtil().getGeneratedBalance(), "span"), "Your current balance is 0...",  null);
					
				} else if(Main.game.getOccupancyUtil().getGeneratedBalance()>0) {
					return new Response("Collect: "+UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedBalance(), "span"),
							"Collect the money that you've earned through your slaves' activities.",  ROOM_UPGRADES) {
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getCurrentDialogueNode();
						}
						@Override
						public void effects() {
							Main.game.getOccupancyUtil().payOutBalance();
						}
					};
					
				} else {
					if(Main.game.getPlayer().getMoney()<Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance())) {
						return new Response("Pay: "+UtilText.formatAsMoneyUncoloured(Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance()), "span"),
								"You don't have enough money to pay off the accumulated debt from the upkeep of your slaves and rooms.",  null);
					}
					
					return new Response("Pay: "+UtilText.formatAsMoney(Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance()), "span", Colour.GENERIC_BAD),
							"Pay off the accumulated debt from the upkeep of your slaves and rooms.",  ROOM_UPGRADES) {
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getCurrentDialogueNode();
						}
						@Override
						public void effects() {
							Main.game.getOccupancyUtil().payOutBalance();
						}
					};
				}
				
			} else if(index==0) {
				return new Response("Back", "Return to the previous screen.", ROOM_UPGRADES) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
	};
	


	public static final DialogueNodeOld ROOM_UPGRADES_MANAGEMENT = new DialogueNodeOld("Room Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return cellToInspect.getPlace().getName()+" Management";
		}

		@Override
		public String getContent() {
			return ROOM_UPGRADES.getContent();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 5) {
				if(Main.game.getOccupancyUtil().getGeneratedBalance()==0) {
					return new Response("Collect: "+UtilText.formatAsMoneyUncoloured(Main.game.getOccupancyUtil().getGeneratedBalance(), "span"), "Your current balance is 0...",  null);
					
				} else if(Main.game.getOccupancyUtil().getGeneratedBalance()>0) {
					return new Response("Collect: "+UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedBalance(), "span"),
							"Collect the money that you've earned through your slaves' activities.",  ROOM_UPGRADES_MANAGEMENT) {
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getCurrentDialogueNode();
						}
						@Override
						public void effects() {
							Main.game.getOccupancyUtil().payOutBalance();
						}
					};
					
				} else {
					if(Main.game.getPlayer().getMoney()<Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance())) {
						return new Response("Pay: "+UtilText.formatAsMoneyUncoloured(Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance()), "span"),
								"You don't have enough money to pay off the accumulated debt from the upkeep of your slaves and rooms.",  null);
					}
					
					return new Response("Pay: "+UtilText.formatAsMoney(Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance()), "span", Colour.GENERIC_BAD),
							"Pay off the accumulated debt from the upkeep of your slaves and rooms.",  ROOM_UPGRADES_MANAGEMENT) {
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getCurrentDialogueNode();
						}
						@Override
						public void effects() {
							Main.game.getOccupancyUtil().payOutBalance();
						}
					};
				}
				
			} else if(index==0) {
				return new Response("Back", "Return to the previous screen.", ROOM_MANAGEMENT);
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
	};
	
	
	private static String getRoomUpgradeHeader() {
		return "<div class='container-full-width' style='margin-bottom:0;'>"
					+ "<div style='width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Upgrade"
					+ "</div>"
					+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
						+ "Capacity"
					+"</div>"
					+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
						+ "<span style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection</span>"
					+"</div>"
					+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
						+ "<span style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</span>"
					+"</div>"
					+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
						+ "<span style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Upkeep</span>"
					+"</div>"
					+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
						+ "<span style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Cost</span>"
					+"</div>"
					+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
						+ "Actions"
					+"</div>"
				+ "</div>";
	}
	
	private static String getUpgradeEntry(Cell cell, PlaceUpgrade upgrade) {
		miscDialogueSB.setLength(0);
		GenericPlace place = cell.getPlace();
		float affectionChange = upgrade.getHourlyAffectionGain();
		float obedienceChange = upgrade.getHourlyObedienceGain();
		boolean owned = place.getPlaceUpgrades().contains(upgrade);
		boolean availableForPurchase = upgrade.isPrerequisitesMet(place) && upgrade.isAvailable(cell) && (owned?Main.game.getPlayer().getMoney()>=upgrade.getRemovalCost():Main.game.getPlayer().getMoney()>=upgrade.getInstallCost());
		boolean canBuy = availableForPurchase;
		
		miscDialogueSB.append(
				"<div class='container-full-width inner' style='margin-bottom:4px; margin-top:4px;"+(owned?"background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
						+ "<div style='width:5%; float:left; margin:0; padding:0;'>"
							+ "<div class='title-button no-select' id='ROOM_MOD_INFO_"+upgrade+"' style='position:relative; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
						+ "</div>"
						+ "<div style='width:25%; float:left; margin:0; padding:0;'>"
							+ (owned
									?"<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(upgrade.getName())+"</b>"
									:(!availableForPurchase
											?"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+Util.capitaliseSentence(upgrade.getName())+"</b>"
											:"<b>"+Util.capitaliseSentence(upgrade.getName())+"</b>"))
//							+ "<div class='item-inline' id='ROOM_MOD_INFO_"+upgrade+"' style='float:right;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
//							+"<div class='overlay' id=''></div>"
						+ "</div>"
						+ "<div style='width:10%; float:left; margin:0; padding:0;'>"
							+ (upgrade.getCapacity()>0
									?"<b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>+"+upgrade.getCapacity()+"</b>"
									:(upgrade.getCapacity()<0
											?"<b style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>"+upgrade.getCapacity()+"</b>"
											:"[style.colourDisabled(0)]"))
						+ "</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ (affectionChange>0
									?"<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>+"+decimalFormat.format(affectionChange)+ "</b>/hour"
									:(affectionChange<0
											?"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(affectionChange)+ "</b>/hour"
											:"[style.colourDisabled(0)]/hour"))
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ (obedienceChange>0
									?"<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>+"+decimalFormat.format(obedienceChange)+ "</b>/hour"
									:(obedienceChange<0
											?"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(obedienceChange)+ "</b>/hour"
											:"[style.colourDisabled(0)]/hour"))
						+"</div>"
						+ "<div style='float:left; width:10%; margin:0; padding:0;'>"
							+ (upgrade.getUpkeep()>0
									?UtilText.formatAsMoney(upgrade.getUpkeep(), "b", Colour.GENERIC_BAD)
									:UtilText.formatAsMoney(upgrade.getUpkeep(), "b", Colour.GENERIC_GOOD))+"/day"
						+"</div>"
						+ "<div style='float:left; width:10%; margin:0; padding:0;'>"
							+ (owned
									?(upgrade.getRemovalCost()<0
											?UtilText.formatAsMoney(upgrade.getRemovalCost(), "b", Colour.GENERIC_GOOD)
											:(upgrade.getRemovalCost() < Main.game.getPlayer().getMoney()
													?UtilText.formatAsMoney(upgrade.getRemovalCost(), "b")
													:UtilText.formatAsMoney(upgrade.getRemovalCost(), "b", Colour.GENERIC_BAD)))
									:(upgrade.getInstallCost()<0
											?UtilText.formatAsMoney(upgrade.getInstallCost(), "b", Colour.GENERIC_GOOD)
											:(upgrade.getInstallCost() < Main.game.getPlayer().getMoney()
													?UtilText.formatAsMoney(upgrade.getInstallCost(), "b")
													:UtilText.formatAsMoney(upgrade.getInstallCost(), "b", Colour.GENERIC_BAD))))
						+"</div>"
						+ "<div style='float:left; width:10%; margin:0 auto; padding:0; display:inline-block; text-align:center;'>");
		
		if(owned) {
			if(Main.game.getPlayer().getMoney()<upgrade.getRemovalCost() || upgrade.isCoreRoomUpgrade()) {
				miscDialogueSB.append("<div id='"+upgrade+"_SELL_DISABLED' class='square-button solo disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionSellDisabled()+"</div></div>");
			} else {
				miscDialogueSB.append("<div id='"+upgrade+"_SELL' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionSell()+"</div></div>");
			}
			
		} else {
			if(Main.game.getPlayer().getMoney()<upgrade.getInstallCost() || Main.game.getOccupancyUtil().getGeneratedBalance()<0) {
				canBuy = false;
			}
			if(canBuy) {
				if(!upgrade.getPrerequisites().isEmpty()) {
					for(PlaceUpgrade prereq : upgrade.getPrerequisites()) {
						if(!place.getPlaceUpgrades().contains(prereq)) {
							canBuy = false;
							break;
						}
					}
				}
			}
			
			if(canBuy) {
				miscDialogueSB.append("<div id='"+upgrade+"_BUY' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBuy()+"</div></div>");
			} else {
				miscDialogueSB.append("<div id='"+upgrade+"_BUY_DISABLED' class='square-button solo disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBuyDisabled()+"</div></div>");
			}
		}
		
		miscDialogueSB.append(
				"</div>"
//					+ "<p>"
//						+ "<i>"
//							+(!owned
//								?"[style.colourDisabled("+upgrade.getDescriptionForPurchase()+")]"
//								:upgrade.getDescriptionAfterPurchase())
//						+"</i>"
//					+ "</p>"
//					+  (upgrade.isCoreRoomUpgrade() && !owned
//							?"<p>This is a [style.boldArcane(core modification)], and will [style.boldBad(remove all other modifications in this room when purchased)].</p>"
//							:"")
					);
		
		if(!canBuy) {
			miscDialogueSB.append("<p>"
				+ "<i>"
				+ "[style.colourBad("+ getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, upgrade)+")]"
				+"</i>"
			+ "</p>");
		}
		
		miscDialogueSB.append("</div>");
		
		return miscDialogueSB.toString();
	}
	
	private static StringBuilder purchaseAvailability = new StringBuilder();
	public static String getPurchaseAvailabilityTooltipText(Cell cell, PlaceUpgrade upgrade) {
		GenericPlace place = cell.getPlace();
		boolean owned = place.getPlaceUpgrades().contains(upgrade);
		
		purchaseAvailability.setLength(0);
		
		if(owned) {
			if(Main.game.getPlayer().getMoney()<upgrade.getRemovalCost()) {
				purchaseAvailability.append("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You cannot afford to remove this modification.</span>");
			}
			
		} else {
			if(Main.game.getOccupancyUtil().getGeneratedBalance()<0) {
				purchaseAvailability.append("<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You cannot purchase any modifications while your slavery balance is negative.</b>");
			}
			
			if(Main.game.getPlayer().getMoney()<upgrade.getInstallCost()) {
				purchaseAvailability.append("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You cannot afford this modification.</span>");
			}
			
			if(!upgrade.getPrerequisites().isEmpty()) {
				purchaseAvailability.append("You need to purchase the following first:");
				for(PlaceUpgrade prereq : upgrade.getPrerequisites()) {
					if(place.getPlaceUpgrades().contains(prereq)) {
						purchaseAvailability.append("<br/><span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>"+prereq.getName()+"</span>");
					} else {
						purchaseAvailability.append("<br/><span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+prereq.getName()+"</span>");
					}
				}
			}
		}
		
		String availabilityDescription = upgrade.getAvailabilityDescription(OccupantManagementDialogue.cellToInspect);
		if(availabilityDescription!=null && availabilityDescription.length()>0) {
			purchaseAvailability.append("<br/><span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+availabilityDescription+"</span>");
		}
		
		return purchaseAvailability.toString();
	}
	
	
	private static final DialogueNodeOld SLAVE_LIST = new DialogueNodeOld("Slave & Occupant Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().getSlaveTrader()!=null) {
				// Append for sale first:
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+"; text-align:center;'>Slaves For Sale</h6>"
						
						+ getSlaveryHeader());
				int i=0;
				List<NPC> npcsPresent = new ArrayList<>(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()));
				
				npcsPresent.removeIf((npc) -> !npc.isSlave());
				
				for(NPC slave : npcsPresent) {
					if(!slave.getOwner().isPlayer()) {
						AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(slave.getAffection(Main.game.getPlayer()));
						ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(slave.getObedienceValue());
						float affectionChange = slave.getDailyAffectionChange();
						float obedienceChange = slave.getDailyObedienceChange();
						GenericPlace place = Main.game.getPlayerCell().getPlace();
						
						UtilText.nodeContentSB.append(getSlaveryEntry(false, place, slave, affection, affectionChange, obedience, obedienceChange, i%2==0));
						i++;
					}
				}
				if(i==0) {
					UtilText.nodeContentSB.append("<div class='container-full-width inner'><h4 style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No slaves for sale!</h4></div>");
				}
				UtilText.nodeContentSB.append("</div>");
				
			} else { // Don't show occupants if trading slaves.
				// Friendly occupants:
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+Colour.GENERIC_GOOD.toWebHexString()+"; text-align:center;'>Friendly Occupants</h6>"
						
						+ getOccupantHeader());
				
				if(Main.game.getPlayer().getFriendlyOccupants().isEmpty()) {
					UtilText.nodeContentSB.append(
							"<div class='container-full-width' style='text-align:center;'>"
									+"<p style='color:"+Colour.BASE_GREY.toWebHexString()+";'>You do not have anyone living with you...</p>"
							+ "</div>");
					
				} else {
					int i = 0;
					for(String id : Main.game.getPlayer().getFriendlyOccupants()) {
						NPC occupant = (NPC) Main.game.getNPCById(id);
						if(!occupant.equals(Main.game.getGenericAndrogynousNPC()) && occupant.getHomeWorldLocation()!=WorldType.DOMINION) {
//							System.out.println(occupant.getName());
							AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(occupant.getAffection(Main.game.getPlayer()));
							ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(occupant.getObedienceValue());
							float affectionChange = occupant.getDailyAffectionChange();
							float obedienceChange = occupant.getDailyObedienceChange();
							GenericPlace place = Main.game.getPlayerCell().getPlace();
							
							UtilText.nodeContentSB.append(getOccupantEntry(place, occupant, affection, affectionChange, obedience, obedienceChange, i%2==0));
							i++;
						}
					}
				}
				
				UtilText.nodeContentSB.append(
						"</div>");
			}
			
			
			// Your slaves:
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<h6 style='color:"+Colour.GENERIC_GOOD.toWebHexString()+"; text-align:center;'>Slaves Owned</h6>"
					
					+ getSlaveryHeader());
			
			if(Main.game.getPlayer().getSlavesOwned().isEmpty()) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='text-align:center;'>"
								+"<p style='color:"+Colour.BASE_GREY.toWebHexString()+";'>You do not own any slaves...</p>"
						+ "</div>");
				
			} else {
				int i = 0;
				for(String id : Main.game.getPlayer().getSlavesOwned()) {
					NPC slave = (NPC) Main.game.getNPCById(id);
					AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(slave.getAffection(Main.game.getPlayer()));
					ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(slave.getObedienceValue());
					float affectionChange = slave.getDailyAffectionChange();
					float obedienceChange = slave.getDailyObedienceChange();
					GenericPlace place = Main.game.getPlayerCell().getPlace();
					
					UtilText.nodeContentSB.append(getSlaveryEntry(true, place, slave, affection, affectionChange, obedience, obedienceChange, i%2==0));
					i++;
				}
			}
			
			UtilText.nodeContentSB.append(
					"</div>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(characterSelected()!=null && characterSelected().isSlave()) {
				if (index == 1) {
					if(characterSelected() == null) {
						return new Response("Inspect", "You haven't selected anyone...", null);
						
					}
					return new Response("Inspect", "Enter the slave management screen.", SLAVE_MANAGEMENT_INSPECT);
					
				} else if (index == 2) {
					if(characterSelected() == null) {
						return new Response("Job", "You haven't selected anyone...", null);
						
					} else if(!characterSelected().getOwner().isPlayer()) {
						return new Response("Job", "You cannot manage the job of a slave you do not own!", null);
					}
					return new Response("Job", "Set this slave's job and work hours.", SLAVE_MANAGEMENT_JOBS);
					
				} else if (index == 3) {
					if(characterSelected() == null) {
						return new Response("Permissions", "You haven't selected anyone...", null);
						
					} else if(!characterSelected().getOwner().isPlayer()) {
						return new Response("Permissions", "You cannot manage the permissions of a slave you do not own!", null);
					}
					return new Response("Permissions", "Set this slave's permissions.", SLAVE_MANAGEMENT_PERMISSIONS);
					
				} else if (index == 4) {
					if(characterSelected() == null) {
						return new Response("Inventory", "You haven't selected anyone...", null);
						
					} else if(!characterSelected().getOwner().isPlayer()) {
						return new Response("Inventory", "You cannot manage the inventory of a slave you do not own!", null);
					}
					
					if(characterSelected().getOwner().isPlayer()) {
						return new ResponseEffectsOnly("Inventory", UtilText.parse(characterSelected(), "Manage [npc.namePos] inventory.")){
							@Override
							public void effects() {
								Main.mainController.openInventory(characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
							}
						};
					} else {
						return new Response("Inventory", UtilText.parse(characterSelected(), "You can't manage [npc.namePos] inventory, as you don't own [npc.herHim]!"), null);
					}
					
				} else if(index == 5) {
	
					if(characterSelected() == null) {
						return new Response("Send to Kate", "You haven't selected anyone...", null);
						
					} else if(!characterSelected().getOwner().isPlayer()) {
						return new Response("Send to Kate", "You send slaves that you do not own to Kate!", null);
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
						return new Response("Send to Kate",
								"Send [npc.name] to Kate's beauty salon, 'Succubi's secrets', to get [npc.her] appearance changed.",
								OccupantManagementDialogue.SLAVE_MANAGEMENT_COSMETICS_HAIR) {
									@Override
									public void effects() {
										BodyChanging.setTarget(characterSelected());
									}
								};
					} else {
						return new Response("Send to Kate", "You haven't met Kate yet!", null);
					}
					
				} else if (index == 6) {
					if(characterSelected() == null) {
						return new Response("Perk Tree", "You haven't selected anyone...", null);
						
					} else if(!characterSelected().getOwner().isPlayer()) {
						return new Response("Perk Tree", "You can't manage the perks of slaves that you do not own!", null);
						
					} 
					return new Response("Perk Tree", "Spend your slave's perk points.", SLAVE_MANAGEMENT_PERKS);
					
				} else if(index == 0) {
					return new Response("Back", "Exit the slave management screen.", SLAVE_LIST) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
						}
						@Override
						public DialogueNodeOld getNextDialogue() {
							if(slaveListManagementOverview) {
								return SLAVE_LIST_MANAGEMENT;
							} else {
								return Main.game.getDefaultDialogueNoEncounter();
							}
						}
					};
					
				} else {
					return null;
				}
			
			} else { // Friendly occupant:
				if (index == 1) {
					if(characterSelected() == null) {
						return new Response("Inspect", "You haven't selected anyone...", null);
						
					}
					return new Response("Inspect", UtilText.parse(characterSelected(), "inspect [npc.name]."), SLAVE_MANAGEMENT_INSPECT);
					
				} else if (index == 2) {
					return new Response("Job", "You cannot manage the job of a free-willed occupant. This option is only available for slaves.", null);
					
				} else if (index == 3) {
					return new Response("Permissions", "You cannot manage the permissions of a free-willed occupant. This option is only available for slaves.", null);
					
				} else if (index == 4) {
					if(characterSelected() == null) {
						return new Response("Inventory", "You haven't selected anyone...", null);
						
					}
					return new ResponseEffectsOnly("Inventory", UtilText.parse(characterSelected(), "Manage [npc.namePos] inventory.")){
						@Override
						public void effects() {
							Main.mainController.openInventory(characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if(index == 5) {
	
					if(characterSelected() == null) {
						return new Response("Send to Kate", "You haven't selected anyone...", null);
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
						return new Response("Send to Kate",
								"Send [npc.name] to Kate's beauty salon, 'Succubi's secrets', to get [npc.her] appearance changed.",
								OccupantManagementDialogue.SLAVE_MANAGEMENT_COSMETICS_HAIR) {
									@Override
									public void effects() {
										BodyChanging.setTarget(characterSelected());
									}
								};
					} else {
						return new Response("Send to Kate", "You haven't met Kate yet!", null);
					}
					
				} else if (index == 6) {
					if(characterSelected() == null) {
						return new Response("Perk Tree", "You haven't selected anyone...", null);
						
					}
					return new Response("Perk Tree", "Assign [npc.namePos] perk points.", SLAVE_MANAGEMENT_PERKS);
					
				} else if(index == 0) {
					return new Response("Back", "Exit the occupant management screen.", SLAVE_LIST) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
						}
						@Override
						public DialogueNodeOld getNextDialogue() {
							if(slaveListManagementOverview) {
								return SLAVE_LIST_MANAGEMENT;
							} else {
								return Main.game.getDefaultDialogueNoEncounter();
							}
						}
					};
					
				} else {
					return null;
				}
			}
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
	};
	

	public static final DialogueNodeOld SLAVE_LIST_MANAGEMENT = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}

		@Override
		public String getContent() {
			return SLAVE_LIST.getContent();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("Back", "Exit the slave management screen.", OCCUPANT_OVERVIEW);
				
			} else {
				return SLAVE_LIST.getResponse(responseTab, index);
			}
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
	};
	
	private static String getSlaveryHeader() {
		return "<div class='container-full-width' style='margin-bottom:0;'>"
					+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ "Slave"
				+ "</div>"
				+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ "Location"
				+ "</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Value</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "Actions"
				+"</div>"
			+ "</div>";
	}
	
	private static String getOccupantHeader() {
		return "<div class='container-full-width' style='margin-bottom:0;'>"
					+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ "Occupant"
				+ "</div>"
				+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ "Location"
				+ "</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Value</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "Actions"
				+"</div>"
			+ "</div>";
	}
	
	private static String getSlaveryEntry(boolean slaveOwned, GenericPlace place, NPC slave, AffectionLevel affection, float affectionChange, ObedienceLevel obedience, float obedienceChange, boolean alternateBackground) {
		miscDialogueSB.setLength(0);
		
		miscDialogueSB.append(
				"<div class='container-full-width inner' style='margin-bottom:0;"+(alternateBackground?"background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
						+ "<div style='width:20%; float:left; margin:0; padding:0;'>"
							+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName()+"</b><br/>"
							+ "<span style='color:"+slave.getRace().getColour().toWebHexString()+";'>"
								+Util.capitaliseSentence((slave.isFeminine()?slave.getSubspecies().getSingularFemaleName(slave):slave.getSubspecies().getSingularMaleName(slave)))+"</span><br/>"
							+ "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slave.getGender().getName())+"</span>"
						+ "</div>"
						+ "<div style='width:20%; float:left; margin:0; padding:0;'>"
							+ "<b style='color:"+slave.getLocationPlace().getColour().toWebHexString()+";'>"+slave.getLocationPlace().getName()+"</b>"
							+",<br/>"
							+ "<span style='color:"+slave.getWorldLocation().getColour().toWebHexString()+";'>"+slave.getWorldLocation().getName()+"</span>"
						+ "</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+slave.getAffection(Main.game.getPlayer())+ "</b>"
							+ "<br/><span style='color:"+(affectionChange==0?Colour.BASE_GREY:(affectionChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+affection.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+slave.getObedienceValue()+ "</b>"
							+ "<br/><span style='color:"+(obedienceChange==0?Colour.BASE_GREY:(obedienceChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
								+decimalFormat.format(obedienceChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+obedience.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(obedience.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ (Main.game.getDialogueFlags().getSlaveTrader()!=null
								?(slaveOwned
										?UtilText.formatAsMoney((int) (slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()), "b", Colour.GENERIC_ARCANE)
										:UtilText.formatAsMoney((int) (slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier()), "b", Colour.GENERIC_ARCANE))
								:UtilText.formatAsMoney(slave.getValueAsSlave()))+"<br/>"
							+ "<b>"+Util.capitaliseSentence(slave.getSlaveJob().getName(slave))+"</b><br/>"
							+ UtilText.formatAsMoney(slave.getSlaveJob().getFinalDailyIncomeAfterModifiers(slave))+"/day"
						+"</div>");
		
		if(slaveOwned) {
			miscDialogueSB.append("<div style='float:left; width:15%; margin:0 auto; padding:0; display:inline-block; text-align:center;'>"
					+ "<div id='"+slave.getId()+"' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveInspect()+"</div></div>"

					+ "<div id='"+slave.getId()+"_JOB' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveJob()+"</div></div>"

					+ "<div id='"+slave.getId()+"_PERMISSIONS' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlavePermissions()+"</div></div>"
					
					+"<div id='"+slave.getId()+"_INVENTORY' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon()+"</div></div>"
						
					+ "<div "+((place.getCapacity()<=Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).size())
								|| !place.isSlaveCell()
								|| (slave.getLocation().equals(Main.game.getPlayer().getLocation()) && slave.getWorldLocation().equals(Main.game.getPlayer().getWorldLocation()))
										?" id='"+slave.getId()+"_TRANSFER_DISABLED' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveTransferDisabled()+"</div></div>"
										:" id='"+slave.getId()+"_TRANSFER' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveTransfer()+"</div></div>"));
			
			if(Main.game.getDialogueFlags().getSlaveTrader()==null) {
				miscDialogueSB.append("<div id='"+slave.getId()+"_SELL_DISABLED' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionSellDisabled()+"</div></div>");
			} else {
				miscDialogueSB.append("<div id='"+slave.getId()+"_SELL' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionSell()+"</div></div>");
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
				miscDialogueSB.append("<div id='"+slave.getId()+"_COSMETICS' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveCosmetics()+"</div></div>");
			} else {
				miscDialogueSB.append("<div id='"+slave.getId()+"_COSMETICS_DISABLED' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveCosmeticsDisabled()+"</div></div>");
			}
			
		} else { // Slave trader's slave:
			miscDialogueSB.append("<div style='float:left; width:15%; margin:0 auto; padding:0; display:inline-block; text-align:center;'>"
					+ "<div id='"+slave.getId()+"_TRADER' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveInspect()+"</div></div>"

					+ "<div id='"+slave.getId()+"_TRADER_JOB' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveJobDisabled()+"</div></div>"

					+ "<div id='"+slave.getId()+"_TRADER_PERMISSIONS' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlavePermissionsDisabled()+"</div></div>"
					
					+"<div id='"+slave.getId()+"_TRADER_INVENTORY' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getInventoryIconDisabled()+"</div></div>"
						
					+ "<div id='"+slave.getId()+"_TRADER_TRANSFER' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveTransferDisabled()+"</div></div>");
			
			if(Main.game.getPlayer().getMoney() < ((int) (slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier()))) {
				miscDialogueSB.append("<div id='"+slave.getId()+"_BUY_DISABLED' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBuyDisabled()+"</div></div>");
			} else {
				miscDialogueSB.append("<div id='"+slave.getId()+"_BUY' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBuy()+"</div></div>");
			}
			
			miscDialogueSB.append("<div id='"+slave.getId()+"_TRADER_COSMETICS' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveCosmeticsDisabled()+"</div></div>");
		}
		
		miscDialogueSB.append("</div></div>");
		
		return miscDialogueSB.toString();
	}
	
	private static String getOccupantEntry(GenericPlace place, NPC occupant, AffectionLevel affection, float affectionChange, ObedienceLevel obedience, float obedienceChange, boolean alternateBackground) {
		miscDialogueSB.setLength(0);
		
		miscDialogueSB.append(
				"<div class='container-full-width inner' style='margin-bottom:0;"+(alternateBackground?"background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
						+ "<div style='width:20%; float:left; margin:0; padding:0;'>"
							+ "<b style='color:"+occupant.getFemininity().getColour().toWebHexString()+";'>"+occupant.getName()+"</b><br/>"
							+ "<span style='color:"+occupant.getRace().getColour().toWebHexString()+";'>"
								+Util.capitaliseSentence((occupant.isFeminine()?occupant.getSubspecies().getSingularFemaleName(occupant):occupant.getSubspecies().getSingularMaleName(occupant)))+"</span><br/>"
							+ "<span style='color:"+occupant.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(occupant.getGender().getName())+"</span>"
						+ "</div>"
						+ "<div style='width:20%; float:left; margin:0; padding:0;'>"
							+ (occupant.getWorldLocation()==WorldType.EMPTY
								?"<b style='color:"+Colour.BASE_GREY.toWebHexString()+";'>At Work</b>"
										+",<br/>"
										+ "<span style='color:"+WorldType.DOMINION.getColour().toWebHexString()+";'>"+WorldType.DOMINION.getName()+"</span>"
								:"<b style='color:"+occupant.getLocationPlace().getColour().toWebHexString()+";'>"+occupant.getLocationPlace().getName()+"</b>"
									+",<br/>"
									+ "<span style='color:"+occupant.getWorldLocation().getColour().toWebHexString()+";'>"+occupant.getWorldLocation().getName()+"</span>")
						+ "</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+occupant.getAffection(Main.game.getPlayer())+ "</b>"
							+ "<br/><span style='color:"+(affectionChange==0?Colour.BASE_GREY:(affectionChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+affection.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+occupant.getObedienceValue()+ "</b>"
							+ "<br/><span style='color:"+(obedienceChange==0?Colour.BASE_GREY:(obedienceChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
								+decimalFormat.format(obedienceChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+obedience.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(obedience.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<b>"+Util.capitaliseSentence(occupant.getSlaveJob().getName(occupant))+"</b><br/>"
							+ UtilText.formatAsMoney(occupant.getSlaveJob().getFinalDailyIncomeAfterModifiers(occupant))+"/day"
						+"</div>"
							
				+ "<div style='float:left; width:15%; margin:0 auto; padding:0; display:inline-block; text-align:center;'>"
				
				+ "<div id='"+occupant.getId()+"' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveInspect()+"</div></div>"

				+ "<div id='"+occupant.getId()+"_JOB' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveJobDisabled()+"</div></div>"

				+ "<div id='"+occupant.getId()+"_PERMISSIONS' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlavePermissionsDisabled()+"</div></div>"
				
				+"<div id='"+occupant.getId()+"_INVENTORY' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon()+"</div></div>"
					
				+ "<div "+((place.getCapacity()<=Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).size())
							|| place.isSlaveCell()
							|| occupant.getHomeWorldLocation()==WorldType.DOMINION
							|| (occupant.getLocation().equals(Main.game.getPlayer().getLocation()) && occupant.getWorldLocation().equals(Main.game.getPlayer().getWorldLocation()))
									?" id='"+occupant.getId()+"_TRANSFER_DISABLED' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveTransferDisabled()+"</div></div>"
									:" id='"+occupant.getId()+"_TRANSFER' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveTransfer()+"</div></div>"));
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
			miscDialogueSB.append("<div id='"+occupant.getId()+"_COSMETICS' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveCosmetics()+"</div></div>");
		} else {
			miscDialogueSB.append("<div id='"+occupant.getId()+"_COSMETICS_DISABLED' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveCosmeticsDisabled()+"</div></div>");
		}
		
		miscDialogueSB.append("</div></div>");
		
		return miscDialogueSB.toString();
	}
	
	private static StringBuilder headerSB = new StringBuilder();
	private static String getSlaveInformationHeader(NPC character) {
		headerSB.setLength(0);
		AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(character.getAffection(Main.game.getPlayer()));
		ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(character.getObedienceValue());
		float affectionChange = character.getDailyAffectionChange();
		float obedienceChange = character.getDailyObedienceChange();
		
		headerSB.append(
				"<div class='container-full-width' style='text-align:center;'>"
				
					// Core naming information:
					+"<div class='container-full-width' style='margin-bottom:0;'>"
						+ "<div style='width:50%; float:left; font-weight:bold; margin:0; padding:0;'>"
							+ "Slave's Name"
						+ "</div>"
						+ "<div style='width:50%; float:left; font-weight:bold; margin:0; padding:0;'>"
							+ UtilText.parse(character, "What [npc.she] calls you")
						+ "</div>"
					+ "</div>"
					+"<div class='container-full-width inner' style='padding:8px;'>"
						+ "<div style='width:49%; float:left; font-weight:bold; margin:0 1% 0 0; padding:0;'>"
							+ "<form style='float:left; width:90%; margin:0; padding:0;'><input type='text' id='slaveNameInput' value='"+ UtilText.parseForHTMLDisplay(character.getName())+ "' style='width:100%; margin:0; padding:0;'></form>"
							+ "<div class='SM-button' id='"+character.getId()+"_RENAME' style='float:left; width:9%; height:28px; line-height:28px; margin:0 0 0 1%; padding:0;'>"
								+ "&#10003;"
							+ "</div>"
						+ "</div>"
						+ "<div style='width:49%; float:left; font-weight:bold; margin:0 0 0 1%; padding:0;'>"
							+ "<form style='float:left; width:50%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(character.getPlayerPetName())+ "' style='width:100%; margin:0; padding:0;'></form>"
							+ "<div class='SM-button' id='"+character.getId()+"_CALLS_PLAYER' style='float:left; width:9%; height:28px; line-height:28px; margin:0 0 0 1%; padding:0;'>"
								+ "&#10003;"
							+ "</div>"
							+ " <div class='SM-button' id='GLOBAL_CALLS_PLAYER' style='float:left; width:39%; height:28px; line-height:28px; margin:0 0 0 1%; padding:0;'>"
								+ "All Slaves"
						+ "</div>"
					+ "</div>"
						
					// Extra core information:
					+"<div class='container-full-width' style='margin-bottom:0;'>"
						+ "<div style='width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
							+ "Location"
						+ "</div>"
						+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection</b>"
						+"</div>"
						+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
						+"</div>"
						+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Income</b>"
						+"</div>"
						+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
							+ "Value"
						+ "</div>"
					+ "</div>"
					+"<div class='container-full-width'>"
						+"<div style='width:30%; float:left; margin:0; padding:0;'>"
							+ "<b style='color:"+character.getLocationPlace().getColour().toWebHexString()+";'>"+character.getLocationPlace().getName()+"</b>"
							+",<br/>"
							+ "<span style='color:"+character.getWorldLocation().getColour().toWebHexString()+";'>"+character.getWorldLocation().getName()+"</span>"
						+ "</div>"
						+ "<div style='float:left; width:20%; margin:0; padding:0;'>"
							+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+character.getAffection(Main.game.getPlayer())+ "</b>" //TODO
							+ "<br/><span style='color:"+(affectionChange==0?Colour.BASE_GREY:(affectionChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+affection.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:20%; margin:0; padding:0;'>"
							+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedienceValue()+ "</b>"
							+ "<br/><span style='color:"+(obedienceChange==0?Colour.BASE_GREY:(obedienceChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
								+decimalFormat.format(obedienceChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+obedience.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(obedience.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ UtilText.formatAsMoney(character.getSlaveJob().getFinalDailyIncomeAfterModifiers(character))+"/day"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ UtilText.formatAsMoney(character.getValueAsSlave())
						+"</div>"
					+ "</div>");

		
		// Job:
		headerSB.append("<div class='container-half-width inner' style='width:50%; margin:0;'>"
				+ "<b>Job:</b> <b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>"+Util.capitaliseSentence(character.getSlaveJob().getName(character))+"</b><br/>");
		int count=0;
		for(SlaveJobSetting setting : character.getSlaveJobSettings()) {
			headerSB.append((count==0?"":", ")+"<span style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</span>");
			count++;
		}
		if(count>0) {
			headerSB.append(".");
		}
		headerSB.append("</div>");
		
		
		// Permissions:
		headerSB.append("<div class='container-half-width inner' style='width:50%; margin:0;'>"
				+ "<b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Permissions:</b><br/>");
		int permissionCount=0;
		for(SlavePermission permission : SlavePermission.values()) {
			for(SlavePermissionSetting setting : permission.getSettings()) {
				if(character.getSlavePermissionSettings().get(permission).contains(setting)) {
					headerSB.append((permissionCount==0?"":", ")+"<span style='color:"+permission.getColour().toWebHexString()+";'>"+setting.getName()+"</span>");
					permissionCount++;
				}
			}
		}
		headerSB.append(".</div>");
		
		
		headerSB.append("</div>"
				+ "</div>");
		
		return headerSB.toString();
	}
	
	
	/**
	 * <b>Use getSlaveryManagementDetailedDialogue(NPC slave) to initialise this!!!</b>
	 */
	public static final DialogueNodeOld SLAVE_MANAGEMENT_INSPECT = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "Inspecting [npc.Name]");
		}
		
		@Override
		public String getContent() {
			NPC character = characterSelected();
			
			UtilText.nodeContentSB.setLength(0);
			
			if(character.isSlave() && character.getOwner().isPlayer()) {
				UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			}
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
							+ "<h6 style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>Inspection</h6>"
							+ "<div class='container-full-width inner'>"
								+character.getCharacterInformationScreen()
							+"</div>"
					+"</div>"
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Inspect", UtilText.parse(characterSelected(), "You are already inspecting [npc.name]."), null);
			}
			
			return SLAVE_LIST.getResponse(responseTab, index);
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_JOBS = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.NamePos] Job");
		}
		
		@Override
		public String getContent() {
			NPC character = characterSelected();
			ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(character.getObedienceValue());
			float affectionChange = character.getDailyAffectionChange();
			float obedienceChange = character.getDailyObedienceChange();
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			
			// Job hours
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='text-align:center;'><span style='color:"+Colour.BASE_YELLOW.toWebHexString()+";'>Work Hours</span></h6>"
						+ "<div class='container-full-width inner'>");
							for(int i=0 ; i< 24; i++) {
								UtilText.nodeContentSB.append("<div class='normal-button hour "+(character.getWorkHours()[i]?" selected":"")+"' id='"+i+"_WORK'>"+String.format("%02d", i)+":00</div>");
							}
			
			UtilText.nodeContentSB.append(
							"<div style='width:100%;margin-top:8px;'><b>Presets</b></div>"
							+ "<div class='container-full-width inner' style='text-align:center;'>");
								for(SlaveJobHours preset : SlaveJobHours.values()) {
									UtilText.nodeContentSB.append("<div class='normal-button' id='"+preset+"_TIME' style='width:16%; margin:2px;'>"+preset.getName()+"</div>");
								}
			UtilText.nodeContentSB.append(
							"</div>"
						+ "</div>"
					+ "</div>");
			
			
			// Jobs:
			// TODO description box explaining that setting just influence random events
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>Jobs</h6>"
						+"<div class='container-full-width' style='margin-bottom:0;'>"
							+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Job"
							+ "</div>"
							+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b>Workers</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
							+"</div>"
							+ "<div style='float:left; width:30%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Income</b>"
										+ " (+<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience Bonus</b>)"
							+"</div>"
							+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
								+ "Actions"
							+"</div>"
						+ "</div>");
			
			for(SlaveJob job : SlaveJob.values()) {
				affectionChange = job.getAffectionGain(character);
				obedienceChange = job.getObedienceGain(character);
				int income = job.getFinalHourlyIncomeAfterModifiers(character);
				boolean isCurrentJob = character.getSlaveJob() == job;
				
				UtilText.nodeContentSB.append(
						"<div class='container-full-width inner' "+(isCurrentJob?"style='background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'":"")+">"
							+ "<div style='width:5%; float:left; margin:0; padding:0;'>"
								+ "<div class='title-button no-select' id='SLAVE_JOB_INFO_"+job+"' style='position:relative; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
							+ "</div>"
							+"<div style='width:15%; float:left; margin:0; padding:0;'>"
								+ (isCurrentJob
									? "[style.boldGood("+Util.capitaliseSentence(job.getName(character))+")]"
									: (job.isAvailable(character)
										? Util.capitaliseSentence(job.getName(character))
										: "[style.colourBad("+Util.capitaliseSentence(job.getName(character))+")]"))
							+ "</div>"
							+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
								+ Main.game.getPlayer().getSlavesWorkingJob(job)+"/"+(job.getSlaveLimit()<0?"&#8734;":job.getSlaveLimit())
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ (affectionChange>0
										?"<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>+"+decimalFormat.format(affectionChange)+ "</b>"
										:(affectionChange<0
												?"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(affectionChange)+ "</b>"
												:"[style.colourDisabled(0)]"))+"/hour"
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ (obedienceChange>0
										?"<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>+"+decimalFormat.format(obedienceChange)+ "</b>"
										:(obedienceChange<0
												?"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(obedienceChange)+ "</b>"
												:"[style.colourDisabled(0)]"))+"/hour"
							+"</div>"
							+ "<div style='float:left; width:30%; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(job.getIncome())
								+ " + ("
								+ (job.getObedienceIncomeModifier()>0
										?"[style.colourObedience("+job.getObedienceIncomeModifier()+")]"
										:"[style.colourDisabled("+job.getObedienceIncomeModifier()+")]")
										+ "*<span style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedienceValue()+"</span>)"
								+ " = "+UtilText.formatAsMoney(income, "b", (income>0?Colour.TEXT:Colour.GENERIC_BAD))+"/hour"
							+"</div>"
							+ "<div style='float:left; width:10%; margin:0; padding:0;'>"
								+ (!job.isAvailable(character) || isCurrentJob
										?"<div id='"+job+"_ASSIGN_DISABLED' class='square-button solo"+(!isCurrentJob?" disabled":"")+"'><div class='square-button-content'>"
											+(isCurrentJob?SVGImages.SVG_IMAGE_PROVIDER.getResponseOption():SVGImages.SVG_IMAGE_PROVIDER.getResponseOptionDisabled())+"</div></div>"
										:"<div id='"+job+"_ASSIGN' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseOptionDisabled()+"</div></div>")
							+"</div>"
							+ (!isCurrentJob && !job.isAvailable(character)
								?"<div class='container-full-width' style='background:transparent; margin:0;'>"
										+ "<i>"
											+ "[style.colourBad("+job.getAvailabilityText(character)+")]"
										+"</i>"
									+ "</div>"
								:""));
				
				// Job Settings:
				if(isCurrentJob) {
					for(SlaveJobSetting setting : job.getMutualSettings()) {
						boolean settingActive = character.getSlaveJobSettings().contains(setting);
						
						UtilText.nodeContentSB.append("<div class='container-full-width inner' style='"+(!isCurrentJob?"background:#1B1B1B;":"")+"'>"
														+"<div style='width:20%; float:left; margin:0; padding:0;"+(!isCurrentJob?"color:#777;":(settingActive?"color:"+Colour.GENERIC_GOOD.toWebHexString()+";":""))+"'>"
															+ setting.getName()
														+ "</div>"
														+"<div style='width:70%; float:left; margin:0; padding:0;"+(!settingActive?"color:#777;":"")+"'>"
															+ "<i>"+setting.getDescription()+"</i>"
														+ "</div>"
														+ "<div style='float:left; width:10%; margin:0; padding:0;'>"
															+ (!isCurrentJob
																	?"<div id='"+setting+"_DISABLED' class='square-button solo disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseUnlockedDisabled()+"</div></div>"
																	: (settingActive
																			?"<div id='"+setting+"_REMOVE' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseUnlocked()+"</div></div>"
																			:"<div id='"+setting+"_ADD' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseUnlockedDisabled()+"</div></div>"))
														+"</div>"
													+ "</div>");
					}
					
					for(Entry<String, List<SlaveJobSetting>> entry : job.getMutuallyExclusiveSettings().entrySet()) {
						UtilText.nodeContentSB.append("<div class='container-full-width inner' style='"+(!isCurrentJob?"background:#1B1B1B;":"")+"'>"
														+ "<div style='width:100%; float:left; margin:0; padding:0;"+(isCurrentJob?"":"color:#777;")+"'><b>"
															+ Util.capitaliseSentence(entry.getKey())
														+"</b></div>");
						
						for(SlaveJobSetting setting : entry.getValue()) {
							boolean settingActive = character.getSlaveJobSettings().contains(setting);
							
							UtilText.nodeContentSB.append("<div style='width:20%; float:left; margin:0; padding:0;"+(!isCurrentJob?"color:#777;":(settingActive?"color:"+Colour.GENERIC_GOOD.toWebHexString()+";":""))+"'>"
																+ setting.getName()
															+ "</div>"
															+"<div style='width:70%; float:left; margin:0; padding:0;"+(!settingActive?"color:#777;":"")+"'>"
																+ "<i>"+setting.getDescription()+"</i>"
															+ "</div>"
															+ "<div style='float:left; width:10%; margin:0; padding:0;'>"
																+ (!isCurrentJob
																		?"<div id='"+setting+"_DISABLED' class='square-button solo disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseOptionDisabled()+"</div></div>"
																		: (settingActive
																				?"<div class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseOption()+"</div></div>"
																				:"<div id='"+setting+"_TOGGLE_ADD' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseOptionDisabled()+"</div></div>"))
															+"</div>");
						}
						UtilText.nodeContentSB.append("</div>");
					}
				}
				
				UtilText.nodeContentSB.append("</div>");
			}
			UtilText.nodeContentSB.append("</div>");
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2) {
				return new Response("Job", "You are already viewing the jobs screen.", null);
				
			}

			return SLAVE_LIST.getResponse(responseTab, index);
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_PERMISSIONS = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Permissions");
		}
		
		@Override
		public String getContent() {
			NPC character = characterSelected();
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			
			// Permissions:
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Permissions</h6>");
			
			for(SlavePermission permission : SlavePermission.values()) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width inner' style='background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'>"
								+ "<h6 style='color:"+permission.getColour().toWebHexString()+"; text-align:center;'>"+permission.getName()+"</h6>");
				
				// Job Settings:
				for(SlavePermissionSetting setting : permission.getSettings()) {
					boolean settingActive = character.getSlavePermissionSettings().get(permission).contains(setting);
					
					UtilText.nodeContentSB.append("<div class='container-full-width inner'>"
													+"<div style='width:20%; float:left; margin:0; padding:0;"+(settingActive?"color:"+Colour.GENERIC_GOOD.toWebHexString()+";":"")+"'>"
														+ setting.getName()
													+ "</div>"
													+"<div style='width:70%; float:left; margin:0; padding:0;"+(!settingActive?"color:#777;":"")+"'>"
														+ "<i>"+setting.getDescription()+"</i>"
													+ "</div>"
													+ "<div style='float:left; width:10%; margin:0; padding:0;'>"
														+ (permission.isMutuallyExclusiveSettings()
																? (settingActive
																	?"<div id='"+setting+"_REMOVE_ME' class='square-button huge'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseOption()+"</div></div>"
																	:"<div id='"+setting+"_ADD' class='square-button huge'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseOptionDisabled()+"</div></div>")
																:(settingActive
																		?"<div id='"+setting+"_REMOVE' class='square-button huge'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseUnlocked()+"</div></div>"
																		:"<div id='"+setting+"_ADD' class='square-button huge'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseUnlockedDisabled()+"</div></div>"))
													+"</div>"
												+ "</div>");
				}
				
				UtilText.nodeContentSB.append("</div>");
			}
			UtilText.nodeContentSB.append("</div>"
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 3) {
				return new Response("Permissions", "You are already viewing the permissions screen.", null);
				
			}

			return SLAVE_LIST.getResponse(responseTab, index);
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	

	private static Map<BodyCoveringType, List<String>> CoveringsNamesMap;
	
	private static Response getCosmeticsResponse(int responseTab, int index) {
		if (index == 1) {
			if(!BodyChanging.getTarget().getBodyMaterial().isAbleToWearMakeup()) {
				return new Response("Makeup", UtilText.parse(BodyChanging.getTarget(), "As [npc.namePos] body is made of "+Main.game.getPlayer().getBodyMaterial().getName()+", Kate is unable to apply any makeup!"), null);
				
			} else {
				return new Response("Makeup",
						"Kate offers a wide range of different cosmetic services, and several pages of the brochure are devoted to images displaying different styles and colours of lipstick, nail polish, and other forms of makeup.",
						SLAVE_MANAGEMENT_COSMETICS_MAKEUP);
			}
			
		} else if (index == 2) {
			return new Response("Hair",
					"There's a double-page spread of all the different dyes, styles, and lengths of hair that Kate's able to work with.",
					SLAVE_MANAGEMENT_COSMETICS_HAIR);

		} else if (index == 3) {
				return new Response("Piercings",
						"Kate offers a wide range of different piercings.",
						SLAVE_MANAGEMENT_COSMETICS_PIERCINGS);

		}  else if (index == 4) {
				return new Response("Eyes",
						"There's a special page near the front of the brochure, advertising Kate's ability to recolour a person's eyes."
						+ " Just like skin recolourings, this is quite demanding on her aura, and is therefore very expensive.", SLAVE_MANAGEMENT_COSMETICS_EYES);

		} else if (index == 5) {
			return new Response("Coverings",
					"There's a special page in the middle of the brochure, advertising Kate's special ability to harness the arcane in order to recolour a person's skin or fur."
					+ " Apparently, this is quite demanding on her aura, and is therefore very expensive.",
					SLAVE_MANAGEMENT_COSMETICS_COVERINGS){
				@Override
				public void effects() {
					
					CoveringsNamesMap = new LinkedHashMap<>();

					if(BodyChanging.getTarget().getBodyMaterial()==BodyMaterial.SLIME) {
						CoveringsNamesMap.put(BodyCoveringType.SLIME, Util.newArrayListOfValues("SLIME"));
					} else {
						for(BodyPartInterface bp : BodyChanging.getTarget().getAllBodyParts()){
							if(bp.getType().getBodyCoveringType(BodyChanging.getTarget())!=null
									&& !(bp instanceof Hair)
									&& !(bp instanceof Eye)) {
								
								String name = bp.getName(BodyChanging.getTarget());
								if(bp instanceof Skin) {
									name = "torso";
								} else if(bp instanceof Vagina) {
									name = "vagina";
								}
								
								if(CoveringsNamesMap.containsKey(bp.getType().getBodyCoveringType(BodyChanging.getTarget()))) {
									CoveringsNamesMap.get(bp.getType().getBodyCoveringType(BodyChanging.getTarget())).add(name);
								} else {
									CoveringsNamesMap.put(bp.getType().getBodyCoveringType(BodyChanging.getTarget()), Util.newArrayListOfValues(name));
								}
							}
						}
						CoveringsNamesMap.put(BodyCoveringType.ANUS, Util.newArrayListOfValues("anus"));
						CoveringsNamesMap.put(BodyCoveringType.MOUTH, Util.newArrayListOfValues("mouth"));
						CoveringsNamesMap.put(BodyCoveringType.NIPPLES, Util.newArrayListOfValues("nipples"));
						CoveringsNamesMap.put(BodyCoveringType.TONGUE, Util.newArrayListOfValues("tongue"));
					}
				}
			};

		} else if (index == 6) {
			return new Response("Other", "Kate can offer other miscellaneous services, such as anal bleaching.", SLAVE_MANAGEMENT_COSMETICS_OTHER);

		} else if (index == 7) {
			return new Response("Tattoos", "Most of the brochure is taken up with drawings and photographs displaying Kate's considerable artistic talents."
					+ " She's even able to apply arcane-enchanted tattoos, but they look to be very expensive...", SLAVE_MANAGEMENT_TATTOOS);

		} else if (index == 0) {
			return new Response("Back", "Return to the slave management screen.",  SLAVE_LIST) {
				@Override
				public DialogueNodeOld getNextDialogue() {
					if(slaveListManagementOverview) {
						return SLAVE_LIST_MANAGEMENT;
					} else {
						return SLAVE_LIST;
					}
				}
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
				}
			};

		} else {
			return null;
		}
	}
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_COSMETICS_MAKEUP = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Makeup");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on [npc.namePos] [npc.hands].", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on [npc.namePos] [npc.feet].", true, true)
					);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Makeup",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] makeup!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_COSMETICS_HAIR = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Hair");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivHairLengths(true, "Hair Length", "Hair length determines what hair styles [npc.namePos] able to have. The longer [npc.her] [npc.hair], the more styles are available.")

					+CharacterModificationUtils.getKatesDivHairStyles(true, "Hair Style", "Hair style availability is determined by [npc.namePos] [npc.hair] length.")
					
					+(BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.SLIME
						?CharacterModificationUtils.getKatesDivCoveringsNew(
								true, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairType().getBodyCoveringType(BodyChanging.getTarget())).getType(),
								UtilText.parse(BodyChanging.getTarget(), "[npc.Hair] Colour"),
								"All hair recolourings are permanent, so if you want to change your colour again at a later time, you'll have to visit Kate again.", true, true)
						:"")
					);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2) {
				return new Response("Hair",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] hair!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_COSMETICS_PIERCINGS = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Piercings");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"

				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.EAR, "Ear Piercing", "Ears are the most common area of the body that are pierced, and enable the equipping of earrings and other ear-related jewellery.")

				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NOSE, "Nose Piercing", "Having a nose piercing will allow [npc.name] to equip jewellery such as nose rings or studs.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.LIP, "Lip Piercing", "A lip piercing will allow [npc.name] to wear lip rings.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NAVEL, "Navel Piercing", "Getting [npc.her] navel (belly button) pierced will allow [npc.name] to equip navel-related jewellery.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.TONGUE, "Tongue Piercing", "Getting a tongue piercing will allow [npc.name] to equip tongue bars.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NIPPLE, "Nipple Piercing", "Nipple piercings will allow [npc.name] to equip nipple bars.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.PENIS, "Penis Piercing", "Having a penis piercing will allow [npc.name] to equip penis-related jewellery.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.VAGINA, "Vagina Piercing", "Having a vagina piercing will allow [npc.name] to equip vagina-related jewellery."));
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 3) {
				return new Response("Piercings",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] piercings!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_COSMETICS_EYES = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Eyes");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyChanging.getTarget().getEyeType().getBodyCoveringType(BodyChanging.getTarget()), "Irises", "The iris is the coloured part of the eye that's responsible for controlling the diameter and size of the pupil.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.EYE_PUPILS, "Pupils", "The pupil is a hole located in the centre of the iris that allows light to strike the retina.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.EYE_SCLERA, "Sclera", "The sclera is the (typically white) part of the eye that surrounds the iris.", true, true));
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 4) {
				return new Response("Eyes",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] eyes!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_COSMETICS_COVERINGS = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Coverings");
		}
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<h6 style='text-align:center;'>"
									+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
								+ "</h6>");
			
			for(Entry<BodyCoveringType, List<String>> entry : CoveringsNamesMap.entrySet()){
				BodyCoveringType bct = entry.getKey();
				
				String title = Util.capitaliseSentence(bct.getName(BodyChanging.getTarget()));
				String description = "This is the "+bct.getName(BodyChanging.getTarget())+" that's currently covering [npc.namePos] "+Util.stringsToStringList(entry.getValue(), false)+".";
				
				if(bct == BodyCoveringType.ANUS) {
					title = "Anus";
					description = "This is the skin that's currently covering [npc.namePos] anal rim. The secondary colour determines what [npc.her] anus's inner-walls look like.";
					
				} else if(bct == BodyCoveringType.VAGINA) {
					title = "Vagina";
					description = "This is the skin that's currently covering [npc.namePos] labia. The secondary colour determines what [npc.her] vagina's inner-walls look like.";
					
				} else if(bct == BodyCoveringType.PENIS) {
					title = "Penis";
					description = "This is the skin that's currently covering [npc.namePos] penis. The secondary colour determines what the inside of [npc.her] urethra looks like (if it's fuckable).";
					
				} else if(bct == BodyCoveringType.NIPPLES) {
					title = "Nipples";
					description = "This is the skin that's currently covering [npc.namePos] nipples and areolae. The secondary colour determines what [npc.her] nipples' inner-walls look like (if they are fuckable).";
					
				} else if(bct == BodyCoveringType.MOUTH) {
					title = "Lips & Throat";
					if(BodyChanging.getTarget().getFaceType() == FaceType.HARPY) {
						description = "This is the colour of [npc.namePos] beak. The secondary colour determines what the insides of [npc.her] mouth and throat look like.";
					} else {
						description = "This is the skin that's currently covering [npc.namePos] lips. The secondary colour determines what the insides of [npc.her] mouth and throat look like.";
					}
					
				} else if(bct == BodyCoveringType.TONGUE) {
					title = "Tongue";
					description = "This is the skin that's currently covering [npc.namePos] tongue.";
				}
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						true, 
						bct,
						title,
						description,
						true,
						true));
			}
			
			
			return UtilText.parse(BodyChanging.getTarget(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 5) {
				return new Response("Coverings",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] coverings!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_COSMETICS_OTHER = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Other");
		}
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivAnalBleaching("Anal bleaching", "Anal bleaching is the process of lightening the colour of the skin around the anus, to make it more uniform with the surrounding area.")

					+(Main.game.isFacialHairEnabled()
							? CharacterModificationUtils.getKatesDivFacialHair("Facial hair", "The body hair found on [npc.namePos] face." 
									+ (Main.game.isFemaleFacialHairEnabled() ? "" : " Feminine characters cannot grow facial hair."))
							:"")
					
					+(Main.game.isPubicHairEnabled()
							?CharacterModificationUtils.getKatesDivPubicHair("Pubic hair", "The body hair found in the genital area; located on and around [npc.namePos] sex organs and crotch.")
							:"")
					
					+(Main.game.isBodyHairEnabled()
							?CharacterModificationUtils.getKatesDivUnderarmHair("Underarm hair", "The body hair found in [npc.namePos] armpits.")
							:"")
					
					+(Main.game.isAssHairEnabled()
							?CharacterModificationUtils.getKatesDivAssHair("Ass hair", "The body hair found around [npc.namePos] asshole.")
							:"")
					);
			
			for(BodyCoveringType bct : BodyCoveringType.values()) {
				if((Main.game.isFacialHairEnabled() && BodyChanging.getTarget().getFacialHairType().getType()==bct)
						|| (Main.game.isBodyHairEnabled() && BodyChanging.getTarget().getUnderarmHairType().getType()==bct)
						|| (Main.game.isAssHairEnabled() && BodyChanging.getTarget().getAssHairType().getType()==bct)
						|| (Main.game.isPubicHairEnabled() && BodyChanging.getTarget().getPubicHairType().getType()==bct)) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							true, bct, "Body hair", "Your body hair.", true, true));
					
				}
			}
			
			return UtilText.parse(BodyChanging.getTarget(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 6) {
				return new Response("Other",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] other features!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_TATTOOS = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoos();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 7) {
				return new Response("Tattoos",
						UtilText.parse(BodyChanging.getTarget(), "You are already managing [npc.namePos] tattoos!"),
						null);
				
			} else if(index==11) {
				return new Response("Confirmations: ",
						"Toggle tattoo removal confirmations."
							+ " When turned on, it will take two clicks to remove tattoos."
							+ " When turned off, it will only take one click.",
							SLAVE_MANAGEMENT_TATTOOS) {
					@Override
					public String getTitle() {
						return "Confirmations: "+(Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)
									?"<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
									:"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
					}
					
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.tattooRemovalConfirmations, !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations));
						Main.getProperties().savePropertiesAsXML();
					}
				};
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_TATTOOS_ADD = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Succubi's Secrets - "+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getName()) +" Tattoo";
		}
		
		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoosAdd();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			int value = CharacterModificationUtils.tattoo.getValue();
			
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<value) {
					return new Response("Apply ("+UtilText.formatAsMoneyUncoloured(value, "span")+")",
							UtilText.parse(BodyChanging.getTarget(), "You don't have enough money to give [npc.name] a tattoo!"),  null);
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.NONE)
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("Apply ("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "You need to select a tattoo type, add some writing, or add a counter in order to make a tattoo!", null);
					
				} else {
					return new Response("Apply ("+UtilText.formatAsMoney(value, "span")+")", 
							UtilText.parse(BodyChanging.getTarget(), "Pay Kate to give [npc.name] this tattoo!"), SLAVE_MANAGEMENT_TATTOOS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-value));

							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							BodyChanging.getTarget().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==0) {
				return new Response("Back", "Decide not to get this tattoo and return to the main selection screen.", SLAVE_MANAGEMENT_TATTOOS);
			}
			
			return null;
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNodeOld SLAVE_MANAGEMENT_PERKS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.NamePos] Perk Tree");
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(characterSelected(),
					"<div class='container-full-width' style='padding:8px;'>"
						+ "<span style='color:"+Colour.PERK.toWebHexString()+";'>Perks</span> (circular icons) apply permanent boosts to [npc.namePos] attributes.<br/>"
						+ "<span style='color:"+Colour.TRAIT.toWebHexString()+";'>Traits</span> (square icons) provide unique effects for [npc.name]."
							+ " Unlike perks, <b>traits will have no effect on [npc.name] until they're slotted into [npc.her] 'Active Traits' bar</b>.<br/>"
						+ "Perks require perk points to unlock. [npc.Name] earns one perk point each time [npc.she] levels up, and an extra two perk points every five levels."
					+ "</div>"
					+ "<div class='container-full-width' style='padding:8px; text-align:center;'>"
					+ "<h6 style='text-align:center;'>Active Traits</h6>"));

			UtilText.nodeContentSB.append(
					"<div id='OCCUPATION_" + characterSelected().getHistory().getAssociatedPerk()+ "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + Colour.TRAIT.toWebHexString() + ";'>"
						+ "<div class='square-button-content'>"+characterSelected().getHistory().getAssociatedPerk().getSVGString()+"</div>"
					+ "</div>");
			
			for(int i=0;i<GameCharacter.MAX_TRAITS;i++) {
				Perk p = null;
				if(i<characterSelected().getTraits().size()) {
					p = characterSelected().getTraits().get(i);
				}
				if(p!=null) {
					UtilText.nodeContentSB.append("<div id='TRAIT_" + p + "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + Colour.TRAIT.toWebHexString() + ";'>"
							+ "<div class='square-button-content'>"+p.getSVGString()+"</div>"
							+ "</div>");
					
				} else {
					UtilText.nodeContentSB.append("<div id='TRAIT_" + i + "' class='square-button small' style='display:inline-block; float:none;'></div>");
					
				}
			}
			UtilText.nodeContentSB.append("</div>"
					+ "<div class='container-full-width' style='padding:8px; text-align:center;'>"
						+ "<i>Please note that this perk tree is a work-in-progress. This is not the final version, and is just a proof of concept!</i>"
					+ "</div>");
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(characterSelected()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 6) {
				return new Response("Perks", UtilText.parse(characterSelected(), "You are already assigning [npc.namePos] perk points."), null);
				
			} else if(index==7) {
				return new Response("Reset perks", "Reset all of [npc.namePos] perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)", SLAVE_MANAGEMENT_PERKS) {
					@Override
					public void effects() {
						characterSelected().resetPerksMap();
						characterSelected().setPerkPoints(characterSelected().getPerkPointsAtLevel(characterSelected().getLevel()));
						characterSelected().clearTraits();
					}
				};
			}
			
			return SLAVE_LIST.getResponse(responseTab, index);
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
}
