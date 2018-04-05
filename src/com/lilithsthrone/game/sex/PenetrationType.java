package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.1.0
 * @version 0.2.2
 * @author Innoxia
 */
public enum PenetrationType {
	
	PENIS(4, true) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getPenisName();
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, OrificeType.URETHRA_PENIS) && Sex.isPenetrationTypeFree(owner, this);
		}
	},
	
	TONGUE(2, false) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getTongueName();
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, OrificeType.MOUTH) && Sex.isPenetrationTypeFree(owner, this);
		}
	},
	
	FINGER(1, false) {
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
	},
	
	TAIL(2, true) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getTailName();
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isPenetrationTypeFree(owner, this);
		}
	},
	
	TENTACLE(3, true) {
		@Override
		public String getName(GameCharacter owner) {
			return "tentacle";
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			// TODO
			return false;
		}
	};

	
	private float baseArousalWhenPenetrating;
	private boolean takesVirginity;

	private PenetrationType(float baseArousalWhenPenetrating, boolean takesVirginity) {
		this.baseArousalWhenPenetrating = baseArousalWhenPenetrating;
		this.takesVirginity = takesVirginity;
	}

	public float getBaseArousalWhenPenetrating() {
		return baseArousalWhenPenetrating;
	}
	
	public boolean isPlural() {
		return false;
	}
	
	public abstract String getName(GameCharacter owner);
	
	public abstract boolean isFree(GameCharacter owner);
	
	public boolean isTakesVirginity() {
		return takesVirginity;
	}
	
}
