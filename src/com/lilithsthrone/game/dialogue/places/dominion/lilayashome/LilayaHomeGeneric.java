package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.managers.dominion.SMRoseHands;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.3.9
 * @author Innoxia
 */
public class LilayaHomeGeneric {
	
	public static void dailyUpdate() {
		if(Main.game.getDialogueFlags().hasSavedLong(LilayaSpa.SPA_CONSTRUCTTION_TIMER_ID)) {
			Cell constructionCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
			if(constructionCell!=null) {
				long dayInstalled = Main.game.getDialogueFlags().getSavedLong(LilayaSpa.SPA_CONSTRUCTTION_TIMER_ID);
				if(Main.game.getDayNumber()-dayInstalled >=7) {
					constructionCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_SPA);
					Main.game.getDialogueFlags().removeSavedLong(LilayaSpa.SPA_CONSTRUCTTION_TIMER_ID);
				}
			}
		}
	}
	
	public static List<NPC> getSlavesAndOccupantsPresent() {
		List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
		charactersPresent.removeIf((character) -> character.isElemental());
		return charactersPresent;
	}
	
	public static String getLilayasHouseStandardResponseTabs(int i) {
		switch(i) {
			case 0:
				return "Actions";
			case 1:
				return "Fast Travel";
			case 2:
				if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_PLAYER) {
					return "Bathroom";
				}
		}
		return null;
	}
	
	public static Response getLilayasHouseFastTravelResponses(int index) {
		 if (index == 1) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_PLAYER) {
				return new Response("Your room", "You are already in your room, so there is no need to fast travel there...", null);
			}
			return new Response("Your room", "Fast travel up to your room.", PlaceType.LILAYA_HOME_ROOM_PLAYER.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
					Main.game.setResponseTab(0);
				}
			};

		} else if (index == 2) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_LAB) {
				return new Response("Lilaya's Lab", "You are already in Lilaya's lab, so there is no need to fast travel there...", null);
			}
			return new Response("Lilaya's Lab", "Fast travel to Lilaya's Lab.", PlaceType.LILAYA_HOME_LAB.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 3) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_KITCHEN) {
				return new Response("Kitchen", "You are already in the kitchen, so there is no need to fast travel there...", null);
			}
			return new Response("Kitchen", "Fast travel to the kitchen.", PlaceType.LILAYA_HOME_KITCHEN.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_KITCHEN, false);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 4) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_LIBRARY) {
				return new Response("Library", "You are already in the library, so there is no need to fast travel there...", null);
			}
			return new Response("Library", "Fast travel to the library.", PlaceType.LILAYA_HOME_LIBRARY.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LIBRARY, false);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 5) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_ENTRANCE_HALL) {
				return new Response("Entrance hall", "You are already in the entrance hall, so there is no need to fast travel there...", null);
			}
			return new Response("Entrance hall", "Fast travel to the entrance hall.",PlaceType.LILAYA_HOME_ENTRANCE_HALL.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
					Main.game.setResponseTab(0);
				}
			};
		}
		
		return null;
	}
	
	private static Response getRoomResponse(int responseTab, int index) {
		AbstractPlaceUpgrade coreUpgrade = null;
		for(AbstractPlaceUpgrade pu : Main.game.getPlayer().getLocationPlace().getPlaceUpgrades()) {
			if(pu.isCoreRoomUpgrade()) {
				coreUpgrade = pu;
			}
		}
		
		if(responseTab==1) {
			return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
		}
		
		List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
		List<NPC> slavesAssignedToRoom = new ArrayList<>();
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_OFFICE
				|| coreUpgrade==PlaceUpgrade.LILAYA_SPA) {
			slavesAssignedToRoom.addAll(charactersPresent);
			
		} else {
			for(String slave : Util.mergeLists(Main.game.getPlayer().getFriendlyOccupants(), Main.game.getPlayer().getSlavesOwned())) {
				try {
					NPC slaveNPC = (NPC)Main.game.getNPCById(slave);
					if(slaveNPC != null && (slaveNPC.getHomeWorldLocation()==Main.game.getPlayer().getWorldLocation() && slaveNPC.getHomeLocation().equals(Main.game.getPlayer().getLocation()))) {
						slavesAssignedToRoom.add(slaveNPC);
					}
				} catch (Exception e) {
					Util.logGetNpcByIdError("getRoomResponse()", slave);
				}
			}
		}
