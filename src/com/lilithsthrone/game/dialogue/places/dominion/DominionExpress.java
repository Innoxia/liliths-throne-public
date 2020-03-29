package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class DominionExpress {
	
	/*
	Natalya training:
		She intends to train player into one of her slaves
		Discover that this involves fetish changes.
		She trains her slaves to hate the concept of pregnancy (pregnant centaurs can't work), and transforms female slaves into shemales.
		Trains them to love receiving anal, and for males to love giving anal.
		Player training involves (interspersed with physical exercises):
			(All scenes replaced with Natalya overseeing player service male centaur if player is androphilic.)
			Servicing her cock with hands
				penis liking, submissive liking
			Ball worship with hands & mouth
				cum liking, submissive loving
			Only proceeds after player has been transformed into centaur. All options are preceded by centaur check - if player is not centaur, Natalya restarts their training
				If female, become futa, if not, then male
				Futa:
					Natalya worships ass
						ass-receiving liking, pussy disliking
					Natalya performs anilingus (she tries to tempt player into presenting their pussy)
						ass-receiving loving, pussy hating
					Natalya fucks ass (she tries to tempt player into presenting their pussy)
						vaginal/impregnation hatred
						Pussy transformed away
				Both:
					Worshipping her ass (a beautiful female centaur tries to tempt the player into kissing them instead)
						ass liking
					Anilingus (she stands beside female centaur and tries to tempt player into servicing their pussy)
						ass loving, vaginal/impregnation disliking
					Fucking her ass (she stands beside female centaur and tries to tempt player into fucking their pussy)
						vaginal/impregnation hatred
		Training complete, player can now work
		If feminine, they work in office (get mounted by males & can mount others), if male, they deliver goods (and can mount females).
		Weekly work rewarded by collar colour for next week:
			Steel collars can only be used
			Bronze collars can do anything with other slaves
			Three silver collars can worship Natalya's cock/balls/ass
			Two gold collars can fuck/be fucked when Natalya allows it.
			The single platinum collar allows the wearer to fuck/be fucked by Natalya whenever they want.
	*/
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			//TODO dismiss companions & elementals as requirement for entry
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode STORAGE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STORAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode STABLES = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
