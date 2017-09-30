package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public class CityPlaces {

	public static final DialogueNodeOld STREET = new DialogueNodeOld("Dominion streets", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if (Main.game.isDayTime()) { // Day time:
				if (Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
					UtilText.nodeContentSB.append("<p>"
							+ "Although the streets of Dominion look similar to those of Victorian-era London, there's a strange feel to them that hints at the other-worldly nature of this place."
							+ " The roads that should be home to a city's heavy traffic are all paved over with clean white flagstones.");

					if (Main.game.getCurrentWeather() == Weather.CLEAR)
						UtilText.nodeContentSB.append(" Rows of trees and marble benches line either side of the pedestrianised streets, offering shade and a place to rest from the hot summer sun."
								+ " On either side of you, the building's clean facades gleam in the sunshine, and high above in the sky, you see harpies flying about on business of their own."
								+ "</p>");

					else if (Main.game.getCurrentWeather() == Weather.CLOUD)
						UtilText.nodeContentSB.append(" Rows of trees and marble benches line either side of the pedestrianised streets, offering a place to rest."
								+ " On either side of you, the building's clean facades gleam in the sunshine, and high above in the sky, you see harpies flying about on business of their own.</p>");

					else if (Main.game.getCurrentWeather() == Weather.RAIN)
						UtilText.nodeContentSB.append(" Rows of trees and marble benches line either side of the pedestrianised streets, offering a place to rest and cover from the rain."
								+ " On either side of you, large canvas awnings have been extended from the buildings, shielding passersby from the sudden downpour."
								+ "</p>");

					else if (Main.game.getCurrentWeather() == Weather.MAGIC_STORM_GATHERING)
						UtilText.nodeContentSB.append(" Marble benches line either side of the pedestrianised streets, granted shade from the summer sun by rows of trees."
								+ " The tree's branches sway as the wind starts to pick up, and the rustling of leaves can be head over the steady hum of the busy crowds."
								+ " On either side of you, the glass frontages of the recently renovated buildings reflect the darkening sky."
								+ "</p>");

					UtilText.nodeContentSB.append("<p>"
							+ "The streets are very busy at this time of day, and are filled with people hurrying to and fro."
							+ " Despite their alarming appearances, the citizens of Dominion appear to be completely normal in every other way."
							+ " You see those people who are always in a rush to be somewhere else, the crowds of shoppers lazily ambling by, the groups of friends laughing and chatting on benches, and"
							+ " all the other sorts that you'd find in any old city."
							+ " As the streets are quiet at this time, you have plenty of room to weave your way through the sparse crowds."
							+ "</p>"
							+ "<p>"
							+ "Most of the people around you are partial or lesser morphs, although there are a quite a few greater morphs to be seen walking the streets as well."
							+ " Dog, cat and horse-morphs are by far the most common races that you see, but it wouldn't take long to find examples of every race."
							+ " The rarest races that you see are humans and demons."
							+ " The latter are very easy to spot, as wherever they walk, people hurriedly move to make way."
							+ "</p>");

					int extraText = Util.random.nextInt(100)
							+ 1;
					if (extraText <= 3)
						UtilText.nodeContentSB.append("<p><i>A particularly large and imposing demon cuts her way through the crowd, holding the leashes of three greater cat-girl slaves."
								+ " They are completely naked, and as they pass, you can clearly see each of their cunts drooling with excitement.</i></p>");
					else if (extraText <= 6)
						UtilText.nodeContentSB.append("<p><i>To one side, you see a pair of greater dog-boy enforcers questioning a lesser cat-boy. As you pass, the cat-boy tries to"
								+ " make a break for it, but is quickly tackled to the floor. The enforcers place a pair of restraints around his wrists before"
								+ " dragging him down a nearby alleyway.</i></p>");
					else if (extraText <= 9)
						UtilText.nodeContentSB.append("<p><i>A huge billboard covers the entire face of one of the buildings across the street."
								+ " On it, there's an advertisement for the tournament, 'Risk it all', promising great rewards for anyone strong enough to beat the challenge."
								+ " Underneath, the words 'Applications opening soon!' are displayed in bold red lettering.</i></p>");
//					else if (extraText == 10)
//						UtilText.nodeContentSB.append("<p><i>A greater cat-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands. You look down"
//								+ " to see that it's just an advertisement for the energy drink '"
//								+ ItemType.THROBBING_GLOW.getName()
//								+ "', promising to enhance the drinker's masculine parts.</i></p>");
//					else if (extraText == 11)
//						UtilText.nodeContentSB.append("<p><i>A greater cat-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands. You look down"
//								+ " to see that it's just an advertisement for the energy drink '"
//								+ ItemType.FLOWERS_WARMTH.getName()
//								+ "', promising to enhance the drinker's feminine parts.</i></p>");
//					else if (extraText == 12)
//						UtilText.nodeContentSB.append("<p><i>A greater cat-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands. You look down"
//								+ " to see that it's just an advertisement for the drink '"
//								+ ItemType.SCARLET_WHISPER.getName()
//								+ "', promising to enhance the drinker's femininity.</i></p>");
//					else if (extraText == 13)
//						UtilText.nodeContentSB.append("<p><i>A greater cat-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands. You look down"
//								+ " to see that it's just an advertisement for the drink '"
//								+ ItemType.FLAMING_THUNDER.getName()
//								+ "', promising to enhance the drinker's masculinity.</i></p>");
					else if (extraText == 14)
						UtilText.nodeContentSB.append("<p><i>A cheering crowd has gathered to one side of the street, and as you glance across, a momentary gap in the crowd allows you to catch a glimpse of what's happening."
								+ " A greater dog-girl is on all fours, and is being double penetrated by a greater horse-boy's pair of massive horse-cocks."
								+ " The girl's juices are leaking down her legs and her tongue lolls from her mouth as the gigantic members thrust in and out of her stretched holes.</i></p>");

				} else {
					UtilText.nodeContentSB.append("<p>"
							+ "Although the streets of Dominion look similar to those of Victorian-era London, there's a few major differences that reveal the other-worldly nature of this place."
							+ " The roads that should be home to a city's heavy traffic are all paved over with clean white flagstones."
							+ " Marble benches line either side of the pedestrianised streets, interspersed by rows of trees and arcane-powered street lamps."
							+ " The tree's branches sway wildly in the storm's wind as it howls down the empty streets."
							+ " The glass frontages of the surrounding buildings reflect each and every lightning strike, filling the streets with bright purple and pink flashes."
							+ "</p>"
							+ " <p>"
							+ "Due to the ongoing storm, the entire city seems to be almost totally deserted."
							+ " Doors are locked, windows are shuttered, and, for the most part, not a soul can be seen."
							+ " The only people able to withstand the storm's thunderous power are demons, and every now and then you see one strutting down the street."
							+ " They sometimes cast a curious glance your way, but most are content to simply ignore you."
							+ "</p>"
							+ " <p>"
							+ "The size and emptiness of the city streets fills you with a sense of foreboding, and you frantically look around for signs of danger as you hurry on your way."
							+ " Remembering what happened the first night you arrived in this world, you know full well that any non-demons caught out in the storm will be filled with an uncontrollable lust."
							+ " If they catch you, they'll be sure to force you into a fight."
							+ "</p>");
					// +(Main.game.getCurrentWeather()==Weather.MAGIC_STORM?"<p>"+WorldType.getDominionStormWarning()+"</p>":""));
				}
			} else { // Night time:
				if (Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
					UtilText.nodeContentSB.append("<p>"
							+ "Although the streets of Dominion look similar to those of Victorian-era London, there's a strange feel to them that hints at the other-worldly nature of this place."
							+ " The roads that should be home to a city's heavy traffic are all paved over with clean white flagstones."
							+ " Marble benches line both sides of the pedestrianised streets, and are interspersed by rows of trees, softly illuminated by the amber glow of arcane-powered street lamps.");

					if (Main.game.getCurrentWeather() == Weather.CLEAR
							|| Main.game.getCurrentWeather() == Weather.CLOUD)
						UtilText.nodeContentSB.append(" On either side of you, lights shine out from the windows of the tall buildings."
								+ "</p>");

					else if (Main.game.getCurrentWeather() == Weather.RAIN)
						UtilText.nodeContentSB.append(" On either side of you, large canvas awnings have been extended from the buildings, shielding passersby from the sudden downpour."
								+ "</p>");

					else if (Main.game.getCurrentWeather() == Weather.MAGIC_STORM_GATHERING)
						UtilText.nodeContentSB.append(" The tree's branches sway as the wind starts to pick up, and the rustling of leaves fills the still night air."
								+ "</p>");

					UtilText.nodeContentSB.append("<p>"
							+ "Although the streets are noticeably quieter than during the day, there are still plenty of people hurrying to and fro."
							+ " Despite their alarming appearances, the citizens of Dominion appear to be completely normal in every other way."
							+ " You see those people who are always in a rush to be somewhere else, the crowds of shoppers lazily ambling by, the groups of friends laughing and chatting on benches, and"
							+ " all the other sorts that you'd find in any old city."
							+ " As the streets are quiet at this time, you have plenty of room to weave your way through the sparse crowds."
							+ "</p>"
							+ "<p>"
							+ "Most of the people around you are partial or lesser morphs, although there are a quite a few greater morphs to be seen walking the streets as well."
							+ " Dog, cat and horse-morphs are by far the most common races that you see, but it wouldn't take long to find examples of every race."
							+ " The rarest races that you see are humans and demons."
							+ " The latter are very easy to spot, as wherever they walk, people hurriedly move to make way."
							+ "</p>");

					int extraText = Util.random.nextInt(100)
							+ 1;
					if (extraText <= 3)
						UtilText.nodeContentSB.append("<p><i>A particularly large and imposing demon cuts her way through the crowd, holding the leashes of three greater cat-girl slaves."
								+ " They are completely naked, and as they pass, you can clearly see each of their cunts drooling with excitement.</i></p>");
					else if (extraText <= 6)
						UtilText.nodeContentSB.append("<p><i>To one side, you see a pair of greater dog-boy enforcers questioning a lesser cat-boy. As you pass, the cat-boy tries to"
								+ " make a break for it, but is quickly tackled to the floor. The enforcers place a pair of restraints around his wrists before"
								+ " dragging him down a nearby alleyway.</i></p>");
					else if (extraText <= 9)
						UtilText.nodeContentSB.append("<p><i>A huge billboard covers the entire face of one of the buildings across the street."
								+ " On it, there's an advertisement for the tournament, 'Risk it all', promising great rewards for anyone strong enough to beat the challenge."
								+ " Underneath, the words 'Applications opening soon!' are displayed in bold red lettering.</i></p>");
//					else if (extraText == 10)
//						UtilText.nodeContentSB.append("<p><i>A greater cat-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands. You look down"
//								+ " to see that it's just an advertisement for the energy drink '"
//								+ ItemType.THROBBING_GLOW.getName()
//								+ "', promising to enhance the drinker's masculine parts.</i></p>");
//					else if (extraText == 11)
//						UtilText.nodeContentSB.append("<p><i>A greater cat-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands. You look down"
//								+ " to see that it's just an advertisement for the energy drink '"
//								+ ItemType.FLOWERS_WARMTH.getName()
//								+ "', promising to enhance the drinker's feminine parts.</i></p>");
//					else if (extraText == 12)
//						UtilText.nodeContentSB.append("<p><i>A greater cat-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands. You look down"
//								+ " to see that it's just an advertisement for the drink '"
//								+ ItemType.SCARLET_WHISPER.getName()
//								+ "', promising to enhance the drinker's femininity.</i></p>");
//					else if (extraText == 13)
//						UtilText.nodeContentSB.append("<p><i>A greater cat-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands. You look down"
//								+ " to see that it's just an advertisement for the drink '"
//								+ ItemType.FLAMING_THUNDER.getName()
//								+ "', promising to enhance the drinker's masculinity.</i></p>");
					else if (extraText == 14)
						UtilText.nodeContentSB.append("<p><i>A cheering crowd has gathered to one side of the street, and as you glance across, a momentary parting allows you to catch a glimpse of what's happening."
								+ " A greater dog-girl is on all fours, and is being roughly fucked by a lesser horse-boy's massive equine-cock."
								+ " The girl's juices are leaking down her legs and her tongue lolls from her mouth as the gigantic member thrusts in and out of her stretched pussy.</i></p>");

				} else {
					UtilText.nodeContentSB.append("<p>"
							+ "Although the streets of Dominion look similar to those of Victorian-era London, there's a few major differences that reveal the other-worldly nature of this place."
							+ "The roads that should be home to a city's heavy traffic are all paved over with clean white flagstones."
							+ " Marble benches line either side of the pedestrianised streets, interspersed by rows of trees, all illuminated by the soft amber glow of arcane-powered street lamps."
							+ " The tree's branches sway wildly in the storm's wind as it howls down the empty streets."
							+ " The glass frontages of the surrounding buildings reflect each and every lightning strike, filling the streets with bright purple and pink flashes."
							+ "</p>"
							+ " <p>"
							+ "Due to the ongoing storm, the entire city seems to be almost totally deserted."
							+ " Doors are locked, windows are shuttered, and, for the most part, not a soul can be seen."
							+ " The only people able to withstand the storm's thunderous power are demons, and every now and then you see one strutting down the street."
							+ " They sometimes cast a curious glance your way, but most are content to simply ignore you."
							+ "</p>"
							+ " <p>"
							+ "The size and emptiness of the city streets fills you with a sense of foreboding, and you frantically look around for signs of danger as you hurry on your way."
							+ " Remembering what happened the first night you arrived in this world, you know full well that any non-demons caught out in the storm will be filled with an uncontrollable lust."
							+ " If they catch you, they'll be sure to force you into a fight."
							+ "</p>");
					// +(Main.game.getCurrentWeather()==Weather.MAGIC_STORM?"<p>"+WorldType.getDominionStormWarning()+"</p>":""));
				}
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			return null;
		}
	};

	
	public static final DialogueNodeOld BACK_ALLEYS = new DialogueNodeOld("Back Alleys", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
					+ "You find yourself walking through labyrinthine alleyways, with not a soul in sight."
					+ " Back-doors and steaming vents line the dark brick walls, and you often have to navigate around overflowing bins and stacks of empty crates in order to make progress."
					+ " These less-travelled parts of Dominion have a dangerous feel to them, and you can't shake the feeling that you're being followed."
					+ "</p>");
			
			for(GameCharacter npc : Main.game.getCharactersPresent()) {
					UtilText.nodeContentSB.append(
							UtilText.genderParsing(npc,
									"<p style='text-align:center;'>"
									+ "<b style='color:"+Femininity.valueOf(npc.getFemininityValue()).getColour().toWebHexString()+";'>[npc.A_femininity]</b>"
									+ " <b style='color:"+npc.getRaceStage().getColour().toWebHexString()+";'>[npc.raceStage]</b>"
									+ " <b style='color:"+npc.getRace().getColour().toWebHexString()+";'>[npc.race]</b> <b>is prowling this area!</b></p>"
									
									+ "<p style='text-align:center;'>"));
							
					// Combat:
					UtilText.nodeContentSB.append(
							UtilText.genderParsing(npc,"You have <b style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+";'>fought</b> <herPro> <b>"));
							
							if(npc.getFoughtPlayerCount()==1) {
								UtilText.nodeContentSB.append("once.");
							} else if(npc.getFoughtPlayerCount()==2) {
								UtilText.nodeContentSB.append("twice.");
							} else {
								UtilText.nodeContentSB.append(Util.intToString(npc.getLostCombatCount())+" times.");
							}
							
					UtilText.nodeContentSB.append(
							UtilText.genderParsing(npc,
									"</b>"
									+ "</br>"
									+ "You have <b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>won</b> <b>"));
							
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
						
					
					// Sex:
					UtilText.nodeContentSB.append("<p style='text-align:center;'>");
							
					UtilText.nodeContentSB.append(
							UtilText.genderParsing(npc,
									"You have had <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>submissive sex</b> with <herPro> <b>"));
					
							if(npc.getSexAsDomCount()==1) {
								UtilText.nodeContentSB.append("once.");
							} else if(npc.getSexAsDomCount()==2) {
								UtilText.nodeContentSB.append("twice.");
							} else {
								UtilText.nodeContentSB.append(Util.intToString(npc.getSexAsDomCount())+" times.");
							}
							
					UtilText.nodeContentSB.append(
							UtilText.genderParsing(npc,
									"</b>"
									+ "</br>"
									+ "You have had <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>dominant sex</b> with <herPro> <b>"));
					
							if(npc.getSexAsSubCount()==1) {
								UtilText.nodeContentSB.append("once.");
							} else if(npc.getSexAsSubCount()==2) {
								UtilText.nodeContentSB.append("twice.");
							} else {
								UtilText.nodeContentSB.append(Util.intToString(npc.getSexAsSubCount())+" times.");
							}
							UtilText.nodeContentSB.append("</b></p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			if(index == 6) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the alleyways. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
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
	
	public static final DialogueNodeOld DARK_ALLEYS = new DialogueNodeOld("Dark Alleyways", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
						+ "You find yourself walking through labyrinthine alleyways, with not a soul in sight."
						+ " Back-doors and steaming vents line the dark brick walls, and you often have to navigate around overflowing bins and stacks of empty crates in order to make progress."
						+ " You're far away from the safety of the main street, and you can't shake the feeling that there's something <b>extremely dangerous</b> lurking around the next corner..."
					+ "</p>");

			for(GameCharacter npc : Main.game.getCharactersPresent()) {
					UtilText.nodeContentSB.append(
							UtilText.genderParsing(npc,
									"<p style='text-align:center;'>"
									+ "<b style='color:"+Femininity.valueOf(npc.getFemininityValue()).getColour().toWebHexString()+";'>[npc.A_femininity]</b>"
									+ " <b style='color:"+npc.getRaceStage().getColour().toWebHexString()+";'>[npc.raceStage]</b>"
									+ " <b style='color:"+npc.getRace().getColour().toWebHexString()+";'>[npc.race]</b> <b>is prowling this area!</b></p>"
									
									+ "<p style='text-align:center;'>"));
							
					// Combat:
					UtilText.nodeContentSB.append(
							UtilText.genderParsing(npc,"You have <b style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+";'>fought</b> <herPro> <b>"));
							
							if(npc.getFoughtPlayerCount()==1) {
								UtilText.nodeContentSB.append("once.");
							} else if(npc.getFoughtPlayerCount()==2) {
								UtilText.nodeContentSB.append("twice.");
							} else {
								UtilText.nodeContentSB.append(Util.intToString(npc.getLostCombatCount())+" times.");
							}
							
					UtilText.nodeContentSB.append(
							UtilText.genderParsing(npc,
									"</b>"
									+ "</br>"
									+ "You have <b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>won</b> <b>"));
							
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
						
					
					// Sex:
					UtilText.nodeContentSB.append("<p style='text-align:center;'>");
							
					UtilText.nodeContentSB.append(
							UtilText.genderParsing(npc,
									"You have had <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>submissive sex</b> with <herPro> <b>"));
					
							if(npc.getSexAsDomCount()==1) {
								UtilText.nodeContentSB.append("once.");
							} else if(npc.getSexAsDomCount()==2) {
								UtilText.nodeContentSB.append("twice.");
							} else {
								UtilText.nodeContentSB.append(Util.intToString(npc.getSexAsDomCount())+" times.");
							}
							
					UtilText.nodeContentSB.append(
							UtilText.genderParsing(npc,
									"</b>"
									+ "</br>"
									+ "You have had <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>dominant sex</b> with <herPro> <b>"));
					
							if(npc.getSexAsSubCount()==1) {
								UtilText.nodeContentSB.append("once.");
							} else if(npc.getSexAsSubCount()==2) {
								UtilText.nodeContentSB.append("twice.");
							} else {
								UtilText.nodeContentSB.append(Util.intToString(npc.getSexAsSubCount())+" times.");
							}
							UtilText.nodeContentSB.append("</b></p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			if(index == 6) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the alleyways. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
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

	// Entrances and exits:

	public static final DialogueNodeOld CITY_EXIT_SEWERS = new DialogueNodeOld("Submission Entrance", "Enter the undercity of Submission.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "In the middle of the street there is a large stairway leading down to the undercity of Submission."
					+ " A pair of enforcers are blocking anyone from going down."
					+ "</p>"
					+ "<p>"
					+ "<b>Scheduled for release in an update to version 0.2.0.</b>"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Submission", "Enter the undercity of Submission. (This will be added after the first chapter of the story is complete!)", null){
//					@Override
//					public void specialEffects() {
//						Main.mainController.moveGameWorld(true);
//					}
				};

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld CITY_EXIT_JUNGLE = new DialogueNodeOld("Jungle Entrance", "Travel to the jungle.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "A sign next to the road informs you that this path leads to the demonic jungles."
					+ " A pair of enforcers are preventing anyone from travelling to the jungle right now."
					+ "</p>"
					+ "<p>"
					+ "<b>Scheduled for release in version 0.4.0.</b>"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Jungle", "Travel to the demonic jungle. (This will be added later!)", null){
//					@Override
//					public void specialEffects() {
//						Main.mainController.moveGameWorld(true);
//					}
				};

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld CITY_EXIT_FIELDS = new DialogueNodeOld("Fields Entrance", "Travel to the fields.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "A sign next to the road informs you that this path leads to the outskirts of the city."
					+ " A pair of enforcers are preventing anyone from travelling to the fields right now."
					+ "</p>"
					+ "<p>"
					+ "<b>Scheduled for release in version 0.3.0.</b>"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Fields", "Travel to the fields. (This will be added later!)", null);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld CITY_EXIT_SEA = new DialogueNodeOld("Endless Sea Entrance", "Travel to the Endless Sea.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "A sign next to the road informs you that this path leads to the endless sea."
					+ " A pair of enforcers are preventing anyone from travelling to the endless sea right now."
					+ "</p>"
					+ "<p>"
					+ "<b>Scheduled for release in version 0.6.0.</b>"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Endless Sea", "Travel to the Endless Sea. (This will be added later!)", null);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld CITY_EXIT_DESERT = new DialogueNodeOld("Desert Entrance", "Travel to the desert.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "A sign next to the road informs you that this path leads to the desert."
					+ " A pair of enforcers are preventing anyone from travelling to the desert right now."
					+ "</p>"
					+ "<p>"
					+ "<b>Scheduled for release in version 0.5.0.</b>"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Desert", "Travel to the desert. (This will be added later!)", null);

			} else {
				return null;
			}
		}
	};
}
