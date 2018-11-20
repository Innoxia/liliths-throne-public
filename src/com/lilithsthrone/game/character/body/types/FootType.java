package com.lilithsthrone.game.character.body.types;

/**
 * @since 0.2.10
 * @version 0.2.10
 * @author Innoxia
 */
public enum FootType {
	
	HUMANOID("humanoid", "foot", "feet", "toe", "toes", "[npc.SheHasFull] human-like feet."),

	PAWS("paw-like", "paw", "paws", "toe", "toes", "[npc.SheHasFull] paw-like feet."),

	HOOFS("hoof-like", "hoof", "hoofs", "hoof", "hoof", "[npc.SheHasFull] hoofs in place of feet."),

	TALONS("bird-like", "talon", "talons", "claw", "claws", "[npc.SheHasFull] bird-like talons in place of feet.");
	
	private String typeName;
	private String singularName;
	private String pluralName;
	private String toesSingularName;
	private String toesPluralName;
	private String description;

	private FootType(String typeName, String singularName, String pluralName, String toesSingularName, String toesPluralName, String description) {
		this.typeName = typeName;
		this.singularName = singularName;
		this.pluralName = pluralName;
		this.toesSingularName = toesSingularName;
		this.toesPluralName = toesPluralName;
		this.description = description;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getSingularName() {
		return singularName;
	}

	public String getPluralName() {
		return pluralName;
	}

	public String getToesSingularName() {
		return toesSingularName;
	}

	public String getToesPluralName() {
		return toesPluralName;
	}

	public String getDescription() {
		return description;
	}
}
