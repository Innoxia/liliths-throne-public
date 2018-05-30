package com.lilithsthrone.game.settings;

public enum PlayerPronouns {
	FIRST_PERSON("first person"),
	SECOND_PERSON("second person"),
	THIRD_PERSON("third person");

	private String description;

	private PlayerPronouns(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
