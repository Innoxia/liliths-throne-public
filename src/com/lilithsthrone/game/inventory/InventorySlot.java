package com.lilithsthrone.game.inventory;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.2
 * @author Innoxia
 */
public enum InventorySlot {

	// HEAD:
	
	/** Clothing slot "head". Used for headgear.<br/>
	 *  Tattoo slot "head".*/
	HEAD(40, "head", false, false, false, "head"),

	/** Clothing slot "eyes". Used for glasses.<br/>
	 *  Tattoo slot "upper face".*/
	EYES(50, "eyes", true, false, false, "upper face"),

	/** Clothing slot "hair". Used for ribbons and hairbands.<br/>
	 *  Tattoo slot "ears".*/
	HAIR(20, "hair", false, false, false, "ears") {
		@Override
		public boolean isPhysicallyAvailable(GameCharacter character) {
			return character.hasHair();
		}
	},

	/** Clothing slot "mouth". Used for ballgags.<br/>
	 *  Tattoo slot "lower face".*/
	MOUTH(10, "mouth", false, false, false, "lower face"),

	/** Clothing slot "neck". Used for necklaces and scarfs.<br/>
	 *  Tattoo slot "neck".*/
	NECK(30, "neck", false, false, false, "neck"),
	
	
	// TORSO:

	/** Clothing slot "over-torso". Used for coats.<br/>
	 *  Tattoo slot "upper back".*/
	TORSO_OVER(50, "over-torso", false, false, false, "upper back"),

	/** Clothing slot "torso". Used for shirts.<br/>
	 *  Tattoo slot "lower back".*/
	TORSO_UNDER(40, "torso", false, true, false, "lower back"),

	/** Clothing slot "chest". Used for bras.<br/>
	 *  Tattoo slot "chest".*/
	CHEST(10, "chest", false, true, false, "chest"),

	/** Clothing slot "nipples". Used for nipple shields, plugs.<br/>
	 *  Tattoo slot "nipples".*/
	NIPPLE(5, "nipples", false, false, false, "nipples"),

	/** Clothing slot "stomach". Used for corsets.<br/>
	 *  Tattoo slot "stomach".*/
	STOMACH(10, "stomach", false, false, false, "stomach"),

	
	// HAND:

	/** Clothing slot "hands". Used for gloves.<br/>
	 *  Tattoo slot "forearms".*/
	HAND(20, "hands", true, false, false, "forearms"),

	/** Clothing slot "wrists". Used for bracelets.<br/>
	 *  Tattoo slot "upper arms".*/
	WRIST(30, "wrists", true, false, false, "upper arms"),

	/** Clothing slot "fingers". Used for rings.<br/>
	 *  Tattoo slot "hands".*/
	FINGER(30, "fingers", true, false, false, "hands"),

	/** Clothing slot "hips". Used for belts.<br/>
	 *  Tattoo slot "hips".*/
	HIPS(40, "hips", true, false, false, "hips"),

//	/** Clothing slot "hips-under". Used for suspender belts.<br/>
//	 *  Tattoo slot "".*/
//	HIPS_UNDER(20, "hips-under", false, false), // suspender belts

	/** Clothing slot "anus". Used for plugs.<br/>
	 *  Tattoo slot "ass".*/
	ANUS(0, "anus", false, false, false, "ass"),

	
	// LEG & FOOT:

	/** Clothing slot "legs". Used for trousers.<br/>
	 *  Tattoo slot "upper leg".*/
	LEG(30, "legs", true, true, false, "upper leg"),

	/** Clothing slot "groin". Used for underwear.<br/>
	 *  Tattoo slot "lower abdomen".*/
	GROIN(10, "groin", false, true, false, "lower abdomen"),

	/** Clothing slot "feet". Used for shoes.<br/>
	 *  Tattoo slot "feet".*/
	FOOT(40, "feet", true, true, false, "feet"),

	/** Clothing slot "calves". Used for socks.<br/>
	 *  Tattoo slot "lower leg".*/
	SOCK(10, "calves", true, true, false, "lower leg"),

