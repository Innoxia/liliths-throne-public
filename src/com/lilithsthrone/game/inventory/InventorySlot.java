package com.lilithsthrone.game.inventory;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.2.6
 * @author Innoxia
 */
public enum InventorySlot {

	// HEAD:
	
	/** Clothing slot "head". Used for headgear.<br/>
	 *  Tattoo slot "head".*/
	HEAD(40, "head", false, false, "head"),

	/** Clothing slot "eyes". Used for glasses.<br/>
	 *  Tattoo slot "upper face".*/
	EYES(50, "eyes", false, false, "upper face"),

	/** Clothing slot "hair". Used for ribbons and hairbands.<br/>
	 *  Tattoo slot "ears".*/
	HAIR(20, "hair", false, false, "ears"),

	/** Clothing slot "mouth". Used for ballgags.<br/>
	 *  Tattoo slot "lower face".*/
	MOUTH(10, "mouth", false, false, "lower face"),

	/** Clothing slot "neck". Used for necklaces and scarfs.<br/>
	 *  Tattoo slot "neck".*/
	NECK(30, "neck", false, false, "neck"), // Necklaces
	
	
	// TORSO:

	/** Clothing slot "over-torso". Used for coats.<br/>
	 *  Tattoo slot "upper back".*/
	TORSO_OVER(50, "over-torso", false, false, "upper back"),

	/** Clothing slot "torso". Used for shirts.<br/>
	 *  Tattoo slot "upper face".*/
	TORSO_UNDER(40, "torso", true, false, "lower back"),

	/** Clothing slot "chest". Used for bras.<br/>
	 *  Tattoo slot "chest".*/
	CHEST(10, "chest", true, false, "chest"),

	/** Clothing slot "nipples". Used for nipple shields, plugs.<br/>
	 *  Tattoo slot "nipples".*/
	NIPPLE(5, "nipples", false, false, "nipples"),

	/** Clothing slot "stomach". Used for corsets.<br/>
	 *  Tattoo slot "stomach".*/
	STOMACH(10, "stomach", false, false, "stomach"),

	
	// HAND:

	/** Clothing slot "hands". Used for gloves.<br/>
	 *  Tattoo slot "forearms".*/
	HAND(20, "hands", false, false, "forearms"),

	/** Clothing slot "wrists". Used for bracelets.<br/>
	 *  Tattoo slot "upper arms".*/
	WRIST(30, "wrists", false, false, "upper arms"),

	/** Clothing slot "fingers". Used for rings.<br/>
	 *  Tattoo slot "hands".*/
	FINGER(30, "fingers", false, false, "hands"),

	/** Clothing slot "hips". Used for belts.<br/>
	 *  Tattoo slot "hips".*/
	HIPS(40, "hips", false, false, "hips"),

//	/** Clothing slot "hips-under". Used for suspender belts.<br/>
//	 *  Tattoo slot "".*/
//	HIPS_UNDER(20, "hips-under", false, false), // suspender belts

	/** Clothing slot "anus". Used for plugs.<br/>
	 *  Tattoo slot "ass".*/
	ANUS(0, "anus", false, false, "ass"),

	
	// LEG & FOOT:

	/** Clothing slot "legs". Used for trousers.<br/>
	 *  Tattoo slot "upper leg".*/
	LEG(30, "legs", true, false, "upper leg"),

	/** Clothing slot "groin". Used for underwear.<br/>
	 *  Tattoo slot "lower abdomen".*/
	GROIN(10, "groin", true, false, "lower abdomen"),

	/** Clothing slot "feet". Used for shoes.<br/>
	 *  Tattoo slot "feet".*/
	FOOT(40, "feet", true, false, "feet"),

	/** Clothing slot "calves". Used for socks.<br/>
	 *  Tattoo slot "lower leg".*/
	SOCK(10, "calves", true, false, "lower leg"),

	/** Clothing slot "ankles". Used for bracelets.<br/>
	 *  Tattoo slot "ankles".*/
	ANKLE(50, "ankles", false, false, "ankles"),
	
	
	// OPTIONAL EXTRAS:

	/** Clothing slot "horns". Used for horn decorations.<br/>
	 *  Tattoo slot "horns".*/
	HORNS(50, "horns", false, false, "horns"),

