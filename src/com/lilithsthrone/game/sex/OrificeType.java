package com.lilithsthrone.game.sex;

import com.lilithsthrone.main.Main;

/**
 * @since 0.1.78
 * @version 0.1.90
 * @author Innoxia
 */
public enum OrificeType {
	
	MOUTH_PLAYER(2, true) {
		@Override
		public String getName() {
			return "mouth";
		}
		@Override
		public boolean isMouth() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPlayerFreeMouth();
		}
	},
	
	NIPPLE_PLAYER(2, true) {
		@Override
		public String getName() {
			return Main.game.getPlayer().getNippleName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isNipple() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPlayerFreeNipples();
		}
	},
	
	BREAST_PLAYER(1, true) {
		@Override
		public String getName() {
			return Main.game.getPlayer().getBreastName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isBreasts() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPlayerFreeBreasts();
		}
	},
	
	ASS_PLAYER(1, true) {
		@Override
		public String getName() {
			return Main.game.getPlayer().getAssName(false);
		}
		@Override
		public boolean isAss() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPlayerFreeAss();
		}
	},
	
	ANUS_PLAYER(2, true) {
		@Override
		public String getName() {
			return Main.game.getPlayer().getAnusName(false);
		}
		@Override
		public boolean isAnus() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPlayerFreeAnus();
		}
	},
	
	VAGINA_PLAYER(4, true) {
		@Override
		public String getName() {
			return Main.game.getPlayer().getVaginaName(false);
		}
		@Override
		public boolean isVagina() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPlayerFreeVagina();
		}
	},
	
	THIGHS_PLAYER(1, false) {
		@Override
		public String getName() {
			return "thighs";
		}
		@Override
		public boolean isThighs() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPlayerFreeThighs();
		}
	},
	
	URETHRA_PLAYER(1, true) {
		@Override
		public String getName() {
			return "urethra";
		}
		@Override
		public boolean isUrethra() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPlayerFreePenis();
		}
	},
	
	// Partner:
	
	MOUTH_PARTNER(2, false) {
		@Override
		public String getName() {
			return "mouth";
		}
		@Override
		public boolean isMouth() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPartnerFreeMouth();
		}
	},
	
	NIPPLE_PARTNER(2, false) {
		@Override
		public String getName() {
			return Sex.getPartner().getNippleName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isNipple() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPartnerFreeNipples();
		}
	},
	
	BREAST_PARTNER(1, false) {
		@Override
		public String getName() {
			return Sex.getPartner().getBreastName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isBreasts() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPartnerFreeBreasts();
		}
	},
	
	ASS_PARTNER(2, false) {
		@Override
		public String getName() {
			return Sex.getPartner().getAssName(false);
		}
		@Override
		public boolean isAss() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPartnerFreeAss();
		}
	},
	
	ANUS_PARTNER(2, false) {
		@Override
		public String getName() {
			return Sex.getPartner().getAnusName(false);
		}
		@Override
		public boolean isAnus() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPartnerFreeAnus();
		}
	},
	
	VAGINA_PARTNER(4, false) {
		@Override
		public String getName() {
			return Sex.getPartner().getVaginaName(false);
		}
		@Override
		public boolean isVagina() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPartnerFreeVagina();
		}
	},
	
	THIGHS_PARTNER(1, false) {
		@Override
		public String getName() {
			return "thighs";
		}
		@Override
		public boolean isThighs() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPartnerFreeThighs();
		}
	},
	
	URETHRA_PARTNER(1, false) {
		@Override
		public String getName() {
			return "urethra";
		}
		@Override
		public boolean isUrethra() { return true; }
		@Override
		public boolean isFree() {
			return Sex.isPartnerFreePenis();
		}
	};

	private float baseArousalWhenPenetrated;
	private boolean isPlayer;

	private OrificeType(float baseArousalWhenPenetrated, boolean isPlayer) {
		this.baseArousalWhenPenetrated = baseArousalWhenPenetrated;
		this.isPlayer = isPlayer;
	}
	
	public float getBaseArousalWhenPenetrated() {
		return baseArousalWhenPenetrated;
	}
	
	public boolean isPlayer() {
		return isPlayer;
	}
	
	public boolean isPlural() {
		return false;
	}
	
	public abstract boolean isFree();

	public boolean isMouth() { return false; }
	public boolean isNipple() { return false; }
	public boolean isBreasts() { return false; }
	public boolean isAss() { return false; }
	public boolean isAnus() { return false; }
	public boolean isVagina() { return false; }
	public boolean isThighs() { return false; }
	public boolean isUrethra() { return false; }
	
	public abstract String getName();
}