	/** Clothing slot "ankles". Used for bracelets.<br/>
	 *  Tattoo slot "ankles".*/
	ANKLE(50, "ankles", true, false, false, "ankles"),
	
	
	// OPTIONAL EXTRAS:

	/** Clothing slot "horns". Used for horn decorations.<br/>
	 *  Tattoo slot "horns".*/
	HORNS(50, "horns", true, false, false, "horns") {
		@Override
		public boolean isPhysicallyAvailable(GameCharacter character) {
			return character.hasHorns();
		}
	},

	/** Clothing slot "wings". Used for wing decorations.<br/>
	 *  Tattoo slot "wings".*/
	WINGS(50, "wings", true, false, false, "wings") {
		@Override
		public boolean isPhysicallyAvailable(GameCharacter character) {
			return character.hasWings();
		}
	},

	/** Clothing slot "tail". Used for tail decorations.<br/>
	 *  Tattoo slot "tail".*/
	TAIL(50, "tail", false, false, false, "tail") {
		@Override
		public boolean isPhysicallyAvailable(GameCharacter character) {
			return character.hasTail();
		}
	},

	/** Clothing slot "penis". Used for cock socks, cages, and plugs.<br/>
	 *  Tattoo slot "penis".*/
	PENIS(0, "penis", false, false, false, "penis") {
		@Override
		public boolean isPhysicallyAvailable(GameCharacter character) {
			return character.hasPenis();
		}
	},

	/** Clothing slot "vagina". Used for plugs.<br/>
	 *  Tattoo slot "vagina".*/
	VAGINA(0, "vagina", false, false, false, "vagina") {
		@Override
		public boolean isPhysicallyAvailable(GameCharacter character) {
			return character.hasVagina();
		}
	},

	// PIERCING:
	PIERCING_EAR(0, "ear piercing", false, false, true, null),
	PIERCING_NOSE(0, "nose piercing", false, false, true, null),
	PIERCING_TONGUE(0, "tongue piercing", false, false, true, null),
	PIERCING_LIP(0, "lip piercing", false, false, true, null),
	PIERCING_STOMACH(0, "navel piercing", false, false, true, null),
	PIERCING_NIPPLE(0, "nipple piercing", false, false, true, null),
	PIERCING_VAGINA(0, "vaginal piercing", false, false, true, null) {
		@Override
		public boolean isPhysicallyAvailable(GameCharacter character) {
			return character.hasVagina();
		}
	},
	PIERCING_PENIS(0, "cock piercing", false, false, true, null) {
		@Override
		public boolean isPhysicallyAvailable(GameCharacter character) {
			return character.hasPenis();
		}
	},

	// EQUIPPABLE:
	WEAPON_MAIN(0, "primary weapon", false, false, false, null),
	WEAPON_OFFHAND(0, "secondary weapon", false, false, false, null);

	private int zLayer;
	private String name;
	private boolean plural;
	private String tattooSlotName;
	private boolean jewellery;
	private boolean coreClothing;

	private static List<InventorySlot> humanoidSlots;
	private static List<InventorySlot> clothingSlots;
	private static List<InventorySlot> mainClothingSlots;
	private static List<InventorySlot> extraClothingSlots;
	private static List<InventorySlot> piercingSlots;

