package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.Litter;
import com.lilithsthrone.game.character.PregnancyPossibility;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.npcDialogue.elemental.ElementalDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.SMMasturbation;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia, tukaima
 */
public class PhoneDialogue {
	
	private static List<GameCharacter> charactersEncountered;
	
	private static StringBuilder journalSB;
	public static final DialogueNode MENU = new DialogueNode("Phone home screen", "Phone", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
//			if(Main.game.isInGlobalMap()) {
//				UtilText.nodeContentSB.append(RenderingEngine.ENGINE.getFullWorldMap());
//			} else {
//				UtilText.nodeContentSB.append(RenderingEngine.ENGINE.getFullMap(Main.game.getPlayer().getWorldLocation(), true));
//			}
			
			UtilText.nodeContentSB.append("<p>You pull out your phone and tap in the unlock code.</p>");
			
			if(Main.game.isInNewWorld()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+"Using your powerful aura, you've managed to figure out a way to channel the arcane into charging the battery of your phone, although considering that it's the only one in this world, it's not much use for calling anyone."
							+ " Instead, you're using it as a way to store information about things you've discovered in this strange new world."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						(Main.game.getPlayer().isMainQuestUpdated() || Main.game.getPlayer().isSideQuestUpdated() || Main.game.getPlayer().isRelationshipQuestUpdated())
							?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Quests</span>"
							:"Quests",
						"Open your planner to view your current quests.", PLANNER_MAIN){
					@Override
					public void effects() {
						Main.game.getPlayer().setMainQuestUpdated(false);
					}
				};
				
			} else if (index == 2) {
				return new Response(
						Main.getProperties().hasValue(PropertyValue.levelUpHightlight)
							? "<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Perk Tree</span>"
							:"Perk Tree",
						"View your character page.", CHARACTER_PERK_TREE) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.levelUpHightlight, false);
					}
				};
				
			} else if (index == 3) {
				return new Response("Spells", "View your spells page.", SpellManagement.CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						SpellManagement.setSpellOwner(Main.game.getPlayer(), MENU);
					}
				};
				
			} else if (index == 4) {
				return new Response("Fetishes", "View your fetishes page.", CHARACTER_FETISHES);
				
			} else if (index == 5) {
				return new Response("Stats", "Take a detailed look at your stats.", CHARACTER_STATS);
				
			} else if (index == 6) {
				return new Response("Selfie", "Take a selfie to get a good view of yourself.", CHARACTER_APPEARANCE);
				
			} else if (index == 7) {
				if(Main.game.getPlayer().getCharactersEncountered().isEmpty()) {
					return new Response("Contacts", "You haven't met anyone yet!", null);
				} else {
					return new Response("Contacts", "Even though you can't call anyone, on account of there being no phones in this world, you've still kept a record of all the people you've come into contact with.", CONTACTS) {
						@Override
						public void effects() {
							Main.game.getPlayer().sortCharactersEncountered();
							charactersEncountered = Main.game.getPlayer().getCharactersEncounteredAsGameCharacters(false);
						}
					};
				}
				
			} else if (index == 8) {
				return new Response(
						(Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered)
								|| Main.getProperties().hasValue(PropertyValue.newClothingDiscovered)
								|| Main.getProperties().hasValue(PropertyValue.newItemDiscovered)
								|| Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))
							? "<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Encyclopedia</span>"
							:"Encyclopedia",
						"Have a look at all the different items and races you've discovered so far.", ENCYCLOPEDIA){
					@Override
					public void effects() {
						resetContentForRaces();
					}
				};
				
			} else if (index == 9) {
				if(Main.game.getPlayer().isAbleToSelfTransform()) {
					return new Response("Transform",
							"Transform your body.",
							BodyChanging.BODY_CHANGING_CORE) {
						@Override
						public void effects() {
							BodyChanging.setTarget(Main.game.getPlayer());
						}
					};
				} else {
					return new Response("Transform", Main.game.getPlayer().getUnableToTransformDescription(), null);
				}
				
			} else if (index == 10) {
				return new Response("Maps", "Take a look at maps of all the places you've visited.", MAP) {
					@Override
					public void effects() {
						worldTypeMap = Main.game.getPlayer().getWorldLocation();
					}
				};
				
			} else if (index == 11) {
				if(Main.game.isSavedDialogueNeutral()) {
					return new Response("Combat Moves", "Adjust the core combat moves you are prepared to perform in combat.", CombatMovesSetup.COMBAT_MOVES_CORE) {
						@Override
						public void effects() {
							CombatMovesSetup.setTarget(Main.game.getPlayer(), PhoneDialogue.MENU);
						}
					};
				} else {
					return new Response("Combat Moves", "You are too busy to change your core combat moves.", null);
				}
				
			} else if (index == 12) {
				if(!Main.game.isSavedDialogueNeutral()) {
						return new Response("Masturbate", "You are too busy to masturbate right now. (Can only be performed in a neutral scene.)", null);
						
				} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
					return new Response("Masturbate", Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getValue(), null);
					
				} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
					return new Response("Masturbate",
							"As you do not have the [style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], you are not comfortable with masturbating in a place where people could see you!",
							null);
					
				} else {
					return new ResponseSex("Masturbate",
							"Decide to take a break from what you're currently doing in order to masturbate.",
							true,
							true,
							new SMMasturbation(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMasturbation.STANDING))),
							Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)
								?Main.game.getPlayer().getParty()
								:null,
							null,
							AFTER_MASTURBATION,
							UtilText.parseFromXMLFile("misc/misc", "MASTURBATION"));
				}
				
			} else if (index == 13){
				if(!Main.game.isSavedDialogueNeutral()) {
					return new Response("Loiter", "You can only loiter to pass the time when in a neutral dialogue scene!", null);
				}
				if(Main.game.getPlayerCell().getPlace().isDangerous()) {
					return new Response("Loiter", "You can only loiter to pass the time when in a safe area!", null);
				}
				if(!Main.game.getPlayerCell().getType().isLoiteringEnabled()) {
					return new Response("Loiter", "This is not a suitable place in which to be loitering about!", null);
				}
				return new Response("Loiter", "THink about loitering in this area for an as-yet undetermined length of time.", LOITER_SELECTION);
				
			} else if (index == 14){
				if(!Main.game.getPlayer().isElementalSummoned()) {
					if(Main.game.getPlayer().hasDiscoveredElemental()) {
						return new Response("[el.Name]",
								"You have not summoned [el.name], so you cannot talk to [el.herHim]!"
										+ "<br/>[style.italicsMinorGood(You can summon your elemental from your 'Spells' screen!)]",
								null);
					}
					return new Response("Elemental",
							"You have not summoned your elemental, so you cannot talk to them..."
									+ "<br/>[style.italicsMinorGood(You can summon your elemental by learning an elemental-summoning spell and casting it from your 'Spells' screen!)]",
							null);
				}
				return new Response("[el.Name]",
						"Spend some time talking with [el.name].",
						ElementalDialogue.ELEMENTAL_START);
				
			} else if (index == 0){
				return new ResponseEffectsOnly("Back", "Put your phone away."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent(false);
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode AFTER_MASTURBATION = new DialogueNode("Finished", "You've had enough of masturbating for now.", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/misc", "AFTER_MASTURBATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way...", Main.game.getDefaultDialogue(false));

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode PLANNER_MAIN = new DialogueNode("Planner", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			// Main Quests:
			QuestLine questLine = QuestLine.MAIN;
			List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
			int index = questList.size()-1;
			Quest q = questList.get(index);
			
			if (Main.game.getPlayer().isQuestCompleted(questLine)) {
				journalSB.append(
						"<details open>"
						+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
							+ "Completed - " + questLine.getName()
						+ "</summary>");
				journalSB.append(getQuestBoxDiv(q, true));
				
			} else{
				journalSB.append(
						"<details open>"
							+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
								+ questLine.getName()
							+ "</summary>");
				journalSB.append(getQuestBoxDiv(q, false));
			}
			
			index--;
				
			while(index>=0) {
				q = questList.get(index);
				journalSB.append(getQuestBoxDiv(q, true));
				index--;
			}

			journalSB.append("</details>");

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", null);
				
			} else if (index == 2) {
				return new Response(
						(Main.game.getPlayer().isSideQuestUpdated()
							?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Side quests</span>"
							:"Side quests"),
						"View your side quests.",
						PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
				
			} else if (index == 3) {
				return new Response(
						(Main.game.getPlayer().isRelationshipQuestUpdated()
							?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Romance quests</span>"
							:"Romance quests"),
						"View your romance quests.",
						PLANNER_RELATIONSHIP){
					@Override
					public void effects() {
						Main.game.getPlayer().setRelationshipQuestUpdated(false);
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode PLANNER_SIDE = new DialogueNode("Planner", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean sideQuestsFound = false;
			
			// Side Quests:
			for (QuestLine questLine : Main.game.getPlayer().getQuests().keySet()) {
				if(questLine.getType()==QuestType.SIDE) {
					sideQuestsFound = true;

					List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
					int index = questList.size()-1;
					Quest q = questList.get(index);
					
					if (Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
									+ "Completed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, true));
						
					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, false));
					}
					
					index--;
						
					while(index>=0) {
						q = questList.get(index);
						journalSB.append(getQuestBoxDiv(q, true));
						index--;
					}
	
					journalSB.append("</details>");
				}
			}
			for(Entry<QuestLine, Quest> questsFailed : Main.game.getPlayer().getQuestsFailed().entrySet()) {
				if(questsFailed.getKey().getType()==QuestType.SIDE) {
					sideQuestsFound = true;
					
					journalSB.append(
							"<details open>"
								+ "<summary class='quest-title' style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>"
									+ "Failed - "+questsFailed.getKey().getName()
								+ "</summary>");
					journalSB.append(getQuestBoxDiv(questsFailed.getValue(), false));
					
					journalSB.append("</details>");
				}
			}
			
			if(!sideQuestsFound) {
				journalSB.append("<div class='subTitle'>You haven't got any side quests!</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response("Side quests", "View your side quests.", null);
			} else if (index == 3) {
				return new Response(
						(Main.game.getPlayer().isRelationshipQuestUpdated()
						?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Romance quests</span>"
							:"Romance quests"),
							"View your romance quests.",
						PLANNER_RELATIONSHIP){
					@Override
					public void effects() {
						Main.game.getPlayer().setRelationshipQuestUpdated(false);
					}
				};
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode PLANNER_RELATIONSHIP = new DialogueNode("Planner", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean relationshipQuestFound = false;
			
			// Side Quests:
			for (QuestLine questLine : Main.game.getPlayer().getQuests().keySet()) {
				if(questLine.getType()==QuestType.RELATIONSHIP) {
					relationshipQuestFound = true;
					
					List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
					int index = questList.size()-1;
					Quest q = questList.get(index);
					
					if(Main.game.getPlayer().isQuestFailed(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + PresetColour.GENERIC_TERRIBLE.toWebHexString() + ";'>"
									+ "Failed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDivFailed(Main.game.getPlayer().getQuestsFailed().get(questLine)));
//						journalSB.append(getQuestBoxDiv(q, true)); // Do not append, as this was the failed Quest
						
					} else if(Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
									+ "Completed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, true));
						
					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, false));
					}
					
					index--;
						
					while(index>=0) {
						q = questList.get(index);
						journalSB.append(getQuestBoxDiv(q, true));
						index--;
					}
	
					journalSB.append("</details>");
				}
			}
			
			if(!relationshipQuestFound) {
				journalSB.append("<div class='subTitle'>You haven't got any romance quests!</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response((Main.game.getPlayer().isSideQuestUpdated()
						?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Side quests</span>"
						:"Side quests"), "View your side quests.", PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
			} else if (index == 3) {
				return new Response("Romance quests", "View your romance quests.", null);
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String getQuestBoxDivFailed(Quest q) {
		return "<div class='quest-box'>"
				+ getLevelAndExperienceHTML(q, true)
				+ "<h6 style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";text-align:center;'>"
						+ "<b>Failed - "+ q.getName() + "</b>"
				+ "</h6>"
				+ "<p style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
					+ q.getCompletedDescription()
				+ "</p>" 
			+ "</div>";
	}
	
	private static String getQuestBoxDiv(Quest q, boolean completed) {
		if(q==Quest.SIDE_UTIL_COMPLETE) {
			return "<div class='quest-box'>"
					+ "<h6 style='color:" + q.getQuestType().getColour().getShades()[1] + ";text-align:center;'>"
							+ "<b>Completed - "+ q.getName() + "</b>"
					+ "</h6>"
				+ "</div>";
		}
		
		if(completed) {
			return "<div class='quest-box'>"
					+ getLevelAndExperienceHTML(q, completed)
					+ "<h6 style='color:" + q.getQuestType().getColour().getShades()[1] + ";text-align:center;'>"
							+ "<b>Completed - "+ q.getName() + "</b>"
					+ "</h6>"
					+ "<p style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
						+ q.getCompletedDescription()
					+ "</p>" 
				+ "</div>";
			
		} else {
			return "<div class='quest-box'>"
					+ getLevelAndExperienceHTML(q, completed)
					+ "<h6 style='color:" + q.getQuestType().getColour().toWebHexString()+ "; text-align:center;'>"
						+ "<b>" + q.getName() + "</b>"
					+ "</h6>"
					+ "<p style='text-align:center;'>"
						+ q.getDescription()
					+ "</p>"
				+ "</div>";
		}
	}
	
	private static String getLevelAndExperienceHTML(Quest q, boolean completed) {
		if(q==Quest.SIDE_UTIL_COMPLETE) {
			return "";
		}
		
		if (!completed) {
			if(q.getLevel() <= Main.game.getPlayer().getLevel() - 3) {
				return "<b class='quest-extra level' style='color:"+  PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
				
			} else if (q.getLevel() >= Main.game.getPlayer().getLevel() + 3) {
				return "<b class='quest-extra level' style='color:"+  PresetColour.GENERIC_BAD.toWebHexString() + ";'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
				
			} else {
				return "<b class='quest-extra level'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
			}
			
		} else {
			return "<b class='quest-extra level' style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>Level " + q.getLevel() + "</b>"
					+ "<b class='quest-extra experience' style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
		}
	}
	

	public static final DialogueNode CHARACTER_APPEARANCE = new DialogueNode("Selfie picture", "Take a selfie", true) {

		@Override
		public String getContent() {
//			return Main.game.getPlayer().getBodyDescription();
			return Main.game.getPlayer().getCharacterInformationScreen(true);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String getAttributeDisplayValue(Attribute att) {
		String valueForDisplay = Units.number(Main.game.getPlayer().getAttributeValue(att));
		
		if(att.isInfiniteAtUpperLimit() && Main.game.getPlayer().getAttributeValue(att)>=att.getUpperLimit()) {
			valueForDisplay = UtilText.getInfinitySymbol(true);
		}
		
		return valueForDisplay;
	}
	
	public static final DialogueNode CHARACTER_STATS = new DialogueNode("Character Stats", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			AbstractStatusEffect physiqueSE = PhysiqueLevel.getPhysiqueLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)).getRelatedStatusEffect();
			AbstractStatusEffect arcaneSE = IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)).getRelatedStatusEffect();
			AbstractStatusEffect corruptionSE = CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)).getRelatedStatusEffect();
					
			UtilText.nodeContentSB.append(
				"<div class='container-full-width'>"
					+ statAttributeHeader()
					
					+ "<p style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'><b>Core Attributes</b></p>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_PHYSIQUE,
							"'<b style='color:"+physiqueSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(physiqueSE.getName(Main.game.getPlayer()))+"</b>' effect gained")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_ARCANE,
							"'<b style='color:"+arcaneSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(arcaneSE.getName(Main.game.getPlayer()))+"</b>' effect gained")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_CORRUPTION,
							"'<b style='color:"+corruptionSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(corruptionSE.getName(Main.game.getPlayer()))+"</b>' effect gained")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.HEALTH_MAXIMUM,
							"When reduced to 0, you lose combat")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MANA_MAXIMUM,
							"Used as a resource for casting spells")
//				+"</div>"
//				
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'><b>Misc. Attributes</b></p>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.FERTILITY,
							"Increases pregnancy chance")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.VIRILITY,
							"Increases impregnation chance")

					+ "<div class='container-full-width' style='text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>"
						+ "<b style='color:"+PresetColour.BASE_PINK_LIGHT.toWebHexString()+";'>Pregnancy calculation:</b>"
								+ "<br/>"
								+ "<i>"+GameCharacter.PREGNANCY_CALCULATION+"</i>"
					+ "</div>"
