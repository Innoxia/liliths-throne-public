package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.3.5.5
 * @author Innoxia
 */
public class TunnelImpsDialogue {

	private static TransformativePotion potion = null;
	private static TransformativePotion companionPotion = null;
	
	public static List<GameCharacter> getImpGroup() {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> Main.game.getPlayer().getParty().contains(npc) || !(npc instanceof ImpAttacker));
		Collections.sort(guards, (a, b)->b.getLevel()-a.getLevel());
		return guards;
	}
	
	public static ImpAttacker getImpLeader() {
		return (ImpAttacker) getImpGroup().get(0);
	}

	public static void banishImpGroup() {
		for(GameCharacter imp : getImpGroup()) {
			if(!imp.isSlave()) {
				Main.game.banishNPC(imp.getId());
			}
		}
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		if(isCompanionDialogue()) {
			List<GameCharacter> allCharacters = new ArrayList<>();
			allCharacters.add(getMainCompanion());
			allCharacters.addAll(getImpGroup());
			return allCharacters;
			
		} else {
			return getImpGroup();
		}
	}
	
	private static boolean isCompanionDialogue() {
		return !Main.game.getPlayer().getCompanions().isEmpty();
	}
	
	private static String getImpEncounterId() {
		StringBuilder idSB = new StringBuilder();
		if(isCompanionDialogue()) {
			idSB.append("Companions");
		}
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
			// Alpha imp group encounter:
			idSB.append("Alpha");
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
			// Demon group encounter:
			idSB.append("Demon");
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
			// Female imps encounter:
			idSB.append("Females");
			
		} else {
			// Male imps encounter:
			idSB.append("Males");
		}
		
		return idSB.toString();
	}
	
	public static final DialogueNode IMP_ATTACK = new DialogueNode("Imp Gang", "A group of imps attack!", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isCompanionDialogue()) {
				if (index == 1) {
					return new ResponseCombat("Fight", "Stand up for yourself and fight this group of imps!", getImpLeader(), getImpGroup(), null);
					
				} else if (index == 2) {
					return new Response("Offer money", "These imps are not interested in your money! You'll have to either fight or surrender yourself to them...", null);
					
				} else if (index == 3) {
					return new Response("Offer body",
							"Offer your body to the imps so that you can avoid a violent confrontation.",
							IMP_ATTACK_OFFER_BODY,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null,
							null,
							null) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new ResponseCombat("Fight", UtilText.parse(getMainCompanion(), "Stand up for yourself and, with the help of [npc.name], fight the imps!"), getImpLeader(), getImpGroup(), null);
					
				} else if (index == 2) {
					return new Response("Offer money", "These imps are not interested in your money! You'll have to either fight or surrender yourself to them...", null);
					
				} else if (index == 3) {
					return new Response("Offer body (solo)",
							UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand aside as you offer your body to the imps in order to avoid a violent confrontation."),
							IMP_ATTACK_OFFER_BODY,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null,
							null,
							null){
						@Override
						public boolean isSexHighlight() {
							return true;
						}
					};
					
				} else if (index == 4) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the imps, and you can't force [npc.herHim] to do it..."),
								null);
						
					} else {
						return new Response("Offer threesome",
								UtilText.parse(companion, "Offer the imps the opportunity to have sex with both you and [npc.name] in order to avoid a violent confrontation."),
								IMP_ATTACK_OFFER_THREESOME,
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null) {
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the imps, and you can't force [npc.herHim] to do it..."),
								null);
						
					} else {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(companion, "Tell the imps that they can use [npc.namePos] body in order to avoid a violent confrontation."),
								IMP_ATTACK_OFFER_COMPANION) {
							@Override
							public void effects() {
								if(!companion.isAttractedTo(getImpLeader()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else {
					return null;
				}
			
			}
		}
	};
	
	public static final DialogueNode IMP_ATTACK_OFFER_BODY = new DialogueNode("Imp Gang", "", true) {

		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().generateTransformativePotion(Main.game.getPlayer());
			} else {
				potion = null;
			}
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_BODY", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(potion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
								"Due to your <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
								null);
					} else {
						return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_TRANSFORMATION_SOLO) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters())); // Re-use TF refuse dialogue
							}
						};
					}
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();

					return new Response("Swallow",
							(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
								?"Swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina, as well as becoming feminine and growing breasts..."
								:"Swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina...",
							AFTER_COMBAT_TRANSFORMATION_SOLO,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							for(GameCharacter imp : getImpGroup()) {
								imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
								if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
									imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								}
							}
							
							Main.game.getTextStartStringBuilder().append(
									UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()) // Re-use TF refuse dialogue
									);
							Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));
						}
					};
				}
				
			} else {
				return AFTER_COMBAT_TRANSFORMATION_SOLO.getResponse(responseTab, index); // Sex responses
			}
			
			return null;
		}
	};

	public static final DialogueNode IMP_ATTACK_OFFER_COMPANION = new DialogueNode("Imp Gang", "", true) {

		public void applyPreParsingEffects() {
			if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().generateTransformativePotion(getMainCompanion());
			} else {
				companionPotion = null;
			}
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(companionPotion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					return new Response("Order spit",
							UtilText.parse(getMainCompanion(), "Tell [npc.name] to spit out the potion the imps are trying to force [npc.herHim] to drink."
									+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
											?" (However, as [npc.name] has the "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" fetish, [npc.sheIsFull] unlikely to listen to you!)"
											:"")),
							AFTER_OFFER_COMPANION_TRANSFORMATION) {
						@Override
						public void effects(){
							if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}
								
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SPIT_COMPANION_SWALLOWS", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SPIT", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
	
						return new Response("Order swallow",
								UtilText.parse(getMainCompanion(),
									((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
										?"Tell [npc.name] to swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina, as well as becoming feminine and growing breasts."
										:"Tell [npc.name] to swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina.")
									+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
											?" (However, as [npc.she] dislikes being transformed, [npc.sheIsFull] unlikely to listen to you!)"
											:"")),
								AFTER_OFFER_COMPANION_TRANSFORMATION,
								applicableFetishes,
								applicableCorruptionLevel,
								null,
								null,
								null) {
							@Override
							public void effects(){
								if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
	
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SWALLOW_COMPANION_SPITS", getAllCharacters()));
								}
							}
						};
				}
				
			} else {
				return AFTER_OFFER_COMPANION_TRANSFORMATION.getResponse(responseTab, index); // Sex responses
			}
			
			return null;
		}
	};
	
	
	public static final DialogueNode IMP_ATTACK_OFFER_THREESOME = new DialogueNode("Imp Gang", "", true) {

		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().generateTransformativePotion(Main.game.getPlayer());
			} else {
				potion = null;
			}
			if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().generateTransformativePotion(getMainCompanion());
			} else {
				companionPotion = null;
			}
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(potion != null && companionPotion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
									"Due to your <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
										+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
								null);
					} else {
						return new Response("Spit", 
								UtilText.parse(getMainCompanion(),
										"Spit out the potion. ([npc.Name] will decide whether to spit or swallow [npc.her] own potion [npc.herself].)"), AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
									
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters()) // Re-use description
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_BOTH_SPIT", getAllCharacters()));
								}
							}
						};
					}
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
					
					return new Response("Swallow",
							UtilText.parse(getMainCompanion(),
								((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
									?"Swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina, as well as becoming feminine and growing breasts..."
									:"Swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina...")
								+ " ([npc.Name] will decide whether to spit or swallow [npc.her] own potion [npc.herself].)"),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							for(GameCharacter imp : getImpGroup()) {
								imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
								if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
									imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								}
							}
							if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}

								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
							} else {
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SPITS", getAllCharacters()));
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
										+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid, or to tell [npc.name] to do the same!"),
								null);
						
					} else {
						return new Response("Spit (both)",
								UtilText.parse(getMainCompanion(), "Spit out the potion, and tell [npc.name] to do the same."
										+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
												?" (However, as [npc.name] has the "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" fetish, [npc.sheIsFull] unlikely to listen to you!)"
												:"")),
								AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
									
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters())
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SPIT", getAllCharacters())
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_BOTH_SPIT_WITH_ORDER", getAllCharacters()));
								}
							}
						};
						
					}
					
				} else if (index == 7) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();

						return new Response("Swallow (both)",
								UtilText.parse(getMainCompanion(),
									((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
										?"Swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina, as well as becoming feminine and growing breasts."
										:"Swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina.")
									+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
											?"Once swallowed, tell [npc.name] to do the same. (However, as [npc.she] dislikes being transformed, [npc.sheIsFull] unlikely to listen to you!)"
											:" Once swallowed, tell [npc.name] to do the same...")),
								AFTER_COMBAT_TRANSFORMATION,
								applicableFetishes,
								applicableCorruptionLevel,
								null,
								null,
								null) {
							@Override
							public void effects(){
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
									}
								}
								if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}

									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_OBEYS_ORDER_TO_SWALLOW", getAllCharacters())
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters()));
								}
							}
						};
				}
				
			} else if(potion==null && companionPotion==null) {
				return AFTER_COMBAT_TRANSFORMATION.getResponse(responseTab, index);  // Sex responses
				
			} else if(potion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					};
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
								"Due to your <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
								null);
					} else {
						return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters()));
							}
						};
					}
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
					
					return new Response("Swallow",
							(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
								?"Swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina, as well as becoming feminine and growing breasts..."
								:"Swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina...",
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							for(GameCharacter imp : getImpGroup()) {
								imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
								if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
									imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								}
							}
							
							Main.game.getTextStartStringBuilder().append(
									UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
							Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));
						}
					};
					
				} else if (index == 6) {
					return new Response("Swallow (both)",
							UtilText.parse(getMainCompanion(), "As the imps are unable to access [npc.namePos] mouth, they are not attempting to force [npc.herHim] to drink their transformative potion."),
							null);
					
				}  else if (index == 7) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Spit (both)");
					}
					return new Response("Spit (both)",
							UtilText.parse(getMainCompanion(), "As the imps are unable to access [npc.namePos] mouth, they are not attempting to force [npc.herHim] to drink their transformative potion."),
							null);
				}
				
			} else {
				if (index == 1) {
					return new Response("Spit", UtilText.parse(getMainCompanion(),"As the imps cannot gain access to your mouth, they are ignoring you and focusing on transforming [npc.name]!"), null);
					
				} else if (index == 2) {
					return new Response("Swallow", UtilText.parse(getMainCompanion(),"As the imps cannot gain access to your mouth, they are ignoring you and focusing on transforming [npc.name]!"), null);
					
				} else if (index == 6) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Order spit");
					}
					return new Response("Order spit",
							UtilText.parse(getMainCompanion(), "Tell [npc.name] to spit out the potion the imps are trying to force [npc.herHim] to drink."
									+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
											?" (However, as [npc.name] has the "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" fetish, [npc.sheIsFull] unlikely to listen to you!)"
											:"")),
							AFTER_COMBAT_TRANSFORMATION) {
						@Override
						public void effects(){
							if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}
								
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_SPIT_WITH_ORDER", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 7) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
	
						return new Response("Order swallow",
								UtilText.parse(getMainCompanion(),
									((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
										?"Tell [npc.name] to swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina, as well as becoming feminine and growing breasts."
										:"Tell [npc.name] to swallow the potion, which, if the imps are to be believed, causes the drinker to grow both a penis and vagina.")
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
								if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
	
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_OBEYS_ORDER_TO_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters()));
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
			return "You have defeated the imps!";
		}

		@Override
		public String getContent() {
			if(getImpGroup().isEmpty()) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_ALL_ENSLAVED", getImpGroup());
				
			} else if(getImpGroup().size()==1) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ENSLAVED_ONE", getImpGroup());
				
			} else if(getImpGroup().size()<4) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ENSLAVED", getImpGroup());
			}
			return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY", getImpGroup());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!getImpGroup().isEmpty()) {
				if(index==0) {
					return "Interactions";
					
				} else if(index==1) {
					return "Inventories";
					
				} else if(index==2) {
					return "Transformations";
					
				}
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getImpGroup().isEmpty()) {
				if(index==1) {
					return new Response("Continue", "As you've enslaved all of the imps, there's nothing left to do but continue on your way...", Main.game.getDefaultDialogue(false));
				}
				return null;
			}
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Continue", "Leave the imps and continue on your way...", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_CONTINUE", getImpGroup()));
								banishImpGroup();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Sex",
								"Well, they <i>are</i> asking for it!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX", getImpGroup()));
						
					} else if (index == 3) {
						return new ResponseSex("Gentle Sex",
								"Well, they <i>are</i> asking for it!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Rough Sex",
								"Well, they <i>are</i> asking for it!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Submit",
								"You're not really sure what to do now... Perhaps it would be best to let the imps choose what to do next...",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGroup(),
								Main.game.getPlayer().getParty(),
								null,
								null,
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
									Main.game.setResponseTab(0);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Continue", "Leave the imps and continue on your way...", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								banishImpGroup();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Solo sex",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX", getImpGroup()));
						
					} else if (index == 3) {
						return new ResponseSex("Solo sex (Gentle)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Solo sex (Rough)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Solo submission",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you submit to the imps, allowing them to have dominant sex with you."),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGroup(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Group sex",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group sex"),
									UtilText.parse(companion, "Have dominant sex with the imps, and get [npc.name] to join in with the fun."),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpGroup(),
									null,
									null,
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_GROUP_SEX", getImpGroup()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Group submission",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group submission"),
									UtilText.parse(companion, "Get [npc.name] to join you in submitting to the imps, allowing them to have dominant sex with the two of you."),
									true,
									false,
									getImpGroup(),
									Main.game.getPlayer().getParty(),
									null,
									null,
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getImpGroup()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, "Tell [npc.name] that [npc.she] can have some fun with the imps while you watch."),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpGroup(),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_GIVE_TO_COMPANION", getImpGroup()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the imps, and you can't force [npc.herHim] to do so..."),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "Hand [npc.name] over to the imps, and watch as they have sex with [npc.herHim]."),
									true,
									false,
									getImpGroup(),
									Util.newArrayListOfValues(getMainCompanion()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_OFFER_COMPANION", getImpGroup())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getImpLeader()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
									Main.game.setResponseTab(0);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {
		
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().generateTransformativePotion(Main.game.getPlayer());
			} else {
				potion = null;
			}
			if(isCompanionDialogue() && getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().generateTransformativePotion(getMainCompanion());
			} else {
				companionPotion = null;
			}
		}
		
		@Override
		public String getDescription() {
			return "You have been defeated by the imps!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				return IMP_ATTACK_OFFER_THREESOME.getResponse(responseTab, index);
			} else {
				return IMP_ATTACK_OFFER_BODY.getResponse(responseTab, index);
			}
		}
	};
	

	public static final DialogueNode AFTER_OFFER_COMPANION_TRANSFORMATION = new DialogueNode("Imp Gang", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Watch",
						UtilText.parse(getMainCompanion(), "Watch the imps have sex with [npc.name]..."),
						true,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(getMainCompanion()),
						null,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						AFTER_SEX_WATCHING_COMPANION,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_START_SEX", getAllCharacters()));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION_SOLO = new DialogueNode("Imp Gang", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"Allow the gang of imps to move you into position...",
						false,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"Eagerly allow yourself to be moved into position by the gang of imps...",
						false,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"Try to resist as the gang of imps move you into position...",
						false,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION = new DialogueNode("Imp Gang", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"Allow the gang of imps to move you into position...",
						false,
						false,
						getImpGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"Eagerly allow yourself to be moved into position by the gang of imps...",
						false,
						false,
						getImpGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"Try to resist as the gang of imps move you into position...",
						false,
						false,
						getImpGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Step back", "", true) {
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave the imps to recover and disperse.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "AFTER_VICTORY_SEX", getImpGroup());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishImpGroup();
						}
					};
				}
				
			} else if(responseTab==1) {
				return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
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
			if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						for(GameCharacter imp :getImpGroup()) {
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && Main.game.getPlayer().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.HEAD);
							}
						}
						banishImpGroup();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_WATCHING_COMPANION = new DialogueNode("Finished", "", true) {
		
		@Override
		public String getDescription(){
			return UtilText.parse(getMainCompanion(), "The imps have finished with [npc.name]...");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_SEX_WATCHING_COMPANION", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						for(GameCharacter imp :getImpGroup()) {
							if(!getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && getMainCompanion().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								getMainCompanion().addDirtySlot(InventorySlot.HEAD);
							}
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && Main.game.getPlayer().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.HEAD);
							}
						}
						banishImpGroup();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
