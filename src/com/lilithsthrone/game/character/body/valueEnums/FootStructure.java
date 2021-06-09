package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.2.8
 * @version 0.4
 * @author Innoxia
 */
public enum FootStructure {

	/**no feet*/
	NONE("none", "[npc.She] [npc.do] not have feet."),
	
	/**walk with feet flat on the ground*/
	PLANTIGRADE("plantigrade", "[npc.She] [npc.verb(walk)] with [npc.her] feet flat on the ground."),

	/**walk on toes with the heel permanently raised*/
	DIGITIGRADE("digitigrade", "[npc.She] [npc.verb(walk)] on [npc.her] [npc.toes], with [npc.her] heel being permanently raised."),

	/**walk on hoof with the rest of the foot permanently raised*/
	UNGULIGRADE("unguligrade", "[npc.She] [npc.verb(walk)] on [npc.her] [npc.toes], with the rest of [npc.her] foot being permanently raised."),

	/**have segmented legs like a spider, so foot is the 'tarsus' segment.*/
	ARACHNOID("arachnoid", "[npc.She] [npc.verb(walk)] on the ends of [npc.her] segmented arachnoid legs."),
	
	/**use tentacle-legs to walk around on*/
	TENTACLED("tentacled", "[npc.She] [npc.verb(use)] the lower parts of [npc.her] tentacles to walk around on.");
	
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
