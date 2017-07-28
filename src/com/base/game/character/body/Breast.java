package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.types.BreastType;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.Lactation;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.68
 * @author Innoxia
 */
public class Breast implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private BreastType type;
	private int size, lactation, rows, elasticity;
	private float capacity, stretchedCapacity;
	private boolean pierced, virgin;

	/**
	 * @param size
	 *            in inches from bust to underbust using the UK system.
	 * @param lactation
	 *            in mL.
	 */
	public Breast(BreastType type, int size, int lactation, int rows, boolean pierced, int capacity, int elasticity, boolean virgin) {
		this.type = type;
		this.size = size;
		this.lactation = lactation;
		this.rows = rows;
		this.pierced = pierced;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.elasticity = elasticity;
		this.virgin = virgin;
	}
	
	public String getNippleName(boolean withDescriptor) {
		if(withDescriptor)
			return (getNippleDescriptor().length() > 0 ? getNippleDescriptor() + " " : "") + type.getNippleName();
		else
			return type.getNippleName();
	}
	
	public String getNippleNameSingular() {
		return type.getNippleNameSingular();
	}
	
	public String getNippleDescriptor() {
		String descriptor[];
		
		// Randomly give a capacity, wetness or type-specific descriptor:
		switch(Util.random.nextInt(2)){
			case 0:
				descriptor = new String[]{type.getNippleDescriptor()};
				break;
			default:
				if(capacity>0)
					descriptor = new String[]{Capacity.getCapacityFromValue(capacity).getDescriptor()};
				else
					descriptor = new String[]{type.getNippleDescriptor()};
				break;
		}
		
		return descriptor[Util.random.nextInt(descriptor.length)];
	}

	public String getDeterminer(GameCharacter gc) {
		if(gc.getBreastRows()==1) {
			return "a pair of";
		} else if(gc.getBreastRows()==2) {
			return "two pairs of";
		} else {
			return "three pairs of";
		}
	}

	public String getLactationDescription() {
		if (lactation <= Lactation.ZERO_NONE.getMaximumValue())
			return " aren't producing any " + getMilkName();
		else
			return " are producing " + getLactation().getDescriptor() + " " + getMilkName() + ", averaging about " + lactation + "mL each time you are milked.";
	}

	@Override
	public BreastType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (BreastType) type;
	}


	// Size:

	public CupSize getSize() {
		return CupSize.getCupSizeFromInt(size);
	}

	public int getRawSizeValue() {
		return size;
	}

	/**
	 * Sets the raw size value. Value is bound to >=0 &&
	 * <=CupSize.MAXIMUM.getMeasurement()
	 * 
	 * @param size
	 *            Value to set size to.
	 * @return True if size was changed.
	 */
	public boolean setSize(int size) {
		if (this.size == size)
			return false;

		if (size < 0) {
			if (this.size == 0)
				return false;

			this.size = 0;
			return true;
		}
		if (size > CupSize.MAXIMUM.getMeasurement()) {
			if (this.size == CupSize.MAXIMUM.getMeasurement())
				return false;

			this.size = CupSize.MAXIMUM.getMeasurement();
			return true;
		}

		this.size = size;
		return true;
	}

	// Lactation:

	public Lactation getLactation() {
		return Lactation.getLactationFromInt(lactation);
	}

	public int getRawLactationValue() {
		return lactation;
	}

	/**
	 * Sets the lactation. Value is bound to >=0 &&
	 * <=Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()
	 * 
	 * @param lactation
	 *            Value to set lactation to.
	 * @return True if lactation was changed.
	 */
	public boolean setLactation(int lactation) {
		if (this.lactation == lactation)
			return false;

		if (lactation < 0) {
			if (this.lactation == 0)
				return false;

			this.lactation = 0;
			return true;
		}
		if (lactation > Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()) {
			if (this.lactation == Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue())
				return false;

			this.lactation = Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue();
			return true;
		}

		this.lactation = lactation;
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

	public boolean isVirgin() {
		return virgin;
	}

	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}

	public String getMilkName() {
		return type.getMilkName();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		if (rows <= 0)
			this.rows = 1;
		else if (rows > 3)
			this.rows = 3;
		else
			this.rows = rows;
	}

	public boolean isPierced() {
		return pierced;
	}

	public void setPierced(boolean pierced) {
		this.pierced = pierced;
	}

	public boolean isFuckable() {
		return capacity > 0 && size > CupSize.C.getMeasurement();
	}

}
