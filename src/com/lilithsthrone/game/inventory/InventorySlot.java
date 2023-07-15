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
 * @version 0.4
 * @author Innoxia
 */
public enum InventorySlot {

	// HEAD:
	
	/** Clothing slot "head". Used for headgear.<br/>
	 *  Tattoo slot "head".*/
	HEAD(40, "head", false, false, false, "head") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "head";
		}
	},

	/** Clothing slot "eyes". Used for glasses.<br/>
	 *  Tattoo slot "upper face".*/
	EYES(50, "eyes", true, false, false, "upper face") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.eyes]";
		}
	},

	/** Clothing slot "hair". Used for ribbons and hairbands.<br/>
	 *  Tattoo slot "ears".*/
	HAIR(20, "hair", false, false, false, "ears") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasHair()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have any hair!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.hair(true)]";
		}
	},

	/** Clothing slot "mouth". Used for ballgags.<br/>
	 *  Tattoo slot "lower face".*/
	MOUTH(10, "mouth", false, false, false, "lower face") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.mouth]";
		}
	},

	/** Clothing slot "neck". Used for necklaces and scarfs.<br/>
	 *  Tattoo slot "neck".*/
	NECK(30, "neck", false, false, false, "neck") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "neck";
		}
	},
	
	
	// TORSO:

	/** Clothing slot "over-torso". Used for coats.<br/>
	 *  Tattoo slot "upper back".*/
	TORSO_OVER(50, "over-torso", false, false, false, "upper back") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "over-torso";
		}
	},

	/** Clothing slot "torso". Used for shirts.<br/>
	 *  Tattoo slot "lower back".*/
	TORSO_UNDER(40, "torso", false, true, false, "lower back") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "torso";
		}
	},

	/** Clothing slot "chest". Used for bras.<br/>
	 *  Tattoo slot "chest".*/
	CHEST(10, "chest", false, true, false, "chest") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.breasts]";
		}
	},

	/** Clothing slot "nipples". Used for nipple shields, plugs.<br/>
	 * <br/>If character is a feral with no breasts, crotch-nipples are referenced.
	 *  Tattoo slot "nipples".*/
	NIPPLE(5, "nipples", false, false, false, "nipples") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(character.isFeral() && !character.getFeralAttributes().isBreastsPresent() && !character.hasBreastsCrotch()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have any breasts or crotch-boobs!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			if(character.isFeral() && !character.getFeralAttributes().isBreastsPresent() && character.hasBreastsCrotch()) {
				return "[npc.nipplesCrotch]";
			}
			return "[npc.nipples]";
		}
	},

	/** Clothing slot "stomach". Used for corsets.<br/>
	 *  Tattoo slot "stomach".*/
	STOMACH(10, "stomach", false, false, false, "stomach") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "stomach";
		}
	},

	
	// HAND:

	/** Clothing slot "hands". Used for gloves.<br/>
	 *  Tattoo slot "forearms".*/
	HAND(20, "hands", true, false, false, "forearms") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(character.isFeral() && !character.getFeralAttributes().isArmsOrWingsPresent()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have hands!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.hands]";
		}
	},

	/** Clothing slot "wrists". Used for bracelets.<br/>
	 *  Tattoo slot "upper arms".*/
	WRIST(30, "wrists", true, false, false, "upper arms") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(character.isFeral() && !character.getFeralAttributes().isArmsOrWingsPresent()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have arms!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "wrists";
		}
	},

	/** Clothing slot "fingers". Used for rings.<br/>
	 *  Tattoo slot "hands".*/
	FINGER(30, "fingers", true, false, false, "hands") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(character.isFeral() && !character.getFeralAttributes().isFingerActionsAvailable()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have fingers!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.fingers]";
		}
	},

	/** Clothing slot "hips". Used for belts.<br/>
	 *  Tattoo slot "hips".*/
	HIPS(40, "hips", true, false, false, "hips") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.hips]";
		}
	},

