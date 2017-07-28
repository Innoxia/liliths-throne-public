package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.ClitorisSize;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.Wetness;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Vagina implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private VaginaType type;
	private int wetness, clitSize, elasticity;
	private float capacity, stretchedCapacity;
	boolean virgin, pierced;

	public Vagina(VaginaType type, int clitSize, int wetness, int capacity, int elasticity, boolean virgin) {
		this.type = type;
		this.clitSize = clitSize;
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.elasticity = elasticity;
		this.virgin = virgin;
		pierced = false;
	}

	@Override
	public VaginaType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (VaginaType) type;
	}

	// Wetness:

	public Wetness getWetness() {
		return Wetness.valueOf(wetness);
	}

	/**
	 * Manually sets wetness attribute. Value is bound to >=0 &&
	 * <=Wetness.MAXIMUM.getValue()
	 * 
	 * @param wetness
	 *            Value to set wetness to.
	 * @return True if wetness was changed.
	 */
	public boolean setWetness(int wetness) {
		if (this.wetness == wetness)
			return false;

		if (wetness < 0) {
			if (this.wetness == 0)
				return false;

			this.wetness = 0;
			return true;
		}
		if (wetness > Wetness.SEVEN_DROOLING.getValue()) {
			if (this.wetness == Wetness.SEVEN_DROOLING.getValue())
				return false;

			this.wetness = Wetness.SEVEN_DROOLING.getValue();
			return true;
		}

		this.wetness = wetness;
		return true;
	}

	// Capacity:

	public Capacity getCapacity() {
		return Capacity.getCapacityFromValue((int) capacity);
	}

	public float getRawCapacityValue() {
		return capacity;
	}

	/**
	 * Sets the capacity. Value is bound to >=0 &&
	 * <=Capacity.SEVEN_GAPING.getMaximumValue()
	 * 
	 * @param capacity
	 *            Value to set capacity to.
	 * @return True if capacity was changed.
	 */
	public boolean setCapacity(float capacity) {
		if (this.capacity == capacity)
			return false;

		if (capacity < 0) {
			if (this.capacity == 0)
				return false;

			this.capacity = 0;
			return true;
		}
		if (capacity > Capacity.SEVEN_GAPING.getMaximumValue()) {
			if (this.capacity == Capacity.SEVEN_GAPING.getMaximumValue())
				return false;

			this.capacity = Capacity.SEVEN_GAPING.getMaximumValue();
			return true;
		}

		this.capacity = capacity;
		return true;
	}

	// Elasticity:

	public float getStretchedCapacity() {
		return stretchedCapacity;
	}

	public boolean setStretchedCapacity(float stretchedCapacity) {
		if (this.stretchedCapacity == stretchedCapacity)
			return false;

		if (stretchedCapacity < 0) {
			if (this.stretchedCapacity == 0)
				return false;

			this.stretchedCapacity = 0;
			return true;
		}
		if (stretchedCapacity > Capacity.SEVEN_GAPING.getMaximumValue()) {
			if (this.stretchedCapacity == Capacity.SEVEN_GAPING.getMaximumValue())
				return false;

			this.stretchedCapacity = Capacity.SEVEN_GAPING.getMaximumValue();
			return true;
		}

		this.stretchedCapacity = stretchedCapacity;
		return true;
	}

	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	/**
	 * Manually sets plasticity attribute. Value is bound to >=0 &&
	 * <=OrificePlasticity.SEVEN_MOULDABLE.getValue()
	 * 
	 * @param plasticity
	 *            Value to set plasticity to.
	 * @return True if plasticity was changed.
	 */
	public boolean setElasticity(int elasticity) {
		if (this.elasticity == elasticity)
			return false;

		if (elasticity < 0) {
			if (this.elasticity == 0)
				return false;

			this.elasticity = 0;
			return true;
		}
		if (elasticity > OrificeElasticity.SEVEN_ELASTIC.getValue()) {
			if (this.elasticity == OrificeElasticity.SEVEN_ELASTIC.getValue())
				return false;

			this.elasticity = OrificeElasticity.SEVEN_ELASTIC.getValue();
			return true;
		}

		this.elasticity = elasticity;
		return true;
	}

	// Size:

	public ClitorisSize getClitorisSize() {
		return ClitorisSize.getClitorisSizeFromInt(clitSize);
	}

	public int getRawClitorisSizeValue() {
		return clitSize;
	}

	/**
	 * Sets the clitSize. Value is bound to >=0 &&
	 * <=ClitorisSize.SEVEN_STALLION.getMaximumValue()
	 * 
	 * @param clitSize
	 *            Value to set clitSize to.
	 * @return True if clitSize was changed.
	 */
	public boolean setClitorisSize(int clitSize) {
		if (this.clitSize == clitSize)
			return false;

		if (clitSize < 0) {
			if (this.clitSize == 0)
				return false;

			this.clitSize = 0;
			return true;
		}
		if (clitSize > ClitorisSize.SEVEN_STALLION.getMaximumValue()) {
			if (this.clitSize == ClitorisSize.SEVEN_STALLION.getMaximumValue())
				return false;

			this.clitSize = ClitorisSize.SEVEN_STALLION.getMaximumValue();
			return true;
		}

		this.clitSize = clitSize;
		return true;
	}

	public boolean isVirgin() {
		return virgin;
	}

	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}

	public boolean isPierced() {
		return pierced;
	}

	public void setPierced(boolean pierced) {
		this.pierced = pierced;
	}
}
