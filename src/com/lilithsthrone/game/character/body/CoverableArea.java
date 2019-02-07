package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public enum CoverableArea {

	NONE("none"), // Utility value

	HANDS("hands"),
	
	ASS("ass"),
	ANUS("asshole"),

	STOMACH("stomach"),
	BACK("back"),
	
	LEGS("legs") {
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
	FEET("feet") {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return LEGS.isPhysicallyAvailable(owner);
		}
	},
	THIGHS("thighs") {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return LEGS.isPhysicallyAvailable(owner);
		}
	},
	
	VAGINA("pussy") {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasVagina();
		}
	},
	
	MOUND("mound") {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return !owner.hasVagina() && !owner.hasPenis();
		}
	},
	
	PENIS("cock") {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasPenis();
		}
	},
	TESTICLES("balls") {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasPenis();
		}
	},
	
	BREASTS("breasts"),
	NIPPLES("nipples"),
	
	BREASTS_CROTCH("crotch-breasts") {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasBreastsCrotch();
		}
	},
	NIPPLES_CROTCH("crotch-nipples") {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasBreastsCrotch();
		}
	},

	HAIR("hair"),
	MOUTH("mouth");

	private String name;

	private CoverableArea(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * @return true if the owner has the related orifice/penetration type.
	 */
	public boolean isPhysicallyAvailable(GameCharacter owner) {
		return true;
	}
}
