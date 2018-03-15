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
 * @version 0.2.1
 * @author Innoxia
 */
public enum InventorySlot {


	// HEAD:
	HEAD(40, "head", false, false), // Headgear
	EYES(50, "eyes", false, false), // Eyes
	HAIR(20, "hair", false, false), // Ribbons, hairbands
	MOUTH(10, "mouth", false, false), // Mouth
	NECK(30, "neck", false, false), // Necklaces

	// TORSO:
	TORSO_OVER(50, "over-torso", false, false), // Coats
	TORSO_UNDER(40, "torso", true, false), // Shirts
	CHEST(10, "chest", true, false), // Bras
	NIPPLE(5, "nipples", false, false), // Nipple shields, insertables
	STOMACH(10, "stomach", false, false), // Corsets

	// HAND:
	HAND(20, "hands", false, false), // Gloves
	WRIST(30, "wrists", false, false), // Bracelets
	FINGER(30, "fingers", false, false), // Rings
	HIPS(40, "hips", false, false), // Belts
	ANUS(0, "anus", false, false), // Insertables

	// LEG & FOOT:
	LEG(30, "legs", true, false), // Trousers
	GROIN(10, "groin", true, false), // Underwear
	FOOT(40, "feet", true, false), // Shoes
	SOCK(10, "calves", true, false), // Socks
	ANKLE(50, "ankles", false, false), // Ankle bracelets
	
	// OPTIONAL EXTRAS:
	HORNS(50, "horns", false, false), // Decorations
//	WINGS(50, "wings", false, false), // Decorations
	TAIL(50, "tail", false, false), // Decorations
	PENIS(0, "penis", false, false), // Cock socks & insertables
	VAGINA(0, "vagina", false, false), // Insertables

	// PIERCING:
	PIERCING_EAR(0, "ear piercing", false, true),
	PIERCING_NOSE(0, "nose piercing", false, true),
	PIERCING_TONGUE(0, "tongue piercing", false, true),
	PIERCING_LIP(0, "lip piercing", false, true),
	PIERCING_STOMACH(0, "navel piercing", false, true),
	PIERCING_NIPPLE(0, "nipple piercing", false, true),
	PIERCING_VAGINA(0, "vaginal piercing", false, true),
	PIERCING_PENIS(0, "cock piercing", false, true),

	// EQUIPPABLE:
	WEAPON_MAIN(0, "primary weapon", false, false),
	WEAPON_OFFHAND(0, "secondary weapon", false, false);

	private int zLayer;
	private String name;
	private boolean jewellery, coreClothing;
	private static List<InventorySlot> clothingSlots, piercingSlots;

	static {
		clothingSlots = new ArrayList<>();
		piercingSlots = new ArrayList<>();

		clothingSlots.add(HEAD);
		clothingSlots.add(EYES);
		clothingSlots.add(MOUTH);
		clothingSlots.add(NECK);
		clothingSlots.add(TORSO_UNDER);
		clothingSlots.add(CHEST);
		clothingSlots.add(STOMACH);
		clothingSlots.add(HAND);
		clothingSlots.add(WRIST);
		clothingSlots.add(FINGER);
		clothingSlots.add(LEG);
		clothingSlots.add(GROIN);
		clothingSlots.add(FOOT);
		clothingSlots.add(SOCK);
		clothingSlots.add(ANKLE);
		
		piercingSlots.add(PIERCING_EAR);
		piercingSlots.add(PIERCING_NOSE);
		piercingSlots.add(PIERCING_TONGUE);
		piercingSlots.add(PIERCING_LIP);
		piercingSlots.add(PIERCING_STOMACH);
		piercingSlots.add(PIERCING_NIPPLE);
		piercingSlots.add(PIERCING_VAGINA);
		piercingSlots.add(PIERCING_PENIS);
	}

	private InventorySlot(int zLayer, String name, boolean coreClothing, boolean jewellery) {
		this.zLayer = zLayer;
		this.name = name;
		this.coreClothing = coreClothing;
		this.jewellery = jewellery;
	}

	public int getZLayer() {
		return zLayer;
	}

	public String getName() {
		return name;
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
	 * Calculates if the character cannot wear clothing in the provided slot due to his or her race. If a part of their body is preventing the clothing from being equipped, this method returns the race of that body part.</br>
	 * e.g. Horse legs block FOOT slot, so passing in a character who has horse legs and InventorySlot.FOOT will return Race.HORSE_MORPH.</br>
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
				return "Your horse-like hooves prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.Name]'s horse-like hooves prevent [npc.herHim] from wearing footwear of any kind!");
		}
		
		if (character.getLegType() == LegType.REINDEER_MORPH && this == InventorySlot.FOOT) {
			if(character.isPlayer())
				return "Your reindeer-like hooves prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.Name]'s reindeer-like hooves prevent [npc.herHim] from wearing footwear of any kind!");
		}
		
		if (character.getLegType() == LegType.COW_MORPH && this == InventorySlot.FOOT) {
			if(character.isPlayer())
				return "Your cow-like hooves prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.Name]'s cow-like hooves prevent [npc.herHim] from wearing footwear of any kind!");
		}
		
		if (character.getLegType() == LegType.HARPY && this == InventorySlot.FOOT) {
			if(character.isPlayer())
				return "Your bird-like talons prevent you from wearing footwear of any kind!";
			else
				return UtilText.parse(character,
						"[npc.Name]'s bird-like talons prevent [npc.herHim] from wearing footwear of any kind!");
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
