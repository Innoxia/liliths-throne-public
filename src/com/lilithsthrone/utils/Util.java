package com.lilithsthrone.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * This is just a big mess of utility classes that I wanted to throw somewhere.
 * 
 * @since 0.1.0
 * @version 0.4.1
 * @author Innoxia, CognitiveMist
 */
public class Util {
	
	public static Random random = new Random();

	private static StringBuilder utilitiesStringBuilder = new StringBuilder();
	
	private static Map<KeyCode, String> KEY_NAMES = new LinkedHashMap<KeyCode, String>() {
		private static final long serialVersionUID = 1L;
	{
		put(KeyCode.ADD, "+");
		put(KeyCode.ALT, "Alt");
		put(KeyCode.AMPERSAND, "&");
		put(KeyCode.ASTERISK, "*");
		put(KeyCode.BACK_QUOTE, "\"");
		put(KeyCode.BACK_SLASH, "\\");
		put(KeyCode.BACK_SPACE, "Back space");
		put(KeyCode.BRACELEFT, "{");
		put(KeyCode.BRACERIGHT, "}");
		put(KeyCode.CAPS, "Caps");
		put(KeyCode.CLOSE_BRACKET, "]");
		put(KeyCode.COLON, ":");
		put(KeyCode.COMMA, ",");
		put(KeyCode.CONTROL, "Ctrl");
		put(KeyCode.DELETE, "Delete");
		put(KeyCode.DIVIDE, "/");
		put(KeyCode.DOLLAR, "$");
		put(KeyCode.DOWN, "Down");
		put(KeyCode.END, "End");
		put(KeyCode.ENTER, "Enter");
		put(KeyCode.EQUALS, "=");
		put(KeyCode.ESCAPE, "Esc");
		put(KeyCode.EURO_SIGN, "&euro"); // €
		put(KeyCode.EXCLAMATION_MARK, "!");
		put(KeyCode.GREATER, ">");
		put(KeyCode.KP_DOWN, "Down");
		put(KeyCode.KP_LEFT, "Left");
		put(KeyCode.KP_RIGHT, "Right");
		put(KeyCode.KP_UP, "Up");
		put(KeyCode.LEFT, "Left");
		put(KeyCode.LEFT_PARENTHESIS, "(");
		put(KeyCode.LESS, "<");
		put(KeyCode.MINUS, "-");
		put(KeyCode.NUMPAD0, "0");
		put(KeyCode.NUMPAD1, "1");
		put(KeyCode.NUMPAD2, "2");
		put(KeyCode.NUMPAD3, "3");
		put(KeyCode.NUMPAD4, "4");
		put(KeyCode.NUMPAD5, "5");
		put(KeyCode.NUMPAD6, "6");
		put(KeyCode.NUMPAD7, "7");
		put(KeyCode.NUMPAD8, "9");
		put(KeyCode.NUMPAD9, "9");
		put(KeyCode.OPEN_BRACKET, "[");
		put(KeyCode.PAGE_DOWN, "Pg Dn");
		put(KeyCode.PAGE_UP, "Pg Up");
		put(KeyCode.PERIOD, ".");
		put(KeyCode.PLUS, "+");
		put(KeyCode.POUND, "&pound"); // £
		put(KeyCode.POWER, "^");
		put(KeyCode.QUOTE, "\"");
		put(KeyCode.RIGHT, "Right");
		put(KeyCode.RIGHT_PARENTHESIS, ")");
		put(KeyCode.SEMICOLON, ";");
		put(KeyCode.SHIFT, "Sft");
		put(KeyCode.SLASH, "/");
		put(KeyCode.SPACE, "Space");
		put(KeyCode.SUBTRACT, "-");
		put(KeyCode.TAB, "Tab");
	}};

	// What madness is this
	public static String inputStreamToString(InputStream is) {
		if (is == null)
			return "";
		try (java.util.Scanner s = new java.util.Scanner(is)) {
			return s.useDelimiter("\\A").hasNext() ? s.next() : "";
		}
	}
	
	public static Color midpointColor(Color first, Color second) {
		
		double r = (first.getRed() + second.getRed())/2;
		double g = (first.getGreen() + second.getGreen())/2;
		double b = (first.getBlue() + second.getBlue())/2;
//		System.out.println(r+","+g+","+b);
		return Color.color(r, g, b);
	}
	
	public static String toWebHexString(Color colour) {
		String c = colour.toString().substring(2, 8);
//		System.out.println(c);
		return "#"+c;
	}

	public static Color newColour(String colourString) {
		int hex = Integer.valueOf(colourString.substring(1), 16);
		return newColour((hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF));
//		return Color.color(
//				Integer.valueOf(colourString.substring(1, 3), 16) / 255,
//				Integer.valueOf(colourString.substring(3, 5), 16) / 255,
//				Integer.valueOf(colourString.substring(5, 7), 16) / 255);
	}
	
	public static Color newColour(double r, double g, double b) {
		return Color.color(r / 255, g / 255, b / 255);
	}

	public static Color newColour(int hex) {
		return newColour((hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF));
	}
	
	/**
	 * Takes an input, and a maximum value, and returns LT's universal "dropoff" formula to it.
	 * @param input
	 * @param maxValue
	 * @return
	 */
	public static float getModifiedDropoffValue(float input, float maxValue) {
		if(Math.abs(input)>Math.abs(maxValue)) {
			input = Math.signum(input) * maxValue;
		}
//		float value = Math.abs(input)/Math.abs(maxValue);
//		//y = 0.75 * cos((x*(pi/2))-(pi/2))
//		return ((int)((Math.signum(input) * maxValue * 0.75f * Math.cos((value * (Math.PI/2)) - (Math.PI/2)))*100))/100f;
		
		
//		sin(x*pi/2)/2
		if(input < maxValue/2) {
			return input;
			
		} else {
			float excess = Math.abs(input) - Math.abs(maxValue/2);
			float value = (excess/Math.abs(maxValue))*2;
			float multiplier = (float) (Math.sin(value * (Math.PI/2))/2f);
//			System.out.println(input+", "+value +", "+ multiplier);
			return Math.round((maxValue/2 + (multiplier * (maxValue/2)))*100)/100f;
		}
	}

	/**
	 * @param containingFolderId To be in the format: <b>"/statusEffects"</b>
	 * @return A map of Files with the author as the key, mapped to a map of ids to files (id is based on file name and folder path).
	 */
	public static Map<String, Map<String, File>> getExternalModFilesById(String containingFolderId) {
		return getExternalModFilesById(containingFolderId, null, null);
	}
	
	/**
	 * @param containingFolderId To be in the format: <b>"/statusEffects"</b>
	 * @param filterFolderName If a non-null String is passed in, only files within folders with that String as their name will be added to the returned map.
	 * @param filterPathName If a non-null String is passed in, only files with that String as their name will be added to the returned map.
	 * @return A map of Files with the author as the key, mapped to a map of ids to files (id is based on file name and folder path).
	 */
	public static Map<String, Map<String, File>> getExternalModFilesById(String containingFolderId, String filterFolderName, String filterPathName) {
		File dir = new File("res/mods");
		Map<String, Map<String, File>> returnMap = new HashMap<>();
		
		if(dir.exists() && dir.isDirectory()) {
			File[] directoryListing = dir.listFiles();
			if(directoryListing != null) {
				for(File directory : directoryListing) {
					String modAuthorName = directory.getName();
					returnMap.putIfAbsent(modAuthorName, new HashMap<>());
					File modAuthorDirectory = new File(directory.getAbsolutePath()+containingFolderId);
					
					populateMapFiles(modAuthorName, directory.getName()+"_", modAuthorDirectory, returnMap, filterFolderName, filterPathName);
				}
			}
		}
		
		return returnMap;
	}

