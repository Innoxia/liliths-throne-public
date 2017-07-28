package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.EarType;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Ear implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private EarType type;
	private boolean pierced;

	public Ear(EarType type) {
		this.type = type;
		pierced = false;
	}

	@Override
	public EarType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (EarType) type;
	}

	public boolean isPierced() {
		return pierced;
	}

	public void setPierced(boolean pierced) {
		this.pierced = pierced;
	}

}
