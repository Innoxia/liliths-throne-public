package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.78
 * @version 0.2.7
 * @author Innoxia
 */
public enum SexAreaOrifice implements SexAreaInterface {
	
	MOUTH(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			2, 15,
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
			4, 2,
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
			25, 0,
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
	
	ASS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			25, 0,
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
			4, 4,
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
			4, 2,
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
			25, 0,
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
			4, 2,
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
			4, 2,
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
	private int cumLossPerMinute;
	private int cumAbsorptionPerMinute;
	private boolean takesPenisVirginity;

	/**
	 * @param baseArousalWhenPenetrated
	 * @param arousalChangePenetratedStretching
	 * @param arousalChangePenetratedTooLoose
	 * @param arousalChangePenetratedDry
	 * @param arousalChangePenetratingStretching
	 * @param arousalChangePenetratingTooLoose
	 * @param arousalChangePenetratingDry
	 * @param cumLossPerMinute The amount of cum or other fluids that leak out of this orifice every minute.
	 * @param cumAbsorptionPerMinute The amount of cum or other fluids that are absorbed into the character's body through this orifice every minute.
	 * @param takesPenisVirginity
	 */
	private SexAreaOrifice(float baseArousalWhenPenetrated,
			float arousalChangePenetratedStretching,
			float arousalChangePenetratedTooLoose,
			float arousalChangePenetratedDry,
			float arousalChangePenetratingStretching,
			float arousalChangePenetratingTooLoose,
			float arousalChangePenetratingDry,
			int cumLossPerMinute,
			int cumAbsorptionPerMinute,
			boolean takesPenisVirginity) {
		this.baseArousalWhenPenetrated = baseArousalWhenPenetrated;
		this.arousalChangePenetratedStretching = arousalChangePenetratedStretching;
		this.arousalChangePenetratedTooLoose = arousalChangePenetratedTooLoose;
		this.arousalChangePenetratedDry = arousalChangePenetratedDry;
		this.arousalChangePenetratingStretching = arousalChangePenetratingStretching;
		this.arousalChangePenetratingTooLoose = arousalChangePenetratingTooLoose;
		this.arousalChangePenetratingDry = arousalChangePenetratingDry;
		this.cumLossPerMinute = cumLossPerMinute;
		this.cumAbsorptionPerMinute = cumAbsorptionPerMinute;
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

	public int getCumLossPerMinute() {
		return cumLossPerMinute;
	}
	
	public int getCumAbsorptionPerMinute() {
		return cumAbsorptionPerMinute;
	}

	/**
	 * @return true If this orifice is a fully internal orifice, capable of taking penile virginity.<br/>
	 * Mouth, vagina, anus, urethras, and nipple are considered internal orifices.<br/>
	 * Ass, breasts, and thighs are not.
	 */
	public boolean isInternalOrifice() {
		return takesPenisVirginity;
	}
	
	public int getCharactersCumLossPerMinute(GameCharacter target) {
		int cumLost = this.getCumAbsorptionPerMinute();
		
		if(!target.isOrificePlugged(this)) {
			cumLost += (int) (this.getCumLossPerMinute()
					* (1 + 5*Util.getModifiedDropoffValue(target.getTotalFluidInArea(this), CumProduction.SEVEN_MONSTROUS.getMaximumValue())/CumProduction.SEVEN_MONSTROUS.getMaximumValue()));
		}
		
		return cumLost;
	}
	
}
