package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.3
 * @author Innoxia
 */
public class HarpyNestsDialogue {
	
	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("Harpy Nests", "Harpy Nests", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "An arrow-shaped sign sits in the middle of the street, and as you approach, you see that it's sporting the words <i>'Harpy Nests'</i>."
						+ " Looking to the side of the road in the direction that the arrow is pointing, you find yourself looking up at one of the tallest buildings in Dominion."
					+ "</p>"
					+ "<p>"
						+ "At eight stories in height, it's not exactly a sky-scraper, but with the larger of Dominion's buildings typically only being four or five stories tall,"
							+ " this structure is large enough to clearly stand out as a building of some importance."
						+ " Despite its unusual height, the only other feature setting it apart from all the other buildings in this area are the pair of huge glass doors set into its frontage."
					+ "</p>"
					+ "<p>"
						+ "Looking through the doors, you see a brightly-lit, extravagantly-decorated lobby."
						+ " Rows of elevators are set into either side of the room, and you notice that there are an unusual amount of harpies loitering around on the many comfortable-looking sofas that litter the area."
					+ "</p>"
					+ "<p>"
					+ (Main.game.getPlayer().isAbleToFly()
						? "It's obvious from even a cursory glance that this building is the entrance to the rooftop Harpy Nests, but seeing as you're able to fly, you don't really have much use for the elevators inside."
						: "It's obvious from even a cursory glance that this building is the entrance to the rooftop Harpy Nests, and you wonder if you should step inside and make use of one of the elevators.")
					+"</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Harpy Nests", "Travel up to the Harpy Nests."){
					@Override
					public void effects() {
						
						if(Main.game.getPlayer().isAbleToFly()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Spreading your wings, you dash into a forwards sprint, and with a little jump and a powerful flap, you take off into the air."
										+ " Quickly gaining altitude, you circle back towards the tall building, looking for an easy spot to land."
										+ " Thankfully, the structure's steepled roof has had a flat platform built over the top of it, and with another flap of your wings, you gracefully swoop down to land on top of it."
									+ "</p>"
									+ "<p>"
										+ "Looking around, you see that the only exit from the platform is a ramp that leads straight down into the building's top floor, and a sign above the opening reads <i>'Harpy entrance'</i>."
										+ " Walking down the ramp, you push open a little door and step inside."
									+ "</p>");
							
						} else {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Pushing open one of the large glass doors, you step forwards into the lobby."
										+ " Although there's a front desk, it's currently surrounded by a group of bickering harpies, and from what snippets of conversation you can catch,"
											+ " it sounds like they're arguing with the receptionist, and each other, over what colour of feather is the most attractive."
									+ "</p>"
									+ "<p>"
										+ "Looking around, you don't see any other members of the building's staff, so, assuming that they're available for just anyone to use, you head over to one of the elevators and step inside."
										+ " Pressing the button on the control panel marked 'up', the elevator's doors slide shut, and with a hum of arcane energy, it lurches into motion."
									+ "</p>"
									+ "<p>"
										+ "Quickly lifting you up to the top of the building, the elevator's doors ping open, and you find yourself stepping out into the building's top floor."
									+ "</p>");
						}
						
