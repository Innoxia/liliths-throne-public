package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.3.21
 * @author Innoxia
 */
public enum NippleShape {

	NORMAL("normal", false),
	
	INVERTED("inverted", false),
	
	VAGINA("nipple-cunts", true),
	
	LIPS("lipples", true);
	
	private String descriptor;
	private boolean associatedWithPenetrationContent;

	private NippleShape(String descriptor, boolean associatedWithPenetrationContent) {
		this.descriptor = descriptor;
		this.associatedWithPenetrationContent = associatedWithPenetrationContent;
	}

	public String getName() {
		return descriptor;
	}

	public boolean isAssociatedWithPenetrationContent() {
		return associatedWithPenetrationContent;
	}

}
