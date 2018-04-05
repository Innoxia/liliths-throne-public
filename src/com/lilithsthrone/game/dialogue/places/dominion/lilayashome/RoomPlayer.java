package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.1.98
 * @author Innoxia
 */
public class RoomPlayer {
	
	private static int sleepTimer = 240;

	private static Response getResponseRoom(int index) {
		int minutesPassed = (int) (Main.game.getMinutesPassed() % (24 * 60));
		
		sleepTimer = (Main.game.isDayTime()
				? (int) ((60 * 21) - minutesPassed)
				: (int) ((60 * (minutesPassed<(60*7)?7:31)) - minutesPassed));

		if (index == 1) {
			return new Response("Rest", "Rest for four hours. As well as replenishing your energy and aura, you will also get the 'Well Rested' status effect.", AUNT_HOME_PLAYERS_ROOM_SLEEP){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setLust(0);
					if(Main.game.getPlayer().hasTrait(Perk.JOB_UNEMPLOYED, true)) {
						Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED_BOOSTED, (8 * 60) + 240);
					} else {
						Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6 * 60) + 240);
					}
				}
			};

		} else if (index == 2) {
			return new Response("Rest until " + (Main.game.isDayTime() ? "Evening" : "Morning"),
					"Rest for " + (sleepTimer >= 60 ? sleepTimer / 60 + " hours " : " ")
					+ (sleepTimer % 60 != 0 ? sleepTimer % 60 + " minutes" : "")
					+ " until " + (Main.game.isDayTime() ? "evening (21:00)." : "morning (07:00).")
					+ " As well as replenishing your energy and aura, you will also get the 'Well Rested' status effect.", AUNT_HOME_PLAYERS_ROOM_SLEEP_LONG){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setLust(0);
					if(Main.game.getPlayer().hasTrait(Perk.JOB_UNEMPLOYED, true)) {
						Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED_BOOSTED, (8 * 60) + sleepTimer);
					} else {
						Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6 * 60) + sleepTimer);
					}
				}
			};

		} else if (index == 3) {
			return new Response("Wash", "Use your room's en-suite to take a bath or shower. Rose will come and clean your clothes while you wash yourself.", AUNT_HOME_PLAYERS_ROOM_WASH){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					
					Set<OrificeType> dirtyOrifices = new HashSet<>();
					for(Entry<OrificeType, Integer> entry : Main.game.getPlayer().getCummedInAreaMap().entrySet()) {
						if(entry.getValue()>0) {
							dirtyOrifices.add(entry.getKey());
						}
					}
					
					Main.game.getPlayer().washAllOrifices();
					Main.game.getPlayer().calculateStatusEffects(0);
					Main.game.getPlayer().cleanAllDirtySlots();
					Main.game.getPlayer().cleanAllClothing();
					
					for(OrificeType orifice : OrificeType.values()) {
						if(dirtyOrifices.contains(orifice)) {
							switch(orifice) {
								case ANUS:
									if(Main.game.getPlayer().getCummedInAreaMap().get(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your [pc.asshole] as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your [pc.asshole]."));
									}
									break;
								case ASS:
									if(Main.game.getPlayer().getCummedInAreaMap().get(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum off of your [pc.ass] as you can, but there's so much that's covering it, that you're unable to fully clean yourself!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum off of your [pc.ass]."));
									}
									break;
								case BREAST:
									if(Main.game.getPlayer().getCummedInAreaMap().get(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum off of your [pc.breasts] as you can, but there's so much that's covering it, that you're unable to fully clean yourself!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum off of your [pc.breasts]."));
									}
									break;
								case MOUTH:
									if(Main.game.getPlayer().getCummedInAreaMap().get(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "The shower does nothing to clean the cum out of your stomach!"));
									}
									break;
								case NIPPLE:
									if(Main.game.getPlayer().getCummedInAreaMap().get(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your [pc.nipples] as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your [pc.nipples]."));
									}
									break;
								case THIGHS:
									if(Main.game.getPlayer().getCummedInAreaMap().get(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum off of your [pc.thighs] as you can, but there's so much that's covering it, that you're unable to fully clean yourself!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum off of your [pc.thighs]."));
									}
									break;
								case URETHRA_PENIS:
									if(Main.game.getPlayer().getCummedInAreaMap().get(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your cock's urethra as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your cock's urethra."));
									}
									break;
								case URETHRA_VAGINA:
									if(Main.game.getPlayer().getCummedInAreaMap().get(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your vagina's urethra as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your vagina's urethra."));
									}
									break;
								case VAGINA:
									if(Main.game.getPlayer().getCummedInAreaMap().get(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your [pc.pussy] as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your [pc.pussy]."));
									}
									break;
							}
						}
					}
					
					Main.game.getTextEndStringBuilder().append("<p>"
								+ "<b style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Your clothes have been cleaned, and you feel refreshed!</b>"
							+ "</p>");
				}
			};

		} else if (index == 4) {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				return new Response("Calendar", "Take another look at the enchanted calendar that's pinned up on one wall.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
			} else {
				return new Response("<span style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Calendar</span>", "There's a calendar pinned up on one wall. Take a closer look at it.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
			}
			
		} else if (index == 6) {
			return new ResponseEffectsOnly("Entrance hall", "Fast travel down to the entrance hall."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_ENTRANCE_HALL, true);
				}
			};

		} else if (index == 7) {
			return new ResponseEffectsOnly("Lilaya's Lab", "Fast travel down to Lilaya's laboratory."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_LAB, true);
				}
			};

		} else {
			return null;
		}
	}
	
	private static String formatWashingArea(boolean isFullyCleaned, String input) {
		return "<p style='color:"+(isFullyCleaned?Colour.GENERIC_GOOD.toWebHexString():Colour.CUMMED.toWebHexString())+";'><i>"
					+ input
				+ "</i></p>";
	}

	public static final DialogueNodeOld ROOM = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Your room is well-furnished, containing a king-sized bed, two sets of drawers and a full-height wardrobe."
						+ " A row of windows provide a good view of the courtyard garden below, which is flanked on all sides by different wings of Lilaya's house."
					+ "</p>"
					+ "<p>"
						+ "Your private en-suite bathroom is accessible from here via a door on one side of the room."
						+ " The bathroom is considerably larger than any other that you've used before, and is extravagantly decorated in marble and gold."
					+ "</p>"
					+ "<p>"
						+ "Like everything else that normally would have run on electricity in your world, the lighting, shower, and radiators all appear to be powered by the arcane."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(index);
		}
	};
	
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_SLEEP = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 240;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You set your phone's alarm before drawing the curtains, lying on your bed and closing your eyes."
						+ " You feel extremely safe and comfortable here in Lilaya's home, and soon drift off to sleep, thinking about all the things that have happened to you recently..."
					+ "</p>"
					+ "<p>"
						+ "<i>Beep-beep... beep-beep... bee-</i>"
					+ "</p>"
					+ "<p>"
						+ "Rolling over, you fumble for your phone, turning off the alarm before sinking back onto the bed."
						+ " You decide that you'd better get up, and as you do so, you let out a satisfied yawn and stretch your [pc.arms]."
						+ " You open the curtains and gather your things, ready to set out once more."
					+ "</p>"
					+ "<p>"
						+ "<b style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>You feel completely refreshed!</b>"
					+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_SLEEP_LONG = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return sleepTimer;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You set your phone's alarm before drawing the curtains, lying on your bed and closing your eyes."
						+ " You feel extremely safe and comfortable here in Lilaya's home, and soon drift off to sleep, thinking about all the things that have happened to you recently..."
					+ "</p>"
					+ "<p>"
						+ "<i>Beep-beep... beep-beep... bee-</i>"
					+ "</p>"
					+ "<p>"
						+ "Rolling over, you fumble for your phone, turning off the alarm before sinking back onto the bed."
						+ " You decide that you'd better get up, and as you do so, you let out a satisfied yawn and stretch your [pc.arms]."
						+ " You open the curtains and gather your things, ready to set out once more."
					+ "</p>"
					+ "<p>"
					+ "<b style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>You feel completely refreshed!</b>"
					+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_WASH = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
						+ "You step into the en-suite, marvelling at its extravagant design. Leaving your dirty clothes on the other side of the door, you take a long, relaxing shower."
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};

	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_CALENDAR = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "You step over to one side of your room, where a calendar has been pinned to the wall."
						+ " It's quite obviously enchanted, for as you flick through the pages, you discover that each month's picture changes based on your current train of thought.");
						
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				UtilText.nodeContentSB.append(" As you think about each month, a thematically-dressed man, incubus, or some kind of animal-boy appears on the page.");
			} else {
				UtilText.nodeContentSB.append(" As you think about each month, a thematically-dressed woman, succubus, or some kind of animal-girl appears on the page.");
			}
			
			if(Main.game.getPlayer().getCorruptionLevel()==CorruptionLevel.ZERO_PURE) {
				UtilText.nodeContentSB.append(" The more you flick back and forth through the calendar, the more scantily-dressed the subject of each picture becomes, until you suddenly realise what you're doing and step back, shocked.");
			} else {
				UtilText.nodeContentSB.append(" The more you flick back and forth through the calendar, the more scantily-dressed the subject of each picture becomes, and you find yourself getting a little turned on...");
			}
			UtilText.nodeContentSB.append("</p>");
			
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				UtilText.nodeContentSB.append("<p>"
						+ "Suddenly remembering what it was that you wanted to look at, you scan through the calendar to find the current date,");
			} else {
				UtilText.nodeContentSB.append("<p>"
						+ "You were so distracted by the changing pictures that you momentarily forgot what it was that you wanted to check."
						+ " Shaking your head, you flip back through the calendar to find out what the current date is,");
			}
			
			UtilText.nodeContentSB.append(" and see that it's the <b style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>"
						+ Main.game.getDateNow().format(DateTimeFormatter.ofPattern("d", Locale.ENGLISH))
						+ Util.getDayOfMonthSuffix(Main.game.getDateNow().getDayOfMonth())
						+ " "
						+ Main.game.getDateNow().format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH))
						+ ", "
						+ Main.game.getDateNow().format(DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH))
					+"</b>. From a quick calculation "+(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)<IntelligenceLevel.ONE_AVERAGE.getMaximumValue()?"(with some help from your phone's calculator)":"")
					+ ", you figure out that it's been <b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>"+Main.game.getDayNumber()+" day"+(Main.game.getDayNumber()>1?"s":"")+"</b> since you appeared in this world."
					+ "</p>");
			
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				UtilText.nodeContentSB.append("<p>"
						+ "[pc.thought(Wait... "+Main.game.getDateNow().format(DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH))+"?! I need to check in with Lilaya about that...)]"
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append("<p>"
					+ "You notice that on each page of the calendar, there's a few paragraphs detailing the events that occur during that month."
					+ "</p>");
			
			// TODO probably not the best place to put it?
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.knowsDate);
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Step away from the calendar.", ROOM);
				
			} else if(index==1) {
				return new Response("October", "Read the information on October's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER);
				
			} else if(index==2) {
				return new Response("December", "Read the information on December's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Flicking through the calendar until you're looking at the page for October, you see that this month's image is now of a topless incubus flexing his muscles."
							+ " After gazing at the picture for a few moments, you force yourself to look away and read the information that's written beneath:"
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Flicking through the calendar until you're looking at the page for October, you see that this month's image is now of a suggestively-posed succubus wearing nothing but a witch's hat."
							+ " After gazing at the picture for a few moments, you force yourself to look away and read the information that's written beneath:"
						+ "</p>");
			}

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+Colour.BASE_ORANGE.toWebHexString()+";'>October</span>"
							+ "</br>"
							+ "<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Lilith's Month</span>"
					+ "</h4>"
					+ "<p><i>"
						+ "October was chosen by Lilith herself to be the month in which all of Dominion shows their devotion towards their glorious queen!"
						+ " Banners and ribbons, typically in Lilith's traditional colours of orange, purple, and black, are proudly flown from every building, in order to show our queen just how devoted her subjects are!"
						+ " While all citizens are expected to celebrate Lilith's rule, the most devout of her followers dress up in traditional demonic costumes in order to prove their loyalty."
					+ "</p>"
					+ "<p>"
						+ "The officially sanctioned 'Cult of Lilith' is the most fanatical group of our queen's supporters, and are very easy to spot during October,"
							+ " as they refuse to wear anything but traditional witch's outfits, of the sort worn by Lilith herself in centuries past."
						+ " While content to carry out their acts of devotion in private for the rest of the year, these cultists can get quite zealous during October,"
							+ " and will sometimes even go so far as to approach members of the public and demand a display of loyalty from them!"
					+ "</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Stop reading about October.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
				
			} else if(index==1) {
				return new Response("October", "You're already reading October's page!", null);
				
			} else if(index==2) {
				return new Response("December", "Read the information on December's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "You turn the calendar to the month of December, and discover that the model adorning this page is a muscular reindeer-boy, who's grinning as he presents his huge cock to the viewer."
							+ " After gazing at the picture for a few moments, you force yourself to look away and read the information that's written beneath:"
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "You turn the calendar to the month of December, and discover that the model adorning this page is a curvy reindeer-girl, who's bending over a wooden table and presenting her wet pussy to the viewer."
							+ " After gazing at the picture for a few moments, you force yourself to look away and read the information that's written beneath:"
						+ "</p>");
			}

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+Colour.BASE_RED.toWebHexString()+";'>December</span>"
							+ "</br>"
							+ "<span style='color:"+Colour.BASE_GOLD.toWebHexString()+";'>Yuletide</span>"
					+ "</h4>"
					+ "<i>"
					+ "<p>"
						+ "The celebration of Yuletide is held throughout the month of December, and sometimes even drags on through January and February!"
						+ " Giving gifts, holding feasts, and throwing parties are the ways in which Yuletide is celebrated."
						+ " As this celebration coincides with the arrival of the reindeer-morphs in Dominion, it has become tradition for the gifts given during Yuletide to be items purchased from these reindeer-morphs."
					+ "</p>"
					+ "<p>"
						+ "The figure associated with this season is the Lilin 'J&oacute;lnir' (meaning 'the Yule one', or 'Yule figure')."
						+ " Not much is known about this Lilin, other than the obvious fact that their name breaks with the tradition of all Lilin's names beginning with an 'L', and that they are the leader of the 'Wild Hunt'."
					+ "</p>"
					+ "<p>"
						+ "Consisting of a wandering horde of summoned arcane elementals, the 'Wild Hunt' was driven away from Dominion many years ago, and is now only found during Yuletide out in the Foloi Fields and the forests nearby."
					+ "</p>"
					+ "</i>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Stop reading about October.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
				
			} else if(index==1) {
				return new Response("October", "Read the information on October's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER);
				
			} else if(index==2) {
				return new Response("December", "You're already reading December's page.", null);
				
			} else {
				return null;
			}
		}
	};
}
