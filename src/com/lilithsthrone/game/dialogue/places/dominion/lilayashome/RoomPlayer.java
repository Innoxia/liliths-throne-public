package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.format.DateTimeFormatter;

import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.LilayasHome;

/**
 * @since 0.1.75
 * @version 0.1.8
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
			return new Response("Rest", "Rest for four hours. As well as replenishing your health, willpower and stamina, you will also get the 'Well Rested' status effect.", AUNT_HOME_PLAYERS_ROOM_SLEEP){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setStamina(Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM));
					Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6 * 60) + 240);
				}
			};

		} else if (index == 2) {
			return new Response("Rest until " + (Main.game.isDayTime() ? "Evening" : "Morning"),
					"Rest for " + (sleepTimer >= 60 ? sleepTimer / 60 + " hours " : " ")
					+ (sleepTimer % 60 != 0 ? sleepTimer % 60 + " minutes" : "")
					+ " until " + (Main.game.isDayTime() ? "evening (21:00)." : "morning (07:00).")
					+ " As well as replenishing your health, willpower and stamina, you will also get the 'Well Rested' status effect.", AUNT_HOME_PLAYERS_ROOM_SLEEP_LONG){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setStamina(Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM));

					Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6
							* 60)
							+ sleepTimer);
				}
			};

		} else if (index == 3) {
			return new Response("Wash", "Use your room's en-suite to take a bath or shower. Rose will come and clean your clothes while you wash yourself.", AUNT_HOME_PLAYERS_ROOM_WASH){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setStamina(Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM));
					for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped())
						c.setDirty(false);
					Main.game.getPlayer().cleanAllClothing();
					Main.game.getPlayer().removeStatusEffect(StatusEffect.CREAMPIE_ANUS);
					Main.game.getPlayer().removeStatusEffect(StatusEffect.CREAMPIE_NIPPLES);
					Main.game.getPlayer().removeStatusEffect(StatusEffect.CREAMPIE_PENIS);
					Main.game.getPlayer().removeStatusEffect(StatusEffect.CREAMPIE_VAGINA);
				}
			};

		} else if (index == 4) {
			if(Main.game.getDialogueFlags().knowsDate) {
				return new Response("Calendar", "Take another look at the enchanted calendar that's pinned up on one wall.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
			} else {
				return new Response("<span style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Calendar</span>", "There's a calendar pinned up on one wall. Take a closer look at it.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
			}
			
		} else if (index == 6) {
			return new ResponseEffectsOnly("Entrance hall", "Fast travel down to the entrance hall."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), LilayasHome.LILAYA_HOME_ENTRANCE_HALL, true);
				}
			};

		} else if (index == 7) {
			return new ResponseEffectsOnly("Lilaya's Lab", "Fast travel down to Lilaya's laboratory."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), LilayasHome.LILAYA_HOME_LAB, true);
				}
			};

		} else {
			return null;
		}
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
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
			return "<p>"
						+ "You step into the en-suite, marvelling at its extravagant design. Leaving your dirty clothes on the other side of the door, you take a long, relaxing shower."
					+ "</p>"
					+ "<p>"
						+ "<b style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Your clothes have been cleaned, and you feel refreshed!</b>"
					+ "</p>";
		}


		@Override
		public Response getResponse(int index) {
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
			
			if(Main.game.getDialogueFlags().knowsDate) {
				UtilText.nodeContentSB.append("<p>"
						+ "Suddenly remembering what it was that you wanted to look at, you scan through the calendar to find the current date,");
			} else {
				UtilText.nodeContentSB.append("<p>"
						+ "You were so distracted by the changing pictures that you momentarily forgot what it was that you wanted to check."
						+ " Shaking your head, you flip back through the calendar to find out what the current date is,");
			}
			
			UtilText.nodeContentSB.append(" and see that it's the <b style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>"
						+ Main.game.getDateNow().format(DateTimeFormatter.ofPattern("d"))
						+ Util.getDayOfMonthSuffix(Main.game.getDateNow().getDayOfMonth())
						+ " "
						+ Main.game.getDateNow().format(DateTimeFormatter.ofPattern("MMMM"))
						+ ", "
						+ Main.game.getDateNow().format(DateTimeFormatter.ofPattern("yyyy"))
					+"</b>. From a quick calculation "+(Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE)<IntelligenceLevel.ONE_AVERAGE.getMaximumValue()?"(with some help from your phone's calculator)":"")
					+ ", you figure out that it's been <b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>"+Main.game.getDayNumber()+" day"+(Main.game.getDayNumber()>1?"s":"")+"</b> since you appeared in this world."
					+ "</p>");
			
			if(!Main.game.getDialogueFlags().knowsDate) {
				UtilText.nodeContentSB.append("<p>"
						+ "[pc.thought(Wait... "+Main.game.getDateNow().format(DateTimeFormatter.ofPattern("yyyy"))+"?! I need to check in with Lilaya about that...)]"
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append("<p>"
					+ "You notice that on each page of the calendar, there's a few paragraphs detailing the events that occur during that month."
					+ " Although there doesn't seem to be anything interesting for any of the other months, October's entry appears to be quite detailed."
					+ "</p>");
			
			// TODO probably not the best place to put it?
			Main.game.getDialogueFlags().knowsDate = true;
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Step away from the calendar.", ROOM);
				
			} else if(index==1) {
				return new Response("October", "Read the information on October's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER);
				
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
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Stop reading about October.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
				
			} else if(index==1) {
				return new Response("October", "You're already reading October's page!", null);
				
			} else {
				return null;
			}
		}
	};
}
