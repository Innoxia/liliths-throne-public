package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.OrificeInterface;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.utils.Util;

import java.io.Serializable;
import java.util.*;

/**
 * @since 0.1.?
 * @version 0.2.4
 * @author Innoxia
 */
public abstract class  AbstractOrifice implements OrificeInterface, Serializable {
	private static final long serialVersionUID = 1L;

	protected int wetness;
	protected int elasticity;
	protected int plasticity;
	protected int ingestionRate;
	protected float capacity;
	protected float stretchedCapacity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;
	protected boolean plugged;
	protected List<FluidStored> fluidsStored;

	public AbstractOrifice(int wetness, float capacity, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		this.orificeModifiers = new HashSet<>(orificeModifiers);
		this.fluidsStored = new ArrayList<>();
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		return Wetness.valueOf(wetness);
	}


	@Override
	public Capacity getCapacity() {
		return Capacity.getCapacityFromValue(capacity);
	}

	@Override
	public float getRawCapacityValue() {
		return capacity;
	}

	
	@Override
	public float getStretchedCapacity() {
		return stretchedCapacity;
	}

	@Override
	public boolean setStretchedCapacity(float stretchedCapacity) {
		float oldStretchedCapacity = this.stretchedCapacity;
		this.stretchedCapacity = Math.max(0, Math.min(stretchedCapacity, Capacity.SEVEN_GAPING.getMaximumValue()));
		return oldStretchedCapacity != this.stretchedCapacity;
	}

	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}


	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}


	@Override
	public IngestionRate getIngestionRate() {
		return IngestionRate.getIngestionRateFromInt(ingestionRate);
	}

	@Override
	public void setIngestionRate(int ingestionRate){
		this.ingestionRate = Math.max(0, Math.min(ingestionRate, IngestionRate.FIVE_MAXIMUM.getValue()));
	}

	@Override
	public boolean isVirgin() {
		return virgin;
	}

	@Override
	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}

	@Override
	public boolean isPlugged() {
		return virgin;
	}

	@Override
	public void setPlugged(boolean virgin) {
		this.virgin = virgin;
	}

	@Override
	public boolean hasOrificeModifier(OrificeModifier modifier) {
		return orificeModifiers.contains(modifier);
	}

	public Set<OrificeModifier> getOrificeModifiers() {
		return orificeModifiers;
	}

	@Override
	public List<FluidStored> getFluidsStored() {
		return fluidsStored;
	}

	@Override
	public void addFluidStored(FluidStored fluid){
		this.fluidsStored.add(fluid);
	}

	public float getTotalFluidInArea() {
		float total = 0;
		for(FluidStored f : fluidsStored) {
			total+=f.getMillilitres();
		}
		return total;
	}

	abstract public float getFluidLeakPerMinute();

	@Override
	public float getFluidLossPerMinute(){

		float fluidLost = getIngestionRate().getRate();

		if(!isPlugged()) {
			fluidLost += (float) (getFluidLeakPerMinute()
					* (1 + 5*Util.getModifiedDropoffValue(getTotalFluidInArea(), CumProduction.SEVEN_MONSTROUS.getMaximumValue())/CumProduction.SEVEN_MONSTROUS.getMaximumValue()));
		}

		return fluidLost;
	}

}
