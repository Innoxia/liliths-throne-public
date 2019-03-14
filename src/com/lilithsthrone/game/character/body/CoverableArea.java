package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public enum CoverableArea {
	
	// Utility value
	NONE("none",
			new ArrayList<>()),

	HANDS("hands",
			Util.newArrayListOfValues(
					InventorySlot.FINGER,
					InventorySlot.HAND,
					InventorySlot.WRIST)),
	
	ASS("ass",
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.ANUS,
					InventorySlot.LEG,
					InventorySlot.TAIL)),
	
	ANUS("asshole",
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.ANUS)),

	STOMACH("stomach",
			Util.newArrayListOfValues(
					InventorySlot.STOMACH,
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER)),
	
	BACK("back",
			Util.newArrayListOfValues(
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER,
					InventorySlot.WINGS,
					InventorySlot.TAIL)),
	
	LEGS("legs",
			Util.newArrayListOfValues(
					InventorySlot.LEG,
					InventorySlot.SOCK)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			switch(owner.getLegConfiguration()) {
				case ARACHNID:
				case BIPEDAL:
				case CEPHALOPOD:
				case TAUR:
					return true;
				case TAIL:
				case TAIL_LONG:
					return false;
			}
			return true;
		}
	},
	
	FEET("feet",
			Util.newArrayListOfValues(
					InventorySlot.FOOT,
					InventorySlot.ANKLE,
					InventorySlot.SOCK)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return LEGS.isPhysicallyAvailable(owner);
		}
	},
	
	THIGHS("thighs",
			Util.newArrayListOfValues(
					InventorySlot.LEG,
					InventorySlot.SOCK)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return LEGS.isPhysicallyAvailable(owner);
		}
	},
	
	VAGINA("pussy",
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.LEG)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasVagina();
		}
	},
	
	MOUND("mound",
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.LEG)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return !owner.hasVagina() && !owner.hasPenis();
		}
	},
	
	PENIS("cock",
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.LEG)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasPenis();
		}
	},
	
	TESTICLES("balls",
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.LEG)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasPenis();
		}
	},
	
	BREASTS("breasts",
			Util.newArrayListOfValues(
					InventorySlot.CHEST,
					InventorySlot.NIPPLE,
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER)),
	
	NIPPLES("nipples",
			Util.newArrayListOfValues(
					InventorySlot.CHEST,
					InventorySlot.NIPPLE,
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER)),
	
	BREASTS_CROTCH("crotch-breasts",
			Util.newArrayListOfValues(
					InventorySlot.STOMACH,
					InventorySlot.GROIN,
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasBreastsCrotch();
		}
		public List<InventorySlot> getAssociatedInventorySlots(GameCharacter owner) {
			if(owner.getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
				return super.getAssociatedInventorySlots(owner);
			} else {
				return Util.newArrayListOfValues(
						InventorySlot.STOMACH,
						InventorySlot.GROIN,
						InventorySlot.LEG);
			}
		}
	},
	
	NIPPLES_CROTCH("crotch-nipples",
			null) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasBreastsCrotch();
		}
		public List<InventorySlot> getAssociatedInventorySlots(GameCharacter owner) {
			return BREASTS_CROTCH.getAssociatedInventorySlots(owner);
		}
	},

	HAIR("hair",
			Util.newArrayListOfValues(
					InventorySlot.HAIR,
					InventorySlot.HORNS,
					InventorySlot.HEAD)),
	
	MOUTH("mouth",
			Util.newArrayListOfValues(
					InventorySlot.HAIR,
					InventorySlot.HORNS,
					InventorySlot.HEAD,
					InventorySlot.EYES,
					InventorySlot.MOUTH,
					InventorySlot.NECK));

	
	private String name;
	private List<InventorySlot> associatedInventorySlots;

	private CoverableArea(String name, List<InventorySlot> associatedInventorySlots) {
		this.name = name;
		this.associatedInventorySlots = associatedInventorySlots;
	}

	public String getName() {
		return name;
	}
	
	public List<InventorySlot> getAssociatedInventorySlots(GameCharacter owner) {
		return associatedInventorySlots;
	}

	/**
	 * @return true if the owner has the related orifice/penetration type.
	 */
	public boolean isPhysicallyAvailable(GameCharacter owner) {
		return true;
	}
}
