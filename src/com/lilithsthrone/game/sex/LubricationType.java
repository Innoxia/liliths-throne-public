package com.lilithsthrone.game.sex;

import com.lilithsthrone.main.Main;

/**
 * @since 0.1.6?
 * @version 0.1.87
 * @author Innoxia
 */
public enum LubricationType {
	
	PLAYER_SALIVA(false) {
		@Override
		public String getName() {
			return "your saliva";
		}
	},
	PARTNER_SALIVA(false) {
		@Override
		public String getName() {
			if(Sex.getActivePartner()!=null) {
				return Sex.getActivePartner().getName("the")+"'s saliva";
			} else {
				return "saliva";
			}
		}
	},
	
	PLAYER_MILK(false) {
		@Override
		public String getName() {
			return "your milk";
		}
	},
	PARTNER_MILK(false) {
		@Override
		public String getName() {
			if(Sex.getActivePartner()!=null) {
				return Sex.getActivePartner().getName("the")+"'s milk";
			} else {
				return "milk";
			}
		}
	},
	
	PLAYER_PRECUM(false) {
		@Override
		public String getName() {
			return "your precum";
		}
	},
	PARTNER_PRECUM(false) {
		@Override
		public String getName() {
			if(Sex.getActivePartner()!=null) {
				return Sex.getActivePartner().getName("the")+"'s precum";
			} else {
				return "precum";
			}
		}
	},
	
	PLAYER_CUM(false) {
		@Override
		public String getName() {
			return "your "+Main.game.getPlayer().getCumName();
		}
	},
	PARTNER_CUM(false) {
		@Override
		public String getName() {
			if(Sex.getActivePartner()!=null) {
				return Sex.getActivePartner().getName("the")+"'s "+Sex.getActivePartner().getCumName();
			} else {
				return "cum";
			}
		}
	},
	OTHER_CUM(false) {
		@Override
		public String getName() {
			return "cum";
		}
	},
	
	
	PLAYER_GIRLCUM(false) {
		@Override
		public String getName() {
			return "your girlcum";
		}
	},
	PARTNER_GIRLCUM(false) {
		@Override
		public String getName() {
			if(Sex.getActivePartner()!=null) {
				return Sex.getActivePartner().getName("the")+"'s girlcum";
			} else {
				return "girlcum";
			}
		}
	},
	
	// This is only present if the anus has been transformed to be 'wetter' than usual:
	
	PLAYER_ANAL_LUBE(false) {
		@Override
		public String getName() {
			return "your anal lubricant";
		}
	},
	PARTNER_ANAL_LUBE(false) {
		@Override
		public String getName() {
			if(Sex.getActivePartner()!=null) {
				return Sex.getActivePartner().getName("the")+"'s anal lubricant";
			} else {
				return "anal lubricant";
			}
		}
	},
	
	OTHER(false) {
		@Override
		public String getName() {
			return "lubrication";
		}
	};
	
	private boolean plural;
	
	private LubricationType(boolean plural){
		this.plural=plural;
	}
	
	public boolean isPlural() {
		return plural;
	}
	
	public abstract String getName();
}
