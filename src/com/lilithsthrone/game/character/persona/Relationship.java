package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.PronounType;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Set;

// TODO: needs plural forms
/**
 * @since 0.2.0
 * @version 0.4.4.1
 * @author Innoxia, orvail
 */
public enum Relationship {

	/** For ovipositor egg incubation */
    IncubatorParent("incubator-mother", "incubator-father", "incubator-parent", 0),
    IncubatorChild("incubated-daughter", "incubated-son", "incubated-child", 0),

    Parent(true, 0),
    GrandParent(true, 1),
    GrandGrandParent(true, 2),
    GrandGrandGrandParent(true, 3),
    GrandGrandGrandGrandParent(true, 4),
    GrandGrandGrandGrandGrandParent(true, 5),
    GrandGrandGrandGrandGrandGrandParent(true, 6),
    GrandGrandGrandGrandGrandGrandGrandParent(true, 7),
    GrandGrandGrandGrandGrandGrandGrandGrandParent(true, 8),
    Child(false, 0),
    GrandChild(false, 1),
    GrandGrandChild(false, 2),
    GrandGrandGrandChild(false, 3),
    GrandGrandGrandGrandChild(false, 4),
    GrandGrandGrandGrandGrandChild(false, 5),
    GrandGrandGrandGrandGrandGrandChild(false, 6),
    GrandGrandGrandGrandGrandGrandGrandChild(false, 7),
    GrandGrandGrandGrandGrandGrandGrandGrandChild(false, 8),
    Sibling("sister", "brother", "sibling", 0),
    SiblingTwin("twin-sister", "twin-brother", "twin", 0),
    HalfSibling("half-sister", "half-brother", "half-sibling", 1.25),
    Cousin("cousin", 2),
    Pibling("aunt", "uncle", "pibling", 1.5),
    GrandPibling("grand-aunt", "grand-uncle", "grand-pibling", 2.5),
    Nibling("niece", "nephew", "nibling", 1.5);

    // Relationship Groups for pet name choices.
    public static final Set<Relationship> parentRelationships = Set.of(Relationship.Parent, Relationship.IncubatorParent);
    public static final Set<Relationship> grandparentRelationships = Set.of(
	GrandParent, GrandGrandParent, GrandGrandGrandParent, GrandGrandGrandGrandChild,
	GrandGrandGrandGrandGrandChild, GrandGrandGrandGrandGrandGrandChild,
	GrandGrandGrandGrandGrandGrandGrandChild, GrandGrandGrandGrandGrandGrandGrandGrandChild);
    public static final Set<Relationship> piblingRelationships = Set.of(GrandPibling, Pibling);

    private static String GrandN(int n, String postfix) {
      if (n == 0) { return postfix; }
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < n - 1; ++i) {
        sb.append("great-");
      }
      sb.append("grand-");
      sb.append(postfix);
      return sb.toString();
    }

    private static String GrandNEnumName(int n, String postfix) {
      if (n == 0) { return postfix; }
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < n-1; ++i) {
        sb.append("Grand");
      }
      sb.append(postfix);
      return sb.toString();
    };

    private static final HashMap<Integer, Relationship> grandNCache = new HashMap<>();
    static {
      // Keys are (1 + distance) * (-1 ^^ (!parent))
      for (int i = 0; i < 8; ++i) {
        grandNCache.put(1 + i, Relationship.valueOf(GrandNEnumName(i, "Parent")));
        grandNCache.put(-1 * (1 + i), Relationship.valueOf(GrandNEnumName(i, "Child")));
      }
    }

    public static Relationship GetGrandN(boolean parent, int distance) {
      int key = (distance + 1) * (parent ? 1 : -1);
      return grandNCache.getOrDefault(key, null);
    }

    private final String displayF;
    private final String displayM;
    private final String displayN;
    private final double distance;

    Relationship(String displayF, String displayM, String displayN, double distance) {
        this.displayF = displayF;
        this.displayM = displayM;
        this.displayN = displayN;
        this.distance = distance;
    }

    Relationship(String display, double distance) {
        this(display, display, display, distance);
    }

    // Direct parents/children
    Relationship(boolean upward, int distance) {
      this(GrandN(distance, upward ? "mother" : "daughter"),
           GrandN(distance, upward ? "father" : "son"),
           GrandN(distance, upward ? "parent" : "child"),
           distance);
    }

    public String toString(PronounType pronounType) {
        switch (pronounType) {
            case FEMININE:
                return displayF;
            case NEUTRAL:
                return displayN;
            case MASCULINE:
                return displayM;
            default:
                throw new RuntimeException();
        }
    }

    public String getName(GameCharacter character) {
    	if(character.isFeminine()) {
    		return getDisplayF();
    	} else {
    		return getDisplayM();
    	}
    }
    
    public String getDisplayF() {
        return displayF;
    }

    public String getDisplayM() {
        return displayM;
    }

    public String getDisplayN() {
        return displayN;
    }

    public double getDistance() {
        return distance;
    }
}
