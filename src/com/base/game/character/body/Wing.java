package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.WingType;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Wing implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private WingType type;

	public Wing(WingType type) {
		this.type = type;
	}

	@Override
	public WingType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (WingType) type;
	}
}
