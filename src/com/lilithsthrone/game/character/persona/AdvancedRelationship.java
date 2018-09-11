package com.lilithsthrone.game.character.persona;

public enum AdvancedRelationship {
    Parent("parent"),
    GrandParent("grand-parent"),
    GrandGrandParent("grand-grand-parent"),
    Child("child"),
    GrandChild("grand-child"),
    GrandGrandChild("grand-grand-child"),
    Sibling("sibling"),
    HalfSibling("half-sibling"),
    Cousin("cousin"),
    Aunt("aunt"),
    Niche("niche");

    private String display;

    AdvancedRelationship(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return display;
    }
}
