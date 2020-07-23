package com.lilithsthrone.game.dialogue.places.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.CultistDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.ReindeerOverseerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.RentalMommyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaHotel;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
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
 * @version 0.3.7
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
				occupantSB.append(
						UtilText.parse(npc,
								"<p>"
									+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>[npc.NamePos] Apartment:</b><br/>"
									+ "[npc.Name], the [npc.race] that you rescued from a life of crime, lives in an apartment building nearby."
									+ " If you wanted to, you could pay [npc.herHim] a visit..."
								+ "</p>"));
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
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.INT_INGREDIENT_FELINE_FANCY.getName(false)+ "'.</i></p>");
		} else if (extraText == 11) {
			return ("<p><i>A greater wolf-boy is handing out leaflets just in front of you, and as you pass, he shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.STR_INGREDIENT_WOLF_WHISKEY.getName(false)+ "'.</i></p>");
		} else if (extraText == 12) {
			return ("<p><i>A greater dog-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.FIT_INGREDIENT_CANINE_CRUSH.getName(false)+ "'.</i></p>");
		} else if (extraText == 13) {
			return ("<p><i>A greater horse-boy is handing out leaflets just in front of you, and as you pass, he shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.STR_INGREDIENT_EQUINE_CIDER.getName(false)+ "'.</i></p>");
		} else if (extraText == 14) {
			return ("<p><i>A cheering crowd has gathered to one side of the street, and as you glance across, a momentary gap in the crowd allows you to catch a glimpse of what's happening."
					+ " A greater dog-girl is on all fours, and is being double penetrated by a greater horse-boy's pair of massive horse-cocks."
					+ " The girl's juices are leaking down her legs and her tongue lolls from her mouth as the gigantic members thrust in and out of her stretched holes.</i></p>");
		}
		return "";
	}
	
	private static String getEnforcersPresent() {
		StringBuilder sb = new StringBuilder();

		if(isCloseToEnforcerHQ()) {
			sb.append("<p style='text-align:center;'><i>");
				sb.append("Due to the close proximity of Dominion's [style.colourBlueDark(Enforcer HQ)], there is a [style.italicsBad(high chance)] of encountering [style.colourBlueDark(Enforcer patrols)] in this area!");
			sb.append("</i></p>");
		}
		
		if(!Main.game.getSavedEnforcers(WorldType.DOMINION).isEmpty()) {
			sb.append("<p style='text-align:center;'><i>");
			sb.append("There is a small chance of running into one of these [style.colourBlueDark(Enforcer patrols)]:");
			
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
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true, true);
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
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true, true);
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
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true, true);
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

	
	public static final DialogueNode DOMINION_PLAZA = new DialogueNode("Lilith's Plaza", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "You find yourself standing in the very centre of Dominion, where an expansive public square is situated."
						+ " Large residential and commercial buildings flank the plaza on each of its four sides; their white marble facades decorated with countless dark-purple flags bearing the black pentagram of Lilith."
					+ "</p>"
					+ "<p>"
						+ "Numerous grandiose statues and extravagantly-detailed water fountains, all carved from polished white marble, reside within this large area."
						+ " Each one of these sculptures appears to represent a demon or Lilin, and although they're each a marvellous work of art, the one in the very middle of the square is quite simply breathtaking."
						+ " On top of a plinth of at least [unit.lSizes(3000)] in height, stands a gigantic marble statue of Lilith herself;"
							+ " with wings fully unfurled, and with her hands resting on her wide hips, she smirks down with a visage of manic delight at the crowds below."
						+ " Completely naked, every [unit.size] of the effigy's subject is on display for all to see, and you find yourself looking straight up at Lilith's tight pussy as you marvel at the workmanship that went into this astounding piece of art."
					+ "</p>");
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Arcane Storm:</b><br/>"
							+ "The arcane storm that's raging overhead has brought out a heavy presence of demon Enforcers in this area."
							+ " Unaffected by the arousing power of the storm's thunder, these elite Enforcers keep a close watch on you as you pass through the all-but-deserted plaza."
							+ " There's no way anyone would be able to assault you while under their watchful gaze, allowing you continue on your way in peace..."
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Being the central meeting place for Dominion's citizens, this plaza is the busiest location in all of Dominion."
							+ " Throngs of people, of all different races and appearances, fill the square."
							+ " Some appear to be simply passing through the area, while others lounge about on the many wooden benches and marble steps at the base of each statue."
						+ "</p>"
						+ "<p>"
							+ "On raised platforms, well-spoken orators address the crowds, relaying news and important announcements to the many citizens who pass by."
							+ " Pamphlets and newspapers are handed out beside each one of these stands, and you realise that this is the only place where you've seen any form of news being distributed to the population."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
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
									List<Subspecies> possibleSubspecies = new ArrayList<>();
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
	
	public static final DialogueNode PARK = new DialogueNode("Park", ".", false) {

		@Override
		public String getAuthor() {
			return "Kumiko";
		}
		
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "This area of Dominion is taken up by a gigantic park, filled with a lush amount of foliage, which makes the air here feel very fresh compared to the rest of the city."
						+ " The park consists of several alternating areas of open lawn and woodland, connected by a series of winding paths."
						+ " There's a small lake situated in one corner of the park, and adjacent to that, there's a small field of wild flowers."
						+ " A couple of food stands have been set up in one area for people that didn't come prepared with a picnic."
					+ "</p>"
					+ "<p>"
						+ "The most noteworthy feature is at the very centre of the park, and takes the form of a huge statue of Lilith herself."
						+ " The sultry smile carved into the white marble almost feels at though it's mocking you, and you can't help but feel as though you don't want to stick around here for long."
					+ "</p>"
					+ "<p>"
						+ "For now, you don't have much reason to wander through this park, but if you had someone else with you, it would be a nice place to spend an afternoon; if you can ignore the statue, that is..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Rose Garden", "There's a beautiful rose garden just off to your right. Walk over to it and take a closer look.", PARK_ROSE_GARDEN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("innoxia_hair_rose", false), false));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PARK_ROSE_GARDEN = new DialogueNode("Park", ".", false, true) {

		@Override
		public String getAuthor() {
			return "Innoxia";
		}
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You find your attention drawn towards a small rose garden that's positioned near the park's entrance."
					+ " Walking over towards it, you see that someone's placed a little sign just in front of the border, which reads:"
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "<i>"
						+ "<b>William's Rose Garden</b><br/>"
						+ "Please feel free to help yourself to these roses!"
						+ " I hope you or your partner gets as much happiness out of them as I do from growing them.<br/>"
						+ "- William"
					+ "</i>"
				+ "</p>"
				+ "<p>"
					+ "You look around, but don't see anyone nearby who could be this 'William' character."
					+ " Focusing your attention back to his rose garden, you decide to do as William's sign says, and, stepping forwards, you pluck a single red rose from the nearest bush."
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Rose Garden", "You've already taken a rose from the garden.", null);
			} else {
				return null;
			}
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
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true, true);
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
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true, true);
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
							Main.mainController.moveGameWorld(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);
							
						} else {
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);
						}
						
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
