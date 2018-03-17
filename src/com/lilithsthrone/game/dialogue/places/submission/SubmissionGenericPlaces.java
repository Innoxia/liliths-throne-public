package com.lilithsthrone.game.dialogue.places.submission;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.1
 * @author Innoxia
 */
public class SubmissionGenericPlaces {

	public static final DialogueNodeOld WALKWAYS = new DialogueNodeOld("Walkways", "", false) {
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
			return "<p>"
						+ "The main tunnels of Submission are surprisingly spacious, clean and full of life."
						+ " A deep channel of opaque, greenish water flows at a steady pace down the centre of each passage, and to either side, well-maintained wooden walkways have been built up against the grey stone walls."
						+ " Numerous bridges cross the watery divide at regular intervals, and every hundred metres or so, a series of stone steps trails down into the murky river to provide easy access for Submission's aquatic inhabitants."
					+ "</p>"
					+ "<p>"
						+ "The damp atmosphere holds just enough chill to gently caress any exposed skin, but, thanks to the wide iron grates built into the ceiling, the air doesn't feel stagnant down here."
						+ " Looking up trough the iron bars, you're granted an unobstructed view of the sky, and, every now and then, up the occasional skirt."
						+ " The dizzying network of pipes and valves that run along the upper-half of the walls makes you feel sorry for whoever has to map and maintain them."
					+ "</p>"
					+ "<p>"
						+ "Houses and shops of all kinds have been tunnelled out and built into the walls, and, while not as busy as Dominion, there are enough people ambling by to fill the tunnels with a continuous background drone of chatter and laughter."
						+ " There seems to be a heavy presence of Enforcers down here, and you can't go more than five minutes without seeing a patrol marching by."
					+ "</p>"
					+ "<p>"
						+ "Despite Submission being their home, you only see the occasional imp in this area;"
						+ " evidence that they're so much of a nuisance that the vast majority of them are banished to the dark tunnels that branch off on either side of this main passage."
						+ " The vast majority of people that you pass are slimes, alligator-morphs, and rat-morphs, but there are also plenty of regular morphs eking out a living down here."
					+ "</p>"
					+ (Math.random()<0.2f
						?"<p><i>"
							+UtilText.returnStringAtRandom(
								"Did that wave just wink at you? No, it must have been your imagination...",
								"Looking over the edge of the walkway, you see an alligator-girl slowly lowering herself down into the water beneath you. Catching your eye, she winks and disappears beneath the surface...",
								"Looking over the edge of the walkway, you see an alligator-boy slowly lowering himself down into the water beneath you. Catching your eye, he winks and disappears beneath the surface...",
								"In his haste, a passing rat-boy almost bumps into you. He scowls and lets out a muffled curse before quickly scurrying away...",
								"In her haste, a passing rat-girl almost bumps into you. She scowls and lets out a muffled curse before quickly scurrying away...")
							+"</i></p>"
						:"");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld TUNNEL = new DialogueNodeOld("Tunnels", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			return "Duner";
		}

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Unlike the main passages of Submission, the shaded alcoves and wide pipe openings make these dark, claustrophobic tunnels the perfect place for an ambush."
						+ " Filmy perspiration drips from the walls, and you flinch every time a drop touches you."
					+ "</p>"
					+ "<p>"
						+ "Kicking up rubbish and random debris as you quickly move through the dilapidated area, each of your steps sounds as loud as a thunder-clap to your ears."
						+ " You can't help but shake the feeling that someone's lying in wait around each corner, waiting for their chance to lunge out of the shadows and attack..."
					+ "</p>");
			
