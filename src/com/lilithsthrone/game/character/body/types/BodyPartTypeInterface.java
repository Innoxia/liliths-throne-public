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
	
	public Race getRace();
	
	public default String getTransformName() {
		if(getRace()==null) {
			return "";
		}
		
		switch(getRace()){
			case ANGEL:
				return "angelic";
			case CAT_MORPH:
				return "feline";
			case DEMON:
				return "demonic";
			case DOG_MORPH:
				return "canine";
			case COW_MORPH:
				return "bovine";
			case SQUIRREL_MORPH:
				return "squirrel";
			case ALLIGATOR_MORPH:
				return "alligator";
			case HARPY:
				return "harpy";
			case HORSE_MORPH:
				return "equine";
			case REINDEER_MORPH:
				return "reindeer";
			case HUMAN:
				return "human";
			case WOLF_MORPH:
				return "wolf";
			case FOX_MORPH:
				return "fox";
			case BAT_MORPH:
				return "bat";
			case RAT_MORPH:
				return "rat";
			case RABBIT_MORPH:
				return "rabbit";
			case ELEMENTAL_AIR:
				return "elemental air";
			case ELEMENTAL_ARCANE:
				return "elemental arcane";
			case ELEMENTAL_EARTH:
				return "elemental earth";
			case ELEMENTAL_FIRE:
				return "elemental fire";
			case ELEMENTAL_WATER:
				return "elemental water";
			case NONE:
				break;
			case SLIME:
				return "slime";
		}
		return "";
	}
}
