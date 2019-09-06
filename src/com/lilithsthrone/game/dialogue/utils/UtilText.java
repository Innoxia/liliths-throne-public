package com.lilithsthrone.game.dialogue.utils;

import java.io.File;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.OccupationTag;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.places.PlaceUpgrade;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia, Pimvgd, AlacoGit
 */
public class UtilText {

	private static String modifiedSentence;
	public static StringBuilder transformationContentSB = new StringBuilder(4096);
	public static StringBuilder nodeContentSB = new StringBuilder(4096);
	private static StringBuilder descriptionSB = new StringBuilder();
	private static List<ParserTag> parserTags;
	private static List<String> parserVariableCalls;
	
	private static AbstractClothingType clothingTypeForParsing;
	
//	private static List<GameCharacter> specialNPCList = new ArrayList<>();
	private static boolean parseCapitalise;
	private static boolean parseAddPronoun;
	
	private static ScriptEngine engine;
	
	private static List<String> specialParsingStrings = new ArrayList<>();

	/**
	 * Converts the input into a format suitable for html display. i.e. converts things like '<' to "&lt;".
	 */
	public static String parseForHTMLDisplay(String input) {
		StringBuilder builder = new StringBuilder();
		
		for (char c : input.toCharArray()) {
			switch (c) {
				case ' ':
					builder.append("&nbsp;");
					break;
				case '<':
					builder.append("&lt;");
					break;
				case '>':
					builder.append("&gt;");
					break;
				case '&':
					builder.append("&amp;");
					break;
				case '"':
					builder.append("&quot;");
					break;
				case '\'':
					builder.append("&#39;");
					break;
				default:
					builder.append(c);
					break;
			}
		}
		
		return builder.toString();
	}
	
	public static String parsePlayerThought(String text) {
		return parseThought(text, Main.game.getPlayer());
//		if(Main.game.getPlayer()==null) {
//			return "";
//		}
//		
//		modifiedSentence = text;
//		if (Main.game.getPlayer().hasFetish(Fetish.FETISH_BIMBO))
//			modifiedSentence = Util.addBimbo(text, 6);
//
//		if (Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.MASCULINE_STRONG)
//			return "<span class='thoughts masculine'>" + modifiedSentence + "</span>";
//		else if (Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.ANDROGYNOUS)
//			return "<span class='thoughts androgynous'>" + modifiedSentence + "</span>";
//		else
//			return "<span class='thoughts feminine'>" + modifiedSentence + "</span>";
	}

	public static String parsePlayerSpeech(String text) {
		return parseSpeech(text, Main.game.getPlayer());
//		modifiedSentence = text;
//		if (Main.game.getPlayer().hasFetish(Fetish.FETISH_BIMBO)) {
//			modifiedSentence = Util.addBimbo(modifiedSentence, 6);
//		}
//		
//		if(Main.game.getPlayer().getAlcoholLevel().getSlurredSpeechFrequency()>0) {
//			modifiedSentence = Util.addDrunkSlur(modifiedSentence, Main.game.getPlayer().getAlcoholLevel().getSlurredSpeechFrequency());
//		}
//		
//		// Apply speech effects:
//		if(Main.game.isInSex()) {
//			if(Sex.isCharacterEngagedInOngoingAction(Main.game.getPlayer())) {
//				modifiedSentence = Util.addSexSounds(modifiedSentence, 6);
//			}
//			
//			if(!Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH).isEmpty()) {
//				modifiedSentence = Util.addMuffle(modifiedSentence, 6);
//			} else {
//				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
//					for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
//						if(c.getClothingType().isMufflesSpeech()) {
//							modifiedSentence = Util.addMuffle(modifiedSentence, 6);
//							break;
//						}
//					}
//				}
//			}
//			
//		} else {
//			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
//				for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
//					if(c.getClothingType().isMufflesSpeech()) {
//						modifiedSentence = Util.addMuffle(modifiedSentence, 6);
//						break;
//					}
//				}
//			}
//		}
//		
//		if(Main.game.getPlayer().getLipSize().isImpedesSpeech()) {
//			modifiedSentence = Util.applyLisp(modifiedSentence);
//		}
//
//		if (Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.MASCULINE_STRONG)
//			return "<span class='speech' style='color:" + Colour.MASCULINE.toWebHexString() + ";'>" + modifiedSentence + "</span>";
//		else if (Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.ANDROGYNOUS)
//			return "<span class='speech' style='color:" + Colour.ANDROGYNOUS.toWebHexString() + ";'>" + modifiedSentence + "</span>";
//		else
//			return "<span class='speech' style='color:" + Colour.FEMININE.toWebHexString() + ";'>" + modifiedSentence + "</span>";
	}

	private static String getGlowStyle(Colour colour) {
		return colour==null?"":"text-shadow: 0px 0px 4px "+colour.getShadesRgbaFormat(0.75f)[1]+";";
	}
	
	public static String parseSpeech(String text, GameCharacter target) {
		modifiedSentence = text;
		
		String[] splitOnConditional = modifiedSentence.split("#THEN");
		
		modifiedSentence = splitOnConditional[splitOnConditional.length-1];
		
		if(!parserTags.contains(ParserTag.SEX_ALLOW_MUFFLED_SPEECH) && Main.game.isInSex()
				&& Sex.getAllParticipants().contains(target)
				&& target.isSpeechMuffled()) {
			if(Sex.isOngoingActionsBlockingSpeech(target)) {
				modifiedSentence = Util.replaceWithMuffle(modifiedSentence, 2);// + " <i style='font-size:66%;'>("+modifiedSentence+")</i>";
			}
			
		} else {
			if (target.hasFetish(Fetish.FETISH_BIMBO)) {
				modifiedSentence = Util.addBimbo(modifiedSentence, 6);
			}
			
			if(target.getAlcoholLevel().getSlurredSpeechFrequency()>0) {
				modifiedSentence = Util.addDrunkSlur(modifiedSentence, target.getAlcoholLevel().getSlurredSpeechFrequency());
			}
			
			// Apply speech effects:
			if(target.isSpeechMuffled()) {
				modifiedSentence = Util.addMuffle(modifiedSentence, 5);
				
			} else if(Main.game.isInSex() && Sex.getAllParticipants().contains(target)) {
				if(Sex.isCharacterEngagedInOngoingAction(target)) {
					modifiedSentence = Util.addSexSounds(modifiedSentence, 6);
				}
				
			}
			
			if(target.getLipSize().isImpedesSpeech()) {
				modifiedSentence = Util.applyLisp(modifiedSentence);
			}
			
			if(splitOnConditional.length>1) {
				modifiedSentence = splitOnConditional[0]+"#THEN"+modifiedSentence;
			}
		}
		
		Colour glow = target.getSpeechGlowColour();
		if (target.getSpeechColour() != null) {
			return "<span class='speech' style='color:" + target.getSpeechColour() + ";"+getGlowStyle(glow)+"'>"
						+ modifiedSentence
					+ "</span>";

		} else {
			if (Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE_STRONG) {
				return "<span class='speech' style='color:" + Colour.MASCULINE_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			} else if (Femininity.valueOf(target.getFemininityValue()) == Femininity.ANDROGYNOUS) {
				return "<span class='speech' style='color:" + Colour.ANDROGYNOUS_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			} else {
				return "<span class='speech' style='color:" + Colour.FEMININE_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			}
		}
	}
	
	public static String parseSpeechNoEffects(String text, GameCharacter target) {
		modifiedSentence = text;

		Colour glow = target.getSpeechGlowColour();
		if (target.getSpeechColour() != null) {
			return "<span class='speech' style='color:" + target.getSpeechColour() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";

		} else {
			if (Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE_STRONG) {
				return "<span class='speech' style='color:" + Colour.MASCULINE_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			} else if (Femininity.valueOf(target.getFemininityValue()) == Femininity.ANDROGYNOUS) {
				return "<span class='speech' style='color:" + Colour.ANDROGYNOUS_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			} else {
				return "<span class='speech' style='color:" + Colour.FEMININE_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			}
		}
	}
	public static String parseThought(String text, GameCharacter target) {
		modifiedSentence = text;

		if (target.hasFetish(Fetish.FETISH_BIMBO))
			modifiedSentence = Util.addBimbo(modifiedSentence, 6);

		// Apply speech effects:
		if(Main.game.isInSex()) {
			if(Sex.isCharacterEngagedInOngoingAction(target))
				modifiedSentence = Util.addSexSounds(modifiedSentence, 5);
		}

		if (target.getSpeechColour() != null) {
			return "<span class='thoughts' style='color:" + target.getSpeechColour() + ";'>" + modifiedSentence + "</span>";

		} else {
			if (Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE_STRONG) {
				return "<span class='thoughts' style='color:" + Colour.MASCULINE_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
			} else if (Femininity.valueOf(target.getFemininityValue()) == Femininity.ANDROGYNOUS) {
				return "<span class='thoughts' style='color:" + Colour.ANDROGYNOUS_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
			} else {
				return "<span class='thoughts' style='color:" + Colour.FEMININE_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
			}
		}
	}

	public static String parseNPCSpeech(String text, Femininity femininity) {
		return parseNPCSpeech(text, femininity, false, false);
	}

	public static String parseNPCSpeech(String text, Femininity femininity, boolean bimbo, boolean stutter) {
		modifiedSentence = text;
		if (bimbo) {
			modifiedSentence = Util.addBimbo(modifiedSentence, 6);
		}
		if (stutter) {
			modifiedSentence = Util.addStutter(modifiedSentence, 6);
		}
		return "<span class='speech' style='color:" + femininity.getColour().toWebHexString() + ";'>" + modifiedSentence + "</span>";
	}
	
	public static String getDisabledResponse(String label) {
		return "<span class='option-disabled'>"+label+"</span>";
	}
	
	// "Temporary" methods until I refine the way DialogueNodes work:
	public static String getRequirementsDescription(AbstractPerk perkRequired) {
		return ("You require the perk '<b style='color:"+perkRequired.getPerkCategory().getColour().toWebHexString()+";'>"+perkRequired.getName(Main.game.getPlayer())+"</b>'.");
	}
	
	public static String getRequirementsDescription(AbstractPerk perkRequired, Gender... gendersRequired) {
		descriptionSB.setLength(0);
		
		descriptionSB.append("You require the perk '<b style='color:"+perkRequired.getPerkCategory().getColour().toWebHexString()+";'>"+perkRequired.getName(Main.game.getPlayer())+"</b>'");
		
		if(gendersRequired.length==0) {
			descriptionSB.append(".");
		} else {
			descriptionSB.append(", and need to be a ");
			
			for(int i=0 ;i<gendersRequired.length; i++) {
				if(i!=0) {
					if(i==gendersRequired.length-1)
						descriptionSB.append(", or ");
					else
						descriptionSB.append(", ");
				}
				descriptionSB.append("<b style='color:"+gendersRequired[i].getColour().toWebHexString()+";'>"+gendersRequired[i].getName()+"</b>");
			}
			
			descriptionSB.append(".");
		}
		
		return descriptionSB.toString();
	}
	
	public static String getRequirementsDescription(CorruptionLevel corruptionNeeded, AbstractPerk... perkRequired) {
		descriptionSB.setLength(0);
		
		descriptionSB.append("You require a corruption level of <b style='color:"+corruptionNeeded.getColour().toWebHexString()+";'>"+corruptionNeeded.getName()+"</b>");
		
		if(perkRequired.length==0) {
			descriptionSB.append(".");
		} else {
			descriptionSB.append(", or the perk"+(perkRequired.length>1?"s ":" "));
			
			for(int i=0 ;i<perkRequired.length; i++) {
				if(i!=0) {
					if(i==perkRequired.length-1)
						descriptionSB.append(", or ");
					else
						descriptionSB.append(", ");
				}
				descriptionSB.append("<b style='color:"+perkRequired[i].getPerkCategory().getColour().toWebHexString()+";'>"+perkRequired[i].getName(Main.game.getPlayer())+"</b>");
			}
			
			descriptionSB.append(".");
		}
		
		return descriptionSB.toString();
	}
	
	public static String formatAsMoney(int money) {
		return formatAsMoney(money, "b");
	}

	public static String getCurrencySymbol() {
//		return "&#9679;"; // Circle
		return "&#164;"; // 'Generic' currency symbol
	}
	
	public static String getPentagramSymbol() {
		return "&#9737;"; // Java doesn't support unicode 6 ;_;   No pentagram for me... ;_;  "&#9956";
	}
	
	public static String formatAsEssencesUncoloured(int amount, String tag, boolean withOverlay) {
		return "<div class='item-inline'>"
					+ TFEssence.ARCANE.getSVGStringUncoloured() + (withOverlay?"<div class='overlay no-pointer' id='ESSENCE_"+TFEssence.ARCANE.hashCode()+"'></div>":"")
				+"</div>"
				+ " <"+tag+" style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"+Units.number(amount)+"</"+tag+">";
	}
	
	
	public static String formatAsEssences(int amount, String tag, boolean withOverlay) {
		return "<div class='item-inline'>"
					+ TFEssence.ARCANE.getSVGString() + (withOverlay?"<div class='overlay no-pointer' id='ESSENCE_"+TFEssence.ARCANE.hashCode()+"'></div>":"")
				+"</div>"
				+ " <"+tag+" style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"+Units.number(amount)+"</"+tag+">";
	}
	
	public static String formatAsMoney(int money, String tag) {
		return formatAsMoney(money, tag, null);
	}
	
	/**
	 * Just used for values like "?". <b>Do not</b> use for numerical values.
	 */
	public static String formatAsMoney(String money, String tag) {
		return "<" + tag + " style='color:" + Colour.CURRENCY_GOLD.toWebHexString() + "; padding-right:2px;'>" + getCurrencySymbol() + "</" + tag + ">"
				+ "<" + tag + " style='color:" + Colour.TEXT.getShades(8)[3] + ";'>" + money + "</" + tag + ">";
	}
	
	public static String applyGlow(String input, Colour colour) {
		return "<span style='color:"+colour.toWebHexString()+"; text-shadow: 0px 0px 4px "+colour.getShades()[4]+";'>"+input+"</span>";
	}
	
	public static String formatAsMoney(int money, String tag, Colour amountColour) {
		String tagColour;
//		int copper = Math.abs(money%100);
//		int silver = Math.abs((money%10000)/100);
//		int gold = Math.abs(money/10000);
		
		if(amountColour==null) {
			tagColour = Colour.TEXT.getShades(8)[3];
		} else {
			tagColour = amountColour.toWebHexString();
		}
		
//		return (money<0?"<b style='color:" + tagColour + ";'>-</b>":"")
//				+(gold!=0
//					?"<" + tag + " style='color:" + (amountColour==Colour.TEXT?Colour.TEXT.toWebHexString():Colour.CURRENCY_GOLD.toWebHexString()) + "; padding-right:2px;'>" + getCurrencySymbol() + "</" + tag + ">"
//						+ "<" + tag + " style='color:" + tagColour + ";'>" + Units.number(moneyString) + "</" + tag + ">"
//					:"")
//				+(silver!=0
//					?"<" + tag + " style='color:" + (amountColour==Colour.TEXT?Colour.TEXT.toWebHexString():Colour.CURRENCY_SILVER.toWebHexString()) + "; padding-right:2px;'>" + getCurrencySymbol() + "</" + tag + ">"
//						+ "<" + tag + " style='color:" + tagColour + ";'>" + silver + "</" + tag + ">"
//					:"")
//				+(copper!=0 || money==0
//					?"<" + tag + " style='color:" + (amountColour==Colour.TEXT?Colour.TEXT.toWebHexString():Colour.CURRENCY_COPPER.toWebHexString()) + "; padding-right:2px;'>" + getCurrencySymbol() + "</" + tag + ">"
//						+ "<" + tag + " style='color:" + tagColour + ";'>" + copper + "</" + tag + ">"
//					:"");
		
		return "<" + tag + " style='color:" + (amountColour==Colour.TEXT?Colour.TEXT.toWebHexString():Colour.CURRENCY_GOLD.toWebHexString()) + "; padding-right:2px;'>" + getCurrencySymbol() + "</" + tag + ">"
					+ "<" + tag + " style='color:" + tagColour + ";'>" + Units.number(money) + "</" + tag + ">";
	}
	
	public static String formatAsMoneyUncoloured(int money, String tag) {
		return formatAsMoney(money, tag, Colour.TEXT);
	}
	
	public static String formatAsItemPrice(int money) {
		return formatAsMoney(money, "b");
	}
	
	public static String formatVirginityLoss(String s) {
		return "<p style='text-align:center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'><i>"+s+"</i></p>";
	}
	
	public static String formatTooLoose(String s) {
		return "<p style='text-align:center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'><i>"+s+"</i></p>";
	}
	
