package com.base.game.dialogue.places.dominion.slaverAlley;

import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.responses.ResponseTrade;
import com.base.game.dialogue.utils.UtilText;
import com.base.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class SlaverAlleyDialogue {

	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("Slaver Alley", "-", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "As you navigate through the labyrinthine passageways of Dominion's back-alleys, you start to hear the faint murmur of voices somewhere before you."
					+ " Proceeding with caution, you slowly step around the next corner, and find yourself looking into a small, well-kept courtyard."
				+ "</p>"
				+ "<p>"
					+ "Clean, grey cobblestones line the floor, and in the middle of the little square, surrounded by wooden benches, a bubbling stream of water cascades out of a finely-crafted stone fountain."
					+ " On three of its sides, multiple narrow entrances link this area to the surrounding alleyways, but it's what's on the fourth that draws your attention."
					+ " A huge, open gateway, flanked by a pair of muscular horse-boys, has been built into the wall, and it's through this opening that the sound of a busy marketplace can be heard."
				+ "</p>"
				+ "<p>"
					+ "As you step closer, neither of the guards react to your presence, and you notice that their attention is focused solely on who's trying to leave the area beyond."
					+ " Crossing the courtyard, you see that the words 'Slaver Alley' have been cast into the dull iron framing of the gate, and on the walls to either side,"
						+ " promotional posters for different vendors have been plastered over the red bricks."
				+ "</p>"
				+ "<p>"
					+ "It looks as though any member of the public is free to come and go as they please."
				+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Slaver Alley", "Step through the gate and enter Slaver Alley."){
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
	
	public static final DialogueNodeOld GATEWAY = new DialogueNodeOld("Gateway", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in the gateway that links Slaver Alley to the rest of Dominion."
						+ " On either side of you, horse-boy guards keep a watchful eye on anyone that passes through here; obviously on the lookout for runaway slaves."
					+ "</p>"
					+ "<p>"
						+ "A touristy-looking information board, looking quite out of place with its surroundings, has been fixed to a nearby wall, and upon reading it,"
							+ " you discover that a powerful arcane shield has been erected over the entire area, which prevents the effects of any on-going arcane storms from being felt by any of the marketplace's occupants."
						+ " True enough, as you glance up at the sky above, you see the faint pink crackle of the shield's arcane energy, reassuring you that you needen't worry about attacks from any horny customers."
					+ "</p>"
					+ "<p>"
						+ "There are only a couple of other pieces of information that are of any real relevance to you."
						+ " In large red writing at the top of the information board, you read that you must be in possession of a slaver license before being able to buy or sell slaves here."
						+ " Underneath, the sentence telling you that you can obtain a license from the Slaver Administration building is crossed out, and in scrawling writing, the words"
						+ " 'Slaver Licenses are not being issued' has been written beneath."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Leave", "Step back out into Dominion's alleyways."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(true);
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEYWAY = new DialogueNodeOld("Alleyway", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 2;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You find yourself walking down one of Slaver Alley's numerous interconnected passageways."
						+ " Lined with clean, grey cobblestone, and being almost as wide as a normal street, they're quite unlike the regular sort of passages that make up the rest of Dominion's back-alleys.");
			
			if (Main.game.isDayTime()) {
				UtilText.nodeContentSB.append(
							" Colourful canvas awnings have been unfurled from the sides of the surrounding buildings, and, looking up through the gap in the middle, you see a faint pink crackle;"
									+ " evidence of the shield that protects Slaver Alley from arcane storms."
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
							" A series of arcane-powered lights cast a flickering glow over the area, and as you look up at the night sky, you see a faint pink crackle;"
									+ " evidence of the shield that protects Slaver Alley from arcane storms."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Although not as busy as Dominion's main streets, there are still a significant amount of people wandering about,"
							+ " and you often have to push your way through small crowds that inexplicably decide to loiter right in the middle of the path."
						+ " The close proximity of the surrounding buildings echoes and amplifies the crowd's inane chatter, making Slaver Alley feel more alive than any other part of the city you've wandered through."
					+ "</p>"
					+ "<p>"
						+ "The most striking feature of this area is the manner of goods that are sold here."
						+ " Down each passageway, multiple wooden stands have been erected, and on top each one, slaves of all different races and sexes are being publicly displayed."
						+ " You see that information boards have been attached to each one of these stands, giving you instructions as to which shop you need to visit in order to negotiate the purchase of the wares on display."
					+ "</p>"
					+ "<p>"
						+ "Glancing up at the slaves as you pass, you notice that the one thing they all have in common is that they're all wearing metal collars of some sort or another."
						+ " Apart from that peculiar accessory, the vast majority of them are completely naked, although you do see a few that have been dressed in skimpy pieces of highly-revealing clothing."
					+ "</p>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld MARKET_STALL = new DialogueNodeOld("Slaver's shop", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding that you'd like to take a look inside one of the shops, you approach the entrance to the nearest building."
						+ " In order to display yet more slaves to passing customers, you see that a series of large glass windows have been installed into the walls before you."
						+ " As you walk up to the entrance, some of the slaves standing behind these windows glance your way; no doubt wondering if you'll be their next owner."
					+ "</p>"
					+ "<p>"
						+ "Pushing open the door, you step inside the shop, and find that the interior is surprisingly clean, well-lit, and airy."
						+ " Yet more slaves line the walls around you, and in the middle of the room, the shop's owner cries out a greeting from behind a circular wooden desk."
					+ "</p>"
					+ "<p>"
						+ "After browsing the wares for a moment, you decide that you've had enough."
						+ " After all, without a permit, you're unable to buy anything."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld AUCTION_BLOCK = new DialogueNodeOld("Auctioning block", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "The source of most of the noise heard throughout Slaver Alley stands before you."
						+ " Surrounded on all sides by bustling crowds, a huge wooden platform has been erected in the centre of a wide courtyard."
					+ "</p>"
					+ "<p>"
						+ "Walking up to one of several information boards that are scattered throughout the area, you read that this is the place where slave auctions are held."
						+ " A piece of paper has been crudely stuck to the bottom of the board, and, looking closer, you read:"
					+ "</p>"
					+ "<p style='text-align:center;'><i>"
						+ "Slave auctions are <b>closed</b> until further notice."
					+ "</i></p>";
		}

		@Override
		public Response getResponse(int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld SLAVERY_ADMINISTRATION_EXTERIOR = new DialogueNodeOld("Slaver's shop", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "A large metal sign, bearing the words 'Slavery Administration', is prominently displayed above the entrance to the building before you."
						+ " Its clean, white-washed walls and heavy oak doors set this particular establishment apart from the rest of the stores in Slaver Alley."
					+ "</p>"
					+ "<p>"
						+ "Noticing a little information board attached to one side of the entrance, you walk up and read what it says."
						+ " From what you can gather, it appears as though this is an official government building, in which all matters relating to the ownership of slaves, licenses, and businesses dealing in slave-trading are handled."
						+ " A little piece of paper has been stuck to the bottom of the board, which reads; 'Slaver Licenses are not being issued!'"
					+ "</p>"
					+ (Main.game.getPlayer().isSlaveTrader()
						?"<p>"
							+ "Being in possession of a slaver license yourself, you could enter the Slavery Administration building and make use of its services."
						+ "</p>"
						:"<p>"
							+ "Although it's doubtful that you'd be able to make use of any of its services, it looks like any member of the public is free to enter the building."
						+ "</p>");
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Enter", "Step inside the 'Slavery Administration' building.", SLAVERY_ADMINISTRATION);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SLAVERY_ADMINISTRATION = new DialogueNodeOld("Slavery Administration", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isSlaveTrader()) {
				return "<p>"
						+ "The heavy oak doors of the 'Slavery Administration' building are wide open, and, stepping through the inviting entranceway, you find yourself standing in a large, cavernous entrance hall."
						+ " Rows of marble pillars line the edges of the room, with little wooden benches interspersed between them."
						+ " The only other piece of furtinture to be seen is a long, black-marble desk, behind which the black-haired cat-boy, Nikki, is grinning at you with mischievous eyes."
					+ "</p>"
					+ "<p>"
						+ "[nikki.speech(Ah, if it isn't my <i>favourite</i> customer, [pc.name]!)]"
						+ " he shouts, beckoning you over to the desk,"
						+ " [nikki.speech(What can I help you with today?)]"
					+ "</p>"
					+ "<p>"
						+ "Walking forwards, you return Nikki's greeting,"
						+ " [pc.speech(Hi Nikki.)]"
					+ "</p>"
					+ "<p>"
						+ "[nikki.speech(As an owner of a slaver license, I'm pleased to offer you my services,)]"
						+ " he says, standing up to reveal his tiny feline cock,"
						+ " [nikki.speech(I've got slave collars, with the appropriate paperwork already completed, as well as a large selection of clothing suitable for your slaves!)]"
					+ "</p>"
					+ "<p>"
						+ "As you come to a halt in front of the black marble desk, Nikki grins expectantly at you,"
						+ " [nikki.speech(Or, if you'd like, we can discuss the management and handling of your property."
							+ " Remember, any new slaves you capture will be delivered here first."
							+ " All the costs of collection and transport are covered in the cost of each slave collar, so just let me know if you want to have your slaves moved elsewhere.)]"
					+ "</p>"
					+ "<p>"
						+ "Thanking Nikki, you wonder what you do next..."
					+ "</p>";
				
			} else {
				if(!Main.game.getDialogueFlags().nikkiIntroduced) {
					return "<p>"
								+ "The heavy oak doors of the 'Slavery Administration' building are wide open, and, stepping through the inviting entranceway, you find yourself standing in a large, cavernous entrance hall."
								+ " Rows of marble pillars line the edges of the room, with little wooden benches interspersed between them."
								+ " The only other piece of furtinture to be seen is a long, black-marble desk, behind which a black-haired cat-boy is grinning at you with mischievous eyes."
							+ "</p>"
							+ "<p>"
								+ "[nikki.speech(Welcome!)]"
								+ " he shouts, beckoning you over to the desk,"
								+ " [nikki.speech(Can I help you with anything?)]"
							+ "</p>"
							+ "<p>"
								+ "Walking forwards, you return the cat-boy's greeting,"
								+ " [pc.speech(Hello, I was just looking around.)]"
							+ "</p>"
							+ "<p>"
								+ "[nikki.speech(I'm afraid that there's not really much to see here."
								+ " All the <i>fun</i> happens in the holding cells, and they're off-limits. Unless you've got a slaver license, there's really not much I can offer you, except for a good day!)]"
								+ " he says, standing up and bowing a little,"
								+ " [nikki.speech(Oh, where are my manners?! I'm Nikki, the manager of the Slavery Administration."
									+ " I keep petitioning my superiors to have the name changed to something a little more exciting, but they're quite set in their ways.)]"
							+ "</p>"
							+ "<p>"
								+ "[pc.speech(I'm [pc.name],)]"
								+ " you answer, returning the cat-boy's disarming smile."
								+ " As Nikki continues grinning at you, your eyes are drawn down to his crotch, where you see that his choice of crotchless chaps and briefs have left his tiny cat-like cock completely exposed."
							+ "</p>"
							+ "<p>"
								+ "Noticing your downwards glance, Nikki lets out a little laugh, and you can't help but notice that there's a slightly unsettling edge to his voice,"
								+ " [nikki.speech(I like having easy access! You never know when a disobedient slave needs to be reminded of their duties...)]"
							+ "</p>"
							+ "<p>"
								+ "Due to your lack of a slaver license, Nikki is unable to offer you any services, so as he sits back down, concealing his exposed groin behind the desk once more, you say farewell and move to make your exit."
							+ "</p>";
				} else {
					return "<p>"
							+ "The heavy oak doors of the 'Slavery Administration' building are wide open, and, stepping through the inviting entranceway, you find yourself standing in a large, cavernous entrance hall."
							+ " Rows of marble pillars line the edges of the room, with little wooden benches interspersed between them."
							+ " The only other piece of furtinture to be seen is a long, black-marble desk, behind which the black-haired cat-boy, Nikki, is grinning at you with mischievous eyes."
						+ "</p>"
						+ "<p>"
							+ "[nikki.speech(Hello again!)]"
							+ " he shouts, beckoning you over to the desk,"
							+ " [nikki.speech(Can I help you with anything?)]"
						+ "</p>"
						+ "<p>"
							+ "Walking forwards, you return Nikki's greeting,"
							+ " [pc.speech(Hello, I was just looking around.)]"
						+ "</p>"
						+ "<p>"
						+ "[nikki.speech(I'm afraid that there's not really much to see here."
							+ " All the <i>fun</i> happens in the holding cells, and they're off-limits. Unless you've got a slaver license, there's really not much I can offer you, except for a good day!)]"
							+ " he says, standing up to reveal his tiny feline cock."
						+ "</p>"
						+ "<p>"
							+ "Due to your lack of a slaver license, Nikki is unable to offer you any services, so you say farewell and move to make your exit."
						+ "</p>";
				}
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isSlaveTrader()) {
					return new ResponseTrade("Trade", "Buy slavery-related items.", Main.game.getNikki()) { //TODO
						@Override
						public void effects() {
							Main.game.getDialogueFlags().nikkiIntroduced = true;
						}
					};
					
				} else {
					return new Response("Trade", "The cat-boy will only deal with registered slavers.", null);
				}

			} else if (index == 2) {
				if(Main.game.getPlayer().isSlaveTrader()) {
					return new Response("Manage slaves", "Open the slave management window. Not yet implemented!", null) { //TODO
						@Override
						public void effects() {
							Main.game.getDialogueFlags().nikkiIntroduced = true;
						}
					};
					
				} else {
					return new Response("Manage slaves", "The cat-boy will only deal with registered slavers.", null);
				}

			} else if (index == 0) {
				return new Response("Leave", "Step back outside.", SLAVERY_ADMINISTRATION_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().nikkiIntroduced = true;
					}
				};

			} else {
				return null;
			}
		}
	};
	
}
