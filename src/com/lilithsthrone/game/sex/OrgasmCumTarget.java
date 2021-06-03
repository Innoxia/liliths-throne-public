package com.lilithsthrone.game.sex;

/**
 * @since 0.1.97
 * @version 0.4.1
 * @author Innoxia
 */
public enum OrgasmCumTarget {

	// Specials:
	LILAYA_PANTIES("into Lilaya's panties", false),
	
	WALL("up the wall", false),
	FLOOR("onto floor", false),
	
	INSIDE("inside", true),
	INSIDE_SWITCH_DOUBLE("inside (double)", true),
	
	ARMPITS("over armpit", true),
	ASS("over ass", true),
	GROIN("over groin", true),
	BREASTS("onto breasts", true),
	FACE("over face", true),
	HAIR("into hair", true),
	STOMACH("onto stomach", true),
	LEGS("onto legs", true),
	FEET("onto feet", true),
	BACK("over back", true),
	
	SELF_GROIN("onto self groin", false),
	SELF_STOMACH("onto self stomach", false),
	SELF_LEGS("onto self legs", false),
	SELF_FEET("onto self feet", false),
	SELF_BREASTS("onto self breasts", false),
	SELF_HANDS("onto self hands", false),
	SELF_FACE("onto self face", false);
	
	private String name;
	private boolean requiresPartner;

	private OrgasmCumTarget(String name, boolean requiresPartner) {
		this.name = name;
		this.requiresPartner = requiresPartner;
	}

	public String getName() {
		return name;
	}

	public boolean isRequiresPartner() {
		return requiresPartner;
	}
	
}
