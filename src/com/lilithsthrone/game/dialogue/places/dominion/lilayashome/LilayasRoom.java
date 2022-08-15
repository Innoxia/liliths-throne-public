package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.managers.universal.SMMasturbation;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.5
 * @version 0.4.4.1
 * @author Innoxia
 */
public class LilayasRoom {
	
	public static AbstractClothing lilayasPanties;
	
	public static final DialogueNode ROOM_LILAYA = new DialogueNode("Lilaya's Room", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "EXTERIOR");
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
					return new Response("Lilaya's Room", "The door is firmly shut and locked at the moment...", null);
				}
				return new Response("Lilaya's Room", "Have a look around Lilaya's room.", ROOM_LILAYA_INSIDE);

			}  else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_LILAYA_INSIDE = new DialogueNode("Lilaya's Room", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "INTERIOR_ENTRY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "Step back out into the corridor.", ROOM_LILAYA);

			} else if (index == 1) {
				return new Response("Panties", "Look through Lilaya's pile of panties.", PANTIES,
						Util.newArrayListOfValues(Fetish.FETISH_INCEST), CorruptionLevel.TWO_HORNY,
						null, null, null) {
					@Override
					public void effects() {
						List<AbstractClothingType> panties = new ArrayList<>();
						panties.add(ClothingType.getClothingTypeFromId("innoxia_groin_lacy_panties"));
						panties.add(ClothingType.getClothingTypeFromId("innoxia_groin_panties"));
						panties.add(ClothingType.getClothingTypeFromId("innoxia_groin_shimapan"));
						panties.add(ClothingType.getClothingTypeFromId("innoxia_groin_crotchless_panties"));
						
						lilayasPanties = Main.game.getItemGen().generateClothing(panties.get(Util.random.nextInt(panties.size())), false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PANTIES = new DialogueNode("Lilaya's Room", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "PANTIES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 0) {
				return new Response("Leave", "Step back out into the corridor.", ROOM_LILAYA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "PANTIES_EXIT"));
					}
				};

			} else if (index == 1) {
				return new ResponseSex("Panty Masturbation", "Use Lilaya's panties to help you masturbate.",
						true, true,
						new SMMasturbation(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMasturbation.KNEELING_PANTIES))) {
							@Override
							public String applyEndSexEffects() {
								return Main.game.getPlayer().addClothing(LilayasRoom.lilayasPanties, 1, false, true);
							}
						},
						null,
						null, PANTIES_POST_MASTURBATION, UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "PANTIES_MASTURBATION"));

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PANTIES_POST_MASTURBATION = new DialogueNode("Finished", "As you stop masturbating, you wonder what you should do with Lilaya's panties next...", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "PANTIES_POST_MASTURBATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Hide", "Quickly hide underneath Lilaya's bed, taking the panties along with you.", HIDE);

			} else if (index == 2) {
				return new Response("Flee", "Quickly make your exit, taking the panties along with you.", FLEE);

			} else if (index == 3) {
				return new Response("Enjoy Panties", "Surely Rose isn't going to come in here... You think it'd be safe to just continue enjoying Lilaya's panties.", CAUGHT) {
					@Override
					public void effects() {
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), -25));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HIDE = new DialogueNode("Lilaya's Room", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "HIDE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Now that Rose has left, you can safely step back out into the corridor.", ROOM_LILAYA);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode FLEE = new DialogueNode("Lilaya's Room", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "FLEE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Your room", "Continue on down the corridor to your room.") {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CAUGHT = new DialogueNode("Lilaya's Room", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "CAUGHT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Apologise", "Say sorry to Rose.", APOLOGY) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
					
			} else if(index==2) {
				return new Response("Threaten", "Threaten Rose and tell her that she'll be sorry if she tells Lilaya about this.", THREATEN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), -25));
					}
				};

			} else if (index == 3) {
				return new ResponseEffectsOnly("Leave", "Make some quick excuses and rush past Rose back to your room.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "CAUGHT_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode APOLOGY = new DialogueNode("Lilaya's Room", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "APOLOGY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Beg", "Beg Rose not to tell Lilaya.", BEG) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), 15));
					}
				};
					
			} else if (index == 2) {
				return new ResponseEffectsOnly("Leave", "Tell Rose not to let Lilaya know, and rush off back to your room.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "APOLOGY_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BEG = new DialogueNode("Lilaya's Room", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "BEG");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Submit", "Let Rose push you back on to the bed and fuck you.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
						null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Rose.class), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public  boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return !character.isPlayer();
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								return Util.newHashMapOfValues(
										new Value<>(
												Main.game.getNpc(Rose.class),
												Util.newArrayListOfValues(
														SexAreaOrifice.VAGINA,
														SexAreaOrifice.ANUS,
														SexAreaOrifice.MOUTH)));
							}
						},
						null,
						null,
						AFTER_ROSE_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "ROSE_AS_DOM")){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), 15));
						if(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN)!=null) {
							Main.game.getNpc(Rose.class).unequipClothingIntoVoid(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN), true, Main.game.getNpc(Rose.class));
						}
						Main.game.getNpc(Rose.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						Main.game.getNpc(Rose.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_penis_strapon", PresetColour.CLOTHING_PURPLE_DARK, false), true, Main.game.getNpc(Rose.class));
					}
				};
					
			} else if (index == 2) {
				return new ResponseEffectsOnly("Refuse", "Refuse Rose's offer and rush off back to your room.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "BEG_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_ROSE_AS_DOM = new DialogueNode("Finished", "Feeling as though she's 'punished' you enough, Rose brings an end to the sex.", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "AFTER_ROSE_AS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Leave", "Hurry back to your room.") {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(Rose.class).equipClothing();
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode THREATEN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Wait", "Do as Rose says and wait while Lilaya comes up to her room to confront you.", THREATEN_WAIT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, false);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Flee", "Not wanting to be confronted by Lilaya, you rush off back to your room.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode THREATEN_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Submit", "Doing as Lilaya says, you decide to submit and apologise to Rose, letting her decide how best to punish you...", THREATEN_SUBMIT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_SEX;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, false);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), 25));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
					
			}
			//TODO needs more options to allow player to fuck Rose?
