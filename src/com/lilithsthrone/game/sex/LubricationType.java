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
	
	// I'm using these two values to represent vaginal lubrication, but due to the nature of the game, a similar kind of lubrication can be gained from having fuckable nipples or a wet asshole.
	// Basically, this is the same slippery liquid that you get when getting wet.
	
	PLAYER_NATURAL_LUBRICATION(true) {
		@Override
		public String getName() {
			return "your juices";
		}
	},
	PARTNER_NATURAL_LUBRICATION(true) {
		@Override
		public String getName() {
			if(Sex.getActivePartner()!=null) {
				return Sex.getActivePartner().getName("the")+"'s juices";
			} else {
				return "juices";
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
