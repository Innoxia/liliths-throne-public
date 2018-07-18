package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.SMPantyMasturbation;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMMissionary;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.5
 * @version 0.2.5
 * @author Innoxia
 */
public class LilayasRoom {
	
	public static AbstractClothing lilayasPanties;
	
	public static final DialogueNodeOld ROOM_LILAYA = new DialogueNodeOld("Lilaya's Room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "EXTERIOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Lilaya's Room", "Have a look around Lilaya's room.", ROOM_LILAYA_INSIDE);

			}  else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ROOM_LILAYA_INSIDE = new DialogueNodeOld("Lilaya's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
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
						panties.add(ClothingType.GROIN_LACY_PANTIES);
						panties.add(ClothingType.GROIN_PANTIES);
						panties.add(ClothingType.GROIN_SHIMAPAN);
						panties.add(ClothingType.GROIN_CROTCHLESS_PANTIES);
						
						lilayasPanties = AbstractClothingType.generateClothing(panties.get(Util.random.nextInt(panties.size())), false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld PANTIES = new DialogueNodeOld("Lilaya's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
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
				return new ResponseSex("Panty Masturbation", "Use Lilaya's panties to help you masturbate",
						true, true,
						new SMPantyMasturbation(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MASTURBATING_KNEELING))),
						PANTIES_POST_MASTURBATION,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "PANTIES_MASTURBATION"));

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld PANTIES_POST_MASTURBATION = new DialogueNodeOld("Lilaya's Room", "As you stop masturbating, you wonder what you should do with Lilaya's panties next...", true) {
		private static final long serialVersionUID = 1L;

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
						Main.game.getTextEndStringBuilder().append(Main.game.getRose().incrementAffection(Main.game.getPlayer(), -25));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld HIDE = new DialogueNodeOld("Lilaya's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
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
	
	public static final DialogueNodeOld FLEE = new DialogueNodeOld("Lilaya's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
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
						Main.game.setContent(new Response("", "", Main.game.getPlayer().getLocationPlace().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CAUGHT = new DialogueNodeOld("Lilaya's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
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
						Main.game.getTextEndStringBuilder().append(Main.game.getRose().incrementAffection(Main.game.getPlayer(), 10));
					}
				};
					
			} else if(index==2) {
				return new Response("Threaten", "Threaten Rose not to tell Lilaya.", THREATEN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getRose().incrementAffection(Main.game.getPlayer(), -25));
					}
				};

			} else if (index == 3) {
				return new ResponseEffectsOnly("Leave", "Make some quick excuses and rush past Rose back to your room.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "CAUGHT_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getPlayer().getLocationPlace().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld APOLOGY = new DialogueNodeOld("Lilaya's Room", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
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
						Main.game.getTextEndStringBuilder().append(Main.game.getRose().incrementAffection(Main.game.getPlayer(), 15));
					}
				};
					
			} else if (index == 2) {
				return new ResponseEffectsOnly("Leave", "Tell Rose not to let Lilaya know, and rush off back to your room.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "APOLOGY_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getPlayer().getLocationPlace().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BEG = new DialogueNodeOld("Lilaya's Room", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
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
						new SMMissionary(
								Util.newHashMapOfValues(new Value<>(Main.game.getRose(), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_ON_BACK))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						AFTER_ROSE_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "ROSE_AS_DOM")){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getRose().incrementAffection(Main.game.getPlayer(), 15));
						Main.game.getRose().unequipClothingIntoVoid(Main.game.getRose().getClothingInSlot(InventorySlot.GROIN), true, Main.game.getRose());
						Main.game.getRose().displaceClothingForAccess(CoverableArea.PENIS);
						Main.game.getRose().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_PENIS_STRAPON, Colour.CLOTHING_PURPLE_DARK, false), true, Main.game.getRose());
					}
				};
					
			} else if (index == 2) {
				return new ResponseEffectsOnly("Refuse", "Refuse Rose's offer and rush off back to your room.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "BEG_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getPlayer().getLocationPlace().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_ROSE_AS_DOM = new DialogueNodeOld("Lilaya's Room", ".", true) {
		private static final long serialVersionUID = 1L;

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
						Main.game.setContent(new Response("", "", Main.game.getPlayer().getLocationPlace().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld THREATEN = new DialogueNodeOld("Lilaya's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Your room", "Continue on down the corridor to your room.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getPlayer().getLocationPlace().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
//			TODO Had to cut due to running out of time:
//			if(index==1) {
//				return new ResponseSex("Submit", "Let Rose and Lilaya fuck you as 'punishment'.",
//						true, false,
//						new SMDoggy(
//								Util.newHashMapOfValues(
//										new Value<>(Main.game.getRose(), SexPositionSlot.DOGGY_BEHIND),
//										new Value<>(Main.game.getLilaya(), SexPositionSlot.DOGGY_INFRONT)),
//								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))) {
//							@Override
//							public boolean isPositionChangingAllowed() {
//								return false;
//							}
//						},
//						AFTER_LILAYA_AND_ROSE_AS_DOMS,
//						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "LILAYA_AND_ROSE_AS_DOMS")) {
//					@Override
//					public void effects() {
//						Main.game.getRose().unequipClothingIntoVoid(Main.game.getRose().getClothingInSlot(InventorySlot.GROIN), true, Main.game.getRose());
//						Main.game.getRose().displaceClothingForAccess(CoverableArea.PENIS);
//						Main.game.getRose().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_PENIS_STRAPON, Colour.CLOTHING_PURPLE_DARK, false), true, Main.game.getRose());
//					}
//				};
//					
//			} else if (index == 2) {
//				return new Response("Explain", "Explain to Lilaya and Rose that you were just feeling particularly horny.", EXPLAIN);
//
//			} else {
//				return null;
//			}
		}
	};
	
	public static final DialogueNodeOld AFTER_LILAYA_AND_ROSE_AS_DOMS = new DialogueNodeOld("Lilaya's Room", ".", true) {
		private static final long serialVersionUID = 1L;

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
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getPlayer().getLocationPlace().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld EXPLAIN = new DialogueNodeOld("Lilaya's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "EXPLAIN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Sex", "Have dominant sex with Rose and Lilaya.",
						true, false,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getLilaya(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
										new Value<>(Main.game.getRose(), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						AFTER_ROSE_AND_LILAYA_AS_SUBS,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "ROSE_AND_LILAYA_AS_SUBS"));
					
			} else if (index == 2) {
				return new ResponseEffectsOnly("Leave", "Turn down their offer and head back to your room.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "EXPLAIN_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getPlayer().getLocationPlace().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_ROSE_AND_LILAYA_AS_SUBS = new DialogueNodeOld("Lilaya's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 20;
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
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getPlayer().getLocationPlace().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
}