//				+"</div>"
//					
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'><b>Combat Attributes</b></p>"
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.CRITICAL_DAMAGE,
							"Deals <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.CRITICAL_DAMAGE))+"%</b> normal damage",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.ENERGY_SHIELDING,
							"<b>"+getAttributeDisplayValue(Attribute.ENERGY_SHIELDING)+"</b> Health Block/Turn",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.SPELL_COST_MODIFIER,
							"Reduces spell cost by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.SPELL_COST_MODIFIER))+"%</b>")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_SPELLS,
							"Increases spell damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_SPELLS))+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_UNARMED,
							"Increases unarmed damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_UNARMED))+"%</b>",
							true)
					

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_MELEE_WEAPON,
							"Increases melee weapon damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_MELEE_WEAPON))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_RANGED_WEAPON,
							"Increases ranged weapon damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_RANGED_WEAPON))+"%</b>",
							true)
					
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_PHYSICAL,
							"Increases physical damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_PHYSICAL))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_PHYSICAL,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_PHYSICAL)+"</b> Physical Block/Turn",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_FIRE,
							"Increases fire damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_FIRE))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_FIRE,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_FIRE)+"</b> Fire Block/Turn",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_ICE,
							"Increases ice damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_ICE))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_ICE,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_ICE)+"</b> Ice Block/Turn",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_POISON,
							"Increases poison damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_POISON))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_POISON,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_POISON)+"</b> Poison Block/Turn",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_LUST,
							"Increases lust damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_LUST,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_LUST)+"</b> Lust Block/Turn",
							true)

