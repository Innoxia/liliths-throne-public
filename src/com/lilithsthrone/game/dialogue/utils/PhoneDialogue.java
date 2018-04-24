package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.Litter;
import com.lilithsthrone.game.character.PregnancyPossibility;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.ClothingRarityComparator;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ItemRarityComparator;
import com.lilithsthrone.utils.TreeNode;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.WeaponRarityComparator;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia, tukaima
 */
public class PhoneDialogue {

	private static StringBuilder journalSB;
	public static final DialogueNodeOld MENU = new DialogueNodeOld("Phone home screen", "Phone", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return RenderingEngine.ENGINE.getFullMap(Main.game.getPlayer().getWorldLocation())
					+"<p>You pull out your phone and tap in the unlock code.</p>"
					+ (Main.game.isInNewWorld()
							?"<p>"
								+"Using your powerful aura, you've managed to figure out a way to channel the arcane into charging the battery of your phone, although considering that it's the only one in this world,"
									+ " it's not much use for calling anyone."
								+ " Instead, you're using it as a way to store information about things you've discovered in this strange new world."
							+ "</p>"
							:"");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						(Main.game.getPlayer().isMainQuestUpdated() || Main.game.getPlayer().isSideQuestUpdated() || Main.game.getPlayer().isRomanceQuestUpdated())
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
							? "<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Perks</span>"
							:"Perks",
						"View your character page.", CHARACTER_LEVEL_UP) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.levelUpHightlight, false);
					}
				};
				
			} else if (index == 3) {
				return new Response("Spells", "View your spells page.", CHARACTER_SPELLS_EARTH);
//					@Override
//					public DialogueNodeOld getNextDialogue() {
//						Map<SpellSchool, Integer> schoolMap = new HashMap<>();
//						for(Spell s : Main.game.getPlayer().getSpells()) {
//							schoolMap.putIfAbsent(s.getSpellSchool(), 0);
//							schoolMap.put(s.getSpellSchool(), schoolMap.get(s.getSpellSchool())+1);
//						}
//						for(SpellUpgrade su : Main.game.getPlayer().getSpellUpgrades()) {
//							schoolMap.putIfAbsent(su.getSpellSchool(), 0);
//							schoolMap.put(su.getSpellSchool(), schoolMap.get(su.getSpellSchool())+1);
//						}
//						
//						SpellSchool favouredSchool
//						
//						schoolMap.entrySet()
//						
//						return CHARACTER_SPELLS_ARCANE;
//					}
//				};
				
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
					return new Response("Contacts", "Even though you can't call anyone, on account of there being no phones in this world, you've still kept a record of all the people you've come into contact with.", CONTACTS);
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
					return new Response("Transform", "Transform your body.", BodyChanging.BODY_CHANGING_CORE) {
						@Override
						public void effects() {
							BodyChanging.setTarget(Main.game.getPlayer());
						}
					};
				} else {
					return new Response("Transform", "Only demons and slimes can transform themselves!", null);
				}
				
			} else if (index == 0){
				return new ResponseEffectsOnly("Back", "Put your phone away."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent();
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};

	public static final DialogueNodeOld PLANNER_MAIN = new DialogueNodeOld("Planner", "", true) {
		private static final long serialVersionUID = 1L;

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
				return new Response((Main.game.getPlayer().isRomanceQuestUpdated()
						?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Romance quests</span>"
						:"Romance quests"), "View your romance quests.", PLANNER_ROMANCE){
					@Override
					public void effects() {
						Main.game.getPlayer().setRomanceQuestUpdated(false);
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	public static final DialogueNodeOld PLANNER_SIDE = new DialogueNodeOld("Planner", "", true) {
		private static final long serialVersionUID = 1L;

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
				return new Response((Main.game.getPlayer().isRomanceQuestUpdated()
						?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Romance quests</span>"
						:"Romance quests"), "View your romance quests.", PLANNER_ROMANCE){
					@Override
					public void effects() {
						Main.game.getPlayer().setRomanceQuestUpdated(false);
					}
				};
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	public static final DialogueNodeOld PLANNER_ROMANCE = new DialogueNodeOld("Planner", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean romanceQuestsFound = false;
			
			// Side Quests:
			for (QuestLine questLine : Main.game.getPlayer().getQuests().keySet()) {
				if(questLine.getType()==QuestType.ROMANCE) {
					romanceQuestsFound = true;
					
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
			
			if(!romanceQuestsFound) {
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
						?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Side quests</span>"
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
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
	

	public static final DialogueNodeOld CHARACTER_APPEARANCE = new DialogueNodeOld("Selfie picture", "Take a selfie", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
//			return Main.game.getPlayer().getBodyDescription();
			return Main.game.getPlayer().getCharacterInformationScreen();
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};

	public static final DialogueNodeOld CHARACTER_STATS = new DialogueNodeOld("Character Stats", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					
				"<details>"
				+ "<summary>[style.boldExcellent(Important information)]</summary>"
					+ "<p style='text-align:center;padding:margin:0;'>"
						+ "All derived stats start to have diminishing returns past the half-way point!</br>"
						+ "<b>For example:</b></br>"
						+ "<b>25</b> <b style='color:"+Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Util.getModifiedDropoffValue(25, 100)+"% damage</i></br>"
						+ "<b>50</b> <b style='color:"+Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Util.getModifiedDropoffValue(50, 100)+"% damage</i></br>"
						+ "<i>Past this point, there are diminishing returns.</i></br>"
						+ "<b>60</b> <b style='color:"+Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Util.getModifiedDropoffValue(60, 100)+"% damage</i></br>"
						+ "<b>80</b> <b style='color:"+Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Util.getModifiedDropoffValue(80, 100)+"% damage</i></br>"
						+ "<b>100</b> <b style='color:"+Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString()+";'>Physical Damage</b> = <i>+"+Util.getModifiedDropoffValue(100, 100)+"% damage</i></br>"
					+ "</p>"
				+ "</details>"
					
				+ "<div class='container-full-width'>"
					+ "<h4 style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>Core Attributes</h4>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_PHYSIQUE, "")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_ARCANE, "")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_CORRUPTION, "")
					
				+"</div>"
				+"<div class='container-full-width'>"

					+ "<h4 style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Misc. Attributes</h4>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.FERTILITY,
							"Pregnancy Chance:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.FERTILITY), Attribute.FERTILITY.getUpperLimit())+"%</b>")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.VIRILITY,
							"Impregnation Chance:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.VIRILITY), Attribute.VIRILITY.getUpperLimit())+"%</b>")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.SPELL_COST_MODIFIER,
							"Spell Cost:</br>"
							+ "<b>-"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.SPELL_COST_MODIFIER), Attribute.SPELL_COST_MODIFIER.getUpperLimit())+"%</b>")

					+ "<div class='container-full-width' style='text-align:center; background:#292929;'>"
						+ "<b style='color:"+Colour.BASE_PINK_LIGHT.toWebHexString()+";'>Pregnancy calculation:</b> <i>"+GameCharacter.PREGNANCY_CALCULATION+"</i>"
					+ "</div>"

				+"</div>"
				+"<div class='container-full-width'>"
				
					+ "<h4 style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Combat Attributes</h4>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.CRITICAL_CHANCE,
							"Critical Hit Chance:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.CRITICAL_CHANCE), Attribute.CRITICAL_CHANCE.getUpperLimit())+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.CRITICAL_DAMAGE,
							"Critical Hit Damage:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.CRITICAL_DAMAGE), Attribute.CRITICAL_DAMAGE.getUpperLimit())+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DODGE_CHANCE,
							"Dodge Chance:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DODGE_CHANCE), Attribute.DODGE_CHANCE.getUpperLimit())+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MISS_CHANCE,
							"Miss Chance:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.MISS_CHANCE), Attribute.MISS_CHANCE.getUpperLimit())+"%</b>",
							true)
					


					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_UNARMED,
							"Unarmed Damage:</br>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_UNARMED), Attribute.DAMAGE_UNARMED.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_SPELLS,
							"Spell Damage:</br>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_SPELLS), Attribute.DAMAGE_SPELLS.getUpperLimit()))+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_MELEE_WEAPON,
							"Melee Weapon Damage:</br>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_MELEE_WEAPON), Attribute.DAMAGE_MELEE_WEAPON.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_RANGED_WEAPON,
							"Ranged Weapon Damage:</br>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_RANGED_WEAPON), Attribute.DAMAGE_RANGED_WEAPON.getUpperLimit()))+"%</b>",
							true)
					
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_PHYSICAL,
							"Physical Damage:</br>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_PHYSICAL), Attribute.DAMAGE_PHYSICAL.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_PHYSICAL,
							"Physical Resistance:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_PHYSICAL), Attribute.RESISTANCE_PHYSICAL.getUpperLimit())+"%</b>",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_FIRE,
							"Fire Damage:</br>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_FIRE), Attribute.DAMAGE_FIRE.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_FIRE,
							"Fire Resistance:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_FIRE), Attribute.RESISTANCE_FIRE.getUpperLimit())+"%</b>",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_ICE,
							"Ice Damage:</br>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_ICE), Attribute.DAMAGE_ICE.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_ICE,
							"Ice Resistance:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_ICE), Attribute.RESISTANCE_ICE.getUpperLimit())+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_POISON,
							"Poison Damage:</br>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_POISON), Attribute.DAMAGE_POISON.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_POISON,
							"Poison Resistance:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_POISON), Attribute.RESISTANCE_POISON.getUpperLimit())+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_LUST,
							"Lust Damage:</br>"
							+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST), Attribute.DAMAGE_LUST.getUpperLimit()))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_LUST,
							"Lust Resistance:</br>"
							+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(Attribute.RESISTANCE_LUST), Attribute.RESISTANCE_LUST.getUpperLimit())+"%</b>",
							true)

				+"</div>"
				+"<div class='container-full-width'>"
					+ "<h6 style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Racial values</h6>");
			
			for(Race race : Race.values()) {

				UtilText.nodeContentSB.append(
						getAttributeBox(Main.game.getPlayer(), race.getDamageMultiplier(),
								Util.capitaliseSentence(race.getName())+" Damage:</br>"
								+ "<b>"+(100+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(race.getDamageMultiplier()), race.getDamageMultiplier().getUpperLimit()))+"%</b>",
								true)
						+ getAttributeBox(Main.game.getPlayer(), race.getResistanceMultiplier(),
								Util.capitaliseSentence(race.getName())+" Resistance:</br>"
								+ "<b>"+Util.getModifiedDropoffValue(Main.game.getPlayer().getAttributeValue(race.getResistanceMultiplier()), race.getResistanceMultiplier().getUpperLimit())+"%</b>",
								true));
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld CHARACTER_STATS_BODY = new DialogueNodeOld("Body Stats", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Orifice Mechanics</summary>"
						
						+ "[style.boldSex(Capacity:)] An orifice's capacity determines the size of objects that can be comfortably inserted."
							+ " <b>Higher capacity values mean that the orifice can take larger insertions without stretching</b>."
							+ "</br>Capacity values range from 0 (extremely tight) to 40 (gaping wide)."
						
						+ "</br></br>"
						
						+ "[style.boldSex(Elasticity:)] An orifice's elasticity determines how quickly it stretches out."
							+ " If a partner's penis is too large for your orifice's capacity value, then your orifice will stretch out each turn during sex, with <b>higher elasticity values meaning that it stretches out quicker</b>."
							+ "</br>Elasticity values range from 0 (extremely resistant to being stretched out) to 7 (instantly stretching out)."
							
							+ "</br></br>"
							
						+ "[style.boldSex(Plasticity:)] An orifice's plasticity determines how quickly it recovers after being stretched out."
							+ " If your orifice has been stretched out during sex, <b>higher plasticity values mean that it will recover slower, with very high values meaning that it will never recover all of its original tightness</b>."
							+ "</br>Plasticity values range from 0 (instantly returns to starting size after sex) to 7 (recovers none of its original size after sex)."
				+ "</details>"
						
				+ "<div class='container-full-width'>"
					+ "<h6 style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+"; text-align:center;'>Core Attributes</h6>"
					+ statHeader()
					+ statRow(Colour.ANDROGYNOUS, "Femininity",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getFemininityValue()),
							Main.game.getPlayer().getFemininity().getColour(), Util.capitaliseSentence(Main.game.getPlayer().getFemininity().getName(false)),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Height (cm)",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getHeightValue()),
							Main.game.getPlayer().getHeight().getColour(), Util.capitaliseSentence(Main.game.getPlayer().getHeight().getDescriptor()),
							false)
					+ statRow(Colour.MUSCLE_THREE, "Muscle Definition",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getMuscleValue()),
							Main.game.getPlayer().getMuscle().getColour(), Util.capitaliseSentence(Main.game.getPlayer().getMuscle().getName(false)),
							true)
					+ statRow(Colour.BODY_SIZE_THREE, "Body Size",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getBodySizeValue()),
							Main.game.getPlayer().getBodySize().getColour(), Util.capitaliseSentence(Main.game.getPlayer().getBodySize().getName(false)),
							false)
					+ statRow(Main.game.getPlayer().getBodyShape().toWebHexStringColour(), "Body Shape",
							Colour.TEXT,
							"<b style='color:"+Main.game.getPlayer().getMuscle().getColour().toWebHexString()+";'>"+Main.game.getPlayer().getMuscleValue()+"</b>"
									+ " <b>|</b> <b style='color:"+Main.game.getPlayer().getBodySize().getColour().toWebHexString()+";'>"+Main.game.getPlayer().getBodySizeValue()+"</b>",
							Main.game.getPlayer().getBodyShape().toWebHexStringColour(), Util.capitaliseSentence(Main.game.getPlayer().getBodyShape().getName(false)),
							true)

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.TRANSFORMATION_GREATER.toWebHexString()+"; text-align:center;'>Head & Throat Attributes</h6>"
					+ statHeader()
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Hair Length (inches)",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getHairRawLengthValue()),
							Main.game.getPlayer().getHairLength().getColour(), Util.capitaliseSentence(Main.game.getPlayer().getHairLength().getDescriptor()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Tongue length (inches)",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getTongueLengthValue()),
							Colour.TRANSFORMATION_GENERIC, Util.capitaliseSentence(Main.game.getPlayer().getTongueLength().getDescriptor()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Throat Wetness",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getFaceWetness().getValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getFaceWetness().getDescriptor()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Throat Capacity (inches)",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getFaceRawCapacityValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getFaceCapacity().getDescriptor()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Throat Elasticity",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getFaceElasticity().getValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getFaceElasticity().getDescriptor()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Throat Plasticity",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getFacePlasticity().getValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getFacePlasticity().getDescriptor()),
							true)
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Breast Attributes</h6>"
					+ statHeader()
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Cup Size",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getBreastRawSizeValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getBreastSize().getCupSizeName()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Milk Storage (mL)",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getBreastRawMilkStorageValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getBreastMilkStorage().getDescriptor()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Milk Regeneration (%/minute)",
							Colour.TEXT, String.valueOf(Math.round((Main.game.getPlayer().getBreastLactationRegeneration().getPercentageRegen()*100)*100)/100f),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getBreastLactationRegeneration().getName()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Capacity (inches)",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getNippleRawCapacityValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getNippleCapacity().getDescriptor()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Elasticity",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getNippleElasticity().getValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getNippleElasticity().getDescriptor()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Plasticity",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getNipplePlasticity().getValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getNipplePlasticity().getDescriptor()),
							true)
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Penis Attributes</h6>"
					+ statHeader()
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Penis Size (inches)",
							Colour.TEXT, Main.game.getPlayer().getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getPenisRawSizeValue()),
							Colour.GENERIC_SEX, Main.game.getPlayer().getPenisType() == PenisType.NONE ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getPenisSize().getDescriptor()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Testicle Size",
							Colour.TEXT, Main.game.getPlayer().getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getTesticleSize().getValue()),
							Colour.GENERIC_SEX, Main.game.getPlayer().getPenisType() == PenisType.NONE ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getTesticleSize().getDescriptor()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Cum Production (mL)",
							Colour.TEXT, Main.game.getPlayer().getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getPenisRawCumProductionValue()),
							Colour.GENERIC_SEX, Main.game.getPlayer().getPenisType() == PenisType.NONE ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getPenisCumProduction().getDescriptor()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Cum Production Pregnancy Modifier",
							Colour.TEXT, Main.game.getPlayer().getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getPenisCumProduction().getPregnancyModifier()),
							Colour.GENERIC_SEX,
							"N/A",
							true)
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Vagina Attributes</h6>"
					+ statHeader()
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Clitoris Size (inches)",
							Colour.TEXT, Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getVaginaRawClitorisSizeValue()),
							Colour.GENERIC_SEX, Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getVaginaClitorisSize().getDescriptor()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Wetness",
							Colour.TEXT, Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getVaginaWetness().getValue()),
							Colour.GENERIC_SEX, Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getVaginaWetness().getDescriptor()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Capacity (inches)",
							Colour.TEXT, Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getVaginaRawCapacityValue()),
							Colour.GENERIC_SEX, Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getVaginaCapacity().getDescriptor()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Elasticity",
							Colour.TEXT, Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getVaginaElasticity().getValue()),
							Colour.GENERIC_SEX, Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getVaginaElasticity().getDescriptor()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Plasticity",
							Colour.TEXT, Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getVaginaPlasticity().getValue()),
							Colour.GENERIC_SEX, Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getVaginaPlasticity().getDescriptor()),
							true)
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Anus Attributes</h6>"
					+ statHeader()
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Wetness",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getAssWetness().getValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getAssWetness().getDescriptor()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Capacity (inches)",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getAssRawCapacityValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getAssCapacity().getDescriptor()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Elasticity",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getAssElasticity().getValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getAssElasticity().getDescriptor()),
							false)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Plasticity",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getAssPlasticity().getValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getAssPlasticity().getDescriptor()),
							true)
					
					+"</div>");
			
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld CHARACTER_STATS_SEX = new DialogueNodeOld("Sex Stats", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "You have orgasmed [style.boldSex("+Main.game.getPlayer().getDaysOrgasmCount()+")] time"+(Main.game.getPlayer().getDaysOrgasmCount()==1?"":"s")
							+" today, bringing your total orgasm count to [style.boldSex("+Main.game.getPlayer().getTotalOrgasmCount()+")].</br>"
						+ "Your record for most orgasms in one day is currently [style.boldArcane("+Main.game.getPlayer().getDaysOrgasmCountRecord()+")]."
					+ "</div>"
					
					+ sexStatHeader()
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Fingering",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.VAGINA)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.FINGER, OrificeType.VAGINA)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Anal Fingering",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.ANUS)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.FINGER, OrificeType.ANUS)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Blowjobs",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH)))
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Cunnilingus",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.VAGINA)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.VAGINA)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Anilingus",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.ANUS)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.ANUS)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Vaginal sex",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.VAGINA)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.VAGINA)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Anal sex",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.ANUS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.ANUS)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Nipple penetration",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.NIPPLE)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.NIPPLE)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.NIPPLE)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.NIPPLE)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Penis Urethra penetration",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.URETHRA_PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.URETHRA_PENIS)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.URETHRA_PENIS)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.URETHRA_PENIS)))
					
					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Vagina Urethra penetration",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.URETHRA_VAGINA)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.URETHRA_VAGINA)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.URETHRA_VAGINA)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.URETHRA_VAGINA)));
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld CHARACTER_STATS_PREGNANCY = new DialogueNodeOld("Pregnancy Stats", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			
			int sonsBirthed=0, daughtersBirthed=0,
					sonsFathered=0, daughtersFathered=0;
			for (Litter litter : Main.game.getPlayer().getLittersBirthed()){
				sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
				daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
			}
			for (Litter litter : Main.game.getPlayer().getLittersFathered()){
				sonsFathered+=litter.getSonsFromMother()+litter.getSonsFromFather();
				daughtersFathered+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
			}
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<div class='extraAttribute-quarter'>"
						+ "Mothered</br><b style='color:"+Colour.MASCULINE.toWebHexString()+";'>Sons</b></br>" + sonsBirthed
					+ "</div>"
					+ "<div class='extraAttribute-quarter'>"
						+ "Mothered</br><b style='color:"+Colour.FEMININE.toWebHexString()+";'>Daughters</b></br>" + daughtersBirthed
					+ "</div>"
					
					+ "<div class='extraAttribute-quarter'>"
						+ "Fathered</br><b style='color:"+Colour.MASCULINE.toWebHexString()+";'>Sons</b></br>" + sonsFathered
					+ "</div>"
					+ "<div class='extraAttribute-quarter'>"
						+ "Fathered</br><b style='color:"+Colour.FEMININE.toWebHexString()+";'>Daughters</b></br>" + daughtersFathered
					+ "</div>"
					+"<div class='subTitle'>Total offspring: "+(sonsBirthed+daughtersBirthed+sonsFathered+daughtersFathered)+"</div>"
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					
					+ pregnancyDetails()

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+"<div class='subTitle'>Offspring list</div>"
					+ "<div class='container-full-width' style='text-align:center;'>"
					
					+ "<table align='center'>"
					+ "<tr><th>Name</th><th>Race</th><th>Mother</th><th>Father</th></tr>"
					+ "<tr style='height:8px;'></tr>");
			
			for(NPC npc : Main.game.getOffspring()) {
				if(npc.isFeminine()) {
					UtilText.nodeContentSB.append(
							"<tr>"
								+ "<td style='min-width:100px;'>"
									+ "<b style='color:"+Colour.FEMININE.toWebHexString()+";'>"+(Main.game.getPlayer().getCharactersEncountered().contains(npc.getId())?npc.getName():"Unknown")+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b style='color:"+npc.getRace().getColour().toWebHexString()+";'>"+npc.getSubspecies().getOffspringFemaleNameSingular()+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b>"+(npc.getMother()==null?"???":(npc.getMother().isPlayer()?"You":npc.getMother().getName()))+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b>"+(npc.getFather()==null?"???":(npc.getFather().isPlayer()?"You":npc.getFather().getName()))+"</b>"
								+ "</td>"
							+ "</tr>");
				} else {
					UtilText.nodeContentSB.append(
							"<tr>"
								+ "<td style='min-width:100px;'>"
									+ "<b style='color:"+Colour.MASCULINE.toWebHexString()+";'>"+(Main.game.getPlayer().getCharactersEncountered().contains(npc.getId())?npc.getName():"Unknown")+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b style='color:"+npc.getRace().getColour().toWebHexString()+";'>"+npc.getSubspecies().getOffspringMaleNameSingular()+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b>"+(npc.getMother()==null?"???":(npc.getMother().isPlayer()?"You":npc.getMother().getName()))+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b>"+(npc.getFather()==null?"???":(npc.getFather().isPlayer()?"You":npc.getFather().getName()))+"</b>"
								+ "</td>"
							+ "</tr>");
				}
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
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
					+ "</br>"
					+ "[style.bold(Possible partners:)]");
			
			for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsMother()){
				if(pp.getFather()!=null) {
					contentSB.append(UtilText.parse(pp.getFather(),
							"</br><b>[npc.Name(A)] (</b>"
								+ (!pp.getFather().getRaceStage().getName().isEmpty()
										?"<b style='color:"+pp.getFather().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(pp.getFather().getRaceStage().getName())+"</b> "
										:"")
								+ "<b style='color:"+pp.getFather().getRace().getColour().toWebHexString()+";'>"
								+ (pp.getFather().getGender().isFeminine()?Util.capitaliseSentence(pp.getFather().getSubspecies().getSingularFemaleName()):Util.capitaliseSentence(pp.getFather().getSubspecies().getSingularMaleName()))
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
								+ "</br>"
								+ "Conceived with [npc.name(a)] on day " + litter.getDayOfConception() + ", delivered on day " + litter.getDayOfBirth() + "."
								+ "</br>"
								+ "You gave birth to "+ litter.getBirthedDescriptionList()+ "."
							+ "</div>"));
				} else {
					contentSB.append(
							"<div class='container-full-width' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "</br>"
								+ "Conceived with someone you can't remember on day " + litter.getDayOfConception() + ", delivered on day " + litter.getDayOfBirth() + "."
								+ "</br>"
								+ "You gave birth to "+ litter.getBirthedDescriptionList()+ "."
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
						+ "</br>"
						+"<b>[npc.Name(A)] (</b>"
							+ (!pp.getMother().getRaceStage().getName().isEmpty()
									?"<b style='color:"+pp.getMother().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(pp.getMother().getRaceStage().getName())+"</b> "
									:"")
							+ "<b style='color:"+pp.getMother().getRace().getColour().toWebHexString()+";'>"
							+ (pp.getMother().getGender().isFeminine()?Util.capitaliseSentence(pp.getMother().getSubspecies().getSingularFemaleName()):Util.capitaliseSentence(pp.getMother().getSubspecies().getSingularMaleName()))
							+ "</b><b>)</b>"));
				
				if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_0)) {
					contentSB.append("</br>Probability of impregnation: ");
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
						contentSB.append("</br>Pregnancy stage: [style.boldSex("+Util.capitaliseSentence(StatusEffect.PREGNANT_1.getName(pp.getMother()))+")]");
						
					} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_2)) {
						contentSB.append("</br>Pregnancy stage: [style.boldSex("+Util.capitaliseSentence(StatusEffect.PREGNANT_2.getName(pp.getMother()))+")]");
						
					} else {
						contentSB.append("</br>Pregnancy stage: [style.boldSex("+Util.capitaliseSentence(StatusEffect.PREGNANT_3.getName(pp.getMother()))+")]");
						
					}
				}
				
				contentSB.append("</b></br>");
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
								+ "</br>"
								+ "Conceived with [npc.name(a)] on day " + litter.getDayOfConception() + ", delivered on day " + litter.getDayOfBirth() + "."
								+ "</br>"
								+ "[npc.She] gave birth to "+ litter.getBirthedDescriptionList()+ "."
							+ "</div>"));
					
				} else {
					contentSB.append(
							"<div class='container-full-width' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "</br>"
								+ "Conceived with someone you can't remember on day " + litter.getDayOfConception() + ", delivered on day " + litter.getDayOfBirth() + "."
								+ "</br>"
								+ "[npc.She] gave birth to "+ litter.getBirthedDescriptionList()+ "."
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
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(light?"background:#292929;'":"'")+">"
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
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(light?"background:#292929;'":"'")+">"
					+ "<div style='color:"+colourLeft.toWebHexString()+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ left
					+ "</div>"
					+ "<div style='color:"+colourCentre.toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ centre
					+ "</div>"
					+ "<div style='color:"+colourRight.toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ right
					+ "</div>"
				+ "</div>";
	}

	private static String getAttributeBox(GameCharacter owner, Attribute att, String effect) {
		return getAttributeBox(owner, att, effect, false);
	}
	
	private static String getAttributeBox(GameCharacter owner, Attribute att, String effect, boolean half) {
		return "<div class='container-half-width' style='"+(half?"width:calc(50% - 16px);":"width:calc(33% - 16px);")+" margin-bottom:0; background:#292929;'>"
					+ "<div class='container-half-width' style='width:66.6%;margin:0;background:#292929;'>"
						+ "<b style='color:" + att.getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(att.getName())+"</b>"
					+ "</div>"
					+ "<div class='container-half-width' style='width:33.3%;margin:0;background:#292929;text-align:center;'>"
						+ "<b"+(owner.getAttributeValue(att)==att.getUpperLimit()?" style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'":"")+">"+owner.getAttributeValue(att)+"</b>"
					+ "</div>"
					+ "<div class='container-full-width' style='height:6px;padding:0;border-radius: 2px;'>"
						+ "<div class='container-full-width' style='width:" + (owner.getAttributeValue(att)/att.getUpperLimit()) * (att.getLowerLimit()==0?100:50) + "%; padding:0;"
								+ " margin:0 0 0 "+(att.getLowerLimit()>=0?0:(owner.getAttributeValue(att)>0?"50%":(Math.abs(att.getLowerLimit())+owner.getAttributeValue(att))+"%"))+";"
								+ " height:100%; background:" + (owner.getAttributeValue(att)>0?att.getColour().toWebHexString():att.getColour().getShades()[1]) + "; float:left; border-radius: 2px;'></div>"
					+ "</div>"
					+ "<div class='container-half-width' style='margin:0;background:#292929; padding:0; text-align:center;'>"
							+ "Base: "+(owner.getBaseAttributeValue(att) > 0 
								? "<b style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";"
										: (owner.getBaseAttributeValue(att) < 0
												? "<b style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";"
												: "<b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";"))+"'>"
												+owner.getBaseAttributeValue(att)
												+"</b>"
					+ "</div>"
					+ "<div class='container-half-width' style='margin:0;background:#292929; padding:0; text-align:center;'>"
							+ "Bonus: "
							+ (owner.getBonusAttributeValue(att) > 0 
									? "<b style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";"
											: (owner.getBonusAttributeValue(att) < 0
													? "<b style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";"
													: "<b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";"))+"'>"
													+owner.getBonusAttributeValue(att)
													+"</b>"
					+ "</div>"
					+ (effect.length()>0
							?"<div class='container-full-width' style='margin:0;background:#292929; padding:0; text-align:center;'>"
								+"<hr></hr>"
								+ "<i>"+effect+"</i>"
							+ "</div>"
							:"")
				+ "</div>";
	}

	public static final DialogueNodeOld CONTACTS = new DialogueNodeOld("Contacts", "Look at your contacts.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>You have encountered the following characters in your travels:</p>");
			
			for (int i = 0; i < Main.game.getPlayer().getCharactersEncountered().size(); i++) {
				if(Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(i))!=null) {
					UtilText.nodeContentSB.append("<p>" + Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(i)).getName() + "</p>");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else if (index <= Main.game.getPlayer().getCharactersEncountered().size()) {
				GameCharacter npc = Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(index - 1));
				if(npc!=null) {
					return new Response(Util.capitaliseSentence(npc.getName()),
							"Take a detailed look at what " + npc.getName("the") + " looks like.",
							CONTACTS_CHARACTER){
						@Override
						public void effects() {
							CharactersPresentDialogue.resetContent(npc);
							
						}
					};
				} else {
					return null;
				}
			
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld CONTACTS_CHARACTER = new DialogueNodeOld("Contacts", "Look at your contacts.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return CharactersPresentDialogue.characterViewed.getName();
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
				GameCharacter npc = Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(index - 1));
				if(npc!=null) {
					return new Response(Util.capitaliseSentence(npc.getName()),
							"Take a detailed look at what " + npc.getName("the") + " looks like.",
							CONTACTS_CHARACTER){
						@Override
						public void effects() {
							CharactersPresentDialogue.resetContent(npc);
							
						}
					};
				} else {
					return null;
				}
			
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	
	public static final DialogueNodeOld ENCYCLOPEDIA = new DialogueNodeOld("Encyclopedia", "", true) {
		private static final long serialVersionUID = 1L;

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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};

	private static List<AbstractItemType> itemsDiscoveredList = new ArrayList<>();
	private static List<AbstractClothingType> clothingDiscoveredList = new ArrayList<>();
	private static List<AbstractWeaponType> weaponsDiscoveredList = new ArrayList<>();
	
	static {
		
		itemsDiscoveredList.addAll(ItemType.allItems);
		itemsDiscoveredList.sort(new ItemRarityComparator());
		
		weaponsDiscoveredList.addAll(WeaponType.allweapons);
		weaponsDiscoveredList.sort(new WeaponRarityComparator());
		
		clothingDiscoveredList.addAll(ClothingType.getAllClothing());
		clothingDiscoveredList.sort(new ClothingRarityComparator());
	}
	public static final DialogueNodeOld WEAPON_CATALOGUE = new DialogueNodeOld("Discovered Weapons", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {

			journalSB = new StringBuilder();

			// All known weapons:

			journalSB.append("<div class='extraAttribute-third slot'>Slot</div>");
			journalSB.append("<div class='extraAttribute-third name'>Weapon</div>");
			journalSB.append("<div class='extraAttribute-third colours'>Damage types <span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>(Hover for image)</span></div>");

			for (AbstractWeaponType weapon : weaponsDiscoveredList) {
				if (Main.getProperties().isWeaponDiscovered(weapon)) {
					journalSB.append("<div class='extraAttribute-third slot'>" + Util.capitaliseSentence(weapon.getSlot().getName()) + "</div>");
					journalSB.append("<div class='extraAttribute-third name' style='color:" + weapon.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(weapon.getName()) + "</div>");

					journalSB.append("<div class='extraAttribute-third colours'>");
					for (DamageType dt : weapon.getAvailableDamageTypes())
						journalSB.append("<div class='phone-item-colour' id='" + (weapon.hashCode() + "_" + dt.toString()) + "' style='background-color:" + dt.getMultiplierAttribute().getColour().toWebHexString() + ";'></div>");
					journalSB.append("</div>");

				} else {
					journalSB.append("<div class='extraAttribute-third slot'>" + Util.capitaliseSentence(weapon.getSlot().getName()) + "</div>");
					journalSB.append("<div class='extraAttribute-third name'>???</div>");

					journalSB.append("<div class='extraAttribute-third colours'>???</div>");
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	public static final DialogueNodeOld CLOTHING_CATALOGUE = new DialogueNodeOld("Discovered Clothing", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {

			journalSB = new StringBuilder();

			// All known weapons:

			journalSB.append("<div class='extraAttribute-third slot'>Slot</div>");
			journalSB.append("<div class='extraAttribute-third name'>Clothing</div>");
			journalSB.append("<div class='extraAttribute-third colours'>Colours <span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>(Hover for image)</span></div>");

			for (AbstractClothingType clothing : clothingDiscoveredList) {
				if (Main.getProperties().isClothingDiscovered(clothing)) {
					String sizeClass = ""; //hack to prevent overflow... works for up to 30 colours
					if (clothing.getAllAvailablePrimaryColours().size() > 15){
						sizeClass = "extraAttribute-third-large";
					}
					journalSB.append("<div class='extraAttribute-third "+sizeClass+" slot'>" + Util.capitaliseSentence(clothing.getSlot().getName()) + "</div>");
					journalSB.append("<div class='extraAttribute-third "+sizeClass+" name' style='color:" + clothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(clothing.getName()) + "</div>");

					journalSB.append("<div class='extraAttribute-third "+sizeClass+" colours'>");
					for (Colour c : clothing.getAllAvailablePrimaryColours())
						journalSB.append("<div class='phone-item-colour' id='" + (clothing.hashCode() + "_" + c.toString()) + "' style='background-color:" + c.toWebHexString() + ";'></div>");
					journalSB.append("</div>");

				} else {
					journalSB.append("<div class='extraAttribute-third slot'>" + Util.capitaliseSentence(clothing.getSlot().getName()) + "</div>");
					journalSB.append("<div class='extraAttribute-third name'>???</div>");

					journalSB.append("<div class='extraAttribute-third colours'>???</div>");
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	public static final DialogueNodeOld ITEM_CATALOGUE = new DialogueNodeOld("Discovered items", "View discovered items", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {

			// All known items:
			journalSB = new StringBuilder();
			journalSB.append("<div class='extraAttribute-third'>Item</div>");
			journalSB.append("<div class='extraAttribute-two-thirds'>Effects</div>");

			for (AbstractItemType item : itemsDiscoveredList) {
				if (Main.getProperties().isItemDiscovered(item)) {
					journalSB.append("<div class='extraAttribute-third' style='color:" + item.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(item.getName(false)) + "</div>");

					journalSB.append("<div class='extraAttribute-two-thirds'>");
					if (item.getEffects().isEmpty()) {
						journalSB.append("-");
					} else {
						int i=1;
						for(ItemEffect ie : item.getEffects()) {
							for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
								journalSB.append(s);
								if(i != ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size())
									journalSB.append("</br>");
								i++;
							}
						}
					}
					journalSB.append("</div>");

				} else {
					journalSB.append("<div class='extraAttribute-third'>???</div>");

					journalSB.append("<div class='extraAttribute-two-thirds'>???</div>");
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	private static List<Race> racesDiscovered = new ArrayList<>();
	private static String title, content;
	
	public static void resetContentForRaces() {
		title = "Races";
		StringBuilder contentSB = new StringBuilder("<p style='text-align:center;'>You have encountered the following races in your travels:</p>");
		
		racesDiscovered.clear();
		
		for (Race r : Race.values()) {
			if(Main.getProperties().isRaceDiscovered(r)) {
				racesDiscovered.add(r);
				contentSB.append("<p style='text-align:center;'><b style='color:"+r.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(r.getName()) + "</b></p>");
			}
		}
		
		racesDiscovered.sort(Comparator.comparing(Race::getName));
		
		content = contentSB.toString();
	}

	private static StringBuilder raceSB = new StringBuilder();
	public static final DialogueNodeOld RACES = new DialogueNodeOld("Discovered races", "View discovered races", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return title;
		}

		@Override
		public String getContent() {
			return content;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);
			
			} else if (index <= racesDiscovered.size()) {
				return new Response(Util.capitaliseSentence(racesDiscovered.get(index - 1).getName()),
						"Take a detailed look at what " + racesDiscovered.get(index - 1).getName() + "s are like.",
						RACES){
					@Override
					public void effects() {
						Race race = racesDiscovered.get(index - 1);
						RacialBody racialBody = RacialBody.valueOfRace(race);
						
						title = Util.capitaliseSentence(race.getName());
						raceSB.setLength(0);
						raceSB.append("<div class='container-full-width' style='width:calc(40% - 16px); float:right;'>"
								+ "<p style='width:100%; text-align:center;'><b style='color:"+race.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(race.getName())+"</b></br>"
										+ "Average stats</p>"
								+ "<table align='center'>"
									+ "<tr>"
										+ "<td>Height (cm)</td>"
										+ "<td>"+racialBody.getFemaleHeight()+"</td>"
										+ "<td>"+racialBody.getMaleHeight()+"</td>"
									+ "</tr>"
									+ "<tr>"
										+ "<td>Femininity</td>"
										+ "<td>"+racialBody.getFemaleFemininity()+"</td>"
										+ "<td>"+racialBody.getMaleFemininity()+"</td>"
									+ "</tr>"
									+ "<tr>"
										+ "<td>Breast size</td>"
										+ "<td>"+(racialBody.getBreastSize()==0
													?"Flat"
													:CupSize.getCupSizeFromInt(racialBody.getBreastSize()).getCupSizeName()+"-cup")+"</td>"
										+ "<td>"+(racialBody.getNoBreastSize()==0
													?"Flat"
													:CupSize.getCupSizeFromInt(racialBody.getNoBreastSize()).getCupSizeName()+"-cup")+"</td>"
									+ "</tr>"
									+ "<tr>"
										+ "<td>Penis size (inches)</td>"
										+ "<td>-</td>"
										+ "<td>"+racialBody.getPenisSize()+"</td>"
									+ "</tr>"
									+ "<tr>"
										+ "<td>Vagina capacity</td>"
										+ "<td>"+Util.capitaliseSentence(Capacity.getCapacityFromValue(racialBody.getVaginaCapacity()).getDescriptor())+"</td>"
										+ "<td>-</td>"
									+ "</tr>");
						
						
						raceSB.append("</table>"
								+ "</div>"
								+ "<details style='width:calc(60% - 16px); float:left;'>"
								+ "<summary>Subspecies</summary>");
						
						for(Subspecies sub : Subspecies.values()) {
							if(sub.getRace()==race) {
								raceSB.append(
										"<p>"
											+ "<b>Subspecies:</b> <b style='color:"+sub.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(sub.getName())+"</b>"
											+ "</br>"
											+ "(<span style='color:"+Femininity.valueOf(racialBody.getMaleFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(sub.getSingularMaleName())+"</span>"
											+ "/<span style='color:"+Femininity.valueOf(racialBody.getFemaleFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(sub.getSingularFemaleName())+"</span>)"
											+ "</br>"
											+ sub.getDescription()
										+ "</p>");
							}
						}
						
						
						raceSB.append(
								"</details>"
								+ "<h6>"+Util.capitaliseSentence(race.getName())+" Lore</h6>"
									+race.getBasicDescription()
									+ (Main.getProperties().isAdvancedRaceKnowledgeDiscovered(race)
										?race.getAdvancedDescription()
										:"<p style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"
											+ "Further information can be discovered in books!"
										+ "</p>"));
						content = raceSB.toString();
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};

	public static final DialogueNodeOld CHARACTER_LEVEL_UP = new DialogueNodeOld("Perks", "", true) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='padding:8px;'>"
						+ "<span style='color:"+Colour.PERK.toWebHexString()+";'>Perks</span> (circular icons) apply permanent boosts to your attributes.</br>"
						+ "<span style='color:"+Colour.TRAIT.toWebHexString()+";'>Traits</span> (square icons) provide unique effects for your character."
							+ " Unlike perks, <b>traits will have no effect on your character until they're slotted into your 'Active Traits' bar</b>.</br>"
						+ "Perks require perk points to unlock. You earn one perk point each time you level up, and earn an extra two perk points every five levels."
					+ "</div>"
					+ "<div class='container-full-width' style='padding:8px; text-align:center;'>"
					+ "<h6 style='text-align:center;'>Active Traits</h6>");

			UtilText.nodeContentSB.append("<div id='HISTORY_" + Main.game.getPlayer().getHistory().getAssociatedPerk() + "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + Colour.TRAIT.toWebHexString() + ";'>"
					+ "<div class='square-button-content'>"+Main.game.getPlayer().getHistory().getAssociatedPerk().getSVGString()+"</div>"
					+ "</div>");
			
			for(int i=0;i<GameCharacter.MAX_TRAITS;i++) {
				Perk p = null;
				if(i<Main.game.getPlayer().getTraits().size()) {
					p = Main.game.getPlayer().getTraits().get(i);
				}
				if(p!=null) {
					UtilText.nodeContentSB.append("<div id='TRAIT_" + p + "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + Colour.TRAIT.toWebHexString() + ";'>"
							+ "<div class='square-button-content'>"+p.getSVGString()+"</div>"
							+ "</div>");
					
				} else {
					UtilText.nodeContentSB.append("<div id='TRAIT_" + i + "' class='square-button small' style='display:inline-block; float:none;'></div>");
					
				}
			}
			UtilText.nodeContentSB.append("</div>"
					+ "<div class='container-full-width' style='padding:8px; text-align:center;'>"
						+ "<i>Please note that this perk tree is a work-in-progress. This is not the final version, and is just a proof of concept!</i>"
					+ "</div>");
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reset", "Reset all perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)", CHARACTER_LEVEL_UP) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetPerksMap();
						Main.game.getPlayer().setPerkPoints(Main.game.getPlayer().getPerkPointsAtLevel(Main.game.getPlayer().getLevel()));
						Main.game.getPlayer().clearTraits();
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld CHARACTER_SPELLS_ARCANE = new DialogueNodeOld("Arcane Spells", "", true) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.ARCANE, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+"<p>"
								+ "Focused on harnessing the most pure form of arcane energy, the spells in the school of Arcane are concerned with either influencing a person's lust, or performing extremely powerful miscellaneous abilities."
							+ "</p>"
							+ "<p>"
								+ "As the only publicly-available spells are the ones associated with influencing lust, the school of Arcane is overlooked by most demons, as their physical charms are more than adequate on this front."
								+ " The vast majority of the students of the school of Arcane can be found in the ranks of the cult of Lilith, who view this school as the one favoured by Lilith herself."
							+ "</p>"
							+ "<p>"
								+ "Once a prospective student has a basic grasp of Arcane spells, they will find that they're able to feel the ebb and flow of the arcane currents woven throughout the world,"
									+ " and will be able to accurately predict when the next arcane storm will break."
							+ "</p>"
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldArcane(School of Arcane ability:)] "
								+(!Main.game.getPlayer().hasAnySpellInSchool(SpellSchool.EARTH)
									?"[style.colourDisabled(Know accurate time until next arcane storm.)]</br>(Requires knowing at least one Arcane school spell to unlock.)"
									:"[style.colourGood(Know accurate time until next arcane storm.)]")
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
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);
				
			} else if(index==4) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);
				
			} else if(index==5) {
				return new Response("Arcane", "You are already viewing your Arcane spells!", null);
				
			} else if(index==6) {
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld CHARACTER_SPELLS_EARTH = new DialogueNodeOld("Earth Spells", "", true) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.EARTH, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+"<p>"
								+ "Spells in the school of Earth are split between creating and manipulating either pure force, or solid objects."
							+ "</p>"
							+ "<p>"
								+ "As with all schools of the arcane, the vast majority of Earth practitioners are demons, and can earn a considerable salary by using their telekenetic powers to aid with construction and heavy lifting."
								+ " Perhaps due to these lucrative applications, the school of Earth is the most widely-practised and popular of all the arcane schools."
							+ "</p>"
							+ "<p>"
								+ "A prerequisite to harnessing Earth spells is the ability to freely manipulate solid, non-organic matter."
								+ " Easily learned by anyone possessing a demon-strength aura, and outlined in the introduction of all Earth spell books, this ability allows the practitioner to change the colour and material of any object."
							+ "</p>"
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldEarth(School of Earth ability:)] "
								+(!Main.game.getPlayer().hasAnySpellInSchool(SpellSchool.EARTH)
									?"[style.colourDisabled(Dye clothing without a dye-brush.)]</br>(Requires knowing at least one Earth school spell to unlock.)"
									:"[style.colourGood(Dye clothing without a dye-brush.)]")
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
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);
				
			} else if(index==4) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);
				
			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);
				
			} else  if(index==6) {
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld CHARACTER_SPELLS_WATER = new DialogueNodeOld("Water Spells", "", true) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.WATER, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+"<p>"
								+ "Spells in the school of Water are focused on infusing liquids with arcane energy in order to manipulate their movement and temperature."
							+ "</p>"
							+ "<p>"
								+ "As with all schools of the arcane, the vast majority of Water practitioners are demons, and mainly use their spells to assist with the maintenance of waterways, and to repair and install plumbing."
								+ " Despite the lack of glamour, a student of Water can complete these tasks in a fraction of the time that it would take a regular person to do manually, allowing them to earn a considerable amount of money for their work."
							+ "</p>"
							+ "<p>"
								+ "Students of the school of Water are able to effortlessly manipulate all fluids, allowing them to enchant any fluid-related potions without needing to expend arcane essences."
							+ "</p>"
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldWater(School of Water ability:)] "
								+(!Main.game.getPlayer().hasAnySpellInSchool(SpellSchool.WATER)
									?"[style.colourDisabled(All fluid-related enchantments are free.)]</br>(Requires knowing at least one Water school spell to unlock.)"
									:"[style.colourGood(All fluid-related enchantments are free.)]")
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
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);
				
			} else if(index==4) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);
				
			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);
				
			} else if(index==6) {
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld CHARACTER_SPELLS_AIR = new DialogueNodeOld("Air Spells", "", true) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.AIR, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+"<p>"
								+ "The school of air focuses on spells that allow the caster to manipulate the temperature and movement of gases."
							+ "</p>"
							+ "<p>"
								+ "As with all schools of the arcane, the vast majority of Air practitioners are demons, but, with not many opportunities to use their spells in day-to-day business,"
									+ " their numbers are considerably lower than those of the schools of Earth and Water."
								+ " The only regular application of Air spells is to increase or decrease the temperature of rooms, allowing the occupants to escape the cold of winter or the heat of summer."
							+ "</p>"
							+ "<p>"
								+ "Students of the school of Air are able to effortlessly control the temperature of air around them, making sure that they're never too hot or too cold."
							+ "</p>"
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldAir(School of Air ability:)] "
								+(!Main.game.getPlayer().hasAnySpellInSchool(SpellSchool.AIR)
									?"[style.colourDisabled(Passive energy and arcane regeneration is doubled.)]</br>(Requires knowing at least one Air school spell to unlock.)"
									:"[style.colourGood(Passive energy and arcane regeneration is doubled.)]")
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
				return new Response("Air", "You are already viewing your Air spells!", null);
				
			} else if(index==4) {
				return new Response("Fire", "View your spells and upgrades in the school of Fire.", CHARACTER_SPELLS_FIRE);
				
			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);
				
			} else if(index==6) {
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld CHARACTER_SPELLS_FIRE = new DialogueNodeOld("Fire Spells", "", true) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.FIRE, Main.game.getPlayer())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+"<p>"
								+ "The school of fire, much as its name would suggest, is purely focused on summoning arcane fire."
							+ "</p>"
							+ "<p>"
								+ "As almost all practical uses of arcane fire are in combat situations, the school of Fire has a rather poor reputation in demon society, which regards it as distasteful and crude."
								+ " Due to this, the only demons who choose to study the school of Fire are either those interested in arcane research, or those who anticipate spending a lot of their time fighting."
							+ "</p>"
							+ "<p>"
								+ "Students of the school of Fire are able to summon a floating ball of arcane fire at will, which allows them to travel through dark areas without need of a torch."
							+ "</p>"
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldFire(School of Fire ability:)] "
								+(!Main.game.getPlayer().hasAnySpellInSchool(SpellSchool.FIRE)
									?"[style.colourDisabled(Immune to darkness status effect.)]</br>(Requires knowing at least one Fire school spell to unlock.)"
									:"[style.colourGood(Immune to darkness status effect.)]")
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
				return new Response("Air", "View your spells and upgrades in the school of Air.", CHARACTER_SPELLS_AIR);
				
			} else if(index==4) {
				return new Response("Fire", "You are already viewing your Fire spells!", null);
				
			} else if(index==5) {
				return new Response("Arcane", "View your spells and upgrades in the school of Arcane.", CHARACTER_SPELLS_ARCANE);
				
			} else if(index==6) {
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
//	private static boolean confirmReset = false;
	public static final DialogueNodeOld CHARACTER_FETISHES = new DialogueNodeOld("Desires & Fetishes", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			journalSB = new StringBuilder(
					"<div class='container-full-width' style='padding:8px;'>"
						+ "You can select your [style.colourLust(desire)] for each fetish [style.colourArcane(for free)],"
							+ " or choose to take the associated [style.colourFetish(fetish)] for [style.colourArcane("+Fetish.FETISH_ANAL_GIVING.getCost()+" Arcane Essences)].</br>"
						+ "Choosing a desire will affect bonus lust gains in sex, while taking a fetish will permanently lock your desire to 'love', and also give you special bonuses."
						+ " Fetishes can only be removed through enchanted potions.</br>"
						+ "Your currently selected desire has a "+Colour.FETISH.getName()+" border, but your true desire (indicated by the coloured desire icon) may be modified by enchanted clothes or other items.</br>"
						+ "You earn experience for each fetish through performing related actions in sex."
						+ " Experience is earned regardless of whether or not you have the associated fetish."
						+ " Higher level fetishes will cause both you and your partner to gain more arousal from related sex actions, as well as increase the fetish's bonuses.</br>"
						+ "Finally, derived fetishes cannot be directly unlocked, but are instead automatically applied when you meet their requirements."
					+ "</div>");
			
			// Normal fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold;'><h6>Fetishes</h6></div>");
			journalSB.append(getFetishEntry(Fetish.FETISH_DOMINANT, Fetish.FETISH_SUBMISSIVE));
			journalSB.append(getFetishEntry(Fetish.FETISH_VAGINAL_GIVING, Fetish.FETISH_VAGINAL_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_ANAL_RECEIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_BREASTS_OTHERS, Fetish.FETISH_BREASTS_SELF));
			journalSB.append(getFetishEntry(Fetish.FETISH_LACTATION_OTHERS, Fetish.FETISH_LACTATION_SELF));
			journalSB.append(getFetishEntry(Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER));
			journalSB.append(getFetishEntry(Fetish.FETISH_CUM_STUD, Fetish.FETISH_CUM_ADDICT));
			journalSB.append(getFetishEntry(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_PURE_VIRGIN));
			journalSB.append(getFetishEntry(Fetish.FETISH_IMPREGNATION, Fetish.FETISH_PREGNANCY));
			journalSB.append(getFetishEntry(Fetish.FETISH_SEEDER, Fetish.FETISH_BROODMOTHER));
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
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
										+ "<div style='width:40%;height:40%;position:absolute;top:0;right:0;'>"+level.getSVGImageOverlay()+"</div>"
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
		return "<div class='square-button"+(desire!=FetishDesire.FOUR_LOVE && Main.game.getPlayer().hasFetish(fetish)?" disabled":"")+"' id='"+fetish+"_"+desire+"'"
					+ " style='"+(Main.game.getPlayer().getBaseFetishDesire(fetish)==desire?"border:2px solid "+Colour.FETISH.getShades()[1]+";":"")+"width:10%; margin:0 5%; float:left;'>"
				+ "<div class='square-button-content'>"+(Main.game.getPlayer().getFetishDesire(fetish)==desire?desire.getSVGImage():desire.getSVGImageDesaturated())+"</div>"
				+ (Main.game.getPlayer().hasFetish(fetish) && Main.game.getPlayer().getFetishDesire(fetish)!=desire
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8; border-radius:5px;'></div>"
					:Main.game.getPlayer().getFetishDesire(fetish)!=desire
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; border-radius:5px;'></div>"
						:"")
			+ "</div>";
	}
}
