package com.lilithsthrone.game.inventory.clothing;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.7
 * @version 0.2.7
 * @author Innoxia
 */
public enum PresetConcealmentLists {

	NONE(new ArrayList<>()),
	
	CONCEALED_PARTIAL_TORSO(Util.newArrayListOfValues(
			InventorySlot.STOMACH,
			InventorySlot.CHEST,
			InventorySlot.NIPPLE,
			InventorySlot.PIERCING_NIPPLE,
			InventorySlot.PIERCING_STOMACH)),

	CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE(Util.newArrayListOfValues(
			InventorySlot.CHEST,
			InventorySlot.NIPPLE,
			InventorySlot.PIERCING_NIPPLE)),

	CONCEALED_STOMACH(Util.newArrayListOfValues(
			InventorySlot.PIERCING_STOMACH)),

	CONCEALED_BREASTS(Util.newArrayListOfValues(
			InventorySlot.NIPPLE,
			InventorySlot.PIERCING_NIPPLE)),

	CONCEALED_FULL_TORSO(Util.newArrayListOfValues(
			InventorySlot.TORSO_UNDER,
			InventorySlot.STOMACH,
			InventorySlot.CHEST,
			InventorySlot.NIPPLE,
			InventorySlot.PIERCING_NIPPLE,
			InventorySlot.PIERCING_STOMACH)),

	CONCEALED_GENITALS(Util.newArrayListOfValues(
			InventorySlot.VAGINA,
			InventorySlot.ANUS,
			InventorySlot.PENIS,
			InventorySlot.PIERCING_PENIS,
			InventorySlot.PIERCING_VAGINA)),

	CONCEALED_GENITALS_NO_ANUS(Util.newArrayListOfValues(
			InventorySlot.VAGINA,
			InventorySlot.PENIS,
			InventorySlot.PIERCING_PENIS,
			InventorySlot.PIERCING_VAGINA)),

	CONCEALED_GROIN(Util.newArrayListOfValues(
			InventorySlot.GROIN,
			InventorySlot.VAGINA,
			InventorySlot.ANUS,
			InventorySlot.PENIS,
			InventorySlot.PIERCING_PENIS,
			InventorySlot.PIERCING_VAGINA)),

	CONCEALED_TAUR(Util.newArrayListOfValues(
			InventorySlot.STOMACH,
			InventorySlot.PIERCING_STOMACH,
			InventorySlot.GROIN,
			InventorySlot.VAGINA,
			InventorySlot.ANUS,
			InventorySlot.PENIS,
			InventorySlot.PIERCING_PENIS,
			InventorySlot.PIERCING_VAGINA)),
	
	CONCEALED_DRESS_FRONT_FULL(Util.newArrayListOfValues(
			InventorySlot.STOMACH,
			InventorySlot.CHEST,
			InventorySlot.NIPPLE,
			InventorySlot.PIERCING_NIPPLE,
			InventorySlot.PIERCING_STOMACH,
			InventorySlot.GROIN,
			InventorySlot.VAGINA,
			InventorySlot.PENIS,
			InventorySlot.PIERCING_PENIS,
			InventorySlot.PIERCING_VAGINA)),

	CONCEALED_UNZIPS_GROIN(Util.newArrayListOfValues(
			InventorySlot.GROIN,
			InventorySlot.PENIS,
			InventorySlot.PIERCING_PENIS)),

	CONCEALED_ANKLES_FROM_TROUSERS(Util.newArrayListOfValues(
			InventorySlot.ANKLE,
			InventorySlot.SOCK));
	
	private List<InventorySlot> presetInventorySlotList;
	
	public List<InventorySlot> getPresetInventorySlotList() {
		return presetInventorySlotList;
	}

	private PresetConcealmentLists(List<InventorySlot> presetInventorySlotList) {
		this.presetInventorySlotList = presetInventorySlotList;
	}

	
	
}
