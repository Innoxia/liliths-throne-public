package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.97
 * @version 0.4.1
 * @author Innoxia
 */
public enum OrgasmCumTarget {

	// Specials:
	LILAYA_PANTIES("into Lilaya's panties", "panties", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
	},
	
	WALL("up the wall", "wall", false) {
		@Override
		public String getName() {
			return UtilText.parse("up the [pc.wall]");
		}

		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
	},
	FLOOR("onto floor", "floor", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
	},
	
	INSIDE("inside", "inside", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
	},
	INSIDE_SWITCH_DOUBLE("inside (double)", "inside", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
	},
	
	ARMPITS("over armpit", "armpit", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ARMPITS;
		}
	},
	ASS("over ass", "ass", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ASS;
		}
	},
	GROIN("over groin", "groin", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			if(owner.hasVagina()) {
				return CoverableArea.VAGINA;
			} else if(owner.hasPenis()) {
				return CoverableArea.PENIS;
			}
			return CoverableArea.MOUND;
		}
	},
	BREASTS("onto breasts", "breasts", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BREASTS;
		}
	},
	FACE("over face", "face", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.MOUTH;
		}
	},
	HAIR("into hair", "hair", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.HAIR;
		}
	},
	STOMACH("onto stomach", "stomach", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.STOMACH;
		}
	},
	LEGS("onto legs", "legs", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.LEGS;
		}
	},
	FEET("onto feet", "feet", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.FEET;
		}
	},
	BACK("over back", "back", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BACK;
		}
	},
	
	SELF_GROIN("onto self groin", "own groin", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			if(owner.hasVagina()) {
				return CoverableArea.VAGINA;
			} else if(owner.hasPenis()) {
				return CoverableArea.PENIS;
			}
			return CoverableArea.MOUND;
		}
	},
	SELF_STOMACH("onto self stomach", "own stomach", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.STOMACH;
		}
	},
	SELF_LEGS("onto self legs", "own legs", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.LEGS;
		}
	},
	SELF_FEET("onto self feet", "own feet", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.FEET;
		}
	},
	SELF_BREASTS("onto self breasts", "own breasts", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BREASTS;
		}
	},
	SELF_HANDS("onto self hands", "own hands", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.HANDS;
		}
	},
	SELF_FACE("onto self face", "own face", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.MOUTH;
		}
	};
	
	private String name;
	private String simpleName;
	private boolean requiresPartner;

	private OrgasmCumTarget(String name, String simpleName, boolean requiresPartner) {
		this.name = name;
		this.simpleName = simpleName;
		this.requiresPartner = requiresPartner;
	}

	/**
	 * @return A name that's suitable for a brief action description, e.g. 'onto floor'
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return A one-word name that's suitable for use in scenes, e.g. 'floor'
	 */
	public String getSimpleName() {
		return simpleName;
	}
	
	public boolean isRequiresPartner() {
		return requiresPartner;
	}
	
	public abstract CoverableArea getRelatedCoverableArea(GameCharacter owner);
}
