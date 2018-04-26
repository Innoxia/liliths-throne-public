package com.lilithsthrone.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.function.Function;

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
 * @version 0.1.96
 * @author Innoxia
 */
public class Util {
	public static Random random = new Random();

	private static StringBuilder utilitiesStringBuilder = new StringBuilder();

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
		s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
		s = s.replaceAll("#ff5555", colour.getShades()[1]);
		s = s.replaceAll("#ff8080", colour.getShades()[2]);
		s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
		s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
		
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
	
	public static class ListValue<U> {
		private U value;

		public ListValue(U value) {
			this.value = value;
		}

		public U getValue() {
			return value;
		}
	}

	@SafeVarargs
	public static <U> ArrayList<U> newArrayListOfValues(ListValue<U>... values) {
		ArrayList<U> list = new ArrayList<>();

		for (ListValue<U> v : values)
			list.add(v.value);

		return list;
	}
	
	@SafeVarargs
	public static <U> HashSet<U> newHashSetOfValues(ListValue<U>... values) {
		HashSet<U> list = new HashSet<>();

		for (ListValue<U> v : values)
			list.add(v.value);

		return list;
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
	
	public static String intToString(int integer) {
		if(integer>=0 && integer<1000){
			String intToString = "";
			if(integer>=100) {
				intToString = numbersLessThanTwenty[(integer%1000)/100]+" hundred";
				if(integer%100!=0) {
					if(integer%100<20) {
						intToString+=" and "+numbersLessThanTwenty[integer%100];
					} else {
						intToString+=" and "+tensGreaterThanNineteen[(integer%100)/10] + ((integer%10!=0)?"-"+numbersLessThanTwenty[integer%10]:"");
					}
				}
			} else {
				if(integer%100<20) {
					intToString+=numbersLessThanTwenty[integer%100];
				} else {
					intToString+=tensGreaterThanNineteen[(integer%100)/10] + ((integer%10!=0)?"-"+numbersLessThanTwenty[integer%10]:"");
				}
			}
			
			return intToString;
		}
		
		return String.valueOf(integer);
	}
	
	public static String formatForHTML(String input) {
		return input.replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
	}
	
	public static String getKeyCodeCharacter(KeyCode code) {
		switch(code) {
			case ADD:
				return "+";
			case ALT:
				return "Alt";
			case AMPERSAND:
				return "&";
			case ASTERISK:
				return "*";
			case BACK_QUOTE:
				return "\"";
			case BACK_SLASH:
				return "\\";
			case BACK_SPACE:
				return "Back space";
			case BRACELEFT:
				return "{";
			case BRACERIGHT:
				return "}";
			case CAPS:
				return "Caps";
			case CLOSE_BRACKET:
				return "]";
			case COLON:
				return ":";
			case COMMA:
				return ",";
			case CONTROL:
				return "Ctrl";
			case DELETE:
				return "Delete";
			case DIVIDE:
				return "/";
			case DOLLAR:
				return "$";
			case DOWN:
				return "Down";
			case END:
				return "End";
			case ENTER:
				return "Enter";
			case EQUALS:
				return "=";
			case ESCAPE:
				return "Esc";
			case EURO_SIGN:
				return "&euro;"; // €
			case EXCLAMATION_MARK:
				return "!";
			case GREATER:
				return ">";
			case KP_DOWN:
				return "Down";
			case KP_LEFT:
				return "Left";
			case KP_RIGHT:
				return "Right";
			case KP_UP:
				return "Up";
			case LEFT:
				return "Left";
			case LEFT_PARENTHESIS:
				return "(";
			case LESS:
				return "<";
			case MINUS:
				return "-";
			case NUMPAD0:
				return "0";
			case NUMPAD1:
				return "1";
			case NUMPAD2:
				return "2";
			case NUMPAD3:
				return "3";
			case NUMPAD4:
				return "4";
			case NUMPAD5:
				return "5";
			case NUMPAD6:
				return "6";
			case NUMPAD7:
				return "7";
			case NUMPAD8:
				return "9";
			case NUMPAD9:
				return "9";
			case OPEN_BRACKET:
				return "[";
			case PAGE_DOWN:
				return "Pg Dn";
			case PAGE_UP:
				return "Pg Up";
			case PERIOD:
				return ".";
			case PLUS:
				return "+";
			case POUND:
				return "&pound;"; // £
			case POWER:
				return "^";
			case QUOTE:
				return "\"";
			case RIGHT:
				return "Right";
			case RIGHT_PARENTHESIS:
				return ")";
			case SEMICOLON:
				return ";";
			case SHIFT:
				return "Sft";
			case SLASH:
				return "/";
			case SPACE:
				return "Space";
			case SUBTRACT:
				return "-";
			case TAB:
				return "Tab";
			case UNDERSCORE:
				return "_";
			case UP:
				return "Up";
			default:
				return code.getName();
		}
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

	private static String[] bimboWords = new String[] { "like, ", "like, ", "like, ", "um, ", "uh, ", "ah, " };
	/**
	 * Turns a normal sentence into the kind of thing a Bimbo would come out with.
	 * Can be safely used in conjunction with addStutter.
	 * If using addStutter after using addBimbo, bimbo words can also become stuttered.</br>
	 * Example: "How far is it to the town hall?"</br>
	 * "How, like, far is it to the town, uh, hall and stuff?"</br>
	 * "How far is, like, it to the, um, town hall and stuff?"</br>
	 * "Like, How far is it to the, like, town hall?"</br>
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
		splitSentence = sentence.split(" ");
		utilitiesStringBuilder.setLength(0);

		// 1 in "frequency" words are bimbo interjections, with a minimum of 1.
		int wordsToBimbofy = splitSentence.length / frequency + 1;

		int offset = 0;
		for (int i = 0; i < wordsToBimbofy; i++) {
			offset = random.nextInt(frequency);
			offset = ((i * frequency + offset) >= splitSentence.length ? splitSentence.length - 1 : (i * frequency + offset));
			if (offset != 0) {
				// If previous word didn't end with punctuation:
				if (splitSentence[offset - 1].charAt(splitSentence[offset - 1].length() - 1) != '.' && splitSentence[offset - 1].charAt(splitSentence[offset - 1].length() - 1) != '!'
						&& splitSentence[offset - 1].charAt(splitSentence[offset - 1].length() - 1) != '?') {
					// Add a comma to the end of the previous word:
					if (splitSentence[offset - 1].charAt(splitSentence[offset - 1].length() - 1) != ',')
						splitSentence[offset - 1] = splitSentence[offset - 1] + ",";
					// Add the bimbo part to this word:
					splitSentence[offset] = bimboWords[random.nextInt(bimboWords.length)] + splitSentence[offset];
				} else {
					// Previous word ended with punctuation, so the bimbo word needs to be capitalised:
					splitSentence[offset] = capitaliseSentence(bimboWords[random.nextInt(bimboWords.length)]) + splitSentence[offset];
				}
			} else {
				// This is the first word in the sentence, so capitalise the bimbo part of it:
				splitSentence[offset] = capitaliseSentence(bimboWords[random.nextInt(bimboWords.length)]) + splitSentence[offset];
			}

			// for(int j=0; j<frequency && ((i*frequency
			// +j)<splitSentence.length);j++)
			// sb.append(splitSentence[i*frequency +j]+" ");
		}
		for (String word : splitSentence)
			utilitiesStringBuilder.append(word + " ");
		utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);

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

	private static String[] muffledSounds = new String[] { "~Mrph~ ", "~Mmm~ ", "~Mrmm~ " };
	/**
	 * Turns a normal sentence into a muffled sentence.</br>
	 * Example:</br>
	 * "How far is it to the town hall?"</br>
	 * "How ~Mrph~ far is it ~Mmm~ to the town ~Mrph~ hall?"</br>
	 * 
	 * @param sentence
	 *            sentence to apply muffles
	 * @param frequency
	 *            of muffled words (i.e. 4 would be 1 in 4 words are muffled)
	 * @return
	 *            modified sentence
	 */
	public static String addMuffle(String sentence, int frequency) {
		splitSentence = sentence.split(" ");
		utilitiesStringBuilder.setLength(0);

		// 1 in "frequency" words are muffled interjections, with a minimum of 1.
		int wordsToMuffle = splitSentence.length / frequency + 1;

		int offset = 0;
		for (int i = 0; i < wordsToMuffle; i++) {
			offset = random.nextInt(frequency);
			offset = ((i * frequency + offset) >= splitSentence.length ? splitSentence.length - 1 : (i * frequency + offset));
			
			// Add the muffled sound to this word:
			splitSentence[offset] = muffledSounds[random.nextInt(muffledSounds.length)] + splitSentence[offset];
			
		}
		for (String word : splitSentence)
			utilitiesStringBuilder.append(word + " ");
		utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);

		return utilitiesStringBuilder.toString();
	}
	
	private static String[] sexSounds = new String[] { "~Aah!~ ", "~Mmm!~ " };
	/**
	 * Turns a normal sentence into a sexy sentence.</br>
	 * Example:</br>
	 * "How far is it to the town hall?"</br>
	 * "How ~Aah!~ far is it ~Mmm!~ to the town ~Aah!~ hall?"</br>
	 * 
	 * @param sentence
	 *            sentence to apply sexy modifications
	 * @param frequency
	 *            of sex sounds (i.e. 4 would be 1 in 4 words are sexy)
	 * @return
	 *            modified sentence
	 */
	public static String addSexSounds(String sentence, int frequency) {
		splitSentence = sentence.split(" ");
		utilitiesStringBuilder.setLength(0);

		// 1 in "frequency" words are sexy interjections, with a minimum of 1.
		int wordsToMuffle = splitSentence.length / frequency + 1;

		int offset = 0;
		for (int i = 0; i < wordsToMuffle; i++) {
			offset = random.nextInt(frequency);
			offset = ((i * frequency + offset) >= splitSentence.length ? splitSentence.length - 1 : (i * frequency + offset));
			
			// Add the sexy sound to this word:
			splitSentence[offset] = sexSounds[random.nextInt(sexSounds.length)] + splitSentence[offset];
			
		}
		for (String word : splitSentence)
			utilitiesStringBuilder.append(word + " ");
		utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);

		return utilitiesStringBuilder.toString();
	}

