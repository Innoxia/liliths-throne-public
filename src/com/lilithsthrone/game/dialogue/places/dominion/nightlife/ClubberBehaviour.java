package com.lilithsthrone.game.dialogue.places.dominion.nightlife;

import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.10
 * @version 0.2.10
 * @author Innoxia
 */
public enum ClubberBehaviour {

	INTRODUCTION() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_MAIN_AREA;
		}
	},
	
	BAR_DRINK() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_BAR;
		}
	},
	BAR_TALK() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_BAR;
		}
	},
	BAR_FLIRT() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_BAR;
		}
	},
	BAR_KISS() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_BAR;
		}
	},
	BAR_GROPE() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_BAR;
		}
	},
	BAR_INVITE_HOME() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_BAR;
		}
	},
	
	DANCE() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_DANCE_FLOOR;
		}
	},
	DANCE_KISS() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_DANCE_FLOOR;
		}
	},
	DANCE_GROPE() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_DANCE_FLOOR;
		}
	},
	
	SIT_DOWN_TALK() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_SEATING_AREA;
		}
	},
	SIT_DOWN_FLIRT() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_SEATING_AREA;
		}
	},
	SIT_DOWN_KISS() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_SEATING_AREA;
		}
	},
	SIT_DOWN_FOOTSIE() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_SEATING_AREA;
		}
	},
	SIT_DOWN_SEX() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_SEATING_AREA;
		}
	},
	SIT_DOWN_INVITE_HOME() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_SEATING_AREA;
		}
	},
	
	TOILETS() {
		@Override
		public PlaceType getPlaceType() {
			return PlaceType.WATERING_HOLE_TOILETS;
		}
	};
	
	public abstract PlaceType getPlaceType();
	
}
