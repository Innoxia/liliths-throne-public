package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public class AlleywayDemonDialogueCompanions {
	
	private static List<GameCharacter> getCompanions() {
		return Main.game.getPlayer().getCompanions();
	}
	
	private static GameCharacter getMainCompanion() {
		return getCompanions().get(0);
	}
	
	private static NPC getDemon() {
		return Main.game.getActiveNPC();
	}
	
	private static boolean isWantsToFight() {
		return getDemon().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_ONE_FRIENDLY.getMinimumValue();
	}
	
	private static boolean isAffectionHighEnoughToInviteHome() {
		return getDemon().getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		List<GameCharacter> allCharacters = new ArrayList<>();
		allCharacters.add(getDemon());
		allCharacters.addAll(Main.game.getPlayer().getCompanions());
		Collections.sort(allCharacters, (c1, c2) -> c1 instanceof Elemental?(c2 instanceof Elemental?0:1):(c2 instanceof Elemental?-1:0));
		return allCharacters;
	}
	
	private static void applyPregnancyReactions() {
		if(getDemon().isVisiblyPregnant()){
			getDemon().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getDemon(), true);
		}
		if(getMainCompanion().isVisiblyPregnant()) {
			getMainCompanion().setCharacterReactedToPregnancy(getDemon(), true);
		}
	}
	
	private static String getStatus() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'><i>");
		AffectionLevel al = getDemon().getAffectionLevel(Main.game.getPlayer());
		switch(al) {
			case NEGATIVE_FIVE_LOATHE:
			case NEGATIVE_FOUR_HATE:
			case NEGATIVE_THREE_STRONG_DISLIKE:
			case NEGATIVE_TWO_DISLIKE:
			case NEGATIVE_ONE_ANNOYED:
			case ZERO_NEUTRAL:
				break;
			case POSITIVE_ONE_FRIENDLY:
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] is acting in a <i style='color:"+al.getColour().toWebHexString()+";'>friendly, flirtatious</i> manner towards you.");
				} else {
					sb.append("[npc.Name] is acting in a <i style='color:"+al.getColour().toWebHexString()+";'>friendly</i> manner towards you.");
				}
				break;
			case POSITIVE_TWO_LIKE:
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>likes you</i>, and sees you as more than just a friend.");
				} else {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>likes you</i>, and sees you as a close friend.");
				}
				break;
			case POSITIVE_THREE_CARING:
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>cares about you a lot</i>, and is deeply attracted towards you.");
				} else {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>cares about you a lot</i>, and considers you to be [npc.her] best friend.");
				}
				break;
			case POSITIVE_FOUR_LOVE:
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					sb.append("You can tell from the way that [npc.she] looks at you that [npc.name] <i style='color:"+al.getColour().toWebHexString()+";'>loves you</i>.");
				} else {
					sb.append("You can tell that [npc.name] <i style='color:"+al.getColour().toWebHexString()+";'>loves you</i> in a purely platonic manner.");
				}
				break;
			case POSITIVE_FIVE_WORSHIP:
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] <i style='color:"+al.getColour().toWebHexString()+";'>worships you</i>, and is head-over-heels in love with you.");
				} else {
					sb.append("[npc.Name] <i style='color:"+al.getColour().toWebHexString()+";'>worships you</i>, and would do almost anything you asked of [npc.herHim].");
				}
				break;
		}
		sb.append("</i></p>");
		
		return UtilText.parse(getDemon(), sb.toString());
	}
	
	public static final DialogueNodeOld ALLEY_DEMON_ATTACK = new DialogueNodeOld("Assaulted!", "A figure jumps out from the shadows!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean pregnancyReaction = false;
			
			if(getDemon().getLastTimeEncountered() != -1) {
				if(isWantsToFight()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_REPEAT_INTRO", getAllCharacters()));
					
					if(getDemon().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!getDemon().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_REPEAT_PREGNANCY_REVEAL", getAllCharacters()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_REPEAT_STILL_PREGNANT", getAllCharacters()));
						}
					}
					
					if(Main.game.getPlayer().isVisiblyPregnant() || getMainCompanion().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if((Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(getDemon()))
								|| (getMainCompanion().isVisiblyPregnant() && !getMainCompanion().isCharacterReactedToPregnancy(getDemon()))) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_REPEAT_PLAYER_COMPANION_PREGNANCY", getAllCharacters()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_REPEAT_CONTINUED_PLAYER_COMPANION_PREGNANCY", getAllCharacters()));
						}
					}

					if(!pregnancyReaction) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_REPEAT", getAllCharacters()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_REPEAT_END", getAllCharacters()));

					
				} else { // The mugger doesn't want to attack the player:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_PEACEFUL_INTRO", getAllCharacters()));
					
					if(getDemon().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!getDemon().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_PEACEFUL_PREGNANCY_REVEAL", getAllCharacters()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_PEACEFUL_STILL_PREGNANT", getAllCharacters()));
						}
					}
					
					if(Main.game.getPlayer().isVisiblyPregnant() || getMainCompanion().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if((Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(getDemon()))
								|| (getMainCompanion().isVisiblyPregnant() && !getMainCompanion().isCharacterReactedToPregnancy(getDemon()))) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_PEACEFUL_PLAYER_COMPANION_PREGNANCY", getAllCharacters()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_PEACEFUL_CONTINUED_PLAYER_COMPANION_PREGNANCY", getAllCharacters()));
						}
					}
					
					if(!pregnancyReaction) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_PEACEFUL", getAllCharacters()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_PEACEFUL_END", getAllCharacters()));

					UtilText.nodeContentSB.append(getStatus());
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_INTRO", getAllCharacters()));

				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isWantsToFight()) {
				if (index == 1) {
					return new ResponseCombat("Fight", "Stand up for yourself and fight [npc.name]!", getDemon()) {
						@Override
						public void effects() {
							applyPregnancyReactions();
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<250) {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "You don't have enough money to offer to pay [npc.name] off. You'll have to either fight [npc.herHim] or offer [npc.herHim] your body!", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "Offer to pay [npc.name] 250 flames to leave you alone.", Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getPlayer().incrementMoney(-250);
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_PAY_OFF", getAllCharacters()));
							}
						};
					}
					
				} else if (index == 3) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(getMainCompanion()),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_OFFER_BODY_SOLO_WITH_COMPANION", getDemon(), getMainCompanion())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Offer body", "You can tell that [npc.name] isn't at all interested in having sex with you. You'll either have to offer [npc.herHim] some money, or prepare for a fight!", null);
					}
					
				} else if (index == 4) {
					GameCharacter companion = getMainCompanion();
	
					if(!getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with you, so wouldn't want to have a threesome..."),
								null);
						
					} else if(!getDemon().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name], so wouldn't want to have a threesome..."),
								null);
						
					} else if(!companion.isAttractedTo(getDemon()) && !companion.isSlave() && !(companion instanceof Elemental)) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and as [npc2.sheIs] not your slave, you can't force [npc2.herHim] to have sex..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "Offer [npc.name] the opportunity to have sex with both you and [npc2.name] in order to avoid a violent confrontation."),
								true, true,
								new SMDoggy(
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.DOGGY_BEHIND)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
												new Value<>(companion, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
								null,
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_OFFER_BODY_WITH_COMPANION", getDemon(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getDemon().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name]..."),
								null);
						
					} else if(!companion.isAttractedTo(getDemon()) && !companion.isSlave() && !(companion instanceof Elemental)) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and as [npc2.sheIs] not your slave, you can't force [npc2.herHim] to have sex..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "Tell [npc.name] that [npc.she] can use [npc2.namePos] body in order to avoid a violent confrontation."),
								true, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_OFFER_COMPANION", getDemon(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getDemon()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else {
					return null;
				}
			
			} else {
				if (index == 1) {
					return new Response("Talk", "Talk to [npc.name] for a while in order to get to know [npc.herHim] a little better.", ALLEY_PEACEFUL_TALK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 5));
							
							if(isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<250) {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"You don't have enough money to offer [npc.name] any.", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"Offer [npc.name] some money to help [npc.herHim] buy food and clothing.", ALLEY_PEACEFUL_OFFER_MONEY) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-250));
								Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 10));

								if(isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
								}
							}
						};
					}
					
				} else if (index == 3) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (dom)", "Take the dominant role and have sex with [npc.name] .",
								Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								AFTER_SEX_PEACEFUL,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_PEACEFUL_SEX_AS_DOM", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (dom)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} else if (index == 4) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (sub)", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								AFTER_SEX_PEACEFUL,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_PEACEFUL_SEX_AS_SUB", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (sub)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} if (index == 5) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !isAffectionHighEnoughToInviteHome()) {
						return new Response("Offer room",
								"You feel as though it would be best to spend some more time getting to know [npc.name] before inviting [npc.herHim] back to Lilaya's mansion...<br/>"
								+ "[style.italics(Requires [npc.name] to have at least "+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()+" affection towards you.)]",
								null);
						
					} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						return new Response("Offer room",
								"You'll need to get Lilaya's permission before inviting [npc.name] back to her mansion...",
								null);
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						return new Response("Offer room",
								"You don't have a suitable room prepared for [npc.name] to move in to. Upgrade one of the empty rooms in Lilaya's house to a 'Guest Room' first.",
								null);
						
					}else {
						return new Response("Offer room", "Ask [npc.name] if [npc.she] would like a room in Lilaya's mansion.", ALLEY_PEACEFUL_OFFER_ROOM) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 25));
							}
						};
					}
					
				} else if (index==10) {
					return new Response("Attack", "Betray [npc.namePos] trust and attack [npc.herHim]!", ALLEY_PEACEFUL_ATTACK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), -50));
							getDemon().addFlag(NPCFlagValue.genericNPCBetrayedByPlayer);
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you're in a rush to be somewhere else, before continuing on your way.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PEACEFUL_TALK = new DialogueNodeOld("Talk", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_PEACEFUL_TALK", getAllCharacters()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_PEACEFUL_CAN_INVITE_HOME", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] go.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PEACEFUL_OFFER_MONEY = new DialogueNodeOld("Offer money", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_PEACEFUL_OFFER_MONEY", getAllCharacters()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_PEACEFUL_CAN_INVITE_HOME", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] go and buy food.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PEACEFUL_OFFER_ROOM = new DialogueNodeOld("Offer room", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_PEACEFUL_OFFER_ROOM", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take home", "Take [npc.name] to [npc.her] new room.", ALLEY_PEACEFUL_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						getDemon().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(getDemon());
						Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 50));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PEACEFUL_OFFER_ROOM_BACK_HOME = new DialogueNodeOld("New Room", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_PEACEFUL_OFFER_ROOM_BACK_HOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] get settled in.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PEACEFUL_ATTACK = new DialogueNodeOld("Attack", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_PEACEFUL_ATTACK", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Start fighting [npc.name]!", getDemon());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_PEACEFUL = new DialogueNodeOld("Continue", "Step away from [npc.name] and prepare to continue on your way.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(getDemon())>0) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_SEX_PEACEFUL", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_SEX_PEACEFUL_NO_ORGASM", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
				
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
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "STORM_ATTACK", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Defend yourself against the unwanted advances of [npc.name]!", getDemon());
				
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
								Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						Util.newArrayListOfValues(getMainCompanion()),
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "STORM_ATTACK_OFFER_BODY", getDemon(), getMainCompanion()));
					
			} else if (index == 4) {
				GameCharacter companion = getMainCompanion();
				
				if(!getDemon().isAttractedTo(Main.game.getPlayer())) {
					return new Response(UtilText.parse(companion, "Offer threesome"),
							UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with you, so wouldn't want to have a threesome..."),
							null);
					
				} else if(!getDemon().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "Offer threesome"),
							UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name], so wouldn't want to have a threesome..."),
							null);
					
				} else if(!companion.isAttractedTo(getDemon()) && !companion.isSlave() && !(companion instanceof Elemental)) {
					return new Response(UtilText.parse(companion, "Offer threesome"),
							UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and as [npc2.sheIs] not your slave, you can't force [npc2.herHim] to have sex..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Offer threesome"),
							UtilText.parse(getDemon(), companion, "Offer [npc.name] the opportunity to have sex with both you and [npc2.name] in order to avoid a violent confrontation."),
							true, true,
							new SMDoggy(
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.DOGGY_BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
											new Value<>(companion, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_OFFER_BODY_WITH_COMPANION", getDemon(), companion));
				}
				
			} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();
				
				if(!getDemon().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name]..."),
							null);
					
				} else if(!companion.isAttractedTo(getDemon()) && !companion.isSlave() && !(companion instanceof Elemental)) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and as [npc2.sheIs] not your slave, you can't force [npc2.herHim] to have sex..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getDemon(), companion, "Tell [npc.name] that [npc.she] can use [npc2.namePos] body in order to avoid a violent confrontation."),
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "ALLEY_ATTACK_OFFER_COMPANION", getDemon(), companion)) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getDemon()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
				}
				
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
			if((getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_ATTRACTION", getAllCharacters());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if((getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("Continue", "Decide against having sex with [npc.name] and continue on your way...", Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
								Main.game.banishNPC(getDemon());
							}
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_SEX", getAllCharacters()));
					
				} else if (index == 3) {
					return new ResponseSex("Gentle sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}
							},
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()));
					
				} else if (index == 4) {
					return new ResponseSex("Rough sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()));
					
				} else if (index == 5) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now... Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(getDemon(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 7 && getDemon().getLocationPlace().getPlaceType().getEncounterType()!=Encounter.DOMINION_STREET) {
					if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
						return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
						
					} else {
						return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
							@Override
							public void effects() {
								getDemon().setPlayerKnowsName(true);
								Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 10));
							}
						};
					}
					
				} else if (index == 8 && getDemon().isAbleToSelfTransform()) {
					return new Response("Transform [npc.herHim]",
							"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(getDemon());
						}
					};
					
				} else if (index == 9 && getDemon().isAbleToSelfTransform()) {
					return new Response("Quick transformations",
							"If all [npc.she] wants is sex, then you're more than happy to oblige. Besides, if [npc.sheIs] able to transform [npc.herself], you have a few ideas in mind..."
									+ "(You'll return to these options once finished transforming [npc.herHim].)",
							AFTER_COMBAT_TRANSFORMATIONS_FEMININITY);
					
				} else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_BANISH_NPC", getAllCharacters()));
							Main.game.banishNPC(getDemon());
						}
					};
					
				} else if (index == 11) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedTo(getDemon())) {
						return new Response(UtilText.parse(companion, "Threesome"), UtilText.parse(companion, getDemon(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Threesome"),
								UtilText.parse(getDemon(), companion, "Have dominant sex with [npc.name], and get [npc2.name] to join in with the fun."),
								true, false,
								new SMDoggy(
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND),
												new Value<>(companion, SexPositionSlot.DOGGY_INFRONT)),
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_THREESOME", getDemon(), companion));
					}
					
				} else if (index == 12) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedTo(getDemon())) {
						return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getDemon(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
								UtilText.parse(companion, getDemon(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] while you watch."),
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getDemon(), companion));
					}
					
				} else if (index == 13 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedTo(getDemon()) && !companion.isSlave() && !(companion instanceof Elemental)) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and as [npc2.sheIs] not your slave, you can't force [npc2.herHim] to have sex..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "Tell [npc.name] that [npc.she] can use [npc2.name]."),
								true, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getDemon(), companion)) {
							@Override
							public void effects() {
								if(!companion.isAttractedTo(getDemon()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
								Main.game.banishNPC(getDemon());
							}
						}
					};
					
				} else if (index == 2) {
					if(!Main.game.isNonConEnabled()) {
						return new Response("Sex", "[npc.Name] has no interest in having sex with you!", null);
					}
					return new ResponseSex(
							"Rape [npc.herHim]", "[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_RAPE", getAllCharacters()));
					
				} else if (index == 3) {
					if(!Main.game.isNonConEnabled()) {
						return new Response("Gentle Sex", "[npc.Name] has no interest in having sex with you!", null);
					}
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_SUBMISSIVE))) {
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
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getAllCharacters()));
					
				} else if (index == 4) {
					if(!Main.game.isNonConEnabled()) {
						return new Response("Rough Sex", "[npc.Name] has no interest in having sex with you!", null);
					}
					return new ResponseSex("Rape [npc.herHim] (rough)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getAllCharacters()));
					
				} else if (index == 5) {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.she] has no interest in having sex with you!",
							null);
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(getDemon(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 7) {
					if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
						return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
						
					} else {
						return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
							@Override
							public void effects() {
								getDemon().setPlayerKnowsName(true);
								Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 10));
							}
						};
					}
					
				} else if (index == 8 && getDemon().isAbleToSelfTransform()) {
					return new Response("Transform [npc.herHim]",
							"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(getDemon());
						}
					};
					
				} else if (index == 9 && getDemon().isAbleToSelfTransform()) {
					return new Response("Quick transformations",
							"If all [npc.she] wants is sex, then you're more than happy to oblige. Besides, if [npc.sheIs] able to transform [npc.herself], you have a few ideas in mind..."
									+ "(You'll return to these options once finished transforming [npc.herHim].)",
							AFTER_COMBAT_TRANSFORMATIONS_FEMININITY);
					
				} else if (index == 10 && !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_BANISH_NPC", getAllCharacters()));
							Main.game.banishNPC(getDemon());
						}
					};
					
				} else if (index == 11) {
					GameCharacter companion = getMainCompanion();
					
					if(!Main.game.isNonConEnabled()) {
						return new Response("Threesome", UtilText.parse(companion, getDemon(), "[npc2.Name] has no interest in having sex with you or [npc.name]!"), null);
					}

					if(!companion.isAttractedTo(getDemon())) {
						return new Response(UtilText.parse(companion, "Threesome"), UtilText.parse(companion, getDemon(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Threesome"),
								UtilText.parse(getDemon(), companion, "Have dominant sex with [npc.name], and get [npc2.name] to join in with the fun."),
								true, false,
								new SMDoggy(
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND),
												new Value<>(companion, SexPositionSlot.DOGGY_INFRONT)),
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_THREESOME", getDemon(), companion));
					}
					
				} else if (index == 12) {
					GameCharacter companion = getMainCompanion();

					if(!Main.game.isNonConEnabled()) {
						return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getDemon(), "[npc2.Name] has no interest in having sex with [npc.name]!"), null);
					}
					
					if(!companion.isAttractedTo(getDemon())) {
						return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getDemon(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
								UtilText.parse(companion, getDemon(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] while you watch."),
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getDemon(), companion));
					}
					
				} else if (index == 13 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();

					if(!Main.game.isNonConEnabled()) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"), UtilText.parse(companion, getDemon(), "[npc2.Name] has no interest in having sex with [npc.name]!"), null);
						
					} else if(!companion.isAttractedTo(getDemon()) && !companion.isSlave() && !(companion instanceof Elemental)) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and as [npc2.sheIs] not your slave, you can't force [npc2.herHim] to have sex..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "Tell [npc.name] that [npc.she] can use [npc2.name]."),
								true, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getDemon(), companion)) {
							@Override
							public void effects() {
								if(!companion.isAttractedTo(getDemon()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY_TALK = new DialogueNodeOld("Talk", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_VICTORY_TALK", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue",
						"Let [npc.name] go.",
						Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATIONS_FEMININITY = new DialogueNodeOld("Transformations", "", true, true) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			String femaleName = getDemon().getSubspecies().getSingularFemaleName(getDemon());
			String maleName = getDemon().getSubspecies().getSingularMaleName(getDemon());
			
			if (index == 1) {
				return new Response("[style.colourMasculineStrong(Very masculine)]",
						"Tell [npc.name] that you want [npc.herHim] to shift [npc.her] femininity so that [npc.sheIs] "+UtilText.generateSingularDeterminer(maleName)+" "+maleName+".",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY_VERY_MASCULINE", getDemon()));
						Main.game.getActiveNPC().setFemininity(10);
					}
				};
				
			} else if (index == 2) {
				return new Response("[style.colourMasculine(Masculine)]",
						"Tell [npc.name] that you want [npc.herHim] to shift [npc.her] femininity so that [npc.sheIs] "+UtilText.generateSingularDeterminer(maleName)+" "+maleName+".",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY_MASCULINE", getDemon()));
						Main.game.getActiveNPC().setFemininity(30);
					}
				};
				
			} else if (index == 3) {
				return new Response("[style.colourAndrogynous(Androgynous)]",
						"Tell [npc.name] that you want [npc.herHim] to shift [npc.her] femininity so that [npc.sheIs] "+UtilText.generateSingularDeterminer(femaleName)+" "+femaleName+".",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY_ANDROGYNOUS", getDemon()));
						Main.game.getActiveNPC().setFemininity(50);
					}
				};
				
			} else if (index == 4) {
				return new Response("[style.colourFeminine(Feminine)]",
						"Tell [npc.name] that you want [npc.herHim] to shift [npc.her] femininity so that [npc.sheIs] "+UtilText.generateSingularDeterminer(femaleName)+" "+femaleName+".",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY_FEMININE", getDemon()));
						Main.game.getActiveNPC().setFemininity(70);
					}
				};
				
			} else if (index == 5) {
				return new Response("[style.colourFeminineStrong(Very feminine)]",
						"Tell [npc.name] that you want [npc.herHim] to shift [npc.her] femininity so that [npc.sheIs] "+UtilText.generateSingularDeterminer(femaleName)+" "+femaleName+".",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY_VERY_FEMININE", getDemon()));
						Main.game.getActiveNPC().setFemininity(90);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATIONS_PENIS = new DialogueNodeOld("Transformations", "", true, true) {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("No cock",
						"Tell the [npc.race] that you don't want [npc.herHim] to have a cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(PenisType.NONE);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS_NONE", getDemon()));
					}
				};
				
			} else if (index == 2) {
				return new Response("Tiny cock",
						"Tell the [npc.race] that you want [npc.herHim] to have a tiny little 1-inch cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(RacialBody.valueOfRace(getDemon().getBody().getRaceFromPartWeighting()).getPenisType());
						Main.game.getActiveNPC().setPenisGirth(PenisGirth.ONE_THIN);
						Main.game.getActiveNPC().setPenisSize(1);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.ONE_TRICKLE.getMaximumValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.ONE_TRICKLE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS_TINY", getDemon()));
					}
				};
				
			} else if (index == 3) {
				return new Response("Average-sized cock",
						"Tell the [npc.race] that you want [npc.herHim] to have an average, 6-inch, cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(RacialBody.valueOfRace(getDemon().getBody().getRaceFromPartWeighting()).getPenisType());
						Main.game.getActiveNPC().setPenisGirth(PenisGirth.TWO_AVERAGE);
						Main.game.getActiveNPC().setPenisSize(6);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.THREE_AVERAGE.getMaximumValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.THREE_AVERAGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS_AVERAGE", getDemon()));
					}
				};
				
			} if (index == 4) {
				return new Response("Large cock",
						"Tell the [npc.race] that you want [npc.herHim] to have a large, 11-inch cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(RacialBody.valueOfRace(getDemon().getBody().getRaceFromPartWeighting()).getPenisType());
						Main.game.getActiveNPC().setPenisGirth(PenisGirth.THREE_THICK);
						Main.game.getActiveNPC().setPenisSize(11);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS_LARGE", getDemon()));
					}
				};
				
			} if (index == 5) {
				return new Response("Enormous cock",
						"Tell the [npc.race] that you want [npc.herHim] to have a massive 19-inch cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(RacialBody.valueOfRace(getDemon().getBody().getRaceFromPartWeighting()).getPenisType());
						Main.game.getActiveNPC().setPenisGirth(PenisGirth.FOUR_FAT);
						Main.game.getActiveNPC().setPenisSize(PenisSize.FIVE_ENORMOUS.getMaximumValue());
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS_HUGE", getDemon()));
					}
				};
				
			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATIONS_VAGINA = new DialogueNodeOld("Transformations", "", true, true) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_VAGINA", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Vagina",
						"Tell the [npc.race] that you want [npc.herHim] to have a pussy.",
						AFTER_COMBAT_TRANSFORMATIONS_BREASTS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setVaginaType(RacialBody.valueOfRace(getDemon().getBody().getRaceFromPartWeighting()).getVaginaType());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_VAGINA_ENABLED", getDemon()));
					}
				};
				
			} else if (index == 2) {
				return new Response("No Vagina",
						"Tell the [npc.race] that [npc.she] won't be needing a pussy.",
						AFTER_COMBAT_TRANSFORMATIONS_BREASTS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setVaginaType(VaginaType.NONE);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_VAGINA_DISABLED", getDemon()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATIONS_BREASTS = new DialogueNodeOld("Transformations", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Flat chest",
						"Tell [npc.name] that [npc.she] should have no breasts.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_FLAT", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.FLAT.getMeasurement());
					}
				};
				
			} else if (index == 2) {
				return new Response("AA-cup",
						"Tell [npc.name] to make [npc.her] breasts tiny little AA-cups.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_AA", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.AA.getMeasurement());
					}
				};
				
			} else if (index == 3) {
				return new Response("C-cup",
						"Tell [npc.name] to make [npc.her] breasts C-cups.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_C", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.C.getMeasurement());
					}
				};
				
			} else if (index == 4) {
				return new Response("E-cup",
						"Tell [npc.name] to make [npc.her] breasts E-cups.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_E", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.E.getMeasurement());
					}
				};
				
			} else if (index == 5) {
				return new Response("E-cup",
						"Tell [npc.name] to make [npc.her] breasts H-cups.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_E", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.H.getMeasurement());
					}
				};
				
			} else if (index == 6) {
				return new Response("N-cup",
						"Tell [npc.name] to make [npc.her] breasts E-cups.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_N", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.N.getMeasurement());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATIONS_FINISHED = new DialogueNodeOld("Transformations", "", true, true) {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {

			if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_DEFEAT_BETRAYED", getAllCharacters());
			}
			
			if(getDemon().isWillingToRape(Main.game.getPlayer())
					&& ((getDemon().isAttractedTo(Main.game.getPlayer()) && getDemon().isAttractedTo(getMainCompanion()))
							|| (getDemon().isAttractedTo(getMainCompanion()) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)))) {

				GameCharacter companion = getMainCompanion();
				
				if (getDemon().isAttractedTo(Main.game.getPlayer()) && getDemon().isAttractedTo(companion)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_DEFEAT_NO_TF_ATTRACTED", getAllCharacters());
				
				} else if (getDemon().isAttractedTo(companion) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_DEFEAT_WANTS_COMPANION", getAllCharacters());
					
				}
			}
			
			if(getDemon().isAttractedTo(Main.game.getPlayer()) && getDemon().isWillingToRape(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_DEFEAT_NO_TF_ATTRACTED", getAllCharacters());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_DEFEAT_NO_TF_NOT_ATTRACTED", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
				}
				return null;
			}
			
			if(getDemon().isWillingToRape(Main.game.getPlayer())
					&& ((getDemon().isAttractedTo(Main.game.getPlayer()) && getDemon().isAttractedTo(getMainCompanion()))
							|| (getDemon().isAttractedTo(getMainCompanion()) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)))) {
				
				GameCharacter companion = getMainCompanion();

				if (index == 1) {
					if (getDemon().isAttractedTo(Main.game.getPlayer()) && getDemon().isAttractedTo(companion)) {
							return new ResponseSex(UtilText.parse(companion, "Threesome"),
									UtilText.parse(getDemon(), companion, "[npc.Name] uses this opportunity to have sex with both you and [npc2.name]..."),
									false, false,
									new SMDoggy(
											Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.DOGGY_BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
													new Value<>(companion, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
									null,
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_DEFEAT_THREESOME", getDemon(), companion));
						
					} else if (getDemon().isAttractedTo(companion) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {

						return new ResponseSex(UtilText.parse(companion, "[npc.Name] used"),
								UtilText.parse(getDemon(), companion, "[npc.Name] uses [npc2.namePos] body in order to get some sexual relief..."),
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_DEFEAT_USES_COMPANION", getDemon(), companion));
						
					}
					
				} else {
					return null;
				}
				
			} else if(getDemon().isAttractedTo(Main.game.getPlayer()) && getDemon().isWillingToRape(Main.game.getPlayer())) {
				
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getDemon(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()));
					
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
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if((getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_SEX_VICTORY", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_SEX_VICTORY_NO_ORGASM", getAllCharacters());
				}
				
			} else {
				if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_SEX_VICTORY_RAPE_BETRAYED", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_SEX_VICTORY_RAPE", getAllCharacters());
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getDemon());
						}
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getDemon(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
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
						Main.game.banishNPC(getDemon());
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
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttackCompanions", "AFTER_SEX_DEFEAT", getAllCharacters());
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
