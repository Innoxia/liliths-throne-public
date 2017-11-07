package com.lilithsthrone.game.dialogue.places;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.Dominion;

/**
 * @since 0.1.0
 * @version 0.1.75
 * @author Innoxia
 */
public class SewerPlaces {

	public static final DialogueNodeOld WALKWAYS = new DialogueNodeOld("Submission walkways", "", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "TODO Walkways in the dark...";
		}


		@Override
		public Response getResponse(int index) {
			return null;
		}
	};

	public static final DialogueNodeOld RAT_TUNNELS = new DialogueNodeOld("Rat tunnels", "Examine the tunnels.", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "TODO";
		}


		@Override
		public Response getResponse(int index) {
			return null;
		}
	};

	public static final DialogueNodeOld GAMBLING_DEN = new DialogueNodeOld("Gambling Den", ".", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "TODO";
		}


		@Override
		public Response getResponse(int index) {
			return null;
		}
	};

	public static final DialogueNodeOld IMP_PALACE = new DialogueNodeOld("Imp's Palace", ".", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "TODO";
		}


		@Override
		public Response getResponse(int index) {
			return null;
		}
	};

	// Entrance and exits:

	public static final DialogueNodeOld SEWER_ENTRANCE = new DialogueNodeOld("To town", "Enter the town.", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "There is a large staircase here, leading back up to the city of Dominion. You could go up the stairs if you wanted to.";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Dominion", "Head back up to Dominion. (This will be added after the first chapter of the story is complete!)", null){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.DOMINION, Dominion.CITY_EXIT_TO_SEWERS, true);
					}
				};

			} else {
				return null;
			}
		}
	};

}
