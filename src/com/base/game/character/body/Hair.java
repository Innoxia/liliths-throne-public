package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.BodyPartTypeInterface;
import com.base.game.character.body.valueEnums.HairLength;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Hair implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private BodyCoveringType type;
	private int length;

	public Hair(BodyCoveringType type, int length) {
		this.type = type;
		this.length = length;
	}

	@Override
	public BodyCoveringType getType() {
		return type;
	}

	@Override
	public void setType(BodyPartTypeInterface type) {
		this.type = (BodyCoveringType) type;
	}

	public HairLength getLength() {
		return HairLength.getHairLengthFromInt(length);
	}

	public int getRawLengthValue() {
		return length;
	}

	/**
	 * Sets the length value. Value is bound to >=0 &&
	 * <=HairLength.SEVEN_TO_FLOOR.getMaximumValue()
	 * 
	 * @param length
	 *            Value to set length to.
	 * @return True if length was changed.
	 */
	public boolean setLength(int length) {
		if (this.length == length)
			return false;

		if (length < 0) {
			if (this.length == 0)
				return false;

			this.length = 0;
			return true;
		}
		if (length > HairLength.SEVEN_TO_FLOOR.getMaximumValue()) {
			if (this.length == HairLength.SEVEN_TO_FLOOR.getMaximumValue())
				return false;

			this.length = HairLength.SEVEN_TO_FLOOR.getMaximumValue();
			return true;
		}

		this.length = length;
		return true;
	}
}
