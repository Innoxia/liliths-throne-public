package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.89
 * @version 0.1.89
 * @author Innoxia
 */
public class ZaranixHomeFirstFloor {
	
	public static final DialogueNodeOld STAIRS = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Staircase";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! Staircase leading down."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Downstairs", "Head downstairs to the ground floor of Zaranix's house.", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CORRIDOR = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Corridor";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! Corridor."
						+ " Just as grand as the ground floor."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld CORRIDOR_MAID_ATTACK = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Corridor";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! Maid!"
						+ " Kelly is here dusting."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld CORRIDOR_MAID_SUBDUED = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Corridor";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! Maid!"
						+ " Kelly is here dusting."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld ROOM = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Room";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! The door is locked."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld ZARANIX_ROOM = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! Zaranix!"
						+ " Lab is similar to Lilaya's, with numerous bottles & tables everywhere."
						+ " You see a door on the right-hand side of the room, and assume that that's where Arthur is being held."
						+ " Zaranix challenges you, and correctly assumes that you must have fought his maids to get here."
						+ " Combat."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld ZARANIX_COMBAT_LOSS = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! Zaranix explains Arthur's use; trying to create demonic TF potions."
						+ " He then tries one on you (or not, if you smash it), and fucks you (either through frustration at it not working, or anger at you smashing it)."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld ZARANIX_COMBAT_WIN = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Lab";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! Demand to know where Arthur is."
						+ " Zaranix unlocks door to Arthur's room, where he's been woken by the commotion."
						+ " You tell him to run to Lilaya's home."
						+ " Can then sex Zaranix."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