	public static String formatStretching(String s) {
		return "<p style='text-align:center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'><i>"+s+"</i></p>";
	}

	public static boolean isVowel(char c) {
		return "AEIOUaeiou".indexOf(c) != -1;
	}

	/**
	 * @return 'a' or 'an'
	 */
	public static String generateSingularDeterminer(String word) {
		if(word.isEmpty()) {
			return "";
		}
		if ((isVowel(word.charAt(0)) || word.charAt(0)=='x' || word.charAt(0)=='X')
				 && !word.startsWith("Unicorn") && !word.startsWith("unicorn")
				 && !word.startsWith("Used") && !word.startsWith("used")) {
			return "an";
		} else {
			return "a";
		}
	}
	
	private static String[] femaleCumNames = new String[] { "juices" };

	public static String getFemaleCumName() {
		return femaleCumNames[Util.random.nextInt(femaleCumNames.length)];
	}

	/**
	 * @return Returns one of the supplied Strings, randomly chosen by using Random's nextInt() method. <b>Automatically removes empty Strings and null entries.</b>
	 */
	public static String returnStringAtRandom(String... strings) {
		List<String> randomStrings = new ArrayList<>();
		
		for(String s : strings) {
			if(s!=null && !s.isEmpty()) {
				randomStrings.add(s);
			}
		}
		
		if(!randomStrings.isEmpty()) {
			return randomStrings.get(Util.random.nextInt(randomStrings.size()));
		} else {
			return "";
		}
	}

	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(String pathName, String tag) {
		return parseFromXMLFile(new ArrayList<>(), pathName, tag, new ArrayList<>());
	}

	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(List<ParserTag> parserTags, String pathName, String tag) {
		return parseFromXMLFile(parserTags, pathName, tag, new ArrayList<>());
	}
	
	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(String pathName, String tag, GameCharacter... specialNPCs) {
		return parseFromXMLFile(new ArrayList<>(), pathName, tag, Util.newArrayListOfValues(specialNPCs));
	}
	
	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(List<ParserTag> parserTags, String pathName, String tag, GameCharacter... specialNPCs) {
		return parseFromXMLFile(parserTags, pathName, tag, Util.newArrayListOfValues(specialNPCs));
	}
	
	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(String pathName, String tag, List<GameCharacter> specialNPC) {
		return parseFromXMLFile(new ArrayList<>(), pathName, tag, specialNPC);
	}
	
	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(List<ParserTag> parserTags, String pathName, String tag, List<GameCharacter> specialNPC) {
		File file = new File("res/txt/"+pathName+".xml");

		List<String> strings = new ArrayList<>();
		
		if (file.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				for(int i=0; i<((Element) doc.getElementsByTagName("dialogue").item(0)).getElementsByTagName("htmlContent").getLength(); i++){
					Element e = (Element) ((Element) doc.getElementsByTagName("dialogue").item(0)).getElementsByTagName("htmlContent").item(i);
					
					if(e.getAttribute("tag").equals(tag)) {
						strings.add(e.getTextContent().replaceFirst("<!\\[CDATA\\[", "").replaceAll("\\]\\]>", ""));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(strings.isEmpty()) {
			return "<p><span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Dialogue for '"+tag+"' not found! (Make sure that the 'res' folder is in the same directory as the .jar or .exe.)</span></p>";

		} else {
			return parse(new ArrayList<>(specialNPC), strings.get(Util.random.nextInt(strings.size())), true, parserTags);
		}
	}
	
	public static String runXmlTest(String pathName) {
		return runXmlTest(pathName, Util.newArrayListOfValues(
				Main.game.getNpc(Lilaya.class),
				Main.game.getNpc(Brax.class),
				Main.game.getNpc(Rose.class),
				Main.game.getNpc(Ralph.class),
				Main.game.getNpc(Nyan.class),
				Main.game.getNpc(Zaranix.class)));
	}
	
	public static String runXmlTest(String pathName, List<GameCharacter> specialNPC) {
		File file = new File(pathName);

		Map<String, String> strings = new HashMap<>();
		
		if (file.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				for(int i=0; i<((Element) doc.getElementsByTagName("dialogue").item(0)).getElementsByTagName("htmlContent").getLength(); i++){
					Element e = (Element) ((Element) doc.getElementsByTagName("dialogue").item(0)).getElementsByTagName("htmlContent").item(i);
					
					strings.put(e.getAttribute("tag"), e.getTextContent().replaceFirst("<!\\[CDATA\\[", "").replaceAll("\\]\\]>", ""));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(strings.isEmpty()) {
			return "<p><span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>No dialogues found! (Make sure that the 'res' folder is in the same directory as the .jar or .exe.)</span></p>";

		} else {
			StringBuilder sb = new StringBuilder();
			for(Entry<String, String> s : strings.entrySet()) {
				sb.append("<p>"
							+ "<b>Dialogue tag: "+s.getKey()+"</b>"
						+ "</p>");
				sb.append(parse(specialNPC, s.getValue(), true)
						+"<br/><br/>");
			}
			return sb.toString();
		}
	}
	
	public static String parse(String input, ParserTag... tags) {
		return parse(new ArrayList<>(), input, tags);
	}
	
	public static String parse(GameCharacter specialNPC, String input, ParserTag... tags) {
		return parse(Util.newArrayListOfValues(specialNPC), input, tags);
	}
	
	public static String parse(GameCharacter specialNPC1, GameCharacter specialNPC2, String input, ParserTag... tags) {
		return parse(Util.newArrayListOfValues(specialNPC1, specialNPC2), input, tags);
	}
	
	private static String speechTarget = "";
	private static boolean suppressOutput = false;

	public static String parse(List<GameCharacter> specialNPC, String input, ParserTag... tags) {
		return parse(specialNPC, input, true, tags);
	}
	

	public static String parse(List<GameCharacter> specialNPC, String input, boolean initialCall, ParserTag... tags) {
		return parse(specialNPC, input, initialCall, Arrays.asList(tags));
	}
	
	/**
	 * Parses supplied text.
	 */
	public static String parse(List<GameCharacter> specialNPC, String input, boolean initialCall, List<ParserTag> tags) {

//		System.out.println("---");
//		try {
//			for(GameCharacter c : specialNPC) {
//				if(c==null) {
//					System.out.println("null");
//				} else {
//					System.out.println(c.getName());
//				}
//			}
//		} catch(Exception ex) {
//		}
		
		parserTags = (tags);
		
		if(Main.game!=null && Main.game.getCurrentDialogueNode()==DebugDialogue.PARSER) {
			input = input.replaceAll("\u200b", "");
		}
		input = input.replaceAll("", "");
		
		if(initialCall) { // Set variables to be parsed on each conditional:
//			specialNPCList = new ArrayList<>(specialNPC);
//			if(specialNPC!=null)
//				System.out.println("s set: "+specialNPC.size()+" | "+specialNPCList.size());
			speechTarget = "";
			parserVariableCalls = new ArrayList<>();
			Matcher matcherVAR = Pattern.compile("(?s)#VAR(.*?)#ENDVAR").matcher(input);
			while(matcherVAR.find()) {
				parserVariableCalls.add(matcherVAR.group().replaceAll("#VAR", "").replaceAll("#ENDVAR", ""));
			}
			input = input.replaceAll("(?s)#VAR(.*?)#ENDVAR", "");
			//  This was removed as it was causing issues with the UI rendering:
//			input = input.replaceAll("(?<!:)//(.*?)\n", "\n"); // Replace comments (but not URLs, like http://)
		}
		
		try {
			StringBuilder resultBuilder = new StringBuilder();
			StringBuilder sb = new StringBuilder();
			int openBrackets = 0;
			int closeBrackets = 0;
			int openArg = 0;
			int closeArg = 0;
//			int conditionalThens = 0;
			int startIndex = 0;
			int endIndex = 0;
			
			String target = null;
			String command = null;
			String arguments = null;
			String conditionalStatement = null;
//			String conditionalTrue = null;
//			String conditionalFalse = null;
			boolean usingConditionalBrackets = false;
			boolean lastConditionalUsedBrackets = false;
			int conditionalOpenBrackets = 0;
			int conditionalCloseBrackets = 0;
			
			Map<String, String> conditionals = null;
			
			boolean conditionalElseFound = false;
			ParseMode currentParseMode = ParseMode.UNKNOWN;
			
			int startedParsingSegmentAt = 0;
			
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				
				if(usingConditionalBrackets) {
					if(input.charAt(i)=='(') {
						conditionalOpenBrackets++;
						
					} else if(input.charAt(i)==')') {
						conditionalCloseBrackets++;
					}
//					System.out.println("o: " +conditionalOpenBrackets);
//					System.out.println("c: " +conditionalCloseBrackets);
				}
				
				if (currentParseMode != ParseMode.REGULAR && currentParseMode != ParseMode.REGULAR_SCRIPT) {
					suppressOutput = false;
					if (c == 'F' && substringMatchesInReverseAtIndex(input, "#IF", i)) {
						if (openBrackets == 0) {
							conditionals = new LinkedHashMap<>(); //TODO
							currentParseMode = ParseMode.CONDITIONAL;
							startIndex = i-2;
							
							for(int j=i+1;j<input.length();j++) {
								if(!Character.isWhitespace(input.charAt(j))) {
									usingConditionalBrackets = input.charAt(j)=='(';
									lastConditionalUsedBrackets = usingConditionalBrackets;
//									System.out.println("usingConditionalBrackets: "+usingConditionalBrackets);
									break;
								}
							}
						} else {
							lastConditionalUsedBrackets = false;
						}
						
						openBrackets++;
						
					} else if (currentParseMode == ParseMode.CONDITIONAL) {
						if(usingConditionalBrackets) {
							//TODO
							if(conditionalOpenBrackets>0 && conditionalOpenBrackets==conditionalCloseBrackets && openBrackets-1==closeBrackets) {
								conditionalStatement = sb.toString().substring(1, sb.length())+")";
								conditionalStatement = conditionalStatement.replaceAll("\n", "").replaceAll("\t", "");
								conditionalStatement = conditionalStatement.trim();
								
//								System.out.println("statement: " +conditionalStatement);
								
								usingConditionalBrackets = false;
								conditionalOpenBrackets = 0;
								conditionalCloseBrackets = 0;
								
								sb.setLength(0);
								
							} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ELSEIF", i) && openBrackets-1==closeBrackets && conditionalStatement!=null) {
								conditionals.put(conditionalStatement, sb.toString().substring(1, sb.length()-6)); // Cut off the '#ELSEIF' at the end of this section.
								
								for(int j=i+1;j<input.length();j++) {
									if(!Character.isWhitespace(input.charAt(j))) {
										usingConditionalBrackets = input.charAt(j)=='(';
										break;
									}
								}
								
								sb.setLength(0);
								
							} else if(c == 'E' && substringMatchesInReverseAtIndex(input, "#ELSE", i) && (i+1==input.length()||input.charAt(i+1)!='I') && openBrackets-1==closeBrackets && conditionalStatement!=null) {
								conditionalElseFound = true;
								conditionals.put(conditionalStatement, sb.toString().substring(1, sb.length()-4)); // Cut off the '#ELSE' at the end of this section.
								sb.setLength(0);
								
							} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ENDIF", i)) {
								closeBrackets++;
								
								if (openBrackets == closeBrackets) {
									if (conditionalElseFound) {
										conditionals.put("true", sb.toString().substring(1, sb.length()-5)); // Cut off the '#ENDIF' at the end.
									} else {
										conditionals.put(conditionalStatement, sb.toString().substring(1, sb.length()-5)); // Cut off the '#ENDIF' at the end of this section.
									}
				
									endIndex = i;
								}
							}
							
						} else {
							if(c == 'N' && substringMatchesInReverseAtIndex(input, "#THEN", i)) {
//								#IF(pc.​​​​​​isFeminine())#THEN#IF!pc.​​​​​​isFeminine()#THEN:3#ELSE>:(#ENDIF#ELSE:(#ENDIF
								// If last conditional was brackets, remove the THEN
								if(lastConditionalUsedBrackets) {
									sb.replace(sb.length()-4, sb.length(), ""); // Reset StringBuilder to exclude #THEN
									i++;
									c = input.charAt(i);
									
								} else if (openBrackets-1==closeBrackets) {
									conditionalStatement = sb.toString().substring(1, sb.length()-4); // Cut off the '#THEN' at the end of the conditional statement.
									conditionalStatement = conditionalStatement.replaceAll("\n", "").replaceAll("\t", "");
									conditionalStatement = conditionalStatement.trim();
									sb.setLength(0);
								}
								
							} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ELSEIF", i) && openBrackets-1==closeBrackets) { //TODO
								conditionals.put(conditionalStatement, sb.toString().substring(1, sb.length()-6)); // Cut off the '#ELSEIF' at the end of this section.

								for(int j=i+1;j<input.length();j++) {
									if(!Character.isWhitespace(input.charAt(j))) {
										usingConditionalBrackets = input.charAt(j)=='(';
										break;
									}
								}
								
								sb.setLength(0);
								
							} else if(c == 'E' && substringMatchesInReverseAtIndex(input, "#ELSE", i) && (i+1==input.length()||input.charAt(i+1)!='I') && openBrackets-1==closeBrackets) {
								conditionalElseFound = true;
	//							conditionalTrue = sb.toString().substring(1, sb.length()-4); // Cut off the '#ELSE' at the end of this section.
								conditionals.put(conditionalStatement, sb.toString().substring(1, sb.length()-4)); // Cut off the '#ELSE' at the end of this section.
								sb.setLength(0);
								
							} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ENDIF", i)) {
								closeBrackets++;
								
								if (openBrackets == closeBrackets) {
									
									if (conditionalElseFound) {
										// conditionalTrue has already been set in the #ELSE catch
	//									conditionalFalse = sb.toString().substring(1, sb.length()-5); // Cut off the '#ENDIF' at the end.
										conditionals.put("true", sb.toString().substring(1, sb.length()-5)); // Cut off the '#ENDIF' at the end.
									} else {
	//									conditionalTrue = sb.toString().substring(1, sb.length()-5); // Cut off the '#ENDIF' at the end.
	//									conditionalFalse = "";
										conditionals.put(conditionalStatement, sb.toString().substring(1, sb.length()-5)); // Cut off the '#ENDIF' at the end of this section.
									}
				
									endIndex = i;
								}
							}
						}
					}
				}
				
				if (currentParseMode != ParseMode.CONDITIONAL) {
					suppressOutput = false;
					if (c == '[') {
						if(openBrackets==0) {
							if(input.charAt(i+1) == '#') {
								currentParseMode = ParseMode.REGULAR_SCRIPT;
							} else {
								currentParseMode = ParseMode.REGULAR;
							}
							startIndex = i;
						}
						
						openBrackets++;
						
					} else if (currentParseMode == ParseMode.REGULAR) {
						if (c =='.' && target == null) {
							target = sb.toString().substring(1); // Cut off the '[' at the start.
							sb.setLength(0);
						
						} else if (c == '(') {
							if(command == null) {
								command = sb.toString().substring(1); // Cut off the '.' at the start.
								if(command.equals("speech") || command.equals("speechNoEffects")) {
									speechTarget = target;
								}
								sb.setLength(0);
							}
							
							openArg++;
							
						} else if (c == ')') {
							closeArg++;
							
							if (openArg == closeArg){
								arguments = sb.toString().substring(1);
							}
							
						} else if (c == ']') {
							closeBrackets++;
							
							if (openBrackets == closeBrackets) {
								if (command == null) {
									command = sb.toString().substring(1); // Cut off the '.' at the start.
									sb.setLength(0);
								}
			
								endIndex = i;
							}
						}
						
					} else if (currentParseMode == ParseMode.REGULAR_SCRIPT) {
						if (c == ']') {
							closeBrackets++;
							
							if (openBrackets == closeBrackets) {
								if(command == null) {
									if(sb.charAt(2)=='#') {
										suppressOutput = true;
										command = sb.toString().substring(3); // Cut off the '[##' at the start.
									} else {
										suppressOutput = false;
										command = sb.toString().substring(2); // Cut off the '[#' at the start.
									}
									sb.setLength(0);
								}
			
								endIndex = i;
							}
						}
					}
				}
				
				if (openBrackets>0 && ((target!=null && command!=null) || (!Character.isWhitespace(c) || c==' '))) {
					sb.append(c);
				}
				
				if (endIndex != 0) {
					resultBuilder.append(input.substring(startedParsingSegmentAt, startIndex));
					String subResult;
					if(currentParseMode == ParseMode.CONDITIONAL) {
//						System.out.println(specialNPC.size());
//						System.out.println(specialNPCList.size());
						subResult = parseConditionalSyntaxNew(specialNPC, conditionals);
					} else {
						subResult = parseSyntaxNew(specialNPC, target, command, arguments, currentParseMode);
					}
					if (openBrackets > 1) {
						subResult = parse(specialNPC, subResult, false, tags);
					}
					if(command!=null && (command.equals("speech") || command.equals("speechNoEffects"))) {
						speechTarget = "";
					}
					resultBuilder.append(subResult);
					startedParsingSegmentAt = endIndex + 1;
					//This is the lamest version of recursion unrolling there is: just reset all your variables by hand.
					sb = new StringBuilder();
					
					openBrackets = 0;
					closeBrackets = 0;
					openArg = 0;
					closeArg = 0;
					startIndex = 0;
					endIndex = 0;
					
					target = null;
					command = null;
					arguments = null;
					conditionalStatement = null;
					conditionals = null;
					conditionalOpenBrackets = 0;
					conditionalCloseBrackets = 0;
					
					conditionalElseFound = false;
					currentParseMode = ParseMode.UNKNOWN;
				}
			}
			
			if (startIndex != 0) {//TODO
				System.err.println("Error in parsing: StartIndex:"+startIndex+" ("+target+", "+command+") - "+input.substring(startIndex, Math.min(input.length()-1, startIndex+20)));
				return input;
			}
			if (startedParsingSegmentAt < input.length()) {
				resultBuilder.append(input.substring(startedParsingSegmentAt, input.length()));
			}
			
			return resultBuilder.toString();
		} catch(Exception ex) {
			System.err.println("Failed to parse: "+input);
			ex.printStackTrace();
			return "";
		}
	}
	
