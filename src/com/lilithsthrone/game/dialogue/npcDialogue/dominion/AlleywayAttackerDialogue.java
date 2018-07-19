package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.?
 * @version 0.2.2
 * @author Innoxia
 */
public class AlleywayAttackerDialogue {

	private static boolean isCanal() {
		PlaceType pt = getMugger().getLocationPlace().getPlaceType();
		return (pt == PlaceType.DOMINION_ALLEYS_CANAL_CROSSING
				|| pt == PlaceType.DOMINION_CANAL
				|| pt == PlaceType.DOMINION_CANAL_END);
	}
	
	private static NPC getMugger() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNodeOld ALLEY_ATTACK = new DialogueNodeOld("Assaulted!", "A figure jumps out from the shadows!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(getMugger().getLastTimeEncountered() != -1) {
				if(isCanal()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_CANAL_REPEAT_INTRO", getMugger()));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_REPEAT_INTRO", getMugger()));
				}
				
				if(getMugger().isVisiblyPregnant()){
					// Pregnant encounters:
					if(!getMugger().isReactedToPregnancy()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_REPEAT_PREGNANCY_REVEAL", getMugger()));
					
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_REPEAT_STILL_PREGNANT", getMugger()));
					}
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_REPEAT", getMugger()));
				}
				
			} else {
				if(isCanal()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_CANAL_INTRO", getMugger()));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_INTRO", getMugger()));
				}

				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK", getMugger()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Stand up for yourself and fight [npc.name]!", getMugger());
				
			} else if (index == 2) {
				if(Main.game.getPlayer().getMoney()<250) {
					return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "You don't have enough money to offer to pay [npc.name] off. You'll have to either fight [npc.herHim] or offer [npc.herHim] your body!", null);
				} else {
					return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "Offer to pay [npc.name] 250 flames to leave you alone.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-250);
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_PAY_OFF", getMugger()));
						}
					};
				}
				
			} else if (index == 3) {
				if(getMugger().isAttractedTo(Main.game.getPlayer())) {
					return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							Main.game.getPlayer().getCompanions().isEmpty()
								?null
								:Util.newArrayListOfValues(Main.game.getPlayer().getCompanions().get(0)),
							AFTER_SEX_DEFEAT,
							Main.game.getPlayer().getCompanions().isEmpty()
								?UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_OFFER_BODY", getMugger())
								:UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_OFFER_BODY_SOLO_WITH_COMPANION", getMugger(), Main.game.getPlayer().getCompanions().get(0)));
					
				} else {
					return new Response("Offer body", "You can tell that [npc.name] isn't at all interested in having sex with you. You'll either have to offer [npc.herHim] some money, or prepare for a fight!", null);
				}
				
			} else if (index == 4 && !Main.game.getPlayer().getCompanions().isEmpty()) {
				GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);

				if(!getMugger().isAttractedTo(Main.game.getPlayer())) {
					return new Response(UtilText.parse(companion, "Offer threesome"),
							UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with you, so wouldn't want to have a threesome..."),
							null);
					
				} else if(!getMugger().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "Offer threesome"),
							UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name], so wouldn't want to have a threesome..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Offer threesome"),
							UtilText.parse(getMugger(), companion, "Offer [npc.name] the opportunity to have sex with both you and [npc2.name] in order to avoid a violent confrontation."),
							true, true,
							new SMDoggy(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.DOGGY_BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
											new Value<>(companion, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_OFFER_BODY_WITH_COMPANION", getMugger(), companion));
				}
				
			} else if (index == 5 && !Main.game.getPlayer().getCompanions().isEmpty() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);

				if(getMugger().isAttractedTo(companion)) {
					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.namePos] body in order to avoid a violent confrontation."),
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_OFFER_COMPANION", getMugger(), companion)) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
					
				} else {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name]..."),
							null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld STORM_ATTACK = new DialogueNodeOld("Attacked!", "A figure jumps out of a nearby doorway!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Assaulted!";
		}

		@Override
		public String getContent() {
			// Storm attackers are different from alley attackers. They are not saved as persistent NPCs, so don't worry about giving any repeat-encounter descriptions.
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "STORM_ATTACK", getMugger());
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
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						Main.game.getPlayer().getCompanions().isEmpty()
							?null
							:Util.newArrayListOfValues(Main.game.getPlayer().getCompanions().get(0)),
						AFTER_SEX_DEFEAT,
						Main.game.getPlayer().getCompanions().isEmpty()
							?UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_OFFER_BODY", getMugger())
							:UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_OFFER_BODY_SOLO_WITH_COMPANION", getMugger(), Main.game.getPlayer().getCompanions().get(0)));
					
			} else if (index == 4 && !Main.game.getPlayer().getCompanions().isEmpty()) {
				GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);

				return new ResponseSex(UtilText.parse(companion, "Offer threesome"),
						UtilText.parse(getMugger(), companion, "Offer [npc.name] the opportunity to have sex with both you and [npc2.name] in order to avoid a violent confrontation."),
						true, true,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
										new Value<>(companion, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_OFFER_BODY_WITH_COMPANION", getMugger(), companion));
				
				
			} else if (index == 5 && !Main.game.getPlayer().getCompanions().isEmpty() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);

				return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
						UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.namePos] body in order to avoid a violent confrontation."),
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "ALLEY_ATTACK_OFFER_COMPANION", getMugger(), companion)) {
					@Override
					public void effects() {
						if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
							Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() {
			if(getMugger().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_ATTRACTION", getMugger());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getMugger());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getMugger().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new Response("Continue", "Decide against having sex with [npc.name] and continue on your way...", Main.game.getDefaultDialogueNoEncounter());
					
				} else if (index == 2) {
					return new ResponseSex("Sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_SEX", getMugger()));
					
				} else if (index == 3) {
					return new ResponseSex("Gentle sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getMugger()));
					
				} else if (index == 4) {
					return new ResponseSex("Rough sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getMugger()));
					
				} else if (index == 5) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now... Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getMugger()));
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 7 && !Main.game.getPlayer().getCompanions().isEmpty()) {
					GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);

					if(!companion.isAttractedTo(getMugger())) {
						return new Response(UtilText.parse(companion, "Threesome"), UtilText.parse(companion, getMugger(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Threesome"),
								UtilText.parse(getMugger(), companion, "Have dominant sex with [npc.name], and get [npc2.name] to join in with the fun."),
								true, false,
								new SMDoggy(
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND),
												new Value<>(companion, SexPositionSlot.DOGGY_INFRONT)),
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_THREESOME", getMugger(), companion));
					}
					
				} else if (index == 8 && !Main.game.getPlayer().getCompanions().isEmpty()) {
					GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);
					
					if(!companion.isAttractedTo(getMugger())) {
						return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getMugger(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
								UtilText.parse(companion, getMugger(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] while you watch."),
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getMugger(), companion));
					}
					
				} else if (index == 9 && !Main.game.getPlayer().getCompanions().isEmpty() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);

					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.name]."),
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getMugger(), companion)) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
					
				} else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_BANISH_NPC", getMugger()));
							Main.game.banishNPC(getMugger());
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
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_RAPE", getMugger()));
					
				} else if (index == 3) {
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getMugger()));
					
				} else if (index == 4) {
					return new ResponseSex("Rape [npc.herHim] (rough)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getMugger()));
					
				} else if (index == 5) {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.she] has no interest in having sex with you!",
							null);
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				}else if (index == 7 && !Main.game.getPlayer().getCompanions().isEmpty()) {
					GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);

					if(!companion.isAttractedTo(getMugger())) {
						return new Response(UtilText.parse(companion, "Threesome"), UtilText.parse(companion, getMugger(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Threesome"),
								UtilText.parse(getMugger(), companion, "Have dominant sex with [npc.name], and get [npc2.name] to join in with the fun."),
								true, false,
								new SMDoggy(
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND),
												new Value<>(companion, SexPositionSlot.DOGGY_INFRONT)),
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_THREESOME", getMugger(), companion));
					}
					
				} else if (index == 8 && !Main.game.getPlayer().getCompanions().isEmpty()) {
					GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);
					
					if(!companion.isAttractedTo(getMugger())) {
						return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getMugger(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
								UtilText.parse(companion, getMugger(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] while you watch."),
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getMugger(), companion));
					}
					
				} else if (index == 9 && !Main.game.getPlayer().getCompanions().isEmpty() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);

					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.name]."),
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getMugger(), companion)) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
					
				}  else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_VICTORY_BANISH_NPC", getMugger()));
							Main.game.banishNPC(getMugger());
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;
		
		Value<String, AbstractItem> potion = null;
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {

			if(!Main.game.getPlayer().getCompanions().isEmpty()
					&& getMugger().isWillingToRape(Main.game.getPlayer())
					&& ((getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isAttractedTo(Main.game.getPlayer().getCompanions().get(0)))
							|| (getMugger().isAttractedTo(Main.game.getPlayer().getCompanions().get(0)) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)))) {

				GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);
				
				if (getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isAttractedTo(companion)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_NO_TF_ATTRACTED", getMugger());
				
				} else if (getMugger().isAttractedTo(companion) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_WANTS_COMPANION", getMugger());
					
				}
			}
			
			if(getMugger().hasTransformationFetish() && getMugger().isWillingToRape(Main.game.getPlayer()) ) {
				potion = getMugger().getTransfomativePotion(true);
				
//				System.out.println("Potion Check 1"); 
//				System.out.println(potion); 
//				System.out.println(potion.getValue().getName()); 
				
				if(potion == null) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_TF_FINISHED", getMugger());
					
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_TF", getMugger());
				}
			}
				
			if(getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isWillingToRape(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_NO_TF_ATTRACTED", getMugger());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_NO_TF_NOT_ATTRACTED", getMugger());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getPlayer().getCompanions().isEmpty()
					&& getMugger().isWillingToRape(Main.game.getPlayer())
					&& ((getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isAttractedTo(Main.game.getPlayer().getCompanions().get(0)))
							|| (getMugger().isAttractedTo(Main.game.getPlayer().getCompanions().get(0)) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)))) {
				
				GameCharacter companion = Main.game.getPlayer().getCompanions().get(0);

				if (index == 1) {
					if (getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isAttractedTo(companion)) {
							return new ResponseSex(UtilText.parse(companion, "Threesome"),
									UtilText.parse(getMugger(), companion, "[npc.Name] uses this opportunity to have sex with both you and [npc2.name]..."),
									false, false,
									new SMDoggy(
											Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.DOGGY_BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
													new Value<>(companion, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
									null,
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_THREESOME", getMugger(), companion));
						
					} else if (getMugger().isAttractedTo(companion) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {

						return new ResponseSex(UtilText.parse(companion, "[npc.Name] used"),
								UtilText.parse(getMugger(), companion, "[npc.Name] uses [npc2.namePos] body in order to get some sexual relief..."),
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_USES_COMPANION", getMugger(), companion));
						
					}
					
				} else {
					return null;
				}
			}
			
			if(getMugger().hasTransformationFetish()
					&& potion != null
					&& getMugger().isWillingToRape(Main.game.getPlayer())) {
				
//				System.out.println("Potion Check 2"); 
//				System.out.println(potion); 
//				System.out.println(potion.getValue()); 
				
				if (index == 1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
								"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
								null);
					} else {
						return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_TRANSFORMATION_REFUSED);
					}
					
				} else if (index == 2) {
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
					
					if(potion.getValue().getItemType() == ItemType.FETISH_REFINED) {
						applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_KINK_RECEIVING);
						applicableCorrutionLevel = Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel();
					}
					
					return new Response("Swallow", "Do as you're told and swallow the strange potion.", AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorrutionLevel,
							null,
							null,
							null){
						@Override
						public void effects(){
							Util.Value<String, AbstractItem> potion = getMugger().getTransfomativePotion();
							
//							System.out.println("Potion Check 3"); 
//							System.out.println(potion.getValue().getName()); 
//							System.out.println(potion); 
							
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "[npc.Name] steps back, grinning down at you as you obediently swallow the strange liquid."
										+ " [npc.speech(Good [pc.girl]! "+potion.getKey()+")]"
									+ "</p>"
									+ "<p>"
										+getMugger().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
									+"</p>");
						}
					};
					
				}
				
			} else if(getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isWillingToRape(Main.game.getPlayer())) {
				
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_SEX", getMugger()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getMugger()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getMugger()));
					
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				}
			}
			
			return null;
			
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATION_REFUSED = new DialogueNodeOld("Avoided Transformation", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_TRANSFORMATION_REFUSED_ATTRACTED", getMugger());
			
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_TRANSFORMATION_REFUSED_NOT_ATTRACTED", getMugger());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getMugger()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getMugger()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getMugger()));
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATION = new DialogueNodeOld("Transformed", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_TRANSFORMATION_ATTRACTED", getMugger());
			
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_TRANSFORMATION_NOT_ATTRACTED", getMugger());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_SEX", getMugger()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getMugger()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getMugger()));
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(!getMugger().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_SEX_VICTORY_RAPE", getMugger());
				
			} else {
				if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_SEX_VICTORY", getMugger());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_SEX_VICTORY_NO_ORGASM", getMugger());
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
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
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
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttack", "AFTER_SEX_DEFEAT", getMugger());
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
				
			} else {
				return null;
			}
		}
	};
}
