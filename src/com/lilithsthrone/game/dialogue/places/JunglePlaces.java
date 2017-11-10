package com.lilithsthrone.game.dialogue.places;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.Dominion;

/**
 * @since 0.1.0
 * @version 0.1.69
 * @author Innoxia
 */
public class JunglePlaces {

	public static final DialogueNodeOld PATH = new DialogueNodeOld("Jungle path", "", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "TODO Walking down a narrow path...";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld CLUB = new DialogueNodeOld("Club", "A place.", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld BROTHEL = new DialogueNodeOld("Brothel", "A place.", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld TENTACLE_QUEENS_LAIR = new DialogueNodeOld("Tentacle Queen's Lair", "A place.", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld DENSE_JUNGLE = new DialogueNodeOld("Dense Jungle", "A place.", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	// Entrances and exits:

	public static final DialogueNodeOld JUNGLE_ENTRANCE = new DialogueNodeOld("", "Travel to Dominion.", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "A half-overgrown sign near the road tells you that this path leads back to Dominion.";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Dominion", "Travel to Dominion. (This will be added later!)", null){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.DOMINION, Dominion.CITY_EXIT_TO_JUNGLE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
}