	private static boolean substringMatchesInReverseAtIndex(String input, String stringToMatch, int index) {
		index++;//this fixes my off by one error and I'm too tired to figure out why
		int startingLocation = index - stringToMatch.length();
		if (startingLocation < 0 || index > input.length()) {
			return false;
		}
		return input.substring(startingLocation, index).equals(stringToMatch);
	}
	

	
	public static List<ParserCommand> commandsList = new ArrayList<>();
	public static Map<BodyPartType, List<ParserCommand>> commandsMap = new EnumMap<>(BodyPartType.class);

	private static String[] lastDescriptors = new String[2];
	
	static{

		// Parsing:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("moneyFormat"),
				true,
				false,
				"(amount, tag)",
				"Formats the supplied number as money, using the tag as the html tag."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return UtilText.formatAsMoney(arguments.split(", ")[0], arguments.split(", ")[1]);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moneyFormatUncoloured",
						"moneyFormatNoColour",
						"moneyFormatUncolored",
						"moneyFormatNoColor"),
				true,
				false,
				"(amount, tag)",
				"Formats the supplied number as money, using the tag as the html tag."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return UtilText.formatAsMoneyUncoloured(Integer.valueOf(arguments.split(", ")[0]), arguments.split(", ")[1]);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("money"),
				true,
				false,
				"",
				"Returns how much money the character has."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return String.valueOf(character.getMoney());
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("random"),
				true,
				false,
				"(text1 | text2 | text3)",
				"Returns a random string from the supplied arguments. Nesting 'random' commands inside one another currently does not work."){ //TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				List<String> strings = new ArrayList<>();
				for(String s : arguments.split("\\|")) {
					strings.add(s);
				}
				return Util.randomItemFrom(strings);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("name"),
				true,
				false,
				"(prefix/real name)",
				"Returns the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter."
				+ " If a prefix is provided, the prefix will be appended (with an automatic addition of a space) to non-capitalised names."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge();
					}
					if(!character.isPlayer()) {
						return character.getName(arguments);
					}
				}
				
				if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR);
					
				} else {
					if(target.startsWith("npc") && character.isPlayer()) {
						if(command.startsWith("N")) {
							return "You";
						} else {
							return "you";
						}
					}
					if(character.isPlayerKnowsName() || character.isPlayer()) {
						return character.getName(true);
					}
					return character.getName("the");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("namePos"),
				true,
				false,
				"(prefix/real name)",
				"Returns a possessive version of the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter."
				+ " If you need the actual name (for player third-person reference, or to ignore knowledge of name), pass either ' ' or 'true' as an argument.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge()+"'s";
					}
					return character.getName(arguments) + "'s";
					
				} else if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR)+"'s";
					
				} else {
					if(target.startsWith("npc") && character.isPlayer()) {
						if(command.startsWith("N")) {
							return "Your";
						} else {						 
							return "your";
						}
					}
					if(character.isPlayerKnowsName()) {
						return character.getName(true) + "'s";
					}
					return character.getName("the") + "'s";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("nameIs"),
				true,
				false,
				"(prefix/real name)",
				"Returns a contractive version of the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter."
				+ " If you need the actual player name for third-person reference, pass a space as an argument.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge()+"'s";
					}
					return character.getName(arguments) + "'s";
					
				} else if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR)+"'s";
					
				} else {
					if(target.startsWith("npc") && character.isPlayer()) {
						return "you're";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName(true) + "'s";
					}
					return character.getName("the") + "'s";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("nameIsFull"),
				true,
				false,
				"(prefix/real name)",
				"Returns a contractive version of the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge()+" is";
					}
					return character.getName(arguments) + " is";
					
				} else if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR)+" is";
					
				} else {
					if(target.startsWith("npc") && character.isPlayer()) {
						return "you are";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName(true) + " is";
					}
					return character.getName("the") + " is";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("nameHas"),
				true,
				false,
				"(prefix/real name)",
				"Returns a contractive version of the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter."
				+ " If you need the actual player name for third-person reference, pass a space as an argument.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge()+"'s";
					}
					return character.getName(arguments) + "'s";
					
				} else if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR)+"'s";
					
				} else {
					if(target.startsWith("npc") && character.isPlayer()) {
						return "you've";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName(true) + "'s";
					}
					return character.getName("the") + "'s";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("nameHasFull"),
				true,
				false,
				"(prefix/real name)",
				"Returns a contractive version of the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter, followed by 'has' or 'have'.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge()+" has";
					}
					return character.getName(arguments) + " has";
					
				} else if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR)+" has";
					
				} else {
					if(target.startsWith("npc") && character.isPlayer()) {
						return "you have";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName(true) + " has";
					}
					return character.getName("the") + " has";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"verb",
						"verbPerson"),
				true,
				false,
				"(verb)",
				"Returns a verb in the (probably) correct person for this character. A player might get 'wiggle' where an NPC would get 'wiggles'.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (character.isPlayer()) {
					return arguments;
				} else if (arguments.endsWith("s")
						||arguments.endsWith("x")
						||arguments.endsWith("sh")
						||arguments.endsWith("ch")
						||arguments.endsWith("o")){
					return arguments + "es";
				} else if (arguments.endsWith("y")
						&&!arguments.endsWith("ay")
						&&!arguments.endsWith("ey")
						&&!arguments.endsWith("iy")
						&&!arguments.endsWith("oy")
						&&!arguments.endsWith("uy")) {
					return arguments.substring(0, arguments.length()-1) + "ies";
				}else {
					return arguments + "s";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("surname"),
				true,
				false,
				"",
				"Returns the surname of the target."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getSurname();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fullName",
						"nameFull"),
				true,
				false,
				"(prefix)",
				"Returns the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter. If you want the basic form of the name, pass in a space as an argument."
				+ " If a prefix is provided, the prefix will be appended (with an automatic addition of a space) to non-capitalised names."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge()+(character.getSurname().isEmpty()?"":" "+character.getSurname());
					}
					return character.getName(arguments)+(character.getSurname().isEmpty()?"":" "+character.getSurname());
				} else {
					return character.getName(false)+(character.getSurname().isEmpty()?"":" "+character.getSurname());
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pcName",
						"pcPetName"),
				true,
				false,
				"",
				"Returns the name that this character prefers to call the player by."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPetName(Main.game.getPlayer());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"petName"),
				true,
				false,
				"(target)",
				"Returns the pet name that this character prefers to call the target by. Target argument should be a parsing target tag, such as 'pc', 'npc2', 'lilaya', etc."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				ParserTarget parserTarget = findParserTargetWithTag(arguments.replaceAll("\u200b", ""));