//	/** Clothing slot "hips-under". Used for suspender belts.<br/>
//	 *  Tattoo slot "".*/
//	HIPS_UNDER(20, "hips-under", false, false), // suspender belts

	/** Clothing slot "anus". Used for plugs.<br/>
	 *  Tattoo slot "ass".*/
	ANUS(0, "anus", false, false, false, "ass") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.asshole]";
		}
	},

	
	// LEG & FOOT:

	/** Clothing slot "legs". Used for trousers.<br/>
	 *  Tattoo slot "upper leg".*/
	LEG(30, "legs", true, true, false, "upper leg") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.legs]";
		}
	},

	/** Clothing slot "groin". Used for underwear.<br/>
	 *  Tattoo slot "lower abdomen".*/
	GROIN(10, "groin", false, true, false, "lower abdomen") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "groin";
		}
	},

	/** Clothing slot "feet". Used for shoes.<br/>
	 *  Tattoo slot "feet".*/
	FOOT(40, "feet", true, true, false, "feet") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasLegs()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have any legs!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.feet]";
		}
	},

	/** Clothing slot "calves". Used for socks.<br/>
	 *  Tattoo slot "lower leg".*/
	SOCK(10, "calves", true, true, false, "lower leg") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasLegs()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have any legs!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "calves";
		}
	},

	/** Clothing slot "ankles". Used for bracelets.<br/>
	 *  Tattoo slot "ankles".*/
	ANKLE(50, "ankles", true, false, false, "ankles") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasLegs()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have any legs!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "ankles";
		}
	},
	
	
	// OPTIONAL EXTRAS:

	/** Clothing slot "horns". Used for horn decorations.<br/>
	 *  Tattoo slot "horns".*/
	HORNS(50, "horns", true, false, false, "horns") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasHorns()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have any horns!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.horns]";
		}
	},

	/** Clothing slot "wings". Used for wing decorations.<br/>
	 *  Tattoo slot "wings".*/
	WINGS(50, "wings", true, false, false, "wings") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasWings()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have any wings!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.wings]";
		}
	},

	/** Clothing slot "tail". Used for tail decorations.<br/>
	 *  Tattoo slot "tail".*/
	TAIL(50, "tail", false, false, false, "tail") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasTail()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have a tail!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.tail]";
		}
	},

	/** Clothing slot "penis". Used for cock socks, cages, and plugs.<br/>
	 *  Tattoo slot "penis".*/
	PENIS(0, "penis", false, false, false, "penis") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasPenisIgnoreDildo()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have a penis!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
			if (character == null) {
				return null;
			}
			if(!isPhysicallyAvailable(character)) {
				return new BodyPartClothingBlock(
					Util.newArrayListOfValues(this),
					null,
					this.getPhysicallyUnavailableReason(character),
					Util.newArrayListOfValues(ItemTag.REQUIRES_NO_PENIS));
			}
			// Leg configuration (takes into account feral):
			List<BodyPartClothingBlock> blockedList = character.getLegConfiguration().getBodyPartClothingBlock(character);
			if(blockedList!=null) {
				for(BodyPartClothingBlock block : blockedList) {
					if(block.getBlockedSlots().contains(this)) {
						return block;
					}
				}
			}
			for(BodyPartInterface bodypart : character.getBody().getAllBodyParts()) {
				BodyPartClothingBlock block = bodypart.getType().getBodyPartClothingBlock();
				if(block!=null) {
					if(block.getBlockedSlots().contains(this)) {
						return block;
					}
				}
			}
			
			return null;
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.cock]";
		}
	},

	/** Clothing slot "vagina". Used for plugs.<br/>
	 *  Tattoo slot "vagina".*/
	VAGINA(0, "vagina", false, false, false, "vagina") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasVagina()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have a vagina!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
			if (character == null) {
				return null;
			}
			if(!isPhysicallyAvailable(character)) {
				return new BodyPartClothingBlock(
					Util.newArrayListOfValues(this),
					null,
					this.getPhysicallyUnavailableReason(character),
					Util.newArrayListOfValues(ItemTag.REQUIRES_NO_VAGINA));
			}
			// Leg configuration (takes into account feral):
			List<BodyPartClothingBlock> blockedList = character.getLegConfiguration().getBodyPartClothingBlock(character);
			if(blockedList!=null) {
				for(BodyPartClothingBlock block : blockedList) {
					if(block.getBlockedSlots().contains(this)) {
						return block;
					}
				}
			}
			for(BodyPartInterface bodypart : character.getBody().getAllBodyParts()) {
				BodyPartClothingBlock block = bodypart.getType().getBodyPartClothingBlock();
				if(block!=null) {
					if(block.getBlockedSlots().contains(this)) {
						return block;
					}
				}
			}
			
			return null;
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.pussy]";
		}
	},

	// PIERCING:
	PIERCING_EAR(0, "ear piercing", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.ears]";
		}
	},
	PIERCING_NOSE(0, "nose piercing", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.nose]";
		}
	},
	PIERCING_TONGUE(0, "tongue piercing", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.tongue]";
		}
	},
	PIERCING_LIP(0, "lip piercing", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.lips]";
		}
	},
	PIERCING_STOMACH(0, "navel piercing", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "navel";
		}
	},
	PIERCING_NIPPLE(0, "nipple piercing", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.nipples]";
		}
	},
	PIERCING_VAGINA(0, "vaginal piercing", false, false, true, null) {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasVagina()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have a vagina!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.pussy]";
		}
	},
	PIERCING_PENIS(0, "cock piercing", false, false, true, null) {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasPenisIgnoreDildo()) {
				return UtilText.parse(character, "[npc.Name] [npc.do] not have a penis!");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.cock]";
		}
	},

	// EQUIPPABLE:
	WEAPON_MAIN_1(0, "primary weapon", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
	},
	WEAPON_MAIN_2(0, "primary weapon (2nd)", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
	},
	WEAPON_MAIN_3(0, "primary weapon (3rd)", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
	},
	
	WEAPON_OFFHAND_1(0, "secondary weapon", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
	},
	WEAPON_OFFHAND_2(0, "secondary weapon (2nd)", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
	},
	WEAPON_OFFHAND_3(0, "secondary weapon (3rd)", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
	};

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
	private static List<InventorySlot> commonTattooSlots;
	
	public static InventorySlot[] mainWeaponSlots;
	public static InventorySlot[] offhandWeaponSlots;
	public static InventorySlot[] allWeaponSlots;

	static {
		mainWeaponSlots = new InventorySlot[] {InventorySlot.WEAPON_MAIN_1, InventorySlot.WEAPON_MAIN_2, InventorySlot.WEAPON_MAIN_3};
		offhandWeaponSlots = new InventorySlot[] {InventorySlot.WEAPON_OFFHAND_1, InventorySlot.WEAPON_OFFHAND_2, InventorySlot.WEAPON_OFFHAND_3};
		allWeaponSlots = new InventorySlot[] {InventorySlot.WEAPON_MAIN_1, InventorySlot.WEAPON_MAIN_2, InventorySlot.WEAPON_MAIN_3, InventorySlot.WEAPON_OFFHAND_1, InventorySlot.WEAPON_OFFHAND_2, InventorySlot.WEAPON_OFFHAND_3};
		
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
		
		commonTattooSlots = Util.newArrayListOfValues(
				InventorySlot.NECK,
				InventorySlot.TORSO_OVER,
				InventorySlot.TORSO_UNDER,
				InventorySlot.CHEST,
				InventorySlot.STOMACH,
				InventorySlot.HAND,
				InventorySlot.WRIST,
				InventorySlot.HIPS,
				InventorySlot.ANUS,
				InventorySlot.LEG,
				InventorySlot.GROIN,
				InventorySlot.SOCK,
				InventorySlot.ANKLE);

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
	
	public String getNameOfAssociatedPart(GameCharacter owner) {
		return UtilText.parse(owner, getNameForParsing(owner));
	}
	
	protected abstract String getNameForParsing(GameCharacter character);

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

	public boolean isWeapon() {
		return false;
	}

	public String getPhysicallyUnavailableReason(GameCharacter character) {
		return "";
	}
	
	public boolean isPhysicallyAvailable(GameCharacter character) {
		return getPhysicallyUnavailableReason(character)==null || getPhysicallyUnavailableReason(character).isEmpty();
	}
	
	public static List<InventorySlot> getHumanoidSlots() {
		return new ArrayList<>(humanoidSlots);
	}

	public static List<InventorySlot> getClothingSlots() {
		return new ArrayList<>(clothingSlots);
	}

	public static List<InventorySlot> getPiercingSlots() {
		return new ArrayList<>(piercingSlots);
	}

	public static List<InventorySlot> getCommonTattooSlots() {
		return new ArrayList<>(commonTattooSlots);
	}
	
	public static List<InventorySlot> getMainClothingSlots() {
		return new ArrayList<>(mainClothingSlots);
	}

	public static List<InventorySlot> getExtraClothingSlots() {
		return new ArrayList<>(extraClothingSlots);
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
//		if(character.getHairRawLengthValue()==0 && this == InventorySlot.HAIR) {
//			return new BodyPartClothingBlock(
//					Util.newArrayListOfValues(InventorySlot.HAIR),
//					character.getHairRace(),
//					UtilText.parse(character, "[npc.Name] [npc.do]n't have any hair, so [npc.she] can't wear any hair accessories!"),
//					Util.newArrayListOfValues());
//		}
		if(!isPhysicallyAvailable(character)) {
			return new BodyPartClothingBlock(
				Util.newArrayListOfValues(this),
				null,
				this.getPhysicallyUnavailableReason(character),
				Util.newArrayListOfValues());
		}
		
		// Leg configuration (takes into account feral):
		List<BodyPartClothingBlock> blockedList = character.getLegConfiguration().getBodyPartClothingBlock(character);
		if(blockedList!=null) {
			for(BodyPartClothingBlock block : blockedList) {
				if(block.getBlockedSlots().contains(this)) {
					return block;
				}
			}
		}
		
		for(BodyPartInterface bodypart : character.getBody().getAllBodyParts()) {
			BodyPartClothingBlock block = bodypart.getType().getBodyPartClothingBlock();
			if(block!=null) {
				if(block.getBlockedSlots().contains(this)) {
					return block;
				}
			}
		}
		
		return null;
	}
}
