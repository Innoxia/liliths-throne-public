package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.5
 * @version 0.2.5
 * @author Innoxia
 */
public class TunnelSlimeDialogue {
	
	private static NPC slime() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNodeOld ATTACK = new DialogueNodeOld("Assaulted!", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_INTRO"));
			
			if(slime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME"));
					if(slime().isAttractedTo(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
					}
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER"));
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(slime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					if (index == 1) {
						return new ResponseCombat("Fight",
								"You aren't going to stand for [npc.name] transforming people into slimes. Teach [npc.herHim] a lesson!", Main.game.getActiveNPC(),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), "[pc.speech(You aren't going to be stalking these tunnels on my watch!)] You shout, launching into an attack!"),
										new Value<>(slime(), "[npc.speech(Traitor! You'll pay for this!)] [npc.Name] cries out as [npc.she] prepares to defend [npc.herself].")));
						
					} else if (index == 2) {
						return new Response("Leave", "Turn down [npc.name]'s offer of sex and take your leave.", ATTACK) {
							@Override
							public void effects(){
								if(slime().isAttractedTo(Main.game.getPlayer())) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_TURN_DOWN_SEX"));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_LEAVE"));
								}
							}
							@Override
							public DialogueNodeOld getNextDialogue(){
								return Main.game.getDefaultDialogueNoEncounter();
							}
						};
						
					} else if (index == 3) {
						if(!slime().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Dominant Sex", "[npc.Name] isn't attracted to you!", null);
							
						} else if(slime().hasFetish(Fetish.FETISH_DOMINANT) && !slime().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
							return new Response("Dominant Sex", "[npc.Name] isn't willing to let you be the dominant partner.", null);
							
						} else {
							return new ResponseSex("Dominant Sex", "Take the dominant role and have sex with [npc.name].",
									null, null, null,
									null, null, null,
									true, true,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(slime(), SexPositionSlot.STANDING_SUBMISSIVE))),
									AFTER_SLIME_SEX_AS_DOM,
									UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_DOM"));
						}
						
					} else if (index == 4) {
						if(!slime().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Submissive Sex", "[npc.Name] isn't attracted to you!", null);
							
						} else if(slime().hasFetish(Fetish.FETISH_SUBMISSIVE) && !slime().hasFetish(Fetish.FETISH_DOMINANT)) {
							return new Response("Submissive Sex", "[npc.Name] isn't willing to let you be the submissive partner.", null);
							
						} else {
							return new ResponseSex("Submissive Sex", "Take the submissive role and have sex with [npc.name].",
									null, null, null,
									null, null, null,
									true, true,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(slime(), SexPositionSlot.STANDING_SUBMISSIVE))),
									AFTER_SLIME_SEX_AS_SUB,
									UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_SUB"));
						}
						
					} else {
						return null;
					}
					
				} else {
					if (index == 1) {
						return new ResponseCombat("Fight",
								"Stand up for yourself and fight [npc.name]!", Main.game.getActiveNPC(),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), ""),
										new Value<>(slime(), "")));
						
					} else if (index == 2) {
						return new Response("Offer Money", "[npc.Name] isn't interested in your money! All [npc.she] wants to do is turn you into a slime!", null);
						
					} else if (index == 3) {
						return new Response("Submit", "Allow [npc.name] to transform you into a slime!", WILLINGLY_TRANSFORMED) {
							@Override
							public Colour getHighlightColour() {
								return Colour.TRANSFORMATION_GENERIC;
							}
							@Override
							public void effects() {
								Main.game.getPlayer().setBodyMaterial(BodyMaterial.SLIME);
							}
						};
						
					} else {
						return null;
					}
				}
				
			} else {
				if (index == 1) {
					return new ResponseCombat("Fight",
							"Stand up for yourself and fight [npc.name]!", Main.game.getActiveNPC(),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), ""),
									new Value<>(slime(), "")));
				
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<25) {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "You don't have enough money to offer to pay [npc.name] off. You'll have to either fight [npc.herHim] or offer [npc.herHim] your body!", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "Offer to pay [npc.name] 250 flames to leave you alone.", OFFER_MONEY) {
							@Override
							public void effects() {
								Main.game.getPlayer().incrementMoney(-250);
							}
						};
					}
					
				} else if (index == 3) {
					if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
								Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								AFTER_OFFER_BODY_SEX,
								UtilText.parseFromXMLFile("places/submission/tunnelSlime", "OFFER_BODY"));
					} else {
						return new Response("Offer body", "You can tell that [npc.name] isn't at all interested in having sex with you. You'll either have to offer [npc.herHim] some money, or prepare for a fight!", null);
					}
					
				} else {
					return null;
				}
				
			}
			
		}
	};
	
	public static final DialogueNodeOld ATTACK_REPEAT = new DialogueNodeOld("Assaulted!", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(slime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_REPEAT_TRANSFORMER_PLAYER_SLIME"));
					if(slime().isAttractedTo(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
					}
					return UtilText.nodeContentSB.toString();
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_REPEAT_TRANSFORMER");
				}
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ATTACK_PREGNANCY_REVEAL = new DialogueNodeOld("Assaulted!", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(slime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_PREGNANCY_REVEAL_TRANSFORMER_PLAYER_SLIME"));
					if(slime().isAttractedTo(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
					}
					return UtilText.nodeContentSB.toString();
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_PREGNANCY_REVEAL_TRANSFORMER");
				}
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_PREGNANCY_REVEAL");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ATTACK.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNodeOld AFTER_SLIME_SEX_AS_DOM = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM");
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SLIME_SEX_AS_DOM){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SLIME_SEX_AS_SUB = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "You've had enough fun with [npc.name] for now. Step back and put an end to this.";
		}

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_SUB");
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_SUB_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SLIME_SEX_AS_SUB){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld WILLINGLY_TRANSFORMED = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "WILLINGLY_TRANSFORMED"));
			if(slime().isAttractedTo(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld FORCIBLY_TRANSFORMED = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "FORCIBLY_TRANSFORMED"));
			if(slime().isAttractedTo(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "FORCIBLY_TRANSFORMED_OFFER_SEX"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "FORCIBLY_TRANSFORMED_NO_SEX"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_COMBAT_PLAYER_DEFEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld RESIST_FORCIBLY_TRANSFORMED = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_FORCIBLY_TRANSFORMED"));
			if(slime().isAttractedTo(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_FORCIBLY_TRANSFORMED_OFFER_SEX"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_FORCIBLY_TRANSFORMED_NO_SEX"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_COMBAT_PLAYER_DEFEAT.getResponse(responseTab, index);
		}
	};
	

	public static final DialogueNodeOld OFFER_MONEY = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "OFFER_MONEY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", OFFER_MONEY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_OFFER_BODY_SEX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_OFFER_BODY_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_OFFER_BODY_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", OFFER_MONEY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	// Standard combat:
	

	public static final DialogueNodeOld AFTER_COMBAT_PLAYER_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_ENEMY_WANTS_SEX");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", null){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Have some fun",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_VICTORY);
					
				} else if (index == 3) {
					return new ResponseSex("Have some gentle fun",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}
							},
							AFTER_SEX_VICTORY);
					
				} else if (index == 4) {
					return new ResponseSex("Have some rough fun",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							AFTER_SEX_VICTORY);
					
				} else if (index == 5) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now...</br>"
								+ "Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "You really aren't sure what to do next, and start to feel pretty uncomfortable with the fact that you just beat up this poor [npc.race]."
								+ " Leaning down, you do the first thing that comes into your mind, and start apologising,"
								+ " [pc.speech(Sorry... I was just trying to defend myself, you know... Erm... Is there anything I can do to make it up to you?)]"
							+ "</p>"
							+ "<p>"
								+ "For a moment, a look of confusion crosses over [npc.name]'s face, but, as [npc.she] sees that you're genuinely troubled by what you've just done, an evil grin crosses [npc.her] face."
								+ " [npc.She] stands up, and, grabbing you by the [pc.arm], roughly pulls you into [npc.her] as [npc.she] growls,"
								+ " [npc.speech(How about you start by apologising properly?!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.Name]'s strong, dominant grip on your [pc.arm] causes you to let out a lewd little moan, and your submissive nature takes over as you do as [npc.she] asks."
								+ " [pc.speech(I'm really sorry! Please forgive me! I'll do anything! Anything you ask! Just please, don't be mad!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.Name] roughly yanks you forwards, and with a menacing growl, [npc.she] forces [npc.her] tongue into your mouth."
								+ " You let out a muffled yelp as your opponent takes charge, but as you feel [npc.her] [npc.hands] reaching down to start roughly groping your ass,"
									+ " you realise that you couldn't be happier with how things have turned out..."
							+ "</p>");
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							AFTER_COMBAT_PLAYER_VICTORY){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							Main.game.banishNPC(Main.game.getActiveNPC());
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", null){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex(
							"Rape [npc.herHim]", "[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_DOM)), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you grab [npc.name]'s [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] firmly in your embrace..."
							+ "</p>");
					
				} else if (index == 3) {
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_DOM)), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}
							},
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you take hold of [npc.name]'s [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start pressing yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] in your embrace..."
							+ "</p>");
					
				} else if (index == 4) {
					return new ResponseSex("Rape [npc.herHim] (rough)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_DOM)), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you grab [npc.name]'s [npc.arm], and, roughly yanking [npc.herHim] to [npc.her] feet, you start forcefully grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you firmly hold [npc.herHim] in your embrace..."
							+ "</p>");
					
				} else if (index == 5) {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.she] has no interest in having sex with you!",
							null);
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							AFTER_COMBAT_PLAYER_VICTORY){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							Main.game.banishNPC(Main.game.getActiveNPC());
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_PLAYER_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			if(slime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_PLAYER_SLIME"));
					
					if(slime().isAttractedTo(Main.game.getPlayer()) && slime().isWillingToRape(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_PLAYER_SLIME_RAPE"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_PLAYER_SLIME_NO_RAPE"));
					}
					return UtilText.nodeContentSB.toString();
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_TRANSFORMER");
				}
				
			} else if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_RAPE");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(slime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
				if (index == 1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Resist",
								"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to resist!",
								null);
					} else {
						return new Response("Resist", "Knock the strange fluid out of [npc.name]'s hands.", RESIST_FORCIBLY_TRANSFORMED);
					}
					
				} else if (index == 2) {
					return new Response("Submit", "Allow [npc.name] to transform you into a slime!", FORCIBLY_TRANSFORMED) {
						@Override
						public Colour getHighlightColour() {
							return Colour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().setBodyMaterial(BodyMaterial.SLIME);
						}
					};
					
				} else {
					return null;
				}
				
			} else if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally pulling away."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_PLAYER_DEFEAT){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(!Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SEX_VICTORY_RAPE");
				
			} else {
				if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
					return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SEX_VICTORY");
				} else {
					return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SEX_VICTORY_NO_ORGASM");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.name]'s clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_PLAYER_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 30;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.name]'s dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SEX_DEFEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_DEFEAT){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
