package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
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
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
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
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.TreeNode;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.comparators.ClothingTypeRarityComparator;
import com.lilithsthrone.utils.comparators.ItemTypeRarityComparator;
import com.lilithsthrone.utils.comparators.WeaponTypeRarityComparator;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.3.1
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
							?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Quests</span>"
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
							? "<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Perk Tree</span>"
							:"Perk Tree",
						"View your character page.", CHARACTER_PERK_TREE) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.levelUpHightlight, false);
					}
				};
				
			} else if (index == 3) {
				return new Response("Spells", "View your spells page.", CHARACTER_SPELLS_EARTH);
				
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
							? "<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Encyclopedia</span>"
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
					return new Response("Loiter", "This is not a suitable place in which to loitering about for four hours!", null);
				}
				return new ResponseEffectsOnly("Loiter", "Loiter in this area for the next four hours.") {
					@Override
					public int getSecondsPassed() {
						return 60*60*4;
					}
					@Override
					public void effects() {
						Main.mainController.openPhone();
						Main.game.getTextStartStringBuilder().append(
								"<p style='text-align:center;'>"
								+ "<i>You spent the next four hours loitering about, doing nothing in particular...</i>"
								+ "</p>");
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
					}
				};
				
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
	
	public static final DialogueNode AFTER_MASTURBATION = new DialogueNode("Finished", "You've had enough of maturbating for now.", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/misc", "AFTER_MASTURBATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way...", Main.game.getDefaultDialogueNoEncounter());

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
			journalSB.append("<details open>"
					+ "<summary class='quest-title' style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";'>" + QuestLine.MAIN.getName() + "</summary>");

			TreeNode<Quest> currentNode = QuestLine.MAIN.getQuestTree().getFirstNodeWithData(Main.game.getPlayer().getQuest(QuestLine.MAIN));
			
			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.MAIN)) {
				journalSB.append(getQuestBoxDiv(currentNode.getData(), false));
				currentNode = currentNode.getParent();
			}
			
			while(currentNode!=null) {
				journalSB.append(getQuestBoxDiv(currentNode.getData(), true));
				currentNode = currentNode.getParent();
			}
			journalSB.append("</details>");

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", null);
				
			} else if (index == 2) {
				return new Response((Main.game.getPlayer().isSideQuestUpdated()
						?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Side quests</span>"
						:"Side quests"), "View your side quests.", PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
				
			} else if (index == 3) {
				return new Response((Main.game.getPlayer().isRelationshipQuestUpdated()
						?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Relationship quests</span>"
						:"Relationship quests"), "View your relationship quests.", PLANNER_RELATIONSHIP){
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

					TreeNode<Quest> currentNode = questLine.getQuestTree().getFirstNodeWithData(Main.game.getPlayer().getQuest(questLine));
					
					if (Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
									+ "Completed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(currentNode.getData(), true));
						
					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(currentNode.getData(), false));
					}

					currentNode = currentNode.getParent();
						
					while(currentNode!=null) {
						journalSB.append(getQuestBoxDiv(currentNode.getData(), true));
						currentNode = currentNode.getParent();
					}
	
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
				return new Response((Main.game.getPlayer().isRelationshipQuestUpdated()
						?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Relationship quests</span>"
						:"Relationship quests"), "View your relationship quests.", PLANNER_RELATIONSHIP){
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
					
					TreeNode<Quest> currentNode = questLine.getQuestTree().getFirstNodeWithData(Main.game.getPlayer().getQuest(questLine));
					
					if (Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
									+ "Completed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(currentNode.getData(), true));
						
					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(currentNode.getData(), false));
					}

					currentNode = currentNode.getParent();
						
					while(currentNode!=null) {
						journalSB.append(getQuestBoxDiv(currentNode.getData(), true));
						currentNode = currentNode.getParent();
					}
	
					journalSB.append("</details>");
				}
			}
			
			if(!relationshipQuestFound) {
				journalSB.append("<div class='subTitle'>You haven't got any relationship quests!</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response((Main.game.getPlayer().isSideQuestUpdated()
						?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Side quests</span>"
						:"Side quests"), "View your side quests.", PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
			} else if (index == 3) {
				return new Response("Relationship quests", "View your relationship quests.", null);
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
					+ "<p style='color:" + Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
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
				return "<b class='quest-extra level' style='color:"+  Colour.GENERIC_GOOD.toWebHexString() + ";'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
				
			} else if (q.getLevel() >= Main.game.getPlayer().getLevel() + 3) {
				return "<b class='quest-extra level' style='color:"+  Colour.GENERIC_BAD.toWebHexString() + ";'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
				
			} else {
				return "<b class='quest-extra level'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
			}
			
		} else {
			return "<b class='quest-extra level' style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Level " + q.getLevel() + "</b>"
					+ "<b class='quest-extra experience' style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
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

	public static final DialogueNode CHARACTER_STATS = new DialogueNode("Character Stats", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			StatusEffect physiqueSE = PhysiqueLevel.getPhysiqueLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)).getRelatedStatusEffect();
			StatusEffect arcaneSE = IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)).getRelatedStatusEffect();
			StatusEffect corruptionSE = CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)).getRelatedStatusEffect();
					
			UtilText.nodeContentSB.append(
					
				"<details>"
				+ "<summary>[style.boldExcellent(Stats Mechanics)]</summary>"
					+ "<p style='text-align:center;padding:margin:0;'>"
						+ "All derived stats start to have diminishing returns past the half-way point!<br/>"
						+ "<b>For example:</b><br/>"
						+ "<b>25</b> <b style='color:"+Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Units.number(Util.getModifiedDropoffValue(25, 100))+"% damage</i><br/>"
						+ "<b>50</b> <b style='color:"+Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Units.number(Util.getModifiedDropoffValue(50, 100))+"% damage</i><br/>"
						+ "<i>Past this point, there are diminishing returns.</i><br/>"
						+ "<b>60</b> <b style='color:"+Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Units.number(Util.getModifiedDropoffValue(60, 100))+"% damage</i><br/>"
						+ "<b>80</b> <b style='color:"+Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Units.number(Util.getModifiedDropoffValue(80, 100))+"% damage</i><br/>"
						+ "<b>100</b> <b style='color:"+Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Units.number(Util.getModifiedDropoffValue(100, 100))+"% damage</i><br/>"
					+ "</p>"
				+ "</details>"
					
				+ "<div class='container-full-width'>"
					+ "<h4 style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>Core Attributes</h4>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_PHYSIQUE,
							"'<b style='color:"+physiqueSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(physiqueSE.getName(Main.game.getPlayer()))+"</b>' effect gained")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_ARCANE,
							"'<b style='color:"+arcaneSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(arcaneSE.getName(Main.game.getPlayer()))+"</b>' effect gained")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_CORRUPTION,
							"'<b style='color:"+corruptionSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(corruptionSE.getName(Main.game.getPlayer()))+"</b>' effect gained")

					// This is displayed in inventory screen, so no point showing it here
