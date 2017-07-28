package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.FaceType;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.MakeupLevel;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.PenisSize;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Face implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private FaceType type;
	private int makeupLevel, elasticity;
	private float capacity, stretchedCapacity; // capacity is a measure of how good this face is at taking cock.
	private boolean piercedNose, piercedLip, virgin;

	private Tongue tongue;

	public Face(FaceType type, int makeupLevel) {
		this.type = type;
		this.makeupLevel = makeupLevel;
		piercedNose = false;
		piercedLip = false;

		tongue = new Tongue(type.getTongueType());

		// Elasticity is basically skill at getting better at giving head.
		elasticity = OrificeElasticity.THREE_FLEXIBLE.getValue();
		capacity = PenisSize.TWO_AVERAGE.getMinimumValue();
		stretchedCapacity = capacity;

		virgin = true;
	}

	@Override
	public FaceType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (FaceType) type;

		tongue.setType(((FaceType) type).getTongueType());
	}

	public MakeupLevel getMakeupLevel() {
		return MakeupLevel.getMakeupLevelFromInt(makeupLevel);
	}

	/**
	 * Sets the makeupLevel value. Value is bound to >=0 &&
	 * <=MakeupLevel.MAXIMUM.getValue()
	 * 
	 * @param makeupLevel
	 *            Value to set makeupLevel to.
	 * @return True if makeupLevel was changed.
	 */
	public boolean setMakeupLevel(int makeupLevel) {
		if (this.makeupLevel == makeupLevel)
			return false;

		if (makeupLevel < 0) {
			if (this.makeupLevel == 0)
				return false;

			this.makeupLevel = 0;
			return true;
		}
		if (makeupLevel > MakeupLevel.MAXIMUM.getValue()) {
			if (this.makeupLevel == MakeupLevel.MAXIMUM.getValue())
				return false;

			this.makeupLevel = MakeupLevel.MAXIMUM.getValue();
			return true;
		}

		this.makeupLevel = makeupLevel;
		return true;
	}


	public Capacity getCapacity() {
		return Capacity.getCapacityFromValue((int)capacity);
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

	public boolean isPiercedNose() {
		return piercedNose;
	}

	public void setPiercedNose(boolean piercedNose) {
		this.piercedNose = piercedNose;
	}

	public boolean isPiercedLip() {
		return piercedLip;
	}

	public void setPiercedLip(boolean piercedLip) {
		this.piercedLip = piercedLip;
	}

	public Tongue getTongue() {
		return tongue;
	}

	public boolean isVirgin() {
		return virgin;
	}

	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}
}
