package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
import com.lilithsthrone.utils.ClothingRarityComparator;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ItemRarityComparator;
import com.lilithsthrone.utils.TreeNode;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.WeaponRarityComparator;

/**
 * @since 0.1.0
 * @version 0.1.99
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
						Main.game.getPlayer().getPerkPoints() > 0
							? "<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Character</span>"
							:"Character",
						"View your character page.", CHARACTER_LEVEL_UP);
				
			} else if (index == 7) {
				return new Response("Fetishes", "View your fetishes page.", CHARACTER_FETISHES);
				
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
					+ attributeValue(Main.game.getPlayer(), Attribute.MAJOR_PHYSIQUE, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.MAJOR_ARCANE, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.MAJOR_CORRUPTION, true)

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
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_PHYSICAL, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_FIRE, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_ICE, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_POISON, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_LUST, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.DAMAGE_SPELLS, false)

					+ "<span style='height:16px;width:100%;float:left;'></span>"
					+ "<h6 style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'>Resistance values</h6>"
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_PHYSICAL, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_FIRE, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_ICE, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_POISON, false)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_LUST, true)
					+ attributeValue(Main.game.getPlayer(), Attribute.RESISTANCE_SPELLS, false)
					
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

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Urethra penetration",
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.URETHRA)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.URETHRA)),
							Main.game.getPlayer().getSexCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.URETHRA)),
							Main.game.getPlayer().getCumCount(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.URETHRA)));
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
							content = ((NPC) npc).getCharacterInformationScreen();
							
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
							content = ((NPC) npc).getCharacterInformationScreen();
							
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
						
						title = Util.capitaliseSentence(race.getName()) + " ("
								+ Util.capitaliseSentence(race.getGenus().getName()) + ")";
						raceSB.setLength(0);
						raceSB.append("<div class='encyclopedia-container'>"
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
						
						for(Subspecies sub : Subspecies.values()) {
							if(sub.getRace()==race) {
								raceSB.append(
										"<tr>"
											+"<th>Subspecies:</th>"
											+ "<th><b style='color:"+Femininity.valueOf(racialBody.getFemaleFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(sub.getSingularFemaleName())+"</b></th>"
											+ "<th><b style='color:"+Femininity.valueOf(racialBody.getMaleFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(sub.getSingularMaleName())+"</b></th>"
										+ "</tr>");
							}
						}
						
						raceSB.append("</table>"
								+ "</div>"
								+ race.getBasicDescription()
								+(Main.getProperties().isAdvancedRaceKnowledgeDiscovered(race)
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

//	private static boolean confirmReset = false;
	public static final DialogueNodeOld CHARACTER_FETISHES = new DialogueNodeOld("Desires & Fetishes", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			journalSB = new StringBuilder(
					"<div class='container-full-width' style='padding:8px;'>"
						+ "You can select your [style.colourLust(desire)] for each fetish at the cost of [style.colourArcane("+FetishDesire.getCostToChange()+" Arcane Essence)],"
								+ " or choose to take the associated [style.colourFetish(fetish)] for [style.colourArcane("+Fetish.FETISH_ANAL_GIVING.getCost()+" Arcane Essences)].</br>"
						+ "Simply choosing a desire will affect bonus lust gains in sex, while taking a fetish will permanently lock your desire to 'love', and also give you special bonuses."
						+ " Fetishes can only be removed through enchanted potions.</br>"
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
			journalSB.append(getFetishEntry(Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING));
			journalSB.append(getFetishEntry(Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER));
			journalSB.append(getFetishEntry(Fetish.FETISH_CUM_STUD, Fetish.FETISH_CUM_ADDICT));
			journalSB.append(getFetishEntry(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_PURE_VIRGIN));
			journalSB.append(getFetishEntry(Fetish.FETISH_IMPREGNATION, Fetish.FETISH_PREGNANCY));
			journalSB.append(getFetishEntry(Fetish.FETISH_SEEDER, Fetish.FETISH_BROODMOTHER));
			journalSB.append(getFetishEntry(Fetish.FETISH_TRANSFORMATION_GIVING, Fetish.FETISH_TRANSFORMATION_RECEIVING));
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
							? " owned' style='border:2px solid " + Colour.FETISH.getShades()[1] + ";'>"
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
		return "<div class='square-button"+(desire!=FetishDesire.FOUR_LOVE&&Main.game.getPlayer().hasFetish(fetish)?" disabled":"")+"' style='width:10%; margin:0 5%;' id='"+fetish+"_"+desire+"'>"
				+ "<div class='square-button-content'>"+desire.getSVGImage()+"</div>"
				+ (Main.game.getPlayer().hasFetish(fetish) && Main.game.getPlayer().getFetishDesire(fetish)!=desire
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8; border-radius:5px;'></div>"
					:Main.game.getPlayer().getFetishDesire(fetish)!=desire
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; border-radius:5px;'></div>"
						:"")
			+ "</div>";
	}
}