						Main.mainController.moveGameWorld(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_ENTRANCE_ENFORCER_POST, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENTRANCE_ENFORCER_POST = new DialogueNodeOld("Enforcer post", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+"You find yourself standing in yet another large, unpartitioned lobby, but this one has quite a different atmosphere than the one on the ground floor."
						+ " Everywhere you look, uniformed enforcers are standing guard, carefully tracking each passerby with a suspicious gaze."
						+ " A large desk, manned by yet more enforcers, sits between you and the door marked <i>'Harpy Nests Entrance'</i>."
					+ "</p>");
			
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Although none of them are really paying you any attention at the moment, it looks like you're going to have to talk to one of these enforcers if you want to get past that desk and out into the Harpy Nests."
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "After having spoken to one of the enforcers and secured a pass, you're free to come and go from the Harpy Nests as you please."
						+ "</p>"
						+(Main.game.getPlayer().getLevel()<5
								? "<p>"
								+ "<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>It's recommended that you be at least level 5 before exploring the Harpy nests!</b>"
							+ "</p>"
							:""));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("To street level", "Travel back down to Dominion's streets."){
					@Override
					public void effects() {
						if(Main.game.getPlayer().isAbleToFly()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Stepping back out through the same door you used to enter the enforcer post, you spread your wings and swoop back down to street level."
									+ "</p>");
							
						} else {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Stepping into the same elevator you used to ride up to the enforcer post, you soon find yourself travelling back down to street level."
									+ "</p>");
						}
						
