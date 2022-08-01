package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueManager;
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
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.FetishPotion;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;

/**
 * @since 0.2.11
 * @version 0.3.5.5
 * @author Innoxia
 */
public class TunnelAttackDialogue {

	private static boolean transformationsApplied = false;
	
	private static boolean isWantsToFight() {
		return getMugger().getAffectionLevel(Main.game.getPlayer()).isWillFightPlayer();
	}

	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static String getDialogueId() {
		if(isCompanionDialogue()) {
			return "tunnelAttackCompanions";
		}
		return "tunnelAttack";
	}

	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static NPC getMugger() {
		return Main.game.getActiveNPC();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		List<GameCharacter> allCharacters = new ArrayList<>();
		allCharacters.add(getMugger());
		allCharacters.addAll(Main.game.getPlayer().getCompanions());
		Collections.sort(allCharacters, (c1, c2) -> c1.isElemental()?(c2.isElemental()?0:1):(c2.isElemental()?-1:0));
		return allCharacters;
	}
	
	private static void applyPregnancyReactions() {
		if(getMugger().isVisiblyPregnant()){
			getMugger().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getMugger(), true);
		}
		if(isCompanionDialogue() && getMainCompanion().isVisiblyPregnant()) {
			getMainCompanion().setCharacterReactedToPregnancy(getMugger(), true);
		}
	}
	
	private static String getStatus() {
		return AffectionLevel.getAttitudeDescription(getMugger(), Main.game.getPlayer(), true);
	}

	public static final DialogueNode TUNNEL_ATTACK = new DialogueNode("Assaulted!", "A figure jumps out from the shadows!", true) {
		@Override
		public void applyPreParsingEffects() {
			getMugger().generatePostCombatPotions();
			transformationsApplied = false;
			Main.game.getDialogueFlags().setFlag("innoxia_alleyway_transformations_applied", false);
			
			if(getMugger().getPlayerSurrenderCount()>=4) { 
				if(getMugger().hasStatusEffect(StatusEffect.WEATHER_STORM_VULNERABLE)) {
					Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 4);
				} else {
					Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", Util.random.nextInt(6)+1);
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==1 && Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand3()) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 2);
					}
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==6
							&& ((Main.game.getPlayer().getTattooInSlot(InventorySlot.GROIN)!=null || !Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
										&& (!Main.game.isAnalContentEnabled() || Main.game.getPlayer().getTattooInSlot(InventorySlot.TORSO_UNDER)!=null || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)))) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 4);
					}
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==4 && (!getMugger().isAttractedTo(Main.game.getPlayer()) || getMugger().hasStatusEffect(StatusEffect.RECOVERING_AURA))) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 5);
					}
				}
			}
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean pregnancyReaction = false;
			
			if(getMugger().getLastTimeEncountered() != -1) {
				if(isWantsToFight()) {
					if(getMugger().getPlayerSurrenderCount()>=4) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/tunnelAttack", "TUNNEL_ATTACK_SUBMITTED", getAllCharacters()));
						
					} else if(getMugger().getPlayerSurrenderCount()==3) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/tunnelAttack", "TUNNEL_ATTACK_DEMAND_SUBMIT", getAllCharacters()));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_REPEAT_INTRO", getAllCharacters()));
						
						if(getMugger().isVisiblyPregnant()) {
							pregnancyReaction = true;
							
							if(!getMugger().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_REPEAT_PREGNANCY_REVEAL", getAllCharacters()));
							
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_REPEAT_STILL_PREGNANT", getAllCharacters()));
							}
						}
						
						if(Main.game.getPlayer().isVisiblyPregnant() || (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant())) {
							pregnancyReaction = true;
							
							if((Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(getMugger()))
									|| (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant() && !getMainCompanion().isCharacterReactedToPregnancy(getMugger()))) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_REPEAT_PLAYER_PREGNANCY", getAllCharacters()));
							
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_REPEAT_CONTINUED_PLAYER_PREGNANCY", getAllCharacters()));
							}
						}
	
						if(!pregnancyReaction) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_REPEAT", getAllCharacters()));
						}
						
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_REPEAT_END", getAllCharacters()));
					}
					
				} else { // The mugger doesn't want to attack the player:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL_INTRO", getAllCharacters()));
					
					if(getMugger().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!getMugger().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL_PREGNANCY_REVEAL", getAllCharacters()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL_STILL_PREGNANT", getAllCharacters()));
						}
					}
					
					if(Main.game.getPlayer().isVisiblyPregnant() || (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant())) {
						pregnancyReaction = true;
						
						if((Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(getMugger()))
								|| (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant() && !getMainCompanion().isCharacterReactedToPregnancy(getMugger()))) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL_PLAYER_PREGNANCY", getAllCharacters()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL_CONTINUED_PLAYER_PREGNANCY", getAllCharacters()));
						}
					}
					
					if(!pregnancyReaction) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL", getAllCharacters()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL_END", getAllCharacters()));

					UtilText.nodeContentSB.append(getStatus());
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_INTRO", getAllCharacters()));
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getMugger().getPlayerSurrenderCount()>=3) { // Bitch content
				return DialogueManager.getDialogueFromId("innoxia_encounters_submission_tunnels_start").getResponse(responseTab, index);
			}
			
			if(isWantsToFight()) {
				if (index == 1) {
					return new ResponseCombat("Fight", "Stand up for yourself and fight [npc.name]!", getMugger()) {
						@Override
						public void effects() {
							applyPregnancyReactions();
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand2()) {
						return new Response("Offer money ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand2(), "span")+")",
								"You don't have enough money to offer to pay [npc.name] off. You'll have to either fight [npc.herHim] or offer [npc.herHim] your body!", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand2(), "span")+")",
								"Offer to pay [npc.name] "+Util.intToString(Main.game.getDialogueFlags().getMuggerDemand2())+" flames to leave you alone.", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().getMuggerDemand2());
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PAY_OFF", getAllCharacters()));
							}
						};
					}
					
				} else if (index == 3) {
					if(getMugger().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Offer body",
								"Offer your body to [npc.name] so that you can avoid a violent confrontation."
										+"<br/>[style.italicsSex(Repeatedly submitting to [npc.name] will eventually lead to [npc.herHim] demanding that you become [npc.her] bitch...)]",
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
								UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), isCompanionDialogue()?"TUNNEL_ATTACK_OFFER_BODY_SOLO_WITH_COMPANION":"TUNNEL_ATTACK_OFFER_BODY", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								getMugger().incrementPlayerSurrenderCount(1);
							}
						};
						
					} else {
						return new Response("Offer body", "You can tell that [npc.name] isn't at all interested in having sex with you. You'll either have to offer [npc.herHim] some money, or prepare for a fight!", null);
					}
					
				} else if (index == 4 && getMugger().isApplyingPostCombatTransformations()) {
					return new Response("Surrender",
							"Completely surrender to [npc.name] and let [npc.herHim] do whatever [npc.she] wants with your body..."
									+"<br/>[style.italicsTfGeneric(This will result in [npc.name] trying to get you to drink a transformation potion, before possibly choosing to fuck you!)]."
									+"<br/>[style.italicsSex(Repeatedly submitting to [npc.name] will eventually lead to [npc.herHim] demanding that you become [npc.her] bitch...)]",
								SURRENDER,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_TRANSFORMATION_RECEIVING), Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							applyPregnancyReactions();
							getMugger().incrementPlayerSurrenderCount(1);
						}
					};
					
				} else if (index == 6 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();
	
					if(!getMugger().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with you, so wouldn't want to have a threesome..."),
								null);
						
					} else if(!getMugger().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name], so wouldn't want to have a threesome..."),
								null);
						
					} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "Offer [npc.name] the opportunity to have sex with both you and [npc2.name] in order to avoid a violent confrontation."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getMugger()),
										Util.newArrayListOfValues(Main.game.getPlayer(), companion),
										null,
										null,
										ResponseTag.PREFER_DOGGY),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_OFFER_BODY_WITH_COMPANION", getMugger(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 7 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getMugger().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name]..."),
								null);
						
					} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.namePos] body in order to avoid a violent confrontation."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getMugger()),
										Util.newArrayListOfValues(companion),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())),
								AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_OFFER_COMPANION", getMugger(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
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
					return new Response("Talk", "Talk to [npc.name] for a while in order to get to know [npc.herHim] a little better.", TUNNEL_PEACEFUL_TALK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getMugger().incrementAffection(Main.game.getPlayer(), 10));
							
							if(getMugger().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand2()) {
						return new Response("Offer money ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand2(), "span")+")",
								"You don't have enough money to offer [npc.name] any.", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand2(), "span")+")",
								"Offer [npc.name] some money to help [npc.herHim] buy food and clothing.", TUNNEL_PEACEFUL_OFFER_MONEY) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().getMuggerDemand2()));
								Main.game.getTextEndStringBuilder().append(getMugger().incrementAffection(Main.game.getPlayer(), 10));

								if(getMugger().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
								}
							}
						};
					}
					
				} else if (index == 3) {
					if(getMugger().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (dom)", "Take the dominant role and have sex with [npc.name].",
								Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getMugger()),
										Main.game.getPlayer().getCompanions(),
										null),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL_SEX_AS_DOM", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (dom)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} else if (index == 4) {
					if(getMugger().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (sub)", "Offer your body to [npc.name].",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getMugger()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Main.game.getPlayer().getCompanions()),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL_SEX_AS_SUB", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (sub)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} if (index == 5) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !getMugger().isAffectionHighEnoughToInviteHome()) {
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
						return new Response("Offer room", "Ask [npc.name] if [npc.she] would like a room in Lilaya's mansion.", TUNNEL_PEACEFUL_OFFER_ROOM) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(getMugger().incrementAffection(Main.game.getPlayer(), 25));
							}
						};
					}
					
				} else if(index==6) {
					if(getMugger().getPlayerSurrenderCount()<3 && getMugger().isApplyingPostCombatTransformations()) {
						if(transformationsApplied) {
							return new Response("Get transformed",
									"[npc.Name] has already given you all the transformation potions [npc.she] had!",
									null);
							
						} else {
							return new Response("Get transformed",
									"Tell [npc.name] that you'd like to drink any transformation potions which [npc.she] has..."
										+"<br/>[style.italicsTfGeneric(This will result in [npc.name] getting you to drink a transformation potion!)]",
										TUNNEL_PEACEFUL_TRANSFORMED,
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_TRANSFORMATION_RECEIVING), Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(), null, null, null) {
								@Override
								public Colour getHighlightColour() {
									return PresetColour.TRANSFORMATION_GENERIC;
								}
								@Override
								public void effects() {
									applyPregnancyReactions();
									Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "PEACEFUL_TRANSFORMATIONS", getAllCharacters()));
									Main.game.appendToTextStartStringBuilder(getMugger().applyPostCombatTransformation());
									transformationsApplied = true;
								}
							};
						}
					}
					
				} else if (index==10) {
					return new Response("Attack", "Betray [npc.namePos] trust and attack [npc.herHim]!", TUNNEL_PEACEFUL_ATTACK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getMugger().incrementAffection(Main.game.getPlayer(), -50));
							getMugger().addFlag(NPCFlagValue.genericNPCBetrayedByPlayer);
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if (index == 11 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();
	
					if(!getMugger().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with you, so wouldn't want to have a threesome..."),
								null);
						
					} else if(!getMugger().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name], so wouldn't want to have a threesome..."),
								null);
						
					} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "Offer [npc.name] the opportunity to have sex with both you and [npc2.name]."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getMugger()),
										Util.newArrayListOfValues(Main.game.getPlayer(), companion),
										null,
										null,
										ResponseTag.PREFER_DOGGY),
								AFTER_SEX_PEACEFUL_THREESOME, UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL_OFFER_BODY_WITH_COMPANION", getMugger(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 12 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getMugger().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name]..."),
								null);
						
					} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.namePos] body."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getMugger()),
										Util.newArrayListOfValues(companion),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())),
								AFTER_SEX_PEACEFUL_OFFERED_COMPANION, UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_ATTACK_PEACEFUL_OFFER_COMPANION", getMugger(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you're in a rush to be somewhere else, before continuing on your way.", Main.game.getDefaultDialogue(false));
				}
				
				return null;
			}
		}
	};
	
	public static final DialogueNode TUNNEL_PEACEFUL_TALK = new DialogueNode("Talk", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_PEACEFUL_TALK", getAllCharacters()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(getMugger().isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_PEACEFUL_CAN_INVITE_HOME", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] go.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode TUNNEL_PEACEFUL_OFFER_MONEY = new DialogueNode("Offer money", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_PEACEFUL_OFFER_MONEY", getAllCharacters()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(getMugger().isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_PEACEFUL_CAN_INVITE_HOME", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] go and buy food.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode TUNNEL_PEACEFUL_OFFER_ROOM = new DialogueNode("Offer room", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_PEACEFUL_OFFER_ROOM", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take home", "Take [npc.name] to [npc.her] new room.", TUNNEL_PEACEFUL_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						getMugger().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(getMugger());
						Main.game.getTextEndStringBuilder().append(getMugger().incrementAffection(Main.game.getPlayer(), 50));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode TUNNEL_PEACEFUL_OFFER_ROOM_BACK_HOME = new DialogueNode("New Room", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_PEACEFUL_OFFER_ROOM_BACK_HOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] get settled in.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode TUNNEL_PEACEFUL_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TUNNEL_ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TUNNEL_PEACEFUL_ATTACK = new DialogueNode("Attack", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TUNNEL_PEACEFUL_ATTACK", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Start fighting [npc.name]!", getMugger());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL = new DialogueNode("Continue", "Step away from [npc.name] and prepare to continue on your way.", true) {
		
		@Override
		public String getContent() {
			if(getMugger().isSatisfiedFromLastSex()) {
				return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_SEX_PEACEFUL", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_SEX_PEACEFUL_NO_ORGASM", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL_THREESOME = new DialogueNode("Continue", "Step away from [npc.name] and prepare to continue on your way.", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_SEX_PEACEFUL_THREESOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL_OFFERED_COMPANION = new DialogueNode("Continue", "Step away from [npc.name] and prepare to continue on your way.", true) {
		
		@Override
		public String getContent() {
			if(getMainCompanion().isAttractedTo(getMugger())) {
				return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_SEX_PEACEFUL_OFFERED_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_SEX_PEACEFUL_OFFERED_COMPANION_RELUCTANT", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMugger().setPlayerSurrenderCount(0);
			getMugger().clearPetName(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() {
			if(getMugger().isAttractedTo(Main.game.getPlayer())
					&& !getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_ATTRACTION", getMugger());
				
			} else {
				if(getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_BETRAYED", getMugger());
				} else {
					return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getMugger());
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way...", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						if(getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getMugger());
						}
					}
				};
				
			} else if (index == 2) {
				if(!getMugger().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getMugger().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getMugger()),
									Main.game.getPlayer().getCompanions(),
									null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_SEX", getAllCharacters()));
				} else {
					return new ResponseSex(
							"Rape [npc.herHim]",
							"[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getMugger()),
									Main.game.getPlayer().getCompanions(),
									null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_RAPE", getAllCharacters()));
				}
				
			} else if (index == 3) {
				if(!getMugger().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Gentle Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getMugger().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
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
							UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()));
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (gentle)",
							"[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getMugger()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getAllCharacters()));
				}
				
			} else if (index == 4) {
				if(!getMugger().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Rough Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getMugger().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
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
							UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()));
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (rough)",
							"[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getMugger()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getAllCharacters()));
				}
				
			} else if (index == 5) {
				if(!getMugger().isAttractedTo(Main.game.getPlayer())) {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.sheHasFull] no interest in having sex with you!",
							null);
				} else {
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
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 7) {
				if(getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
					
				} else {
					return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
						@Override
						public void effects() {
							getMugger().setPlayerKnowsName(true);
							Main.game.getTextEndStringBuilder().append(getMugger().setAffection(Main.game.getPlayer(), 10));
						}
					};
				}
				
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
			
			} else if (index == 10 && !getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"Remove character",
						UtilText.parse(getMugger(), "Scare [npc.name] away."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_BANISH_NPC", getAllCharacters()));
						Main.game.banishNPC(getMugger());
					}
				};
				
			} else if (index == 11 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!Main.game.isNonConEnabled() && (!getMugger().isAttractedTo(Main.game.getPlayer()) || !getMugger().isAttractedTo(companion))) {
					return new Response("Threesome", UtilText.parse(companion, getMugger(), "[npc2.Name] has no interest in having sex with you or [npc.name]!"), null);
					
				} else if(!companion.isAttractedTo(getMugger())) {
					return new Response(UtilText.parse(companion, "Threesome"), UtilText.parse(companion, getMugger(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Threesome"),
							UtilText.parse(getMugger(), companion, "Have dominant sex with [npc.name], and get [npc2.name] to join in with the fun."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getMugger()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_THREESOME", getMugger(), companion));
				}
				
			} else if (index == 12 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !getMugger().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getMugger(), "[npc2.Name] isn't attracted to [npc.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
					
				} else if(!companion.isAttractedTo(getMugger())) {
					return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getMugger(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
							UtilText.parse(companion, getMugger(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] while you watch."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getMugger()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getMugger(), companion));
				}
				
			} else if (index == 13 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !companion.isAttractedTo(getMugger())) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"), UtilText.parse(companion, getMugger(), "[npc.Name] has no interest in having sex with [npc2.name]!"), null);
					
				} else if(!getMugger().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"), UtilText.parse(companion, getMugger(), "[npc2.Name] has no interest in having sex with [npc.name]!"), null);
					
				} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMugger(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.name]."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getMugger(), companion)) {
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
	
	public static final DialogueNode AFTER_COMBAT_VICTORY_TALK = new DialogueNode("Talk", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_VICTORY_TALK", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue",
						"Let [npc.name] go.",
						Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};

	private static String applyTransformation(GameCharacter target,
			TransformativePotion potion,
			boolean forcedTF,
			FetishPotion fetishPotion,
			boolean forcedFetish) {
		
		StringBuilder sb = new StringBuilder();
		
		if(potion!=null && forcedTF) {
			sb.append(UtilText.parse(getMugger(), target,
					"<p>"
						+ "[npc.Name] steps back, grinning down at [npc2.name] as [npc2.she] obediently [npc2.verb(swallow)] the strange liquid."
						+ " [npc.speech(Good [npc2.girl]! I'm going to turn you into my perfect "+getMugger().getPreferredBodyDescription("b")+"!)]"
					+ "</p>"));
			sb.append(getMugger().applyPotion(potion, target));
		}
		
		if(fetishPotion!=null && forcedFetish) {
			sb.append(UtilText.parse(getMugger(),
					"<p>"
						+ "With a look of fiendish delight in [npc.her] [npc.eyes], [npc.name] excitedly cries out,"
						+ " [npc.speech(That's right, swallow it all down! These changes are all for the better!)]"
					+ "</p>"));
			sb.append(getMugger().applyPotion(fetishPotion, target));
		}
		return sb.toString();
	}

	public static final DialogueNode SURRENDER = new DialogueNode("", "", true) {
		public void applyPreParsingEffects() {
			AFTER_COMBAT_DEFEAT.applyPreParsingEffects();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "SURRENDER", getAllCharacters());		
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_COMBAT_DEFEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {

		TransformativePotion potion = null;
		TransformativePotion companionPotion = null;
		FetishPotion fetishPotion = null;
		FetishPotion companionFetishPotion = null;
		
		public void applyPreParsingEffects() {
			transformationsApplied = false;
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getMugger().generateTransformativePotion(Main.game.getPlayer());
				fetishPotion = getMugger().generateFetishPotion(Main.game.getPlayer(), true);
			} else {
				potion = null;
				fetishPotion = null;
			}
			if(isCompanionDialogue()) {
				if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					companionPotion = getMugger().generateTransformativePotion(getMainCompanion());
					companionFetishPotion = getMugger().generateFetishPotion(getMainCompanion(), true);
				} else {
					companionPotion = null;
					companionFetishPotion = null;
				}
			}
		}
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			if(getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_BETRAYED", getAllCharacters());
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_GENERIC_START", getAllCharacters()));
			
			boolean forcedTF = getMugger().isUsingForcedTransform(Main.game.getPlayer());
			boolean forcedFetish = getMugger().isUsingForcedFetish(Main.game.getPlayer());
			boolean companionForcedTF = isCompanionDialogue() && getMugger().isUsingForcedTransform(getMainCompanion());
			boolean companionForcedFetish = isCompanionDialogue() && getMugger().isUsingForcedFetish(getMainCompanion());
			if((forcedTF && potion!=null)
					|| (forcedFetish && fetishPotion!=null)
					|| (companionForcedTF && companionPotion!=null)
					|| (companionForcedFetish && companionFetishPotion!=null)) {
				if(((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null))
						&& ((companionForcedTF || companionPotion!=null) && (companionForcedFetish || companionFetishPotion!=null))) { // Both TF:
					
					if(fetishPotion!=null && forcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_TF", getAllCharacters()));
					}
	
					if(companionFetishPotion!=null && companionForcedFetish) {
						if(companionPotion!=null && companionForcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_COMPANION_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_COMPANION_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_COMPANION_TF", getAllCharacters()));
					}
					
					return sb.toString();
					
				} else if((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null)) { // Player TF:
					if(fetishPotion!=null && forcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_TF", getAllCharacters()));
					}
					return sb.toString();
					
				} else if(isCompanionDialogue()) { // Companion TF:
					if(companionFetishPotion!=null && companionForcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_TF", getAllCharacters()));
					}
					return sb.toString();
				}
			}
			
			// If no transformations are going to be applied, just return content (and responses) as though it's the AFTER_COMBAT_TRANSFORMATION node:
			sb.append(AFTER_COMBAT_TRANSFORMATION.getContent());
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.banishNPC(getMugger());
						}
					};
				}
				return null;
			}

			// Response variables:
			boolean forcedTF = getMugger().isUsingForcedTransform(Main.game.getPlayer());
			boolean forcedFetish = getMugger().isUsingForcedFetish(Main.game.getPlayer());
			List<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(
					forcedTF && potion!=null
						?Fetish.FETISH_TRANSFORMATION_RECEIVING
						:null,
					forcedFetish && fetishPotion!=null
						?Fetish.FETISH_KINK_RECEIVING
						:null);
			CorruptionLevel applicableCorruptionLevel = forcedFetish && fetishPotion!=null
					?Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel()
					:Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
			boolean multiplePotions = applicableFetishes.size()>1;
			
			// Companion's response variables:
			boolean companionForcedTF = isCompanionDialogue() && getMugger().isUsingForcedTransform(getMainCompanion());
			boolean companionForcedFetish = isCompanionDialogue() && getMugger().isUsingForcedFetish(getMainCompanion());
			boolean companionMultiplePotions = (companionForcedTF && companionPotion!=null) && (companionForcedFetish && companionFetishPotion!=null);
			
			// Swallow/spit responses:
			if((!forcedTF || potion==null)
					&& (!forcedFetish || fetishPotion==null)
					&& (!companionForcedTF || companionPotion==null)
					&& (!companionForcedFetish || companionFetishPotion==null)) {
				return AFTER_COMBAT_TRANSFORMATION.getResponse(responseTab, index);
				
			} else if(((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null))
						&& ((companionForcedTF || companionPotion!=null) && (companionForcedFetish || companionFetishPotion!=null))) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(!Collections.disjoint(Main.game.getPlayer().getFetishes(true), applicableFetishes)) {
						return new Response("Spit",
									"Due to your [style.boldFetish("+applicableFetishes.get(0).getName(Main.game.getPlayer())+")] fetish,"
										+ " you love "+applicableFetishes.get(0).getShortDescriptor(Main.game.getPlayer())+" so much that you can't bring yourself to spit out the transformative "+(multiplePotions?"potions":"potion")+"!",
								null);
					} else {
						return new Response("Spit", 
								UtilText.parse(getMainCompanion(),
										"Spit out the "+(multiplePotions?"potions":"potion")+"."
												+ " ([npc.Name] will likely choose to "
													+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()?"[style.boldTfGeneric(swallow)]":"[style.boldMinorBad(spit out)]")
													+" [npc.her] own "+(companionMultiplePotions?"potions":"potion")+"!)"),
								AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								transformationsApplied = true;
								if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_SPIT", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_COMPANION_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_BOTH_SPIT", getAllCharacters()));
								}
							}
						};
					}
					
				} else if (index == 2) {
					return new Response("Swallow",
							UtilText.parse(getMainCompanion(),
								"Swallow the "+(multiplePotions?"potions":"potion")+"."
										+ " ([npc.Name] will likely choose to "
											+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()?"[style.boldTfGeneric(swallow)]":"[style.boldMinorBad(spit out)]")
											+" [npc.her] own "+(companionMultiplePotions?"potions":"potion")+"!)"),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_SWALLOW", getAllCharacters()));
							Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
							
							if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_COMPANION_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_COMPANION_SPIT", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 6) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Spit (both)");
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit (both)",
								UtilText.parse(getMainCompanion(),
									"Due to your <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
										+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative "+(multiplePotions?"potions":"potion")+", nor to tell [npc.name] to do so!"),
								null);
						
					} else {
						return new Response("Spit (both)",
								UtilText.parse(getMainCompanion(),
										"Spit out the "+(multiplePotions?"potions":"potion")+", and tell [npc.name] to do the same."
										+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
												?" (However, as [npc.name] has the "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" fetish, [npc.sheIsFull] unlikely to listen to you!)"
												:"")),
								AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								transformationsApplied = true;
								if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_SPIT", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "ORDER_SPIT_REFUSED", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_COMPANION_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_SPIT", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "ORDER_SPIT", getAllCharacters()));
								}
							}
						};
						
					}
					
				} else if (index == 7) {
					return new Response("Swallow (both)",
							UtilText.parse(getMainCompanion(),
									"Swallow the "+(multiplePotions?"potions":"potion")+", and tell [npc.name] to do the same."
									+ (getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
											?" (However, as [npc.name] has a negative desire towards the "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" fetish, [npc.sheIsFull] unlikely to listen to you!)"
											:"")),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
								
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "ORDER_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "ORDER_SWALLOW_REFUSED", getAllCharacters()));
							}
						}
					};
				}
				
			} else if((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null)) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					};
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
								"Due to your <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative "+(multiplePotions?"potions":"potion")+"!",
								null);
					} else {
						return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects() {
								transformationsApplied = true;
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_SPIT", getAllCharacters()));
							}
						};
					}
					
				} else if (index == 2) {
					return new Response("Swallow",
							"Swallow the "+(multiplePotions?"potions":"potion")+".",
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_SWALLOW", getAllCharacters()));
							Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
						}
					};
					
				} else if (index == 6 && isCompanionDialogue()) {
					return new Response("Swallow (both)",
							UtilText.parse(getMainCompanion(),
									getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"[npc.Name] is not being forced to drink any transformative potions!"
										:"As [npc.namePos] mouth is blocked, [npc.she] cannot drink any transformative potions!"),
							null);
					
				}  else if (index == 7 && isCompanionDialogue()) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Spit (both)");
					}
					return new Response("Spit (both)",
							UtilText.parse(getMainCompanion(),
									getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"[npc.Name] is not being forced to drink any transformative potions!"
										:"As [npc.namePos] mouth is blocked, [npc.she] cannot drink any transformative potions!"),
							null);
				}
				
			} else if(isCompanionDialogue()) {
				if (index == 1) {
					return new Response("Spit",
							UtilText.parse(getMainCompanion(),
									Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"You are not being forced to drink any transformative potions!"
										:"As your mouth is blocked, you cannot drink any transformative potions!"),
							null);
					
				} else if (index == 2) {
					return new Response("Swallow",
							UtilText.parse(getMainCompanion(),
									Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"You are not being forced to drink any transformative potions!"
										:"As your mouth is blocked, you cannot drink any transformative potions!"),
							null);
					
				} else if (index == 6) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Order spit");
					}
					return new Response("Order spit",
							UtilText.parse(getMainCompanion(), "Tell [npc.name] to spit out the "+(companionMultiplePotions?"potions":"potion")+"!"
									+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
											?" (However, as [npc.name] has the "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" fetish, [npc.sheIsFull] unlikely to listen to you!)"
											:"")),
							AFTER_COMBAT_TRANSFORMATION) {
						@Override
						public void effects(){
							transformationsApplied = true;
							if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "ORDER_SPIT_REFUSED", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "TF_COMPANION_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "ORDER_SPIT", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 7) {
					return new Response("Order swallow",
							UtilText.parse(getMainCompanion(),
								"Tell [npc.name] to swallow the "+(companionMultiplePotions?"potions":"potion")+"."
								+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
										?" (However, as [npc.she] dislikes being transformed, [npc.sheIsFull] unlikely to listen to you!)"
										:"")),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "ORDER_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "ORDER_SWALLOW_REFUSED", getAllCharacters()));
							}
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION = new DialogueNode("Transformed", "", true) {
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				if(getMugger().isAttractedTo(Main.game.getPlayer())) {
					if(getMugger().isAttractedTo(getMainCompanion())) {
						if(getMugger().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "RAPE_BOTH", getAllCharacters());
						} else {
							return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "OFFER_SEX_BOTH", getAllCharacters());
						}
						
					} else {
						if(getMugger().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "RAPE_PLAYER_SOLO", getAllCharacters());
						} else {
							return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "OFFER_SEX_SOLO", getAllCharacters());
						}
					}
					
				} else if(getMugger().isAttractedTo(getMainCompanion()) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {
					if(getMugger().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "RAPE_COMPANION", getAllCharacters());
					} else {
						return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "OFFER_SEX_COMPANION", getAllCharacters());
					}
				}
				
			} else {
				if(getMugger().isAttractedTo(Main.game.getPlayer())) {
					if(getMugger().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "RAPE_PLAYER", getAllCharacters());
					} else {
						return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "OFFER_SEX", getAllCharacters());
					}
				}
			}

			if(transformationsApplied) {
				return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "NO_SEX_POST_TRANSFORM", getAllCharacters());
			}
			return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "NO_SEX", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				boolean companionHappyToHaveSex = getMainCompanion().isAttractedTo(getMugger()) || getMainCompanion().isAttractedTo(Main.game.getPlayer());
				boolean companionSex = getMugger().isAttractedTo(getMainCompanion()) && (companionHappyToHaveSex || getMugger().isWillingToRape());
				
				
				if(getMugger().isAttractedTo(Main.game.getPlayer())) {
					if(getMugger().isAttractedTo(getMainCompanion())) { // Threesome sex:
						if (index == 1) {
							return new ResponseSex("Sex",
									UtilText.parse(getMugger(),
											getMugger().isWillingToRape()
												?"[npc.Name] forces [npc.herself] on you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+"..."
												:"Tell [npc.name] that you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+" would like to have sex with [npc.herHim]."),
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
									UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX_THREESOME", getAllCharacters()));
							
						} else if (index == 2) {
							return new ResponseSex("Eager Sex",
									UtilText.parse(getMugger(),
											getMugger().isWillingToRape()
												?"[npc.Name] forces [npc.herself] on you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+"..."
												:"Tell [npc.name] that you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+" are more than happy to have sex with [npc.herHim]."),
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
									UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX_THREESOME", getAllCharacters()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("Resist Sex",
									UtilText.parse(getMugger(), "[npc.Name] forces [npc.herself] on you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+"..."),
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
									UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX_THREESOME_RESIST", getAllCharacters()));
							
						} else if (index == 4 && !getMugger().isWillingToRape()) {
							return new Response("Refuse",
									UtilText.parse(getMugger(), "Refuse to have sex with [npc.name] and continue on your way."),
									Main.game.getDefaultDialogue(false)) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "DEFEATED_REFUSE_THREESOME", getAllCharacters()));
								}
							};
						}
						return null;
						
					} else { // Solo sex with player:
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
									UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX_SOLO", getAllCharacters()));
							
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
									UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX_SOLO", getAllCharacters()));
							
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
									UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX_SOLO_RESIST", getAllCharacters()));
							
						} else if (index == 4 && !getMugger().isWillingToRape()) {
							return new Response("Refuse",
									UtilText.parse(getMugger(), "Refuse to have sex with [npc.name] and continue on your way."),
									Main.game.getDefaultDialogue(false)) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "DEFEATED_REFUSE_SEX_SOLO", getAllCharacters()));
								}
							};
						}
						return null;
					}
					
				} else if(getMugger().isAttractedTo(getMainCompanion())
						&& Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) { // Solo sex with companion:
					if(getMugger().isWillingToRape()) {
						if (index == 1) {
							return new ResponseSex("Watch rape",
									UtilText.parse(getMugger(), getMainCompanion(),
											"You can do nothing but watch as [npc.name] forces [npc.herself] on [npc2.name]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getMugger()),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX_SOLO_COMPANION_RAPE", getAllCharacters()));
						}
						
					} else if(companionHappyToHaveSex) {
						if (index == 1) {
							return new ResponseSex("Watch sex",
									UtilText.parse(getMugger(), getMainCompanion(),
											"You can do nothing but watch as [npc2.name] happily agrees to let [npc.name] fuck [npc2.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getMugger()),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX_SOLO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 1) {
						return new Response(
								UtilText.parse(getMainCompanion(), "[npc.Name] refuses"),
								UtilText.parse(getMugger(), getMainCompanion(), "It looks like [npc2.name] is going to refuse to have sex with [npc.name]."),
								Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "DEFEATED_REFUSE_SEX_SOLO_COMPANION", getAllCharacters()));
							}
						};
					}
				}
				
			} else {
				if(getMugger().isAttractedTo(Main.game.getPlayer())) { // Solo sex with player:
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
								UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX", getAllCharacters()));
						
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
								UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX", getAllCharacters()));
						
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
								UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "START_DEFEATED_SEX_RESIST", getAllCharacters()));
						
					} else if (index == 4 && !getMugger().isWillingToRape()) {
						return new Response("Refuse",
								UtilText.parse(getMugger(), "Refuse to have sex with [npc.name] and continue on your way."),
								Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "DEFEATED_REFUSE_SEX", getAllCharacters()));
							}
						};
					}
					return null;
				}
			}
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "DEFEATED_NO_SEX", getAllCharacters()));
					}
				};
				
			} else {
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
			if((getMugger().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if(Main.sex.getNumberOfOrgasms(getMugger()) >= getMugger().getOrgasmsBeforeSatisfied()) {
					return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_SEX_VICTORY", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_SEX_VICTORY_NO_ORGASM", getAllCharacters());
				}
				
			} else {
				if(getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_SEX_VICTORY_RAPE_BETRAYED", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_SEX_VICTORY_RAPE", getAllCharacters());
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						if(getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getMugger());
						}
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && !getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"Remove character",
						UtilText.parse(getMugger(), "Scare [npc.name] away."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
						AFTER_COMBAT_VICTORY){
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
						Main.game.banishNPC(getMugger());
					}
				};
				
			} else {
				return null;
			}
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
			return UtilText.parseFromXMLFile("encounters/submission/"+getDialogueId(), "AFTER_SEX_DEFEAT", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY) {
					@Override
					public void effects() {
						if(getMugger().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getMugger());
						}
					}
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
}
