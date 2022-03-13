package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.5
 * @version 0.3.4
 * @author Innoxia
 */
public class TunnelSlimeDialogue {
	
	private static NPC getSlime() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode ATTACK = new DialogueNode("Assaulted!", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_INTRO"));
			
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME"));
					if(getSlime().isAttractedTo(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
					}
					
				}
				// Slime transformation descriptions are appended in SubmissionAttacker getEncounterDialogue() method.
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					if (index == 1) {
						return new ResponseCombat("Fight",
								"You aren't going to stand for [npc.name] transforming people into slimes. Teach [npc.herHim] a lesson!", Main.game.getActiveNPC(),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), "[pc.speech(You aren't going to be stalking these tunnels on my watch!)] you shout, launching into an attack!"),
										new Value<>(getSlime(), "[npc.speech(Traitor! You'll pay for this!)] [npc.Name] cries out as [npc.she] prepares to defend [npc.herself].")));
						
					} else if (index == 2) {
						return new Response("Leave", "Continue on your journey.", ATTACK) {
							@Override
							public void effects(){
								if(getSlime().isAttractedTo(Main.game.getPlayer())) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_TURN_DOWN_SEX"));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_LEAVE"));
								}
							}
							@Override
							public DialogueNode getNextDialogue(){
								return Main.game.getDefaultDialogue(false);
							}
						};
						
					} else if (index == 3) {
						if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Dominant Sex", "[npc.Name] isn't attracted to you!", null);
							
						} else if(getSlime().hasFetish(Fetish.FETISH_DOMINANT) && !getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
							return new Response("Dominant Sex", "[npc.Name] isn't willing to let you be the dominant partner.", null);
							
						} else {
							return new ResponseSex("Dominant Sex", "Take the dominant role and have sex with [npc.name].",
									null, null, null,
									null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(getSlime()),
									null,
									null), AFTER_SLIME_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_DOM"));
						}
						
					} else if (index == 4) {
						if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Submissive Sex", "[npc.Name] isn't attracted to you!", null);
							
						} else if(getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE) && !getSlime().hasFetish(Fetish.FETISH_DOMINANT)) {
							return new Response("Submissive Sex", "[npc.Name] isn't willing to let you be the submissive partner.", null);
							
						} else {
							return new ResponseSex("Submissive Sex", "Take the submissive role and have sex with [npc.name].",
									null, null, null,
									null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(getSlime()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null), AFTER_SLIME_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_SUB"));
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
										new Value<>(getSlime(), "")));
						
					} else if (index == 2) {
						return new Response("Offer Money", "[npc.Name] isn't interested in your money! All [npc.she] wants to do is turn you into a slime!", null);
						
					} else if (index == 3) {
						return new Response("Submit", "Allow [npc.name] to transform you into a slime!", TRANSFORMED) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.TRANSFORMATION_GENERIC;
							}
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setBodyMaterial(BodyMaterial.SLIME));
								
								if(getSlime().isAttractedTo(Main.game.getPlayer())) {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMED_SLIME_OFFER_SEX"));
								} else {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMED_SLIME_NO_SEX"));
								}
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
									new Value<>(getSlime(), "")));
				
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand2()) {
						return new Response("Offer money ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand2(), "span")+")",
								"You don't have enough money to offer to pay [npc.name] off. You'll have to either fight [npc.herHim] or offer [npc.herHim] your body!", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand2(), "span")+")",
								"Offer to pay [npc.name] "+Util.intToString(Main.game.getDialogueFlags().getMuggerDemand2())+" flames to leave you alone.", OFFER_MONEY) {
							@Override
							public void effects() {
								Main.game.getPlayer().incrementMoney(-250);
							}
						};
					}
					
				} else if (index == 3) {
					if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getSlime()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null) {
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS;
										}
										return super.getSexControl(character);
									}
								},
								AFTER_SLIME_SEX_AS_SUB,
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
	
	public static final DialogueNode ATTACK_REPEAT = new DialogueNode("Assaulted!", "", true) {
		
		@Override
		public String getContent() {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_REPEAT_TRANSFORMER_PLAYER_SLIME"));
					if(getSlime().isAttractedTo(Main.game.getPlayer())) {
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
	
	public static final DialogueNode ATTACK_PREGNANCY_REVEAL = new DialogueNode("Assaulted!", "", true) {
		
		@Override
		public String getContent() {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_PREGNANCY_REVEAL_TRANSFORMER_PLAYER_SLIME"));
					if(getSlime().isAttractedTo(Main.game.getPlayer())) {
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
	
	
	public static final DialogueNode AFTER_SLIME_SEX_AS_DOM = new DialogueNode("Step back", "", true) {
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getSlime()) >= getSlime().getOrgasmsBeforeSatisfied()) {
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
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SLIME_SEX_AS_SUB = new DialogueNode("Used", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "[npc.Name] has had enough fun with you for now.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getSlime()) >= getSlime().getOrgasmsBeforeSatisfied()) {
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
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode TRANSFORMED = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Continue on your journey.", TRANSFORMED) {
					@Override
					public void effects(){
						if(getSlime().isAttractedTo(Main.game.getPlayer())) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_TURN_DOWN_SEX"));
						}
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if (index == 2) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
					return new Response("Dominant Sex", "[npc.Name] isn't attracted to you!", null);
					
				} else if(getSlime().hasFetish(Fetish.FETISH_DOMINANT) && !getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
					return new Response("Dominant Sex", "[npc.Name] isn't willing to let you be the dominant partner.", null);
					
				} else {
					return new ResponseSex("Dominant Sex", "Take the dominant role and have sex with [npc.name].",
							null, null, null,
							null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
							null,
							null), AFTER_SLIME_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_DOM"));
				}
				
			} else if (index == 3) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
					return new Response("Submissive Sex", "[npc.Name] isn't attracted to you!", null);
					
				} else if(getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE) && !getSlime().hasFetish(Fetish.FETISH_DOMINANT)) {
					return new Response("Submissive Sex", "[npc.Name] isn't willing to let you be the submissive partner.", null);
					
				} else {
					return new ResponseSex("Submissive Sex", "Take the submissive role and have sex with [npc.name].",
							null, null, null,
							null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SLIME_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_SUB"));
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode RESIST_TRANSFORMED = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_TRANSFORMED"));
			
			if(getSlime().isAttractedTo(Main.game.getPlayer()) && getSlime().isWillingToRape() && Main.game.isNonConEnabled()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_TRANSFORMED_RAPE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_TRANSFORMED_NO_RAPE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape() && Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMLyingDown(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
							null,
							null, AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "You surrender yourself to [npc.name], lying back and offering no resistance as [npc.she] tongue-fucks your throat."
								+ " After a moment, [npc.she] sits back up, leaving a wet pool of slime all over your [pc.lips]."
								+ " [npc.speech(Good bitch! You know your place already! Now be a good [pc.girl] and try to enjoy this...)]"
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMLyingDown(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							null, AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "You eagerly surrender yourself to [npc.name], raising your head to help [npc.herHim] tongue-fuck your throat."
								+ " After a moment, [npc.she] sits back up, leaving a wet pool of slime all over your [pc.lips]."
								+ " [npc.speech(Good bitch! You know your place already! Now be a good [pc.girl] and try to enjoy this...)]"
							+ "</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMLyingDown(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							null, AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "You try to turn your face away, but [npc.name] simply reaches up to grip both sides of your head, holding you still as [npc.she] tongue-fucks your throat."
								+ " After a moment, [npc.she] sits back up, leaving a wet pool of slime all over your [pc.lips]."
								+ " [npc.speech(Stupid bitch! You can resist as much as you want! I'm not going to stop until I'm satisfied!)]"
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_PLAYER_DEFEAT){
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	

	public static final DialogueNode OFFER_MONEY = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
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
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	// Standard combat:
	

	public static final DialogueNode AFTER_COMBAT_PLAYER_VICTORY = new DialogueNode("Victory", "", true) {

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
		
			if (index == 1) {
				return new Response("Continue", "Carry on your way...", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						if(getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getSlime());
						}
					}
				};
				
			} else if (index == 2) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getSlime().isAttractedTo(Main.game.getPlayer())) {
					return new ResponseSex("Sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_SEX", getSlime()));
					
				} else {
					return new ResponseSex(
							"Rape [npc.herHim]",
							"[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_RAPE", getSlime()));
				}
				
			} else if (index == 3) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Gentle Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getSlime().isAttractedTo(Main.game.getPlayer())){
					return new ResponseSex("Gentle sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getSlime()));
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getSlime()));
				}
				
			} else if (index == 4) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Rough Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getSlime().isAttractedTo(Main.game.getPlayer())){
					return new ResponseSex("Rough sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getSlime()));
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (rough)", "[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getSlime()));
				}
				
			} else if (index == 5) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
					return new Response("Submit",
							"You can't submit to [npc.name], as [npc.sheHasFull] no interest in having sex with you!",
							null);
				} else {
					return new ResponseSex("Submit",
							"You're not really sure what to do now... Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null),
							AFTER_SLIME_SEX_AS_SUB,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getSlime()));
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getSlime(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			}
			//TODO
