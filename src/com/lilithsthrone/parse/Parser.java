package com.lilithsthrone.parse;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.GameCharacter;
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
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.ArrayUtil;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.StringUtils;
import com.lilithsthrone.utils.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

public class Parser {

	public static List<ParserCommand> commandsList = new ArrayList<>();
    public static Map<String, ParserFunction> functionMap = new HashMap<String, ParserFunction>();
	public static Map<BodyPartType, List<ParserCommand>> commandsMap = new EnumMap<>(BodyPartType.class);

	private static ScriptEngine engine;

	enum ParseMode {
		UNKNOWN,
		CONDITIONAL,
		REGULAR
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

	private static boolean substringMatchesInReverseAtIndex(String input, String stringToMatch, int index) {
		index++;//this fixes my off by one error and I'm too tired to figure out why
		int startingLocation = index - stringToMatch.length();
		if (startingLocation < 0 || index > input.length()) {
			return false;
		}
		return input.substring(startingLocation, index).equals(stringToMatch);
	}

	/**
	 * Parses supplied text.
	 */
	public static String parse(List<GameCharacter> specialNPC, String input) {
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
					Parser.specialNPCList = specialNPC;
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
			return Parser.parse(specialNPC, strings.get(Util.random.nextInt(strings.size())));
		}
	}

	private static List<GameCharacter> specialNPCList = new ArrayList<>();
	private static boolean parseCapitalise;
	private static String parseSyntaxNew(String target, String command, String arguments, List<GameCharacter> specialNPCList) {
		
		Parser.specialNPCList = specialNPCList;
		parseCapitalise = false;
			
		if(Character.isUpperCase(command.charAt(0))) {
			parseCapitalise = true;
		}
		
		ParserTarget parserTarget = findParserTargetWithTag(target.replaceAll("\u200b", ""));
		if (parserTarget == null) {
			return "INVALID_TARGET_NAME("+target+")";
		}
		
		GameCharacter character;
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
					return parse(specialNPC, conditionalTrue);
				}
			} else if((boolean) engine.eval(conditionalStatement)){
//				return conditionalTrue;
				return parse(specialNPC, conditionalTrue);
			}
