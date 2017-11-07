package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.List;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.dominion.SMRoseHands;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.Dominion;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.LilayasHome;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.1.87
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
		public Response getResponse(int index) {
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
						
						Main.mainController.moveGameWorld(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_ENTRANCE_HALL, true);
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
		public Response getResponse(int index) {
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
						return MiscDialogue.getSlaveryOverviewDialogue();
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
						return MiscDialogue.getSlaveryManagementDialogue(Main.game.getPlayerCell().getPlace().getDialogue(false), null);
					}
				};
			} else {
				return new Response("Slave List", "You'll need a slaver license before you can access this menu!",  null);
			}
			
		} else if (index == 3) {
			if(Main.game.getPlayer().isHasSlaverLicense()) {
				return new Response("Room List", "Enter the room upgrades options screen.", MiscDialogue.ROOM_MANAGEMENT);
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
			return getRoomResponse(index);
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
						
						Main.game.getDialogueFlags().auntHomeJustEntered = false;
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Leave Lilaya's house."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), Dominion.CITY_AUNTS_HOME, true);
					}
				};

			} if (index == 6) {
				return new ResponseEffectsOnly("Your room", "Fast travel up to your room."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR), LilayasHome.LILAYA_HOME_ROOM_PLAYER, true);
					}
				};

			} if (index == 7) {
				return new ResponseEffectsOnly("Lilaya's Lab", "Fast travel to Lilaya's Lab."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), LilayasHome.LILAYA_HOME_LAB, true);
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Upstairs", "Go upstairs to the first floor."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR), LilayasHome.LILAYA_HOME_STAIR_DOWN, true);
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Downstairs", "Go back downstairs to the ground floor."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), LilayasHome.LILAYA_HOME_STAIR_UP, true);
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
		public Response getResponse(int index) {
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
								+ UtilText.parseSpeech("When you sell yourself as a slave, all your debts and crimes are forgiven, so many people choose a life of slavery in order to escape their past."
										+ " I suppose I'm somewhat of an unusual case, though, because I didn't have anything to run away from."
										+ " You see, I used to work as a maid for Lilaya's mother, Lyssieth, and while working there, Lilaya and I grew quite close...", Main.game.getRose())
								+ "</p>"
								+ "<p>"
								+ "Rose suddenly blushes, and you notice that her tail has wrapped itself tightly around her leg."
								+ " She's obviously thinking back to when she first met Lilaya, and as she takes a moment to move past her recollection of the past, you wonder how she ended up selling herself into slavery."
								+ "</p>"
								+ "<p>"
								+ "You don't have to wonder for too long, as after a moment, Rose suddenly remembers that you're here, and continues, "
								+ UtilText.parseSpeech("As you might imagine, Lyssieth didn't approve of how much time Lilaya was spending with her maid."
										+ " I was called before her and told that I was to be fired, and never to speak to Lilaya again."
										+ " I didn't have any family or friends, and meeting Lilaya was, and still is, the best thing to ever have happened to me."
										+ " At my suggestion, we agreed that the only way Lyssieth would tolerate my presence would be if I became Lilaya's slave."
										+ " So, I sold myself to her and ended up here!", Main.game.getRose())
								+ "</p>"
								+ "<p>"
								+ "From the moment you saw them interacting with each other, you knew Lilaya and Rose were close, but even so, the revelation that Rose sacrificed her freedom so that they could be together takes you by surprise."
								+ " You realise that they must truly love each other, and you feel a little bit awkward at having Rose tell you about this intimate part of their relationship."
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
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_LAB, false);
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseSex("Hand-holding", "Warning: This content contains extreme descriptions of hand-holding, finger sucking, and even palm-licking."
						+ " <b>Please remember that you need to have read the disclaimer before playing this game!</b> <b style='color:"+BaseColour.CRIMSON.toWebHexString()+";'>18+ only!</b>",
						ROSE_HANDS,
						true, false, Main.game.getRose(), new SMRoseHands(), Rose.END_HAND_SEX);

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