	private static String[] drunkSounds = new String[] { "~Hic!~ " };
	/**
	 * Turns a normal sentence into a sexy sentence.</br>
	 * Example:</br>
	 * "How far is it to the town hall?"</br>
	 * "How ~Aah!~ far is it ~Mmm!~ to the town ~Aah!~ hall?"</br>
	 * 
	 * @param sentence
	 *            sentence to apply sexy modifications
	 * @param frequency
	 *            of sex sounds (i.e. 4 would be 1 in 4 words are sexy)
	 * @return
	 *            modified sentence
	 */
	public static String addDrunkSlur(String sentence, int frequency) {
		splitSentence = sentence.split(" ");
		utilitiesStringBuilder.setLength(0);

		// 1 in "frequency" words are sexy interjections, with a minimum of 1.
		int wordsToMuffle = splitSentence.length / frequency + 1;

		int offset = 0;
		for (int i = 0; i < wordsToMuffle; i++) {
			offset = random.nextInt(frequency);
			offset = ((i * frequency + offset) >= splitSentence.length ? splitSentence.length - 1 : (i * frequency + offset));
			
			// Add the sexy sound to this word:
			splitSentence[offset] = drunkSounds[random.nextInt(drunkSounds.length)] + splitSentence[offset];
			
		}
		for (String word : splitSentence) {
			utilitiesStringBuilder.append(word + " ");
		}
		utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);
		
		return utilitiesStringBuilder.toString().replaceAll("Hi ", "Heeey ").replaceAll("yes", "yesh").replaceAll("is", "ish").replaceAll("So", "Sho").replaceAll("so", "sho");
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

	public static String displacementTypesToStringList(List<DisplacementType> displacedList) {
		return Util.toStringList(displacedList, DisplacementType::getDescriptionPast, "and");
	}

}
