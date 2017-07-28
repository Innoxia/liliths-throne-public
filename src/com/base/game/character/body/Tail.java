package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.TailType;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Tail implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private TailType type;

	public Tail(TailType type) {
		this.type = type;
	}

	@Override
	public TailType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (TailType) type;
	}
}
