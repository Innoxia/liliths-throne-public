package com.base.game.character.body;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.BodyPartTypeInterface;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public interface BodyPartInterface {

	public BodyPartTypeInterface getType();

	public String getDeterminer(GameCharacter gc);
	
	public String getName(GameCharacter gc);
	
	public String getNameSingular(GameCharacter gc);

	public String getNamePlural(GameCharacter gc);
	
	public String getDescriptor(GameCharacter gc);
	
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
	
	/** @return The name of this body part with its descriptor. */
	public default String getNameSingular(GameCharacter gc, boolean withDescriptor) {
		if(withDescriptor) {
			if(getType().getDescriptor(gc)==null)
				return getType().getNameSingular(gc);
			
			return (getType().getDescriptor(gc).length() > 0 ? getType().getDescriptor(gc) + " " : "") + getType().getNameSingular(gc);
		} else {
			return getType().getNameSingular(gc);
		}
	}
}