//		charactersPresent.removeIf((characterPresent) -> Main.game.getPlayer().hasCompanion(characterPresent) && !slavesAssignedToRoom.contains(characterPresent));
		
		if(index==0) {
			return null;
			
		} else if (index == 1) {
			if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
				return new Response("Manage room", "Enter the management screen for this particular room.", OccupantManagementDialogue.ROOM_UPGRADES) {
					@Override
					public void effects() {
						OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
					}
				};
			} else {
				return new Response("Manage room", "You'll either need a slaver license, or permission from Lilaya to house your friends, before you can access this menu!",  null);
			}
			
		}  else if (index == 2) {
			if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
				return new Response("Manage people", "Enter the management screen for your slaves and friendly occupants.", CORRIDOR) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null);
					}
					@Override
					public void effects() {
						CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
					}
				};
			} else {
				return new Response("Manage people", "You'll either need a slaver license, or permission from Lilaya to house your friends, before you can access this menu!",  null);
			}
		}
		
		int indexPresentStart = 3;
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_OFFICE) {
			indexPresentStart = 4;
			if(index==3) {
				return new Response("Occupancy ledger", "Open the occupancy ledger screen, from which you can manage all rooms, slaves, and friendly occupants.", CORRIDOR) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryOverviewDialogue(null);
					}
					@Override
					public void effects() {
						CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
					}
				};
			}
		}
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_SPA) {
			indexPresentStart = 4;
			if(index==3) {
				return LilayaSpa.SPA_RECEPTION.getResponse(responseTab, index);
			}
		}
		
		if(index-indexPresentStart<slavesAssignedToRoom.size()) {
			NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
			if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
				return new Response(
						UtilText.parse(character, "[npc.Name]"),
						UtilText.parse(character, "Interact with [npc.name]."),
						character.isSlave()
							?SlaveDialogue.SLAVE_START
							:OccupantDialogue.OCCUPANT_START) {
					@Override
					public Colour getHighlightColour() {
						return character.getFemininity().getColour();
					}
					@Override
					public void effects() {
						if(character.isSlave()) {
							SlaveDialogue.initDialogue(character, false);
						} else {
							OccupantDialogue.initDialogue(character, false, false);
						}
					}
				};
				
			} else {
				return new Response(UtilText.parse(character, "[npc.Name]"), UtilText.parse(character, "Although this is [npc.namePos] room, [npc.sheIs] out at work at the moment."), null);
			}
		}
		
		return null;
	}
	
	public static String getBaseRoomDescription() {
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR) {
			return "<p>"
						+ "This particular room has a series of large windows set into one wall, which allow a generous amount of natural daylight to flood out into the corridor when the door is left open."
						+ " Stepping into the room to glance out of the windows, you find yourself looking out onto the hustle and bustle of Dominion's busy streets."
					+ "</p>";
			
		} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR) {
			return "<p>"
						+ "This particular room has a series of large windows set into one wall, which allow a generous amount of natural daylight to flood out into the corridor when the door is left open."
						+ " Stepping into the room to glance out of the windows, you find yourself looking down on the hustle and bustle of Dominion's busy streets."
					+ "</p>";
			
		} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR) {
			return "<p>"
						+ "This room has a series of wide, ceiling-height windows set into one wall, which swing open to allow access to and from the adjoining garden courtyard."
					+ "</p>";
			
		} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR) {
			return "<p>"
						+ "This room has a series of wide, ceiling-height windows set into one wall, which allow a generous amount of natural daylight to flood out into the corridor when the door is left open."
						+ " Stepping inside and walking over to the windows, you find yourself looking down on the house's garden courtyard."
					+ "</p>";
		}
		return "";
	}
	
	public static String getRoomModificationsDescription(boolean includeDefaultRoomDescription) {
		GenericPlace place = Main.game.getPlayer().getLocationPlace();
		
		for(AbstractPlaceUpgrade pu : place.getPlaceUpgrades()) {
			DialogueNode dn = pu.getRoomDialogue(Main.game.getPlayerCell());
			if(dn!=null) {
				return dn.getContent();
			}
		}
		
		StringBuilder roomSB = new StringBuilder();
		
		if(includeDefaultRoomDescription) {
			roomSB.append(getBaseRoomDescription());
		}
		
		for(AbstractPlaceUpgrade pu : PlaceUpgrade.getAllPlaceUpgrades()) { // For consistent ordering.
			if(place.getPlaceUpgrades().contains(pu)) {
				roomSB.append(formatRoomUpgrade(pu));
			}
		}
		
		if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
			List<NPC> charactersHome = Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell());
			for(String occupantId : Main.game.getPlayer().getFriendlyOccupants()) {
				try {
					NPC occupant = (NPC) Main.game.getNPCById(occupantId);
					if(occupant!=null && charactersHome.contains(occupant) && !Main.game.getCharactersPresent().contains(occupant)) {
						roomSB.append(UtilText.parse(occupant,
								"<p>"
									+ "[npc.Name] doesn't appear to be here at the moment, and as you briefly scan the room for any sign of [npc.herHim], you see a little note has been left on [npc.her] bedside cabinet."
											+ " Walking over and picking it up, you read:"
								+ "</p>"
								+ "<p style='text-align:center;'><i>"
									+ "Hi, [pc.name]!<br/>"
									+ "I'm out at work at the moment, my hours are from "+occupant.getHistory().getWorkHourStart()+":00 to "+occupant.getHistory().getWorkHourEnd()+":00, "
										+occupant.getHistory().getStartDay().getDisplayName(TextStyle.FULL, Locale.ENGLISH)+"-"+occupant.getHistory().getEndDay().getDisplayName(TextStyle.FULL, Locale.ENGLISH)+"<br/>"
									+ "Come and see me when I'm not at work!<br/>"
									+ "- [npc.Name]"
								+ "</i></p>"));
					}
				} catch (Exception e) {
					Util.logGetNpcByIdError("getRoomModificationsDescription()", occupantId);
				}
			}
		}
		
		return roomSB.toString();
	}
	

	private static String formatRoomUpgrade(AbstractPlaceUpgrade upgrade) {
		return "<p>"
				+ "<b style='color:"+upgrade.getColour().toWebHexString()+";'>"+upgrade.getName()+"</b><br/>"
				+ upgrade.getRoomDescription(Main.game.getPlayerCell())
			+ "</p>";
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getLabel() {
			return "Lilaya's Home - Street";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				LocalDateTime time = Main.game.getDateNow();
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.daddyFound)
						&& !Main.game.getPlayer().getFetishDesire(Fetish.FETISH_INCEST).isNegative()
						&& Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN) // Only trigger after having met Lyssieth
						&& Main.game.isExtendedWorkTime()
						&& time.getMonth().equals(Month.JUNE) && time.getDayOfMonth()>=14 && time.getDayOfMonth()<=21) { // Father's day timing, 3rd week of June
					return new Response("Enter", "Knock on the door and wait for Rose to let you in.", DaddyDialogue.FIRST_ENCOUNTER) {
						@Override
						public void effects() {
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.daddyFound, true);
						}
					};
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.daddySendingReward)) {
					return new Response("Enter", "Knock on the door and wait for Rose to let you in.", DADDY_PACKAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.daddySendingReward, false);
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_DADDY_PACKAGE"));
							
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);

							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.TELEKENETIC_SHOWER)), false, true));
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.getSpellScrollType(SpellSchool.EARTH)), 5, false, true));
						}
					};
					
				} else {
					return new Response("Enter", "Knock on the door and wait for Rose to let you in.", PlaceType.LILAYA_HOME_ENTRANCE_HALL.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_KNOCK_ON_DOOR"));
							
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNode DADDY_PACKAGE = new DialogueNode("Entrance hall", "", true) {

		@Override
		public int getSecondsPassed() {
			return 1*60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue onwards into Lilaya's house.", ENTRANCE_HALL);
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Corridor", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "CORRIDOR"));
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append("<p>"
							+ "This corridor is deserted at the moment, and there doesn't really seem to be much to do here."
						+ "</p>");
			} else {
				for(NPC slave : charactersPresent) {
					UtilText.nodeContentSB.append(UtilText.parse(slave,
							"<p>"
								+ "Having been assigned to work as a "+(SlaveJob.CLEANING.getName(slave))+", <b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.name]</b> is present in this area."));
					
					if(slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)) {
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								" As you've instructed [npc.herHim] to crawl, [npc.sheIs] down on all fours, "));
					} else {
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								" [npc.sheIs] currently "));
					}
					
					switch(slave.getObedience()) {
						case NEGATIVE_FIVE_REBELLIOUS: case NEGATIVE_FOUR_DEFIANT: case NEGATIVE_THREE_STRONG_INSUBORDINATE:
							UtilText.nodeContentSB.append(UtilText.parse(slave,
										"not even bothering to pretend that [npc.sheIs] cleaning."
									+ "</p>"));
							break;
						case NEGATIVE_ONE_DISOBEDIENT:  case NEGATIVE_TWO_UNRULY:
							UtilText.nodeContentSB.append(UtilText.parse(slave,
										"half-heartedly cleaning the carpet."
									+ "</p>"));
							break;
						case ZERO_FREE_WILLED:
							UtilText.nodeContentSB.append(UtilText.parse(slave,
										"dusting the skirting boards."
									+ "</p>"));
							break;
						case POSITIVE_ONE_AGREEABLE: case POSITIVE_TWO_OBEDIENT:
							UtilText.nodeContentSB.append(UtilText.parse(slave,
										"busily polishing the floorboards."
									+ "</p>"));
							break;
						case POSITIVE_THREE_DISCIPLINED: case POSITIVE_FOUR_DUTIFUL: case POSITIVE_FIVE_SUBSERVIENT:
							UtilText.nodeContentSB.append(UtilText.parse(slave,
										"dutifully dusting, polishing, and cleaning everything in this area."
									+ "</p>"));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(index==0) {
				return null;
				
			} else if(index-1<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-1);
				return new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public Colour getHighlightColour() {
						return slave.getFemininity().getColour();
					}
					@Override
					public void effects() {
						SlaveDialogue.initDialogue((NPC) slave, false);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_WINDOW = new DialogueNode("Room", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return getRoomModificationsDescription(true);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_GARDEN_GROUND_FLOOR = new DialogueNode("Garden-view room", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return getRoomModificationsDescription(true);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_GARDEN = new DialogueNode("Garden-view room", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return getRoomModificationsDescription(true);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode BIRTHING_ROOM = new DialogueNode("Birthing room", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Lilaya has had this room repurposed in order to be a suitable place for delivering children."
					+ "</p>"
					+ "<p>"
						+ "Instead of being carpeted, like most of the other rooms in this house, clean white tiles have been chosen for this room's flooring."
						+ " A surprisingly modern-looking birthing bed is sitting against one wall of the room, but apart from that, there isn't much else in the way of medical equipment."
						+ " A few comfortable chairs have been placed around the edges of the room, but other than those, and a drinks cabinet that's sitting in one corner, the room is devoid of any other furniture."
					+ "</p>"
					+ "<p>"
						+ "In anyone else's house, finding a room dedicated to delivering pregnancies might come as a bit of a shock, but Lilaya seems to be involved in all manner of strange things,"
							+ " so you shrug it off as just another peculiarity of this world."
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			return null;
		}
	};
	
	public static final DialogueNode KITCHEN = new DialogueNode("Kitchen", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			UtilText.nodeContentSB.append("<p>"
						+ "Just like every other room in Lilaya's house, the kitchen is far larger than any you've ever set foot in before."
						+ " A row of wooden cabinets, topped with polished granite, line the edge of the room, and a pair of long, free-standing worktops sit in the middle of the cavernous space."
						+ " The kitchen's trio of cast iron ovens, combined with its rustic oak flooring and lack of any modern-looking appliances, give it a rather vintage look."
					+ "</p>"
					+ "<p>"
						+ "There's an open doorway set into one side of the room, and, looking through the opening, you see a series of fridges, freezers and larder units."
						+ " Ingredients and foodstuffs of all shapes and sizes sit on open shelves, and you find yourself marvelling at the quantity and variety of supplies that are kept in stock."
					+ "</p>");
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append("<p>"
							+ "The kitchen is deserted at the moment, and there doesn't really seem to be much to do here."
						+ "</p>");
			} else {
				for(NPC slave : charactersPresent) {
					UtilText.nodeContentSB.append(UtilText.parse(slave,
							"<p>"
								+ "Having been assigned to work as a "+(SlaveJob.KITCHEN.getName(slave))+", <b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.name]</b> is present in this area."));
					
					if(slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)) {
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								" As you've instructed [npc.herHim] to crawl, [npc.sheIs] down on all fours, and "));
					} else {
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								" [npc.She] "));
					}
					
					switch(slave.getObedience()) {
					case NEGATIVE_FIVE_REBELLIOUS: case NEGATIVE_FOUR_DEFIANT: case NEGATIVE_THREE_STRONG_INSUBORDINATE:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
									"is quite clearly not doing any cooking."
									+ " To make matters worse, [npc.she] doesn't seem to care that you're watching [npc.herHim], and turns [npc.her] back on you."
								+ "</p>"));
						break;
					case NEGATIVE_ONE_DISOBEDIENT:  case NEGATIVE_TWO_UNRULY:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
									"is currently half-heartedly preparing some food on the other side of the kitchen."
								+ "</p>"));
						break;
					case ZERO_FREE_WILLED:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
									" is busy cooking something in one of the kitchen's ovens."
								+ "</p>"));
						break;
					case POSITIVE_ONE_AGREEABLE: case POSITIVE_TWO_OBEDIENT:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
									"is currently preparing some food."
									+ " You can see that [npc.sheIs] putting a lot of effort into making sure that [npc.sheIs] doing a good job."
								+ "</p>"));
						break;
					case POSITIVE_THREE_DISCIPLINED: case POSITIVE_FOUR_DUTIFUL: case POSITIVE_FIVE_SUBSERVIENT:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
									" is dutifully making Lilaya a meal."
									+ " You notice that [npc.sheIs] taking care to prepare it just the way your demonic [lilaya.relation(pc)] likes."
								+ "</p>"));
						break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(index==0) {
				return null;
				
			} else if(index-1<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-1);
				return new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public Colour getHighlightColour() {
						return slave.getFemininity().getColour();
					}
					@Override
					public void effects() {
						SlaveDialogue.initDialogue((NPC) slave, false);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_ROSE = new DialogueNode("Rose's Room", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROOM_ROSE");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if (index == 1) {
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("Call for Rose", "The sign hanging beside Rose's door explicitly says not to disturb her, so it would be best to return during the day if you wanted to talk to her about anything.", null);
				}
				
				return new Response("Call for Rose", "Lilaya's slave, Rose, is always close at hand. If you were to ring the little bell beside her bedroom's door, she'd be sure to come running.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROOM_ROSE_INITIAL_CALL");
						
						Main.game.getDialogueFlags().values.remove(DialogueFlagValue.auntHomeJustEntered);
						Main.game.getNpc(Rose.class).setLocation(Main.game.getActiveWorld().getWorldType(), Main.game.getPlayer().getLocation(), false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	private static String roseContent = "";
	private static boolean giftedRose = false;
	public static final DialogueNode AUNT_HOME_ROSE = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return roseContent;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Lilaya", "Ask Rose about her owner, Lilaya.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_LILAYA");
					}
				};

			} else if (index == 2) {
				return new Response("Slavery", "Ask Rose how she became a slave.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_SLAVE");
					}
				};

			} else if (index == 3) {
				return new Response("World", "Ask Rose to tell you something about this world.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_WORLD");
					}
				};

			} else if (index == 4) {
				return new Response("Duties", "Ask Rose about what duties she's expected to perform.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_DUTIES");
					}
				};

			} else if (index == 5) {
				if(Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_hair_rose"), false) && !giftedRose) {
					return new Response("Offer rose", "Offer Rose the rose you have in your inventory.", AUNT_HOME_ROSE) {
						@Override
						public void effects() {
							roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_OFFER_ROSE");
							Main.game.getPlayer().removeClothingByType(ClothingType.getClothingTypeFromId("innoxia_hair_rose"));
							giftedRose = true;
						}
					};
					
				} else if(giftedRose) {
					return new Response("Rose's hands", "You've never noticed how amazing Rose's hands are before...", ROSE_HANDS) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							giftedRose = false;
						}
					};
					
				} else {
					return new Response("Offer rose", "You do not have a rose to offer to Rose.", null);
				}
				
			} else if (index == 6
					&& (Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer()))) {

				if(Main.game.getPlayer().hasItemType(ItemType.MOTHERS_MILK)) {
					return new Response("Mother's milk", "Give Rose one of the Mother's milk from your inventory, and ask her to give it to Lilaya so that her pregnancy can be over quicker.", AUNT_HOME_ROSE) {
						@Override
						public void effects() {
							giftedRose = false;
							roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_MOTHERS_MILK");
							Main.game.getPlayer().removeItemByType(ItemType.MOTHERS_MILK);
							Main.game.getNpc(Lilaya.class).useItem(Main.game.getItemGen().generateItem(ItemType.MOTHERS_MILK), Main.game.getNpc(Lilaya.class), false);
						}
					};
					
				} else {
					return new Response("Mother's milk", "If you were to have a 'Mother's milk' in your inventory, you could ask Rose to give it to Lilaya so that her pregnancy can be over quicker.", null);
				}
				
			} else if (index == 0) {
				return new Response("Dismiss", "Let Rose get back on with her work.", ROOM_ROSE) {
					@Override
					public void effects() {
						giftedRose = false;
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
					
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode ROSE_HANDS = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "Rose's hands";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_HANDS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Hand-holding", "Warning: This content contains extreme descriptions of hand-holding, finger sucking, and even palm-licking."
						+ " <b>Please remember that you need to have read the disclaimer before playing this game!</b> <b style='color:"+BaseColour.CRIMSON.toWebHexString()+";'>18+ only!</b>",
						true, false,
						new SMRoseHands(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.HAND_SEX_DOM_ROSE)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Rose.class), SexSlotUnique.HAND_SEX_SUB_ROSE))),
						null, null, Rose.END_HAND_SEX);

			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNode GARDEN = new DialogueNode("Garden courtyard", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "GARDEN");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			return null;
		}
	};
	
	public static final DialogueNode FOUNTAIN = new DialogueNode("Water fountain", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "FOUNTAIN");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_HALL = new DialogueNode("Entrance hall", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ENTRANCE_HALL");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if (index == 1) {
				return new Response("Exit", "Leave Lilaya's house.", PlaceType.DOMINION_AUNTS_HOME.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode STAIRCASE_UP = new DialogueNode("Staircase up", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_UP");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if (index == 1) {
				return new Response("Upstairs", "Go upstairs to the first floor.", PlaceType.LILAYA_HOME_STAIR_DOWN.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_STAIR_DOWN, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_UP_SECONDARY = new DialogueNode("Staircase up", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "CORRIDOR")
					+ UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_UP_SECONDARY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if (index == 1) {
				return new Response("Upstairs", "Go upstairs to the first floor.", STAIRCASE_DOWN_SECONDARY){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, Main.game.getPlayer().getLocation(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_DOWN = new DialogueNode("Staircase down", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_DOWN");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if (index == 1) {
				return new Response("Downstairs", "Go back downstairs to the ground floor.",PlaceType.LILAYA_HOME_STAIR_UP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_STAIR_UP, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_DOWN_SECONDARY = new DialogueNode("Staircase down", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "CORRIDOR")
					+ UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_DOWN_SECONDARY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if (index == 1) {
				return new Response("Downstairs", "Go back downstairs to the ground floor.", STAIRCASE_UP_SECONDARY){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, Main.game.getPlayer().getLocation(), false);
					}
				};
			}
			return null;
		}
	};
}