//				+"</div>"
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'><b>Racial Damage Attributes</b></p>");
			
			List<Attribute> encounteredAttributes = new ArrayList<>();
			for(Subspecies subspecies : Subspecies.values()) {
				Attribute damageModifier = subspecies.getDamageMultiplier();
				if(!encounteredAttributes.contains(damageModifier)) {
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), damageModifier,
									"Increases damage vs "+subspecies.getNamePlural(null)+" by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(damageModifier))+"%</b>",
									true));
					encounteredAttributes.add(damageModifier);
				}
			}
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", null);
			
			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static String getBodyStatsPanel(GameCharacter character) {
		boolean knowsNipples = character.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer());
		boolean knowsCrotchNipples = character.isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer());
		boolean knowsPenis = character.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer());
		boolean knowsVagina = character.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer());
		boolean knowsAnus = character.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer());
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='margin-bottom:0;'>");
		rowCount = 0;
		
		sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Body Attributes")
				+ statHeader()
				+ statRow(PresetColour.ANDROGYNOUS, "Femininity",
						PresetColour.TEXT, String.valueOf(character.getFemininityValue()),
						character.getFemininity().getColour(), Util.capitaliseSentence(character.getFemininity().getName(false)))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Height",
						PresetColour.TEXT, Units.size(character.getHeightValue()),
						character.getHeight().getColour(), Util.capitaliseSentence(character.getHeight().getDescriptor()))
				+ statRow(PresetColour.MUSCLE_THREE, "Muscle Definition",
						PresetColour.TEXT, String.valueOf(character.getMuscleValue()),
						character.getMuscle().getColour(), Util.capitaliseSentence(character.getMuscle().getName(false)))
				+ statRow(PresetColour.BODY_SIZE_THREE, "Body Size",
						PresetColour.TEXT, String.valueOf(character.getBodySizeValue()),
						character.getBodySize().getColour(), Util.capitaliseSentence(character.getBodySize().getName(false)))
				+ statRow(character.getBodyShape().toWebHexStringColour(), "Body Shape",
						PresetColour.TEXT,
						"<b style='color:"+character.getMuscle().getColour().toWebHexString()+";'>"+character.getMuscleValue()+"</b>"
								+ " <b>|</b> <b style='color:"+character.getBodySize().getColour().toWebHexString()+";'>"+character.getBodySizeValue()+"</b>",
						character.getBodyShape().toWebHexStringColour(), Util.capitaliseSentence(character.getBodyShape().getName(false))));

		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(character.hasTail()) {
			if(Main.game.isPenetrationLimitationsEnabled()) {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Tail Attributes")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Length | Used for penetration",
							PresetColour.TEXT, Units.size(character.getTailLength(false))+" | "+Units.size(character.getTailLength(true)),
							PresetColour.GENERIC_SEX, "N/A"));
			} else {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Tail Attributes")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Length",
							PresetColour.TEXT, Units.size(character.getTailLength(false)),
							PresetColour.GENERIC_SEX, "N/A"));
			}
			sb.append(
					statRow(PresetColour.TRANSFORMATION_GENERIC,
						"Diameter at lengths",
						PresetColour.TEXT,
						"[style.colourSize4("+Units.size(character.getTailDiameter(0))+")]"
							+"<br/>[style.colourSize3("+Units.size(character.getTailDiameter(character.getTailLength(false)*0.33f))+")]"
							+"<br/>[style.colourSize2("+Units.size(character.getTailDiameter(character.getTailLength(false)*0.66f))+")]"
							+"<br/>[style.colourSize1("+Units.size(character.getTailDiameter(character.getTailLength(false)))+")]",
						PresetColour.TRANSFORMATION_GENERIC,
						"[style.colourSize4(Base)]"
							+ "<br/>[style.colourSize3(33%)]"
							+ "<br/>[style.colourSize2(66%)]"
							+ "<br/>[style.colourSize1(Tip)]")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC,
							"Circumference at lengths",
							PresetColour.TEXT,
							"[style.colourSize4("+Units.size(character.getTailCircumference(0))+")]"
								+"<br/>[style.colourSize3("+Units.size(character.getTailCircumference(character.getTailLength(false)*0.33f))+")]"
								+"<br/>[style.colourSize2("+Units.size(character.getTailCircumference(character.getTailLength(false)*0.66f))+")]"
								+"<br/>[style.colourSize1("+Units.size(character.getTailCircumference(character.getTailLength(false)))+")]",
							PresetColour.TRANSFORMATION_GENERIC,
							"[style.colourSize4(Base)]"
								+ "<br/>[style.colourSize3(33%)]"
								+ "<br/>[style.colourSize2(66%)]"
								+ "<br/>[style.colourSize1(Tip)]"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Tail Attributes")
					+ statRow(PresetColour.TEXT, UtilText.parse(character, "[npc.NameHasFull] no tail...")));
		}

		
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		sb.append(statRowHeader(PresetColour.TRANSFORMATION_GREATER, "Head & Throat Attributes")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Hair Length",
						PresetColour.TEXT, Units.size(character.getHairRawLengthValue()),
						character.getHairLength().getColour(), Util.capitaliseSentence(character.getHairLength().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Tongue Length",
						PresetColour.TEXT, Units.size(character.getTongueLengthValue()),
						PresetColour.TRANSFORMATION_GENERIC, Util.capitaliseSentence(character.getTongueLength().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Throat Wetness",
						PresetColour.TEXT, String.valueOf(character.getFaceWetness().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceWetness().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Throat Capacity Diameter",
						PresetColour.TEXT, Units.size(character.getFaceRawCapacityValue()), //TODO
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceCapacity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Throat Depth",
						PresetColour.TEXT, String.valueOf(character.getFaceDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceDepth().getDescriptor()) + " ("+(Math.round(character.getFaceDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Throat Depth Limit",
						PresetColour.GENERIC_MINOR_GOOD, Units.size(character.getFaceMaximumPenetrationDepthComfortable()),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Throat Depth Limit",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":Units.size(character.getFaceMaximumPenetrationDepthUncomfortable()),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Throat Elasticity",
						PresetColour.TEXT, String.valueOf(character.getFaceElasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceElasticity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Throat Plasticity",
						PresetColour.TEXT, String.valueOf(character.getFacePlasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFacePlasticity().getDescriptor())));
				
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Breast Attributes")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Cup Size",
						PresetColour.TEXT, String.valueOf(character.getBreastRawSizeValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getBreastSize().getCupSizeName()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Count",
						PresetColour.TEXT, String.valueOf(character.getBreastRows()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(Util.capitaliseSentence(Util.intToString(character.getBreastRows()))+" pair"+(character.getBreastRows()==1?"":"s")))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Storage",
						PresetColour.TEXT, !knowsNipples?"Unknown":Units.fluid(character.getBreastRawMilkStorageValue()),
						PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getBreastMilkStorage().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Regeneration Per Breast",
						PresetColour.TEXT, !knowsNipples?"Unknown":Units.fluid(character.getLactationRegenerationPerSecond(false)*60)+"/minute",
						PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getBreastLactationRegeneration().getName()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Regeneration Total",
						PresetColour.TEXT, !knowsNipples?"Unknown":Units.fluid(character.getLactationRegenerationPerSecond(true)*60)+"/minute",
						PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getBreastLactationRegeneration().getName()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Capacity",
						PresetColour.TEXT, !knowsNipples?"Unknown":Units.size(character.getNippleRawCapacityValue()),
						PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getNippleCapacity().getDescriptor()))
				+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Nipple Depth",
						PresetColour.TEXT, String.valueOf(character.getNippleDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getNippleDepth().getDescriptor()) + " ("+(Math.round(character.getNippleDepth().getDepthPercentage()*100))+"%)")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Nipple Depth Limit",
						PresetColour.GENERIC_MINOR_GOOD, (!knowsNipples?"Unknown":Units.size(character.getNippleMaximumPenetrationDepthComfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Nipple Depth Limit",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(!knowsNipples?"Unknown":Units.size(character.getNippleMaximumPenetrationDepthUncomfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Elasticity",
						PresetColour.TEXT, !knowsNipples?"Unknown":String.valueOf(character.getNippleElasticity().getValue()),
						PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getNippleElasticity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Plasticity",
						PresetColour.TEXT, !knowsNipples?"Unknown":String.valueOf(character.getNipplePlasticity().getValue()),
						PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getNipplePlasticity().getDescriptor())));
		
		if(character.hasBreastsCrotch()) {
			sb.append("<span style='height:16px;width:100%;float:left;'></span>");
			rowCount = 0;
			
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, (character.getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs"))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Size",
								PresetColour.TEXT, !character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples?"Unknown":String.valueOf(character.getBreastCrotchRawSizeValue()),
								PresetColour.GENERIC_SEX,
								!character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples
									?"Unknown"
									:(character.getBreastCrotchShape()==BreastShape.UDDERS
										?Util.capitaliseSentence(character.getBreastCrotchSize().getDescriptor())
										:Util.capitaliseSentence(character.getBreastCrotchSize().getCupSizeName())))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Count",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getBreastCrotchRows()),
								PresetColour.GENERIC_SEX,
								!knowsCrotchNipples
									?"Unknown"
									:(character.getBreastCrotchRows()==0
										?"A single udder"
										:Util.capitaliseSentence(Util.intToString(character.getBreastCrotchRows()))+" pair"+(character.getBreastCrotchRows()==1?"":"s")))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Storage",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":Units.fluid(character.getBreastCrotchRawMilkStorageValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getBreastCrotchMilkStorage().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Regeneration Per Crotch-boob",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":Units.fluid(character.getCrotchLactationRegenerationPerSecond(false)*60)+"/minute",
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getBreastCrotchLactationRegeneration().getName()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Regeneration Total",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":Units.fluid(character.getCrotchLactationRegenerationPerSecond(true)*60)+"/minute",
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getBreastCrotchLactationRegeneration().getName()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Capacity",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchRawCapacityValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getNippleCrotchCapacity().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Nipple Depth",
								PresetColour.TEXT, String.valueOf(character.getNippleCrotchDepth().getValue()),
								PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getNippleCrotchDepth().getDescriptor()) + " ("+(Math.round(character.getNippleCrotchDepth().getDepthPercentage()*100))+"%)")
						+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleCrotchRawCapacityValue()>0
							?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Nipple Depth Limit",
								PresetColour.GENERIC_MINOR_GOOD, (!knowsCrotchNipples?"Unknown":Units.size(character.getNippleCrotchMaximumPenetrationDepthComfortable())),
								PresetColour.TEXT, "N/A")
							:"")
						+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleCrotchRawCapacityValue()>0
							?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Nipple Depth Limit",
								PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(!knowsCrotchNipples?"Unknown":Units.size(character.getNippleCrotchMaximumPenetrationDepthUncomfortable())),
								PresetColour.TEXT, "N/A")
							:"")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Elasticity",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchElasticity().getValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getNippleCrotchElasticity().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Plasticity",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchPlasticity().getValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getNippleCrotchPlasticity().getDescriptor())));
		}
		
		sb.append( "<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(!knowsPenis) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Penis Attributes")
					+ statRow(PresetColour.TEXT_GREY, "Unknown!"));
			
		} else if(character.hasPenis()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Penis Attributes")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Length",
						PresetColour.TEXT, (Units.size(character.getPenisRawSizeValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getPenisSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Diameter | Circumference",
						PresetColour.TEXT, (Units.size(character.getPenisDiameter())+" | "+Units.size(character.getPenisCircumference())),
						PresetColour.TEXT_GREY, "N/A")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Testicle Size",
						PresetColour.TEXT, (String.valueOf(character.getTesticleSize().getValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getTesticleSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Cum Storage",
						PresetColour.TEXT, (Units.fluid(character.getPenisRawCumStorageValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getPenisCumStorage().getDescriptor())))
				+ (Main.getProperties().hasValue(PropertyValue.cumRegenerationContent) ? statRow(PresetColour.TRANSFORMATION_GENERIC, "Cum Regeneration",
						PresetColour.TEXT, Units.fluid(character.getCumRegenerationPerSecond()*60)+"/minute",
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getPenisCumProductionRegeneration().getName()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Cum Expulsion (% of stored cum)",
						PresetColour.TEXT, String.valueOf(character.getPenisRawCumExpulsionValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getPenisCumExpulsion().getDescriptor())) : ""));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Penis Attributes")
					+ statRow(PresetColour.TEXT, UtilText.parse(character, "[npc.NameHasFull] no penis...")));
		}
			
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(!knowsVagina) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Vagina Attributes")
					+ statRow(PresetColour.TEXT_GREY, "Unknown!"));
			
		} else if(character.hasVagina()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Vagina Attributes")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Clitoris Size",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Units.size(character.getVaginaRawClitorisSizeValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaClitorisSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Wetness",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaWetness().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaWetness().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Capacity",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Units.size(character.getVaginaRawCapacityValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaCapacity().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Depth",
						PresetColour.TEXT, String.valueOf(character.getVaginaDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getVaginaDepth().getDescriptor()) + " ("+(Math.round(character.getVaginaDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Depth Limit",
						PresetColour.GENERIC_MINOR_GOOD, (Units.size(character.getVaginaMaximumPenetrationDepthComfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Depth Limit",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(Units.size(character.getVaginaMaximumPenetrationDepthUncomfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Elasticity",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaElasticity().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaElasticity().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Plasticity",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaPlasticity().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaPlasticity().getDescriptor()))));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Vagina Attributes")
					+ statRow(PresetColour.TEXT, UtilText.parse(character, "[npc.NameHasFull] no vagina...")));
		}
			
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;

		if(!knowsAnus) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Anus Attributes")
					+ statRow(PresetColour.TEXT_GREY, "Unknown!"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Anus Attributes")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Wetness",
						PresetColour.TEXT, String.valueOf(character.getAssWetness().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssWetness().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Capacity",
						PresetColour.TEXT, Units.size(character.getAssRawCapacityValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssCapacity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Depth",
						PresetColour.TEXT, String.valueOf(character.getAssDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssDepth().getDescriptor()) + " ("+(Math.round(character.getAssDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Anus Depth Limit",
						PresetColour.GENERIC_MINOR_GOOD, (Units.size(character.getAssMaximumPenetrationDepthComfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Anus Depth Limit",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(Units.size(character.getAssMaximumPenetrationDepthUncomfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Elasticity",
						PresetColour.TEXT, String.valueOf(character.getAssElasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssElasticity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Plasticity",
						PresetColour.TEXT, String.valueOf(character.getAssPlasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssPlasticity().getDescriptor())));
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode CHARACTER_STATS_BODY = new DialogueNode("Body Stats", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Orifice Mechanics</summary>"
						
						+ "[style.boldSex(Capacity:)] An orifice's capacity determines the maximum diameter of penises and tails that can be comfortably inserted."
						+ " <b>Higher capacity values mean that the orifice can take thicker insertions without stretching.</b>"
						+ "<br/>- The diameter of a penis is calculated from a combination of their 'length' and 'girth' value, with additional modifiers (such as 'flared' or 'tapered') further altering the final value."
						+ "<br/>- The diameter of a tail is calculated from both its 'girth' value as well as the character's height."
						+ "<br/>- Capacity diameter values range from 0 [units.sizes] (extremely tight) to [units.sizes("+Math.round(Capacity.SEVEN_GAPING.getMaximumValue(false))+")] (gaping wide)."
						
						+ "<br/><br/>");
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				UtilText.nodeContentSB.append(
					"[style.boldSex(Depth:)] An orifice's depth determines the maximum length of penises and tails which can be comfortably or uncomfortably inserted into it."
						+ " <b>Higher depth values mean that longer penetrative objects can be accommodated before the character experiences discomfort.</b>"
						+ "<br/>- Comfortable/Uncomfortable depths of an orifice are based on the height of the character, and are then modified by the orifice's 'depth' value."
						+ "<br/>- Sexual partners who are not in the 'rough' sex-pace, and whose penises or tails are too long to be fully inserted into an orifice will stop at the limit of comfortable depth."
						+ "<br/>- Sexual partners who are in the 'rough' sex-pace will push past the comfortable orifice depth limit, causing the character discomfort."
						+ "<br/>- Size queens treat the 'uncomfortable' portion of orifice depth as being comfortable."
						+ "<br/>- Slimes and elementals always have the maximum depth value for each of their orifices."
						+ "<br/>- Depth values range from 0 ("+OrificeDepth.ZERO_EXTREMELY_SHALLOW.getDescriptor()+") to 7 ("+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+")."
						
						+ "<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("[style.boldSex(Elasticity:)] An orifice's elasticity determines how quickly it stretches out, and also has an influence on detecting whether a penetrative object is too big for the orifice."
						+ " If a partner's penis is too large for your orifice's capacity value, then your orifice will stretch out each turn during sex, with <b>higher elasticity values meaning that it stretches out quicker</b>."
						+ " <b>Higher elasticity values also have an increased tolerance for objects that would normally be too large for the orifice, allowing larger objects to be inserted before stretching begins</b>."
						+ "<br/>Elasticity values range from 0 (extremely resistant to being stretched out) to 7 (instantly stretching out)."
						
						+ "<br/><br/>"
						
					+ "[style.boldSex(Plasticity:)] An orifice's plasticity determines how quickly it recovers after being stretched out."
						+ " If your orifice has been stretched out during sex, <b>higher plasticity values mean that it will recover slower, with very high values meaning that it will never recover all of its original tightness</b>."
						+ "<br/>Plasticity values range from 0 (instantly returns to starting size after sex) to 7 (recovers none of its original size after sex)."
			+ "</details>"
					
			+ getBodyStatsPanel(Main.game.getPlayer()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", null);
			
			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CHARACTER_STATS_SEX = new DialogueNode("Sex Stats", "", true) {

		@Override
		public String getContent() {
			return "<div class='container-full-width' style='text-align:center; width:100%; padding:0; margin:4px 0;'>"
						+ "You have orgasmed [style.boldSex("+Main.game.getPlayer().getDaysOrgasmCount()+")] time"+(Main.game.getPlayer().getDaysOrgasmCount()==1?"":"s")
							+" today, bringing your total orgasm count to [style.boldSex("+Main.game.getPlayer().getTotalOrgasmCount()+")].<br/>"
						+ "Your record for most orgasms in one day is currently [style.boldArcane("+Main.game.getPlayer().getDaysOrgasmCountRecord()+")]."
					+ "</div>"
					
					+ sexStatHeader()
					
					+ sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Fingering",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
							-1,
							true)

					+ (Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Anal Fingering",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
									-1,
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
									-1,
									false)
							:"")
					
					+ sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Blowjobs",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
							true)
					
					+ sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Cunnilingus",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
							-1,
							false)
					
					+ (Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Anilingus",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
									-1,
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
									-1,
									true)
							:"")
					
					+ (Main.game.isFootContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Footjobs",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
									true)
							:"")
					
					+ sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Vaginal sex",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
							false)

					
					+ (Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Anal sex",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
									true)
							:"")

					+ (Main.game.isNipplePenEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Nipple penetration",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
									false)
							:"")

					+ (Main.game.isNipplePenEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Crotch Nipple penetration",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
									true)
							:"")
					
					+ (Main.game.isUrethraEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Penis Urethra penetration",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
									false)
							:"")

					+ (Main.game.isUrethraEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Vagina Urethra penetration",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
									true)
							:"");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", null);
			
			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CHARACTER_STATS_PREGNANCY = new DialogueNode("Pregnancy Stats", "", true) {

		private void OffspringHeaderDisplay(StringBuilder output, String word_one, String word_two, String color, int count) {
			output.append("<div class='extraAttribute-quarter'>");
			output.append(word_one);
			output.append("<br/>");
			output.append("<b style='color:").append(color).append(";'>");
			output.append(word_two);
			output.append("</b>");
			output.append("<br/>");
			output.append(count);
			output.append("</div>");
		}

		private boolean ChildMet(NPC npc) {
			return Main.game.getPlayer().getCharactersEncountered().contains(npc.getId());
		}

		private void OffspringTableLine(StringBuilder output, NPC npc) {
			boolean female = npc.isFeminine();
			String color = female ? PresetColour.FEMININE.toWebHexString() : PresetColour.MASCULINE.toWebHexString();
			String child_name = ChildMet(npc) ? npc.getName(true) : "Unknown";
			String race_color = npc.getRace().getColour().toWebHexString();
			String species_name = female
								? Util.capitaliseSentence(npc.getSubspecies().getSingularFemaleName(npc))
								: Util.capitaliseSentence(npc.getSubspecies().getSingularMaleName(npc));
			String mother = npc.getMother() == null ? "???" : (npc.getMother().isPlayer() ? "[style.colourExcellent(You)]" : npc.getMother().getName(true));
			String father = npc.getFather() == null ? "???" : (npc.getFather().isPlayer() ? "[style.colourExcellent(You)]" : npc.getFather().getName(true));
			Set<Relationship> extraRelationships = Main.game.getPlayer().getRelationshipsTo(npc, Relationship.Parent);
			boolean isGreyedOut = extraRelationships.isEmpty();
			List<String> relationships = extraRelationships.stream().map((relationship) -> relationship.getName(Main.game.getPlayer())).collect(Collectors.toList());
			if(npc.getMother()!=null && npc.getMother().isPlayer()) {
				relationships.add(0, "Mother");
				
				if(npc.getFather()!=null && npc.getFather().isPlayer()) {
					relationships.add(1, "father");
				}
				
			} else {
				relationships.add(0, "Father");
			}

			output.append("<tr>");
				output.append("<td style='min-width:100px;'>");
					output.append("<b style='color:").append(color).append(";'>");
						output.append(child_name);
					output.append("</b>");
				output.append("</td>");
				output.append("<td style='min-width:100px;'>");
					output.append("<b style='color:").append(race_color).append(";'>");
						output.append(species_name);
					output.append("</b>");
				output.append("</td>");
				output.append("<td style='min-width:100px;'>");
					output.append("<b>");
						output.append(mother);
					output.append("</b>");
				output.append("</td>");
				output.append("<td style='min-width:100px;'>");
					output.append("<b>");
						output.append(father);
					output.append("</b>");
				output.append("</td>");
				output.append("<td style='min-width:100px;'>");
					output.append("<b>");
						output.append(
								isGreyedOut
									?"[style.boldDisabled("+Util.stringsToStringList(relationships, false)+")]"
									:Util.stringsToStringList(relationships, false));
					output.append("</b>");
				output.append("</td>");
			output.append("</tr>");
		}

		@Override
		public String getContent() {
			
			int sonsBirthed=0;
			int daughtersBirthed=0;
			int sonsFathered=0;
			int daughtersFathered=0;
			int childrenMet = 0;
			
			for (Litter litter : Main.game.getPlayer().getLittersBirthed()){
				sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
				daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
			}
			for (Litter litter : Main.game.getPlayer().getLittersFathered()){
				sonsFathered+=(litter.isSelfImpregnation()?0:litter.getSonsFromMother()+litter.getSonsFromFather());
				daughtersFathered+=(litter.isSelfImpregnation()?0:litter.getDaughtersFromMother()+litter.getDaughtersFromFather());
			}
			
			UtilText.nodeContentSB.setLength(0);

			OffspringHeaderDisplay(UtilText.nodeContentSB, "Mothered", "Sons", PresetColour.MASCULINE.toWebHexString(), sonsBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Mothered", "Daughters", PresetColour.FEMININE.toWebHexString(), daughtersBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Fathered", "Sons", PresetColour.MASCULINE.toWebHexString(), sonsFathered);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Fathered", "Daughters", PresetColour.FEMININE.toWebHexString(), daughtersFathered);

			for (NPC npc : Main.game.getOffspring(false)) {
				childrenMet += ChildMet(npc) ? 1 : 0;
			}
			int totalChildren = (sonsBirthed+daughtersBirthed+sonsFathered+daughtersFathered);
			int percentageMet = totalChildren == 0 ? 100 : (100 * childrenMet / totalChildren);

			UtilText.nodeContentSB.append(
					"<div class='subTitle'>Total offspring: "+ totalChildren+" (Children met: "+ percentageMet +"%)</div>"
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					
					+ pregnancyDetails()

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+"<div class='subTitle'>Offspring list</div>"
					+ "<div class='container-full-width' style='text-align:center;'>"
					
					+ "<table align='center'>"
					+ "<tr><th>Name</th><th>Race</th><th>Mother</th><th>Father</th><th>You are their:</th></tr>"
					+ "<tr style='height:8px;'></tr>");
			
			for(NPC npc : Main.game.getOffspring(false)) {
				OffspringTableLine(UtilText.nodeContentSB, npc);
			}
			
			UtilText.nodeContentSB.append("</table></div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", null);
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	private static String sexStatHeader() {
		return "<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; font-weight:bold; text-align:center;'>"
					+ "<div class='container-full-width' style='width:33.3%; padding:0; margin:0;'>"
						+ "Type"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "Performed"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "Creampies<br/>Given"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "Received"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "Creampies<br/>Received"
					+ "</div>"
				+ "</div>";
	}
	
	private static String sexStatRow(Colour colour, String name, int given, int loadsGiven, int received, int loadsReceived, boolean light) {
		return 
//				"<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; text-align:center;'>"
				"<div class='container-full-width inner' style='text-align:center; margin-bottom:0;"+(light?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='float:left; width:33.3%; padding:0; margin:0;'>"
						+ "<span style='color:" + colour.toWebHexString() + ";'>" + name + "</span>"
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ given
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ (loadsGiven < 0 ? "<span class='option-disabled'>-</span>" : loadsGiven)
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ received
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ (loadsReceived < 0 ? "<span class='option-disabled'>-</span>" : loadsReceived) 
					+ "</div>"
				+ "</div>";
	}

	private static String pregnancyDetails() {
		StringBuilder contentSB = new StringBuilder();

		// Mothered children:
		
		boolean noPregnancies=true;

		contentSB.append("<div class='subTitle'>Mothered children</div>");
		
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_1)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_2)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)){
			
			contentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "[style.boldBad(Ongoing pregnancy)]"
					+ "<br/>"
					+ "[style.bold(Possible partners:)]");
			
			for(PregnancyPossibility pp : new ArrayList<>(Main.game.getPlayer().getPotentialPartnersAsMother())){
				if(pp.getFather()!=null) {
					contentSB.append(UtilText.parse(pp.getFather(),
							"<br/><b>[npc.Name(A)] (</b>"
								+ (!pp.getFather().getRaceStage().getName().isEmpty()
										?"<b style='color:"+pp.getFather().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(pp.getFather().getRaceStage().getName())+"</b> "
										:"")
								+ "<b style='color:"+pp.getFather().getRace().getColour().toWebHexString()+";'>"
								+ (pp.getFather().getGender().isFeminine()
										?Util.capitaliseSentence(pp.getFather().getSubspecies().getSingularFemaleName(pp.getFather()))
										:Util.capitaliseSentence(pp.getFather().getSubspecies().getSingularMaleName(pp.getFather())))
								+ "</b><b>) Probability: "));
					
					if (pp.getProbability() <= 0) {
						contentSB.append("None");
					} else if(pp.getProbability()<=0.15f) {
						contentSB.append("Low");
					} else if(pp.getProbability()<=0.3f) {
						contentSB.append("Average");
					} else if(pp.getProbability()<1) {
						contentSB.append("High");
					} else {
						contentSB.append("Certainty");
					}
					
					contentSB.append("</b>");
				}
			}
			
			contentSB.append("</div>");
			
			noPregnancies=false;
		
		}
		
		if (!Main.game.getPlayer().getLittersBirthed().isEmpty()) {
			for (Litter litter : Main.game.getPlayer().getLittersBirthed()) {
				if(litter.getFather()!=null) {
					contentSB.append(UtilText.parse(litter.getFather(),
							"<div class='container-full-width' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "<br/>"
								+ "Conceived with [npc.name(a)] on " + Units.date(litter.getConceptionDate(), Units.DateType.LONG) + ", delivered on " + Units.date(litter.getBirthDate(), Units.DateType.LONG) + "."
								+ "<br/>"
								+ "You gave birth to "+ litter.getBirthedDescription()+ "."
							+ "</div>"));
				} else {
					contentSB.append(
							"<div class='container-full-width' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "<br/>"
								+ "Conceived with someone you can't remember on " + Units.date(litter.getConceptionDate(), Units.DateType.LONG) + ", delivered on " + Units.date(litter.getBirthDate(), Units.DateType.LONG) + "."
								+ "<br/>"
								+ "You gave birth to "+ litter.getBirthedDescription()+ "."
							+ "</div>");
				}
			}
			noPregnancies=false;
		}
		
		if(noPregnancies){
			contentSB.append("<div class='subTitle'>"
					+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>You have never been pregnant!</span>"
					+ "</div>");
		}
		
		// Fathered children:

		noPregnancies=true;
		
		contentSB.append("<span style='height:16px;width:100%;float:left;'></span>"
				+ "<div class='subTitle'>Fathered children</div>");
		
		for(PregnancyPossibility pp : new ArrayList<>(Main.game.getPlayer().getPotentialPartnersAsFather())) {
			if(pp.getMother()!=null) {
				contentSB.append(UtilText.parse(pp.getMother(),
						"<div class='container-full-width' style='text-align:center;'>"
						+ "[style.boldBad(Ongoing pregnancy)]"
						+ "<br/>"
						+"<b>[npc.Name(A)] (</b>"
							+ (!pp.getMother().getRaceStage().getName().isEmpty()
									?"<b style='color:"+pp.getMother().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(pp.getMother().getRaceStage().getName())+"</b> "
									:"")
							+ "<b style='color:"+pp.getMother().getRace().getColour().toWebHexString()+";'>"
							+ (pp.getMother().getGender().isFeminine()
									?Util.capitaliseSentence(pp.getMother().getSubspecies().getSingularFemaleName(pp.getMother()))
									:Util.capitaliseSentence(pp.getMother().getSubspecies().getSingularMaleName(pp.getMother())))
							+ "</b><b>)</b>"));
				
				if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_0)) {
					contentSB.append("<br/>Probability of impregnation: ");
					if (pp.getProbability() <= 0) {
						contentSB.append("None");
					} else if(pp.getProbability()<=0.15f) {
						contentSB.append("Low");
					} else if(pp.getProbability()<=0.3f) {
						contentSB.append("Average");
					} else if(pp.getProbability()<1) {
						contentSB.append("High");
					} else {
						contentSB.append("Certainty");
					}
					
				} else {
					if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_1)) {
						contentSB.append("<br/>Pregnancy stage: [style.boldSex("+Util.capitaliseSentence(StatusEffect.PREGNANT_1.getName(pp.getMother()))+")]");
						
					} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_2)) {
						contentSB.append("<br/>Pregnancy stage: [style.boldSex("+Util.capitaliseSentence(StatusEffect.PREGNANT_2.getName(pp.getMother()))+")]");
						
					} else {
						contentSB.append("<br/>Pregnancy stage: [style.boldSex("+Util.capitaliseSentence(StatusEffect.PREGNANT_3.getName(pp.getMother()))+")]");
						
					}
				}
				
				contentSB.append("</b><br/>");
				contentSB.append("</div>");
			}
			noPregnancies=false;
		}
		
		if (!Main.game.getPlayer().getLittersFathered().isEmpty()) {
			for (Litter litter : Main.game.getPlayer().getLittersFathered()) {
				if(litter.getMother()!=null) {
					contentSB.append(UtilText.parse(litter.getMother(),
							"<div class='container-full-width' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "<br/>"
								+ "Conceived with [npc.name(a)] on " + Units.date(litter.getConceptionDate(), Units.DateType.LONG) + ", delivered on " + Units.date(litter.getBirthDate(), Units.DateType.LONG) + "."
								+ "<br/>"
								+ "[npc.She] gave birth to "+ litter.getBirthedDescription()+ "."
							+ "</div>"));
					
				} else {
					contentSB.append(
							"<div class='container-full-width' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "<br/>"
								+ "Conceived with someone you can't remember on " + Units.date(litter.getConceptionDate(), Units.DateType.LONG) + ", delivered on " + Units.date(litter.getBirthDate(), Units.DateType.LONG) + "."
								+ "<br/>"
								+ "They gave birth to "+ litter.getBirthedDescription()+ "."
							+ "</div>");
				}
			}
			noPregnancies=false;
		}
		
		if(noPregnancies){
			contentSB.append("<div class='subTitle'>"
					+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>You have never got anyone pregnant!</span>"
					+ "</div>");
		}
		

		return contentSB.toString();
	}
	
	private static String statHeader() {
		return "<div class='container-full-width' style='margin-bottom:0;'>"
					+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Attribute"
					+ "</div>"
					+ "<div style='width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Value"
					+ "</div>"
					+ "<div style='float:left; width:30%; font-weight:bold; margin:0; padding:0;'>"
						+ "Description"
					+"</div>"
				+ "</div>";
	}

	private static String statRowHeader(Colour colour, String text) {
		return "<h6 style='color:"+colour.toWebHexString()+"; text-align:center; margin-bottom:0; padding-bottom:0;'>"+text+"</h6>";
	}
	
	private static int rowCount = 0;
	
	private static String statRow(String colourLeft, String left, Colour colourCentre, String centre, String colourRight, String right) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
				+ "<div style='color:"+colourLeft+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ left
				+ "</div>"
				+ "<div style='color:"+colourCentre.toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ centre
				+ "</div>"
				+ "<div style='color:"+colourRight+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ right
				+ "</div>"
			+ "</div>";
	}
	
	private static String statRow(Colour colourLeft, String left, Colour colourCentre, String centre, Colour colourRight, String right) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='color:"+colourLeft.toWebHexString()+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ left
					+ "</div>"
					+ "<div style='color:"+(centre.equalsIgnoreCase("unknown") || centre.equalsIgnoreCase("N/A")?PresetColour.TEXT_GREY:colourCentre).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ centre
					+ "</div>"
					+ "<div style='color:"+(right.equalsIgnoreCase("unknown") || right.equalsIgnoreCase("N/A")?PresetColour.TEXT_GREY:colourRight).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ right
					+ "</div>"
				+ "</div>";
	}

	private static String statRow(Colour colour, String text) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='color:"+colour.toWebHexString()+"; width:100%; float:left; font-weight:bold; margin:0; padding:0; text-align:center;'>"
						+ text
					+ "</div>"
				+ "</div>";
	}

	private static String statAttributeHeader() {
		return "<div class='container-full-width' style='text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'><b>"
				
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:26%;'>"
						+ "Attribute"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:16%;'>"
						+ "Core | Bonus"
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:20%;'>"
						+ "Value/Max."
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:36%;'>"
						+ "Description"
					+ "</div>"
					
				+ "</b></div>";
	}
	
	private static String getAttributeBox(GameCharacter owner, Attribute att, String effect) {
		return getAttributeBox(owner, att, effect, false);
	}
	
	private static String getAttributeBox(GameCharacter owner, Attribute att, String effect, boolean half) {
		float value = owner.getAttributeValue(att);

		String valueForDisplay;
		if(((int)value)==value) {
			valueForDisplay = String.valueOf(((int)value));
		} else {
			valueForDisplay = String.valueOf(value);
		}
		if(att.isInfiniteAtUpperLimit() && value>=att.getUpperLimit()) {
			valueForDisplay = UtilText.getInfinitySymbol(true);
		}
		if(att.isPercentage()){
			valueForDisplay = (value>=0?"+":"")+valueForDisplay+"%";
		}
		
		return "<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>"
				
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:30%;'>"
						+ "<b style='color:" + att.getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(att.getName())+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:6%;text-align:right;'>"
						+(owner.getBaseAttributeValue(att) > 0 
								? "<b style='color:" + PresetColour.GENERIC_MINOR_GOOD.getShades()[1] + ";"
								: (owner.getBaseAttributeValue(att) < 0
									? "<b style='color:" + PresetColour.GENERIC_MINOR_BAD.getShades()[1] + ";"
									: "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";"))+"'>"
							+Units.number(owner.getBaseAttributeValue(att), 0, 1)
						+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:6%;text-align:left;'>"
						+" | "	
						+ (owner.getBonusAttributeValue(att) > 0 
								? "<b style='color:" + PresetColour.GENERIC_MINOR_GOOD.getShades()[1] + ";"
								: (owner.getBonusAttributeValue(att) < 0
									? "<b style='color:" + PresetColour.GENERIC_MINOR_BAD.getShades()[1] + ";"
									: "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";"))+"'>"
							+Units.number(owner.getBonusAttributeValue(att), 0, 1)
						+"</b>"
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;text-align:right;'>"
						+ "<b style='color:"+att.getColour().toWebHexString()+";'>"
							+ valueForDisplay
						+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;text-align:left;'>"
						+ "/"
						+ (value>=att.getUpperLimit() || (att==Attribute.MAJOR_CORRUPTION && value==0)
								?"[style.boldGood("+att.getUpperLimit()+")]"
								:"[style.boldDisabled("+att.getUpperLimit()+")]")
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:40%;'>"
						+ "[style.italicsDisabled("+effect.replaceAll("<br/>", " ")+")]"
					+ "</div>"
					
				+ "</div>";
	}

	private static String getContactEntry(GameCharacter contact) {
		boolean isOffspring = contact.getMotherId().equals(Main.game.getPlayer().getId()) || contact.getFatherId().equals(Main.game.getPlayer().getId());
		
		return UtilText.parse(contact, "<b style='color:"+contact.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"+(isOffspring
				?(contact.isFeminine()
						?", your [style.colourFeminine(daughter)]"
						:", your [style.colourMasculine(son)]")
				:"")
				+". [npc.She] is "
				+ (contact.getRaceStage()==RaceStage.HUMAN || contact.isRaceConcealed()
					?"[npc.a_race(true)]"
					:"[npc.a_raceStage(true)] [npc.race(true)]")
				+", whose current location is: <i>"+contact.getWorldLocation().getName()+", "+contact.getLocationPlace().getPlaceType().getName()+"</i>.");
	}
	
	public static final DialogueNode CONTACTS = new DialogueNode("Contacts", "Look at your contacts.", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			for (int i = 0; i < charactersEncountered.size(); i++) {
				GameCharacter npc = charactersEncountered.get(i);
				
				UtilText.nodeContentSB.append("<p>"
												+ getContactEntry(npc)
											+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else if (index <= charactersEncountered.size()) {
				GameCharacter npc = charactersEncountered.get(index-1);
				boolean isOffspring = npc.getMotherId().equals(Main.game.getPlayer().getId()) || npc.getFatherId().equals(Main.game.getPlayer().getId());
				return new Response(
						UtilText.parse(npc, isOffspring
								?(npc.isFeminine()?"[style.colourFeminine([npc.Name])]":"[style.colourMasculine([npc.Name])]")
								:"[npc.Name]"),
						getContactEntry(npc),
						CONTACTS_CHARACTER){
					@Override
					public void effects() {
						CharactersPresentDialogue.resetContent(npc);
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CONTACTS_CHARACTER = new DialogueNode("Contacts", "Look at your contacts.", true) {

		@Override
		public String getLabel() {
			return CharactersPresentDialogue.characterViewed.getName(true);
		}

		@Override
		public String getContent() {
			return CharactersPresentDialogue.menuContent;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else if (index <= Main.game.getPlayer().getCharactersEncountered().size()) {
				try {
					GameCharacter npc = Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(index - 1));
					return new Response(Util.capitaliseSentence(npc.getName(true)),
							UtilText.parse(npc, "Take a detailed look at what [npc.name] looks like."),
							CONTACTS_CHARACTER){
						@Override
						public void effects() {
							CharactersPresentDialogue.resetContent(npc);
							
						}
					};
				} catch (Exception e) {
					Util.logGetNpcByIdError("CONTACTS_CHARACTER.getResponse()", Main.game.getPlayer().getCharactersEncountered().get(index - 1));
					return null;
				}
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	private static String getSubspeciesDiscoveredIndication() {
		return Main.getProperties().getSubspeciesDiscoveredCount()+"/"+Subspecies.values().length;
	}

	private static String getWeaponsDiscoveredIndication() {
		return Main.getProperties().getWeaponsDiscoveredCount()+"/"+weaponsDiscoveredList.size();
	}
	
	private static String getClothingDiscoveredIndication() {
		return Main.getProperties().getClothingDiscoveredCount()+"/"+clothingDiscoveredList.size();
	}
	
	private static String getItemsDiscoveredIndication() {
		return Main.getProperties().getItemsDiscoveredCount()+"/"+itemsDiscoveredList.size();
	}
	
	public static final DialogueNode ENCYCLOPEDIA = new DialogueNode("Encyclopedia", "", true) {

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "You have collected information on all the races, weapons, clothing, and items that you've encountered in your travels."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>");
				sb.append("You have discovered:");
				sb.append((Main.getProperties().getSubspeciesDiscoveredCount()==Subspecies.values().length
								?"<br/>[style.colourGood(Subspecies: "+getSubspeciesDiscoveredIndication()+")]"
								:"<br/>Subspecies: "+getSubspeciesDiscoveredIndication()));
	
				sb.append((Main.getProperties().getWeaponsDiscoveredCount()==weaponsDiscoveredList.size()
								?"<br/>[style.colourGood(Weapons: "+getWeaponsDiscoveredIndication()+")]"
								:"<br/>Weapons: "+getWeaponsDiscoveredIndication()));
	
				sb.append((Main.getProperties().getClothingDiscoveredCount()==clothingDiscoveredList.size()
								?"<br/>[style.colourGood(Clothing: "+getClothingDiscoveredIndication()+")]"
								:"<br/>Clothing: "+getClothingDiscoveredIndication()));
	
				sb.append((Main.getProperties().getItemsDiscoveredCount()==itemsDiscoveredList.size()
								?"<br/>[style.colourGood(Items: "+getItemsDiscoveredIndication()+")]"
								:"<br/>Items: "+getItemsDiscoveredIndication()));
			sb.append("</p>");
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Races</span>":"Races",
						"Have a look at all the different races that you've encountered in your travels.", RACES){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newRaceDiscovered, false);
					}
				};
			
			} else if (index == 2) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Weapons</span>":"Weapons",
						"Have a look at all the different weapons that you've encountered in your travels.", WEAPON_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
					}
				};
			
			} else if (index == 3) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newClothingDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Clothing</span>":"Clothing",
						"Have a look at all the different clothing that you've encountered in your travels.", CLOTHING_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
						clothingSlotKey = clothingSlotCategories.keySet().iterator().next();
					}
				};
			
			} else if (index == 4) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newItemDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Items</span>":"Items",
						"Have a look at all the different items that you've encountered in your travels.", ITEM_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
					}
				};
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static List<AbstractItemType> itemsDiscoveredList = new ArrayList<>();
	private static List<AbstractClothingType> clothingDiscoveredList = new ArrayList<>();
	private static List<AbstractWeaponType> weaponsDiscoveredList = new ArrayList<>();

	
	private static Map<String, List<InventorySlot>> clothingSlotCategories;
	private static String clothingSlotKey;
	
	static {
		itemsDiscoveredList.addAll(ItemType.getAllItems());
		itemsDiscoveredList.removeIf((it) -> it.getItemTags().contains(ItemTag.CHEAT_ITEM));
		Collections.sort(itemsDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		weaponsDiscoveredList.addAll(WeaponType.getAllWeapons());
		weaponsDiscoveredList.removeIf((wt) -> wt.getItemTags().contains(ItemTag.CHEAT_ITEM));
		Collections.sort(weaponsDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		clothingDiscoveredList.addAll(ClothingType.getAllClothing());
		clothingDiscoveredList.removeIf((ct) -> ct.getDefaultItemTags().contains(ItemTag.CHEAT_ITEM));
		Collections.sort(clothingDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		clothingSlotCategories = new LinkedHashMap<>();
		
		clothingSlotCategories.put("Head",
				Util.newArrayListOfValues(
				InventorySlot.HEAD,
				InventorySlot.EYES,
				InventorySlot.HAIR,
				InventorySlot.MOUTH,
				InventorySlot.NECK));
		
		clothingSlotCategories.put("Torso",
				Util.newArrayListOfValues(
				InventorySlot.TORSO_OVER,
				InventorySlot.TORSO_UNDER));
		
		clothingSlotCategories.put("Chest",
				Util.newArrayListOfValues(
				InventorySlot.CHEST,
				InventorySlot.NIPPLE));
		
		clothingSlotCategories.put("Arms",
				Util.newArrayListOfValues(
				InventorySlot.HAND,
				InventorySlot.WRIST,
				InventorySlot.FINGER));
		
		clothingSlotCategories.put("Waist",
				Util.newArrayListOfValues(
				InventorySlot.STOMACH,
				InventorySlot.HIPS));
		
		clothingSlotCategories.put("Groin",
				Util.newArrayListOfValues(
				InventorySlot.GROIN,
				InventorySlot.PENIS,
				InventorySlot.VAGINA,
				InventorySlot.ANUS));
		
		clothingSlotCategories.put("Legs",
				Util.newArrayListOfValues(
				InventorySlot.LEG,
				InventorySlot.SOCK));
		
		clothingSlotCategories.put("Feet",
				Util.newArrayListOfValues(
				InventorySlot.FOOT,
				InventorySlot.ANKLE));
		
		clothingSlotCategories.put("Extra",
				Util.newArrayListOfValues(
				InventorySlot.HORNS,
				InventorySlot.WINGS,
				InventorySlot.TAIL));
		
		clothingSlotCategories.put("Piercings",
				Util.newArrayListOfValues(
				InventorySlot.PIERCING_EAR,
				InventorySlot.PIERCING_LIP,
				InventorySlot.PIERCING_NOSE,
				InventorySlot.PIERCING_TONGUE,
				InventorySlot.PIERCING_NIPPLE,
				InventorySlot.PIERCING_STOMACH,
				InventorySlot.PIERCING_PENIS,
				InventorySlot.PIERCING_VAGINA));
	}
	
	public static final DialogueNode WEAPON_CATALOGUE = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "Discovered Weapons ("+getWeaponsDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			StringBuilder sbMelee = new StringBuilder();
			StringBuilder sbRanged = new StringBuilder();
			StringBuilder sbDamageTypes = new StringBuilder();

			int meleeCount = 0;
			int meleeKnownCount = 0;
			int rangedCount = 0;
			int rangedKnownCount = 0;
			
			for(AbstractWeaponType weaponType : weaponsDiscoveredList) {
				boolean discovered = Main.getProperties().isWeaponDiscovered(weaponType);
				String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+"; width:78%; margin:1%; padding:0; '>"
									+ "<div class='inventory-icon-content'>"+(discovered?weaponType.getSVGImage():"")+"</div>"
									+ "<div class='overlay"+(discovered?"' id='"+weaponType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
								+ "</div>";
				sbDamageTypes.setLength(0);
				if(discovered) {
					for(DamageType dt : weaponType.getAvailableDamageTypes()) {
						sbDamageTypes.append("<div class='square-button' "+(discovered?"id='"+(weaponType.getId()+"_"+dt.toString())+"'":"")
												+ " style='cursor:default; width:18%; margin:1%; padding:0; background-color:"+dt.getMultiplierAttribute().getColour().toWebHexString()+";'>"
											+ "</div>");
					}
				}
				if(weaponType.isMelee()) {
					meleeCount++;
					if(discovered) {
						meleeKnownCount++;
					}
					sbMelee.append("<div class='container-full-width' style='width:11.5%; padding:0; margin:0.5%;'>");
						sbMelee.append(entry);
						sbMelee.append(sbDamageTypes.toString());
					sbMelee.append("</div>");
					
				} else {
					rangedCount++;
					if(discovered) {
						rangedKnownCount++;
					}
					sbRanged.append("<div class='container-full-width' style='width:11.5%; padding:0; margin:0.5%;'>");
						sbRanged.append(entry);
						sbRanged.append(sbDamageTypes.toString());
					sbRanged.append("</div>");
				}
			}
			
			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldBlue(Melee Weapons ("+meleeKnownCount+"/"+meleeCount+"))]");
				sb.append("</p>");
				sb.append(sbMelee.toString());
			sb.append("</div>");
			
			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldYellow(Ranged Weapons ("+rangedKnownCount+"/"+rangedCount+"))]");
				sb.append("</p>");
				sb.append(sbRanged.toString());
			sb.append("</div>");
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CLOTHING_CATALOGUE = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "Discovered Clothing ("+getClothingDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			List<InventorySlot> slots = clothingSlotCategories.get(clothingSlotKey);
			Map<InventorySlot, StringBuilder> sbMap = new LinkedHashMap<>();
			Map<InventorySlot, Value<Integer, Integer>> discoveredMap = new HashMap<>();
			for(InventorySlot slot : slots) {
				sbMap.put(slot, new StringBuilder());
				discoveredMap.put(slot, new Value<>(0, 0));
			}
			
			for(AbstractClothingType clothingType : clothingDiscoveredList) {
				if(Collections.disjoint(clothingType.getEquipSlots(), slots)) {
					continue;
				}
				boolean discovered = Main.getProperties().isClothingDiscovered(clothingType);
				String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+"; width:8%;'>"
									+ "<div class='inventory-icon-content'>"+(discovered?clothingType.getSVGImageRandomColour(true, false, false):"")+"</div>"
									+ "<div class='overlay"+(discovered?"' id='"+clothingType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
								+ "</div>";
				
				for(InventorySlot slot : clothingType.getEquipSlots()) {
					if(slots.contains(slot)) {
						sbMap.get(slot).append(entry);
						discoveredMap.put(slot, new Value<>(discoveredMap.get(slot).getKey()+(discovered?1:0), discoveredMap.get(slot).getValue()+1));
					}
				}
			}

			for(InventorySlot slot : slots) {
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldYellowLight("+Util.capitaliseSentence(slot.getName())+" ("+discoveredMap.get(slot).getKey()+"/"+discoveredMap.get(slot).getValue()+"))]");
					sb.append("</p>");
					sb.append(sbMap.get(slot).toString());
				sb.append("</div>");
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);
			
			} else {
				List<Response> responses = new ArrayList<>();
				for(Entry<String, List<InventorySlot>> entry : clothingSlotCategories.entrySet()) {
					if(clothingSlotKey==entry.getKey()) {
						responses.add(new Response(entry.getKey(), "You are already viewing this category!", null));
						
					} else {
						responses.add(new Response(entry.getKey(), "View all clothing which fits into the following slots:<br/><i>"+Util.capitaliseSentence(Util.inventorySlotsToStringList(entry.getValue()))+".</i>", CLOTHING_CATALOGUE) {
							@Override
							public void effects() {
								clothingSlotKey = entry.getKey();
							}
						});
					}
				}
				if(index-1<responses.size()) {
					return responses.get(index-1);
				}
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode ITEM_CATALOGUE = new DialogueNode("", "View discovered items", true) {
		@Override
		public String getLabel() {
			return "Discovered Items ("+getItemsDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			StringBuilder sbItems = new StringBuilder();
			StringBuilder sbBooks = new StringBuilder();
			StringBuilder sbEssences = new StringBuilder();
			StringBuilder sbSpells = new StringBuilder();
			
			int itemCount = 0;
			int itemKnownCount = 0;
			int bookCount = 0;
			int bookKnownCount = 0;
			int essenceCount = 0;
			int essenceKnownCount = 0;
			int spellCount = 0;
			int spellKnownCount = 0;
			
			for(AbstractItemType itemType : itemsDiscoveredList) {
				boolean discovered = Main.getProperties().isItemDiscovered(itemType);
				String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+"; width:8%;'>"
									+ "<div class='inventory-icon-content'>"+(discovered?itemType.getSVGString():"")+"</div>"
									+ "<div class='overlay"+(discovered?"' id='"+itemType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
								+ "</div>";
				
				if(itemType.getItemTags().contains(ItemTag.BOOK)) {
					sbBooks.append(entry);
					bookCount++;
					if(discovered) {
						bookKnownCount++;
					}
					
				} else if(itemType.getItemTags().contains(ItemTag.ESSENCE)) {
					sbEssences.append(entry);
					essenceCount++;
					if(discovered) {
						essenceKnownCount++;
					}
					
				} else if(itemType.getItemTags().contains(ItemTag.SPELL_BOOK) || itemType.getItemTags().contains(ItemTag.SPELL_SCROLL)) {
					sbSpells.append(entry);
					spellCount++;
					if(discovered) {
						spellKnownCount++;
					}
					
				} else {
					sbItems.append(entry);
					itemCount++;
					if(discovered) {
						itemKnownCount++;
					}
				}
			}
			
			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldBlueLight(Items ("+itemKnownCount+"/"+itemCount+"))]");
				sb.append("</p>");
				sb.append(sbItems.toString());
			sb.append("</div>");

			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldOrange(Books ("+bookKnownCount+"/"+bookCount+"))]");
				sb.append("</p>");
				sb.append(sbBooks.toString());
			sb.append("</div>");

			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldArcane(Essences ("+essenceKnownCount+"/"+essenceCount+"))]");
				sb.append("</p>");
				sb.append(sbEssences.toString());
			sb.append("</div>");

			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldSpells(Spells ("+spellKnownCount+"/"+spellCount+"))]");
				sb.append("</p>");
				sb.append(sbSpells.toString());
			sb.append("</div>");
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static List<Race> racesDiscovered = new ArrayList<>();
	private static List<Subspecies> subspeciesDiscovered = new ArrayList<>();
	private static Race raceSelected;
	private static Subspecies subspeciesSelected;
	private static StringBuilder subspeciesSB = new StringBuilder();
	
	public static void resetContentForRaces() {
		
		subspeciesDiscovered.clear();
		
		for (Subspecies subspecies : Subspecies.values()) {
			if(Main.getProperties().isRaceDiscovered(subspecies)) {
				Race race = subspecies.getRace();
				if(!racesDiscovered.contains(race)) {
					racesDiscovered.add(race);
				}
				subspeciesDiscovered.add(subspecies);
			}
		}
		
		racesDiscovered.sort((a, b) -> a.getName(false).compareTo(b.getName(false)));
		subspeciesDiscovered.sort((a, b) -> a.getName(null).compareTo(b.getName(null)));
		
	}

	public static final DialogueNode RACES = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "Discovered Races ("+getSubspeciesDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>"
						+ "You have encountered the following races in your travels:<br/>"
						+ "(Discovered races are [style.boldGood(highlighted)], while undiscovered races are [style.colourDisabled(greyed out)].)"
					+ "</p>");
			List<Race> sortedRaces = new ArrayList<>();
			Collections.addAll(sortedRaces, Race.values());
			sortedRaces.remove(Race.NONE);
			sortedRaces.sort((r1, r2) -> r1.getName(false).compareTo(r2.getName(false)));
			for(Race race : sortedRaces) {
				UtilText.nodeContentSB.append("<div style='box-sizing: border-box; text-align:center; width:50%; padding:8px; margin:0; float:left;'>");
				if(racesDiscovered.contains(race)) {
					UtilText.nodeContentSB.append("<b style='color:"+race.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(race.getName(false)) + "</b>");
				} else {
					UtilText.nodeContentSB.append("[style.colourDisabled(" + Util.capitaliseSentence(race.getName(false)) + ")]");
				}
				UtilText.nodeContentSB.append("</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);
			
			} else if (index <= racesDiscovered.size()) {
				return new Response(Util.capitaliseSentence(racesDiscovered.get(index - 1).getName(false)),
						"Take a look at all the subspecies of the race: '" + racesDiscovered.get(index - 1).getName(false) + "'",
						SUBSPECIES){
					@Override
					public void effects() {
						raceSelected = racesDiscovered.get(index - 1);
						subspeciesSelected = Subspecies.getMainSubspeciesOfRace(raceSelected);
						if(!subspeciesDiscovered.contains(subspeciesSelected)) {
							for(Subspecies sub : subspeciesDiscovered) {
								if(sub.getRace()==raceSelected) {
									subspeciesSelected = sub;
									break;
								}
							}
						}
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode SUBSPECIES = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return Util.capitaliseSentence(subspeciesSelected.getName(null));
		}
		@Override
		public String getContent() {
			subspeciesSB.setLength(0);
			
			Body femaleBody = CharacterUtils.generateBody(null, Gender.F_V_B_FEMALE, subspeciesSelected, RaceStage.GREATER);
			Body maleBody = CharacterUtils.generateBody(null, Gender.M_P_MALE, subspeciesSelected, RaceStage.GREATER);
			
			subspeciesSB.append(
				"<div class='container-full-width' style='width:40%; float:right;'>"
					
					+ "<p style='width:100%; text-align:center;'><b style='color:"+subspeciesSelected.getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getName(null))+"</b><br/>"
							+ "Average stats</p>"
					+ "<table align='center'>"
						+ "<tr>"
							+ "<td></td>"
							+ "<td>[style.colourFeminine("+Util.capitaliseSentence(subspeciesSelected.getSingularFemaleName(null))+")]</td>"
							+ "<td>[style.colourMasculine("+Util.capitaliseSentence(subspeciesSelected.getSingularMaleName(null))+")]</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Height</td>"
							+ "<td>"+Units.size(femaleBody.getHeightValue())+"</td>"
							+ "<td>"+Units.size(maleBody.getHeightValue())+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Femininity</td>"
							+ "<td>"+femaleBody.getFemininity()+"</td>"
							+ "<td>"+maleBody.getFemininity()+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Breast size</td>"
							+ "<td>"+(femaleBody.getBreast().getRawSizeValue()==0
										?"Flat"
										:femaleBody.getBreast().getSize().getCupSizeName()+"-cup")+"</td>"
							+ "<td>"+(maleBody.getBreast().getRawSizeValue()==0
										?"Flat"
										:maleBody.getBreast().getSize().getCupSizeName()+"-cup")+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Penis size</td>"
							+ "<td>-</td>"
							+ "<td>"+Units.size(maleBody.getPenis().getRawLengthValue())+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Vagina capacity</td>"
							+ "<td>"+Util.capitaliseSentence(femaleBody.getVagina().getOrificeVagina().getCapacity().getDescriptor())+"</td>"
							+ "<td>-</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Litter size</td>"
							+ "<td>"+subspeciesSelected.getRace().getNumberOfOffspringLow()+"-"+subspeciesSelected.getRace().getNumberOfOffspringHigh()+"</td>"
							+ "<td>-</td>"
						+ "</tr>"
					+ "</table>"
				+ "</div>"
					
				+"<p>"
					+ "<b style='color:"+subspeciesSelected.getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getName(null))+"</b>"
					+ (Subspecies.getMainSubspeciesOfRace(raceSelected)==subspeciesSelected
							?" ([style.colourGood(Core)] subspecies of <span style='color:"+raceSelected.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceSelected.getName(false))+"</span>)"
							:" (Subspecies of <span style='color:"+raceSelected.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceSelected.getName(false))+"</span>)")
					+ "<br/>"
					+ "Masculine: <span style='color:"+Femininity.valueOf(maleBody.getFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getSingularMaleName(null))+"</span>"
					+ "<br/>"
					+ "Feminine: <span style='color:"+Femininity.valueOf(femaleBody.getFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getSingularFemaleName(null))+"</span>"
					+ "<br/><br/>"
					+ "<i>"+subspeciesSelected.getDescription(null)+"</i>"
				+ "</p>"
					
				+"<h6>"+Util.capitaliseSentence(raceSelected.getName(false))+" Lore</h6>"
					+subspeciesSelected.getBasicDescription(null)
					+ (Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspeciesSelected)
						?subspeciesSelected.getAdvancedDescription(null)
						:"<p style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
							+ "Further information can be discovered in books!"
						+ "</p>"));
			
			return subspeciesSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Subspecies> raceSubspecies = Subspecies.getSubspeciesOfRace(raceSelected);
			
			if (index == 0) {
				return new Response("Back", "Return to the race selection screen.", RACES);
			
			} else if (index <= raceSubspecies.size()) {
				Subspecies indexSubspecies = raceSubspecies.get(index - 1);
				if(!subspeciesDiscovered.contains(indexSubspecies)) {
					return new Response(Util.capitaliseSentence(indexSubspecies.getName(null)),
							"You haven't discovered this subspecies yet!",
							null);
				}
				return new Response(Util.capitaliseSentence(indexSubspecies.getName(null)),
						"Take a detailed look at what " + indexSubspecies.getNamePlural(null) + " are like."
						+ (Subspecies.getMainSubspeciesOfRace(raceSelected)==indexSubspecies
							?"<br/>This is the [style.colourGood(core)] "+raceSelected.getName(false)+" subspecies."
							:""),
						SUBSPECIES){
					@Override
					public Colour getHighlightColour() {
						if(Subspecies.getMainSubspeciesOfRace(raceSelected)==indexSubspecies) {
							return PresetColour.GENERIC_GOOD;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						subspeciesSelected = indexSubspecies;
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_PERK_TREE = new DialogueNode("Perk Tree", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary>[style.boldPerk(Perk & Trait Information)]</summary>"
							+ "[style.colourPerk(Perks)] (circular icons) apply permanent boosts to your attributes.<br/>"
							+ "[style.colourPerk(Traits)] (square icons) provide unique effects for your character."
								+ " Unlike perks, <b>traits will have no effect on your character until they're slotted into your 'Active Traits' bar</b>.<br/>"
							+ "Perks require perk points to unlock. You earn one perk point each time you level up, and earn an extra two perk points every five levels.<br/><br/>"
							+ "In addition to the perks that can be purchased via perk points, there are also several special, hidden perks that are unlocked via special events."
					+ "</details>");
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(Main.game.getPlayer(), true));
			
			UtilText.nodeContentSB.append("</div>"
					+ "<div class='container-full-width' style='padding:8px; text-align:center;'>"
						+ "[style.italicsBad(Please note that this perk tree is a work-in-progress. This is not the final version, and is just a proof of concept!)]"
					+ "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reset", "Reset all perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)", CHARACTER_PERK_TREE) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetPerksMap(false, false);
					}
				};
				
				
			} else if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.levelUpHightlight, false);
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	
//	private static boolean confirmReset = false;
	public static final DialogueNode CHARACTER_FETISHES = new DialogueNode("Desires & Fetishes", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder(
					"<details>"
						+ "<summary>[style.boldFetish(Fetish Information)]</summary>"
							+ "You can select your [style.colourLust(desire)] for each fetish [style.colourArcane(for free)],"
							+ " or choose to take the associated [style.colourFetish(fetish)] for [style.colourArcane("+Fetish.FETISH_ANAL_GIVING.getCost()+" Arcane Essences)].<br/><br/>"
							+ "Choosing a desire will affect bonus lust gains in sex, while taking a fetish will permanently lock your desire to 'love', and also give you special bonuses."
							+ " Fetishes can only be removed through enchanted potions.<br/><br/>"
							+ "Your currently selected desire has a "+PresetColour.FETISH.getName()+" border, but your true desire (indicated by the coloured desire icon) may be modified by enchanted clothes or other items.<br/><br/>"
							+ "You earn experience for each fetish through performing related actions in sex."
							+ " Experience is earned regardless of whether or not you have the associated fetish."
							+ " Higher level fetishes will cause both you and your partner to gain more arousal from related sex actions, as well as increase the fetish's bonuses.<br/><br/>"
							+ "Finally, derived fetishes cannot be directly unlocked, but are instead automatically applied when you meet their requirements."
					+ "</details>");
			
			// Normal fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold;'><h6>Fetishes</h6></div>");
			journalSB.append(getFetishEntry(Fetish.FETISH_DOMINANT, Fetish.FETISH_SUBMISSIVE));
			journalSB.append(getFetishEntry(Fetish.FETISH_VAGINAL_GIVING, Fetish.FETISH_VAGINAL_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_PENIS_GIVING, Fetish.FETISH_PENIS_RECEIVING));
			if(Main.game.isAnalContentEnabled()) {
				journalSB.append(getFetishEntry(Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_ANAL_RECEIVING));
			}
			journalSB.append(getFetishEntry(Fetish.FETISH_BREASTS_OTHERS, Fetish.FETISH_BREASTS_SELF));
			if(Main.game.isLactationContentEnabled()) {
				journalSB.append(getFetishEntry(Fetish.FETISH_LACTATION_OTHERS, Fetish.FETISH_LACTATION_SELF));
			}
			journalSB.append(getFetishEntry(Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER));
			if(Main.game.isFootContentEnabled()) {
				journalSB.append(getFetishEntry(Fetish.FETISH_FOOT_GIVING, Fetish.FETISH_FOOT_RECEIVING));
			}
			journalSB.append(getFetishEntry(Fetish.FETISH_CUM_STUD, Fetish.FETISH_CUM_ADDICT));
			journalSB.append(getFetishEntry(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_PURE_VIRGIN));
			journalSB.append(getFetishEntry(Fetish.FETISH_IMPREGNATION, Fetish.FETISH_PREGNANCY));
			journalSB.append(getFetishEntry(Fetish.FETISH_TRANSFORMATION_GIVING, Fetish.FETISH_TRANSFORMATION_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_KINK_GIVING, Fetish.FETISH_KINK_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_SADIST, Fetish.FETISH_MASOCHIST));
			journalSB.append(getFetishEntry(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_NON_CON_SUB));
			
//			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>Individual Fetishes</h6></div>");
			journalSB.append(getFetishEntry(Fetish.FETISH_DENIAL, Fetish.FETISH_DENIAL_SELF));
			journalSB.append(getFetishEntry(Fetish.FETISH_VOYEURIST, Fetish.FETISH_EXHIBITIONIST));
			journalSB.append(getFetishEntry(Fetish.FETISH_BIMBO, Fetish.FETISH_CROSS_DRESSER));
			if(Main.game.isIncestEnabled()) {
				journalSB.append(getFetishEntry(Fetish.FETISH_MASTURBATION, Fetish.FETISH_INCEST));
				if(Main.game.isPenetrationLimitationsEnabled()) {
					journalSB.append(getFetishEntry(Fetish.FETISH_SIZE_QUEEN, null));
				}
			} else {
				if(Main.game.isPenetrationLimitationsEnabled()) {
					journalSB.append(getFetishEntry(Fetish.FETISH_MASTURBATION, Fetish.FETISH_SIZE_QUEEN));
				} else {
					journalSB.append(getFetishEntry(Fetish.FETISH_MASTURBATION, null));
				}
			}
			
			// Derived fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>Derived Fetishes</h6></div>");
			journalSB.append("<div class='fetish-container'>");
			
			for(Fetish fetish : Fetish.values()) {
				if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					journalSB.append(
							"<div id='fetishUnlock" + fetish + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:2px solid " + PresetColour.FETISH.getShades()[1] + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:2px solid " +  PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";'>"))
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString(Main.game.getPlayer())+"</div>"
							+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
									? ""
									: (fetish.isAvailable(Main.game.getPlayer())
											? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.5; border-radius:5px;'></div>"
											: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.7; border-radius:5px;'></div>"))
							+ "</div>");
				}
			}
			
			// Free Fetishes:
			
			journalSB.append("</div>");
			
			
			return journalSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	
	private static String getFetishEntry(Fetish othersFetish, Fetish selfFetish) {
		return "<div class='container-full-width' style='background:transparent; margin:2px 0; width:100%;'>"
					+ getIndividualFetishEntry(othersFetish)
					+ (selfFetish==null?"":getIndividualFetishEntry(selfFetish))
				+ "</div>";
	}
	
	private static String getIndividualFetishEntry(Fetish fetish) {
		FetishLevel level = FetishLevel.getFetishLevelFromValue(Main.game.getPlayer().getFetishExperience(fetish));
		float experiencePercentage = ((Main.game.getPlayer().getFetishExperience(fetish)) / (float)(level.getMaximumExperience()))*100;
		
		return "<div class='container-half-width' style='margin:0 8px;'>"
					+"<div class='container-full-width' style='text-align:center; font-weight:bold; margin:0 8px; width: calc(78% - 16px);'>"
						+ (Main.game.getPlayer().hasFetish(fetish)
								?"[style.colourPink("+Util.capitaliseSentence(fetish.getName(Main.game.getPlayer()))+" "+level.getNumeral()+")]"
								:Util.capitaliseSentence(fetish.getName(Main.game.getPlayer()))+" "+level.getNumeral())
						+"<div class='container-full-width' style='margin:2px 0; padding:0; width:100%;'></div>" // Spacer
						+getFetishDesireEntry(fetish, FetishDesire.ZERO_HATE)
						+getFetishDesireEntry(fetish, FetishDesire.ONE_DISLIKE)
						+getFetishDesireEntry(fetish, FetishDesire.TWO_NEUTRAL)
						+getFetishDesireEntry(fetish, FetishDesire.THREE_LIKE)
						+getFetishDesireEntry(fetish, FetishDesire.FOUR_LOVE)
					+ "</div>"
					+"<div class='container-full-width' style='margin:0 8px; width: calc(22% - 16px);'>"
						+ "<div id='fetishUnlock" + fetish + "' class='fetish-icon full" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:2px solid " + PresetColour.FETISH.toWebHexString() + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + PresetColour.TEXT_GREY_DARK.toWebHexString() + ";'>"))
										+ "<div class='fetish-icon-content'>"+fetish.getSVGString(Main.game.getPlayer())+"</div>"
										+ "<div style='width:40%;height:40%;position:absolute;top:0;right:4px;'>"+level.getSVGImageOverlay()+"</div>"
										+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
											? ""
											: (fetish.isAvailable(Main.game.getPlayer())
													? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.5; border-radius:5px;'></div>"
													: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.7; border-radius:5px;'></div>"))
						+ "</div>"
					+ "</div>"
					+"<div class='container-full-width' style='margin:0; padding:0; width:100%;'>"
						+"<div class='container-full-width' style='text-align:center; font-weight:bold; margin:0 8px; width: calc(78% - 16px);'>"
							+"<div class='container-full-width' style='margin:4px 0; padding:2px; width:100%; background:#222;'>"
								+ "<div class='container-full-width' style='margin:0; padding:2px; width:" + experiencePercentage + "%; background:"+level.getColour().toWebHexString()+";'></div>"
							+ "</div>"
						+ "</div>"
						+"<div class='container-full-width' style='text-align:center; margin:0 8px; width: calc(22% - 16px);'>"
							+ "<span style='color:"+level.getColour().toWebHexString()+";'>"+Main.game.getPlayer().getFetishExperience(fetish)+" xp</span>"
						+ "</div>"
//						+ "<div class='overlay no-pointer' id='"+fetish+"_EXPERIENCE'></div>"
					+ "</div>"
				+ "</div>";
	}
	
	private static String getFetishDesireEntry(Fetish fetish, FetishDesire desire) {
		boolean disabled = desire!=FetishDesire.FOUR_LOVE && Main.game.getPlayer().hasFetish(fetish);
		
		return "<div class='square-button"+(disabled?" disabled":"")+"' id='"+fetish+"_"+desire+"'"
					+ " style='"+(Main.game.getPlayer().getBaseFetishDesire(fetish)==desire
								?"border:2px solid "+PresetColour.FETISH.getShades()[1]+";"
								:"")+"width:10%; margin:0 5%; float:left; cursor:pointer;'>"
				+ "<div class='square-button-content'>"+(Main.game.getPlayer().getFetishDesire(fetish)==desire?desire.getSVGImage():desire.getSVGImageDesaturated())+"</div>"
				+ (Main.game.getPlayer().hasFetish(fetish) && Main.game.getPlayer().getFetishDesire(fetish)!=desire
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8; border-radius:5px;'></div>"
					:Main.game.getPlayer().getFetishDesire(fetish)!=desire
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; border-radius:5px;'></div>"
						:"")
			+ "</div>";
	}
	
	public static AbstractWorldType worldTypeMap = WorldType.DOMINION;

	public static final DialogueNode MAP = new DialogueNode("Maps", "", true) {
		@Override
		public String getLabel() {
			return "Map: "+Util.capitaliseSentence(worldTypeMap.getName());
		}
		
		@Override
		public String getContent() {
			if(worldTypeMap==WorldType.WORLD_MAP) {
//				return RenderingEngine.ENGINE.getFullWorldMap();
				return RenderingEngine.ENGINE.getFullMap(worldTypeMap, true, false);
			} else {
				return RenderingEngine.ENGINE.getFullMap(worldTypeMap, true, true);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			int i=2;
			List<AbstractWorldType> worldTypes = Main.getProperties().hasValue(PropertyValue.mapReveal)?WorldType.getAllWorldTypes():new ArrayList<>(Main.game.getPlayer().getWorldsVisited());
			for(AbstractWorldType world : worldTypes) {//WorldType.values()) {
				if(world != WorldType.WORLD_MAP
						&& world != WorldType.EMPTY
						&& world != WorldType.MUSEUM
						&& world != WorldType.MUSEUM_LOST) {
					if(index==i) {
						boolean playerPresent = Main.game.getPlayer().getWorldLocation()==world;
						if(worldTypeMap==world) {
							return new Response(Util.capitaliseSentence(world.getName()), "You are already viewing the map of "+world.getName()+"."+(playerPresent?"<br/>[style.colourGood(You are currently in this area!)]":""), null);
							
						} else if(Main.game.getPlayer().getWorldsVisited().contains(world) || Main.getProperties().hasValue(PropertyValue.mapReveal)) { 
							return new Response(Util.capitaliseSentence(world.getName()), "View the map of "+world.getName()+"."+(playerPresent?"<br/>[style.colourGood(You are currently in this area!)]":""), MAP) {
								@Override
								public Colour getHighlightColour() {
									if(playerPresent) {
										return PresetColour.GENERIC_GOOD;
									}
									return super.getHighlightColour();
								}
								@Override
								public void effects() {
									Pathing.initPathingVariables();
									worldTypeMap = world;
								}
							};
							
						} else {
							return new Response("???", "You haven't discovered this area yet.", null);
						}
					}
					i++;
				}
			}
			if (index == 1) {
				boolean playerPresent = Main.game.getPlayer().getWorldLocation()==WorldType.WORLD_MAP;
				if(worldTypeMap==WorldType.WORLD_MAP) {
					return new Response("World map", "You are already viewing the world map."+(playerPresent?"<br/>[style.colourGood(You are currently in this area!)]":""), null);
					
				} else if(Main.game.getPlayer().isDiscoveredWorldMap()) {
					return new Response("World map", "Take a look at the world map."+(playerPresent?"<br/>[style.colourGood(You are currently in this area!)]":""), MAP) {
						@Override
						public Colour getHighlightColour() {
							if(playerPresent) {
								return PresetColour.GENERIC_GOOD;
							}
							return super.getHighlightColour();
						}
						@Override
						public void effects() {
							Pathing.initPathingVariables();
							worldTypeMap = WorldType.WORLD_MAP;
						}
					};
				} else {
					return new Response("World map", "You haven't discovered the world map yet!", null);
				}
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode LOITER_SELECTION = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "You think about how long you'd like to spend loitering in the area..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Decide against loitering in this area.", MENU);
			}
			if(index == 1) {
				return new ResponseEffectsOnly("15 minutes", "Loiter in this area for the next fifteen minutes.") {
					@Override
					public void effects() {
//						Main.mainController.openPhone();
						Main.game.getTextStartStringBuilder().append(
								"<p style='text-align:center;'>"
										+ "<i>You spend the next fifteen minutes loitering about, doing nothing in particular...</i>"
								+ "</p>");
						Main.game.endTurn(60*15);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
					}
				};
				
			} else if(index == 2) {
				return new ResponseEffectsOnly("1 hour", "Loiter in this area for the next hour.") {
					@Override
					public void effects() {
//						Main.mainController.openPhone();
						Main.game.getTextStartStringBuilder().append(
								"<p style='text-align:center;'>"
										+ "<i>You spend the next hour loitering about, doing nothing in particular...</i>"
								+ "</p>");
						Main.game.endTurn(60*60);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
					}
				};
				
			} else if(index == 3) {
				return new ResponseEffectsOnly("4 hours", "Loiter in this area for the next four hours.") {
					@Override
					public void effects() {
//						Main.mainController.openPhone();
						Main.game.getTextStartStringBuilder().append(
								"<p style='text-align:center;'>"
										+ "<i>You spend the next four hours loitering about, doing nothing in particular...</i>"
								+ "</p>");
						Main.game.endTurn(60*60*4);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
					}
				};
				
			} else if(index == 4) {
				return new ResponseEffectsOnly("8 hours", "Loiter in this area for the next eight hours.") {
					@Override
					public void effects() {
//						Main.mainController.openPhone();
						Main.game.getTextStartStringBuilder().append(
								"<p style='text-align:center;'>"
										+ "<i>You spend the next eight hours loitering about, doing nothing in particular...</i>"
								+ "</p>");
						Main.game.endTurn(60*60*8);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
					}
				};
				
			} else if(index == 5) {
				return new ResponseEffectsOnly("12 hours", "Loiter in this area for the next twelve hours.") {
					@Override
					public void effects() {
//						Main.mainController.openPhone();
						Main.game.getTextStartStringBuilder().append(
								"<p style='text-align:center;'>"
										+ "<i>You spend the next twelve hours loitering about, doing nothing in particular...</i>"
								+ "</p>");
						Main.game.endTurn(60*60*12);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
					}
				};
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
}
