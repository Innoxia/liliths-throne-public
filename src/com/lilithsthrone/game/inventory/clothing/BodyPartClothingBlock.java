package com.lilithsthrone.game.inventory.clothing;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class BodyPartClothingBlock {

	private List<InventorySlot> blockedSlots;
	private Race race;
	private String description;
	private List<ItemTag> requiredTags;
	
	public BodyPartClothingBlock(List<InventorySlot> blockedSlots, Race race, String description, List<ItemTag> requiredTags) {
		this.blockedSlots = blockedSlots;
		this.race = race;
		this.description = description;
		
		if(requiredTags==null) {
			this.requiredTags = new ArrayList<>();
		} else {
			this.requiredTags = requiredTags;
		}
	}

	public List<InventorySlot> getBlockedSlots() {
		return blockedSlots;
	}

	public Race getRace() {
		return race;
	}

	public String getDescription() {
		return description;
	}

	public List<ItemTag> getRequiredTags() {
		return requiredTags;
	}
	
	
}