	/**
	 * @param containingFolderId To be in the format: <b>"res/statusEffects"</b>
	 * @return A map of Files with the author as the key, mapped to a map of ids to files (id is based on file name and folder path).
	 */
	public static Map<String, Map<String, File>> getExternalFilesById(String containingFolderId) {
		return getExternalFilesById(containingFolderId, null, null);
	}
	
	/**
	 * @param containingFolderId To be in the format: <b>"res/statusEffects"</b>
	 * @param filterFolderName If a non-null String is passed in, only files within folders with that String as their name will be added to the returned map.
	 * @param filterPathName If a non-null String is passed in, only files with that String as their name will be added to the returned map.
	 * @return A map of Files with the author as the key, mapped to a map of ids to files (id is based on file name and folder path).
	 */
	public static Map<String, Map<String, File>> getExternalFilesById(String containingFolderId, String filterFolderName, String filterPathName) {
		File dir = new File(containingFolderId);
		Map<String, Map<String, File>> returnMap = new HashMap<>();
		
		if(dir.exists() && dir.isDirectory()) {
			File[] authorDirectoriesListing = dir.listFiles();
			if(authorDirectoriesListing != null) {
				for(File authorDirectory : authorDirectoriesListing) {
					if(authorDirectory.isDirectory()){
						String authorName = authorDirectory.getName();
						returnMap.putIfAbsent(authorName, new HashMap<>());
						
						populateMapFiles(authorName, authorDirectory.getName()+"_", authorDirectory, returnMap, filterFolderName, filterPathName);
					}
				}
			}
		}
		
		return returnMap;
	}
	
	private static Map<String, Map<String, File>> populateMapFiles(String modAuthorName, String idPrefix, File directory, Map<String, Map<String, File>> returnMap, String filterFolderName, String filterPathName) {
		if(filterFolderName==null || filterFolderName.equalsIgnoreCase(directory.getName())) {
			File[] innerDirectoryListing = directory.listFiles((path, filename) -> filename.toLowerCase().endsWith(".xml"));
			
			if(innerDirectoryListing != null) {
				for(File innerChild : innerDirectoryListing) {
					if(filterPathName==null || filterPathName.equalsIgnoreCase(innerChild.getName().split("\\.")[0])) {
						try {
							String id = (idPrefix!=null?idPrefix:"")+innerChild.getName().split("\\.")[0];
							returnMap.get(modAuthorName).put(id, innerChild);
						} catch(Exception ex) {
							System.err.println("Loading external mod files failed at Util.getExternalModFilesById()");
							System.err.println("File path: "+innerChild.getAbsolutePath());
							ex.printStackTrace();
						}
					}
				}
			}
		}
		
		File[] additionalDirectories =  directory.listFiles();

		if(additionalDirectories != null) {
			for(File f : additionalDirectories) {
				if(f.isDirectory()) {
					populateMapFiles(modAuthorName, (idPrefix!=null?idPrefix:"")+f.getName()+"_", f, returnMap, filterFolderName, filterPathName);
				}
			}
		}
		
		return returnMap;
	}
	
	public static String getXmlRootElementName(File XMLFile) {
		try {
			Document doc = Main.getDocBuilder().parse(XMLFile);

			// Cast magic:
			doc.getDocumentElement().normalize();
			
			Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>
			
			return coreElement.getTagName();
			
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
			return "";
		}
	}
	
	public static class Value<T, S> {
		private T key;
		private S value;
		
		public Value(T key, S value) {
			this.key = key;
			this.value = value;
		}

		public T getKey() {
			return key;
		}

		public S getValue() {
			return value;
		}
	}

	@SafeVarargs
	public static <T, S> LinkedHashMap<T, S> newHashMapOfValues(Value<T, S>... values) {
		LinkedHashMap<T, S> map = new LinkedHashMap<>();

		for (Value<T, S> v : values) {
			if(v!=null) {
				map.put(v.getKey(), v.getValue());
			}
		}
		
		return map;
	}


	public String keyCodeToShortString(KeyCode keyCode) {
		switch (keyCode) {
		case OPEN_BRACKET:
			return "[";
		case CLOSE_BRACKET:
			return "]";
		case UP:
			return "Up";
		case DOWN:
			return "Down";
		case LEFT:
			return "Left";
		case RIGHT:
			return "Right";
		default:
			return keyCode.toString();
		}
	}
	
