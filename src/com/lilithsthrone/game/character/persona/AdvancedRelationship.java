package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.game.character.gender.PronounType;

// TODO: needs plural forms
public enum AdvancedRelationship {
    Parent("mother", "father", "parent", 0),
    GrandParent("grand-mother", "grand-father", "grand-parent", 1),
    GrandGrandParent("grand-grand-mother", "grand-grand-father", "grand-grand-parent", 2),
    Child("daughter", "son", "child", 0),
    GrandChild("grand-daughter", "grand-son", "grand-child", 1),
    GrandGrandChild("grand-grand-daughter", "grand-grand-son", "grand-grand-child", 2),
    Sibling("sister", "brother", "sibling", 0),
    HalfSibling("half-sister", "half-brother", "half-sibling", 1.25),
    Cousin("cousin", 2),
    Pibling("aunt", "uncle", "pibling", 1.5),
    GrandPibling("grand-aunt", "grand-uncle", "grand-pibling", 2.5),
    Nibling("niece", "nephew", "nibling", 1.5);

    private final String displayF;
    private final String displayM;
    private final String displayN;
    private final double distance;

    AdvancedRelationship(String displayF, String displayM, String displayN, double distance) {
        this.displayF = displayF;
        this.displayM = displayM;
        this.displayN = displayN;
        this.distance = distance;
    }

    AdvancedRelationship(String display, double distance) {
        this(display, display, display, distance);
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
}
