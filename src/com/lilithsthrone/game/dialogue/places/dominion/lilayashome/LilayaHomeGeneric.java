package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.SlaveJob;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.game.sex.managers.dominion.SMRoseHands;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.3.5.8
 * @author Innoxia
 */
public class LilayaHomeGeneric {
	
	public static List<NPC> getSlavesAndOccupantsPresent() {
		List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
		charactersPresent.removeIf((character) -> character.isElemental());
		return charactersPresent;
	}
	
	private static GameCharacter getMilkingTarget() {
		return MilkingRoom.getTargetedCharacter();
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
			return new ResponseEffectsOnly("Your room", "Fast travel up to your room."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR), PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
					Main.game.setResponseTab(0);
				}
			};

		} else if (index == 2) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_LAB) {
				return new Response("Lilaya's Lab", "You are already in Lilaya's lab, so there is no need to fast travel there...", null);
			}
			return new ResponseEffectsOnly("Lilaya's Lab", "Fast travel to Lilaya's Lab."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_LAB, true);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 3) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_KITCHEN) {
				return new Response("Kitchen", "You are already in the kitchen, so there is no need to fast travel there...", null);
			}
			return new ResponseEffectsOnly("Kitchen", "Fast travel to the kitchen."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_KITCHEN, true);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 4) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_LIBRARY) {
				return new Response("Library", "You are already in the library, so there is no need to fast travel there...", null);
			}
			return new ResponseEffectsOnly("Library", "Fast travel to the library."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_LIBRARY, true);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 5) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_ENTRANCE_HALL) {
				return new Response("Entrance hall", "You are already in the entrance hall, so there is no need to fast travel there...", null);
			}
			return new ResponseEffectsOnly("Entrance hall", "Fast travel to the entrance hall."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_ENTRANCE_HALL, true);
					Main.game.setResponseTab(0);
				}
			};
		}
		
		return null;
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return CityPlaces.TRAVEL_TIME_STREET;
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
							
							Main.mainController.moveGameWorld(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);

							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.getSpellBookType(Spell.TELEKENETIC_SHOWER)), false, true));
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.getSpellScrollType(SpellSchool.EARTH)), 5, false, true));
						}
					};
					
				} else {
					return new ResponseEffectsOnly("Enter", "Knock on the door and wait for Rose to let you in."){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_KNOCK_ON_DOOR"));
							
							Main.mainController.moveGameWorld(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, true);
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
			
			UtilText.nodeContentSB.append("<p>"
						+ "The many corridors running through Lilaya's house are, while extremely impressive, all much of the same."
						+ " Immaculately-clean red carpet runs down the centre of each one, while fine paintings and masterfully-carved marble busts line the walls."
					+ "</p>"
					+ "<p>"
						+ (Main.game.isDayTime()
							?"Delicate glass windows provide a good amount of natural daylight, and Rose seems to be taking care to leave some of them open every now and again, making sure that the air in the house always feels fresh."
							:"The curtains are currently drawn over the corridor's delicate glass windows, but during the day, they're able to provide a good amount of natural daylight.")
					+ "</p>");
			
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
				return new Response(UtilText.parse(charactersPresent.get(index-1), "[npc.Name]"), UtilText.parse(charactersPresent.get(index-1), "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public void effects() {
						SlaveDialogue.initDialogue(charactersPresent.get(index-1), false);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	private static Response getRoomResponse(int responseTab, int index) {
		PlaceUpgrade coreUpgrade = null;
		for(PlaceUpgrade pu : Main.game.getPlayer().getLocationPlace().getPlaceUpgrades()) {
			if(pu.isCoreRoomUpgrade()) {
				coreUpgrade = pu;
			}
		}
		
		if(responseTab==1) {
			return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
		}
		
		List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
		List<NPC> slavesAssignedToRoom = new ArrayList<>();
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_MILKING_ROOM
				|| coreUpgrade==PlaceUpgrade.LILAYA_OFFICE) {
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
			
		} else if(coreUpgrade==PlaceUpgrade.LILAYA_MILKING_ROOM) {
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(Main.game.getPlayerCell().getType(), Main.game.getPlayerCell().getLocation());
			
//			if(index==3) {
//				if(room.isAutoSellMilk()) {
//					return new ResponseEffectsOnly("Milk: [style.colourGold(Selling)]", "Any milk that's collected in this room is being automatically sold.") {
//						@Override
//						public void effects() {
//							room.setAutoSellMilk(false);
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}
//					};
//					
//				} else {
//					return new ResponseEffectsOnly("Milk: [style.colourOrange(Storing)]", "Any milk that's collected in this room is being stored.") {
//						@Override
//						public void effects() {
//							room.setAutoSellMilk(true);
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}
//					};
//				}
//				
//			} else if(index==4) {
//				if(room.isAutoSellCum()) {
//					return new ResponseEffectsOnly("Cum: [style.colourGold(Selling)]", "Any cum that's collected in this room is being automatically sold.") {
//						@Override
//						public void effects() {
//							room.setAutoSellCum(false);
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}
//					};
//					
//				} else {
//					return new ResponseEffectsOnly("Cum: [style.colourOrange(Storing)]", "Any cum that's collected in this room is being stored.") {
//						@Override
//						public void effects() {
//							room.setAutoSellCum(true);
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}
//					};
//				}
//				
//			} else if(index==5) {
//				if(room.isAutoSellGirlcum()) {
//					return new ResponseEffectsOnly("Girlcum: [style.colourGold(Selling)]", "Any girlcum that's collected in this room is being automatically sold.") {
//						@Override
//						public void effects() {
//							room.setAutoSellGirlcum(false);
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}
//					};
//					
//				} else {
//					return new ResponseEffectsOnly("Girlcum: [style.colourOrange(Storing)]", "Any girlcum that's collected in this room is being stored.") {
//						@Override
//						public void effects() {
//							room.setAutoSellGirlcum(true);
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}
//					};
//				}
//				
//			} else 
			if(index>=3 && index<6) {
				return null;
				
			} else if(index==6) {
				if(getMilkingTarget().getBreastRawStoredMilkValue()==0) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(), "There is no milk stored in [npc.namePos] breasts, so [npc.she] can't be milked at the moment!"),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] unable to get access to [npc.her] nipples, so [npc.she] can't be milked at the moment!"),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(), "There are no free milking machines for [npc.name] to use!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer() && getMilkingTarget().isAbleToRefuseSexAsCompanion() && getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(),
									"As [npc.sheIs] not your slave, [npc.name] will only let you do this if [npc.she]"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> you."),
							null);
					
				} else if(!getMilkingTarget().isPlayer() && getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative())) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]")),
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative()
												?"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.she] has a negative desire for both the "
													+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" and "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" fetishes."
												:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.she] has a negative desire for the "+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" fetish.")
											:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.she] has a negative desire for the "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" fetish.")),
							null);
					
				} else {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							"Use this room's spare milking equipment to milk "+(getMilkingTarget().isPlayer()?"yourself":UtilText.parse(getMilkingTarget(), "[npc.name]"))+".",
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualMilkPerHour(getMilkingTarget());
							if(milked < getMilkingTarget().getBreastRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(getMilkingTarget())) {
								milked = (int) Math.min(getMilkingTarget().getBreastRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(getMilkingTarget()));
							}
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getMilk(), milked), milked);

							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_TARGET", getMilkingTarget()));
							}
							
							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+Colour.MILK.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " of [npc.milk] added to this room's storage!")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualMilkPerHour(getMilkingTarget());
							getMilkingTarget().incrementBreastStoredMilk(-milked);
							return true;
						}
					};
				}
				
			} else if(index==7) {
				if(!getMilkingTarget().hasPenisIgnoreDildo()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(), "[npc.Name] [npc.do]n't have a penis, so [npc.she] can't produce any cum..."),
							null);
					
				} else if(getMilkingTarget().getPenisRawStoredCumValue()==0) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(), "There isn't any cum stored in [npc.namePos] balls, so [npc.her] cock can't be milked..."),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] unable to get access to [npc.her] cock, so [npc.she] can't be milked at the moment..."),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(), "There are no free milking machines for [npc.name] to use!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer() && getMilkingTarget().isAbleToRefuseSexAsCompanion() && getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(),
									"As [npc.sheIs] not your slave, [npc.name] will only let you do this if [npc.she]"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> you."),
							null);
					
				} else if(!getMilkingTarget().isPlayer() && getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative())) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative()
												?"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.she] has a negative desire for both the "
													+Fetish.FETISH_CUM_STUD.getName(getMilkingTarget())+" and "+Fetish.FETISH_PENIS_GIVING.getName(getMilkingTarget())+" fetishes."
												:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.she] has a negative desire for the "+Fetish.FETISH_CUM_STUD.getName(getMilkingTarget())+" fetish.")
											:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.she] has a negative desire for the "+Fetish.FETISH_PENIS_GIVING.getName(getMilkingTarget())+" fetish.")),
							null);
					
				} else {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(), "Use this room's spare milking equipment to milk [npc.namePos] cock."),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualCumPerHour(getMilkingTarget());
							room.incrementFluidStored(new FluidStored(getMilkingTarget(), getMilkingTarget().getCum(), milked), milked);

							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_TARGET", getMilkingTarget()));
							}

							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+Colour.CUM.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " of [npc.cum] added to this room's storage!")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualCumPerHour(getMilkingTarget());
							getMilkingTarget().incrementPenisStoredCum(-milked);
							return true;
						}
					};
				}
				
			} else if(index==8) {
				if(!getMilkingTarget().hasVagina()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "[npc.Name] [npc.do]n't have a vagina, so [npc.she] can't produce any girlcum..."),
							null);
					
				} else if(getMilkingTarget().getVaginaWetness().getValue()==0) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "[npc.NamePos] pussy is completely dry, and cannot produce even one drop of girlcum..."),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] unable access to [npc.her] pussy, so can't be milked of [npc.her] girlcum at the moment..."),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "There are no free milking machines for [npc.name] to use!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer() && getMilkingTarget().isAbleToRefuseSexAsCompanion() && getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(),
									"As [npc.sheIs] not your slave, [npc.name] will only let you do this if [npc.she]"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> you."),
							null);
					
				} else if(!getMilkingTarget().isPlayer() && getMilkingTarget().isAbleToRefuseSexAsCompanion() && getMilkingTarget().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.she] has a negative desire for the "+Fetish.FETISH_VAGINAL_RECEIVING.getName(getMilkingTarget())+" fetish."),
							null);
					
				}else {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "Use this room's spare milking equipment to milk [npc.namePos] pussy."),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualGirlcumPerHour(getMilkingTarget());
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getGirlcum(), milked), milked);
							
							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_TARGET", getMilkingTarget()));
							}

							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center; color:"+Colour.GIRLCUM.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " of [npc.girlcum] added to this room's storage!")
									+ "</p>");
						}
					};
				}
				
				
			} else if(index==9) {
				if(!getMilkingTarget().hasBreastsCrotch()) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self udders"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] udders")),
							UtilText.parse(getMilkingTarget(), "[npc.Name] [npc.do]n't have any udders to milk!"),
							null);
					
				} else if(getMilkingTarget().getBreastCrotchRawStoredMilkValue()==0) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "There is no milk stored in [npc.namePos] [npc.crotchBoobs], so [npc.she] can't be milked at the moment!"),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.NIPPLES_CROTCH, true)) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] unable access to [npc.her] [npc.crotchNipples], so can't be milked at the moment..."),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "There are no free milking machines for [npc.name] to use!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer() && getMilkingTarget().isAbleToRefuseSexAsCompanion() && getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(),
									"As [npc.sheIs] not your slave, [npc.name] will only let you do this if [npc.she]"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> you."),
							null);
					
				} else if(!getMilkingTarget().isPlayer() && getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative())) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative()
												?"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.she] has a negative desire for both the "
													+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" and "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" fetishes."
												:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.she] has a negative desire for the "+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" fetish.")
											:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.she] has a negative desire for the "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" fetish.")),
							null);
					
				} else {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "Use this room's spare milking equipment to milk [npc.namePos] [npc.crotchBoobs]."),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualCrotchMilkPerHour(getMilkingTarget());
							if(milked < getMilkingTarget().getBreastCrotchRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(getMilkingTarget())) {
								milked = (int) Math.min(getMilkingTarget().getBreastCrotchRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(getMilkingTarget()));
							}
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getMilkCrotch(), milked), milked);

							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_TARGET", getMilkingTarget()));
							}
							
							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+Colour.MILK.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " of [npc.crotchMilk] added to this room's storage!")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualCrotchMilkPerHour(getMilkingTarget());
							getMilkingTarget().incrementBreastCrotchStoredMilk(-milked);
							return true;
						}
					};
				}
				
			} else if(index==10) {
				if(Main.game.getCharactersPresent().isEmpty()) {
					return new ResponseEffectsOnly(
							"Target: <span style='color:"+getMilkingTarget().getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(getMilkingTarget(), "[npc.Name]")+"</span>",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] currently targeted as the person both to be milked and to have fluids pumped inside of [npc.herHim]."
									+ "If this room wasn't empty, you could use this button to cycle between available targets.")) {
						
					};
					
				} else {
					return new Response(
							"Target: <span style='color:"+getMilkingTarget().getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(getMilkingTarget(), "[npc.Name]")+"</span>",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] currently targeted as the person both to be milked and to have fluids pumped inside of [npc.herHim]. Activate this button to cycle between available targets."),
							null) {
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getCurrentDialogueNode();
						}
						@Override
						public void effects() {
							List<GameCharacter> targetCharactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
							targetCharactersPresent.add(Main.game.getPlayer());
							for(int i=0; i<targetCharactersPresent.size();i++) {
								if(targetCharactersPresent.get(i).equals(getMilkingTarget())) {
									if(i==targetCharactersPresent.size()-1) {
										MilkingRoom.setTargetedCharacter(targetCharactersPresent.get(0));
									} else {
										MilkingRoom.setTargetedCharacter(targetCharactersPresent.get(i+1));
									}
									break;
								}
							}
							Main.game.updateResponses();
						}
					};
				}
				
			} else if(index-11<charactersPresent.size()) {
				GameCharacter character = charactersPresent.get(index-11);
				return new Response(UtilText.parse(character, "[npc.Name]"), UtilText.parse(character, "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public void effects() {
						SlaveDialogue.initDialogue((NPC) character, false);
					}
				};
				
			}
			
		}
		
		int indexPresentStart = 3;
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_OFFICE) {
			indexPresentStart = 4;
			if(index==3) {
//				if(Main.game.getPlayer().isHasSlaverLicense()) {
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
//				} else {
//					return new Response("Slavery Overview", "You'll need a slaver license before you can access this menu!",  null);
//				}
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
	
	private static StringBuilder roomSB = new StringBuilder();
	public static String getRoomModificationsDescription() {
		GenericPlace place = Main.game.getPlayer().getLocationPlace();
		roomSB.setLength(0);
		
		for(PlaceUpgrade pu : PlaceUpgrade.values()) {
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
	

	private static String formatRoomUpgrade(PlaceUpgrade upgrade) {
		return "<p>"
				+ "<b style='color:"+upgrade.getColour().toWebHexString()+";'>"+upgrade.getName()+"</b><br/>"
				+ upgrade.getRoomDescription(Main.game.getPlayerCell())
			+ "</p>";
	}
	
	public static final DialogueNode MILKED = new DialogueNode("Room", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		
		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Unstrap yourself from the milking machine and continue on your way.", MILKED) {
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
			return "<p>"
						+ "This particular room has a series of large windows set into one wall, which allow a generous amount of natural daylight to flood out into the corridor when the door is left open."
						+ " Stepping into the room to glance out of the windows, you find yourself looking down on the hustle and bustle of Dominion's busy streets."
					+ "</p>"
					+getRoomModificationsDescription();
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
			return "<p>"
						+ "This room has a series of wide, ceiling-height windows set into one wall, which swing open to allow access to and from the adjoining garden courtyard."
					+ "</p>"
					+getRoomModificationsDescription();
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
			return "<p>"
						+ "This room has a series of wide, ceiling-height windows set into one wall, which allow a generous amount of natural daylight to flood out into the corridor when the door is left open."
						+ " Stepping inside and walking over to the windows, you find yourself looking down on the house's garden courtyard."
					+ "</p>"
					+getRoomModificationsDescription();
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
	
	public static final DialogueNode ROOM_ARTHUR_INSTALLATION = new DialogueNode("Arthur's Room", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding that this room would be suitable to convert for Arthur's use, you walk over to the bell-pull in one corner and give it a tug."
						+ " After just a few moments, Rose steps through the door, before curtsying and greeting you,"
						+ " [rose.speech(Is there anything you need, [pc.name]?)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(I've decided that this would be the best place for Arthur to have as his room,)]"
						+ " you reply, and, deciding that you'd like to get it set up as quickly as possible, you continue,"
						+ " [pc.speech(If you show me what needs moving in here, I can give you a hand.)]"
					+ "</p>"
					+ "<p>"
						+ "Thanking you for the offer of help, Rose proceeds to lead you to a storage cupboard, where a significant amount of spare lab equipment is being kept."
						+ " After showing you which of the items Arthur will need in order to carry out experiments for Lilaya, Rose picks up one of the smaller desks and sets off in the direction of the room."
						+ " Following her lead, you start moving furniture into Arthur's new room, and after just half an hour, you manage to get all of the work finished."
					+ "</p>"
					+ "<p>"
						+ "[rose.speech(I'll fetch mistress,)]"
						+ " Rose says, before slipping out of the room."
					+ "</p>"
					+ "<p>"
						+ "After just a couple of minutes, the door pushes open again, and Rose steps into the room, followed by Arthur."
						+ " [rose.speech(Here you are, sir. If there's anything you need, please use the bell-pull to call for me.)]"
					+ "</p>"
					+ "<p>"
						+ "With that, Rose exits the room once again, leaving you alone with Arthur."
						+ " He lets out a deep sigh as he walks over to sit on his new bed, before looking up at you and smiling."
						+ " [arthur.speech(I don't think I've said it properly yet, so thank you [pc.name], for rescuing me.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(You're welcome,)]"
						+ " you reply."
						+ " [pc.speech(So do you think you can figure out how I was transported into your world? Lilaya told me that you're the best arcane researcher out there...)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Lilaya said that, huh?)]"
						+ " Arthur says, leaning back and smiling."
						+ " [arthur.speech(Well, I do have a certain theory, but when I told Lilaya about it, she wasn't too convinced."
							+ " Considering my position, I don't really have a choice but to perform the experiments and research that she asks for, but I'm absolutely convinced that they'll get us no closer to unravelling your mystery."
							+ " What I'd really like to do is get to talk to someone about my theory...)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(You could talk to me about it,)]"
						+ " you offer."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Ah, well, thank you, but I wouldn't want to worry you, in case I turn out to be wrong,)]"
						+ " Arthur stammers, before quickly continuing,"
						+ " [arthur.speech(What I really meant is that I'd like to talk to someone who would be able to confirm or deny my theory absolutely."
							+ " Someone with more understanding of the arcane than anyone you'd ever find wandering around in Dominion."
							+ " Someone like a Lilin."
							+ " But not just any Lilin; it needs to be one of the seven elders.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(An elder Lilin? Lilaya's mother wouldn't happen to be one of those, would she?)]"
						+ " you offer, remembering that Rose once told you that Lilaya's mother was the Lilin Lyssieth."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Not only is she an elder Lilin, but she's the very one who told me such a thing existed in the first place! It'd be near-impossible to get any other Lilin to talk with me, but I know Lyssieth will...)]"
						+ " Arthur explains, before suddenly blurting out,"
						+ " [arthur.speech(B-But please, <i>please</i> don't tell Lilaya that I'm looking to speak with her mother...)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Find Lyssieth", "If you ever want to find out what's going on, it looks like you'll have to agree to help.", ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH = new DialogueNode("Arthur's Room", ".", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Ok, I'll find Lyssieth and see if I can convince her to meet with you,)]"
						+ " you say,"
						+ " [pc.speech(but if I'm to keep this from Lilaya, I'll need to know why.)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur smiles happily as he hears that you're willing to help, but as you demand an explanation, he lets out a heavy sigh."
						+ " [arthur.speech(That's fair enough, I suppose. I'd have to explain the whole situation to you anyway, as it's the reason why Lyssieth isn't exactly easy to get hold of these days.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Is this related to Lilaya's accusations which I witnessed earlier?)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Yes... You see, as I'm sure you already know, Lilaya and I used to work here together."
							+ " After a time, we started dating, but it didn't last long...)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur seems quite reluctant to continue, but after a moment of silence, he continues,"
						+ " [arthur.speech(At the time, Lyssieth used to visit very regularly, and would always make a fuss over Lilaya."
							+ " Well, the last time Lyssieth visited, Lilaya was out on a rare errand, and, left alone with her in the lab... well... i-it's hard saying no to a Lilin... and after she told me she had a thing for humans...)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Ah,)]"
						+ " you sigh, seeing where this is going."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Mmm... Needless to say, Lilaya returned and caught us in the act,)]"
						+ " Arthur continues, casting his eyes to the floor,"
						+ " [arthur.speech(and you can imagine the rest... She still hasn't forgiven either of us for that."
							+ " I mean, polyamory isn't exactly uncommon - hell, Lilaya herself was still sleeping with Rose at the time - but seeing as it was with her mother, as well as the fact that we'd done it behind her back...)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur falls into silence for a moment again, before shaking his head and looking up at you."
						+ " [arthur.speech(Anyway, that's the whole reason for Lilaya living alone like this."
							+ " You see, Lyssieth only has a few recognised children, and amongst them, Lilaya was clearly her favourite."
							+ " When Lilaya told her mother that she never wanted to see her again, Lyssieth really took it hard."
							+ " Although she'd been appointed by Lilith to govern the undercity of Submission, she'd never really taken her duties seriously before, but after all this, she retreated to her official residence down there."
							+ " I don't think she's been seen up here in Dominion since....)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(So you want me to go down into Submission, find Lyssieth, and convince her to come and meet with you, all behind Lilaya's back?"
							+ " I can't betray her trust like that...)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(This is all to find out the truth behind why you appeared in this world,)]"
						+ " Arthur tries to remind you, but, upon seeing the serious expression on your face, he concedes,"
						+ " [arthur.speech(Bloody hell... Fine, I'll admit that I'm more than a little interested in it as well."
							+ " Don't worry about going behind her back; I'll tell Lilaya about all of this."
							+ " I'll need a little time to build up to it, however, so until then please don't mention anything about this to her.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Ok, but I won't bring Lyssieth here until you've told Lilaya,)]"
						+ " you firmly state."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Don't worry, by the time you've found and convinced her, I'll have let Lilaya know."
							+ " Well, with that settled, I should get on with these experiments Lilaya's assigned to me."
							+ " If there's ever anything you need, please don't hesitate to drop by and let me know; my door's always open to the person who saved me!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Allow Arthur to get on with his experiments.", ROOM_ARTHUR);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR = new DialogueNode("Arthur's Room", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in Arthur's Room, which, due to the occupant's need to carry out any experiments assigned to him, looks like a miniature version of Lilaya's lab."
						+ " The walls are lined with cluttered tables, stacked bookcases, and cupboards full of all manner of scientific-looking apparatus."
						+ " A solitary bed is positioned in one corner, but aside from that, there's no other indication that this room doubles as Arthur's sleeping quarters."
					+ "</p>"
					+ "<p>"
						+ "Busily performing one of the experiments that Lilaya has assigned to him, Arthur looks to be hard at work, although you don't think he'd mind if you asked him a few questions..."
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
			if(index == 1) {
				return new Response("Lyssieth", "Ask Arthur about Lilaya's mother, Lyssieth.", ROOM_ARTHUR_LYSSIETH);
				
			} else if(index == 2) {
				return new Response("Lilaya", "Ask Arthur about his past relationship with Lilaya.", ROOM_ARTHUR_LILAYA);
				
			} else if(index == 3) {
				if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HYPNO_WATCH)) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_HYPNO_WATCH)) {
						return new Response("Experiments", "Ask Arthur about the sort of experiments he's currently running.", ROOM_ARTHUR_HYPNO_WATCH_START) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_HYPNO_WATCH));
							}
						};
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH) == Quest.SIDE_HYPNO_WATCH_VICKY) {
						if(!Main.game.getPlayer().hasItem(AbstractItemType.generateItem(ItemType.ARTHURS_PACKAGE))) {
							return new Response("Deliver package", "You need to fetch the package from Arcane Arts first!", null);
							
						} else {
							return new Response("Deliver package", "Hand over the package that you fetched from Arcane Arts.", ROOM_ARTHUR_HYPNO_WATCH_DELIVERY) {
								@Override
								public void effects() {
									Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.ARTHURS_PACKAGE));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT));
									Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
								}
							};
						}
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_LYSSIETH = new DialogueNode("Arthur's Room", ".", false) {

		@Override
		public String getContent() {
			return "<p>"
						+ "Wanting to know a little more about Lilaya's mother, you approach Arthur and get his attention, before asking,"
						+ " [pc.speech(What was Lyssieth like back when you used to work with Lilaya?)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur smiles a little at the question."
						+ " [arthur.speech(In a lot of ways, she was exactly what you'd expect one of Lilith's daughters to be; confident, controlling, and immensely powerful."
							+ " My first impression of her was one of incredible haughtiness and arrogance, but as I watched her interactions with Lilaya, I saw that she had a softer, more loving side...)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur seems to get a little caught-up in his thoughts for a moment, before shaking his head and looking up at you."
						+ " [arthur.speech(Of course, as you'd expect from a Lilin, she's practically dripping with sex appeal."
							+ " She also has a thing for humans, which explains why most of her daughters are half-demons; if a Lilin's partner isn't corrupted into a demon before having sex, then their offspring end up like Lilaya."
							+ " Not that that's a bad thing, of course, but society does typically treat half-demons as the lowest of all the demonic races."
							+ " That is, of course, unless they've been recognised by their mother, like Lilaya...)]"
					+ "<p>"
					+ "</p>"
						+ "Arthur turns to one side to check on his experiment, making a satisfied humming noise before facing you once more and continuing,"
						+ "[arthur.speech(Sorry about that."
							+ " Anyway, you should know that Lyssieth's love of humans even extends to the appearance she likes to take on the most."
							+ " The Lilin can transform themselves into any race imaginable, you see, and Lyssieth was most often seen taking the form of a human."
							+ " She'd change her style almost every day, but no matter what appearance she took on, she'd always be so beautiful...)]"
					+ "</p>"
					+ "<p>"
						+ "Again, Arthur seems to lose himself in his thoughts, and, having heard enough, you decide to take a step back and let him resume his experiments."
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index == 1) {
					return new Response("Lyssieth", "You're already asking Arthur about Lyssieth.", null);
					
				}
			}
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_LILAYA = new DialogueNode("Arthur's Room", ".", false) {

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself curious to know what Arthur's memories of Lilaya are like, so, stepping forwards, you ask,"
						+ " [pc.speech(I hope you don't mind me asking, but what was Lilaya like when you two were dating?)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Loving, but very controlling,)]"
						+ " Arthur replies with a sad smile."
						+ " [arthur.speech(While she'd often spend entire evenings alone with Rose, she all but banned me from spending any time with anyone else."
							+ " Not that I really had other people that I wanted to see, but it still felt a little stifling."
							+ " I'm not trying to make excuses for what I did, but maybe that's partly why I... erm, you know... with Lyssieth..."
							+ " Anyway, returning to your question, Lilaya was much as she still is now; kind and friendly with people she knows, but nervous and confrontational around strangers."
							+ " Like others with demonic blood, she's naturally gifted with the arcane, and also has quite a high sex drive...)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur blushes and clears his throat, before turning back to his experiments."
						+ " [arthur.speech(I-I need to get on with this now!)]"
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index == 2) {
					return new Response("Lilaya", "You're already asking Arthur about Lilaya.", null);
					
				}
			}
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_START = new DialogueNode("Arthur's Room", ".", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROOM_ARTHUR_HYPNO_WATCH_START");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};

	
	private static GameCharacter testingSlave;
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_DELIVERY = new DialogueNode("Arthur's Room", ".", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROOM_ARTHUR_HYPNO_WATCH_DELIVERY");
		}

		@Override
		public String getResponseTabTitle(int index) {
//			if(index==0) {
//				return "Offer self";
//				
//			} else if(index==1) {
//				return "Offer slave";
//				
//			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
//			if(responseTab==0) {
				if(index == 1) {
					return new Response("Agree", "You trust Lilaya enough to agree with her request.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF);
					
				} 
				//TODO
//				else if(index == 2) {
//					return new Response("Refuse", "Refuse to be a test subject.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED);
//				}
				else {
					return null;
				}
				
//			} else if(responseTab == 1){
//				if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HYPNO_WATCH) && Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH) == Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT) {
//					if(Main.game.getPlayer().getSlavesOwned().isEmpty()) {
//						return new Response("No slaves", "You don't have any slaves, so you'll have to offer yourself for Arthur's tests!", null);
//						
//					} else {
//						if(index!=0 && index<=Main.game.getPlayer().getSlavesOwned().size()) {
//							return new Response(Main.game.getNPCById((Main.game.getPlayer().getSlavesOwned().get(index-1))).getName(),
//									"Offer this slave to Arthur for testing.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SLAVE) {
//								@Override
//								public void effects() {
//									testingSlave = Main.game.getNPCById(Main.game.getPlayer().getSlavesOwned().get(index-1));
//									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.ORIENTATION_HYPNO_WATCH), false));
//									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.SIDE_HYPNO_WATCH));
//									Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
//								}
//							};
//						}
//					}
//					
//				} else {
//					return null;
//				}
//			}
//			
//			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF = new DialogueNode("Arthur's Room", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wake up", "You suddenly snap out of your trance.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.ORIENTATION_HYPNO_WATCH), false, true));

						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_UTIL_COMPLETE));
						
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP = new DialogueNode("Arthur's Room", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Let Arthur continue with his other experiments.", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_SLAVE = new DialogueNode("Arthur's Room", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parse(testingSlave,
					"<p>"
						+ "Hypno-watch slave: [npc.name]"
						+ "Lilaya tries to turn [npc.herHim] (Gynephilic if not, else Androphilic if not)."
						+ "It starts to work, and [npc.name] finds Lilaya/Arthur looking hotter than they did a moment ago, but she suddenly stops, and you shake your head clear of the thoughts, finding that it's had no permanent effect."
						+ "She says she detected extremely strong arcane corruption about to burst out, and then Lilaya resets the enchantment for good measure."
						+ "She starts to scold Arthur for creating something like this, she gives you the watch."
						+ "(She demands Arthur write up a full report report about it, on top of his other work)."
						+ " She leaves, Arthur groans."
					+ "</p>");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Let Arthur continue with his other experiments.", ROOM_ARTHUR);
				
			} else {
				return null;
			}
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
				return new Response(UtilText.parse(charactersPresent.get(index-1), "[npc.Name]"), UtilText.parse(charactersPresent.get(index-1), "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public void effects() {
						SlaveDialogue.initDialogue(charactersPresent.get(index-1), false);
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
							Main.game.getNpc(Lilaya.class).useItem(AbstractItemType.generateItem(ItemType.MOTHERS_MILK), Main.game.getNpc(Lilaya.class), false);
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
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
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
				return new ResponseEffectsOnly("Exit", "Leave Lilaya's house."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_AUNTS_HOME, true);
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
			return 10;
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
				return new ResponseEffectsOnly("Upstairs", "Go upstairs to the first floor."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR), PlaceType.LILAYA_HOME_STAIR_DOWN, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode STAIRCASE_DOWN = new DialogueNode("Staircase down", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
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
				return new ResponseEffectsOnly("Downstairs", "Go back downstairs to the ground floor."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_STAIR_UP, true);
					}
				};

			} else {
				return null;
			}
		}
	};
}
