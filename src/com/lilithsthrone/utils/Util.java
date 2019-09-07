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

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.main.Main;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * This is just a big mess of utility classes that I wanted to throw somewhere.
 * 
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
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
		int total = 0;
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

	public static String capitaliseSentence(String sentence) {
		if(sentence==null || sentence.isEmpty()) {
			return sentence;
		}
		return Character.toUpperCase(sentence.charAt(0)) + sentence.substring(1);
	}

	public static boolean isVowel(char c) {
		return "AEIOUaeiou".indexOf(c) != -1;
	}

	private static String[] splitSentence;

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
		splitSentence = sentence.split(" ");
		utilitiesStringBuilder.setLength(0);

		// 1 in "frequency" words are stutters, with a minimum of 1.
		int wordsToStutter = splitSentence.length / frequency + 1;

		int offset = 0;
		for (int i = 0; i < wordsToStutter; i++) {
			offset = random.nextInt(frequency);
			offset = (i * frequency + offset) >= splitSentence.length ? splitSentence.length - 1 : (i * frequency + offset);

			// In case of an accidental comma position?
			if (splitSentence[offset].charAt(0) != ',')
				splitSentence[offset] = splitSentence[offset].charAt(0) + "-" + splitSentence[offset];
			else
				splitSentence[offset] = "," + splitSentence[offset].charAt(1) + "-" + splitSentence[offset].substring(1, splitSentence[offset].length() + 1);

			for (int j = 0; j < frequency && ((i * frequency + j) < splitSentence.length); j++)
				utilitiesStringBuilder.append(splitSentence[i * frequency + j] + " ");
		}

		utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);
		return utilitiesStringBuilder.toString();
	}

	private static Pattern endOfSentence = Pattern.compile("[,.!?]");
	/**
	 * Determine whether a given string contains sentence-ending punctuation.
	 * @param text text to check whether
	 * @return boolean whether the text contains a period, exclamation or question mark
	 */
	private static boolean isEndOfSentence(String text) {
		if(text.isEmpty()) {
			return false;
		}
		return endOfSentence.matcher(text.substring(text.length()-1)).matches();
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
		utilitiesStringBuilder.setLength(0);
		
		String [] splitConditional = sentence.split("#IF\\((.*?)\\)|#ELSEIF\\((.*?)\\)"); // Do not replace text inside conditional parsing statements
		String modifiedSentence = sentence;
		if(splitConditional.length>1) {
			for(String s : splitConditional) {
				modifiedSentence = sentence.replace(s, insertIntoSentences(s, frequency, inserts, middle));
			}
		}
		if(!modifiedSentence.contains(" ")) {
			return modifiedSentence;
		}
		
		splitSentence = modifiedSentence.split(" ");
		
		// 1 in "frequency" words have an insert, with a minimum of 1.
		int wordsToInsert = splitSentence.length / frequency + 1;
		int offset = 0;
		
		for (int i = 0; i < wordsToInsert; i++) {
			offset = Math.min(i * frequency + random.nextInt(frequency), splitSentence.length - 1);
			String insert = inserts[random.nextInt(inserts.length)];

			// If wanted, ensure not inserting to the start or end of a sentence
			if (offset >= splitSentence.length -1 || isEndOfSentence(splitSentence[offset])) {
				if (middle) {
					// Skip if at end of string or sentence
					continue;
				}

//				// Add a full stop to the insert, creating its own sentence
//				insert += ".";
			}

			int len = splitSentence[offset].length();
			// Remove duplicate commas if selected position ends with one and insert has one
			if (insert.trim().charAt(0) == ',' && splitSentence[offset].charAt(len -1) == ',') {
				splitSentence[offset] = splitSentence[offset].substring(0, len-1);
			}

			// Append the insert to this word:
			splitSentence[offset] = splitSentence[offset] + insert;
		}
		for (String word : splitSentence) {
			utilitiesStringBuilder.append(word + " ");
		}
		utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);

		return utilitiesStringBuilder.toString();
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

		// 1/3 chance of having a bimbo sentence ending:
		if(!sentence.endsWith("~") && !sentence.endsWith("-")) {
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

	private static String[] sexSounds = new String[] { " ~Aah!~", " ~Mmm!~", "~Ooh!~" };
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
		String [] split = sentence.split("\\[(.*?)\\]");
		for(String s : split) {
			String [] splitConditional = s.split("#IF\\((.*?)\\)|#ELSEIF\\((.*?)\\)"); // Do not replace text inside conditional parsing statements
			for(String s2 : splitConditional) {
				String sReplace = s2
						.replaceAll("s", "<i>th</i>")
						.replaceAll("z", "<i>th</i>")
						.replaceAll("S", "<i>Th</i>")
						.replaceAll("Z", "<i>Th</i>");
					
					sentence = sentence.replace(s2, sReplace);
			}
		}
		
		return sentence;
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

	public static String subspeciesToStringList(Collection<Subspecies> subspecies, boolean capitalise) {
		return Util.toStringList(subspecies,
				(Subspecies o) -> 
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

	public static String colourSetToStringList(Set<Colour> colourSet) {
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
	
	public static String getClosestStringMatch(String input, Collection<String> choices) {
		if (choices.contains(input)) {
			return input;
		}
		int distance = Integer.MAX_VALUE;
		String closestString = input;
		for(String choice : choices) {
			int newDistance = getLevenshteinDistance(input, choice);
			if(newDistance < distance) {
				closestString = choice;
				distance = newDistance;
			}
		}
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
}
