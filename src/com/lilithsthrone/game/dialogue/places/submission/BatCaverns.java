package com.lilithsthrone.game.dialogue.places.submission;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.3
 * @version 0.2.3
 * @author Innoxia
 */
public class BatCaverns {

	public static final DialogueNodeOld STAIRCASE = new DialogueNodeOld("Winding Staircase", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "STAIRCASE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Submission", "Head back up to Submission."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.SUBMISSION, PlaceType.SUBMISSION_BAT_CAVERNS, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CAVERN_DARK = new DialogueNodeOld("Dark Cavern", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getAuthor() {
			return "Duner & Innoxia";
		}
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "CAVERN_DARK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null; //TODO explore
		}
	};
	
	public static final DialogueNodeOld CAVERN_LIGHT = new DialogueNodeOld("Bioluminescent Cavern", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getAuthor() {
			return "Duner & Innoxia";
		}
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "CAVERN_LIGHT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null; //TODO explore
		}
	};
	
	public static final DialogueNodeOld RIVER = new DialogueNodeOld("Underground River", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null; //TODO explore
		}
	};
	
	public static final DialogueNodeOld RIVER_BRIDGE = new DialogueNodeOld("Mushroom Bridge", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER_BRIDGE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null; //TODO explore
		}
	};
	
	public static final DialogueNodeOld RIVER_END = new DialogueNodeOld("Underground River", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null; //TODO explore
		}
	};
	
	public static final DialogueNodeOld SLIME_LAKE = new DialogueNodeOld("Slime Lake", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null; //TODO explore
		}
	};
}
