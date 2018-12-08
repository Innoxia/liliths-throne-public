package com.lilithsthrone.game.inventory;

/**
 * @since 0.2.1
 * @version 0.2.12
 * @author Innoxia
 */
public enum ItemTag {

	REMOVE_FROM_DEBUG_SPAWNER,

	NOT_FOR_SALE,
	
	REINDEER_GIFT,
	SOLD_BY_RALPH,
	SOLD_BY_NYAN,
	SOLD_BY_KATE,
	SOLD_BY_FINCH,
	SOLD_BY_VICKY,
	
	WEAPON_BLADE, // Should be added to all weapons that use an arcane blade
	
	DRESS, // For helping to generate clothing in CharacterUtils

	SPREADS_FEET, // Prevents double foot actions, like wrap-around footjobs

	MUFFLES_SPEECH, // Causes the wearer to not be able to talk. E.g. Ball gags

	HINDERS_ARM_MOVEMENT, // Hinders the ability of the wearer to use their arms. E.g. Hand cuffs

	HINDERS_LEG_MOVEMENT, // Hinders the ability of the wearer to run or use their legs properly. E.g. Spreader bar

	DISCARDED_WHEN_UNEQUIPPED, //  Makes the clothing be thrown away when unequipped. E.g. Condoms

	ENABLE_SEX_EQUIP, // Allows this clothing to be equipped during sex. E.g. Condoms or strapons
	
	// To detect whether creampies should leak out or not:
	PLUGS_ANUS, // Counts as being inserted into the wearer's anus. E.g. butt plugs or anal beads
	SEALS_ANUS, // Counts as sealing, but not inserted into, the wearer's anus. E.g. Tape
	
	PLUGS_VAGINA, // Counts as being inserted into the wearer's vagina. E.g. insertable dildo
	SEALS_VAGINA, // Counts as sealing, but not inserted into, the wearer's vagina. E.g. Tape
	
	PLUGS_NIPPLES, // Counts as being inserted into the wearer's nipples. E.g. insertable nipple-dildos
	SEALS_NIPPLES, // Counts as sealing, but not inserted into, the wearer's nipples. E.g. Pasties
	
	// Self-explanatory requirements in order to equip this clothing:
	REQUIRES_PENIS,
	REQUIRES_NO_PENIS,
	REQUIRES_VAGINA,
	REQUIRES_NO_VAGINA,
	REQUIRES_FUCKABLE_NIPPLES,
	
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
