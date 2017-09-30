package com.lilithsthrone.game.sex;

import com.lilithsthrone.main.Main;

/**
 * @since 0.1.78
 * @version 0.1.84
 * @author Innoxia
 */
public enum OrificeType {
	
	MOUTH_PLAYER(true) {
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
	
	NIPPLE_PLAYER(true) {
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
	
	BREAST_PLAYER(true) {
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
	
	ANUS_PLAYER(true) {
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
	
	VAGINA_PLAYER(true) {
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
	
	URETHRA_PLAYER(true) {
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
	
	MOUTH_PARTNER(false) {
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
	
	NIPPLE_PARTNER(false) {
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
	
	BREAST_PARTNER(false) {
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
	
	ANUS_PARTNER(false) {
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
	
	VAGINA_PARTNER(false) {
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
	
	URETHRA_PARTNER(false) {
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
	
	private boolean isPlayer;

	private OrificeType(boolean isPlayer) {
		this.isPlayer = isPlayer;
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
	public boolean isAnus() { return false; }
	public boolean isVagina() { return false; }
	public boolean isUrethra() { return false; }
	
	public abstract String getName();
}
