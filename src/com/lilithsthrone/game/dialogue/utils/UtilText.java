package com.lilithsthrone.game.dialogue.utils;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
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
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAnusType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFootType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractMouthType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractNippleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTesticleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.AnusType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.MouthType;
import com.lilithsthrone.game.character.body.types.NippleType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.TesticleType;
import com.lilithsthrone.game.character.body.types.TongueType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.gender.PronounType;
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
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.baseActions.ToyVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

/**
 * @since 0.1.0
 * @version 0.3.8.9
 * @author Innoxia, Pimvgd, AlacoGit
 */
public class UtilText {

	private static String modifiedSentence;
	public static StringBuilder nodeContentSB = new StringBuilder(4096);
	private static StringBuilder descriptionSB = new StringBuilder();
	private static List<ParserTag> parserTags;
	private static List<String> parserVariableCalls = new ArrayList<>();
	
	private static AbstractClothingType clothingTypeForParsing;
	
//	private static List<GameCharacter> specialNPCList = new ArrayList<>();
	private static boolean parseCapitalise;
	private static boolean parseAddPronoun;
	
	private static ScriptEngine engine;
	
	private static List<String> specialParsingStrings = new ArrayList<>();
	
	private static Map<String, String> americanEnglishConversions = Util.newHashMapOfValues(
			// -our to -or:
			new Value<>("armour", "armor"),
			new Value<>("armoury", "armoury"),
			new Value<>("behaviour", "behavior"),
			new Value<>("candour", "candor"),
			new Value<>("clamour", "clamor"),
			new Value<>("colour(\\s|\\.|,|s|e|i)", "color$1"),
			new Value<>("demeanour", "demeanor"),
			new Value<>("endeavour", "endeavor"),
			new Value<>("favourite", "favourite"),
			new Value<>("flavour", "flavor"),
			new Value<>("glamour", "glamor"),
			new Value<>("harbour", "harbor"),
			new Value<>("honour", "honor"),
			new Value<>("humour", "humor"),
			new Value<>("labour", "labor"),
			new Value<>("neighbour", "neighbor"),
			new Value<>("odour", "odor"),
			new Value<>("rancour", "rancor"),
			new Value<>("rigour", "rigor"),
			new Value<>("rumour", "rumor"),
			new Value<>("saviour", "savior"),
			new Value<>("savour", "savor"),
			new Value<>("savoury", "savoury"),
			new Value<>("splendour", "splendor"),
			new Value<>("valour", "valor"),
			new Value<>("vapour", "vapor"),
			new Value<>("vigour", "vigor"),
			
			// -re to -er:
			new Value<>("amphitheatre", "amphitheater"),
			new Value<>("calibre", "caliber"),
			new Value<>("centimetre", "centimeter"),
			new Value<>("centre", "center"),
			new Value<>("fibre", "fiber"),
			new Value<>("kilometre", "kilometer"),
			new Value<>("litre", "liter"),
			new Value<>("louvre", "louver"),
			new Value<>("lustre", "luster"),
			new Value<>("manoeuvre", "manoeuver"),
			new Value<>("meagre", "meager"),
			new Value<>("metre", "meter"),
			new Value<>("millimetre", "millimeter"),
			new Value<>("sabre", "saber"),
			new Value<>("sceptre", "scepter"),
			new Value<>("sombre", "somber"),
			new Value<>("spectre", "specter"),
			new Value<>("theatre", "theater"),

			// -ogue to -og:
			new Value<>("analogue", "analog"),
			new Value<>("dialogue", "dialog"),
			new Value<>("catalogue", "catalog"),
//			new Value<>("epilogue", "epilog"),
//			new Value<>("monologue", "monolog"),
//			new Value<>("prologue", "prolog"),
//			new Value<>("travelogue", "travelog"),
			
			// -l endings are not doubled:
			new Value<>("cancelled", "canceled"),
			new Value<>("counsellor", "counselor"),
			new Value<>("equalled", "equaled"),
			new Value<>("fuelling", "fueling"),
			new Value<>("fuelled", "fueled"),
			new Value<>("grovelling", "groveling"),
			new Value<>("jeweller", "jeweler"),
			new Value<>("jewellery", "jewelery"),
			new Value<>("levelled", "leveled"),
			new Value<>("libelled", "libeled"),
			new Value<>("marvellous", "marvelous"),
			new Value<>("modelling", "modeling"),
			new Value<>("panelled", "paneled"),
			new Value<>("quarrelling", "quarreling"),
			new Value<>("revelled", "reveled"),
			new Value<>("woollen", "woolen"),

			// some -l words are doubled:
			new Value<>("appal(^l)", "appall$1"),
			new Value<>("distil(^l)", "distill$1"),
			new Value<>("enrol(^l)", "enroll$1"),
			new Value<>("enthral(^l)", "enthrall$1"),
			new Value<>("fulfil(^l)", "fulfill$1"),
			new Value<>("instil(^l)", "instill$1"),
			new Value<>("skilful(^l)", "skillful$1"),
			new Value<>("wilful(^l)", "willful$1"),

			// -ae and -oe words change to -e:
			new Value<>("diarrhoea", "diarrhea"),
			new Value<>("oestrogen", "oestrgen"),
			new Value<>("foetus", "fetus"),
			new Value<>("manoeuvre", "maneuvre"),
			new Value<>("mementoes", "mementes"),
			new Value<>("anaemia", "anemia"),
			new Value<>("caesarean", "cesarean"),
			new Value<>("gynaecology", "gynecology"),
			new Value<>("haemorrhage", "hemorrhage"),
			new Value<>("leukaemia", "leukemia"),
			new Value<>("palaeontology", "paleontology"),
			new Value<>("paediatric", "pediatric"),

			// -ise words change to -ize:
			new Value<>("appetiser", "appetizer"),
			new Value<>("authorise", "authorize"),
			new Value<>("capitalise", "capitalize"),
			new Value<>("characterise", "characterize"),
			new Value<>("civilise", "civilize"),
			new Value<>("colonise", "colonize"),
			new Value<>("criticise", "criticize"),
			new Value<>("dramatise", "dramatize"),
			new Value<>("emphasise", "emphasize"),
			new Value<>("equalise", "equalize"),
			new Value<>("mobilise", "mobilize"),
			new Value<>("naturalise", "naturalize"),
			new Value<>("organise", "organize"),
			new Value<>("popularise", "popularize"),
			new Value<>("realise", "realize"),
			new Value<>("recognise", "recognize"),
			new Value<>("satirise", "satirize"),
			new Value<>("standardise", "standardize"),
			new Value<>("symbolise", "symbolize"),
			new Value<>("vaporise", "vaporize"),
			new Value<>("analyse", "analyze"),
			new Value<>("paralyse", "paralyze"),

			// -ce words change to -se:
			new Value<>("defence", "defense"),
			new Value<>("defencive", "defensive"),
			new Value<>("offence", "offense"),
			new Value<>("offencive", "offensive"),
			new Value<>("pretence", "pretense"),
			new Value<>("licence", "license"),
			new Value<>("practise", "practice"),

			// other:
			new Value<>("draught", "daft"),
			new Value<>("plough", "plow"),
			new Value<>("tyre", "tire"),
			new Value<>("mould", "mold"),
			new Value<>("moult", "molt"),
			new Value<>("smoulder", "smolder"),
			new Value<>("programme", "program"),
			new Value<>("cheque", "check"),
			new Value<>("chequer", "checker"),
			new Value<>("acknowledgement", "acknowledgment"),
			new Value<>("ageing", "aging"),
			new Value<>("judgement", "judgment"),
			new Value<>("aluminium", "aluminum"),
			new Value<>("axe", "ax"),
			new Value<>("cosy", "cozy"),
			new Value<>("kerb", "curb"),
			new Value<>("furore", "furor"),
			new Value<>("grey", "gray"),
			new Value<>("carat", "karat"),
			new Value<>("liquorice", "licorice"),
			new Value<>("moustache", "mustache"),
			new Value<>("nought", "naught"),
			new Value<>("pyjamas", "pajamas"),
			new Value<>("sceptic", "skeptic"),
			new Value<>("phial", "vial"),
			new Value<>("whisky", "whiskey")
			);
	