//				System.out.println(target+" | "+arguments);
				if (parserTarget == null) {
					return "petName INVALID_TARGET_NAME("+arguments+")";
				}
				try {
					GameCharacter characterTarget = parserTarget.getCharacter(arguments, specialNPCs);
					if(parseCapitalise) {
						return Util.capitaliseSentence(character.getPetName(characterTarget));
					} else {
						return character.getPetName(characterTarget);
					}
				} catch(Exception ex) {
					System.err.println("PetName parsing failed on "+arguments);
					ex.printStackTrace();
				}
				return "";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"description",
						"desc"),
				true,
				false,
				"",
				"Returns a brief descriptive overview of this character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getDescription();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"age"),
				true,
				false,
				"",
				"Returns the age of this character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getAgeValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"job",
						"jobName"),
				true,
				true,
				"",
				"Returns the name of this character's job."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isSlave()) {
					return character.getSlaveJob().getName(character);
				}
				return character.getHistory().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"jobHourStart",
						"jobStartHour"),
				true,
				true,
				"",
				"Returns the twenty-four hour start time for this character's job. Does not work for slave jobs."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return String.valueOf(character.getHistory().getWorkHourStart());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"jobHourEnd",
						"jobEndHour",
						"jobHourFinish",
						"jobFinishHour"),
				true,
				true,
				"",
				"Returns the twenty-four hour end time for this character's job. Does not work for slave jobs."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return String.valueOf(character.getHistory().getWorkHourEnd());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"jobDayStart",
						"jobStartDay"),
				true,
				true,
				"",
				"Returns the day of the week start for this character's job. Does not work for slave jobs."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHistory().getStartDay().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"jobDayEnd",
						"jobEndDay",
						"jobDayFinish",
						"jobFinishDay"),
				true,
				true,
				"",
				"Returns the day of the week end for this character's job. Does not work for slave jobs."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHistory().getEndDay().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"gender"),
				true,
				true,
				"(coloured)",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Gender gender = character.getGender();
				
				boolean pronoun = parseAddPronoun;
				parseAddPronoun = false;
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return "<span style='color:"+gender.getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(gender.getName())+" ":"")+gender.getName())
											:(pronoun?UtilText.generateSingularDeterminer(gender.getName())+" ":"")+gender.getName())
							+"</span>";
				}
				return parseCapitalise
						?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(gender.getName())+" ":"")+gender.getName())
						:(pronoun?UtilText.generateSingularDeterminer(gender.getName())+" ":"")+gender.getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"genderAppears",
						"genderAppearsAs",
						"appearsAsGender"),
				true,
				true,
				"(coloured)",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Gender gender = character.getAppearsAsGender();
				
				boolean pronoun = parseAddPronoun;
				parseAddPronoun = false;
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return "<span style='color:"+gender.getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(gender.getName())+" ":"")+gender.getName())
											:(pronoun?UtilText.generateSingularDeterminer(gender.getName())+" ":"")+gender.getName())
							+"</span>";
				}
				return parseCapitalise
						?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(gender.getName())+" ":"")+gender.getName())
						:(pronoun?UtilText.generateSingularDeterminer(gender.getName())+" ":"")+gender.getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"affection"),
				true,
				true,
				"(target)",
				"Prints out the name of this character's affection towards the target. e.g. lilaya.affection(pc) would print 'likes' by default"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				ParserTarget parserTarget = findParserTargetWithTag(arguments.replaceAll("\u200b", ""));
				try {
					GameCharacter targetedCharacter = parserTarget.getCharacter(arguments.toLowerCase(), null);
					return character.getAffectionLevel(targetedCharacter).getDescriptor();
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Error: affection command character argument not found! ("+arguments+")</i>";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"relation",
						"relationship"),
				true,
				true,
				"(target)",
				"Prints out the most important name of this character's relation towards the target (it will cut off multiple relation names). e.g. blaze.relation(crystal) would print 'brother'"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				ParserTarget parserTarget = findParserTargetWithTag(arguments.replaceAll("\u200b", ""));
				try {
					GameCharacter targetedCharacter = parserTarget.getCharacter(arguments.toLowerCase(), null);
					Set<Relationship> set = character.getRelationshipsTo(targetedCharacter);
					if(set.size()>=1) {
						return set.iterator().next().getName(character);
					} else {
						return "no relation";
					}
//					return character.getRelationshipStrTo(targetedCharacter);
					
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Error: relation command character argument not found! ("+arguments+")</i>";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"relationFull",
						"relationshipFull"),
				true,
				true,
				"(target)",
				"Prints out all of this character's relations towards the target. e.g. lilaya.relation(pc) might print 'half-sister and aunt'"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				ParserTarget parserTarget = findParserTargetWithTag(arguments.replaceAll("\u200b", ""));
				try {
					GameCharacter targetedCharacter = parserTarget.getCharacter(arguments.toLowerCase(), null);
					return character.getRelationshipStrTo(targetedCharacter);
					
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Error: relation command character argument not found! ("+arguments+")</i>";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"daughter",
						"son"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Relationship.Child.toString(character.getGender().getType());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mother",
						"father"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Relationship.Parent.toString(character.getGender().getType());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mommy",
						"daddy"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "mommy";
				} else {
					return "daddy";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mom",
						"mum",
						"dad"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "mom";
				} else {
					return "dad";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sister",
						"brother"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Relationship.Sibling.toString(character.getGender().getType());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sis",
						"bro"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "sis";
				} else {
					return "bro";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"niece",
						"nephew"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Relationship.Nibling.toString(character.getGender().getType());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mistress",
						"master"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "mistress";
				} else {
					return "master";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"miss",
						"ma'am",
						"sir"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "ma'am";
				} else {
					return "sir";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"heroine",
						"hero"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "heroine";
				} else {
					return "hero";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"miss",
						"ms",
						"mister",
						"mr"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "Miss";
				} else {
					return "Mr.";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"boyfriend",
						"girlfriend"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "girlfriend";
				} else {
					return "boyfriend";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bitch",
						"slut",
						"insult"),
				true,
				true,
				"",
				"Returns a random mean word to describe this person, based on their femininity."){ // R-Rude!
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return UtilText.returnStringAtRandom("bitch", "slut", "cunt", "whore", "skank");
				} else {
					return UtilText.returnStringAtRandom("asshole", "bastard", "fuckface", "fucker");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bitches",
						"sluts",
						"insultPlural"),
				true,
				true,
				"",
				"Returns a random mean pluralised word to describe this person, based on their femininity."){ // R-Rude!
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return UtilText.returnStringAtRandom("bitches", "sluts", "cunts", "whores", "skanks");
				} else {
					return UtilText.returnStringAtRandom("assholes", "bastards", "fuckfaces", "fuckers");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hun",
						"babe"),
				true,
				true,
				"",
				"Returns a random mean word to describe this person, based on their femininity."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "babe";
				} else {
					return "hun";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fullRace",
						"raceFull",
						"femininityRace"),
				true,
				true,
				"(coloured)",
				"Returns a full description of this characters race (including femininity). Pass in 'true' to colour the text."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				boolean pronoun = parseAddPronoun;
				parseAddPronoun = false;
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return "<span style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence(Femininity.getFemininityName(character.getFemininityValue(), pronoun))
									:Femininity.getFemininityName(character.getFemininityValue(), pronoun))+"</span>"
							+ " <span style='color:"+character.getRaceStage().getColour().toWebHexString()+";'>" +character.getRaceStage().getName()+"</span>"
							+ " <span style='color:"+character.getSubspecies().getColour(character).toWebHexString()+";'>" +  getSubspeciesName(character.getSubspecies(),character) + "</span>";
				}
				return (parseCapitalise
						?Util.capitaliseSentence(Femininity.getFemininityName(character.getFemininityValue(), pronoun))
						:Femininity.getFemininityName(character.getFemininityValue(), pronoun))+" "+character.getRaceStage().getName()+" "+getSubspeciesName(character.getSubspecies(),character);
			}
			@Override
			protected String applyDeterminer(String descriptor, String input) {
				return input;
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"race"),
				true,
				true,
				"(coloured)",
				"Returns the name of this characters race. Pass in 'true' to colour the text."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					String name = character.isRaceConcealed()?"unknown race":getSubspeciesName(character.getSubspecies(),character);
					return "<span style='color:"+(character.isRaceConcealed()?Colour.TEXT_GREY:character.getSubspecies().getColour(character)).toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
							+"</span>";
				}
				return getSubspeciesName(character.getSubspecies(),character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"races",
						"racePlural"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return getSubspeciesNamePlural(character.getSubspecies(),character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"raceStage"),
				true,
				true,
				"(coloured)",
				"Returns the name of the 'stage' of characters race (partial, lesser, greater). Pass in 'true' to colour the text."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					String name = character.getRaceStage().getName();
					return "<span style='color:"+character.getRaceStage().getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
							+"</span>";
				}
				return character.getRaceStage().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"preferredBody"),
				false,
				false,
				"(tag)",
				"Returns the description of this character's preferred body."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(!(character instanceof NPC)) {
					return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>preferredBody_not_npc</i>";
				}
				if(arguments!=null) {
					return ((NPC) character).getPreferredBodyDescription(arguments);
				}
				return ((NPC) character).getPreferredBodyDescription("b");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialDescriptor",
						"materialCompositionDescriptor",
						"compositionDescriptor"),
				true,
				false,
				"",
				"Returns the name of the character's BodyMaterial."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getBodyMaterial()==BodyMaterial.FLESH) {
					return "covered in";
				} else {
					return UtilText.returnStringAtRandom(
							"made from",
							"composed entirely of",
							"formed out of",
							"made entirely from");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bodyMaterial",
						"materialName"),
				true,
				true,
				"",
				"Returns the name of the character's BodyMaterial."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialSkin"),
				true,
				true,
				"",
				"Returns a descriptor of the character's skin."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getSkinNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialSkinAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's skin."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getSkinAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialSkinAlt"),
				true,
				true,
				"",
				"Returns a descriptor of the character's skin on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getSkinAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialSkinAltAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's skin on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getSkinAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialOrifice"),
				true,
				true,
				"",
				"Returns a descriptor of the material lining the character's orifices."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getOrificeNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialOrificeAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the material lining the character's orifices."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getOrificeAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialOrificeAlt"),
				true,
				true,
				"",
				"Returns a descriptor of the material lining the character's orifices on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getOrificeAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialOrificeAltAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the material lining the character's orifices on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getOrificeAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHair"),
				true,
				true,
				"",
				"Returns a descriptor of the character's hair."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHairAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's hair."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHairBody",
						"materialBodyHair"),
				true,
				true,
				"",
				"Returns a descriptor of the character's body hair."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairBodyNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHairBodyAdjective",
						"materialBodyHairAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's body hair."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairBodyAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHairAlt"),
				true,
				true,
				"",
				"Returns a descriptor of the character's Hair on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHairAltAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's hair on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFur"),
				true,
				true,
				"",
				"Returns a descriptor of the character's fur."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFurNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFurAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's fur."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFurAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFurAlt"),
				true,
				true,
				"",
				"Returns a descriptor of the character's fur on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFurAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFurAltAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's fur on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFurAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFeather",
						"materialFeathers"),
				true,
				true,
				"",
				"Returns a descriptor of the character's feathers."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFeatherNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFeatherAdjective",
						"materialFeathersAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's feathers."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFeatherAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFeatherAlt",
						"materialFeathersAlt"),
				true,
				true,
				"",
				"Returns a descriptor of the character's feathers on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFeatherAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFeatherAltAdjective",
						"materialFeathersAltAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's fur on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFeatherAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialScale",
						"materialScales"),
				true,
				true,
				"",
				"Returns a descriptor of the character's scales."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getScaleNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialScaleAdjective",
						"materialScalesAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's scales."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getScaleAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialScaleAlt",
						"materialScalesAlt"),
				true,
				true,
				"",
				"Returns a descriptor of the character's scales on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getScaleAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialScaleAltAdjective",
						"materialScalesAltAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's scales on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getScaleAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialShell"),
				true,
				true,
				"",
				"Returns a descriptor of the character's shell."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getShellNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialShellAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's shell."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getShellAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialShellAlt"),
				true,
				true,
				"",
				"Returns a descriptor of the character's shell on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getShellAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialShellAltAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's shell on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getShellAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialKeratin"),
				true,
				true,
				"",
				"Returns a descriptor of the character's keratin."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getKeratinNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialKeratinAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's keratin."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getKeratinAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialKeratinAlt"),
				true,
				true,
				"",
				"Returns a descriptor of the character's keratin on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getKeratinAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialKeratinAltAdjective"),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's keratin on nonhumanoid body parts."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getKeratinAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"femininity",
						"fem",
						"masculinity",
						"mas"),
				true,
				true,
				"(coloured)",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Femininity.valueOf(character.getFemininityValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bodySize"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return BodySize.valueOf(character.getBodySizeValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"muscle"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Muscle.valueOf(character.getMuscleValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bodyShape"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyShape().getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"height"),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHeight().getDescriptor();
			}
		});
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("heightValue"),
				false,
				false,
				"",
				"Returns the character's height in the long, localized format.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.size(character.getHeightValue(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"heightDown",
						"heightUp"),
				true,
				true,
				"target",
				"Returns 'down' if this character is taller than the target, or 'up' if they are smaller than them."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				ParserTarget parserTarget = findParserTargetWithTag(arguments.replaceAll("\u200b", ""));
				try {
					GameCharacter targetedCharacter = parserTarget.getCharacter(arguments.toLowerCase(), null);
					if(targetedCharacter.getHeightValue()<character.getHeightValue()) {
						return "down";
					} else {
						return "up";
					}
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Error: heightDown character argument not found! ("+arguments+")</i>";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"weight"),
				false,
				false,
				"",
				"Returns the character's weight in the long, localized format.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.weight(character.getWeight() / 1000.0, Units.ValueType.NUMERIC, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speech",
						"dialogue",
						"talk",
						"say"),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseSpeech(arguments, character);
				} else {
					return parseSpeech("...", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechMasculine",
						"dialogueMasculine",
						"talkMasculine",
						"sayMasculine"),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.MASCULINE);
				} else {
					return parseNPCSpeech("...", Femininity.MASCULINE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechMasculineStrong",
						"dialogueMasculineStrong",
						"talkMasculineStrong",
						"sayMasculineStrong"),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.MASCULINE_STRONG);
				} else {
					return parseNPCSpeech("...", Femininity.MASCULINE_STRONG);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechAndrogynous",
						"dialogueAndrogynous",
						"talkAndrogynous",
						"sayAndrogynous"),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.ANDROGYNOUS);
				} else {
					return parseNPCSpeech("...", Femininity.ANDROGYNOUS);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechFeminine",
						"dialogueFeminine",
						"talkFeminine",
						"sayFeminine"),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.FEMININE);
				} else {
					return parseNPCSpeech("...", Femininity.FEMININE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechFeminineStrong",
						"dialogueFeminineStrong",
						"talkFeminineStrong",
						"sayFeminineStrong"),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.FEMININE_STRONG);
				} else {
					return parseNPCSpeech("...", Femininity.FEMININE_STRONG);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechNoEffects",
						"dialogueNoEffects",
						"talkNoEffects",
						"sayNoEffects"),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseSpeechNoEffects(arguments, character);
				} else {
					return parseSpeechNoEffects("...", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thought"),
				false,
				false,
				"(thought content)",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseThought(arguments, character);
				} else {
					return parseThought("...", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moan",
						"groan",
						"sob",
						"cry"),
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("sob", "scream", "cry");
						} else {
							return returnStringAtRandom("shout", "cry");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("moan", "squeal", "gasp");
				} else {
					return returnStringAtRandom("groan", "grunt");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moan+",
						"moanD",
						"groan+",
						"groanD",
						"sob+",
						"sobD",
						"cry+",
						"cryD"),
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."
				+ " <b>Expansion of 'moan' command:</b> This command will append a suitable descriptor before the 'moan' noise. e.g. 'lewd squeal', or 'eager grunt'."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("sob", "scream", "cry");
						} else {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("shout", "cry");
						}
						
					} else if(Sex.getSexPace(character)==SexPace.DOM_GENTLE) {
						if(character.isFeminine()) {
							return returnStringAtRandom("soft", "gentle", "quiet") + " " + returnStringAtRandom("moan", "sigh", "gasp");
						} else {
							return returnStringAtRandom("soft", "gentle", "quiet") + " " + returnStringAtRandom("groan", "grunt");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("lewd", "high-pitched", "desperate") + " " + returnStringAtRandom("moan", "squeal", "cry", "gasp");
				} else {
					return returnStringAtRandom("deep", "low", "desperate") + " " + returnStringAtRandom("groan", "grunt");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moanVerb",
						"groanVerb",
						"sobVerb",
						"cryVerb",
						"moansVerb",
						"groansVerb",
						"sobsVerb",
						"criesVerb"),
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isPlayer()) {
					if(Main.game.isInSex()) {
						if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
							if(character.isFeminine()) {
								return returnStringAtRandom("sob", "scream", "cry");
							} else {
								return returnStringAtRandom("shout", "cry");
							}
						}
					}
					
					if(character.isFeminine()) {
						return returnStringAtRandom("moan", "squeal", "gasp");
					} else {
						return returnStringAtRandom("groan", "grunt");
					}
				} else {
					if(Main.game.isInSex()) {
						if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
							if(character.isFeminine()) {
								return returnStringAtRandom("sobs", "screams", "cries");
							} else {
								return returnStringAtRandom("shouts", "cries");
							}
						}
					}
					
					if(character.isFeminine()) {
						return returnStringAtRandom("moans", "squeals", "gasps");
					} else {
						return returnStringAtRandom("groans", "grunts");
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moanVerb+",
						"moanVerbD",
						"groanVerb+",
						"groanVerbD",
						"sobVerb+",
						"sobVerbD",
						"cryVerb+",
						"cryVerbD",
						"moansVerb+",
						"moansVerbD",
						"groansVerb+",
						"groansVerbD",
						"sobsVerb+",
						"sobsVerbD",
						"criesVerb+",
						"criesVerbD"),
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."
				+ " <b>Expansion of 'moan' command:</b> This command will append a suitable descriptor before the 'moan' noise. e.g. 'lewd squeal', or 'eager grunt'."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isPlayer()) {
					if(Main.game.isInSex()) {
						if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
							if(character.isFeminine()) {
								return returnStringAtRandom("miserably", "pathetically") + " " + returnStringAtRandom("sob", "scream", "cry");
							} else {
								return returnStringAtRandom("miserably", "pathetically") + " " + returnStringAtRandom("shout", "cry");
							}
							
						} else if(Sex.getSexPace(character)==SexPace.DOM_GENTLE) {
							if(character.isFeminine()) {
								return returnStringAtRandom("softly", "gently", "quietly") + " " + returnStringAtRandom("moan", "sigh", "cry", "gasp");
							} else {
								return returnStringAtRandom("softly", "gently", "quietly") + " " + returnStringAtRandom("groans", "grunts");
							}
						}
					}
					
					if(character.isFeminine()) {
						return returnStringAtRandom("lewdly", "desperately") + " " + returnStringAtRandom("moan", "squeal", "cry", "gasp");
					} else {
						return returnStringAtRandom("lewdly", "desperately") + " " + returnStringAtRandom("groan", "grunt");
					}
				} else {
					if(Main.game.isInSex()) {
						if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
							if(character.isFeminine()) {
								return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("sobs", "cries");
							} else {
								return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("shouts", "cries");
							}
							
						} else if(Sex.getSexPace(character)==SexPace.DOM_GENTLE) {
							if(character.isFeminine()) {
								return returnStringAtRandom("softly", "gently", "quietly") + " " + returnStringAtRandom("moans", "sighs", "gasps");
							} else {
								return returnStringAtRandom("softly", "gently", "quietly") + " " + returnStringAtRandom("groans", "grunts");
							}
						}
					}
					
					if(character.isFeminine()) {
						return returnStringAtRandom("lewdly", "desperately") + " " + returnStringAtRandom("moans", "squeals", "cries");
					} else {
						return returnStringAtRandom("eagerly", "desperately") + " " + returnStringAtRandom("groans", "grunts", "cries");
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moans",
						"groans",
						"sobs",
						"cries"),
				true,
				false,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will make moans, while if they are masculine, they will make groans."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sobs' or 'cries'."
				+" <b>Provides an appropriate <i>noun</i> version of 'moans'.</b> (Use 'moansVerb' for the verb version.)"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("sobs", "cries");
						} else {
							return returnStringAtRandom("shouts", "cries");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("moans", "squeals", "gasps");
				} else {
					return returnStringAtRandom("groans", "grunts");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moans+",
						"moansD",
						"groans+",
						"groansD",
						"sobs+",
						"sobsD",
						"cries+",
						"criesD"),
				true,
				false,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will make moans, while if they are masculine, they will make groans."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sobs' or 'cries'."
				+ " <b>Expansion of 'moans' command:</b> This command will append a suitable descriptor before the 'moans' noise. e.g. 'lewd squeals', or 'eager grunts'."
				+ " <b>Provides an appropriate <i>noun</i> version of 'moans'.</b> (Use 'moansVerb+' for the verb version.)"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("sobs", "cries");
						} else {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("shouts", "cries");
						}
						
					} else if(Sex.getSexPace(character)==SexPace.DOM_GENTLE) {
						if(character.isFeminine()) {
							return returnStringAtRandom("soft", "gentle", "quiet") + " " + returnStringAtRandom("moans", "sighs", "gasps");
						} else {
							return returnStringAtRandom("soft", "gentle", "quiet") + " " + returnStringAtRandom("groans", "grunts");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("lewd", "high-pitched", "desperate") + " " + returnStringAtRandom("moans", "squeals", "gasps");
				} else {
					return returnStringAtRandom("deep", "eager", "desperate") + " " + returnStringAtRandom("groans", "grunts");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moaning",
						"groaning",
						"sobbing",
						"crying"),
				true,
				false,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("sobbing", "crying");
						} else {
							return returnStringAtRandom("shouting", "protesting");
						}
					}
				}
					
				if(character.isFeminine()) {
					return returnStringAtRandom("moaning", "squealing");
				} else {
					return returnStringAtRandom("groaning", "grunting");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moaning+",
						"moaningD",
						"groaning+",
						"groaningD",
						"sobbing+",
						"sobbingD",
						"crying+",
						"cryingD"),
				true,
				false,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("sobbing", "crying");
						} else {
							return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("shouting", "protesting");
						}
						
					} else if(Sex.getSexPace(character)==SexPace.DOM_GENTLE) {
						if(character.isFeminine()) {
							return returnStringAtRandom("softly", "gently", "quietly") + " " + returnStringAtRandom("moaning", "sighing");
						} else {
							return returnStringAtRandom("softly", "gently", "quietly") + " " + returnStringAtRandom("groaning", "grunting");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("lewdly", "eagerly", "desperately") + " " + returnStringAtRandom("moaning", "squealing");
				} else {
					return returnStringAtRandom("lewdly", "eagerly", "desperately") + " " + returnStringAtRandom("groaning", "grunting");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"eagerly",
						"gently",
						"roughly"),
				true,
				false,
				"(Alternative start string)",
				"Returns an appropriate descriptor based on the pace of the character. The argument is used in place of a descriptor if the pace is SUB_NORMAL."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					List<String> descriptors = new ArrayList<>();
					switch(Sex.getSexPace(character)) {
						case DOM_GENTLE:
							descriptors = Util.newArrayListOfValues("gently", "softly", "lovingly");
							break;
						case DOM_NORMAL:
							descriptors = Util.newArrayListOfValues("happily", "eagerly", "enthusiastically", "desperately");
							break;
						case DOM_ROUGH:
							descriptors = Util.newArrayListOfValues("roughly", "forcefully", "violently", "dominantly");
							break;
						case SUB_EAGER:
							descriptors = Util.newArrayListOfValues("happily", "eagerly", "enthusiastically", "desperately");
							break;
						case SUB_NORMAL:
							if(arguments!=null && !arguments.isEmpty()) {
								return Util.capitaliseSentence(arguments); // Assume start of sentence, so capitalise.
							} else if(Character.isUpperCase(command.charAt(0))) {
								descriptors = Util.newArrayListOfValues("happily", "eagerly", "willingly"); // If start of sentence, need descriptor.
								break;
							} else {
								return "";
							}
						case SUB_RESISTING:
							descriptors = Util.newArrayListOfValues("frantically", "desperately", "maniacally");
							break;
					}
					
					
					for(int i=lastDescriptors.length-1; i>=0; i--) {
						descriptors.remove(lastDescriptors[i]);
						if(i>0) {
							lastDescriptors[i] = lastDescriptors[i-1];
						}
					}
					String returnString = Util.randomItemFrom(descriptors);
					
					lastDescriptors[0] = returnString;

					if(arguments!=null && !arguments.isEmpty()) {
						return returnString+" "+arguments;
					}
					return returnString;
				}
					
				return "eagerly";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scent",
						"smell"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.GENERIC){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return returnStringAtRandom("scent", "scent", "perfume");
				} else {
					return returnStringAtRandom("musk", "musk", "aroma");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scent+",
						"scentD",
						"smell+",
						"smellD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.GENERIC){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return returnStringAtRandom("feminine scent", "feminine perfume", "delicate scent");
				} else {
					return returnStringAtRandom("masculine musk", "masculine aroma");
				}
			}
		});
		
		// Gender parsing:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"girl",
						"boy"),
				true,
				true,
				"",
				"Returns the correct gender version of 'girl' or 'boy' for this character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return Gender.F_V_B_FEMALE.getNounYoung();
				} else {
					return Gender.M_P_MALE.getNounYoung();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"girls",
						"boys"),
				true,
				true,
				"",
				"Returns the correct gender version of 'girls' or 'boys' for this character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return Gender.F_V_B_FEMALE.getNounYoung()+"s";
				} else {
					return Gender.M_P_MALE.getNounYoung()+"s";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"woman",
						"man"),
				true,
				true,
				"",
				"Returns the correct gender version of 'woman' or 'man' for this character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine())
					return Gender.F_V_B_FEMALE.getNoun();
				else
					return Gender.M_P_MALE.getNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"female",
						"male"),
				true,
				true,
				"",
				"Returns the correct gender version of 'female' or 'male' for this character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine())
					return Gender.F_V_B_FEMALE.getName();
				else
					return Gender.M_P_MALE.getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"feminine",
						"masculine"),
				true,
				true,
				"",
				"Returns the correct gender version of 'feminine' or 'masculine' for this character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine())
					return "feminine";
				else
					return "masculine";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"wife",
						"husband"),
				true,
				true,
				"",
				"Returns the correct gender version of 'wife' or 'husband' for this character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "wife";
				} else {
					return "husband";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"have",
						"has"),
				true,
				true,
				"",
				"Returns the correct version of 'has' for this character (has or have)."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "have";
				} else {
					return "has";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"is",
						"are"),
				true,
				true,
				"",
				"Returns the correct version of 'is' for this character (is or are)."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "are";
				} else {
					return "is";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"does",
						"do"),
				true,
				true,
				"",
				"Returns the correct version of 'does' for this character (do or does)."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "do";
				} else {
					return "does";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"were",
						"was"),
				true,
				true,
				"",
				"Returns the correct version of 'was' for this character (was or were)."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "were";
				} else {
					return "was";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"her",
						"his",
						"herPos",
						"herHis",
						"hisPos",
						"hisHer"),
				true,
				true,
				"(real pronoun)",
				"Returns the correct gender-specific possessive pronoun for this character (your, her, his). By default, returns 'your' for player character."
				+ " If you need the actual third-person player character pronoun, pass a space as an argument."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "your";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getPossessiveBeforeNoun();
						} else {
							return GenderPronoun.POSSESSIVE_BEFORE_NOUN.getFeminine();
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getPossessiveBeforeNoun();
						} else {
							return GenderPronoun.POSSESSIVE_BEFORE_NOUN.getMasculine();
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hers",
						"hersHis",
						"hisHers"),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "yours";
				} else {
					if(character.isFeminine()) {
						return Gender.F_V_B_FEMALE.getPossessiveAlone();
					} else {
						return Gender.M_P_MALE.getPossessiveAlone();
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"nameHers",
						"nameHersHis",
						"nameHisHers"),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "yours";
				} else {
					if(character.isPlayerKnowsName()) {
						return character.getName(true) + "'s";
					}
					return character.getName("the") + "'s";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"him",
						"herPro",
						"herHim",
						"himHer"),
				true,
				true,
				"(real pronoun)",
				"Returns the correct pronoun for this character (you, him, her). By default, returns 'you' for player character."
				+ " If you need the regular third-person player character pronoun, pass a space as an argument."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "you";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getThirdPerson();
						} else {
							return GenderPronoun.THIRD_PERSON.getFeminine();
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getThirdPerson();
						} else {
							return GenderPronoun.THIRD_PERSON.getMasculine();
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"she",
						"sheHe",
						"he",
						"heShe"),
				true,
				true,
				"(real pronoun)",
				"Returns the correct pronoun for this character (you, she, he). By default, returns 'you' for player character."
				+ " If you need the regular third-person player character pronoun, pass a space as an argument."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "you";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getFeminine();
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getMasculine();
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sheIs",
						"sheHeIs",
						"heIs",
						"heSheIs"),
				true,
				true,
				"(real pronoun)",
				"Returns the correct gender-specific pronoun contraction for this character (you're, she's, he's). By default, returns 'you're' for player character."
				+ " If you need the regular third-person player character pronoun contraction, pass a space as an argument."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "you're";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getSecondPerson() + "'s";
						} else {
							return GenderPronoun.SECOND_PERSON.getFeminine() + "'s";
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getSecondPerson() + "'s";
						} else {
							return GenderPronoun.SECOND_PERSON.getMasculine() + "'s";
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sheIsFull",
						"sheHeIsFull",
						"heIsFull",
						"heSheIsFull"),
				true,
				true,
				"(real pronoun)",
				"Returns the correct gender-specific pronoun contraction for this character (you are, she is, he is). By default, returns 'you are' for player character."
				+ " If you need the regular third-person player character pronoun contraction, pass a space as an argument."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "you are";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getSecondPerson() + " is";
						} else {
							return GenderPronoun.SECOND_PERSON.getFeminine() + " is";
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getSecondPerson() + " is";
						} else {
							return GenderPronoun.SECOND_PERSON.getMasculine() + " is";
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sheHas",
						"sheHeHas",
						"heHas",
						"heSheHas"),
				true,
				true,
				"(real pronoun)",
				"Returns the correct gender-specific pronoun contraction for this character (you've, she's, he's). By default, returns 'you've' for player character."
				+ " If you need the regular third-person player character pronoun contraction, pass a space as an argument."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "you've";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getSecondPerson() + "'s";
						} else {
							return GenderPronoun.SECOND_PERSON.getFeminine() + "'s";
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getSecondPerson() + "'s";
						} else {
							return GenderPronoun.SECOND_PERSON.getMasculine() + "'s";
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sheHasFull",
						"sheHeHasFull",
						"heHasFull",
						"heSheHasFull"),
				true,
				true,
				"",
				"Returns the correct gender-specific pronoun contraction for this character (you have, she has, he has). By default, returns 'you have' for player character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(target.startsWith("npc") && character.isPlayer()) {
					return "you have";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getSecondPerson() + " has";
						} else {
							return GenderPronoun.SECOND_PERSON.getFeminine() + " has";
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getSecondPerson() + " has";
						} else {
							return GenderPronoun.SECOND_PERSON.getMasculine() + " has";
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"herself",
						"himself"),
				true,
				true,
				"",
				"Returns correct gender-specific reflexive pronoun for this character (yourself, herself, himself). By default, returns 'yourself' for player character."
						+ " If you need the regular reflexive player character pronoun, pass a space as an argument."){
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
							return "yourself";
						} else {
							if(character.isFeminine()) {
								if(character.isPlayer()) {
									return Gender.F_V_B_FEMALE.getThirdPerson()+"self";
								} else {
									return GenderPronoun.THIRD_PERSON.getFeminine()+"self";
								}
							} else {
								if(character.isPlayer()) {
									return Gender.M_P_MALE.getThirdPerson()+"self";
								} else {
									return GenderPronoun.THIRD_PERSON.getMasculine()+"self";
								}
							}
						}
					}
				});
		
		// Clothing:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"topClothing",
						"highestClothing",
						"highClothing"),
				true,
				true,
				"(bodyPart)",
				"Returns the name of the highest piece of clothing that's blocking the area passed in as an argument. Possible arguments are the CoverableArea values."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					CoverableArea area = CoverableArea.valueOf(arguments);
					if(character.getHighestZLayerCoverableArea(area)==null) {
						return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_"+area+"</i>";
					} else {
						try {
							return character.getHighestZLayerCoverableArea(area).getName();
						} catch(Exception ex) {
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
						}
					}
					
				} else {
					return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
				}
			}
			
			@Override
			public String getArgumentExample() {
				return "pussy";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bottomClothing",
						"lowestClothing",
						"lowClothing"),
				true,
				true,
				"(bodyPart)",
				"Returns the name of the lowest piece of clothing that's blocking the area passed in as an argument. Possible arguments are the CoverableArea values."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					CoverableArea area = CoverableArea.valueOf(arguments);
					if(character.getLowestZLayerCoverableArea(area)==null) {
						return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_"+area+"</i>";
					} else {
						try {
							return character.getLowestZLayerCoverableArea(area).getName();
						} catch(Exception ex) {
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
						}
					}
					
				} else {
					return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
				}
			}
			
			@Override
			public String getArgumentExample() {
				return "pussy";
			}
		});
		
		
		// Styles & non-character parsing:
		

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"evening",
						"morning"),
				true,
				true,
				"",
				"Returns 'morning' in the morning, 'afternoon' in the afternoon, or 'evening' in the evening, and night."){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				int hour = Main.game.getHourOfDay();
				
				if(hour<4) {
					return "evening";
				} else if(hour<12) {
					return "morning";
				}else if(hour<17) {
					return "afternoon";
				}
				
				return "evening";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bold",
						"b"),
				false,
				false,
				"(text to make bold)",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null)
					return "<b>"+arguments+"</b>";
				else
					return "<b>...</b>";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"italic",
						"italics",
						"i"),
				false,
				false,
				"(text to italicise)",
				"Description of method"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null)
					return "<i>"+arguments+"</i>";
				else
					return "<i>...</i>";
			}
		});
		

		List<String> commandNames = new ArrayList<>();
		for(Colour c : Colour.values()) {
			if(c.getFormattingNames()!=null) {
				
				commandNames = new ArrayList<>();
				for(String s : c.getFormattingNames()) {
					commandNames.add("color"+Util.capitaliseSentence(s));
					commandNames.add("c"+Util.capitaliseSentence(s));
					commandNames.add("colour"+Util.capitaliseSentence(s));
				}
				
				commandsList.add(new ParserCommand(
						commandNames,
						false,
						false,
						"(text to colour)",
						"Description of method"){//TODO
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null)
							return "<span style='color:"+c.toWebHexString()+";'>"+arguments+"</span>";
						else
							return "<span style='color:"+c.toWebHexString()+";'>...</span>";
					}
				});
				
				commandNames = new ArrayList<>();
				for(String s : c.getFormattingNames()) {
					commandNames.add("bold"+Util.capitaliseSentence(s));
					commandNames.add("b"+Util.capitaliseSentence(s));
				}
				
				commandsList.add(new ParserCommand(
						commandNames,
						false,
						false,
						"(text to make bold)",
						"Description of method"){//TODO
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null)
							return "<b style='color:"+c.toWebHexString()+";'>"+arguments+"</b>";
						else
							return "<b style='color:"+c.toWebHexString()+";'>...</b>";
					}
				});
				
				commandNames = new ArrayList<>();
				for(String s : c.getFormattingNames()) {
					commandNames.add("italic"+Util.capitaliseSentence(s));
					commandNames.add("italics"+Util.capitaliseSentence(s));
					commandNames.add("i"+Util.capitaliseSentence(s));
				}
				
				commandsList.add(new ParserCommand(
						commandNames,
						false,
						false,
						"(text to italicise)",
						"Description of method"){//TODO
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null)
							return "<i style='color:"+c.toWebHexString()+";'>"+arguments+"</i>";
						else
							return "<i style='color:"+c.toWebHexString()+";'>...</i>";
					}
				});
				
				commandNames = new ArrayList<>();
				for(String s : c.getFormattingNames()) {
					commandNames.add("glow"+Util.capitaliseSentence(s));
					commandNames.add("glowing"+Util.capitaliseSentence(s));
					commandNames.add("g"+Util.capitaliseSentence(s));
				}
				
				commandsList.add(new ParserCommand(
						commandNames,
						false,
						false,
						"(text to glow)",
						"Description of method"){//TODO
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null)
							return applyGlow(arguments, c);
						else
							return "<i>...</i>";
					}
				});
			}
		}


		// Units

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"size"),
				true,
				false,
				"(cm to convert)",
				"Returns the converted size in the localized, singular form. " +
						"If no argument is given, returns the small singular length unit.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "centimetre" : "inch";
				}
				return Units.size(Double.valueOf(arguments), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sizes",
						"sizePlural"),
				true,
				false,
				"(cm to convert)",
				"Returns the converted size in the long, localized form. " +
						"If no argument is given, returns the small plural length unit.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "centimetres" : "inches";
				}
				return Units.size(Double.valueOf(arguments), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sizeShort"),
				true,
				false,
				"(cm to convert)",
				"Returns the converted size in the localized, singular form. " +
						"If no argument is given, returns the small singular length unit.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "cm" : Units.INCH_SYMBOL;
				}
				return Units.size(Double.valueOf(arguments), Units.ValueType.NUMERIC, Units.UnitType.SHORT);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lSize",
						"largeSize"),
				true,
				false,
				"(cm to convert)",
				"Returns the converted size in the localized, singular text form. " +
						"If no argument is given, returns the large singular length unit.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "metre" : "foot";
				}
				return Units.size(Double.valueOf(arguments), Units.ValueType.TEXT, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lSizes",
						"largeSizePlural"),
				true,
				false,
				"(cm to convert)",
				"Returns the converted size in the localized text form. " +
						"If no argument is given, returns the large plural length unit.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "metres" : "feet";
				}
				return Units.size(Double.valueOf(arguments), Units.ValueType.TEXT, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mainWeapon",
						"primaryWeapon",
						"mainWeapon1",
						"primaryWeapon1"),
				true,
				true,
				"",
				"Returns the name of the main weapon equipped by the character in their primary arm row. Returns 'fists' if no weapon is equipped."){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getMainWeapon(0)==null) {
					return "fists";
				} else {
					return character.getMainWeapon(0).getName();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mainWeapon2",
						"primaryWeapon2"),
				true,
				true,
				"",
				"Returns the name of the main weapon equipped by the character in their secondary arm row (for characters who have more than one pair of arms). Returns 'fists' if no weapon is equipped."){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getMainWeapon(1)==null) {
					return "fists";
				} else {
					return character.getMainWeapon(1).getName();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mainWeapon3",
						"primaryWeapon3"),
				true,
				true,
				"",
				"Returns the name of the main weapon equipped by the character in their tertiary arm row (for characters who have more than one pair of arms). Returns 'fists' if no weapon is equipped."){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getMainWeapon(2)==null) {
					return "fists";
				} else {
					return character.getMainWeapon(2).getName();
				}
			}
		});

		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"offhandWeapon",
						"secondaryWeapon",
						"offhandWeapon0",
						"secondaryWeapon0"),
				true,
				true,
				"",
				"Returns the name of the offhand weapon equipped by the character in their primary arm row. Returns 'fists' if no weapon is equipped."){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getOffhandWeapon(0)==null) {
					return "fists";
				} else {
					return character.getOffhandWeapon(0).getName();
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"offhandWeapon2",
						"secondaryWeapon2"),
				true,
				true,
				"",
				"Returns the name of the offhand weapon equipped by the character in their primary arm row (for characters who have more than one pair of arms). Returns 'fists' if no weapon is equipped."){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getOffhandWeapon(1)==null) {
					return "fists";
				} else {
					return character.getOffhandWeapon(1).getName();
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"offhandWeapon3",
						"secondaryWeapon3"),
				true,
				true,
				"",
				"Returns the name of the offhand weapon equipped by the character in their tertiary arm row (for characters who have more than one pair of arms). Returns 'fists' if no weapon is equipped."){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getOffhandWeapon(2)==null) {
					return "fists";
				} else {
					return character.getOffhandWeapon(2).getName();
				}
			}
		});
		
		
		
		
		
		// Body parts:
		
		// Add standard parsing for all types:

		addStandardParsingCommands(
				Util.newArrayListOfValues("antenna"),
				Util.newArrayListOfValues("antennae"),
				BodyPartType.ANTENNA);
				
		addStandardParsingCommands(
				Util.newArrayListOfValues("arm"),
				Util.newArrayListOfValues("arms"),
				BodyPartType.ARM);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("ass", "butt"),
				Util.newArrayListOfValues("asses", "butts"),
				BodyPartType.ASS);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("anus", "asshole"),
				Util.newArrayListOfValues("anuses", "assholes"),
				BodyPartType.ANUS);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("breast", "tit", "boob", "chest"),
				Util.newArrayListOfValues("breasts", "tits", "boobs"),
				BodyPartType.BREAST);

		addStandardParsingCommands(
				Util.newArrayListOfValues("nipple", "teat"),
				Util.newArrayListOfValues("nipples", "teats"),
				BodyPartType.NIPPLES);

		addStandardParsingCommands(
				Util.newArrayListOfValues("milk"),
				Util.newArrayListOfValues("milks"), // milks? Really?
				BodyPartType.MILK);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("breastCrotch", "titCrotch", "boobCrotch", "crotchBreast", "crotchTit", "crotchBoob", "udder"),
				Util.newArrayListOfValues("breastsCrotch", "titsCrotch", "boobsCrotch", "crotchBreasts", "crotchTits", "crotchBoobs", "udders"),
				BodyPartType.BREAST_CROTCH);

		addStandardParsingCommands(
				Util.newArrayListOfValues("nippleCrotch", "teatCrotch", "crotchNipple", "crotchTeat", "udderTeat", "uddersTeat"),
				Util.newArrayListOfValues("nipplesCrotch", "teatsCrotch", "crotchNipples", "crotchTeats", "udderTeats", "uddersTeats"),
				BodyPartType.NIPPLES_CROTCH);

		addStandardParsingCommands(
				Util.newArrayListOfValues("milkCrotch", "crotchMilk", "udderMilk", "uddersMilk"),
				Util.newArrayListOfValues("milksCrotch", "crotchMilks", "udderMilks", "uddersMilks"), // milks? Really?
				BodyPartType.MILK_CROTCH);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("ear"),
				Util.newArrayListOfValues("ears"),
				BodyPartType.EAR);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("eye"),
				Util.newArrayListOfValues("eyes"),
				BodyPartType.EYE);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("face"),
				Util.newArrayListOfValues("faces"),
				BodyPartType.FACE);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("mouth"),
				Util.newArrayListOfValues("mouths"),
				BodyPartType.MOUTH);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("hairSingular", "feather"),
				Util.newArrayListOfValues("hair", "feathers"),
				BodyPartType.HAIR);

		addStandardParsingCommands(
				Util.newArrayListOfValues("horn"),
				Util.newArrayListOfValues("horns"),
				BodyPartType.HORN);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("leg"),
				Util.newArrayListOfValues("legs"),
				BodyPartType.LEG);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("penis", "cock", "dick"),
				Util.newArrayListOfValues("penises", "cocks", "dicks"),
				BodyPartType.PENIS);

		addStandardParsingCommands(
				Util.newArrayListOfValues("secondPenis", "secondCock", "secondDick", "penis2", "cock2", "dick2"),
				Util.newArrayListOfValues("secondPenises", "secondCocks", "secondDicks", "penises2", "cocks2", "dicks2"),
				BodyPartType.SECOND_PENIS);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("testicle", "ball"),
				Util.newArrayListOfValues("testicles", "balls"),
				BodyPartType.TESTICLES);

		addStandardParsingCommands(
				Util.newArrayListOfValues("cum"),
				Util.newArrayListOfValues("cums"), // :s
				BodyPartType.CUM);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("skin"),
				Util.newArrayListOfValues("skinPlural"),
				BodyPartType.SKIN);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("tail"),
				Util.newArrayListOfValues("tails"),
				BodyPartType.TAIL);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("tentacle"),
				Util.newArrayListOfValues("tentacles"),
				BodyPartType.TENTACLE);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("tongue"),
				Util.newArrayListOfValues("tongues"),
				BodyPartType.TONGUE);

		addStandardParsingCommands(
				Util.newArrayListOfValues("clit", "clitoris"),
				Util.newArrayListOfValues("clits", "clitorises"),
				BodyPartType.CLIT);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("vagina", "pussy", "cunt"),
				Util.newArrayListOfValues("vaginas", "pussies", "cunts"),
				BodyPartType.VAGINA);

		addStandardParsingCommands(
				Util.newArrayListOfValues("girlcum", "gcum"),
				Util.newArrayListOfValues("girlcums", "gcums"),
				BodyPartType.GIRL_CUM);

		addStandardParsingCommands(
				Util.newArrayListOfValues("wing"),
				Util.newArrayListOfValues("wings"),
				BodyPartType.WING);
		
		
		// Special body parts:
		
		// Arm:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"armRows"),
				true,
				true,
				"",
				"Returns a descriptor in the form of the character number of arms. i.e. If the character has 1 arm row it will return 'a pair of', for 2, 'two pairs of', and 3, 'three pairs of'.",
				BodyPartType.ARM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getArmRows()==1) {
					return "a pair of";
				} else if(character.getArmRows()==2) {
					return "two pairs of";
				} else {
					return "three pairs of";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hand"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getArmType().getHandsNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hand+",
						"handD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getArmType().getHandsDescriptor(character), character.getArmType().getHandsNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hands"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getArmType().getHandsNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hands+",
						"handsD"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getArmType().getHandsDescriptor(character), character.getArmType().getHandsNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"finger"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getArmType().getFingersNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"finger+",
						"fingerD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getArmType().getFingersDescriptor(character), character.getArmType().getFingersNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fingers"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getArmType().getFingersNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fingers+",
						"fingersD"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getArmType().getFingersDescriptor(character), character.getArmType().getFingersNamePlural(character));
			}
		});
		
	
		// Ass:
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assCapacity",
						"assholeCapacity"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getAssStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assElasticity",
						"assholeElasticity"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assWetness",
						"assholeWetness"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssWetness().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assCloaca"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return "cloaca";
				} else {
					return character.getBody().getAss().getNameSingular(character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assCloaca+"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return "slit-like cloaca";
				} else {
					return applyDescriptor(character.getBody().getAss().getDescriptor(character), character.getBody().getAss().getNameSingular(character));
				}
			}
		});


		
		// Hips: TODO rough
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hipSkin",
						"hipsSkin"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return getSkinName(character.getSkinType(), character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hipSkin+",
						"hipsSkin+"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return getSkinNameWithDescriptor(character.getSkinType(), character.getCovering(character.getSkinType().getBodyCoveringType(character)), character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hip",
						"hips"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "hips";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hip+",
						"hipD",
						"hips+",
						"hipsD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getHipSize().getDescriptor(), "hips");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hipSize",
						"hipsSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHipSize().getDescriptor();
			}
		});
		
		// Breasts:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastSize",
						"breastsSize",
						"titSize",
						"titsSize",
						"boobSize",
						"boobsSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastShape",
						"breastsShape",
						"titShape",
						"titsShape",
						"boobShape",
						"boobsShape"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastShape().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"nippleSize",
						"nipplesSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleSize().getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"areolaSize",
						"areolaeSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAreolaeSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cupSize",
						"cups",
						"breastCups",
						"breastsCups",
						"titCups",
						"titsCups",
						"boobCups",
						"boobsCups"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastSize().getCupSizeName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastCapacity",
						"breastsCapacity",
						"titCapacity",
						"titsCapacity",
						"boobCapacity",
						"boobsCapacity"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getNippleStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastElasticity",
						"breastsElasticity",
						"titElasticity",
						"titsElasticity",
						"boobElasticity",
						"boobsElasticity"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastRows",
						"nippleRows"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getBreastRows()==1) {
					return "pair of";
				} else {
					return Util.intToString(character.getBreastRows())+" pairs of";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"totalBreasts",
						"totalBreastCount"),
				true,
				false,
				"",
				"Returns the total number of breasts that this character has. i.e. Breast rows * 2",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getBreastRows()*2);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"nipplesPerBreast"),
				true,
				false,
				"",
				"Returns the number of nipples that this character has on each breast. Will usually just be one.",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getNippleCountPerBreast());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"totalNipples",
						"totalNippleCount"),
				true,
				false,
				"",
				"Returns the total number of nipples that this character has. i.e. Breast rows * 2 * nipples per row",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getBreastRows()*2*character.getNippleCountPerBreast());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lactation"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastMilkStorage().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"milkRegen",
						"milkRegeneration"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastLactationRegeneration().getName();
			}
		});
		
		
		// Crotch Breasts:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastCrotchPair",
						"crotchBoobPair",
						"crotchBoobsPair",
						"udderSet"),
				true,
				true,
				"",
				"Returns 'pair', or 'set', based on whether the character's crotch boobs are udders or not",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchShape()==BreastShape.UDDERS?"set":"pair";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderSize",
						"uddersSize",
						"crotchBreastSize",
						"crotchBreastsSize",
						"crotchTitSize",
						"crotchTitsSize",
						"crotchBoobSize",
						"crotchBoobsSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderShape",
						"uddersShape",
						"crotchBreastShape",
						"crotchBreastsShape",
						"crotchTitShape",
						"crotchTitsShape",
						"crotchBoobShape",
						"crotchBoobsShape"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchShape().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderNippleSize",
						"udderNipplesSize",
						"uddersNippleSize",
						"uddersNipplesSize",
						"crotchNippleSize",
						"crotchNipplesSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleCrotchSize().getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderAreolaSize",
						"udderAreolaeSize",
						"uddersAreolaSize",
						"uddersAreolaeSize",
						"crotchBoobsAreolaSize",
						"crotchAreolaSize",
						"crotchAreolaeSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAreolaeCrotchSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderCups",
						"udderCups",
						"uddersCups",
						"uddersCups",
						"crotchBreastCups",
						"crotchBreastsCups",
						"crotchTitCups",
						"crotchTitsCups",
						"crotchBoobCups",
						"crotchBoobsCups",
						"crotchBoobCupSize",
						"crotchBoobsCupSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchSize().getCupSizeName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderCapacity",
						"uddersCapacity",
						"crotchBreastCapacity",
						"crotchBreastsCapacity",
						"crotchTitCapacity",
						"crotchTitsCapacity",
						"crotchBoobCapacity",
						"crotchBoobsCapacity"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getNippleCrotchStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderElasticity",
						"uddersElasticity",
						"crotchBreastElasticity",
						"crotchBreastsElasticity",
						"crotchTitElasticity",
						"crotchTitsElasticity",
						"crotchBoobElasticity",
						"crotchBoobsElasticity"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleCrotchElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderRows",
						"uddersRows",
						"crotchBoobRows",
						"crotchBoobsRows",
						"crotchBreastRows",
						"crotchNippleRows"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getBreastCrotchRows()==1) {
					return "pair of";
				} else {
					return Util.intToString(character.getBreastCrotchRows())+" pairs of";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"totalUdders",
						"totalUdderCount",
						"totalCrotchBoobs",
						"totalCrotchBoobCount"),
				true,
				false,
				"",
				"Returns the total number of breasts that this character has. i.e. Breast rows * 2",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getBreastCrotchRows()*2);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderNipplesPerBreast",
						"uddersNipplesPerBreast",
						"crotchBoobNipplesPerBreast",
						"crotchBoobsNipplesPerBreast",
						"crotchNipplesPerBreast"),
				true,
				false,
				"",
				"Returns the number of nipples that this character has on each breast. Will usually just be one.",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getNippleCrotchCountPerBreast());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"totalUdderNipples",
						"totalUddersNipples",
						"totalCrotchNipples",
						"totalCrotchNippleCount"),
				true,
				false,
				"",
				"Returns the total number of nipples that this character has. i.e. Breast rows * 2 * nipples per row",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getBreastCrotchRows()*2*character.getNippleCrotchCountPerBreast());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderLactation",
						"uddersLactation",
						"crotchLactation"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchMilkStorage().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderMilkRegen",
						"uddersMilkRegen",
						"crotchMilkRegen",
						"crotchMilkRegeneration"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchLactationRegeneration().getName();
			}
		});
		
		
		// Eyes:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"eyePairs",
						"eyesPairs",
						"eyeRows",
						"eyesRows"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getEyeDeterminer();
			}
		});
		
		// Face:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tongueLength",
						"tongueSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTongueLength().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"nose"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNoseNameSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"noses"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNoseNamePlural();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lipSize",
						"lipsSize"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLipSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lip"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLipsNameSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lip+",
						"lipD"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getLipsDescriptor(), character.getLipsNameSingular());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lips"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLipsNamePlural();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lips+",
						"lipsD"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getLipsDescriptor(), character.getLipsNamePlural());
			}
		});
		
		// Hair:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hairLength"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.HAIR){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHairLength().getDescriptor();
			}
		});
		
		// Horns:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hornSize",
						"hornsSize",
						"hornLength",
						"hornsLength"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.HORN){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return HornLength.getHornLengthFromInt(character.getHornLength()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hornRows"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.HORN){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getHornRows());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hornsPerRow"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.HORN){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getHornsPerRow());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hornsDeterminer"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.HORN){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHornDeterminer();
			}
		});
		
		// Leg:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thighs"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "thighs";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thighs+",
						"thighsD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "thighs";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"foot"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(parserTags.contains(ParserTag.SEX_DESCRIPTION)) {
					if(!character.isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						try {
							AbstractClothing clothing = character.getHighestZLayerCoverableArea(CoverableArea.FEET);
							return UtilText.returnStringAtRandom(
									clothing.getSlotEquippedTo()==InventorySlot.FOOT
										?"footwear"
										:clothing.getName()+"-clad-"+character.getLegType().getFootNameSingular(character),
									clothing.getName(),
									clothing.getName());
						} catch(Exception ex) {
						}
					}
				}
				return character.getLegType().getFootNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"foot+",
						"footD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(parserTags.contains(ParserTag.SEX_DESCRIPTION)) {
					if(!character.isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						try {
							AbstractClothing clothing = character.getHighestZLayerCoverableArea(CoverableArea.FEET);
							return UtilText.returnStringAtRandom(
									clothing.getSlotEquippedTo()==InventorySlot.FOOT
									?"footwear"
									:clothing.getName()+"-clad-"+character.getLegType().getFootNameSingular(character),
									clothing.getName(),
									clothing.getName());
						} catch(Exception ex) {
						}
					}
				}
				return applyDescriptor(character.getLegType().getFootDescriptor(character), character.getLegType().getFootNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"feet"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(parserTags.contains(ParserTag.SEX_DESCRIPTION)) {
					if(!character.isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						try {
							AbstractClothing clothing = character.getHighestZLayerCoverableArea(CoverableArea.FEET);
							return UtilText.returnStringAtRandom(
									clothing.getSlotEquippedTo()==InventorySlot.FOOT
										?"footwear"
										:clothing.getName()+"-clad-"+character.getLegType().getFootNamePlural(character),
									clothing.getNamePlural(),
									clothing.getNamePlural());
						} catch(Exception ex) {
						}
					}
				}
				return character.getLegType().getFootNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"feet+",
						"feetD"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(parserTags.contains(ParserTag.SEX_DESCRIPTION)) {
					if(!character.isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						try {
							AbstractClothing clothing = character.getHighestZLayerCoverableArea(CoverableArea.FEET);
							return UtilText.returnStringAtRandom(
									clothing.getSlotEquippedTo()==InventorySlot.FOOT
										?"footwear"
										:clothing.getName()+"-clad-"+character.getLegType().getFootNamePlural(character),
									clothing.getNamePlural(),
									clothing.getNamePlural());
						} catch(Exception ex) {
						}
					}
				}
				return applyDescriptor(character.getLegType().getFootDescriptor(character), character.getLegType().getFootNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toes"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegType().getToeNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toes+",
						"toesD"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getLegType().getToeDescriptor(character), character.getLegType().getToeNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"footjob"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegType().getFootType().getFootjobName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"footStructure"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getFootStructure().getName();
			}
		});
		
		// Penis:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethra",
						"cockUrethra",
						"urethraPenis",
						"urethraCock"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "urethra";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethra+",
						"cockUrethra+",
						"urethraPenis+",
						"urethraCock+",
						"penisUrethraD",
						"cockUrethraD",
						"urethraPenisD",
						"urethraCockD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getPenisUrethraDescriptor(), "urethra");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cumAmount",
						"cumProduction",
						"jizzAmount",
						"jizzProduction",
						"cumStorage"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPenisCumStorage().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cumMl",
						"cumMeasurement"),
				false,
				false,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return String.valueOf(character.getPenisRawCumStorageValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"ballsCount",
						"ballCount",
						"testiclesCount"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return (parseCapitalise
						?Util.capitaliseSentence(Util.intToString(character.getPenisNumberOfTesticles()))
						:Util.intToString(character.getPenisNumberOfTesticles()));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"ballSize",
						"ballsSize",
						"testicleSize",
						"testiclesSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTesticleSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisHead",
						"cockHead",
						"dickHead",
						"cockTip"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBody().getPenis().getPenisHeadName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisHead+",
						"penisHeadD",
						"cockHead+",
						"cockHeadD",
						"dickHead+",
						"dickHeadD",
						"cockTip+",
						"cockTipD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getBody().getPenis().getPenisHeadDescriptor(character), character.getBody().getPenis().getPenisHeadName(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisSize",
						"cockSize",
						"dickSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPenisSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisGirth",
						"cockGirth",
						"dickGirth"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPenisGirth().getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("penisValue"),
				false,
				false,
				"",
				"Returns the localized, formatted size of the penis with long singular units.",
				BodyPartType.PENIS) {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.size(character.getPenisRawSizeValue(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"urethra"),
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "urethra";
			}
		});
		
		// Second penis:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"secondPenisHead",
						"secondCockHead",
						"secondDickHead",
						"penis2Head",
						"cock2Head",
						"dick2Head"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBody().getSecondPenis().getPenisHeadName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"secondPenisHead+",
						"secondCockHead+",
						"secondDickHead+",
						"penis2Head+",
						"cock2Head+",
						"dick2Head+",
						"secondPenisHeadD",
						"secondCockHeadD",
						"secondDickHeadD",
						"penis2HeadD",
						"cock2HeadD",
						"dick2HeadD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getBody().getSecondPenis().getPenisHeadDescriptor(character), character.getBody().getSecondPenis().getPenisHeadName(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"secondPenisSize",
						"secondCockSize",
						"secondDickSize",
						"penis2Size",
						"cock2Size",
						"dick2Size"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getSecondPenisSize().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"secondPenisValue",
						"penis2Value"),
				false,
				false,
				"",
				"Returns the localized, formatted size of the second penis with long units.",
				BodyPartType.PENIS) {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.size(character.getSecondPenisRawSizeValue(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"secondUrethra"),
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "urethra";
			}
		});
		
		// Tail:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailCount",
						"tailsCount"),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTailDeterminer();
			}
		});
		
		// Vagina:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethra",
						"vaginaUrethra",
						"vaginalUrethra",
						"urethraVagina",
						"urethraVaginal"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "urethra";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethra+",
						"vaginaUrethra+",
						"vaginalUrethra+",
						"urethraVagina+",
						"urethraVaginal+",
						"vaginaUrethraD",
						"pussyUrethraD",
						"vaginalUrethraD",
						"urethraVaginaD",
						"urethraVaginalD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getVaginaUrethraDescriptor(), "urethra");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"vaginaCapacity",
						"pussyCapacity",
						"cuntCapacity"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getVaginaStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"vaginaElasticity",
						"pussyElasticity",
						"cuntElasticity"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"vaginaWetness",
						"pussyWetness",
						"cuntWetness"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaWetness().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"labiaSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaLabiaSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"labia"),
				true,
				true,
				"",
				"A name for the supplied character's labia.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "labia";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"labia+",
						"labiaD"),
				true,
				true,
				"",
				"A name for the supplied character's labia, with a descriptor appended before it.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				List<String> descriptors = Util.newArrayListOfValues(character.getVaginaLabiaSize().getName());
				
				if(character.getVaginaCovering()!=null) {
					descriptors.add(character.getVaginaLabiaSize().getName());
					descriptors.add(character.getCovering(character.getVaginaCovering()).getPrimaryColour().getName());
				}
				
				return Util.randomItemFrom(descriptors)+" labia";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitSize",
						"clitorisSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaClitorisSize().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitSizeValue",
						"clitorisSizeValue"),
				true,
				true,
				"",
				"Returns the localized, formatted size of the clitoris with long units.",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.size(character.getVaginaRawClitorisSizeValue(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitGirth",
						"clitorisGirth"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getClitorisGirth().getName();
			}
		});
		
		// Wings:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"wingSize",
						"wingsSize"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.WING){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getWingSize().getName();
			}
		});
		
		// Legs:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"legConfiguration",
						"legShape"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getName();
			}
		});
		
		// Eyes:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"irisShape",
						"irisesShape"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getIrisShape().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"irisFullDescription",
						"irisesFullDescription"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(character.getEyeCovering())
						.getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"irisColour",
						"irisColor",
						"irisesColour",
						"irisesColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(character.getEyeCovering())
						.getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"irisColourPrimary",
						"irisColorPrimary",
						"irisesColourPrimary",
						"irisesColorPrimary",
						"irisPrimaryColour",
						"irisPrimaryColor",
						"irisesPrimaryColour",
						"irisesPrimaryColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(character.getEyeCovering())
						.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"irisColourSecondary",
						"irisColorSecondary",
						"irisesColourSecondary",
						"irisesColorSecondary",
						"irisSecondaryColour",
						"irisSecondaryColor",
						"irisesSecondaryColour",
						"irisesSecondaryColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(character.getEyeCovering())
						.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pupilShape",
						"pupilsShape"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPupilShape().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pupilFullDescription",
						"pupilsFullDescription"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pupilColour",
						"pupilColor",
						"pupilsColour",
						"pupilsColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pupilColourPrimary",
						"pupilColorPrimary",
						"pupilsColourPrimary",
						"pupilsColorPrimary",
						"pupilPrimaryColour",
						"pupilPrimaryColor",
						"pupilsPrimaryColour",
						"pupilsPrimaryColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pupilColourSecondary",
						"pupilColorSecondary",
						"pupilsColourSecondary",
						"pupilsColorSecondary",
						"pupilSecondaryColour",
						"pupilSecondaryColor",
						"pupilsSecondaryColour",
						"pupilsSecondaryColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scleraFullDescription",
						"scleraeFullDescription"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scleraColour",
						"scleraColor",
						"scleraeColour",
						"scleraeColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scleraColourPrimary",
						"scleraColorPrimary",
						"scleraeColourPrimary",
						"scleraeColorPrimary",
						"scleraPrimaryColour",
						"scleraPrimaryColor",
						"scleraePrimaryColour",
						"scleraePrimaryColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scleraColourSecondary",
						"scleraColorSecondary",
						"scleraeColourSecondary",
						"scleraeColorSecondary",
						"scleraSecondaryColour",
						"scleraSecondaryColor",
						"scleraeSecondaryColour",
						"scleraeSecondaryColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		// Tail:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailHead",
						"tailTip"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTailType().getTailTipName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailHead+",
						"tailHeadD",
						"tailTip+",
						"tailTipD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getTailType().getTailTipDescriptor(character), character.getTailType().getTailTipName(character));
			}
		});
		
		commandsList.sort(Comparator.nullsLast(Comparator.comparing(ParserCommand::getRelatedBodyPart)));
		
		for(BodyPartType bpt : BodyPartType.values()) {
			commandsMap.put(bpt, new ArrayList<>());
		}
		for(ParserCommand cmd : commandsList) {
			commandsMap.get(cmd.getRelatedBodyPart()).add(cmd);
		}
	}

	private static String parseSyntaxNew(List<GameCharacter> specialNPCs, String target, String command, String arguments, ParseMode currentParseMode) {
		GameCharacter character;
		
		if(currentParseMode == ParseMode.REGULAR_SCRIPT) {
			if(engine==null) {
				initScriptEngine();
			}
			if(!specialNPCs.isEmpty()) {
				for(int i = 0; i<specialNPCs.size(); i++) {
					if(i==0) {
						engine.put("npc", specialNPCs.get(i));
					}
					engine.put("npc"+(i+1), specialNPCs.get(i));
				}
			} else {
				try { // Getting the target NPC can throw a NullPointerException, so if it does (i.e., there's no NPC suitable for parsing), just catch it and carry on.
					engine.put("npc", ParserTarget.NPC.getCharacter("npc", specialNPCs));
				} catch(Exception ex) {
					System.err.println("Parsing error: Could not initialise npc");
				}
			}
			
			try {
				if(suppressOutput) {
					engine.eval(command);
					return "";
				}
				return String.valueOf(engine.eval(command));
				
			} catch (ScriptException e) {
				System.err.println("Scripting parsing error: "+command);
				System.err.println(e.getMessage());
//				e.printStackTrace();
				return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>(Error in script parsing!)</i>";
			}
			
		} else if(Main.game.isStarted()) { //TODO test:
			if(engine==null) {
				initScriptEngine();
			}
			if(!specialNPCs.isEmpty()) {
				for(int i = 0; i<specialNPCs.size(); i++) {
					if(i==0) {
						engine.put("npc", specialNPCs.get(i));
					}
					engine.put("npc"+(i+1), specialNPCs.get(i));
				}
			} else {
				try { // Getting the target NPC can throw a NullPointerException, so if it does (i.e., there's no NPC suitable for parsing), just catch it and carry on.
					engine.put("npc", ParserTarget.NPC.getCharacter("npc", specialNPCs));
				} catch(Exception ex) {
//					System.err.println("Parsing error: Could not initialise npc 2");
				}
			}
		}
		
		// Non-script parsing:
		
		parseCapitalise = false;
		parseAddPronoun = false;
		
		if(command.split("_").length==2) {
			if(Character.isUpperCase(command.split("_")[0].charAt(0)))
				parseCapitalise = true;
			command = command.split("_")[1];
			parseAddPronoun = true;
		} 
			
		if(Character.isUpperCase(command.charAt(0))) {
			parseCapitalise = true;
		}
		
		ParserTarget parserTarget = findParserTargetWithTag(target.replaceAll("\u200b", ""));
		if (parserTarget == null) {
			return "INVALID_TARGET_NAME("+target+")";
		}
		
		try {
			character = parserTarget.getCharacter(target.toLowerCase(), specialNPCs);
		} catch(Exception ex) {
			ex.printStackTrace();
			return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Error: parserTarget.getCharacter() not found! ("+target+")</i>";
		}
		
		// Commands with arguments:
		ParserCommand cmd = findCommandWithTag(command.replaceAll("\u200b", ""));
		if (cmd == null) {
			return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>command_unknown</i>";
		}


		String output = cmd.parse(specialNPCs, command, arguments, target, character);
		if(suppressOutput) {
			return "";
		}
		parseCapitalise = parseCapitalise && cmd.isAllowsCapitalisation();
		parseAddPronoun = parseAddPronoun && cmd.isAllowsPronoun();
		
		if(parseAddPronoun) {
			output = (UtilText.isVowel(output.charAt(0))?"an ":"a ")+output;
		}
		if (parseCapitalise) {
			return Util.capitaliseSentence(output);
		}
		return output;
	}

	private static ParserTarget findParserTargetWithTag(String target) {
		for(ParserTarget parserTarget : ParserTarget.values()) {
			for(String s : parserTarget.getTags()) {
				if(s.toLowerCase().equals(target.toLowerCase())) {
					return parserTarget;
				}
			}
		}
		return null;
	}

	private static ParserCommand findCommandWithTag(String command) {
		for(ParserCommand cmd : commandsList) {
			for(String s : cmd.getTags()) {
				if(command.equalsIgnoreCase(s)) {
					return cmd;
				}
			}
		}
		return null;
	}
	
	public static void resetParsingEngine() {
		engine = null;
		specialParsingStrings = new ArrayList<>();
	}
	
	/**
	 * @param string The String to add.
	 * @return Size of specialParsingStrings list.
	 */
	public static int addSpecialParsingString(String string, boolean clearListBeforeAdding) {
		if(clearListBeforeAdding) {
			clearSpecialParsingStrings();
		}
		specialParsingStrings.add(string);
		initScriptEngine();
		return specialParsingStrings.size();
	}
	
	public static void clearSpecialParsingStrings() {
		specialParsingStrings = new ArrayList<>();
	}
	
	public static void initScriptEngine() {
		
		NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
		// http://hg.openjdk.java.net/jdk8/jdk8/nashorn/rev/eb7b8340ce3a
		engine = factory.getScriptEngine("-strict", "--no-java", "--no-syntax-extensions", "-scripting");
		
//		ScriptEngineManager manager = new ScriptEngineManager();
//		engine = manager.getEngineByName("javascript");
		
		for(ParserTarget target : ParserTarget.values()) {
			if(target!=ParserTarget.STYLE && target!=ParserTarget.UNIT && target!=ParserTarget.NPC && target!=ParserTarget.COMPANION) {
				for(String tag : target.getTags()) {
					engine.put(tag, target.getCharacter(tag, null));
				}
			}
		}
		engine.put("game", Main.game);
		engine.put("sex", Main.sexEngine);
		engine.put("properties", Main.getProperties());
		
		for(int i=0; i<specialParsingStrings.size(); i++) {
			engine.put("SPECIAL_PARSE_"+i, specialParsingStrings.get(i));
		}
		
		// Enums:
		for(Race race : Race.values()) {
			engine.put("RACE_"+race.toString(), race);
		}
		for(RaceStage raceStage : RaceStage.values()) {
			engine.put("RACE_STAGE_"+raceStage.toString(), raceStage);
		}
		for(Subspecies subspecies : Subspecies.values()) {
			engine.put("SUBSPECIES_"+subspecies.toString(), subspecies);
		}
		for(LegConfiguration legConf : LegConfiguration.values()) {
			engine.put("LEG_CONFIGURATION_"+legConf.toString(), legConf);
		}
		for(FootStructure footStructure : FootStructure.values()) {
			engine.put("FOOT_STRUCTURE_"+footStructure.toString(), footStructure);
		}
		for(BodyMaterial material : BodyMaterial.values()) {
			engine.put("BODY_MATERIAL_"+material.toString(), material);
		}
		for(Fetish f : Fetish.values()) {
			engine.put(f.toString(), f);
		}
		for(FetishDesire fetishDesire : FetishDesire.values()) {
			engine.put("FETISH_DESIRE_"+fetishDesire.toString(), fetishDesire);
		}
		for(Occupation occ : Occupation.values()) {
			engine.put("OCCUPATION_"+occ.toString(), occ);
		}
		for (OccupationTag occupationTag : OccupationTag.values()) {
			engine.put("OCCUPATION_TAG_" + occupationTag.toString(), occupationTag);
		}
		for(AbstractPerk p : Perk.getAllPerks()) {
			engine.put("PERK_"+Perk.getIdFromPerk(p), p);
		}
		for(StatusEffect sa : StatusEffect.values()) {
			engine.put("SE_"+sa.toString(), sa);
		}
		for(Attribute att : Attribute.values()) {
			engine.put("ATTRIBUTE_"+att.toString(), att);
		}
		for(AbstractClothingType ct : ClothingType.getAllClothing()) {
			engine.put("CT_"+ClothingType.getIdFromClothingType(ct), ct);
		}
		for(CoverableArea ca : CoverableArea.values()) {
			engine.put("CA_"+ca.toString(), ca);
		}
		for(InventorySlot is : InventorySlot.values()) {
			engine.put("IS_"+is.toString(), is);
		}
		for(DamageType damageType : DamageType.values()) {
			engine.put("DAMAGE_TYPE_"+damageType.toString(), damageType);
		}
		for(ItemTag it : ItemTag.values()) {
			engine.put("ITEM_TAG_"+it.toString(), it);
		}
		for(Season season : Season.values()) {
			engine.put("SEASON_"+season.toString(), season);
		}
		for(Weather w : Weather.values()) {
			engine.put("WEATHER_"+w.toString(), w);
		}
		for(DialogueFlagValue flag : DialogueFlagValue.values()) {
			engine.put("FLAG_"+flag.toString(), flag);
		}
		for(NPCFlagValue flag : NPCFlagValue.values()) {
			engine.put("NPC_FLAG_"+flag.toString(), flag);
		}
		for(Occupation occupation : Occupation.values()) {
			engine.put("OCCUPATION_"+occupation.toString(), occupation);
		}
		for(SlavePermissionSetting permission : SlavePermissionSetting.values()) {
			engine.put("SLAVE_PERMISSION_SETTING_"+permission.toString(), permission);
		}
		for(QuestLine questLine : QuestLine.values()) {
			engine.put("QUEST_LINE_"+questLine.toString(), questLine);
		}
		for(Quest quest : Quest.values()) {
			engine.put("QUEST_"+quest.toString(), quest);
		}
		for(SexualOrientation orientation : SexualOrientation.values()) {
			engine.put("ORIENTATION_"+orientation.toString(), orientation);
		}
		for(Femininity femininity : Femininity.values()) {
			engine.put("FEMININITY_"+femininity.toString(), femininity);
		}
		for(AffectionLevel affectionLevel : AffectionLevel.values()) {
			engine.put("AFFECTION_"+affectionLevel.toString(), affectionLevel);
		}
		for(AffectionLevelBasic affectionLevelBasic : AffectionLevelBasic.values()) {
			engine.put("AFFECTION_BASIC_"+affectionLevelBasic.toString(), affectionLevelBasic);
		}
		for(ObedienceLevel obedienceLevel : ObedienceLevel.values()) {
			engine.put("OBEDIENCE_"+obedienceLevel.toString(), obedienceLevel);
		}
		for(ObedienceLevelBasic obedienceLevelBasic : ObedienceLevelBasic.values()) {
			engine.put("OBEDIENCE_BASIC_"+obedienceLevelBasic.toString(), obedienceLevelBasic);
		}
		for(Relationship relationship : Relationship.values()) {
			engine.put("RELATIONSHIP_"+relationship.toString(), relationship);
		}
		for(FurryPreference furryPreference : FurryPreference.values()) {
			engine.put("FURRY_PREF_"+furryPreference.toString(), furryPreference);
		}
		for(ForcedTFTendency tfTendency : ForcedTFTendency.values()) {
			engine.put("FORCED_TF_"+tfTendency.toString(), tfTendency);
		}
		for(ForcedFetishTendency fetishTendency : ForcedFetishTendency.values()) {
			engine.put("FORCED_FETISH_"+fetishTendency.toString(), fetishTendency);
		}
		for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
			engine.put("ORIFICE_"+orifice.toString(), orifice);
		}
		for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
			engine.put("PENETRATION_"+penetration.toString(), penetration);
		}
		for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
			engine.put("PLACE_UPGRADE_"+upgrade.toString(), upgrade);
		}
		engine.put("RND", Util.random);

		
		
		//TODO static methods don't work unless initialised like so:
