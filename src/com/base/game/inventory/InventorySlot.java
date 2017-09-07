package com.base.game.inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.0
 * @version 0.1.69
 * @author Innoxia
 */
public enum InventorySlot {

	/*
	 * Z-Layers: 0: Piercings 10: Minimum (GROIN [underwear], STOMACH [corsets],
	 * LEG_UNDER [stockings], CHEST [bras], MOUTH [ballgag]) 20: Low (SOCK
	 * [socks], HAND [gloves]) 30: Medium: (LEG [trousers], WRIST [bracelets],
	 * FINGER [rings], NECK[necklace]) 40: High: (FOOT [shoes], TORSO [dress],
	 * HEAD [circlet]) 50: Max: (ANKLE [ankle-cuffs], EYES [blindfold])
	 */

	// HEAD:
	HEAD(40, "head", false, false), // Headgear ***
	EYES(50, "eyes", false, false), // Eyes
	MOUTH(10, "mouth", false, false), // Mouth
	NECK(30, "neck", false, false), // Necklaces

	// TORSO:
	TORSO(40, "torso", true, false), // Tops ***
	CHEST(10, "chest", true, false), // Bras ***
	STOMACH(10, "stomach", false, false), // Corsets

	// HAND:
	HAND(20, "hands", false, false), // Gloves
	WRIST(30, "wrists", false, false), // Bracelets
	FINGER(30, "fingers", false, false), // Rings

	// LEG:
	LEG(30, "legs", true, false), // Trousers ***
	GROIN(10, "groin", true, false), // Underwear ***
	// LEG_UNDER(10, "thighs"), // Tights

	// FOOT:
	FOOT(40, "feet", true, false), // Shoes ***
	SOCK(10, "calves", true, false), // Socks ***
	ANKLE(50, "ankles", false, false), // Ankle bracelets

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
	WEAPON_OFFHAND(0, "offhand weapon", false, false),
	WEAPON_MAIN(0, "main weapon", false, false);

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
		clothingSlots.add(TORSO);
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
}