	/**
	 * Converts the input into a format suitable for html display. i.e. converts things like '<' to "&lt;".
	 */
	public static String parseForHTMLDisplay(String input) {
		StringBuilder builder = new StringBuilder();
		
		for (char c : input.toCharArray()) {
			switch (c) {
				// I'm not sure why this was being changed to a non-breaking space... It was interfering with clothing name equality and such, so I removed it in v0.3.5.1
//				case ' ':
//					builder.append("&nbsp;");
//					break;
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
	}

	public static String parsePlayerSpeech(String text) {
		return parseSpeech(text, Main.game.getPlayer());
	}

	private static String getGlowStyle(Colour colour) {
		return colour==null?"":"text-shadow: 0px 0px 4px "+colour.getShadesRgbaFormat(0.75f)[1]+";";
	}

	public static String parseSpeech(String text, GameCharacter target, boolean includePersonalityEffects, boolean includeExtraEffects) {
		modifiedSentence = text;
		
		String[] splitOnConditional = modifiedSentence.split("#THEN");
		
		modifiedSentence = splitOnConditional[splitOnConditional.length-1];
		
		if(target.hasPersonalityTrait(PersonalityTrait.MUTE)) {
			modifiedSentence = Util.replaceWithMute(modifiedSentence, Main.game.isInSex() && Main.sex.getAllParticipants().contains(target));
			
		} else if(includeExtraEffects
				&& !parserTags.contains(ParserTag.SEX_ALLOW_MUFFLED_SPEECH)
				&& Main.game.isInSex()
				&& Main.sex.getAllParticipants().contains(target)
				&& target.isSpeechMuffled()) {
			if(Main.sex.isOngoingActionsBlockingSpeech(target)) {
				modifiedSentence = Util.replaceWithMuffle(modifiedSentence, 2);
			}
			
		} else {
			if(includePersonalityEffects) {
				if(target.hasFetish(Fetish.FETISH_BIMBO)) {
					if(target.isFeminine()) {
						modifiedSentence = Util.addBimbo(modifiedSentence, 6);
					} else {
						modifiedSentence = Util.addBro(modifiedSentence, 6);
					}
				}
				
				if(target.hasPersonalityTrait(PersonalityTrait.SLOVENLY)) {
					modifiedSentence = Util.applySlovenlySpeech(modifiedSentence);
				}
			}
			
			if(includeExtraEffects) {
				if(target.getAlcoholLevel().getSlurredSpeechFrequency()>0) {
					modifiedSentence = Util.addDrunkSlur(modifiedSentence, target.getAlcoholLevel().getSlurredSpeechFrequency());
				}
				
				// Apply speech effects:
				if(target.isSpeechMuffled()) {
					modifiedSentence = Util.addMuffle(modifiedSentence, 5);
					
				} else if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(target)) {
					if(Main.sex.isCharacterEngagedInOngoingAction(target)) {
						modifiedSentence = Util.addSexSounds(modifiedSentence, 6);
					}
					
				}
			}

			if(includePersonalityEffects) {
				if(target.getLipSize().isImpedesSpeech() || target.hasPersonalityTrait(PersonalityTrait.LISP)) {
					modifiedSentence = Util.applyLisp(modifiedSentence);
				}
	
				if(target.hasPersonalityTrait(PersonalityTrait.STUTTER)) {
					modifiedSentence = Util.addStutter(modifiedSentence, 4);
				}
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
				return "<span class='speech' style='color:" + PresetColour.MASCULINE_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			} else if (Femininity.valueOf(target.getFemininityValue()) == Femininity.ANDROGYNOUS) {
				return "<span class='speech' style='color:" + PresetColour.ANDROGYNOUS_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			} else {
				return "<span class='speech' style='color:" + PresetColour.FEMININE_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			}
		}
	
	}
	
	public static String parseSpeech(String text, GameCharacter target) {
		return parseSpeech(text, target, true, true);
	}
	
	public static String parseSpeechNoEffects(String text, GameCharacter target) {
		return parseSpeech(text, target, false, false);
	}
	
	public static String parseSpeechNoExtraEffects(String text, GameCharacter target) {
		return parseSpeech(text, target, true, false);
	}
	
	public static String parseThought(String text, GameCharacter target) {
		return "<i>"+parseSpeech(text, target, true, false).replaceAll("class='speech'", "class='thoughts'")+"</i>";
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
			modifiedSentence = Util.addStutter(modifiedSentence, 4);
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

	public static String getCurrencySymbol() {
//		return "&#9679;"; // Circle
		return "&#164;"; // 'Generic' currency symbol
	}
	
	public static String getPentagramSymbol() {
		return "&#9737;"; // Java doesn't support unicode 6 ;_;   No pentagram for me... ;_;  "&#9956";
	}
	
	public static String getShieldSymbol() {
		return "&#9930;";
	}

	public static String getInfinitySymbol(boolean largerFont) {
		//"&#9854;";
//		return "<span style='font-family:serif; font-weight:normal; font-size:1.25em;'>&#8734;</span>";
		return "<span style='font-weight:normal; color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; "+(largerFont?"font-size:28px;":"")+"'>&#8734;</span>";
	}
	
	public static String applyGlow(String input, Colour colour) {
		return "<span style='color:"+colour.toWebHexString()+"; text-shadow: 0px 0px 4px "+colour.getShades()[4]+";'>"+input+"</span>";
	}
	
	public static String applyVibration(String input, Colour colour) {
		return "<span style='text-shadow: 2px 2px "+colour.getShades()[0]+";'>"+input+"</span>";
	}
	
	public static String formatAsEssencesUncoloured(int amount, String tag, boolean withOverlay) {
		return "<div class='item-inline'>"
					+ TFEssence.ARCANE.getSVGStringUncoloured() + (withOverlay?"<div class='overlay no-pointer' id='ESSENCE_"+TFEssence.ARCANE.hashCode()+"'></div>":"")
				+"</div>"
				+ " <"+tag+" style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+Units.number(amount)+"</"+tag+">";
	}
	
	
	public static String formatAsEssences(int amount, String tag, boolean withOverlay) {
		return "<div class='item-inline'>"
					+ TFEssence.ARCANE.getSVGString() + (withOverlay?"<div class='overlay no-pointer' id='ESSENCE_"+TFEssence.ARCANE.hashCode()+"'></div>":"")
				+"</div>"
				+ " <"+tag+" style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>"+Units.number(amount)+"</"+tag+">";
	}

	// Money formatting:
	
	public static String formatAsItemPrice(int money) {
		String moneyString = Units.number(money);
		
		if(money > 1_000_000) {
			float moneyAbbreviated = money/1_000_000f;
			moneyString = Units.number(moneyAbbreviated, 1, 1)+"M";
			return formatAsMoney(moneyString, "b", PresetColour.CURRENCY_GOLD);
			
		} else if(money > 1_000) {
			float moneyAbbreviated = money/1_000f;
			int precision = money < 10_000?1:0;
			moneyString = Units.number(moneyAbbreviated, precision, precision)+"k";
			return formatAsMoney(moneyString, "b", PresetColour.CURRENCY_SILVER);
		}
		
		return formatAsMoney(moneyString, "b", PresetColour.CURRENCY_COPPER);
	}
	
	public static String formatAsMoney(int money) {
		return formatAsMoney(money, "b");
	}
	
	public static String formatAsMoneyUncoloured(int money, String tag) {
		return formatAsMoney(money, tag, null);
	}
	
	public static String formatAsMoney(int money, String tag) {
		return formatAsMoney(money, tag, PresetColour.TEXT);
	}
	
	public static String formatAsMoney(String money, String tag) {
		return formatAsMoney(money, tag, PresetColour.TEXT);
	}
	
	public static String formatAsMoney(int money, String tag, Colour amountColour) {
		return formatAsMoney(Units.number(money), tag, amountColour);
	}
	
	public static String formatAsMoney(String money, String tag, Colour amountColour) {
		return "<" + tag + " style='" + (amountColour==null?"":"color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";") + " padding-right:2px;'>" + getCurrencySymbol() + "</" + tag + ">"
				+ "<" + tag + (amountColour==null?"":" style='color:"+amountColour.toWebHexString()+";'") + ">" + money + "</" + tag + ">";
	}
	
	
	public static String formatVirginityLoss(String s) {
		return "<p style='text-align:center; color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'><i>"+s+"</i></p>";
	}
	
	public static String formatTooLoose(String s) {
		return "<p style='text-align:center; color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'><i>"+s+"</i></p>";
	}
	
	public static String formatStretching(String s) {
		return "<p style='text-align:center; color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'><i>"+s+"</i></p>";
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
				 && !word.startsWith("Uni") && !word.startsWith("uni")
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
			return "<p><span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Dialogue for '"+tag+"' not found! (Make sure that the 'res' folder is in the same directory as the .jar or .exe.)</span></p>";

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

		Map<String, List<String>> strings = new HashMap<>();
		
		if (file.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				for(int i=0; i<((Element) doc.getElementsByTagName("dialogue").item(0)).getElementsByTagName("htmlContent").getLength(); i++){
					Element e = (Element) ((Element) doc.getElementsByTagName("dialogue").item(0)).getElementsByTagName("htmlContent").item(i);
					
					strings.putIfAbsent(e.getAttribute("tag"), new ArrayList<>());
					strings.get(e.getAttribute("tag")).add(e.getTextContent().replaceFirst("<!\\[CDATA\\[", "").replaceAll("\\]\\]>", ""));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(strings.isEmpty()) {
			return "<p><span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>No dialogues found! (Make sure that the 'res' folder is in the same directory as the .jar or .exe.)</span></p>";

		} else {
			StringBuilder sb = new StringBuilder();
			StringBuilder duplicationSB = new StringBuilder();
			for(Entry<String, List<String>> s : strings.entrySet()) {
				if(s.getValue().size()>1) {
					duplicationSB.append("[style.italicsMinorBad(XML test duplication: tag '"+s.getKey()+"' is repeated "+s.getValue().size()+" times!)]<br/>");
				}
				for(String savedString : s.getValue()) {
					sb.append("<p>"
								+ "<b>Dialogue tag: "+s.getKey()+"</b>"
							+ "</p>"
							+ parse(specialNPC, savedString, true)
							+"<br/><br/>");
				}
			}
			return duplicationSB.toString() + sb.toString();
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
	
	public static boolean isInSpeech() {
		return speechTarget!=null && !speechTarget.isEmpty();
	}
	
	private static String speechTarget = "";
	private static boolean suppressOutput = false;

	public static String parse(List<GameCharacter> specialNPC, String input, ParserTag... tags) {
		return parse(specialNPC, input, false, tags);
	}
	
	private static String parse(List<GameCharacter> specialNPC, String input, boolean xmlParsing, ParserTag... tags) {
		return parse(specialNPC, input, xmlParsing, Arrays.asList(tags));
	}
	
	/**
	 * Parses supplied text.
	 */
	public static String parse(List<GameCharacter> specialNPC, String input, boolean xmlParsing, List<ParserTag> tags) {
		parserTags = (tags);
		
		if(Main.game!=null && Main.game.getCurrentDialogueNode()==DebugDialogue.PARSER) {
			input = input.replaceAll("\u200b", "");
		}
//		input = input.replaceAll("", ""); //???
		for(int i=0; i<specialParsingStrings.size(); i++) {
			input = input.replaceAll("\\[#SPECIAL_PARSE_"+i+"\\]", specialParsingStrings.get(i));
		}
		
		if(xmlParsing) {
			if(input.contains("#VAR")) { // Set variables to be parsed on each conditional:
				speechTarget = "";
				parserVariableCalls = new ArrayList<>();
				Matcher matcherVAR = Pattern.compile("(?s)#VAR(.*?)#ENDVAR").matcher(input);
				while(matcherVAR.find()) {
					String s = matcherVAR.group().replaceAll("#VAR", "").replaceAll("#ENDVAR", "");
					parserVariableCalls.add(s);
				}
				input = input.replaceAll("(?s)#VAR(.*?)#ENDVAR", "");
			} else {
				speechTarget = "";
				parserVariableCalls = new ArrayList<>();
			}
		}
		
		try {
			StringBuilder resultBuilder = new StringBuilder();
			StringBuilder sb = new StringBuilder();
			int openBrackets = 0;
			int closeBrackets = 0;
			int openArg = 0;
			int closeArg = 0;
			int startIndex = 0;
			int endIndex = 0;
			
			String target = null;
			String command = null;
			String arguments = null;
			String conditionalStatement = null;
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
				}
				
				if (currentParseMode != ParseMode.REGULAR && currentParseMode != ParseMode.REGULAR_SCRIPT) {
					suppressOutput = false;
					if (c == 'F' && substringMatchesInReverseAtIndex(input, "#IF", i)) {
						if (openBrackets == 0) {
							conditionals = new LinkedHashMap<>();
							currentParseMode = ParseMode.CONDITIONAL;
							startIndex = i-2;
							
							for(int j=i+1;j<input.length();j++) {
								if(!Character.isWhitespace(input.charAt(j))) {
									usingConditionalBrackets = input.charAt(j)=='(';
									lastConditionalUsedBrackets = usingConditionalBrackets;
									break;
								}
							}
						} else {
							lastConditionalUsedBrackets = false;
						}
						
						openBrackets++;
						
					} else if (currentParseMode == ParseMode.CONDITIONAL) {
						if(usingConditionalBrackets) {
							if(conditionalOpenBrackets>0 && conditionalOpenBrackets==conditionalCloseBrackets && openBrackets-1==closeBrackets) {
								conditionalStatement = sb.toString().substring(1, sb.length())+")";
								conditionalStatement = conditionalStatement.replaceAll("\n", "").replaceAll("\t", "");
								conditionalStatement = conditionalStatement.trim();
								
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
								
							} else if(c == 'E' && substringMatchesInReverseAtIndex(input, "#ELSE", i)
									&& (i+1==input.length()||i+2==input.length()||input.charAt(i+1)!='I'||input.charAt(i+2)!='F')
									&& openBrackets-1==closeBrackets
									&& conditionalStatement!=null) {
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
								
							} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ELSEIF", i) && openBrackets-1==closeBrackets) {
								conditionals.put(conditionalStatement, sb.toString().substring(1, sb.length()-6)); // Cut off the '#ELSEIF' at the end of this section.

								for(int j=i+1;j<input.length();j++) {
									if(!Character.isWhitespace(input.charAt(j))) {
										usingConditionalBrackets = input.charAt(j)=='(';
										break;
									}
								}
								
								sb.setLength(0);
								
							} else if(c == 'E' && substringMatchesInReverseAtIndex(input, "#ELSE", i)
									&& (i+1==input.length()||i+2==input.length()||input.charAt(i+1)!='I'||input.charAt(i+2)!='F')
									&& openBrackets-1==closeBrackets) {
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
								if(command.equals("speech") || command.equals("speechNoEffects") || command.equals("speechNoExtraEffects")) {
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
						subResult = parseConditionalSyntaxNew(specialNPC, conditionals, xmlParsing);
					} else {
						subResult = parseSyntaxNew(specialNPC, target, command, arguments, currentParseMode);
					}
					if (openBrackets > 1) {
						subResult = parse(specialNPC, subResult, false, tags);
					}
					if(command!=null && (command.equals("speech") || command.equals("speechNoEffects") || command.equals("speechNoExtraEffects"))) {
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
			
			if (startIndex != 0) {
				System.err.println("Error in parsing: StartIndex:"+startIndex+" ("+target+", "+command+") - "+input.substring(startIndex, Math.min(input.length()-1, startIndex+20)));
				return input;
			}
			if (startedParsingSegmentAt < input.length()) {
				resultBuilder.append(input.substring(startedParsingSegmentAt, input.length()));
			}

			String result = resultBuilder.toString();
			result = result.replaceAll("german", "German"); // This is needed as the subspecies 'german-shepherd-morph' needs to use a lowercase 'g' for generic name determiner detection.
			
			if(Main.game.isStarted() && Main.game.getPlayer().getHistory()==Occupation.TOURIST) {
				for(Entry<String, String> entry : americanEnglishConversions.entrySet()) {
					result = result.replaceAll(entry.getKey(), entry.getValue());
					result = result.replaceAll(Util.capitaliseSentence(entry.getKey()), Util.capitaliseSentence(entry.getValue()));
				}
			}
			
			return result;
			
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
				"(asWords)",
				"Returns how much money the character has. Pass in true to format the integer into words (e.g. 100 -> one hundred).") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && (arguments.equalsIgnoreCase("true"))) {
					return Util.intToString(character.getMoney());
				}
				return String.valueOf(character.getMoney());
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("random"),
				true,
				false,
				"(text1 | text2 | text3)",
				"Returns a random string from the supplied arguments. Nesting 'random' commands inside one another currently does not work.") {
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
				Util.newArrayListOfValues(
						"walk",
						"slither"),
				true,
				false,
				"",
				"Returns the appropriate present first person singular verb for this character's movement."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getMovementVerbPresentFirstPersonSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"walks",
						"slithers"),
				true,
				false,
				"",
				"Returns the appropriate present third person singular verb for this character's movement."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getMovementVerbPresentThirdPersonSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"walking",
						"slithering"),
				true,
				false,
				"",
				"Returns the appropriate present participle verb for this character's movement."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getMovementVerbPresentParticiple();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"walked",
						"slithered"),
				true,
				false,
				"",
				"Returns the appropriate past participle verb for this character's movement."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getMovementVerbPastParticiple();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"step",
						"slide"),
				true,
				false,
				"",
				"Returns the appropriate present first person singular verb for this character's individual movement action."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getIndividualMovementVerbPresentFirstPersonSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"steps",
						"slides"),
				true,
				false,
				"",
				"Returns the appropriate present third person singular verb for this character's individual movement action."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getIndividualMovementVerbPresentThirdPersonSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"stepping",
						"sliding"),
				true,
				false,
				"",
				"Returns the appropriate present participle verb for this character's individual movement action."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getIndividualMovementVerbPresentParticiple();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"stepped",
						"slid"),
				true,
				false,
				"",
				"Returns the appropriate past participle verb for this character's individual movement action."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getIndividualMovementVerbPastParticiple();
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
					return character.getSlaveJob(Main.game.getHourOfDay()).getName(character);
				}
				return character.getHistory().getName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"desiredJob"),
				true,
				true,
				"",
				"Returns the name of this character's desired job."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getDesiredJobs().iterator().next().getName(character);
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
				"(coloured)",
				"Returns the name of this character's gender, which is coloured if the argument is 'true'."){
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
				"(coloured)",
				"Returns the name of the gender that this character appears to be (which may not be their actual gender), which is coloured if the argument is 'true'."){
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
						"orientation"),
				true,
				true,
				"",
				"Returns the name of this character's sexual orientation."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getSexualOrientation().getName();
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
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Error: affection command character argument not found! ("+arguments+")</i>";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"companion"),
				true,
				true,
				"",
				"Prints out the most important name of this character's relation towards their party's leader (it will cut off multiple relation names) for half of the time. The other half will return 'companion' or 'slave' (if applicable)."
				+ " e.g. If the player's companion is their daughter, then 'npc.companion' would print 'daughter', otherwise 'companion' or 'slave' (if applicable)."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				try {
					GameCharacter targetedCharacter = character.getPartyLeader();
					if(targetedCharacter==null) {
						return "companion";
					}
					Set<Relationship> set = character.getRelationshipsTo(targetedCharacter);
					if(set.size()>=1 && Math.random()<0.5f) {
						return set.iterator().next().getName(character);
					} else {
						if(character.isSlave() && character.getOwner().equals(targetedCharacter) && Math.random()<0.5f) {
							return "slave";
						}
						return "companion";
					}
					
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Error: relation command character argument not found! ("+arguments+")</i>";
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
					GameCharacter targetedCharacter = parserTarget.getCharacter(arguments.toLowerCase(), specialNPCs);
					Set<Relationship> set = character.getRelationshipsTo(targetedCharacter);
					if(set.size()>=1) {
						return set.iterator().next().getName(character);
					} else {
						return "no relation";
					}
//					return character.getRelationshipStrTo(targetedCharacter);
					
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Error: relation command character argument not found! ("+arguments+")</i>";
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
					GameCharacter targetedCharacter = parserTarget.getCharacter(arguments.toLowerCase(), specialNPCs);
					return character.getRelationshipStrTo(targetedCharacter);
					
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Error: relation command character argument not found! ("+arguments+")</i>";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"daughter",
						"son"),
				true,
				true,
				"",
				"Returns either 'son' or 'daughter' based on the character's femininity."){
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
				"",
				"Returns either 'father' or 'mother' based on the character's femininity."){
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
				"",
				"Returns either 'daddy' or 'mommy' based on the character's femininity."){
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
				"",
				"Returns either 'dad' or 'mom' based on the character's femininity."){
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
				"",
				"Returns either 'brother' or 'sister' based on the character's femininity."){
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
				"",
				"Returns either 'bro' or 'sis' based on the character's femininity."){
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
				"",
				"Returns either 'nephew' or 'niece' based on the character's femininity."){
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
				"",
				"Returns either 'master' or 'mistress' based on the character's femininity."){
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
				"",
				"Returns either 'sir' or 'ma'am' based on the character's femininity."){
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
				"",
				"Returns either 'hero' or 'heroine' based on the character's femininity."){
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
				"",
				"Returns either 'Mr.' or 'Miss' based on the character's femininity."){
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
				"",
				"Returns either 'boyfriend' or 'girlfriend' based on the character's femininity."){
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
				"Returns a random mean word to describe this person, based on their femininity.") {
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
				"Returns a random mean pluralised word to describe this person, based on their femininity.") {
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
				"Returns a random mean word to describe this person, based on their femininity.") {
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
						"fullRaces",
						"racesFull",
						"femininityRaces"),
				true,
				true,
				"(coloured)",
				"Returns a full description of this characters pluralised race (including femininity). Pass in 'true' to colour the text."){
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
							+ " <span style='color:"+character.getSubspecies().getColour(character).toWebHexString()+";'>" +  getSubspeciesNamePlural(character.getSubspecies(),character) + "</span>";
				}
				return (parseCapitalise
						?Util.capitaliseSentence(Femininity.getFemininityName(character.getFemininityValue(), pronoun))
						:Femininity.getFemininityName(character.getFemininityValue(), pronoun))+" "+character.getRaceStage().getName()+" "+getSubspeciesNamePlural(character.getSubspecies(),character);
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
					String name = character.isRaceConcealed()?"unknown race":getSubspeciesName(character.getSubspecies(), character);
					return "<span style='color:"+(character.isRaceConcealed()?PresetColour.TEXT_GREY:character.getSubspecies().getColour(character)).toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
							+"</span>";
				}
				return getSubspeciesName(character.getSubspecies(), character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"races",
						"racePlural"),
				true,
				true,
				"(coloured)",
				"Returns the plural name of this characters race. Pass in 'true' to colour the text."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					String name = character.isRaceConcealed()?"unknown race":getSubspeciesNamePlural(character.getSubspecies(), character);
					return "<span style='color:"+(character.isRaceConcealed()?PresetColour.TEXT_GREY:character.getSubspecies().getColour(character)).toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
							+"</span>";
				}
				return getSubspeciesNamePlural(character.getSubspecies(), character);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"raceFeral",
						"feralRace"),
				true,
				true,
				"(coloured)",
				"Returns the feral name of this characters race. Pass in 'true' to colour the text."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					String name = character.isRaceConcealed()?"unknown race":character.getSubspecies().getFeralName(character);
					return "<span style='color:"+(character.isRaceConcealed()?PresetColour.TEXT_GREY:character.getSubspecies().getColour(character)).toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
							+"</span>";
				}
				return character.getSubspecies().getFeralName(character);
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
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>preferredBody_not_npc</i>";
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
				"(coloured)",
				"Returns the name of this character's femininity. Pass in 'true' as an argument to make the returned text coloured in the femininity's colour."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Femininity fem =  Femininity.valueOf(character.getFemininityValue());
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return "<span style='color:"+fem.getColour().toWebHexString()+";'>"+fem.getName(false)+"</span>";
							
				}
				return fem.getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bodySize"),
				true,
				true,
				"",
				"Returns a single word descriptor of this character's body size."){
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
				"",
				"Returns a single word descriptor of this character's muscle mass."){
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
				"",
				"Returns a single word descriptor of this character's body shape."){
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
				"",
				"Returns a single word descriptor of this character's height."){
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
				"Returns the character's height in the long, localised format.") {
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
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Error: heightDown character argument not found! ("+arguments+")</i>";
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
						"speech"),
				false,
				false,
				"(speech content)",
				"Parses the containing dialogue as though the targeted character is saying it. Automatically applies any extra speech-modifying effects, such as drunken slurs, slovenly speech, sexual moans, etc."){
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
						"speechMasculine"),
				false,
				false,
				"(speech content)",
				"Parses the containing dialogue as though a generic, masculine character is saying it."){
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
						"speechMasculineStrong"),
				false,
				false,
				"(speech content)",
				"Parses the containing dialogue as though a generic, very masculine character is saying it."){
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
						"speechAndrogynous"),
				false,
				false,
				"(speech content)",
				"Parses the containing dialogue as though a generic, androgynous character is saying it."){
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
						"speechFeminine"),
				false,
				false,
				"(speech content)",
				"Parses the containing dialogue as though a generic, feminine character is saying it."){
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
						"speechFeminineStrong"),
				false,
				false,
				"(speech content)",
				"Parses the containing dialogue as though a generic, very feminine character is saying it."){
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
						"speechNoEffects"),
				false,
				false,
				"(speech content)",
				"Parses the containing dialogue as though the targeted character is saying it. Cuts out *all* extra speech-modifying effects, such as drunken slurs, slovenly speech, sexual moans, etc."){
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
						"speechNoExtraEffects"),
				false,
				false,
				"(speech content)",
				"Parses the containing dialogue as though the targeted character is saying it. Cuts out only the extra speech-modifying effects which are not a part of the character's personality, such as drunken slurs, sexual moans, etc."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseSpeechNoExtraEffects(arguments, character);
				} else {
					return parseSpeechNoExtraEffects("...", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thought"),
				false,
				false,
				"(thought content)",
				"Parses the supplied text in teh argument as though it's a thought of the character."){
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
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
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
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("sob", "scream", "cry");
						} else {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("shout", "cry");
						}
						
					} else if(Main.sex.getSexPace(character)==SexPace.DOM_GENTLE) {
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
						if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
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
						if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
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
						if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
							if(character.isFeminine()) {
								return returnStringAtRandom("miserably", "pathetically") + " " + returnStringAtRandom("sob", "scream", "cry");
							} else {
								return returnStringAtRandom("miserably", "pathetically") + " " + returnStringAtRandom("shout", "cry");
							}
							
						} else if(Main.sex.getSexPace(character)==SexPace.DOM_GENTLE) {
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
						if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
							if(character.isFeminine()) {
								return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("sobs", "cries");
							} else {
								return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("shouts", "cries");
							}
							
						} else if(Main.sex.getSexPace(character)==SexPace.DOM_GENTLE) {
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
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
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
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("sobs", "cries");
						} else {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("shouts", "cries");
						}
						
					} else if(Main.sex.getSexPace(character)==SexPace.DOM_GENTLE) {
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
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
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
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("sobbing", "crying");
						} else {
							return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("shouting", "protesting");
						}
						
					} else if(Main.sex.getSexPace(character)==SexPace.DOM_GENTLE) {
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
						"roughly",
						"sexPaceVerb"),
				true,
				false,
				"(Alternative start string)",
				"Returns an appropriate descriptor based on the pace of the character. The argument is used in place of a descriptor if the pace is SUB_NORMAL."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					List<String> descriptors = new ArrayList<>();
					switch(Main.sex.getSexPace(character)) {
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
						"filly",
						"colt"),
				true,
				true,
				"",
				"Returns the correct gender version of 'filly' or 'colt' for this character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "filly";
				} else {
					return "colt";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mare",
						"stallion"),
				true,
				true,
				"",
				"Returns the correct gender version of 'mare' or 'stallion' for this character."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "mare";
				} else {
					return "stallion";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"feminineDescriptor",
						"masculineDescriptor"),
				true,
				true,
				"(coloured)",
				"Returns a one or two word descriptor of the femininity of the character. Pass in 'true' as an argument if you want the descriptor to be coloured."){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				
				String descriptor = "";
				
				switch(character.getFemininity()) {
					case FEMININE_STRONG:
						descriptor = UtilText.returnStringAtRandom("very feminine", "beautiful", "gorgeous");
						break;
					case FEMININE:
						descriptor = UtilText.returnStringAtRandom("pretty", "feminine", "cute");
						break;
					case ANDROGYNOUS:
						descriptor = UtilText.returnStringAtRandom("androgynous");
						break;
					case MASCULINE:
						descriptor = UtilText.returnStringAtRandom("masculine", "handsome");
						break;
					case MASCULINE_STRONG:
						descriptor = UtilText.returnStringAtRandom("very masculine", "extremely handsome");
						break;
				}
				
				String determiner = "";
				if(parseAddPronoun) {
					parseAddPronoun = false;
					determiner = UtilText.generateSingularDeterminer(descriptor)+" ";
				}
				
				if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
					switch(character.getFemininity()) {
						case FEMININE_STRONG:
							descriptor = "[style.colourFeminineStrong("+descriptor+")]";
							break;
						case FEMININE:
							descriptor = "[style.colourFeminine("+descriptor+")]";
							break;
						case ANDROGYNOUS:
							descriptor = "[style.colourAndrogynous("+descriptor+")]";
							break;
						case MASCULINE:
							descriptor = "[style.colourMasculine("+descriptor+")]";
							break;
						case MASCULINE_STRONG:
							descriptor = "[style.colourMasculineStrong("+descriptor+")]";
							break;
					}
				}
				
				return determiner + descriptor;
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
						return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_"+area+"</i>";
					} else {
						try {
							return character.getHighestZLayerCoverableArea(area).getName();
						} catch(Exception ex) {
							return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
						}
					}
					
				} else {
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
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
						return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_"+area+"</i>";
					} else {
						try {
							return character.getLowestZLayerCoverableArea(area).getName();
						} catch(Exception ex) {
							return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
						}
					}
					
				} else {
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
				}
			}
			
			@Override
			public String getArgumentExample() {
				return "pussy";
			}
		});
		
		
		// Styles & non-character parsing:
		
		for(Gender gender : Gender.values()) {
			commandsList.add(new ParserCommand(
					Util.newArrayListOfValues(
							gender.getType()==PronounType.FEMININE
								?gender.getGenderName().getFeminine()
								:(gender.getType()==PronounType.MASCULINE
									?gender.getGenderName().getMasculine()
									:gender.getGenderName().getNeutral())),
					true,
					true,
					"",
					"Returns this gender name (based on user settings)."){
				@Override
				public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
					return gender.getName();
				}
			});
		}
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"evening",
						"afternoon",
						"morning"),
				true,
				true,
				"",
				"Returns 'morning' in the morning, 'afternoon' in the afternoon, or 'evening' in the evening and night."){
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
		for(Colour c : PresetColour.getAllPresetColours()) {
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
						"Formats the argument text into coloured text."){
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null) {
							if(c.getRainbowColours()!=null) {
								StringBuilder sb = new StringBuilder();
								
								int i=0;
								int openBrackets = 0;
								char[] characters = arguments.toCharArray();
								for(char ch : characters) {
									if(ch=='<') {
										openBrackets++;
									}
									if(openBrackets==0) {
										sb.append("<span style='color:"+c.getRainbowColours().get(i%c.getRainbowColours().size())+";'>");
											sb.append(ch);
										sb.append("</span>");
										i++;
									} else {
										sb.append(ch);
									}
									if(ch=='>') {
										openBrackets--;
									}
								}
								
								return sb.toString();
								
							} else {
								return "<span style='color:"+c.toWebHexString()+";'>"+arguments+"</span>";
							}
						} else {
							return "<span style='color:"+c.toWebHexString()+";'>...</span>";
						}
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
						"Formats the argument text into bold, coloured text."){
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null) {
							if(c.getRainbowColours()!=null) {
								StringBuilder sb = new StringBuilder();
								
								int i=0;
								int openBrackets = 0;
								char[] characters = arguments.toCharArray();
								for(char ch : characters) {
									if(ch=='<') {
										openBrackets++;
									}
									if(openBrackets==0) {
										sb.append("<b style='color:"+c.getRainbowColours().get(i%c.getRainbowColours().size())+";'>");
											sb.append(ch);
										sb.append("</b>");
										i++;
									} else {
										sb.append(ch);
									}
									if(ch=='>') {
										openBrackets--;
									}
								}
								
								return sb.toString();
								
							} else {
								return "<b style='color:"+c.toWebHexString()+";'>"+arguments+"</b>";
							}
						} else {
							return "<b style='color:"+c.toWebHexString()+";'>...</b>";
						}
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
						"Formats the argument text into italicised, coloured text."){
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null) {
							if(c.getRainbowColours()!=null) {
								StringBuilder sb = new StringBuilder();
								
								int i=0;
								int openBrackets = 0;
								char[] characters = arguments.toCharArray();
								for(char ch : characters) {
									if(ch=='<') {
										openBrackets++;
									}
									if(openBrackets==0) {
										sb.append("<i style='color:"+c.getRainbowColours().get(i%c.getRainbowColours().size())+";'>");
											sb.append(ch);
										sb.append("</i>");
										i++;
									} else {
										sb.append(ch);
									}
									if(ch=='>') {
										openBrackets--;
									}
								}
								
								return sb.toString();
								
							} else {
								return "<i style='color:"+c.toWebHexString()+";'>"+arguments+"</i>";
							}
						} else {
							return "<i style='color:"+c.toWebHexString()+";'>...</i>";
						}
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
						"currentTime"),
				true,
				false,
				"",
				"Returns the time of day, in hours and minutes.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.time(Main.game.getDateNow());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"time"),
				true,
				false,
				"(hours to convert)",
				"Returns the converted hours into a time reading.") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return "NaN";
				}
				double time = Double.valueOf(arguments);
				LocalDateTime now = Main.game.getDateNow();
				return Units.time(LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), (int) time, Math.min(59, (int)((time%1)*60))));
			}
		});
		
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
				Util.newArrayListOfValues("hair", "feather"),
				Util.newArrayListOfValues("hairs", "feathers"),
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
				Util.newArrayListOfValues("skin"), // Will usually return the plural anyway...
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
				"Returns a descriptor of this character's ass size.",
				BodyPartType.ASS){
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
				"Returns a descriptor of this character's asshole capacity.",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getAssStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assDepth",
						"assholeDepth"),
				true,
				true,
				"",
				"Returns a descriptor of this character's asshole depth.",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assElasticity",
						"assholeElasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's asshole elasticity.",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assPlasticity",
						"assholePlasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's asshole plasticity.",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssPlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assWetness",
						"assholeWetness"),
				true,
				true,
				"",
				"Returns a descriptor of this character's asshole wetness.",
				BodyPartType.ASS){
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
				return getSkinName(character.getTorsoType(), character);
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
				return getSkinNameWithDescriptor(character.getTorsoType(), character.getCovering(character.getTorsoType().getBodyCoveringType(character)), character);
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
				"Returns the name of this character's cup size. (e.g. 'AA', 'B', 'DD')",
				BodyPartType.BREAST){
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
				"Returns a descriptor of this character's nipple capacity.",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getNippleStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastDepth",
						"breastsDepth",
						"titDepth",
						"titsDepth",
						"boobDepth",
						"boobsDepth"),
				true,
				true,
				"",
				"Returns a descriptor of this character's nipple depth.",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleDepth().getDescriptor();
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
				"Returns a descriptor of this character's nipple elasticity.",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastPlasticity",
						"breastsPlasticity",
						"titPlasticity",
						"titsPlasticity",
						"boobPlasticity",
						"boobsPlasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's nipple plasticity.",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNipplePlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastRows",
						"nippleRows"),
				true,
				true,
				"",
				"Returns the number of this character's breast rows, formatted as a word + 'pairs of'. (e.g. 'pair of', 'three pairs of')",
				BodyPartType.BREAST){
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
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"milkFlavour",
						"flavourMilk"),
				false,
				false,
				"",
				"Returns the name of the flavour of this character's milk.",
				BodyPartType.MILK){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getMilk().getFlavour().getName();
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
				"Returns the name of this character's crotch-boob cup size. (e.g. 'AA', 'B', 'DD')",
				BodyPartType.BREAST){
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
				"Returns a descriptor of this character's crotch-nipple capacity.",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getNippleCrotchStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderDepth",
						"uddersDepth",
						"crotchBreastDepth",
						"crotchBreastsDepth",
						"crotchTitDepth",
						"crotchTitsDepth",
						"crotchBoobDepth",
						"crotchBoobsDepth"),
				true,
				true,
				"",
				"Returns a descriptor of this character's crotch-nipple depth.",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleCrotchDepth().getDescriptor();
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
				"Returns a descriptor of this character's crotch-nipple elasticity.",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleCrotchElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderPlasticity",
						"uddersPlasticity",
						"crotchBreastPlasticity",
						"crotchBreastsPlasticity",
						"crotchTitPlasticity",
						"crotchTitsPlasticity",
						"crotchBoobPlasticity",
						"crotchBoobsPlasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's crotch-nipple plasticity.",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleCrotchPlasticity().getDescriptor();
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
				"Returns the number of this character's crotch-boob rows, formatted as a word + 'pairs of'. (e.g. 'pair of', 'three pairs of')",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getBreastCrotchRows()==0) {
					return "single";
				} else if(character.getBreastCrotchRows()==1) {
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
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderMilkFlavour",
						"flavourUdderMilk",
						"crotchMilkFlavour",
						"flavourCrotchMilk"),
				false,
				false,
				"",
				"Returns the name of the flavour of this character's milk.",
				BodyPartType.MILK){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getMilkCrotch().getFlavour().getName();
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
						"faceCapacity",
						"throatCapacity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's throat capacity.",
				BodyPartType.FACE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getFaceStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"faceDepth",
						"throatDepth"),
				true,
				true,
				"",
				"Returns a descriptor of this character's throat depth.",
				BodyPartType.FACE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getFaceDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"faceElasticity",
						"throatElasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's throat elasticity.",
				BodyPartType.FACE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getFaceElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"facePlasticity",
						"throatPlasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's throat plasticity.",
				BodyPartType.FACE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getFacePlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"faceWetness",
						"throatWetness"),
				true,
				true,
				"",
				"Returns a descriptor of this character's throat wetness.",
				BodyPartType.FACE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getFaceWetness().getDescriptor();
			}
		});
		
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
						"hairDeterminer"),
				true,
				false,
				"",
				"The determiner for the hair type of the character. Will usually be 'a head of'.",
				BodyPartType.HAIR){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHairType().getDeterminer(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hairLength"),
				true,
				true,
				"",
				"Returns a descriptor of this character's hair length.",
				BodyPartType.HAIR){
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
						"urethra",
						"penisUrethra",
						"cockUrethra",
						"urethraPenis",
						"urethraCock"),
				true,
				true,
				"",
				"Returns the name of this character's urethra.",
				BodyPartType.PENIS){
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
				"Returns the name of this character's urethra, with a descriptor appended in front of it.",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getPenisUrethraDescriptor(), "urethra");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethraCapacity",
						"cockUrethraCapacity",
						"urethraPenisCapacity",
						"urethraCockCapacity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's penile urethra capacity.",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getPenisStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethraDepth",
						"cockUrethraDepth",
						"urethraPenisDepth",
						"urethraCockDepth"),
				true,
				true,
				"",
				"Returns a descriptor of this character's penile urethra depth.",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getUrethraDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethraElasticity",
						"cockUrethraElasticity",
						"urethraPenisElasticity",
						"urethraCockElasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's penile urethra elasticity.",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getUrethraElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethraPlasticity",
						"cockUrethraPlasticity",
						"urethraPenisPlasticity",
						"urethraCockPlasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's penile urethra plasticity.",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getUrethraPlasticity().getDescriptor();
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
				"Returns a descriptor of the amount of cum that this character's balls store when full.",
				BodyPartType.CUM){
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
				"Returns the numerical value of the amount of cum that this character's balls store when full.",
				BodyPartType.CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.fluid(character.getPenisRawCumStorageValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cumFlavour",
						"flavourCum"),
				false,
				false,
				"",
				"Returns the name of the flavour of this character's cum.",
				BodyPartType.CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCum().getFlavour().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"precum"),
				false,
				false,
				"",
				"Returns the name of this character's precum.",
				BodyPartType.CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "precum";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"precum+",
						"precumD"),
				false,
				false,
				"",
				"Returns the name of this character's precum, with a descriptor appended to the front.",
				BodyPartType.CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(!character.getCumModifiers().isEmpty()) {
					return character.getCumModifiers().get(Util.random.nextInt(character.getCumModifiers().size())).getName() + " precum";
				}
				
				return "precum";
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
						"cockDescriptive",
						"penisDescriptive",
						"dickDescriptive"),
				true,
				true,
				"(coloured)",
				"Returns a description of the character's penis, in the format: 'size', 'girth', 'colour' 'feralRace'-cock."
				+ "<br/>If the 'size' or 'girth' are average, those descriptors are omitted."
				+ "<br/>Pass in 'true' if you want the output coloured.",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				StringBuilder sb = new StringBuilder();
				boolean coloured = arguments!=null && arguments.equalsIgnoreCase("true");
				
				// Length:
				if(character.getPenisSize()!=PenisLength.TWO_AVERAGE) {
					if(coloured) {
						sb.append("<span style='color:"+character.getPenisSize().getColour().toWebHexString()+";'>");
					}
					sb.append(character.getPenisSize().getDescriptor());
					if(coloured) {
						sb.append("</span>");
					}
				}

				// Girth:
				if(character.getPenisGirth()!=PenetrationGirth.THREE_AVERAGE) {
					if(sb.length()>0) {
						sb.append(", ");	
					}
					if(coloured) {
						sb.append("<span style='color:"+character.getPenisGirth().getColour().toWebHexString()+";'>");
					}
					sb.append(character.getPenisGirth().getName());
					if(coloured) {
						sb.append("</span>");
					}
				}

				// Colour:
				if(sb.length()>0) {
					sb.append(", ");
				}
				sb.append(character.getCovering(character.getPenisCovering()).getColourDescriptor(character, coloured, false));
				
				// Race:
				sb.append(" ");
				if(coloured) {
					sb.append("<span style='color:"+character.getPenisRace().getColour().toWebHexString()+";'>");
				}
				if(character.getPenisRace()!=Race.HUMAN) {
					sb.append(character.getPenisRace().getName(true)+"-cock");
					
				} else {
					sb.append("human cock");
				}
				if(coloured) {
					sb.append("</span>");
				}
				return sb.toString();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisSize",
						"cockSize",
						"dickSize",
						"penisLength",
						"cockLength",
						"dickLength"),
				true,
				true,
				"",
				"Returns the length of the character's penis, in the metric or imperial units as defined in user settings.",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPenisSize().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisCircumference",
						"cockCircumference",
						"dickCircumference"),
				true,
				true,
				"(pluralUnits)",
				"Returns the circumference of the character's penis, in the metric or imperial units as defined in user settings."
						+ " Pass in 'true' as an argument if you want the resulting units to be pluralised (i.e. 'inches' instead of 'inch').",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getPenisCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getPenisCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisDiameter",
						"cockDiameter",
						"dickDiameter",
						"penisDiametre",
						"cockDiametre",
						"dickDiametre"),
				true,
				true,
				"(pluralUnits)",
				"Returns the diameter of the character's penis, in the metric or imperial units as defined in user settings."
						+ " Pass in 'true' as an argument if you want the resulting units to be pluralised (i.e. 'inches' instead of 'inch').",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getPenisDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getPenisDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
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
				Util.newArrayListOfValues(
						"cockValue",
						"cockLengthValue",
						"penisValue",
						"penisLengthValue"),
				false,
				false,
				"(short)",
				"Returns the localized, formatted size of the penis with long singular units ('centimetre'). Pass in true to return as short measurement ('cm').",
				BodyPartType.PENIS) {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && arguments.equalsIgnoreCase("true")) {
					return Units.size(character.getPenisRawSizeValue(), Units.ValueType.NUMERIC, Units.UnitType.SHORT);
				}
				return Units.size(character.getPenisRawSizeValue(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
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
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailGirth",
						"tailsGirth"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTailGirthDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailBaseCircumference"),
				true,
				true,
				"(pluralUnits)",
				"Returns the circumference of the character's tail, as measured at the base, in the metric or imperial units as defined in user settings."
						+ "  Pass in 'true' as an argument if you want the resulting units to be pluralised (i.e. 'inches' instead of 'inch').",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTailBaseCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTailBaseCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailTipCircumference"),
				true,
				true,
				"(pluralUnits)",
				"Returns the circumference of the character's tail, as measured at the very tip, in the metric or imperial units as defined in user settings."
						+ "  Pass in 'true' as an argument if you want the resulting units to be pluralised (i.e. 'inches' instead of 'inch').",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTailCircumference(character.getTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTailCircumference(character.getTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailBaseDiameter",
						"tailBaseDiametre"),
				true,
				true,
				"(pluralUnits)",
				"Returns the diameter of the character's tail, as measured at the base, in the metric or imperial units as defined in user settings."
						+ "  Pass in 'true' as an argument if you want the resulting units to be pluralised (i.e. 'inches' instead of 'inch').",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTailBaseDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTailBaseDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailTipDiameter",
						"tailTipDiametre"),
				true,
				true,
				"(pluralUnits)",
				"Returns the diameter of the character's tail, as measured at the very tip, in the metric or imperial units as defined in user settings."
						+ "  Pass in 'true' as an argument if you want the resulting units to be pluralised (i.e. 'inches' instead of 'inch').",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTailDiameter(character.getTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTailDiameter(character.getTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailLength",
						"tailSize"),
				true,
				true,
				"(pluralUnits)",
				"Returns the length of the character's tail, in the metric or imperial units as defined in user settings."
						+ "  Pass in 'true' as an argument if you want the resulting units to be pluralised (i.e. 'inches' instead of 'inch').",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailPenetrationLength",
						"tailPenetrationSize"),
				true,
				true,
				"(pluralUnits)",
				"Returns the length of the character's tail which is used in penetrations (80% of total length), in the metric or imperial units as defined in user settings."
						+ "  Pass in 'true' as an argument if you want the resulting units to be pluralised (i.e. 'inches' instead of 'inch').",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTailLength(true), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTailLength(true), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
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
				"Returns the name of this character's vaginal urethra.",
				BodyPartType.VAGINA){
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
				"Returns the name of this character's vaginal urethra, with a descriptor appended in front of it.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getVaginaUrethraDescriptor(), "urethra");
			}
		});
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethraCapacity",
						"vaginaUrethraCapacity",
						"urethraPussyCapacity",
						"urethraVaginaCapacity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's vaginal urethra capacity.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getVaginaUrethraStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethraDepth",
						"vaginaUrethraDepth",
						"urethraPussyDepth",
						"urethraVaginaDepth"),
				true,
				true,
				"",
				"Returns a descriptor of this character's vaginal urethra depth.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaUrethraDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethraElasticity",
						"vaginaUrethraElasticity",
						"urethraPussyElasticity",
						"urethraVaginaElasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's vaginal urethra elasticity.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaUrethraElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethraPlasticity",
						"vaginaUrethraPlasticity",
						"urethraPussyPlasticity",
						"urethraVaginaPlasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's vaginal urethra plasticity.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaUrethraPlasticity().getDescriptor();
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
				"Returns a descriptor of this character's vagina capacity.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getVaginaStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"vaginaDepth",
						"pussyDepth",
						"cuntDepth"),
				true,
				true,
				"",
				"Returns a descriptor of this character's vagina depth.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaDepth().getDescriptor();
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
				"Returns a descriptor of this character's vagina elasticity.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"vaginaPlasticity",
						"pussyPlasticity",
						"cuntPlasticity"),
				true,
				true,
				"",
				"Returns a descriptor of this character's vagina plasticity.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaPlasticity().getDescriptor();
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
				"Returns a descriptor of this character's vagina wetness.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaWetness().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"girlcumFlavour",
						"flavourGirlcum"),
				false,
				false,
				"",
				"Returns the name of the flavour of this character's girlcum.",
				BodyPartType.GIRL_CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getGirlcum().getFlavour().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"labiaSize"),
				true,
				true,
				"",
				"Returns a descriptor of this character's labia size.",
				BodyPartType.VAGINA){
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
				"Returns a desriptor of the size of the character's clitoris.",
				BodyPartType.VAGINA){
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
				"Returns the localised, formatted size of the clitoris with long units.",
				BodyPartType.VAGINA){
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
				"Returns a descriptor of the character's clitoris girth.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getClitorisGirth().getName();
			}
		});
		
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVagina",
						"toyPussy"),
				true,
				true,
				"",
				"Returns a name for the toy inserted into this character's vagina.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return "toy";
				}
				return toy.getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVagina+",
						"toyVaginaD",
						"toyPussy+",
						"toyPussyD"),
				true,
				true,
				"",
				"Returns a name for the toy inserted into this character's vagina, with descriptor (describing the toy's colour, girth, or length).",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return "toy";
				}
				AbstractClothingType toyType = toy.getClothingType();
				PenisLength length = PenisLength.getPenisLengthFromInt(toyType.getPenetrationSelfLength());
				PenetrationGirth girth = PenetrationGirth.getGirthFromInt(toyType.getPenetrationSelfGirth());
				return applyDescriptor(UtilText.returnStringAtRandom(length.getDescriptor(), girth.getName(), toy.getColourName()), toy.getName());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaHead",
						"toyVaginaTip",
						"toyPussyHead",
						"toyPussyTip"),
				true,
				true,
				"",
				"Returns a name for the tip of the toy inserted into this character's vagina.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return UtilText.returnStringAtRandom("head", "tip");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaHead+",
						"toyVaginaTip+",
						"toyPussyHead+",
						"toyPussyTip+"),
				true,
				true,
				"",
				"Returns a name for the tip of the toy inserted into this character's vagina, with descriptor (describing the toy's colour).",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("head", "tip");
				}
				String descriptor = toy.getColour(0).getName();
				return applyDescriptor(descriptor, UtilText.returnStringAtRandom("head", "tip"));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaDescriptive",
						"toyPussyDescriptive"),
				true,
				true,
				"(coloured)",
				"Returns a description of the toy inside this character's vagina, in the format: 'size', 'girth', 'name'."
				+ "<br/>If the 'size' or 'girth' are average, those descriptors are omitted."
				+ "<br/>Pass in 'true' if you want the output coloured.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				StringBuilder sb = new StringBuilder();
				boolean coloured = arguments!=null && arguments.equalsIgnoreCase("true");
				
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("toy");
				}
				AbstractClothingType toyType = toy.getClothingType();
				
				// Length:
				PenisLength length = PenisLength.getPenisLengthFromInt(toyType.getPenetrationSelfLength());
				if(character.getPenisSize()!=PenisLength.TWO_AVERAGE) {
					if(coloured) {
						sb.append("<span style='color:"+length.getColour().toWebHexString()+";'>");
					}
					sb.append(length.getDescriptor());
					if(coloured) {
						sb.append("</span>");
					}
				}

				// Girth:
				PenetrationGirth girth = PenetrationGirth.getGirthFromInt(toyType.getPenetrationSelfGirth());
				if(character.getPenisGirth()!=PenetrationGirth.THREE_AVERAGE) {
					if(sb.length()>0) {
						sb.append(", ");	
					}
					if(coloured) {
						sb.append("<span style='color:"+girth.getColour().toWebHexString()+";'>");
					}
					sb.append(girth.getName());
					if(coloured) {
						sb.append("</span>");
					}
				}

				// Colour:
				if(sb.length()>0) {
					sb.append(", ");
				}
				sb.append(toy.getDisplayName(coloured, false));
				
				return sb.toString();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaSize",
						"toyPussySize",
						"toyVaginaLength",
						"toyPussyLength"),
				true,
				true,
				"",
				"Returns the length of the toy inserted in this character's vagina, in the metric or imperial units as defined in user settings.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("nonexistant");
				}
				PenisLength length = PenisLength.getPenisLengthFromInt(toy.getClothingType().getPenetrationSelfLength());
				return length.getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaCircumference",
						"toyPussyCircumference"),
				true,
				true,
				"(pluralUnits)",
				"Returns the circumference of the toy inserted in this character's vagina, in the metric or imperial units as defined in user settings."
						+ " Pass in 'true' as an argument if you want the resulting units to be pluralised (i.e. 'inches' instead of 'inch').",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("N/A");
				}
				float circumference = (float) (Penis.getGenericDiameter(toy.getClothingType().getPenetrationSelfLength(), PenetrationGirth.getGirthFromInt(toy.getClothingType().getPenetrationSelfGirth())) * Math.PI);
				if(arguments!=null && arguments.equalsIgnoreCase("true")) {
					return Units.size(circumference, Units.ValueType.NUMERIC, Units.UnitType.LONG);
				}
				return Units.size(circumference, Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaDiameter",
						"toyPussyDiameter",
						"toyVaginaDiametre",
						"toyPussyDiametre"),
				true,
				true,
				"(pluralUnits)",
				"Returns the diameter of the toy inserted in this character's vagina, in the metric or imperial units as defined in user settings."
						+ " Pass in 'true' as an argument if you want the resulting units to be pluralised (i.e. 'inches' instead of 'inch').",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("N/A");
				}
				float diameter = Penis.getGenericDiameter(toy.getClothingType().getPenetrationSelfLength(), PenetrationGirth.getGirthFromInt(toy.getClothingType().getPenetrationSelfGirth()));
				if(arguments!=null &&  arguments.equalsIgnoreCase("true")) {
					return Units.size(diameter, Units.ValueType.NUMERIC, Units.UnitType.LONG);
				}
				return Units.size(diameter, Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaGirth",
						"toyPussyGirth"),
				true,
				true,
				"",
				"Returns a descriptor of the girth of the toy inserted in this character's vagina.",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("N/A");
				}
				return PenetrationGirth.getGirthFromInt(toy.getClothingType().getPenetrationSelfGirth()).getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaValue",
						"toyVaginaLengthValue",
						"toyPussyValue",
						"toyPussyLengthValue"),
				false,
				false,
				"(short)",
				"Returns the localised, formatted size of the toy inserted in this character's vagina with long singular units ('centimetre'). Pass in true to return as short measurement ('cm').",
				BodyPartType.VAGINA) {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("N/A");
				}
				int length = toy.getClothingType().getPenetrationSelfLength();
				if(arguments!=null && arguments.equalsIgnoreCase("true")) {
					return Units.size(length, Units.ValueType.NUMERIC, Units.UnitType.SHORT);
				}
				return Units.size(length, Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
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
				return character.getTailType().getTailTipNameSingular(character);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailHeads",
						"tailTips"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTailType().getTailTipNamePlural(character);
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
				return applyDescriptor(character.getTailType().getTailTipDescriptor(character), character.getTailType().getTailTipNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailHeads+",
						"tailHeadsD",
						"tailTips+",
						"tailTipsD"),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getTailType().getTailTipDescriptor(character), character.getTailType().getTailTipNamePlural(character));
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
//					System.err.println("Parsing error: Could not initialise npc");
				}
			}
			
			if(Main.game.isStarted() && Main.game.getPlayer().hasCompanions()) {
				for(int i = 0; i<Main.game.getPlayer().getCompanions().size(); i++) {
					if(i==0) {
						engine.put("com", Main.game.getPlayer().getCompanions().get(i));
					}
					engine.put("com"+(i+1), Main.game.getPlayer().getCompanions().get(i));
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
				return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>(Error in script parsing!)</i>";
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
			
			if(Main.game.getPlayer().hasCompanions()) {
				for(int i = 0; i<Main.game.getPlayer().getCompanions().size(); i++) {
					if(i==0) {
						engine.put("com", Main.game.getPlayer().getCompanions().get(i));
					}
					engine.put("com"+(i+1), Main.game.getPlayer().getCompanions().get(i));
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
			return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Error: parserTarget.getCharacter() not found! ("+target+")</i>";
		}
		
		// Commands with arguments:
		ParserCommand cmd = findCommandWithTag(command.replaceAll("\u200b", ""));
		if (cmd == null) {
			return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>command_unknown</i>";
		}


		String output = cmd.parse(specialNPCs, command, arguments, target, character);
		if(suppressOutput) {
			return "";
		}
		parseCapitalise = parseCapitalise && cmd.isAllowsCapitalisation();
		parseAddPronoun = parseAddPronoun && cmd.isAllowsPronoun();
		
		if(parseAddPronoun) {
			output = generateSingularDeterminer(output)+" "+output;
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
		
		// Parser targets:
		for(ParserTarget target : ParserTarget.values()) {
			if(target!=ParserTarget.STYLE && target!=ParserTarget.UNIT && target!=ParserTarget.NPC && target!=ParserTarget.COMPANION) {
				for(String tag : target.getTags()) {
					engine.put(tag, target.getCharacter(tag, null));
				}
			}
		}
//		for(int i=0; i<specialParsingStrings.size(); i++) {
//			engine.put("SPECIAL_PARSE_"+i, specialParsingStrings.get(i));
//		}
		
		// Core classes:
		engine.put("game", Main.game);
		engine.put("sex", Main.sex);
		engine.put("properties", Main.getProperties());
		engine.put("RND", Util.random);
		
		// Java classes:
		for(DayOfWeek dayOfWeek : DayOfWeek.values()) {
			engine.put("DOW_"+dayOfWeek, dayOfWeek);
		}
		
		// Items:
		for(AbstractWeaponType weaponType : WeaponType.getAllWeapons()) {
			engine.put("WEAPON_"+WeaponType.getIdFromWeaponType(weaponType), weaponType);
		}
		for(AbstractClothingType clothingType : ClothingType.getAllClothing()) {
			engine.put("CLOTHING_"+ClothingType.getIdFromClothingType(clothingType), clothingType);
			engine.put("CT_"+ClothingType.getIdFromClothingType(clothingType), clothingType);
		}
		for(AbstractItemType itemType : ItemType.getAllItems()) {
			engine.put("ITEM_"+ItemType.getIdFromItemType(itemType), itemType);
		}
		for(AbstractSetBonus setBonus : SetBonus.getAllSetBonuses()) {
			engine.put("SET_BONUS_"+SetBonus.getIdFromSetBonus(setBonus), setBonus);
		}
		for(ItemTag it : ItemTag.values()) {
			engine.put("ITEM_TAG_"+it.toString(), it);
		}
		for(CoverableArea ca : CoverableArea.values()) {
			engine.put("CA_"+ca.toString(), ca);
		}
		for(InventorySlot is : InventorySlot.values()) {
			engine.put("IS_"+is.toString(), is);
		}
		
		// Misc.:
		for(Colour colour : PresetColour.getAllPresetColours()) {
			engine.put("COLOUR_"+PresetColour.getIdFromColour(colour), colour);
		}
		
		// Bodies:
		for(Race race : Race.values()) {
			engine.put("RACE_"+race.toString(), race);
		}
		for(AbstractRacialBody racialBody : RacialBody.getAllRacialBodies()) {
			engine.put("RACIAL_BODY_"+RacialBody.getIdFromRacialBody(racialBody), racialBody);
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
		for(GenitalArrangement genArrangement : GenitalArrangement.values()) {
			engine.put("GENITALS_"+genArrangement.toString(), genArrangement);
		}
		for(BodyMaterial material : BodyMaterial.values()) {
			engine.put("BODY_MATERIAL_"+material.toString(), material);
		}
		for(BodyCoveringType bct : BodyCoveringType.values()) {
			engine.put("BODY_COVERING_TYPE_"+bct.toString(), bct);
		}
		for(NippleShape nippleShape : NippleShape.values()) {
			engine.put("NIPPLE_SHAPE_"+nippleShape.toString(), nippleShape);
		}
		// Types:
		for(AbstractFluidType fluidType : FluidType.getAllFluidTypes()) {
			engine.put("FLUID_TYPE_"+FluidType.getIdFromFluidType(fluidType), fluidType);
		}
		for(AbstractAntennaType type : AntennaType.getAllAntennaTypes()) {
			engine.put("ANTENNA_TYPE_"+AntennaType.getIdFromAntennaType(type), type);
		}
		for(AbstractAnusType type : AnusType.getAllAnusTypes()) {
			engine.put("ANUS_TYPE_"+AnusType.getIdFromAnusType(type), type);
		}
		for(AbstractArmType type : ArmType.getAllArmTypes()) {
			engine.put("ARM_TYPE_"+ArmType.getIdFromArmType(type), type);
		}
		for(AbstractAssType type : AssType.getAllAssTypes()) {
			engine.put("ASS_TYPE_"+AssType.getIdFromAssType(type), type);
		}
		for(AbstractBreastType type : BreastType.getAllBreastTypes()) {
			engine.put("BREAST_TYPE_"+BreastType.getIdFromBreastType(type), type);
		}
		for(AbstractEarType type : EarType.getAllEarTypes()) {
			engine.put("EAR_TYPE_"+EarType.getIdFromEarType(type), type);
		}
		for(AbstractEyeType type : EyeType.getAllEyeTypes()) {
			engine.put("EYE_TYPE_"+EyeType.getIdFromEyeType(type), type);
		}
		for(AbstractFaceType type : FaceType.getAllFaceTypes()) {
			engine.put("FACE_TYPE_"+FaceType.getIdFromFaceType(type), type);
		}
		for(AbstractFootType type : FootType.getAllFootTypes()) {
			engine.put("FOOT_TYPE_"+FootType.getIdFromFootType(type), type);
		}
		for(AbstractHairType type : HairType.getAllHairTypes()) {
			engine.put("HAIR_TYPE_"+HairType.getIdFromHairType(type), type);
		}
		for(AbstractHornType type : HornType.getAllHornTypes()) {
			engine.put("HORN_TYPE_"+HornType.getIdFromHornType(type), type);
		}
		for(AbstractLegType type : LegType.getAllLegTypes()) {
			engine.put("LEG_TYPE_"+LegType.getIdFromLegType(type), type);
		}
		for(AbstractMouthType type : MouthType.getAllMouthTypes()) {
			engine.put("MOUTH_TYPE_"+MouthType.getIdFromMouthType(type), type);
		}
		for(AbstractNippleType type : NippleType.getAllNippleTypes()) {
			engine.put("NIPPLE_TYPE_"+NippleType.getIdFromNippleType(type), type);
		}
		for(AbstractPenisType type : PenisType.getAllPenisTypes()) {
			engine.put("PENIS_TYPE_"+PenisType.getIdFromPenisType(type), type);
		}
		for(AbstractTorsoType type : TorsoType.getAllTorsoTypes()) {
			engine.put("TORSO_TYPE_"+TorsoType.getIdFromTorsoType(type), type);
		}
		for(AbstractTailType type : TailType.getAllTailTypes()) {
			engine.put("TAIL_TYPE_"+TailType.getIdFromTailType(type), type);
		}
		for(AbstractTentacleType type : TentacleType.getAllTentacleTypes()) {
			engine.put("TENTACLE_TYPE_"+TentacleType.getIdFromTentacleType(type), type);
		}
		for(AbstractTesticleType type : TesticleType.getAllTesticleTypes()) {
			engine.put("TESTICLE_TYPE_"+TesticleType.getIdFromTesticleType(type), type);
		}
		for(AbstractTongueType type : TongueType.getAllTongueTypes()) {
			engine.put("TONGUE_TYPE_"+TongueType.getIdFromTongueType(type), type);
		}
		for(AbstractVaginaType type : VaginaType.getAllVaginaTypes()) {
			engine.put("VAGINA_TYPE_"+VaginaType.getIdFromVaginaType(type), type);
		}
		for(AbstractWingType type : WingType.getAllWingTypes()) {
			engine.put("WING_TYPE_"+WingType.getIdFromWingType(type), type);
		}
		
		
		// Effects & persona:
		for(Fetish f : Fetish.values()) {
			engine.put(f.toString(), f);
		}
		for(FetishDesire fetishDesire : FetishDesire.values()) {
			engine.put("FETISH_DESIRE_"+fetishDesire.toString(), fetishDesire);
		}
		for(PersonalityTrait personalityTrait : PersonalityTrait.values()) {
			engine.put("PERSONALITY_TRAIT_"+personalityTrait.toString(), personalityTrait);
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
		for(AbstractStatusEffect sa : StatusEffect.getAllStatusEffects()) {
			engine.put("SE_"+StatusEffect.getIdFromStatusEffect(sa), sa);
		}
		for(Attribute att : Attribute.values()) {
			engine.put("ATTRIBUTE_"+att.toString(), att);
		}
		
		// Combat:
		for(DamageType damageType : DamageType.values()) {
			engine.put("DAMAGE_TYPE_"+damageType.toString(), damageType);
		}
		for(SpellSchool spellSchool : SpellSchool.values()) {
			engine.put("SPELL_SCHOOL_"+spellSchool.toString(), spellSchool);
		}
		for(Spell spell: Spell.values()) {
			engine.put("SPELL_"+spell.toString(), spell);
		}
		for(SpellUpgrade spellUpgrade: SpellUpgrade.values()) {
			engine.put("SPELL_UPGRADE_"+spellUpgrade.toString(), spellUpgrade);
		}
		
		// Sex:
		for(SexParticipantType particiantType : SexParticipantType.values()) {
			engine.put("SEX_PT_"+particiantType.toString(), particiantType);
		}
		for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
			engine.put("ORIFICE_"+orifice.toString(), orifice);
		}
		for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
			engine.put("PENETRATION_"+penetration.toString(), penetration);
		}
		for(GenericSexFlag flag : GenericSexFlag.values()) {
			engine.put("SEX_FLAG_"+flag.toString(), flag);
		}
		
		// Other:
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
		for(AbstractWorldType worldType : WorldType.getAllWorldTypes()) {
			engine.put("WORLD_TYPE_"+WorldType.getIdFromWorldType(worldType), worldType);
		}
		for(AbstractPlaceType placeType : PlaceType.getAllPlaceTypes()) {
			engine.put("PLACE_TYPE_"+PlaceType.getIdFromPlaceType(placeType), placeType);
		}
		for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
			engine.put("PLACE_UPGRADE_"+upgrade.toString(), upgrade);
		}

		
		
		// static methods don't work unless initialised like so:
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
	
	private static String parseConditionalSyntaxNew(List<GameCharacter> specialNPCs, Map<String, String> conditionals, boolean hasXmlVariables) {
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
		
		if(Main.game.getPlayer().hasCompanions()) {
			for(int i = 0; i<Main.game.getPlayer().getCompanions().size(); i++) {
				if(i==0) {
					engine.put("com", Main.game.getPlayer().getCompanions().get(i));
				}
				engine.put("com"+(i+1), Main.game.getPlayer().getCompanions().get(i));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(Entry<String, String> entry : conditionals.entrySet()) {
			sb.setLength(0);
			
			if(hasXmlVariables) {
				for(String s : parserVariableCalls) {
					sb.append(s+";");
				}
			}
			sb.append(entry.getKey());
			
			String conditionalStatement = sb.toString();
			
			try {
				if((boolean) engine.eval(conditionalStatement)){
					return UtilText.parse(specialNPCs, entry.getValue(), false);
				}
				
			} catch (ScriptException e) {
				System.err.println("Conditional parsing (from Map) error: "+conditionalStatement+" | Size of variableCalls: "+parserVariableCalls.size());
				System.err.println(e.getMessage());
				e.printStackTrace();
				return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>(Error in conditional parsing!)</i>";
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
					return getBodyPartFromType(bodyPart,character).getType().getRace().getName(character, getBodyPartFromType(bodyPart, character).isBestial(character));
				} catch(Exception ex) {
					return "null_body_part";
				}
			}
		});

		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural,
						"raceFeral",
						"feralRace"),
				true,
				true,
				"",
				"Returns the name of the feral race that's associated with this body part. Race is *not* gender-specific (i.e. will return 'dog', not 'bitch').",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				try {
					return getBodyPartFromType(bodyPart,character).getType().getRace().getName(true);
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
				"(forceSingular)",
				"Returns the basic, singular name for this body part. Pass in true as an argument to prevent this name from being automatically adjusted to its plural equivalent, if applicable."
						+ " (e.g. A character with two horns would have the command 'horn(true)' output 'horn')",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if((arguments!=null && Boolean.valueOf(arguments)) && bodyPart!=BodyPartType.SKIN) {  // Skin replacements (such as scales, feathers, fur), should always use the default plurality.
					return getBodyPartFromType(bodyPart,character).getNameSingular(character);
				}
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart,character).getDeterminer(character), getBodyPartFromType(bodyPart,character).getName(character));
					
				} else {
					return getBodyPartFromType(bodyPart,character).getName(character);
				}
			}
		});

		commandsList.add(new ParserCommand(
				tagsPlural,
				true,
				true,
				"(forcePlurality)",
				"Returns the basic, plural name for this body part. Pass in true as an argument to prevent this name from being automatically adjusted to its singular equivalent, if applicable."
						+ " (e.g. A character with a single udder would have the command 'crotchBoobs(true)' output 'udders')",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return getBodyPartFromType(bodyPart,character).getNamePlural(character);
				}
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart,character).getDeterminer(character), getBodyPartFromType(bodyPart,character).getName(character));
					
				} else {
					return getBodyPartFromType(bodyPart,character).getName(character);
				}
			}
		});

		commandsList.add(new ParserCommand(
				getModifiedTags(tags, null, "+", "D"),
				true,
				true,
				"(forceSingular)",
				"Returns the singular name for this body part, with a descriptor appended to the start (if one is available)."
						+ " Pass in true as an argument to prevent this name from being automatically adjusted to its plural equivalent, if applicable."
						+ " (e.g. A character with two horns would have the command 'horn+(true)' output 'curved horn')",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				String name;
				if((arguments!=null && Boolean.valueOf(arguments)) && bodyPart!=BodyPartType.SKIN) {  // Skin replacements (such as scales, feathers, fur), should always use the default plurality.
					name = getBodyPartFromType(bodyPart,character).getNameSingular(character);
				} else {
					name = getBodyPartFromType(bodyPart,character).getName(character);
				}
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart,character).getDeterminer(character), applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), name));
					
				} else {
					return applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), name);
				}
			}
		});

		commandsList.add(new ParserCommand(
				getModifiedTags(null, tagsPlural, "+", "D"),
				true,
				true,
				"(forcePlurality)",
				"Returns the plural name for this body part, with a descriptor appended to the start (if one is available)."
						+ " Pass in true as an argument to prevent this name from being automatically adjusted to its singular equivalent, if applicable."
						+ " (e.g. A character with a single udder would have the command 'crotchBoobs+(true)' output 'big udders')",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				String name;
				if((arguments!=null && Boolean.valueOf(arguments))) {
					name = getBodyPartFromType(bodyPart,character).getNamePlural(character);
				} else {
					name = getBodyPartFromType(bodyPart,character).getName(character);
				}
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart,character).getDeterminer(character), applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), name));
					
				} else {
					return applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), name);
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
				return character.getBody().getTorso();
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
		if(descriptor==null) {
			return input;
		}
		
		return descriptor.length()>0
				? descriptor + " "
				: UtilText.generateSingularDeterminer(input) + input;
	}

	private static String getSubspeciesName(Subspecies subspecies, GameCharacter character) {
		if(subspecies==null) {
			return "";
		}
		
		if (character.isFeminine()) {
			return subspecies.getSingularFemaleName(character);
		} else {
			return subspecies.getSingularMaleName(character);
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
