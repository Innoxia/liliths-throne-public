package com.lilithsthrone.game.character.body.types;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public enum FootStructure {
	
	/**walk with feet flat on the ground*/
	PLANTIGRADE("plantigrade", "[npc.She] [npc.verb(walk)] with [npc.her] feet flat on the ground."),

	/**walk on toes with the heel permanently raised*/
	DIGITIGRADE("digitigrade", "[npc.She] [npc.verb(walk)] on [npc.her] toes, with [npc.her] heel being permanently raised."),

	/**walk on hoof with the rest of the foot permanently raised*/
	UNGULIGRADE("unguligrade", "[npc.She] [npc.verb(walk)] on [npc.her] hoofs, with the rest of [npc.her] foot being permanently raised.");
	
	private String name;
	private String description;

	private FootStructure(String name, String description) {
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
