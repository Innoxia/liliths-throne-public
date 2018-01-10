package com.lilithsthrone.game.sex;

import java.util.List;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.97
 * @version 0.1.97
 * @author Innoxia
 */
public enum SexPositionNew {
	
	DOGGY_STYLE("Doggy-style",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.DOGGY_ON_ALL_FOURS))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.DOGGY_BEHIND), new ListValue<>(SexPositionSlot.DOGGY_BEHIND_ORAL))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.DOGGY_INFRONT), new ListValue<>(SexPositionSlot.DOGGY_INFRONT_ANAL))))) {
		@Override
		public String getDescription() {
			return null;
		}
	};
	
	private String name;
	/**Key is role position. Value is list of all slots that this slot can switch to.*/
	private List<List<SexPositionSlot>> availableSlots;
	
	private SexPositionNew(String name, List<List<SexPositionSlot>> availableSlots) {
		this.name = name;
		this.availableSlots = availableSlots;
	}
	
	public String getName() {
		return name;
	}

	public List<List<SexPositionSlot>> getAvailableSlots() {
		return availableSlots;
	}

	public abstract String getDescription();
	
	public int getMaximumSlots() {
		return availableSlots.size();
	}
}
