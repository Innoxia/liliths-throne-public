package com.lilithsthrone.parse;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

public class Parser {

    public static List<ParserCommand> commandsList = new ArrayList<>();
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

	private static GameCharacter character;
	private static List<GameCharacter> specialNPCList = new ArrayList<>();
	private static boolean parseCapitalise, parseAddPronoun;
	private static String parseSyntaxNew(String target, String command, String arguments, List<GameCharacter> specialNPCList) {
		
		Parser.specialNPCList = specialNPCList;
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
}