			for(GameCharacter npc : Main.game.getCharactersPresent()) {
					UtilText.nodeContentSB.append(
							UtilText.parse(npc,
									"<p style='text-align:center;'>"
									+ "<b style='color:"+Femininity.valueOf(npc.getFemininityValue()).getColour().toWebHexString()+";'>[npc.A_femininity]</b>"
									+ " <b style='color:"+npc.getRaceStage().getColour().toWebHexString()+";'>[npc.raceStage]</b>"
									+ " <b style='color:"+npc.getRace().getColour().toWebHexString()+";'>[npc.race]</b> <b>is prowling this area!</b></p>"
									
									+ "<p style='text-align:center;'>"));
							
					// Combat:
					if(npc.getFoughtPlayerCount()>0) {
						UtilText.nodeContentSB.append(
								UtilText.parse(npc,"You have <b style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+";'>fought</b> [npc.herHim] <b>"));
								
								if(npc.getFoughtPlayerCount()==1) {
									UtilText.nodeContentSB.append("once.");
								} else if(npc.getFoughtPlayerCount()==2) {
									UtilText.nodeContentSB.append("twice.");
								} else {
									UtilText.nodeContentSB.append(Util.intToString(npc.getFoughtPlayerCount())+" times.");
								}
								
						UtilText.nodeContentSB.append("</b>"
										+ "</br>"
										+ "You have <b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>won</b> <b>");
								
								if(npc.getLostCombatCount()==1) {
									UtilText.nodeContentSB.append("once.");
								} else if(npc.getLostCombatCount()==2) {
									UtilText.nodeContentSB.append("twice.");
								} else {
									UtilText.nodeContentSB.append(Util.intToString(npc.getLostCombatCount())+" times.");
								}
										
						UtilText.nodeContentSB.append("</b>"
								+ "</br>"
								+ "You have <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>lost</b> <b>");
								if(npc.getWonCombatCount()==1) {
									UtilText.nodeContentSB.append("once.");
								} else if(npc.getWonCombatCount()==2) {
									UtilText.nodeContentSB.append("twice.");
								} else {
									UtilText.nodeContentSB.append(Util.intToString(npc.getWonCombatCount())+" times.");
								}
								UtilText.nodeContentSB.append("</b></p>");
					}
					
					// Sex:
					if(npc.getSexPartners().containsKey(Main.game.getPlayer().getId())) {
						UtilText.nodeContentSB.append("<p style='text-align:center;'>");
								
						UtilText.nodeContentSB.append(
								UtilText.parse(npc,
										"You have had <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>submissive sex</b> with [npc.herHim]<b> "));
						
								if(npc.getSexAsDomCount()==1) {
									UtilText.nodeContentSB.append("once.");
								} else if(npc.getSexAsDomCount()==2) {
									UtilText.nodeContentSB.append("twice.");
								} else {
									UtilText.nodeContentSB.append(Util.intToString(npc.getSexAsDomCount())+" times.");
								}
								
						UtilText.nodeContentSB.append(
								UtilText.parse(npc,
										"</b>"
										+ "</br>"
										+ "You have had <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>dominant sex</b> with  [npc.herHim]<b> "));
						
								if(npc.getSexAsSubCount()==1) {
									UtilText.nodeContentSB.append("once.");
								} else if(npc.getSexAsSubCount()==2) {
									UtilText.nodeContentSB.append("twice.");
								} else {
									UtilText.nodeContentSB.append(Util.intToString(npc.getSexAsSubCount())+" times.");
								}
								UtilText.nodeContentSB.append("</b></p>");
					}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 6) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the tunnels. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
							@Override
							public void effects() {
								DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld BAT_CAVERNS = new DialogueNodeOld("Bat Caverns", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "TODO: Entrance to the bat caverns - a series of caves deep underground.</br>"
						+ "This will be a mini-area (with bat-morphs in it), which will contain a couple of side-quests."
					+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld RAT_WARREN = new DialogueNodeOld("The Rat Warren", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "TODO: Entrance to the Rat Warren - a criminal gang's headquarters.</br>"
					+ "There will be a large side-quest related to this building (which will have an internal map)."
				+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld GAMBLING_DEN = new DialogueNodeOld("Gambling Den", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "TODO: An especially large and busy section of Submission - a huge two-story building dominates the area, with a sign proclaiming it to be the 'Gambling Den'.</br>"
						+ "This will be a commercial hub for Submission, which will include a few normal shops (of poorer quality than the ones in the Shopping Arcade), and one large gambling hall."
				+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld LILIN_PALACE = new DialogueNodeOld("Lyssieth's Palace", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "TODO: Submission opens up into a large underground cavern here, and a massive, Gothic palace has been carved into the stone wall."
					+ " A dark chasm separates the palace from where you're standing."
					+ " A heavy wooden drawbridge is the only means of entry."
				+ "</p>"
				+ "<p>"
					+ "- Demon guards are everywhere.</br>"
					+ "- Get stopped by the guard and asked what business you have here.</br>"
					+ "- Leads into Lyssieth's/Submission's main quest."
				+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld IMP_FORTRESS_1 = new DialogueNodeOld("Imp Fortress", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "TODO: A large underground cave, and in the middle, a crude fortress has been built out of old wooden planks and pieces of sheet metal.</br>"
					+ "Each of the three imp fortresses will be a repeatable quest (clearing the fortress), which, once cleared, will pacify the surrounding tunnels for a week or so."
				+ "</p>"
				+ "<p>"
					+ "This particular fortress will be run by a single, very tough alpha-imp."
				+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld IMP_FORTRESS_2 = new DialogueNodeOld("Imp Fortress", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "TODO: A large underground cave, and in the middle, a crude fortress has been built out of old wooden planks and pieces of sheet metal.</br>"
					+ "Each of the three imp fortresses will be a repeatable quest (clearing the fortress), which, once cleared, will pacify the surrounding tunnels for a week or so."
				+ "</p>"
				+ "<p>"
					+ "This particular fortress will be made up of several groups of imps, who all share the same territory."
				+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld IMP_FORTRESS_3 = new DialogueNodeOld("Imp Fortress", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "TODO: A large underground cave, and in the middle, a crude fortress has been built out of old wooden planks and pieces of sheet metal.</br>"
					+ "Each of the three imp fortresses will be a repeatable quest (clearing the fortress), which, once cleared, will pacify the surrounding tunnels for a week or so."
				+ "</p>"
				+ "<p>"
					+ "This particular fortress will be a female-only imp clan, who prefer to use seduction rather than fighting physically."
				+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	// Entrance and exits:

	public static final DialogueNodeOld SEWER_ENTRANCE = new DialogueNodeOld("Enforcer Checkpoint", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "TODO: Enforcer checkpoint - need to talk to Enforcer before being given access to Submission. (Explains the basics of how Submission society works.)"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Dominion", "Head back up to Dominion."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.DOMINION, PlaceType.DOMINION_EXIT_TO_SEWERS, true);
					}
				};

			} else {
				return null;
			}
		}
	};

}
