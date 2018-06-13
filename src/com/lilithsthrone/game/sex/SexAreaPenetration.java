package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.1.0
 * @version 0.2.7
 * @author Innoxia
 */
public enum SexAreaPenetration implements SexAreaInterface {
	
	PENIS(4, true) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getPenisName();
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, SexAreaOrifice.URETHRA_PENIS) && Sex.isPenetrationTypeFree(owner, this);
		}
	},
	
	TONGUE(2, false) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getTongueName();
		}

		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, SexAreaOrifice.MOUTH) && Sex.isPenetrationTypeFree(owner, this);
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

	private SexAreaPenetration(float baseArousalWhenPenetrating, boolean takesVirginity) {
		this.baseArousalWhenPenetrating = baseArousalWhenPenetrating;
		this.takesVirginity = takesVirginity;
	}

	@Override
	public boolean isOrifice() {
		return false;
	}
	
	public float getBaseArousalWhenPenetrating() {
		return baseArousalWhenPenetrating;
	}
	
	public boolean isTakesVirginity() {
		return takesVirginity;
	}
	
}
