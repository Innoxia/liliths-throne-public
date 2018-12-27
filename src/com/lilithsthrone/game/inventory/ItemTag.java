package com.lilithsthrone.game.inventory;

/**
 * @since 0.2.1
 * @version 0.2.12
 * @author Innoxia
 */
public enum ItemTag {

	REMOVE_FROM_DEBUG_SPAWNER(false),

	NOT_FOR_SALE(false),
	
	REINDEER_GIFT(false),
	SOLD_BY_RALPH(false),
	SOLD_BY_NYAN(false),
	SOLD_BY_KATE(false),
	SOLD_BY_FINCH(false),
	SOLD_BY_VICKY(false),
	
	WEAPON_BLADE(false), // Should be added to all weapons that use an arcane blade
	
	DRESS(false), // For helping to generate clothing in CharacterUtils

	SPREADS_FEET(false), // Prevents double foot actions(false), like wrap-around footjobs

	MUFFLES_SPEECH(false), // Causes the wearer to not be able to talk. E.g. Ball gags

	HINDERS_ARM_MOVEMENT(false), // Hinders the ability of the wearer to use their arms. E.g. Hand cuffs

	HINDERS_LEG_MOVEMENT(false), // Hinders the ability of the wearer to run or use their legs properly. E.g. Spreader bar

	DISCARDED_WHEN_UNEQUIPPED(false), //  Makes the clothing be thrown away when unequipped. E.g. Condoms

	ENABLE_SEX_EQUIP(false), // Allows this clothing to be equipped during sex. E.g. Condoms or strapons
	
	// To detect whether creampies should leak out or not:
	PLUGS_ANUS(true), // Counts as being inserted into the wearer's anus. E.g. butt plugs or anal beads
	SEALS_ANUS(true), // Counts as sealing(false), but not inserted into(false), the wearer's anus. E.g. Tape
	
	PLUGS_VAGINA(true), // Counts as being inserted into the wearer's vagina. E.g. insertable dildo
	SEALS_VAGINA(true), // Counts as sealing(false), but not inserted into(false), the wearer's vagina. E.g. Tape
	
	PLUGS_NIPPLES(true), // Counts as being inserted into the wearer's nipples. E.g. insertable nipple-dildos
	SEALS_NIPPLES(true), // Counts as sealing(false), but not inserted into(false), the wearer's nipples. E.g. Pasties
	
	// Self-explanatory requirements in order to equip this clothing:
	REQUIRES_PENIS(false),
	REQUIRES_NO_PENIS(false),
	REQUIRES_VAGINA(false),
	REQUIRES_NO_VAGINA(false),
	REQUIRES_FUCKABLE_NIPPLES(false),
	
	REVEALS_CONCEALABLE_SLOT(false), // If a piece of clothing has this tag(false), it will always be visible(false), even if another item of clothing is concealing its slot. (Used for spreader bar.)
	
	DILDO_TINY(true), // 3 inches
	DILDO_AVERAGE(true), // 6 inches
	DILDO_LARGE(true), // 10 inches
	DILDO_HUGE(true), // 14 inches
	DILDO_ENORMOUS(true), // 18 inches
	DILDO_GIGANTIC(true), // 22 inches
	DILDO_STALLION(true), // 32 inches
	
	SPELL_BOOK(false),
	SPELL_SCROLL(false),
	ESSENCE(false),
	ATTRIBUTE_TF_ITEM(false),
	RACIAL_TF_ITEM(false),
	MISC_TF_ITEM(false),
	BOOK(false), 
	GIFT(false),
	DOMINION_ALLEYWAY_SPAWN(false),
	SUBMISSION_TUNNEL_SPAWN(false),
	BAT_CAVERNS_SPAWN(false);
	
	/** If true, then this item tag marks the associating clothing as a sex toy, which, if worn by a dom, is unable to be removed by subs in sex. */
	private boolean sexToy;

	private ItemTag(boolean sexToy) {
		this.sexToy = sexToy;
	}

	public boolean isSexToy() {
		return sexToy;
	}
	
}