//		try {
//			engine.eval("var sex = Java.type('com.lilithsthrone.game.sex.Sex');");
//		} catch (ScriptException e) {
//			e.printStackTrace();
//		}
		// This requires the flag "--no-java" to be removed from the engine init line up above, and I'm not sure if that's a good idea or not...

//		engine.put("Packages.com.lilithsthrone.utils.Util");
		
//		StringBuilder sb = new StringBuilder();
//		for(Entry<String, Object> entry : engine.getBindings(ScriptContext.ENGINE_SCOPE).entrySet()) {
//			sb.append(entry.getKey()+", "+entry.getValue().toString()+"\n");
//		}
//		System.out.println(sb.toString());
	}
	
//	private static String parseConditionalSyntaxNew(String conditionalStatement, String conditionalTrue, String conditionalFalse) {
//		if(engine==null) {
//			initScriptEngine();
//		}
//		
//		if(!specialNPCList.isEmpty()) {
////			System.out.println("List size: "+specialNPCList.size());
//			for(int i = 0; i<specialNPCList.size(); i++) {
//				if(i==0) {
//					engine.put("npc", specialNPCList.get(i));
//				}
//				engine.put("npc"+(i+1), specialNPCList.get(i));
////				System.out.println("Added: npc"+(i+1));
//			}
//		} else {
//			try { // Getting the target NPC can throw a NullPointerException, so if it does (i.e., there's no NPC suitable for parsing), just catch it and carry on.
//				engine.put("npc", ParserTarget.NPC.getCharacter("npc"));
////				System.out.println("specialNPCList is empty");
//			} catch(Exception ex) {
////				System.err.println("Parsing error 2: Could not initialise npc");
//			}
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		
//		for(String s : parserVariableCalls) {
//			sb.append(s+";");
//		}
//		sb.append(conditionalStatement);
//		
//		conditionalStatement = sb.toString();
//		
//		try {
//			if(Main.game.getCurrentDialogueNode()==DebugDialogue.PARSER) { //TODO what
//				if((boolean) engine.eval(conditionalStatement)) {
////					return conditionalTrue;
//					return UtilText.parse(specialNPCList, conditionalTrue, false);
//				}
//			} else if((boolean) engine.eval(conditionalStatement)){
////				return conditionalTrue;
//				return UtilText.parse(specialNPCList, conditionalTrue, false);
//			}
//			
////			return conditionalFalse;
//			return UtilText.parse(specialNPCList, conditionalFalse, false);
//			
//		} catch (ScriptException e) {
//			System.err.println("Conditional parsing error: "+conditionalStatement);
//			System.err.println(e.getMessage());
////			e.printStackTrace();
//			return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>(Error in conditional parsing!)</i>";
//		}
//	}
	
	private static String parseConditionalSyntaxNew(List<GameCharacter> specialNPCs, Map<String, String> conditionals) {
		if(engine==null) {
			initScriptEngine();
		}
		
		if(!specialNPCs.isEmpty()) {
//			System.out.println("List size: "+specialNPCList.size());
			for(int i = 0; i<specialNPCs.size(); i++) {
				if(i==0) {
					engine.put("npc", specialNPCs.get(i));
				}
				engine.put("npc"+(i+1), specialNPCs.get(i));
//				System.out.println("Added: npc"+(i+1));
			}
			
		} else {
			try { // Getting the target NPC can throw a NullPointerException, so if it does (i.e., there's no NPC suitable for parsing), just catch it and carry on.
				engine.put("npc", ParserTarget.NPC.getCharacter("npc", specialNPCs));
//				System.out.println("specialNPCList is empty");
			} catch(Exception ex) {
//				System.err.println("Parsing error 2: Could not initialise npc");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(Entry<String, String> entry : conditionals.entrySet()) {
			sb.setLength(0);

			for(String s : parserVariableCalls) {
				sb.append(s+";");
			}
			sb.append(entry.getKey());
			
			String conditionalStatement = sb.toString();
			
			try {
				if((boolean) engine.eval(conditionalStatement)){
					return UtilText.parse(specialNPCs, entry.getValue(), false);
				}
				
			} catch (ScriptException e) {
				System.err.println("Conditional parsing (from Map) error: "+conditionalStatement);
				System.err.println(e.getMessage());
				e.printStackTrace();
				return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>(Error in conditional parsing!)</i>";
			}
		}
		
		return "";
	}
	
	
	/**
	 * Adds standard commands related to the baseCommand.<br/>
	 * Commands include:<br/>
	 * Race<br/>
	 * Skin<br/>
	 * Skin+<br/>
	 * Colour<br/>
	 * name<br/>
	 * name+<br/>
	 * names<br/>
	 * names+<br/>
	 */
	private static void addStandardParsingCommands(List<String> tags, List<String> tagsPlural, BodyPartType bodyPart) {
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "Race"),
				true,
				true,
				"",
				"Returns the name of the race that's associated with this body part. Race is *not* gender-specific (i.e. will return 'wolf-morph', not 'wolf-girl').",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				try {
					return getBodyPartFromType(bodyPart,character).getType().getRace().getName(getBodyPartFromType(bodyPart,character).isBestial(character));
				} catch(Exception ex) {
					return "null_body_part";
				}
			}
		});
		
