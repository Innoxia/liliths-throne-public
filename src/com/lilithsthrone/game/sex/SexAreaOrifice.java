package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;

/**
 * @since 0.1.78
 * @version 0.3.1
 * @author Innoxia
 */
public enum SexAreaOrifice implements SexAreaInterface {
	
	MOUTH(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			2/60f, 15/60f,
			true) {
		@Override
		public String getName(GameCharacter owner) {
			return "mouth";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this) && Sex.isPenetrationTypeFree(owner, SexAreaPenetration.TONGUE);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.MOUTH;
		}
	},
	
	NIPPLE(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getNippleName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.NIPPLES;
		}
	},
	
	BREAST(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getBreastName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.BREASTS;
		}
	},
	
	NIPPLE_CROTCH(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getNippleCrotchName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.NIPPLES_CROTCH;
		}
	},
	
	BREAST_CROTCH(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getBreastCrotchName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.BREASTS_CROTCH;
		}
	},
	
	ASS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner) {
			return "ass cheeks";
//			return owner.getAssName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.ASS;
		}
	},
	
	ANUS(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			4/60f, 4/60f,
			true) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getAnusName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.ANUS;
		}
	},
	
	VAGINA(4,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner) {
			return owner.getVaginaName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.VAGINA;
		}
	},
	
	THIGHS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner) {
			return "thighs";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.THIGHS;
		}
	},
	
	URETHRA_VAGINA(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner) {
			return "urethra";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.VAGINA;
		}
	},
	
	URETHRA_PENIS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner) {
			return "urethra";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Sex.isOrificeFree(owner, this) && Sex.isPenetrationTypeFree(owner, SexAreaPenetration.PENIS);
		}
		@Override
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.PENIS;
		}
	};

	private float baseArousalWhenPenetrated;
	private float arousalChangePenetratedStretching;
	private float arousalChangePenetratedTooLoose;
	private float arousalChangePenetratedDry;
	private float arousalChangePenetratingStretching;
	private float arousalChangePenetratingTooLoose;
	private float arousalChangePenetratingDry;
	private float cumLossPerSecond;
	private float cumAbsorptionPerSecond;
	private boolean takesPenisVirginity;

	/**
	 * @param baseArousalWhenPenetrated
	 * @param arousalChangePenetratedStretching
	 * @param arousalChangePenetratedTooLoose
	 * @param arousalChangePenetratedDry
	 * @param arousalChangePenetratingStretching
	 * @param arousalChangePenetratingTooLoose
	 * @param arousalChangePenetratingDry
	 * @param cumLossPerSecond The amount of cum or other fluids that leak out of this orifice every second.
	 * @param cumAbsorptionPerSecond The amount of cum or other fluids that are absorbed into the character's body through this orifice every second.
	 * @param takesPenisVirginity
	 */
	private SexAreaOrifice(float baseArousalWhenPenetrated,
			float arousalChangePenetratedStretching,
			float arousalChangePenetratedTooLoose,
			float arousalChangePenetratedDry,
			float arousalChangePenetratingStretching,
			float arousalChangePenetratingTooLoose,
			float arousalChangePenetratingDry,
			float cumLossPerSecond,
			float cumAbsorptionPerSecond,
			boolean takesPenisVirginity) {
		this.baseArousalWhenPenetrated = baseArousalWhenPenetrated;
		this.arousalChangePenetratedStretching = arousalChangePenetratedStretching;
		this.arousalChangePenetratedTooLoose = arousalChangePenetratedTooLoose;
		this.arousalChangePenetratedDry = arousalChangePenetratedDry;
		this.arousalChangePenetratingStretching = arousalChangePenetratingStretching;
		this.arousalChangePenetratingTooLoose = arousalChangePenetratingTooLoose;
		this.arousalChangePenetratingDry = arousalChangePenetratingDry;
		this.cumLossPerSecond = cumLossPerSecond;
		this.cumAbsorptionPerSecond = cumAbsorptionPerSecond;
		this.takesPenisVirginity = takesPenisVirginity;
	}

	@Override
	public boolean isOrifice() {
		return true;
	}
	
	public float getBaseArousalWhenPenetrated() {
		return baseArousalWhenPenetrated;
	}
	
	public float getArousalChangePenetratedStretching() {
		return arousalChangePenetratedStretching;
	}

	public float getArousalChangePenetratedTooLoose() {
		return arousalChangePenetratedTooLoose;
	}

	public float getArousalChangePenetratedDry() {
		return arousalChangePenetratedDry;
	}

	public float getArousalChangePenetratingStretching() {
		return arousalChangePenetratingStretching;
	}

	public float getArousalChangePenetratingTooLoose() {
		return arousalChangePenetratingTooLoose;
	}

	public float getArousalChangePenetratingDry() {
		return arousalChangePenetratingDry;
	}

	public float getCumLossPerSecond() {
		return cumLossPerSecond;
	}
	
	public float getCumAbsorptionPerSecond() {
		return cumAbsorptionPerSecond/60f;
	}

	/**
	 * @return true If this orifice is a fully internal orifice, capable of taking penile virginity.<br/>
	 * Mouth, vagina, anus, urethras, and nipple are considered internal orifices.<br/>
	 * Ass, breasts, and thighs are not.
	 */
	public boolean isInternalOrifice() {
		return takesPenisVirginity;
	}
	
	public float getCharactersCumLossPerSecond(GameCharacter target) {
		float cumLost = this.getCumAbsorptionPerSecond();
		float fluidInArea = target.getTotalFluidInArea(this);
		// The rate obviously decreases as the fluid drains out, but assuming if the drain was applied all at once, it would take about 5.5 hours to all drain out (not factoring in absorption or natural loss):
		float secondPercentageLoss = fluidInArea/20_000;
		
		if(!target.isOrificePlugged(this)) {
			cumLost += this.getCumLossPerSecond() + secondPercentageLoss;
		}
		return cumLost;
	}
	
}
