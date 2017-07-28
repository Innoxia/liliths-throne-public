package com.base.game.dialogue.places.dominion;

import com.base.game.character.Quest;
import com.base.game.character.QuestLine;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.utils.UtilText;
import com.base.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.78
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
						
//						Main.game.getTextStartStringBuilder().append(
//								"<p>"
//									+ "." //TODO
//								+ "</p>");
						
						Main.mainController.moveGameWorld(true);
					}
				};

			} else {
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
	
	public static final DialogueNodeOld SCARLETTS_SHOP = new DialogueNodeOld("Scarlett's shop", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_D_SLAVERY) {
				return "<p>"
						+ "Tucked away in one corner of Slaver Alley, you see a shop that's quite unlike all the others."
						+ " Although the words 'Scarlett's shop; open for business' are painted in fancy gold lettering above the door, the large glass windows don't show any sign of there being any slaves for sale."
					+ "</p>"
					+ "<p>"
						+ "Pushing open the door, you find that the shop's interior is just as bare as you feared."
						+ " There isn't a single slave for sale in sight, and the only sign of life is a rather defeated-looking harpy slouched down at the shop's front desk."
					+ "</p>"
					+ "<p>"
						+ "The harpy, who you assume to be Scarlett, looks up at you as you enter the shop, and with an annoyed huff, she shouts out, "
						+ "[scarlett.speech(If you aren't here to donate any slaves, then you'd better turn around and fuck right off! I'm not in the mood to deal with any fucking morons like you today.)]"
					+ "</p>"
					+ "<p>"
						+ "Apparently this extremely rude harpy is the person that you need to deal with, and, letting out an annoyed sigh, you wonder if you should ask about Arthur now, or come back at another time."
					+ "</p>";
				
			} else if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_E_REPORT_TO_ALEXA) {
				return "<p>"
						+ "You haven't gone to report to Scarlett's matriarch, Alexa, yet, and you don't really want to have to talk to Scarlett until you've done as she's asked."
						+ " Scarlett said that you can find Alexa up in the Harpy Nests, so you'll need to go there report to her before stepping foot inside this shop again."
					+ "</p>";
				
			} else if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_F_RESOLVE_SCARLETTS_REQUEST) {
				return "<p>"
						+ "As you approach Scarlett's shop, you see that the door has been completely smashed in."
						+ " Hurrying forwards, you carefully step over the pieces of broken glass as you enter the shop."
					+ "</p>"
					+ "<p>"
						+ "Looking up, you see Alexa grinning at you in delight as she slouches down behind the front desk, and she turns her head to one side as she calls out,"
						+ " [alexa.speech(Look bitch, our first new customer! Say hello like an obedient little slave!)]"
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(M-mrph!)]"
					+ "</p>"
					+ "<p>"
						+ "Turning your head towards the source of the muffled greeting, you gasp as you see what Alexa has done to Scarlett..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<i>This is as far as the main quest goes for now, but in next week's update you'll find out what Alexa has done to Scarlett. (I'm sure you can take a good guess!)</i>"
					+ "</p>";
				
			} else {
				return "<p>"
							+ "Tucked away in one corner of Slaver Alley, you see a shop quite unlike all the others."
							+ " Although the words 'Scarlett's shop; open for business' are painted in fancy gold lettering above the door, the large glass windows don't show any sign of there being any slaves for sale."
						+ "</p>"
						+ "<p>"
							+ "Pushing open the door, you find that the shop's interior is just as bare as you feared."
							+ " There isn't a single slave for sale in sight, and the only sign of life is a rather defeated-looking harpy slouched down at the shop's front desk."
						+ "</p>"
						+ "<p>"
							+ "The harpy, who you assume to be Scarlett, looks up at you as you enter the shop, and with an annoyed huff, she shouts out, "
							+ "[scarlett.speech(If you aren't here to donate any slaves, then you'd better turn around and fuck right off! I'm not in the mood to deal with any fucking morons like you today.)]"
						+ "</p>"
						+ "<p>"
							+ "You're quite taken aback at Scarlett's rude words, and, deciding that it's probably best not to get involved with someone like that, you turn around and leave the shop."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int index) {
			if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_D_SLAVERY) {
				
				if (index == 1) {
					return new Response("Ask for Arthur", "Ask Scarlett if she's got a slave named Arthur for sale.", SCARLETT_IS_A_BITCH);

				}else {
					return null;
				}
				
			} else {
				return null;
			}

		}
	};
	
	public static final DialogueNodeOld SCARLETT_IS_A_BITCH = new DialogueNodeOld("Scarlett's shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Letting out a defeated sigh as you wonder why everything has to be so difficult, you walk towards the front desk where Scarlett is sitting."
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Didn't I tell you to fuck off already?)]"
						+ " the rude harpy calls out, sitting up in her chair as you approach."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Look, I just need to know if you've got a slave for sale going by the name of 'Arthur', ok?)]"
					+ "</p>"
					+ "<p>"
						+ "As you mention the name 'Arthur', Scarlett stands up from behind her desk, and her cheeks flush red as she starts to shout and curse,"
						+ " [scarlett.speech(If I hear that fucking name one more time, I swear I'm going to kill someone!"
						+ " Are you working with them?! Huh?! Those fucking cunts who fucked me over?! You've got five fucking seconds to start explaining!)]"
					+ "</p>"
					+ "<p>"
						+ "Despite her foul language and aggressive posturing, you find Scarlett's little outburst to be more embarrassing rather than anything else."
						+ " She clearly lacks the physical strength required in order to follow through on any of her threats, so it's more out of a desire to calm her down rather than due to feeling intimidated that you start to answer her,"
						+ " [pc.speech(Calm down, I'm not working with 'them', whoever they might be. I'm just a friend of Arthur's who's been trying to track him down."
						+ " I found out from a helpful enforcer that he was meant to have been given to you."
						+ " If you're still in possession of him, I'd like to see if I can buy his freedom, and if not, then could you [style.italics(please)] just tell me who you sold him to?)]"
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Huh,)]"
						+ " Scarlett huffs as she sits back down, "
						+ "[scarlett.speech(so I guess you really are as stupid as you look. Can't you see that I've got no slaves for sale? I'm fucking finished. And it's all thanks to that business with Arthur...)]"
					+ "</p>"
					+ "<p>"
						+ "Rolling your eyes at the annoying harpy's reaction, you don't get any time to respond before she continues,"
						+ " [scarlett.speech(There might be a way for both of us to profit here though."
						+ " Yeah, I know what happened to Arthur, [style.italics(and)] where he's gone, but I'm also not going to tell a fucking idiot like you anything about it."
						+ " Not without helping me out first, at least.)]"
					+ "</p>"
					+ "<p>"
						+ "Wondering just how many different people you're going to have to deal with before finally finding Arthur, you sigh,"
						+ " [pc.speech(And what is it I'd need to do?)]"
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Even a clueless moron like you can see that this business is a complete failure,)]"
						+ " Scarlett grumbles as she leans back in her chair, "
						+ "[scarlett.speech(and my matriarch is [style.italics(not)] going to be happy when she finds out."
						+ " If you want to find out what happened to your stupid little friend, you're going to go up to the Harpy Nests, find a matriarch called 'Alexa',"
							+ " tell her that my business is bust, and take whatever punishment she'll decide upon on my behalf.)]"
					+ "</p>"
					+ "<p>"
						+ "You realise that if you ever want to find out what happened to Arthur, you're going to have to agree to Scarlett's demands, even if you don't actually plan on taking any punishment on her behalf."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Agree", "Agree to help Scarlett.", SCARLETT_IS_A_SUPER_BITCH) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SCARLETT_IS_A_SUPER_BITCH = new DialogueNodeOld("Scarlett's shop", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding to just agree with the insufferable harpy for now, you respond,"
						+ " [pc.speech(Fine, I'll do it, but you'd better keep your end of the bargain here. When I get back, you're going to tell me exactly what's happened to Arthur, ok?)]"
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Just fuck off and do it already!"
						+ " Fuck, you're a real asshole to try and talk to, you know?!)]"
						+ " Scarlett shouts, looking just as infuriated as you feel right now, "
						+ "[scarlett.speech(Don't bother coming back here until you've taken Alexa's punishment, ok?"
						+ " And don't let her go easy on you, I'm gonna enjoy hearing what she did to you when you get back!)]"
					+ "</p>"
					+ "<p>"
						+ "Not wanting to waste any more time talking to her, you turn your back on Scarlett and walk out of the shop."
						+ " As the door swings shut behind you, you find yourself wondering if you've ever met anyone as annoying as that harpy is."
						+ " You seriously hope that this matriarch, 'Alexa', is nothing like Scarlett, and as you set off in the direction of the Harpy Nests, you wonder how many more hurdles you'll be presented with before finally finding Arthur..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld GATEWAY = new DialogueNodeOld("Gateway", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in the gateway that links Slaver Alley to the rest of Dominion."
						+ " On either side of you, horse-boy guards keep a watchful on anyone that passes through here; obviously on the lookout for runaway slaves."
						+ " A touristy-looking information board, looking quite out of place with its surroundings, has been fixed to a nearby wall, and upon reading it, you discover several important facts about the marketplace beyond."
					+ "</p>"
					+ "<p>"
						+ "The most important piece of information is that a powerful arcane shield has been erected over the entire area, which prevents the effects of any on-going arcane storms from being felt by any of the marketplace's occupants."
						+ " True enough, as you glance up at the sky above, you see the faint pink crackle of the shield's arcane energy, reassuring you that you needen't worry about attacks from any horny customers."
					+ "</p>"
					+ "<p>"
						+ "The other pieces of information relate to several boring laws about things like 'no sexual activity being permitted in the area'."
						+ " The only thing that's of any real relevance to you is the apparent requirement of being able to present a [style.boldBad(slave-owner's permit)] before being able to buy or sell slaves here."
						+ " Apparently, you'll need to apply at City Hall in order to obtain such a permit, but,"
							+ " according to a little piece of paper tacked to the bottom of the board, [style.boldBad(slave-owner's permits are currently unavailable)]."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Leave", "Step back out into Dominion's alleyways."){
					@Override
					public void effects() {
						if(Main.game.getPlayer().isAbleToFly()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "" //TODO
									+ "</p>");
							
						} else {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "" //TODO
									+ "</p>");
						}
						
						Main.mainController.moveGameWorld(true);
					}
				};

			}else {
				return null;
			}
		}
	};
}