//					+ (Main.game.isEnchantmentCapacityEnabled()
//						?"<div class='container-full-width' style='text-align:center; background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'>"
//								+ "<b style='color:"+Colour.GENERIC_ENCHANTMENT.toWebHexString()+";'>"+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+":</b>"
//								+ " <i>"
//									+(int)Main.game.getPlayer().getAttributeValue(Attribute.ENCHANTMENT_LIMIT)
//								+"</i>"
//							+ "</div>"
//						:"")
					
				+"</div>"
				+"<div class='container-full-width'>"

					+ "<h4 style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Misc. Attributes</h4>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.FERTILITY,
							"<b>"+Units.number(Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.FERTILITY), Attribute.FERTILITY.getUpperLimit()))+"</b> Fertility Factor")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.VIRILITY,
							"<b>"+Units.number(Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.VIRILITY), Attribute.VIRILITY.getUpperLimit()))+"</b> Virility Factor")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.SPELL_COST_MODIFIER,
							"<b>-"+Units.number(Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.SPELL_COST_MODIFIER), Attribute.SPELL_COST_MODIFIER.getUpperLimit()))+"%</b> Spell cost")

					+ "<div class='container-full-width' style='text-align:center; background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'>"
						+ "<b style='color:"+Colour.BASE_PINK_LIGHT.toWebHexString()+";'>Pregnancy calculation:</b> <i>"+GameCharacter.PREGNANCY_CALCULATION+"</i>"
					+ "</div>"

				+"</div>"
				+"<div class='container-full-width'>"
				
					+ "<h4 style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Combat Attributes</h4>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.CRITICAL_DAMAGE,
							"<b>"+Units.number(Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.CRITICAL_DAMAGE), Attribute.CRITICAL_DAMAGE.getUpperLimit()))+"%</b> Critical Hit Damage",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.ENERGY_SHIELDING,
							"<b>"+Units.number(Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.ENERGY_SHIELDING), Attribute.ENERGY_SHIELDING.getUpperLimit()))+"</b> Health Block/Turn",
							true)
					


					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_UNARMED,
							"<b>"+Units.number((100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_UNARMED), Attribute.DAMAGE_UNARMED.getUpperLimit())))+"%</b> Unarmed Damage",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_SPELLS,
							"<b>"+Units.number((100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_SPELLS), Attribute.DAMAGE_SPELLS.getUpperLimit())))+"%</b> Spell Damage",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_MELEE_WEAPON,
							"<b>"+Units.number((100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_MELEE_WEAPON), Attribute.DAMAGE_MELEE_WEAPON.getUpperLimit())))+"%</b> Melee Damage",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_RANGED_WEAPON,
							"<b>"+Units.number((100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_RANGED_WEAPON), Attribute.DAMAGE_RANGED_WEAPON.getUpperLimit())))+"%</b> Ranged Damage",
							true)
					
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_PHYSICAL,
							"<b>"+Units.number((100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_PHYSICAL), Attribute.DAMAGE_PHYSICAL.getUpperLimit())))+"%</b> Physical Damage",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_PHYSICAL,
							"<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_PHYSICAL))+"</b> Physical Block/Turn",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_FIRE,
							"<b>"+Units.number((100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_FIRE), Attribute.DAMAGE_FIRE.getUpperLimit())))+"%</b> Fire Damage",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_FIRE,
							"<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_FIRE))+"</b> Fire Block/Turn",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_ICE,
							"<b>"+Units.number((100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_ICE), Attribute.DAMAGE_ICE.getUpperLimit())))+"%</b> Ice Damage",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_ICE,
							"<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_ICE))+"</b> Ice Block/Turn",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_POISON,
							"<b>"+Units.number((100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_POISON), Attribute.DAMAGE_POISON.getUpperLimit())))+"%</b> Poison Damage",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_POISON,
							"<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_POISON))+"</b> Poison Block/Turn",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_LUST,
							"<b>"+Units.number((100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST), Attribute.DAMAGE_LUST.getUpperLimit())))+"%</b> Lust Damage",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_LUST,
							"<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_LUST))+"</b> Lust Block/Turn",
							true)

				+"</div>"
				+"<div class='container-full-width'>"
					+ "<h6 style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Racial values</h6>");
			
			List<Attribute> encounteredAttributes = new ArrayList<>();
			for(Subspecies subspecies : Subspecies.values()) {
				Attribute damageModifier = subspecies.getDamageMultiplier();
				if(!encounteredAttributes.contains(damageModifier)) {
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), damageModifier,
									"<b>"+Units.number((100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(damageModifier), damageModifier.getUpperLimit())))+"%</b> "+Util.capitaliseSentence(damageModifier.getName()),
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
		
		return "<h6 style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+"; text-align:center;'>Core Attributes</h6>"
				+ statHeader()
				+ statRow(Colour.ANDROGYNOUS, "Femininity",
						Colour.TEXT, String.valueOf(character.getFemininityValue()),
						character.getFemininity().getColour(), Util.capitaliseSentence(character.getFemininity().getName(false)),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Height",
						Colour.TEXT, Units.size(character.getHeightValue()),
						character.getHeight().getColour(), Util.capitaliseSentence(character.getHeight().getDescriptor()),
						false)
				+ statRow(Colour.MUSCLE_THREE, "Muscle Definition",
						Colour.TEXT, String.valueOf(character.getMuscleValue()),
						character.getMuscle().getColour(), Util.capitaliseSentence(character.getMuscle().getName(false)),
						true)
				+ statRow(Colour.BODY_SIZE_THREE, "Body Size",
						Colour.TEXT, String.valueOf(character.getBodySizeValue()),
						character.getBodySize().getColour(), Util.capitaliseSentence(character.getBodySize().getName(false)),
						false)
				+ statRow(character.getBodyShape().toWebHexStringColour(), "Body Shape",
						Colour.TEXT,
						"<b style='color:"+character.getMuscle().getColour().toWebHexString()+";'>"+character.getMuscleValue()+"</b>"
								+ " <b>|</b> <b style='color:"+character.getBodySize().getColour().toWebHexString()+";'>"+character.getBodySizeValue()+"</b>",
						character.getBodyShape().toWebHexStringColour(), Util.capitaliseSentence(character.getBodyShape().getName(false)),
						true)

				+ "<span style='height:16px;width:100%;float:left;'></span>"
				+ "<h6 style='color:"+Colour.TRANSFORMATION_GREATER.toWebHexString()+"; text-align:center;'>Head & Throat Attributes</h6>"
