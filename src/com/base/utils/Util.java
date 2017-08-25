package com.base.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.inventory.clothing.DisplacementType;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * This is just a big mess of utility classes that I wanted to throw somewhere.
 * 
 * @since 0.1.0
 * @version 0.1.83
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
	
	public static String getFileExtention(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}

		return extension;
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
	public static <T, S> HashMap<T, S> newHashMapOfValues(Value<T, S>... values) {
		HashMap<T, S> map = new HashMap<>();

		for (Value<T, S> v : values)
			map.put(v.getKey(), v.getValue());

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

	// This seems stupid... ;_;
	public static String intToString(int integer) {
		switch (integer) {
		case 0:
			return "zero";
		case 1:
			return "one";
		case 2:
			return "two";
		case 3:
			return "three";
		case 4:
			return "four";
		case 5:
			return "five";
		case 6:
			return "six";
		case 7:
			return "seven";
		case 8:
			return "eight";
		case 9:
			return "nine";
		case 10:
			return "ten";
		case 11:
			return "eleven";
		case 12:
			return "twelve";
		case 13:
			return "thirteen";
		case 14:
			return "fourteen";
		case 15:
			return "fifteen";
		case 16:
			return "sixteen";
		case 17:
			return "seventeen";
		case 18:
			return "eighteen";
		case 19:
			return "nineteen";
		case 20:
			return "twenty";
		default:
			return String.valueOf(integer);
		}
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
				return "€";
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
				return "£";
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
		return (int) (cm / 2.54f);
	}

	public static int conversionInchesToCentimetres(int inches) {
		return (int) (inches * 2.54f);
	}

	public static String centimetresToMetresAndCentimetres(int cm) {
		return ((cm / 100) + ((cm % 100) != 0 ? ("." + cm % 100) + "m." : "m"));
	}

	public static String inchesToFeetAndInches(int inches) {
		return ((((inches) / 12) == 0 ? "" : (inches) / 12) + (((inches) / 12) > 0 ? "'" : "") + (((inches) % 12) == 0 ? "" : " ") + (((inches) % 12) != 0 ? ((inches) % 12) + "\"" : ""));
	}

	public static int conversionKilogramsToPounds(int kg) {
		return (int) (kg * 2.20462268f);
	}

	public static String poundsToStoneAndPounds(int pounds) {
		return ((((pounds) / 14) == 0 ? "" : (pounds) / 14) + (((pounds) / 12) > 0 ? "st." : "") + (((pounds) % 14) == 0 ? "" : " ") + (((pounds) % 14) != 0 ? ((pounds) % 14) + "lb" : ""));
	}

	public static String capitaliseSentence(String sentence) {
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
	 * @param frequency
	 *            of stutter words (i.e. 4 would be 1 in 4 words are stutters)
	 * @return
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
	 * Used in conjuntion with addStutter(): "L-Like, How far is it t-to the, like, town hall?"
	 * 
	 * @param sentence
	 * @param frequency
	 *            of bimbo interjections (i.e. 4 would be 1 in 4 words havea
	 *            bimbo interjection)
	 * @return
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
		for (int i = 0; i < splitSentence.length; i++)
			utilitiesStringBuilder.append(splitSentence[i] + " ");
		utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);

		// 1/3 chance of having a bimbo sentence ending:
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
	 * @param frequency
	 *            of muffled words (i.e. 4 would be 1 in 4 words are muffled)
	 * @return
	 */
	public static String addMuffle(String sentence, int frequency) {
		splitSentence = sentence.split(" ");
		utilitiesStringBuilder.setLength(0);

		// 1 in "frequency" words are bimbo interjections, with a minimum of 1.
		int wordsToMuffle = splitSentence.length / frequency + 1;

		int offset = 0;
		for (int i = 0; i < wordsToMuffle; i++) {
			offset = random.nextInt(frequency);
			offset = ((i * frequency + offset) >= splitSentence.length ? splitSentence.length - 1 : (i * frequency + offset));
			
			// Add the muffled sound to this word:
			splitSentence[offset] = muffledSounds[random.nextInt(muffledSounds.length)] + splitSentence[offset];
			
		}
		for (int i = 0; i < splitSentence.length; i++)
			utilitiesStringBuilder.append(splitSentence[i] + " ");
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
	 * @param frequency
	 *            of sex sounds (i.e. 4 would be 1 in 4 words are sexy)
	 * @return
	 */
	public static String addSexSounds(String sentence, int frequency) {
		splitSentence = sentence.split(" ");
		utilitiesStringBuilder.setLength(0);

		// 1 in "frequency" words are bimbo interjections, with a minimum of 1.
		int wordsToMuffle = splitSentence.length / frequency + 1;

		int offset = 0;
		for (int i = 0; i < wordsToMuffle; i++) {
			offset = random.nextInt(frequency);
			offset = ((i * frequency + offset) >= splitSentence.length ? splitSentence.length - 1 : (i * frequency + offset));
			
			// Add the muffled sound to this word:
			splitSentence[offset] = sexSounds[random.nextInt(sexSounds.length)] + splitSentence[offset];
			
		}
		for (int i = 0; i < splitSentence.length; i++)
			utilitiesStringBuilder.append(splitSentence[i] + " ");
		utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);

		return utilitiesStringBuilder.toString();
	}

	// What a mess :^)
	public static String clothesToStringList(Collection<AbstractClothing> clothingSet) {
		utilitiesStringBuilder.setLength(0);
		int i = 0;
		for (AbstractClothing clothing : clothingSet) {
			i++;
			utilitiesStringBuilder.append((utilitiesStringBuilder.length() != 0 ? (i == clothingSet.size() ? " and " : ", ") : "") + Util.capitaliseSentence(clothing.getClothingType().getName()));
		}
		return utilitiesStringBuilder.toString();
	}

	public static String setToStringListCoverableArea(Set<CoverableArea> coverableAreaSet) {
		utilitiesStringBuilder.setLength(0);
		int i = 0;
		for (CoverableArea coverableArea : coverableAreaSet) {
			i++;
			utilitiesStringBuilder.append((utilitiesStringBuilder.length() != 0 ? (i == coverableAreaSet.size() ? " and " : ", ") : "") + Util.capitaliseSentence(coverableArea.getName()));
		}
		return utilitiesStringBuilder.toString();
	}

	public static String stringsToStringList(List<String> list, boolean capitalise) {
		utilitiesStringBuilder.setLength(0);
		int i = 0;
		for (String s : list) {
			i++;
			utilitiesStringBuilder.append((utilitiesStringBuilder.length() != 0 ? (i == list.size() ? " and " : ", ") : "") + (capitalise?Util.capitaliseSentence(s):s));
		}
		return utilitiesStringBuilder.toString();
	}

	public static String stringsToStringChoice(List<String> list) {
		utilitiesStringBuilder.setLength(0);
		int i = 0;
		for (String s : list) {
			i++;
			utilitiesStringBuilder.append((utilitiesStringBuilder.length() != 0 ? (i == list.size() ? " or " : ", ") : "") + Util.capitaliseSentence(s));
		}
		return utilitiesStringBuilder.toString();
	}

	public static String colourSetToStringList(Set<Colour> colourSet) {
		utilitiesStringBuilder.setLength(0);
		int i = 0;
		for (Colour colour : colourSet) {
			i++;
			utilitiesStringBuilder.append((utilitiesStringBuilder.length() != 0 ? (i == colourSet.size() ? " and " : ", ") : "") + colour.getName());
		}
		return utilitiesStringBuilder.toString();
	}

	public static String coverableAreaListToStringList(List<CoverableArea> coverableAreaCollection) {
		utilitiesStringBuilder.setLength(0);
		int i = 0;
		for (CoverableArea coverableArea : coverableAreaCollection) {
			i++;
			utilitiesStringBuilder.append((utilitiesStringBuilder.length() != 0 ? (i == coverableAreaCollection.size() ? " and " : ", ") : "") + coverableArea.getName());
		}
		return utilitiesStringBuilder.toString();
	}

	public static String inventorySlotsToStringList(List<InventorySlot> inventorySlots) {
		utilitiesStringBuilder.setLength(0);
		int i = 0;
		for (InventorySlot invSlot : inventorySlots) {
			i++;
			utilitiesStringBuilder.append((utilitiesStringBuilder.length() != 0 ? (i == inventorySlots.size() ? " and " : ", ") : "") + invSlot.getName());
		}
		return utilitiesStringBuilder.toString();
	}

	public static String displacementTypesToStringList(List<DisplacementType> displacedList) {
		utilitiesStringBuilder.setLength(0);
		int i = 0;
		for (DisplacementType invSlot : displacedList) {
			i++;
			utilitiesStringBuilder.append((utilitiesStringBuilder.length() != 0 ? (i == displacedList.size() ? " and " : ", ") : "") + invSlot.getDescriptionPast());
		}
		return utilitiesStringBuilder.toString();
	}

}
