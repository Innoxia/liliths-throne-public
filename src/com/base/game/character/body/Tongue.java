package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.TongueType;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Tongue implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private TongueType type;
	private boolean pierced;

	public Tongue(TongueType type) {
		this.type = type;
		pierced = false;
	}

	@Override
	public TongueType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (TongueType) type;
	}

	public boolean isPierced() {
		return pierced;
	}

	public void setPierced(boolean pierced) {
		this.pierced = pierced;
	}
}
