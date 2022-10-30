package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

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
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.10
 * @version 0.3.7.4
 * @author Innoxia
 */
public class AlleywayDemonDialogue {

	private static boolean talked = false;
	private static boolean transformationsApplied = false;
	
	private static boolean isCanal() {
		AbstractPlaceType pt = getDemon().getLocationPlace().getPlaceType();
		return pt.equals(PlaceType.DOMINION_ALLEYS_CANAL_CROSSING)
				|| pt.equals(PlaceType.DOMINION_CANAL)
				|| pt.equals(PlaceType.DOMINION_CANAL_END);
	}
	
	private static boolean isWantsToFight() {
		return getDemon().getAffectionLevel(Main.game.getPlayer()).isWillFightPlayer();
	}

	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static NPC getDemon() {
		return Main.game.getActiveNPC();
	}
	
	private static void applyPregnancyReactions() {
		if(getDemon().isVisiblyPregnant()){
			getDemon().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getDemon(), true);
		}
		if(isCompanionDialogue() && getMainCompanion().isVisiblyPregnant()) {
			getMainCompanion().setCharacterReactedToPregnancy(getDemon(), true);
		}
	}
	
	private static String getStatus() {
		return AffectionLevel.getAttitudeDescription(getDemon(), Main.game.getPlayer(), true);
	}
	
	public static final DialogueNode DEMON_ATTACK = new DialogueNode("Assaulted!", "A figure jumps out from the shadows!", true) {
		@Override
		public void applyPreParsingEffects() {
			talked = false;
			getDemon().generatePostCombatPotions();
			transformationsApplied = false;
			Main.game.getDialogueFlags().setFlag("innoxia_alleyway_transformations_applied", false);
			
			if(getDemon().getPlayerSurrenderCount()>=4) { 
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) { // Even if immune, only give fuck option as others dno't make sense to trigger during a storm
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
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==4 && (!getDemon().isAttractedTo(Main.game.getPlayer()) || getDemon().hasStatusEffect(StatusEffect.RECOVERING_AURA))) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 5);
					}
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean pregnancyReaction = false;
			
			if(getDemon().getLastTimeEncountered() != -1) {
				if(isWantsToFight()) {
					if(getDemon().getPlayerSurrenderCount()>=4) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_SUBMITTED", getDemon()));
						
					} else if(getDemon().getPlayerSurrenderCount()==3) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_DEMAND_SUBMIT", getDemon()));
						
					} else {
						if(isCanal()) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_CANAL_REPEAT_INTRO", getDemon()));
							
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_INTRO", getDemon()));
						}
						
						if(getDemon().isVisiblyPregnant()) {
							pregnancyReaction = true;
							
							if(!getDemon().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_PREGNANCY_REVEAL", getDemon()));
							
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_STILL_PREGNANT", getDemon()));
							}
						}
						
						if(Main.game.getPlayer().isVisiblyPregnant() || (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant())) {
							pregnancyReaction = true;
							
							if((Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(getDemon()))
									|| (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant() && !getMainCompanion().isCharacterReactedToPregnancy(getDemon()))) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_PLAYER_PREGNANCY", getDemon()));
							
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_CONTINUED_PLAYER_PREGNANCY", getDemon()));
							}
						}
	
						if(!pregnancyReaction) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT", getDemon()));
						}
						
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_END", getDemon()));
					}
					
				} else { // The mugger doesn't want to attack the player:
					if(isCanal()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_CANAL_INTRO", getDemon()));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_INTRO", getDemon()));
					}
					
					if(getDemon().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!getDemon().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_PREGNANCY_REVEAL", getDemon()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_STILL_PREGNANT", getDemon()));
						}
					}
					
					if(Main.game.getPlayer().isVisiblyPregnant() || (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant())) {
						pregnancyReaction = true;
						
						if((Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(getDemon()))
								|| (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant() && !getMainCompanion().isCharacterReactedToPregnancy(getDemon()))) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_PLAYER_PREGNANCY", getDemon()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_CONTINUED_PLAYER_PREGNANCY", getDemon()));
						}
					}
					
					if(!pregnancyReaction) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL", getDemon()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_END", getDemon()));

					UtilText.nodeContentSB.append(getStatus());
				}
				
			} else {
				if(isCanal()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_CANAL_INTRO", getDemon()));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_INTRO", getDemon()));
				}

				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK", getDemon()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getDemon().getPlayerSurrenderCount()>=3) { // Bitch content
				return DialogueManager.getDialogueFromId("innoxia_encounters_dominion_alleyway_demon_start").getResponse(responseTab, index);
			}
			
			if(isWantsToFight()) {
				if (index == 1) {
					return new ResponseCombat("Fight", "Stand up for yourself and fight [npc.name]!", getDemon()) {
						@Override
						public void effects() {
							applyPregnancyReactions();
						}
					};
					
				} else if (index == 2) {
					return new Response("Offer money", UtilText.parse(getDemon(), "Unlike common muggers, [npc.Name] isn't interested in your money!"), null);
					
				} else if (index == 3) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Offer body",
								"Offer your body to [npc.name] so that you can avoid a violent confrontation."
									+"<br/>[style.italicsSex(Repeatedly submitting to [npc.name] will eventually lead to [npc.herHim] demanding that you become [npc.her] bitch...)]",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
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
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_OFFER_BODY", getDemon())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								getDemon().incrementPlayerSurrenderCount(1);
							}
						};
						
					} else {
						return new Response("Offer body", "You can tell that [npc.name] isn't at all interested in having sex with you. You'll either have to offer [npc.herHim] some money, or prepare for a fight!", null);
					}
					
				} else if (index == 4 && getDemon().isApplyingPostCombatTransformations()) {
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
							getDemon().incrementPlayerSurrenderCount(1);
						}
					};
					
				} else if (index == 6 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();
	
					if(!getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with you, so wouldn't want to have a threesome..."),
								null);
						
					} else if(!getDemon().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name], so wouldn't want to have a threesome..."),
								null);
						
					} else if(!companion.isAttractedTo(getDemon()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "Offer [npc.name] the opportunity to have sex with both you and [npc2.name] in order to avoid a violent confrontation."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer(), companion),
										null,
										null,
										ResponseTag.PREFER_DOGGY),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_OFFER_BODY_THREESOME", getDemon(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 7 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getDemon().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name]..."),
								null);
						
					} else if(!companion.isAttractedTo(getDemon()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "Tell [npc.name] that [npc.she] can use [npc2.namePos] body in order to avoid a violent confrontation."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(companion),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_OFFER_COMPANION", getDemon(), companion)) {
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
					if(talked) {
						return new Response("Talk", "You've already spent time talking with [npc.name]...", null);
					}
					return new Response("Talk", "Talk to [npc.name] for a while in order to get to know [npc.herHim] a little better.", DEMON_PEACEFUL_TALK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 10));
							
							if(getDemon().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					return new Response("Offer money", UtilText.parse(getDemon(), "Unlike common muggers, [npc.name] has no need for your money!"), null);
					
				} else if (index == 3) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (dom)", "Take the dominant role and have sex with [npc.name].",
								Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getDemon()),
										Main.game.getPlayer().getCompanions(),
										null),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_SEX_AS_DOM", getDemon())) {
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
						return new ResponseSex("Sex (sub)", "Offer your body to [npc.name].",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Main.game.getPlayer().getCompanions()),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_SEX_AS_SUB", getDemon())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (sub)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} if (index == 5) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !getDemon().isAffectionHighEnoughToInviteHome()) {
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
						return new Response("Offer room", "Ask [npc.name] if [npc.she] would like a room in Lilaya's mansion.", DEMON_PEACEFUL_OFFER_ROOM) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 25));
							}
						};
					}
					
				} else if(index==6) {
					if(getDemon().getPlayerSurrenderCount()<3 && getDemon().isApplyingPostCombatTransformations()) {
						if(transformationsApplied) {
							return new Response("Get transformed",
									"[npc.Name] has already given you all the transformation potions [npc.she] had!",
									null);
							
						} else {
							return new Response("Get transformed",
									"Tell [npc.name] that you'd like to drink any transformation potions which [npc.she] has..."
										+"<br/>[style.italicsTfGeneric(This will result in [npc.name] getting you to drink a transformation potion!)]",
										DEMON_PEACEFUL_TRANSFORMED,
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_TRANSFORMATION_RECEIVING), Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(), null, null, null) {
								@Override
								public Colour getHighlightColour() {
									return PresetColour.TRANSFORMATION_GENERIC;
								}
								@Override
								public void effects() {
									applyPregnancyReactions();
									Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "PEACEFUL_TRANSFORMATIONS", getDemon()));
									Main.game.appendToTextStartStringBuilder(getDemon().applyPostCombatTransformation());
									transformationsApplied = true;
								}
							};
						}
					}
					
				} else if (index==10) {
					return new Response("Attack", "Betray [npc.namePos] trust and attack [npc.herHim]!", DEMON_PEACEFUL_ATTACK) {
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
					
				} else if (index == 11 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();
	
					if(!getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with you, so wouldn't want to have a threesome..."),
								null);
						
					} else if(!getDemon().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name], so wouldn't want to have a threesome..."),
								null);
						
					} else if(!companion.isAttractedTo(getDemon()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getDemon(), companion, "Offer [npc.name] the opportunity to have sex with both you and [npc2.name]."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer(), companion),
										null,
										null,
										ResponseTag.PREFER_DOGGY),
								AFTER_SEX_PEACEFUL_THREESOME, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_OFFER_BODY_THREESOME", getDemon(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 12 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getDemon().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name]..."),
								null);
						
					} else if(!companion.isAttractedTo(getDemon()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getDemon(), companion, "Tell [npc.name] that [npc.she] can use [npc2.namePos] body."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(companion),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())),
								AFTER_SEX_PEACEFUL_OFFERED_COMPANION, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_OFFER_COMPANION", getDemon(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getDemon()) && Main.game.isNonConEnabled()) {
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
	
	public static final DialogueNode DEMON_PEACEFUL_TALK = new DialogueNode("Talk", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			talked = true;
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_TALK", getDemon()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(getDemon().isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_CAN_INVITE_HOME", getDemon()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getDemon()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEMON_ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DEMON_PEACEFUL_OFFER_ROOM = new DialogueNode("Offer room", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_OFFER_ROOM", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take home", "Take [npc.name] to [npc.her] new room.", DEMON_PEACEFUL_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						getDemon().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(getDemon());
						Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_PEACEFUL_OFFER_ROOM_BACK_HOME = new DialogueNode("New Room", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_OFFER_ROOM_BACK_HOME", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] get settled in.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_PEACEFUL_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEMON_ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DEMON_PEACEFUL_ATTACK = new DialogueNode("Attack", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_ATTACK", getDemon());
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
	
	public static final DialogueNode AFTER_SEX_PEACEFUL = new DialogueNode("Continue", "Step away from [npc.name] and prepare to continue on your way.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(getDemon().isSatisfiedFromLastSex()) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL", getDemon());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL_NO_ORGASM", getDemon());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL_THREESOME = new DialogueNode("Continue", "Step away from [npc.name] and prepare to continue on your way.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL_THREESOME", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL_OFFERED_COMPANION = new DialogueNode("Continue", "Step away from [npc.name] and prepare to continue on your way.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(getMainCompanion().isAttractedTo(getDemon())) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL_OFFERED_COMPANION", getDemon());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL_OFFERED_COMPANION_RELUCTANT", getDemon());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getDemon().setPlayerSurrenderCount(0);
			getDemon().clearPetName(Main.game.getPlayer());
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}
		@Override
		public String getContent() {
			if(getDemon().isAttractedTo(Main.game.getPlayer())
					&& !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_ATTRACTION", getDemon());
				
			} else {
				if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_BETRAYED", getDemon());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getDemon());
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way...", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getDemon());
						}
					}
				};
				
			} else if (index == 2) {
				if(!getDemon().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_SEX", getDemon()));
				} else {
					return new ResponseSex(
							"Rape [npc.herHim]",
							"[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_RAPE", getDemon()));
				}
				
			} else if (index == 3) {
				if(!getDemon().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Gentle Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Gentle sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getDemon()));
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (gentle)",
							"[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getDemon()));
				}
				
			} else if (index == 4) {
				if(!getDemon().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Rough Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Rough sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getDemon()));
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (rough)",
							"[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getDemon()));
				}
				
			} else if (index == 5) {
				if(!getDemon().isAttractedTo(Main.game.getPlayer())) {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.sheHasFull] no interest in having sex with you!",
							null);
				} else {
					return new ResponseSex("Submit",
							"You're not really sure what to do now... Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getDemon()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion())),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getDemon()));
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getDemon(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 7) {
				if(Main.game.getCurrentDialogueNode()==AFTER_COMBAT_VICTORY_TALK) {
					return new Response("Talk", "You are already talking to [npc.name]...", null);
					
				} else if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
					
				} else {
					return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
						@Override
						public void effects() {
							getDemon().setPlayerKnowsName(true);
							Main.game.getTextEndStringBuilder().append(getDemon().setAffection(Main.game.getPlayer(), 10));
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
						QuickTransformations.initQuickTransformations("misc/quickTransformations", getDemon(), AFTER_COMBAT_VICTORY));
			
			} else if (index == 10 && !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"Remove character",
						UtilText.parse(getDemon(), "Scare [npc.name] away."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_BANISH_NPC", getDemon()));
						Main.game.banishNPC(getDemon());
					}
				};
				
			} else if (index == 11 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!Main.game.isNonConEnabled() && (!getDemon().isAttractedTo(Main.game.getPlayer()) || !getDemon().isAttractedTo(companion))) {
					return new Response("Threesome", UtilText.parse(companion, getDemon(), "[npc2.Name] has no interest in having sex with you or [npc.name]!"), null);
					
				} else if(!companion.isAttractedTo(getDemon())) {
					return new Response(UtilText.parse(companion, "Threesome"), UtilText.parse(companion, getDemon(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Threesome"),
							UtilText.parse(getDemon(), companion, "Have dominant sex with [npc.name], and get [npc2.name] to join in with the fun."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getDemon()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_THREESOME", getDemon(), companion));
				}
				
			} else if (index == 12 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !getDemon().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getDemon(), "[npc2.Name] isn't attracted to [npc.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
					
				} else if(!companion.isAttractedTo(getDemon())) {
					return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getDemon(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
							UtilText.parse(companion, getDemon(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] while you watch."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getDemon()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getDemon(), companion));
				}
				
			} else if (index == 13 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !companion.isAttractedTo(getDemon())) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"), UtilText.parse(companion, getDemon(), "[npc.Name] has no interest in having sex with [npc2.name]!"), null);
					
				} else if(!getDemon().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"), UtilText.parse(companion, getDemon(), "[npc2.Name] has no interest in having sex with [npc.name]!"), null);
					
				} else if(!companion.isAttractedTo(getDemon()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getDemon(), companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getDemon(), companion, "Tell [npc.name] that [npc.she] can use [npc2.name]."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getDemon()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getDemon(), companion)) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getDemon()) && Main.game.isNonConEnabled()) {
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
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_TALK", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
		}
	};

	private static String applyTransformation(GameCharacter target,
			TransformativePotion potion,
			boolean forcedTF,
			FetishPotion fetishPotion,
			boolean forcedFetish) {
		
		StringBuilder sb = new StringBuilder();
		
		if(potion!=null && forcedTF) {
			sb.append(UtilText.parse(getDemon(), target,
					"<p>"
						+ "[npc.Name] steps back, grinning down at [npc2.name] as [npc2.she] obediently [npc2.verb(swallow)] the strange liquid."
						+ " [npc.speech(Good [npc2.girl]! I'm going to turn you into my perfect "+getDemon().getPreferredBodyDescription("b")+"!)]"
					+ "</p>"));
			sb.append(getDemon().applyPotion(potion, target));
		}
		
		if(fetishPotion!=null && forcedFetish) {
			sb.append(UtilText.parse(getDemon(),
					"<p>"
						+ "With a look of fiendish delight in [npc.her] [npc.eyes], [npc.name] excitedly cries out,"
						+ " [npc.speech(That's right, swallow it all down! These changes are all for the better!)]"
					+ "</p>"));
			sb.append(getDemon().applyPotion(fetishPotion, target));
		}
		return sb.toString();
	}

	public static final DialogueNode SURRENDER = new DialogueNode("", "", true) {
		public void applyPreParsingEffects() {
			AFTER_COMBAT_DEFEAT.applyPreParsingEffects();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "SURRENDER", getDemon());		
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
				potion = getDemon().generateTransformativePotion(Main.game.getPlayer());
				fetishPotion = getDemon().generateFetishPotion(Main.game.getPlayer(), true);
			} else {
				potion = null;
				fetishPotion = null;
			}
			if(isCompanionDialogue()) {
				if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					companionPotion = getDemon().generateTransformativePotion(getMainCompanion());
					companionFetishPotion = getDemon().generateFetishPotion(getMainCompanion(), true);
				} else {
					companionPotion = null;
					companionFetishPotion = null;
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}
		@Override
		public String getContent() {
			if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_BETRAYED", getDemon());
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_GENERIC_START", getDemon()));
			
			boolean forcedTF = getDemon().isUsingForcedTransform(Main.game.getPlayer());
			boolean forcedFetish = getDemon().isUsingForcedFetish(Main.game.getPlayer());
			boolean companionForcedTF = isCompanionDialogue() && getDemon().isUsingForcedTransform(getMainCompanion());
			boolean companionForcedFetish = isCompanionDialogue() && getDemon().isUsingForcedFetish(getMainCompanion());
			if((forcedTF && potion!=null)
					|| (forcedFetish && fetishPotion!=null)
					|| (companionForcedTF && companionPotion!=null)
					|| (companionForcedFetish && companionFetishPotion!=null)) {
				if(((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null))
						&& ((companionForcedTF || companionPotion!=null) && (companionForcedFetish || companionFetishPotion!=null))) { // Both TF:
					
					if(fetishPotion!=null && forcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_TF_AND_FETISH", getDemon()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_FETISH", getDemon()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_TF", getDemon()));
					}
	
					if(companionFetishPotion!=null && companionForcedFetish) {
						if(companionPotion!=null && companionForcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_TF_AND_FETISH", getDemon()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_FETISH", getDemon()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_TF", getDemon()));
					}
					
					return sb.toString();
					
				} else if((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null)) { // Player TF:
					if(fetishPotion!=null && forcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_TF_AND_FETISH", getDemon()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_FETISH", getDemon()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_TF", getDemon()));
					}
					return sb.toString();
					
				} else if(isCompanionDialogue()) { // Companion TF:
					if(companionFetishPotion!=null && companionForcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_TF_AND_FETISH", getDemon()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_FETISH", getDemon()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_TF", getDemon()));
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
			if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.banishNPC(getDemon());
						}
					};
				}
				return null;
			}

			// Response variables:
			boolean forcedTF = getDemon().isUsingForcedTransform(Main.game.getPlayer());
			boolean forcedFetish = getDemon().isUsingForcedFetish(Main.game.getPlayer());
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
			boolean companionForcedTF = isCompanionDialogue() && getDemon().isUsingForcedTransform(getMainCompanion());
			boolean companionForcedFetish = isCompanionDialogue() && getDemon().isUsingForcedFetish(getMainCompanion());
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
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SPIT", getDemon()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_COMPANION_SWALLOW", getDemon()));
									Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_BOTH_SPIT", getDemon()));
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
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SWALLOW", getDemon()));
							Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
							
							if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_COMPANION_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_COMPANION_SPIT", getDemon()));
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
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SPIT", getDemon()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SPIT_REFUSED", getDemon()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_COMPANION_SWALLOW", getDemon()));
									Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SPIT", getDemon()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SPIT", getDemon()));
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
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
								
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SWALLOW_REFUSED", getDemon()));
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
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SPIT", getDemon()));
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
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SWALLOW", getDemon()));
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
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SPIT_REFUSED", getDemon()));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_COMPANION_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SPIT", getDemon()));
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
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SWALLOW_REFUSED", getDemon()));
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
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					if(getDemon().isAttractedTo(getMainCompanion())) {
						if(getDemon().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "RAPE_BOTH", getDemon());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "OFFER_SEX_BOTH", getDemon());
						}
						
					} else {
						if(getDemon().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "RAPE_PLAYER", getDemon());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "OFFER_SEX", getDemon());
						}
					}
					
				} else if(getDemon().isAttractedTo(getMainCompanion()) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {
					if(getDemon().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "RAPE_COMPANION", getDemon());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "OFFER_SEX_COMPANION", getDemon());
					}
				}
				
			} else {
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					if(getDemon().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "RAPE_PLAYER", getDemon());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "OFFER_SEX", getDemon());
					}
				}
			}

			if(transformationsApplied) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "NO_SEX_POST_TRANSFORM", getDemon());
			}
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "NO_SEX", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				boolean companionHappyToHaveSex = getMainCompanion().isAttractedTo(getDemon()) || getMainCompanion().isAttractedTo(Main.game.getPlayer());
				boolean companionSex = getDemon().isAttractedTo(getMainCompanion()) && (companionHappyToHaveSex || getDemon().isWillingToRape());
				
				
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					if(getDemon().isAttractedTo(getMainCompanion())) { // Threesome sex:
						if (index == 1) {
							return new ResponseSex("Sex",
									UtilText.parse(getDemon(),
											getDemon().isWillingToRape()
												?"[npc.Name] forces [npc.herself] on you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+"..."
												:"Tell [npc.name] that you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+" would like to have sex with [npc.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
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
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_THREESOME", getDemon()));
							
						} else if (index == 2) {
							return new ResponseSex("Eager Sex",
									UtilText.parse(getDemon(),
											getDemon().isWillingToRape()
												?"[npc.Name] forces [npc.herself] on you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+"..."
												:"Tell [npc.name] that you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+" are more than happy to have sex with [npc.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
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
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_THREESOME", getDemon()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("Resist Sex",
									UtilText.parse(getDemon(), "[npc.Name] forces [npc.herself] on you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+"..."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
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
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_THREESOME_RESIST", getDemon()));
							
						} else if (index == 4 && !getDemon().isWillingToRape()) {
							return new Response("Refuse",
									UtilText.parse(getDemon(), "Refuse to have sex with [npc.name] and continue on your way."),
									Main.game.getDefaultDialogue(false)) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEFEATED_REFUSE_THREESOME", getDemon()));
								}
							};
						}
						return null;
						
					} else { // Solo sex with player:
						if (index == 1) {
							return new ResponseSex("Sex",
									UtilText.parse(getDemon(),
											getDemon().isWillingToRape()
												?"[npc.Name] forces [npc.herself] on you..."
												:"Tell [npc.name] that you would like to have sex with [npc.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX", getDemon()));
							
						} else if (index == 2) {
							return new ResponseSex("Eager Sex",
									UtilText.parse(getDemon(),
											getDemon().isWillingToRape()
												?"[npc.Name] forces [npc.herself] on you..."
												:"Tell [npc.name] that you are more than happy to have sex with [npc.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_EAGER),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX", getDemon()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("Resist Sex",
									UtilText.parse(getDemon(), "[npc.Name] forces [npc.herself] on you..."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_RESIST", getDemon()));
							
						} else if (index == 4 && !getDemon().isWillingToRape()) {
							return new Response("Refuse",
									UtilText.parse(getDemon(), "Refuse to have sex with [npc.name] and continue on your way."),
									Main.game.getDefaultDialogue(false)) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEFEATED_REFUSE_SEX", getDemon()));
								}
							};
						}
						return null;
					}
					
				} else if(getDemon().isAttractedTo(getMainCompanion())
						&& Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) { // Solo sex with companion:
					if(getDemon().isWillingToRape()) {
						if (index == 1) {
							return new ResponseSex("Watch rape",
									UtilText.parse(getDemon(), getMainCompanion(),
											"You can do nothing but watch as [npc.name] forces [npc.herself] on [npc2.name]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_SOLO_COMPANION_RAPE", getDemon()));
						}
						
					} else if(companionHappyToHaveSex) {
						if (index == 1) {
							return new ResponseSex("Watch sex",
									UtilText.parse(getDemon(), getMainCompanion(),
											"You can do nothing but watch as [npc2.name] happily agrees to let [npc.name] fuck [npc2.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_SOLO_COMPANION", getDemon()));
						}
						
					} else if (index == 1) {
						return new Response(
								UtilText.parse(getMainCompanion(), "[npc.Name] refuses"),
								UtilText.parse(getDemon(), getMainCompanion(), "It looks like [npc2.name] is going to refuse to have sex with [npc.name]."),
								Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEFEATED_REFUSE_SEX_SOLO_COMPANION", getDemon()));
							}
						};
					}
				}
				
			} else {
				if(getDemon().isAttractedTo(Main.game.getPlayer())) { // Solo sex with player:
					if (index == 1) {
						return new ResponseSex("Sex",
								UtilText.parse(getDemon(),
										getDemon().isWillingToRape()
											?"[npc.Name] forces [npc.herself] on you..."
											:"Tell [npc.name] that you would like to have sex with [npc.herHim]."),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion())),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX", getDemon()));
						
					} else if (index == 2) {
						return new ResponseSex("Eager Sex",
								UtilText.parse(getDemon(),
										getDemon().isWillingToRape()
											?"[npc.Name] forces [npc.herself] on you..."
											:"Tell [npc.name] that you are more than happy to have sex with [npc.herHim]."),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion()),
										ResponseTag.START_PACE_PLAYER_SUB_EAGER),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX", getDemon()));
						
					} else if (index == 3 && Main.game.isNonConEnabled()) {
						return new ResponseSex("Resist Sex",
								UtilText.parse(getDemon(), "[npc.Name] forces [npc.herself] on you..."),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion()),
										ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_RESIST", getDemon()));
						
					} else if (index == 4 && !getDemon().isWillingToRape()) {
						return new Response("Refuse",
								UtilText.parse(getDemon(), "Refuse to have sex with [npc.name] and continue on your way."),
								Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEFEATED_REFUSE_SEX", getDemon()));
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEFEATED_NO_SEX", getDemon()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Step back", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}
		@Override
		public String getContent() {
			if((getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if(Main.sex.getNumberOfOrgasms(getDemon()) >= getDemon().getOrgasmsBeforeSatisfied()) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY", getDemon());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY_NO_ORGASM", getDemon());
				}
				
			} else {
				if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY_RAPE_BETRAYED", getDemon());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY_RAPE", getDemon());
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)){
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
						UtilText.parse(getDemon(), "Scare [npc.name] away."
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
						Main.game.banishNPC(getDemon());
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
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_DEFEAT", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY) {
					@Override
					public void effects() {
						if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getDemon());
						}
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
			}
			return null;
		}
	};
	
}
