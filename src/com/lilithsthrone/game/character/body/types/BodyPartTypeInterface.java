package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;

/**
 * @since 0.1.0
 * @version 0.3.9.1
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

	/**
	 * <b>BodyCoveringType when assigned to a character should be checked through their appropriate methods!</b>
	 * @param body The body that this covering type is a part of.
	 * @return The type of skin that is covering this body part. */
	public BodyCoveringType getBodyCoveringType(Body body);
	
	/**
	 * <b>BodyCoveringType when assigned to a character should be checked through their appropriate methods!</b>
	 */
	public default BodyCoveringType getBodyCoveringType(GameCharacter gc) {
		return getBodyCoveringType(gc.getBody());
	}

	/** @return The race of this body part. */
	public AbstractRace getRace();
	
	/** @return The TFModifier for this body part. */
	public default TFModifier getTFModifier() {
		return TFModifier.NONE;
	}

	public default TFModifier getTFTypeModifier(List<? extends BodyPartTypeInterface> types) {
		switch (types.indexOf(this)) {
			case 0: return TFModifier.TF_TYPE_1;
			case 1: return TFModifier.TF_TYPE_2;
			case 2: return TFModifier.TF_TYPE_3;
			case 3: return TFModifier.TF_TYPE_4;
			case 4: return TFModifier.TF_TYPE_5;
			default: return TFModifier.NONE;
		}
	}

	//TODO
//	/** @return The description of this body part as seen in the character view screen. */
//	public String getBodyDescription(GameCharacter owner);
	
	//TODO
//	/** @return The description of this body part being changed. */
//	public String getTransformationDescription(GameCharacter owner);

	/** @return The name that should be used when describing this body part in the context of transformations. */
	public default String getTransformName() {
		if(getRace()==null) {
			return "";
		}
		return getRace().getDefaultTransformName();
	}
	
	/**
	 * @return A BodyPartClothingBlock object which defines how this BodyPartInterface is blocking InventorySlots. Returns null if it doesn't affect inventorySlots in any way.
	 */
	public default BodyPartClothingBlock getBodyPartClothingBlock() {
		return null;
	}
}
