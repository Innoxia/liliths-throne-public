package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.PenisSize;
import com.base.game.character.body.valueEnums.TesticleSize;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Penis implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;

	public static final float TWO_PENIS_SIZE_MULTIPLIER = 1.6f;

	private PenisType firstPenisType, secondPenisType;
	// Capacity is a measure of how much can be fit down the urethra.
	private int size, testicleSize, cumProduction, numberOfTesticles, elasticity;
	private float capacity, stretchedCapacity;
	private boolean virgin, pierced;

	public Penis(PenisType firstPenisType, PenisType secondPenisType, int size, int testicleSize, int cumProduction, int numberOfTesticles) {
		this.firstPenisType = firstPenisType;
		this.secondPenisType = secondPenisType;
		this.size = size;

		this.cumProduction = cumProduction;
		this.numberOfTesticles = numberOfTesticles;

		this.testicleSize = testicleSize;

		pierced = false;
		virgin = true;

		capacity = -1;
		stretchedCapacity = -1;
		elasticity = 0;
	}

	@Override
	public PenisType getType() {
		return firstPenisType;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		firstPenisType = (PenisType) type;
	}

	public PenisType getSecondPenisType() {
		return secondPenisType;
	}

	public void setSecondPenisType(PenisType secondPenisType) {
		this.secondPenisType = secondPenisType;
	}

	public boolean hasSecondPenis() {
		return secondPenisType != PenisType.NONE;
	}

	public String getSizeDescription(int size) {
		return PenisSize.getPenisSizeFromInt(size).getDescriptor();
	}

	public String getTesticleSizeDescription() {
		if (testicleSize <= 3) {
			return ("vestigial");
		} else if (testicleSize <= 6) {
			return ("tiny");
		} else if (testicleSize <= 9) {
			return ("human-sized");
		} else if (testicleSize <= 12) {
			return ("large");
		} else if (testicleSize <= 15) {
			return ("orange-sized");
		} else if (testicleSize <= 18) {
			return ("grapefruit-sized");
		} else {
			return ("watermelon-sized");
		}
	}

	// Size:

	public PenisSize getSize() {
		return PenisSize.getPenisSizeFromInt(size);
	}

	public int getRawSizeValue() {
		return size;
	}

	/**
	 * Sets the size. Value is bound to >=0 &&
	 * <=PenisSize.SEVEN_STALLION.getMaximumValue()
	 * 
	 * @param size
	 *            Value to set size to.
	 * @return True if size was changed.
	 */
	public boolean setPenisSize(int size) {
		if (this.size == size)
			return false;

		if (size < 0) {
			if (this.size == 0)
				return false;

			this.size = 0;
			return true;
		}
		if (size > PenisSize.SEVEN_STALLION.getMaximumValue()) {
			if (this.size == PenisSize.SEVEN_STALLION.getMaximumValue())
				return false;

			this.size = PenisSize.SEVEN_STALLION.getMaximumValue();
			return true;
		}

		this.size = size;
		return true;
	}

	// Testicle size:

	public TesticleSize getTesticleSize() {
		return TesticleSize.getTesticleSizeFromInt(testicleSize);
	}

	/**
	 * Sets the testicleSize. Value is bound to >=0 &&
	 * <=TesticleSize.SEVEN_ABSURD.getValue()
	 * 
	 * @param testicleSize
	 *            Value to set testicleSize to.
	 * @return True if testicleSize was changed.
	 */
	public boolean setTesticleSize(int testicleSize) {
		if (this.testicleSize == testicleSize)
			return false;

		if (testicleSize < 0) {
			if (this.testicleSize == 0)
				return false;

			this.testicleSize = 0;
			return true;
		}
		if (testicleSize > TesticleSize.SEVEN_ABSURD.getValue()) {
			if (this.testicleSize == TesticleSize.SEVEN_ABSURD.getValue())
				return false;

			this.testicleSize = TesticleSize.SEVEN_ABSURD.getValue();
			return true;
		}

		this.testicleSize = testicleSize;
		return true;
	}

	// Cum production:

	public CumProduction getCumProduction() {
		return CumProduction.getCumProductionFromInt(cumProduction);
	}

	public int getRawCumProductionValue() {
		return cumProduction;
	}

	/**
	 * Sets the cumProduction. Value is bound to >=0 &&
	 * <=CumProduction.SEVEN_MONSTROUS.getMaximumValue()
	 * 
	 * @param cumProduction
	 *            Value to set cumProduction to.
	 * @return True if cumProduction was changed.
	 */
	public boolean setCumProduction(int cumProduction) {
		if (this.cumProduction == cumProduction)
			return false;

		if (cumProduction < 0) {
			if (this.cumProduction == 0)
				return false;

			this.cumProduction = 0;
			return true;
		}
		if (cumProduction > CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			if (this.cumProduction == CumProduction.SEVEN_MONSTROUS.getMaximumValue())
				return false;

			this.cumProduction = CumProduction.SEVEN_MONSTROUS.getMaximumValue();
			return true;
		}

		this.cumProduction = cumProduction;
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

	public int getNumberOfTesticles() {
		return numberOfTesticles;
	}

	/**
	 * Sets the numberOfTesticles.
	 * 
	 * @param numberOfTesticles
	 *            Value to set numberOfTesticles to.
	 * @return True if numberOfTesticles was changed.
	 */
	public boolean setNumberOfTesticles(int numberOfTesticles) {
		if (this.numberOfTesticles == numberOfTesticles)
			return false;

		this.numberOfTesticles = numberOfTesticles;
		return true;
	}

	public boolean isPierced() {
		return pierced;
	}

	public void setPierced(boolean pierced) {
		this.pierced = pierced;
	}

	public boolean isUrethraVirgin() {
		return virgin;
	}

	public void setUrethraVirgin(boolean virgin) {
		this.virgin = virgin;
	}
}
