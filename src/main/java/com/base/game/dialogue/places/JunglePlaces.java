package com.base.game.dialogue.places;

import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.main.Main;

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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Dominion", "Travel to Dominion. (This will be added later!)", null){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(true);
					}
				};

			} else {
				return null;
			}
		}
	};
}