//			else if (index == 2) {
//				return new Response("Dominate",
//						"Without apologising, dominantly tell Lilaya and Rose that you did what you did because you were feeling particularly horny, and that now that they're here, they should help you to get some relief.",
//						DOMINATE,
//						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
//						null, null, null, null) {
//					@Override
//					public Colour getHighlightColour() {
//						return PresetColour.GENERIC_SEX_AS_DOM;
//					}
//				};
//				
//			}
			else if (index == 2) {
				return new ResponseEffectsOnly("Apologise", "Not wanting to risk Lilaya's wrath, you quickly apologise and hurry back to your room.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, false);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), 15));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN_APOLOGISE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			}
			return null;
		}
	};
	
	public static final DialogueNode THREATEN_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Lilaya's pussy", "Tell Rose that you like the taste of Lilaya's pussy.",
						true, false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Rose.class), SexSlotAllFours.BEHIND),
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return partner.equals(Main.game.getNpc(Rose.class)) && Main.sex.isSatisfiedFromOrgasms(Main.game.getNpc(Rose.class), true) && Main.sex.isSatisfiedFromOrgasms(Main.game.getNpc(Lilaya.class), true);
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(targetedCharacter.isPlayer()) {
									return getMainSexPreference(character, targetedCharacter);
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.equals(Main.game.getNpc(Rose.class)) && targetedCharacter.isPlayer()) {
									if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
									} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
								}
								if(character.equals(Main.game.getNpc(Lilaya.class)) && targetedCharacter.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public  boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return !character.isPlayer();
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								return Util.newHashMapOfValues(
										new Value<>(
												Main.game.getNpc(Rose.class),
												Util.newArrayListOfValues(
														SexAreaOrifice.VAGINA,
														SexAreaOrifice.ANUS,
														SexAreaOrifice.MOUTH)));
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.equals(Main.game.getNpc(Lilaya.class));
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> exposeMap = new HashMap<>();
								
								if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
									exposeMap.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
								}
								if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
									exposeMap.putIfAbsent(Main.game.getPlayer(), new ArrayList<>());
									exposeMap.get(Main.game.getPlayer()).add(CoverableArea.VAGINA);
								} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
									exposeMap.putIfAbsent(Main.game.getPlayer(), new ArrayList<>());
									exposeMap.get(Main.game.getPlayer()).add(CoverableArea.ANUS);
								}
								
								return exposeMap;
							}
						},
						null,
						null,
						AFTER_LILAYA_AND_ROSE_AS_DOMS,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "LILAYA_AND_ROSE_AS_DOMS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
									?new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true)
									:null,
								Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
									?new InitialSexActionInformation(Main.game.getNpc(Rose.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true)
									:(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
										?new InitialSexActionInformation(Main.game.getNpc(Rose.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true)
										:null));
					}
					@Override
					public void effects() {
						if(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN)!=null) {
							Main.game.getNpc(Rose.class).unequipClothingIntoVoid(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN), true, Main.game.getNpc(Rose.class));
						}
						Main.game.getNpc(Rose.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						Main.game.getNpc(Rose.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_penis_strapon", PresetColour.CLOTHING_PURPLE_DARK, false), true, Main.game.getNpc(Rose.class));
					}
				};
					
			} else if(index==2) {
				return new ResponseSex("Lilaya's cock", "Tell Rose that you want Lilaya to grow a cock for you to suck.",
						true, false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Rose.class), SexSlotAllFours.BEHIND),
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return partner.equals(Main.game.getNpc(Rose.class)) && Main.sex.isSatisfiedFromOrgasms(Main.game.getNpc(Rose.class), true) && Main.sex.isSatisfiedFromOrgasms(Main.game.getNpc(Lilaya.class), true);
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(targetedCharacter.isPlayer()) {
									return getMainSexPreference(character, targetedCharacter);
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.equals(Main.game.getNpc(Rose.class)) && targetedCharacter.isPlayer()) {
									if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
									} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
								}
								if(character.equals(Main.game.getNpc(Lilaya.class)) && targetedCharacter.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public  boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return !character.isPlayer();
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								return Util.newHashMapOfValues(
										new Value<>(
												Main.game.getNpc(Rose.class),
												Util.newArrayListOfValues(
														SexAreaOrifice.VAGINA,
														SexAreaOrifice.ANUS,
														SexAreaOrifice.MOUTH)));
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.equals(Main.game.getNpc(Lilaya.class));
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> exposeMap = new HashMap<>();
								
								if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
									exposeMap.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
								}
								if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
									exposeMap.putIfAbsent(Main.game.getPlayer(), new ArrayList<>());
									exposeMap.get(Main.game.getPlayer()).add(CoverableArea.VAGINA);
								} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
									exposeMap.putIfAbsent(Main.game.getPlayer(), new ArrayList<>());
									exposeMap.get(Main.game.getPlayer()).add(CoverableArea.ANUS);
								}
								
								return exposeMap;
							}
						},
						null,
						null,
						AFTER_LILAYA_AND_ROSE_AS_DOMS,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "LILAYA_AND_ROSE_AS_DOMS_PENIS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
									?new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), PenisMouth.GIVING_BLOWJOB_START, false, true)
									:null,
								Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
									?new InitialSexActionInformation(Main.game.getNpc(Rose.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true)
									:(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
										?new InitialSexActionInformation(Main.game.getNpc(Rose.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true)
										:null));
					}
					@Override
					public void effects() {
						Main.game.getNpc(Lilaya.class).setVaginaType(VaginaType.NONE);
						((Lilaya)Main.game.getNpc(Lilaya.class)).growCock();
						
						if(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN)!=null) {
							Main.game.getNpc(Rose.class).unequipClothingIntoVoid(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN), true, Main.game.getNpc(Rose.class));
						}
						Main.game.getNpc(Rose.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						Main.game.getNpc(Rose.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_penis_strapon", PresetColour.CLOTHING_PURPLE_DARK, false), true, Main.game.getNpc(Rose.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_LILAYA_AND_ROSE_AS_DOMS = new DialogueNode("Finished", "Feeling as though she's 'punished' you enough, Rose brings an end to the sex.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "AFTER_LILAYA_AND_ROSE_AS_DOMS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Leave", "Hurry back to your room.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "AFTER_LILAYA_AND_ROSE_AS_DOMS_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DOMINATE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "DOMINATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Fuck Lilaya", "Have dominant sex with Rose and Lilaya.",
						true, false,
						new SMAllFours(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lilaya.class), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						Util.newArrayListOfValues(Main.game.getNpc(Rose.class)),
						null,
						AFTER_LILAYA_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "LILAYA_AS_SUB_START"));
					
			} else if (index == 2) {
				return new ResponseEffectsOnly("Leave", "Turn down Rose's offer and head back to your room.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "DOMINATE_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_LILAYA_AS_SUB = new DialogueNode("Finished", "Having had your fun with Lilaya, you bring an end to the sex.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "AFTER_ROSE_AND_LILAYA_AS_SUBS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Leave", "Head back to your room.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "AFTER_ROSE_AND_LILAYA_AS_SUBS_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};
			}
			return null;
		}
	};
}
