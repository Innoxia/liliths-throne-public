package com.base.game.character.body;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.BodyPartTypeInterface;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public interface BodyPartInterface {

	public BodyPartTypeInterface getType();
	
	public void setType(BodyPartTypeInterface type);

	public default String getName(GameCharacter gc) {
		return getName(gc, false);
	}
	
	/** @return The name of this body part with its descriptor. */
	public default String getName(GameCharacter gc, boolean withDescriptor) {
		if(withDescriptor) {
			if(getType().getDescriptor(gc)==null)
				return getType().getName(gc);
			
			return (getType().getDescriptor(gc).length() > 0 ? getType().getDescriptor(gc) + " " : "") + getType().getName(gc);
		} else {
			return getType().getName(gc);
		}
	}
}