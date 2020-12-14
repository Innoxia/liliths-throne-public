package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.84
 * @version 0.4
 * @author Innoxia
 */
public enum GenitalArrangement {
	
	NORMAL("normal", "[npc.NameHasFull] external genitals, in a separate area to that of [npc.her] asshole."),
	
	CLOACA("cloaca", "[npc.NamePos] genitals and asshole are all located within a slit-like cloaca, which is located in the place where [npc.her] genitals would normally be."),
	
	CLOACA_BEHIND("rear-facing cloaca", "[npc.NamePos] genitals and asshole are all located within a slit-like cloaca, which is located in the place where [npc.her] asshole would normally be.");
	
	
	private String name;
	private String description;

	private GenitalArrangement(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
