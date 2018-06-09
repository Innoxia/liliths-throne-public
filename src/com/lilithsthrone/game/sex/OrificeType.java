package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.78
 * @version 0.1.98
 * @author Innoxia
 */
public enum OrificeType {
	
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
			return Sex.isOrificeFree(owner, this) && Sex.isPenetrationTypeFree(owner, PenetrationType.TONGUE);
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
			return Sex.isOrificeFree(owner, this) && Sex.isPenetrationTypeFree(owner, PenetrationType.PENIS);
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
	private OrificeType(float baseArousalWhenPenetrated,
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

	public abstract String getName(GameCharacter owner);
	
	public abstract boolean isFree(GameCharacter owner);

	public boolean isPlural() {
		return false;
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

	public boolean isTakesPenisVirginity() {
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
