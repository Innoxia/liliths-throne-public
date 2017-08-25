package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.character.body.valueEnums.OrificePlasticity;
import com.base.game.character.body.valueEnums.Wetness;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public interface OrificeInterface {
	
	public Wetness getWetness(GameCharacter owner);
	public String setWetness(GameCharacter owner, int wetness);

	public Capacity getCapacity();
	public float getRawCapacityValue();
	public String setCapacity(GameCharacter owner, float capacity);
	public float getStretchedCapacity();
	public boolean setStretchedCapacity(float stretchedCapacity);

	public OrificeElasticity getElasticity();
	public String setElasticity(GameCharacter owner, int elasticity);
	
	public OrificePlasticity getPlasticity();
	public String setPlasticity(GameCharacter owner, int plasticity);

	public boolean isVirgin();
	public void setVirgin(boolean virgin);

	public boolean hasOrificeModifier(OrificeModifier modifier);
	public String addOrificeModifier(GameCharacter owner, OrificeModifier modifier);
	public String removeOrificeModifier(GameCharacter owner, OrificeModifier modifier);
	
}