package com.lilithsthrone.game.dialogue.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.parse.ParserCommand;
import com.lilithsthrone.parse.ParserTarget;
import com.lilithsthrone.utils.ArrayUtil;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia, Pimvgd
 */
public class UtilText {

	private static String modifiedSentence;
	public static StringBuilder transformationContentSB = new StringBuilder(4096);
	public static StringBuilder nodeContentSB = new StringBuilder(4096);
	private static StringBuilder descriptionSB = new StringBuilder();
	
	private static ScriptEngine engine;

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
		if(Main.game.getPlayer()==null) {
			return "";
		}
		
		modifiedSentence = text;
		if (Main.game.getPlayer().hasFetish(Fetish.FETISH_BIMBO))
			modifiedSentence = Util.addBimbo(text, 6);

		if (Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.MASCULINE_STRONG)
			return "<span class='thoughts masculine'>" + modifiedSentence + "</span>";
		else if (Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.ANDROGYNOUS)
			return "<span class='thoughts androgynous'>" + modifiedSentence + "</span>";
		else
			return "<span class='thoughts feminine'>" + modifiedSentence + "</span>";
	}

	public static String parsePlayerSpeech(String text) {
		modifiedSentence = text;
		if (Main.game.getPlayer().hasFetish(Fetish.FETISH_BIMBO)) {
			modifiedSentence = Util.addBimbo(modifiedSentence, 6);
		}
		
		if(Main.game.getPlayer().getAlcoholLevelValue()>0.75f) {
			modifiedSentence = Util.addDrunkSlur(modifiedSentence, 4);
		} else if(Main.game.getPlayer().getAlcoholLevelValue()>0.5f) {
			modifiedSentence = Util.addDrunkSlur(modifiedSentence, 8);
		}
		
		// Apply speech effects:
		if(Main.game.isInSex()) {
			if(Sex.isCharacterEngagedInOngoingAction(Main.game.getPlayer())) {
				modifiedSentence = Util.addSexSounds(modifiedSentence, 6);
			}
			
			if(!Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH).isEmpty()) {
				modifiedSentence = Util.addMuffle(modifiedSentence, 6);
			} else {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
					for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
						if(c.getClothingType().isMufflesSpeech()) {
							modifiedSentence = Util.addMuffle(modifiedSentence, 6);
							break;
						}
					}
				}
			}
			
		} else {
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
				for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
					if(c.getClothingType().isMufflesSpeech()) {
						modifiedSentence = Util.addMuffle(modifiedSentence, 6);
						break;
					}
				}
			}
		}

		if (Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.MASCULINE_STRONG)
			return "<span class='speech' style='color:" + Colour.MASCULINE.toWebHexString() + ";'>" + modifiedSentence + "</span>";
		else if (Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.ANDROGYNOUS)
			return "<span class='speech' style='color:" + Colour.ANDROGYNOUS.toWebHexString() + ";'>" + modifiedSentence + "</span>";
		else
			return "<span class='speech' style='color:" + Colour.FEMININE.toWebHexString() + ";'>" + modifiedSentence + "</span>";
	}

	public static String parseSpeech(String text, GameCharacter target) {
		modifiedSentence = text;
		
		String[] splitOnConditional = modifiedSentence.split("#THEN");
		
		modifiedSentence = splitOnConditional[splitOnConditional.length-1];
		
		if (target.hasFetish(Fetish.FETISH_BIMBO)) {
			modifiedSentence = Util.addBimbo(modifiedSentence, 6);
		}
		
		if(target.getAlcoholLevelValue()>0.75f) {
			modifiedSentence = Util.addDrunkSlur(modifiedSentence, 4);
		} else if(target.getAlcoholLevelValue()>0.5f) {
			modifiedSentence = Util.addDrunkSlur(modifiedSentence, 8);
		}
		
		// Apply speech effects:
		if(Main.game.isInSex() && target.isPlayer()) {
			if(Sex.isCharacterEngagedInOngoingAction(Main.game.getPlayer())) {
				modifiedSentence = Util.addSexSounds(modifiedSentence, 6);
			}
			if(!Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH).isEmpty()) {
				modifiedSentence = Util.addMuffle(modifiedSentence, 6);
			} else {
				if(!target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
					for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
						if(c.getClothingType().isMufflesSpeech()) {
							modifiedSentence = Util.addMuffle(modifiedSentence, 6);
							break;
						}
					}
				}
			}
			
		} else if(Main.game.isInSex() && Sex.getAllParticipants().contains(character)) {
			if(Sex.isCharacterEngagedInOngoingAction(character)) {
				modifiedSentence = Util.addSexSounds(modifiedSentence, 6);
			}
			
			if(!Sex.getContactingSexAreas(character, SexAreaOrifice.MOUTH).isEmpty()) {
				modifiedSentence = Util.addMuffle(modifiedSentence, 6);
			} else {
				if(!target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
					for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
						if(c.getClothingType().isMufflesSpeech()) {
							modifiedSentence = Util.addMuffle(modifiedSentence, 6);
							break;
						}
					}
				}
			}
			
		} else if(!target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.getClothingType().isMufflesSpeech()) {
					modifiedSentence = Util.addMuffle(modifiedSentence, 6);
					break;
				}
			}
		}
		
		if(splitOnConditional.length>1) {
			modifiedSentence = splitOnConditional[0]+"#THEN"+modifiedSentence;
		}
		
		if (target.getSpeechColour() != null) {

			return "<span class='speech' style='color:" + target.getSpeechColour() + ";'>" + modifiedSentence + "</span>";

		} else {
			if (Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE_STRONG)
				return "<span class='speech' style='color:" + Colour.MASCULINE_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
			else if (Femininity.valueOf(target.getFemininityValue()) == Femininity.ANDROGYNOUS)
				return "<span class='speech' style='color:" + Colour.ANDROGYNOUS_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
			else
				return "<span class='speech' style='color:" + Colour.FEMININE_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
		}
	}
	
	public static String parseSpeechNoEffects(String text, GameCharacter target) {
		modifiedSentence = text;

		if (target.getSpeechColour() != null) {

			return "<span class='speech' style='color:" + target.getSpeechColour() + ";'>" + modifiedSentence + "</span>";

		} else {
			if (Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE_STRONG)
				return "<span class='speech' style='color:" + Colour.MASCULINE_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
			else if (Femininity.valueOf(target.getFemininityValue()) == Femininity.ANDROGYNOUS)
				return "<span class='speech' style='color:" + Colour.ANDROGYNOUS_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
			else
				return "<span class='speech' style='color:" + Colour.FEMININE_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
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
			if (Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE_STRONG)
				return "<span class='thoughts' style='color:" + Colour.MASCULINE_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
			else if (Femininity.valueOf(target.getFemininityValue()) == Femininity.ANDROGYNOUS)
				return "<span class='thoughts' style='color:" + Colour.ANDROGYNOUS_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
			else
				return "<span class='thoughts' style='color:" + Colour.FEMININE_NPC.toWebHexString() + ";'>" + modifiedSentence + "</span>";
		}
	}

	public static String parseNPCSpeech(String text, Femininity femininity) {
		return parseNPCSpeech(text, femininity, false, false);
	}

	public static String parseNPCSpeech(String text, Femininity femininity, boolean bimbo, boolean stutter) {
		modifiedSentence = text;
		if (bimbo)
			modifiedSentence = Util.addBimbo(modifiedSentence, 6);
		if (stutter)
			modifiedSentence = Util.addStutter(modifiedSentence, 6);

		return "<span class='speech' style='color:" + femininity.getColour().toWebHexString() + ";'>" + modifiedSentence + "</span>";
	}
	
	public static String getDisabledResponse(String label) {
		return "<span class='option-disabled'>"+label+"</span>";
	}
	
	// "Temporary" methods until I refine the way DialogueNodes work:
	public static String getRequirementsDescription(Perk perkRequired) {
		return ("You require the perk '<b style='color:"+perkRequired.getPerkCategory().getColour().toWebHexString()+";'>"+perkRequired.getName(Main.game.getPlayer())+"</b>'.");
	}
	
	public static String getRequirementsDescription(Perk perkRequired, Gender... gendersRequired) {
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
	
	public static String getRequirementsDescription(CorruptionLevel corruptionNeeded, Perk... perkRequired) {
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
		return "&#164";
	}
	
	public static String getPentagramSymbol() {
		return "&#9737;"; // Java doesn't support unicode 6 ;_;   No pentagram for me... ;_;  "&#9956";
	}
	
	public static String formatAsEssencesUncoloured(int amount, String tag, boolean withOverlay) {
		String essenceString = formatter.format(amount);
		return "<div class='item-inline'>"
					+ TFEssence.ARCANE.getSVGStringUncoloured() + (withOverlay?"<div class='overlay no-pointer' id='ESSENCE_"+TFEssence.ARCANE.hashCode()+"'></div>":"")
				+"</div>"
				+ " <"+tag+" style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"+essenceString+"</"+tag+">";
	}
	
	
	public static String formatAsEssences(int amount, String tag, boolean withOverlay) {
		String essenceString = formatter.format(amount);
		
		return "<div class='item-inline'>"
					+ TFEssence.ARCANE.getSVGString() + (withOverlay?"<div class='overlay no-pointer' id='ESSENCE_"+TFEssence.ARCANE.hashCode()+"'></div>":"")
				+"</div>"
				+ " <"+tag+" style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"+essenceString+"</"+tag+">";
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
	
	private static NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
	private static DecimalFormat formatter;
	static{
		formatter = (DecimalFormat)nf;
		formatter.applyPattern("#,###,###");
	}
	
	public static String formatAsMoney(int money, String tag, Colour amountColour) {
		String tagColour;
		String moneyString = formatter.format(money);
		
		if(amountColour==null ) {
			tagColour = Colour.TEXT.getShades(8)[3];
		} else {
			tagColour = amountColour.toWebHexString();
		}
		
		return "<" + tag + " style='color:" + (amountColour==Colour.TEXT?Colour.TEXT.toWebHexString():Colour.CURRENCY_GOLD.toWebHexString()) + "; padding-right:2px;'>" + getCurrencySymbol() + "</" + tag + ">"
					+ "<" + tag + " style='color:" + tagColour + ";'>" + moneyString + "</" + tag + ">";
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

	public static String generateSingularDeterminer(String word) {
		if(word.isEmpty()) {
			return "";
		}
		if (isVowel(word.charAt(0)) || word.charAt(0)=='x' || word.charAt(0)=='X') {
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
	 * @return Returns one of the supplied Strings, randomly chosen by using Random's nextInt() method.
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
		return parseFromXMLFile(pathName, tag, new ArrayList<>());
	}

	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(String pathName, String tag, GameCharacter... specialNPCs) {
		return parseFromXMLFile(pathName, tag, Util.newArrayListOfValues(specialNPCs));
	}
	
	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(String pathName, String tag, List<GameCharacter> specialNPC) {
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
			return parse(specialNPC, strings.get(Util.random.nextInt(strings.size())));
		}
	}
	
	public static String parse(String input) {
		return parse(new ArrayList<>(), input);
	}
	
	public static String parse(GameCharacter specialNPC, String input) {
		return parse(Util.newArrayListOfValues(specialNPC), input);
	}
	
	public static String parse(GameCharacter specialNPC1, GameCharacter specialNPC2, String input) {
		return parse(Util.newArrayListOfValues(specialNPC1, specialNPC2), input);
	}
	
	enum ParseMode {
		UNKNOWN,
		CONDITIONAL,
		REGULAR
	}
	
	/**
	 * Parses supplied text.
	 */
	public static String parse(List<GameCharacter> specialNPC, String input) {
		
		if(Main.game!=null && Main.game.getCurrentDialogueNode()==DebugDialogue.PARSER) {
			input = input.replaceAll("\u200b", "");
		}
		
		// Loop through input, when find '[', start parsing.
		// [target.command(arguments)]
		
		// {npc1.isPlayer?Your:[npc1.Name]'s} [npc1.moans] are muffled into {npc2.isPlayer?your:[npc2.namePos]} [npc2.mouth]. {npc1.isPlayer?{npc1.isPlayer?Your:[npc1.Name]'s} feel turned on...}
		try {
			StringBuilder resultBuilder = new StringBuilder();
			StringBuilder sb = new StringBuilder();
			int openBrackets = 0;
			int closeBrackets = 0;
			int openArg = 0;
			int closeArg = 0;
			int conditionalThens = 0;
			int startIndex = 0;
			int endIndex = 0;
			
			String target = null;
			String command = null;
			String arguments = null;
			String conditionalStatement = null;
			String conditionalTrue = null;
			String conditionalFalse = null;
			
			
			boolean conditionalElseFound = false;
			ParseMode currentParseMode = ParseMode.UNKNOWN;
			
			int startedParsingSegmentAt = 0;
			
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				
				if (currentParseMode != ParseMode.REGULAR) {
					if (c == 'F' && substringMatchesInReverseAtIndex(input, "#IF", i)) {
						if (openBrackets == 0) {
							currentParseMode = ParseMode.CONDITIONAL;
							startIndex = i-2;
						}
						
						openBrackets++;
						
					} else if (currentParseMode == ParseMode.CONDITIONAL) {
						if(c == 'N' && substringMatchesInReverseAtIndex(input, "#THEN", i)) {
							conditionalThens++;
							
							if (conditionalThens == 1){
								if (conditionalStatement == null) {
									conditionalStatement = sb.toString().substring(1, sb.length()-4); // Cut off the '#THEN' at the start.
									conditionalStatement = conditionalStatement.replaceAll("\n", "").replaceAll("\t", "");
									conditionalStatement = conditionalStatement.trim();
								}
								sb.setLength(0);
							}
							
						} else if(c == 'E' && substringMatchesInReverseAtIndex(input, "#ELSE", i) && openBrackets-1==closeBrackets) {
							conditionalElseFound = true;
							conditionalTrue = sb.toString().substring(1, sb.length()-4); // Cut off the '#ELSE' at the start.
							sb.setLength(0);
							
						} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ENDIF", i)) {
							closeBrackets++;
							
							if (openBrackets == closeBrackets) {
								
								if (conditionalElseFound) {
									conditionalFalse = sb.toString().substring(1, sb.length()-5); // Cut off the '#ENDIF' at the start.
								} else {
									conditionalTrue = sb.toString().substring(1, sb.length()-5); // Cut off the '#ENDIF' at the start.
									conditionalFalse = "";
								}
			
								endIndex = i;
							}
						}
					}
				}
				
				if (currentParseMode != ParseMode.CONDITIONAL) {
					if (c == '[') {
						if(openBrackets==0) {
							currentParseMode = ParseMode.REGULAR;
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
					}
				}
				
				//TODO
				if (openBrackets>0 && ((target!=null && command!=null) || (!Character.isWhitespace(c) || c==' '))) {
						//(Character.isLetterOrDigit(c) || c=='+' || c=='.' || c=='[' || c=='(' || c==')'))) {
					//String.valueOf(c).matches(".") || c!=' ')) {
					sb.append(c);
				}
				
				if (endIndex != 0) {
					resultBuilder.append(input.substring(startedParsingSegmentAt, startIndex));
					UtilText.specialNPCList = specialNPC;
					// resetParsingEngine();
					String subResult = (currentParseMode == ParseMode.CONDITIONAL
							? parseConditionalSyntaxNew(specialNPC, conditionalStatement, conditionalTrue, conditionalFalse)
							: parseSyntaxNew(target, command, arguments, specialNPC)
						);
					if (openBrackets > 1) {
						subResult = parse(specialNPC, subResult);
					}
					resultBuilder.append(subResult);
					
					startedParsingSegmentAt = endIndex + 1;
					//This is the lamest version of recursion unrolling there is:
					//just reset all your variables by hand.
					sb = new StringBuilder();
					
					openBrackets = 0;
					closeBrackets = 0;
					openArg = 0;
					closeArg = 0;
					conditionalThens = 0;
					startIndex = 0;
					endIndex = 0;
					
					target = null;
					command = null;
					arguments = null;
					conditionalTrue = null;
					conditionalFalse = null;
					conditionalStatement = null;
					
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
				new String[] {"money"},
				true,
				false,
				"(amount, tag)",
				"Formats the supplied number as money, using the tag as the html tag."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return UtilText.formatAsMoney(arguments.split(", ")[0], arguments.split(", ")[1]);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"moneyUncoloured",
						"moneyNoColour",
						"moneyUncolored",
						"moneyNoColor"},
				true,
				false,
				"(amount, tag)",
				"Formats the supplied number as money, using the tag as the html tag."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return UtilText.formatAsMoneyUncoloured(Integer.valueOf(arguments.split(", ")[0]), arguments.split(", ")[1]);
			}
		});

		commandsList.add(new ParserCommand(
				new String[] {"random"},
				true,
				false,
				"(text1 | text2 | text3)",
				"Returns a random string from the supplied arguments."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				List<String> strings = new ArrayList<>();
				for(String s : arguments.split("\\|")) {
					strings.add(s);
				}
				return Util.randomItemFrom(strings);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {"name"},
				true,
				false,
				"(prefix)",
				"Returns the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter."
				+ " If a prefix is provided, the prefix will be appended (with an automatic addition of a space) to non-capitalised names."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(target.startsWith("npc") && character.isPlayer()) {
					if(command.startsWith("N")) {
						return "You";
					} else {
						return "you";
					}
				}
				if(arguments!=null) {
					return character.getName(arguments);
				} else {
					if(character.isPlayerKnowsName() || character.isPlayer()) {
						return character.getName();
					}
					return character.getName("the");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {"namePos"},
				true,
				false,
				"(real name)",
				"Returns a possessive version of the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter."
				+ " If you need the actual player name for third-person reference, pass a space as an argument.") {
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return character.getName() + "'s";
				} else {
					if(target.startsWith("npc") && character.isPlayer()) {
						if(command.startsWith("N")) {
							return "Your";
						} else {						 
							return "your";
						}
					}
					if(character.isPlayerKnowsName()) {
						return character.getName() + "'s";
					}
					return character.getName("the") + "'s";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {"nameIs"},
				true,
				false,
				"(real name)",
				"Returns a contractive version of the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter."
				+ " If you need the actual player name for third-person reference, pass a space as an argument.") {
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return character.getName() + "'s";
				} else {
					if(target.startsWith("npc") && character.isPlayer()) {
						return "you're";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName() + "'s";
					}
					return character.getName("the") + "'s";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {"nameIsFull"},
				true,
				false,
				"(real name)",
				"Returns a contractive version of the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter.") {
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return character.getName() + " is";
				} else {
					if(target.startsWith("npc") && character.isPlayer()) {
						return "you are";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName() + " is";
					}
					return character.getName("the") + " is";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {"nameHas"},
				true,
				false,
				"(real name)",
				"Returns a contractive version of the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter."
				+ " If you need the actual player name for third-person reference, pass a space as an argument.") {
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return character.getName() + "'s";
				} else {
					if(target.startsWith("npc") && character.isPlayer()) {
						return "you've";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName() + "'s";
					}
					return character.getName("the") + "'s";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"verb",
						"verbPerson"},
				true,
				false,
				"(verb)",
				"Returns a verb in the (probably) correct person for this character. A player might get 'wiggle' where an NPC would get 'wiggles'.") {
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {"surname"},
				true,
				false,
				"",
				"Returns the surname of the target."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getSurname();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {"fullName"},
				true,
				false,
				"(prefix)",
				"Returns the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter. If you want the basic form of the name, pass in a space as an argument."
				+ " If a prefix is provided, the prefix will be appended (with an automatic addition of a space) to non-capitalised names."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return character.getName(arguments)+(character.getSurname().isEmpty()?"":" "+character.getSurname());
				} else {
					if(character.isPlayerKnowsName() || character.isPlayer()) {
						return character.getName()+(character.getSurname().isEmpty()?"":" "+character.getSurname());
					}
					return character.getName("the")+(character.getSurname().isEmpty()?"":" "+character.getSurname());
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"pcName",
						"pcPetName"},
				true,
				false,
				"",
				"Returns the name that this character prefers to call the player by."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getPlayerPetName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"description",
						"desc"},
				true,
				false,
				"",
				"Returns a breif descriptive overview of this character."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getDescription();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"job",
						"jobName"},
				true,
				true,
				"",
				"Returns the name of this character's job."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isSlave()) {
					return character.getSlaveJob().getName(character);
				}
				return character.getHistory().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"gender"},
				true,
				true,
				"(coloured)",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"genderAppears",
						"genderAppearsAs",
						"appearsAsGender"},
				true,
				true,
				"(coloured)",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"daughter",
						"son"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "daughter";
				} else {
					return "son";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"mother",
						"father"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "mother";
				} else {
					return "father";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"mommy",
						"daddy"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "mommy";
				} else {
					return "daddy";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"mom",
						"mum",
						"dad"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "mom";
				} else {
					return "dad";
				}
			}
		});

		commandsList.add(new ParserCommand(
				new String[] {
						"sister",
						"brother"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "sister";
				} else {
					return "brother";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"sis",
						"bro"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "sis";
				} else {
					return "bro";
				}
			}
		});

		commandsList.add(new ParserCommand(
				new String[] {
						"niece",
						"nephew"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "niece";
				} else {
					return "nephew";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"mistress",
						"master"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "mistress";
				} else {
					return "master";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"heroine",
						"hero"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "heroine";
				} else {
					return "hero";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"miss",
						"ms",
						"mister",
						"mr"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "Miss";
				} else {
					return "Mr.";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"boyfriend",
						"girlfriend"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return "girlfriend";
				} else {
					return "boyfriend";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"bitch",
						"slut"},
				true,
				true,
				"",
				"Returns a random mean word to describe this person, based on their femininity."){ // R-Rude!
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return UtilText.returnStringAtRandom("bitch", "slut", "cunt", "whore", "skank");
				} else {
					return UtilText.returnStringAtRandom("asshole", "bastard", "fuckface", "fucker");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"fullRace",
						"raceFull",
						"femininityRace"},
				true,
				true,
				"(coloured)",
				"Returns a full description of this characters race (including femininity). Pass in 'true' to colour the text."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				boolean pronoun = parseAddPronoun;
				parseAddPronoun = false;
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return "<span style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence(Femininity.getFemininityName(character.getFemininityValue(), pronoun))
									:Femininity.getFemininityName(character.getFemininityValue(), pronoun))+"</span>"
							+ " <span style='color:"+character.getRaceStage().getColour().toWebHexString()+";'>" +character.getRaceStage().getName()+"</span>"
							+ " <span style='color:"+character.getSubspecies().getColour().toWebHexString()+";'>" +  getSubspeciesName(character.getSubspecies()) + "</span>";
				}
				return (parseCapitalise
						?Util.capitaliseSentence(Femininity.getFemininityName(character.getFemininityValue(), pronoun))
						:Femininity.getFemininityName(character.getFemininityValue(), pronoun))+" "+character.getRaceStage().getName()+" "+getSubspeciesName(character.getSubspecies());
			}
			@Override
			protected String applyDeterminer(String descriptor, String input) {
				return input;
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"race"},
				true,
				true,
				"(coloured)",
				"Returns the name of this characters race. Pass in 'true' to colour the text."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					String name = character.isRaceConcealed()?"unknown race":getSubspeciesName(character.getSubspecies());
					return "<span style='color:"+(character.isRaceConcealed()?Colour.TEXT_GREY:character.getSubspecies().getColour()).toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+" ":"")+name)
							+"</span>";
				}
				return getSubspeciesName(character.getSubspecies());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"races"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return getSubspeciesNamePlural(character.getSubspecies());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"raceStage"},
				true,
				true,
				"(coloured)",
				"Returns the name of the 'stage' of characters race (partial, lesser, greater). Pass in 'true' to colour the text."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"preferredBody"},
				false,
				false,
				"(tag)",
				"Returns the description of this character's preferred body."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"materialName"},
				true,
				true,
				"",
				"Returns the name of the character's BodyMaterial."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialSkin"},
				true,
				true,
				"",
				"Returns a descriptor of the character's skin."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getSkinNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialSkinAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's skin."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getSkinAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialSkinAlt"},
				true,
				true,
				"",
				"Returns a descriptor of the character's skin on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getSkinAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialSkinAltAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's skin on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getSkinAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialOrifice"},
				true,
				true,
				"",
				"Returns a descriptor of the material lining the character's orifices."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getOrificeNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialOrificeAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the material lining the character's orifices."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getOrificeAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialOrificeAlt"},
				true,
				true,
				"",
				"Returns a descriptor of the material lining the character's orifices on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getOrificeAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialOrificeAltAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the material lining the character's orifices on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getOrificeAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialHair"},
				true,
				true,
				"",
				"Returns a descriptor of the character's hair."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getHairNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialHairAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's hair."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getHairAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialHairBody",
						"materialBodyHair"},
				true,
				true,
				"",
				"Returns a descriptor of the character's body hair."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getHairBodyNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialHairBodyAdjective",
						"materialBodyHairAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's body hair."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getHairBodyAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialHairAlt"},
				true,
				true,
				"",
				"Returns a descriptor of the character's Hair on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getHairAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialHairAltAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's hair on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getHairAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialFur"},
				true,
				true,
				"",
				"Returns a descriptor of the character's fur."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getFurNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialFurAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's fur."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getFurAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialFurAlt"},
				true,
				true,
				"",
				"Returns a descriptor of the character's fur on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getFurAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialFurAltAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's fur on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getFurAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialFeather",
						"materialFeathers"},
				true,
				true,
				"",
				"Returns a descriptor of the character's feathers."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getFeatherNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialFeatherAdjective",
						"materialFeathersAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's feathers."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getFeatherAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialFeatherAlt",
						"materialFeathersAlt"},
				true,
				true,
				"",
				"Returns a descriptor of the character's feathers on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getFeatherAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialFeatherAltAdjective",
						"materialFeathersAltAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's fur on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getFeatherAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialScale",
						"materialScales"},
				true,
				true,
				"",
				"Returns a descriptor of the character's scales."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getScaleNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialScaleAdjective",
						"materialScalesAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's scales."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getScaleAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialScaleAlt",
						"materialScalesAlt"},
				true,
				true,
				"",
				"Returns a descriptor of the character's scales on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getScaleAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialScaleAltAdjective",
						"materialScalesAltAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's scales on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getScaleAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialShell"},
				true,
				true,
				"",
				"Returns a descriptor of the character's shell."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getShellNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialShellAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's shell."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getShellAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialShellAlt"},
				true,
				true,
				"",
				"Returns a descriptor of the character's shell on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getShellAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialShellAltAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's shell on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getShellAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialKeratin"},
				true,
				true,
				"",
				"Returns a descriptor of the character's keratin."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getKeratinNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialKeratinAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's keratin."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getKeratinAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialKeratinAlt"},
				true,
				true,
				"",
				"Returns a descriptor of the character's keratin on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getKeratinAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"materialKeratinAltAdjective"},
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's keratin on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyMaterial().getKeratinAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"femininity",
						"fem",
						"masculinity",
						"mas"},
				true,
				true,
				"(coloured)",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return Femininity.valueOf(character.getFemininityValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"bodySize"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return BodySize.valueOf(character.getBodySizeValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"muscle"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return Muscle.valueOf(character.getMuscleValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"bodyShape"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBodyShape().getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"height"},
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getHeight().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"heightCm"},
				false,
				false,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return String.valueOf(character.getHeightValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"heightInches"},
				false,
				false,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return String.valueOf(Util.conversionCentimetresToInches(character.getHeightValue()));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"heightFeetInches"},
				false,
				false,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return Util.inchesToFeetAndInches(Util.conversionCentimetresToInches(character.getHeightValue()));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"weight"},
				false,
				false,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return String.valueOf(character.getWeight());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"speech",
						"dialogue",
						"talk",
						"say"},
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return parseSpeech(arguments, character);
				} else {
					return parseSpeech("...", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"speechMasculine",
						"dialogueMasculine",
						"talkMasculine",
						"sayMasculine"},
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.MASCULINE);
				} else {
					return parseNPCSpeech("...", Femininity.MASCULINE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"speechMasculineStrong",
						"dialogueMasculineStrong",
						"talkMasculineStrong",
						"sayMasculineStrong"},
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.MASCULINE_STRONG);
				} else {
					return parseNPCSpeech("...", Femininity.MASCULINE_STRONG);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"speechAndrogynous",
						"dialogueAndrogynous",
						"talkAndrogynous",
						"sayAndrogynous"},
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.ANDROGYNOUS);
				} else {
					return parseNPCSpeech("...", Femininity.ANDROGYNOUS);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"speechFeminine",
						"dialogueFeminine",
						"talkFeminine",
						"sayFeminine"},
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.FEMININE);
				} else {
					return parseNPCSpeech("...", Femininity.FEMININE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"speechNoEffects",
						"dialogueNoEffects",
						"talkNoEffects",
						"sayNoEffects"},
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return parseSpeechNoEffects(arguments, character);
				} else {
					return parseSpeechNoEffects("...", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"thought"},
				false,
				false,
				"(thought content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					return parseThought(arguments, character);
				} else {
					return parseThought("...", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"moan",
						"groan",
						"sob",
						"cry"},
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"moan+",
						"moanD",
						"groan+",
						"groanD",
						"sob+",
						"sobD",
						"cry+",
						"cryD"},
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."
				+ " <b>Expansion of 'moan' command:</b> This command will append a suitable descriptor before the 'moan' noise. e.g. 'lewd squeal', or 'eager grunt'."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(Main.game.isInSex()) {
					if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("sob", "scream", "cry");
						} else {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("shout", "cry");
						}
						
					} else if(Sex.getSexPace(character)==SexPace.DOM_GENTLE) {
						if(character.isFeminine()) {
							return returnStringAtRandom("softly", "gently", "quietly") + " " + returnStringAtRandom("moan", "sigh", "gasp");
						} else {
							return returnStringAtRandom("softly", "gently", "quietly") + " " + returnStringAtRandom("groan", "grunt");
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
				new String[] {
						"moanVerb",
						"groanVerb",
						"sobVerb",
						"cryVerb",
						"moansVerb",
						"groansVerb",
						"sobsVerb",
						"criesVerb"},
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
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
						"criesVerbD"},
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."
				+ " <b>Expansion of 'moan' command:</b> This command will append a suitable descriptor before the 'moan' noise. e.g. 'lewd squeal', or 'eager grunt'."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"moans",
						"groans",
						"sobs",
						"cries"},
				true,
				false,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will make moans, while if they are masculine, they will make groans."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sobs' or 'cries'."
				+" <b>Provides an appropriate <i>noun</i> version of 'moans'.</b> (Use 'moansVerb' for the verb version.)"){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"moans+",
						"moansD",
						"groans+",
						"groansD",
						"sobs+",
						"sobsD",
						"cries+",
						"criesD"},
				true,
				false,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will make moans, while if they are masculine, they will make groans."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sobs' or 'cries'."
				+ " <b>Expansion of 'moans' command:</b> This command will append a suitable descriptor before the 'moans' noise. e.g. 'lewd squeals', or 'eager grunts'."
				+ " <b>Provides an appropriate <i>noun</i> version of 'moans'.</b> (Use 'moansVerb+' for the verb version.)"){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(Main.game.isInSex()) {
					if(Sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("sobs", "cries");
						} else {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("shouts", "cries");
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
					return returnStringAtRandom("lewd", "high-pitched", "desperate") + " " + returnStringAtRandom("moans", "squeals", "gasps");
				} else {
					return returnStringAtRandom("deep", "eager", "desperate") + " " + returnStringAtRandom("groans", "grunts");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"moaning",
						"groaning",
						"sobbing",
						"crying"},
				true,
				false,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"moaning+",
						"moaningD",
						"groaning+",
						"groaningD",
						"sobbing+",
						"sobbingD",
						"crying+",
						"cryingD"},
				true,
				false,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"eagerly",
						"gently",
						"roughly"),
				true,
				false,
				"(Alternative start string)",
				"Returns an appropriate descriptor based on the pace of the character. The argument is used in place of a descriptor if the pace is SUB_NORMAL."){
			@Override
			public String parse(String command, String arguments, String target) {
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
								return Util.capitaliseSentence(arguments); // Assume start of setnece, so capitalise.
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
						"smell"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.GENERIC){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return returnStringAtRandom("scent", "scent", "perfume");
				} else {
					return returnStringAtRandom("musk", "musk", "aroma");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"scent+",
						"scentD",
						"smell+",
						"smellD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.GENERIC){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine()) {
					return returnStringAtRandom("feminine scent", "feminine perfume", "delicate scent");
				} else {
					return returnStringAtRandom("masculine musk", "masculine aroma");
				}
			}
		});
		
		// Gender parsing:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"girl",
						"boy"},
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine())
					return Gender.F_V_B_FEMALE.getNounYoung();
				else
					return Gender.M_P_MALE.getNounYoung();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"woman",
						"man"},
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine())
					return Gender.F_V_B_FEMALE.getNoun();
				else
					return Gender.M_P_MALE.getNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"female",
						"male"},
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine())
					return Gender.F_V_B_FEMALE.getName();
				else
					return Gender.M_P_MALE.getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"feminine",
						"masculine"},
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.isFeminine())
					return "feminine";
				else
					return "masculine";
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"have",
						"has"},
				true,
				true,
				"",
				"Returns the correct version of 'has' for this character (has or have)."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "have";
				} else {
					return "has";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"does",
						"do"},
				true,
				true,
				"",
				"Returns the correct version of 'does' for this character (do or does)."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "do";
				} else {
					return "does";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"her",
						"his",
						"herPos",
						"herHis",
						"hisPos",
						"hisHer"},
				true,
				true,
				"(real pronoun)",
				"Returns the correct gender-specific possessive pronoun for this character (your, her, his). By default, returns 'your' for player character."
				+ " If you need the actual third-person player character pronoun, pass a space as an argument."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"hers",
						"hersHis",
						"hisHers"},
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"nameHers",
						"nameHersHis",
						"nameHisHers"},
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(target.startsWith("npc") && arguments==null && character.isPlayer()) {
					return "yours";
				} else {
					if(character.isPlayerKnowsName()) {
						return character.getName() + "'s";
					}
					return character.getName("the") + "'s";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"him",
						"herPro",
						"herHim",
						"himHer"},
				true,
				true,
				"(real pronoun)",
				"Returns the correct pronoun for this character (you, him, her). By default, returns 'you' for player character."
				+ " If you need the regular third-person player character pronoun, pass a space as an argument."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"she",
						"sheHe",
						"he",
						"heShe"},
				true,
				true,
				"(real pronoun)",
				"Returns the correct pronoun for this character (you, she, he). By default, returns 'you' for player character."
				+ " If you need the regular third-person player character pronoun, pass a space as an argument."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"sheIs",
						"sheHeIs",
						"heIs",
						"heSheIs"},
				true,
				true,
				"(real pronoun)",
				"Returns the correct gender-specific pronoun contraction for this character (you're, she's, he's). By default, returns 'you're' for player character."
				+ " If you need the regular third-person player character pronoun contraction, pass a space as an argument."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"sheIsFull",
						"sheHeIsFull",
						"heIsFull",
						"heSheIsFull"},
				true,
				true,
				"(real pronoun)",
				"Returns the correct gender-specific pronoun contraction for this character (you are, she is, he is). By default, returns 'you are' for player character."
				+ " If you need the regular third-person player character pronoun contraction, pass a space as an argument."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"sheHas",
						"sheHeHas",
						"heHas",
						"heSheHas"},
				true,
				true,
				"(real pronoun)",
				"Returns the correct gender-specific pronoun contraction for this character (you've, she's, he's). By default, returns 'you've' for player character."
				+ " If you need the regular third-person player character pronoun contraction, pass a space as an argument."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"sheHasFull",
						"sheHeHasFull",
						"heHasFull",
						"heSheHasFull"},
				true,
				true,
				"",
				"Returns the correct gender-specific pronoun contraction for this character (you have, she has, he has). By default, returns 'you have' for player character."){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"herself",
						"himself"},
				true,
				true,
				"",
				"Returns correct gender-specific reflexive pronoun for this character (yourself, herself, himself). By default, returns 'yourself' for player character."
						+ " If you need the regular reflexive player character pronoun, pass a space as an argument."){
					@Override
					public String parse(String command, String arguments, String target, GameCharacter gc) {
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
				new String[] {
						"topClothing",
						"highestClothing",
						"highClothing"},
				true,
				true,
				"(bodyPart)",
				"Returns the name of the highest piece of clothing that's blocking the area passed in as an argument. Possible arguments:<br/>"
				+ "vagina | pussy | cunt<br/>"
				+ "penis | cock | dick<br/>"
				+ "nipples | nipple<br/>"
				+ "ass | asshole"){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("vagina") || arguments.equalsIgnoreCase("pussy") || arguments.equalsIgnoreCase("cunt")) {
						if(character.getHighestZLayerCoverableArea(CoverableArea.VAGINA)==null)
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_vagina</i>";
						else
							return character.getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName();
						
					} else if(arguments.equalsIgnoreCase("penis") || arguments.equalsIgnoreCase("cock") || arguments.equalsIgnoreCase("dick")) {
						if(character.getHighestZLayerCoverableArea(CoverableArea.PENIS)==null)
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_penis</i>";
						else
							return character.getHighestZLayerCoverableArea(CoverableArea.PENIS).getName();
						
					} else if(arguments.equalsIgnoreCase("nipple") || arguments.equalsIgnoreCase("nipples")) {
						if(character.getHighestZLayerCoverableArea(CoverableArea.NIPPLES)==null)
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_nipples</i>";
						else
							return character.getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName();
						
					} else if(arguments.equalsIgnoreCase("ass") || arguments.equalsIgnoreCase("asshole")) {
						if(character.getHighestZLayerCoverableArea(CoverableArea.ANUS)==null)
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_asshole</i>";
						else
							return character.getHighestZLayerCoverableArea(CoverableArea.ANUS).getName();
						
					} else {
						return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
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
				new String[] {
						"bottomClothing",
						"lowestClothing",
						"lowClothing"},
				true,
				true,
				"(bodyPart)",
				"Returns the name of the lowest piece of clothing that's blocking the area passed in as an argument. Possible arguments:<br/>"
				+ "vagina | pussy | cunt<br/>"
				+ "penis | cock | dick<br/>"
				+ "nipples | nipple<br/>"
				+ "ass | asshole"){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("vagina") || arguments.equalsIgnoreCase("pussy") || arguments.equalsIgnoreCase("cunt")) {
						if(character.getLowestZLayerCoverableArea(CoverableArea.VAGINA)==null)
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_vagina</i>";
						else
							return character.getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName();
						
					} else if(arguments.equalsIgnoreCase("penis") || arguments.equalsIgnoreCase("cock") || arguments.equalsIgnoreCase("dick")) {
						if(character.getLowestZLayerCoverableArea(CoverableArea.PENIS)==null)
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_penis</i>";
						else
							return character.getLowestZLayerCoverableArea(CoverableArea.PENIS).getName();
						
					} else if(arguments.equalsIgnoreCase("nipple") || arguments.equalsIgnoreCase("nipples")) {
						if(character.getLowestZLayerCoverableArea(CoverableArea.NIPPLES)==null)
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_nipples</i>";
						else
							return character.getLowestZLayerCoverableArea(CoverableArea.NIPPLES).getName();
						
					} else if(arguments.equalsIgnoreCase("ass") || arguments.equalsIgnoreCase("asshole")) {
						if(character.getLowestZLayerCoverableArea(CoverableArea.ANUS)==null)
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_asshole</i>";
						else
							return character.getLowestZLayerCoverableArea(CoverableArea.ANUS).getName();
						
					} else {
						return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
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
		
		
		// Styles:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"bold",
						"b"},
				false,
				false,
				"(text to make bold)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(arguments!=null)
					return "<b>"+arguments+"</b>";
				else
					return "<b>...</b>";
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"italic",
						"italics",
						"i"},
				false,
				false,
				"(text to italicise)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
						ArrayUtil.convertToArray(commandNames),
						false,
						false,
						"(text to colour)",
						"Description of method"){//TODO
					@Override
					public String parse(String command, String arguments, String target, GameCharacter gc) {
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
						ArrayUtil.convertToArray(commandNames),
						false,
						false,
						"(text to make bold)",
						"Description of method"){//TODO
					@Override
					public String parse(String command, String arguments, String target, GameCharacter gc) {
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
						ArrayUtil.convertToArray(commandNames),
						false,
						false,
						"(text to italicise)",
						"Description of method"){//TODO
					@Override
					public String parse(String command, String arguments, String target, GameCharacter gc) {
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
						ArrayUtil.convertToArray(commandNames),
						false,
						false,
						"(text to glow)",
						"Description of method"){//TODO
					@Override
					public String parse(String command, String arguments, String target, GameCharacter gc) {
						if(arguments!=null)
							return applyGlow(arguments, c);
						else
							return "<i>...</i>";
					}
				});
			}
		}
		
		
		
		
		
		
		
		
		// Body parts:
		
		// Add standard parsing for all types:

		addStandardParsingCommands(
				new String[] {"antenna"},
				new String[] {"antennae"},
				BodyPartType.ANTENNA);
				
		addStandardParsingCommands(
				new String[] {"arm"},
				new String[] {"arms"},
				BodyPartType.ARM);
		
		addStandardParsingCommands(
				new String[] {"ass", "butt"},
				new String[] {"asses", "butts"},
				BodyPartType.ASS);
		
		addStandardParsingCommands(
				new String[] {"anus", "asshole"},
				new String[] {"anuses", "assholes"},
				BodyPartType.ANUS);
		
		addStandardParsingCommands(
				new String[] {"breast", "tit", "boob", "chest"},
				new String[] {"breasts", "tits", "boobs"},
				BodyPartType.BREAST);

		addStandardParsingCommands(
				new String[] {"nipple", "teat"},
				new String[] {"nipples", "teats"},
				BodyPartType.NIPPLES);

		addStandardParsingCommands(
				new String[] {"milk"},
				new String[] {"milks"}, // milks? Really?
				BodyPartType.MILK);
		
		addStandardParsingCommands(
				new String[] {"ear"},
				new String[] {"ears"},
				BodyPartType.EAR);
		
		addStandardParsingCommands(
				new String[] {"eye"},
				new String[] {"eyes"},
				BodyPartType.EYE);
		
		addStandardParsingCommands(
				new String[] {"face"},
				new String[] {"faces"},
				BodyPartType.FACE);
		
		addStandardParsingCommands(
				new String[] {"mouth"},
				new String[] {"mouths"},
				BodyPartType.MOUTH);
		
		addStandardParsingCommands(
				new String[] {"hairSingular", "feather"},
				new String[] {"hair", "feathers"},
				BodyPartType.HAIR);

		addStandardParsingCommands(
				new String[] {"horn"},
				new String[] {"horns"},
				BodyPartType.HORN);
		
		addStandardParsingCommands(
				new String[] {"leg"},
				new String[] {"legs"},
				BodyPartType.LEG);
		
		addStandardParsingCommands(
				new String[] {"penis", "cock", "dick"},
				new String[] {"penises", "cocks", "dicks"},
				BodyPartType.PENIS);

		addStandardParsingCommands(
				new String[] {"secondPenis", "secondCock", "secondDick", "penis2", "cock2", "dick2"},
				new String[] {"secondPenises", "secondCocks", "secondDicks", "penises2", "cocks2", "dicks2"},
				BodyPartType.SECOND_PENIS);
		
		addStandardParsingCommands(
				new String[] {"testicle", "ball"},
				new String[] {"testicles", "balls"},
				BodyPartType.TESTICLES);

		addStandardParsingCommands(
				new String[] {"cum"},
				new String[] {"cums"}, // :s
				BodyPartType.CUM);
		
		addStandardParsingCommands(
				new String[] {"skin"},
				new String[] {"skinPlural"},
				BodyPartType.SKIN);
		
		addStandardParsingCommands(
				new String[] {"tail"},
				new String[] {"tails"},
				BodyPartType.TAIL);
		
		addStandardParsingCommands(
				new String[] {"tentacle"},
				new String[] {"tentacles"},
				BodyPartType.TENTACLE);
		
		addStandardParsingCommands(
				new String[] {"tongue"},
				new String[] {"tongues"},
				BodyPartType.TONGUE);

		addStandardParsingCommands(
				new String[] {"clit", "clitoris"},
				new String[] {"clits", "clitorises"},
				BodyPartType.CLIT);
		
		addStandardParsingCommands(
				new String[] {"vagina", "pussy", "cunt"},
				new String[] {"vaginas", "pussies", "cunts"},
				BodyPartType.VAGINA);

		addStandardParsingCommands(
				new String[] {"girlcum", "gcum"},
				new String[] {"girlcums", "gcums"},
				BodyPartType.GIRL_CUM);

		addStandardParsingCommands(
				new String[] {"wing"},
				new String[] {"wings"},
				BodyPartType.WING);
		
		
		// Special body parts:
		
		// Arm:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"armRows"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.getArmRows()==1) {
					return "pair of";
				} else if(character.getArmRows()==2) {
					return "two pairs of";
				} else {
					return "three pairs of";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hand"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getArmType().getHandsNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hand+",
						"handD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getArmType().getHandsDescriptor(character), character.getArmType().getHandsNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hands"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getArmType().getHandsNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hands+",
						"handsD"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getArmType().getHandsDescriptor(character), character.getArmType().getHandsNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"finger"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getArmType().getFingersNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"finger+",
						"fingerD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getArmType().getFingersDescriptor(character), character.getArmType().getFingersNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"fingers"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getArmType().getFingersNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"fingers+",
						"fingersD"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getArmType().getFingersDescriptor(character), character.getArmType().getFingersNamePlural(character));
			}
		});
		
	
		// Ass:
		commandsList.add(new ParserCommand(
				new String[] {
						"assSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getAssSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"assCapacity",
						"assholeCapacity"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return Capacity.getCapacityFromValue(character.getAssStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"assElasticity",
						"assholeElasticity"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getAssElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"assWetness",
						"assholeWetness"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getAssWetness().getDescriptor();
			}
		});
		
		// Hips: TODO rough
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hipSkin",
						"hipsSkin"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return getSkinName(character.getSkinType());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hipSkin+",
						"hipsSkin+"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return getSkinNameWithDescriptor(character.getSkinType());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hip",
						"hips"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return "hips";
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hip+",
						"hipD",
						"hips+",
						"hipsD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getHipSize().getDescriptor(), "hips");
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hipSize",
						"hipsSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getHipSize().getDescriptor();
			}
		});
		
		// Breasts:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"breastSize",
						"breastsSize",
						"titSize",
						"titsSize",
						"boobSize",
						"boobsSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBreastSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"breastShape",
						"breastsShape",
						"titShape",
						"titsShape",
						"boobShape",
						"boobsShape"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBreastShape().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"nippleSize",
						"nipplesSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getNippleSize().getName();
			}
		});

		commandsList.add(new ParserCommand(
				new String[] {
						"areolaSize",
						"areolaeSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getAreolaeSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"cupSize",
						"cups",
						"breastCups",
						"breastsCups",
						"titCups",
						"titsCups",
						"boobCups",
						"boobsCups"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBreastSize().getCupSizeName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"breastCapacity",
						"breastsCapacity",
						"titCapacity",
						"titsCapacity",
						"boobCapacity",
						"boobsCapacity"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return Capacity.getCapacityFromValue(character.getNippleStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"breastElasticity",
						"breastsElasticity",
						"titElasticity",
						"titsElasticity",
						"boobElasticity",
						"boobsElasticity"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getNippleElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"breastRows",
						"nippleRows"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.getBreastRows()==1) {
					return "pair of";
				} else {
					return Util.intToString(character.getBreastRows())+" pairs of";
				}
			}
		});
		