						Main.mainController.moveGameWorld(WorldType.DOMINION, PlaceType.DOMINION_HARPY_NESTS_ENTRANCE, true);
					}
				};

			} else if (index == 2) {
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess)) {
					return new Response("Request access", "Walk up to the desk and ask if you can visit the Harpy Nests.", ENTRANCE_ENFORCER_POST_ASK_FOR_ACCESS) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.hasHarpyNestAccess);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.BOOK_HARPY), false, true));
						}
					};
					
				} else {
					return new Response("Request access", "You already have access to the Harpy Nests!", null);
				}

			} else if (index == 3 && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess)) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_HARPY_PACIFICATION)) {
					return new Response("Angry Harpies", "Ask one of the Enforcers about the recent troubles in the Harpy Nests.", ENTRANCE_ENFORCER_POST_ASK_ABOUT_RIOTS) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_HARPY_PACIFICATION));
						}
					};
					
				} else {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_REWARD && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) {
						return new Response("Report back", "Report to the Enforcer that you've calmed the three matriarchs down.", ENTRANCE_ENFORCER_POST_COMPLETED_PACIFICATION) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.SIDE_UTIL_COMPLETE));
								Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.getSpellScrollType(SpellSchool.AIR)), false, true);
								Main.game.getPlayer().incrementMoney(5000);
							}
						};
						
					} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)){
						return new Response("Report back", "You haven't calmed the three matriarchs down yet!", null);
					} else {
						return null;
					}
				}

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENTRANCE_ENFORCER_POST_ASK_FOR_ACCESS = new DialogueNodeOld("Enforcer post", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {

			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_ALEXA) {
				return "<p>"
						+"As you approach the desk, one of the enforcers, a minor wolf-girl, catches your eye and calls out to you, "
						+ "[style.speechFeminine(Hey you, yeah <i>you</i>, come here!)]"
					+ "</p>"
					+ "<p>"
						+ "You do as she asks and walk over towards her."
						+ " Stopping just short of the desk, you look down and explain why you're here, "
						+ "[pc.speech(Hi, I'm here to find a harpy matriarch called Alexa, you wouldn't happen to know where she is, would you?)]"
					+ "</p>"
					+"<p>"
						+"A look of surprise crosses the wolf-girl's face as you mention that name. "
						+ "[style.speechFeminine(Well, I don't know where exactly in the Nests any particular harpies are, but if it's the matriarch you're after with that name, then watch out; she's trouble."
						+ " She's got a reputation for being extremely quick to take offence at anything, even for a harpy, so choose your words wisely when you find her.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Ok, I'll be careful, thanks!)] you reply, but before you can leave, the wolf-girl has a few last things to say."
					+ "</p>"
					+"<p>"
						+ "[style.speechFeminine(Wait, hold on a moment. I don't know if you know what happened here recently, but I'd better inform you of the facts before you go out there."
						+ " Basically, some idiot dog-girl decided to say to Alexa that she wasn't as pretty as another matriarch she'd seen."
						+ " Well, after Alexa got her flock to fuck that dog-girl into a broken mess, she decided to go and see how pretty this other matriarch was."
						+ " One thing led to another, and within about twenty minutes we almost had a full-blown riot on our hands.)]"
					+ "</p>"
					+ "<p>"
						+ "The wolf-girl leans back in her chair, before gesturing to the room for emphasis as she continues,"
						+ " [style.speechFeminine(It took far more enforcers than you see here to get things back under control."
						+ " Tensions are still running high in the Nests, so make sure you don't do anything to offend any of the matriarchs, ok?"
						+ " These harpies may look weak, but they're no joke if you piss them off, so remember to mind your manners out there!)]"
					+ "</p>"
					+ "<p>"
						+ "She bends down to retrieve something from under her desk, and, straightening back up, she holds out a little booklet."
						+ " Reaching out to take it, you look down to see the words 'All about Harpies' on the front cover."
						+ " [style.speechFeminine(Here you go, make sure you've read through and understood all this before going out into the nests. Oh, and please try to be careful out there!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Alright, I'll be careful...)] you respond, and, after the wolf-girl gives you a pass to show to any other enforcers that try to stop you, you're allowed to proceed out into the Harpy Nests."
					+ "</p>"
					+ (Main.game.getPlayer().getLevel()<5
							?"<p>"
								+ "Just before you step out into the Nests, the wolf-girl calls after you,"
								+ " [style.speechFeminine(I'm serious about these harpies! They're far more dangerous than any old mugger you might meet in the alleyways below!)]"
							+ "</p>"
							+ "<p>"
								+ "<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>It's recommended that you be at least level 5 before exploring the Harpy nests!</b>"
							+ "</p>"
							:"");
				
			} else {
				return "<p>"
						+"As you approach the desk, one of the enforcers, a lesser cat-boy, catches your eye and calls out to you, "
						+ "[style.speechMasculine(Hey you, come over here!)]"
					+ "</p>"
					+ "<p>"
						+ "You do as he asks and walk over towards him."
						+ " Stopping just short of the desk, you look down and greet him, "
						+ "[pc.speech(Hi, I'm just up here to have a look around the Harpy Nests, don't mind me.)]"
					+ "</p>"
					+"<p>"
						+"The cat-boy raises one eyebrow and leans back in his chair. "
						+ "[style.speechMasculine(Oh you are, are you? And do you know <i>anything</i> about harpies?)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Erm...)] you stumble, not sure what you should say."
					+ "</p>"
					+"<p>"
						+ "[style.speechMasculine(Eugh... Well, it's my job to make sure nobody goes out there unprepared, so listen up!"
						+ " I don't know if you know what happened here recently, but some idiot dog-girl decided to say to one flock's matriarch that she wasn't as pretty as another matriarch she'd seen."
						+ " Well, after getting her flock to, erm, 'play' with the dog-girl for a while, she decided to go and see how pretty this other matriarch was."
						+ " One thing led to another, and within about twenty minutes we almost had a full-blown riot on our hands.)]"
					+ "</p>"
					+ "<p>"
						+ "The cat-boy leans back in his chair, before gesturing to the room for emphasis as he continues,"
						+ " [style.speechMasculine(It took almost triple the amount of enforcers you see here to get things back under control."
						+ " Tensions are still running high in the Nests, so make sure you don't do anything to offend any of the matriarchs, ok?"
						+ " These harpies may look weak, but they're no joke if you piss them off, so remember to mind your manners out there!)]"
					+ "</p>"
					+ "<p>"
						+ "He bends down to retrieve something from under his desk, and, straightening back up, he holds out a little booklet."
						+ " Reaching out to take it, you look down to see the words 'All about Harpies' on the front cover."
						+ " [style.speechMasculine(Here you go, make sure you've read through and understood all this before going out into the nests. Oh, and be careful out there!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Alright, I'll be careful...)] you respond, and, after the cat-boy gives you a pass to show to any other enforcers that try to stop you, you're allowed to proceed out into the Harpy Nests."
					+ "</p>"
					+ (Main.game.getPlayer().getLevel()<5
							?"<p>"
								+ "Just before you step out into the Nests, the cat-boy calls after you,"
								+ " [style.speechMasculine(I'm serious about these harpies! They're far more dangerous than any old mugger you might meet in the alleyways below!)]"
							+ "</p>"
							+ "<p>"
								+ "<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>It's recommended that you be at least level 5 before exploring the Harpy nests!</b>"
							+ "</p>"
							:"");
			}
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE_ENFORCER_POST.getResponse(0, index);
		}
	};
	
	public static final DialogueNodeOld ENTRANCE_ENFORCER_POST_ASK_ABOUT_RIOTS = new DialogueNodeOld("Enforcer post", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Curious to find out more about the recent unrest in the harpy nests, you quickly scan the room for the most senior-looking enforcer."
						+ " A tall, muscular horse-boy, with chevrons on his uniform marking him as a sergeant, fits the bill, and you start to walk over towards him."
						+ " He's currently giving orders to a pair of cat-girls, and you hang around for a moment, waiting for him to finish his business."
					+ "</p>"
					+ "<p>"
						+ "[style.speechMasculineStrong(Alright, report back here as soon as you get that warrant,)] you hear him say."
						+ " [style.speechMasculineStrong(These harpies are getting out of control..."
						+ " Oh, and Alice, before you go, if I hear just <i>one more</i> rumour about you shirking your duties to go visit your boyfriend, I'll fuck you myself! Now get out of my sight!)]"
					+ "</p>"
					+ "<p>"
						+ "[style.speechFeminine(Yes sir!)] the cat-girls call out in unison, before running off."
					+ "</p>"
					+ "<p>"
						+ "Seeing your opportunity to find out more about what's going on in the Harpy Nests, you walk up to the muscular horse-boy."
						+ " Noticing your approach, he lets out a grunt."
						+ " [style.speechMasculineStrong(What is it now? If one of those harpy bitches out there did something, I don't have the time to help you. You should have known the risks before going out there!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Actually, <i>I</i> was wondering if I could help <i>you</i>,)] you say."
						+ " [pc.speech(I couldn't help but overhear how much trouble you're having with these harpies. Perhaps I could sort them out for you?)]"
					+ "</p>"
					+ "<p>"
						+ "[style.speechMasculineStrong(Hah!)] the horse-boy laughs, [style.speechMasculineStrong(you think you could do what half the enforcers in Dominion couldn't?!)]"
					+ "</p>"
					+ "<p>"
						+ "You give the horse-boy a very unamused frown, and after a moment of trying to stare you down, he lets out a deep sigh,"
						+ " [style.speechMasculineStrong(Eugh... I can't believe it's come to this, but I've run out of ideas...)]"
					+ "</p>"
					+ "<p>"
						+ "Pushing open a door behind him, he beckons you to follow, and you walk into a small office."
						+ " The horse-boy strides over to a solid wooden desk, before collapsing down into his chair."
						+ " Leafing through a pile of papers, he pulls out three separate sheets, before handing them over to you."
						+ " There's a portrait of an exceptionally beautiful harpy on each one, and as you take a closer look, the enforcer starts speaking."
					+ "</p>"
					+ "<p>"
						+ "[style.speechMasculineStrong(So, as everyone's aware, the Harpy Nests are exceptionally dangerous right now."
						+ " The more prominent of the matriarchs have been at each other's throats ever since that business with the dog-girl."
						+ " We've come to an agreement with Alexa, who, if you didn't know, is the most important of the matriarchs."
						+ " Those other three, however,)] he points to the papers in your hand, [style.speechMasculineStrong(are refusing to cooperate.)]"
					+ "</p>"
					+ "<p>"
						+ "You flick between the papers as he continues,"
						+ " [style.speechMasculineStrong(That one with the blonde feathers is [bimboHarpy.name], the mean-looking one is [dominantHarpy.name], and the other one is called [nymphoHarpy.name]."
						+ " They outright refuse to get along with one another, and their little ongoing feud is causing the Nests to descend into anarchy."
						+ " We've even started to get reports of harpies attacking travellers in broad daylight! No matter how many enforcer patrols we send out, nothing changes."
						+ " As much as I hate to admit it, it's the matriarchs who are the ones responsible for keeping law and order up here.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(So you need someone to get these matriarchs under control?)] you ask."
					+ "</p>"
					+ "<p>"
						+ "[style.speechMasculineStrong(If we tried sending enforcers in to do that, it'd be an insult to all the harpy flocks."
						+ " Alexa would probably even get involved, which is the last thing we need."
						+ " I hate asking for help like this, but if by some miracle you're able to subdue those three,)]"
						+ " he points to the papers in your hand again,"
						+ " [style.speechMasculineStrong(I could offer a five thousand flame reward.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Consider it done,)] you say, before moving to leave the office. [pc.speech(I'll be back to claim that reward!)]"
					+ "</p>"
					+ "<p>"
						+ "You hear the horse-boy grunt as he sinks down into his chair, and, closing the door behind you, you walk back out into the Enforcer Post."
					+ "</p>";
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE_ENFORCER_POST.getResponse(0, index);
		}
	};
	
	public static final DialogueNodeOld ENTRANCE_ENFORCER_POST_COMPLETED_PACIFICATION = new DialogueNodeOld("Enforcer post", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You walk over towards the office where you first accepted the horse-boy enforcer's request to pacify the Harpy Nests."
						+ " As you approach, the door opens, and an exhausted-looking, and vaguely familiar, cat-girl steps out."
						+ " She shuffles off in the direction of the elevators, and before the door swings shut, you step forwards into the office."
					+ "</p>"
					+ "<p>"
						+ ""
						+ "[style.speechMasculineStrong(Hah! Hungry for more, Alic- Oh! It's you!)] the horse-boy calls out, tucking in his shirt as he sits down in his chair."
						+ " [style.speechMasculineStrong(I heard what you've been up to out there!)]"
					+ "</p>"
					+ "<p>"
						+ "Ignoring the smell of sex that's saturating the room, you respond,"
						+ " [pc.speech(Then you must have heard that all three of those matriarchs have agreed to calm down.)]"
					+ "</p>"
					+ "<p>"
						+ "[style.speechMasculineStrong(Hah! Well, I don't know how you managed to pull it off, but we've received messages from each one of those matriarchs saying that they'll stop causing a problem!)]"
					+ "</p>"
					+ "<p>"
						+ "The muscular horse-boy produces a little brass key, and, bending down to unlock a heavy iron safe that's sitting beneath his desk, he retrieves a bag of money."
						+ " Setting it down in front of him, he grins up at you."
						+ " [style.speechMasculineStrong(I still can't believe that you actually managed to do it! Well, I'll keep my end of the bargain; here's five thousand flames! Here have this scroll too, for the speed with which you handled everything.)]"
					+ "</p>"
					+ "<p>"
						+ "Stepping forwards, you take the offered bag of money, before thanking the enforcer sergeant."
						+ " He seems to be in very high spirits, and lets out another happy exclamation as he leans back in his chair,"
						+ " [style.speechMasculineStrong(Hah! We're going to start regular patrols of the walkways to make sure another incident like this doesn't happen again."
						+ " Thanks to your efforts, and these patrols, we should be able to put a stop to all these harpy attacks!)]"
					+ "</p>"
					+ "<p>"
						+ "Having received your reward, you say farewell to the happy horse-boy, and, walking out of his office, head back into the Enforcer Post."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>You have received:</b> <b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>"+UtilText.getCurrencySymbol()+"</b> <b>5,000</b>"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>The Harpy Nests are now safe to travel through!</b>"
					+ "</p>";
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE_ENFORCER_POST.getResponse(0, index);
		}
		
	};
	
	
	public static final DialogueNodeOld WALKWAY = new DialogueNodeOld("Walkway", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {// if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) TODO Also harpy attack
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You find yourself walking down a winding, narrow walkway; part of an interconnected system of pathways, stairs, and ladders that have been built into the rooftops of Dominion."
						+ " Being able to fly, the harpies that live up here have no use for them, and there aren't many people willing to risk a harpy's wrath just for the sake of taking a stroll through the nests."
						+ " As a result, these links between the sprawling network of harpy nests are completely deserted."
					+ "</p>"
					+ "<p>"
						+ "Although these walkways don't lead directly through any nests, you're still able to observe them as you pass."
						+ " Each nest takes the form of a series of platforms, built on top of, and into, the roofs and upper floors of Dominion's residences."
						+ " While most are confined to a single building, you see that a few span multiple houses, and have several tiers of platforms built on top of one anther;"
							+ " evidence that the matriarch of that particular nest is extremely important."
					+ "</p>");
			
			switch(Main.game.getCurrentWeather()) {
				case MAGIC_STORM:
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Due to the ongoing arcane storm, each flock has taken shelter in the building below their nest."
								+ " The only movement to be seen in the deserted nests takes the form of canvas coverings, erected to protect the harpies from the elements, billowing in the wind."
								+ " Peering over the side of the railing, you see that the streets below are similarly abandoned, with only the occasional demon to be seen wandering through the area."
							+ "</p>"
							+ "<p>"
								+ (Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)
									?"Although you've pacified the harpy nests, these walkways are dangerous at the moment, due to the ongoing arcane storm."
										+ " The harpies should all be taking shelter, you can't help but shake the feeling that you're being watched."
										+ " An occasional flash of colour out of the corner of your [pc.eye] confirms your suspicions, but each time you turn to face your elusive stalker, they quickly duck out of sight."
										+ " It's probably only a matter of time before they make their move..."
									:"Although the harpies should all be taking shelter, you can't help but shake the feeling that you're being watched."
										+ " An occasional flash of colour out of the corner of your [pc.eye] confirms your suspicions, but each time you turn to face your elusive stalker, they quickly duck out of sight."
										+ " It's probably only a matter of time before they make their move...")
							+ "</p>");
					break;
				case RAIN:
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Due to the ongoing rain, each flock has taken shelter in the building below their nest, or beneath the canvas coverings that have been erected to protect the harpies from the elements."
								+ " The matriarch of each nest is clearly visible, even from a great distance, as they're always surrounded by other harpies eager to gain the favour of their leader."
								+ " Peering over the side of the railing, you see that the people walking down the streets below are similarly huddling beneath any shelter they can find, desperate to get out of the rain."
							+ "</p>"
							+ "<p>"
								+ (Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)
									?"You sometimes see an enforcer patrolling a walkway off in the distance; reminding you that it's now safe for people to be travelling between the nests."
										+ " Although there's no risk of being attacked, harpies are still quite touchy by nature, and you imagine that it wouldn't be too hard to cause a confrontation if you were to go looking for trouble..."
									:"As you continue travelling down the walkways, you can't help but shake the feeling that you're being watched."
										+ " An occasional flash of colour out of the corner of your [pc.eye] confirms your suspicions, but each time you turn to face your elusive stalker, they quickly fly off back to their nest."
										+ " It's probably only a matter of time before one of them decides to openly confront you...")
							+ "</p>");
					break;
				default:
					if(Main.game.isDayTime()) {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "The members of each flock are sprawled out over their nest's platforms."
									+ " Most are simply lounging about beneath canvas coverings, but there are plenty to be seen sunbathing or talking in little groups."
									+ " The matriarch of each nest is clearly visible, even from a great distance, as they're always surrounded by other harpies eager to gain the favour of their leader."
									+ " Peering over the side of the railing, you see the people walking down the streets below, oblivious to what's going on high above them."
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "The members of each flock are sprawled out over their nest's platforms."
									+ " Even at night, there are still plenty of harpies to be seen lounging about or talking in little groups, their brightly-coloured feathers illuminated by a series of bright lights that cover the rooftops."
									+ " The matriarch of each nest is clearly visible, even from a great distance, as they're always surrounded by other harpies eager to gain the favour of their leader."
									+ " Peering over the side of the railing, you see the people walking down the streets below, oblivious to what's going on high above them."
								+ "</p>");
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ (Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)
									?"You sometimes see an enforcer patrolling a walkway off in the distance; reminding you that it's now safe for people to be travelling between the nests."
										+ " Although there's no risk of being attacked, harpies are still quite touchy by nature, and you imagine that it wouldn't be too hard to cause a confrontation if you were to go looking for trouble..."
									:"As you continue travelling down the walkways, you can't help but shake the feeling that you're being watched."
										+ " An occasional flash of colour out of the corner of your [pc.eye] confirms your suspicions, but each time you turn to face your elusive stalker, they quickly fly off back to their nest."
										+ " It's probably only a matter of time before one of them decides to openly confront you...")
							+ "</p>");
					break;
			
			}
			
			return UtilText.nodeContentSB.toString();
		}

		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) {
					return new ResponseEffectsOnly(
							"Look for trouble",
							"Although you've pacified the harpy nests, you're sure that you can find a harpy who's looking for a confrontation..."){
								@Override
								public void effects() {
									DialogueNodeOld dn = Encounter.HARPY_NEST_LOOK_FOR_TROUBLE.getRandomEncounter(true);
									Main.game.setContent(new Response("", "", dn));
								}
							};
							
				} else {
					return new ResponseEffectsOnly(
							"Explore",
							"Explore the walkways. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
								@Override
								public void effects() {
									DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true, true);
									Main.game.setContent(new Response("", "", dn));
								}
							};
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WALKWAY_BRIDGE = new DialogueNodeOld("Walkway Bridge", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {// if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) TODO Also harpy attack
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You find yourself walking over a narrow wooden bridge; part of an interconnected system of pathways, stairs, and ladders that have been built into the rooftops of Dominion."
						+ " Being able to fly, the harpies that live up here have no use for them, and there aren't many people willing to risk a harpy's wrath just for the sake of taking a stroll through the nests."
						+ " As a result, these links between the sprawling network of harpy nests are completely deserted."
					+ "</p>"
					+ "<p>"
						+ "Although these walkways don't lead directly through any nests, you're still able to observe them as you pass."
						+ " Each nest takes the form of a series of platforms, built on top of, and into, the roofs and upper floors of Dominion's residences."
						+ " While most are confined to a single building, you see that a few span multiple houses, and have several tiers of platforms built on top of one anther;"
							+ " evidence that the matriarch of that particular nest is extremely important."
					+ "</p>");
			
			switch(Main.game.getCurrentWeather()) {
				case MAGIC_STORM:
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Due to the ongoing arcane storm, each flock has taken shelter in the building below their nest."
								+ " The only movement to be seen in the deserted nests takes the form of canvas coverings, erected to protect the harpies from the elements, billowing in the wind."
								+ " Peering over the side of the railing, you see that the streets below are similarly abandoned, with only the occasional demon to be seen wandering through the area."
							+ "</p>"
							+ "<p>"
								+ (Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)
									?"Although you've pacified the harpy nests, these walkways are dangerous at the moment, due to the ongoing arcane storm."
										+ " The harpies should all be taking shelter, you can't help but shake the feeling that you're being watched."
										+ " An occasional flash of colour out of the corner of your [pc.eye] confirms your suspicions, but each time you turn to face your elusive stalker, they quickly duck out of sight."
										+ " It's probably only a matter of time before they make their move..."
									:"Although the harpies should all be taking shelter, you can't help but shake the feeling that you're being watched."
										+ " An occasional flash of colour out of the corner of your [pc.eye] confirms your suspicions, but each time you turn to face your elusive stalker, they quickly duck out of sight."
										+ " It's probably only a matter of time before they make their move...")
							+ "</p>");
					break;
				case RAIN:
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Due to the ongoing rain, each flock has taken shelter in the building below their nest, or beneath the canvas coverings that have been erected to protect the harpies from the elements."
								+ " The matriarch of each nest is clearly visible, even from a great distance, as they're always surrounded by other harpies eager to gain the favour of their leader."
								+ " Peering over the side of the railing, you see that the people walking down the streets below are similarly huddling beneath any shelter they can find, desperate to get out of the rain."
							+ "</p>"
							+ "<p>"
								+ (Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)
									?"You sometimes see an enforcer patrolling a walkway off in the distance; reminding you that it's now safe for people to be travelling between the nests."
										+ " Although there's no risk of being attacked, harpies are still quite touchy by nature, and you imagine that it wouldn't be too hard to cause a confrontation if you were to go looking for trouble..."
									:"As you continue travelling down the walkways, you can't help but shake the feeling that you're being watched."
										+ " An occasional flash of colour out of the corner of your [pc.eye] confirms your suspicions, but each time you turn to face your elusive stalker, they quickly fly off back to their nest."
										+ " It's probably only a matter of time before one of them decides to openly confront you...")
							+ "</p>");
					break;
				default:
					if(Main.game.isDayTime()) {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "The members of each flock are sprawled out over their nest's platforms."
									+ " Most are simply lounging about beneath canvas coverings, but there are plenty to be seen sunbathing or talking in little groups."
									+ " The matriarch of each nest is clearly visible, even from a great distance, as they're always surrounded by other harpies eager to gain the favour of their leader."
									+ " Peering over the side of the railing, you see the people walking down the streets below, oblivious to what's going on high above them."
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "The members of each flock are sprawled out over their nest's platforms."
									+ " Even at night, there are still plenty of harpies to be seen lounging about or talking in little groups, their brightly-coloured feathers illuminated by a series of bright lights that cover the rooftops."
									+ " The matriarch of each nest is clearly visible, even from a great distance, as they're always surrounded by other harpies eager to gain the favour of their leader."
									+ " Peering over the side of the railing, you see the people walking down the streets below, oblivious to what's going on high above them."
								+ "</p>");
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ (Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)
									?"You sometimes see an enforcer patrolling a walkway off in the distance; reminding you that it's now safe for people to be travelling between the nests."
										+ " Although there's no risk of being attacked, harpies are still quite touchy by nature, and you imagine that it wouldn't be too hard to cause a confrontation if you were to go looking for trouble..."
									:"As you continue travelling down the walkways, you can't help but shake the feeling that you're being watched."
										+ " An occasional flash of colour out of the corner of your [pc.eye] confirms your suspicions, but each time you turn to face your elusive stalker, they quickly fly off back to their nest."
										+ " It's probably only a matter of time before one of them decides to openly confront you...")
							+ "</p>");
					break;
			
			}
			
			return UtilText.nodeContentSB.toString();
		}

		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) {
					return new ResponseEffectsOnly(
							"Look for trouble",
							"Although you've pacified the harpy nests, you're sure that you can find a harpy who's looking for a confrontation..."){
								@Override
								public void effects() {
									DialogueNodeOld dn = Encounter.HARPY_NEST_LOOK_FOR_TROUBLE.getRandomEncounter(true);
									Main.game.setContent(new Response("", "", dn));
								}
							};
							
				} else {
					return new ResponseEffectsOnly(
							"Explore",
							"Explore the walkways. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
								@Override
								public void effects() {
									DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true, true);
									Main.game.setContent(new Response("", "", dn));
								}
							};
				}
			} else {
				return null;
			}
		}
	};
	
}
