package com.lilithsthrone.game.dialogue.companions;

/**
 * @since 0.4.4.1
 * @version 0.4.9.1
 * @author Anonymous-BCFED
 */
public enum OccupantSortingMethod {
    NONE("none", "Reset the sorting order, resulting in values being ordered by their default values."),
    
    NAME("name", "Sort slaves in alphabetical order of their first name."),

    FEMININITY("femininity", "Sort slaves in by their femininity value."),
    
    RACE("race", "Sort slaves in alphabetical order of their race name."),
    
    ROOM("room", "Sort slaves in alphabetical order of the room in which they're located."),
    
    VALUE("value", "Sort slaves in order of their monetary value.");
    
	
	private String name;
	private String sortingDescription;

	private OccupantSortingMethod(String name, String sortingDescription) {
		this.name = name;
		this.sortingDescription = sortingDescription;
	}

	public String getName() {
		return name;
	}

	public String getSortingDescription() {
		return sortingDescription;
	}
}