//		commandsList.add(new ParserCommand(
//				new String[] {
//						"milk",
//						"milkName"},
//				true,
//				true,
//				"",
//				"Description of method",
//				BodyPartType.BREAST){//TODO
//			@Override
//			public String parse(String command, String arguments, String target, GameCharacter gc) {
//				return character.getMilkName();
//			}
//		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"lactation"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBreastMilkStorage().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				new String[] {
						"milkRegen",
						"milkRegeneration"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBreastLactationRegeneration().getName();
			}
		});
		
		// Eyes:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"eyePairs",
						"eyesPairs",
						"eyeRows",
						"eyesRows"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getEyeDeterminer();
			}
		});
		
		// Face:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"tongueLength",
						"tongueSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getTongueLength().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"nose"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getNoseNameSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"noses"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getNoseNamePlural();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"lipSize",
						"lipsSize"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getLipSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"lip"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getLipsNameSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"lip+",
						"lipD"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getLipsDescriptor(), character.getLipsNameSingular());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"lips"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getLipsNamePlural();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"lips+",
						"lipsD"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getLipsDescriptor(), character.getLipsNamePlural());
			}
		});
		
		// Hair:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hairLength"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.HAIR){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getHairLength().getDescriptor();
			}
		});
		
		// Horns:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"hornSize",
						"hornsSize",
						"hornLength",
						"hornsLength"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.HORN){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return HornLength.getHornLengthFromInt(character.getHornLength()).getDescriptor();
			}
		});
		
		// Leg:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"thighs"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return "thighs";
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"thighs+",
						"thighsD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return "thighs";
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"foot"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getLegType().getFeetNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"foot+",
						"footD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getLegType().getFeetDescriptor(character), character.getLegType().getFeetNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"feet"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getLegType().getFeetNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"feet+",
						"feetD"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getLegType().getFeetDescriptor(character), character.getLegType().getFeetNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"toes"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getLegType().getToesNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"toes+",
						"toesD"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getLegType().getToesDescriptor(character), character.getLegType().getToesNamePlural(character));
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
			public String parse(String command, String arguments, String target) {
				switch(character.getLegType().getFootType()) {
					case HOOFS:
						return "hoofjob";
					case HUMANOID:
					case PAWS:
						return "footjob";
					case TALONS:
						return "clawjob";
				}
				return "footjob";
			}
		});
		
		// Penis:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"penisUrethra",
						"cockUrethra",
						"urethraPenis",
						"urethraCock"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return "urethra";
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"penisUrethra+",
						"cockUrethra+",
						"urethraPenis+",
						"urethraCock+",
						"penisUrethraD",
						"cockUrethraD",
						"urethraPenisD",
						"urethraCockD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getPenisUrethraDescriptor(), "urethra");
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"cumAmount",
						"cumProduction",
						"jizzAmount",
						"jizzProduction",
						"cumStorage"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getPenisCumStorage().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"cumMl",
						"cumMeasurement"},
				false,
				false,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return String.valueOf(character.getPenisRawCumStorageValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"ballsCount",
						"ballCount",
						"testiclesCount"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return (parseCapitalise
						?Util.capitaliseSentence(Util.intToString(character.getPenisNumberOfTesticles()))
						:Util.intToString(character.getPenisNumberOfTesticles()));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"ballSize",
						"ballsSize",
						"testicleSize",
						"testiclesSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getTesticleSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"penisHead",
						"cockHead",
						"dickHead",
						"cockTip"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBody().getPenis().getPenisHeadName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"penisHead+",
						"penisHeadD",
						"cockHead+",
						"cockHeadD",
						"dickHead+",
						"dickHeadD",
						"cockTip+",
						"cockTipD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getBody().getPenis().getPenisHeadDescriptor(character), character.getBody().getPenis().getPenisHeadName(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"penisSize",
						"cockSize",
						"dickSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getPenisSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"penisGirth",
						"cockGirth",
						"dickGrith"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getPenisGirth().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"penisCm"},
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return String.valueOf(Util.conversionInchesToCentimetres(character.getPenisRawSizeValue()));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"penisInches"},
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return String.valueOf(character.getPenisRawSizeValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"urethra"},
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return "urethra";
			}
		});
		
		// Second penis:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"secondPenisHead",
						"secondCockHead",
						"secondDickHead",
						"penis2Head",
						"cock2Head",
						"dick2Head"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getBody().getSecondPenis().getPenisHeadName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
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
						"dick2HeadD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getBody().getSecondPenis().getPenisHeadDescriptor(character), character.getBody().getSecondPenis().getPenisHeadName(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"secondPenisSize",
						"secondCockSize",
						"secondDickSize",
						"penis2Size",
						"cock2Size",
						"dick2Size"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getSecondPenisSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"secondPenisCm",
						"penis2Cm"},
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return String.valueOf(Util.conversionInchesToCentimetres(character.getSecondPenisRawSizeValue()));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"secondPenisInches",
						"penis2Inches"},
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return String.valueOf(character.getSecondPenisRawSizeValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"secondUrethra"},
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return "urethra";
			}
		});
		
		// Tail:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"tailCount",
						"tailsCount"},
				true,
				false,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getTailDeterminer();
			}
		});
		
		// Vagina:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"vaginaUrethra",
						"vaginalUrethra",
						"urethraVagina",
						"urethraVaginal"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return "urethra";
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"vaginaUrethra+",
						"vaginalUrethra+",
						"urethraVagina+",
						"urethraVaginal+",
						"vaginaUrethraD",
						"vaginalUrethraD",
						"urethraVaginaD",
						"urethraVaginalD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(character.getVaginaUrethraDescriptor(), "urethra");
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"vaginaCapacity",
						"pussyCapacity",
						"cuntCapacity"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return Capacity.getCapacityFromValue(character.getVaginaStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"vaginaElasticity",
						"pussyElasticity",
						"cuntElasticity"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getVaginaElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"vaginaWetness",
						"pussyWetness",
						"cuntWetness"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getVaginaWetness().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"labiaSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getVaginaLabiaSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"labia"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return "labia";
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"labia+",
						"labiaD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getVaginaLabiaSize().getName()+" labia";
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"clitSize",
						"clitorisSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getVaginaClitorisSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"clitSizeInches",
						"clitorisSizeInches"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return String.valueOf(character.getVaginaRawClitorisSizeValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"clitGirth",
						"clitorisGirth"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getClitorisGirth().getName();
			}
		});
		
		// Wings:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"wingSize",
						"wingsSize"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.WING){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getWingSize().getName();
			}
		});
		
		// Eyes:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"irisShape",
						"irisesShape"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getIrisShape().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"irisFullDescription",
						"irisesFullDescription"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(character.getEyeType().getBodyCoveringType(character))
						.getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"irisColour",
						"irisColor",
						"irisesColour",
						"irisesColor"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(character.getEyeType().getBodyCoveringType(character))
						.getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"irisColourPrimary",
						"irisColorPrimary",
						"irisesColourPrimary",
						"irisesColorPrimary",
						"irisPrimaryColour",
						"irisPrimaryColor",
						"irisesPrimaryColour",
						"irisesPrimaryColor"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(character.getEyeType().getBodyCoveringType(character))
						.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		commandsList.add(new ParserCommand(
				new String[] {
						"irisColourSecondary",
						"irisColorSecondary",
						"irisesColourSecondary",
						"irisesColorSecondary",
						"irisSecondaryColour",
						"irisSecondaryColor",
						"irisesSecondaryColour",
						"irisesSecondaryColor"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(character.getEyeType().getBodyCoveringType(character))
						.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"pupilShape",
						"pupilsShape"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getPupilShape().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"pupilFullDescription",
						"pupilsFullDescription"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"pupilColour",
						"pupilColor",
						"pupilsColour",
						"pupilsColor"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"pupilColourPrimary",
						"pupilColorPrimary",
						"pupilsColourPrimary",
						"pupilsColorPrimary",
						"pupilPrimaryColour",
						"pupilPrimaryColor",
						"pupilsPrimaryColour",
						"pupilsPrimaryColor"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		commandsList.add(new ParserCommand(
				new String[] {
						"pupilColourSecondary",
						"pupilColorSecondary",
						"pupilsColourSecondary",
						"pupilsColorSecondary",
						"pupilSecondaryColour",
						"pupilSecondaryColor",
						"pupilsSecondaryColour",
						"pupilsSecondaryColor"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"scleraFullDescription",
						"scleraeFullDescription"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"scleraColour",
						"scleraColor",
						"scleraeColour",
						"scleraeColor"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"scleraColourPrimary",
						"scleraColorPrimary",
						"scleraeColourPrimary",
						"scleraeColorPrimary",
						"scleraPrimaryColour",
						"scleraPrimaryColor",
						"scleraePrimaryColour",
						"scleraePrimaryColor"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		commandsList.add(new ParserCommand(
				new String[] {
						"scleraColourSecondary",
						"scleraColorSecondary",
						"scleraeColourSecondary",
						"scleraeColorSecondary",
						"scleraSecondaryColour",
						"scleraSecondaryColor",
						"scleraeSecondaryColour",
						"scleraeSecondaryColor"},
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		// Tail:
		
		commandsList.add(new ParserCommand(
				new String[] {
						"tailHead",
						"tailTip"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return character.getTailType().getTailTipName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				new String[] {
						"tailHead+",
						"tailHeadD",
						"tailTip+",
						"tailTipD"},
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
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

	private static GameCharacter character;
	private static List<GameCharacter> specialNPCList = new ArrayList<>();
	private static boolean parseCapitalise, parseAddPronoun;
	private static String parseSyntaxNew(String target, String command, String arguments, List<GameCharacter> specialNPCList) {
		
		UtilText.specialNPCList = specialNPCList;
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
			character = parserTarget.getCharacter(target.toLowerCase());
		} catch(Exception ex) {
			ex.printStackTrace();
			return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Error: parserTarget.getCharacter() not found!</i>";
		}
		
		// Commands with arguments:
		ParserCommand cmd = findCommandWithTag(command.replaceAll("\u200b", ""));
		if (cmd == null) {
			return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>command_unknown</i>";
		}
		
		String output = cmd.parse(command, arguments, target, character);
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
	}
	
	private static void initScriptEngine() {
		
		NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
		// http://hg.openjdk.java.net/jdk8/jdk8/nashorn/rev/eb7b8340ce3a
		engine = factory.getScriptEngine("-strict", "--no-java", "--no-syntax-extensions", "-scripting");
		
//		ScriptEngineManager manager = new ScriptEngineManager();
//		engine = manager.getEngineByName("javascript");
		
		for(ParserTarget target : ParserTarget.values()) {
			if(target!=ParserTarget.STYLE && target!=ParserTarget.NPC) {
				for(String tag : target.getTags()) {
					engine.put(tag, target.getCharacter(tag));
				}
			}
		}
		engine.put("game", Main.game);
		for(Fetish f : Fetish.values()) {
			engine.put(f.toString(), f);
		}
		for(CoverableArea ca : CoverableArea.values()) {
			engine.put("CA_"+ca.toString(), ca);
		}
		for(Weather w : Weather.values()) {
			engine.put("WEATHER_"+w.toString(), w);
		}
		for(DialogueFlagValue flag : DialogueFlagValue.values()) {
			engine.put("FLAG_"+flag.toString(), flag);
		}
		for(History history : History.values()) {
			engine.put("HISTORY_"+history.toString(), history);
		}
		engine.put("sex", Main.sexEngine); //TODO static methods don't work...
		
//		StringBuilder sb = new StringBuilder();
//		for(Entry<String, Object> entry : engine.getBindings(ScriptContext.ENGINE_SCOPE).entrySet()) {
//			sb.append(entry.getKey()+", "+entry.getValue().toString()+"\n");
//		}
//		System.out.println(sb.toString());
	}
	
	private static String parseConditionalSyntaxNew(List<GameCharacter> specialNPC, String conditionalStatement, String conditionalTrue, String conditionalFalse) {
		if(engine==null) {
			initScriptEngine();
		}
		
		if(!specialNPC.isEmpty()) {
			for(int i = 0; i<specialNPC.size(); i++) {
				if(i==0) {
					engine.put("npc", specialNPC.get(i));
				}
				engine.put("npc"+(i+1), specialNPC.get(i));
			}
		} else {
			try { // Getting the target NPC can throw a NullPointerException, so if it does (i.e., there's no NPC suitable for parsing), just catch it and carry on.
				engine.put("npc", ParserTarget.NPC.getCharacter("npc"));
			} catch(Exception ex) {
			}
		}
		
		try {
			if(Main.game.getCurrentDialogueNode()==DebugDialogue.PARSER) {
				if((boolean) engine.eval(conditionalStatement)) {
//					return conditionalTrue;
					return UtilText.parse(specialNPC, conditionalTrue);
				}
			} else if((boolean) engine.eval(conditionalStatement)){
//				return conditionalTrue;
				return UtilText.parse(specialNPC, conditionalTrue);
			}
//			return conditionalFalse;
			return UtilText.parse(specialNPC, conditionalFalse);
			
		} catch (ScriptException e) {
			System.err.println("Conditional parsing error: "+conditionalStatement);
			System.err.println(e.getMessage());
			return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>(Error in conditional parsing!)</i>";
		}
	}
	
	
	public static List<GameCharacter> getSpecialNPCList() {
		return specialNPCList;
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
	private static void addStandardParsingCommands(String[] tags, String[] tagsPlural, BodyPartType bodyPart) {
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "Race"),
				true,
				true,
				"",
				"Returns the name of the race that's associated with this body part. Race is *not* gender-specific (i.e. will return 'wolf-morph', not 'wolf-girl').",
				bodyPart){
			@Override
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				try {
					return getBodyPartFromType(bodyPart).getType().getRace().getName();
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
//			public String parse(String command, String arguments, String target, GameCharacter gc) {
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return getSkinName(getBodyPartFromType(bodyPart).getType());
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return getSkinNameWithDescriptor(getBodyPartFromType(bodyPart).getType());
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character))==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character)).getFullDescription(character, true);
					}
				}
				return character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character)).getFullDescription(character, false);
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character))==null) {
					return "";
				}
				return character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character)).getFullDescription(character, true);
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character))==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character)).getColourDescriptor(character, true, parseCapitalise);
					}
				}
				return character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character)).getColourDescriptor(character, false, parseCapitalise);
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character))==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character)).getPrimaryColourDescriptor(true);
					}
				}
				return character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character)).getPrimaryColourDescriptor(false);
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character))==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character)).getSecondaryColourDescriptor(true);
					}
				}
				return character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character)).getSecondaryColourDescriptor(false);
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character))==null) {
					return "";
				}
				return character.getCovering(getBodyPartFromType(bodyPart).getType().getBodyCoveringType(character)).getPrimaryColour().toWebHexString();
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return getBodyPartFromType(bodyPart).getNameSingular(character);
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart).getDeterminer(character), getBodyPartFromType(bodyPart).getNamePlural(character));
					
				} else {
					return getBodyPartFromType(bodyPart).getNamePlural(character);
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				return applyDescriptor(getBodyPartFromType(bodyPart).getDescriptor(character), getBodyPartFromType(bodyPart).getNameSingular(character));
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
			public String parse(String command, String arguments, String target, GameCharacter gc) {
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart).getDeterminer(character), applyDescriptor(getBodyPartFromType(bodyPart).getDescriptor(character), getBodyPartFromType(bodyPart).getNamePlural(character)));
					
				} else {
					return applyDescriptor(getBodyPartFromType(bodyPart).getDescriptor(character), getBodyPartFromType(bodyPart).getNamePlural(character));
				}
			}
		});
	}
	
	/**
	 * Helper method for generating tags with specified endings.
	 */
	private static String[] getModifiedTags(String[] tags, String[] tagsPlural, String... ending) {
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
		
		return ArrayUtil.convertToArray(modifiedTags);
	}
	
	
	private static BodyPartInterface getBodyPartFromType(BodyPartType type) {
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
	
	private static String getSubspeciesName(Subspecies race) {
		if(race==null)
			return "";
		if (character.isFeminine()) {
			return race.getSingularFemaleName();
		} else {
			return race.getSingularMaleName();
		}
	}
	
	private static String getSubspeciesNamePlural(Subspecies race) {
		if(race==null)
			return "";
		if (character.isFeminine()) {
			return race.getPluralFemaleName();
		} else {
			return race.getPluralMaleName();
		}
	}
	
	private static String getSkinName(BodyPartTypeInterface bodyPart) {
		if(bodyPart.getBodyCoveringType(character)==null)
			return "";
		
		if(parseAddPronoun) {
			parseAddPronoun = false;
			return applyDeterminer(bodyPart.getBodyCoveringType(character).getDeterminer(character), bodyPart.getBodyCoveringType(character).getName(character));
		} else {
			return bodyPart.getBodyCoveringType(character).getName(character);
		}
	}
	
	private static String getSkinNameWithDescriptor(BodyPartTypeInterface bodyPart) {
		if(bodyPart.getBodyCoveringType(character)==null)
			return "";
		
		if(parseAddPronoun) {
			parseAddPronoun = false;
			return applyDeterminer(bodyPart.getBodyCoveringType(character).getDeterminer(character),
					applyDescriptor(character.getCovering(bodyPart.getBodyCoveringType(character)).getModifier().getName(),
					bodyPart.getBodyCoveringType(character).getName(character)));
		} else {
			return applyDescriptor(character.getCovering(bodyPart.getBodyCoveringType(character)).getModifier().getName(), bodyPart.getBodyCoveringType(character).getName(character));
		}
	}
	
}
