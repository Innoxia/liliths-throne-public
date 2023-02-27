package com.lilithsthrone.game.dialogue.companions;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slaveEvent.SlaveEventType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.comparators.SlaveNameComparator;
import com.lilithsthrone.utils.comparators.SlaveRoomComparator;
import com.lilithsthrone.utils.comparators.SlaveValueComparator;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.8?
 * @version 0.3.9.2
 * @author Innoxia
 */
public class OccupantManagementDialogue {
	
	private static DialogueNode dialogueToExitTo = null;
	private static StringBuilder miscDialogueSB = new StringBuilder();
	private static int dayNumber = 1;
	private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	private static List<SlaveEventType> eventTypeFilterExclusions = new ArrayList<>();
	private static List<String> slaveIdFilterExclusions = new ArrayList<>();
	private static OccupantSortingMethod sortingMethod = OccupantSortingMethod.NONE;
	private static boolean reverseSortSlaves = false;
	
	static {
		decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
	}
	
	public static NPC characterSelected() {
		return Main.game.getDialogueFlags().getManagementCompanion();
	}
	
	/**
	 * @param dialogueToExitTo The DialogueNode which should be displayed when exiting out of the occupant management windows.
	 * @return OCCUPANT_OVERVIEW
	 */
	public static DialogueNode getSlaveryOverviewDialogue(DialogueNode dialogueToExitTo) {
		OccupantManagementDialogue.dialogueToExitTo = dialogueToExitTo;
		dayNumber = Main.game.getDayNumber();
		Main.game.getDialogueFlags().setSlaveTrader(null);
		return OCCUPANT_OVERVIEW;
	}

	/**
	 * @param dialogueToExitTo The DialogueNode which should be displayed when exiting out of the occupant management windows. Pass in null to return to default dialogue.
	 * @param slaveTrader The character you are trading with.
	 * @return SLAVE_LIST_MANAGEMENT
	 */
	public static DialogueNode getSlaveryManagementDialogue(DialogueNode dialogueToExitTo, NPC slaveTrader) {
		OccupantManagementDialogue.dialogueToExitTo = dialogueToExitTo;
		dayNumber = Main.game.getDayNumber();
		Main.game.getDialogueFlags().setSlaveTrader(slaveTrader);
		return SLAVE_LIST_MANAGEMENT;
	}
	
	/**
	 * @param dialogueToExitTo The DialogueNode which should be displayed when exiting out of the occupant management windows. Pass in null to return to default dialogue.
	 * @param slaveTrader The character you are trading with.
	 * @return SLAVE_LIST
	 */
	public static DialogueNode getSlaveryRoomListDialogue(DialogueNode dialogueToExitTo, NPC slaveTrader) {
		OccupantManagementDialogue.dialogueToExitTo = dialogueToExitTo;
		dayNumber = Main.game.getDayNumber();
		Main.game.getDialogueFlags().setSlaveTrader(slaveTrader);
		return SLAVE_LIST;
	}
	
	public static int getDayNumber() {
		return dayNumber;
	}

	public static void setDayNumber(int dayNumber) {
		OccupantManagementDialogue.dayNumber = Math.max(1, dayNumber);
	}

	public static OccupantSortingMethod setSlavesSortedBy() {
		return sortingMethod;
	}

	public static void setSlavesSortedBy(OccupantSortingMethod osm) {
		OccupantManagementDialogue.sortingMethod = osm;
	}

	public static boolean getSlavesAreInReverseOrder() {
		return OccupantManagementDialogue.reverseSortSlaves;
	}

	public static void setSlavesAreInReverseOrder(boolean truth) {
		OccupantManagementDialogue.reverseSortSlaves = truth;
	}
	
