package com.lilithsthrone.game.dialogue.places.dominion.cityHall;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.dominion.vanessa.SMVanessaOral;
import com.lilithsthrone.game.sex.managers.dominion.vanessa.SMVanessaSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.2
 * @version 0.3.2
 * @author Innoxia
 */
public class CityHallDemographics {
	
	public static final DialogueNode CITY_HALL_DEMOGRAPHICS_ENTRANCE = new DialogueNode("Bureau of Demographics", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "CITY_HALL_DEMOGRAPHICS_ENTRANCE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Ring bell",
						Main.game.getPlayer().hasCompanions()
							?UtilText.parse(Main.game.getPlayer().getMainCompanion(), "Tell [npc.name] to wait outside, before ringing the little bell on [vanessa.namePos] desk and waiting for her to appear.")
							:"Ring the little bell on [vanessa.namePos] desk and wait for her to appear.",
						CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "CITY_HALL_DEMOGRAPHICS_RING_BELL"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaIntroduced, true);
						
						if(Main.game.getNpc(Vanessa.class).isVisiblyPregnant()) {
							Main.game.getNpc(Vanessa.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						
						for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
							character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						}
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Decide against bothering [vanessa.name], and head back out into the corridor.", CityHall.CITY_HALL_CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "CITY_HALL_DEMOGRAPHICS_ENTRANCE_TURN_BACK"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_DEMOGRAPHICS_MAIN = new DialogueNode("Bureau of Demographics", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Name change", "Ask [vanessa.name] about getting your name changed.", NAME_CHANGE);
				
			} else if (index == 2) {
				if(Main.game.getPlayer().hasItemType(ItemType.OFFSPRING_MAP)) {
					return new Response("Offspring map", "You've already purchased an offspring map from [vanessa.name], and do not need another one.", null);
					
				} else {
					return new Response("Offspring map", "Ask [vanessa.name] about obtaining a map of where any offspring of yours live.", OFFSPRING_MAP);
				}
				
			} else if (index == 3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyHelped)) {
					return new Response("Offer help", "You've already helped [vanessa.name] with her work today.", null);
				}
				return new Response("Offer help", "Ask [vanessa.name] if she needs any help.", OFFER_HELP) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyHelped, true);
						Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ARCHIVES);
						Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ARCHIVES);
						
						for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
							character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						}
					}
				};
				
			} else if (index == 4 && (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyHelped) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaMassaged))) {
				if(Main.game.isFootContentEnabled()) { // Foot massage:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyMassage)) {
						return new Response("Foot massage", "You've already given [vanessa.name] a foot massage today. She won't want another one until tomorrow.", null);
					}
					
					return new Response("Foot massage", "Offer [vanessa.name] a foot massage.", FOOT_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);
							
							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.FOOT), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing any shoes!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else { // Shoulder massage:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyMassage)) {
						return new Response("Shoulder massage", "You've already given [vanessa.name] a massage today. She won't want another one until tomorrow.", null);
					}
					
					return new Response("Shoulder massage", "Offer [vanessa.name] a shoulder massage.", SHOULDER_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);

							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.TORSO_OVER), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing a cardigan!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
				
			} else if (index == 5
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyHelped)
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaFucked)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyMassage)) {
				if(Main.game.isFootContentEnabled()) { // Foot massage:
					return new Response("Make her beg", "Get [vanessa.name] to beg you for a foot massage.", FOOT_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);
							
							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.FOOT), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing any shoes!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_BEG"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else { // Shoulder massage:
					return new Response("Make her beg", "Get [vanessa.name] to beg you for a shoulder massage.", SHOULDER_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);

							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.TORSO_OVER), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing a cardigan!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_BEG"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
				
			} else if (index == 6) {
				return new Response("Cataloguing", "Ask [vanessa.name] about what sort of cataloguing work she's responsible for.", QUESTION_CATALOGUING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "QUESTION_CATALOGUING"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaAskedAboutCatalogue, true);
					}
				};
				
			} else if (index == 7) {
				return new Response("Solitary", "Ask [vanessa.name] why she's the only person working in this department.", QUESTION_SOLITARY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "QUESTION_SOLITARY"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaAskedAboutSolitary, true);
					}
				};
				
			} else if(index==10 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaHelped)) {
				return new ResponseEffectsOnly("Calling her: [vanessa.Name]", "Cycle between calling the elderly fox-girl 'Ms. Cunningham' and 'Vanessa'.") {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).setPlayerKnowsName(!Main.game.getNpc(Vanessa.class).isPlayerKnowsName());
					}
				};
				
			} if (index == 0) {
				return new Response("Leave", "Decide against bothering [vanessa.name], and head back out into the corridor.", CityHall.CITY_HALL_CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ARCHIVES);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode QUESTION_CATALOGUING = new DialogueNode("Bureau of Demographics", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6) {
				return new Response("Cataloguing", "You just asked [vanessa.name] how name cataloguing works.", null); 
			}
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode QUESTION_SOLITARY = new DialogueNode("Bureau of Demographics", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==7) {
				return new Response("Solitary", "You just asked [vanessa.name] why she's the only person working in this department.", null); 
			}
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFER_HELP = new DialogueNode("Bureau of Demographics", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaHelped)) {
				if (index == 1) {
					return new Response("Work", "Get to work alongside [vanessa.name] cataloguing these papers.", OFFER_HELP_FINISH) {
						@Override
						public void effects() {
							Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 10));
							
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
							}
						}
					};	
					
				}
				
			} else {
				if (index == 1) {
					return new Response("Vanessa",
							"Tell the elderly fox-girl that you'd be happy to call her 'Vanessa', before getting to work on cataloguing papers."
									+ "<br/><i>(You can later switch between calling her 'Vanessa' and 'Ms. Cunningham' at any time.)</i>",
							OFFER_HELP_FINISH) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP_VANESSA"));
							
							Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 15));
							
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
							}
							Main.game.getNpc(Vanessa.class).setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaHelped, true);
						}
					};	
					
				} else if(index==2) {
					return new Response("Ms. Cunningham",
							"Tell the elderly fox-girl that you prefer to call her 'Ms. Cunningham', before getting to work on cataloguing papers."
									+ "<br/><i>(You can later switch between calling her 'Vanessa' and 'Ms. Cunningham' at any time.)</i>",
							OFFER_HELP_FINISH) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP_MS_CUNNINGHAM"));
							
							Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 10));
							
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
							}
							Main.game.getNpc(Vanessa.class).setPlayerKnowsName(false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaHelped, true);
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OFFER_HELP_FINISH = new DialogueNode("Bureau of Demographics", "-", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP_FINISH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode FOOT_MASSAGE = new DialogueNode("Bureau of Demographics", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Bare feet", "Pull off [vanessa.namePos] pantyhose so that you can massage her bare feet.", BARE_FOOT_MASSAGE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						try {
							Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.SOCK), true, Main.game.getNpc(Vanessa.class));
						} catch(Exception ex) { System.err.println("Vanessa is not wearing pantyhose!"); }
						
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("Stop", "Stop giving [vanessa.name] a foot massage.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_STOP"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BARE_FOOT_MASSAGE = new DialogueNode("Bureau of Demographics", "-", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "BARE_FOOT_MASSAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Perform cunnilingus", "You are unable to access your mouth, so cannot perform cunnilingus on [vanessa.name]!", null);
				}
				return new ResponseSex("Perform cunnilingus",
						"Shuffle forwards between [vanessa.namePos] legs and start eating her out.",
						true, true,
						new SMVanessaOral(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						END_ORAL_SEX,
						UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_ORAL")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
					}
				};
				
			} else if (index == 2) {
				return new Response("Stop", "Stop giving [vanessa.name] a foot massage.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "BARE_FOOT_MASSAGE_STOP"));
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode SHOULDER_MASSAGE = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Thigh massage", "[vanessa.Name] kicks off her shoes and asks you to pull off her pantyhose so that you can massage her bare thighs.", THIGH_MASSAGE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						try {
							Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.FOOT), true, Main.game.getNpc(Vanessa.class));
						} catch(Exception ex) { System.err.println("Vanessa is not wearing any shoes!"); }
						try {
							Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.SOCK), true, Main.game.getNpc(Vanessa.class));
						} catch(Exception ex) { System.err.println("Vanessa is not wearing pantyhose!"); }
						
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("Stop", "Stop giving [vanessa.name] a massage.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_STOP"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode THIGH_MASSAGE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "THIGH_MASSAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Perform cunnilingus", "You are unable to access your mouth, so cannot perform cunnilingus on [vanessa.name]!", null);
				}
				return new ResponseSex("Perform cunnilingus",
						"Shuffle forwards between [vanessa.namePos] legs and start eating her out.",
						true, true,
						new SMVanessaOral(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						END_ORAL_SEX,
						UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_ORAL")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
					}
				};
				
			} else if (index == 2) {
				return new Response("Stop", "Stop giving [vanessa.name] a thigh massage.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "THIGH_MASSAGE_STOP"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode END_ORAL_SEX = new DialogueNode("Pushed back", "[vanessa.Name] pushes you away from her pussy and grins down at you...", true) {
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>0) {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_ORAL_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_ORAL_SEX_NO_ORGASM");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().hasPenis() && (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true) || !Main.game.getPlayer().hasVagina())) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						boolean biped = !Main.game.getPlayer().isTaur();
						return new ResponseSex("Sex",
								biped
									?"Push [vanessa.name] back onto her desk and fuck her."
									:"Mount [vanessa.name] and start fucking her.",
								true, true,
								biped
									?new SMVanessaSex(
											SexPosition.OVER_DESK,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.BETWEEN_LEGS)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotDesk.OVER_DESK_ON_BACK)))
									:new SMVanessaSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotAllFours.ALL_FOURS))),
								null,
								null,
								END_SEX,
								UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_SEX_FUCKING_VANESSA")){
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), PenisVagina.PENIS_FUCKING_START, false, true));
							}
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
							}
						};
						
					} else {
						return new Response("Sex", "As you cannot get access to your cock, [vanessa.name] cannot have sex with you...", null);
					}
					
				} else if(Main.game.getPlayer().hasVagina()) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						boolean biped = !Main.game.getPlayer().isTaur();
						return new ResponseSex("Receive cunnilingus",
								biped
									?"Allow [vanessa.name] to swap places with you so that she can eat you out."
									:"Allow [vanessa.name] to kneel behind you so that she can eat you out.",
								true, true,
								biped
									?new SMVanessaOral(
											SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.PERFORMING_ORAL)))
									:new SMVanessaOral(
											SexPosition.STANDING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotStanding.PERFORMING_ORAL_BEHIND))),
								null,
								null,
								END_SEX_ORAL_RECEIVING,
								UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_SEX_EATEN_OUT")){
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vanessa.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							}
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
							}
						};
						
					} else {
						return new Response("Receive cunnilingus", "As you cannot get access to your pussy, [vanessa.name] cannot have sex with you...", null);
					}
					
				} else {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						boolean biped = !Main.game.getPlayer().isTaur();
						return new ResponseSex("Receive oral",
								biped
									?"Allow [vanessa.name] to swap places with you so that she can perform oral on your genderless mound."
									:"Allow [vanessa.name] to kneel behind you so that she can perform oral on your genderless mound.",
								true, true,
								biped
									?new SMVanessaOral(
											SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.PERFORMING_ORAL)))
									:new SMVanessaOral(
											SexPosition.STANDING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotStanding.PERFORMING_ORAL_BEHIND))),
								null,
								null,
								END_SEX_ORAL_RECEIVING,
								UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_SEX_EATEN_OUT_MOUND")){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
							}
						};
						
					} else {
						return new Response("Receive oral", "As you cannot get access to your genderless mound, [vanessa.name] cannot have sex with you...", null);
					}
				}
				
			} else if(index==2) {
				return new Response("Decline", "Tell [vanessa.name] that you've had enough for now.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_ORAL_SEX_DECLINE_MORE"));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode END_SEX = new DialogueNode("Finished", "Having had enough sex for now, the two of you start to get your clothing back in order...", true) {
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>0) {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX_NO_PLAYER_ORGASM");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode END_SEX_ORAL_RECEIVING = new DialogueNode("Finished", "Having had enough sex for now, the two of you start to get your clothing back in order...", true) {
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>0) {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX_NO_PLAYER_ORGASM");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFSPRING_MAP = new DialogueNode("Bureau of Demographics", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFSPRING_MAP");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()>=ItemType.OFFSPRING_MAP.getValue()) {
					return new Response("Purchase ("+UtilText.formatAsMoney(ItemType.OFFSPRING_MAP.getValue(), "span")+")", "Tell [vanessa.name] that you want to buy an arcane offspring map.", OFFSPRING_MAP_PURCHASE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-ItemType.OFFSPRING_MAP.getValue()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.OFFSPRING_MAP), false));
						}
					};
					
				} else {
					return new Response("Purchase ("+UtilText.formatAsMoneyUncoloured(ItemType.OFFSPRING_MAP.getValue(), "span")+")", "You don't have enough money for this...", null);
				}
				
			} else if (index == 2) {
				return new Response("Change mind", "Decide against buying an offspring map.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFSPRING_MAP_BACK"));
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode OFFSPRING_MAP_PURCHASE = new DialogueNode("Bureau of Demographics", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFSPRING_MAP_PURCHASE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	private static boolean unsuitableName = false;
	private static boolean unsuitableSurname = false;
	
	public static final DialogueNode NAME_CHANGE = new DialogueNode("Bureau of Demographics", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "NAME_CHANGE"));
			
			UtilText.nodeContentSB.append("<br/>"
					+ "<div class='container-full-width' style='text-align:center;'>"
					+ "<div style='position:relative; display:inline-block; padding-bottom:0; margin 0 auto; vertical-align:middle; width:100%; text-align:center;'>"
					+ "<i>"
						+ "Your first name can be set as three values; your masculine name, androgynous name, and feminine name."
						+ " Your name will automatically switch to the one which corresponds to your body femininity."
					+ "</i>"
					+ "<br/>"
					+ "<p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>First name: </p>"
					+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
							+ "<input type='text' id='nameMasculineInput' style=' color:"+PresetColour.MASCULINE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getMasculine())+ "'>"
							
					+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
						+ "<input type='text' id='nameAndrogynousInput' style=' color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getAndrogynous())+ "'>"
						
					+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
						+ "<input type='text' id='nameFeminineInput' style=' color:"+PresetColour.FEMININE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getFeminine())+ "'>"
					
					+ "<br/>"
					+ "<p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>Surname: </p>"
					+ "<form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='surnameInput' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getSurname())+ "'></form>"
				+ "</div>"
						+ "<br/>"
						+ "<i>Your name must be between 2 and 32 characters long. You cannot use the square bracket characters or full stops. (Surname may be left blank.)</i>"
						+ (unsuitableName ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Invalid name.</b></p>" : "")
						+ (unsuitableSurname ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Invalid Surname.</b></p>" : "")
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>"
					+ "<p id='hiddenFieldSurname' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getMoney() < 100) {
					return new Response("Confirm ("+UtilText.formatAsMoneyUncoloured(100, "span")+")",
							"Have your name changed.<br/>[style.italicsBad(You cannot afford this!)]",
							null);
					
				} else {
					return new ResponseEffectsOnly("Confirm ("+UtilText.formatAsMoney(100, "span")+")", "Have your name changed.<br/>This will cost "+UtilText.formatAsMoney(100)+"."){
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							applyNameChange(false);
						}
					};
				}
				
			} else if(index==2) {
				if(Main.game.getPlayer().getAllCharactersOfRelationType(Relationship.Parent).isEmpty()) {
					return new Response("Offspring ("+UtilText.formatAsMoneyUncoloured(5000, "span")+")",
							"Change your name, and also have all lines of your offspring update their surnames to your surname.<br/>[style.italicsBad(You do not have any children, so you can't do this!)]", null);
					
				} else if (Main.game.getPlayer().getMoney() < 5000) {
					return new Response("Offspring ("+UtilText.formatAsMoneyUncoloured(5000, "span")+")",
							"Change your name, and also have all lines of your offspring update their surnames to your surname.<br/>[style.italicsBad(You cannot afford this!)]", null);
					
				} else {
					return new ResponseEffectsOnly("Offspring ("+UtilText.formatAsMoney(5000, "span")+")",
							"Change your name, and also have all lines of your offspring update their surnames to your surname.<br/>This will cost "+UtilText.formatAsMoney(5000)+"."){
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							applyNameChange(true);
						}
					};
				}
				
			} else if (index == 0) {
				return new Response("Back", "Decide not to change your name.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "NAME_CHANGE_BACK"));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	private static void applyNameChange(boolean applyOffspringSurnames) {
		List<String> fieldsList = Util.newArrayListOfValues("nameMasculineInput", "nameAndrogynousInput", "nameFeminineInput");
		List<String> namesList = new ArrayList<>();
		for(String s : fieldsList) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('"+s+"').value;");
			if(Main.mainController.getWebEngine().getDocument()!=null) {
				if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
						|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32
						|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+")) {
					unsuitableName = true;
				} else {
					unsuitableName = false;
					namesList.add(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
				}
			}
		}
		Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
		if(Main.mainController.getWebEngine().getDocument()!=null) {
			if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
					&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 32
							|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+"))) {
				unsuitableSurname = true;
			} else {
				unsuitableSurname = false;
			}
		}
		
		if(applyOffspringSurnames && Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()<1) {
			unsuitableSurname = true;
		}
		
		if (unsuitableName || unsuitableSurname)  {
			Main.game.setContent(new Response("" ,"", NAME_CHANGE));
			
		} else {
			Main.game.getPlayer().setName(new NameTriplet(namesList.get(0), namesList.get(1), namesList.get(2)));
			Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());
			
			if(applyOffspringSurnames && Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1) {
				for(NPC npc : Main.game.getAllNPCs()) {
					GameCharacter mother = npc.getMother();
					if(mother!=null) {
						while(mother.getMother()!=null) {
							mother = mother.getMother();
						}
						if(mother.isPlayer()) {
							npc.setSurname(Main.game.getPlayer().getSurname());
						}
					}
				}
			}
			if(applyOffspringSurnames) {
				Main.game.getTextEndStringBuilder().append(
						"<p style='text-align:center;'>"
							+ "You fill out the forms and hand over the required fee, officially changing your name to:<br/>"
							+ "<b>[pc.Name]"+(!Main.game.getPlayer().getSurname().isEmpty()?" [pc.Surname]":"")+"</b><br/>"
							+ "All of your offspring, all the way down your family tree, have had their surnames legally changed to:<br/>"
							+ "<b>[pc.Surname]</b><br/>"
						+ "</p>"
						+Main.game.getPlayer().incrementMoney(-5000));
			} else {
				Main.game.getTextEndStringBuilder().append(
						"<p style='text-align:center;'>"
							+ "You fill out the forms and hand over the required fee, officially changing your name to:<br/>"
							+ "<b>[pc.Name]"+(!Main.game.getPlayer().getSurname().isEmpty()?" [pc.Surname]":"")+"</b>"
						+ "</p>"
						+Main.game.getPlayer().incrementMoney(-100));
			}
			Main.game.setContent(new Response("" ,"", NAME_CHANGE));
		}
	}
}