	/** Clothing slot "wings". Used for wing decorations.<br/>
	 *  Tattoo slot "wings".*/
	WINGS(50, "wings", false, false, "wings"),

	/** Clothing slot "tail". Used for tail decorations.<br/>
	 *  Tattoo slot "tail".*/
	TAIL(50, "tail", false, false, "tail"),

	/** Clothing slot "penis". Used for cock socks, cages, and plugs.<br/>
	 *  Tattoo slot "penis".*/
	PENIS(0, "penis", false, false, "penis"), // 

	/** Clothing slot "vagina". Used for plugs.<br/>
	 *  Tattoo slot "vagina".*/
	VAGINA(0, "vagina", false, false, "vagina"),

	// PIERCING:
	PIERCING_EAR(0, "ear piercing", false, true, null),
	PIERCING_NOSE(0, "nose piercing", false, true, null),
	PIERCING_TONGUE(0, "tongue piercing", false, true, null),
	PIERCING_LIP(0, "lip piercing", false, true, null),
	PIERCING_STOMACH(0, "navel piercing", false, true, null),
	PIERCING_NIPPLE(0, "nipple piercing", false, true, null),
	PIERCING_VAGINA(0, "vaginal piercing", false, true, null),
	PIERCING_PENIS(0, "cock piercing", false, true, null),

	// EQUIPPABLE:
	WEAPON_MAIN(0, "primary weapon", false, false, null),
	WEAPON_OFFHAND(0, "secondary weapon", false, false, null);

	private int zLayer;
	private String name;
	private String tattooSlotName;
	private boolean jewellery, coreClothing;
	private static List<InventorySlot> clothingSlots;
	private static List<InventorySlot> piercingSlots;

	static {
		clothingSlots = new ArrayList<>();
		piercingSlots = new ArrayList<>();
		
		clothingSlots.add(HEAD);
		clothingSlots.add(EYES);
		clothingSlots.add(HAIR);
		clothingSlots.add(MOUTH);
		clothingSlots.add(NECK);

		clothingSlots.add(TORSO_OVER);
		clothingSlots.add(TORSO_UNDER);
		clothingSlots.add(CHEST);
		clothingSlots.add(NIPPLE);
		clothingSlots.add(STOMACH);
		
		clothingSlots.add(HAND);
		clothingSlots.add(WRIST);
		clothingSlots.add(FINGER);
		clothingSlots.add(HIPS);
		clothingSlots.add(ANUS);
		
		clothingSlots.add(LEG);
		clothingSlots.add(GROIN);
		clothingSlots.add(FOOT);
		clothingSlots.add(SOCK);
		clothingSlots.add(ANKLE);
		
		clothingSlots.add(HORNS);
		clothingSlots.add(TAIL);
		clothingSlots.add(WINGS);
		clothingSlots.add(PENIS);
		clothingSlots.add(VAGINA);
		
		
		piercingSlots.add(PIERCING_EAR);
		piercingSlots.add(PIERCING_NOSE);
		piercingSlots.add(PIERCING_TONGUE);
		piercingSlots.add(PIERCING_LIP);
		piercingSlots.add(PIERCING_STOMACH);
		piercingSlots.add(PIERCING_NIPPLE);
		piercingSlots.add(PIERCING_VAGINA);
		piercingSlots.add(PIERCING_PENIS);
	}

	private InventorySlot(int zLayer, String name, boolean coreClothing, boolean jewellery, String tattooSlotName) {
		this.zLayer = zLayer;
		this.name = name;
		this.coreClothing = coreClothing;
		this.jewellery = jewellery;
		this.tattooSlotName = tattooSlotName;
	}

	public int getZLayer() {
		return zLayer;
	}

	public String getName() {
		return name;
	}

	public String getTattooSlotName() {
		return tattooSlotName;
	}
	
	public boolean isCoreClothing() {
		return coreClothing;
	}

	public boolean isJewellery() {
		return jewellery;
	}

	public static List<InventorySlot> getClothingSlots() {
		return clothingSlots;
	}

	public static List<InventorySlot> getPiercingSlots() {
		return piercingSlots;
	}
	
