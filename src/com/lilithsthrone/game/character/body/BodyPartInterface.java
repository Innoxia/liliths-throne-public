package com.lilithsthrone.game.character.body;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public interface BodyPartInterface {

	public abstract BodyPartTypeInterface getType();

	public abstract String getDeterminer(GameCharacter gc);
	
	public abstract String getName(GameCharacter gc);
	
	public abstract String getNameSingular(GameCharacter gc);

	public abstract String getNamePlural(GameCharacter gc);
	
	public abstract String getDescriptor(GameCharacter gc);
	
	/** @return The name of this body part with its descriptor. */
	public default String getName(GameCharacter gc, boolean withDescriptor) {
		if(withDescriptor) {
			if(getType().getDescriptor(gc)==null) {
				return getType().getName(gc);
			}
			
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
	
	public default BodyCoveringType getBodyCoveringType(GameCharacter gc) {
		return getType().getBodyCoveringType(gc.getBody());
	}
	
	public default BodyCoveringType getBodyCoveringType(Body body) {
		return getType().getBodyCoveringType(body);
	}
	
	/**
	 * @return true if this part is (near enough to) 100% animal-like, with no anthropomorphic qualities at all. This will almost certainly only ever be seen on feral characters or characters who have a non-bipedal body.
	 */
	public boolean isBestial(GameCharacter owner);
	
}