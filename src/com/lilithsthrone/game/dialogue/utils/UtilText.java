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
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.1.98
 * @author Innoxia
 */
public class UtilText {

	private static String modifiedSentence;
	public static StringBuilder transformationContentSB = new StringBuilder(4096);
	public static StringBuilder nodeContentSB = new StringBuilder(4096);
	private static StringBuilder descriptionSB = new StringBuilder();

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
		
		if(Main.game.getPlayer().getAlcoholLevel()>0.75f) {
			modifiedSentence = Util.addDrunkSlur(modifiedSentence, 4);
		} else if(Main.game.getPlayer().getAlcoholLevel()>0.5f) {
			modifiedSentence = Util.addDrunkSlur(modifiedSentence, 8);
		}
		
		// Apply speech effects:
		if(Main.game.isInSex()) {
			if(Sex.isCharacterPenetrated(Main.game.getPlayer())) {
				modifiedSentence = Util.addSexSounds(modifiedSentence, 6);
			}
			
			if(Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.MOUTH)!=null) {
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

		if (target.hasFetish(Fetish.FETISH_BIMBO)) {
			modifiedSentence = Util.addBimbo(modifiedSentence, 6);
		}
		
		if(target.getAlcoholLevel()>0.75f) {
			modifiedSentence = Util.addDrunkSlur(modifiedSentence, 4);
		} else if(target.getAlcoholLevel()>0.5f) {
			modifiedSentence = Util.addDrunkSlur(modifiedSentence, 8);
		}
		
		// Apply speech effects:
		if(Main.game.isInSex()) {
			if(target.isPlayer()) {
				if(Sex.isCharacterPenetrated(Main.game.getPlayer())) {
					modifiedSentence = Util.addSexSounds(modifiedSentence, 6);
				}
				if(Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.MOUTH)!=null) {
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
				
			} else {
				if(Sex.isCharacterPenetrated(character)) {
					modifiedSentence = Util.addSexSounds(modifiedSentence, 6);
				}
				
				if(Sex.getPenetrationTypeInOrifice(character, OrificeType.MOUTH)!=null) {
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
			}
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
			if(Sex.isCharacterPenetrated(target))
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
		
		return "<" + tag + " style='color:" + Colour.CURRENCY_GOLD.toWebHexString() + "; padding-right:2px;'>" + getCurrencySymbol() + "</" + tag + ">"
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

//	private static String[] assNames = new String[] { "ass", "rear end", "butt", "rump" };
//
//	public static String getAssName() {
//		return assNames[Util.random.nextInt(assNames.length)];
//	}
//
//	private static String[] breastsNames = new String[] { "breasts", "boobs", "mammaries", "tits" };
//
//	public static String getBreastsName() {
//		return breastsNames[Util.random.nextInt(breastsNames.length)];
//	}

	// private static String[] penisNames = new String[]{"cock", "dick", "knob",
	// "member", "penis", "prick", "shaft", "manhood"};
	// public static String getPenisName(){
	// return penisNames[Utilities.random.nextInt(penisNames.length)];
	// }

//	private static String[] vaginaNames = new String[] { "cherry", "cunt", "fuck hole", "gash", "kitty", "muff", "pussy", "sex", "slit", "twat", "vagina" };
//
//	public static String getVaginaName() {
//		return vaginaNames[Util.random.nextInt(vaginaNames.length)];
//	}

//	private static String[] cumNames = new String[] { "cum", "cream", "jism", "jizz", "load", "seed", "spooge" };
//
//	public static String getCumName() {
//		return cumNames[Util.random.nextInt(cumNames.length)];
//	}

	private static String[] femaleCumNames = new String[] { "juices" };

	public static String getFemaleCumName() {
		return femaleCumNames[Util.random.nextInt(femaleCumNames.length)];
	}

	private static List<String> randomStrings = new ArrayList<>();
	/**
	 * @return Returns one of the supplied Strings, randomly chosen by using Random's nextInt() method.
	 */
	public static String returnStringAtRandom(String... strings) {
		randomStrings.clear();
		
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
		return parse(Util.newArrayListOfValues(new ListValue<>(specialNPC)), input);
	}
	
	public static String parse(GameCharacter specialNPC1, GameCharacter specialNPC2, String input) {
		return parse(Util.newArrayListOfValues(new ListValue<>(specialNPC1), new ListValue<>(specialNPC2)), input);
	}
	
	/**
	 * Parses supplied text.
	 */
	public static String parse(List<GameCharacter> specialNPC, String input) {
		
		// Loop through input, when find '[', start parsing.
		// [target.command(arguments)]
		
		// {npc1.isPlayer?Your:[npc1.Name]'s} [npc1.moans] are muffled into {npc2.isPlayer?your:[npc2.name]'s} [npc2.mouth]. {npc1.isPlayer?{npc1.isPlayer?Your:[npc1.Name]'s} feel turned on...}
		
		StringBuilder sb = new StringBuilder();
		
		int openBrackets = 0;
		int closeBrackets = 0;
		int openArg = 0;
		int closeArg = 0;
		int conditionalThens = 0;
		int startIndex = 0;
		int endIndex = 0;
		
		String target=null;
		String command=null;
		String arguments=null;
		String conditionalTrue=null;
		String conditionalFalse=null;
		
		boolean conditionalElseFound=false;
		boolean processingConditional=false;
		boolean processingRegular=false;
		
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			
			if(!processingRegular) {
				if(c=='F' && i-1>1 && input.charAt(i-1)=='I' && i-2>=0 && input.charAt(i-2)=='#') {
					if(openBrackets==0) {
						processingConditional=true;
						startIndex = i-2;
					}
					
					openBrackets++;
					
				} else if(processingConditional) {
					if(c=='.' && target==null) {
						target=sb.toString().substring(1); // Cut off the '#IF' at the start.
						target = target.trim();
						sb.setLength(0);
					
					} else if(c=='(') {
						if(command==null) {
							command=sb.toString().substring(1); // Cut off the '.' at the start.
							command = command.trim();
							sb.setLength(0);
						}
						
						openArg++;
						
					} else if(c==')') {
						closeArg++;
						
						if(openArg==closeArg){
							arguments = sb.toString().substring(1);
						}
						
					} else if((c=='N' &&
							(i-1>1 && input.charAt(i-1)=='E')
							&& (i-2>=0 && input.charAt(i-2)=='H')
							&& (i-3>=0 && input.charAt(i-3)=='T')
							&& (i-4>=0 && input.charAt(i-4)=='#'))) {
						conditionalThens++;
						
						if(conditionalThens==1){
							if(command==null) {
								command=sb.toString().substring(1, sb.length()-4); // Cut off the '#THEN' at the start.
								command = command.replaceAll("\n", "").replaceAll("\t", "");
								command = command.trim();
							}
							sb.setLength(0);
						}
						
					} else if((c=='E' &&
							(i-1>1 && input.charAt(i-1)=='S')
							&& (i-2>=0 && input.charAt(i-2)=='L')
							&& (i-3>=0 && input.charAt(i-3)=='E')
							&& (i-4>=0 && input.charAt(i-4)=='#')) && openBrackets-1==closeBrackets) {
						conditionalElseFound = true;
						conditionalTrue = sb.toString().substring(1, sb.length()-4); // Cut off the '#ELSE' at the start.
						sb.setLength(0);
						
					} else if(c=='F' &&
							(i-1>1 && input.charAt(i-1)=='I')
							&& (i-2>=0 && input.charAt(i-2)=='D')
							&& (i-3>=0 && input.charAt(i-3)=='N')
							&& (i-4>=0 && input.charAt(i-4)=='E')
							&& (i-5>=0 && input.charAt(i-5)=='#')) {
						closeBrackets++;
						
						if(openBrackets==closeBrackets) {
							
							if(conditionalElseFound){
								conditionalFalse = sb.toString().substring(1, sb.length()-5); // Cut off the '#ENDIF' at the start.
							} else {
								conditionalTrue = sb.toString().substring(1, sb.length()-5); // Cut off the '#ENDIF' at the start.
								conditionalFalse = "";
							}
		
							endIndex = i;
							break;
						}
					}
				}
			}
			
			if(!processingConditional) {
				if(c=='[') {
					if(openBrackets==0) {
						processingRegular=true;
						startIndex = i;
					}
					
					openBrackets++;
					
				} else if(processingRegular) {
					
					if(c=='.' && target==null) {
						target=sb.toString().substring(1); // Cut off the '[' at the start.
						sb.setLength(0);
					
					} else if(c=='(') {
						if(command==null) {
							command=sb.toString().substring(1); // Cut off the '.' at the start.
							sb.setLength(0);
						}
						
						openArg++;
						
					} else if(c==')') {
						closeArg++;
						
						if(openArg==closeArg){
							arguments = sb.toString().substring(1);
						}
						
					} else if(c==']') {
						closeBrackets++;
						
						if(openBrackets==closeBrackets) {
							if(command==null) {
								command=sb.toString().substring(1); // Cut off the '.' at the start.
								sb.setLength(0);
							}
		
							endIndex = i;
							break;
						}
					}
				}
			}
			
			
			if(openBrackets>0 && ((target!=null && command!=null) || String.valueOf(c).matches(".") || c!=' ')) {
				sb.append(c);
			}
		}
		
		
		if(startIndex!=0 || endIndex!=0) {
			return parse(specialNPC, input.substring(0, startIndex)
					+ (processingConditional
							?parseConditionalSyntaxNew(target, command, arguments, conditionalTrue, conditionalFalse)
							:parseSyntaxNew(target, command, arguments, specialNPC))
					+ input.substring(endIndex+1, input.length()));
		} else {
			return input;//.replaceAll(" a ", " <span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a big moo</span> ");
		}
	}
	

	public static List<ParserConditionalCommand> conditionalCommandsList = new ArrayList<>();
	
	public static List<ParserCommand> commandsList = new ArrayList<>();
	public static Map<BodyPartType, List<ParserCommand>> commandsMap = new EnumMap<>(BodyPartType.class);
	
	static{
		
		conditionalCommandsList.add(new ParserConditionalCommand(
				Util.newArrayListOfValues(
						new ListValue<>("isPlayer"),
						new ListValue<>("player")),
				"",
				"Returns true if the character is the player."){
			@Override
			public boolean process(String command, String arguments, String target) {
				return character.isPlayer();
			}
		});
		
		conditionalCommandsList.add(new ParserConditionalCommand(
				Util.newArrayListOfValues(
						new ListValue<>("isVaginaExposed"),
						new ListValue<>("isPussyExposed"),
						new ListValue<>("isExposedVagina"),
						new ListValue<>("isExposedPussy")),
				"",
				"Returns true if the character's vagina is exposed."){
			@Override
			public boolean process(String command, String arguments, String target) {
				return character.isCoverableAreaExposed(CoverableArea.VAGINA);
			}
		});
		
		conditionalCommandsList.add(new ParserConditionalCommand(
				Util.newArrayListOfValues(
						new ListValue<>("isArcaneStorm"),
						new ListValue<>("isStorm")),
				"",
				"Returns true if the weather is currently an arcane storm."){
			@Override
			public boolean process(String command, String arguments, String target) {
				return Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
			}
		});
		
		conditionalCommandsList.add(new ParserConditionalCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hairLengthMinimum"),
						new ListValue<>("minimumHairLength")),
				"(HairLength Enum)",
				"Returns true if the character's hair is at least as long as the supplied hair length's minimum value."){
			@Override
			public boolean process(String command, String arguments, String target) {
				if(arguments == null || arguments.isEmpty()) {
					System.err.println("No arguments passed for HairLength in the conditional statement 'hairLengthMinimum'!");
					return false;
				} else {
					try {
						return character.getHairRawLengthValue()>HairLength.valueOf(arguments).getMinimumValue();
					} catch(Exception ex) {
						System.err.println("Could not parse HairLength in the conditional statement 'hairLengthMinimum'!");
						return false;
					}
				}
			}
		});
		
		conditionalCommandsList.add(new ParserConditionalCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hairLengthMaximum"),
						new ListValue<>("maximumHairLength")),
				"(HairLength Enum)",
				"Returns true if the character's hair is no longer than the supplied hair length's maximum value."){
			@Override
			public boolean process(String command, String arguments, String target) {
				if(arguments == null || arguments.isEmpty()) {
					System.err.println("No arguments passed for HairLength in the conditional statement 'hairLengthMinimum'!");
					return false;
				} else {
					try {
						return character.getHairRawLengthValue()<=HairLength.valueOf(arguments).getMinimumValue();
					} catch(Exception ex) {
						System.err.println("Could not parse HairLength in the conditional statement 'hairLengthMinimum'!");
						return false;
					}
				}
			}
		});
		
		
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(new ListValue<>("name")),
				true,
				false,
				"(prefix)",
				"Returns the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter. If you want the basic form of the name, pass in a space as an argument."
				+ " If a prefix is provided, the prefix will be appended (with an automatic addition of a space) to non-capitalised names."){
			@Override
			public String parse(String command, String arguments, String target) {
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
				Util.newArrayListOfValues(new ListValue<>("surname")),
				true,
				false,
				"",
				"Returns the surname of the target."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getSurname();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(new ListValue<>("fullName")),
				true,
				false,
				"(prefix)",
				"Returns the name of the target, <b>automatically appending</b> 'the' to names that don't start with a capital letter. If you want the basic form of the name, pass in a space as an argument."
				+ " If a prefix is provided, the prefix will be appended (with an automatic addition of a space) to non-capitalised names."){
			@Override
			public String parse(String command, String arguments, String target) {
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
				Util.newArrayListOfValues(
						new ListValue<>("pcName"),
						new ListValue<>("pcPetName")),
				true,
				false,
				"",
				"Returns the name that this character prefers to call the player by."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getPlayerPetName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("description"),
						new ListValue<>("desc")),
				true,
				false,
				"",//TODO
				"Description"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getDescription();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("gender")),
				true,
				true,
				"(coloured)",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
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
						new ListValue<>("genderAppears"),
						new ListValue<>("genderAppearsAs"),
						new ListValue<>("appearsAsGender")),
				true,
				true,
				"(coloured)",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
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
						new ListValue<>("daughter"),
						new ListValue<>("son")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return "daughter";
				} else {
					return "son";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("mother"),
						new ListValue<>("father")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return "mother";
				} else {
					return "father";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("mommy"),
						new ListValue<>("daddy")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return "mommy";
				} else {
					return "daddy";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("mom"),
						new ListValue<>("mum"),
						new ListValue<>("dad")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return "mom";
				} else {
					return "dad";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("sister"),
						new ListValue<>("brother")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return "sister";
				} else {
					return "brother";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("sis"),
						new ListValue<>("bro")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return "sis";
				} else {
					return "bro";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("niece"),
						new ListValue<>("nephew")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return "niece";
				} else {
					return "nephew";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("mistress"),
						new ListValue<>("master")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return "mistress";
				} else {
					return "master";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("miss"),
						new ListValue<>("ms"),
						new ListValue<>("mister"),
						new ListValue<>("mr")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return "Miss";
				} else {
					return "Mr.";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("boyfriend"),
						new ListValue<>("girlfriend")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return "girlfriend";
				} else {
					return "boyfriend";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("bitch"),
						new ListValue<>("slut")),
				true,
				true,
				"",
				"Returns a random mean word to describe this person, based on their femininity."){ // R-Rude!
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					return UtilText.returnStringAtRandom("bitch", "slut", "cunt", "whore", "skank");
				} else {
					return UtilText.returnStringAtRandom("bitch", "bastard", "fuckface", "fucker");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("fullRace"),
						new ListValue<>("femininityRace")),
				true,
				true,
				"(coloured)",//TODO
				"Returns a full description of this characters race (including femininity)."){
			@Override
			public String parse(String command, String arguments, String target) {
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
				Util.newArrayListOfValues(
						new ListValue<>("race")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return getSubspeciesName(character.getSubspecies());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("races")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return getSubspeciesNamePlural(character.getSubspecies());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("raceStage")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getRaceStage().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialName")),
				true,
				true,
				"",
				"Returns the name of the character's BodyMaterial."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialSkin")),
				true,
				true,
				"",
				"Returns a descriptor of the character's skin."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getSkinNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialSkinAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's skin."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getSkinAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialSkinAlt")),
				true,
				true,
				"",
				"Returns a descriptor of the character's skin on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getSkinAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialSkinAltAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's skin on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getSkinAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialOrifice")),
				true,
				true,
				"",
				"Returns a descriptor of the material lining the character's orifices."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getOrificeNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialOrificeAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the material lining the character's orifices."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getOrificeAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialOrificeAlt")),
				true,
				true,
				"",
				"Returns a descriptor of the material lining the character's orifices on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getOrificeAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialOrificeAltAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the material lining the character's orifices on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getOrificeAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialHair")),
				true,
				true,
				"",
				"Returns a descriptor of the character's hair."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getHairNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialHairAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's hair."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getHairAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialHairBody"),
						new ListValue<>("materialBodyHair")),
				true,
				true,
				"",
				"Returns a descriptor of the character's body hair."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getHairBodyNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialHairBodyAdjective"),
						new ListValue<>("materialBodyHairAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's body hair."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getHairBodyAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialHairAlt")),
				true,
				true,
				"",
				"Returns a descriptor of the character's Hair on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getHairAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialHairAltAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's hair on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getHairAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialFur")),
				true,
				true,
				"",
				"Returns a descriptor of the character's fur."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getFurNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialFurAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's fur."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getFurAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialFurAlt")),
				true,
				true,
				"",
				"Returns a descriptor of the character's fur on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getFurAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialFurAltAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's fur on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getFurAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialFeather"),
						new ListValue<>("materialFeathers")),
				true,
				true,
				"",
				"Returns a descriptor of the character's feathers."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getFeatherNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialFeatherAdjective"),
						new ListValue<>("materialFeathersAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's feathers."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getFeatherAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialFeatherAlt"),
						new ListValue<>("materialFeathersAlt")),
				true,
				true,
				"",
				"Returns a descriptor of the character's feathers on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getFeatherAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialFeatherAltAdjective"),
						new ListValue<>("materialFeathersAltAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's fur on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getFeatherAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialScale"),
						new ListValue<>("materialScales")),
				true,
				true,
				"",
				"Returns a descriptor of the character's scales."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getScaleNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialScaleAdjective"),
						new ListValue<>("materialScalesAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's scales."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getScaleAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialScaleAlt"),
						new ListValue<>("materialScalesAlt")),
				true,
				true,
				"",
				"Returns a descriptor of the character's scales on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getScaleAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialScaleAltAdjective"),
						new ListValue<>("materialScalesAltAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's scales on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getScaleAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialShell")),
				true,
				true,
				"",
				"Returns a descriptor of the character's shell."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getShellNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialShellAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's shell."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getShellAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialShellAlt")),
				true,
				true,
				"",
				"Returns a descriptor of the character's shell on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getShellAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialShellAltAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's shell on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getShellAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialKeratin")),
				true,
				true,
				"",
				"Returns a descriptor of the character's keratin."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getKeratinNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialKeratinAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's keratin."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getKeratinAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialKeratinAlt")),
				true,
				true,
				"",
				"Returns a descriptor of the character's keratin on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getKeratinAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("materialKeratinAltAdjective")),
				true,
				true,
				"",
				"Returns an adjectival descriptor of the character's keratin on nonhumanoid body parts."){
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyMaterial().getKeratinAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("femininity"),
						new ListValue<>("fem"),
						new ListValue<>("masculinity"),
						new ListValue<>("mas")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return Femininity.valueOf(character.getFemininityValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("bodySize")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return BodySize.valueOf(character.getBodySizeValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("muscle")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return Muscle.valueOf(character.getMuscleValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("bodyShape")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBodyShape().getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("height")),
				true,
				true,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getHeight().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("heightCm")),
				false,
				false,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return String.valueOf(character.getHeightValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("heightInches")),
				false,
				false,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return String.valueOf(Util.conversionCentimetresToInches(character.getHeightValue()));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("heightFeetInches")),
				false,
				false,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return Util.inchesToFeetAndInches(Util.conversionCentimetresToInches(character.getHeightValue()));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("weight")),
				false,
				false,
				"",//TODO
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return String.valueOf(character.getWeight());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("speech"),
						new ListValue<>("dialogue"),
						new ListValue<>("talk"),
						new ListValue<>("say")),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					return parseSpeech(arguments, character);
				} else {
					return parseSpeech("...", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("speechMasculine"),
						new ListValue<>("dialogueMasculine"),
						new ListValue<>("talkMasculine"),
						new ListValue<>("sayMasculine")),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.MASCULINE);
				} else {
					return parseNPCSpeech("...", Femininity.MASCULINE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("speechMasculineStrong"),
						new ListValue<>("dialogueMasculineStrong"),
						new ListValue<>("talkMasculineStrong"),
						new ListValue<>("sayMasculineStrong")),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.MASCULINE_STRONG);
				} else {
					return parseNPCSpeech("...", Femininity.MASCULINE_STRONG);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("speechAndrogynous"),
						new ListValue<>("dialogueAndrogynous"),
						new ListValue<>("talkAndrogynous"),
						new ListValue<>("sayAndrogynous")),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.ANDROGYNOUS);
				} else {
					return parseNPCSpeech("...", Femininity.ANDROGYNOUS);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("speechFeminine"),
						new ListValue<>("dialogueFeminine"),
						new ListValue<>("talkFeminine"),
						new ListValue<>("sayFeminine")),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.FEMININE);
				} else {
					return parseNPCSpeech("...", Femininity.FEMININE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("speechNoEffects"),
						new ListValue<>("dialogueNoEffects"),
						new ListValue<>("talkNoEffects"),
						new ListValue<>("sayNoEffects")),
				false,
				false,
				"(speech content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					return parseSpeechNoEffects(arguments, character);
				} else {
					return parseSpeechNoEffects("...", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("thought")),
				false,
				false,
				"(thought content)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					return parseThought(arguments, character);
				} else {
					return parseThought("...", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("moan"),
						new ListValue<>("groan"),
						new ListValue<>("sob"),
						new ListValue<>("cry")),
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."){
			@Override
			public String parse(String command, String arguments, String target) {
				if(Main.game.isInSex()) {
					if((character.isPlayer() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) || (!character.isPlayer() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
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
						new ListValue<>("moan+"),
						new ListValue<>("moanD"),
						new ListValue<>("groan+"),
						new ListValue<>("groanD"),
						new ListValue<>("sob+"),
						new ListValue<>("sobD"),
						new ListValue<>("cry+"),
						new ListValue<>("cryD")),
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."
				+ " <b>Expansion of 'moan' command:</b> This command will append a suitable descriptor before the 'moan' noise. e.g. 'lewd squeal', or 'eager grunt'."){
			@Override
			public String parse(String command, String arguments, String target) {
				if(Main.game.isInSex()) {
					if((character.isPlayer() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) || (!character.isPlayer() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("sob", "scream", "cry");
						} else {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("shout", "cry");
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
						new ListValue<>("moanVerb"),
						new ListValue<>("groanVerb"),
						new ListValue<>("sobVerb"),
						new ListValue<>("cryVerb")),
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."){
			@Override
			public String parse(String command, String arguments, String target) {
				if(Main.game.isInSex()) {
					if((character.isPlayer() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) || (!character.isPlayer() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
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
						new ListValue<>("moanVerb+"),
						new ListValue<>("moanVerbD"),
						new ListValue<>("groanVerb+"),
						new ListValue<>("groanVerbD"),
						new ListValue<>("sobVerb+"),
						new ListValue<>("sobVerbD"),
						new ListValue<>("cryVerb+"),
						new ListValue<>("cryVerbD")),
				true,
				true,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will moan, while if they are masculine, they will groan."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sob' or 'cry'."
				+ " <b>Expansion of 'moan' command:</b> This command will append a suitable descriptor before the 'moan' noise. e.g. 'lewd squeal', or 'eager grunt'."){
			@Override
			public String parse(String command, String arguments, String target) {
				if(Main.game.isInSex()) {
					if((character.isPlayer() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) || (!character.isPlayer() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserably", "pathetically") + " " + returnStringAtRandom("sob", "scream", "cry");
						} else {
							return returnStringAtRandom("miserably", "pathetically") + " " + returnStringAtRandom("shout", "cry");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("lewdly", "desperately") + " " + returnStringAtRandom("moan", "squeal", "cry", "gasp");
				} else {
					return returnStringAtRandom("lewdly", "desperately") + " " + returnStringAtRandom("groan", "grunt");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("moans"),
						new ListValue<>("groans"),
						new ListValue<>("sobs"),
						new ListValue<>("cries")),
				true,
				false,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will make moans, while if they are masculine, they will make groans."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sobs' or 'cries'."
				+" <b>Provides an appropriate <i>noun</i> version of 'moans'.</b> (Use 'moansVerb' for the verb version.)"){
			@Override
			public String parse(String command, String arguments, String target) {
				if(Main.game.isInSex()) {
					if((character.isPlayer() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) || (!character.isPlayer() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
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
						new ListValue<>("moans+"),
						new ListValue<>("moansD"),
						new ListValue<>("groans+"),
						new ListValue<>("groansD"),
						new ListValue<>("sobs+"),
						new ListValue<>("sobsD"),
						new ListValue<>("cries+"),
						new ListValue<>("criesD")),
				true,
				false,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will make moans, while if they are masculine, they will make groans."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sobs' or 'cries'."
				+ " <b>Expansion of 'moans' command:</b> This command will append a suitable descriptor before the 'moans' noise. e.g. 'lewd squeals', or 'eager grunts'."
				+ " <b>Provides an appropriate <i>noun</i> version of 'moans'.</b> (Use 'moansVerb+' for the verb version.)"){
			@Override
			public String parse(String command, String arguments, String target) {
				if(Main.game.isInSex()) {
					if((character.isPlayer() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) || (!character.isPlayer() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("sobs", "cries");
						} else {
							return returnStringAtRandom("miserable", "pathetic", "distressed") + " " + returnStringAtRandom("shouts", "cries");
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
						new ListValue<>("moansVerb"),
						new ListValue<>("groansVerb"),
						new ListValue<>("sobsVerb"),
						new ListValue<>("criesVerb")),
				true,
				false,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will make moans, while if they are masculine, they will make groans."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sobs' or 'cries'."
				+" <b>Provides an appropriate <i>verb</i> version of 'moans'.</b> (Use 'moans' for the noun version.)"){
			@Override
			public String parse(String command, String arguments, String target) {
				if(Main.game.isInSex()) {
					if((character.isPlayer() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) || (!character.isPlayer() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
						if(character.isFeminine()) {
							return returnStringAtRandom("sobs", "cries");
						} else {
							return returnStringAtRandom("shouts", "cries");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("moans", "squeals");
				} else {
					return returnStringAtRandom("groans", "grunts");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("moansVerb+"),
						new ListValue<>("moansVerbD"),
						new ListValue<>("groansVerb+"),
						new ListValue<>("groansVerbD"),
						new ListValue<>("sobsVerb+"),
						new ListValue<>("sobsVerbD"),
						new ListValue<>("criesVerb+"),
						new ListValue<>("criesVerbD")),
				true,
				false,
				"",
				"Returns a suitable variant of a 'sexual noise' that the target might make. For example, if they're feminine, they will make moans, while if they are masculine, they will make groans."
				+ " This method takes into account if the target is resisting, and if they are, the returned noise will be something like 'sobs' or 'cries'."
				+ " <b>Expansion of 'moansVerb' command:</b> This command will append a suitable descriptor before the 'moans' noise. e.g. 'lewdly squeals', or 'eagerly grunts'."
				+ " <b>Provides an appropriate <i>verb</i> version of 'moans'.</b> (Use 'moans+' for the noun version.)"){
			@Override
			public String parse(String command, String arguments, String target) {
				if(Main.game.isInSex()) {
					if((character.isPlayer() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) || (!character.isPlayer() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("sobs", "cries");
						} else {
							return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("shouts", "cries");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("lewdly", "desperately") + " " + returnStringAtRandom("moans", "squeals", "cries");
				} else {
					return returnStringAtRandom("eagerly", "desperately") + " " + returnStringAtRandom("groans", "grunts", "cries");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("moaning"),
						new ListValue<>("groaning"),
						new ListValue<>("sobbing"),
						new ListValue<>("crying")),
				true,
				false,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(Main.game.isInSex()) {
					if((character.isPlayer() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) || (!character.isPlayer() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
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
						new ListValue<>("moaning+"),
						new ListValue<>("moaningD"),
						new ListValue<>("groaning+"),
						new ListValue<>("groaningD"),
						new ListValue<>("sobbing+"),
						new ListValue<>("sobbingD"),
						new ListValue<>("crying+"),
						new ListValue<>("cryingD")),
				true,
				false,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(Main.game.isInSex()) {
					if((character.isPlayer() && Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) || (!character.isPlayer() && Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING)) {
						if(character.isFeminine()) {
							return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("sobbing", "crying");
						} else {
							return returnStringAtRandom("miserably", "pathetically", "desperately") + " " + returnStringAtRandom("shouting", "protesting");
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
						new ListValue<>("scent"),
						new ListValue<>("smell")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.GENERIC){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
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
						new ListValue<>("girl"),
						new ListValue<>("boy")),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine())
					return Gender.F_V_B_FEMALE.getNounYoung();
				else
					return Gender.M_P_MALE.getNounYoung();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("woman"),
						new ListValue<>("man")),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine())
					return Gender.F_V_B_FEMALE.getNoun();
				else
					return Gender.M_P_MALE.getNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("female"),
						new ListValue<>("male")),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine())
					return Gender.F_V_B_FEMALE.getName();
				else
					return Gender.M_P_MALE.getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("feminine"),
						new ListValue<>("masculine")),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine())
					return "feminine";
				else
					return "masculine";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("her"),
						new ListValue<>("his"),
						new ListValue<>("herPos"),
						new ListValue<>("herHis"),
						new ListValue<>("hisPos"),
						new ListValue<>("hisHer")),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
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
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hers"),
						new ListValue<>("hersHis"),
						new ListValue<>("hisHers")),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.isFeminine()) {
					if(character.isPlayer()) {
						return Gender.F_V_B_FEMALE.getPossessiveAlone();
					} else {
						return GenderPronoun.POSSESSIVE_ALONE.getFeminine();
					}
				} else {
					if(character.isPlayer()) {
						return Gender.M_P_MALE.getPossessiveAlone();
					} else {
						return GenderPronoun.POSSESSIVE_ALONE.getMasculine();
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("him"),
						new ListValue<>("herPro"),
						new ListValue<>("herHim"),
						new ListValue<>("himHer")),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
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
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("she"),
						new ListValue<>("sheHe"),
						new ListValue<>("he"),
						new ListValue<>("heShe")),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
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
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("herself"),
						new ListValue<>("himself")),
				true,
				true,
				"",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
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
		});
		
		// Clothing:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("topClothing"),
						new ListValue<>("highestClothing"),
						new ListValue<>("highClothing")),
				true,
				true,
				"(bodyPart)",
				"Returns the name of the highest piece of clothing that's blocking the area passed in as an argument. Possible arguments:</br>"
				+ "vagina | pussy | cunt</br>"
				+ "penis | cock | dick</br>"
				+ "nipples | nipple</br>"
				+ "ass | asshole"){
			@Override
			public String parse(String command, String arguments, String target) {
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
				Util.newArrayListOfValues(
						new ListValue<>("bottomClothing"),
						new ListValue<>("lowestClothing"),
						new ListValue<>("lowClothing")),
				true,
				true,
				"(bodyPart)",
				"Returns the name of the lowest piece of clothing that's blocking the area passed in as an argument. Possible arguments:</br>"
				+ "vagina | pussy | cunt</br>"
				+ "penis | cock | dick</br>"
				+ "nipples | nipple</br>"
				+ "ass | asshole"){
			@Override
			public String parse(String command, String arguments, String target) {
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
				Util.newArrayListOfValues(
						new ListValue<>("bold"),
						new ListValue<>("b")),
				false,
				false,
				"(text to make bold)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null)
					return "<b>"+arguments+"</b>";
				else
					return "<b>...</b>";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("italic"),
						new ListValue<>("italics"),
						new ListValue<>("i")),
				false,
				false,
				"(text to italicise)",
				"Description of method"){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
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
					public String parse(String command, String arguments, String target) {
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
					public String parse(String command, String arguments, String target) {
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
					public String parse(String command, String arguments, String target) {
						if(arguments!=null)
							return "<i style='color:"+c.toWebHexString()+";'>"+arguments+"</i>";
						else
							return "<i style='color:"+c.toWebHexString()+";'>...</i>";
					}
				});
			}
		}
		
		
		
		
		
		
		
		
		// Body parts:
		
		// Add standard parsing for all types:

		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("antenna")),
				Util.newArrayListOfValues(new ListValue<>("antennae")),
				BodyPartType.ANTENNA);
				
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("arm")),
				Util.newArrayListOfValues(new ListValue<>("arms")),
				BodyPartType.ARM);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("ass"), new ListValue<>("butt")),
				Util.newArrayListOfValues(new ListValue<>("asses"), new ListValue<>("butts")),
				BodyPartType.ASS);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("anus"), new ListValue<>("asshole")),
				Util.newArrayListOfValues(new ListValue<>("anuses"), new ListValue<>("assholes")),
				BodyPartType.ANUS);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("breast"), new ListValue<>("tit"), new ListValue<>("boob"), new ListValue<>("chest")),
				Util.newArrayListOfValues(new ListValue<>("breasts"), new ListValue<>("tits"), new ListValue<>("boobs")),
				BodyPartType.BREAST);

		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("nipple"), new ListValue<>("teat")),
				Util.newArrayListOfValues(new ListValue<>("nipples"), new ListValue<>("teats")),
				BodyPartType.NIPPLES);

		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("milk")),
				Util.newArrayListOfValues(new ListValue<>("milks")), // milks? Really?
				BodyPartType.MILK);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("ear")),
				Util.newArrayListOfValues(new ListValue<>("ears")),
				BodyPartType.EAR);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("eye")),
				Util.newArrayListOfValues(new ListValue<>("eyes")),
				BodyPartType.EYE);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("face")),
				Util.newArrayListOfValues(new ListValue<>("faces")),
				BodyPartType.FACE);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("mouth")),
				Util.newArrayListOfValues(new ListValue<>("mouths")),
				BodyPartType.MOUTH);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("hairSingular"), new ListValue<>("feather")),
				Util.newArrayListOfValues(new ListValue<>("hair"), new ListValue<>("feathers")),
				BodyPartType.HAIR);

		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("horn")),
				Util.newArrayListOfValues(new ListValue<>("horns")),
				BodyPartType.HORN);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("leg")),
				Util.newArrayListOfValues(new ListValue<>("legs")),
				BodyPartType.LEG);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("penis"), new ListValue<>("cock"), new ListValue<>("dick")),
				Util.newArrayListOfValues(new ListValue<>("penises"), new ListValue<>("cocks"), new ListValue<>("dicks")),
				BodyPartType.PENIS);

		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("secondPenis"), new ListValue<>("secondCock"), new ListValue<>("secondDick"), new ListValue<>("penis2"), new ListValue<>("cock2"), new ListValue<>("dick2")),
				Util.newArrayListOfValues(new ListValue<>("secondPenises"), new ListValue<>("secondCocks"), new ListValue<>("secondDicks"), new ListValue<>("penises2"), new ListValue<>("cocks2"), new ListValue<>("dicks2")),
				BodyPartType.SECOND_PENIS);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("testicle"), new ListValue<>("ball")),
				Util.newArrayListOfValues(new ListValue<>("testicles"), new ListValue<>("balls")),
				BodyPartType.TESTICLES);

		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("cum")),
				Util.newArrayListOfValues(new ListValue<>("cums")), // :s
				BodyPartType.CUM);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("skin")),
				Util.newArrayListOfValues(new ListValue<>("skinPlural")),
				BodyPartType.SKIN);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("tail")),
				Util.newArrayListOfValues(new ListValue<>("tails")),
				BodyPartType.TAIL);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("tongue")),
				Util.newArrayListOfValues(new ListValue<>("tongues")),
				BodyPartType.TONGUE);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("vagina"), new ListValue<>("pussy"), new ListValue<>("cunt")),
				Util.newArrayListOfValues(new ListValue<>("vaginas"), new ListValue<>("pussies"), new ListValue<>("cunts")),
				BodyPartType.VAGINA);

		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("girlcum"), new ListValue<>("gcum")),
				Util.newArrayListOfValues(new ListValue<>("girlcums"), new ListValue<>("gcums")),
				BodyPartType.GIRL_CUM);

		addStandardParsingCommands(
				Util.newArrayListOfValues(new ListValue<>("wing")),
				Util.newArrayListOfValues(new ListValue<>("wings")),
				BodyPartType.WING);
		
		
		// Special body parts:
		
		// Arm:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("armRows")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
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
				Util.newArrayListOfValues(
						new ListValue<>("hand")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getArmType().getHandsNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hand+"),
						new ListValue<>("handD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getArmType().getHandsDescriptor(character), character.getArmType().getHandsNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hands")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getArmType().getHandsNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hands+"),
						new ListValue<>("handsD")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getArmType().getHandsDescriptor(character), character.getArmType().getHandsNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("finger")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getArmType().getFingersNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("finger+"),
						new ListValue<>("fingerD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getArmType().getFingersDescriptor(character), character.getArmType().getFingersNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("fingers")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getArmType().getFingersNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("fingers+"),
						new ListValue<>("fingersD")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getArmType().getFingersDescriptor(character), character.getArmType().getFingersNamePlural(character));
			}
		});
		
	
		// Ass:
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("assSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getAssSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("assCapacity"),
						new ListValue<>("assholeCapacity")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return Capacity.getCapacityFromValue(character.getAssStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("assElasticity"),
						new ListValue<>("assholeElasticity")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getAssElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("assWetness"),
						new ListValue<>("assholeWetness")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getAssWetness().getDescriptor();
			}
		});
		
		// Hips: TODO rough
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hipSkin"),
						new ListValue<>("hipsSkin")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return getSkinName(character.getSkinType());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hipSkin+"),
						new ListValue<>("hipsSkin+")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return getSkinNameWithDescriptor(character.getSkinType());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hip"),
						new ListValue<>("hips")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return "hips";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hip+"),
						new ListValue<>("hipD"),
						new ListValue<>("hips+"),
						new ListValue<>("hipsD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getHipSize().getDescriptor(), "hips");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hipSize"),
						new ListValue<>("hipsSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getHipSize().getDescriptor();
			}
		});
		
		// Breasts:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("breastSize"),
						new ListValue<>("breastsSize"),
						new ListValue<>("titSize"),
						new ListValue<>("titsSize"),
						new ListValue<>("boobSize"),
						new ListValue<>("boobsSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBreastSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("breastShape"),
						new ListValue<>("breastsShape"),
						new ListValue<>("titShape"),
						new ListValue<>("titsShape"),
						new ListValue<>("boobShape"),
						new ListValue<>("boobsShape")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBreastShape().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("nippleSize"),
						new ListValue<>("nipplesSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getNippleSize().getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("areolaSize"),
						new ListValue<>("areolaeSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getAreolaeSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("cupSize"),
						new ListValue<>("cups"),
						new ListValue<>("breastCups"),
						new ListValue<>("breastsCups"),
						new ListValue<>("titCups"),
						new ListValue<>("titsCups"),
						new ListValue<>("boobCups"),
						new ListValue<>("boobsCups")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBreastSize().getCupSizeName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("breastCapacity"),
						new ListValue<>("breastsCapacity"),
						new ListValue<>("titCapacity"),
						new ListValue<>("titsCapacity"),
						new ListValue<>("boobCapacity"),
						new ListValue<>("boobsCapacity")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return Capacity.getCapacityFromValue(character.getNippleStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("breastElasticity"),
						new ListValue<>("breastsElasticity"),
						new ListValue<>("titElasticity"),
						new ListValue<>("titsElasticity"),
						new ListValue<>("boobElasticity"),
						new ListValue<>("boobsElasticity")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getNippleElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("breastRows"),
						new ListValue<>("nippleRows")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(character.getBreastRows()==1) {
					return "pair of";
				} else {
					return Util.intToString(character.getBreastRows())+" pairs of";
				}
			}
		});
		
//		commandsList.add(new ParserCommand(
//				Util.newArrayListOfValues(
//						new ListValue<>("milk"),
//						new ListValue<>("milkName")),
//				true,
//				true,
//				"",
//				"Description of method",
//				BodyPartType.BREAST){//TODO
//			@Override
//			public String parse(String command, String arguments, String target) {
//				return character.getMilkName();
//			}
//		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("lactation")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBreastMilkStorage().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("milkRegen"),
						new ListValue<>("milkRegeneration")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBreastLactationRegeneration().getName();
			}
		});
		
		// Eyes:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("eyePairs"),
						new ListValue<>("eyesPairs"),
						new ListValue<>("eyeRows"),
						new ListValue<>("eyesRows")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getEyeDeterminer();
			}
		});
		
		// Face:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("tongueLength"),
						new ListValue<>("tongueSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getTongueLength().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("nose")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getNoseNameSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("noses")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getNoseNamePlural();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("lipSize"),
						new ListValue<>("lipsSize")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getLipSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("lip")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getLipsNameSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("lip+"),
						new ListValue<>("lipD")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getLipsDescriptor(), character.getLipsNameSingular());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("lips")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getLipsNamePlural();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("lips+"),
						new ListValue<>("lipsD")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getLipsDescriptor(), character.getLipsNamePlural());
			}
		});
		
		// Hair:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hairLength")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.HAIR){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getHairLength().getDescriptor();
			}
		});
		
		// Horns:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("hornSize"),
						new ListValue<>("hornsSize"),
						new ListValue<>("hornLength"),
						new ListValue<>("hornsLength")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.HORN){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return HornLength.getHornLengthFromInt(character.getHornLength()).getDescriptor();
			}
		});
		
		// Leg:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("thighs")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return "thighs";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("thighs+"),
						new ListValue<>("thighsD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return "thighs";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("foot")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getLegType().getFeetNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("foot+"),
						new ListValue<>("footD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getLegType().getFeetDescriptor(character), character.getLegType().getFeetNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("feet")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getLegType().getFeetNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("feet+"),
						new ListValue<>("feetD")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getLegType().getFeetDescriptor(character), character.getLegType().getFeetNamePlural(character));
			}
		});
		
		// Penis:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("penisUrethra"),
						new ListValue<>("cockUrethra"),
						new ListValue<>("urethraPenis"),
						new ListValue<>("urethraCock")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return "urethra";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("penisUrethra+"),
						new ListValue<>("cockUrethra+"),
						new ListValue<>("urethraPenis+"),
						new ListValue<>("urethraCock+"),
						new ListValue<>("penisUrethraD"),
						new ListValue<>("cockUrethraD"),
						new ListValue<>("urethraPenisD"),
						new ListValue<>("urethraCockD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getPenisUrethraDescriptor(), "urethra");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("cumAmount"),
						new ListValue<>("cumProduction"),
						new ListValue<>("jizzAmount"),
						new ListValue<>("jizzProduction")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getPenisCumProduction().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("cumMl"),
						new ListValue<>("cumMeasurement")),
				false,
				false,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return String.valueOf(character.getPenisRawCumProductionValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("ballsCount"),
						new ListValue<>("ballCount"),
						new ListValue<>("testiclesCount")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return (parseCapitalise
						?Util.capitaliseSentence(Util.intToString(character.getPenisNumberOfTesticles()))
						:Util.intToString(character.getPenisNumberOfTesticles()));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("ballSize"),
						new ListValue<>("ballsSize"),
						new ListValue<>("testicleSize"),
						new ListValue<>("testiclesSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getTesticleSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("penisHead"),
						new ListValue<>("cockHead"),
						new ListValue<>("dickHead")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBody().getPenis().getPenisHeadName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("penisHead+"),
						new ListValue<>("penisHeadD"),
						new ListValue<>("cockHead+"),
						new ListValue<>("cockHeadD"),
						new ListValue<>("dickHead+"),
						new ListValue<>("dickHeadD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getBody().getPenis().getPenisHeadDescriptor(character), character.getBody().getPenis().getPenisHeadName(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("penisSize"),
						new ListValue<>("cockSize"),
						new ListValue<>("dickSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getPenisSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("penisGirth"),
						new ListValue<>("cockGirth"),
						new ListValue<>("dickGrith")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getPenisGirth().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("penisCm")),
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return String.valueOf(Util.conversionInchesToCentimetres(character.getPenisRawSizeValue()));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("penisInches")),
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return String.valueOf(character.getPenisRawSizeValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("urethra")),
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return "urethra";
			}
		});
		
		// Second penis:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("secondPenisHead"),
						new ListValue<>("secondCockHead"),
						new ListValue<>("secondDickHead"),
						new ListValue<>("penis2Head"),
						new ListValue<>("cock2Head"),
						new ListValue<>("dick2Head")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getBody().getSecondPenis().getPenisHeadName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("secondPenisHead+"),
						new ListValue<>("secondCockHead+"),
						new ListValue<>("secondDickHead+"),
						new ListValue<>("penis2Head+"),
						new ListValue<>("cock2Head+"),
						new ListValue<>("dick2Head+"),
						new ListValue<>("secondPenisHeadD"),
						new ListValue<>("secondCockHeadD"),
						new ListValue<>("secondDickHeadD"),
						new ListValue<>("penis2HeadD"),
						new ListValue<>("cock2HeadD"),
						new ListValue<>("dick2HeadD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getBody().getSecondPenis().getPenisHeadDescriptor(character), character.getBody().getSecondPenis().getPenisHeadName(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("secondPenisSize"),
						new ListValue<>("secondCockSize"),
						new ListValue<>("secondDickSize"),
						new ListValue<>("penis2Size"),
						new ListValue<>("cock2Size"),
						new ListValue<>("dick2Size")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getSecondPenisSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("secondPenisCm"),
						new ListValue<>("penis2Cm")),
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return String.valueOf(Util.conversionInchesToCentimetres(character.getSecondPenisRawSizeValue()));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("secondPenisInches"),
						new ListValue<>("penis2Inches")),
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return String.valueOf(character.getSecondPenisRawSizeValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("secondUrethra")),
				false,
				false,
				"",
				"Description of method",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return "urethra";
			}
		});
		
		// Tail:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("tailCount"),
						new ListValue<>("tailsCount")),
				true,
				false,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getTailDeterminer();
			}
		});
		
		// Vagina:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("vaginaUrethra"),
						new ListValue<>("vaginalUrethra"),
						new ListValue<>("urethraVagina"),
						new ListValue<>("urethraVaginal")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return "urethra";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("vaginaUrethra+"),
						new ListValue<>("vaginalUrethra+"),
						new ListValue<>("urethraVagina+"),
						new ListValue<>("urethraVaginal+"),
						new ListValue<>("vaginaUrethraD"),
						new ListValue<>("vaginalUrethraD"),
						new ListValue<>("urethraVaginaD"),
						new ListValue<>("urethraVaginalD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return applyDescriptor(character.getVaginaUrethraDescriptor(), "urethra");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("vaginaCapacity"),
						new ListValue<>("pussyCapacity"),
						new ListValue<>("cuntCapacity")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return Capacity.getCapacityFromValue(character.getVaginaStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("vaginaElasticity"),
						new ListValue<>("pussyElasticity"),
						new ListValue<>("cuntElasticity")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getVaginaElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("vaginaWetness"),
						new ListValue<>("pussyWetness"),
						new ListValue<>("cuntWetness")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getVaginaWetness().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("labiaSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getVaginaLabiaSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("labia")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return "labia";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("labia+"),
						new ListValue<>("labiaD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getVaginaLabiaSize().getName()+" labia";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("clit"),
						new ListValue<>("clitoris")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return returnStringAtRandom("clit", "button", "nub");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("clit+"),
						new ListValue<>("clitD"),
						new ListValue<>("clitoris+"),
						new ListValue<>("clitorisD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return returnStringAtRandom(character.getVaginaClitorisSize().getDescriptor(), "feminine", "delicate", "sensitive") + returnStringAtRandom(" clit", " button", " nub");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("clitSize"),
						new ListValue<>("clitorisSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getVaginaClitorisSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("clitSizeInches"),
						new ListValue<>("clitorisSizeInches")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.VAGINA){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return String.valueOf(character.getVaginaRawClitorisSizeValue());
			}
		});
		
		// Wings:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("wingSize"),
						new ListValue<>("wingsSize")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.WING){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getWingSize().getName();
			}
		});
		
		// Eyes:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("irisShape"),
						new ListValue<>("irisesShape")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getIrisShape().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("pupilShape"),
						new ListValue<>("pupilsShape")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getPupilShape().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("irisFullDescription"),
						new ListValue<>("irisesFullDescription")),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(character.getEyeType().getBodyCoveringType(character)).getFullDescription(character, true);
					}
				}
				return character.getCovering(character.getEyeType().getBodyCoveringType(character)).getFullDescription(character, false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("irisColour"),
						new ListValue<>("irisColor"),
						new ListValue<>("irisesColour"),
						new ListValue<>("irisesColor")),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(character.getEyeType().getBodyCoveringType(character)).getColourDescriptor(character, true, parseCapitalise);
					}
				}
				return character.getCovering(character.getEyeType().getBodyCoveringType(character)).getColourDescriptor(character, false, parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("irisColourPrimary"),
						new ListValue<>("irisColorPrimary"),
						new ListValue<>("irisesColourPrimary"),
						new ListValue<>("irisesColorPrimary"),
						new ListValue<>("irisPrimaryColour"),
						new ListValue<>("irisPrimaryColor"),
						new ListValue<>("irisesPrimaryColour"),
						new ListValue<>("irisesPrimaryColor")),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(character.getEyeType().getBodyCoveringType(character)).getPrimaryColourDescriptor(true);
					}
				}
				return character.getCovering(character.getEyeType().getBodyCoveringType(character)).getPrimaryColourDescriptor(false);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("irisColourSecondary"),
						new ListValue<>("irisColorSecondary"),
						new ListValue<>("irisesColourSecondary"),
						new ListValue<>("irisesColorSecondary"),
						new ListValue<>("irisSecondaryColour"),
						new ListValue<>("irisSecondaryColor"),
						new ListValue<>("irisesSecondaryColour"),
						new ListValue<>("irisesSecondaryColor")),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(character.getEyeType().getBodyCoveringType(character)).getSecondaryColourDescriptor(true);
					}
				}
				return character.getCovering(character.getEyeType().getBodyCoveringType(character)).getSecondaryColourDescriptor(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("pupilFullDescription"),
						new ListValue<>("pupilsFullDescription")),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(BodyCoveringType.EYE_PUPILS).getFullDescription(character, true);
					}
				}
				return character.getCovering(BodyCoveringType.EYE_PUPILS).getFullDescription(character, false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("pupilColour"),
						new ListValue<>("pupilColor"),
						new ListValue<>("pupilsColour"),
						new ListValue<>("pupilsColor")),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(BodyCoveringType.EYE_PUPILS).getColourDescriptor(character, true, parseCapitalise);
					}
				}
				return character.getCovering(BodyCoveringType.EYE_PUPILS).getColourDescriptor(character, false, parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("pupilColourPrimary"),
						new ListValue<>("pupilColorPrimary"),
						new ListValue<>("pupilsColourPrimary"),
						new ListValue<>("pupilsColorPrimary"),
						new ListValue<>("pupilPrimaryColour"),
						new ListValue<>("pupilPrimaryColor"),
						new ListValue<>("pupilsPrimaryColour"),
						new ListValue<>("pupilsPrimaryColor")),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(BodyCoveringType.EYE_PUPILS).getPrimaryColourDescriptor(true);
					}
				}
				return character.getCovering(BodyCoveringType.EYE_PUPILS).getPrimaryColourDescriptor(false);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("pupilColourSecondary"),
						new ListValue<>("pupilColorSecondary"),
						new ListValue<>("pupilsColourSecondary"),
						new ListValue<>("pupilsColorSecondary"),
						new ListValue<>("pupilSecondaryColour"),
						new ListValue<>("pupilSecondaryColor"),
						new ListValue<>("pupilsSecondaryColour"),
						new ListValue<>("pupilsSecondaryColor")),
				true,
				true,
				"true If you want this colour's name to be coloured.",
				"Description of method",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return character.getCovering(BodyCoveringType.EYE_PUPILS).getSecondaryColourDescriptor(true);
					}
				}
				return character.getCovering(BodyCoveringType.EYE_PUPILS).getSecondaryColourDescriptor(false);
			}
		});
		
		// Tail:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("tailHead"),
						new ListValue<>("tailTip")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
				return character.getTailType().getTailTipName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						new ListValue<>("tailHead+"),
						new ListValue<>("tailHeadD"),
						new ListValue<>("tailTip+"),
						new ListValue<>("tailTipD")),
				true,
				true,
				"",
				"Description of method",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(String command, String arguments, String target) {
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
		parseCapitalise=false;
		parseAddPronoun=false;
		
		if(command.split("_").length==2) {
			if(Character.isUpperCase(command.split("_")[0].charAt(0)))
				parseCapitalise = true;
			command = command.split("_")[1];
			if(Character.isUpperCase(command.charAt(0)))
				parseCapitalise = true;
			parseAddPronoun = true;
			
		} else {
			if(Character.isUpperCase(command.charAt(0)))
				parseCapitalise = true;
		}
		
		String output = "";
		boolean commandFound=false;
		
		boolean characterFound = false;
		targetLoop:
		for(ParserTarget parserTarget : ParserTarget.values()) {
			for(String s : parserTarget.getTags()) {
				if(s.toLowerCase().equals(target.toLowerCase())) {
					character = parserTarget.getCharacter(s.toLowerCase());
					characterFound = true;
					break targetLoop;
				}
			}
//			if(parserTarget.getTags().contains(target.toLowerCase())) {
//				character = parserTarget.getCharacter();
//				characterFound = true;
//				break;
//			}
		}
		if(!characterFound) {
			return "INVALID_TARGET_NAME";
		}
		
		// Commands with arguments:
		
		hibikeEuphoniumIsTheBest:
		for(ParserCommand cmd : commandsList) {
			for(String s : cmd.getTags()) {
				if(command.equalsIgnoreCase(s)) {
					commandFound=true;
					output = cmd.parse(command, arguments, target);
					if (!cmd.isAllowsCapitalisation()) {
						parseCapitalise = false;
					}
					if (!cmd.isAllowsPronoun()) {
						parseAddPronoun = false;
					}
					break hibikeEuphoniumIsTheBest;
				}
			}
		}
		
		if(!commandFound)
			return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>command_unknown</i>";
		
		if(parseAddPronoun) {
			output = (UtilText.isVowel(output.charAt(0))?"an ":"a ")+output;
			return (parseCapitalise?Util.capitaliseSentence(output):output);
		} else {
			return (parseCapitalise?Util.capitaliseSentence(output):output);
		}
	}
	
	private static String parseConditionalSyntaxNew(String target, String command, String arguments, String conditionalTrue, String conditionalFalse) {
		if(target==null || target.isEmpty()) {
			target = "npc";
		}
		
		boolean characterFound = false;
		targetLoop:
		for(ParserTarget parserTarget : ParserTarget.values()) {
			for(String s : parserTarget.getTags()) {
				if(s.toLowerCase().equals(target.toLowerCase())) {
					character = parserTarget.getCharacter(s.toLowerCase());
					characterFound = true;
					break targetLoop;
				}
			}
		}
		if(!characterFound) {
			return "INVALID_TARGET_NAME";
		}
		
		// Commands with arguments:
		
		for(ParserConditionalCommand cmd : conditionalCommandsList) {
			for(String s : cmd.getTags()) {
				if(command.equalsIgnoreCase(s)) {
					if (cmd.process(command, arguments, target)) {
						return conditionalTrue;
					} else {
						return conditionalFalse;
					}
				}
			}
		}
		
		return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>command_unknown</i>";
		
	}
	
	
	public static List<GameCharacter> getSpecialNPCList() {
		return specialNPCList;
	}
	
	/**
	 * Adds standard commands related to the baseCommand.</br>
	 * Commands include:</br>
	 * Race</br>
	 * Skin</br>
	 * Skin+</br>
	 * Colour</br>
	 * name</br>
	 * name+</br>
	 * names</br>
	 * names+</br>
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
			public String parse(String command, String arguments, String target) {
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
//			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
			public String parse(String command, String arguments, String target) {
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
				return character.getBody().getPenis();
			case SECOND_PENIS:
				return character.getBody().getSecondPenis();
			case TESTICLES:
				return character.getBody().getPenis().getTesticle();
			case CUM:
				return character.getBody().getPenis().getTesticle().getCum();
			case SKIN:
				return character.getBody().getSkin();
			case TAIL:
				return character.getBody().getTail();
			case TONGUE:
				return character.getBody().getFace().getTongue();
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
