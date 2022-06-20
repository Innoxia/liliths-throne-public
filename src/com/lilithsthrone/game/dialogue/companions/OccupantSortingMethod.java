package com.lilithsthrone.game.dialogue.companions;

/**
 * @since 0.4.4.1
 * @version 0.4.4.1
 * @author Anonymous-BCFED
 */
public enum OccupantSortingMethod {
    NONE("none"),
    NAME("name"),
    ROOM("room"),
    VALUE("value"),
    CUSTOM_CATEGORY("category");
	
	private String name;

	private OccupantSortingMethod(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}