	static {
		humanoidSlots = new ArrayList<>();
		clothingSlots = new ArrayList<>();
		piercingSlots = new ArrayList<>();
		extraClothingSlots = new ArrayList<>();
		
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
		
		extraClothingSlots.add(HORNS);
		extraClothingSlots.add(TAIL);
		extraClothingSlots.add(WINGS);
		extraClothingSlots.add(PENIS);
		extraClothingSlots.add(VAGINA);
		
		mainClothingSlots = new ArrayList<>(clothingSlots);
		mainClothingSlots.removeAll(extraClothingSlots);
		
		piercingSlots.add(PIERCING_EAR);
		piercingSlots.add(PIERCING_NOSE);
		piercingSlots.add(PIERCING_TONGUE);
		piercingSlots.add(PIERCING_LIP);
		piercingSlots.add(PIERCING_STOMACH);
		piercingSlots.add(PIERCING_NIPPLE);
		piercingSlots.add(PIERCING_VAGINA);
		piercingSlots.add(PIERCING_PENIS);
		

		humanoidSlots.add(HEAD);
		humanoidSlots.add(EYES);
		humanoidSlots.add(HAIR);
		humanoidSlots.add(MOUTH);
		humanoidSlots.add(NECK);

		humanoidSlots.add(TORSO_OVER);
		humanoidSlots.add(TORSO_UNDER);
		humanoidSlots.add(CHEST);
		humanoidSlots.add(NIPPLE);
		humanoidSlots.add(STOMACH);
		
		humanoidSlots.add(HAND);
		humanoidSlots.add(WRIST);
		humanoidSlots.add(FINGER);
		humanoidSlots.add(HIPS);
//		humanoidSlots.add(ANUS);
		
//		humanoidSlots.add(LEG);
//		humanoidSlots.add(GROIN);
//		humanoidSlots.add(FOOT);
//		humanoidSlots.add(SOCK);
//		humanoidSlots.add(ANKLE);
		
		humanoidSlots.add(HORNS);
		humanoidSlots.add(TAIL);
//		humanoidSlots.add(WINGS); //TODO special case, as can be either
		humanoidSlots.add(PENIS);
		humanoidSlots.add(VAGINA);
		
		humanoidSlots.add(PIERCING_EAR);
		humanoidSlots.add(PIERCING_NOSE);
		humanoidSlots.add(PIERCING_TONGUE);
		humanoidSlots.add(PIERCING_LIP);
		humanoidSlots.add(PIERCING_STOMACH);
		humanoidSlots.add(PIERCING_NIPPLE);
//		humanoidSlots.add(PIERCING_VAGINA);
//		humanoidSlots.add(PIERCING_PENIS);
	}

	private InventorySlot(int zLayer, String name, boolean plural, boolean coreClothing, boolean jewellery, String tattooSlotName) {
		this.zLayer = zLayer;
		this.name = name;
		this.plural = plural;
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

	public boolean isPlural() {
		return plural;
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

	public boolean isPhysicallyAvailable(GameCharacter character) {
		return true;
	}
	
	public static List<InventorySlot> getHumanoidSlots() {
		return humanoidSlots;
	}

	public static List<InventorySlot> getClothingSlots() {
		return clothingSlots;
	}

	public static List<InventorySlot> getPiercingSlots() {
		return piercingSlots;
	}
	
	public static List<InventorySlot> getMainClothingSlots() {
		return mainClothingSlots;
	}

	public static List<InventorySlot> getExtraClothingSlots() {
		return extraClothingSlots;
	}

	/**
	 * Returns the first applicable BodyPartClothingBlock found from the supplied character's body parts.
	 * 
	 * @param character The character to check.
	 * @return A BodyPartClothingBlock object which represents how this part is being blocked. Returns null if nothing is blocking the slot.
	 */
	public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
		if (character == null) {
			return null;
		}
		if(character.getHairRawLengthValue()==0 && this == InventorySlot.HAIR) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(InventorySlot.HAIR),
					character.getHairRace(),
					UtilText.parse(character, "[npc.Name] [npc.do]n't have any hair, so [npc.she] can't wear any hair accessories!"),
					Util.newArrayListOfValues());
		}
		// leg configuration:
		BodyPartClothingBlock block = character.getLegConfiguration().getBodyPartClothingBlock(character);
		if(block!=null) {
			if(block.getBlockedSlots().contains(this)) {
				return block;
			}
		}
		
		for(BodyPartInterface bodypart : character.getBody().getAllBodyParts()) {
			block = bodypart.getType().getBodyPartClothingBlock();
			if(block!=null) {
				if(block.getBlockedSlots().contains(this)) {
					return block;
				}
			}
		}
		return null;
	}
}
