package com.lilithsthrone.game.inventory;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.1
 * @version 0.4.5.5
 * @author Innoxia
 */
public enum ItemTag {

	CHEAT_ITEM, // Cheat items are hidden in the debug spawner, and are also not added to the Encyclopedia.
	SILLY_MODE, // Silly mode items only appear in shopkeepers inventories when silly mode is on.
	
	REMOVE_FROM_DEBUG_SPAWNER,
	NOT_FOR_SALE,
	
	REINDEER_GIFT, // Can be found in the presents that the reindeer sell (who appear in Dominion during winter months).
	SOLD_BY_RALPH, // Will also be used for any future consumable and miscellaneous item vendors.
	SOLD_BY_NYAN, // Clothing (is added to all clothing vendors)
	SOLD_BY_MONICA, // Clothing (is added to Elis only)
	SOLD_BY_KATE, // Jewellery
	SOLD_BY_FINCH, // BDSM and sex-related stuff
	SOLD_BY_VICKY, // Weapons

	// Items with these tags can randomly spawn in encounters in the commented area
	// Please note that due to legacy issues, clothing and weapons only use the 'DOMINION_ALLEYWAY_SPAWN' tag to determine whether or not it can randomly spawn in any area.
		// This will liekly be changed at a later date, so please use the appropriate area spawn tag for your clothing/weapon, even though it does nothing for now
	ALL_AREAS_SPAWN, // Every area in the game
	DOMINION_ALLEYWAY_SPAWN, // Dominion
	SUBMISSION_TUNNEL_SPAWN, // Submission (excluding Bat Caverns)
	BAT_CAVERNS_SPAWN, // Bat Caverns
	ELIS_ALLEYWAY_SPAWN, // Foloi Fields
	
	SPELL_BOOK,
	SPELL_SCROLL,
	ESSENCE,
	ATTRIBUTE_TF_ITEM,
	RACIAL_TF_ITEM,
	MISC_TF_ITEM, // Fetish or non-racial body part transformations
	BOOK,
	GIFT,
	ALCOHOLIC, // For easy detection of alcoholic items in some scenes
	
