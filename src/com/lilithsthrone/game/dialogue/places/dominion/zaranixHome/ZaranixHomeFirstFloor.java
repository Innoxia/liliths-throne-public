package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;

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
	
	public static final DialogueNodeOld GARDEN_ROOM = new DialogueNodeOld("", "", false) {
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
	
	public static final DialogueNodeOld STREET_ROOM = new DialogueNodeOld("", "", false) {
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
	
	public static final DialogueNodeOld ZARANIX_ROOM = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Zaranix's Room";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Zaranix!"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
