package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.Litter;
import com.lilithsthrone.game.character.PregnancyPossibility;
import com.lilithsthrone.game.character.Quest;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.QuestType;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkInterface;
import com.lilithsthrone.game.character.effects.PerkTree;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.ClothingRarityComparator;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ItemRarityComparator;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.WeaponRarityComparator;

/**
 * @since 0.1.0
 * @version 0.1.88
 * @author Innoxia
 */
public class PhoneDialogue {

	private static StringBuilder journalSB;
	public static final DialogueNodeOld MENU = new DialogueNodeOld("Phone home screen", "Phone", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>You pull out your phone and tap in the unlock code.</p>"
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
							?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Planner</span>"
							:"Planner",
						"Open your planner to view your current quests.", PLANNER_MAIN){
					@Override
					public void effects() {
						Main.game.getPlayer().setMainQuestUpdated(false);
					}
				};
				
			} else if (index == 2) {
				return new Response("Selfie", "Take a selfie to get a good view of yourself.", CHARACTER_APPEARANCE);
				
			} else if (index == 3) {
				return new Response("Stats", "Take a detailed look at your stats.", CHARACTER_STATS);
				
				
			} else if (index == 4) {
				if(Main.game.getPlayer().getCharactersEncountered().isEmpty()) {
					return new Response("Contacts", "You haven't met anyone yet!", null);
				} else {
					return new Response("Contacts", "Even though you can't call anyone, on account of there being no phones in this world, you've still kept a record of all the people you've come into contact with.", CONTACTS){
						@Override
						public void effects() {
							resetContentForContacts();
						}
					};
				}
				
			} else if (index == 5) {
				return new Response(
						(Main.getProperties().isNewWeaponDiscovered() || Main.getProperties().isNewClothingDiscovered() || Main.getProperties().isNewItemDiscovered() || Main.getProperties().isNewRaceDiscovered())
							? "<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Encyclopedia</span>"
							:"Encyclopedia",
						"Have a look at all the different items and races you've discovered so far.", ENCYCLOPEDIA){
					@Override
					public void effects() {
						resetContentForRaces();
					}
				};
				
			} else if (index == 6) {
				return new Response(
						((Main.game.getPlayer().getLevelUpPoints() > 0 
								&& (Main.game.getPlayer().getBaseAttributeValue(Attribute.STRENGTH) + Main.game.getPlayer().getBaseAttributeValue(Attribute.INTELLIGENCE) + Main.game.getPlayer().getBaseAttributeValue(Attribute.FITNESS))<300)
								|| Main.game.getPlayer().getPerkPoints() > 0)
							? "<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Character</span>"
							:"Character",
						"View your character page.", CHARACTER_LEVEL_UP){
					@Override
					public void effects() {
						strengthPoints = 0;
						intelligencePoints = 0;
						fitnessPoints = 0;
						spendingPoints = Main.game.getPlayer().getPerkPoints();
						levelUpPerks.clear();
					}
				};
				
			} else if (index == 7) {
				return new Response("Fetishes", "View your fetishes page.", CHARACTER_FETISHES){
					@Override
					public void effects() {
						confirmReset = false;
						levelUpFetishes.clear();
					}
				};
				
			} else if (index == 8) {
				if(Main.game.getPlayer().getRace()==Race.DEMON) {
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
			journalSB.append("<details open>");
			journalSB.append("<summary class='quest-title' style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";'>" + QuestLine.MAIN.getName() + "</summary>");

			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.MAIN)) {
				journalSB.append(
						"<div class='quest-box'>"
							+ Quest.getLevelAndExperienceHTML(Main.game.getPlayer().getQuest(QuestLine.MAIN), true)
							+ "<h6 style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";text-align:center;'>"
								+ Main.game.getPlayer().getQuest(QuestLine.MAIN).getName()
							+ "</h6>"
							+ "<p style='text-align:center;'>"
								+ Main.game.getPlayer().getQuest(QuestLine.MAIN).getDescription()
							+ "</p>"
						+ "</div>");
			} else {
				journalSB.append(
						"<div class='quest-box'>"
							+ "<h6 style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";text-align:center;'>"
								+ QuestLine.MAIN.getName()
							+ "</h6>"
							+ "<p style='text-align:center;'>"
								+ QuestLine.MAIN.getCompletedDescription()
							+ "</p>"
						+ "</div>");
			}
			
			for (int i = Main.game.getPlayer().getQuest(QuestLine.MAIN).getSortingOrder() - 1; i >= 0; i--) {
				journalSB.append(
						"<div class='quest-box'>"
							+ Quest.getLevelAndExperienceHTML(QuestLine.MAIN.getQuestArray()[i], false)
							+ "<h6 style='color:" + QuestType.MAIN.getColour().getShades()[1]+ ";text-align:center;'>"
								+ "<b>Completed - " + QuestLine.MAIN.getQuestArray()[i].getName() + "</b>"
							+ "</h6>"
							+ "<p style='color:" + Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
								+ QuestLine.MAIN.getQuestArray()[i].getCompletedDescription()
							+ "</p>"
						+ "</div>");
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
					
					journalSB.append("<details open>");
						if (Main.game.getPlayer().isQuestCompleted(questLine)) {
							journalSB.append(
									"<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
										+ "Completed - " + questLine.getName()
									+ "</summary>"
									+"<div class='quest-box'>"
										+ "<h6 style='color:" + questLine.getType().getColour().getShades()[1] + "; text-align:center;'>"
											+ "<b>Side Quest Completed</b>"
										+ "</h6>"
										+ "<p style='color:"+ Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
											+ questLine.getCompletedDescription()
										+ "</p>"
									+ "</div>");
							
							for (int i = questLine.getQuestArray().length; i > 0; i--) {
								journalSB.append(
											"<div class='quest-box'>"
												+ Quest.getLevelAndExperienceHTML(questLine.getQuestArray()[i-1], false)
												+ "<h6 style='color:" + questLine.getType().getColour().getShades()[1] + ";text-align:center;'>"
														+ "<b>Completed - "+ questLine.getQuestArray()[i-1].getName() + "</b>"
												+ "</h6>"
												+ "<p style='color:" + Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
													+ questLine.getQuestArray()[i-1].getCompletedDescription()
												+ "</p>" 
											+ "</div>");
							}
		
						} else {
							journalSB.append(
									"<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>"
									+"<div class='quest-box'>"
										+ Quest.getLevelAndExperienceHTML(Main.game.getPlayer().getQuest(questLine), true)
										+ "<h6 style='color:" + questLine.getType().getColour().toWebHexString()+ "; text-align:center;'>"
											+ "<b>" + Main.game.getPlayer().getQuest(questLine).getName() + "</b>"
										+ "</h6>"
										+ "<p style='text-align:center;'>"
											+ Main.game.getPlayer().getQuest(questLine).getDescription()
										+ "</p>"
									+ "</div>");
							
							for (int i = Main.game.getPlayer().getQuest(questLine).getSortingOrder() - 1; i >= 0; i--) {
								journalSB.append(
											"<div class='quest-box'>"
												+ Quest.getLevelAndExperienceHTML(questLine.getQuestArray()[i], false)
												+ "<h6 style='color:" + questLine.getType().getColour().getShades()[1] + ";text-align:center;'>"
														+ "<b>Completed - "+ questLine.getQuestArray()[i].getName() + "</b>"
												+ "</h6>"
												+ "<p style='color:" + Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
													+ questLine.getQuestArray()[i].getCompletedDescription()
												+ "</p>" 
											+ "</div>");
							}
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
					
					journalSB.append("<details open>");
						if (Main.game.getPlayer().isQuestCompleted(questLine)) {
							journalSB.append(
									"<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
										+ "Completed - " + questLine.getName()
									+ "</summary>"
									+"<div class='quest-box'>"
										+ "<h6 style='color:" + questLine.getType().getColour().getShades()[1] + "; text-align:center;'>"
											+ "<b>Side Quest Completed</b>"
										+ "</h6>"
										+ "<p style='color:"+ Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
											+ questLine.getCompletedDescription()
										+ "</p>"
									+ "</div>");
		
						} else {
							journalSB.append(
									"<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>"
									+"<div class='quest-box'>"
										+ Quest.getLevelAndExperienceHTML(Main.game.getPlayer().getQuest(questLine), true)
										+ "<h6 style='color:" + questLine.getType().getColour().toWebHexString()+ "; text-align:center;'>"
											+ "<b>" + Main.game.getPlayer().getQuest(questLine).getName() + "</b>"
										+ "</h6>"
										+ "<p style='text-align:center;'>"
											+ Main.game.getPlayer().getQuest(questLine).getDescription()
										+ "</p>"
									+ "</div>");
		
						}

						for (int i = Main.game.getPlayer().getQuest(questLine).getSortingOrder() - 1; i >= 0; i--) {
							journalSB.append(
										"<div class='quest-box'>"
											+ Quest.getLevelAndExperienceHTML(questLine.getQuestArray()[i], false)
											+ "<h6 style='color:" + questLine.getType().getColour().getShades()[1] + ";text-align:center;'>"
													+ "<b>Completed - "+ questLine.getQuestArray()[i].getName() + "</b>"
											+ "</h6>"
											+ "<p style='color:" + Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
												+ questLine.getQuestArray()[i].getCompletedDescription()
											+ "</p>" 
										+ "</div>");
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

	public static final DialogueNodeOld CHARACTER_APPEARANCE = new DialogueNodeOld("Selfie picture", "Take a selfie", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			/*
			 * sexCountReceivingAnal, sexCountReceivingVaginal,
			 * sexCountGivingTitjob, sexCountGivingBlowjob,
			 * sexCountGivingCunnilingus; private String gaveFirstBlowjobTo,
			 * gaveFirstCunnilingusTo, gaveVaginalVirginityTo,
			 * gaveAnalVirginityTo;
			 */
			return Main.game.getPlayer().getBodyDescription();
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

	public static final DialogueNodeOld CHARACTER_STATS = new DialogueNodeOld("Stats", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
				"<div class='container-full-width'>"
					+ "<h4 style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>Core Attributes</h4>"
					+ attributeHeader()
					+ attributeValue(Main.game.getPlayer(), Attribute.STRENGTH, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.INTELLIGENCE, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.FITNESS, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.CORRUPTION, false)

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h4 style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Misc. Attributes</h4>"
					+ attributeHeader()
					+ attributeValue(Main.game.getPlayer(), Attribute.FERTILITY, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.VIRILITY, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.SPELL_COST_MODIFIER, true)

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h4 style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Combat Attributes</h4>"
					+ attributeHeader()
					+ attributeValue(Main.game.getPlayer(), Attribute.CRITICAL_CHANCE, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.CRITICAL_DAMAGE, false)

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Attack values</h6>"
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_PURE, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_ATTACK, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_SPELLS, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_MANA, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_STAMINA, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_PHYSICAL, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_FIRE, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_ICE, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_POISON, false)

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Resistance values</h6>"
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_PURE, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_ATTACK, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_SPELLS, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_MANA, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_STAMINA, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_PHYSICAL, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_FIRE, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_ICE, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_POISON, false)
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Racial values</h6>");
			
			for(Race race : Race.values()) {
				UtilText.nodeContentSB.append(attributeValue(Main.game.getPlayer(), race.getDamageMultiplier(), true));
				UtilText.nodeContentSB.append(attributeValue(Main.game.getPlayer(), race.getResistanceMultiplier(), false));
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
	
	public static final DialogueNodeOld CHARACTER_STATS_BODY = new DialogueNodeOld("Body stats", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
				"<div class='container-full-width'>"
						+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Orifice Mechanics</h6>"
						
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
				+ "</div>"
						
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

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.TRANSFORMATION_GREATER.toWebHexString()+"; text-align:center;'>Head & Face Attributes</h6>"
					+ statHeader()
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Hair Length (inches)",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getHairRawLengthValue()),
							Main.game.getPlayer().getHairLength().getColour(), Util.capitaliseSentence(Main.game.getPlayer().getHairLength().getDescriptor()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Tongue length (inches)",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getTongueLengthValue()),
							Colour.TRANSFORMATION_GENERIC, Util.capitaliseSentence(Main.game.getPlayer().getTongueLength().getDescriptor()),
							false)
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Breast Attributes</h6>"
					+ statHeader()
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Cup Size",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getBreastRawSizeValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getBreastSize().getCupSizeName()),
							true)
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Milk production (mL)",
							Colour.TEXT, String.valueOf(Main.game.getPlayer().getBreastRawLactationValue()),
							Colour.GENERIC_SEX, Util.capitaliseSentence(Main.game.getPlayer().getBreastLactation().getDescriptor()),
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
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Penis Size",
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
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Vagina Attributes</h6>"
					+ statHeader()
					+ statRow(Colour.TRANSFORMATION_GENERIC, "Clitoris Size",
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
	
	public static final DialogueNodeOld CHARACTER_STATS_SEX = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return
			"<div class='subTitle'>" + "Sex Stats" + "</div>" + "<div class='extraAttribute-third'>" + "Type" + "</div>" + "<div class='extraAttribute-sixth'>" + "Given" + "</div>" + "<div class='extraAttribute-sixth'>" + "Cum Given" + "</div>"
					+ "<div class='extraAttribute-sixth'>" + "Taken" + "</div>" + "<div class='extraAttribute-sixth'>" + "Cum Taken" + "</div>"

					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Fingering",
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.FINGER_PLAYER, OrificeType.VAGINA_PARTNER)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Anal Fingering",
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.FINGER_PLAYER, OrificeType.ANUS_PARTNER)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.ANUS_PLAYER)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Blowjobs",
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER)),
							Main.game.getPlayer().getCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER)),
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER)),
							Main.game.getPlayer().getCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER)))
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Cunnilingus",
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.TONGUE_PLAYER, OrificeType.VAGINA_PARTNER)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.TONGUE_PARTNER, OrificeType.VAGINA_PLAYER)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Anilingus",
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.TONGUE_PLAYER, OrificeType.ANUS_PARTNER)),
							-1,
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.TONGUE_PARTNER, OrificeType.ANUS_PLAYER)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Vaginal sex",
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER)),
							Main.game.getPlayer().getCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER)),
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER)),
							Main.game.getPlayer().getCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Anal sex",
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER)),
							Main.game.getPlayer().getCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER)),
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER)),
							Main.game.getPlayer().getCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Nipple penetration",
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.NIPPLE_PARTNER)),
							Main.game.getPlayer().getCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.NIPPLE_PARTNER)),
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.NIPPLE_PLAYER)),
							Main.game.getPlayer().getCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.NIPPLE_PLAYER)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Urethra penetration",
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.URETHRA_PARTNER)),
							Main.game.getPlayer().getCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.URETHRA_PARTNER)),
							Main.game.getPlayer().getSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.URETHRA_PLAYER)),
							Main.game.getPlayer().getCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.URETHRA_PLAYER)));
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
	
	public static final DialogueNodeOld CHARACTER_STATS_PREGNANCY = new DialogueNodeOld("Pregnancy stats", "", true) {
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
					
					+"<div class='container-full-width'>"
						+ "<p>"
							+ "Due to the incredible speed of both pregnancies and the development of children, the attachment between a child and his or her parents is far weaker in this world than what you're used to."
							+ " After reaching full maturity within a matter of hours, the vast majority of children will immediately leave their parents in order to strike out for themselves in the world."
							+ "</br></br>"
							+ "Despite this, however, a parent will always share a special maternal or paternal bond with their children, and, whether due to the arcane or natural intuition,"
								+ " a parent and child will always recognise each other at first sight."
						+ "</p>"
					+ "</div>"
					
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
									+ "<b style='color:"+npc.getRace().getColour().toWebHexString()+";'>"+npc.getRace().getOffspringFemaleNameSingular()+"</b>"
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
									+ "<b style='color:"+npc.getRace().getColour().toWebHexString()+";'>"+npc.getRace().getOffspringMaleNameSingular()+"</b>"
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
	

	private static String sexStatRow(Colour colour, String name, int given, int loadsGiven, int received, int loadsReceived) {
		return "<div class='extraAttribute-third'>" + "<span style='color:" + colour.toWebHexString() + ";'>" + name + "</span>" + "</div>" + "<div class='extraAttribute-sixth'>" + given + "</div>" + "<div class='extraAttribute-sixth'>"
				+ (loadsGiven < 0 ? "<span class='option-disabled'>-</span>" : loadsGiven) + "</div>" + "<div class='extraAttribute-sixth'>" + received + "</div>" + "<div class='extraAttribute-sixth'>"
				+ (loadsReceived < 0 ? "<span class='option-disabled'>-</span>" : loadsReceived) + "</div>";
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
								+ (pp.getFather().getGender().isFeminine()?Util.capitaliseSentence(pp.getFather().getRace().getSingularFemaleName()):Util.capitaliseSentence(pp.getFather().getRace().getSingularMaleName()))
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
							+ (pp.getMother().getGender().isFeminine()?Util.capitaliseSentence(pp.getMother().getRace().getSingularFemaleName()):Util.capitaliseSentence(pp.getMother().getRace().getSingularMaleName()))
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

	private static String attributeHeader() {
		return "<div class='container-full-width' style='margin-bottom:0;'>"
					+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Attribute"
					+ "</div>"
					+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Base Value"
					+ "</div>"
					+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Modifiers"
					+ "</div>"
					+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
						+ "Final Value"
					+"</div>"
				+ "</div>";
	}
	
	private static String attributeValue(GameCharacter owner, Attribute att, boolean light) {
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(light?"background:#292929;'":"'")+">"
				+ "<div style='color:" + att.getColour().toWebHexString() + "; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ Util.capitaliseSentence(att.getName())
				+ "</div>"
				+ (owner.getBaseAttributeValue(att) > 0 
						? "<div style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";"
						: (owner.getBaseAttributeValue(att) < 0
								? "<div style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";"
								: "<div style='color:" + Colour.TEXT_GREY.toWebHexString() + ";"))
					+" width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
					// To get rid of e.g. 2.3999999999999999999999:
					+ Math.round(owner.getBaseAttributeValue(att)*100)/100f
				+ "</div>"
				+ (owner.getBonusAttributeValue(att) > 0
						? "<div style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";"
						: (owner.getBonusAttributeValue(att) < 0
								? "<div style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";"
								: "<div style='color:" + Colour.TEXT_GREY.toWebHexString() + ";"))
					+" width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
					// To get rid of e.g. 2.3999999999999999999999:
					+ Math.round(owner.getBonusAttributeValue(att)*100)/100f
				+ "</div>"
				+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
					// To get rid of e.g. 2.3999999999999999999999:
					+ Math.round(owner.getAttributeValue(att)*100)/100f
				+"</div>"
				+ "</div>";
	}

	private static String content, title;

	public static void resetContentForContacts() {
		CharactersPresentDialogue.characterViewed = Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(0));
		title = "Contacts";
		StringBuilder contentSB = new StringBuilder("<p>You have encountered the following characters in your travels:</p>");
		for (int i = 0; i < Main.game.getPlayer().getCharactersEncountered().size(); i++) {
			if(Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(i))!=null) {
				contentSB.append("<p>" + Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(i)).getName() + "</p>");
			}
		}
		content = contentSB.toString();
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
							CharactersPresentDialogue.characterViewed = npc;
							
							title = Util.capitaliseSentence(npc.getName());
							content = NPC.getCharacterInformationScreen((NPC) npc);
							
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
			return title;
		}

		@Override
		public String getContent() {
			return content;
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
							CharactersPresentDialogue.characterViewed = npc;
							
							title = Util.capitaliseSentence(npc.getName());
							content = NPC.getCharacterInformationScreen((NPC) npc);
							
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
				return new Response((Main.getProperties().isNewRaceDiscovered())?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Races</span>":"Races",
						"Have a look at all the different races that you've encountered in your travels.", RACES){
					@Override
					public void effects() {
						Main.getProperties().setNewRaceDiscovered(false);
					}
				};
			
			} else if (index == 2) {
				return new Response((Main.getProperties().isNewWeaponDiscovered())?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Weapons</span>":"Weapons",
						"Have a look at all the different weapons that you've encountered in your travels.", WEAPON_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setNewWeaponDiscovered(false);
					}
				};
			
			} else if (index == 3) {
				return new Response((Main.getProperties().isNewClothingDiscovered())?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Clothing</span>":"Clothing",
						"Have a look at all the different clothing that you've encountered in your travels.", CLOTHING_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setNewClothingDiscovered(false);
					}
				};
			
			} else if (index == 4) {
				return new Response((Main.getProperties().isNewItemDiscovered())?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Items</span>":"Items",
						"Have a look at all the different items that you've encountered in your travels.", ITEM_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setNewItemDiscovered(false);
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
						
						title = Util.capitaliseSentence(race.getName()) + " ("
								+ Util.capitaliseSentence(race.getGenus().getName()) + ")";
						content = "<div class='encyclopedia-container'>"
								+ "<p style='width:100%; text-align:center;'><b style='color:"+race.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(race.getName())+"</b></br>"
										+ "Average stats</p>"
								+ "<table align='center'>"
									+ "<tr>"
										+ "<th></th>"
										+ "<th><b style='color:"+Femininity.valueOf(racialBody.getFemaleFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(race.getSingularFemaleName())+"</b></th>"
										+ "<th><b style='color:"+Femininity.valueOf(racialBody.getMaleFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(race.getSingularMaleName())+"</b></th>"
									+ "</tr>"
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
									+ "</tr>"
								+ "</table>"
								+ "</div>"
								+ "<p>"
									+ "Male " + race.getName() + "s are called <b style='color:" + Colour.MASCULINE.toWebHexString() + ";'>"
									+ race.getPluralMaleName() + "</b>."
								+ "</p>"
								+ "<p>"
									+ "Female " + race.getName()+ "s are called <b style='color:" + Colour.FEMININE.toWebHexString() + ";'>"
									+ race.getPluralFemaleName() + "</b>."
								+ "</p>"
								+ race.getBasicDescription()
								+(Main.getProperties().isAdvancedRaceKnowledgeDiscovered(race)
										?race.getAdvancedDescription()
										:"<p style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"
											+ "Further information can be discovered in books!"
										+ "</p>");
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

	public static int strengthPoints = 0, intelligencePoints = 0, fitnessPoints = 0, spendingPoints=0;
	public static List<PerkInterface> levelUpPerks = new ArrayList<>();
	public static List<Fetish> levelUpFetishes = new ArrayList<>();

	public static boolean isAttributePointsAvailableToSpend() {
		return (Main.game.getPlayer().getLevelUpPoints() - (strengthPoints + intelligencePoints + fitnessPoints)) > 0;
	}
	public static final DialogueNodeOld CHARACTER_LEVEL_UP = new DialogueNodeOld("", "Level up.", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			journalSB = new StringBuilder(
					"<div class='lvlup-container'>"

						+ "<div class='lvlup-title'>"
							+ "Core Attributes (<b " + (isAttributePointsAvailableToSpend() ? "style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'" : "") + ">"
							+ (Main.game.getPlayer().getLevelUpPoints() - (strengthPoints + intelligencePoints + fitnessPoints)) + "</b> point"
							+ ((Main.game.getPlayer().getLevelUpPoints() - (strengthPoints + intelligencePoints + fitnessPoints)) != 1 ? "s" : "") + " to spend)"
						+ "</div>"
	
						+ "<div class='lvlup-box'>"
	
							+ "<div class='lvlup-attribute'>"
		
								+ "<div class='lvlup-attribute-innerContainer'>"
									+ "<h6>" + Util.capitaliseSentence(Attribute.STRENGTH.getName()) + "</h6>"
									+ "<div style='width:180px; height:8px; background:#222222; float:left; border-radius: 2;'>"
										+ "<div style='width:" + Main.game.getPlayer().getBaseAttributeValue(Attribute.STRENGTH) * 1.8 + "px; height:8px; background:" + Colour.ATTRIBUTE_STRENGTH.toWebHexString() + "; float:left; border-radius: 2;'></div>"
										+ "<div style='width:" + strengthPoints * 1.8 + "px; height:8px; background:" + Colour.GENERIC_EXCELLENT.toWebHexString() + "; float:left; border-radius: 2;'></div>"
									+ "</div>"
									+ "<p style='padding:0;margin:0;line-height:16px;" + (strengthPoints > 0 ? "color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";" : "") + "'>"
										+ ((int) Math.ceil(Main.game.getPlayer().getBaseAttributeValue(Attribute.STRENGTH)) + strengthPoints)
									+ "</p>"
								+ "</div>"
			
								+ "<div class='lvlup-attribute-innerContainer'>"
									+ "<div class='lvlup-button" + (strengthPoints == 0 ? " disabled" : "") + "' id='strength-decrease' style='float:left;'><b>-</b></div>"
									+ "<span style='float:left; padding:0; margin:0; width:174px; text-align:center; height:24px;'>"
									+ "<b" + (strengthPoints > 0 ? " style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'" : "") + ">+" + strengthPoints + "</b>"
									+ "</span>"
									+ "<div class='lvlup-button" + (isAttributePointsAvailableToSpend() && (Main.game.getPlayer().getBaseAttributeValue(Attribute.STRENGTH) + strengthPoints < 100) ? "" : " disabled")
										+ "' id='strength-increase' style='float:left;'><b>+</b></div>"
								+ "</div>"
		
							+ "</div>"
		
							
							+ "<div class='lvlup-attribute'>"

								+ "<div class='lvlup-attribute-innerContainer'>"
									+ "<h6>" + Util.capitaliseSentence(Attribute.INTELLIGENCE.getName()) + "</h6>"
									+ "<div style='width:180px; height:8px; background:#222222; float:left; border-radius: 2;'>"
										+ "<div style='width:" + Main.game.getPlayer().getBaseAttributeValue(Attribute.INTELLIGENCE) * 1.8 + "px; height:8px; background:" + Colour.ATTRIBUTE_INTELLIGENCE.toWebHexString() + "; float:left; border-radius: 2;'></div>"
										+ "<div style='width:" + intelligencePoints * 1.8 + "px; height:8px; background:" + Colour.GENERIC_EXCELLENT.toWebHexString() + "; float:left; border-radius: 2;'></div>"
									+ "</div>"
									+ "<p style='padding:0;margin:0;line-height:16px;" + (intelligencePoints > 0 ? "color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";" : "") + "'>"
										+ ((int) Math.ceil(Main.game.getPlayer().getBaseAttributeValue(Attribute.INTELLIGENCE)) + intelligencePoints)
									+ "</p>"
								+ "</div>"
			
								+ "<div class='lvlup-attribute-innerContainer'>"
									+ "<div class='lvlup-button" + (intelligencePoints == 0 ? " disabled" : "") + "' id='intelligence-decrease' style='float:left;'><b>-</b></div>"
									+ "<span style='float:left; padding:0; margin:0; width:174px; text-align:center; height:24px;'>"
									+ "<b" + (intelligencePoints > 0 ? " style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'" : "") + ">+" + intelligencePoints + "</b>"
									+ "</span>"
									+ "<div class='lvlup-button" + (isAttributePointsAvailableToSpend() && (Main.game.getPlayer().getBaseAttributeValue(Attribute.INTELLIGENCE) + intelligencePoints < 100) ? "" : " disabled")
										+ "' id='intelligence-increase' style='float:left;'><b>+</b></div>"
								+ "</div>"
		
							+ "</div>"
							
							
							+ "<div class='lvlup-attribute'>"

								+ "<div class='lvlup-attribute-innerContainer'>"
									+ "<h6>" + Util.capitaliseSentence(Attribute.FITNESS.getName()) + "</h6>"
									+ "<div style='width:180px; height:8px; background:#222222; float:left; border-radius: 2;'>"
										+ "<div style='width:" + Main.game.getPlayer().getBaseAttributeValue(Attribute.FITNESS) * 1.8 + "px; height:8px; background:" + Colour.ATTRIBUTE_FITNESS.toWebHexString() + "; float:left; border-radius: 2;'></div>"
										+ "<div style='width:" + fitnessPoints * 1.8 + "px; height:8px; background:" + Colour.GENERIC_EXCELLENT.toWebHexString() + "; float:left; border-radius: 2;'></div>"
									+ "</div>"
									+ "<p style='padding:0;margin:0;line-height:16px;" + (fitnessPoints > 0 ? "color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";" : "") + "'>"
										+ ((int) Math.ceil(Main.game.getPlayer().getBaseAttributeValue(Attribute.FITNESS)) + fitnessPoints)
									+ "</p>"
								+ "</div>"
			
								+ "<div class='lvlup-attribute-innerContainer'>"
									+ "<div class='lvlup-button" + (fitnessPoints == 0 ? " disabled" : "") + "' id='fitness-decrease' style='float:left;'><b>-</b></div>"
									+ "<span style='float:left; padding:0; margin:0; width:174px; text-align:center; height:24px;'>"
									+ "<b" + (fitnessPoints > 0 ? " style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'" : "") + ">+" + fitnessPoints + "</b>"
									+ "</span>"
									+ "<div class='lvlup-button" + (isAttributePointsAvailableToSpend() && (Main.game.getPlayer().getBaseAttributeValue(Attribute.FITNESS) + fitnessPoints < 100) ? "" : " disabled")
										+ "' id='fitness-increase' style='float:left;'><b>+</b></div>"
								+ "</div>"
		
							+ "</div>"
							
							
						+ "</div>"

					+ "</div>"

					+ "<div class='lvlup-container'>"

					+ "<div class='lvlup-title'>" + "Perks (" + (spendingPoints > 0 ? "<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>" : "<span>")
					+ (spendingPoints)
					+ "</span> point" + (spendingPoints != 1 ? "s" : "") + " to spend)" + "</div>"

					+ "<table class='perkTable' style='margin:0 auto 0 auto;'>");

			for (int i = 0; i < 7; i++) {
				journalSB.append("<tr class='perkRow'>");
				for (int j = 0; j < 17; j++) {

					if (j == 0) {
						if (i % 2 == 0)
							journalSB.append("<td class='perkCell' " + (Main.game.getPlayer().getLevel() >= (i == 0 ? 1 : (i / 2) * 5) ? "" : "style='color:#666;'") + ">"
							// +"<h6
							// style='text-align:center;margin:0;padding:0'>Lvl</h6>"
									+ "<h6 style='text-align:center;margin:0;padding:0'>" + (i == 0 ? "1" : (i / 2) * 5) + "</h6>" + "</td>");
						else
							journalSB.append("<td class='arrowCell'></td>");
					} else {
						if (i % 2 == 0) { // Perk icon
							if (PerkTree.getPerkGrid()[i / 2][j - 1] != null) {
								journalSB.append("<td id='perkUnlock" + PerkTree.getPerkGrid()[i / 2][j - 1] + "' class='perkCell" + (Main.game.getPlayer().hasPerk(PerkTree.getPerkGrid()[i / 2][j - 1])
										? " owned' style='border:4px solid " + PerkTree.getPerkGrid()[i / 2][j - 1].getPerkCategory().getColour().toWebHexString() + ";'>"
										: (PerkTree.getPerkGrid()[i / 2][j - 1].isAvailable(Main.game.getPlayer(), levelUpPerks)
												? " unlocked' style='border:4px solid " + (levelUpPerks.contains(PerkTree.getPerkGrid()[i / 2][j - 1]) ? Colour.GENERIC_EXCELLENT.toWebHexString() + ";"
														: PerkTree.getPerkGrid()[i / 2][j - 1].getPerkCategory().getColour().getShades()[0] + ";") + "'>"
												: " locked' style='border:4px solid " + Colour.TEXT_GREY.toWebHexString() + ";'>"))
										+ PerkTree.getPerkGrid()[i / 2][j - 1].getSVGString()
										+ (Main.game.getPlayer().hasPerk(PerkTree.getPerkGrid()[i / 2][j - 1]) || levelUpPerks.contains(PerkTree.getPerkGrid()[i / 2][j - 1]) // Overlay to create disabled effect:
												? ""
												: (PerkTree.getPerkGrid()[i / 2][j - 1].isAvailable(Main.game.getPlayer(), levelUpPerks)
														? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.5; border-radius:5px;'></div>"
														: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.7; border-radius:5px;'></div>"))
										+ "</td>");
							} else
								journalSB.append("<td class='perkCell'></td>");

						} else { // Arrow icon
							if (PerkTree.getArrowGrid()[i / 2][j - 1]) {
								journalSB.append("<td class='arrowCell'>" + SVGImages.SVG_IMAGE_PROVIDER.getPerkTreeArrow() + "</td>");
							} else {
								journalSB.append("<td class='arrowCell'></td>");
							}
						}
					}
				}
				journalSB.append("</tr>");
			}
			journalSB.append("</table></div>");
			
			
			return journalSB.toString();
		}
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU);
			
			} else if (index == 1) {
				if (strengthPoints == 0 && intelligencePoints == 0 && fitnessPoints == 0 && levelUpPerks.isEmpty())
					return new Response("Apply", "You haven't spent any points, so there isn't anything to apply.", null);
				else
					return new Response("Apply", "Apply the changes that you've chosen. <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Once applied, this cannot be undone!</b>", CHARACTER_LEVEL_UP){
						@Override
						public void effects() {
							if (strengthPoints != 0)
								Main.game.getPlayer().incrementAttribute(Attribute.STRENGTH, strengthPoints);
							if (intelligencePoints != 0)
								Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, intelligencePoints);
							if (fitnessPoints != 0)
								Main.game.getPlayer().incrementAttribute(Attribute.FITNESS, fitnessPoints);

							Main.game.getPlayer().setLevelUpPoints(Main.game.getPlayer().getLevelUpPoints() - (strengthPoints + intelligencePoints + fitnessPoints));

							strengthPoints = 0;
							intelligencePoints = 0;
							fitnessPoints = 0;

							// Add perks in level order:
							levelUpPerks.sort(Comparator.comparing(a -> a.getRequiredLevel().getLevel()));

							for (PerkInterface p : levelUpPerks) {
								Main.game.getPlayer().addPerk((Perk)p);
								p.applyPerkGained(Main.game.getPlayer());
								Main.game.getPlayer().setPerkPoints(Main.game.getPlayer().getPerkPoints()-1);
							}
							
							if(Main.game.getPlayer().getPerkPoints()<0)
								Main.game.getPlayer().setPerkPoints(0);
							
							spendingPoints = Main.game.getPlayer().getPerkPoints();

							levelUpPerks.clear();
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
	
	public static int getFetishCost() {
		int cost = 0;
		for(Fetish f : levelUpFetishes) {
			cost+=f.getCost();
		}
		return cost;
	}

	private static boolean confirmReset = false;
	public static final DialogueNodeOld CHARACTER_FETISHES = new DialogueNodeOld("Fetishes", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			journalSB = new StringBuilder(
					"<div class='container-full-width'>"
						+ "You can unlock fetishes by using <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>arcane essences</b> (gained from from orgasming in sex)."
						+ " Fetishes cost <b>five</b> arcane essences each."
						+ " Derived fetishes cannot be directly unlocked, but are instead automatically unlocked when you meet their requirements."
					+ "</div>"
						
					+"<div class='extraAttribute-third'>"
						+ "Arcane essences owned</br>"
						+"<div class='item-inline"
						+ (TFEssence.ARCANE.getRarity() == Rarity.COMMON ? " common" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.RARE ? " rare" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.EPIC ? " epic" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
						+ TFEssence.ARCANE.getSVGString()
						+ "</div>"
						+ " [style.boldArcane("+Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) + ")]"
					+ "</div>"
					
					+"<div class='extraAttribute-third'>"
						+ "Arcane essences spent</br>"
						+"<div class='item-inline"
						+ (TFEssence.ARCANE.getRarity() == Rarity.COMMON ? " common" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.RARE ? " rare" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.EPIC ? " epic" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
						+ TFEssence.ARCANE.getSVGString()
						+ "</div>"
						+ " "+getFetishCost()
					+ "</div>"
					
					+"<div class='extraAttribute-third'>"
						+ "Arcane essences available</br>"
						+"<div class='item-inline"
						+ (TFEssence.ARCANE.getRarity() == Rarity.COMMON ? " common" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.RARE ? " rare" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.EPIC ? " epic" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
						+ (TFEssence.ARCANE.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
						+ TFEssence.ARCANE.getSVGString()
						+ "</div>"
						+ " "+(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) - getFetishCost())
					+ "</div>"
					

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					
					+ "<div class='fetish-container'>"
					+ "<p style='width:100%;'>Fetishes</p>");
			
			// Normal fetishes:
			
			for(Fetish fetish : Fetish.values()) {
				if(fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					journalSB.append(
							"<div id='fetishUnlock" + fetish + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:4px solid " + PerkCategory.FETISH.getColour().getShades()[1] + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:4px solid " + (levelUpFetishes.contains(fetish)
											? Colour.GENERIC_EXCELLENT.toWebHexString() + ";"
											: Colour.TEXT_GREY.toWebHexString() + ";") + "'>"
									: " locked' style='border:4px solid " + Colour.TEXT_GREY.toWebHexString() + ";'>"))
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString()+"</div>"
							+ (Main.game.getPlayer().hasFetish(fetish) || levelUpFetishes.contains(fetish) // Overlay to create disabled effect:
									? ""
									: (fetish.isAvailable(Main.game.getPlayer())
											? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.5; border-radius:5px;'></div>"
											: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.7; border-radius:5px;'></div>"))
							+ "</div>");
				}
			}
			
			// Derived fetishes:
			
			journalSB.append("</div>"

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					
					+ "<div class='fetish-container'>"
					+ "<p style='width:100%;'>Derived Fetishes</p>");
			
			for(Fetish fetish : Fetish.values()) {
				if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					journalSB.append(
							"<div id='fetishUnlock" + fetish + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:4px solid " + PerkCategory.FETISH.getColour().getShades()[1] + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:4px solid " + (levelUpFetishes.contains(fetish)
											? Colour.GENERIC_EXCELLENT.toWebHexString() + ";"
											: Colour.TEXT_GREY.toWebHexString() + ";") + "'>"
									: " locked' style='border:4px solid " + Colour.TEXT_GREY.toWebHexString() + ";'>"))
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString()+"</div>"
							+ (Main.game.getPlayer().hasFetish(fetish) || levelUpFetishes.contains(fetish) // Overlay to create disabled effect:
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
				return new Response("Back", "Return to your phone's main menu.", MENU) {
					@Override
					public void effects() {
						levelUpFetishes.clear();
					}
				};
			
			} else if (index == 1) {
				if (levelUpFetishes.isEmpty()) {
					return new Response("Apply", "You haven't spent any points, so there isn't anything to apply.", null);
				} else {
					return new Response("Apply", "Apply the changes that you've chosen. <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Once applied, this cannot be undone!</b>", CHARACTER_FETISHES){
						@Override
						public void effects() {
							for (Fetish f : levelUpFetishes) {
								Main.game.getPlayer().addFetish(f);
								f.applyPerkGained(Main.game.getPlayer());
							}
							
							Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -getFetishCost());

							levelUpFetishes.clear();

							PhoneDialogue.confirmReset = false;
						}
					};
				}
			
			}
			else if (index == 2) {
				return new Response("Orientation", "Cycle your sexual orientation. (Hover over the status effect on the left of the screen to see what each orientation means.)", CHARACTER_FETISHES){
					@Override
					public void effects() {
						if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC) {
							Main.game.getPlayer().setSexualOrientation(SexualOrientation.ANDROPHILIC);
							
						} else if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
							Main.game.getPlayer().setSexualOrientation(SexualOrientation.AMBIPHILIC);
							
						} else {
							Main.game.getPlayer().setSexualOrientation(SexualOrientation.GYNEPHILIC);
							
						}

						PhoneDialogue.confirmReset = false;
					}
				};
				
			}
			else if (index == 5) {
				if (!confirmReset) {
					return new Response("Reset", "This will remove all of your fetishes, and you will be refunded half of their <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'> arcane essence</b> cost!", CHARACTER_FETISHES) {
						@Override
						public void effects() {
							PhoneDialogue.confirmReset = true;
						}
					};
					
				} else {
					return new Response("<b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Confirm</b>",
							"This will remove all of your fetishes, and you will be refunded half of their <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'> arcane essence</b> cost!", CHARACTER_FETISHES) {
						@Override
						public void effects() {
							Iterator<Fetish> it = Main.game.getPlayer().getFetishes().iterator();
							int refund = 0;
							while(it.hasNext()) {
								Fetish f = it.next();
								if(f.getFetishesForAutomaticUnlock().isEmpty()) {
									refund += f.getCost();
									Main.game.getPlayer().removeFetish(f);
								}
							}
							
							it = Main.game.getPlayer().getFetishes().iterator();
							while(it.hasNext()) {
								Fetish f = it.next();
								refund += f.getCost();
								Main.game.getPlayer().removeFetish(f);
							}
							
							Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, refund/2);
							levelUpFetishes.clear();
							PhoneDialogue.confirmReset = false;
						}
					};
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
}