	private static Response getSlaveryResponse(int index) {
		if (index == 1) {
			return new Response("Room List", "View the management screen for all rooms.", ROOM_MANAGEMENT) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setManagementCompanion(null);
				}
			};
			
		} else if (index == 2) {
			return new Response("Occupant List", "Enter the management screen for all slaves and friendly occupants.", SLAVE_LIST_MANAGEMENT) {
				@Override
				public DialogueNode getNextDialogue() {
					return OccupantManagementDialogue.getSlaveryManagementDialogue(dialogueToExitTo, Main.game.getDialogueFlags().getSlaveTrader());
				}
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setManagementCompanion(null);
				}
			};
			
		} else if (index == 5) {
			if(Main.game.getOccupancyUtil().getGeneratedBalance()==0) {
				return new Response("Collect: "+UtilText.formatAsMoneyUncoloured(Main.game.getOccupancyUtil().getGeneratedBalance(), "span"), "Your current balance is 0...",  null);
				
			} else if(Main.game.getOccupancyUtil().getGeneratedBalance()>0) {
				return new Response("Collect: "+UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedBalance(), "span"), "Collect the money that you've earned through your slaves' activities.",  OCCUPANT_OVERVIEW) {
					@Override
					public DialogueNode getNextDialogue() {
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
				
				return new Response("Pay: "+UtilText.formatAsMoney(Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance()), "span", PresetColour.GENERIC_BAD), "Pay off the accumulated debt from the upkeep of your slaves and rooms.",  OCCUPANT_OVERVIEW) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getCurrentDialogueNode();
					}
					@Override
					public void effects() {
						Main.game.getOccupancyUtil().payOutBalance();
					}
				};
			}
			
		} else if (index == 0) {
			return new Response("Back", "Exit the occupancy ledger.", dialogueToExitTo==null?Main.game.getDefaultDialogue():dialogueToExitTo) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setManagementCompanion(null);
					Main.game.getDialogueFlags().setSlaveTrader(null);
				}
			};

		} else {
			return null;
		}
	}
	
	public static final DialogueNode OCCUPANT_OVERVIEW = new DialogueNode("Slavery Overview", ".", true) {
		
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
						+ "<h6 style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>Totals</h6>"
						+ "<div class='container-full-width' style='text-align:center; margin-bottom:0;'>"
							+ "<div style='width:10%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Slaves"
							+ "</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Income</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Upkeep</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Profit</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Funds</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Payments</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Balance</b>"
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
								+ UtilText.formatAsMoney(upkeep, "b", PresetColour.GENERIC_BAD)+"/day"
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(income-upkeep, "b", (income-upkeep<0?PresetColour.GENERIC_BAD:PresetColour.TEXT))+"/day"
							+"</div>"
							+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedIncome(), "b")
							+ "</div>"
							+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedUpkeep(), "b", PresetColour.GENERIC_BAD)
							+ "</div>"
							+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ (Main.game.getOccupancyUtil().getGeneratedBalance()<0
										? UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedBalance(), "b", PresetColour.GENERIC_BAD)
										: UtilText.formatAsMoney(Main.game.getOccupancyUtil().getGeneratedBalance(), "b"))
							+ "</div>"
						+ "</div>"
					+"</div>");
			
			// Logs:
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Activity Log</h6>");
			// Buttons:
			for(int i=6; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='SLAVE_DAY_"+i+"' class='normal-button' style='width:12%; margin:1%;"+(Main.game.getDayNumber()-i==dayNumber?"color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";":"")+"'>"
						+ (i==0
							?"Today"
							:(i==1
								?"Yesterday"
								:Main.game.getDateNow().minusDays(i).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH)))
						+ "</div>");
			}
			
			// Headers:
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center; margin-bottom:0;'>"
							+ "<div style='width:10%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Time"
							+ "</div>"
							+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Slave"
							+ "</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "Event"
							+"</div>"
							+ "<div style='float:left; width:60%; font-weight:bold; margin:0; padding:0;'>"
								+ "Description"
							+"</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='text-align:center; margin-bottom:0;'>");
			
			int count=0;
			if(Main.game.getSlaveryEvents(dayNumber)!=null) {
				List<SlaveryEventLogEntry> entries = new ArrayList<>(Main.game.getSlaveryEvents(dayNumber));
				int filtered = 0;
				for(SlaveryEventLogEntry entry : Main.game.getSlaveryEvents(dayNumber)) {
					if(eventTypeFilterExclusions.contains(entry.getEvent().getType())
							|| (slaveIdFilterExclusions.contains(entry.getSlaveID()) && slaveIdFilterExclusions.containsAll(entry.getInvolvedSlaveIDs()))) {
						filtered++;
						entries.remove(entry);
					}
				}
				if(filtered>0) {
					UtilText.nodeContentSB.append("<div class='container-full-width inner' style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>[style.italicsBad(Filtered events: "+filtered+")]</div>");
				}
				for(SlaveryEventLogEntry entry : entries) {
					if(count%2==0) {
						UtilText.nodeContentSB.append("<div class='container-full-width inner' style='background:"+PresetColour.BACKGROUND.toWebHexString()+";'>");
					} else {
						UtilText.nodeContentSB.append("<div class='container-full-width inner' style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>");
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
					count++;
				}
			}
			if(count==0) {
				UtilText.nodeContentSB.append("<div class='container-full-width inner' style='background:"+PresetColour.BACKGROUND.toWebHexString()+";'>[style.colourDisabled(No events for this day...)]</div>");
			}
			
			UtilText.nodeContentSB.append("</div>"
					+ "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Room";
			} else if(index==1) {
				return "Filter (type)";
			} else if(index==2) {
				return "Filter (slave)";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				return getSlaveryResponse(index);
				
			} else if(responseTab==1) {
				if(index==0) {
					return getSlaveryResponse(index);
				}
				if(index==1) {
					return new Response("Add all", "Add all types to the filter.", OCCUPANT_OVERVIEW) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_GOOD;
						}
						@Override
						public void effects() {
							eventTypeFilterExclusions.clear();
						}
					};
					
				} else if(index==2) {
					return new Response("Clear all", "Remove all types from the filter.", OCCUPANT_OVERVIEW) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_BAD;
						}
						@Override
						public void effects() {
							eventTypeFilterExclusions.clear();
							Collections.addAll(eventTypeFilterExclusions, SlaveEventType.values());
						}
					};
				}
				if(index-3<SlaveEventType.values().length) {
					SlaveEventType type = SlaveEventType.values()[index-3];
					return new Response(type.getName(), "Click to filter events by this type:<br/><i>"+type.getDescription()+"</i>", OCCUPANT_OVERVIEW) {
						@Override
						public Colour getHighlightColour() {
							if(eventTypeFilterExclusions.contains(type)) {
								return PresetColour.TEXT_GREY;
							} else {
								return PresetColour.GENERIC_MINOR_GOOD;
							}
						}
						@Override
						public void effects() {
							if(eventTypeFilterExclusions.contains(type)) {
								eventTypeFilterExclusions.remove(type);
							} else {
								eventTypeFilterExclusions.add(type);
							}
						}
					};
				}
				
			} else if(responseTab==2) {
				List<String> ownedSlaves = Main.game.getPlayer().getSlavesOwned();
				if(index==0) {
					return getSlaveryResponse(index);
				}
				if(index==1) {
					return new Response("Add all", "Add all slaves to the filter.", OCCUPANT_OVERVIEW) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_GOOD;
						}
						@Override
						public void effects() {
							slaveIdFilterExclusions.clear();
						}
					};
					
				} else if(index==2) {
					return new Response("Clear all", "Remove all slaves from the filter.", OCCUPANT_OVERVIEW) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_BAD;
						}
						@Override
						public void effects() {
							slaveIdFilterExclusions.clear();
							slaveIdFilterExclusions.addAll(ownedSlaves);
						}
					};
				}
				if(index-3<ownedSlaves.size()) {
					String slaveId = ownedSlaves.get(index-3);
					GameCharacter slave = null;
					try {
						slave = Main.game.getNPCById(slaveId);
					} catch(Exception ex) {}
					if(slave==null) {
						return null;
					}
					GameCharacter slaveInner = slave;
					return new Response(UtilText.parse(slave, "[npc.Name]"), "Click to filter this slave in or out of displayed events.", OCCUPANT_OVERVIEW) {
						@Override
						public Colour getHighlightColour() {
							if(slaveIdFilterExclusions.contains(slaveId)) {
								return PresetColour.TEXT_GREY;
							} else {
								return slaveInner.getFemininity().getColour();
							}
						}
						@Override
						public void effects() {
							if(slaveIdFilterExclusions.contains(slaveId)) {
								slaveIdFilterExclusions.remove(slaveId);
							} else {
								slaveIdFilterExclusions.add(slaveId);
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	
	public static final DialogueNode ROOM_MANAGEMENT = new DialogueNode("Room Management", ".", true) {

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
						+ "<h6 style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+"; text-align:center;'>Current Location</h6>"
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
						+ "<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>Affection</b>"
					+"</div>"
					+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
						+ "<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
					+"</div>"
					+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
						+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Upkeep</b>"
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
				"<div class='container-full-width inner' style='margin-bottom:4px; margin-top:4px; "+(!occupants.isEmpty()?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
						+ "<div style='width:15%; float:left; margin:0; padding:0;'>"
							+ "<span style='color:"+place.getColour().toWebHexString()+";'>"+place.getName()+"</span><br/>"
						+ "</div>"
						+ "<div style='width:20%; float:left; margin:0; padding:0;'>");
		
		int i=0;
		for(NPC occupant : occupants) {
			if(occupant.isSlave()) {
				miscDialogueSB.append("<b style='color:"+occupant.getFemininity().getColour().toWebHexString()+";'>"+occupant.getName(true)+"</b>"+(i+1==occupants.size()?"":"<br/>"));
				i++;
			}
		}
		if(i==0) {
			miscDialogueSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>Empty</b>");
		}
		
		miscDialogueSB.append("</div>"
						+ "<div style='float:left; width:10%; margin:0; padding:0;'>"
							+ i+"/"+place.getCapacity()
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<span style='color:"+(affectionChange==0?PresetColour.BASE_GREY:(affectionChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/hour"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<span style='color:"+(obedienceChange==0?PresetColour.BASE_GREY:(obedienceChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
								+decimalFormat.format(obedienceChange)+"</span>/hour"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ (disabled
									?"[style.colourDisabled(N/A)]"
									:(place.getUpkeep()>0
										?UtilText.formatAsMoney(-place.getUpkeep(), "span", PresetColour.GENERIC_BAD)
										:(place.getUpkeep()==0
											?UtilText.formatAsMoney(-place.getUpkeep(), "span", PresetColour.TEXT_GREY)
											:UtilText.formatAsMoney(-place.getUpkeep(), "span", PresetColour.GENERIC_GOOD))))+"/day"
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
			AbstractWorldType[] importantWorlds = new AbstractWorldType[] {WorldType.LILAYAS_HOUSE_GROUND_FLOOR, WorldType.LILAYAS_HOUSE_FIRST_FLOOR, WorldType.getWorldTypeFromId("acexp_dungeon")};
			for(AbstractWorldType wt : importantWorlds) {
				Cell[][] cellGrid = Main.game.getWorlds().get(wt).getCellGrid();
				for(int i = 0; i< cellGrid.length; i++) {
					for(int j = 0; j < cellGrid[0].length; j++) {
						if(!cellGrid[i][j].getPlace().getPlaceType().equals(PlaceType.LILAYA_HOME_CORRIDOR)
								&& !cellGrid[i][j].getPlace().getPlaceType().equals(PlaceType.GENERIC_IMPASSABLE)
								&& !cellGrid[i][j].getPlace().getPlaceType().equals(PlaceType.getPlaceTypeFromId("acexp_dungeon_corridor"))
								&& !cellGrid[i][j].getPlace().getPlaceType().equals(PlaceType.getPlaceTypeFromId("acexp_dungeon_stairs"))
								&& !cellGrid[i][j].getPlace().getPlaceType().equals(PlaceType.getPlaceTypeFromId("acexp_dungeon_stairs_garden"))) {
							importantCells.add(cellGrid[i][j]);
						}
					}
				}
			}
		}
		
		return importantCells;
	}
	
	
	
	private static String getWorldRooms(AbstractWorldType worldType) {
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
	
	public static final DialogueNode ROOM_UPGRADES = new DialogueNode("Room Management", ".", true) {

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
							+ "<h6 style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>Overview (Total Values for this Room)</h6>"
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
									+ "<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>Affection</b>"
								+"</div>"
								+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
									+ "<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
								+"</div>"
								+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
									+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Upkeep</b>"
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
					UtilText.nodeContentSB.append("<b style='color:"+occupant.getFemininity().getColour().toWebHexString()+";'>"+occupant.getName(true)+"</b>"+(i+1==occupants.size()?"":"<br/>"));
					i++;
				}
			}
			if(i==0) {
				UtilText.nodeContentSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>Empty</b>");
			}
			
			
			UtilText.nodeContentSB.append(
					"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ i+"/"+place.getCapacity()
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ "<span style='color:"+(affectionChange==0?PresetColour.BASE_GREY:(affectionChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
									+decimalFormat.format(affectionChange)+"</span>/hour"
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ "<span style='color:"+(obedienceChange==0?PresetColour.BASE_GREY:(obedienceChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
									+decimalFormat.format(obedienceChange)+"</span>/hour"
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ (place.getUpkeep()>0
											?UtilText.formatAsMoney(-place.getUpkeep(), "span", PresetColour.GENERIC_BAD)
											:UtilText.formatAsMoney(-place.getUpkeep(), "span", PresetColour.GENERIC_GOOD))+"/day"
							+"</div>"
						+ "</div>"
						+ "</div>");
			
			
			// Normal upgrades:
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
											+ "<h6 style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+"; text-align:center;'>Modifications</h6>"
											+ getRoomUpgradeHeader());
			
			List<AbstractPlaceUpgrade> coreUpgrades = new ArrayList<>();
			for(AbstractPlaceUpgrade upgrade : place.getPlaceType().getAvailablePlaceUpgrades(place.getPlaceUpgrades())) {
				if(upgrade.getAvailability(cellToInspect).getKey() || (!upgrade.getAvailability(cellToInspect).getValue().isEmpty())) { // Do not display upgrades that have no explanation as to why they're banned.
					if(upgrade.isCoreRoomUpgrade()) {
						coreUpgrades.add(upgrade);
					} else {
						UtilText.nodeContentSB.append(getUpgradeEntry(cellToInspect, upgrade));
						i++;
					}
				}
			}
			if(i==0) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width inner' style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>"
								+ "<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No Modifications Available</b>"
						+ "</div>");
			}
			
			UtilText.nodeContentSB.append("</div>");
			
			// Core upgrades:
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<h6 style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Core Modifications</h6>"
					+"<p><i>Purchasing a [style.boldArcane(core modification)] will remove [style.boldBad(all)] other modifications in this room!</i></p>"
					+ getRoomUpgradeHeader());

			
//			for (PlaceUpgrade upgrade : place.getPlaceUpgrades()) {
//				if(upgrade.isCoreRoomUpgrade()) {
//					UtilText.nodeContentSB.append(getUpgradeEntry(cellToInspect, upgrade));
//				}
//			}
			
			i = 0;
			for (AbstractPlaceUpgrade upgrade : coreUpgrades) {
				UtilText.nodeContentSB.append(getUpgradeEntry(cellToInspect, upgrade));
				i++;
			}
			if(i==0) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width inner' style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>"
								+ "<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No Core Modifications Available</b>"
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
						public DialogueNode getNextDialogue() {
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
					
					return new Response("Pay: "+UtilText.formatAsMoney(Math.abs(Main.game.getOccupancyUtil().getGeneratedBalance()), "span", PresetColour.GENERIC_BAD),
							"Pay off the accumulated debt from the upkeep of your slaves and rooms.",  ROOM_UPGRADES) {
						@Override
						public DialogueNode getNextDialogue() {
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
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
			} else {
				return null;
			}
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
						+ "<span style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>Affection</span>"
					+"</div>"
					+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
						+ "<span style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Obedience</span>"
					+"</div>"
					+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
						+ "<span style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Upkeep</span>"
					+"</div>"
					+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
						+ "<span style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Cost</span>"
					+"</div>"
					+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
						+ "Actions"
					+"</div>"
				+ "</div>";
	}
	
	private static String getUpgradeEntry(Cell cell, AbstractPlaceUpgrade upgrade) {
		miscDialogueSB.setLength(0);
		GenericPlace place = cell.getPlace();
		float affectionChange = upgrade.getHourlyAffectionGain();
		float obedienceChange = upgrade.getHourlyObedienceGain();
		boolean owned = place.getPlaceUpgrades().contains(upgrade);
		boolean availableForPurchase = upgrade.isPrerequisitesMet(place) && upgrade.getAvailability(cell).getKey() && (owned?Main.game.getPlayer().getMoney()>=upgrade.getRemovalCost():Main.game.getPlayer().getMoney()>=upgrade.getInstallCost());
		boolean canBuy = availableForPurchase;
		
		miscDialogueSB.append(
				"<div class='container-full-width inner' style='margin-bottom:4px; margin-top:4px;"+(owned?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
						+ "<div style='width:5%; float:left; margin:0; padding:0;'>"
							+ "<div class='title-button no-select' id='ROOM_MOD_INFO_"+PlaceUpgrade.getIdFromPlaceUpgrade(upgrade)+"' style='position:relative; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
						+ "</div>"
						+ "<div style='width:25%; float:left; margin:0; padding:0;'>"
							+ (owned
									?"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(upgrade.getName())+"</b>"
									:(!availableForPurchase
											?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+Util.capitaliseSentence(upgrade.getName())+"</b>"
											:"<b>"+Util.capitaliseSentence(upgrade.getName())+"</b>"))
//							+ "<div class='item-inline' id='ROOM_MOD_INFO_"+upgrade+"' style='float:right;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
//							+"<div class='overlay' id=''></div>"
						+ "</div>"
						+ "<div style='width:10%; float:left; margin:0; padding:0;'>"
							+ (upgrade.getCapacity()>0
									?"<b style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>+"+upgrade.getCapacity()+"</b>"
									:(upgrade.getCapacity()<0
											?"<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>"+upgrade.getCapacity()+"</b>"
											:"[style.colourDisabled(0)]"))
						+ "</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ (affectionChange>0
									?"<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>+"+decimalFormat.format(affectionChange)+ "</b>/hour"
									:(affectionChange<0
											?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(affectionChange)+ "</b>/hour"
											:"[style.colourDisabled(0)]/hour"))
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ (obedienceChange>0
									?"<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>+"+decimalFormat.format(obedienceChange)+ "</b>/hour"
									:(obedienceChange<0
											?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(obedienceChange)+ "</b>/hour"
											:"[style.colourDisabled(0)]/hour"))
						+"</div>"
						+ "<div style='float:left; width:10%; margin:0; padding:0;'>"
							+ (upgrade.getUpkeep()>0
									?UtilText.formatAsMoney(upgrade.getUpkeep(), "b", PresetColour.GENERIC_BAD)
									:UtilText.formatAsMoney(upgrade.getUpkeep(), "b", PresetColour.GENERIC_GOOD))+"/day"
						+"</div>"
						+ "<div style='float:left; width:10%; margin:0; padding:0;'>"
							+ (owned
									?(upgrade.getRemovalCost()<0
											?UtilText.formatAsMoney(upgrade.getRemovalCost(), "b", PresetColour.GENERIC_GOOD)
											:(upgrade.getRemovalCost() < Main.game.getPlayer().getMoney()
													?UtilText.formatAsMoney(upgrade.getRemovalCost(), "b")
													:UtilText.formatAsMoney(upgrade.getRemovalCost(), "b", PresetColour.GENERIC_BAD)))
									:(upgrade.getInstallCost()<0
											?UtilText.formatAsMoney(upgrade.getInstallCost(), "b", PresetColour.GENERIC_GOOD)
											:(upgrade.getInstallCost() < Main.game.getPlayer().getMoney()
													?UtilText.formatAsMoney(upgrade.getInstallCost(), "b")
													:UtilText.formatAsMoney(upgrade.getInstallCost(), "b", PresetColour.GENERIC_BAD))))
						+"</div>"
						+ "<div style='float:left; width:10%; margin:0 auto; padding:0; display:inline-block; text-align:center;'>");
		
		if(owned) {
			if(Main.game.getPlayer().getMoney()<upgrade.getRemovalCost() || !upgrade.getRemovalAvailability(cell).getKey()) {
				miscDialogueSB.append("<div id='"+PlaceUpgrade.getIdFromPlaceUpgrade(upgrade)+"_SELL_DISABLED' class='square-button solo disabled'><div class='square-button-content'>"
							+SVGImages.SVG_IMAGE_PROVIDER.getTransactionSellDisabled()+"</div></div>");
			} else {
				miscDialogueSB.append("<div id='"+PlaceUpgrade.getIdFromPlaceUpgrade(upgrade)+"_SELL' class='square-button solo'><div class='square-button-content'>"
							+SVGImages.SVG_IMAGE_PROVIDER.getTransactionSell()+"</div></div>");
			}
			
		} else {
			if(Main.game.getPlayer().getMoney()<upgrade.getInstallCost() || Main.game.getOccupancyUtil().getGeneratedBalance()<0) {
				canBuy = false;
			}
			if(canBuy) {
				if(!upgrade.getPrerequisites().isEmpty()) {
					for(AbstractPlaceUpgrade prereq : upgrade.getPrerequisites()) {
						if(!place.getPlaceUpgrades().contains(prereq)) {
							canBuy = false;
							break;
						}
					}
				}
			}
			
			if(canBuy) {
				miscDialogueSB.append("<div id='"+PlaceUpgrade.getIdFromPlaceUpgrade(upgrade)+"_BUY' class='square-button solo'><div class='square-button-content'>"
							+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBuy()+"</div></div>");
			} else {
				miscDialogueSB.append("<div id='"+PlaceUpgrade.getIdFromPlaceUpgrade(upgrade)+"_BUY_DISABLED' class='square-button solo disabled'><div class='square-button-content'>"
							+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBuyDisabled()+"</div></div>");
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
	public static String getPurchaseAvailabilityTooltipText(Cell cell, AbstractPlaceUpgrade upgrade) {
		GenericPlace place = cell.getPlace();
		boolean owned = place.getPlaceUpgrades().contains(upgrade);
		
		purchaseAvailability.setLength(0);
		
		if(owned) {
			if(Main.game.getPlayer().getMoney()<upgrade.getRemovalCost()) {
				purchaseAvailability.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You cannot afford to remove this modification.</span>");
			}
			
		} else {
			if(Main.game.getOccupancyUtil().getGeneratedBalance()<0) {
				purchaseAvailability.append("<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You cannot purchase any modifications while you are in debt!</b>");
			}
			
			if(Main.game.getPlayer().getMoney()<upgrade.getInstallCost()) {
				purchaseAvailability.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You cannot afford this modification.</span>");
			}
			
			if(!upgrade.getPrerequisites().isEmpty()) {
				purchaseAvailability.append("You need to purchase the following first:");
				for(AbstractPlaceUpgrade prereq : upgrade.getPrerequisites()) {
					if(place.getPlaceUpgrades().contains(prereq)) {
						purchaseAvailability.append("<br/><span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+prereq.getName()+"</span>");
					} else {
						purchaseAvailability.append("<br/><span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+prereq.getName()+"</span>");
					}
				}
			}
		}
		
		String availabilityDescription = upgrade.getAvailability(OccupantManagementDialogue.cellToInspect).getValue();
		if(availabilityDescription!=null && availabilityDescription.length()>0) {
			purchaseAvailability.append("<br/><span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+availabilityDescription+"</span>");
		}
		
		return purchaseAvailability.toString();
	}
	
	
	public static final DialogueNode SLAVE_LIST = new DialogueNode("Slave & Occupant Management", ".", true) {

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
						+ "<h6 style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+"; text-align:center;'>Slaves For Sale</h6>"
						
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
					UtilText.nodeContentSB.append("<div class='container-full-width inner'><h4 style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No slaves for sale!</h4></div>");
				}
				UtilText.nodeContentSB.append("</div>");
				
			} else { // Show friendly occupants if not trading slaves:
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+"; text-align:center;'>Friendly Occupants</h6>"
						
						+ getOccupantHeader());
				
				if(Main.game.getPlayer().getFriendlyOccupants().isEmpty()) {
					UtilText.nodeContentSB.append(
							"<div class='container-full-width' style='text-align:center;'>"
									+"<p style='color:"+PresetColour.BASE_GREY.toWebHexString()+";'>You do not have anyone living with you...</p>"
							+ "</div>");
					
				} else {
					int i = 0;
					for(String id : Main.game.getPlayer().getFriendlyOccupants()) {
						try {
							NPC occupant = (NPC) Main.game.getNPCById(id);
							if(occupant.getHomeWorldLocation()!=WorldType.DOMINION) {
								AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(occupant.getAffection(Main.game.getPlayer()));
								ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(occupant.getObedienceValue());
								float affectionChange = occupant.getDailyAffectionChange();
								float obedienceChange = occupant.getDailyObedienceChange();
								GenericPlace place = Main.game.getPlayerCell().getPlace();
								
								UtilText.nodeContentSB.append(getOccupantEntry(place, occupant, affection, affectionChange, obedience, obedienceChange, i%2==0));
								i++;
							}
						} catch (Exception e) {
							Util.logGetNpcByIdError("SLAVE_LIST.getResponse()", id);
						}
					}
				}
				
				UtilText.nodeContentSB.append(
						"</div>");
			}
			
			
			
			// Your slaves:
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<h6 style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+"; text-align:center;'>Slaves Owned</h6>");
					
			//UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>");
			//UtilText.nodeContentSB.append(  "<h6 style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+"'>Sorting</h6>");
			String buttonStyle = "margin:2px; width:16%;";
			UtilText.nodeContentSB.append(  "<div class='container-full-width inner' style='text-align:center'>");
			UtilText.nodeContentSB.append(    "<div style='width:100%;font-weight:bold;margin-top:8px'>Sort By</div>");
			UtilText.nodeContentSB.append(    "<div id='SORT_SLAVES_BY_NONE' class='normal-button"+((sortingMethod==OccupantSortingMethod.NONE)?" selected":"")+"' style='"+buttonStyle+"'>None</div>");
			UtilText.nodeContentSB.append(    "<div id='SORT_SLAVES_BY_NAME' class='normal-button"+((sortingMethod==OccupantSortingMethod.NAME)?" selected":"")+"' style='"+buttonStyle+"'>Name</div>");
			UtilText.nodeContentSB.append(    "<div id='SORT_SLAVES_BY_ROOM' class='normal-button"+((sortingMethod==OccupantSortingMethod.ROOM)?" selected":"")+"' style='"+buttonStyle+"'>Room</div>");
			UtilText.nodeContentSB.append(    "<div id='SORT_SLAVES_BY_VALUE' class='normal-button"+((sortingMethod==OccupantSortingMethod.VALUE)?" selected":"")+"' style='"+buttonStyle+"'>Value</div>");
//			UtilText.nodeContentSB.append(    "<div id='SORT_SLAVES_BY_CUSTOM_CATEGORY' class='normal-button"+((sortingMethod==OccupantSortingMethod.CUSTOM_CATEGORY)?" selected":"")+"' style='"+buttonStyle+"'>Category</div>");
//			UtilText.nodeContentSB.append(  "</div>");
//			UtilText.nodeContentSB.append(  "<div class='container-full-width inner' style='text-align:center'>");
//			UtilText.nodeContentSB.append(    "<div style='width:100%;font-weight:bold;margin-top:8px'>Order</div>");
			UtilText.nodeContentSB.append(  "<div style='width:100%; height:0;'></div>");
			UtilText.nodeContentSB.append(    "<div id='SORT_SLAVES_ASC' class='normal-button"+((!reverseSortSlaves)?" selected":"")+"' style='"+buttonStyle+"'>Ascending</div>");
			UtilText.nodeContentSB.append(    "<div id='SORT_SLAVES_DESC' class='normal-button"+((reverseSortSlaves)?" selected":"")+"' style='"+buttonStyle+"'>Descending</div>");
			UtilText.nodeContentSB.append(  "</div>");
			//UtilText.nodeContentSB.append("<div>");
			
			UtilText.nodeContentSB.append(getSlaveryHeader());
			
			if(Main.game.getPlayer().getSlavesOwned().isEmpty()) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='text-align:center;'>"
								+"<p style='color:"+PresetColour.BASE_GREY.toWebHexString()+";'>You do not own any slaves...</p>"
						+ "</div>");
				
			} else {
				int i = 0;
				List<NPC> slaves = Main.game.getPlayer().getSlavesOwned().stream()
					.filter(npcid -> Main.game.isCharacterExisting(npcid))
					.map(npcid -> {
						try {
							return (NPC)Main.game.getNPCById(npcid);
						} catch (Exception e) {
							// Should never happen. Just satisfying Java's pickiness.
							System.err.println("Main.game.getNPCById("+npcid+") returning null 2nd instance in method: SLAVE_LIST.getResponse()");
							return null;
						}
					})
					.filter(npc -> npc != null)
					.collect(Collectors.toList());
				Comparator<NPC> ssm = null;
				switch(sortingMethod) {
					case NAME:
						ssm = new SlaveNameComparator();
						break;
					case ROOM:
						ssm = new SlaveRoomComparator();
						break;
					case VALUE:
						ssm = new SlaveValueComparator();
						break;
					default:
						break;
				}
				if(ssm != null) {
					if(reverseSortSlaves) {
						ssm = Collections.reverseOrder(ssm);
					}
					Collections.sort(slaves, ssm);
				}
				for(NPC slave : slaves) {
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
			return CompanionManagement.getManagementResponses(index);
		}
	};
	

	public static final DialogueNode SLAVE_LIST_MANAGEMENT = new DialogueNode("Slave Management", ".", true) {

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
				if(Main.game.getCurrentDialogueNode()==SLAVE_LIST_MANAGEMENT) {
					return new Response("Back", "Exit the slave management screen.", dialogueToExitTo==null?OCCUPANT_OVERVIEW:dialogueToExitTo);
				} else {
					return new Response("Back", "Return to the management screen.", SLAVE_LIST_MANAGEMENT);
				}
			}
			if(Main.game.getDialogueFlags().getSlaveTrader()==null) {
				return SLAVE_LIST.getResponse(responseTab, index);
			}
			return null;
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
					+ "<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>Affection</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Value</b>"
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
					+ "<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>Affection</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Value</b>"
				+"</div>"
				+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
					+ "Actions"
				+"</div>"
			+ "</div>";
	}
	
	private static String getSlaveryEntry(boolean slaveOwned, GenericPlace place, NPC slave, AffectionLevel affection, float affectionChange, ObedienceLevel obedience, float obedienceChange, boolean alternateBackground) {
		miscDialogueSB.setLength(0);
		
		miscDialogueSB.append(
				"<div class='container-full-width inner' style='margin-bottom:0;"+(alternateBackground?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
						+ "<div style='width:20%; float:left; margin:0; padding:0;'>"
							+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName(true)+"</b><br/>"
							+ "<span style='color:"+slave.getRace().getColour().toWebHexString()+";'>"
								+Util.capitaliseSentence((slave.isFeminine()?slave.getSubspecies().getSingularFemaleName(slave.getBody()):slave.getSubspecies().getSingularMaleName(slave.getBody())))+"</span><br/>"
							+ "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slave.getGender().getName())+"</span>"
						+ "</div>"
						+ "<div style='width:20%; float:left; margin:0; padding:0;'>"
							+ "<b style='color:"+slave.getLocationPlace().getColour().toWebHexString()+";'>"+slave.getLocationPlace().getName()+"</b>"
							+",<br/>"
							+ "<span style='color:"+slave.getWorldLocation().getColour().toWebHexString()+";'>"+slave.getWorldLocation().getName()+"</span>"
						+ "</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+slave.getAffection(Main.game.getPlayer())+ "</b>"
							+ "<br/><span style='color:"+(affectionChange==0?PresetColour.BASE_GREY:(affectionChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+affection.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+slave.getObedienceValue()+ "</b>"
							+ "<br/><span style='color:"+(obedienceChange==0?PresetColour.BASE_GREY:(obedienceChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
								+decimalFormat.format(obedienceChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+obedience.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(obedience.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ (Main.game.getDialogueFlags().getSlaveTrader()!=null
								?(slaveOwned
										?UtilText.formatAsMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()), "b", PresetColour.GENERIC_ARCANE)
										:UtilText.formatAsMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier(null)), "b", PresetColour.GENERIC_ARCANE))
								:UtilText.formatAsMoney(slave.getValueAsSlave(true)))+"<br/>"
							+ "<b>"+Util.capitaliseSentence(slave.getSlaveJob(Main.game.getHourOfDay()).getName(slave))+" (now)</b><br/>"
							+ UtilText.formatAsMoney(SlaveJob.getFinalDailyIncomeAfterModifiers(slave))+"/day"
						+"</div>");

		miscDialogueSB.append("<div style='float:left; width:15%; margin:0 auto; padding:0; display:inline-block; text-align:center;'>");
		if(slaveOwned) {
			miscDialogueSB.append("<div id='"+slave.getId()+"' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveInspect()+"</div></div>");

			if(Main.game.getDialogueFlags().getSlaveTrader()==null) { // Only show these buttons if you aren't in a trade screen:
				miscDialogueSB.append("<div id='"+slave.getId()+"_JOB' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveJob()+"</div></div>");
	
				miscDialogueSB.append("<div id='"+slave.getId()+"_PERMISSIONS' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlavePermissions()+"</div></div>");
				
				miscDialogueSB.append("<div id='"+slave.getId()+"_INVENTORY' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon()+"</div></div>");
					
				miscDialogueSB.append("<div "+((place.getCapacity()<=Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).size())
							|| !place.isSlaveCell()
							|| (slave.getLocation().equals(Main.game.getPlayer().getLocation()) && slave.getWorldLocation().equals(Main.game.getPlayer().getWorldLocation()))
									?" id='"+slave.getId()+"_TRANSFER_DISABLED' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveTransferDisabled()+"</div></div>"
									:" id='"+slave.getId()+"_TRANSFER' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveTransfer()+"</div></div>"));
			}
			
			if(Main.game.getDialogueFlags().getSlaveTrader()==null || !slave.isAbleToBeSold()) {
				miscDialogueSB.append("<div id='"+slave.getId()+"_SELL_DISABLED' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionSellDisabled()+"</div></div>");
			} else {
				miscDialogueSB.append("<div id='"+slave.getId()+"_SELL' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionSell()+"</div></div>");
			}

			if(Main.game.getDialogueFlags().getSlaveTrader()==null) { // Only show these buttons if you aren't in a trade screen:
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
					miscDialogueSB.append("<div id='"+slave.getId()+"_COSMETICS' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveCosmetics()+"</div></div>");
				} else {
					miscDialogueSB.append("<div id='"+slave.getId()+"_COSMETICS_DISABLED' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveCosmeticsDisabled()+"</div></div>");
				}
			}
			
		} else { // Slave trader's slave:
			miscDialogueSB.append("<div id='"+slave.getId()+"_TRADER' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveInspect()+"</div></div>");

//			miscDialogueSB.append("<div id='"+slave.getId()+"_TRADER_JOB' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveJobDisabled()+"</div></div>");
//
//			miscDialogueSB.append("<div id='"+slave.getId()+"_TRADER_PERMISSIONS' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlavePermissionsDisabled()+"</div></div>");
//			
//			miscDialogueSB.append("<div id='"+slave.getId()+"_TRADER_INVENTORY' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getInventoryIconDisabled()+"</div></div>");
//				
//			miscDialogueSB.append("<div id='"+slave.getId()+"_TRADER_TRANSFER' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveTransferDisabled()+"</div></div>");
			
			if(Main.game.getPlayer().getMoney() < ((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier(null)))) {
				miscDialogueSB.append("<div id='"+slave.getId()+"_BUY_DISABLED' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBuyDisabled()+"</div></div>");
			} else {
				miscDialogueSB.append("<div id='"+slave.getId()+"_BUY' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBuy()+"</div></div>");
			}
			
//			miscDialogueSB.append("<div id='"+slave.getId()+"_TRADER_COSMETICS' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveCosmeticsDisabled()+"</div></div>");
		}
		
		miscDialogueSB.append("</div></div>");
		
		return miscDialogueSB.toString();
	}
	
	private static String getOccupantEntry(GenericPlace place, NPC occupant, AffectionLevel affection, float affectionChange, ObedienceLevel obedience, float obedienceChange, boolean alternateBackground) {
		miscDialogueSB.setLength(0);
		
		miscDialogueSB.append(
				"<div class='container-full-width inner' style='margin-bottom:0;"+(alternateBackground?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
						+ "<div style='width:20%; float:left; margin:0; padding:0;'>"
							+ "<b style='color:"+occupant.getFemininity().getColour().toWebHexString()+";'>"+occupant.getName(true)+"</b><br/>"
							+ "<span style='color:"+occupant.getRace().getColour().toWebHexString()+";'>"
								+Util.capitaliseSentence((occupant.isFeminine()?occupant.getSubspecies().getSingularFemaleName(occupant.getBody()):occupant.getSubspecies().getSingularMaleName(occupant.getBody())))+"</span><br/>"
							+ "<span style='color:"+occupant.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(occupant.getGender().getName())+"</span>"
						+ "</div>"
						+ "<div style='width:20%; float:left; margin:0; padding:0;'>"
							+ (occupant.getWorldLocation()==WorldType.EMPTY
								?"<b style='color:"+PresetColour.BASE_GREY.toWebHexString()+";'>At Work</b>"
										+",<br/>"
										+ "<span style='color:"+WorldType.DOMINION.getColour().toWebHexString()+";'>"+WorldType.DOMINION.getName()+"</span>"
								:"<b style='color:"+occupant.getLocationPlace().getColour().toWebHexString()+";'>"+occupant.getLocationPlace().getName()+"</b>"
									+",<br/>"
									+ "<span style='color:"+occupant.getWorldLocation().getColour().toWebHexString()+";'>"+occupant.getWorldLocation().getName()+"</span>")
						+ "</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+occupant.getAffection(Main.game.getPlayer())+ "</b>"
							+ "<br/><span style='color:"+(affectionChange==0?PresetColour.BASE_GREY:(affectionChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+affection.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+occupant.getObedienceValue()+ "</b>"
							+ "<br/><span style='color:"+(obedienceChange==0?PresetColour.BASE_GREY:(obedienceChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
								+decimalFormat.format(obedienceChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+obedience.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(obedience.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ "<b>"+Util.capitaliseSentence(occupant.getHistory().getName(occupant))+"</b><br/>"
							+ UtilText.formatAsMoney(occupant.hasJob()?PlaceUpgrade.LILAYA_GUEST_ROOM.getUpkeep():0)+"/day"
						+"</div>"
							
				+ "<div style='float:left; width:15%; margin:0 auto; padding:0; display:inline-block; text-align:center;'>"
				
				+ "<div id='"+occupant.getId()+"' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveInspect()+"</div></div>"

				+ (occupant.hasJob()
						?"<div id='"+occupant.getId()+"_JOB' class='square-button big disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveJobDisabled()+"</div></div>"
						:"<div id='"+occupant.getId()+"_JOB' class='square-button big'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getSlaveJob()+"</div></div>")

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
	
	
}