	/**
	 * Calculates if the character cannot wear clothing in the provided slot due to his or her race. If a part of their body is preventing the clothing from being equipped, this method returns the race of that body part.<br/>
	 * e.g. Horse legs block FOOT slot, so passing in a character who has horse legs and InventorySlot.FOOT will return Race.HORSE_MORPH.<br/>
	 * This method returns null if the slot is not being blocked.
	 * 
	 * @param character
	 * @param slot
	 * @return Race which is blocking this slot. Returns null if nothing is
	 *         blocking the slot.
	 */
	public Race slotBlockedByRace(GameCharacter character) {
		if (character == null) {
			return null;
		}
		
		if(character.getHairRawLengthValue()==0 && this == InventorySlot.HAIR) {
			return character.getHairRace();
		}
		
		if (character.getLegType() == LegType.HORSE_MORPH && this == InventorySlot.FOOT) {
			return Race.HORSE_MORPH;
		}
		
		if (character.getLegType() == LegType.DEMON_HOOFED && this == InventorySlot.FOOT) {
			return Race.DEMON;
		}
		
		if (character.getLegType() == LegType.REINDEER_MORPH && this == InventorySlot.FOOT) {
			return Race.REINDEER_MORPH;
		}
		
		if (character.getLegType() == LegType.HARPY && this == InventorySlot.FOOT) {
			return Race.HARPY;
		}
		
		if (character.getArmType() == ArmType.HARPY && (this == InventorySlot.HAND || this == InventorySlot.FINGER)) {
			return Race.HARPY;
		}
		
		if (character.getLegType() == LegType.COW_MORPH && this == InventorySlot.FOOT) {
			return Race.COW_MORPH;
		}

		return null;
	}
	
	/**
	 * Complimentary method to slotBlockedByRace(GameCharacter character,
	 * InventorySlot slot).
	 * 
	 * @param character
	 * @param slot
	 * @return A description of why this slot can't be used.
	 */
	public String getCannotBeWornDescription(GameCharacter character) {
		
		if(character.getHairRawLengthValue()==0 && this == InventorySlot.HAIR) {
			if(character.isPlayer())
				return "You don't have any hair, so you can't wear any hair accessories!";
			else
				return UtilText.parse(character,
						"[npc.Name] doesn't have any hair, so [npc.she] can't wear any hair accessories!");
		}
		
		if (character.getLegType() == LegType.HORSE_MORPH && this == InventorySlot.FOOT) {
			if(character.isPlayer())
				return "Your horse-like hoofs prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.NamePos] horse-like hoofs prevent [npc.herHim] from wearing footwear of any kind!");
		}
		
		if (character.getLegType() == LegType.DEMON_HOOFED && this == InventorySlot.FOOT) {
			if(character.isPlayer())
				return "Your demonic hoofs prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.NamePos] demonic hoofs prevent [npc.herHim] from wearing footwear of any kind!");
		}
		
		if (character.getLegType() == LegType.REINDEER_MORPH && this == InventorySlot.FOOT) {
			if(character.isPlayer())
				return "Your reindeer-like hoofs prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.NamePos] reindeer-like hoofs prevent [npc.herHim] from wearing footwear of any kind!");
		}
		
		if (character.getLegType() == LegType.COW_MORPH && this == InventorySlot.FOOT) {
			if(character.isPlayer())
				return "Your cow-like hoofs prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.NamePos] cow-like hoofs prevent [npc.herHim] from wearing footwear of any kind!");
		}
		
		if (character.getLegType() == LegType.HARPY && this == InventorySlot.FOOT) {
			if(character.isPlayer())
				return "Your bird-like talons prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.NamePos] bird-like talons prevent [npc.herHim] from wearing footwear of any kind!");
		}
		
		if (character.getArmType() == ArmType.HARPY && this == InventorySlot.HAND) {
			if(character.isPlayer())
				return "You can't fit anything onto your harpy wings!";
			else
				return UtilText.parse(character,
						"[npc.Name] can't fit anything onto [npc.her] harpy wings!");
		}
		
		if (character.getArmType() == ArmType.HARPY && this == InventorySlot.FINGER) {
			if(character.isPlayer())
				return "You only have a single thumb in the middle of your harpy wings, so you can't wear anything that would require fingers!";
			else
				return UtilText.parse(character,
						"[npc.Name] doesn't have any fingers on [npc.her] harpy wings!");
		}
		
		return null;
	}
}
