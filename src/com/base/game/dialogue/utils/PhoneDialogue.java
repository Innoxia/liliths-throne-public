package com.base.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.Litter;
import com.base.game.character.PregnancyPossibility;
import com.base.game.character.Quest;
import com.base.game.character.QuestLine;
import com.base.game.character.QuestType;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.PerkInterface;
import com.base.game.character.effects.PerkTree;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.Race;
import com.base.game.combat.DamageType;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.inventory.Rarity;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.item.ItemEffect;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.WeaponType;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.SexType;
import com.base.main.Main;
import com.base.rendering.RenderingEngine;
import com.base.rendering.SVGImages;
import com.base.utils.ClothingRarityComparator;
import com.base.utils.Colour;
import com.base.utils.ItemRarityComparator;
import com.base.utils.Util;
import com.base.utils.WeaponRarityComparator;

/**
 * @since 0.1.0
 * @version 0.1.82
 * @author Innoxia
 */
public class PhoneDialogue {

	private static StringBuilder journalSB;
	public static final DialogueNodeOld MENU = new DialogueNodeOld("Phone home screen", "Phone", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>You pull out your phone and tap in the unlock code.</p>"
					+ "<p>"
					+ "Using your powerful aura, you've managed to figure out a way to channel the arcane into charging the battery of your phone, although considering that it's the only one in this world, it's not much use for calling anyone."
					+ " Instead, you're using it as a way to store information about things you've discovered in this strange new world."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
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
						(Main.game.getPlayer().isNewWeaponDiscovered() || Main.game.getPlayer().isNewClothingDiscovered() || Main.game.getPlayer().isNewItemDiscovered() || Main.game.getPlayer().isNewRaceDiscovered())
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
						(Main.game.getPlayer().getLevelUpPoints() > 0 || Main.game.getPlayer().getPerkPoints() > 0)
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
						levelUpFetishes.clear();
					}
				};
				
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

			// journalSB.append("<div class='quest-title main'
			// style='color:"+QuestType.MAIN.getColour().toWebHexString()+";'>"+QuestLine.MAIN.getName()+"</div>");
			if (!Main.game.getPlayer().isMainQuestCompleted()) {
				journalSB.append("<div class='quest-box'>" + Quest.getLevelAndExperinceHTML(Main.game.getPlayer().getMainQuest(), true) + "<h6 style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";text-align:center;'>"
						+ Main.game.getPlayer().getMainQuest().getName() + "</h6>" + "<p style='text-align:center;'>" + Main.game.getPlayer().getMainQuest().getDescription() + "</p>" + "</div>");
			} else {
				journalSB.append("<div class='quest-box'>" + "<h6 style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";text-align:center;'>" + QuestLine.MAIN.getName() + "</h6>" + "<p style='text-align:center;'>"
						+ QuestLine.MAIN.getCompletedDescription() + "</p>" + "</div>");
			}

			if (Main.game.getPlayer().getMainQuestProgress() != 0) {

				for (int i = Main.game.getPlayer().getMainQuestProgress() - 1; i >= 0; i--)
					journalSB.append("<div class='quest-box'>" + Quest.getLevelAndExperinceHTML(Main.game.getPlayer().getMainQuestAtIndex(i), false) + "<h6 style='color:" + QuestType.MAIN.getColour().getShades()[1]
							+ ";text-align:center;'><b>Completed - " + Main.game.getPlayer().getMainQuestAtIndex(i).getName() + "</b></h6>" + "<p style='color:" + Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
							+ Main.game.getPlayer().getMainQuestAtIndex(i).getCompletedDescription() + "</p>" + "</div>");
			}
			journalSB.append("</details>");

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int index) {
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

			// Side Quests:
			if (Main.game.getPlayer().getNumberOfSideQuests() != 0) {

				for (QuestLine q : Main.game.getPlayer().getSetOfSideQuests()) {

					journalSB.append("<details open>");

					if (q.isCompleted(Main.game.getPlayer().getSideQuestProgress(q))) {
						journalSB.append("<summary class='quest-title' style='color:" + q.getType().getColour().getShades()[1] + ";'>Completed - " + q.getName() + "</summary>");
						journalSB.append("<div class='quest-box'>" + "<h6 style='color:" + q.getType().getColour().getShades()[1] + "; text-align:center;'><b>Side Quest Completed</b></h6>" + "<p style='color:"
								+ Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>" + q.getCompletedDescription() + "</p>" + "</div>");

					} else {
						journalSB.append("<summary class='quest-title' style='color:" + q.getType().getColour().toWebHexString() + ";'>" + q.getName() + "</summary>");
						journalSB.append("<div class='quest-box'>" + Quest.getLevelAndExperinceHTML(q.getQuestArray()[Main.game.getPlayer().getSideQuestProgress(q)], true) + "<h6 style='color:" + q.getType().getColour().toWebHexString()
								+ "; text-align:center;'><b>" + q.getQuestArray()[Main.game.getPlayer().getSideQuestProgress(q)].getName() + "</b></h6>" + "<p style='text-align:center;'>"
								+ q.getQuestArray()[Main.game.getPlayer().getSideQuestProgress(q)].getDescription() + "</p>" + "</div>");

					}

					for (int i = 0; (i < Main.game.getPlayer().getSideQuestProgress(q) && i < q.getQuestArray().length); i++)
						journalSB.append("<div class='quest-box'>" + Quest.getLevelAndExperinceHTML(q.getQuestArray()[i], false) + "<h6 style='color:" + q.getType().getColour().getShades()[1] + ";text-align:center;'><b>Completed - "
								+ q.getQuestArray()[i].getName() + "</b></h6>" + "<p style='color:" + Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>" + q.getQuestArray()[i].getCompletedDescription() + "</p>" + "</div>");

					journalSB.append("</details>");
				}
			} else {
				journalSB.append("<div class='subTitle'>You haven't got any side quests!</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int index) {
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

			// Romance Quests:
			if (Main.game.getPlayer().getNumberOfRomanceQuests() != 0) {

				for (QuestLine q : Main.game.getPlayer().getSetOfRomanceQuests()) {

					journalSB.append("<details open>");

					if (q.isCompleted(Main.game.getPlayer().getRomanceQuestProgress(q))) {
						journalSB.append("<summary class='quest-title' style='color:" + q.getType().getColour().getShades()[1] + ";'>" + q.getName() + " - Completed</summary>");
						journalSB.append("<div class='quest-box'>" + "<h6 style='color:" + q.getType().getColour().toWebHexString() + "; text-align:center;'><b>Romance Quest Completed</b></h6>" + "<p style='text-align:center;'>"
								+ q.getCompletedDescription() + "</p>" + "</div>");

					} else {
						journalSB.append("<summary class='quest-title' style='color:" + q.getType().getColour().toWebHexString() + ";'>" + q.getName() + "</summary>");
						journalSB.append("<div class='quest-box'>" + Quest.getLevelAndExperinceHTML(q.getQuestArray()[Main.game.getPlayer().getRomanceQuestProgress(q)], true) + "<h6 style='color:" + q.getType().getColour().toWebHexString()
								+ ";text-align:center;'><b>" + q.getQuestArray()[Main.game.getPlayer().getRomanceQuestProgress(q)].getName() + "</b></h6>" + "<p style='text-align:center;'>"
								+ q.getQuestArray()[Main.game.getPlayer().getRomanceQuestProgress(q)].getDescription() + "</p>" + "</div>");

					}

					for (int i = 0; (i < Main.game.getPlayer().getRomanceQuestProgress(q) && i < q.getQuestArray().length); i++)
						journalSB.append("<div class='quest-box'>" + Quest.getLevelAndExperinceHTML(q.getQuestArray()[i], false) + "<h6 style='color:" + q.getType().getColour().getShades()[1] + ";text-align:center;'><b>Completed: "
								+ q.getQuestArray()[i].getName() + "</b></h6>" + "<p style='color:" + Colour.TEXT_GREY.toWebHexString() + ";text-align:center;'>" + q.getQuestArray()[i].getCompletedDescription() + "</p>" + "</div>");

					journalSB.append("</details>");
				}
				
			} else {
				journalSB.append("<div class='subTitle'>You haven't got any romance quests!</div>");
				
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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

	public static final DialogueNodeOld CHARACTER_STATS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return
			// TODO: Perks, status effects & special attacks</p>"
				"<div class='subTitle'>" + "Attributes" + "</div>"

					+ "<div class='extraAttribute-third'>" + attributeValue(Main.game.getPlayer(), Attribute.STRENGTH) + "</div>" + "<div class='extraAttribute-third'>" + attributeValue(Main.game.getPlayer(), Attribute.INTELLIGENCE) + "</div>"
					+ "<div class='extraAttribute-third'>" + attributeValue(Main.game.getPlayer(), Attribute.FITNESS) + "</div>"

					+ "<span style='height:16px;width:800px;float:left;'></span>"

					+ "<div class='subTitle'>" + "Combat Attributes" + "</div>"
					+ extraAttributeBonus(Main.game.getPlayer(), Attribute.CRITICAL_CHANCE)
					+ extraAttributeBonus(Main.game.getPlayer(), Attribute.CRITICAL_DAMAGE)

			// Header:
					+ "<div class='extraAttribute-third type'>" + "Type" + "</div>" + "<div class='extraAttribute-third damage'>" + "&#8224 Damage &#8224" + "</div>" + "<div class='extraAttribute-third resist'>" + "&#8225 Resist &#8225" + "</div>"

			// Values:
					+ extraAttributeTableRow(Main.game.getPlayer(), "Attack", Attribute.DAMAGE_ATTACK, Attribute.RESISTANCE_ATTACK)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Spell", Attribute.DAMAGE_SPELLS, Attribute.RESISTANCE_SPELLS)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Willpower", Attribute.DAMAGE_MANA, Attribute.RESISTANCE_MANA)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Stamina", Attribute.DAMAGE_STAMINA, Attribute.RESISTANCE_STAMINA)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Physical", Attribute.DAMAGE_PHYSICAL, Attribute.RESISTANCE_PHYSICAL)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Fire", Attribute.DAMAGE_FIRE, Attribute.RESISTANCE_FIRE)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Cold", Attribute.DAMAGE_ICE, Attribute.RESISTANCE_ICE)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Poison", Attribute.DAMAGE_POISON, Attribute.RESISTANCE_POISON)

					+ "<span style='height:16px;width:800px;float:left;'></span>"

					+ "<div class='subTitle'>" + "Other Attributes" + "</div>"

					+ extraAttributeBonus(Main.game.getPlayer(), Attribute.FERTILITY) + extraAttributeBonus(Main.game.getPlayer(), Attribute.VIRILITY) + extraAttributeBonus(Main.game.getPlayer(), Attribute.SPELL_COST_MODIFIER);
		}

		@Override
		public Response getResponse(int index) {
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
	
	public static final DialogueNodeOld CHARACTER_STATS_BODY = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<div class='subTitle'>" + "Body stats" + "</div>"

					+ statRow("Core", "Femininity", String.valueOf(Main.game.getPlayer().getFemininity())) + statRow(null, "Height (cm)", String.valueOf(Main.game.getPlayer().getHeight()))

					+ statRow("Head", "Hair length (cm)", String.valueOf(Util.conversionInchesToCentimetres(Main.game.getPlayer().getHairRawLengthValue())))
					+ statRow(null, "Makeup", String.valueOf(Main.game.getPlayer().getFaceMakeupLevel().getValue()))

					+ statRow("Chest", "Cup size", Main.game.getPlayer().getBreastRawSizeValue() == 0 ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getBreastSize().getCupSizeName()))
					+ statRow(null, "Milk production (mL)", String.valueOf(Main.game.getPlayer().getBreastRawLactationValue()))
					+ statRow(null, "Capacity (inches)", String.valueOf(Main.game.getPlayer().getBreastRawCapacityValue()))
					+ statRow(null, "Elasticity", String.valueOf(Main.game.getPlayer().getBreastElasticity().getValue()) + " ("+Util.capitaliseSentence(Main.game.getPlayer().getBreastElasticity().getDescriptor())+")")

					+ statRow("Penis", "Length (inches)", Main.game.getPlayer().getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getPenisRawSizeValue()))
					+ statRow(null, "Ball size", Main.game.getPlayer().getPenisType() == PenisType.NONE ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getTesticleSize().getDescriptor()))
					+ statRow(null, "Cum production (mL)", Main.game.getPlayer().getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getPenisRawCumProductionValue()))

					+ statRow("Vagina", "Capacity (inches)", Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getVaginaRawCapacityValue()))
					+ statRow(null, "Elasticity", String.valueOf(Main.game.getPlayer().getVaginaElasticity().getValue()) + " ("+Util.capitaliseSentence(Main.game.getPlayer().getVaginaElasticity().getDescriptor())+")")
					+ statRow(null, "Wetness", Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(Main.game.getPlayer().getVaginaWetness().getDescriptor()))
					+ statRow(null, "Clit size (inches)", Main.game.getPlayer().getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(Main.game.getPlayer().getVaginaRawClitorisSizeValue()))

					+ statRow("Asshole", "Capacity (inches)", String.valueOf(Main.game.getPlayer().getAssRawCapacityValue()))
					+ statRow(null, "Elasticity", String.valueOf(Main.game.getPlayer().getAssElasticity().getValue()) + " ("+Util.capitaliseSentence(Main.game.getPlayer().getAssElasticity().getDescriptor())+")")
					+ statRow(null, "Wetness", Util.capitaliseSentence(Main.game.getPlayer().getAssWetness().getDescriptor()))

					+ "<span style='height:16px;width:800px;float:left;'></span>";
		}

		@Override
		public Response getResponse(int index) {
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
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.FINGER_PLAYER, OrificeType.VAGINA_PARTNER)),
							-1,
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Anal Fingering",
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.FINGER_PLAYER, OrificeType.ANUS_PARTNER)),
							-1,
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.ANUS_PLAYER)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Blowjobs",
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER)),
							Main.game.getPlayer().getStats().getCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER)),
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER)),
							Main.game.getPlayer().getStats().getCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER)))
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Cunnilingus",
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.TONGUE_PLAYER, OrificeType.VAGINA_PARTNER)),
							-1,
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.TONGUE_PARTNER, OrificeType.VAGINA_PLAYER)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_TWO, "Anilingus",
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.TONGUE_PLAYER, OrificeType.ANUS_PARTNER)),
							-1,
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.TONGUE_PARTNER, OrificeType.ANUS_PLAYER)),
							-1)
					
					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Vaginal sex",
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER)),
							Main.game.getPlayer().getStats().getCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER)),
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER)),
							Main.game.getPlayer().getStats().getCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Anal sex",
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER)),
							Main.game.getPlayer().getStats().getCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER)),
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER)),
							Main.game.getPlayer().getStats().getCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Nipple penetration",
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.NIPPLE_PARTNER)),
							Main.game.getPlayer().getStats().getCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.NIPPLE_PARTNER)),
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.NIPPLE_PLAYER)),
							Main.game.getPlayer().getStats().getCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.NIPPLE_PLAYER)))

					+ sexStatRow(Colour.AROUSAL_STAGE_FIVE, "Urethra penetration",
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.URETHRA_PARTNER)),
							Main.game.getPlayer().getStats().getCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.URETHRA_PARTNER)),
							Main.game.getPlayer().getStats().getSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.URETHRA_PLAYER)),
							Main.game.getPlayer().getStats().getCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.URETHRA_PLAYER)));
		}
		
		@Override
		public Response getResponse(int index) {
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
						+ "Mothered <b style='color:"+Colour.MASCULINE.toWebHexString()+";'>Sons</b></br>" + sonsBirthed
					+ "</div>"
					+ "<div class='extraAttribute-quarter'>"
						+ "Mothered <b style='color:"+Colour.FEMININE.toWebHexString()+";'>Daughters</b></br>" + daughtersBirthed
					+ "</div>"
					
					+ "<div class='extraAttribute-quarter'>"
						+ "Fathered <b style='color:"+Colour.MASCULINE.toWebHexString()+";'>Sons</b></br>" + sonsFathered
					+ "</div>"
					+ "<div class='extraAttribute-quarter'>"
						+ "Fathered <b style='color:"+Colour.FEMININE.toWebHexString()+";'>Daughters</b></br>" + daughtersFathered
					+ "</div>"
					+"<div class='subTitle'>Total offspring: "+(sonsBirthed+daughtersBirthed+sonsFathered+daughtersFathered)+"</div>"
					
					+"<div class='statsDescriptionBox'>"
						+ "Due to the incredible speed of both pregnancies and the development of children, the attachment between a child and his or her parents is far weaker in this world than what you're used to."
						+ " After reaching full maturity within a matter of hours, the vast majority of children will immediately leave their parents in order to strike out for themselves in the world."
						+ "</br></br>"
						+ "Despite this, however, a parent will always share a special maternal or paternal bond with their children, and, whether due to the arcane or natural intuition,"
							+ " a parent and child will always recognise each other at first sight."
					+ "</div>"
					
					+ "<span style='height:16px;width:800px;float:left;'></span>"
					
					+ pregnancyDetails()

					+ "<span style='height:16px;width:800px;float:left;'></span>"
					+"<div class='subTitle'>Offspring list</div>"
					+ "<div class='statsDescriptionBox' style='text-align:center;'>"
					
					+ "<table align='center'>"
					+ "<tr><th>Name</th><th>Race</th><th>Mother</th><th>Father</th></tr>"
					+ "<tr style='height:8px;'></tr>");
			
			for(NPC npc : Main.game.getOffspring()) {
				if(npc.isFeminine()) {
					UtilText.nodeContentSB.append(
							"<tr>"
								+ "<td style='min-width:100px;'>"
									+ "<b style='color:"+Colour.FEMININE.toWebHexString()+";'>"+(Main.game.getPlayer().getCharactersEncountered().contains(npc)?npc.getName():"Unknown")+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b style='color:"+npc.getRace().getColour().toWebHexString()+";'>"+npc.getRace().getOffspringFemaleNameSingular()+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b>"+(npc.getMother().isPlayer()?"You":npc.getMother().getName())+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b>"+(npc.getFather().isPlayer()?"You":npc.getFather().getName())+"</b>"
								+ "</td>"
							+ "</tr>");
				} else {
					UtilText.nodeContentSB.append(
							"<tr>"
								+ "<td style='min-width:100px;'>"
									+ "<b style='color:"+Colour.MASCULINE.toWebHexString()+";'>"+(Main.game.getPlayer().getCharactersEncountered().contains(npc)?npc.getName():"Unknown")+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b style='color:"+npc.getRace().getColour().toWebHexString()+";'>"+npc.getRace().getOffspringMaleNameSingular()+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b>"+(npc.getMother().isPlayer()?"You":npc.getMother().getName())+"</b>"
								+ "</td>"
								+ "<td style='min-width:100px;'>"
									+ "<b>"+(npc.getFather().isPlayer()?"You":npc.getFather().getName())+"</b>"
								+ "</td>"
							+ "</tr>");
				}
			}
			
			UtilText.nodeContentSB.append("</table></div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
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

	private static StringBuilder contentSB;
	private static String pregnancyDetails() {
		contentSB = new StringBuilder();
		
		// Mothered children:
		
		boolean noPregnancies=true;

		contentSB.append("<div class='subTitle'>Mothered children</div>");
		
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_1)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_2)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)){
			
			contentSB.append("<div class='statsDescriptionBox' style='text-align:center;'>"
					+ "[style.boldBad(Ongoing pregnancy)]"
					+ "</br>"
					+ "[style.bold(Possible partners:)]"
					+ "</br>");
			
			for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsMother()){
				if(pp.getFather()!=null) {
					contentSB.append(UtilText.parse(pp.getFather(),
							"<b>[npc.Name(A)] (</b>"
								+ (pp.getFather().getRaceStage().getName()!=""
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
					
					contentSB.append("</br>");
				}
			}
			contentSB.append("</b></div>");
			
			noPregnancies=false;
		
		}
		
		if (!Main.game.getPlayer().getLittersBirthed().isEmpty()) {
			for (Litter litter : Main.game.getPlayer().getLittersBirthed()) {
				if(litter.getFather()!=null) {
					contentSB.append(UtilText.parse(litter.getFather(),
							"<div class='statsDescriptionBox' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "</br>"
								+ "Conceived with [npc.name(a)] on day " + litter.getDayOfConception() + ", delivered on day " + litter.getDayOfBirth() + "."
								+ "</br>"
								+ "You gave birth to "+ litter.getBirthedDescriptionList()+ "."
							+ "</div>"));
				} else {
					contentSB.append(
							"<div class='statsDescriptionBox' style='text-align:center;'>"
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
		
		contentSB.append("<span style='height:16px;width:800px;float:left;'></span>"
				+ "<div class='subTitle'>Fathered children</div>");
		
		if(!Main.game.getPlayer().getPotentialPartnersAsFather().isEmpty()){
			
			for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsFather()){
				if(pp.getMother()!=null) {
					contentSB.append(UtilText.parse(pp.getMother(),
							"<div class='statsDescriptionBox' style='text-align:center;'>"
							+ "[style.boldBad(Ongoing pregnancy)]"
							+ "</br>"
							+"<b>[npc.Name(A)] (</b>"
								+ (pp.getMother().getRaceStage().getName()!=""
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
					
					contentSB.append("</br></div>");
				}
			}
			contentSB.append("</b></div>");
			
			noPregnancies=false;
		
		}
		
		if (!Main.game.getPlayer().getLittersFathered().isEmpty()) {
			for (Litter litter : Main.game.getPlayer().getLittersFathered()) {
				if(litter.getMother()!=null) {
					contentSB.append(UtilText.parse(litter.getMother(),
							"<div class='statsDescriptionBox' style='text-align:center;'>"
								+ "[style.boldGood(Resolved Pregnancy)]"
								+ "</br>"
								+ "Conceived with [npc.name(a)] on day " + litter.getDayOfConception() + ", delivered on day " + litter.getDayOfBirth() + "."
								+ "</br>"
								+ "[npc.She] gave birth to "+ litter.getBirthedDescriptionList()+ "."
							+ "</div>"));
					
				} else {
					contentSB.append(
							"<div class='statsDescriptionBox' style='text-align:center;'>"
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

	private static String statRow(String left, String centre, String right) {
		return "<div class='extraAttribute-third'" + (left == "" || left == null ? " style='height:24px;background:transparent;'" : "") + ">" + (left != null ? left : "") + "</div>" + "<div class='extraAttribute-third'>" + centre + "</div>"
				+ "<div class='extraAttribute-third'>" + right + "</div>";
	}

	private static String attributeValue(GameCharacter owner, Attribute att) {
		return "<span style='color:" + att.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(att.getName()) + "</span></br>"
				+ (owner.getBaseAttributeValue(att) > 0 ? "<span style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";'>"
						: (owner.getBaseAttributeValue(att) < 0 ? "<span style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";'>" : "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>"))
				+ owner.getBaseAttributeValue(att) + "</span>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'> + </span>"
				+ (owner.getBonusAttributeValue(att) > 0 ? "<span style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";'>"
						: (owner.getBonusAttributeValue(att) < 0 ? "<span style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";'>" : "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>"))
				+ owner.getBonusAttributeValue(att) + "</span>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'> = </span>" + owner.getAttributeValue(att);
	}

	private static String extraAttributeTableRow(GameCharacter owner, String type, Attribute damage, Attribute resist) {
		return "<div class='extraAttribute-third type'>"
				+ "<span style='color:" + damage.getColour().toWebHexString() + ";'>" + type + "</span>" + "</div>"
				
				+ "<div class='extraAttribute-third damage'>"
					+ "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>" + damage.getBaseValue() + "</span>"
					+ " + "
					+ (owner.getBonusAttributeValue(damage)==0?"<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>"
							:(owner.getBonusAttributeValue(damage)>0
								?"<span style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";'>"
								:"<span style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";'>"))
					+ owner.getBonusAttributeValue(damage)+"</span> = "
					+ (owner.getAttributeValue(damage) > damage.getBaseValue()
											? "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"
											: (owner.getAttributeValue(damage) < damage.getBaseValue()
													? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>"
													: ""))
					+ owner.getAttributeValue(damage)
					+ "</span>"
				+ "</div>"
					
				+ "<div class='extraAttribute-third damage'>"
					+ "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>" + resist.getBaseValue() + "</span>"
					+ " + "
					+ (owner.getBonusAttributeValue(resist)==0?"<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>"
							:(owner.getBonusAttributeValue(resist)>0
								?"<span style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";'>"
								:"<span style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";'>"))
					+ owner.getBonusAttributeValue(resist)+"</span> = "
					+ (owner.getAttributeValue(resist) > resist.getBaseValue()
											? "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"
											: (owner.getAttributeValue(resist) < resist.getBaseValue()
													? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>"
													: ""))
					+ owner.getAttributeValue(resist)
					+ "</span>"
				+ "</div>";
		
	}

	private static String extraAttributeBonus(GameCharacter owner, Attribute bonus) {
		return "<div class='extraAttribute-half bonus'>" + "<span style='color:" + bonus.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(bonus.getName()) + "</span></br>"

				+ (owner.getBaseAttributeValue(bonus) > 0 ? "<span style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";'>"
						: (owner.getBaseAttributeValue(bonus) < 0 ? "<span style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";'>" : "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>"))
				+ owner.getBaseAttributeValue(bonus) + "</span>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'> + </span>"
				+ (owner.getBonusAttributeValue(bonus) > 0 ? "<span style='color:" + Colour.GENERIC_GOOD.getShades()[1] + ";'>"
						: (owner.getBonusAttributeValue(bonus) < 0 ? "<span style='color:" + Colour.GENERIC_BAD.getShades()[1] + ";'>" : "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>"))
				+ owner.getBonusAttributeValue(bonus) + "</span>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'> = </span>"
				+ (owner.getAttributeValue(bonus) > 0 ? "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>" : (owner.getAttributeValue(bonus) < 0 ? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>" : "<span>"))
				+ owner.getAttributeValue(bonus) + "</span>"

				+ "</div>";
	}

	private static String content, title;

	public static void resetContentForContacts() {
		CharactersPresentDialogue.characterViewed = Main.game.getPlayer().getCharactersEncountered().get(0);
		title = "Contacts";
		content = "<p>You have encountered the following characters in your travels:</p>";
		for (int i = 0; i < Main.game.getPlayer().getCharactersEncountered().size(); i++)
			content += "<p>" + Main.game.getPlayer().getCharactersEncountered().get(i).getName() + "</p>";
	}

	public static final DialogueNodeOld CONTACTS = new DialogueNodeOld("Contacts", "Look at your contacts.", true) {
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
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else if (index <= Main.game.getPlayer().getCharactersEncountered().size()) {
				return new Response(Util.capitaliseSentence(Main.game.getPlayer().getCharactersEncountered().get(index - 1).getName()),
						"Take a detailed look at what " + Main.game.getPlayer().getCharactersEncountered().get(index - 1).getName("the") + " looks like.",
						CONTACTS){
					@Override
					public void effects() {
						CharactersPresentDialogue.characterViewed = Main.game.getPlayer().getCharactersEncountered().get(index - 1);
						
						RenderingEngine.ENGINE.setCharactersInventoryToRender(Main.game.getPlayer().getCharactersEncountered().get(index - 1));
						title = Util.capitaliseSentence(Main.game.getPlayer().getCharactersEncountered().get(index - 1).getName());
						content = CharactersPresentDialogue.getCharacterInfoBox(Main.game.getPlayer().getCharactersEncountered().get(index - 1))
								+ "<h4>Background</h4>"
								+ "<p>"
										+ Main.game.getPlayer().getCharactersEncountered().get(index - 1).getDescription()
								+ "</p>"
										+ "<h4>Appearance</h4>"
								+ "<p>"
									+ Main.game.getPlayer().getCharactersEncountered().get(index - 1).getBodyDescription()
								+ "</p>"
								+ CharactersPresentDialogue.getStats(Main.game.getPlayer().getCharactersEncountered().get(index - 1));
								
								
								
//								"<p>" + Main.game.getPlayer().getCharactersEncountered().get(index - 1).getDescription() + "</p>" + "<p>" + Main.game.getPlayer().getCharactersEncountered().get(index - 1).getBodyDescription() + "</p>";
//						RenderingEngine.ENGINE.renderInventory();
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
	
	
	public static final DialogueNodeOld ENCYCLOPEDIA = new DialogueNodeOld("Encyclopedia", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "You have collected information on all the races, weapons, clothing, and items that you've encountered in your travels."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response((Main.game.getPlayer().isNewRaceDiscovered())?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Races</span>":"Races",
						"Have a look at all the different races that you've encountered in your travels.", RACES){
					@Override
					public void effects() {
						Main.game.getPlayer().setNewRaceDiscovered(false);
					}
				};
			
			} else if (index == 2) {
				return new Response((Main.game.getPlayer().isNewWeaponDiscovered())?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Weapons</span>":"Weapons",
						"Have a look at all the different weapons that you've encountered in your travels.", WEAPON_CATALOGUE){
					@Override
					public void effects() {
						Main.game.getPlayer().setNewWeaponDiscovered(false);
					}
				};
			
			} else if (index == 3) {
				return new Response((Main.game.getPlayer().isNewClothingDiscovered())?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Clothing</span>":"Clothing",
						"Have a look at all the different clothing that you've encountered in your travels.", CLOTHING_CATALOGUE){
					@Override
					public void effects() {
						Main.game.getPlayer().setNewClothingDiscovered(false);
					}
				};
			
			} else if (index == 4) {
				return new Response((Main.game.getPlayer().isNewItemDiscovered())?"<span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>Items</span>":"Items",
						"Have a look at all the different items that you've encountered in your travels.", ITEM_CATALOGUE){
					@Override
					public void effects() {
						Main.game.getPlayer().setNewItemDiscovered(false);
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

	private static List<ItemType> itemsDiscoveredList = new ArrayList<>();
	private static List<WeaponType> weaponsDiscoveredList = new ArrayList<>();
	private static List<ClothingType> clothingDiscoveredList = new ArrayList<>();
	static {
		for (ItemType item : ItemType.availableItems)
			itemsDiscoveredList.add(item);
		itemsDiscoveredList.sort(new ItemRarityComparator());

		for (WeaponType weapon : WeaponType.values())
			weaponsDiscoveredList.add(weapon);
		weaponsDiscoveredList.sort(new WeaponRarityComparator());

		for (ClothingType clothing : ClothingType.values())
			clothingDiscoveredList.add(clothing);
		clothingDiscoveredList.sort(new ClothingRarityComparator());

	}
	public static final DialogueNodeOld WEAPON_CATALOGUE = new DialogueNodeOld("Discovered Weapons", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {

			journalSB = new StringBuilder();

			// All known weapons:

			journalSB.append("<div class='phone-item-third slot'>Slot</div>");
			journalSB.append("<div class='phone-item-third name'>Weapon</div>");
			journalSB.append("<div class='phone-item-third colours'>Damage types <span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>(Hover for image)</span></div>");

			for (WeaponType weapon : weaponsDiscoveredList) {
				if (Main.game.getPlayer().getWeaponsDiscovered().contains(weapon)) {
					journalSB.append("<div class='phone-item-third slot'>" + Util.capitaliseSentence(weapon.getSlot().getName()) + "</div>");
					journalSB.append("<div class='phone-item-third name' style='color:" + weapon.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(weapon.getName()) + "</div>");

					journalSB.append("<div class='phone-item-third colours'>");
					for (DamageType dt : weapon.getAvailableDamageTypes())
						journalSB.append("<div class='phone-item-colour' id='" + (weapon.toString() + "_" + dt.toString()) + "' style='background-color:" + dt.getMultiplierAttribute().getColour().toWebHexString() + ";'></div>");
					journalSB.append("</div>");

				} else {
					journalSB.append("<div class='phone-item-third slot'>" + Util.capitaliseSentence(weapon.getSlot().getName()) + "</div>");
					journalSB.append("<div class='phone-item-third name'>???</div>");

					journalSB.append("<div class='phone-item-third colours'>???</div>");
				}
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int index) {
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

			journalSB.append("<div class='phone-item-third slot'>Slot</div>");
			journalSB.append("<div class='phone-item-third name'>Clothing</div>");
			journalSB.append("<div class='phone-item-third colours'>Colours <span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>(Hover for image)</span></div>");

			for (ClothingType clothing : clothingDiscoveredList) {
				if (Main.game.getPlayer().getClothingDiscovered().contains(clothing)) {
					journalSB.append("<div class='phone-item-third slot'>" + Util.capitaliseSentence(clothing.getSlot().getName()) + "</div>");
					journalSB.append("<div class='phone-item-third name' style='color:" + clothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(clothing.getName()) + "</div>");

					journalSB.append("<div class='phone-item-third colours'>");
					for (Colour c : clothing.getAvailableColours())
						journalSB.append("<div class='phone-item-colour' id='" + (clothing.toString() + "_" + c.toString()) + "' style='background-color:" + c.toWebHexString() + ";'></div>");
					journalSB.append("</div>");

				} else {
					journalSB.append("<div class='phone-item-third slot'>" + Util.capitaliseSentence(clothing.getSlot().getName()) + "</div>");
					journalSB.append("<div class='phone-item-third name'>???</div>");

					journalSB.append("<div class='phone-item-third colours'>???</div>");
				}
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int index) {
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

			journalSB.append("<div class='phone-item-half name'>Item</div>");
			journalSB.append("<div class='phone-item-half effects'>Effects</div>");

			for (ItemType item : itemsDiscoveredList) {
				if (Main.game.getPlayer().getItemsDiscovered().contains(item)) {
					journalSB.append("<div class='phone-item-half name' style='color:" + item.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(item.getName(false)) + "</div>");

					journalSB.append("<div class='phone-item-half effects'>");
					if(item.getEffects().size()==0) {
						journalSB.append("-");
					}
					int i=1;
					for(ItemEffect ie : item.getEffects()) {
						for(String s : ie.getEffectsDescription()) {
							journalSB.append(s);
							if(i != ie.getEffectsDescription().size())
								journalSB.append("</br>");
							i++;
						}
					}
					journalSB.append("</div>");

				} else {
					journalSB.append("<div class='phone-item-half name'>???</div>");

					journalSB.append("<div class='phone-item-half effects'>???</div>");
				}
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int index) {
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

	public static void resetContentForRaces() {
		title = "Races";
		content = "<p style='text-align:center;'>You have encountered the following races in your travels:</p>";
		
		for (Race r : Main.game.getPlayer().getRacesDiscovered())
			content += "<p style='text-align:center;'><b style='color:"+r.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(r.getName()) + "</b></p>";
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
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);
			
			} else if (index <= Main.game.getPlayer().getRacesDiscovered().size()) {
				return new Response(Util.capitaliseSentence(Main.game.getPlayer().getRacesDiscovered().get(index - 1).getName()),
						"Take a detailed look at what " + Main.game.getPlayer().getRacesDiscovered().get(index - 1).getName() + "s are like.",
						RACES){
					@Override
					public void effects() {
						title = Util.capitaliseSentence(Main.game.getPlayer().getRacesDiscovered().get(index - 1).getName()) + " ("
								+ Util.capitaliseSentence(Main.game.getPlayer().getRacesDiscovered().get(index - 1).getGenus().getName()) + ")";
						content = "<p>" + "Male " + Main.game.getPlayer().getRacesDiscovered().get(index - 1).getName() + "s are called <b style='color:" + Colour.MASCULINE.toWebHexString() + ";'>"
								+ Main.game.getPlayer().getRacesDiscovered().get(index - 1).getPluralMaleName() + "</b>." + "</p>" + "<p>" + "Female " + Main.game.getPlayer().getRacesDiscovered().get(index - 1).getName()
								+ "s are called <b style='color:" + Colour.FEMININE.toWebHexString() + ";'>" + Main.game.getPlayer().getRacesDiscovered().get(index - 1).getPluralFemaleName() + "</b>." + "</p>"
								+ Main.game.getPlayer().getRacesDiscovered().get(index - 1).getDescription();
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

	public static int strengthPoints = 0, intelligencePoints = 0, fitnessPoints = 0, spendingPoints=0, spendingFetishPoints=0;
	public static List<PerkInterface> levelUpPerks = new ArrayList<>(), levelUpFetishes = new ArrayList<>();

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
														: PerkTree.getPerkGrid()[i / 2][j - 1].getPerkCategory().getColour().getShades()[1] + ";") + "'>"
												: " locked' style='border:4px solid " + Colour.TEXT_GREY.toWebHexString() + ";'>"))
										+ PerkTree.getPerkGrid()[i / 2][j - 1].getSVGString()
										+ (Main.game.getPlayer().hasPerk(PerkTree.getPerkGrid()[i / 2][j - 1]) || levelUpPerks.contains(PerkTree.getPerkGrid()[i / 2][j - 1]) // Overlay to create disabled effect:
												? ""
												: (PerkTree.getPerkGrid()[i / 2][j - 1].isAvailable(Main.game.getPlayer(), levelUpPerks)
														? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.2; border-radius:5px;'></div>"
														: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.3; border-radius:5px;'></div>"))
										+ "</td>");
							} else
								journalSB.append("<td class='perkCell'></td>");

						} else { // Arrow icon
							if (i < 8) {
								if (PerkTree.getArrowGrid()[i / 2][j - 1] == true) {
									journalSB.append("<td class='arrowCell'>" + SVGImages.SVG_IMAGE_PROVIDER.getPerkTreeArrow() + "</td>");
								} else
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
		public Response getResponse(int index) {
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
							Collections.sort(levelUpPerks, (a, b) -> a.getRequiredLevel().getLevel() - b.getRequiredLevel().getLevel());

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
//		float i = (Main.game.getPlayer().getFetishes().size() + levelUpFetishes.size());
//		return (int) ((i*i*0.5f) + (i*0.5f) + 1);
		if(Main.game.getPlayer().getFetishes().size()+PhoneDialogue.levelUpFetishes.size()==0)
			return 0;
		else
			return 5;
	}
	
	public static final DialogueNodeOld CHARACTER_FETISHES = new DialogueNodeOld("Fetishes", "", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			journalSB = new StringBuilder(
					"<div class='statsDescriptionBox'>"
						+ "You can unlock fetishes by using the arcane essences that you've gathered (from orgasming in sex)."
						+ " <b>Your first fetish is free</b>, but it will cost you <b>five</b> arcane essences for each one you unlock after that."
						+ "</br></br>"
						+ "Derived fetishes cannot be directly unlocked, but are instead automatically unlocked when you meet their requirements."
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
						+ " "+spendingFetishPoints
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
						+ " "+(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) - spendingFetishPoints)
					+ "</div>"
					

					+ "<span style='height:16px;width:800px;float:left;'></span>"
					
					+ "<div class='subTitle'>"
					+ "Unlockable fetishes"
					+ "</div>"
					
					+ "<div class='fetish-container'>");
			
			for(Fetish fetish : Fetish.values()) {
				if(fetish.getFetishesForAutomaticUnlock().isEmpty())
					journalSB.append(
							"<div id='fetishUnlock" + fetish + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:4px solid " + fetish.getPerkCategory().getColour().getShades()[1] + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer(), levelUpFetishes)
									? " unlocked' style='border:4px solid " + (levelUpFetishes.contains(fetish)
											? Colour.GENERIC_EXCELLENT.toWebHexString() + ";"
											: Colour.TEXT_GREY.toWebHexString() + ";") + "'>"
									: " locked' style='border:4px solid " + Colour.TEXT_GREY.toWebHexString() + ";'>"))
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString()+"</div>"
							+ (Main.game.getPlayer().hasFetish(fetish) || levelUpFetishes.contains(fetish) // Overlay to create disabled effect:
									? ""
									: (fetish.isAvailable(Main.game.getPlayer(), levelUpFetishes)
											? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.3; border-radius:5px;'></div>"
											: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; border-radius:5px;'></div>"))
							+ "</div>");
			}
			
			journalSB.append("</div>"

					+ "<span style='height:16px;width:800px;float:left;'></span>"
					
					+ "<div class='subTitle'>"
					+ "Derived fetishes"
					+ "</div>"
					+ "<div class='fetish-container'>");
			
			for(Fetish fetish : Fetish.values()) {
				if(!fetish.getFetishesForAutomaticUnlock().isEmpty())
					journalSB.append(
							"<div id='fetishUnlock" + fetish + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
									? " owned' style='border:4px solid " + fetish.getPerkCategory().getColour().getShades()[1] + ";'>"
									: " unlocked' style='border:4px solid " + Colour.TEXT_GREY.toWebHexString() + ";'>")
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString()+"</div>"
							+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
									? ""
									: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.3; border-radius:5px;'></div>")
							+ "</div>");
			}
			journalSB.append("</div>");
			
			
			return journalSB.toString();
		}
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU) {
					@Override
					public void effects() {
						spendingFetishPoints = 0;
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
							for (PerkInterface f : levelUpFetishes) {
								Main.game.getPlayer().addFetish((Fetish)f);
								f.applyPerkGained(Main.game.getPlayer());
							}
							
							Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -spendingFetishPoints);
							spendingFetishPoints = 0;

							levelUpFetishes.clear();
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
