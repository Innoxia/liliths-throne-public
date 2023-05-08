package com.lilithsthrone.game.dialogue.places.dominion;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.CultistDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.ReindeerOverseerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.RentalMommyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaHotel;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanDateFinalRepeat;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanFirstDate;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanFirstDoubleDate;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanRepeatDate;
import com.lilithsthrone.game.dialogue.places.submission.BatCaverns;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.2
 * @author Innoxia
 */
public class DominionPlaces {

	public static final int TRAVEL_TIME_STREET = 2*60;
	
	public static boolean isCloseToEnforcerHQ() {
		return Vector2i.getDistance(Main.game.getPlayerCell().getLocation(), Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_ENFORCER_HQ).getLocation())<4f;
	}
	
	private static String getExtraStreetFeatures() {
		StringBuilder mommySB = new StringBuilder();
		StringBuilder occupantSB = new StringBuilder();
		StringBuilder cultistSB = new StringBuilder();
		StringBuilder reindeerSB = new StringBuilder();
		
		Set<NPC> characters = new HashSet<>(Main.game.getNonCompanionCharactersPresent());
		characters.addAll(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()));

		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_NYAN_APARTMENT) {
			mommySB.append("<p>"
							+ "[style.boldPinkLight(Nyan's Apartment:)]<br/>"
							+ (Main.game.getNpc(Nyan.class).getWorldLocation()==WorldType.NYANS_APARTMENT
								?"Nyan lives in this area, and so if you wanted to, you could head over to her apartment building and pay her a visit..."
								:"Nyan lives in this area, although you know that she'll be at work at this hour, so there's not much point in heading over to her apartment building...")
						+ "</p>");
		}
		
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_CALLIE_BAKERY) {
			mommySB.append("<p>[style.boldBrown(The Creamy Bakey:)]</p>");
			mommySB.append(UtilText.parseFromXMLFile("nnxx/callie_bakery", "EXTERIOR"));
		}
		
		for(NPC npc : characters) {
			if(npc instanceof RentalMommy) {
				mommySB.append(
						UtilText.parse(npc,
								"<p>"
									+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Mommy's House:</b><br/>"
									+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM
										?"Mommy's house is located down this street, but due to the ongoing arcane storm, her usual bench is currently unoccupied."
												+ " If you wanted to interact with her, you'd better come back after the storm has passed..."
										:"Mommy's house is located down this street, and as you look over towards it, you see her sitting on her usual bench outside."
											+ " Still wearing her 'Rental Mommy' t-shirt, she's quite clearly still open for business as usual...")
								+ "</p>"));
				break;
			}
			if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) {
				occupantSB.append("<p>");
				occupantSB.append(UtilText.parse(npc,
								"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>[npc.NamePos] Apartment:</b><br/>"
									+ "After moving out from Lilaya's home, [npc.name] has ended up living in an apartment building near to this location."));

				if(npc.isSleepingAtHour(Main.game.getHourOfDay())) {
					occupantSB.append(UtilText.parse(npc,
							" If you wanted to, you could pay the [npc.race] a visit, but as [npc.sheIs] currently [style.colourSleep(sleeping)], [npc.she] will likely be annoyed at being woken up..."));
				} else {
					occupantSB.append(UtilText.parse(npc, " If you wanted to, you could pay the [npc.race] a visit..."));
				}
				occupantSB.append("<br/>");
				occupantSB.append(UtilText.parse(npc, "<i>[npc.Name] sleeps between the hours of [style.time("+npc.getSleepStartHour()+")]-[style.time("+npc.getSleepEndHour()+")]</i>"));
				occupantSB.append("</p>");
				break;
			}
			
			if(npc instanceof Cultist) {
				cultistSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Cultist's Chapel:</b><br/>"
							+ UtilText.parse(npc, "You remember that [npc.namePos] chapel is near here, and if you were so inclined, you could easily find it again...")
						+ "</p>");
				break;
			}
			
			if(npc instanceof ReindeerOverseer) {
				reindeerSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.RACE_REINDEER_MORPH.toWebHexString()+";'>Reindeer Workers:</b><br/>"
							+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM
								?UtilText.parse(npc, "The reindeer-morphs have all taken shelter from the ongoing arcane storm."
										+ " If you wanted to speak with their overseer, you'd need to come back after the storm has passed.")
								:UtilText.parse(npc, "A large group of reindeer-morphs are hard at work shovelling snow."
										+ " Their leader, [npc.a_race], is shouting out orders and travelling to-and-fro between the workers to make sure that the job is being done to [npc.her] satisfaction."
										+ " Although the workers look to be far too busy to stop and talk, you'd probably be able to catch a word with the overseer if you wanted to."))
						+ "</p>");
				break;
			}
		}
		
		mommySB.append(cultistSB.toString()).append(occupantSB.toString()).append(reindeerSB.toString());
		
		AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
		if(collar!=null && collar.getClothingType().getId().equals("innoxia_neck_filly_choker")) {
			mommySB.append("<p>");
				mommySB.append("[style.boldPinkLight(Filly Choker:)]<br/>");
				mommySB.append("By wearing your filly choker, you're signalling to any passing centaur slaves from Dominion Express that you're available to sexually service them.");
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					mommySB.append(" As there's an ongoing arcane storm, however, there's [style.colourMinorBad(zero chance)] that you'll encounter any of them...");
				} else if(!Main.game.isExtendedWorkTime()) {
					mommySB.append(" As they're not out at work at this time, however, there's [style.colourMinorBad(zero chance)] that you'll encounter any of them...");
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					mommySB.append(" As you aren't able to access your mouth, however, there's [style.colourMinorBad(zero chance)] that any of them will approach you...");
				} else {
					mommySB.append(" Although Dominion is a huge city, there are a significant number of centaur slaves who work at Dominion Express, meaning that there's at least a [style.colourMinorGood(small chance)] that you'll run into one...");
				}
			mommySB.append("</p>");
		}
		
		
		return mommySB.toString();
	}
	
	private static List<Response> getExtraStreetResponses() {
		List<Response> mommyResponses = new ArrayList<>();
		List<Response> occupantResponses = new ArrayList<>();
		List<Response> cultistResponses = new ArrayList<>();
		List<Response> reindeerResponses = new ArrayList<>();

		Set<NPC> characters = new HashSet<>(Main.game.getNonCompanionCharactersPresent());
		characters.addAll(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()));
		
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_NYAN_APARTMENT) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)) {
				//TODO
//				if(Main.game.getNpc(Nyan.class).getWorldLocation()==WorldType.NYANS_APARTMENT) {
//					mommyResponses.add(new Response("Visit Nyan", "Head over to Nyan's apartment building and pay her a visit.", Main.game.getDefaultDialogue(false)));
//					
//				} else {
//					mommyResponses.add(new Response("Visit Nyan", "Nyan is out at work at this time of day, so you're unable to head over to her apartment building and pay her a visit...", null));
//				}
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumInterviewPassed)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend))) {
				int dateCost = 4000;
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWeekendDated)) {
					mommyResponses.add(new Response("Double date ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
							"You've already taken Nyan and [nyanmum.name] out for a date this weekend. You'll have to wait until next weekend before taking them out again...",
							null));
					
				} else if((Main.game.getDayOfWeek()==DayOfWeek.FRIDAY || Main.game.getDayOfWeek()==DayOfWeek.SATURDAY)
						&& (Main.game.getHourOfDay()>=18 && Main.game.getHourOfDay()<23)) {
					if(Main.game.getNpc(Nyan.class).getWorldLocation()!=WorldType.NYANS_APARTMENT) {
						mommyResponses.add(new Response("Double date ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"Nyan and [nyanmum.name] are not at home at the moment. You'll have to come back after their work day ends...",
								null));
						
					} else if(Main.game.getPlayer().getMoney()<dateCost) {
						mommyResponses.add(new Response("Double date ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"'The Oaken Glade' is a very expensive place to go on a date. You need at least "+Util.intToString(dateCost)+" flames before asking Nyan and [nyanmum.name] out to a date there.",
								null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						mommyResponses.add(new Response("Double date ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"You're not going to be able to go out on a date to a restaurant if you're not able to eat anything!"
									+ "<br/>[style.italicsMinorBad(You need to be able to access your mouth in order to take Nyan and [nyanmum.name] out on a date...)]",
								null));
						
					} else {
						mommyResponses.add(new Response("Double date ("+UtilText.formatAsMoney(dateCost, "span")+")",
								"Pick Nyan and [nyanmum.name] up from their apartment building and take them out on a date to the restaurant, 'The Oaken Glade'.",
								Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)
									?NyanDateFinalRepeat.DOUBLE_DATE_START
									:NyanFirstDoubleDate.DATE_START));
					}
					
				} else {
					mommyResponses.add(new Response("Double date ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
							"You cannot take Nyan and [nyanmum.name] out for a date at this time..."
								+ "<br/><i>It needs to be either a "
								+ (Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
									?"[style.italicsMinorGood(Friday)]"
									:"[style.italicsMinorBad(Friday)]")
								+" or "
								+ (Main.game.getDayOfWeek()==DayOfWeek.SATURDAY
									?"[style.italicsMinorGood(Saturday)]"
									:"[style.italicsMinorBad(Saturday)]")
								+", and between the hours of "
								+ (Main.game.getHourOfDay()>=18 && Main.game.getHourOfDay()<23
									?"[style.italicsMinorGood([unit.time(18)]-[unit.time(23)])]"
									:"[style.italicsMinorBad([unit.time(18)]-[unit.time(23)])]")
								+" in order to take Nyan and [nyanmum.name] out for a date!",
							null));
				}
				
			} else {
				int dateCost = 2500;
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWeekendDated)) {
					mommyResponses.add(new Response("Date Nyan ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
							"You've already taken Nyan out for a date this weekend. You'll have to wait until next weekend before taking her out again...",
							null));
					
				} else if((Main.game.getDayOfWeek()==DayOfWeek.FRIDAY || Main.game.getDayOfWeek()==DayOfWeek.SATURDAY)
						&& (Main.game.getHourOfDay()>=18 && Main.game.getHourOfDay()<23)) {
					if(Main.game.getNpc(Nyan.class).getWorldLocation()!=WorldType.NYANS_APARTMENT) {
						mommyResponses.add(new Response("Date Nyan ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"Nyan is out at work at the moment. You'll have to come back after her work day ends...",
								null));
						
					} else if(Main.game.getPlayer().getMoney()<dateCost) {
						mommyResponses.add(new Response("Date Nyan ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"'The Oaken Glade' looked to be a very expensive place to go on a date. You need at least "+Util.intToString(dateCost)+" flames before asking Nyan out to a date there.",
								null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						mommyResponses.add(new Response("Date Nyan ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"You're not going to be able to go out on a date to a restaurant if you're not able to eat anything!"
									+ "<br/>[style.italicsMinorBad(You need to be able to access your mouth in order to take Nyan out on a date...)]",
								null));
						
					} else {
						mommyResponses.add(new Response("Date Nyan ("+UtilText.formatAsMoney(dateCost, "span")+")",
								"Pick Nyan up from her apartment building and take her out on a date to the restaurant, 'The Oaken Glade'.",
								Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)
									?NyanDateFinalRepeat.SOLO_DATE_START
									:(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateCompleted) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumInterviewPassed)
										?NyanRepeatDate.DATE_START
										:NyanFirstDate.DATE_START)));
					}
					
				} else {
					mommyResponses.add(new Response("Date Nyan ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
							"You cannot take Nyan out for a date at this time..."
								+ "<br/><i>It needs to be either a "
								+ (Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
									?"[style.italicsMinorGood(Friday)]"
									:"[style.italicsMinorBad(Friday)]")
								+" or "
								+ (Main.game.getDayOfWeek()==DayOfWeek.SATURDAY
									?"[style.italicsMinorGood(Saturday)]"
									:"[style.italicsMinorBad(Saturday)]")
								+", and between the hours of "
								+ (Main.game.getHourOfDay()>=18 && Main.game.getHourOfDay()<23
									?"[style.italicsMinorGood([unit.time(18)]-[unit.time(23)])]"
									:"[style.italicsMinorBad([unit.time(18)]-[unit.time(23)])]")
								+" in order to take Nyan out for a date!",
							null));
				}
			}
		}
		
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_CALLIE_BAKERY) {
			int hourOpen = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("nnxx_callie_upgrade_2"))?7:9;
			int hourClose = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("nnxx_callie_upgrade_2"))?17:15;
			
			if(Main.game.isHourBetween(hourOpen, hourClose) && Main.game.getDayOfWeek()!=DayOfWeek.SUNDAY) {
				mommyResponses.add(new Response("The Creamy Bakey",
						"Head over to the nearby bakery, 'The Creamy Bakey', and take a look inside."
								+ "<br/><i>The bakery is open from [style.italicsMinorGood([unit.time("+hourOpen+")]-[unit.time("+hourClose+")])].</i>",
						Main.game.getDialogueFlags().hasFlag("nnxx_callie_introduced")
							?DialogueManager.getDialogueFromId("nnxx_callie_bakery_entry")
							:DialogueManager.getDialogueFromId("nnxx_callie_bakery_entry_first_time")) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("nnxx_callie_bakery"), PlaceType.getPlaceTypeFromId("nnxx_callie_bakery_counter"));
					}
				});
				
			} else {
				mommyResponses.add(new Response("The Creamy Bakey",
						"The nearby bakery, 'The Creamy Bakey', is closed at this time of day."
								+ "<br/><i>You'll have to come back between"
								+ (Main.game.isHourBetween(hourOpen, hourClose)
										?" [style.italicsMinorGood([unit.time("+hourOpen+")]-[unit.time("+hourClose+")])],"
										:" [style.italicsMinorBad([unit.time("+hourOpen+")]-[unit.time("+hourClose+")])],")
								+ (Main.game.getDayOfWeek()!=DayOfWeek.SUNDAY
										?" [style.italicsMinorGood(Monday to Saturday)]"
										:" [style.italicsMinorBad(Monday to Saturday)]")
								+ ".</i>",
						null));
			}
		}
		
		for(NPC npc : characters) {
			if(npc instanceof RentalMommy) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					mommyResponses.add(new Response("Mommy", "'Mommy' is not sitting on her usual bench, and you suppose that she's waiting out the current storm inside her house.", null));
				} else {
					mommyResponses.add(new Response("Mommy", "You see 'Mommy' sitting on the wooden bench outside her house. Walk up to her and say hello.", RentalMommyDialogue.ENCOUNTER) {
						@Override
						public void effects() {
							Main.game.setActiveNPC(npc);	
						}
					});
				}
			}
			
			if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) {
//				if(!Main.game.getCharactersPresent().contains(npc)) {
//					occupantResponses.add(new Response(
//							UtilText.parse(npc, "[npc.Name]"),
//							UtilText.parse(npc, "[npc.Name] is out at work at the moment, and so you'll have to return at another time if you wanted to pay [npc.herHim] a visit..."),
//							null));
//				}
				occupantResponses.add(new Response(
						UtilText.parse(npc, "[npc.Name]"),
						UtilText.parse(npc,
								Main.game.getPlayer().getCompanions().contains(npc)
									?"Head back over to [npc.namePos] apartment."
									:"Head over to [npc.namePos] apartment building and pay [npc.herHim] a visit."),
						OccupantDialogue.OCCUPANT_APARTMENT) {
					@Override
					public void effects() {
						OccupantDialogue.initDialogue(npc, true, false);
						if(npc.isSleepingAtHour(Main.game.getHourOfDay())) {
							Main.game.appendToTextEndStringBuilder("<p style='text-align:center;'>[style.italicsMinorBad([npc.Name] doesn't appreciate being woken up...)]</p>");
							Main.game.appendToTextEndStringBuilder(npc.incrementAffection(Main.game.getPlayer(), -1));
						}
					}
				});
			}
			
			if(npc instanceof Cultist) {
				cultistResponses.add(new Response("Chapel", UtilText.parse(npc, "Visit [npc.namePos] chapel again."), CultistDialogue.ENCOUNTER_CHAPEL_REPEAT) {
						@Override
						public void effects() {
							Main.game.setActiveNPC(npc);
						}
					});
			}
			
			if(npc instanceof ReindeerOverseer) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					reindeerResponses.add(new Response("Overseer",
							"The reindeer-morph workers are currently sheltering from the ongoing arcane storm. You'll have to come back later if you wanted to speak to the overseer.",
							null));
				} else {
					reindeerResponses.add(new Response("Overseer", UtilText.parse(npc, "Walk up to [npc.name] and say hello."), ReindeerOverseerDialogue.ENCOUNTER_START) {
							@Override
							public void effects() {
								Main.game.setActiveNPC(npc);
								npc.setPlayerKnowsName(true);
							}
						});
				}
			}
		}
		
		mommyResponses.addAll(cultistResponses);
		mommyResponses.addAll(occupantResponses);
		mommyResponses.addAll(reindeerResponses);
		
		return mommyResponses;
	}

	private static String getRandomStreetEvent() {
		int extraText = Util.random.nextInt(100) + 1;
		if (extraText <= 3) {
			return ("<p><i>A particularly large and imposing incubus cuts his way through the crowd, holding the leashes of three greater cat-girl slaves."
					+ " Each one is completely naked, and as they pass, you can clearly see their cunts drooling with excitement.</i></p>");
		} else if (extraText <= 6) {
			return ("<p><i>To one side, you see a pair of dog-boy Enforcers questioning a shady-looking cat-boy."
					+ " As you pass, the cat-boy tries to make a break for it, but is quickly tackled to the floor."
					+ " The Enforcers place a pair of restraints around his wrists before dragging him down a nearby alleyway.</i></p>");
		} else if (extraText <= 9) {
			return ("<p><i>A huge billboard covers the entire face of one of the buildings across the street."
					+ " On it, there's an advertisement for the tournament, 'Risk it all', promising great rewards for anyone strong enough to beat the challenge."
					+ " Underneath, the words 'Applications opening soon!' are displayed in bold red lettering.</i></p>");
		} else if (extraText == 10) {
			return ("<p><i>A greater cat-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.getItemTypeFromId("innoxia_race_cat_felines_fancy").getName(false)+ "'.</i></p>");
		} else if (extraText == 11) {
			return ("<p><i>A greater wolf-boy is handing out leaflets just in front of you, and as you pass, he shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.getItemTypeFromId("innoxia_race_wolf_wolf_whiskey").getName(false)+ "'.</i></p>");
		} else if (extraText == 12) {
			return ("<p><i>A greater dog-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.getItemTypeFromId("innoxia_race_dog_canine_crush").getName(false)+ "'.</i></p>");
		} else if (extraText == 13) {
			return ("<p><i>A greater horse-boy is handing out leaflets just in front of you, and as you pass, he shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.getItemTypeFromId("innoxia_race_horse_equine_cider").getName(false)+ "'.</i></p>");
		} else if (extraText == 14) {
			return ("<p><i>A cheering crowd has gathered to one side of the street, and as you glance across, a momentary gap in the crowd allows you to catch a glimpse of what's happening."
					+ " A greater dog-girl is on all fours, and is being double penetrated by a greater horse-boy's pair of massive horse-cocks."
					+ " The girl's juices are leaking down her legs and her tongue lolls from her mouth as the gigantic members thrust in and out of her stretched holes.</i></p>");
		}
		return "";
	}
	
	private static String getEnforcersPresent() {
		StringBuilder sb = new StringBuilder();

		if(Main.game.getSavedEnforcers(WorldType.DOMINION).isEmpty()) {
			if(isCloseToEnforcerHQ()) {
				sb.append("<p style='text-align:center;'><i>");
					sb.append("Due to the close proximity of Dominion's [style.colourBlueDark(Enforcer HQ)], there is a [style.italicsBad(high chance)] of encountering [style.colourBlueDark(Enforcer patrols)] in this area!");
					if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
						sb.append("<br/>However, due to the ongoing arcane storm, there's no chance of encountering any patrols at the moment...");
					}
				sb.append("</i></p>");
			}
			
		} else {
			sb.append("<p style='text-align:center;'><i>");
			
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					sb.append("Due to the ongoing [style.italicsArcane(arcane storm)], there's [style.italicsGood(no chance)] of encountering any of these [style.colourBlueDark(Enforcer patrols)]:");
				} else if(isCloseToEnforcerHQ()) {
					sb.append("Due to the close proximity of Dominion's [style.colourBlueDark(Enforcer HQ)], there is a [style.italicsBad(high chance)] of encountering one of these [style.colourBlueDark(Enforcer patrols)]:");
				} else {
					sb.append("There is a [style.italicsMinorBad(small chance)] of running into one of these [style.colourBlueDark(Enforcer patrols)]:");
				}
				for(List<String> enforcerIds : Main.game.getSavedEnforcers(WorldType.DOMINION)) {
					sb.append("<br/>");
					List<String> names = new ArrayList<>();
					for(String id : enforcerIds) {
						try {
							GameCharacter enforcer = Main.game.getNPCById(id);
							names.add(UtilText.parse(enforcer, "<span style='color:"+enforcer.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					sb.append(Util.stringsToStringList(names, false));
				}
			
			sb.append("</i></p>");
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode STREET = new DialogueNode("Dominion Streets", "", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET"));
			if (Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
				sb.append(getRandomStreetEvent());
			}

			sb.append(getExtraStreetFeatures());
			
			if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_OCTOBER"));
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_SNOW"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			if(index == 0) {
				return null;
				
			} else if(index-1 < responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};
	


	public static final DialogueNode BACK_ALLEYS_SAFE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BACK_ALLEYS_SAFE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode BACK_ALLEYS = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BACK_ALLEYS", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the alleyways. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
							Main.game.setContent(new Response("", "", dn));
						}
					};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DARK_ALLEYS = new DialogueNode("Dark Alleyways", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DARK_ALLEYS", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription(false));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the alleyways. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
							Main.game.setContent(new Response("", "", dn));
						}
					};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BACK_ALLEYS_CANAL = new DialogueNode("Canal Crossing", ".", false) {
		
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BACK_ALLEYS_CANAL", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore this area. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
							Main.game.setContent(new Response("", "", dn));
						}
					};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BOULEVARD = new DialogueNode("Dominion Boulevard", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 90;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BOULEVARD"));
			
			sb.append(getExtraStreetFeatures());
			
			if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_OCTOBER"));
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_SNOW"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			if(index == 0) {
				return null;
				
			} else if(index-1 < responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};

	
	public static final DialogueNode DOMINION_PLAZA = new DialogueNode("Lilith's Plaza", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DOMINION_PLAZA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					return new Response(
							"News", "Due to the ongoing arcane storm, there's nobody here at the moment...", null);
					
				} else {
					return new Response(
							"News",
							"Decide to stay a while and listen to one of the orators...", DOMINION_PLAZA_NEWS){
								@Override
								public void effects() {
									List<AbstractSubspecies> possibleSubspecies = new ArrayList<>();
									possibleSubspecies.add(Subspecies.CAT_MORPH);
									possibleSubspecies.add(Subspecies.DOG_MORPH);
									possibleSubspecies.add(Subspecies.HORSE_MORPH);
									possibleSubspecies.add(Subspecies.WOLF_MORPH);
									
									String randomFemalePerson = possibleSubspecies.get(Util.random.nextInt(possibleSubspecies.size())).getSingularFemaleName(null);
									String randomMalePerson = possibleSubspecies.get(Util.random.nextInt(possibleSubspecies.size())).getSingularMaleName(null);
									
									Main.game.getTextEndStringBuilder().append("<p>"
											+UtilText.returnStringAtRandom(
													"A rough-looking "+randomMalePerson+" unrolls a large scroll, before clearing his throat and calling out,"
														+ " [maleNPC.speech(By decree of Lilith, and in the interests of Dominion's security,"
															+ " any human seen walking the streets outside of daylight hours may legally be subjected to a full body search from any Enforcer.)]",
													Util.capitaliseSentence(UtilText.generateSingularDeterminer(randomFemalePerson))+" "+randomFemalePerson+" holds up an official-looking piece of paper, complete with a red wax seal, and declares,"
														+ " [femaleNPC.speech(A reward of two-hundred-thousand flames has been issued for any information leading to the arrest of the person or persons responsible"
															+ " for distributing illegal newspapers in the districts beneath the Harpy Nests!)]",
													"A rather wild-looking succubus, dressed in a very Halloween-esque witch's costume, points to different members of the crowd as she screams,"
														+ " [femaleNPC.speech(I count no less than three demons in the crowd who are without a cultist's uniform!"
															+ " What would Lilith say if she could see this now?!)]",
													Util.capitaliseSentence(UtilText.generateSingularDeterminer(randomMalePerson))+" "+randomMalePerson+" relays several boring, mundane pieces of news to the crowd."
															+ " There's nothing that is of any interest to you, and you eventually turn away, having felt as though you just wasted your time.",
													Util.capitaliseSentence(UtilText.generateSingularDeterminer(randomFemalePerson))+" "+randomFemalePerson+" relays several boring, mundane pieces of news to the crowd."
															+ " There's nothing that is of any interest to you, and you eventually turn away, having felt as though you just wasted your time.",
													Util.capitaliseSentence(UtilText.generateSingularDeterminer(randomMalePerson))+" "+randomMalePerson+" is currently reading out a list of advertisements for shops in the local area."
															+ " There's really nothing of interest to be heard, and you soon find yourself turning away and moving on.",
													Util.capitaliseSentence(UtilText.generateSingularDeterminer(randomFemalePerson))+" "+randomFemalePerson+" is currently reading out a list of advertisements for shops in the local area."
															+ " There's really nothing of interest to be heard, and you soon find yourself turning away and moving on.")
											+"</p>");
								}
							};
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DOMINION_PLAZA_NEWS = new DialogueNode("Lilith's Plaza", ".", false, true) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
					+ "You decide to stay and listen to one of the many orators who are addressing the crowds..."
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return DOMINION_PLAZA.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_HOTEL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(STREET.getContent());
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_SHADED"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "HELENAS_HOTEL"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			if(index == 0) {
				return null;
				
			} else if(index==1) {
				return new Response("Helena's Nest", "Use the elevator in the hotel to travel directly up to Helena's nest.", HelenaHotel.HOTEL_TRAVEL_TO_NEST) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
					}
				};
					
			} else if(index-2 < responses.size()) {
				return responses.get(index-2);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode STREET_SHADED = new DialogueNode("Dominion Streets (Shaded)", ".", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(STREET.getContent());
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_SHADED"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			 if(index!=0 && index-1<responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CANAL = new DialogueNode("Dominion Canals", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CANAL", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore this area. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
							Main.game.setContent(new Response("", "", dn));
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode CANAL_END = new DialogueNode("Dominion Canals", ".", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CANAL", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CANAL_END", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore this area. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
							Main.game.setContent(new Response("", "", dn));
						}
					};
			} else {
				return null;
			}
		}
	};

	// Entrances and exits:

	public static final DialogueNode CITY_EXIT_SEWERS = new DialogueNode("Submission Entrance", "Enter the undercity of Submission.", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CITY_EXIT_SEWERS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Submission", "Enter the undercity of Submission.", CITY_EXIT_SEWERS_ENTERING_SUBMISSION){
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.visitedSubmission, false);
						}
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);
						
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_EXIT_SEWERS_ENTERING_SUBMISSION = new DialogueNode("Enforcer Checkpoint", "Enter the undercity of Submission.", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.visitedSubmission);
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION"));
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION_FIRST_TIME"));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION_REPEAT"));
			}
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.visitedSubmission)) {
				if (index == 1) {
					return new Response("Confirm", "Confirm to the cat-girl that this is indeed your first time visiting Submission.", CITY_EXIT_SEWERS_ENTERING_SUBMISSION_FIRST_TIME) {
						@Override
						public void effects() {
							Main.game.getNpc(Claire.class).setPlayerKnowsName(true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_SLIME_QUEEN));
						}
					};
					
				} else {
					return null;
				}
			} else {
				return SubmissionGenericPlaces.SEWER_ENTRANCE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode CITY_EXIT_SEWERS_ENTERING_SUBMISSION_FIRST_TIME = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION_FIRST_TIME_CONFIRMATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way through the Enforcer Post.", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.visitedSubmission, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_EXIT_BAT_CAVERNS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CITY_EXIT_BAT_CAVERNS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToFly()) {
					if(!Main.game.getPlayer().isPartyAbleToFly()) {
						return new Response("Bat Caverns", "As your party members are unable to fly, you cannot use the shaft to travel down to the Bat Caverns...", null);
						
					} else {
						return new Response("Bat Caverns", "Fly down the shaft to return to the Bat Caverns.", CITY_EXIT_BAT_CAVERNS_FLY_DOWN) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SHAFT, false);
							}
						};
					}
					
				} else {
					return new Response("Bat Caverns", "As you are unable to fly, you cannot use the shaft to travel down to the Bat Caverns...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_EXIT_BAT_CAVERNS_FLY_DOWN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CITY_EXIT_BAT_CAVERNS_FLY_DOWN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return BatCaverns.SHAFT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CITY_EXIT = new DialogueNode("Dominion Exit", "", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isDiscoveredWorldMap()) {
				return "<p>"
						+ "A pair of elite demon Enforcers are keeping a close watch on everyone who enters or leaves the city."
						+ " Now that you have a map, as well as business out there in the world beyond Dominion, there's nothing stopping you from leaving right now."
					+ "</p>";
				
			} else {
				return "<p>"
							+ "A pair of elite demon Enforcers are keeping a close watch on everyone who enters or leaves the city."
							+ " Although there's nothing stopping you from heading out into the world beyond, you have no reason to leave Dominion at the moment, and, without a map, you imagine that it would be quite easy to get lost."
						+ "</p>"
						+ "<p>"
							+ "Your quest to find out how to return to your old world will no doubt eventually lead you to places other than Dominion, but for now, your business is within the city itself."
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isDiscoveredWorldMap()) {
					return new ResponseEffectsOnly("World travel", "Exit Dominion and head out into the wide world...") {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.WORLD_MAP, Main.game.getPlayer().getGlobalLocation(), false);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else {
					return new Response("World travel", "You don't know what the rest of the world looks like, and, for now, your business is within the city.", null);
				}

			} else {
				return null;
			}
		}
	};
}
