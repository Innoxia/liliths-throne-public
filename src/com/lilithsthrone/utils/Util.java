package com.lilithsthrone.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.regex.Pattern;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * This is just a big mess of utility classes that I wanted to throw somewhere.
 * 
 * @since 0.1.0
 * @version 0.2.6
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
		
		double r = (first.getRed() + second.getRed())/2,
				g = (first.getGreen() + second.getGreen())/2,
					b = (first.getBlue() + second.getBlue())/2;
		
		return newColour(r*255, g*255, b*255);
	}
	
	public static String toWebHexString(Color colour) {
		return colour.toString().substring(2, 8);
	}
	
	public static Color newColour(double r, double g, double b) {
		return Color.color(r / 255, g / 255, b / 255);
	}

	public static Color newColour(int hex) {
		return newColour((hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF));
	}
	
	public static String colourReplacement(String gradientReplacementID, Colour colour, Colour colourSecondary, Colour colourTertiary, String inputString) {
		String s = inputString;
		
		for (int i = 0; i <= 14; i++) {
			s = s.replaceAll("linearGradient" + i, gradientReplacementID + colour.toString() + (colourSecondary!=null?colourSecondary.toString():"") + (colourTertiary!=null?colourTertiary.toString():"") + "linearGradient" + i);
			s = s.replaceAll("innoGrad" + i, gradientReplacementID + colour.toString() + (colourSecondary!=null?colourSecondary.toString():"") + (colourTertiary!=null?colourTertiary.toString():"") + "innoGrad" + i);
		}
		
		if(colour!=null) {
			s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
			s = s.replaceAll("#ff5555", colour.getShades()[1]);
			s = s.replaceAll("#ff8080", colour.getShades()[2]);
			s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
			s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
		}
		
		if(colourSecondary!=null) {
			s = s.replaceAll("#ff7f2a", colourSecondary.getShades()[0]);
			s = s.replaceAll("#ff9955", colourSecondary.getShades()[1]);
			s = s.replaceAll("#ffb380", colourSecondary.getShades()[2]);
			s = s.replaceAll("#ffccaa", colourSecondary.getShades()[3]);
			s = s.replaceAll("#ffe6d5", colourSecondary.getShades()[4]);
		}
		
		if(colourTertiary!=null) {
			s = s.replaceAll("#ffd42a", colourTertiary.getShades()[0]);
			s = s.replaceAll("#ffdd55", colourTertiary.getShades()[1]);
			s = s.replaceAll("#ffe680", colourTertiary.getShades()[2]);
			s = s.replaceAll("#ffeeaa", colourTertiary.getShades()[3]);
			s = s.replaceAll("#fff6d5", colourTertiary.getShades()[4]);
		}
		
		return s;
	}
	
	public static String colourReplacementPattern(String gradientReplacementID, Colour colour, Colour colourSecondary, Colour colourTertiary, String inputString) {
		String s = inputString;

		for (int i = 0; i <= 14; i++) {
			s = s.replaceAll("linearGradient" + i, gradientReplacementID + colour.toString() + (colourSecondary!=null?colourSecondary.toString():"") + (colourTertiary!=null?colourTertiary.toString():"") + "linearGradient" + i);
			s = s.replaceAll("innoGrad" + i, gradientReplacementID + colour.toString() + (colourSecondary!=null?colourSecondary.toString():"") + (colourTertiary!=null?colourTertiary.toString():"") + "innoGrad" + i);
		}
		
		if(colour!=null) {
			s = s.replaceAll("#f4d7d7", colour.getShades()[0]);
			s = s.replaceAll("#e9afaf", colour.getShades()[1]);
			s = s.replaceAll("#de8787", colour.getShades()[2]);
			s = s.replaceAll("#d35f5f", colour.getShades()[3]);
			s = s.replaceAll("#c83737", colour.getShades()[4]);
		}
		
		if(colourSecondary!=null) {
			s = s.replaceAll("#f4e3d7", colourSecondary.getShades()[0]);
			s = s.replaceAll("#e9c6af", colourSecondary.getShades()[1]);
			s = s.replaceAll("#deaa87", colourSecondary.getShades()[2]);
			s = s.replaceAll("#d38d5f", colourSecondary.getShades()[3]);
			s = s.replaceAll("#c87137", colourSecondary.getShades()[4]);
		}
		
		if(colourTertiary!=null) {
			s = s.replaceAll("#f4eed7", colourTertiary.getShades()[0]);
			s = s.replaceAll("#e9ddaf", colourTertiary.getShades()[1]);
			s = s.replaceAll("#decd87", colourTertiary.getShades()[2]);
			s = s.replaceAll("#d3bc5f", colourTertiary.getShades()[3]);
			s = s.replaceAll("#c8ab37", colourTertiary.getShades()[4]);
		}

		return s;
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
			map.put(v.getKey(), v.getValue());
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
	
	public static String getFileTime(File file) throws IOException {
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy - hh:mm");
	    return dateFormat.format(file.lastModified());
	}
	
	@SafeVarargs
	public static <U> ArrayList<U> newArrayListOfValues(U... values) {
		return new ArrayList<>(Arrays.asList(values));
	}
	
	@SafeVarargs
	public static <U> ArrayList<U> mergeLists(List<U>... lists) {
		ArrayList<U> mergedList = new ArrayList<>();
		
		for(List<U> list : lists) {
			for(U value : list) {
				mergedList.add(value);
			}
		}
		
		return mergedList;
	}
	
	@SafeVarargs
	public static <U> HashSet<U> newHashSetOfValues(U... values) {
		return new HashSet<>(Arrays.asList(values));
	}
	
	@SafeVarargs
	public static <U, T> Map<U, List<T>> mergeMaps(Map<U, List<T>>... maps) {
		Map<U, List<T>> mergedMap = new HashMap<>();
		
		for(Map<U, List<T>> map : maps) {
			for(Entry<U, List<T>> entry : map.entrySet()) {
				mergedMap.putIfAbsent(entry.getKey(), new ArrayList<>());
				mergedMap.get(entry.getKey()).addAll(entry.getValue());
			}
		}
		
		return mergedMap;
	}
	
	public static <T> T getRandomObjectFromWeightedMap(Map<T, Integer> map) {
		int total = 0;
		for(int i : map.values()) {
			total+=i;
		}
		
		int choice = Util.random.nextInt(total) + 1;
		
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

	public static String getDayOfMonthSuffix(int n) {
		if (n >= 11 && n <= 13) {
	    	return "th";
	    }
	    switch (n % 10) {
	    	case 1:  return "st";
	    	case 2:  return "nd";
	    	case 3:  return "rd";
	    	default: return "th";
	    }
	}
	
	public static float getRoundedFloat(float input, int significantFigures) {
		return (float) (((int)(input*Math.pow(10, significantFigures)))/Math.pow(10, significantFigures));
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

	public static String intToDate(int integer) {
		if(integer%10==1 && (integer%100<10 || integer%100>20)) {
			return integer+"st";
		} else if(integer%10==2 && (integer%100<10 || integer%100>20)) {
			return integer+"nd";
		} else if(integer%10==3 && (integer%100<10 || integer%100>20)) {
			return integer+"rd";
		} else {
			return integer+"th";
		}
	}
	
	public static String getStringOfLocalDateTime(LocalDateTime date) {
		return intToDate(date.getDayOfMonth())+" "+date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault())+", "+date.getYear();
	}
	
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
			return intToString + " a lot";
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
	
	public static String intToPosition(int integer) {
		String intToString = "";
		
		if(integer<0) {
			intToString = "minus ";
		}
		integer = Math.abs(integer);
		if (integer >= 100_000) {
			return intToString + " a lot";
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
	
	public static String intToTally(int integer) {
		StringBuilder numeralSB = new StringBuilder();
		for(int i=0; i<integer/5; i++) {
			numeralSB.append("<strike>IIII</strike> ");
		}
		for(int i=0; i<integer%5; i++) {
			numeralSB.append("I");
		}
		
		return numeralSB.toString();
	}
	
	public static String getKeyCodeCharacter(KeyCode code) {
		String name = KEY_NAMES.get(code);
		return name != null? name : code.getName();
	}

	public static int conversionCentimetresToInches(int cm) {
		// System.out.println(cm + " -> "+(int)(cm/2.54f));
		return Math.round(cm / 2.54f);
	}

	public static int conversionInchesToCentimetres(int inches) {
		return Math.round(inches * 2.54f);
	}

	public static String centimetresToMetresAndCentimetres(int cm) {
		return ((cm / 100) + ((cm % 100) != 0 ? ("." + cm % 100) + "m." : "m"));
	}

	public static String inchesToFeetAndInches(int inches) {
		return ((((inches) / 12) == 0 ? "" : (inches) / 12) + (((inches) / 12) > 0 ? "'" : "") + (((inches) % 12) == 0 ? "" : " ") + (((inches) % 12) != 0 ? ((inches) % 12) + "&quot;" : ""));
	}

	public static int conversionKilogramsToPounds(int kg) {
		return Math.round(kg * 2.20462268f);
	}

	public static String poundsToStoneAndPounds(int pounds) {
		return ((((pounds) / 14) == 0 ? "" : (pounds) / 14) + (((pounds) / 12) > 0 ? "st." : "") + (((pounds) % 14) == 0 ? "" : " ") + (((pounds) % 14) != 0 ? ((pounds) % 14) + "lb" : ""));
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
		splitSentence = sentence.split(" ");
		utilitiesStringBuilder.setLength(0);

		// 1 in "frequency" words have an insert, with a minimum of 1.
		int wordsToInsert = splitSentence.length / frequency + 1,
				offset = 0;
		for (int i = 0; i < wordsToInsert; i++) {
			offset = Math.min(i * frequency + random.nextInt(frequency), splitSentence.length - 1);
			String insert = inserts[random.nextInt(inserts.length)];

			// If wanted, ensure not inserting to the start or end of a sentence
			if (offset >= splitSentence.length -1 || isEndOfSentence(splitSentence[offset])) {
				if (middle) {
					// Skip if at end of string or sentence
					continue;
				}

				// Add a full stop to the insert, creating its own sentence
				insert += ".";
			}

			int len = splitSentence[offset].length();
			// Remove duplicate commas if selected position ends with one and insert has one
			if (insert.trim().charAt(0) == ',' && splitSentence[offset].charAt(len -1) == ',') {
				splitSentence[offset] = splitSentence[offset].substring(0, len-1);
			}

			// Append the insert to this word:
			splitSentence[offset] = splitSentence[offset] + insert;

		}
		for (String word : splitSentence)
			utilitiesStringBuilder.append(word + " ");
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

	private static String[] sexSounds = new String[] { " ~Aah!~", " ~Mmm!~" };
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
	 * "How ~Hic!~ far is it ~Hic!~ to the town ~Hic!~ hall?"<br/>
	 *
	 * @param sentence
	 *            sentence to apply sexy modifications
	 * @param frequency
	 *            of drunk sounds (i.e. 4 would be 1 in 4 words are drunk)
	 * @return
	 *            modified sentence
	 */
	public static String addDrunkSlur(String sentence, int frequency) {
		return insertIntoSentences(sentence, frequency, drunkSounds, false)
			.replaceAll("Hi ", "Heeey ")
			.replaceAll("yes", "yesh")
			.replaceAll("is", "ish")
			.replaceAll("So", "Sho")
			.replaceAll("so", "sho");
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
		}
		return utilitiesStringBuilder.toString();
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

	public static String stringsToStringChoice(List<String> list) {
		return Util.toStringList(list, Util::capitaliseSentence, "or");
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
	
	public static String tattooInventorySlotsToStringList(List<InventorySlot> inventorySlots) {
		return Util.toStringList(inventorySlots, InventorySlot::getTattooSlotName, "and");
	}

	public static String displacementTypesToStringList(List<DisplacementType> displacedList) {
		return Util.toStringList(displacedList, DisplacementType::getDescriptionPast, "and");
	}

	public static <Any> Any randomItemFrom(List<Any> list) {
		return list.get(Util.random.nextInt(list.size()));
	}

	public static <Any> Any randomItemFrom(Any[] array) {
		return array[Util.random.nextInt(array.length)];
	}
	
	public static int randomItemFrom(int[] array) {
		return array[Util.random.nextInt(array.length)];
	}
}