//		commandsList.add(new ParserCommand(
//				getModifiedTags(tags, tagsPlural, "Races"),
//				true,
//				true,
//				"",
//				"Returns the plural name of the race that's associated with this body part. Race is *not* gender-specific (i.e. will return 'wolf-morph', not 'wolf-girl').",
//				bodyPart){
//			@Override
//			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
//				return getBodyPartFromType(bodyPart).getType().getRace().getName();
//			}
//		});

		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "Skin"),
				true,
				true,
				"",
				"Returns the name of the 'skin' covering this body part. This could, in fact, be quite different from skin, for example: fur, keratin, scales, slime, etc.",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return getSkinName(getBodyPartFromType(bodyPart,character).getType(), character);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "Skin+", "SkinD"),
				true,
				true,
				"",
				"Returns the 'skin' covering this body part, just as the 'Skin' command does, but this adds a descriptor to the start (if one is available).",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));
				
				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				return getSkinNameWithDescriptor(getBodyPartFromType(bodyPart,character).getType(), coveringHandledFreckles, character);
			}
		});

		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "FullDescription"),
				true,
				true,
				"true If you want this description's colour names to be coloured.",
				"Returns a full description of this part.",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));

				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				if(coveringHandledFreckles==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return coveringHandledFreckles.getFullDescription(character, true);
					}
				}
				return coveringHandledFreckles.getFullDescription(character, false);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "FullDescriptionColour", "FullDescriptionColor", "FullDescriptionColoured", "FullDescriptionColored"),
				true,
				true,
				"",
				"Returns a full description of this part.",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));

				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				if(coveringHandledFreckles==null) {
					return "";
				}
				return coveringHandledFreckles.getFullDescription(character, true);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "Colour", "Color"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Returns the colour of whatever 'skin' is covering this body part.",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));

				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				if(coveringHandledFreckles==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return coveringHandledFreckles.getColourDescriptor(character, true, parseCapitalise);
					}
				}
				return coveringHandledFreckles.getColourDescriptor(character, false, parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "ColourPrimary", "ColorPrimary", "PrimaryColour", "PrimaryColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Returns the primary colour of whatever 'skin' is covering this body part.",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));

				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				if(coveringHandledFreckles==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return coveringHandledFreckles.getPrimaryColourDescriptor(true);
					}
				}
				return coveringHandledFreckles.getPrimaryColourDescriptor(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "ColourSecondary", "ColorSecondary", "SecondaryColour", "SecondaryColor"),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Returns the secondary colour of whatever 'skin' is covering this body part.",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));

				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				if(coveringHandledFreckles==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return coveringHandledFreckles.getSecondaryColourDescriptor(true);
					}
				}
				return coveringHandledFreckles.getSecondaryColourDescriptor(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "ColourHex", "ColorHex"),
				true,
				true,
				"",
				"Returns the hex code for the colour of whatever 'skin' is covering this body part.",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getCovering(getBodyPartFromType(bodyPart,character).getBodyCoveringType(character))==null) {
					return "";
				}
				return character.getCovering(getBodyPartFromType(bodyPart,character).getBodyCoveringType(character)).getPrimaryColour().toWebHexString();
			}
		});
		
		commandsList.add(new ParserCommand(
				tags,
				true,
				true,
				"",
				"Returns the basic, singular name for this body part.",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return getBodyPartFromType(bodyPart,character).getNameSingular(character);
			}
		});

		commandsList.add(new ParserCommand(
				tagsPlural,
				true,
				true,
				"",
				"Returns the basic, plural name for this body part.",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart,character).getDeterminer(character), getBodyPartFromType(bodyPart,character).getNamePlural(character));
					
				} else {
					return getBodyPartFromType(bodyPart,character).getNamePlural(character);
				}
			}
		});

		commandsList.add(new ParserCommand(
				getModifiedTags(tags, null, "+", "D"),
				true,
				true,
				"",
				"Returns the singular name for this body part, with a descriptor appended to the start (if one is available).",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), getBodyPartFromType(bodyPart,character).getNameSingular(character));
			}
		});

		commandsList.add(new ParserCommand(
				getModifiedTags(null, tagsPlural, "+", "D"),
				true,
				true,
				"",
				"Returns the plural name for this body part, with a descriptor appended to the start (if one is available).",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart,character).getDeterminer(character), applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), getBodyPartFromType(bodyPart,character).getNamePlural(character)));
					
				} else {
					return applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), getBodyPartFromType(bodyPart,character).getNamePlural(character));
				}
			}
		});
	}
	
	/**
	 * Helper method for generating tags with specified endings.
	 */
	private static List<String> getModifiedTags(List<String> tags, List<String> tagsPlural, String... ending) {
		List<String> modifiedTags = new ArrayList<>();
		
		if(tags!=null)
			for(String s : tags) {
				for(String e : ending)
					modifiedTags.add(s+e);
			}
		
		if(tagsPlural!=null)
			for(String s : tagsPlural) {
				for(String e : ending)
					modifiedTags.add(s+e);
			}
		
		return modifiedTags;
	}
	
	
	private static BodyPartInterface getBodyPartFromType(BodyPartType type, GameCharacter character) {
		switch(type){
			case ANTENNA:
				return character.getBody().getAntenna();
			case ARM:
				return character.getBody().getArm();
			case ASS:
				return character.getBody().getAss();
			case ANUS:
				return character.getBody().getAss().getAnus();
			case BREAST:
				return character.getBody().getBreast();
			case MILK:
				return character.getBody().getBreast().getMilk();
			case NIPPLES:
				return character.getBody().getBreast().getNipples();
			case BREAST_CROTCH:
				return character.getBody().getBreastCrotch();
			case NIPPLES_CROTCH:
				return character.getBody().getBreastCrotch().getNipples();
			case MILK_CROTCH:
				return character.getBody().getBreastCrotch().getMilk();
			case EAR:
				return character.getBody().getEar();
			case EYE:
				return character.getBody().getEye();
			case FACE:
				return character.getBody().getFace();
			case MOUTH:
				return character.getBody().getFace().getMouth();
			case HAIR:
				return character.getBody().getHair();
			case HORN:
				return character.getBody().getHorn();
			case LEG:
				return character.getBody().getLeg();
			case PENIS:
				return character.getCurrentPenis();
			case SECOND_PENIS:
				return character.getBody().getSecondPenis();
			case TESTICLES:
				return character.getCurrentPenis().getTesticle();
			case CUM:
				return character.getCurrentPenis().getTesticle().getCum();
			case SKIN:
				return character.getBody().getSkin();
			case TAIL:
				return character.getBody().getTail();
			case TENTACLE:
				return character.getBody().getTentacle();
			case TONGUE:
				return character.getBody().getFace().getTongue();
			case CLIT:
				return character.getBody().getVagina().getClitoris();
			case VAGINA:
				return character.getBody().getVagina();
			case GIRL_CUM:
				return character.getBody().getVagina().getGirlcum();
			case WING:
				return character.getBody().getWing();
			case GENERIC:
				return null;
		}
		return null;
	}
	
	
	/**
	 * Some methods might return a null or empty string for a descriptor. This method accounts for that, applying a descriptor if one is available and then returning the descriptor + name combination.
	 */
	private static String applyDescriptor(String descriptor, String name) {
		if(descriptor==null)
			return name;
		
		return (descriptor.length() > 0 ? descriptor + " " : "") + name;
	}
	
	/**
	 * Some methods might return a null or empty string for a determiner. This method accounts for that, applying a special determiner if one is available and then returning the descriptor + name combination.
	 */
	private static String applyDeterminer(String descriptor, String input) {
		if(descriptor==null)
			return input;
		
		return (descriptor.length() > 0 ? descriptor + " " : (UtilText.isVowel(input.charAt(0))?"an ":"a ")) + input;
	}

	private static String getSubspeciesName(Subspecies race, GameCharacter character) {
		if(race==null) {
			return "";
		}
		
		if (character.isFeminine()) {
			return race.getSingularFemaleName(character);
		} else {
			return race.getSingularMaleName(character);
		}
	}
	
	private static String getSubspeciesNamePlural(Subspecies race, GameCharacter character) {
		if(race==null)
			return "";
		if (character.isFeminine()) {
			return race.getPluralFemaleName(character);
		} else {
			return race.getPluralMaleName(character);
		}
	}
	
	private static String getSkinName(BodyPartTypeInterface bodyPart, GameCharacter character) {
		if(bodyPart.getBodyCoveringType(character)==null)
			return "";
		
		if(parseAddPronoun) {
			parseAddPronoun = false;
			return applyDeterminer(bodyPart.getBodyCoveringType(character).getDeterminer(character), bodyPart.getBodyCoveringType(character).getName(character));
		} else {
			return bodyPart.getBodyCoveringType(character).getName(character);
		}
	}
	
	private static String getSkinNameWithDescriptor(BodyPartTypeInterface bodyPart, Covering asCovering, GameCharacter character) {
		if(bodyPart.getBodyCoveringType(character)==null)
			return "";
		
		if(parseAddPronoun) {
			parseAddPronoun = false;
			return applyDeterminer(
					asCovering.getType().getDeterminer(character),
					applyDescriptor(asCovering.getModifier().getName(),
					asCovering.getType().getName(character)));
		} else {
			return applyDescriptor(asCovering.getModifier().getName(), asCovering.getType().getName(character));
		}
	}
	
	private static Covering getCoveringHandledFreckles(GameCharacter character, BodyPartInterface bodyPart, Covering covering) {
		if(covering.getPattern()==CoveringPattern.FRECKLED_FACE) {
			return new Covering(
					bodyPart.getBodyCoveringType(character),
					CoveringPattern.FRECKLED,
					covering.getModifier(),
					covering.getPrimaryColour(),
					covering.isPrimaryGlowing(),
					covering.getSecondaryColour(),
					covering.isSecondaryGlowing());
		}
		return covering;
	}
	
	public static String getNaturalEnumeration(List<String> elements) {
		if(elements.isEmpty()) {
			return "";
		}
		
		if(elements.size() == 1) {
			return elements.get(0);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<elements.size(); ++i) {
			sb.append(elements.get(i));

			if(i == elements.size()-2) {
				sb.append(" and ");
			} else if(i != elements.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	public static AbstractClothingType getClothingTypeForParsing() {
		return clothingTypeForParsing;
	}

	public static void setClothingTypeForParsing(AbstractClothingType clothingTypeForParsing) {
		UtilText.clothingTypeForParsing = clothingTypeForParsing;
		engine.put("clothing", getClothingTypeForParsing());
	}
	
}
