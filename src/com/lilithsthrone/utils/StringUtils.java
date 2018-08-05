package com.lilithsthrone.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import com.lilithsthrone.game.dialogue.utils.UtilText;

public class StringUtils{

    /**
	 * Prepends a descriptor to a word. Accounts for hyphenated descriptors.
	 */
	public static String applyDescriptor(String descriptor, String name) {
		if (descriptor == null || descriptor.isEmpty()){
			return name;
		}
        
        if (descriptor.endsWith("-")) {
            return descriptor + name;
        }

		return  descriptor + " " + name;
    }
    

    /**
	 * Prepends either 'a' or 'an' depending on the word.
	 */
	public static String applyDeterminer(String input) {
        boolean startsWithVowelSound = UtilText.isVowel(input.charAt(0));

        if(vowelSounds.contains(input)) {
            startsWithVowelSound = true;
        } else if (consonantSounds.contains(input)) {
            startsWithVowelSound = false;
        }

		return startsWithVowelSound ? "an " + input : "a " + input;
    }
    
    private static final TreeSet<String> consonantSounds = new TreeSet<String>(Arrays.asList(
        "eunuch",
        "one",
        "united",
        "universal",
        "urea",
        "ureter",
        "urethra",
        "urine",
        "use",
        "utensil",
        "uterus",
        "uvula"
    ));

    private static final TreeSet<String> vowelSounds = new TreeSet<String>(Arrays.asList(
        "heir",
        "honest",
        "honor",
        "honorable",
        "honored",
        "hour"
    ));

    public static String conjugateVerb(String verb, VerbTense tense) {
        if(irregularVerbs.containsKey(verb)){
            return irregularVerbs.get(verb)[tense.ordinal()];
        }

        if (tense == VerbTense.Past){
            return verb + "ed";
        } else if (tense == VerbTense.Present) {
            return verb + "s";
        } else if (tense == VerbTense.Future) {
            return verb;
        } else if (tense == VerbTense.Continuous) {
            return verb + "ing";
        } else if (tense == VerbTense.PastParticiple) {
            return verb + "en";
        }

        return verb;
    }

    private static Map<String, String[]> irregularVerbs = new HashMap<String, String[]>();

    // Not exhuastive (obviously). Add more as needed
    static {
        irregularVerbs.put("awake", new String[] {"awoke", "awakes", "awake", "awaking", "awoken"});
        irregularVerbs.put("begin", new String[] {"began", "begins", "begin", "beginning", "begun"});
        irregularVerbs.put("bend", new String[] {"bent", "bends", "bend", "bending", "begun"});
        irregularVerbs.put("bite", new String[] {"bit", "bites", "bite", "bitting", "bitten"});
        irregularVerbs.put("blow", new String[] {"blew", "blows", "blow", "blowing", "blown"});
        irregularVerbs.put("break", new String[] {"broke", "breaks", "break", "breaking", "broken"});
        irregularVerbs.put("eat", new String[] {"ate", "eats", "eat", "eating", "eaten"});
        irregularVerbs.put("run", new String[] {"ran", "runs", "run", "running", "run"});
        irregularVerbs.put("wear", new String[] {"wore", "wears", "wear", "wearing", "worn"});
    }

    public static boolean endWithAny(String s, String... values) {
        for(String value : values){
            if(s.endsWith(value)) { return true;}
        }
        return false;
    }

    /**
     * Converts a name to a possesive form.
     */
    public static String makePossesive(String name) {
        if(name.endsWith("s")) {
            return name + "'";
        }

        return name + "'s";
    }

    public enum VerbAgreement {
        Singular,
        Plural
    }

    public enum VerbTense {
        Past,
        Present,
        Future,
        Continuous,
        PastParticiple
    }

    /**
     * Makes a verb agree with its subject.
     */
    public static String makeVerbAgree(String verb) {
        return StringUtils.makeVerbAgree(verb, VerbAgreement.Singular);
    }

    /**
     * Makes a verb agree with its subject.
     */
    public static String makeVerbAgree(String verb, VerbAgreement agreement) {
        if(agreement == VerbAgreement.Singular){
            if (StringUtils.endWithAny(verb, "s", "x", "sh", "ch", "o")){
                return verb + "es";
            } else if (verb.endsWith("y")) {
                return verb.substring(0, verb.length()-1) + "ies";
            } else {
                return verb + "s";
            }
        }

        return verb;
    }

    public static String pluralise(String word) {
        if (StringUtils.endWithAny(word, "s", "x", "sh", "ch", "o")){
            return word + "es";
        } else if (word.endsWith("y")) {
            return word.substring(0, word.length()-1) + "ies";
        } else {
            return word + "s";
        }
    }

    public static boolean startsWithAny(String s, String... values) {
        for(String value : values){
            if(s.startsWith(value)) { return true;}
        }
        return false;
    }

    public static boolean startsWithUpperCase(String s) {
        return Character.isUpperCase(s.charAt(0));
    }
}