	public static void openLinkInDefaultBrowser(String url) {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("xdg-open " + url);
		} catch (IOException e0) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
				e0.printStackTrace();
			}
		}
	}

	public static String getFileTime(File file) {
		try {
			Instant fileTime = Files.getLastModifiedTime(file.toPath()).toInstant();
			return Units.dateTime(fileTime);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
	}
	
	/**
	 * @param values The values to add to the new list.
	 * @return A list of provided values, with nulls stripped.
	 */
	@SafeVarargs
	public static <U> ArrayList<U> newArrayListOfValues(U... values) {
		ArrayList<U> list = new ArrayList<>(Arrays.asList(values));
		list.removeIf(e -> e==null);
		return list;
	}
	
	@SafeVarargs
	/**
	 * @param lists The lists to merge.
	 * @return A new ArrayList which contains all the elements from both lists.
	 */
	public static <U> ArrayList<U> mergeLists(List<U>... lists) {
		ArrayList<U> mergedList = new ArrayList<>();
		
		for(List<U> list : lists) {
			if(list!=null) {
				for(U value : list) {
					mergedList.add(value);
				}
			}
		}
		
		return mergedList;
	}
	
	@SafeVarargs
	public static <U> HashSet<U> newHashSetOfValues(U... values) {
		return new HashSet<>(Arrays.asList(values));
	}
	
	@SafeVarargs
	/**
	 * @param maps The maps to draw entries from.
	 * @return A new map containing all of the entries from the provided 'maps'. Nulls are stripped, and 'maps' are unaltered.
	 */
	public static <U, T> Map<U, List<T>> mergeMaps(Map<U, List<T>>... maps) {
		Map<U, List<T>> mergedMap = new HashMap<>();
		
		for(Map<U, List<T>> map : maps) {
			if(map!=null) {
				for(Entry<U, List<T>> entry : map.entrySet()) {
					mergedMap.putIfAbsent(entry.getKey(), new ArrayList<>());
					mergedMap.get(entry.getKey()).addAll(entry.getValue());
				}
			}
		}
		
		return mergedMap;
	}
	
	public static <T> T getHighestProbabilityEntryFromWeightedMap(Map<T, Integer> map) {
		T top = null;
		int high = 0;
		for(Entry<T, Integer> entry : map.entrySet()) {
			if(entry.getValue()>high) {
				high = entry.getValue();
				top = entry.getKey();
			}
		}
		return top;
	}
	
	public static <T> T getRandomObjectFromWeightedMap(Map<T, Integer> map) {
		return getRandomObjectFromWeightedMap(map, Util.random);
	}
	
	public static <T> T getRandomObjectFromWeightedMap(Map<T, Integer> map, Random rnd) {
		int total = 0;
		for(int i : map.values()) {
			total+=i;
		}
		
		if(total==0) {
			return null;
		}
		
		int choice = rnd.nextInt(total) + 1;
		
		total = 0;
		for(Entry<T, Integer> entry : map.entrySet()) {
			total+=entry.getValue();
			if(choice<=total) {
				return entry.getKey();
			}
		}

		return null;
	}
	
	public static <T> T getRandomObjectFromWeightedFloatMap(Map<T, Float> map) {
		float total = 0;
		for(float f : map.values()) {
			total+=f;
		}
		
		float choice = (float) (Math.random()*total);
		
		total = 0;
		for(Entry<T, Float> entry : map.entrySet()) {
			total+=entry.getValue();
			if(choice<=total) {
				return entry.getKey();
			}
		}

		return null;
	}
	
	private static String[] numbersLessThanTwenty = {
			"zero",
			"one",
			"two",
			"three",
			"four",
			"five",
			"six",
			"seven",
			"eight",
			"nine",
			"ten",
			"eleven",
			"twelve",
			"thirteen",
			"fourteen",
			"fifteen",
			"sixteen",
			"seventeen",
			"eighteen",
			"nineteen"
	};
	private static String[] positionsLessThanTwenty = {
			"zero",
			"first",
			"second",
			"third",
			"fourth",
			"fifth",
			"sixth",
			"seventh",
			"eighth",
			"ninth",
			"tenth",
			"eleventh",
			"twelfth",
			"thirteenth",
			"fourteenth",
			"fifteenth",
			"sixteenth",
			"seventeenth",
			"eighteenth",
			"nineteenth"
	};
	private static String[] tensGreaterThanNineteen = {
			"",
			"",
			"twenty",
			"thirty",
			"forty",
			"fifty",
			"sixty",
			"seventy",
			"eighty",
			"ninety"
	};
	
	/**
	 * Only works for values -99,999 to 99,999.
	 * @param integer
	 * @return
	 */
	public static String intToString(int integer) {
		String intToString = "";
		
		if(integer<0) {
			intToString = "minus ";
		}
		integer = Math.abs(integer);
		if (integer >= 100_000) {
			return String.valueOf(integer); // Too big
		}
		
		
		if(integer>=1000) {
			if((integer/1000)<20) {
				intToString+=numbersLessThanTwenty[(integer/1000)]+" thousand";
			} else {
				intToString+=tensGreaterThanNineteen[integer/10000] + (((integer/1000)%10!=0)?"-"+numbersLessThanTwenty[(integer/1000)%10]:"")+" thousand";
			}
		}
		
		if(integer>=100) {
			if(integer>=1000 && integer%1000 != 0) {
				intToString+=", ";
			}
			integer = integer % 1000;
			if (intToString.isEmpty() || integer>=100) {
				intToString += numbersLessThanTwenty[integer/100]+" hundred";
			}
			if(integer%100!=0) {
				intToString+=" and ";
				integer = integer % 100;
			}
		}
		
		if(integer%100<20) {
			if (integer%100 == 0) {
				if (intToString.isEmpty()) {
					return "zero";
				}
			} else {
				intToString+=numbersLessThanTwenty[integer%100];
			}
		} else {
			intToString+=tensGreaterThanNineteen[(integer%100)/10] + ((integer%10!=0)?"-"+numbersLessThanTwenty[integer%10]:"");
		}
		
		return intToString;
	}
	
	private static String[] primarySequence = {
			"primary",
			"secondary",
			"tertiary",
			"quaternary",
			"quinary",
			"senary",
			"septenary",
			"octonary",
			"nonary",
			"denary"
	};
	
	public static String intToPrimarySequence(int integer) {
		if(integer>0 && integer<=primarySequence.length) {
			return primarySequence[integer-1];
		}
		return intToString(integer);
	}
	

	public static String intToDate(int integer) {
		int remainderHundred = integer%100;
		if(remainderHundred<=10 || remainderHundred>20) {
			if(integer%10==1) {
				return integer+"st";
			} else if(integer%10==2) {
				return integer+"nd";
			} else if(integer%10==3) {
				return integer+"rd";
			}
		}
		return integer+"th";
	}
	
	/**
	 * @param integer Input number to convert.
	 * @return 'once', 'twice', or 'integer times'
	 */
	public static String intToCount(int integer) {
		if(integer==1) {
			return "once";
		} else if(integer==2) {
			return "twice";
		}
		
		return intToString(integer)+" times";
	}

	/**
	 * @param integer Input number to convert.
	 * @return 'first', 'second', etc.
	 */
	public static String intToPosition(int integer) {
		String intToString = "";
		
		if(integer<0) {
			intToString = "minus ";
		}
		integer = Math.abs(integer);
		if (integer >= 100_000) {
			return String.valueOf(integer); // Too big
		}
		
		
		if(integer>=1000) {
			if((integer/1000)<20) {
				intToString+=numbersLessThanTwenty[(integer/1000)]+" thousand";
			} else {
				intToString+=tensGreaterThanNineteen[integer/10000] + (((integer/1000)%10!=0)?"-"+numbersLessThanTwenty[(integer/1000)%10]:"")+" thousand";
			}
		}
		
		if(integer>=100) {
			if(integer>=1000 && integer%1000 != 0) {
				intToString+=", ";
			}
			integer = integer % 1000;
			if (intToString.isEmpty() || integer>=100) {
				intToString += numbersLessThanTwenty[integer/100]+" hundred";
			}
			if(integer%100!=0) {
				intToString+=" and ";
				integer = integer % 100;
			}
		}
		
		if(integer%100<20) {
			if (integer%100 == 0) {
				if (intToString.isEmpty()) {
					return "zero";
				}
			} else {
				intToString+=positionsLessThanTwenty[integer%100];
			}
		} else {
			intToString+=tensGreaterThanNineteen[(integer%100)/10] + ((integer%10!=0)?"-"+positionsLessThanTwenty[integer%10]:"");
		}
		
		return intToString;
	}
	
	private final static TreeMap<Integer, String> numeralMap = new TreeMap<Integer, String>();
	static {
        numeralMap.put(1000, "M");
        numeralMap.put(900, "CM");
        numeralMap.put(500, "D");
        numeralMap.put(400, "CD");
        numeralMap.put(100, "C");
        numeralMap.put(90, "XC");
        numeralMap.put(50, "L");
        numeralMap.put(40, "XL");
        numeralMap.put(10, "X");
        numeralMap.put(9, "IX");
        numeralMap.put(5, "V");
        numeralMap.put(4, "IV");
        numeralMap.put(1, "I");
    }
	
	public static String intToNumerals(int integer) {
		if(integer<=0) {
			return "0";
		}
		int l =  numeralMap.floorKey(integer);
        if (integer == l) {
            return numeralMap.get(integer);
        }
        return numeralMap.get(l) + intToNumerals(integer-l);
	}
	
	public static String intToTally(int integer, int max) {
		StringBuilder numeralSB = new StringBuilder();
		int limit = Math.min(integer, max);
		for(int i=0; i<limit/5; i++) {
			numeralSB.append("<strike>IIII</strike> ");
		}
		for(int i=0; i<limit%5; i++) {
			numeralSB.append("I");
		}
		if(limit<integer) {
			numeralSB.append("... (Total: "+integer+")");
		}
		
		return numeralSB.toString();
	}
	
	public static String getKeyCodeCharacter(KeyCode code) {
		String name = KEY_NAMES.get(code);
		return name != null? name : code.getName();
	}

	/**
	 * @return A capitalised version of the sentence. This method ignores spaces and html formatting, so it should be safe to use on formatted inputs.
	 */
	public static String capitaliseSentence(String sentence) {
		if(sentence==null || sentence.isEmpty()) {
			return sentence;
		}
		int openingCurly = 0;
		int closingCurly = 0;
		int openingAngular = 0;
		int closingAngular = 0;
		int openingSquare = 0;
		int closingSquare = 0;
		for(int i = 0; i<sentence.length(); i++) {
			if(sentence.charAt(i)=='(') {
				openingCurly++;
			} else if(sentence.charAt(i)=='<') {
				openingAngular++;
			} else if(sentence.charAt(i)=='[') {
				openingSquare++;
			}
			
			if(openingCurly==closingCurly && openingAngular==closingAngular && openingSquare==closingSquare && sentence.charAt(i)!=' ') {
				return (i>0?sentence.substring(0, i):"") + Character.toUpperCase(sentence.charAt(i)) + sentence.substring(i+1);
			}
			
			if(sentence.charAt(i)==')') {
				closingCurly++;
			} else if(sentence.charAt(i)=='>') {
				closingAngular++;
			} else if(sentence.charAt(i)==']') {
				closingSquare++;
			}
		}
		return Character.toUpperCase(sentence.charAt(0)) + sentence.substring(1);
	}

	public static boolean isVowel(char c) {
		return "AEIOUaeiou".indexOf(c) != -1;
	}

	/**
	 * Turns a normal sentence into a stuttering sentence. Example:
	 * "How far is it to the town hall?" "H-How far is it to the town h-hall?"
	 * "How far i-is it to the t-town hall?"
	 * 
	 * @param sentence
	 *            sentence to apply stutters
	 * @param frequency
	 *            of stutter words (i.e. 4 would be 1 in 4 words are stutters)
	 * @return
	 *            modified sentence
	 */
	public static String addStutter(String sentence, int frequency) {
		StringBuilder modifiedSentence = new StringBuilder();
		int openingCurly = 0;
		int closingCurly = 0;
		int openingAngular = 0;
		int closingAngular = 0;
		int openingSquare = 0;
		int closingSquare = 0;
		float chance = 1f/frequency;
		for(int i = sentence.length()-1; i>=0; i--) {
			if(sentence.charAt(i)=='(') {
				openingCurly++;
			} else if(sentence.charAt(i)==')') {
				closingCurly++;
			} else if(sentence.charAt(i)=='<') {
				openingAngular++;
			} else if(sentence.charAt(i)=='>') {
				closingAngular++;
			} else if(sentence.charAt(i)=='[') {
				openingSquare++;
			} else if(sentence.charAt(i)==']') {
				closingSquare++;
			}
			
			if(sentence.charAt(i)==' '
					&& Character.isLetter(sentence.charAt(i+1))
					&& openingCurly==closingCurly
					&& openingAngular==closingAngular
					&& openingSquare==closingSquare) {
				if(Math.random()<chance) {
					modifiedSentence.append("-");
					modifiedSentence.append(sentence.charAt(i+1));
				}
			}
			modifiedSentence.append(sentence.charAt(i));
		}
		
		modifiedSentence.reverse();
		return modifiedSentence.toString();
	}

	private static Pattern endOfSentence = Pattern.compile("[,.!?]");
	
	private static boolean isEndOfSentence(char c) {
		return endOfSentence.matcher(String.valueOf(c)).matches();
	}

	/**
	 * Inserts words randomly into a sentence.<br/>
	 *
	 * @param sentence
	 *            sentence to insert words into
	 * @param frequency
	 *            how often words are inserted. 1/frequency is the probability of inserting a word
	 * @param inserts
	 *            list of strings to insert into. These are appended to the end of words, so ensure
	 *            any whitespace wanted is put before the insert. A space separates the next word
	 * @param middle
	 *            boolean, whether to avoid inserting at the start/end of a sentence
	 * @return
	 *            modified sentence
	 */
	private static String insertIntoSentences(String sentence, int frequency, String[] inserts, boolean middle) {
		StringBuilder modifiedSentence = new StringBuilder();
		int openingCurly = 0;
		int closingCurly = 0;
		int openingSquare = 0;
		int closingSquare = 0;
		float chance = 1f/frequency;
		for(int i = sentence.length()-1; i>=0; i--) {
			if(sentence.charAt(i)=='(') {
				openingCurly++;
			} else if(sentence.charAt(i)==')') {
				closingCurly++;
			} else if(sentence.charAt(i)=='[') {
				openingSquare++;
			} else if(sentence.charAt(i)==']') {
				closingSquare++;
			}
			if(i!=sentence.length()-1
					&& sentence.charAt(i+1)==' '
					&& !isEndOfSentence(sentence.charAt(i))
					&& (i==0 || !middle || !isEndOfSentence(sentence.charAt(i-1)))
					&& openingCurly==closingCurly
					&& openingSquare==closingSquare) {
				if(Math.random()<chance) {
					String word = Util.randomItemFrom(inserts);
					char[] charArray = word.toCharArray();
					for(int cIndex=charArray.length-1; cIndex>=0; cIndex--) {
						modifiedSentence.append(charArray[cIndex]);
					}
				}
			}
			modifiedSentence.append(sentence.charAt(i));
		}
		
		modifiedSentence.reverse();
		return modifiedSentence.toString();
	}

	private static String insertIntoSentences(String sentence, int frequency, String[] inserts) {
		return insertIntoSentences(sentence, frequency, inserts, true);
	}

	private static String[] bimboWords = new String[] { ", like,", ", like,", ", like,", ", um,", ", uh,", ", ah," };
	/**
	 * Turns a normal sentence into the kind of thing a Bimbo would come out with.
	 * Can be safely used in conjunction with addStutter.
	 * If using addStutter after using addBimbo, bimbo words can also become stuttered.<br/>
	 * Example: "How far is it to the town hall?"<br/>
	 * "How, like, far is it to the town, uh, hall and stuff?"<br/>
	 * "How far is, like, it to the, um, town hall and stuff?"<br/>
	 * "Like, How far is it to the, like, town hall?"<br/>
	 * Used in conjunction with addStutter(): "L-Like, How far is it t-to the, like, town hall?"
	 *
	 * @param sentence
	 *            sentence to apply bimbo modifications
	 * @param frequency
	 *            of bimbo interjections (i.e. 4 would be 1 in 4 words have a
	 *            bimbo interjection)
	 * @return
	 *            modified sentence
	 */
	public static String addBimbo(String sentence, int frequency) {
		sentence = insertIntoSentences(sentence, frequency, bimboWords);
		utilitiesStringBuilder.setLength(0);
		utilitiesStringBuilder.append(sentence);
		
		// 1/3 chance of having a bimbo sentence ending: TODO improve so it can be added anywhere
		if(!sentence.endsWith("~") && !sentence.endsWith("-") && !sentence.endsWith("#ENDIF")) {
			switch (random.nextInt(6)) {
				case 0:
					char end = utilitiesStringBuilder.charAt(utilitiesStringBuilder.length() - 1);
					utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);
					utilitiesStringBuilder.append(" and stuff");
					utilitiesStringBuilder.append(end);
					break;
				case 1:
					utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);
					utilitiesStringBuilder.append(", y'know?");
					break;
				default:
					break;
			}
		}

		return utilitiesStringBuilder.toString();
	}
	
	private static String[] broWords = new String[] { ", like,", ", like, dude,", ", like, bro,", ", like,", ", um,", ", uh,", ", ah," };
	public static String addBro(String sentence, int frequency) {
		sentence = insertIntoSentences(sentence, frequency, broWords);
		utilitiesStringBuilder.setLength(0);
		utilitiesStringBuilder.append(sentence);
		
		// 1/3 chance of having a bimbo sentence ending: TODO improve so it can be added anywhere
		if(!sentence.endsWith("~") && !sentence.endsWith("-") && !sentence.endsWith("#ENDIF")) {
			switch (random.nextInt(6)) {
				case 0:
					char end = utilitiesStringBuilder.charAt(utilitiesStringBuilder.length() - 1);
					utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);
					utilitiesStringBuilder.append(" and stuff");
					utilitiesStringBuilder.append(end);
					break;
				case 1:
					utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);
					utilitiesStringBuilder.append(UtilText.returnStringAtRandom(", y'know, bro?", ", y'know, dude?"));
					break;
				default:
					break;
			}
		}
		
		
		
		return utilitiesStringBuilder.toString();
	}

	private static String[] muteSexSounds = new String[] { "... ~Ooh!~", "... ~Mmm!~", "... ~Aah!~" };
	/**
	 * @param sentence The sentence to mute.
	 * @param sexMoans If the character should moan/pant due to being in sex.
	 * @return A series of "..." if no sexMoans, or "... ~Ooh!~", "... ~Mmm!~", "... ~Aah!~" if sexMoans
	 */
	public static String replaceWithMute(String sentence, boolean sexMoans) {
		int length = Math.max(1, sentence.split(" ").length/3);
		
		StringBuilder muteSB = new StringBuilder();
		for(int i=0; i<length; i++) {
			if(sexMoans) {
				muteSB.append(muteSexSounds[random.nextInt(muteSexSounds.length)]+" ");
				
			} else {
				muteSB.append("... ");
			}
		}
		muteSB.deleteCharAt(muteSB.length()-1);
		return muteSB.toString();
	}
		
	private static String[] muffledSounds = new String[] { " ~Mrph~", " ~Mmm~", " ~Mrmm~" };
	/**
	 * Turns a normal sentence into a muffled sentence.<br/>
	 * Example:<br/>
	 * "How far is it to the town hall?"<br/>
	 * "How ~Mrph~ far is it ~Mmm~ to the town ~Mrph~ hall?"<br/>
	 *
	 * @param sentence
	 *            sentence to apply muffles
	 * @param frequency
	 *            of muffled words (i.e. 4 would be 1 in 4 words are muffled)
	 * @return
	 *            modified sentence
	 */
	public static String addMuffle(String sentence, int frequency) {
		return insertIntoSentences(sentence, frequency, muffledSounds);
	}
	
	public static String replaceWithMuffle(String sentence, int wordToMuffleRatio) {
		int muffles = sentence.split(" ").length/wordToMuffleRatio;
		StringBuilder muffleSB = new StringBuilder();
		for(int i=0; i<muffles; i++) {
			muffleSB.append(muffledSounds[random.nextInt(muffledSounds.length)]);
		}
		muffleSB.delete(0, 1); // Remove space at start
		return muffleSB.toString();
	}

	private static String[] sexSounds = new String[] { " ~Aah!~", " ~Mmm!~", " ~Ooh!~" };
	/**
	 * Turns a normal sentence into a sexy sentence.<br/>
	 * Example:<br/>
	 * "How far is it to the town hall?"<br/>
	 * "How ~Aah!~ far is it ~Mmm!~ to the town ~Aah!~ hall?"<br/>
	 *
	 * @param sentence
	 *            sentence to apply sexy modifications
	 * @param frequency
	 *            of sex sounds (i.e. 4 would be 1 in 4 words are sexy)
	 * @return
	 *            modified sentence
	 */
	public static String addSexSounds(String sentence, int frequency) {
		return insertIntoSentences(sentence, frequency, sexSounds);
	}

	private static String[] drunkSounds = new String[] { " ~Hic!~" };
	/**
	 * Turns a normal sentence into a drunk one.<br/>
	 * Example:<br/>
	 * "How far is it to the town hall?"<br/>
	 * "How ~Hic!~ far ish it ~Hic!~ to the town ~Hic!~ hall?"<br/>
	 *
	 * @param sentence to apply drunk modifications to.
	 * @param frequency of drunk sounds (i.e. 4 would be 1 in 4 words are drunk)
	 * @return modified sentence
	 */
	public static String addDrunkSlur(String sentence, int frequency) {
		sentence = insertIntoSentences(sentence, frequency, drunkSounds, false);
		
		String [] split = sentence.split("\\[(.*?)\\]");
		for(String s : split) {
			String [] splitConditional = s.split("#IF\\((.*?)\\)|#ELSEIF\\((.*?)\\)"); // Do not replace text inside conditional parsing statements
			for(String s2 : splitConditional) {
				String sReplace = s2
						.replaceAll("Hi ", "Heeey ")
						.replaceAll("yes", "yesh")
						.replaceAll("Is", "Ish")
						.replaceAll("is", "ish")
						.replaceAll("It's", "It'sh")
						.replaceAll("it's", "it'sh")
						.replaceAll("So", "Sho")
						.replaceAll("so", "sho");
					
					sentence = sentence.replace(s2, sReplace);
			}
		}
		
		return sentence;
		
//		return insertIntoSentences(sentence, frequency, drunkSounds, false)
//			.replaceAll("Hi ", "Heeey ")
//			.replaceAll("yes", "yesh")
//			.replaceAll("is", "ish")
//			.replaceAll("So", "Sho")
//			.replaceAll("so", "sho");
	}

	private static Map<String, String> slovenlySpeechReplacementMap = new LinkedHashMap<>();
	static {
		slovenlySpeechReplacementMap.put("What are", "Wot's");
		slovenlySpeechReplacementMap.put("what are", "wot's");
		
		slovenlySpeechReplacementMap.put("Are", "Is");
		slovenlySpeechReplacementMap.put("are", "is");

		slovenlySpeechReplacementMap.put("You're", "You's");
		slovenlySpeechReplacementMap.put("you're", "you's");
		
		slovenlySpeechReplacementMap.put("Your", "Yer");
		slovenlySpeechReplacementMap.put("your", "yer");
		
		slovenlySpeechReplacementMap.put("You ", "Ya "); // End with a space as sentences which are simply 'You.' are awkward to read when converted to 'Ya.'
		slovenlySpeechReplacementMap.put("you", "ya");
		
		slovenlySpeechReplacementMap.put("Yourself", "Yerself");
		slovenlySpeechReplacementMap.put("yourself", "yerself");

		slovenlySpeechReplacementMap.put("You'd", "You's");
		slovenlySpeechReplacementMap.put("you'd", "you's");

		slovenlySpeechReplacementMap.put("Her", "'Er");
		slovenlySpeechReplacementMap.put("her", "'er");

		slovenlySpeechReplacementMap.put("His", "'Is");
		slovenlySpeechReplacementMap.put("his", "'is");
		
		slovenlySpeechReplacementMap.put("Going to", "Gonna");
		slovenlySpeechReplacementMap.put("going to", "gonna");
		
		slovenlySpeechReplacementMap.put("To", "Ta");
		slovenlySpeechReplacementMap.put("to", "ta");
		slovenlySpeechReplacementMap.put("Into", "Inta");
		slovenlySpeechReplacementMap.put("into", "inta");

		slovenlySpeechReplacementMap.put("The", "Da");
		slovenlySpeechReplacementMap.put("the", "da");

		slovenlySpeechReplacementMap.put("Them", "Dem");
		slovenlySpeechReplacementMap.put("them", "dem");

		slovenlySpeechReplacementMap.put("They", "Dey");
		slovenlySpeechReplacementMap.put("they", "dey");

		slovenlySpeechReplacementMap.put("These", "Dese");
		slovenlySpeechReplacementMap.put("these", "dese");
		
		slovenlySpeechReplacementMap.put("And", "'An");
		slovenlySpeechReplacementMap.put("and", "an'");
		
		slovenlySpeechReplacementMap.put("Of", "O'");
		slovenlySpeechReplacementMap.put("of", "o'");
		slovenlySpeechReplacementMap.put("Who", "'O");
		slovenlySpeechReplacementMap.put("who", "'o");
		slovenlySpeechReplacementMap.put("Whoever", "'Oever");
		slovenlySpeechReplacementMap.put("whoever", "'oever");
		
		slovenlySpeechReplacementMap.put("Was", "Were");
		slovenlySpeechReplacementMap.put("was", "were");
		
		slovenlySpeechReplacementMap.put("What", "Wot");
		slovenlySpeechReplacementMap.put("what", "wot");
		
		slovenlySpeechReplacementMap.put("Isn't", "Ain't");
		slovenlySpeechReplacementMap.put("isn't", "ain't");
		slovenlySpeechReplacementMap.put("Aren't", "Ain't");
		slovenlySpeechReplacementMap.put("aren't", "ain't");
		
		slovenlySpeechReplacementMap.put("This one", "This 'un");
		slovenlySpeechReplacementMap.put("this one", "this 'un");
		slovenlySpeechReplacementMap.put("That one", "That 'un");
		slovenlySpeechReplacementMap.put("that one", "that 'un");
		
		slovenlySpeechReplacementMap.put("Before", "'Afore");
		slovenlySpeechReplacementMap.put("before", "'afore");
		
		slovenlySpeechReplacementMap.put("Give me", "Gimme");
		slovenlySpeechReplacementMap.put("give me", "gimme");
		
		slovenlySpeechReplacementMap.put("We're", "We's");
		slovenlySpeechReplacementMap.put("we're", "we's");
		
		slovenlySpeechReplacementMap.put("So that", "So's");
		slovenlySpeechReplacementMap.put("so that", "so's");

		slovenlySpeechReplacementMap.put("Have not", "'Aven't");
		slovenlySpeechReplacementMap.put("have not", "'aven't");
		slovenlySpeechReplacementMap.put("Haven't", "'Aven't");
		slovenlySpeechReplacementMap.put("haven't", "'aven't");
		slovenlySpeechReplacementMap.put("Have", "'Ave");
		slovenlySpeechReplacementMap.put("have", "'ave");

		slovenlySpeechReplacementMap.put("Here", "'Ere");
		slovenlySpeechReplacementMap.put("here", "'ere");
		
		slovenlySpeechReplacementMap.put("My", "Me");
		slovenlySpeechReplacementMap.put("my", "me");

		slovenlySpeechReplacementMap.put("Myself", "Meself");
		slovenlySpeechReplacementMap.put("myself", "meself");
		
		slovenlySpeechReplacementMap.put("That", "Dat");
		slovenlySpeechReplacementMap.put("that", "dat");

		slovenlySpeechReplacementMap.put("Some", "Sum");
		slovenlySpeechReplacementMap.put("some", "sum");

		slovenlySpeechReplacementMap.put("This", "Dis");
		slovenlySpeechReplacementMap.put("this", "dis");
		
		slovenlySpeechReplacementMap.put("For", "Fer");
		slovenlySpeechReplacementMap.put("for", "fer");
		
		slovenlySpeechReplacementMap.put("Very", "Real");
		slovenlySpeechReplacementMap.put("very", "real");
		
		slovenlySpeechReplacementMap.put("Yes", "Yeah");
		slovenlySpeechReplacementMap.put("yes", "yeah");

		slovenlySpeechReplacementMap.put("Hurry", "'Urry");
		slovenlySpeechReplacementMap.put("hurry", "'urry");
		
		slovenlySpeechReplacementMap.put("Doesn't", "Don't");
		slovenlySpeechReplacementMap.put("doesn't", "don't");
		
		slovenlySpeechReplacementMap.put("Because", "'Cause");
		slovenlySpeechReplacementMap.put("because", "'cause");
	}
	/**
	 * Replaces words in the sentence to give the impression that the speaker is talking in a slovenly manner. The replacements are:
			<br/>Are -> Is
			<br/>You're -> You's
			<br/>Your -> Yer
			<br/>You -> Ya
			<br/>Yourself - Yerself
			<br/>You'd -> You's
			<br/>Her -> 'Er
			<br/>His -> 'Is
			<br/>To -> Ta
			<br/>Into -> inta
			<br/>The -> Da
			<br/>Them -> Dem
			<br/>These -> Dese
			<br/>And -> An'
			<br/>Of -> 'O
			<br/>Who -> 'O
			<br/>Who -> 'O
			<br/>Was -> Were
			<br/>Isn't -> ain't
			<br/>Aren't -> ain't
			<br/>This one -> This 'un
			<br/>That one -> That 'un
			<br/>Before -> 'afore
			<br/>Give me -> Gimme
			<br/>Going to -> gonna
			<br/><i>X</i>ing -> <i>X</i>in'
			<br/>We're -> We's
			<br/>So that -> so's
			<br/>Have not -> 'aven't
			<br/>Haven't -> 'aven't
			<br/>Have -> 'ave
			<br/>My -> Me
			<br/>Myself -> Meself
			<br/>That -> Dat
			<br/>Some -> Sum
			<br/>For -> Fer
			<br/>Here -> 'ere
			<br/>Very -> Real
			<br/>Yes -> Yeah
			<br/>Hurry -> 'Urry
			<br/>Doesn't -> Don't
			<br/>Because -> 'Cause
	 *
	 * @param sentence The speech to which the lisp should be applied.
	 * @return The modified sentence.
	 */
	public static String applySlovenlySpeech(String sentence) {
		//Use non-letter regex replacement ([^A-Za-z0-9]) 
		String modifiedSentence = sentence;
		for(Entry<String, String> entry : slovenlySpeechReplacementMap.entrySet()) {
			modifiedSentence = modifiedSentence.replaceAll("([^A-Za-z0-9\\.]|^)"+entry.getKey()+"([^A-Za-z0-9\\]])", "$1"+entry.getValue()+"$2");
		}
		modifiedSentence = modifiedSentence.replaceAll("ing([^A-Za-z0-9\\]])", "in'$1");
		return modifiedSentence;
	}
	
	/**
	 * Applies a lisp to speech (a speech defect in which s is pronounced like th in thick and z is pronounced like th in this). Modified sibilants are italicised in order to assist with reading.<br/>
	 * Example:<br/>
	 * "Is there a zoo that's nearby?"<br/>
	 * "I<i>th</i> there a <i>th</i>oo that'<i>th</i> nearby?"<br/>
	 *
	 * @param sentence The speech to which the lisp should be applied.
	 * @return The modified sentence.
	 */
	public static String applyLisp(String sentence) {
		StringBuilder modifiedSentence = new StringBuilder();
		int openingCurly = 0;
		int closingCurly = 0;
		int openingAngular = 0;
		int closingAngular = 0;
		int openingSquare = 0;
		int closingSquare = 0;
		for(int i = sentence.length()-1; i>=0; i--) {
			if(sentence.charAt(i)=='(') {
				openingCurly++;
			} else if(sentence.charAt(i)==')') {
				closingCurly++;
			} else if(sentence.charAt(i)=='<') {
				openingAngular++;
			} else if(sentence.charAt(i)=='>') {
				closingAngular++;
			}else if(sentence.charAt(i)=='[') {
				openingSquare++;
			} else if(sentence.charAt(i)==']') {
				closingSquare++;
			}
			
			if(openingCurly==closingCurly && openingAngular==closingAngular && openingSquare==closingSquare) {
				if(sentence.charAt(i)=='s' || sentence.charAt(i)=='z') {
					modifiedSentence.append(">i/<ht>i<");
				} else if((sentence.charAt(i)=='S' && (i-1>=0 && sentence.charAt(i-1)!='L')) || sentence.charAt(i)=='Z') {
					modifiedSentence.append(">i/<hT>i<");
				} else {
					modifiedSentence.append(sentence.charAt(i));
				}
				
			} else {
				modifiedSentence.append(sentence.charAt(i));
			}
		}
		
		modifiedSentence.reverse();
		return modifiedSentence.toString();
	}
	
	
	/**
	 * Builds a string representing the list of items in a collection.
	 *
	 * If there is one item, that string will be returned:
	 * <code>"something"</code>.
	 * If there are two items, they are combined with the combining word:
	 * <code>"something and nothing"</code>.
	 * If there are three or more items, all will be combined with commas, except the last two will use the combining word:
	 * <code>"something, nothing and everything"</code>.
	 *
	 * @param items a {@link Collection} of items to turn into a pretty list
	 * @param stringExtractor the function used to get the strings out of the objects in the collection
	 * @param combiningWord the word used to combine the last two items
	 * @param <T> the type of the objects in the collection
	 * @return a pretty string list representing the collection
	 */
	private static <T> String toStringList(Collection<T> items, Function<T, String> stringExtractor, String combiningWord) {
		Iterator<T> itemIterator = items.iterator();
		utilitiesStringBuilder.setLength(0);
		try {
			T currentItem = itemIterator.next();
	
			utilitiesStringBuilder.append(stringExtractor.apply(currentItem));
			if (itemIterator.hasNext()) { // If more than one item, enter the loop
				currentItem = itemIterator.next();
				while (itemIterator.hasNext()) { // Use commas until we're on the last item
					utilitiesStringBuilder.append(", " + stringExtractor.apply(currentItem));
					currentItem = itemIterator.next();
				}
				utilitiesStringBuilder.append((items.size()>2?", ":" ") + combiningWord + " " + stringExtractor.apply(currentItem));
			}
		} catch(NoSuchElementException ex) {
			System.err.println("Util.toStringList() error - NoSuchElementException! (It's probably nothing to worry about...)");
			ex.printStackTrace();
		}
		return utilitiesStringBuilder.toString();
	}

	public static String subspeciesToStringList(Collection<AbstractSubspecies> subspecies, boolean capitalise) {
		return Util.toStringList(subspecies,
				(AbstractSubspecies o) -> 
				"<span style='color:"+o.getColour(null).toWebHexString()+";'>"
					+(capitalise
							?Util.capitaliseSentence(o.getNamePlural(null))
							:o.getNamePlural(null))
					+"</span>",
				"and");
	}

	public static String clothesToStringList(Collection<AbstractClothing> clothingSet, boolean capitalise) {
		return Util.toStringList(clothingSet, (AbstractClothing o) -> (capitalise?Util.capitaliseSentence(o.getClothingType().getName()):o.getClothingType().getName()), "and");
	}

	public static String setToStringListCoverableArea(Set<CoverableArea> coverableAreaSet) {
		return Util.toStringList(coverableAreaSet, (CoverableArea o) -> Util.capitaliseSentence(o.getName()), "and");
	}

	public static String stringsToStringList(List<String> list, boolean capitalise) {
		return Util.toStringList(list, (String o) -> capitalise?Util.capitaliseSentence(o):o, "and");
	}

	public static String stringsToStringChoice(List<String> list, boolean capitalise) {
		return Util.toStringList(list, (String o) -> capitalise?Util.capitaliseSentence(o):o, "or");
	}

	public static String coloursToStringList(Collection<Colour> colourSet) {
		return Util.toStringList(colourSet, Colour::getName, "and");
	}

	public static String coverableAreaListToStringList(List<CoverableArea> coverableAreaCollection) {
		return Util.toStringList(coverableAreaCollection, CoverableArea::getName, "and");
	}

	public static String inventorySlotsToStringList(List<InventorySlot> inventorySlots) {
		return Util.toStringList(inventorySlots, InventorySlot::getName, "and");
	}
	
	public static String inventorySlotsToParsedStringList(List<InventorySlot> inventorySlots, GameCharacter owner) {
		return Util.toStringList(inventorySlots, ((slot) -> slot.getNameOfAssociatedPart(owner)), "and");
	}
	
	public static String tattooInventorySlotsToStringList(List<InventorySlot> inventorySlots) {
		return Util.toStringList(inventorySlots, InventorySlot::getTattooSlotName, "and");
	}

	public static String displacementTypesToStringList(List<DisplacementType> displacedList) {
		return Util.toStringList(displacedList, DisplacementType::getDescriptionPast, "and");
	}

	public static <Any> Any randomItemFrom(List<Any> list) {
		if(list.isEmpty()) {
			return null;
		}
		return list.get(Util.random.nextInt(list.size()));
	}

	public static <Any> Any randomItemFrom(Set<Any> set) {
		List<Any> list = new ArrayList<>(set);
		return randomItemFrom(list);
	}

	public static <Any> Any randomItemFrom(Any[] array) {
		return array[Util.random.nextInt(array.length)];
	}

	public static int randomItemFrom(int[] array) {
		return array[Util.random.nextInt(array.length)];
	}

	@SafeVarargs
	public static <Any> Any randomItemFromValues(Any... values) {
		return values[Util.random.nextInt(values.length)];
	}

	/**
	 * This method will determine the closest string in {@code choices} to the given {@code input}.
	 * The Levenshtein edit distance metric is used for this calculation.
	 *
	 * @param input String for which to find the closest match.
	 * @param choices Collection of valid Strings, among which the closest match to {@code input}
	 *                   will be found.
	 * @param maxDistance The maximum distance for a match. If no match within this distance,
	 *                    return null.
	 * @return The closest match.
	 */
	public static String getClosestStringMatch(String input, Collection<String> choices, int maxDistance) {
		// If input is empty, just return the empty string. It would make no sense to guess, so hopefully the caller will handle the case correctly.
		if (input.isEmpty() || choices.contains(input)) {
			return input;
		}
		int stringMatchDistance = Integer.MAX_VALUE;
		String closestString = input;
		for(String choice : choices) {
			int newDistance = getLevenshteinDistance(input, choice);
			if(newDistance < stringMatchDistance) {
				closestString = choice;
				stringMatchDistance = newDistance;
			}
		}
		if(stringMatchDistance>maxDistance) {
			System.err.println("Warning: getClosestStringMatch() did not find a close enough match for '"+input+"'; returning null. (Closest match was '"+closestString+"' at distance: "+stringMatchDistance+")");
			return null;
		}
		if(stringMatchDistance>0) { // Only show error message if difference is more than just capitalisation differences
			System.err.println("Warning: getClosestStringMatch() did not find an exact match for '"+input+"'; returning '"+closestString+"' instead. (Distance: "+stringMatchDistance+")");
		}
		if(Main.DEBUG) {
			new IllegalArgumentException().printStackTrace(System.err);
		}
		return closestString;
	}
	
	public static String getClosestStringMatch(String input, Collection<String> choices) {
		return getClosestStringMatch(input, choices, Integer.MAX_VALUE);
	}

	private static String unordered(String input, int prefix) {
		// TODO This could be improved if, by some method, the non-prefix words were left as an
		//      unordered set, rather than rejoining them in alphabetical order, since typos can
		//      occur in the first letter, too. However, this would require
		//      com.lilithsthrone.utils.Util.getLevenshteinDistance to handle java.util.Set<E>.
		//      A harder problem is how to handle the omission or addition of an underscore, for
		//      which two words should match with one, or vice-versa.
		String p = "";
		String r = input;
		int prefixLen = 0;
		for (int i = 0; i < prefix; i++) {
			int idx = input.indexOf('_', prefixLen);
			if (idx < 0) {
				// we've ran out of words, the whole thing is prefix
				p = input;
				r = "";
				break;
			}
			prefixLen = idx+1;
			p = input.substring(0, prefixLen);
			r = input.substring(prefixLen);
			//System.out.println("len: "+prefixLen+", "+p+"|"+r);
		}
		return p + Arrays.stream(r.split("_")).sorted().collect(Collectors.joining("_"));
	}

	/**
	 * This method will determine the closest string in {@code choices} to the given {@code input}.
	 * All strings will be treated as underscore-delimited words that have no order.
	 * The Levenshtein edit distance metric is used for this calculation.
	 *
	 * @param input String for which to find the closest match.
	 * @param choices Collection of valid Strings, among which the closest match to {@code input}
	 *                   will be found.
	 * @return The closest match.
	 */
	public static String getClosestStringMatchUnordered(String input, Collection<String> choices) {
		return getClosestStringMatchUnordered(input, 0, choices);
	}

	/**
	 * This method will determine the closest string in {@code choices} to the given {@code input}.
	 * The first {@code prefix} underscore-delimited words of each string will be preserved, but
	 * all words after that will be treated as having no order.
	 * The Levenshtein edit distance metric is used for this calculation.
	 *
	 * @param input String for which to find the closest match.
	 * @param prefix Number of underscore-delimited words for which the ordering should be
	 *               preserved. If zero or less, the whole string is considered unordered. If it
	 *               is the number of words or more, the whole string is considered ordered.
	 * @param choices Collection of valid Strings, among which the closest match to {@code input}
	 *                will be found.
	 * @return The closest match.
	 */
	public static String getClosestStringMatchUnordered(String inputRaw, int prefix, Collection<String> choices) {
		// If inputRaw is empty, just return the empty string. It would make no sense to guess, so hopefully
		// the caller will handle the case correctly.
		if (inputRaw.isEmpty() || choices.contains(inputRaw)) {
			return inputRaw;
		}

		// Util.unordered expects words to be underscore-delimited. However, some misbehaving
		// mods uses spaces or hyphens instead. We'll fix that for them here, to try to get more
		// accurate matches. We assume all values in choices are well-behaved.
		String input = inputRaw.replaceAll("[ -]", "_");

		if (choices.contains(input)) {
			System.err.println("Warning: getClosestStringMatchUnordered() did not find an exact match for '"+inputRaw+"'; returning '"+input+"' instead. (Invalid word delimiter)");
			return input;
		}

		Map<String,String> unorderedChoices = choices.stream().collect(Collectors
				.toMap(s -> Util.unordered(s, prefix), Function.identity(), (a,b) -> {
					System.err.println("Warning: keeping " + a + " and discarding " + b + "!");
					return a;
				}));
		String unorderedInput = unordered(input, prefix);
		if (unorderedChoices.containsKey(unorderedInput)) {
			String unorderedMatch = unorderedChoices.get(unorderedInput);
			System.err.println("Warning: getClosestStringMatchUnordered() did not find an exact match for '"+inputRaw+"'; returning '"+unorderedMatch+"' instead. (Reordered words)");
			return unorderedMatch;
		}
		int distance = Integer.MAX_VALUE;
		String closestString = input;
		for(String unorderedChoice : unorderedChoices.keySet()) {
			int newDistance = getLevenshteinDistance(unorderedInput, unorderedChoice);
			if(newDistance < distance) {
				closestString = unorderedChoices.get(unorderedChoice);
				distance = newDistance;
			}
		}
		if(distance>0) { // Only show error message if difference is more than just capitalisation differences
			System.err.println("Warning: getClosestStringMatchUnordered() did not find an exact match for '"+inputRaw+"'; returning '"+closestString+"' instead. (Distance: "+distance+")");
		}
//		throw new IllegalArgumentException();
		return closestString;
	}
	
	public static int getLevenshteinDistance(String inputOne, String inputTwo) {
		// Don't care about case:
		inputOne = inputOne.toLowerCase();
		inputTwo = inputTwo.toLowerCase();
		
		// i == 0
		int[] costs = new int[inputTwo.length() + 1];
		
		for (int j = 0; j < costs.length; j++) {
			costs[j] = j;
		}
		for (int i = 1; i <= inputOne.length(); i++) {
			// j == 0; nw = lev(i - 1, j)
			costs[0] = i;
			int nw = i - 1;
			for (int j = 1; j <= inputTwo.length(); j++) {
				int cj = Math.min(
						1 + Math.min(costs[j], costs[j - 1]),
						inputOne.charAt(i - 1) == inputTwo.charAt(j - 1)
							? nw
							: nw + 1);
				nw = costs[j];
				costs[j] = cj;
			}
		}
		return costs[inputTwo.length()];
	}
	
	private static Map<String, List<String>> errorLogMap = new HashMap<>();
	public static void logGetNpcByIdError(String method, String id) {
		if(Main.DEBUG) { // So this doesn't flood error.log
			errorLogMap.putIfAbsent(method, new ArrayList<>());
			if(!errorLogMap.get(method).contains(id)) {
				System.err.println("Main.game.getNPCById("+id+") returning null in method: "+method);
				errorLogMap.get(method).add(id);
			}
		}
	}

	public static String getFileName(File f) {
		return f.getName().substring(0, f.getName().lastIndexOf('.'));
	}
	
	public static String getFileIdentifier(File f) {
		return f.getName().substring(0, f.getName().lastIndexOf('.')).replaceAll("'", "Q");
	}
	
	public static String getFileName(String filePath) {
		return filePath.substring(0, filePath.lastIndexOf('.'));
	}
	
	public static String getFileIdentifier(String filePath) {
		return filePath.substring(0, filePath.lastIndexOf('.')).replaceAll("'", "Q");
	}

	public static  <T extends Enum<T>> List<T> toEnumList(final Collection<Element> elements, final Class<T> enumType) {
		return elements.stream()
			.map(Element::getTextContent)
			.map(x -> {
				try { return T.valueOf(enumType, x); }
				catch (Exception e) { return null; } })
			.filter(x -> x != null)
			.collect(Collectors.toList());
	}
}
