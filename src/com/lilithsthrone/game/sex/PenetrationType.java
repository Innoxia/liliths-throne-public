package com.lilithsthrone.game.sex;

import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.79
 * @author Innoxia
 */
public enum PenetrationType {
	
	PENIS_PLAYER(4, true, true) {
		@Override
		public String getName() {
			return Main.game.getPlayer().getPenisName();
		}

		@Override
		public boolean isFree() {
			return Sex.isPenetrationTypeFree(Main.game.getPlayer(), this);
		}
		
		@Override
		public boolean isPenis() {
			return true;
		}
	},
	
	TONGUE_PLAYER(2, true, false) {
		@Override
		public String getName() {
			return Main.game.getPlayer().getTongueName();
		}

		@Override
		public boolean isFree() {
			return Sex.isOrificeFree(Main.game.getPlayer(), OrificeType.MOUTH_PLAYER);
		}
		
		@Override
		public boolean isTongue() {
			return true;
		}
	},
	
	FINGER_PLAYER(1, true, false) {
		@Override
		public String getName() {
			return Main.game.getPlayer().getArmType().getFingersNamePlural(Main.game.getPlayer());
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree() {
			return Sex.isPenetrationTypeFree(Main.game.getPlayer(), this);
		}
		
		@Override
		public boolean isFinger() {
			return true;
		}
	},
	
	TAIL_PLAYER(2, true, true) {
		@Override
		public String getName() {
			return Main.game.getPlayer().getTailName();
		}

		@Override
		public boolean isFree() {
			return Sex.isPenetrationTypeFree(Main.game.getPlayer(), this);
		}
		
		@Override
		public boolean isTail() {
			return true;
		}
	},
	
	TENTACLE_PLAYER(3, true, true) {
		@Override
		public String getName() {
			return "tentacle";
		}

		@Override
		public boolean isFree() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isTentacle() {
			return true;
		}
	},
	
	// Partner:
	
	PENIS_PARTNER(4, false, true) {
		@Override
		public String getName() {
			return Sex.getActivePartner().getPenisName();
		}

		@Override
		public boolean isFree() {
			return Sex.isPenetrationTypeFree(Sex.getActivePartner(), this);
		}
		
		@Override
		public boolean isPenis() {
			return true;
		}
	},
	
	TONGUE_PARTNER(2, false, false) {
		@Override
		public String getName() {
			return Sex.getActivePartner().getTongueName();
		}

		@Override
		public boolean isFree() {
			return Sex.isOrificeFree(Sex.getActivePartner(), OrificeType.MOUTH_PARTNER);
		}
		
		@Override
		public boolean isTongue() {
			return true;
		}
	},
	
	FINGER_PARTNER(1, false, false) {
		@Override
		public String getName() {
			return Sex.getActivePartner().getArmType().getFingersNamePlural(Sex.getActivePartner());
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree() {
			return Sex.isPenetrationTypeFree(Sex.getActivePartner(), this);
		}
		
		@Override
		public boolean isFinger() {
			return true;
		}
	},
	
	TAIL_PARTNER(2, false, true) {
		@Override
		public String getName() {
			return Sex.getActivePartner().getTailName();
		}

		@Override
		public boolean isFree() {
			return Sex.isPenetrationTypeFree(Sex.getActivePartner(), this);
		}
		
		@Override
		public boolean isTail() {
			return true;
		}
	},
	
	TENTACLE_PARTNER(3, false, true) {
		@Override
		public String getName() {
			return "tentacle";
		}

		@Override
		public boolean isFree() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isTentacle() {
			return true;
		}
	};

	
	private float baseArousalWhenPenetrating;
	private boolean isPlayer, takesVirginity;

	private PenetrationType(float baseArousalWhenPenetrating, boolean isPlayer, boolean takesVirginity) {
		this.baseArousalWhenPenetrating = baseArousalWhenPenetrating;
		this.isPlayer = isPlayer;
		this.takesVirginity = takesVirginity;
	}

	public float getBaseArousalWhenPenetrating() {
		return baseArousalWhenPenetrating;
	}
	
	public boolean isPlayer() {
		return isPlayer;
	}
	
	public boolean isPlural() {
		return false;
	}
	
	public abstract boolean isFree();
	
	public boolean isTakesVirginity() {
		return takesVirginity;
	}
	
	public abstract String getName();
	
	// Yes, I know this is silly x_x
	
	public boolean isPenis() {
		return false;
	}
	public boolean isTongue() {
		return false;
	}
	public boolean isFinger() {
		return false;
	}
	public boolean isTail() {
		return false;
	}
	public boolean isTentacle() {
		return false;
	}

}
