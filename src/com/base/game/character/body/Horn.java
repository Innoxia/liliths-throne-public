package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.HornType;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Horn implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private HornType type;

	public Horn(HornType type) {
		this.type = type;
	}

	@Override
	public HornType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (HornType) type;
	}

}
