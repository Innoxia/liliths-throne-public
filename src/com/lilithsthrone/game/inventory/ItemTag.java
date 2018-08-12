package com.lilithsthrone.game.inventory;

/**
 * @since 0.2.1
 * @version 0.2.10
 * @author Innoxia
 */
public enum ItemTag {

	REMOVE_FROM_DEBUG_SPAWNER,

	NOT_FOR_SALE,
	
	REINDEER_GIFT,
	SOLD_BY_NYAN,
	SOLD_BY_KATE,
	SOLD_BY_FINCH,
	SOLD_BY_VICKY,
	
	DRESS, // For helping to generate clothing in CharacterUtils

	SPREADS_FEET, // Prevents double foot actions, like wrap-around footjobs
	
	// To detect whether creampies should leak out or not:
	PLUGS_ANUS,
	PLUGS_VAGINA,
	PLUGS_NIPPLES,
	
	REQUIRES_PENIS,
	REQUIRES_NO_PENIS,
	REQUIRES_VAGINA,
	REQUIRES_NO_VAGINA,
	
	REVEALS_CONCEALABLE_SLOT, // If a piece of clothing has this tag, it will always be visible, even if another item of clothing is concealing its slot. (Used for spreader bar.)
	
	DILDO_TINY, // 3 inches
	DILDO_AVERAGE, // 6 inches
	DILDO_LARGE, // 10 inches
	DILDO_HUGE, // 14 inches
	DILDO_ENORMOUS, // 18 inches
	DILDO_GIGANTIC, // 22 inches
	DILDO_STALLION, // 32 inches
	
	SPELL_BOOK,
	SPELL_SCROLL,
	ESSENCE,
	ATTRIBUTE_TF_ITEM,
	RACIAL_TF_ITEM,
	MISC_TF_ITEM,
	BOOK, 
	GIFT,
	DOMINION_ALLEYWAY_SPAWN,
	SUBMISSION_TUNNEL_SPAWN,
	BAT_CAVERNS_SPAWN;
	
}
