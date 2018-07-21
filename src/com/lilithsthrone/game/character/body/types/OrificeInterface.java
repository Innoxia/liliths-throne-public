package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.*;

import java.util.List;

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
	public String setCapacity(GameCharacter owner, float capacity, boolean setStretchedValueToNewValue);
	public float getStretchedCapacity();
	public boolean setStretchedCapacity(float stretchedCapacity);

	public OrificeElasticity getElasticity();
	public String setElasticity(GameCharacter owner, int elasticity);
	
	public OrificePlasticity getPlasticity();
	public String setPlasticity(GameCharacter owner, int plasticity);

	public IngestionRate getIngestionRate();
	public String setIngestionRate(GameCharacter owner, int ingestionRate);
	public void setIngestionRate(int ingestionRate);

	public boolean isVirgin();
	public void setVirgin(boolean virgin);

	public boolean isPlugged ();
	public void setPlugged(boolean plugged);

	public boolean hasOrificeModifier(OrificeModifier modifier);
	public String addOrificeModifier(GameCharacter owner, OrificeModifier modifier);
	public String removeOrificeModifier(GameCharacter owner, OrificeModifier modifier);
	public List<FluidStored> getFluidsStored();
	public void addFluidStored(FluidStored fluid);
	public float getFluidLossPerMinute();
	
}