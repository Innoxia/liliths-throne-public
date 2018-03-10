package com.lilithsthrone.game.sex;

/**
 * @since 0.1.97
 * @version 0.1.97
 * @author Innoxia
 */
public enum OrgasmCumTarget {

	WALL("up the wall"),
	FLOOR("onto floor"),
	
	INSIDE("inside"),
	
	ASS("over ass"),
	GROIN("over groin"),
	BREASTS("onto breasts"),
	FACE("over face"),
	HAIR("into hair"),
	STOMACH("onto stomach"),
	LEGS("onto legs"),
	BACK("over back"),
	SELF_STOMACH("onto self stomach");
	
	private String name;

	private OrgasmCumTarget(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