//				+ statHeader()
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Hair Length",
						Colour.TEXT, Units.size(character.getHairRawLengthValue()),
						character.getHairLength().getColour(), Util.capitaliseSentence(character.getHairLength().getDescriptor()),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Tongue length",
						Colour.TEXT, Units.size(character.getTongueLengthValue()),
						Colour.TRANSFORMATION_GENERIC, Util.capitaliseSentence(character.getTongueLength().getDescriptor()),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Throat Wetness",
						Colour.TEXT, String.valueOf(character.getFaceWetness().getValue()),
						Colour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceWetness().getDescriptor()),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Throat Capacity",
						Colour.TEXT, Units.size(character.getFaceRawCapacityValue()),
						Colour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceCapacity().getDescriptor()),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Throat Elasticity",
						Colour.TEXT, String.valueOf(character.getFaceElasticity().getValue()),
						Colour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceElasticity().getDescriptor()),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Throat Plasticity",
						Colour.TEXT, String.valueOf(character.getFacePlasticity().getValue()),
						Colour.GENERIC_SEX, Util.capitaliseSentence(character.getFacePlasticity().getDescriptor()),
						true)
				
				+ "<span style='height:16px;width:100%;float:left;'></span>"
				+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Breast Attributes</h6>"
//				+ statHeader()
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Cup Size",
						Colour.TEXT, String.valueOf(character.getBreastRawSizeValue()),
						Colour.GENERIC_SEX, Util.capitaliseSentence(character.getBreastSize().getCupSizeName()),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Count",
						Colour.TEXT, String.valueOf(character.getBreastRows()),
						Colour.GENERIC_SEX, Util.capitaliseSentence(Util.capitaliseSentence(Util.intToString(character.getBreastRows()))+" pair"+(character.getBreastRows()==1?"":"s")),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Milk Storage",
						Colour.TEXT, !knowsNipples?"Unknown":Units.fluid(character.getBreastRawMilkStorageValue()),
						Colour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getBreastMilkStorage().getDescriptor()),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Milk Regeneration Per Breast",
						Colour.TEXT, !knowsNipples?"Unknown":Units.fluid(character.getLactationRegenerationPerSecond(false)*60)+"/minute",
						Colour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getBreastLactationRegeneration().getName()),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Milk Regeneration Total",
						Colour.TEXT, !knowsNipples?"Unknown":Units.fluid(character.getLactationRegenerationPerSecond(true)*60)+"/minute",
						Colour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getBreastLactationRegeneration().getName()),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Capacity",
						Colour.TEXT, !knowsNipples?"Unknown":Units.size(character.getNippleRawCapacityValue()),
						Colour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getNippleCapacity().getDescriptor()),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Elasticity",
						Colour.TEXT, !knowsNipples?"Unknown":String.valueOf(character.getNippleElasticity().getValue()),
						Colour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getNippleElasticity().getDescriptor()),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Plasticity",
						Colour.TEXT, !knowsNipples?"Unknown":String.valueOf(character.getNipplePlasticity().getValue()),
						Colour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getNipplePlasticity().getDescriptor()),
						true)
				
				+ (character.hasBreastsCrotch()
						?"<span style='height:16px;width:100%;float:left;'></span>"
							+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>"+(character.getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs")+" Attributes</h6>"
							+ statRow(Colour.TRANSFORMATION_GENERIC, "Size",
									Colour.TEXT, !character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples?"Unknown":String.valueOf(character.getBreastCrotchRawSizeValue()),
									Colour.GENERIC_SEX,
									!character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples
										?"Unknown"
										:(character.getBreastCrotchShape()==BreastShape.UDDERS
											?Util.capitaliseSentence(character.getBreastCrotchSize().getDescriptor())
											:Util.capitaliseSentence(character.getBreastCrotchSize().getCupSizeName())),
									true)
							+ statRow(Colour.TRANSFORMATION_GENERIC, "Count",
									Colour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getBreastCrotchRows()),
									Colour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(Util.intToString(character.getBreastCrotchRows()))+" pair"+(character.getBreastCrotchRows()==1?"":"s"),
									true)
							+ statRow(Colour.TRANSFORMATION_GENERIC, "Milk Storage",
									Colour.TEXT, !knowsCrotchNipples?"Unknown":Units.fluid(character.getBreastCrotchRawMilkStorageValue()),
									Colour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getBreastCrotchMilkStorage().getDescriptor()),
									false)
							+ statRow(Colour.TRANSFORMATION_GENERIC, "Milk Regeneration Per Crotch-boob",
									Colour.TEXT, !knowsCrotchNipples?"Unknown":Units.fluid(character.getCrotchLactationRegenerationPerSecond(false)*60)+"/minute",
									Colour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getBreastCrotchLactationRegeneration().getName()),
									false)
							+ statRow(Colour.TRANSFORMATION_GENERIC, "Milk Regeneration Total",
									Colour.TEXT, !knowsCrotchNipples?"Unknown":Units.fluid(character.getCrotchLactationRegenerationPerSecond(true)*60)+"/minute",
									Colour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getBreastCrotchLactationRegeneration().getName()),
									false)
							+ statRow(Colour.TRANSFORMATION_GENERIC, "Capacity (inches)",
									Colour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchRawCapacityValue()),
									Colour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getNippleCrotchCapacity().getDescriptor()),
									true)
							+ statRow(Colour.TRANSFORMATION_GENERIC, "Elasticity",
									Colour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchElasticity().getValue()),
									Colour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getNippleCrotchElasticity().getDescriptor()),
									false)
							+ statRow(Colour.TRANSFORMATION_GENERIC, "Plasticity",
									Colour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchPlasticity().getValue()),
									Colour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getNippleCrotchPlasticity().getDescriptor()),
									true)
						:"")
				
				+ "<span style='height:16px;width:100%;float:left;'></span>"
				+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Penis Attributes</h6>"