//			else if (index == 7) {
//				if(slime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
//					return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
//					
//				} else {
//					return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
//						@Override
//						public void effects() {
//							slime().setPlayerKnowsName(true);
//							Main.game.getTextEndStringBuilder().append(slime().setAffection(Main.game.getPlayer(), 10));
//						}
//					};
//				}
//				
//			} 
			else if (index == 8 && getSlime().isAbleToSelfTransform()) {
				return new Response("Transform [npc.herHim]",
						"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getSlime());
					}
				};
				
			} else if (index == 10 && !getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"Remove character",
						UtilText.parse(getSlime(), "Scare [npc.name] away."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_BANISH_NPC", getSlime()));
						Main.game.banishNPC(getSlime());
					}
				};
				
			} else {
				return null;
			}
			
		}
	};

	public static final DialogueNode AFTER_COMBAT_PLAYER_DEFEAT = new DialogueNode("Defeat", "", true) {
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT"));
			
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_TRANSFORMER"));
				
			} else {
				if(getSlime().isAttractedTo(Main.game.getPlayer()) && getSlime().isWillingToRape() && Main.game.isNonConEnabled()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_RAPE"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_NO_RAPE"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Resist");
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Resist",
								"Due to your <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to resist!",
								null);
					} else {
						return new Response("Resist", "Knock the strange fluid out of [npc.namePos] hands.", RESIST_TRANSFORMED);
					}
					
				} else if (index == 2) {
					return new Response("Submit", "Allow [npc.name] to transform you into a slime!", TRANSFORMED) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().setBodyMaterial(BodyMaterial.SLIME);
						}
					};
					
				} else {
					return null;
				}
				
			} else if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape() && Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getActiveNPC()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "You surrender yourself to [npc.name], lying back and offering no resistance as [npc.she] tongue-fucks your throat."
								+ " After a moment, [npc.she] sits back up, leaving a wet pool of slime all over your [pc.lips]."
								+ " [npc.speech(Good bitch! You know your place already! Now be a good [pc.girl] and try to enjoy this...)]"
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getActiveNPC()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER), AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "You eagerly surrender yourself to [npc.name], raising your head to help [npc.herHim] tongue-fuck your throat."
								+ " After a moment, [npc.she] sits back up, leaving a wet pool of slime all over your [pc.lips]."
								+ " [npc.speech(Good bitch! You know your place already! Now be a good [pc.girl] and try to enjoy this...)]"
							+ "</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getActiveNPC()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING), AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "You try to turn your face away, but [npc.name] simply reaches up to grip both sides of your head, holding you still as [npc.she] tongue-fucks your throat."
								+ " After a moment, [npc.she] sits back up, leaving a wet pool of slime all over your [pc.lips]."
								+ " [npc.speech(Stupid bitch! You can resist as much as you want! I'm not going to stop until I'm satisfied!)]"
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_PLAYER_DEFEAT){
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Step back", "", true) {
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getSlime()) >= getSlime().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM");
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						UtilText.parse(getSlime(), "Scare [npc.name] away."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
						AFTER_COMBAT_PLAYER_VICTORY){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
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
	
}
