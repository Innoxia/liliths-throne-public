package com.lilithsthrone.game.dialogue.places.submission;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMMissionary;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class SlimeQueensLair {
	
	private static void resetTower() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsDefeated, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeated, false);
	}
	
	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("Entrance Hall", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ENTRANCE"));
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsIntroduced)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[style.italicsBad(You realise that it's not going to be possible to escape if you find yourself in a fight in here!)]"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Push open the tower's front door and step back outside.", BatCaverns.SLIME_LAKE_ISLAND){
					@Override
					public void effects() {
						resetTower();
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "LEAVE_TOWER"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld GUARD_POST = new DialogueNodeOld("Guard Post", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsBluffed);
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_AREA"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_DEFEATED"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_PACIFIED"));
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsIntroduced)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsBluffed)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_REPEAT_BLUFFED"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_REPEAT"));
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsBluffed)) {
				if(index==1) {
					return new Response("Talk", UtilText.parse(Main.game.getSlimeGuardFire(), Main.game.getSlimeGuardIce(), "Ask [npc1.name] and [npc2.name] about how they came to be guarding the Slime Queen."), GUARD_POST_TALK);
						
				} else if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE) && index==2){
					return new ResponseCombat("Fight",
							"Decide to drop the act and attack [slimeFire.name] and [slimeIce.name]!",
							Util.newArrayListOfValues(Main.game.getSlimeGuardFire(), Main.game.getSlimeGuardIce()),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(I can't believe you fell for my tale!)] You laugh, getting ready to fight the slime siblings. [pc.speech(I'll teach you not to be so gullible!)]"),
									new Value<>(Main.game.getSlimeGuardFire(), "[slimeFire.speech(Sis' and I are gonna have so much fun with you!)] [slimeFire.name] sneers, gripping his fire-enchanted sword in one hand."),
									new Value<>(Main.game.getSlimeGuardIce(), "[slimeIce.speech(You'll be sorry for tricking us!)] [slimeIce.name] calls out, stepping up beside her brother as she prepares to fight.")));
					
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)?index==2:index==3) {
					return new ResponseSex("Side-by-side",
							"Push [slimeFire.name] and [slimeIce.name] down onto all fours, side-by-side, and get ready to fuck them.",
							null, null, null, null, null, null,
							true, false,
							new SMDoggy(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getSlimeGuardIce(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
											new Value<>(Main.game.getSlimeGuardFire(), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							AFTER_SLIME_GUARD_SEX_AS_DOM,
							UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SIDE_BY_SIDE"));
				
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)?index==3:index==4) {
					return new ResponseSex("Get Spitroasted",
							"Let [slimeFire.name] and [slimeIce.name] spitroast you.",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
							true, true,
							new SMDoggy(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getSlimeGuardIce(), SexPositionSlot.DOGGY_INFRONT),
											new Value<>(Main.game.getSlimeGuardFire(), SexPositionSlot.DOGGY_BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							AFTER_SLIME_GUARD_SEX_AS_SUB,
							UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SPITROASTED"));
				} else {
					return null;
				}
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated)) {
				if(index==1) {
					return new ResponseSex("Side-by-side",
							"Push [slimeFire.name] and [slimeIce.name] down onto all fours, side-by-side, and get ready to fuck them.",
							null, null, null, null, null, null,
							true, false,
							new SMDoggy(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getSlimeGuardIce(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
											new Value<>(Main.game.getSlimeGuardFire(), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							AFTER_SLIME_GUARD_SEX_AS_DOM,
							UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SIDE_BY_SIDE"));
				
				} else if(index==2) {
					return new ResponseSex("Get Spitroasted",
							"Let [slimeFire.name] and [slimeIce.name] spitroast you.",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
							true, true,
							new SMDoggy(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getSlimeGuardIce(), SexPositionSlot.DOGGY_INFRONT),
											new Value<>(Main.game.getSlimeGuardFire(), SexPositionSlot.DOGGY_BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							AFTER_SLIME_GUARD_SEX_AS_SUB,
							UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SPITROASTED"));
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new ResponseCombat("Fight",
							"Fight your way past these slimes!",
							Util.newArrayListOfValues(Main.game.getSlimeGuardFire(), Main.game.getSlimeGuardIce()),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(If it's a fight you want, it's a fight you're going to get!)] you cry out, ready to fight the slime siblings."),
									new Value<>(Main.game.getSlimeGuardFire(), "[slimeFire.speech(Sis' and I are gonna have so much fun with you!)] [slimeFire.name] sneers, gripping his fire-enchanted sword in one hand."),
									new Value<>(Main.game.getSlimeGuardIce(), "[slimeIce.speech(You'll be sorry!)] [slimeIce.name] calls out, stepping up beside her brother as she prepares to fight."))){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
						}
					};
					
				} else if(index==2) {
					if(Main.game.getSlimeGuardFire().getFoughtPlayerCount()>0) {
						return new Response("Slime Bluff", "It's a little too late to be trying to bluff your way past these two, what with having already fought them...", null);
						
					} else if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
						return new Response("Slime Bluff", "Pretend to be one of the Slime Queen's new subjects.", GUARD_POST_SLIME_BLUFF) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsBluffed, true);
							}
						};
						
					} else {
						return new Response("Slime Bluff", "You'd need to be a slime in order to do this!", null);
					}
					
				} else if(index==3) {
					if(Main.game.getSlimeGuardFire().getFoughtPlayerCount()>0) {
						return new Response("Servant Bluff", "It's a little too late to be trying to bluff your way past these two, what with having already fought them...", null);
						
					} else if(Main.game.getPlayer().getHistory()==History.BUTLER) {
						return new Response("Butler Bluff", "Use the knowledge that you've gained by working as a butler to bluff your way past these slimes.", GUARD_POST_BUTLER_BLUFF) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsBluffed, true);
							}
						};
						
					} else if(Main.game.getPlayer().getHistory()==History.MAID) {
						return new Response("Maid Bluff", "Use the knowledge that you've gained by working as a maid to bluff your way past these slimes.", GUARD_POST_MAID_BLUFF) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsBluffed, true);
							}
						};
						
					} else {
						return new Response("Servant Bluff", "You don't have the knowledge required to pretend to be a servant! (Requires 'Maid' or 'Butler' history.)", null);
					}
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld GUARD_POST_SLIME_BLUFF = new DialogueNodeOld("Guard Post", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SLIME_BLUFF")
					+UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BLUFF_SUCCESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld GUARD_POST_BUTLER_BLUFF = new DialogueNodeOld("Guard Post", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_BUTLER_BLUFF")
					+UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BLUFF_SUCCESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld GUARD_POST_MAID_BLUFF = new DialogueNodeOld("Guard Post", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_MAID_BLUFF")
					+UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BLUFF_SUCCESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld GUARD_POST_TALK = new DialogueNodeOld("Guard Post", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_SLIME_GUARD_SEX_AS_DOM = new DialogueNodeOld("Finished", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_AS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_SLIME_GUARD_SEX_AS_SUB = new DialogueNodeOld("Finished", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_AS_SUB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLIME_GUARDS_COMBAT_PLAYER_VICTORY = new DialogueNodeOld("Victory", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_GUARDS_COMBAT_PLAYER_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLIME_GUARDS_COMBAT_PLAYER_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_GUARDS_COMBAT_PLAYER_DEFEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Spitroasted",
						"[slimeFire.name] and [slimeIce.name] move to have some fun with you...",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						false, false,
						new SMDoggy(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSlimeGuardIce(), SexPositionSlot.DOGGY_INFRONT),
										new Value<>(Main.game.getSlimeGuardFire(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						AFTER_SLIME_GUARD_SEX_DEFEATED,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SPITROASTED_UPON_DEFEAT"));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SLIME_GUARD_SEX_DEFEATED = new DialogueNodeOld("Finished", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_DEFEATED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Kicked Out", "[slimeFire.name] kicks you out of the front door.", BatCaverns.SLIME_LAKE_ISLAND){
					@Override
					public void effects() {
						resetTower();
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_DEFEATED_KICKED_OUT"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	
	
	public static final DialogueNodeOld STAIRCASE_UP = new DialogueNodeOld("Spiral Staircase", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "STAIRCASE_UP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Upstairs", "Travel up the spiral staircase to the first floor."){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLIME_QUEENS_LAIR_FIRST_FLOOR, PlaceType.SLIME_QUEENS_LAIR_STAIRS_DOWN);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld STAIRCASE_DOWN = new DialogueNodeOld("Spiral Staircase", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "STAIRCASE_DOWN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Downstairs", "Travel down the spiral staircase to the ground floor."){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLIME_QUEENS_LAIR_GROUND_FLOOR, PlaceType.SLIME_QUEENS_LAIR_STAIRS_UP);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CORRIDOR = new DialogueNodeOld("Corridor", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld ROOM = new DialogueNodeOld("Bedroom", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld STORAGE_VATS = new DialogueNodeOld("Distillery", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "STORAGE_VATS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld ROYAL_GUARD_POST = new DialogueNodeOld("Royal Guard Post", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated);
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_DEFEATED");
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_PACIFIED");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated)) {
				if(index==1) {
					return new ResponseSex("Sex",
							UtilText.parse(Main.game.getSlimeRoyalGuard(), "Pull [slimeRoyalGuard.name] to [slimeRoyalGuard.his] feet and get ready to fuck [slimeRoyalGuard.him]."),
							null, null, null, null, null, null,
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getSlimeRoyalGuard(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM,
							UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_DOM"));
				
				} else if(index==2) {
					return new ResponseSex("Submit",
							UtilText.parse(Main.game.getSlimeRoyalGuard(), "Let [slimeRoyalGuard.name] fuck you."),
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getSlimeRoyalGuard(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB,
							UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_SUB"));
				} else {
					return null;
				}
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				if(index==1) {
					return new Response("Talk", "Ask [slimeRoyalGuard.name] how [slimeRoyalGuard.he] ended up guarding the Slime Queen.", ROYAL_GUARD_POST_TALK);
					
				} else if(index==2) {
					return new ResponseCombat("Spar",
							"Accept [slimeRoyalGuard.namePos] offer of a sparring match, with the agreement that the winner can do whatever they like with the loser's body.",
							Util.newArrayListOfValues(Main.game.getSlimeRoyalGuard()),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(Fine, I'll spar with you,)] you say, readying yourself for a fight, [pc.speech(but remember what you said about your body being mine when you lose!)]"),
									new Value<>(Main.game.getSlimeRoyalGuard(), "[slimeRoyalGuard.speech(Hah!)] [slimeRoyalGuard.name] booms. [slimeRoyalGuard.speech(I'm looking forwards to claiming my prize!)]")));
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new ResponseCombat("Fight",
							"Defend yourself against [slimeRoyalGuard.name].",
							Util.newArrayListOfValues(Main.game.getSlimeRoyalGuard()),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(I've fought and defeated mightier foes than you!)] you cry out, readying yourself for a fight."),
									new Value<>(Main.game.getSlimeRoyalGuard(), "[slimeRoyalGuard.speech(No-one is mightier than I!)] [slimeRoyalGuard.name] bellows in response. [slimeRoyalGuard.speech(Prepare to be defeated!)]")));
					
				} else if(index==2) {
					if(Main.game.getSlimeRoyalGuard().getFoughtPlayerCount()>0) {
						return new Response("Compliment", "Having already fought [slimeRoyalGuard.name] once before, he's now far too alert to fall for any of your tricks...", null);
						
					} else {
						return new Response("Compliment", "Compliment [slimeRoyalGuard.namePos] fancy footwork in an attempt to trick [slimeRoyalGuard.him] into making a mistake.", ROYAL_GUARD_POST_ADMIRE) {
							@Override
							public void effects() {
								Main.game.getSlimeRoyalGuard().setHealthPercentage(0.8f);
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld ROYAL_GUARD_POST_TALK = new DialogueNodeOld("Royal Guard Post", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ROYAL_GUARD_POST_ADMIRE = new DialogueNodeOld("Royal Guard Post", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight",
						"Defend yourself against [slimeRoyalGuard.name].",
						Util.newArrayListOfValues(Main.game.getSlimeRoyalGuard()),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(Very well, I'll fight you,)] you say, stepping back and preparing for combat."),
								new Value<>(Main.game.getSlimeRoyalGuard(), "[slimeRoyalGuard.speech(Excellent! Now, defend yourself!)] [slimeRoyalGuard.name] booms, before moving forwards to attack.")));
				
			} else if(index==2) {
				if(Main.game.getPlayer().hasTraitActivated(Perk.BRAWLER) || Main.game.getPlayer().getHistory()==History.SOLDIER) {
					return new Response("Instruct", "Tell [slimeRoyalGuard.name] what [slimeRoyalGuard.he]'s doing wrong in an attempt to fluster [slimeRoyalGuard.him] into wearing [slimeRoyalGuard.himself] out.", ROYAL_GUARD_POST_ADMIRE_INSTRUCT) {
						@Override
						public void effects() {
							Main.game.getSlimeRoyalGuard().setHealthPercentage(0.4f);
						}
					};
					
				} else {
					return new Response("Instruct", "You don't know enough about fighting techniques to offer [slimeRoyalGuard.name] any advice. (Requires 'Brawler' trait to be active, or for you to have the 'Soldier' history.)", null);
				}
				
			} else if(index==3) {
				if(Main.game.getPlayer().hasTraitActivated(Perk.NYMPHOMANIAC) || Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST)>=50) {
					return new Response("Seduce", "Seduce [slimeRoyalGuard.name] and encourage [slimeRoyalGuard.him] to show off to you in an attempt to get [slimeRoyalGuard.him] to wear [slimeRoyalGuard.himself] out.", ROYAL_GUARD_POST_ADMIRE_SEDUCE) {
						@Override
						public void effects() {
							Main.game.getSlimeRoyalGuard().setLust(80);
						}
					};
					
				} else {
					return new Response("Seduce", "You aren't skilled enough in seduction to attempt this. (Requires 'Nymphomaniac' trait to be active, or for you to have over 50 lust damage.)", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ROYAL_GUARD_POST_ADMIRE_INSTRUCT = new DialogueNodeOld("Royal Guard Post", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE_INSTRUCT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight",
						"Now that you've worn [slimeRoyalGuard.name] out, [slimeRoyalGuard.he] should be easier to beat!",
						Util.newArrayListOfValues(Main.game.getSlimeRoyalGuard()),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(Sure, I'll take you on,)] you reply, reading yourself for a fight."),
								new Value<>(Main.game.getSlimeRoyalGuard(), "[slimeRoyalGuard.speech(Prepare... Prepare to... defend yourself!)] [slimeRoyalGuard.name] pants, struggling to catch his breath.")));
				
			} else if(index==2) {
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE) > Main.game.getSlimeRoyalGuard().getAttributeValue(Attribute.MAJOR_PHYSIQUE)) {
					return new Response("Overpower", "Now that you've worn [slimeRoyalGuard.name] out, it would be a simple matter to overpower [slimeRoyalGuard.him].", ROYAL_GUARD_POST_ADMIRE_INSTRUCT_OVERPOWER) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeated, true);
						}
					};
					
				} else {
					return new Response("Overpower", "Although you've worn [slimeRoyalGuard.name] out, you're not quite strong enough to overpower [slimeRoyalGuard.him]. (Requires your physique to be greater than [slimeRoyalGuard.his].)", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ROYAL_GUARD_POST_ADMIRE_INSTRUCT_OVERPOWER = new DialogueNodeOld("Royal Guard Post", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE_INSTRUCT_OVERPOWER");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	

	public static final DialogueNodeOld ROYAL_GUARD_POST_ADMIRE_SEDUCE = new DialogueNodeOld("Royal Guard Post", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE_SEDUCE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight",
						"Now that you've worn [slimeRoyalGuard.name] out, [slimeRoyalGuard.he] should be easier to beat!",
						Util.newArrayListOfValues(Main.game.getSlimeRoyalGuard()),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(Ok, I'll fight you...)] you sigh, preparing for combat."),
								new Value<>(Main.game.getSlimeRoyalGuard(), "[slimeRoyalGuard.speech(E-Excellent!)] [slimeRoyalGuard.name] replies, already flustered and turned on from your teasing.")));
				
			} else if(index==2) {
				return new ResponseSex("Submissive sex",
						UtilText.parse(Main.game.getSlimeRoyalGuard(), "Push [slimeRoyalGuard.name] over the edge and tire him out through sex."),
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSlimeRoyalGuard(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
							@Override
							public boolean isPlayerAbleToStopSex() {
								return false;
							}
						},
						AFTER_SLIME_ROYAL_GUARD_SEX_SEDUCTION,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_SEX_SEDUCTION")) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeated, true);
					}
				};
					
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM = new DialogueNodeOld("Finished", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB = new DialogueNodeOld("Finished", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_SLIME_ROYAL_GUARD_SEX_SEDUCTION = new DialogueNodeOld("Finished", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_SEDUCTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLIME_ROYAL_GUARD_COMBAT_PLAYER_VICTORY = new DialogueNodeOld("Victory", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Sex",
						"[slimeRoyalGuard.Name] moves to have some fun with you...",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSlimeRoyalGuard(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT_SEX"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SLIME_ROYAL_GUARD_SPARRING_PLAYER_VICTORY = new DialogueNodeOld("Victory", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_SPARRING_PLAYER_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Sex",
						UtilText.parse(Main.game.getSlimeRoyalGuard(), "Pull [slimeRoyalGuard.name] to [slimeRoyalGuard.his] feet and get ready to fuck [slimeRoyalGuard.him]."),
						null, null, null, null, null, null,
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSlimeRoyalGuard(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_DOM"));
			
			} else if(index==2) {
				return new ResponseSex("Submit",
						UtilText.parse(Main.game.getSlimeRoyalGuard(), "Let [slimeRoyalGuard.name] fuck you."),
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSlimeRoyalGuard(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_SUB"));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SLIME_ROYAL_GUARD_SPARRING_PLAYER_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_SPARRING_PLAYER_DEFEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Sex",
						"[slimeRoyalGuard.Name] moves to have some fun with you...",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSlimeRoyalGuard(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SLIME_ROYAL_GUARD_SEX_SPARRING_DEFEATED,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT_SEX"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED = new DialogueNodeOld("Finished", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thrown Out", "[slimeRoyalGuard.name] throws you out of the front door.", BatCaverns.SLIME_LAKE_ISLAND){
					@Override
					public void effects() {
						resetTower();
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED_KICKED_OUT"));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SLIME_ROYAL_GUARD_SEX_SPARRING_DEFEATED = new DialogueNodeOld("Finished", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_SPARRING_DEFEATED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld BED_CHAMBER = new DialogueNodeOld("Bed Chamber", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
//		@Override
//		public boolean isTravelDisabled() {
//			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE);
//		}
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BED_CHAMBER_PACIFIED");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BED_CHAMBER");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				return SLIME_QUEEN_CONVINCE.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("Convince", "Convince [slimeQueen.name] to stop encouraging other slimes to go around transforming people in Submission's tunnels.", SLIME_QUEEN_CONVINCE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeQueenConvinced, true);
							
							AbstractClothing crown = Main.game.getSlimeQueen().getClothingInSlot(InventorySlot.HEAD);
							if(crown!=null) {
								Main.game.getSlimeQueen().unequipClothingIntoVoid(crown, true, Main.game.getSlimeQueen());
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.HEAD_SLIME_QUEENS_TIARA, false), true));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_SIX_CONVINCE));
						}
					};
					
				} else if(index==2) {
					return new Response("Force", "If she really wants to be treated roughly, then that's what [slimeQueen.namePos] going to get. Push her down on her bed and force her to give up her plans.", SLIME_QUEEN_FORCE,
							Util.newArrayListOfValues(Fetish.FETISH_SADIST), Fetish.FETISH_SADIST.getAssociatedCorruptionLevel(), null, null, null) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeQueenForced, true);

							AbstractClothing crown = Main.game.getSlimeQueen().getClothingInSlot(InventorySlot.HEAD);
							if(crown!=null) {
								Main.game.getSlimeQueen().unequipClothingIntoVoid(crown, true, Main.game.getSlimeQueen());
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.HEAD_SLIME_QUEENS_TIARA, false), true));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_FORCE));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_SIX_FORCE));
						}
					};
					
				} else if(index==3) {
					return new Response("Help", "Instead of stopping her, offer Catherine your assistance instead. <b>This will greatly increase the number of slime encounters in Submission.</b>", SLIME_QUEEN_SUBMIT,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeQueenHelped, true);

							AbstractClothing crown = Main.game.getSlimeQueen().getClothingInSlot(InventorySlot.HEAD);
							if(crown!=null) {
								Main.game.getSlimeQueen().unequipClothingIntoVoid(crown, true, Main.game.getSlimeQueen());
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.HEAD_SLIME_QUEENS_TIARA, false), true));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_SUBMIT));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_SIX_SUBMIT));
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld SLIME_QUEEN_CONVINCE = new DialogueNodeOld("Bed Chamber", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_CONVINCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Ignore [slimeQueen.namePos] provocative moans and take your leave.", SLIME_QUEEN_LEAVE);
				
			} else if(index==2) {
				return new ResponseSex("'Rape'",
						UtilText.parse(Main.game.getSlimeQueen(), "Play along with [slimeQueen.namePos] fantasies and force yourself on her."),
						null, null, null, null, null, null,
						true, false,
						new SMMissionary(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSlimeQueen(), SexPositionSlot.MISSIONARY_ON_BACK))),
						AFTER_SLIME_QUEEN_SEX,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_SEX_START"));
				
			} else if(index==3) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					return new Response("Slime Bath", "Have a bath with [slimeQueen.Name].", SLIME_QUEEN_BATH);
					
				} else {
					return new Response("Slime Bath", "Have a bath with [slimeQueen.name]. <b>This will transform you into a slime!</b>", SLIME_QUEEN_BATH_TRANSFORM) {
						@Override
						public Colour getHighlightColour() {
							return Colour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setBodyMaterial(BodyMaterial.SLIME));
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH_TRANSFORM_END"));
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld SLIME_QUEEN_FORCE = new DialogueNodeOld("Bed Chamber", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_FORCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLIME_QUEEN_CONVINCE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNodeOld SLIME_QUEEN_SUBMIT = new DialogueNodeOld("Bed Chamber", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_SUBMIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLIME_QUEEN_CONVINCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLIME_QUEEN_BATH = new DialogueNodeOld("Bed Chamber", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Decline [slimeQueen.namePos] provocative moans and take your leave.", SLIME_QUEEN_LEAVE);
				
			} else if(index==2) {
				return new ResponseSex("'Rape'",
						UtilText.parse(Main.game.getSlimeQueen(), "Play along with [slimeQueen.namePos] fantasies and force yourself on her."),
						null, null, null, null, null, null,
						true, false,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getSlimeQueen(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						AFTER_SLIME_QUEEN_SEX,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH_SEX_START"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SLIME_QUEEN_BATH_TRANSFORM = new DialogueNodeOld("Bed Chamber", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH_TRANSFORM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLIME_QUEEN_BATH.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLIME_QUEEN_LEAVE = new DialogueNodeOld("Bed Chamber", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_LEAVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld SLIME_QUEEN_TALK = new DialogueNodeOld("Bed Chamber", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return BED_CHAMBER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_SLIME_QUEEN_SEX = new DialogueNodeOld("Bed Chamber", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Main.game.getSlimeQueen())>0) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_QUEEN_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_QUEEN_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return BED_CHAMBER.getResponse(responseTab, index);
		}
	};
}