//				+ statHeader()
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Penis Size",
						Colour.TEXT, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : Units.size(character.getPenisRawSizeValue())),
						Colour.GENERIC_SEX, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : Util.capitaliseSentence(character.getPenisSize().getDescriptor())),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Testicle Size",
						Colour.TEXT, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getTesticleSize().getValue())),
						Colour.GENERIC_SEX, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : Util.capitaliseSentence(character.getTesticleSize().getDescriptor())),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Cum Storage",
						Colour.TEXT, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : Units.fluid(character.getPenisRawCumStorageValue())),
						Colour.GENERIC_SEX, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : Util.capitaliseSentence(character.getPenisCumStorage().getDescriptor())),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Cum Production Pregnancy Modifier",
						Colour.TEXT, !knowsPenis?"Unknown":(character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getPenisCumStorage().getPregnancyModifier())),
						Colour.GENERIC_SEX, !knowsPenis?"Unknown":"N/A",
						true)
				+ (Main.getProperties().hasValue(PropertyValue.cumRegenerationContent) ? statRow(Colour.TRANSFORMATION_GENERIC, "Cum Regeneration",
						Colour.TEXT, !knowsPenis?"Unknown":Units.fluid(character.getCumRegenerationPerSecond()*60)+"/minute",
						Colour.GENERIC_SEX, !knowsPenis?"Unknown":Util.capitaliseSentence(character.getPenisCumProductionRegeneration().getName()),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Cum Expulsion (% of stored cum)",
						Colour.TEXT, !knowsPenis?"Unknown":String.valueOf(character.getPenisRawCumExpulsionValue()),
						Colour.GENERIC_SEX, !knowsPenis?"Unknown":Util.capitaliseSentence(character.getPenisCumExpulsion().getDescriptor()),
						false) : "")
				
				+ "<span style='height:16px;width:100%;float:left;'></span>"
				+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Vagina Attributes</h6>"
//				+ statHeader()
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Clitoris Size",
						Colour.TEXT, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Units.size(character.getVaginaRawClitorisSizeValue())),
						Colour.GENERIC_SEX, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaClitorisSize().getDescriptor())),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Wetness",
						Colour.TEXT, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaWetness().getValue())),
						Colour.GENERIC_SEX, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaWetness().getDescriptor())),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Capacity",
						Colour.TEXT, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Units.size(character.getVaginaRawCapacityValue())),
						Colour.GENERIC_SEX, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaCapacity().getDescriptor())),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Elasticity",
						Colour.TEXT, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaElasticity().getValue())),
						Colour.GENERIC_SEX, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaElasticity().getDescriptor())),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Plasticity",
						Colour.TEXT, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaPlasticity().getValue())),
						Colour.GENERIC_SEX, !knowsVagina?"Unknown":(character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaPlasticity().getDescriptor())),
						true)
				
				+ "<span style='height:16px;width:100%;float:left;'></span>"
				+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Anus Attributes</h6>"
