package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.3.3.10
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
			return new Response("Rest",
					"Rest for four hours. As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
					AUNT_HOME_PLAYERS_ROOM_SLEEP){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setLustNoText(0);
					if(Main.game.getPlayer().hasTrait(Perk.JOB_UNEMPLOYED, true)) {
						Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED_BOOSTED, (8*60*60) + 240);
					} else {
						Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6*60*60) + 240);
					}
				}
			};

		} else if (index == 2) {
			return new Response("Rest until " + (Main.game.isDayTime() ? "Evening" : "Morning"),
					"Rest for " + (sleepTimer >= 60 ? sleepTimer / 60 + " hours " : " ")
						+ (sleepTimer % 60 != 0 ? sleepTimer % 60 + " minutes" : "")
						+ " until " + (Main.game.isDayTime() ? "evening (21:00)." : "morning (07:00).")
						+ " As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
					AUNT_HOME_PLAYERS_ROOM_SLEEP_LONG){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setLustNoText(0);
					if(Main.game.getPlayer().hasTrait(Perk.JOB_UNEMPLOYED, true)) {
						Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED_BOOSTED, (8*60*60) + sleepTimer*60);
					} else {
						Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6*60*60) + sleepTimer*60);
					}
				}
			};

		} else if (index == 3) {
			return new Response("Quick wash",
					"Use your room's en-suite to take a bath or shower. Rose will come and clean your clothes while you wash yourself."
							+ " [style.italicsGood(This will clean <b>a maximum of "+Units.fluid(500)+"</b> of fluids out of all your orifices.)]"
									+ " [style.italicsExcellent(This will clean all of your equipped clothing.)]",
					AUNT_HOME_PLAYERS_ROOM_WASH){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					
					
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().washAllOrifices(false));
					Main.game.getPlayer().calculateStatusEffects(0);
					Main.game.getPlayer().cleanAllDirtySlots();
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().cleanAllClothing(false));
				}
				@Override
				public int getSecondsPassed() {
					return 10*60;
				}
			};

		} else if (index == 4) {
			return new Response("Thorough wash",
					"Use your room's en-suite to take a bath or shower, in which you will thoroughly clean yourself. Rose will come and clean your clothes while you wash yourself."
							+ " [style.italicsExcellent(This will clean <b>all</b> fluids out of all your orifices.)]"
							+ " [style.italicsExcellent(This will clean <b>all</b> clothing in your inventory.)]",
					AUNT_HOME_PLAYERS_ROOM_WASH){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().washAllOrifices(true));
					Main.game.getPlayer().calculateStatusEffects(0);
					Main.game.getPlayer().cleanAllDirtySlots();
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().cleanAllClothing(true));
				}
				@Override
				public int getSecondsPassed() {
					return 30*60;
				}
			};

		} else if(index == 5) {
			if(Main.game.getPlayer().isHasSlaverLicense()) {
				return new Response("Slavery Overview", "Open the slave management screen.",  ROOM) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryOverviewDialogue();
					}
				};
			} else {
				return new Response("Slavery Overview", "You'll need a slaver license before you can access this menu!",  null);
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

		} else if (index == 10) {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				return new Response("Calendar", "Take another look at the enchanted calendar that's pinned up on one wall.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
			} else {
				return new Response("<span style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Calendar</span>", "There's a calendar pinned up on one wall. Take a closer look at it.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
			}
			
		} else {
			return null;
		}
	}

	public static final DialogueNode ROOM = new DialogueNode("Your Room", "", false) {

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
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_SLEEP = new DialogueNode("Your Room", "", false) {

		@Override
		public int getSecondsPassed() {
			return 240*60;
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
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_SLEEP_LONG = new DialogueNode("Your Room", "", false) {

		@Override
		public int getSecondsPassed() {
			return sleepTimer*60;
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
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_WASH = new DialogueNode("Your Room", "", false) {

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

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR = new DialogueNode("Your Room", "", false) {

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
						+ Units.date(Main.game.getDateNow(), Units.DateType.LONG)
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
				return new Response("January", "Read the information on January's page. [style.italicsMinorBad(There are currently no special events during January.)]", null);
			} else if(index==2) {
				return new Response("February", "Read the information on February page. [style.italicsMinorBad(There are currently no special events during February.)]", null);
			} else if(index==3) {
				return new Response("March", "Read the information on March's page. [style.italicsMinorBad(There are currently no special events during March.)]", null);
			} else if(index==4) {
				return new Response("April", "Read the information on April's page. [style.italicsMinorBad(There are currently no special events during April.)]", null);
			} else if(index==5) {
				return new Response("May", "Read the information on May's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_MAY);
			} else if(index==6) {
				return new Response("June", "Read the information on June's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JUNE);
			} else if(index==7) {
				return new Response("July", "Read the information on July's page. [style.italicsMinorBad(There are currently no special events during July.)]", null);
			} else if(index==8) {
				return new Response("August", "Read the information on August's page. [style.italicsMinorBad(There are currently no special events during August.)]", null);
			} else if(index==9) {
				return new Response("September", "Read the information on September's page. [style.italicsMinorBad(There are currently no special events during September.)]", null);
			} else if(index==10) {
				return new Response("October", "Read the information on October's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER);
			} else if(index==11) {
				return new Response("November", "Read the information on November's page. [style.italicsMinorBad(There are currently no special events during November.)]", null);
			} else if(index==12) {
				return new Response("December", "Read the information on December's page.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER);
			} else {
				return null;
			}
		}
	};
	
	/** Calendar's associated animal-morphs are based on the twelve animals of the Chinese zodiac, with the Monkey being replaced with a demon, the Rooster with a harpy, and the Snake with a lamia.
	 *  The ordering of the demon and harpy have also been switched, so that October has demons.<br/>
	 *  There is also a 20% chance of giving a different, random animal-morph for each month.<br/>
	 * Animals are:<br/>
	 * Rat, Cow, Tiger, Rabbit, Dragon, Lamia (Snake), Horse, Sheep/Goat, Harpy (Rooster), Demon (Monkey), Dog, Pig
	 */
	private static String getCalendarImageDescription(Month month) {
		StringBuilder sb = new StringBuilder();

		sb.append("<p>"
				+ "Flicking through the calendar until you're looking at the page for "+month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)+", you see that this month's image is now of ");
		
		if(Util.random.nextInt()<15) {
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				sb.append(UtilText.returnStringAtRandom(
						"a handsome merman, who's busily flexing his muscles while perched on a wave-swept rock.",
						"muscular reindeer-boy, who's grinning as he presents his huge cock to you."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"a beautiful mermaid, who's happily showing off her exposed breasts while perched on a wave-swept rock.",
						"a curvy reindeer-girl, who's bending over a wooden table and presenting her wet pussy to you."));
			}
			
		} else {
			switch(month) {
				case JANUARY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a toned "+Subspecies.RAT_MORPH.getSingularMaleName(null)+", who's grinning mischievously at you while stroking his fat, erect cock.");
					} else {
						sb.append("a horny "+Subspecies.RAT_MORPH.getSingularFemaleName(null)+", who's bent over a table in order to present her dripping pussy to you.");
					}
					break;
				case FEBRUARY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a topless "+Subspecies.COW_MORPH.getSingularMaleName(null)+"."
								+ " His huge muscles are flexing as he carries a felled tree over one shoulder, while between his legs, you can't help but notice that he's got a massive bulge pressing out against the fabric of his shorts.");
					} else {
						sb.append("a black-and-white "+Subspecies.COW_MORPH.getSingularFemaleName(null)+", who's sitting on a small milking stool."
								+ " With a happy smile on her face, she's busily pinching and tugging at at her engorged nipples, causing a stream of milk to flow out into a metal bucket.");
					}
					break;
				case MARCH:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a fierce-looking "+Subspecies.CAT_MORPH_TIGER.getSingularMaleName(null)+"."
								+ " Striking a dominant pose, he's flashing you a toothy grin, clearly excited by the fact that his huge feline cock is fully on display.");
					} else {
						sb.append("a fierce-looking "+Subspecies.CAT_MORPH_TIGER.getSingularFemaleName(null)+"."
								+ " Striking a dominant pose, she's flashing you a toothy grin, clearly excited by the fact that her large breasts and tight pussy are fully on display.");
					}
					break;
				case APRIL:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a handsome "+Subspecies.RABBIT_MORPH.getSingularMaleName(null)+", who's holding his massive cock in one hand while giving you a suggestive wink.");
					} else {
						sb.append("three blushing "+Subspecies.RABBIT_MORPH.getPluralFemaleName(null)+", who are down on all fours, side-by-side, presenting their pussies to you.");
					}
					break;
				case MAY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a powerful dragon, who's sitting on a golden throne perched on the top of a huge pile of treasure."
								+ " His huge, scaly cock is fully on display, and with a grin on his face, he's giving you an expectant look, as though he's waiting for you to climb up and get a taste of it.");
					} else {
						sb.append("a powerful dragoness, who's sitting on a golden throne perched on the top of a huge pile of treasure."
								+ " Her wet, scaly pussy is fully on display, and with a grin on her face, she's giving you an expectant look, as though she's waiting for you to climb up and get a taste of it.");
					}
					break;
				case JUNE:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("an exotic-looking male lamia."
								+ " He's quite clearly turned on and eager to have sex with someone, for his twin-cocks have pushed out from his cloaca; their heads already glistening in the sun from the slimy precum they're starting to exude.");
					} else {
						sb.append("an exotic-looking female lamia."
								+ " She's quite clearly turned on and eager to have sex with someone, for she's reaching down to spread her cloaca and present her dripping-wet pussy to you.");
					}
					break;
				case JULY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("an impressively-endowed "+Subspecies.HORSE_MORPH.getSingularMaleName(null)+", who's flexing his muscles as he presents his fully-erect flared cock to you.");
					} else {
						sb.append("a fit "+Subspecies.HORSE_MORPH.getPluralFemaleName(null)+", who's leaning against a fence, flicking her tail to one side in order to present her animalistic-pussy to you.");
					}
					break;
				case AUGUST:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a sheep-boy and goat-boy, standing side-by-side and presenting their erect cocks as they wink playfully at you.");
					} else {
						sb.append("a wooly sheep-girl and goat-girl, who are lying back and spreading their legs, presenting you with their tight, wet pussies.");
					}
					break;
				case SEPTEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("an unusually-masculine harpy."
								+ " Although the size of his cock is nothing to write home about, he's extremely handsome, and you feel your heart beating faster as you see him winking at you.");
					} else {
						sb.append("a beautiful female harpy."
								+ " Although she's willingly presenting her wet pussy to you, the look on her face is one of condescending superiority,"
									+ " and you get the impression that she'd make some kind of outrageous demand in exchange for allowing you to have sex with her.");
					}
					break;
				case OCTOBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a fit, handsome "+Subspecies.DEMON.getSingularMaleName(null)+", who's suggestively winking at you as he runs his fingers over his huge, erect cock.");
					} else {
						sb.append("a fit, beautiful "+Subspecies.DEMON.getSingularFemaleName(null)+", wearing nothing but a witch's hat, who's suggestively winking at you as she runs her fingers over her wet pussy and huge breasts.");
					}
					break;
				case NOVEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("an energetic-looking "+Subspecies.DOG_MORPH.getSingularMaleName(null)+", who's smiling at you as he strokes his erect, knotted dog-cock.");
					} else {
						sb.append("an excited-looking "+Subspecies.DOG_MORPH.getPluralFemaleName(null)+", who's down on all fours, raising her hips in order to present you with her wet pussy.");
					}
					break;
				case DECEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("a muscular boar-boy, who's grinning at you in anticipation as he strokes his huge cock and pair of massive, cum-filled balls.");
					} else {
						sb.append("a pretty, blushing pig-girl, who's leaning back against a wall and reaching down to spread her puffy pink pussy to you.");
					}
					break;
			}
		}
		
		sb.append(" After gazing at the picture for a few moments, you force yourself to look away and read the information that's written beneath:"
				+ "</p>");
		
		return sb.toString();
	}
	
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_MAY = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.MAY));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>May</span>"
							+ "<br/>"
							+ "<span style='color:"+Colour.BASE_PINK_LIGHT.toWebHexString()+";'>Mother's Week</span>"
					+ "</h4>"
					+ "<p><i>"
						+ "The second week of May is a time in which to celebrate mothers, motherhood, and the nature of the maternal bond between mother and child."
						+ " During this time, fertility-enhancing consumables are generously provided free of charge for all residents of Dominion, and are handed out by volunteers down the main boulevards."
						+ " In this way, Lilith shows her love for mothers, and ensures that many more will be made!"
					+ "</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5) {
				return new Response("May", "You are already reading the calendar's page concerning the month of May.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JUNE = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JUNE));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>June</span>"
							+ "<br/>"
							+ "<span style='color:"+Colour.BASE_BLUE.toWebHexString()+";'>Father's Week</span>"
					+ "</h4>"
					+ "<p><i>"
						+ "The third week of June is a time in which to celebrate fathers, fatherhood, and the nature of the paternal bond between father and child."
						+ " During this time, fertility-enhancing consumables are generously provided free of charge for all residents of Dominion, and are handed out by volunteers down the main boulevards."
						+ " In this way, Lilith shows her love for fathers, and ensures that many more will be made!"
					+ "</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6) {
				return new Response("June", "You are already reading the calendar's page concerning the month of June.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.OCTOBER));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>October</span>"
							+ "<br/>"
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
			if(index==10) {
				return new Response("October", "You are already reading the calendar's page concerning the month of October.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER = new DialogueNode("Your Room", "", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.DECEMBER));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>December</span>"
							+ "<br/>"
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
			if(index==12) {
				return new Response("December", "You are already reading the calendar's page concerning the month of December.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};
	

	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME = new DialogueNode("Your Room", "", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME", NightlifeDistrict.getClubbersPresent());
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Sex (dom)", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Have dominant sex with [npc.name]."),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
						null,
						null), BACK_HOME_AFTER_CLUBBER_SEX, UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEX_AS_DOM", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==2) {
				return new ResponseSex("Sex (sub)", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Have submissive sex with [npc.name]."),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), BACK_HOME_AFTER_CLUBBER_SEX, UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEX_AS_SUB", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==4) {
				return new Response("Say goodbye",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you've changed your mind, sending [npc.herHim] home with the promise of seeing [npc.herHim] at the club another time."
								+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
						AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_CHANGE_MIND", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
					}
				};
				
			} else if(index==5) {
				return new Response("Send home",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you've changed your mind and abruptly send [npc.herHim] home."
								+ "</br>[style.italicsBad(Removes this character from the game.)]"),
						AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_CHANGE_MIND_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BACK_HOME_AFTER_CLUBBER_SEX = new DialogueNode("Your Room", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_CLUBBER_SEX", NightlifeDistrict.getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_CLUBBER_SEX_NO_ORGASM", NightlifeDistrict.getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("See again",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you hope to see [npc.herHim] again.</br>"
								+ "[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("Hope not (gentle)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Make a non-committal response, secretly hoping that you won't see [npc.name] again.</br>[style.italicsBad(Removes this character from the game.)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("Hope not (harsh)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Crudely tell [npc.name] that you were only interested in fucking [npc.herHim].</br>[style.italicsBad(Removes this character from the game.)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode BACK_HOME_AFTER_SEX = new DialogueNode("Your Room", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME = new DialogueNode("Your Room", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM.getResponse(responseTab, index);
		}
	};
}
