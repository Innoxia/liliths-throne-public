package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.AssType;
import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.valueEnums.AssSize;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.HipSize;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.OrificeType;
import com.base.game.sex.Sex;
import com.base.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public class Ass implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private AssType type;
	private int assSize, hipSize, wetness, elasticity;
	private float capacity, stretchedCapacity;
	private boolean virgin, bleached;

	public Ass(AssType type, int size, int wetness, int capacity, int elasticity, boolean virgin) {
		this.type = type;
		assSize = size;
		hipSize = size;
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.elasticity = elasticity;
		this.virgin = virgin;
		bleached = false;
	}
	
	
	public String getAssholeName(GameCharacter owner, boolean withDescriptor) {
		if(withDescriptor)
			return (getAssholeDescriptor(owner).length() > 0 ? getAssholeDescriptor(owner) + " " : "") + type.getAssholeName();
		else
			return type.getAssholeName();
	}
	
	public String getAssholeDescriptor(GameCharacter owner) {
		// Randomly give a capacity, wetness or type-specific descriptor:
		
		String wetnessDescriptor = Wetness.valueOf(wetness).getDescriptor();
		if(Main.game.isInSex()) {
			if(owner.isPlayer() && !Sex.getWetOrificeTypes().get(OrificeType.ANUS_PLAYER).isEmpty()) {
				wetnessDescriptor = "wet";
			} else if(!owner.isPlayer() && !Sex.getWetOrificeTypes().get(OrificeType.ANUS_PARTNER).isEmpty()) {
				wetnessDescriptor = "wet";
			}
		}
		
		return UtilText.returnStringAtRandom(
				type.getAssholeDescriptor(),
				
				wetnessDescriptor,
				
				Capacity.getCapacityFromValue(capacity).getDescriptor());
		
	}

	@Override
	public AssType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (AssType) type;
	}

	// Ass size:

	public AssSize getAssSize() {
		return AssSize.getAssSizeFromInt(assSize);
	}

	/**
	 * Manually sets assSize attribute. Value is bound to >=0 &&
	 * <=AssSize.SEVEN_GIGANTIC.getValue()
	 * 
	 * @param assSize
	 *            Value to set assSize to.
	 * @return True if assSize was changed.
	 */
	public boolean setAssSize(int assSize) {
		if (this.assSize == assSize)
			return false;

		if (assSize < 0) {
			if (this.assSize == 0)
				return false;

			this.assSize = 0;
			return true;
		}
		if (assSize > AssSize.SEVEN_GIGANTIC.getValue()) {
			if (this.assSize == AssSize.SEVEN_GIGANTIC.getValue())
				return false;

			this.assSize = AssSize.SEVEN_GIGANTIC.getValue();
			return true;
		}

		this.assSize = assSize;
		return true;
	}

	// Hip size:

	public HipSize getHipSize() {
		return HipSize.getHipSizeFromInt(hipSize);
	}

	/**
	 * Manually sets hipSize attribute. Value is bound to >=0 &&
	 * <=HipSize.SEVEN_ABSURDLY_WIDE.getValue()
	 * 
	 * @param hipSize
	 *            Value to set hipSize to.
	 * @return True if hipSize was changed.
	 */
	public boolean setHipSize(int hipSize) {
		if (this.hipSize == hipSize)
			return false;

		if (hipSize < 0) {
			if (this.hipSize == 0)
				return false;

			this.hipSize = 0;
			return true;
		}
		if (hipSize > HipSize.SEVEN_ABSURDLY_WIDE.getValue()) {
			if (this.hipSize == HipSize.SEVEN_ABSURDLY_WIDE.getValue())
				return false;

			this.hipSize = HipSize.SEVEN_ABSURDLY_WIDE.getValue();
			return true;
		}

		this.hipSize = hipSize;
		return true;
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

	public boolean isVirgin() {
		return virgin;
	}

	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}

	public boolean isBleached() {
		return bleached;
	}

	public void setBleached(boolean bleached) {
		this.bleached = bleached;
	}

}