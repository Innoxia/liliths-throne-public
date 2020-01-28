package com.lilithsthrone.game.inventory;

import java.util.List;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.1
 * @version 0.3.6
 * @author Innoxia
 */
public enum ItemTag {

	HIDDEN_IN_DEBUG_SPAWNER(false),
	
	REMOVE_FROM_DEBUG_SPAWNER(false),

	NOT_FOR_SALE(false),
	
	REINDEER_GIFT(false), // Can be found in the presents that the reindeer sell (who appear in Dominion during winter months).
	SOLD_BY_RALPH(false), // Will also be used for any future consumable and miscellaneous item vendors.
	SOLD_BY_NYAN(false), // Clothing
	SOLD_BY_KATE(false), // Jewellery
	SOLD_BY_FINCH(false), // BDSM and sex-related stuff
	SOLD_BY_VICKY(false), // Weapons

	SPELL_BOOK(false),
	SPELL_SCROLL(false),
	ESSENCE(false),
	ATTRIBUTE_TF_ITEM(false),
	RACIAL_TF_ITEM(false),
	MISC_TF_ITEM(false), // Fetish or non-racial body part transformations
	BOOK(false), 
	GIFT(false),
	DOMINION_ALLEYWAY_SPAWN(false),
	SUBMISSION_TUNNEL_SPAWN(false),
	BAT_CAVERNS_SPAWN(false),
	
	//-------------- WEAPONS & CLOTHING --------------//

	REVEALS_CONCEALABLE_SLOT(false), // If a piece of clothing has this tag, it will always be visible, even if another item of clothing is concealing its slot. (Used for spreader bar.)

	TRANSPARENT( // This item of clothing does not conceal any areas. Used for chastity cages & condoms (so penis is still visible). Could also be used for sheer clothing material.
			Util.newArrayListOfValues(
					"[style.colourSex(Cannot conceal any body parts)]"),
			false),
	
	WEAPON_BLADE(false), // Should be added to all weapons that use an arcane blade
	
	WEAPON_UNARMED(false), // Should be added to all weapons that should use unarmed damage calculations instead of melee
	
	DRESS(false), // For helping to generate clothing in CharacterUtils

	PROVIDES_KEY( // The person whi equips this clothing will get an unlock key, making the removal cost for jinxes equal to 0
			Util.newArrayListOfValues(
					"[style.colourGood(Provides equipper with key)]"),
			false),
	
	SPREADS_FEET( // Prevents double foot actions, like wrap-around footjobs
			Util.newArrayListOfValues(
					"[style.colourBad(Restricts sex actions)]"),
			false),

	MUFFLES_SPEECH( // Causes the wearer to not be able to talk. E.g. Ball gags
			Util.newArrayListOfValues(
					"[style.colourBad(Muffles speech)]"),
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
					"[style.colourBestial(Only fits hoofs)]"),
			false),
	FITS_HOOFS(
			Util.newArrayListOfValues(
					"[style.colourBestial(Fits hoofs)]"),
			false),
	
	FITS_TALONS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourBestial(Only fits talons)]"),
			false),
	FITS_TALONS(
			Util.newArrayListOfValues(
					"[style.colourBestial(Fits talons)]"),
			false),
	
	FITS_FEATHERED_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourBestial(Only fits feathered arm-wings)]"),
			false),
	FITS_FEATHERED_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourBestial(Fits feathered arm-wings)]"),
			false),
	FITS_LEATHERY_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourBestial(Only fits leathery arm-wings)]"),
			false),
	FITS_LEATHERY_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourBestial(Fits leathery arm-wings)]"),
			false),
	FITS_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourBestial(Only fits arm-wings)]"),
			false),
	FITS_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourBestial(Fits arm-wings)]"),
			false),
	
	FITS_NON_BIPED_BODY_HUMANOID(
			Util.newArrayListOfValues(
					"[style.colourHuman(Fits humanoid parts of non-biped bodies)]"),
			false),
	FITS_TAUR_BODY(
			Util.newArrayListOfValues(
					"[style.colourBestial(Only fits taur bodies)]"),
			false),
	FITS_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourBestial(Only fits long-tailed bodies)]"),false), //lamia, eels
	FITS_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourBestial(Only fits tailed bodies)]"),false), //mermaids
	FITS_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colourBestial(Only fits arachnid bodies)]"),false), //spiders and scorpions
	FITS_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colourBestial(Only fits cephalopod bodies)]"),false), //octopuses and squids

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
	
	// Sex-related clothing:

	/**<b>IMPORTANT</b> This tag should only ever be given to clothing going into the PENIS InventorySlot, as otherwise it will throw errors.*/
	CONDOM(true), // Gives this clothing condom behaviour
	
	
	// To detect whether creampies should leak out or not:
	
	PLUGS_ANUS( // Counts as being inserted into the wearer's anus. E.g. butt plugs or anal beads
			Util.newArrayListOfValues(
					"[style.colourSex(Plugs asshole (does not get dirty from creampies))]"),
			true),
	SEALS_ANUS( // Counts as sealing(false), but not inserted into(false), the wearer's anus. E.g. Tape
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

	
	DILDO_TINY( // 3 inches
			Util.newArrayListOfValues(
					"[style.colourSex(3-inch dildo)]"),
			true),
	DILDO_AVERAGE( // 6 inches
			Util.newArrayListOfValues(
					"[style.colourSex(6-inch dildo)]"),
			true),
	DILDO_LARGE( // 10 inches
			Util.newArrayListOfValues(
					"[style.colourSex(10-inch dildo)]"),
			true),
	DILDO_HUGE( // 14 inches
			Util.newArrayListOfValues(
					"[style.colourSex(14-inch dildo)]"),
			true),
	DILDO_ENORMOUS( // 18 inches
			Util.newArrayListOfValues(
					"[style.colourSex(18-inch dildo)]"),
			true),
	DILDO_GIGANTIC( // 22 inches
			Util.newArrayListOfValues(
					"[style.colourSex(22-inch dildo)]"),
			true),
	DILDO_STALLION( // 32 inches
			Util.newArrayListOfValues(
					"[style.colourSex(32-inch dildo)]"),
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

	public List<String> getClothingTooltipAdditions() {
		return clothingTooltipAdditions;
	}

	public boolean isSexToy() {
		return sexToy;
	}
	
}
