package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;

/**
 * @since 0.1.0
 * @version 0.2.7
 * @author Innoxia
 */
public enum SexAreaPenetration implements SexAreaInterface {
	
	PENIS(4, -2f, true) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getPenisName();
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, SexAreaOrifice.URETHRA_PENIS) && Sex.isPenetrationTypeFree(owner, this);
		}

		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.PENIS;
		}
	},
	
	CLIT(4, -2f, true) {
		@Override
		public String getName(GameCharacter owner) {
			return "clit";
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isPenetrationTypeFree(owner, this);
		}

		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.VAGINA;
		}
	},
	
	TONGUE(2, 0, false) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getTongueName();
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, SexAreaOrifice.MOUTH) && Sex.isPenetrationTypeFree(owner, this);
		}

		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.MOUTH;
		}
	},
	
	FINGER(1, 0, false) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getArmType().getFingersNamePlural(owner);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.NONE;
		}
	},
	
	FOOT(1, 0, false) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getLegType().getFeetNameSingular(owner);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.FEET;
		}
	},
	
	TAIL(2, -1f, true) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getTailName();
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isPenetrationTypeFree(owner, this);
		}

		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.NONE;
		}
	},
	
	TENTACLE(3, -1.5f, true) {
		@Override
		public String getName(GameCharacter owner) {
			return "tentacle";
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			// TODO
			return false;
		}

		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.NONE;
		}
	};

	
	private float baseArousalWhenPenetrating;
	private float arousalChangePenetratingDry;
	private boolean takesVirginity;

	private SexAreaPenetration(float baseArousalWhenPenetrating, float arousalChangePenetratingDry, boolean takesVirginity) {
		this.baseArousalWhenPenetrating = baseArousalWhenPenetrating;
		this.arousalChangePenetratingDry = arousalChangePenetratingDry;
		this.takesVirginity = takesVirginity;
	}

	@Override
	public boolean isOrifice() {
		return false;
	}
	
	public float getBaseArousalWhenPenetrating() {
		return baseArousalWhenPenetrating;
	}
	
	public float getArousalChangePenetratingDry() {
		return arousalChangePenetratingDry;
	}
	
	public boolean isTakesVirginity() {
		return takesVirginity;
	}
	
}
