package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.BodyPartTypeInterface;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Eye implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private BodyCoveringType type;

	public Eye(BodyCoveringType type) {
		this.type = type;
	}

	@Override
	public BodyCoveringType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (BodyCoveringType) type;
	}
}
