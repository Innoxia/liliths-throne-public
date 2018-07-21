package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;

/**
 * @since 0.1.78
 * @version 0.2.7
 * @author Innoxia
 */
public enum SexAreaOrifice implements SexAreaInterface {
	
	MOUTH(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f ,-1f,
			true, true) {
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
			true, true) {
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
			false, false) {
		@Override
		public String getName(GameCharacter owner) { return owner.getBreastName(); }
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
			false, false) {
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
			true, true) {
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
			true, true) {
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
			false, false) {
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
			true, true) {
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
			true, true) {
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
	private boolean isIngestive;

	/**
	 * @param baseArousalWhenPenetrated
	 * @param arousalChangePenetratedStretching
	 * @param arousalChangePenetratedTooLoose
	 * @param arousalChangePenetratedDry
	 * @param arousalChangePenetratingStretching
	 * @param arousalChangePenetratingTooLoose
	 * @param arousalChangePenetratingDry
	 * @param takesPenisVirginity
	 */
	private SexAreaOrifice(float baseArousalWhenPenetrated,
			float arousalChangePenetratedStretching,
			float arousalChangePenetratedTooLoose,
			float arousalChangePenetratedDry,
			float arousalChangePenetratingStretching,
			float arousalChangePenetratingTooLoose,
			float arousalChangePenetratingDry,
			boolean takesPenisVirginity,
			boolean isIngestive) {
		this.baseArousalWhenPenetrated = baseArousalWhenPenetrated;
		this.arousalChangePenetratedStretching = arousalChangePenetratedStretching;
		this.arousalChangePenetratedTooLoose = arousalChangePenetratedTooLoose;
		this.arousalChangePenetratedDry = arousalChangePenetratedDry;
		this.arousalChangePenetratingStretching = arousalChangePenetratingStretching;
		this.arousalChangePenetratingTooLoose = arousalChangePenetratingTooLoose;
		this.arousalChangePenetratingDry = arousalChangePenetratingDry;;
		this.takesPenisVirginity = takesPenisVirginity;
		this.isIngestive = isIngestive;
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

	public boolean isTakesPenisVirginity() {
		return takesPenisVirginity;
	}

	public boolean isIngestive(){
		return isIngestive;
	}

	
}
