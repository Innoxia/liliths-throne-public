package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.12
 * @author Innoxia
 */
public class TunnelImpsDialogue {

	private static List<GameCharacter> impGroup = new ArrayList<>();
	
	private static Value<String, AbstractItem> potion = null;
	private static Value<String, AbstractItem> companionPotion = null;
	
	public static void setImpGroup(List<GameCharacter> impGroup) {
		TunnelImpsDialogue.impGroup = impGroup;
	}
	
	public static List<GameCharacter> getImpGroup() {
		return impGroup;
	}
	
	public static ImpAttacker getImpLeader() {
		return (ImpAttacker) impGroup.get(0);
	}

	public static void banishImpGroup() {
		for(GameCharacter imp : impGroup) {
			if(!imp.isSlave()) {
				Main.game.banishNPC(imp.getId());
			}
		}
		impGroup.clear();
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
					
					if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the imps, and as [npc.sheIs] not your slave, you can't force [npc.herHim] to do it..."),
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

					if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the imps, and as [npc.sheIs] not your slave, you can't force [npc.herHim] to do it..."),
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
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().getTransformativePotion(Main.game.getPlayer(), true);
			}
			
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
								"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
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
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();

					Util.Value<String, AbstractItem> potion = getImpLeader().getTransformativePotion(Main.game.getPlayer(), false);
					
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
									+ "<p>"
										+ getImpLeader().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
									+"</p>");
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
		
		@Override
		public String getContent() {
			if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().getTransformativePotion(getMainCompanion(), true);
			}
			
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
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SPIT_COMPANION_SWALLOWS", getAllCharacters())
										+ "<p>"
											+ getImpLeader().useItem(companionPotion.getValue(), getMainCompanion(), false, true)
										+"</p>");
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SPIT", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 2) {
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
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
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SWALLOW", getAllCharacters())
											+ "<p>"
												+ getImpLeader().useItem(companionPotion.getValue(), getMainCompanion(), false, true)
											+"</p>");
									
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
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().getTransformativePotion(Main.game.getPlayer(), true);
			}
			if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().getTransformativePotion(getMainCompanion(), true);
			}
			
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(potion != null && companionPotion != null) {

				Util.Value<String, AbstractItem> potion = getImpLeader().getTransformativePotion(Main.game.getPlayer(), false);
				Util.Value<String, AbstractItem> companionPotion = getImpLeader().getTransformativePotion(getMainCompanion(), false);
				
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
									"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
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
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters())
											+ "<p>"
												+ getImpLeader().useItem(companionPotion.getValue(), getMainCompanion(), false, true)
											+"</p>");
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_BOTH_SPIT", getAllCharacters()));
								}
							}
						};
					}
					
				} else if (index == 2) {
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
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
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters())
										+ "<p>"
											+ getImpLeader().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
										+ "</p>"
										+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters())
										+ "<p>"
											+ getImpLeader().useItem(companionPotion.getValue(), getMainCompanion(), false, true)
										+"</p>");
								
							} else {
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters())
										+ "<p>"
											+ getImpLeader().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
										+"</p>"
										+UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SPITS", getAllCharacters()));
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
									"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
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
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters())
											+ "<p>"
												+ getImpLeader().useItem(companionPotion.getValue(), getMainCompanion(), false, true)
											+"</p>");
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_BOTH_SPIT_WITH_ORDER", getAllCharacters()));
								}
							}
						};
						
					}
					
				} else if (index == 7) {
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
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
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters())
											+ "<p>"
												+ getImpLeader().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
											+"</p>"
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_OBEYS_ORDER_TO_SWALLOW", getAllCharacters())
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters())
											+ "<p>"
												+ getImpLeader().useItem(companionPotion.getValue(), getMainCompanion(), false, true)
											+"</p>");
									
								} else {
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters())
											+ "<p>"
												+ getImpLeader().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
											+"</p>"
											+UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters()));
								}
							}
						};
				}
				
			} else if(potion==null && companionPotion==null) {
				return AFTER_COMBAT_TRANSFORMATION.getResponse(responseTab, index);  // Sex responses
				
			} else if(potion != null) {

				Util.Value<String, AbstractItem> potion = getImpLeader().getTransformativePotion(Main.game.getPlayer(), false);
				
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					};
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
								"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
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
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
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
									UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters())
									+ "<p>"
										+ getImpLeader().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
									+"</p>");
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

				Util.Value<String, AbstractItem> companionPotion = getImpLeader().getTransformativePotion(getMainCompanion(), false);
				
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
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters())
										+ "<p>"
											+ getImpLeader().useItem(companionPotion.getValue(), getMainCompanion(), false, true)
										+ "</p>");
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_SPIT_WITH_ORDER", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 7) {
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
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
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_OBEYS_ORDER_TO_SWALLOW", getAllCharacters())
											+ "<p>"
												+ getImpLeader().useItem(companionPotion.getValue(), getMainCompanion(), false, true)
											+"</p>");
									
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
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Standard";
				
			} else if(index==1) {
				return "Inventories";
				
			} else if(index==2) {
				return "Transformations";
				
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Continue", "Leave the imps and continue on your way...", Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								banishImpGroup();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Sex",
								"Well, they <i>are</i> asking for it!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								impGroup,
								null,
								null,
								AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Gentle Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
								true,
								false,
								Main.game.getPlayer().getParty(),
								impGroup,
								null,
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Rough Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'rough' pace.)",
								true,
								false,
								Main.game.getPlayer().getParty(),
								impGroup,
								null,
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
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
								impGroup,
								Main.game.getPlayer().getParty(),
								null,
								null,
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
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
						return new Response("Continue", "Leave the imps and continue on your way...", Main.game.getDefaultDialogueNoEncounter()) {
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
								AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Solo sex (Gentle)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps. (Start sex in the gentle pace.)"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Solo sex (Rough)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps. (Start sex in the rough pace.)"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
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
								AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
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
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_GROUP_SEX", getAllCharacters()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
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
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getAllCharacters()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
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
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_GIVE_TO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the imps, and as [npc.sheIs] not your slave, you can't force [npc.herHim] to do so..."),
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
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_OFFER_COMPANION", getAllCharacters())) {
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
		
		@Override
		public String getDescription() {
			return "You have been defeated by the imps!";
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().getTransformativePotion(Main.game.getPlayer(), true);
			}
			if(isCompanionDialogue() && getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().getTransformativePotion(getMainCompanion(), true);
			}
			
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
						AFTER_SEX_WATCHING_COMPANION, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_START_SEX", getAllCharacters()));
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
						impGroup,
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
						impGroup,
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
						impGroup,
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
						impGroup,
						Main.game.getPlayer().getParty(),
						null,
						null,
						AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"Eagerly allow yourself to be moved into position by the gang of imps...",
						false,
						false,
						impGroup,
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
						impGroup,
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
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_VICTORY_SEX", getAllCharacters()); //TODO
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
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
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
			if(Sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
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
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
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
	
	public static final DialogueNode CONTINUE_ENSLAVEMENT = new DialogueNode("Imps", "", true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(getImpLeader().getTotalTimesHadSex(Main.game.getPlayer())>0) {
				return AFTER_SEX_VICTORY.getResponseTabTitle(index);
			} else {
				return AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
		}
		
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getImpLeader().getTotalTimesHadSex(Main.game.getPlayer())>0) {
				return AFTER_SEX_VICTORY.getResponse(responseTab, index);
			} else {
				return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode IMP_ENSLAVEMENT_DIALOGUE = new DialogueNode("New Slave", "", true) {
		
		@Override
		public String getDescription(){
			return ".";
		}

		@Override
		public String getContent() {
			GameCharacter target = SlaveDialogue.getEnslavementTarget();
			AbstractClothing enslavementClothing = target.getEnslavementClothing();
			
			if(!target.isSlave()) {
				return UtilText.parse(target,
						"<p>"
							+ "Holding the "+enslavementClothing.getName()+" in one [pc.hand], you take a step towards [npc.name]."
							+ " [npc.She] lets out a distressed cry as [npc.she] sees what you're about to do, but [npc.sheIs] so exhausted that [npc.she] can't manage to put up any significant amount of resistance."
						+ "</p>"
						+ "<p>"
							+ "Forcing the item of clothing onto [npc.herHim], you step back, looking down at a face filled with fear."
							+ " The "+enslavementClothing.getName()+"'s arcane enchantment recognises [npc.name] as being a criminal, and, with a purple flash,"
								+ " <b>[npc.sheIs] teleported to the 'Slave Administration' building in Slaver Alley, where [npc.she]'ll be waiting for you to pick them up</b>."
						+ "</p>"
						+ "<p>"
							+ "Just before they disappear, glowing purple lettering is projected into the air, which reads:<br/>"
							+ "<i>Slave identification: [style.boldArcane("+target.getNameIgnoresPlayerKnowledge()+")]</i>"
						+ "</p>");
				
			} else {
				if(target.isSlave()) {
					return UtilText.parse(target,
							"<p>"
								+ "Holding the "+enslavementClothing.getName()+" in one [pc.hand], you take a step towards [npc.name]."
								+ " [npc.She] lets out a sigh as [npc.she] sees what you're about to do, and says,"
								+ " [npc.speech(If you're trying to enslave me, it won't work... I'm already a slave...)]"
							+ "</p>"
							+ "<p>"
								+ "Despite [npc.her] words, you force the item of clothing onto [npc.name], before stepping back and waiting to see if anything happens."
								+ " True to [npc.her] words, however, the "+enslavementClothing.getName()+"'s arcane enchantment recognises [npc.name] as already being a slave,"
										+ " evidenced by glowing green lettering that's projected into the air, which reads:<br/>"
								+ "<i>[style.boldGreen(Target already enslaved!)]</i>"
							+ "</p>");
					
				} else if(target.getSubspecies()==Subspecies.DEMON) {
					return UtilText.parse(target,
							"<p>"
								+ "Holding the "+enslavementClothing.getName()+" in one [pc.hand], you take a step towards [npc.name]."
								+ " [npc.She] lets out a mocking laugh as [npc.she] sees what you're about to do, and sneers,"
								+ " [npc.speech(If you're trying to enslave me, it's no use! Demons aren't allowed to be enslaved without their written consent! Everyone knows that!)]"
							+ "</p>"
							+ "<p>"
								+ "Despite [npc.her] words, you force the item of clothing onto [npc.name], before stepping back and waiting to see if anything happens."
								+ " True to [npc.her] words, however, the "+enslavementClothing.getName()+"'s arcane enchantment doesn't recognise [npc.name] as being a criminal,"
										+ " evidenced by glowing pink lettering that's projected into the air, which reads:<br/>"
								+ "<i>[style.boldPink(Demonic target! Cannot enslave!)]</i>"
							+ "</p>");
					
				} else {
					return UtilText.parse(target,
							"<p>"
								+ "Holding the "+enslavementClothing.getName()+" in one [pc.hand], you take a step towards [npc.name]."
								+ " [npc.She] lets out a mocking laugh as [npc.she] sees what you're about to do, and sneers, [npc.speech(If you're trying to enslave me, it's no use! I haven't been targeted by the Enforcers for anything!)]"
							+ "</p>"
							+ "<p>"
								+ "Despite [npc.her] words, you force the item of clothing onto [npc.name], before stepping back and waiting to see if anything happens."
								+ " True to [npc.her] words, however, the "+enslavementClothing.getName()+"'s arcane enchantment doesn't recognise [npc.name] as being a criminal,"
										+ " evidenced by glowing red lettering that's projected into the air, which reads:<br/>"
								+ "<i>[style.boldRed(Invalid target! Cannot enslave!)]</i>"
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(getImpGroup().size()>1) {
					return new Response("Continue", "Continue dealing with the other imps.", CONTINUE_ENSLAVEMENT){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(IMP_ENSLAVEMENT_DIALOGUE.getContent());
							GameCharacter target = SlaveDialogue.getEnslavementTarget();
							
							target.applyEnslavementEffects(Main.game.getPlayer());
							Main.game.getPlayer().addSlave((NPC) target);
							target.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
							getImpGroup().remove(target);
						}
					};
					
				} else {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							GameCharacter target = SlaveDialogue.getEnslavementTarget();
							
							target.applyEnslavementEffects(Main.game.getPlayer());
							Main.game.getPlayer().addSlave((NPC) target);
							target.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
							getImpGroup().remove(target);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
}