//				+ statHeader()
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Wetness",
						Colour.TEXT, !knowsAnus?"Unknown":String.valueOf(character.getAssWetness().getValue()),
						Colour.GENERIC_SEX, !knowsAnus?"Unknown":Util.capitaliseSentence(character.getAssWetness().getDescriptor()),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Capacity",
						Colour.TEXT, !knowsAnus?"Unknown":Units.size(character.getAssRawCapacityValue()),
						Colour.GENERIC_SEX, !knowsAnus?"Unknown":Util.capitaliseSentence(character.getAssCapacity().getDescriptor()),
						true)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Elasticity",
						Colour.TEXT, !knowsAnus?"Unknown":String.valueOf(character.getAssElasticity().getValue()),
						Colour.GENERIC_SEX, !knowsAnus?"Unknown":Util.capitaliseSentence(character.getAssElasticity().getDescriptor()),
						false)
				+ statRow(Colour.TRANSFORMATION_GENERIC, "Plasticity",
						Colour.TEXT, !knowsAnus?"Unknown":String.valueOf(character.getAssPlasticity().getValue()),
						Colour.GENERIC_SEX, !knowsAnus?"Unknown":Util.capitaliseSentence(character.getAssPlasticity().getDescriptor()),
						true)
				;
	}
	
	public static final DialogueNode CHARACTER_STATS_BODY = new DialogueNode("Body Stats", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Orifice Mechanics</summary>"
						
						+ "[style.boldSex(Capacity:)] An orifice's capacity determines the size of objects that can be comfortably inserted."
							+ " <b>Higher capacity values mean that the orifice can take larger insertions without stretching</b>."
							+ "<br/>Capacity values range from 0 (extremely tight) to 40 (gaping wide)."
						
						+ "<br/><br/>"
						
						+ "[style.boldSex(Elasticity:)] An orifice's elasticity determines how quickly it stretches out."
							+ " If a partner's penis is too large for your orifice's capacity value, then your orifice will stretch out each turn during sex, with <b>higher elasticity values meaning that it stretches out quicker</b>."
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
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "You have orgasmed [style.boldSex("+Main.game.getPlayer().getDaysOrgasmCount()+")] time"+(Main.game.getPlayer().getDaysOrgasmCount()==1?"":"s")
							+" today, bringing your total orgasm count to [style.boldSex("+Main.game.getPlayer().getTotalOrgasmCount()+")].<br/>"
						+ "Your record for most orgasms in one day is currently [style.boldArcane("+Main.game.getPlayer().getDaysOrgasmCountRecord()+")]."
					+ "</div>"
					
					+ sexStatHeader()
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Fingering",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Anal Fingering",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Blowjobs",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)))
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Cunnilingus",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Anilingus",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Vaginal sex",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Anal sex",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Nipple penetration",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Penis Urethra penetration",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)))
					
					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Vagina Urethra penetration",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)));
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
			String color = female ? Colour.FEMININE.toWebHexString() : Colour.MASCULINE.toWebHexString();
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

			OffspringHeaderDisplay(UtilText.nodeContentSB, "Mothered", "Sons", Colour.MASCULINE.toWebHexString(), sonsBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Mothered", "Daughters", Colour.FEMININE.toWebHexString(), daughtersBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Fathered", "Sons", Colour.MASCULINE.toWebHexString(), sonsFathered);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Fathered", "Daughters", Colour.FEMININE.toWebHexString(), daughtersFathered);

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
					+ "<div class='container-full-width' style='width:calc(33.3% - 16px); padding:0;'>"
						+ "Type"
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ "Given"
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ "Cum Given"
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ "Taken"
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ "Cum Taken"
					+ "</div>"
				+ "</div>";
	}
	
	private static String sexStatRow(Colour colour, String name, int given, int loadsGiven, int received, int loadsReceived) {
		return "<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; text-align:center;'>"
					+ "<div class='container-full-width' style='width:calc(33.3% - 16px); padding:0;'>"
						+ "<span style='color:" + colour.toWebHexString() + ";'>" + name + "</span>"
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ given
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ (loadsGiven < 0 ? "<span class='option-disabled'>-</span>" : loadsGiven)
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
						+ received
					+ "</div>"
					+ "<div class='container-full-width' style='width:calc(16.66% - 16px); padding:0;'>"
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
			
			for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsMother()){
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
					+ "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>You have never been pregnant!</span>"
					+ "</div>");
		}
		
		// Fathered children:

		noPregnancies=true;
		
		contentSB.append("<span style='height:16px;width:100%;float:left;'></span>"
				+ "<div class='subTitle'>Fathered children</div>");
		
		for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsFather()){
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
					+ "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>You have never got anyone pregnant!</span>"
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

	private static String statRow(String colourLeft, String left, Colour colourCentre, String centre, String colourRight, String right, boolean light) {
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(light?"background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
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
	
	private static String statRow(Colour colourLeft, String left, Colour colourCentre, String centre, Colour colourRight, String right, boolean light) {
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(light?"background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='color:"+colourLeft.toWebHexString()+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ left
					+ "</div>"
					+ "<div style='color:"+(centre.equalsIgnoreCase("unknown")?Colour.TEXT_GREY:colourCentre).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ centre
					+ "</div>"
					+ "<div style='color:"+(right.equalsIgnoreCase("unknown")?Colour.TEXT_GREY:colourRight).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ right
					+ "</div>"
				+ "</div>";
	}

	private static String getAttributeBox(GameCharacter owner, Attribute att, String effect) {
		return getAttributeBox(owner, att, effect, false);
	}
	
	private static String getAttributeBox(GameCharacter owner, Attribute att, String effect, boolean half) {
		return "<div class='container-full-width' style='background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'>"
				
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:30%;'>"
						+ "<b style='color:" + att.getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(att.getName())+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;'>"
						+(owner.getBaseAttributeValue(att) > 0 
								? "<b style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";"
								: (owner.getBaseAttributeValue(att) < 0
									? "<b style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";"
									: "<b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";"))+"'>"
							+Units.number(owner.getBaseAttributeValue(att), 1, 1)
						+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;'>"
						+ (owner.getBonusAttributeValue(att) > 0 
								? "<b style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";"
								: (owner.getBonusAttributeValue(att) < 0
									? "<b style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";"
									: "<b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";"))+"'>"
							+Units.number(owner.getBonusAttributeValue(att), 1, 1)
						+"</b>"
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;text-align:right;'>"
						+ "<b style='color:"
							+(owner.getAttributeValue(att)>=att.getUpperLimit() || (att==Attribute.MAJOR_CORRUPTION&&owner.getAttributeValue(att)==0)?Colour.GENERIC_EXCELLENT:att.getColour()).toWebHexString()+";'>"
							+Units.number(owner.getAttributeValue(att), 1, 1)
						+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;text-align:left;'>"
						+ "[style.boldDisabled(/"+att.getUpperLimit()+")]"
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:38%;'>"
						+ "<i>"+effect.replaceAll("<br/>", " ")+"</i>"
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
							"Take a detailed look at what " + npc.getName("the") + " looks like.",
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
	
	
	public static final DialogueNode ENCYCLOPEDIA = new DialogueNode("Encyclopedia", "", true) {

		@Override
		public String getContent() {
			return "<p>"
					+ "You have collected information on all the races, weapons, clothing, and items that you've encountered in your travels."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Races</span>":"Races",
						"Have a look at all the different races that you've encountered in your travels.", RACES){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newRaceDiscovered, false);
					}
				};
			
			} else if (index == 2) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered))?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Weapons</span>":"Weapons",
						"Have a look at all the different weapons that you've encountered in your travels.", WEAPON_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
					}
				};
			
			} else if (index == 3) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newClothingDiscovered))?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Clothing</span>":"Clothing",
						"Have a look at all the different clothing that you've encountered in your travels.", CLOTHING_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
					}
				};
			
			} else if (index == 4) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newItemDiscovered))?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Items</span>":"Items",
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
	
	static {
		
		itemsDiscoveredList.addAll(ItemType.getAllItems());
		itemsDiscoveredList.sort(new ItemTypeRarityComparator());
		
		weaponsDiscoveredList.addAll(WeaponType.getAllWeapons());
		weaponsDiscoveredList.sort(new WeaponTypeRarityComparator());
		
		clothingDiscoveredList.addAll(ClothingType.getAllClothing());
		clothingDiscoveredList.sort(new ClothingTypeRarityComparator());
	}
	public static final DialogueNode WEAPON_CATALOGUE = new DialogueNode("Discovered Weapons", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			journalSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<i>Hover over the coloured icons to see preview pictures of each weapon.</i>"
					+ "</div>");
			
			for (AbstractWeaponType weapon : weaponsDiscoveredList) {
				if (Main.getProperties().isWeaponDiscovered(weapon)) {
					journalSB.append(
							"<div class='container-full-width' style='margin-bottom:0;'>"
							+ "<div class='container-full-width' style='width:calc(60% - 16px)'>"
									+ "<b style='color:" + weapon.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(weapon.getName()) + "</b> ("+(weapon.isMelee()?"Melee":"Ranged")+")"
							+ "</div>"
							+ "<div class='container-full-width' style='width:calc(40% - 16px)'>");
					
					for (DamageType dt : weapon.getAvailableDamageTypes()) {
						journalSB.append("<div class='phone-item-colour' id='" + (weapon.hashCode() + "_" + dt.toString()) + "' style='background-color:" + dt.getMultiplierAttribute().getColour().toWebHexString() + ";'></div>");
					}
					
					journalSB.append("</div>"
							+ "</div>");
					
				} else {
					journalSB.append(
						"<div class='container-full-width' style='text-align:center; margin-bottom:0;'>"
								+ "[style.boldDisabled(Undiscovered)]"
						+ "</div>");
				}
			}

			return journalSB.toString();
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
	public static final DialogueNode CLOTHING_CATALOGUE = new DialogueNode("Discovered Clothing", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			journalSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<i>Hover over the coloured icons to see preview pictures of each item of clothing.</i>"
					+ "</div>");
			
			for (AbstractClothingType clothing : clothingDiscoveredList) {
				if (Main.getProperties().isClothingDiscovered(clothing)) {
					journalSB.append(
							"<div class='container-full-width' style='margin-bottom:0;'>"
							+ "<div class='container-full-width' style='width:calc(40% - 16px)'>"
									+ "<b style='color:" + clothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(clothing.getName()) + "</b> (");
					
					for(int i=0; i<clothing.getEquipSlots().size(); i++) {
						InventorySlot slot = clothing.getEquipSlots().get(i);
						journalSB.append(Util.capitaliseSentence(slot.getName())+(i==clothing.getEquipSlots().size()-1?"":"/"));
					}
					
					journalSB.append(")</div>"
							+ "<div class='container-full-width' style='width:calc(60% - 16px)'>");
					
					for (Colour c : clothing.getAllAvailablePrimaryColours()) {
						journalSB.append("<div class='phone-item-colour' id='" + (clothing.hashCode() + "_" + c.toString()) + "' style='background-color:" + c.toWebHexString() + ";'></div>");
					}
					
					journalSB.append("</div>"
							+ "</div>");
					
				} else {
					journalSB.append(
						"<div class='container-full-width' style='text-align:center; margin-bottom:0;'>"
								+ "[style.boldDisabled(Undiscovered (");

					for(int i=0; i<clothing.getEquipSlots().size(); i++) {
						InventorySlot slot = clothing.getEquipSlots().get(i);
						journalSB.append(Util.capitaliseSentence(slot.getName())+(i==clothing.getEquipSlots().size()-1?"":"/"));
					}
					
					journalSB.append("))]"
						+ "</div>");
				}
			}

			return journalSB.toString();
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
	public static final DialogueNode ITEM_CATALOGUE = new DialogueNode("Discovered items", "View discovered items", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();
			
			journalSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<i>Hover over the 'i' icons to see preview pictures of each item.</i>"
					+ "</div>");
			
			for (AbstractItemType item : itemsDiscoveredList) {
				if (Main.getProperties().isItemDiscovered(item)) {
					journalSB.append(
							"<div class='container-full-width' style='margin-bottom:0;'>"
							+ "<div class='container-full-width' style='width:calc(40% - 16px)'>"
									+ "<div class='title-button' id='"+ItemType.getItemToIdMap().get(item)+"' style='background:transparent; position:relative; top:0; left:0; float:left; margin:0 8px 0 0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
									+ " <b style='color:" + item.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(item.getName(false)) + "</b>"
							+ "</div>"
							+ "<div class='container-full-width' style='width:calc(60% - 16px)'>");
					
					if (item.getEffects().isEmpty()) {
						journalSB.append("-");
					} else {
						int i=1;
						for(ItemEffect ie : item.getEffects()) {
							for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
								journalSB.append(s);
								if(i != ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size())
									journalSB.append("<br/>");
								i++;
							}
						}
					}
					
					journalSB.append("</div>"
							+ "</div>");
					
				} else {
					journalSB.append(
						"<div class='container-full-width' style='text-align:center; margin-bottom:0;'>"
								+ "[style.boldDisabled(Undiscovered)]"
						+ "</div>");
				}
			}
			
			return journalSB.toString();
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

	public static final DialogueNode RACES = new DialogueNode("Discovered races", "View discovered races", true) {

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
	
	public static final DialogueNode SUBSPECIES = new DialogueNode("Discovered races", "View discovered races", true) {

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
				"<div class='container-full-width' style='width:calc(40% - 16px); float:right;'>"
					+ "<p style='width:100%; text-align:center;'><b style='color:"+subspeciesSelected.getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getName(null))+"</b><br/>"
							+ "Average stats</p>"
					+ "<table align='center'>"
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
							+ "<td>"+Units.size(maleBody.getPenis().getRawSizeValue())+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Vagina capacity</td>"
							+ "<td>"+Util.capitaliseSentence(femaleBody.getVagina().getOrificeVagina().getCapacity().getDescriptor())+"</td>"
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
						:"<p style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"
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
							return Colour.GENERIC_GOOD;
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
	
	public static final DialogueNode CHARACTER_SPELLS_ARCANE = new DialogueNode("Arcane Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.ARCANE, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.ARCANE.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldArcane(School of Arcane ability:)] "
								+(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)
									?"[style.colourDisabled("+SpellSchool.ARCANE.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Arcane school spells to unlock.)"
									:"[style.colourGood("+SpellSchool.ARCANE.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Earth", "View your spells and upgrades in the school of Earth.", CHARACTER_SPELLS_EARTH);
				
			} else if(index==2) {
				return new Response("Water", "View your spells and upgrades in the school of Water.", CHARACTER_SPELLS_WATER);
				
			} else if(index==3) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);
				
			} else if(index==4) {
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);
				
			} else if(index==5) {
				return new Response("Arcane", "You are already viewing your Arcane spells!", null);
				
			} else if(index==6) {
				if(Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_ARCANE)) {
					if(!Main.game.getSavedDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false))) {
						return new Response("Arcane Elemental", "You can only summon your elemental in combat, or in a neutral scene!", null);
						
					} else if(Main.game.getPlayer().getMana()<Spell.ELEMENTAL_ARCANE.getModifiedCost(Main.game.getPlayer())) {
						return new Response("Arcane Elemental", "You need at least <b>"+Spell.ELEMENTAL_ARCANE.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)] in order to cast this spell!", null);
						
					} else {
						return new Response("Arcane Elemental",
								"Summon your elemental by binding it to the school of Arcane! This will cost <b>"+Spell.ELEMENTAL_ARCANE.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_ARCANE) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+Spell.ELEMENTAL_ARCANE.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false)
										+"</p>");
							}
						};
					}
					
				} else {
					return new Response("Arcane Elemental", "You don't know how to bind your elemental to the school of Arcane! (Requires spell: '"+Spell.ELEMENTAL_ARCANE.getName()+"')", null);
				}
				
			} else if(index==11) {
				return new Response("Reset Arcane", "Reset your Arcane upgrades, refunding all points spent. Your spells will not be reset.", CHARACTER_SPELLS_ARCANE) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetSpellUpgrades(SpellSchool.ARCANE);
					}
				};
				
			} else if (index == 0) {
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
	
	public static final DialogueNode CHARACTER_SPELLS_EARTH = new DialogueNode("Earth Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.EARTH, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.EARTH.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldEarth(School of Earth ability:)] "
								+(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
									?"[style.colourDisabled("+SpellSchool.EARTH.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Earth school spells to unlock.)"
									:"[style.colourGood("+SpellSchool.EARTH.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Earth", "You are already viewing your Earth spells.", null);
				
			} else if(index==2) {
				return new Response("Water", "View your spells and upgrades in the school of Water.", CHARACTER_SPELLS_WATER);
				
			} else if(index==3) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);
				
			} else if(index==4) {
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);
				
			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);
				
			} else if(index==6) {
				if(Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_EARTH)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						if(Main.game.isInCombat()) {
							return new Response("Earth Elemental", "While in combat, use the combat spells menu to summon your elemental!", null);
						} else {
							return new Response("Earth Elemental", "You can only summon your elemental in a neutral scene!", null);
						}
						
					} else if(Main.game.getPlayer().getMana()<Spell.ELEMENTAL_EARTH.getModifiedCost(Main.game.getPlayer())) {
						return new Response("Earth Elemental", "You need at least <b>"+Spell.ELEMENTAL_EARTH.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)] in order to cast this spell!", null);
						
					} else {
						return new Response("Earth Elemental",
								"Summon your elemental by binding it to the school of Earth! This will cost <b>"+Spell.ELEMENTAL_EARTH.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_EARTH) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+Spell.ELEMENTAL_EARTH.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false)
										+"</p>");
							}
						};
					}
					
				} else {
					return new Response("Earth Elemental", "You don't know how to bind your elemental to the school of Earth! (Requires spell: '"+Spell.ELEMENTAL_EARTH.getName()+"')", null);
				}
				
			}  else  if(index==11) {
				return new Response("Reset Earth", "Reset your Earth upgrades, refunding all points spent. Your spells will not be reset.", CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetSpellUpgrades(SpellSchool.EARTH);
					}
				};
				
			} else if (index == 0) {
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
	
	public static final DialogueNode CHARACTER_SPELLS_WATER = new DialogueNode("Water Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.WATER, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.WATER.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldWater(School of Water ability:)] "
								+(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)
									?"[style.colourDisabled("+SpellSchool.WATER.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Water school spells to unlock.)"
									:"[style.colourGood("+SpellSchool.WATER.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Earth", "View your spells and upgrades in the school of Earth.", CHARACTER_SPELLS_EARTH);
				
			} else if(index==2) {
				return new Response("Water", "You are already viewing your Water spells!", null);
				
			} else if(index==3) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);
				
			} else if(index==4) {
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);
				
			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);
				
			} else if(index==6) {
				if(Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_WATER)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						if(Main.game.isInCombat()) {
							return new Response("Water Elemental", "While in combat, use the combat spells menu to summon your elemental!", null);
						} else {
							return new Response("Water Elemental", "You can only summon your elemental in a neutral scene!", null);
						}
						
					} else if(Main.game.getPlayer().getMana()<Spell.ELEMENTAL_WATER.getModifiedCost(Main.game.getPlayer())) {
						return new Response("Water Elemental", "You need at least <b>"+Spell.ELEMENTAL_WATER.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)] in order to cast this spell!", null);
						
					} else {
						return new Response("Water Elemental",
								"Summon your elemental by binding it to the school of Water! This will cost <b>"+Spell.ELEMENTAL_WATER.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_WATER) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+Spell.ELEMENTAL_WATER.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false)
										+"</p>");
							}
						};
					}
					
				} else {
					return new Response("Water Elemental", "You don't know how to bind your elemental to the school of Water! (Requires spell: '"+Spell.ELEMENTAL_WATER.getName()+"')", null);
				}
				
			} else if(index==11) {
				return new Response("Reset Water", "Reset your Water upgrades, refunding all points spent. Your spells will not be reset.", CHARACTER_SPELLS_WATER) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetSpellUpgrades(SpellSchool.WATER);
					}
				};
				
			} else if (index == 0) {
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
	
	public static final DialogueNode CHARACTER_SPELLS_AIR = new DialogueNode("Air Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.AIR, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.AIR.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldAir(School of Air ability:)] "
								+(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.AIR)
									?"[style.colourDisabled("+SpellSchool.AIR.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Air school spells to unlock.)"
									:"[style.colourGood("+SpellSchool.AIR.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Earth", "View your spells and upgrades in the school of Earth.", CHARACTER_SPELLS_EARTH);
				
			} else if(index==2) {
				return new Response("Water", "View your spells and upgrades in the school of Water.", CHARACTER_SPELLS_WATER);
				
			} else if(index==3) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);
				
			} else if(index==4) {
				return new Response("Air", "You are already viewing your Air spells!", null);
				
			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);
				
			} else if(index==6) {
				if(Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_AIR)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						if(Main.game.isInCombat()) {
							return new Response("Air Elemental", "While in combat, use the combat spells menu to summon your elemental!", null);
						} else {
							return new Response("Air Elemental", "You can only summon your elemental in a neutral scene!", null);
						}
						
					} else if(Main.game.getPlayer().getMana()<Spell.ELEMENTAL_AIR.getModifiedCost(Main.game.getPlayer())) {
						return new Response("Air Elemental", "You need at least <b>"+Spell.ELEMENTAL_AIR.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)] in order to cast this spell!", null);
						
					} else {
						return new Response("Air Elemental",
								"Summon your elemental by binding it to the school of Air! This will cost <b>"+Spell.ELEMENTAL_AIR.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_AIR) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+Spell.ELEMENTAL_AIR.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false)
										+"</p>");
							}
						};
					}
					
				} else {
					return new Response("Air Elemental", "You don't know how to bind your elemental to the school of Air! (Requires spell: '"+Spell.ELEMENTAL_AIR.getName()+"')", null);
				}
				
			}  else if(index==11) {
				return new Response("Reset Air", "Reset your Air upgrades, refunding all points spent. Your spells will not be reset.", CHARACTER_SPELLS_AIR) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetSpellUpgrades(SpellSchool.AIR);
					}
				};
				
			} else if (index == 0) {
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
	
	public static final DialogueNode CHARACTER_SPELLS_FIRE = new DialogueNode("Fire Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.FIRE, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.FIRE.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldFire(School of Fire ability:)] "
								+(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE)
									?"[style.colourDisabled("+SpellSchool.FIRE.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Fire school spells to unlock.)"
									:"[style.colourGood("+SpellSchool.FIRE.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Earth", "View your spells and upgrades in the school of Earth.", CHARACTER_SPELLS_EARTH);
				
			} else if(index==2) {
				return new Response("Water", "View your spells and upgrades in the school of Water.", CHARACTER_SPELLS_WATER);
				
			} else if(index==3) {
				return new Response("Fire", "You are already viewing your Fire spells!", null);
				
			} else if(index==4) {
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);
				
			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);
				
			} else if(index==6) {
				if(Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_FIRE)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						if(Main.game.isInCombat()) {
							return new Response("Fire Elemental", "While in combat, use the combat spells menu to summon your elemental!", null);
						} else {
							return new Response("Fire Elemental", "You can only summon your elemental in a neutral scene!", null);
						}
						
					} else {
						String description = "Summon your elemental by binding it to the school of Fire! This will cost <b>"+Spell.ELEMENTAL_FIRE.getModifiedCost(Main.game.getPlayer())+"</b> [style.boldMana(aura)]!";
						if(Main.game.getPlayer().getMana()<Spell.ELEMENTAL_FIRE.getModifiedCost(Main.game.getPlayer())) {
							description = "Summon your elemental by binding it to the school of Fire! This will cost <b>"
									+Math.round(Spell.ELEMENTAL_FIRE.getModifiedCost(Main.game.getPlayer())*0.25f)+"</b> [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]!";
						}
						return new Response("Fire Elemental",
								description,
								CHARACTER_SPELLS_FIRE) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+Spell.ELEMENTAL_FIRE.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false)
										+"</p>");
							}
						};
					}
					
				} else {
					return new Response("Fire Elemental", "You don't know how to bind your elemental to the school of Fire! (Requires spell: '"+Spell.ELEMENTAL_FIRE.getName()+"')", null);
				}
				
			} else if(index==11) {
				return new Response("Reset Fire", "Reset your Fire upgrades, refunding all points spent. Your spells will not be reset.", CHARACTER_SPELLS_FIRE) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetSpellUpgrades(SpellSchool.FIRE);
					}
				};
				
			} else if (index == 0) {
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
							+ "Your currently selected desire has a "+Colour.FETISH.getName()+" border, but your true desire (indicated by the coloured desire icon) may be modified by enchanted clothes or other items.<br/><br/>"
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
			journalSB.append(getFetishEntry(Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_ANAL_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_BREASTS_OTHERS, Fetish.FETISH_BREASTS_SELF));
			journalSB.append(getFetishEntry(Fetish.FETISH_LACTATION_OTHERS, Fetish.FETISH_LACTATION_SELF));
			journalSB.append(getFetishEntry(Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER));
			journalSB.append(getFetishEntry(Fetish.FETISH_FOOT_GIVING, Fetish.FETISH_FOOT_RECEIVING));
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
			journalSB.append(getFetishEntry(Fetish.FETISH_MASTURBATION, Fetish.FETISH_INCEST));
			
			// Derived fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>Derived Fetishes</h6></div>");
			journalSB.append("<div class='fetish-container'>");
			
			for(Fetish fetish : Fetish.values()) {
				if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					journalSB.append(
							"<div id='fetishUnlock" + fetish + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:2px solid " + Colour.FETISH.getShades()[1] + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:2px solid " +  Colour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + Colour.TEXT_GREY.toWebHexString() + ";'>"))
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString()+"</div>"
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
					+ getIndividualFetishEntry(selfFetish)
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
							? " owned' style='border:2px solid " + Colour.FETISH.toWebHexString() + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:2px solid " + Colour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + Colour.TEXT_GREY.toWebHexString() + ";'>"))
										+ "<div class='fetish-icon-content'>"+fetish.getSVGString()+"</div>"
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
								?"border:2px solid "+Colour.FETISH.getShades()[1]+";"
								:"")+"width:10%; margin:0 5%; float:left; cursor:pointer;'>"
				+ "<div class='square-button-content'>"+(Main.game.getPlayer().getFetishDesire(fetish)==desire?desire.getSVGImage():desire.getSVGImageDesaturated())+"</div>"
				+ (Main.game.getPlayer().hasFetish(fetish) && Main.game.getPlayer().getFetishDesire(fetish)!=desire
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8; border-radius:5px;'></div>"
					:Main.game.getPlayer().getFetishDesire(fetish)!=desire
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; border-radius:5px;'></div>"
						:"")
			+ "</div>";
	}
	
	public static WorldType worldTypeMap = WorldType.DOMINION;

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
			for(WorldType world : WorldType.values()) {
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
										return Colour.GENERIC_GOOD;
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
								return Colour.GENERIC_GOOD;
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
}