//			return conditionalFalse;
			return parse(specialNPC, conditionalFalse);
			
		} catch (ScriptException e) {
			System.err.println("Conditional parsing error: "+conditionalStatement);
			System.err.println(e.getMessage());
			return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>(Error in conditional parsing!)</i>";
		}
	}

	private static String modifiedSentence;
	public static StringBuilder transformationContentSB = new StringBuilder(4096);
	public static StringBuilder nodeContentSB = new StringBuilder(4096);
	
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
			
		} else if(Main.game.isInSex() && Sex.getAllParticipants().contains(target)) {
			if(Sex.isCharacterEngagedInOngoingAction(target)) {
				modifiedSentence = Util.addSexSounds(modifiedSentence, 6);
			}
			
			if(!Sex.getContactingSexAreas(target, SexAreaOrifice.MOUTH).isEmpty()) {
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
	
	private static void addParserFunction(ParserFunction function){
		for(String key : function.getTags()){
			Parser.functionMap.put(key, function);
		}
	}

	static{

		Parser.addParserFunction(new ParserFunction(new String[] {"money"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return UtilText.formatAsMoney(arguments.split(", ")[0], arguments.split(", ")[1]);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {
			"moneyUncoloured",
			"moneyNoColour",
			"moneyUncolored",
			"moneyNoColor"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return UtilText.formatAsMoneyUncoloured(Integer.valueOf(arguments.split(", ")[0]), arguments.split(", ")[1]);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"random"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				List<String> strings = new ArrayList<>();
				for(String s : arguments.split("\\|")) {
					strings.add(s);
				}
				return Util.randomItemFrom(strings);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"name"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayerKnowsName() || target.isPlayer()) {
					return target.getName();
				}
				return target.getName("the");
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"nameOrRace"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayerKnowsName() || target.isPlayer()) {
					return target.getName();
				}
				return target.getName("a");
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"namePos"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "your";
				}
				if(target.isPlayerKnowsName()) {
					return StringUtils.makePossesive(target.getName());
				}
				return StringUtils.makePossesive(target.getName("the"));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"nameIs"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "you're";
				}
				if(target.isPlayerKnowsName()) {
					return target.getName() + "'s";
				}
				return target.getName("the") + "'s";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"nameIsFull"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "you are";
				}
				if(target.isPlayerKnowsName()) {
					return target.getName() + " is";
				}
				return target.getName("the") + " is";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"nameHas"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "you've";
				}
				if(target.isPlayerKnowsName()) {
					return target.getName() + "'s";
				}
				return target.getName("the") + "'s";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"verb", "verbPerson"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if (target.isPlayer()) {
					return arguments;
				}

				return StringUtils.makeVerbAgree(arguments);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"surname"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getSurname();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"fullName"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayerKnowsName() || target.isPlayer()) {
					return target.getFullName();
				}
				return target.getName("the");
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"pcName", "pcPetName"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getPlayerPetName();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"description", "desc"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getDescription();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"job", "jobName"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isSlave()) {
					return target.getSlaveJob().getName(target);
				}
				return target.getHistory().getName();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"a_gender", "gender"}, true) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				Gender gender = target.getGender();
				
				boolean article = StringUtils.startsWithAny(command, "a_", "A_");
				boolean capitalize = StringUtils.startsWithUpperCase(command);
				String result = gender.getName();

				if (article) {
					result = UtilText.generateSingularDeterminer(gender.getName()) + " " + result;
				}

				if (capitalize) {
					result = Util.capitaliseSentence(result);
				}

				if (arguments!=null && Boolean.valueOf(arguments)) {
					result = "<span style='color:"+gender.getColour().toWebHexString()+";'>" + result +"</span>";
				}

				return result;
			}
		});

		Parser.addParserFunction(new ParserFunction(
			new String[] {
				"a_genderAppears", "a_genderAppearsAs", "a_appearsAsGender",
				"genderAppears", "genderAppearsAs", "appearsAsGender"},
			true) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				Gender gender = target.getAppearsAsGender();
				
				boolean article = StringUtils.startsWithAny(command, "a_", "A_");
				boolean capitalize = StringUtils.startsWithUpperCase(command);
				String result = gender.getName();

				if (article) {
					result = UtilText.generateSingularDeterminer(gender.getName()) + " " + result;
				}

				if (capitalize) {
					result = Util.capitaliseSentence(result);
				}

				if (arguments!=null && Boolean.valueOf(arguments)) {
					result = "<span style='color:"+gender.getColour().toWebHexString()+";'>" + result +"</span>";
				}

				return result;
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"daughter", "son"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "daughter" : "son";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"mother", "father"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "mother" : "father";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"mommy", "daddy"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "mommy" : "daddy";
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] {"mom", "mum", "dad"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "mom" : "dad";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"sister", "brother"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "sister" : "brother";
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] {"sis", "bro"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "sis" : "bro";
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] {"niece", "nephew"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "niece" : "nephew";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"mistress", "master"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "mistress" : "master";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"heroine", "hero"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "heroine" : "hero";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"miss", "ms", "mister", "mr"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "Miss" : "Mr.";
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] {"girlfriend", "boyfriend"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "girlfriend" : "boyfriend";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"bitch", "slut"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isFeminine()) {
					return UtilText.returnStringAtRandom("bitch", "slut", "cunt", "whore", "skank");
				} else {
					return UtilText.returnStringAtRandom("asshole", "bastard", "fuckface", "fucker");
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {
			"a_fullRace", "a_raceFull", "a_femininityRace",
			"fullRace", "raceFull", "femininityRace"},
			true) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {

				boolean article = StringUtils.startsWithAny(command, "a_", "A_");
				boolean capitalize = StringUtils.startsWithUpperCase(command);

				String result = Femininity.getFemininityName(target.getFemininityValue(), article);

				if (capitalize) {
					result = Util.capitaliseSentence(result);
				}

				if (arguments!=null && Boolean.valueOf(arguments)) {
					result = "<span style='color:"+target.getFemininity().getColour().toWebHexString()+";'>" + result +"</span>"
						+ " <span style='color:"+target.getRaceStage().getColour().toWebHexString()+";'>" +target.getRaceStage().getName()+"</span>"
						+ " <span style='color:"+target.getSubspecies().getColour().toWebHexString()+";'>" +  getSubspeciesName(target) + "</span>";
				} else {
					result += " "+target.getRaceStage().getName()+" "+getSubspeciesName(target);
				}

				return result;
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] {"a_race", "race"}, true) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {

				boolean article = StringUtils.startsWithAny(command, "a_", "A_");
				boolean capitalize = StringUtils.startsWithUpperCase(command);

				String result = target.isRaceConcealed() ? "unknown race" : getSubspeciesName(target);

				if (article) {
					result = UtilText.generateSingularDeterminer(result) + " " + result;
				}

				if (capitalize) {
					result = Util.capitaliseSentence(result);
				}

				if (arguments!=null && Boolean.valueOf(arguments)) {
					result = "<span style='color:" 
						+ ( target.isRaceConcealed() ? Colour.TEXT_GREY : target.getSubspecies().getColour()).toWebHexString()+";'>"
						+ result +"</span>";
				} 

				return result;
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"races"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return getSubspeciesNamePlural(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"a_raceStage", "raceStage"}, true) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {

				boolean article = StringUtils.startsWithAny(command, "a_", "A_");
				boolean capitalize = StringUtils.startsWithUpperCase(command);

				String result = target.getRaceStage().getName();

				if (article) {
					result = UtilText.generateSingularDeterminer(result) + " " + result;
				}

				if (capitalize) {
					result = Util.capitaliseSentence(result);
				}

				if (arguments!=null && Boolean.valueOf(arguments)) {
					result = "<span style='color:" + target.getRaceStage().getColour().toWebHexString() + ";'>" + result +"</span>";
				} 

				return result;
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"preferredBody"}, true) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(!(target instanceof NPC)) {
					return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>preferredBody_not_npc</i>";
				}
				if(arguments!=null) {
					return ((NPC) target).getPreferredBodyDescription(arguments);
				}
				return ((NPC) target).getPreferredBodyDescription("b");
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"material"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				switch(arguments) {
					case "body":
						return target.getBodyMaterial().getName();
					case "bodyHair":
					case "hairBody":
						return target.getBodyMaterial().getHairBodyNoun();
					case "feathers":
						return target.getBodyMaterial().getFeatherNoun();
					case "feathersAlt":
						return target.getBodyMaterial().getFeatherAltNoun();
					case "fur":
						return target.getBodyMaterial().getFurNoun();
					case "furAlt":
						return target.getBodyMaterial().getFurAltNoun();
					case "hair":
						return target.getBodyMaterial().getHairNoun();
					case "hairAlt":
						return target.getBodyMaterial().getHairAltNoun();
					case "keratin":
						return target.getBodyMaterial().getKeratinNoun();
					case "keratinAlt":
						return target.getBodyMaterial().getKeratinAltNoun();
					case "orifice":
						return target.getBodyMaterial().getOrificeNoun();
					case "orificeAlt":
						return target.getBodyMaterial().getOrificeAltNoun();
					case "scales":
						return target.getBodyMaterial().getScaleNoun();
					case "scalesAlt":
						return target.getBodyMaterial().getScaleAltNoun();
					case "shell":
						return target.getBodyMaterial().getShellNoun();
					case "shellAlt":
						return target.getBodyMaterial().getShellAltNoun();
					case "skin":
						return target.getBodyMaterial().getSkinNoun();
					case "skinAlt":
						return target.getBodyMaterial().getSkinAltNoun();

					default:
						return "<i>Parser Error: Unknown body part material '" + arguments + "'</i>";
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {"materialAdj"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				switch(arguments) {
					case "bodyHair":
					case "hairBody":
						return target.getBodyMaterial().getHairBodyAdj();
					case "feathers":
						return target.getBodyMaterial().getFeatherAdj();
					case "feathersAlt":
						return target.getBodyMaterial().getFeatherAltAdj();
					case "fur":
						return target.getBodyMaterial().getFurAdj();
					case "furAlt":
						return target.getBodyMaterial().getFurAltAdj();
					case "hair":
						return target.getBodyMaterial().getHairAdj();
					case "hairAlt":
						return target.getBodyMaterial().getHairAltAdj();
					case "keratin":
						return target.getBodyMaterial().getKeratinAdj();
					case "keratinAlt":
						return target.getBodyMaterial().getKeratinAltAdj();
					case "orifice":
						return target.getBodyMaterial().getOrificeAdj();
					case "orificeAlt":
						return target.getBodyMaterial().getOrificeAltAdj();
					case "scales":
						return target.getBodyMaterial().getScaleAdj();
					case "scalesAlt":
						return target.getBodyMaterial().getScaleAltAdj();
					case "shell":
						return target.getBodyMaterial().getShellAdj();
					case "shellAlt":
						return target.getBodyMaterial().getShellAltAdj();
					case "skin":
						return target.getBodyMaterial().getSkinAdj();
					case "skinAlt":
						return target.getBodyMaterial().getSkinAltAdj();

					default:
						return "<i>Parser Error: Unknown body part material '" + arguments + "'</i>";
				}
			}
		});

		
		Parser.addParserFunction(new ParserFunction(new String[] { "femininity", "fem", "masculinity", "mas" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return Femininity.valueOf(target.getFemininityValue()).getName(false);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "bodySize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return BodySize.valueOf(target.getBodySizeValue()).getName(false);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "muscle" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return Muscle.valueOf(target.getMuscleValue()).getName(false);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "bodyShape" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getBodyShape().getName(false);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "height" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getHeight().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "heightCm" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return String.valueOf(target.getHeightValue());
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "heightInches" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return String.valueOf(Util.conversionCentimetresToInches(target.getHeightValue()));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "heightFeetInches" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return Util.inchesToFeetAndInches(Util.conversionCentimetresToInches(target.getHeightValue()));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "weight" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return String.valueOf(target.getWeight());
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "speech", "dialogue", "talk", "say" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(arguments!=null) {
					return parseSpeech(arguments, target);
				} else {
					return parseSpeech("...", target);
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"speechMasculine", 
			"dialogueMasculine", 
			"talkMasculine", 
			"sayMasculine" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.MASCULINE);
				} else {
					return parseNPCSpeech("...", Femininity.MASCULINE);
				}
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] { 
			"speechMasculineStrong",
			"dialogueMasculineStrong",
			"talkMasculineStrong",
			"sayMasculineStrong" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.MASCULINE_STRONG);
				} else {
					return parseNPCSpeech("...", Femininity.MASCULINE_STRONG);
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"speechAndrogynous",
			"dialogueAndrogynous",
			"talkAndrogynous",
			"sayAndrogynous" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.ANDROGYNOUS);
				} else {
					return parseNPCSpeech("...", Femininity.ANDROGYNOUS);
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"speechFeminine",
			"dialogueFeminine",
			"talkFeminine",
			"sayFeminine" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.FEMININE);
				} else {
					return parseNPCSpeech("...", Femininity.FEMININE);
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"speechNoEffects",
			"dialogueNoEffects",
			"talkNoEffects",
			"sayNoEffects" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(arguments!=null) {
					return parseSpeechNoEffects(arguments, target);
				} else {
					return parseSpeechNoEffects("...", target);
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "thought" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(arguments!=null) {
					return parseThought(arguments, target);
				} else {
					return parseThought("...", target);
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "moan", "groan", "sob", "cry" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(Sex.getSexPace(target)==SexPace.SUB_RESISTING) {
					return Parser.randomProtest(target);
				}
				
				return Parser.randomMoan(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "moan+", "moanD", "groan+", "groanD", "sob+", "sobD", "cry+", "cryD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(Sex.getSexPace(target)==SexPace.SUB_RESISTING) {
					return UtilText.returnStringAtRandom("miserable", "pathetic", "distressed") + " " + Parser.randomProtest(target);
				}
				
				if(target.isFeminine()) {
					return UtilText.returnStringAtRandom("lewd", "high-pitched", "desperate") + " " + UtilText.returnStringAtRandom("moan", "squeal", "cry", "gasp");
				} else {
					return UtilText.returnStringAtRandom("deep", "low", "desperate") + " " + UtilText.returnStringAtRandom("groan", "grunt");
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"moanVerb", "groanVerb", "sobVerb", "cryVerb",
			"moansVerb", "groansVerb", "sobsVerb", "criesVerb" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				StringUtils.VerbAgreement agreement = target.isPlayer() 
					? StringUtils.VerbAgreement.Plural 
					: StringUtils.VerbAgreement.Singular;
					
				if(Sex.getSexPace(target)==SexPace.SUB_RESISTING) {
					return StringUtils.makeVerbAgree(Parser.randomProtest(target), agreement);
				}
				
				return StringUtils.makeVerbAgree(Parser.randomProtest(target), agreement);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"moanVerb+", "moanVerbD", "groanVerb+", "groanVerbD", "sobVerb+", "sobVerbD", "cryVerb+", "cryVerbD",
			"moansVerb+", "moansVerbD", "groansVerb+", "groansVerbD", "sobsVerb+", "sobsVerbD", "criesVerb+", "criesVerbD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				StringUtils.VerbAgreement agreement = target.isPlayer() 
					? StringUtils.VerbAgreement.Plural 
					: StringUtils.VerbAgreement.Singular;
					
				if(Sex.getSexPace(target)==SexPace.SUB_RESISTING) {
					return UtilText.returnStringAtRandom("miserably", "pathetically", "desperately") + " " 
						+ StringUtils.makeVerbAgree(Parser.randomProtest(target), agreement);
				}
				
				return UtilText.returnStringAtRandom("lewdly", "desperately") + " " 
					+ StringUtils.makeVerbAgree(Parser.randomProtest(target), agreement);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "moans", "groans", "sobs", "crys" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(Sex.getSexPace(target)==SexPace.SUB_RESISTING) {
					return StringUtils.pluralise(Parser.randomProtest(target));
				}
				
				return StringUtils.pluralise(Parser.randomMoan(target));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"moans+", "moansD", "groans+", "groansD", "sobs+", "sobsD", "cries+", "criesD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(Sex.getSexPace(target)==SexPace.SUB_RESISTING) {
					return UtilText.returnStringAtRandom("miserable", "pathetic", "distressed") + " " + StringUtils.pluralise(Parser.randomProtest(target));
				}
				
				if(target.isFeminine()) {
					return UtilText.returnStringAtRandom("lewd", "high-pitched", "desperate") + " " + UtilText.returnStringAtRandom("moans", "squeals", "gasps");
				} else {
					return UtilText.returnStringAtRandom("deep", "eager", "desperate") + " " + UtilText.returnStringAtRandom("groans", "grunts");
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "moaning","groaning", "sobbing", "crying" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(Sex.getSexPace(target)==SexPace.SUB_RESISTING) {
					return StringUtils.conjugateVerb(Parser.randomProtest(target), StringUtils.VerbTense.Continuous);
				}
				
				return StringUtils.conjugateVerb(Parser.randomMoan(target), StringUtils.VerbTense.Continuous);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"moaning+", "moaningD", "groaning+", "groaningD", "sobbing+", "sobbingD", "crying+", "cryingD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(Sex.getSexPace(target)==SexPace.SUB_RESISTING) {
					return UtilText.returnStringAtRandom("miserably", "pathetically", "desperately") + " " 
						+ StringUtils.conjugateVerb(Parser.randomProtest(target), StringUtils.VerbTense.Continuous);
				}
				
				return UtilText.returnStringAtRandom("lewdly", "eagerly", "desperately") + " " 
					+ StringUtils.conjugateVerb(Parser.randomMoan(target), StringUtils.VerbTense.Continuous);
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] { "scent", "smell" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isFeminine()) {
					return UtilText.returnStringAtRandom("scent", "scent", "perfume");
				} else {
					return UtilText.returnStringAtRandom("musk", "musk", "aroma");
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "scent+", "scentD", "smell+", "smellD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isFeminine()) {
					return UtilText.returnStringAtRandom("feminine scent", "feminine perfume", "delicate scent");
				} else {
					return UtilText.returnStringAtRandom("masculine musk", "masculine aroma");
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "girl", "boy" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isFeminine()) {
					return Gender.F_V_B_FEMALE.getNounYoung();
				} else {
					return Gender.M_P_MALE.getNounYoung();
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "woman", "man" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isFeminine()) {
					return Gender.F_V_B_FEMALE.getNoun();
				} else {
					return Gender.M_P_MALE.getNoun();
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "female", "male" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isFeminine()) {
					return Gender.F_V_B_FEMALE.getName();
				} else {
					return Gender.M_P_MALE.getName();
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "feminine", "masculine" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isFeminine() ? "feminine" :  "masculine";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "have", "has" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isPlayer() ? "have" : "has";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "does", "do" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.isPlayer() ? "do" : "does";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "her", "his", "herHis", "hisHer" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "your";
				} else {
					if(target.isFeminine()) {
						return GenderPronoun.POSSESSIVE_BEFORE_NOUN.getFeminine();
					} else {
						return GenderPronoun.POSSESSIVE_BEFORE_NOUN.getMasculine();
					}
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "hers", "his", "hersHis", "hisHers" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "yours";
				} else {
					if(target.isFeminine()) {
						return Gender.F_V_B_FEMALE.getPossessiveAlone();
					} else {
						return Gender.M_P_MALE.getPossessiveAlone();
					}
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "hers", "his", "hersHis", "hisHers" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "yours";
				} else {
					if(target.isFeminine()) {
						return Gender.F_V_B_FEMALE.getPossessiveAlone();
					} else {
						return Gender.M_P_MALE.getPossessiveAlone();
					}
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "nameHers", "nameHis", "nameHersHis", "nameHisHers" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "yours";
				} else {
					if(target.isPlayerKnowsName()) {
						return target.getName() + "'s";
					}
					return target.getName("the") + "'s";
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "him", "her", "herHim", "himHer" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "you";
				} else {
					if (target.isFeminine()) {
						return GenderPronoun.THIRD_PERSON.getFeminine();
					} else {
						return GenderPronoun.THIRD_PERSON.getMasculine();
					}
				}
			}
		});

		
		Parser.addParserFunction(new ParserFunction(new String[] { "she", "sheHe", "he", "heShe" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "you";
				} else {
					if (target.isFeminine()) {
						return GenderPronoun.SECOND_PERSON.getFeminine();
					} else {
						return GenderPronoun.SECOND_PERSON.getMasculine();
					}
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "sheIs", "sheHeIs", "heIs", "heSheIs" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "you're";
				} else {
					if(target.isFeminine()) {
						return GenderPronoun.SECOND_PERSON.getFeminine() + "'s";
					} else {
						return GenderPronoun.SECOND_PERSON.getMasculine() + "'s";
					}
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "sheIsFull", "sheHeIsFull", "heIsFull", "heSheIsFull" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "you are";
				} else {
					if(target.isFeminine()) {
						return GenderPronoun.SECOND_PERSON.getFeminine() + " is";
					} else {
						return GenderPronoun.SECOND_PERSON.getMasculine() + " is";
					}
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "sheHas", "sheHeHas", "heHas", "heSheHas" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "you've";
				} else {
					if(target.isFeminine()) {
						return GenderPronoun.SECOND_PERSON.getFeminine() + "'s";
					} else {
						return GenderPronoun.SECOND_PERSON.getMasculine() + "'s";
					}
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "sheHasFull", "sheHeHasFull", "heHasFull", "heSheHasFull" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "you have";
				} else {
					if(target.isFeminine()) {
						return GenderPronoun.SECOND_PERSON.getFeminine() + " has";
					} else {
						return GenderPronoun.SECOND_PERSON.getMasculine() + " has";
					}
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "herself", "himself" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.isPlayer()) {
					return "yourself";
				} else {
					if(target.isFeminine()) {
						return GenderPronoun.THIRD_PERSON.getFeminine()+"self";
					} else {
						return GenderPronoun.THIRD_PERSON.getMasculine()+"self";
					}
				}
			}
		});
		
		// Clothing:

		Parser.addParserFunction(new ParserFunction(new String[] { "topClothing", "highestClothing", "highClothing" }, true) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(arguments!=null) {
					switch (arguments.toLowerCase()){
						case "vagina":
						case "pussy":
						case "cunt":
							if(target.getHighestZLayerCoverableArea(CoverableArea.VAGINA)==null)
								return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_vagina</i>";
							else 
								return target.getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName();
						case "penis":
						case "dick":
						case "cock":
							if(target.getHighestZLayerCoverableArea(CoverableArea.PENIS)==null)
								return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_penis</i>";
							else
								return target.getHighestZLayerCoverableArea(CoverableArea.PENIS).getName();
						case "nipple":
						case "nipples":
							if(target.getHighestZLayerCoverableArea(CoverableArea.NIPPLES)==null)
								return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_nipples</i>";
							else
								return target.getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName();
						case "ass":
						case "asshole":
							if(target.getHighestZLayerCoverableArea(CoverableArea.ANUS)==null)
								return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_asshole</i>";
							else
								return target.getHighestZLayerCoverableArea(CoverableArea.ANUS).getName();
						default:
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";

					}
				} else {
					return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "bottomClothing", "lowestClothing", "lowClothing" }, true) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(arguments!=null) {
					switch (arguments.toLowerCase()){
						case "vagina":
						case "pussy":
						case "cunt":
							if(target.getLowestZLayerCoverableArea(CoverableArea.VAGINA)==null)
								return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_vagina</i>";
							else
								return target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName();
						case "penis":
						case "dick":
						case "cock":
							if(target.getLowestZLayerCoverableArea(CoverableArea.PENIS)==null)
								return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_penis</i>";
							else
								return target.getLowestZLayerCoverableArea(CoverableArea.PENIS).getName();
						case "nipple":
						case "nipples":
							if(target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES)==null)
								return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_nipples</i>";
							else
								return target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES).getName();
						case "ass":
						case "asshole":
							if(target.getLowestZLayerCoverableArea(CoverableArea.ANUS)==null)
								return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_asshole</i>";
							else
								return target.getLowestZLayerCoverableArea(CoverableArea.ANUS).getName();
						default:
							return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";

					}
				} else {
					return "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
				}
			}
		});
		
		// Styles:
		
		Parser.addParserFunction(new ParserFunction(new String[] { "bold", "b" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(arguments!=null)
					return "<b>"+arguments+"</b>";
				else
					return "<b>...</b>";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "italic", "italics", "i" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
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
				
				Parser.addParserFunction(new ParserFunction(ArrayUtil.convertToArray(commandNames)) {
					@Override
					public String parse(String command, String arguments, GameCharacter target) {
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
				
				Parser.addParserFunction(new ParserFunction(ArrayUtil.convertToArray(commandNames)) {
					@Override
					public String parse(String command, String arguments, GameCharacter target) {
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
				
				Parser.addParserFunction(new ParserFunction(ArrayUtil.convertToArray(commandNames)) {
					@Override
					public String parse(String command, String arguments, GameCharacter target) {
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
				
				Parser.addParserFunction(new ParserFunction(ArrayUtil.convertToArray(commandNames)) {
					@Override
					public String parse(String command, String arguments, GameCharacter target) {
						if(arguments!=null)
							return UtilText.applyGlow(arguments, c);
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
		
		Parser.addParserFunction(new ParserFunction(new String[] { "armRows" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				switch(target.getArmRows()){
					case 1: return "pair of";
					case 2: return "two pairs of";
					default: return "three pairs of";
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "hand" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getArmType().getHandsNameSingular(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "hand+", "handD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getArmType().getHandsDescriptor(target), target.getArmType().getHandsNameSingular(target));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "hands" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getArmType().getHandsNamePlural(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "hands+", "handsD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getArmType().getHandsDescriptor(target), target.getArmType().getHandsNamePlural(target));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "finger" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getArmType().getFingersNameSingular(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "finger+", "fingerD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getArmType().getFingersDescriptor(target), target.getArmType().getFingersNameSingular(target));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "fingers" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getArmType().getFingersNamePlural(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "fingers+", "fingersD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getArmType().getFingersDescriptor(target), target.getArmType().getFingersNamePlural(target));
			}
		});

		// Ass:
		Parser.addParserFunction(new ParserFunction(new String[] { "assSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getAssSize().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "assCapacity", "assholeCapacity" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return Capacity.getCapacityFromValue(target.getAssStretchedCapacity()).getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "assElasticity", "assholeElasticity" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getAssElasticity().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "assWetness", "assholeWetness" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getAssWetness().getDescriptor();
			}
		});
		
		// Hips: TODO rough

		Parser.addParserFunction(new ParserFunction(new String[] { "hipSkin", "hipsSkin" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return getSkinName(target, target.getSkinType());
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "hipSkin+", "hipsSkin+" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return getSkinNameWithDescriptor(target, target.getSkinType());
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] { "hip", "hips" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return "hips";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "hip+", "hipD", "hips+", "hipsD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getHipSize().getDescriptor(), "hips");
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "hipSize", "hipsSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getHipSize().getDescriptor();
			}
		});

		// Breasts:

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"breastSize", "breastsSize", "titSize", "titsSize", "boobSize", "boobsSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getBreastSize().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"breastShape", "breastsShape", "titShape", "titsShape", "boobShape", "boobsShape" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getBreastSize().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "nippleSize", "nipplesSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getNippleSize().getName();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "areolaSize", "areolaeSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getAreolaeSize().getName();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"cupSize", "cups", "breastCups", "breastsCups", "titCups", "titsCups", "boobCups", "boobsCups" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getBreastSize().getCupSizeName();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"breastCapacity", "breastsCapacity", "titCapacity", "titsCapacity", "boobCapacity", "boobsCapacity" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return Capacity.getCapacityFromValue(target.getNippleStretchedCapacity()).getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"breastElasticity", "breastsElasticity", "titElasticity", "titsElasticity", "boobElasticity", "boobsElasticity" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getNippleElasticity().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "breastRows", "nippleRows" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.getBreastRows() == 1) {
					return "pair of";
				} else {
					return Util.intToString(target.getBreastRows()) + " pairs of";
				}
			}
		});
		
//		Parser.addParserFunction(new ParserFunction(new String[] { "milk", "milkName" }) {
//			@Override
//			public String parse(String command, String arguments, GameCharacter target) {
//				return target.getMilkName();
//			}
//		});

		Parser.addParserFunction(new ParserFunction(new String[] { "lactation" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getBreastMilkStorage().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "milkRegen", "milkRegeneration" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getBreastLactationRegeneration().getName();
			}
		});
		
		// Eyes:
		
		Parser.addParserFunction(new ParserFunction(new String[] { "eyePairs", "eyesPairs", "eyeRows", "eyesRows" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getEyeDeterminer();
			}
		});
		
		// Face:

		Parser.addParserFunction(new ParserFunction(new String[] { "tongueLength", "tongueSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getTongueLength().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "nose" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getNoseNameSingular();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "noses" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getNoseNamePlural();
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] { "lipSize", "lipsSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getLipSize().getName();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "lip" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getLipsNameSingular();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "lip+", "lipD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getLipsDescriptor(), target.getLipsNameSingular());
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "lips" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getLipsNamePlural();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "lips+", "lipsD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getLipsDescriptor(), target.getLipsNamePlural());
			}
		});

		// Hair:

		Parser.addParserFunction(new ParserFunction(new String[] { "hairLength" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getHairLength().getDescriptor();
			}
		});
		
		// Horns:

		Parser.addParserFunction(new ParserFunction(new String[] { "hornSize", "hornsSize", "hornLength", "hornsLength" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return HornLength.getHornLengthFromInt(target.getHornLength()).getDescriptor();
			}
		});
		
		// Leg:

		Parser.addParserFunction(new ParserFunction(new String[] { "thighs" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return "thighs";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "thighs+", "thighsD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return "thighs";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "foot" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getLegType().getFeetNameSingular(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "foot+", "footD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getLegType().getFeetDescriptor(target), target.getLegType().getFeetNameSingular(target));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "feet" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getLegType().getFeetNamePlural(target);
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] { "feet+", "feetD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getLegType().getFeetDescriptor(target), target.getLegType().getFeetNamePlural(target));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "toes" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getLegType().getToesNamePlural(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "toes+", "toesD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getLegType().getToesDescriptor(target), target.getLegType().getToesNamePlural(target));
			}
		});
		
		// Penis:
		
		Parser.addParserFunction(new ParserFunction(new String[] { "urethra", "penisUrethra", "cockUrethra", "urethraPenis", "urethraCock" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return "urethra";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"penisUrethra+", "cockUrethra+", "urethraPenis+", "urethraCock+", "penisUrethraD", "cockUrethraD", "urethraPenisD", "urethraCockD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getPenisUrethraDescriptor(), "urethra");
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"cumAmount", "cumProduction", "jizzAmount", "jizzProduction", "cumStorage" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getPenisCumStorage().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] {  "cumMl", "cumMeasurement" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return String.valueOf(target.getPenisRawCumStorageValue());
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] {  "ballsCount", "ballCount", "testiclesCount" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return Util.intToString(target.getPenisNumberOfTesticles());
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "ballSize", "ballsSize", "testicleSize", "testiclesSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getTesticleSize().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "penisHead", "cockHead", "dickHead", "cockTip" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getBody().getPenis().getPenisHeadName(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"penisHead+", "penisHeadD", "cockHead+", "cockHeadD", "dickHead+", "dickHeadD", "cockTip+", "cockTipD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getBody().getPenis().getPenisHeadDescriptor(target), target.getBody().getPenis().getPenisHeadName(target));
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] { "penisSize", "cockSize", "dickSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getPenisSize().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "penisGirth", "cockGirth", "dickGrith" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getPenisGirth().getName();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "penisCm" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return String.valueOf(Util.conversionInchesToCentimetres(target.getPenisRawSizeValue()));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "penisInches" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return String.valueOf(target.getPenisRawSizeValue());
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "penisInches" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return String.valueOf(target.getPenisRawSizeValue());
			}
		});
		
		// Second penis:

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"secondPenisHead", "secondCockHead", "secondDickHead", "penis2Head", "cock2Head", "dick2Head" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getBody().getSecondPenis().getPenisHeadName(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"secondPenisHead+", "secondCockHead+", "secondDickHead+", "penis2Head+", "cock2Head+", "dick2Head+",
			"secondPenisHeadD", "secondCockHeadD", "secondDickHeadD", "penis2HeadD", "cock2HeadD", "dick2HeadD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getBody().getSecondPenis().getPenisHeadDescriptor(target), target.getBody().getSecondPenis().getPenisHeadName(target));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"secondPenisSize", "secondCockSize", "secondDickSize", "penis2Size", "cock2Size", "dick2Size" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getSecondPenisSize().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "secondPenisCm", "penis2Cm" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return String.valueOf(Util.conversionInchesToCentimetres(target.getSecondPenisRawSizeValue()));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "secondPenisInches", "penis2Inches" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return String.valueOf(target.getSecondPenisRawSizeValue());
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "secondUrethra" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return "urethra";
			}
		});
		
		// Tail:

		Parser.addParserFunction(new ParserFunction(new String[] { "tailCount", "tailsCount" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getTailDeterminer();
			}
		});
		
		// Vagina:

		Parser.addParserFunction(new ParserFunction(new String[] { "vaginaUrethra", "vaginalUrethra", "urethraVagina", "urethraVaginal" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return "urethra";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"vaginaUrethra+", "vaginalUrethra+", "urethraVagina+", "urethraVaginal+",
			"vaginaUrethraD", "vaginalUrethraD","urethraVaginaD","urethraVaginalD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getVaginaUrethraDescriptor(), "urethra");
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "vaginaCapacity", "pussyCapacity", "cuntCapacity" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return Capacity.getCapacityFromValue(target.getVaginaStretchedCapacity()).getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"vaginaElasticity", "pussyElasticity", "cuntElasticity" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getVaginaElasticity().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "vaginaWetness", "pussyWetness", "cuntWetness" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getVaginaWetness().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "labiaSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getVaginaLabiaSize().getName();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "labia" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return "labia";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "labia+", "labiaD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getVaginaLabiaSize().getName()+" labia";
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "clitSize", "clitorisSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getVaginaClitorisSize().getDescriptor();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "clitSizeInches", "clitorisSizeInches" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return String.valueOf(target.getVaginaRawClitorisSizeValue());
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "clitGirth", "clitorisGirth" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getClitorisGirth().getName();
			}
		});
				
		// Wings:
		
		Parser.addParserFunction(new ParserFunction(new String[] { "wingSize", "wingsSize" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getWingSize().getName();
			}
		});
		
		// Eyes:
		
		Parser.addParserFunction(new ParserFunction(new String[] { "irisShape", "irisesShape" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getIrisShape().getName();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "irisFullDescription", "irisesFullDescription" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(target.getEyeType().getBodyCoveringType(target))
						.getFullDescription(target, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "irisColour", "irisColor", "irisesColour", "irisesColor" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(target.getEyeType().getBodyCoveringType(target))
					.getColourDescriptor(target, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"irisColourPrimary", "irisColorPrimary", "irisesColourPrimary", "irisesColorPrimary", 
			"irisPrimaryColour", "irisPrimaryColor", "irisesPrimaryColour", "irisesPrimaryColor"
		 }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(target.getEyeType().getBodyCoveringType(target))
					.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"irisColourSecondary", "irisColorSecondary", "irisesColourSecondary", "irisesColorSecondary",
			"irisSecondaryColour", "irisSecondaryColor", "irisesSecondaryColour", "irisesSecondaryColor"
		 }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(target.getEyeType().getBodyCoveringType(target))
					.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "pupilShape", "pupilsShape" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getPupilShape().getName();
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "pupilFullDescription", "pupilsFullDescription"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(BodyCoveringType.EYE_PUPILS)
					.getFullDescription(target, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "pupilColour", "pupilColor", "pupilsColour", "pupilsColor"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(BodyCoveringType.EYE_PUPILS)
					.getColourDescriptor(target, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"pupilColourPrimary", "pupilColorPrimary", "pupilsColourPrimary", "pupilsColorPrimary", 
			"pupilPrimaryColour", "pupilPrimaryColor", "pupilsPrimaryColour", "pupilsPrimaryColor"
		}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(BodyCoveringType.EYE_PUPILS)
					.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"pupilColourSecondary", "pupilColorSecondary", "pupilsColourSecondary", "pupilsColorSecondary",
			"pupilSecondaryColour", "pupilSecondaryColor", "pupilsSecondaryColour", "pupilsSecondaryColor"
		}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(BodyCoveringType.EYE_PUPILS)
					.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "scleraFullDescription", "scleraeFullDescription"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(BodyCoveringType.EYE_SCLERA)
					.getFullDescription(target, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "scleraColour", "scleraColor", "scleraeColour", "scleraeColor"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(BodyCoveringType.EYE_SCLERA)
					.getColourDescriptor(target, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		Parser.addParserFunction(new ParserFunction(new String[] { 
			"scleraColourPrimary",  "scleraColorPrimary", "scleraeColourPrimary", "scleraeColorPrimary",
			"scleraPrimaryColour", "scleraPrimaryColor", "scleraePrimaryColour", "scleraePrimaryColor"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(BodyCoveringType.EYE_SCLERA)
					.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { 
			"scleraColourSecondary", "scleraColorSecondary", "scleraeColourSecondary", "scleraeColorSecondary",
			"scleraSecondaryColour", "scleraSecondaryColor", "scleraeSecondaryColour", "scleraeSecondaryColor"}) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getCovering(BodyCoveringType.EYE_SCLERA)
					.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		// Tail:

		Parser.addParserFunction(new ParserFunction(new String[] { "tailHead", "tailTip" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return target.getTailType().getTailTipName(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(new String[] { "tailHead+", "tailHeadD", "tailTip+", "tailTipD" }) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(target.getTailType().getTailTipDescriptor(target), target.getTailType().getTailTipName(target));
			}
		});
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
		
		Parser.addParserFunction(new ParserFunction(getModifiedTags(tags, tagsPlural, "Race")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				try {
					return getBodyPartFromType(target, bodyPart).getType().getRace().getName();
				} catch(Exception ex) {
					return "null_body_part";
				}
			}
		});

		Parser.addParserFunction(new ParserFunction(getModifiedTags(tags, tagsPlural, "Skin")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return getSkinName(target, getBodyPartFromType(target, bodyPart).getType());
			}
		});

		Parser.addParserFunction(new ParserFunction(getModifiedTags(tags, tagsPlural, "Skin+", "SkinD")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return getSkinNameWithDescriptor(target, getBodyPartFromType(target, bodyPart).getType());
			}
		});	
		
		Parser.addParserFunction(new ParserFunction(getModifiedTags(tags, tagsPlural, "FullDescription")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target))==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target)).getFullDescription(target, true);
					}
				}
				return target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target)).getFullDescription(target, false);
			}
		});	

		Parser.addParserFunction(new ParserFunction(
			getModifiedTags(tags, tagsPlural, "FullDescriptionColour", "FullDescriptionColor", "FullDescriptionColoured", "FullDescriptionColored")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target))==null) {
					return "";
				}
				return target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target)).getFullDescription(target, true);
			}
		});

		Parser.addParserFunction(new ParserFunction( getModifiedTags(tags, tagsPlural, "Colour", "Color")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target))==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target)).getColourDescriptor(target, true, parseCapitalise);
					}
				}
				return target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target)).getColourDescriptor(target, false, parseCapitalise);
			}
		});

		Parser.addParserFunction(new ParserFunction( getModifiedTags(tags, tagsPlural, "ColourPrimary", "ColorPrimary", "PrimaryColour", "PrimaryColor")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target))==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target)).getPrimaryColourDescriptor(true);
					}
				}
				return target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target)).getPrimaryColourDescriptor(false);
			}
		});

		Parser.addParserFunction(new ParserFunction( getModifiedTags(tags, tagsPlural, "ColourSecondary", "ColorSecondary", "SecondaryColour", "SecondaryColor")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target))==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target)).getSecondaryColourDescriptor(true);
					}
				}
				return target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target)).getSecondaryColourDescriptor(false);
			}
		});

		Parser.addParserFunction(new ParserFunction( getModifiedTags(tags, tagsPlural, "ColourHex", "ColorHex")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				if(target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target))==null) {
					return "";
				}
				return target.getCovering(getBodyPartFromType(target, bodyPart).getType().getBodyCoveringType(target)).getPrimaryColour().toWebHexString();
			}
		});

		Parser.addParserFunction(new ParserFunction(tags) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return getBodyPartFromType(target, bodyPart).getNameSingular(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(tagsPlural) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return getBodyPartFromType(target, bodyPart).getNamePlural(target);
			}
		});

		Parser.addParserFunction(new ParserFunction(getModifiedTags(tags, null, "+", "D")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(getBodyPartFromType(target, bodyPart).getDescriptor(target), getBodyPartFromType(target, bodyPart).getNameSingular(target));
			}
		});

		Parser.addParserFunction(new ParserFunction(getModifiedTags(null, tagsPlural, "+", "D")) {
			@Override
			public String parse(String command, String arguments, GameCharacter target) {
				return applyDescriptor(getBodyPartFromType(target, bodyPart).getDescriptor(target), getBodyPartFromType(target, bodyPart).getNamePlural(target));
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
	
	
	private static BodyPartInterface getBodyPartFromType(GameCharacter character, BodyPartType type) {
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
	
	private static String getSubspeciesName(GameCharacter character) {
		if(character == null)
			return "";
		if (character.isFeminine()) {
			return character.getSubspecies().getSingularFemaleName();
		} else {
			return character.getSubspecies().getSingularMaleName();
		}
	}
	
	private static String getSubspeciesNamePlural(GameCharacter character) {
		if(character==null)
			return "";
		if (character.isFeminine()) {
			return character.getSubspecies().getPluralFemaleName();
		} else {
			return character.getSubspecies().getPluralMaleName();
		}
	}
	
	private static String getSkinName(GameCharacter character, BodyPartTypeInterface bodyPart) {
		if(bodyPart.getBodyCoveringType(character)==null)
			return "";
		
		return bodyPart.getBodyCoveringType(character).getName(character);
	}
	
	private static String getSkinNameWithDescriptor(GameCharacter character, BodyPartTypeInterface bodyPart) {
		if(bodyPart.getBodyCoveringType(character)==null)
			return "";
		
		return applyDescriptor(character.getCovering(bodyPart.getBodyCoveringType(character)).getModifier().getName(), bodyPart.getBodyCoveringType(character).getName(character));
	}

	private static String randomMoan(GameCharacter character) {
		if(character.isFeminine()) {
			return UtilText.returnStringAtRandom("moan", "squeal", "gasp", "cry");
		} else {
			return UtilText.returnStringAtRandom("groan", "grunt", "gasp");
		}
	}

	private static String randomProtest(GameCharacter character) {
		if(character.isFeminine()) {
			return UtilText.returnStringAtRandom("sob", "scream", "cry");
		} else {
			return UtilText.returnStringAtRandom("shout", "cry");
		}
	}
} 