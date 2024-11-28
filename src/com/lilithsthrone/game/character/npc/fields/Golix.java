package com.lilithsthrone.game.character.npc.fields;

import com.lilithsthrone.game.character.npc.misc.Elemental;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Innoxia
 */
public class Golix extends Elemental {

	public Golix() {
		this(false);
	}
	
	public Golix(boolean isImported) {
		super(isImported);
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getArtworkFolderName() {
		return "Golix";
	}
}
