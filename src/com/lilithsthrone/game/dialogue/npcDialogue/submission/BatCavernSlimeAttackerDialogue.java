package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import java.util.Collections;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.4
 * @version 0.3.1
 * @author Innoxia
 */
public class BatCavernSlimeAttackerDialogue {
	
	private static NPC getSlime() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode SLIME_ATTACK = new DialogueNode("Assaulted!", "A slime lurches forwards from out of the shadows!", true) {
		
		@Override
		public String getLabel(){
			return "Assaulted!";
		}
		
		@Override
		public String getContent() {
			if(getSlime().getLastTimeEncountered() != -1) {
				if(getSlime().isVisiblyPregnant()){
					// Pregnant encounters:
					if(!getSlime().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
						return UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ATTACK_REPEAT_PREGNANCY_REACT")
								+ "<p style='text-align:center;'>" 
									+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You ended up getting [npc.name] pregnant!</b>"
								+ "</p>";
					
					} else {
						return UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ATTACK_REPEAT_PREGNANCY")
									+ (getSlime().isAttractedTo(Main.game.getPlayer())
										?UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ATTRACTED_TO_PLAYER")
										:UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "NOT_ATTRACTED_TO_PLAYER"));
					}
					
				} else {
					// Standard repeat encounter:
					return UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ATTACK_REPEAT")
								+ (getSlime().isAttractedTo(Main.game.getPlayer())
										?UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ATTRACTED_TO_PLAYER")
										:UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "NOT_ATTRACTED_TO_PLAYER"));
				}
				
			} else {
				return UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ATTACK")
						+ (getSlime().isAttractedTo(Main.game.getPlayer())
								?UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ATTRACTED_TO_PLAYER")
								:UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "NOT_ATTRACTED_TO_PLAYER"));
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Stand up for yourself and fight [npc.name]!", getSlime()) {
					@Override
					public void effects() {
						if(getSlime().isVisiblyPregnant()){
							getSlime().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
					}
				};
				
			} else if (index == 2) {
				if(Main.game.getPlayer().getMoney()<250) {
					return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "You don't have enough money to offer to pay [npc.name] off. You'll have to either fight [npc.herHim] or offer [npc.herHim] your body!", null);
				} else {
					return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "Offer to pay [npc.name] 250 flames to leave you alone.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							if(getSlime().isVisiblyPregnant()){
								getSlime().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ATTACK_PAID_OFF")
									+ Main.game.getPlayer().incrementMoney(-250));
						}
					};
				}
				
			} else if (index == 3) {
				if(!Main.game.getPlayer().hasItemType(ItemType.MUSHROOM)) {
					return new Response("Offer mushroom", "You don't have any mushrooms to offer to [npc.name]!", null);
				} else {
					return new Response("Offer mushroom", "Offer one of your Glowing Mushrooms to [npc.name] in exchange for leaving you alone.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							if(getSlime().isVisiblyPregnant()){
								getSlime().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.MUSHROOM), 1, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ATTACK_PAID_OFF_WITH_MUSHROOM")
									+"<p>"
										+ Main.game.getPlayer().removedItemFromInventoryText(ItemType.MUSHROOM)//"You gave away one "+ItemType.MUSHROOM.getName(true)+"!"
									+ "</p>");
						}
					};
				}
				
			} else if (index == 4) {
				if(getSlime().isAttractedTo(Main.game.getPlayer())) {
					return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null, null, null,
							true, true,
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
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ATTACK_OFFER_BODY")) {
						@Override
						public void effects() {
							if(getSlime().isVisiblyPregnant()){
								getSlime().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
						}
					};
					
				} else {
					return new Response("Offer body", "You can tell that [npc.name] isn't at all interested in having sex with you. You'll either have to offer [npc.herHim] some money, or prepare for a fight!", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() {
			if(getSlime().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				return UtilText.parse(getSlime(), UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ENEMY_DEFEATED_WANTS_SEX"));
				
			} else {
				return UtilText.parse(getSlime(), UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "ENEMY_DEFEATED"));
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
		
			if (index == 1) {
				return new Response("Continue", "Carry on your way...", Main.game.getDefaultDialogueNoEncounter()){
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
							UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "AFTER_COMBAT_VICTORY_SEX", getSlime()));
					
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
							UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "AFTER_COMBAT_VICTORY_RAPE", getSlime()));
				}
				
			} else if (index == 3) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Gentle Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getSlime().isAttractedTo(Main.game.getPlayer())){
					return new ResponseSex("Gentle sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getSlime()));
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getSlime()));
				}
				
			} else if (index == 4) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer()) && !Main.game.isNonConEnabled()) {
					return new Response("Rough Sex", "[npc.Name] has no interest in having sex with you!", null);
					
				} else if(getSlime().isAttractedTo(Main.game.getPlayer())){
					return new ResponseSex("Rough sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getSlime()));
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (rough)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getSlime()));
				}
				
			} else if (index == 5) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
					return new Response("Submit",
							"You can't submit to [npc.name], as [npc.she] has no interest in having sex with you!",
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
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getSlime()));
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
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/submission/batCavernSlime", "AFTER_COMBAT_VICTORY_BANISH_NPC", getSlime()));
						Main.game.banishNPC(getSlime());
					}
				};
				
			} else {
				return null;
			}
			
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {
		
		Util.Value<String, AbstractItem> potion = null;
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && getSlime().isWillingToRape(Main.game.getPlayer())) {
				potion = getSlime().generateTransformativePotion(Main.game.getPlayer());
				
				if(potion == null) {
					return UtilText.parse(getSlime(),
							"<p>"
								+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
								+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
							+ "</p>"
							+ "<p>"
								+ "Pulling you to your feet, [npc.name] starts grinding [npc.herself] up against you, [npc.moaning] into your [pc.ear] as [npc.she] starts groping your body."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(You're my perfect little " +getSlime().getPreferredBodyDescription("b") + " now! Don't forget, bitch, <i>I'm</i> the one in charge!)] [npc.she] growls, before pulling you into a forceful kiss."
							+ "</p>");
					
				} else {
					return UtilText.parse(getSlime(),
							"<p>"
								+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
								+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down and pushing you to the ground."
							+ "</p>"
							+ "<p>"
								+ "As [npc.she] pins you to the floor, [npc.she] produces a curious little bottle from somewhere out of sight, and shakes it from side to side, grinning."
								+ " [npc.speech(I think you could do with some <i>improvements</i>! I'm going to turn you into my perfect "+getSlime().getPreferredBodyDescription("b")+"!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.She] pulls out the little stopper from the top of the bottle, and as you open your mouth to protest, [npc.she] suddenly shoves the neck past your [pc.lips+]."
								+ " As the sickly sweet fluid pours out into your mouth, you let out a muffled whine; the only act of resistance that you're able to summon in your current state."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Come on! Swallow it all down already!)] [npc.she] growls, throwing the now-empty vessel to one side as [npc.she] tries to force you to swallow the strange fluid..."
							+ "</p>");
				}
			}
				
			if(getSlime().isAttractedTo(Main.game.getPlayer()) && getSlime().isWillingToRape(Main.game.getPlayer())) {
					return UtilText.parse(getSlime(),
							"<p>"
								+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
								+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
							+ "</p>"
							+ "<p>"
								+ "Pulling you to your feet, [npc.name] starts grinding [npc.herself] up against you, [npc.moaning] into your [pc.ear] as [npc.she] starts groping your body."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Don't try anything, bitch! <i>I'm</i> the one in charge here!)] [npc.she] growls, before pulling you into a forceful kiss."
							+ "</p>");
				
			} else {
				return UtilText.parse(getSlime(),
						"<p>"
							+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
							+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
						+ "</p>"
						+ "<p>"
							+ "Pulling you to your feet, [npc.name] pushes you against a nearby wall, before demanding that you hand over your money."
							+ " Reluctantly, you do as [npc.she] says, and, after giving [npc.herHim] some of your cash, [npc.she] roughly pushes you to the floor once more."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Don't even <i>think</i> about reporting this to the enforcers!)] [npc.she] growls down at you, before turning around and running off."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && potion != null && getSlime().isWillingToRape(Main.game.getPlayer())) {
				Fetish applicableFetish = potion.getValue().getItemType() == ItemType.FETISH_REFINED
						?Fetish.FETISH_KINK_RECEIVING
						:Fetish.FETISH_TRANSFORMATION_RECEIVING;
				CorruptionLevel applicableCorruptionLevel = potion.getValue().getItemType() == ItemType.FETISH_REFINED
						?Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel()
						:Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();

				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(!Collections.disjoint(Main.game.getPlayer().getFetishes(true), Util.newArrayListOfValues(applicableFetish))) {
						return new Response("Spit",
								"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+applicableFetish.getName(Main.game.getPlayer())
									+"</b> fetish, you love "+applicableFetish.getShortDescriptor()+" so much that you can't bring yourself to spit out the transformative liquid!",
								null);
					} else {
						return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_TRANSFORMATION_REFUSED);
					}
					
				} else if (index == 2) {
					return new Response("Swallow", "Do as you're told and swallow the strange potion.", AFTER_COMBAT_TRANSFORMATION,
							Util.newArrayListOfValues(applicableFetish),
							applicableCorruptionLevel,
							null,
							null,
							null){
						@Override
						public void effects(){
							Util.Value<String, AbstractItem> potion = getSlime().generateTransformativePotion(Main.game.getPlayer());
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "[npc.Name] steps back, grinning down at you as you obediently swallow the strange liquid,"
										+ " [npc.speech(Good [pc.girl]! "+potion.getKey()+")]"
									+ "</p>"
									+ "<p>"
										+getSlime().useItem(potion.getValue(), Main.game.getPlayer(), false)
									+"</p>");
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(getSlime().isAttractedTo(Main.game.getPlayer()) && getSlime().isWillingToRape(Main.game.getPlayer())) {
					if (index == 1) {
						return new ResponseSex("Sex",
								"[npc.Name] forces [npc.herself] on you...",
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getSlime()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null), AFTER_SEX_DEFEAT, "<p>"
									+ "[npc.NamePos] [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally pulling away."
									+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.sheIs] probably not going to be content with just a kiss..."
								+ "</p>");
						
					} else if (index == 2) {
						return new ResponseSex("Eager Sex",
								"[npc.Name] forces [npc.herself] on you...",
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getSlime()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null,
										ResponseTag.START_PACE_PLAYER_SUB_EAGER), AFTER_SEX_DEFEAT, "<p>"
									+ "[npc.NamePos] [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
									+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.sheIs] going to want more than just a kiss..."
								+ "</p>");
						
					} else if (index == 3 && Main.game.isNonConEnabled()) {
						return new ResponseSex("Resist Sex",
								"[npc.Name] forces [npc.herself] on you...",
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getSlime()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null,
										ResponseTag.START_PACE_PLAYER_SUB_RESISTING), AFTER_SEX_DEFEAT, "<p>"
									+ "[npc.NamePos] [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
									+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
									+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.sheIs] not going to let you go..."
								+ "</p>");
						
					} else {
						return null;
					}
					
				} else {
					if (index == 1) {
						return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
						};
						
					} else {
						return null;
					}
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION_REFUSED = new DialogueNode("Avoided Transformation", "", true) {

		@Override
		public String getContent() {
			if(getSlime().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(getSlime(),
						"<p>"
							+ "Despite [npc.namePos] best efforts, you manage to twist your head to one side and spit the strange fluid out onto the floor."
							+ " Your response is met by an anguished groan from your assailant, and, turning your head back up to look at them once more, you see them snarling down angrily at you,"
							+ " [npc.speech(You <i>"+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"</i>! Do you know how much that cost me?!)]"
						+ "</p>"
						+ "<p>"
							+ "After shouting down into your face, [npc.name] stands up, pulling you roughly to your [pc.feet] as [npc.she] does so, before pressing [npc.herself] against you and forcing you into a wet kiss."
							+ " You're powerless to resist [npc.her] advances, and as [npc.her] [npc.hands] reach around to give your [pc.ass+] a squeeze, [npc.she] laughs,"
							+ " [npc.speech(I'll turn you into my perfect little "
							+getSlime().getPreferredBodyDescription("b")
							+" next time! For now, I'm going to get some fun out of you just as you are!)]"
						+ "</p>");
			
			} else {
				return UtilText.parse(getSlime(),
						"<p>"
							+ "Despite [npc.namePos] best efforts, you manage to twist your head to one side and spit the strange fluid out onto the floor."
							+ " Your response is met by an anguished groan from your assailant, and, turning your head back up to look at them once more, you see them snarling down angrily at you,"
							+ " [npc.speech(You <i>"+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"</i>! Do you know how much that cost me?!)]"
						+ "</p>"
						+"<p>"
							+ "After shouting down into your face, [npc.name] stands up, pulling you roughly to your [pc.feet] as [npc.she] does so, before pushing you against a nearby wall and demanding that you hand over your money."
							+ " Reluctantly, you do as [npc.she] says, and, after giving [npc.herHim] some of your cash, [npc.she] shoves you down to the floor once more."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(This money of yours is going to pay for your next potion!)] [npc.she] growls down at you. [npc.speech(Come back and pay me another visit, <i>or else</i>! And don't you dare refuse to swallow next time!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and runs off, leaving you to recover from your ordeal and continue on your way..."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getSlime().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.sheIs] probably not going to be content with just a kiss..."
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER), AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.sheIs] going to want more than just a kiss..."
							+ "</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING), AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.sheIs] not going to let you go..."
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION = new DialogueNode("Transformed", "", true) {

		@Override
		public String getContent() {
			if(getSlime().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(getSlime(),
						"<p>"
							+ "As you struggle to recover from your transformation, [npc.name] pulls you to your [pc.feet], before pressing [npc.herself] against you and forcing you into a wet kiss."
							+ " You're powerless to resist [npc.her] advances, and as [npc.her] [npc.hands] reach around to give your [pc.ass+] a squeeze, [npc.she] laughs,"
							+ " [npc.speech(I'll turn you into my perfect little "
							+ getSlime().getPreferredBodyDescription("b")
							+ "! Now for the real fun!)]"
						+ "</p>");
			
			} else {
				return UtilText.parse(getSlime(),
						"<p>"
							+ "As you struggle to recover from your transformation, [npc.name] pulls you to your [pc.feet], before pushing you against a nearby wall and demanding that you hand over your money."
							+ " Reluctantly, you do as [npc.she] says, and, after giving [npc.herHim] some of your cash, [npc.she] roughly pushes you to the floor once more."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(You're not good enough for me to be interested in you just yet!)] [npc.she] growls down at you. [npc.speech(Come back and pay me another visit, <i>or else</i>! I'm going to turn you into my perfect little "
								+getSlime().getPreferredBodyDescription("b")+"!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and runs off, leaving you panting and sweating as you attempt to recover from the transformations that were just forced upon you..."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getSlime().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.sheIs] probably not going to be content with just a kiss..."
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER), AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.sheIs] going to want more than just a kiss..."
							+ "</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING), AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.sheIs] not going to let you go..."
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
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
			if(!getSlime().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
				return UtilText.parse(getSlime(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a thankful sob as [npc.she] realises that you've finished."
							+ " [npc.She] starts frantically gathering [npc.her] belongings, obviously quite keen to make [npc.her] exit before you decide to do anything else..."
						+ "</p>");
				
			} else {
				if(Sex.getNumberOfOrgasms(getSlime()) >= getSlime().getOrgasmsBeforeSatisfied()) {
					return UtilText.parse(getSlime(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfOrgasms(getSlime()) > 1?"s":"")+"."
								+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you realise that you gave [npc.herHim] exactly what [npc.she] wanted."
							+ "</p>");
				} else {
					return UtilText.parse(getSlime(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
								+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getSlime(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getSlime());
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
			return UtilText.parse(getSlime(),
					"<p>"
						+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink to the floor, totally worn out from [npc.her] dominant treatment of you."
						+ " [npc.She] looks down at you, and you glance up to see a very satisfied smile cross [npc.her] face."
						+ " [npc.She] leans down and pats you on the head."
						+ " [npc.speech(We should do this again some time!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, [npc.she] walks off, leaving you panting on the floor."
						+ " It takes a little while for you to recover from your ordeal, but eventually you feel strong enough to get your things in order and carry on your way."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
