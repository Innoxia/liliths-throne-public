package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.SlaveryManagementDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.SMRoseHands;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.1.97
 * @author Innoxia
 */
public class LilayaHomeGeneric {
	
	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getLabel() {
			return "Lilaya's Home - Street";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing before Lilaya's home, the size of which is more akin to that of a palace than a normal town-house."
					+ "</p>"

					+ "<p>"
						+ " You can come and go from here as you please, knowing that Rose will quickly be there to let you in should you knock on the front door."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Enter", "Knock on the door and wait for Rose to let you in."){
					@Override
					public void effects() {
						
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You knock on the front door, and after only a brief moment, it swings open."
								+ "</p>"
								+ "<p>"
									+ UtilText.parseNPCSpeech("Welcome back,", Femininity.FEMININE)
									+ " Rose says, curtsying to you as you step into Lilaya's house."
								+ "</p>"
								+ "<p>"
									+ "You greet Rose as she closes the door behind you, and, excusing herself, she quickly hurries off to another part of the house, leaving you standing in the entrance hall."
								+ "</p>");
						
						Main.mainController.moveGameWorld(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CORRIDOR = new DialogueNodeOld("Corridor", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			
			UtilText.nodeContentSB.append("<p>"
						+ "The many corridors running through Lilaya's house are, while extremely impressive, all much of the same."
						+ " Immaculately-clean red carpet runs down the centre of each one, while fine paintings and masterfully-carved marble busts line the walls."
					+ "</p>"
					+ "<p>"
						+ (Main.game.isDayTime()
							?"Delicate glass windows provide a good amount of natural daylight, and Rose seems to be taking care to leave some of them open every now and again, making sure that the air in the house always feels fresh."
							:"The curtains are currently drawn over the corridor's delicate glass windows, but during the day, they're able to provide a good amount of natural daylight.")
					+ "</p>");
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append("<p>"
							+ "This corridor is deserted at the moment, and there doesn't really seem to be much to do here."
						+ "</p>");
			} else {
				for(NPC slave : charactersPresent) {
					switch(slave.getObedience()) {
					case NEGATIVE_FIVE_REBELLIOUS: case NEGATIVE_FOUR_DEFIANT: case NEGATIVE_THREE_STRONG_INSUBORDINATE:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "Although <b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.name]</b> is here, [npc.she]'s not even bothering to pretend that [npc.she]'s cleaning."
								+ "</p>"));
						break;
					case NEGATIVE_ONE_DISOBEDIENT:  case NEGATIVE_TWO_UNRULY:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> appears to be half-heartedly dusting some of picture frames that line the corridor."
								+ "</p>"));
						break;
					case ZERO_FREE_WILLED:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> is down on all fours dusting the skirting boards."
								+ "</p>"));
						break;
					case POSITIVE_ONE_AGREEABLE: case POSITIVE_TWO_OBEDIENT:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> is busily polishing the floorboards."
								+ "</p>"));
						break;
					case POSITIVE_THREE_DISCIPLINED: case POSITIVE_FOUR_DUTIFUL: case POSITIVE_FIVE_SUBSERVIENT:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> is dutifully dusting, polishing, and cleaning everything in this area."
								+ "</p>"));
						break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index-1<Main.game.getCharactersPresent().size()) {
				return new Response(UtilText.parse(Main.game.getCharactersPresent().get(index-1), "[npc.Name]"), UtilText.parse(Main.game.getCharactersPresent().get(index-1), "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public void effects() {
						Main.game.setActiveNPC(Main.game.getCharactersPresent().get(index-1));
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	private static Response getRoomResponse(int index) {
		List<NPC> charactersPresent = Main.game.getCharactersPresent();
		
		if(index==0) {
			return null;
			
		} if (index == 1) {
			if(Main.game.getPlayer().isHasSlaverLicense()) {
				return new Response("Slavery Overview", "Open the slave management screen.",  CORRIDOR) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return SlaveryManagementDialogue.getSlaveryOverviewDialogue();
					}
				};
			} else {
				return new Response("Slavery Overview", "You'll need a slaver license before you can access this menu!",  null);
			}
			
		} 
		else if (index == 2) {
			if(Main.game.getPlayer().isHasSlaverLicense()) {
				return new Response("Slave List", "Enter the slave management screen.", CORRIDOR) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return SlaveryManagementDialogue.getSlaveryManagementDialogue(null);
					}
				};
			} else {
				return new Response("Slave List", "You'll need a slaver license before you can access this menu!",  null);
			}
			
		} else if (index == 3) {
			if(Main.game.getPlayer().isHasSlaverLicense()) {
				return new Response("Room List", "Enter the room upgrades options screen.", SlaveryManagementDialogue.ROOM_MANAGEMENT);
			} else {
				return new Response("Room List", "You'll need a slaver license before you can access this menu!",  null);
			}
			
		} else if(index-4<charactersPresent.size()) {
			return new Response(UtilText.parse(charactersPresent.get(index-4), "[npc.Name]"), UtilText.parse(charactersPresent.get(index-4), "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
				@Override
				public void effects() {
					Main.game.setActiveNPC(charactersPresent.get(index-4));
				}
			};
			
		} else {
			return null;
		}
	}
	
	private static StringBuilder roomSB = new StringBuilder();
	private static String getRoomModificationsDescription() {
		GenericPlace place = Main.game.getPlayer().getLocationPlace();
		roomSB.setLength(0);
		
		for(PlaceUpgrade pu : PlaceUpgrade.values()) {
			if(place.getPlaceUpgrades().contains(pu)) {
				roomSB.append(formatRoomUpgrade(pu));
			}
		}
		
		return roomSB.toString();
	}
	

	private static String formatRoomUpgrade(PlaceUpgrade upgrade) {
		return "<p>"
				+ "<b style='color:"+upgrade.getColour().toWebHexString()+";'>"+upgrade.getName()+"</b></br>"
				+ upgrade.getRoomDescription(Main.game.getPlayer().getLocationPlace())
			+ "</p>";
	}
	
	public static final DialogueNodeOld ROOM_WINDOW = new DialogueNodeOld("Room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "This particular room has a series of large windows set into one wall, which allow a generous amount of natural daylight to flood out into the corridor when the door is left open."
						+ " Stepping into the room to glance out of the windows, you find yourself looking down on the hustle and bustle of Dominion's busy streets."
					+ "</p>"
					+getRoomModificationsDescription();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(index);
		}
	};
	
	public static final DialogueNodeOld ROOM_GARDEN_GROUND_FLOOR = new DialogueNodeOld("Garden-view room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "This room has a series of wide, ceiling-height windows set into one wall, which swing open to allow access to and from the adjoining garden courtyard."
					+ "</p>"
					+getRoomModificationsDescription();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(index);
		}
	};
	
	public static final DialogueNodeOld ROOM_GARDEN = new DialogueNodeOld("Garden-view room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "This room has a series of wide, ceiling-height windows set into one wall, which allow a generous amount of natural daylight to flood out into the corridor when the door is left open."
						+ " Stepping inside and walking over to the windows, you find yourself looking down on the house's garden courtyard."
					+ "</p>"
					+getRoomModificationsDescription();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(index);
		}
	};
	
	public static final DialogueNodeOld ROOM_WINDOW_SLAVE = new DialogueNodeOld("Slave's Room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "This room has a series of large windows set into one wall, which allow a generous amount of natural daylight to flood out into the corridor when the door is left open."
						+ " What with this room being situated on the outside of the house, the view from the windows is of the hustle and bustle of Dominion's busy streets."
					+ "</p>"
					+getRoomModificationsDescription();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(index);
		}
	};
	
	public static final DialogueNodeOld ROOM_GARDEN_GROUND_FLOOR_SLAVE = new DialogueNodeOld("Slave's Garden-view Room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "This room has a series of wide, ceiling-height windows set into one wall, which swing open to allow access to and from the adjoining garden courtyard."
					+ "</p>"
					+getRoomModificationsDescription();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(index);
		}
	};
	
	public static final DialogueNodeOld ROOM_GARDEN_SLAVE = new DialogueNodeOld("Slave's Garden-view Room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "This room has a series of wide, ceiling-height windows set into one wall, which allow a generous amount of natural daylight to flood out into the corridor when the door is left open."
						+ " What with this room being situated in the interior of the house, the view from the windows is of the house's garden courtyard."
					+ "</p>"
					+getRoomModificationsDescription();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(index);
		}
	};
	
	public static final DialogueNodeOld ROOM_ARTHUR_INSTALLATION = new DialogueNodeOld("Arthur's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding that this room would be suitable to convert for Arthur's use, you walk over to the bell-pull in one corner and give it a tug."
						+ " After just a few moments, Rose steps through the door, before curtsying and greeting you,"
						+ " [rose.speech(Is there anything you need, [pc.name]?)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(I've decided that this would be the best place for Arthur to have as his room,)]"
						+ " you reply, and, deciding that you'd like to get it set up as quickly as possible, you continue,"
						+ " [pc.speech(If you show me what needs moving in here, I can give you a hand.)]"
					+ "</p>"
					+ "<p>"
						+ "Thanking you for the offer of help, Rose proceeds to lead you to a storage cupboard, where a significant amount of spare lab equipment is being kept."
						+ " After showing you which of the items Arthur will need in order to carry out experiments for Lilaya, Rose picks up one of the smaller desks and sets off in the direction of the room."
						+ " Following her lead, you start moving furniture into Arthur's new room, and after just half an hour, you manage to get all of the work finished."
					+ "</p>"
					+ "<p>"
						+ "[rose.speech(I'll fetch mistress,)]"
						+ " Rose says, before slipping out of the room."
					+ "</p>"
					+ "<p>"
						+ "After just a couple of minutes, the door pushes open again, and Rose steps into the room, followed by Arthur,"
						+ " [rose.speech(Here you are, sir. If there's anything you need, please use the bell-pull to call for me.)]"
					+ "</p>"
					+ "<p>"
						+ "With that, Rose exits the room once again, leaving you alone with Arthur."
						+ " He lets out a deep sigh as he walks over to sit on his new bed, before looking up at you and smiling,"
						+ " [arthur.speech(I don't think I've said it properly yet, so thank you [pc.name], for rescuing me.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(You're welcome,)]"
						+ " you reply,"
						+ " [pc.speech(So do you think you can figure out how I was transported into your world? Lilaya told me that you're the best arcane researcher out there...)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Lilaya said that, huh?)]"
						+ " Arthur says, leaning back and smiling,"
						+ " [arthur.speech(Well, I do have a certain theory, but when I told Lilaya about it, she wasn't too convinced."
							+ " Considering my position, I don't really have a choice but to perform the experiments and research that she asks for, but I'm absolutely convinced that they'll get us no closer to unravelling your mystery."
							+ " What I'd really like to do is get to talk to someone about my theory...)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(You could talk to me about it,)]"
						+ " you offer."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Ah, well, thank you, but I wouldn't want to worry you, in case I turn out to be wrong,)]"
						+ " Arthur stammers, before quickly continuing,"
						+ " [arthur.speech(What I really meant is that I'd like to talk to someone who would be able to confirm or deny my theory absolutely."
							+ " Someone with more understanding of the arcane than anyone you'd ever find wandering around in Dominion."
							+ " Someone like a Lilin."
							+ " But not just any Lilin; it needs to be one of the seven elders.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(An elder Lilin? Lilaya's mother wouldn't happen to be one of those, would she?)]"
						+ " you offer, remembering that Rose once told you that Lilaya's mother was the Lilin Lyssieth."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Not only is she an elder Lilin, but she's the very one who told me such a thing existed in the first place! It'd be near-impossible to get any other Lilin to talk with me, but I know Lyssieth will...)]"
						+ " Arthur explains, before suddenly blurting out,"
						+ " [arthur.speech(B-But please, <i>please</i> don't tell Lilaya that I'm looking to speak with her mother...)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Find Lyssieth", "If you ever want to find out what's going on, it looks like you'll have to agree to help.", ROOM_ARTHUR){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH = new DialogueNodeOld("Arthur's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Ok, I'll find Lyssieth and see if I can convince her to meet with you,)]"
						+ " you say,"
						+ " [pc.speech(but if I'm to keep this from Lilaya, I'll need to know why.)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur smiles happily as he hears that you're willing to help, but as you demand an explanation, he lets out a heavy sigh,"
						+ " [arthur.speech(That's fair enough, I suppose. I'd have to explain the whole situation to you anyway, as it's the reason why Lyssieth isn't exactly easy to get hold of these days.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Is this related to Lilaya's accusations which I witnessed earlier?)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Yes... You see, as I'm sure you already know, Lilaya and I used to work here together."
							+ " After a time, we started dating, but it didn't last long...)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur seems quite reluctant to continue, but after a minute of silence, he continues,"
						+ " [arthur.speech(At the time, Lyssieth used to visit very regularly, and would always make a fuss over Lilaya."
							+ " Well, the last time Lyssieth visited, Lilaya was out on a rare errand, and, left alone with her in the lab... well... i-it's hard saying no to a Lilin... and after she told me she had a thing for humans...)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Ah,)]"
						+ " you sigh, seeing where this is going."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Yeah... Needless to say, Lilaya returned and caught us in the act,)]"
						+ " Arthur continues, casting his eyes to the floor,"
						+ " [arthur.speech(and you can imagine the rest... She still hasn't forgiven either of us for that."
							+ " I mean, polyamory isn't exactly uncommon - hell, Lilaya herself was still sleeping with Rose at the time - but seeing as it was with her mother, as well as the fact that we'd done it behind her back...)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur falls into silence for a moment again, before shaking his head and looking up at you,"
						+ " [arthur.speech(Anyway, that's the whole reason for Lilaya living alone like this."
							+ " You see, Lyssieth only has a few recognised children, and amongst them, Lilaya was clearly her favourite."
							+ " When Lilaya told her mother that she never wanted to see her again, Lyssieth really took it hard."
							+ " Although she'd been appointed by Lilith to govern the undercity of Submission, she'd never really taken her duties seriously before, but after all this, she retreated to her official residence down there."
							+ " I don't think she's been seen up here in Dominion since....)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(So you want me to go down into Submission, find Lyssieth, and convince her to come and meet with you, all behind Lilaya's back?"
							+ " I can't betray her trust like that...)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(This is all to find out the truth behind why you appeared in this world,)]"
						+ " Arthur tries to remind you, but, upon seeing the serious expression on your face, he concedes,"
						+ " [arthur.speech(Bloody hell... Fine, I'll admit that I'm more than a little interested in it as well."
							+ " Don't worry about going behind her back; I'll tell Lilaya about all of this."
							+ " I'll need a little time to build up to it, however, so until then please don't mention anything about this to her.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Ok, but I won't bring Lyssieth here until you've told Lilaya,)]"
						+ " you firmly state."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Don't worry, by the time you've found and convinced her, I'll have let Lilaya know."
							+ " Well, with that settled, I should get on with these experiments Lilaya's assigned to me."
							+ " If there's ever anything you need, please don't hesitate to drop by and let me know; my door's always open to the person who saved me!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Allow Arthur to get on with his experiments.", ROOM_ARTHUR);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ROOM_ARTHUR = new DialogueNodeOld("Arthur's Room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in Arthur's Room, which, due to the occupant's need to carry out any experiments assigned to him, looks like a miniature version of Lilaya's lab."
						+ " The walls are lined with cluttered tables, stacked bookcases, and cupboards filled with all manner of scientific-looking apparatus."
						+ " A solitary bed is positioned in one corner, but aside from that, there's no other indication that this room doubles as Arthur's sleeping quarters."
					+ "</p>"
					+ "<p>"
						+ "Busily performing one of the experiments that Lilaya has assigned to him, Arthur looks to be hard at work, although you don't think he'd mind if you asked him a few questions..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Lyssieth", "Ask Arthur about Lilaya's mother, Lyssieth.", ROOM_ARTHUR_LYSSIETH);
				
			} else if(index == 2) {
				return new Response("Lilaya", "Ask Arthur about his past relationship with Lilaya.", ROOM_ARTHUR_LILAYA);
				
			} else if(index == 3) {
				if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HYPNO_WATCH)) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_HYPNO_WATCH)) {
						return new Response("Experiments", "Ask Arthur about the sort of experiments he's currently running.", ROOM_ARTHUR_HYPNO_WATCH_START) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_HYPNO_WATCH));
							}
						};
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH) == Quest.SIDE_HYPNO_WATCH_VICKY) {
						if(!Main.game.getPlayer().hasItem(AbstractItemType.generateItem(ItemType.ARTHURS_PACKAGE))) {
							return new Response("Deliver package", "You need to fetch the package from Arcane Arts first!", null);
							
						} else {
							return new Response("Deliver package", "Hand over the package that you fetched from Arcane Arts.", ROOM_ARTHUR_HYPNO_WATCH_DELIVERY) {
								@Override
								public void effects() {
									Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.ARTHURS_PACKAGE));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT));
									Main.game.getLilaya().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
								}
							};
						}
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld ROOM_ARTHUR_LYSSIETH = new DialogueNodeOld("Arthur's Room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Wanting to know a little more about Lilaya's mother, you approach Arthur and get his attention, before asking,"
						+ " [pc.speech(What was Lyssieth like back when you used to work with Lilaya?)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur smiles a little at the question,"
						+ " [arthur.speech(In a lot of ways, she was exactly what you'd expect one of Lilith's daughters to be; confident, controlling, and immensely powerful."
							+ " My first impression of her was one of incredible haughtiness and arrogance, but as I watched her interactions with Lilaya, I saw that she had a softer, more loving side...)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur seems to get a little caught-up in his thoughts for a moment, before shaking his head and looking up at you,"
						+ " [arthur.speech(Of course, as you'd expect from a Lilin, she's practically dripping with sex appeal."
							+ " She also has a thing for humans, which explains why most of her daughters are half-demons; if a Lilin's partner isn't corrupted into a demon before having sex, then their offspring end up like Lilaya."
							+ " Not that that's a bad thing, of course, but society does typically treat half-demons as the lowest of all the demonic races."
							+ " That is, of course, unless they've been recognised by their mother, like Lilaya...)]"
					+ "<p>"
					+ "</p>"
						+ "Arthur turns to one side to check on his experiment, making a satisfied humming noise before facing you once more and continuing,"
						+ "[arthur.speech(Sorry about that."
							+ " Anyway, you should know that Lyssieth's love of humans even extends to the appearance she likes to take on the most."
							+ " The Lilin can transform themselves into any race imaginable, you see, and Lyssieth was most often seen taking the form of a human."
							+ " She'd change her style almost every day, but no matter what appearance she took on, she'd always be so beautiful...)]"
					+ "</p>"
					+ "<p>"
						+ "Again, Arthur seems to lose himself in his thoughts, and, having heard enough, you decide to take a step back and let him resume his experiments."
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index == 1) {
					return new Response("Lyssieth", "You're already asking Arthur about Lyssieth.", null);
					
				}
			}
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ROOM_ARTHUR_LILAYA = new DialogueNodeOld("Arthur's Room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself curious to know what Arthur's memories of Lilaya are like, so, stepping forwards, you ask,"
						+ " [pc.speech(I hope you don't mind me asking, but what was Lilaya like when you two were dating?)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Loving, but very controlling,)]"
						+ " Arthur replies with a sad smile,"
						+ " [arthur.speech(While she'd often spend entire evenings alone with Rose, she all but banned me from spending any time with anyone else."
							+ " Not that I really had other people that I wanted to see, but it still felt a little stifling."
							+ " I'm not trying to make excuses for what I did, but maybe that's partly why I... erm, you know... with Lyssieth..."
							+ " Anyway, returning to your question, Lilaya was much as she still is now; kind and friendly with people she knows, but nervous and confrontational around strangers."
							+ " Like others with demonic blood, she's naturally gifted with the arcane, and also has quite a high sex drive...)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur blushes and clears his throat, before turning back to his experiments,"
						+ " [arthur.speech(I-I need to get on with this now!)]"
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index == 2) {
					return new Response("Lilaya", "You're already asking Arthur about Lilaya.", null);
					
				}
			}
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ROOM_ARTHUR_HYPNO_WATCH_START = new DialogueNodeOld("Arthur's Room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "The sight of the numerous arcane instruments and beakers of bubbling, brightly-coloured liquid that are scattered over every surface fills you with curiosity, and you can't help but ask,"
						+ " [pc.speech(What sort of experiments are you working on?"+((Main.game.getPlayer().getName().equals("Eru") && Main.game.getPlayer().getSurname().equals("Chitanda"))?" Watashi ki ni narimasu!":"")+")]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Nothing too interesting, really;"
							+ " Lilaya's tasked me to repeat one of her experiments that's related to detecting inter-dimensional particles."
							+ " I've already told her that it doesn't hold up in theory, let alone practice, but she doesn't listen,)]"
							+ " Arthur sighs, before a mischievous look flashes across his face,"
						+ " [arthur.speech(You know... there is <i>something</i> a little more interesting that I could do."
							+ " You see, back when I was working for Zaranix, one of his ideas was to get me to find an arcane method of changing a person's sexual orientation."
							+ " At first I thought it might prove impossible, like all his other ideas, but after doing a little research into it, I think I might actually have found a way to enchant a certain item that could do the trick."
							+ " I'd never have use for such an instrument myself, you understand, but in the name of scientific curiosity, I'd very much like to test my theory.)]"
					+ "</p>"
					+ "<p>"
						+ "Seeing where this conversation is headed, you sigh,"
						+ " [pc.speech(Why do I get the feeling you're about to ask me to do something?)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Well, it's only a small errand. I just need a package collected from Vicky over at Arcane Arts."
							+ " I would ask Lilaya to send Rose, but I can't afford the hour's worth of lecturing I receive every time I talk to her...)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Well, if it's just picking up a package, I could do that the next time I'm over in the Shopping Arcade,)]"
						+ " you offer."
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Excellent!)]"
						+ " Arthur exclaims,"
						+ " [arthur.speech(Thank you so much, [pc.name]! Now, I really should get on with this pointless experiment, or else Lilaya'll have yet more ammunition to use against me...)]"
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};

	
	private static GameCharacter testingSlave;
	
	public static final DialogueNodeOld ROOM_ARTHUR_HYPNO_WATCH_DELIVERY = new DialogueNodeOld("Arthur's Room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Presenting the package to Arthur, you ask the question at the forefront of your mind,"
						+ " [pc.speech(Here's the package from Arcane Arts. I couldn't help but wonder, though, what's inside?)]" // What's in the box?!
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Ah! Thank you [pc.name]! I hope you didn't have any trouble with Vicky; she can be a little overwhelming sometimes,)]"
						+ " Arthur says, taking the package from you and ringing the bell-pull in the corner of his room."
						+ " [arthur.speech(As to what's inside, I'll show you right now!)]"
					+ "</p>"
					+ "<p>"
						+ "Tearing off the tape that's holding the box together, Arthur quickly opens it up and reaches inside."
						+ " Stepping closer to get a better look, you see him pull out a silver pocket-watch, with a long chain attached to the top."
						+ " The hour and minute hands don't look as though they're moving, and the time that's shown is stuck at four minutes past ten."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(A broken pocket watch?)]"
						+ " You ask, a little unimpressed."
					+ "</p>"
					+ "<p>"
						+ "Before Arthur can answer, there's a knock at the door, before Rose steps inside the room,"
						+ " [rose.speech(You called, sir?)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Ah, yes, Rose! Could you please ask Lilaya to come up here? I need her help with a special experiment,)]"
						+ " Arthur asks, and Rose obediently steps back and hurries off to fetch her mistress."
					+ "</p>"
					+ "<p>"
						+ "Arthur hurriedly grabs several pages of notes, before placing them beside the watch on the table in front of you."
						+ " Before he has any time to explain what his plan is, the door suddenly burts open, and a very angry-looking Lilaya strides into the room,"
						+ " [lilaya.speech(What is it now Arthur?! Oh, [pc.name], you're here too!)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Well, Lilaya, you know I told you about that research I did into changing a person's sexual orientation? Well, I-)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Yes, I remember,)]"
						+ " Lilaya interrupts,"
						+ " [lilaya.speech(and do you remember <i>me</i> telling <i>you</i> about how I thought it was irresponsible to want to go around changing people's nature like that?!)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(I don't intend to use it, Lilaya!)]"
						+ " Arthur objects,"
						+ " [arthur.speech(It's only for research purposes! If I could harness the arcane, I'd do it all myself... Please, all I need you to do is follow these instructions and enchant this watch.)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Eugh, fine... But you're writing up a full report on this, on top of the work I've already assigned to you,)]"
						+ " Lilaya sighs, before stepping over to the desk and starting to follow Arthur's instructions."
					+ "</p>"
					+ "<p>"
						+ "After a few minutes, and several flashes of arcane energy, Lilaya's work is done, and you step forwards to see that the watch has undergone a drastic change."
						+ " Now, instead of looking like a regular time-piece, the face of the watch is a swirling vortex of purple energy."
						+ " Before you can take a closer look, Lilaya picks the watch up by the chain, and turns to Arthur,"
						+ " [lilaya.speech(So how does this thing work?)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(C-Careful Lilaya!)]"
						+ " Arthur exclaims, averting his eyes from the watch,"
						+ " [arthur.speech(You just have to swing it back and forth in front of someone, and they should undergo a hypnotic-type effect, which, if my calculations are correct, will change their orientation."
							+ " Oh, you should be able to channel arcane essences into it to change the desired orientation as well."
							+ " We really need a test subject for this...)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Well, whatever the result is, we can always undo it, right?"
							+ " I want to be the one who uses it, as I'll be able to sense if anything starts to go wrong, and you can't be the test subject, Arthur, as you'll need to be the one to fix any unexpected effects...)]"
						+ " Lilaya muses, before both she and Arthur turn towards you,"
						+ " [lilaya.speech([pc.Name]! You're ok letting us test this on you, aren't you? I promise that I won't apply the full effect, so there's really nothing to worry about!)]"
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
//			if(index==0) {
//				return "Offer self";
//				
//			} else if(index==1) {
//				return "Offer slave";
//				
//			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
//			if(responseTab==0) {
				if(index == 1) {
					return new Response("Agree", "You trust Lilaya enough to agree with her request.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF);
					
				} 
				//TODO
//				else if(index == 2) {
//					return new Response("Refuse", "Refuse to be a test subject.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED);
//				}
				else {
					return null;
				}
				
//			} else if(responseTab == 1){
//				if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HYPNO_WATCH) && Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH) == Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT) {
//					if(Main.game.getPlayer().getSlavesOwned().isEmpty()) {
//						return new Response("No slaves", "You don't have any slaves, so you'll have to offer yourself for Arthur's tests!", null);
//						
//					} else {
//						if(index!=0 && index<=Main.game.getPlayer().getSlavesOwned().size()) {
//							return new Response(Main.game.getNPCById((Main.game.getPlayer().getSlavesOwned().get(index-1))).getName(),
//									"Offer this slave to Arthur for testing.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SLAVE) {
//								@Override
//								public void effects() {
//									testingSlave = Main.game.getNPCById(Main.game.getPlayer().getSlavesOwned().get(index-1));
//									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.ORIENTATION_HYPNO_WATCH), false));
//									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.SIDE_HYPNO_WATCH));
//									Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
//								}
//							};
//						}
//					}
//					
//				} else {
//					return null;
//				}
//			}
//			
//			return null;
		}
	};

	private static SexualOrientation orientationTarget(GameCharacter target) {
		switch(target.getSexualOrientation()) {
			case AMBIPHILIC:
				if(target.isFeminine()) {
					return SexualOrientation.ANDROPHILIC;
				} else {
					return SexualOrientation.GYNEPHILIC;
				}
			default:
				return SexualOrientation.AMBIPHILIC;
		}
	}
	
	public static final DialogueNodeOld ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF = new DialogueNodeOld("Arthur's Room", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "Reassured by the fact Lilaya and Arthur are both highly-competent arcane researchers, you step forwards,"
						+ " [pc.speech(Ok, ok... But this had better not be permanent. Seeing as I'm "+Main.game.getPlayer().getSexualOrientation().getName()+", you'll probably need to set the watch to something other than that...)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Don't worry, this process is completely reversible,)]"
						+ " Arthur says, before turning to Lilaya,");
			
			switch(orientationTarget(Main.game.getPlayer())) {
				case AMBIPHILIC:
					UtilText.nodeContentSB.append(" [arthur.speech(Ok, Lilaya, enchant the watch to try and change [pc.name] into being ambiphilic.)]"
							+ "</p>"
							+ "<p>"
								+ "Holding the watch by the chain in one hand, Lilaya reaches up with her other, and, with a little flash of lilac, enchants the watch as Arthur instructs."
								+ " The swirling purple face slowly shifts into a light purple, and, instructed by Arthur to keep focused on the watch, you fix your gaze on the mesmerising surface as Lilaya starts slowly swaying it back and forth."
								+ " You feel your head rocking from side to side in time with each swing, and, starting to feel quite light-headed, you begin to find it very hard to remember what it is you're doing..."
							+ "</p>"
							+ "<i><p>"
								+ "[lilaya.speech(Perhaps we can leave the experiment for later,)] Lilaya moans, placing the watch to one side, before stepping forwards and planting a wet kiss on your lips."
							+ "</p>"
							+ "<p>"
								+ "[arthur.speech(We could all use a break,)] Arthur joins in, stepping around to take a firm hold of your waist."
							+ "</p>"
							+ "<p>"
								+ "[pc.speech(Mmm, yes...)] you moan, feeling a jolt of excitement as Arthur's hands run down to your groin."
								+ " Leaning back into him, you pull your aunt forwards, passionately thrusting your tongue into her mouth as you reach down to help guide Arthur's fingers between your legs..."
							+ "</p>"
							+ "<p>"
								+ "[lilaya.speech(Wake up, [pc.name]...)] Lilaya sighs..."
							+ "</p></i>");
					break;
				case ANDROPHILIC:
					UtilText.nodeContentSB.append(" [arthur.speech(Ok, Lilaya, enchant the watch to try and change [pc.name] into being androphilic.)]"
							+ "</p>"
							+ "<p>"
								+ "Holding the watch by the chain in one hand, Lilaya reaches up with her other, and, with a little flash of turquoise, enchants the watch as Arthur instructs."
								+ " The swirling purple face slowly shifts into a deep shade of blue, and, instructed by Arthur to keep focused on the watch,"
									+ " you fix your gaze on the mesmerising surface as Lilaya starts slowly swaying it back and forth."
								+ " You feel your head rocking from side to side in time with each swing, and, starting to feel quite light-headed, you begin to find it very hard to remember what it is you're doing..."
							+ "</p>"
							+ "<i><p>"
								+ "[arthur.speech(Perhaps we can leave the experiment for later,)] Arthur states, getting Lilaya to put the watch to one side, before leading her out of the room."
							+ "</p>"
							+ "<p>"
								+ "[pc.speech(What's wrong? Is it not work- ~Aah!~)] You start to question, but Arthur confidently steps around behind you and takes a firm hold of your waist."
							+ "</p>"
							+ "<p>"
								+ "[arthur.speech(I could see that look in your eyes...)] he says, and a jolt of excitement suddenly runs through you as you feel Arthur's hands run down to your groin."
							+ "</p>"
							+ "<p>"
								+ "[pc.speech(Mmm... Yes...)] You moan, turning your head and pressing your lips against Arthur's."
								+ " Leaning back into him, you passionately thrust your tongue into his mouth as you reach down to help guide his fingers between your legs..."
							+ "</p>"
							+ "<p>"
								+ "[lilaya.speech(Wake up, [pc.name]...)] you hear Lilaya call from the other side of the door..."
							+ "</p></i>");
					break;
				case GYNEPHILIC:
					UtilText.nodeContentSB.append(" [arthur.speech(Ok, Lilaya, enchant the watch to try and change [pc.name] into being gynephilic.)]"
							+ "</p>"
							+ "<p>"
								+ "Holding the watch by the chain in one hand, Lilaya reaches up with her other, and, with a little flash of scarlet, enchants the watch as Arthur instructs."
								+ " The swirling purple face slowly shifts into a deep shade of pink, and, instructed by Arthur to keep focused on the watch,"
									+ " you fix your gaze on the mesmerising surface as Lilaya starts slowly swaying it back and forth."
								+ " You feel your head rocking from side to side in time with each swing, and, starting to feel quite light-headed, you begin to find it very hard to remember what it is you're doing..."
							+ "</p>"
							+ "<i><p>"
								+ "[lilaya.speech(Honestly Arthur, we should leave this experiment for later,)] Lilaya states, putting the watch to one side, before leading Arthur out of the room."
							+ "</p>"
							+ "<p>"
								+ "[pc.speech(What's wrong? Is it not work- ~Aah!~)] You start to question, but Lilaya suddenly steps around behind you, and with a little giggle, reaches forwards to wrap her arms around you."
							+ "</p>"
							+ "<p>"
								+ "[lilaya.speech(I could see that look in your eyes...)] she says, and a jolt of excitement suddenly runs through you as you feel your aunt's hands run down to your groin."
							+ "</p>"
							+ "<p>"
								+ "[pc.speech(Mmm... Yes...)] You moan, turning your head and pressing your lips against Lilaya's."
								+ " Leaning back into her, you passionately thrust your tongue into her mouth as you reach down to help guide her fingers between your legs..."
							+ "</p>"
							+ "<p>"
								+ "[lilaya.speech(Wake up, [pc.name]...)] Lilaya sighs..."
							+ "</p></i>");
					break;
			}
			
			// Wake up and... smell the ashes...
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wake up", "You suddenly snap out of your trance.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.ORIENTATION_HYPNO_WATCH), false, true));

						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_UTIL_COMPLETE));
						
						Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP = new DialogueNodeOld("Arthur's Room", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "[lilaya.speech([pc.Name]? Wake up!)] Lilaya calls, and, with a blink, you suddenly snap out of the dream-like trance."
					+ " You're standing just where you were when the experiment started, but Lilaya, now with a mildly-concerned expression on her face, has placed the watch to one side, and is holding you by the shoulders."
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(W-What was that?!)] you ask, trying to shake the vision you just had from your head,"
					+ " [pc.speech(It was so real! We were... well...)]"
				+ "</p>"
				+ "<p>"
					+ "[lilaya.speech(Good, you're ok,)]"
					+ " Lilaya says, releasing you and stepping back,"
					+ " [lilaya.speech(I stopped the test before the change in orientation became permanent, and, more importantly, before a surge of corruptive arcane energy seeped into your mind.)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(Wait... What?! Nobody mentioned anything about that!)]"
				+ "</p>"
				+ "<p>"
					+ "[arthur.speech(I'm sorry, [pc.name], I didn't know that could be a side-effect,)]"
					+ " Arthur says, stepping forwards,"
					+ " [arthur.speech(It's a good job Lilaya was the one to carry out the test...)]"
				+ "</p>"
				+ "<p>"
					+ "[lilaya.speech(Well, at least you've come to no harm, [pc.name],)]"
					+ " Lilaya says,"
					+ " [lilaya.speech(You see, just as you appeared to enter that strange trance, I felt a wave of corruptive energy surging forwards."
						+ " If I hadn't stopped the test, not only would your sexual orientation have been permanently changed, but whatever vision you were witnessing would have undoubtedly played out in full;"
							+ " corrupting your mind in the process.)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(Thank you Lilaya,)]"
					+ " you say, stepping forwards to give your aunt a loving hug,"
					+ " [pc.speech(you saved me again, huh?)]"
				+ "</p>"
				+ "<p>"
					+ "[lilaya.speech(Y-Yes, well, y-you're welcome,)]"
					+ " she stammers, clearly a little embarrassed to be hugged in front of Arthur."
					+ " After a moment, she breaks off the hug, and, turning to pick up the watch, waves her hand over the face."
					+ " The swirling vortex fades away, leaving the watch looking just like any other, and, satisfied with the result, Lilaya hands it to you,"
					+ " [lilaya.speech(I've removed the enchantment, so there's no fear of accidentally subjecting anyone else to those effects."
						+ " I think you should keep hold of this. After all, now that you know what it feels like to be on the receiving end, I'm sure you have a greater respect for this item's power.)]"
				+ "</p>"
				+ "<p>"
					+ "You take the watch from Lilaya, nodding at her words,"
					+ " [pc.speech(I'll take good care of it.)]"
				+ "</p>"
				+ "<p>"
					+ "Turning to Arthur, Lilaya puts on a stern voice,"
					+ " [lilaya.speech(I expect a full report on my desk by this time tomorrow. And not a minute later!)]"
				+ "</p>"
				+ "<p>"
					+ "With that, Lilaya strides out of the room, leaving you alone with Arthur, who proceeds to glance over at you with a rather defeated-looking expression on his face,"
					+ " [arthur.speech(Well, it <i>does</i> work, at least... Sorry about all that, [pc.name]. I really should get on with this report now...)]"
				+ "</p>"
				+ "<p>"
					+ "With the hypnotic, orientation-changing watch now in your possession, you leave Arthur to get on with his work."
				+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Let Arthur continue with his other experiments.", ROOM_ARTHUR);
			} else {
				return null;
			}
		}
	};
	
	
	
	public static final DialogueNodeOld ROOM_ARTHUR_HYPNO_WATCH_OFFER_SLAVE = new DialogueNodeOld("Arthur's Room", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parse(testingSlave,
					"<p>"
						+ "Hypno-watch slave: [npc.name]"
						+ "Lilaya tries to turn [npc.herHim] (Gynephilic if not, else Androphilic if not)."
						+ "It starts to work, and [npc.name] finds Lilaya/Arthur looking hotter than they did a moment ago, but she suddenly stops, and you shake your head clear of the thoughts, finding that it's had no permanent effect."
						+ "She says she detected extremely strong arcane corruption about to burst out, and then Lilaya resets the enchantment for good measure."
						+ "She starts to scold Arthur for creating something like this, she gives you the watch."
						+ "(She demands Arthur write up a full report report about it, on top of his other work)."
						+ " She leaves, Arthur groans."
					+ "</p>");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Let Arthur continue with his other experiments.", ROOM_ARTHUR);
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNodeOld BIRTHING_ROOM = new DialogueNodeOld("Birthing room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Lilaya has had this room repurposed in order to be a suitable place for delivering children."
					+ "</p>"
					+ "<p>"
						+ "Instead of being carpeted, like most of the other rooms in this house, clean white tiles have been chosen for this room's flooring."
						+ " A surprisingly modern-looking birthing bed is sitting against one wall of the room, but apart from that, there isn't much else in the way of medical equipment."
						+ " A few comfortable chairs have been placed around the edges of the room, but other than those, and a drinks cabinet that's sitting in one corner, the room is devoid of any other furniture."
					+ "</p>"
					+ "<p>"
						+ "In anyone else's house, finding a room dedicated to delivering pregnancies might come as a bit of a shock, but Lilaya seems to be involved in all manner of strange things,"
							+ " so you shrug it off as just another peculiarity of this world."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld KITCHEN = new DialogueNodeOld("Kitchen", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			
			UtilText.nodeContentSB.append("<p>"
						+ "Just like every other room in Lilaya's house, the kitchen is far larger than any you've ever set foot in before."
						+ " A row of wooden cabinets, topped with polished granite, line the edge of the room, and a pair of long, free-standing worktops sit in the middle of the cavernous space."
						+ " The kitchen's trio of cast iron ovens, combined with its rustic oak flooring and lack of any modern-looking appliances, give it a rather vintage look."
					+ "</p>"
					+ "<p>"
						+ "There's an open doorway set into one side of the room, and, looking through the opening, you see a series of fridges, freezers and larder units."
						+ " Ingredients and foodstuffs of all shapes and sizes sit on open shelves, and you find yourself marvelling at the quantity and variety of supplies that are kept in stock."
					+ "</p>");
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append("<p>"
							+ "The kitchen is deserted at the moment, and there doesn't really seem to be much to do here."
						+ "</p>");
			} else {
				for(NPC slave : charactersPresent) {
					switch(slave.getObedience()) {
					case NEGATIVE_FIVE_REBELLIOUS: case NEGATIVE_FOUR_DEFIANT: case NEGATIVE_THREE_STRONG_INSUBORDINATE:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "Although <b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.name]</b> is here, [npc.she]'s quite clearly not doing any cooking."
									+ " To make matters worse, [npc.she] doesn't seem to care that you're watching [npc.herHim], and turns [npc.her] back on you."
								+ "</p>"));
						break;
					case NEGATIVE_ONE_DISOBEDIENT:  case NEGATIVE_TWO_UNRULY:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> appears to be half-heartedly preparing some food on the other side of the kitchen."
								+ "</p>"));
						break;
					case ZERO_FREE_WILLED:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> is busy cooking something in one of the kitchen's ovens."
								+ "</p>"));
						break;
					case POSITIVE_ONE_AGREEABLE: case POSITIVE_TWO_OBEDIENT:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> is preparing some food, and you can see that [npc.she]'s putting a lot of effort into doing a good job."
								+ "</p>"));
						break;
					case POSITIVE_THREE_DISCIPLINED: case POSITIVE_FOUR_DUTIFUL: case POSITIVE_FIVE_SUBSERVIENT:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> is dutifully making Lilaya a meal, and you notice that [npc.she]'s taking care to prepare it just the way your demonic aunt likes."
								+ "</p>"));
						break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index-1<Main.game.getCharactersPresent().size()) {
				return new Response(UtilText.parse(Main.game.getCharactersPresent().get(index-1), "[npc.Name]"), UtilText.parse(Main.game.getCharactersPresent().get(index-1), "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public void effects() {
						Main.game.setActiveNPC(Main.game.getCharactersPresent().get(index-1));
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ROOM_ROSE = new DialogueNodeOld("Rose's room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Evidence of Rose's close relationship with Lilaya is apparent as you approach the cat-girl's room."
					+ " Hanging on the door, there's a little home-made sign bearing her name, and underneath, in what is clearly Lilaya's handwriting, a little message reads: <i>'Lilaya's favourite pet'</i>."
				+ "</p>"
				+ "<p>"
					+ "The door appears to be locked at the moment, and there's no sound of anyone stirring within."
					+ " Rose seems to only allow herself some rest when she's sure that nobody else is around who might need her, so she's probably off in another part of the house at the moment."
				+ "</p>"
				+ "<p>"
					+ "You notice that there's a little bell set into the wall beside her door, and you wonder if you should try ringing it to get Rose to come up to her room."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Call for Rose", "Lilaya's slave, Rose, is always close at hand. If you were to ring the little bell beside her bedroom's door, she'd be sure to come running.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = "<p>"
									+ "Deciding that you'd like to talk to Rose, you decide to push the little bell that's situated beside her bedroom's door, causing a faint ringing noise to echo up from somewhere else in the house."
									+ " Despite the enormous size of Lilaya's home, Rose never seems to be far away, and you soon hear her walking down the corridor as she rushes to respond to your call."
								+ "</p>"
								+ "<p>"
									+ "As she approaches, you see her cat-like tail swishing from side to side, and you notice one of her ears twitch as she sees you standing outside her bedroom waiting for her."
									+ " Realising that you're obviously wanting to have a talk, she curtsies before issuing a greeting."
								+ "</p>"
								+ "<p>"
									+ "[rose.speech("+(Main.game.isDayTime() ? "Good day," : "Good evening,")+")]"
									+ " she says,"
									+ " [rose.speech(How may I help you?)]"
								+ "</p>";
						
						Main.game.getDialogueFlags().values.remove(DialogueFlagValue.auntHomeJustEntered);
						Main.game.getRose().setLocation(Main.game.getActiveWorld().getWorldType(), Main.game.getPlayer().getLocation(), false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ROOM_LILAYA = new DialogueNodeOld("Lilaya's room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "All of the spare rooms in the house have had their doors left wide open, so you know that this particular room is special just from the fact that its door is tightly shut."
						+ " As you approach, you catch the faint scent of Lilaya's perfume lingering out here in the corridor, letting you know exactly whose room this is."
					+ "</p>"
					+ "<p>"
						+ "There doesn't seem to be any sort of lock on the door, so you could always take a peek inside if you really wanted to..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Lilaya's room", "Have a look around Lilaya's room.", ROOM_LILAYA_INSIDE);

			}  else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ROOM_LILAYA_INSIDE = new DialogueNodeOld("Lilaya's room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Turning the door's handle, you find that your suspicions were correct in that it's not locked."
						+ " Peeking through the gap in the door as you open it, you breathe a sigh of relief as you see that Lilaya's room is currently deserted."
						+ " Stepping through and closing the door behind you, you find yourself standing in your demonic aunt's bedroom."
					+ "</p>"
					+ "<p>"
						+ "Lilaya's serious, mature attitude is in stark contrast to the girly nature of her personal space."
						+ " You find yourself surrounded on all sides by every shade of pink imaginable;"
							+ " from the pale rose hue of the walls, to the offensively-bright hot pink sheets on her bed, you feel as though you can probably take a reasonable guess as to what Lilaya's favourite colour is."
						+ " Some pieces of furniture, which have been painted white, along with the white ceiling and thick, cream carpet underfoot, are the only parts of her room that aren't one type of pink or another."
					+ "</p>"
					+ "<p>"
						+ "After taking in the surprisingly girly look of her room, the next thing that you notice is just how messy everything is."
						+ " The doors of her ceiling-height wardrobe have been left wide open, clothes are spilling out of half-closed drawers,"
							+ " and the floor is a complete minefield of dirty clothing, shoes, bags, hairbrushes, and all sorts of other miscellaneous items."
						+ " The mess even extends into her en-suite bathroom, and through the open door you can see crumpled towels littering the marble floor."
					+ "</p>"
					+ "<p>"
						+ "The huge, four-poster bed that dominates one side of the room seems to be the crowing achievement of Lilaya's messiness."
						+ " A huge pile of clothes has been stacked up on top of the hot pink sheets, and you find yourself wondering just how she manages to sleep in there."
						+ " Lilaya must have given Rose clear instructions to not tidy her room up, so, for whatever reason, you realise that she must like living in all this mess."
					+ "</p>"
					+ "<p>"
						+ " Now that your curiosity has been sated, you decide that there's nothing more to do in here, and turn back towards the door."
					+ "</p>"
					+ "<p>"
						+ "<i>Content will be added for this area soon!</i>"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Search drawers", "Look through Lilaya's drawers for anything interesting.", null,
//						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_INCEST)), null, AttributeLevelCorruption.TWO_HORNY,
//						null, null, null);
//
//			} else if (index == 2) {
//				return new Response("Search laundry", "Look through Lilaya's laundry basket for anything interesting.", null,
//						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_INCEST)), null, AttributeLevelCorruption.THREE_DIRTY,
//						null, null, null);
//
//			} else {
//				return null;
//			}
			return null;
		}
	};
	
	public static final DialogueNodeOld CLEAN_PANTIES = new DialogueNodeOld("Lilaya's room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You find Lilaya's clean panties." //TODO
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Panty masturbation", "Upon finding Lilaya's panties, you find yourself thinking some dirty thoughts...", CLEAN_PANTIES);

			} else if (index == 0) {
				return new Response("Back", "Step back into Lilaya's room.", ROOM_LILAYA);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld DIRTY_PANTIES = new DialogueNodeOld("Lilaya's room", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You find Lilaya's dirty panties." //TODO
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Panty masturbation", "Upon finding Lilaya's dirty panties, you find yourself thinking some dirty thoughts...", DIRTY_PANTIES);

			} else if (index == 0) {
				return new Response("Back", "Step back into Lilaya's room.", ROOM_LILAYA);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld GARDEN = new DialogueNodeOld("Garden courtyard", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "The garden courtyard consists of a series of wide, perfectly trimmed grass pathways, each one lined with beds of brightly-coloured flowers."
					+ " Although Rose is now the one responsible for maintaining it, you guess that Lilaya must have hired a professional company in order to have had this area landscaped so perfectly."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld FOUNTAIN = new DialogueNodeOld("Water fountain", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "In the very centre of the garden courtyard, a huge, ornate water fountain happily bubbles away with a mind of its own."
					+ " The structure is made up of a collection of intricate statues; each one of a beautiful woman in some manner of indecent pose."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// Companion NPC test was here. Since I'm hoping to bring in Arkhi as a fully fledged companion soon, this code can be removed, but it's here in case any tests on companions will be needed :3
			/*if (index == 1) {
				if(!Main.game.getPlayer().hasCompanion(Main.game.GetArkhi()))
				{
					return new ResponseEffectsOnly("Invite Arkhi", "Invites Arkhi to travel with you."){
						@Override
						public void effects() {
							Main.game.getPlayer().addCompanion(Main.game.GetArkhi());
						}
					};
				}
				else
				{
					return new Response("Invite Arkhi", "Arkhi is already traveling with you!", null){
					};
				}
			}
			else
			{
				return null;
			}*/
			return null;
		}
	};
	
	public static final DialogueNodeOld ENTRANCE_HALL = new DialogueNodeOld("Entrance hall", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Lilaya's house is by far the most impressive building you've ever been in."
						+ " The entrance hall that you find yourself standing in is alone far larger than your old flat, and is extravagantly decorated in a style befitting a royal palace."
						+ " Fine paintings and marble busts line the walls, and a huge crystal chandelier hangs from the double-height ceiling, casting its warm light over a grand, red-carpeted staircase that leads to the upper floor."
					+ "</p>"
					+ "<p>"
						+ "From previous explorations, you know that the rest of the house is furnished in much the same manner."
						+ " You tried to count all the rooms once, but gave up after reaching well over one hundred."
						+ " Despite its grand appearance and impressive size, the only member of staff you've ever seen is Lilaya's slave; the cat-girl maid called Rose."
						+ " Lilaya herself spends almost every waking moment working in her lab, resulting in the house being eerily quiet for most of the time."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Leave Lilaya's house."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_AUNTS_HOME, true);
					}
				};

			} if (index == 6) {
				return new ResponseEffectsOnly("Your room", "Fast travel up to your room."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR), PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
					}
				};

			} if (index == 7) {
				return new ResponseEffectsOnly("Lilaya's Lab", "Fast travel to Lilaya's Lab."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_LAB, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld STAIRCASE_UP = new DialogueNodeOld("Staircase up", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "The grand, red-carpeted staircase is one of the first things you see when entering Lilaya's house."
						+ " You wonder if you should use it to go upstairs."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Upstairs", "Go upstairs to the first floor."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR), PlaceType.LILAYA_HOME_STAIR_DOWN, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld STAIRCASE_DOWN = new DialogueNodeOld("Staircase down", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "The grand, red-carpeted staircase is one of the first things you see when entering Lilaya's house."
					+ " You wonder if you should use it to go back downstairs."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Downstairs", "Go back downstairs to the ground floor."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_STAIR_UP, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	private static String roseContent = "";
	private static boolean askedAboutDuties = false;
	public static final DialogueNodeOld AUNT_HOME_ROSE = new DialogueNodeOld("", "", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return Main.game.getLilaya().getName()
					+ "'s Home";
		}

		@Override
		public String getContent() {
			return roseContent;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Lilaya", "Ask Rose about her owner, Lilaya.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						askedAboutDuties = false;
						roseContent = "<p>"
								+ UtilText.parsePlayerSpeech("What can you tell me about Lilaya? She seems to be quite... reclusive,")
								+ " you ask."
								+ "</p>"
								+ "<p>"
								+ UtilText.parseSpeech("Yes, I suppose she does give that impression,", Main.game.getRose())
								+ " Rose replies, "
								+ UtilText.parseSpeech("but I'm afraid that I can't tell you much about her."
										+ " It's not my place to talk about my Mistress behind her back, as I'm sure you can understand."
										+ " If you want to know more about her, you'll have to get to know her better.", Main.game.getRose())
								+ "</p>"
								+ "<p>"
								+ "Rose blushes and fidgets nervously on the spot, obviously feeling very awkward about telling you that she can't help you out."
								+ " You suppose that she's right, though."
								+ " It would be a big breach of trust if Rose were to start telling you Lilaya's secrets."
								+ "</p>";
					}
				};

			} else if (index == 2) {
				return new Response("Slavery", "Ask Rose how she became a slave.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						askedAboutDuties = false;
						roseContent = "<p>"
									+ "Not really sure about the best way to approach the subject, you decide to just ask your question outright, and hope that you don't cause Rose any offence, "
									+ UtilText.parsePlayerSpeech("If you don't mind me asking, how did you end up as a slave?")
								+ "</p>"
								+ "<p>"
									+ "Rose smiles to put you at ease, obviously sensing that you're a bit hesitant to broach the subject, "
									+ UtilText.parseSpeech("Well, like most other slaves, I sold myself into slavery.", Main.game.getRose())
								+ "</p>"
								+ "<p>"
									+ "You can't help but feel shocked as you hear that Rose willingly became a slave, but before you can ask why, she seems to sense your incoming question and continues, "
									+ "[rose.speech(When you sell yourself as a slave, all your debts and crimes are forgiven, so many people choose a life of slavery in order to escape their past."
											+ " I suppose I'm somewhat of an unusual case, though, because I didn't have anything to run away from."
											+ " You see, I used to work as a maid for Lilaya's mother, Lyssieth, and while working for her, Lilaya and I grew very close...)]"
								+ "</p>"
								+ "<p>"
									+ "Rose suddenly blushes, and you notice that her tail has wrapped itself tightly around her leg."
									+ " She's obviously thinking back to when she first met Lilaya, and as she takes a moment to move past her recollection of the past, you wonder how she ended up selling herself into slavery."
								+ "</p>"
								+ "<p>"
									+ "You don't have to wonder for too long, as after a moment, Rose suddenly remembers that you're here, and continues,"
									+ " [rose.speech(Lyssieth didn't approve of how much time we were spending together, and when Lilaya started to insist that we call each other 'sister', her mother didn't take it well..."
											+ " I was called before her and told that I was to be fired, and never to speak to Lilaya again."
											+ " I didn't have any family or friends, and meeting Lilaya was, and still is, the best thing to ever have happened to me."
											+ " At my suggestion, we agreed that the only way Lyssieth would tolerate my presence would be if I became Lilaya's slave."
											+ " We were forbidden from referring to each other as 'sister', which we still keep to now, even though Lyssieth isn't around anymore, but when we're in private, we..."
											+ " I-I've said too much...)]"
								+ "</p>"
								+ "<p>"
									+ "From the moment you saw them interacting with each other, you knew Lilaya and Rose were close, but even so, the revelation that Rose sacrificed her freedom so that they could be together takes you by surprise."
									+ " As Rose starts blushing, you realise that they must truly love each other, and you feel a little bit awkward at having Rose tell you about this intimate part of their relationship."
								+ "</p>";
					}
				};

			} else if (index == 3) {
				return new Response("World", "Ask Rose to tell you something about this world.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						askedAboutDuties = false;
						roseContent = "<p>"
								+ "You wonder if Rose can tell you anything interesting about this world, "
								+ UtilText.parsePlayerSpeech("What can you tell me about this world?")
								+ "</p>"
								+ "<p>"
								+ UtilText.parseSpeech("Well, I don't really know what's different here compared to where you come from, so without spending all day telling you every detail about this place,"
										+ " I'll just give you some general information, ", Main.game.getRose())
								+ " Rose replies helpfully, "
								+ UtilText.parseSpeech("Basically, this world is ruled by queen Lilith, who's the most powerful demon to ever have lived."
										+ " She lives here in Dominion, in that huge tower that can be seen from miles around."
										+ " Although she personally rules over Dominion, she allows her daughters, the Lilin, to control other parts of her domain."
										+ " Surrounding Dominion, there are four different areas of control, each ruled by a different Lilin;"
										+ " the jungle, ruled by Lyxias; the Foloi Fields, ruled by Lunette; the desert, ruled by Lisophia; and the Endless Sea, ruled by Lirecea.", Main.game.getRose())
								+ "</p>"
								+ "<p>"
								+ "It seems as though every Lilin's name beings with an 'L', and you wonder how Rose is able to remember all these unusual names."
								+ " Even though Rose's information was quite limited, you're still grateful that she's told you a little more about how this world works."
								+ "</p>"
								+ "<p>"
								+ "From the way she finished her last sentence, you thought Rose had finished, but instead, she continues, "
								+ UtilText.parseSpeech("If you're looking for more information about the culture and ways of Dominion, then I suppose I can give you a few pointers as well."
										+ " The four most common races are cat, dog, horse, and wolf-morphs."
										+ " Although there are several other races that inhabit this world, a lot of the more exotic ones tend to stay in the outer regions."
										+ " There aren't many humans in our world, as most of them transform themselves in order to fit in with Dominion society a bit easier."
										+ " Oh, speaking of which, watch out what you eat and drink, as there are many transformative consumables that will alter your body."
										+ " So, unless you want to end up looking more like me, you shouldn't taste everything you find.", Main.game.getRose())
								+ "</p>"
								+ "<p>"
								+ "With that last warning, Rose looks like she's finished, and you wonder if you should ask her anything else, or leave her to carry on with her dusting."
								+ "</p>";
					}
				};

			} else if (index == 4) {
				return new Response("Duties", "Ask Rose about what duties she's expected to perform.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						askedAboutDuties = true;
						roseContent = "<p>"
								+ "Being curious about the sort of things Rose is expected to do as a slave, you ask her, "
								+ UtilText.parsePlayerSpeech("So, what sort of things do you have to do for Lilaya?")
								+ "</p>"
								+ "<p>"
								+ "Rose's cheeks suddenly flush red, and although you asked your question in innocence, you realise that Rose's thoughts have instantly turned to some of her more intimate duties, "
								+ UtilText.parseSpeech("E-Erm, well, ah, y-you know, demons get pretty horny, and Lilaya's no different,", Main.game.getRose())
								+ " she stammers, before regaining her composure, "
								+ UtilText.parseSpeech("but other than that, I spend most of my time cleaning and cooking. You've got no idea how much dusting this place requires!", Main.game.getRose())
								+ "</p>"
								+ "<p>"
								+ "As she says this, you suddenly become aware that she's holding a little feather duster, of the same 'French-maid' style as the rest of her uniform."
								+ " Looking at the little instrument, you find your gaze being curiously drawn to Rose's delicate hands."
								+ "</p>"
								+ "<p>"
								+ "Sensing what you're looking at, Rose suddenly blushes, and blurts out, "
								+ UtilText.parseSpeech("I use a duster like this so I don't have to get my hands dirty!"
										+ " You know, I take really good care of them, but Lilaya never seems to notice...", Main.game.getRose())
								+ "</p>"
								+ "<p>"
								+ "You don't quite know what's come over you, but you're finding it hard to think of anything other than Rose's perfect, feminine hands."
								+ " As you watch, she places her duster down on a little table next to you, and, with a little step forwards, she starts to gently rub her hands together,"
									+ " sliding her slender, feminine fingers over each other and making little moaning noises."
								+ " With a gulp, you suddenly realise that she's displaying them for your benefit, and as she looks up into your eyes, she makes a pleading little whine."
								+ "</p>";
					}
				};

			} else if (index == 5 && askedAboutDuties) {
				return new Response("Rose's hands", "You've never noticed how amazing Rose's hands are before...", ROSE_HANDS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
				};

			} else if (index == 0) {
				return new Response("Dismiss", "Let Rose get back on with her work.", ROOM_ROSE){
					@Override
					public void effects() {
						askedAboutDuties = false;
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
					
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld ROSE_HANDS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Rose's hands";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "As Rose steps forwards, you find yourself unable to look at anything but her hands... her amazing hands... "
					+ "[pc.thought(Holy shit... Look at those hands!)]"
					+ "</p>"
					+ "<p>"
					+ "As her cat-like tail swishes excitedly behind her, Rose holds up her perfect, angelic hands."
					+ " Her soft, pale skin almost seems to glow as she steps closer and closer, and you subconsciously start reaching out towards her delicate fingers."
					+ " Her nails are painted a soft shade of pink, and as your fingertips touch with hers, you feel her soft warmth radiating into your [pc.armSkin]."
					+ "</p>"
					+ "<p>"
					+ "The moment you make physical contact, Rose lets out a desperate moan, and as her cheeks somehow manage to flush an ever deeper shade of crimson, she sighs, "
					+ "[rose.speech(~Aah!~ Yes! Lilaya never appreciates how much effort I put into keeping my hands so nice and soft! ~Yes!~ Take me! Take me now!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Hand-holding", "Warning: This content contains extreme descriptions of hand-holding, finger sucking, and even palm-licking."
						+ " <b>Please remember that you need to have read the disclaimer before playing this game!</b> <b style='color:"+BaseColour.CRIMSON.toWebHexString()+";'>18+ only!</b>",
						true, false,
						new SMRoseHands(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.HAND_SEX_DOM_ROSE)),
								Util.newHashMapOfValues(new Value<>(Main.game.getRose(), SexPositionSlot.HAND_SEX_SUB_ROSE))),
						Rose.END_HAND_SEX);

			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
}
