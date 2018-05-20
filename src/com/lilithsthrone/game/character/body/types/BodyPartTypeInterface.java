package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public interface BodyPartTypeInterface {

	public boolean isDefaultPlural();

	/** @return Pronoun for this body part. (They, it) */
	public default String getPronoun(){
		if(isDefaultPlural()) {
			return "they";
		} else {
			return "it";
		}
	}
	
	/**
	 * @return Determiner for this body part. (Returns an empty string if a default 'a' or 'an' should be used.)
	 */
	public String getDeterminer(GameCharacter gc);

	/** @return The default name of this body part. */
	public default String getName(GameCharacter gc){
		if(isDefaultPlural()) {
			return getNamePlural(gc);
		} else {
			return getNameSingular(gc);
		}
	}
	
	/** @return The singular name of this body part. */
	public String getNameSingular(GameCharacter gc);
	
	/** @return The plural name of this body part. */
	public String getNamePlural(GameCharacter gc);

	/** @return The name of this body part with its descriptor. */
	public default String getName(boolean withDescriptor, GameCharacter gc) {
		return (getDescriptor(gc).length() > 0 ? getDescriptor(gc) + " " : "") + getName(gc);
	}

	/** A 1-word descriptor that best describes this body part. */
	public String getDescriptor(GameCharacter gc);

	/** @param gc TODO
	 * @return The type of skin that is covering this body part. */
	public BodyCoveringType getBodyCoveringType(Body body);
	
	public default BodyCoveringType getBodyCoveringType(GameCharacter gc) {
		return getBodyCoveringType(gc.getBody());
	}

	public default String getTransformName() {
		return getRace().getTransformName();
	}
	
	public Race getRace();
}