	// To mark consumables as containing caffeine, with the number representing the equivalent alcoholic level to be applied to spider-morphs
	// Only one of these should be applied to an item
	CAFFEINATED_005(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 5% to [style.boldAlcohol(intoxication level)] for [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_010(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 10% to [style.boldAlcohol(intoxication level)] for [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_015(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 15% to [style.boldAlcohol(intoxication level)] for [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_020(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 20% to [style.boldAlcohol(intoxication level)] for [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_025(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 25% to [style.boldAlcohol(intoxication level)] for [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_030(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 30% to [style.boldAlcohol(intoxication level)] for [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_040(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 40% to [style.boldAlcohol(intoxication level)] for [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_050(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 50% to [style.boldAlcohol(intoxication level)] for [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_075(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 75% to [style.boldAlcohol(intoxication level)] for [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_100(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 100% to [style.boldAlcohol(intoxication level)] for [style.boldSpider(spider-morphs)]"), false),
	
	CONTRABAND_LIGHT(// 'Restricted' items will not be bought by honest shopkeepers
			Util.newArrayListOfValues(
					"[style.colourDarkBlue(Restricted)]",
					"[style.colourMinorBad(Honest traders will not buy this)]"),
			false),
	CONTRABAND_MEDIUM(// 'Illegal' items will not be bought by honest shopkeepers and will be confiscated by Enforcers
			Util.newArrayListOfValues(
					"[style.colourDarkBlue(Illegal)]",
					"[style.colourMinorBad(Honest traders will not buy this)]",
					"[style.colourBad(Enforcers will confiscate this)]"),
			false),
	CONTRABAND_HEAVY(// 'Highly Illegal' items will not be bought by honest shopkeepers and Enforcers will arrest anyone possessing them
			Util.newArrayListOfValues(
					"[style.colourDarkBlue(Highly Illegal)]",
					"[style.colourMinorBad(Honest traders will not buy this)]",
					"[style.colourTerrible(Enforcers will arrest people possessing this)]"),
			false),
	
	//-------------- WEAPONS & CLOTHING --------------//
	
	/** Excludes this clothing from being randomly chosen to equip on an NPC in automatic outfit generation.
	 *  Also excludes the clothing from randomly spawning as tile-exploration loot.
	 *  This only really affects common-rarity clothing, as all clothing of a rarity higher than common are typically only able to be added to characters directly. */
	NO_RANDOM_SPAWN,
	
	UNIQUE_NO_NPC_EQUIP, // Prevents the clothing/weapon from being equipped onto an NPC. Only works on items with a 'UNIQUE' rarity level

	UNLOCKS_ENCOUNTER( // Special tag which has no effect other than displaying text for clothing stickers. Used for the 'rental mommy' tshirt, so look there for an example.
			Util.newArrayListOfValues(
			"[style.colourBlueLight(Can cause an encounter)]"),
			false),
	
	NIGHT_VISION_SELF( // Makes this clothing or weapon provide immunity to the darkness debuff for just the wearer while equipped
			Util.newArrayListOfValues(
					"[style.colourGood(Negates 'Darkness' effect)]"),
			false),
	
	NIGHT_VISION_AREA( // Makes this clothing or weapon provide immunity to the darkness debuff for all characters in the area in which the wearer is located while equipped
			Util.newArrayListOfValues(
					"[style.colourExcellent(Negates 'Darkness' effect for all nearby characters)]"),
			false),
	
	REVEALS_CONCEALABLE_SLOT, // If a piece of clothing has this tag, it will always be visible, even if another item of clothing is concealing its slot. (Used for spreader bar.)

	TRANSPARENT( // This item of clothing does not conceal any areas. Used for chastity cages & condoms (so penis is still visible). Could also be used for sheer clothing material.
			Util.newArrayListOfValues(
					"[style.colourSex(Cannot conceal any body parts)]"),
			false),

	WEAPON_FERAL_EQUIPPABLE( // Allows ferals to equip this weapon (as they cannot equip weapons by default)
			Util.newArrayListOfValues(
					"[style.colourFeral(Can be equipped by ferals)]"),
			false),
	
	WEAPON_BLADE, // Should be added to all weapons that use an arcane blade
	
	WEAPON_UNARMED, // Should be added to all weapons that should use unarmed damage calculations instead of melee
	
	DRESS, // For helping to generate clothing in CharacterUtils

	PROVIDES_KEY( // The person who equips this clothing will get an unlock key, making the unsealing cost 0
			Util.newArrayListOfValues(
					"[style.colourGood(Provides equipper with key)]"),
			false),
	
	UNENCHANTABLE(  // Prevents the player from enchanting this in the inventory menu.
			Util.newArrayListOfValues(
					"[style.colourBad(Unenchantable)]"),
			false),

	SPREADS_FEET( // Prevents double foot actions, like wrap-around footjobs
			Util.newArrayListOfValues(
					"[style.colourBad(Restricts sex actions)]"),
			false),

	MUFFLES_SPEECH( // Causes the wearer to not be able to talk. E.g. Ball gags
			Util.newArrayListOfValues(
					"[style.colourBad(Muffles speech)]"),
			false),

	BLOCKS_SIGHT( // Causes the wearer to suffer from 'blinded' status effect
			Util.newArrayListOfValues(
					"[style.colourBad(Blocks sight)]"),
			false),
	
	HINDERS_ARM_MOVEMENT( // Hinders the ability of the wearer to use their arms. E.g. Hand cuffs
			Util.newArrayListOfValues(
					"[style.colourBad(Hinders arm movement)]",
					"[style.colourTerrible(Blocks arm-wing flight)]"),
			false),

	HINDERS_LEG_MOVEMENT(  // Hinders the ability of the wearer to run or use their legs properly. E.g. Spreader bar
			Util.newArrayListOfValues(
					"[style.colourBad(Hinders leg movement)]",
					"[style.colourTerrible(Blocks non-flight combat escape)]"),
			false),

	PREVENTS_COMBAT_ESCAPE(  // Prevents the character from escaping from combat
			Util.newArrayListOfValues(
					"[style.colourTerrible(Prevents combat escape)]"),
			false),
	
	DISCARDED_WHEN_UNEQUIPPED( //  Makes the clothing be thrown away when unequipped. E.g. Condoms
			Util.newArrayListOfValues(
					"[style.colourMinorBad(Discarded when unequipped)]"),
			false),

	ENABLE_SEX_EQUIP( // Allows this clothing to be equipped during sex. E.g. Condoms or strapons
			Util.newArrayListOfValues(
					"[style.colourSex(Sex-equip enabled)]"),
			false),
	
	// Self-explanatory requirements in order to equip this clothing:
	REQUIRES_PENIS(
			Util.newArrayListOfValues(
					"[style.colourSex(Requires penis)]"),
			false),
	REQUIRES_NO_PENIS(
			Util.newArrayListOfValues(
					"[style.colourSex(Requires no penis)]"),
			false),
	REQUIRES_VAGINA(
			Util.newArrayListOfValues(
					"[style.colourSex(Requires vagina)]"),
			false),
	REQUIRES_NO_VAGINA(
			Util.newArrayListOfValues(
					"[style.colourSex(Requires no vagina)]"),
			false),
	REQUIRES_FUCKABLE_NIPPLES(
			Util.newArrayListOfValues(
					"[style.colourSex(Requires fuckable nipples)]"),
			false),
	
	// These 'FITS' tags are used to check for whether clothing is suitable for certain body parts. They should be pretty self-explanatory.
	FITS_HOOFS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourFeral(Only fits hoofs)]"),
			false),
	FITS_HOOFS(
			Util.newArrayListOfValues(
					"[style.colourFeral(Fits hoofs)]"),
			false),
	
	FITS_TALONS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourFeral(Only fits talons)]"),
			false),
	FITS_TALONS(
			Util.newArrayListOfValues(
					"[style.colourFeral(Fits talons)]"),
			false),
	
	FITS_FEATHERED_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Only fits feathered arm-wings)]"),
			false),
	FITS_FEATHERED_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Fits feathered arm-wings)]"),
			false),
	FITS_LEATHERY_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Only fits leathery arm-wings)]"),
			false),
	FITS_LEATHERY_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Fits leathery arm-wings)]"),
			false),
	FITS_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Only fits arm-wings)]"),
			false),
	FITS_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Fits arm-wings)]"),
			false),
	
	FITS_NON_BIPED_BODY_HUMANOID(
			Util.newArrayListOfValues(
					"[style.colourHuman(Fits humanoid parts of non-biped bodies)]"),
			false),
	FITS_TAUR_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Only fits quadrupedal bodies)]"),
			false),
	FITS_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Only fits long-tailed bodies)]"),false), //lamia, eels
	FITS_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Only fits tailed bodies)]"),false), //mermaids
	FITS_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Only fits arachnid bodies)]"),false), //spiders and scorpions
	FITS_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Only fits cephalopod bodies)]"),false), //octopuses and squids
	FITS_AVIAN_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Only fits avian bodies)]"),false), //bird-taurs
	
	// These tags are non-exclusive, so clothing with them can additionally be equipped by non-ferals:
	FITS_FERAL_ALL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Fits all feral bodies)]"),false), //All feral bodies can equip clothing marked with this tag.
	FITS_FERAL_QUADRUPED_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Fits quadrupedal feral bodies)]"),false), //Quadrupedal feral bodies can equip clothing marked with this tag
	FITS_FERAL_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Fits long-tailed feral bodies)]"),false), //Long-tailed feral bodies can equip clothing marked with this tag
	FITS_FERAL_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Fits tailed feral bodies)]"),false), //Tailed feral bodies can equip clothing marked with this tag
	FITS_FERAL_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Fits arachnid feral bodies)]"),false), //Arachnid feral bodies can equip clothing marked with this tag
	FITS_FERAL_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Fits cephalopod feral bodies)]"),false), //Cephalopod feral bodies can equip clothing marked with this tag
	FITS_FERAL_AVIAN_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Fits avian feral bodies)]"),false), //Avian feral bodies can equip clothing marked with this tag
	// These tags are exclusive, so clothing with them can ONLY be equipped by the corresponding body type (unless the clothing additionally has other permissive tags):
	ONLY_FITS_FERAL_ALL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Only fits feral bodies)]"),false),
	ONLY_FITS_FERAL_QUADRUPED_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Only fits quadrupedal feral bodies)]"),false),
	ONLY_FITS_FERAL_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Only fits long-tailed feral bodies)]"),false),
	ONLY_FITS_FERAL_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Only fits tailed feral bodies)]"),false),
	ONLY_FITS_FERAL_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Only fits arachnid feral bodies)]"),false),
	ONLY_FITS_FERAL_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Only fits cephalopod feral bodies)]"),false),
	ONLY_FITS_FERAL_AVIAN_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Only fits avian feral bodies)]"),false),
	
	
	RIGID_MATERIAL( // The clothing is made out of a rigid material, and as such, groping actions cannot be performed on it. Used for chastity cages/belts.
			Util.newArrayListOfValues(
					"[style.colourTerrible(Blocks groping actions)]"),
			false),
	
	PREVENTS_ERECTION_PHYSICAL( // Prevents the wearer from getting an erection during sex, by means of physically limiting the space into which the erection could take shape (i.e. chastity cages). As of 0.3.1, only affects descriptors.
			Util.newArrayListOfValues(
					"[style.colourTerrible(Prevents erection)]"),
			false),
	PREVENTS_ERECTION_OTHER( // Prevents the wearer from getting an erection during sex, by means other than physical limitations. As of 0.3.1, only affects descriptors.
			Util.newArrayListOfValues(
					"[style.colourTerrible(Prevents erection)]"),
			false),
	PREVENTS_ORGASM( // Prevents the user from orgasming.
			Util.newArrayListOfValues(
					"[style.colourTerrible(Prevents orgasm)]"),
			false),
	ALLOW_ORGASM( // Negates the effect of orgasm blocking items.
			Util.newArrayListOfValues(
					"[style.colourSex(Negates orgasm prevention)]"),
			false),
	
	// Sex-related clothing:

	/**<b>IMPORTANT</b> This tag should only ever be given to clothing going into the PENIS InventorySlot, as otherwise it will throw errors.*/
	CONDOM(true), // Gives this clothing condom behaviour
	
	CHOKER_SNAP( // Snaps (into wearer's inventory) if throat stretches.
			Util.newArrayListOfValues(
					"[style.colourSex(Snaps if throat bulges too much during sex)]"),
			true),
	
	CHASTITY( // Tags the clothing as being some form of chastity device, meaning that it will apply the 'CHASTITY_1' status effect when equipped
			Util.newArrayListOfValues(
					"[style.colourTerrible(Chastity device)]"),
			true),
	
	// To detect whether creampies should leak out or not:
	
	PLUGS_ANUS( // Counts as being inserted into the wearer's anus. E.g. butt plugs or anal beads
			Util.newArrayListOfValues(
					"[style.colourSex(Plugs asshole (does not get dirty from creampies))]"),
			true),
	SEALS_ANUS( // Counts as sealing, but not inserted into, the wearer's anus. E.g. Tape
			Util.newArrayListOfValues(
					"[style.colourSex(Seals asshole (does not get dirty from creampies))]"),
			true),
	
	PLUGS_VAGINA( // Counts as being inserted into the wearer's vagina. E.g. insertable dildo
			Util.newArrayListOfValues(
					"[style.colourSex(Plugs pussy (does not get dirty from creampies))]"),
			true),
	SEALS_VAGINA( // Counts as sealing, but not inserted into, the wearer's vagina. E.g. Tape
			Util.newArrayListOfValues(
					"[style.colourSex(Seals pussy (does not get dirty from creampies))]"),
			true) {
		@Override
		public List<String> getClothingTooltipAdditions() {
			if(Main.game.isUrethraEnabled()) {
				return Util.newArrayListOfValues("[style.colourSex(Seals pussy (including urethra))]");
			} else {
				return Util.newArrayListOfValues("[style.colourSex(Seals pussy)]");
			}
		}
	},
	
	PLUGS_NIPPLES( // Counts as being inserted into the wearer's nipples. E.g. insertable nipple-dildos
			Util.newArrayListOfValues(
					"[style.colourSex(Plugs nipples (does not get dirty from creampies))]"),
			true),
	SEALS_NIPPLES( // Counts as sealing, but not inserted into, the wearer's nipples. E.g. Pasties
			Util.newArrayListOfValues(
					"[style.colourSex(Seals nipples (does not get dirty from creampies))]"),
			true),
	
	MILKING_EQUIPMENT(
			Util.newArrayListOfValues(
					"[style.colourMilk(Milking equipment (drains creampies))]"),
			true),
	
	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	DILDO_SELF(
			Util.newArrayListOfValues(
					"[style.colourSub(Insertable)] [style.colourSex(dildo)]"),
			true),

	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	DILDO_OTHER(
			Util.newArrayListOfValues(
					"[style.colourDom(Wearable)] [style.colourSex(dildo)]"),
			true),

	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	ONAHOLE_SELF(
			Util.newArrayListOfValues(
					"[style.colourSex(Fuckable onahole)]"),
			true),

	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	ONAHOLE_OTHER(
			Util.newArrayListOfValues(
					"[style.colourSex(Wearable onahole)]"),
			true),
	;

	private List<String> clothingTooltipAdditions;
	private boolean sexToy;

	/**
	 * @param clothingTooltipAdditions The descriptions that should be appended to clothing tooltips for when this ItemTag is present on clothing.
	 * @param sexToy If true, then this item tag marks the associating clothing as a sex toy, which, if worn by a dom, is unable to be removed by subs in sex.
	 */
	private ItemTag(List<String> clothingTooltipAdditions, boolean sexToy) {
		this.clothingTooltipAdditions = clothingTooltipAdditions;
		this.sexToy = sexToy;
	}
	/**
	 * @param sexToy If true, then this item tag marks the associating clothing as a sex toy, which, if worn by a dom, is unable to be removed by subs in sex.
	 */
	private ItemTag(boolean sexToy) {
		this.clothingTooltipAdditions = null;
		this.sexToy = sexToy;
	}
	
	private ItemTag() {
		this.clothingTooltipAdditions = null;
		this.sexToy = false;
	}

	public List<String> getClothingTooltipAdditions() {
		if(clothingTooltipAdditions==null) {
			return new ArrayList<>();
		}
		return clothingTooltipAdditions;
	}
	
	/**
	 * @return true if this tag makes the clothing to which it is applied a sex toy, meaning that NPCs will not remove it during sex unless it is blocking the part they wish to access.
	 */
	public boolean isSexToy() {
		return sexToy;
	}
	
}
