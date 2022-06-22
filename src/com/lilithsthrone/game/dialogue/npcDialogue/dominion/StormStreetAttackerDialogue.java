package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.QuickTransformations;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.8.8
 * @version 0.3.8.8
 * @author Innoxia
 */
public class StormStreetAttackerDialogue {
	
	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static NPC getMugger() {
		return Main.game.getActiveNPC();
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	public static final DialogueNode STORM_ATTACK = new DialogueNode("Attacked!", "", true) {
		@Override
		public String getContent() {
			// Storm attackers are different from alley attackers. They are not saved as persistent NPCs, so don't worry about giving any repeat-encounter descriptions.
			return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK", getMugger());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Defend yourself against the unwanted advances of [npc.name]!", getMugger());
				
			} else if (index == 2) {
				return new Response("Offer money",
						"Due to the ongoing arcane storm, [npc.name] isn't interested in your money, and only wants to have sex! You'll have to either fight [npc.herHim] or give [npc.herHim] what [npc.she] wants!",
						null);
				
			} else if (index == 3) {
				return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
						null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(getMugger()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Main.game.getPlayer().getCompanions()) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS;
								}
								return super.getSexControl(character);
							}
						},
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK_OFFER_BODY", getMugger()));
					
			} else if (index == 4 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!getMugger().isAttractedTo(Main.game.getPlayer())) {
					return new Response("Offer threesome",
							UtilText.parse(getMugger(), "You can tell that [npc.name] isn't at all interested in having sex with you, so wouldn't want to have a threesome..."),
							null);
					
				} else if(!getMugger().isAttractedTo(companion)) {
					return new Response("Offer threesome",
							UtilText.parse(getMugger(), "You can tell that [npc.name] isn't at all interested in having sex with [com.name], so wouldn't want to have a threesome..."),
							null);
					
				} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("Offer threesome",
							UtilText.parse(getMugger(), "You can tell that [com.name] isn't at all interested in having sex with [npc.name], and you can't force [com.herHim] to do so..."),
							null);
					
				} else {
					return new ResponseSex("Offer threesome",
							UtilText.parse(getMugger(), "Offer [npc.name] the opportunity to have sex with both you and [com.name] in order to avoid a violent confrontation."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK_OFFER_THREESOME", getMugger()));
				}
				
			} else if (index == 5 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();
				
				if(!getMugger().isAttractedTo(companion)) {
					return new Response("Offer [com.name]",
							UtilText.parse(getMugger(), "You can tell that [npc.name] isn't at all interested in having sex with [com.name]..."),
							null);
					
				} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("Offer [com.name]",
							UtilText.parse(getMugger(), "You can tell that [com.name] isn't at all interested in having sex with [npc.name], and you can't force [com.herHim] to do so..."),
							null);
					
				} else {
					return new ResponseSex("Offer [com.name]",
							UtilText.parse(getMugger(), "Tell [npc.name] that [npc.she] can use [com.namePos] body in order to avoid a violent confrontation."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK_OFFER_COMPANION", getMugger())) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {
		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}
		@Override
		public String getContent() {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_ATTRACTION", getMugger());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getMugger());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue",
						"Leave [npc.name] behind and continue on your way..."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Sex",
						"Well, [npc.she] <i>is</i> asking for it!",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMugger()),
								Main.game.getPlayer().getCompanions(),
								null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX", getMugger()));
				
			} else if (index == 3) {
				return new ResponseSex("Gentle sex",
						"Well, [npc.she] <i>is</i> asking for it!",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMugger()),
								Main.game.getPlayer().getCompanions(),
								null,
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getMugger()));
				
			} else if (index == 4) {
				return new ResponseSex("Rough sex",
						"Well, [npc.she] <i>is</i> asking for it!",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMugger()),
								Main.game.getPlayer().getCompanions(),
								null,
								ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getMugger()));
				
			} else if (index == 5) {
				return new ResponseSex("Submit",
						"You're not really sure what to do now... Perhaps it would be best to let [npc.name] choose what to do next?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
						null, CorruptionLevel.THREE_DIRTY, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getMugger()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion())),
						AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getMugger()));
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 8 && getMugger().isAbleToSelfTransform()) {
				return new Response("Transform [npc.herHim]",
						"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getMugger());
					}
				};
				
			} else if (index == 9 && getMugger().isAbleToSelfTransform()) {
				return new Response("Quick transformations",
						"As [npc.she] is able to transform [npc.herself], you have a few quick ideas in mind..."
								+ "(You'll return to these options once finished transforming [npc.herHim].)",
						QuickTransformations.initQuickTransformations("misc/quickTransformations", getMugger(), AFTER_COMBAT_VICTORY));
			
			} else if (index == 11 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!companion.isAttractedTo(getMugger())) {
					return new Response("Threesome", UtilText.parse(getMugger(), "[com.Name] isn't attracted to [npc.name], so wouldn't be willing to have sex with [npc.herHim]!"), null);
					
				} else {
					return new ResponseSex("Threesome",
							UtilText.parse(getMugger(), companion, "Have dominant sex with [npc.name], and get [npc2.name] to join in with the fun."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getMugger()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_THREESOME", getMugger()));
				}
				
			} else if (index == 12 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !getMugger().isAttractedTo(companion)) {
					return new Response("Give to [com.name]", UtilText.parse(getMugger(), "[npc.Name] isn't attracted to [com.name], so wouldn't be willing to have sex with [npc.herHim]!"), null);
					
				} else if(!companion.isAttractedTo(getMugger())) {
					return new Response("Give to [com.name]", UtilText.parse(getMugger(), "[com.Name] isn't attracted to [npc.name], so wouldn't be willing to have sex with [npc.herHim]!"), null);
					
				} else {
					return new ResponseSex("Give to [com.name]",
							UtilText.parse(getMugger(), "Tell [com.name] that [com.she] can have some fun with [npc.name] while you watch."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getMugger()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getMugger()));
				}
				
			} else if (index == 13 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !companion.isAttractedTo(getMugger())) {
					return new Response("Offer [com.name]", UtilText.parse(getMugger(), "[com.Name] has no interest in having sex with [npc.name]!"), null);
					
				} else if(!getMugger().isAttractedTo(companion)) {
					return new Response("Offer [com.name]", UtilText.parse(getMugger(), "[npc.Name] has no interest in having sex with [com.name]!"), null);
					
				} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("Offer [com.name]",
							UtilText.parse(getMugger(), "You can tell that [com.name] isn't at all interested in having sex with [npc.name], and you can't force [com.herHim] to do so..."),
							null);
					
				} else {
					return new ResponseSex("Offer [com.name]",
							UtilText.parse(getMugger(), "Tell [npc.name] that [npc.she] can use [com.name]."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getMugger())) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
				}
			}
			
			return null;
		}
	};
	

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_DEFEAT_GENERIC_START", getMugger()));
			
			if(isCompanionDialogue()) {
				if(getMugger().isWillingToRape()) {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "RAPE_BOTH", getMugger()));
				} else {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "OFFER_SEX_BOTH", getMugger()));
				}
				
			} else {
				if(getMugger().isWillingToRape()) {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "RAPE_PLAYER", getMugger()));
				} else {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "OFFER_SEX", getMugger()));
				}
			}
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				boolean companionHappyToHaveSex = getMainCompanion().isAttractedTo(getMugger()) || getMainCompanion().isAttractedTo(Main.game.getPlayer());
				boolean companionSex = getMugger().isAttractedTo(getMainCompanion()) && (companionHappyToHaveSex || getMugger().isWillingToRape());
				
				if (index == 1) {
					return new ResponseSex("Sex",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name] forces [npc.herself] on you"+(companionSex?" and [com.name]":"")+"..."
										:"Tell [npc.name] that you"+(companionSex?" and [com.name]":"")+" would like to have sex with [npc.herHim]."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companionSex
												?getMainCompanion()
												:null),
									null,
									Util.newArrayListOfValues(
										companionSex
											?null
											:getMainCompanion())),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_THREESOME", getMugger()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name] forces [npc.herself] on you"+(companionSex?" and [com.name]":"")+"..."
										:"Tell [npc.name] that you"+(companionSex?" and [com.name]":"")+" are more than happy to have sex with [npc.herHim]."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companionSex
												?getMainCompanion()
												:null),
									null,
									Util.newArrayListOfValues(
										companionSex
											?null
											:getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_THREESOME", getMugger()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							UtilText.parse(getMugger(), "[npc.Name] forces [npc.herself] on you"+(companionSex?" and [com.name]":"")+"..."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companionSex
												?getMainCompanion()
												:null),
									null,
									Util.newArrayListOfValues(
										companionSex
											?null
											:getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_THREESOME_RESIST", getMugger()));
					
				} else if (index == 4 && !getMugger().isWillingToRape()) {
					return new Response("Refuse",
							UtilText.parse(getMugger(), "Refuse to have sex with [npc.name] and continue on your way."),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "DEFEATED_REFUSE_THREESOME", getMugger()));
							Main.game.banishNPC(getMugger());
						}
					};
				}
				return null;
				
			} else {
				if (index == 1) {
					return new ResponseSex("Sex",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name] forces [npc.herself] on you..."
										:"Tell [npc.name] that you would like to have sex with [npc.herHim]."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion())),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX", getMugger()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name] forces [npc.herself] on you..."
										:"Tell [npc.name] that you are more than happy to have sex with [npc.herHim]."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX", getMugger()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							UtilText.parse(getMugger(), "[npc.Name] forces [npc.herself] on you..."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_RESIST", getMugger()));
					
				} else if (index == 4 && !getMugger().isWillingToRape()) {
					return new Response("Refuse",
							UtilText.parse(getMugger(), "Refuse to have sex with [npc.name] and continue on your way."),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "DEFEATED_REFUSE_SEX", getMugger()));
							Main.game.banishNPC(getMugger());
						}
					};
				}
				return null;
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
			if(Main.sex.getNumberOfOrgasms(getMugger()) >= getMugger().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_SEX_VICTORY", getMugger());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_SEX_VICTORY_NO_ORGASM", getMugger());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue",
						"Leave [npc.name] behind and continue on your way."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Collapse", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_SEX_DEFEAT", getMugger());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue",
						"Continue on your way."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.name] from the game!)]",
						Main.game.getDefaultDialogue(false)) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
					}
				};
			}
			return null;
		}
	};
}
