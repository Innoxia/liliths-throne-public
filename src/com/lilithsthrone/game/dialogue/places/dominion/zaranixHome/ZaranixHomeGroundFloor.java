package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.StrengthLevel;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.89
 * @version 0.1.89
 * @author Innoxia
 */
public class ZaranixHomeGroundFloor {

//	

	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Home";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Outside Zaranix's home."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Knock door", "Knock on the door and wait for someone to answer.", OUTSIDE);

			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new ResponseEffectsOnly("Fly over fence", "Fly over the garden's fence and see if there's a way in through there.") {
						@Override
						public void effects() {
							Main.mainController.moveGameWorld(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_GARDEN_ENTRY, true);
						}
					};
				}
				return new ResponseEffectsOnly("Climb fence", "Climb over the garden's fence and see if there's a way in through there.") {
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_GARDEN_ENTRY, true);
					}
				};

			} else if (index == 3) {
				if(Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH) >= StrengthLevel.THREE_POWERFUL.getMinimumValue()) {
					return new Response("Kick down door", "Kick down the front door.", OUTSIDE) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				} else {
					return new Response("Kick down door", "You don't think you're strong enough to kick down such a sturdy-looking door. (Requires 35 strength.)", null);
				}

			} else if (index == 0) {
				return new Response("Leave", "Turn around and walk away.", DebugDialogue.getDefaultDialogueNoEncounter());

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Entrance Hall";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Entrance Hall."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld STAIRS = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Staircase";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Staircase."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
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
						+ "Corridor."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld GARDEN_ENTRY = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Garden";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Garden."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld GARDEN = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Garden";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Garden."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld GARDEN_ROOM = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Garden Room";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Garden room."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld CORRIDOR_MAID = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Corridor";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Maid!"
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
						+ "Room."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld LOUNGE = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Lounge";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Lounge."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
