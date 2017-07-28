package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.LegType;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Leg implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private LegType type;

	public Leg(LegType type) {
		this.type = type;
	}

	@Override
	public LegType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (LegType) type;
	}

}
