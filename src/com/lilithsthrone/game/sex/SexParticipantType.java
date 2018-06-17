package com.lilithsthrone.game.sex;

/**
 * @since 0.1.98
 * @version 0.2.8
 * @author Innoxia
 */
public enum SexParticipantType {

	MISC {
		@Override
		public boolean isUsingSelfPenetrationType() {
			return false;
		}

		@Override
		public boolean isUsingSelfOrificeType() {
			return false;
		}
	},
	
	PITCHER {
		@Override
		public boolean isUsingSelfPenetrationType() {
			return true;
		}

		@Override
		public boolean isUsingSelfOrificeType() {
			return false;
		}
	},
	
	CATCHER {
		@Override
		public boolean isUsingSelfPenetrationType() {
			return false;
		}

		@Override
		public boolean isUsingSelfOrificeType() {
			return true;
		}
	},
	
	NORMAL {
		@Override
		public boolean isUsingSelfPenetrationType() {
			return false;
		}

		@Override
		public boolean isUsingSelfOrificeType() {
			return true;
		}
	},
	
	SELF {
		@Override
		public boolean isUsingSelfPenetrationType() {
			return true;
		}

		@Override
		public boolean isUsingSelfOrificeType() {
			return true;
		}
	};
	

	public abstract boolean isUsingSelfPenetrationType();
	public abstract boolean isUsingSelfOrificeType();
}
