package com.lilithsthrone.game.character.race;

/**
 * @since 0.1.78
 * @version 0.1.78
 * @author Innoxia
 */
public enum FurryPreference {

	/**No furry parts at all. (NPCs will spawn as regular humans.)*/
	HUMAN("Human", "No furry parts at all. (NPCs will spawn as regular humans.)"),
	
	/**NPCs will have randomised bodies, but will never have furry arms, legs, faces or skin.*/
	MINIMUM("Minimum", "NPCs will have randomised bodies, but will never have furry arms, legs, faces or skin."),
	
	/**NPCs will have randomised bodies, but will never have furry faces or skin.*/
	REDUCED("Reduced", "NPCs will have randomised bodies, but will never have furry faces or skin."),
	
	/**NPCs will have randomised bodies, with no body-part limitations.*/
	NORMAL("Normal", "NPCs will have randomised bodies, with no body-part limitations."),
	
	/**NPCs will have fully-furry bodies wherever possible.*/
	MAXIMUM("Maximum", "NPCs will have fully-furry bodies wherever possible.");
	
	private String name, description;
	private FurryPreference(String name, String description) {
		this.name=name;
		this.description=description;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
}
