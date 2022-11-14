package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.78
 * @version 0.4.3.4
 * @author Innoxia, Rfpnj
 */
public class Library {
	
	private enum LibraryAisle {
		DEMON,
		DOMINION,
		FIELDS,
		JUNGLE,
		MOUNTAIN,
		SEA,
		DESERT;
	}
	
	private static Set<AbstractSubspecies> getAisleSubspecies(LibraryAisle aisle) {
		Set<AbstractSubspecies> aisleSubspecies = new HashSet<>();

		for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
			List<WorldRegion> mostCommonRegion = subspecies.getMostCommonWorldRegions();
			if(mostCommonRegion.isEmpty()) {
				mostCommonRegion.add(WorldRegion.DOMINION);
			}
			boolean add = false;
			boolean demonic = subspecies.getRace()==Race.DEMON || subspecies.getRace()==Race.ANGEL || subspecies.getRace()==Race.ELEMENTAL;
			switch(aisle) {
				case DEMON:
					add = demonic;
					break;
				case DESERT:
					add = !demonic && (mostCommonRegion.contains(WorldRegion.SAVANNAH) || mostCommonRegion.contains(WorldRegion.DESERT) || mostCommonRegion.contains(WorldRegion.DESERT_CITY) || mostCommonRegion.contains(WorldRegion.VOLCANO));
					break;
				case DOMINION:
					add = !demonic && (mostCommonRegion.contains(WorldRegion.DOMINION) || mostCommonRegion.contains(WorldRegion.SUBMISSION));
					break;
				case FIELDS:
					add = !demonic && (mostCommonRegion.contains(WorldRegion.WOODLAND) || mostCommonRegion.contains(WorldRegion.FIELDS) || mostCommonRegion.contains(WorldRegion.FIELD_CITY) || mostCommonRegion.contains(WorldRegion.RIVER));
					break;
				case JUNGLE:
					add = !demonic && (mostCommonRegion.contains(WorldRegion.JUNGLE) || mostCommonRegion.contains(WorldRegion.JUNGLE_CITY));
					break;
				case MOUNTAIN:
					add = !demonic && (mostCommonRegion.contains(WorldRegion.MOUNTAINS) || mostCommonRegion.contains(WorldRegion.YOUKO_FOREST) || mostCommonRegion.contains(WorldRegion.SNOW));
					break;
				case SEA:
					add = !demonic && (mostCommonRegion.contains(WorldRegion.SEA) || mostCommonRegion.contains(WorldRegion.SEA_CITY));
					break;
			}
			if(add) {
				aisleSubspecies.add(subspecies);
			}
		}
		
		return aisleSubspecies;
	}
	
	public static final DialogueNode LIBRARY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
			
			UtilText.nodeContentSB.append("<p>"
					+ "Pushing open the heavy wooden door, you find yourself walking into Lilaya's library."
					+ " Much like her lab, all four walls are covered in shelving; stacked full of what must be thousands of books of all shapes and sizes."
					+ " Much of the room is taken up by free-standing book cases, although there's a little space on one side of the room, where a couple of comfortable leather-bound chairs flank an ornate fireplace."
				+ "</p>"
				+ "<p>" //TODO
					+ "Walking down one of the aisles, you see a great deal of organisation has gone into the design of the room, and upon closer inspection, you see that the shelves are immaculately clean;"
					+ " evidence that a lot of care goes into its maintenance."
					+ " As you walk, you scan the titles printed onto the spines of the books, but there's not really much that catches your eye."
					+ " Only a few shelves really look to be of any interest, and you wonder if you should take some time to do a spot of reading."
				+ "</p>"
				+ "<p>"
					+ "On the back wall at the end of the shelves is a huge map of the city of Dominion. You wonder if you should take a picture of it with your phone's camera."
				+ "</p>"
				+ "<p>"//TODO
					+"One of the library's aisles is dedicated to holding copies of the spell books that you've discovered and read in your travels."
					+ " As you walk down this aisle, you see that the shelves in this section are fashioned out of shimmering purple energy, and seem to shift and move with a life of their own."
				+ "</p>");
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append("<p>"
							+ "The library is deserted at the moment..."
						+ "</p>");
			} else {
				for(NPC slave : charactersPresent) {
					UtilText.nodeContentSB.append(UtilText.parse(slave,
							"<p>"
								+ "Having been assigned to work as a "+(SlaveJob.LIBRARY.getName(slave))+", <b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.name]</b> is present in this area."));
					
					if(slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)) {
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								" As you've instructed [npc.herHim] to crawl, [npc.sheIs] down on all fours, and "));
					} else {
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								" [npc.She] "));
					}
					
					switch(slave.getObedience()) {
						case NEGATIVE_FIVE_REBELLIOUS: case NEGATIVE_FOUR_DEFIANT: case NEGATIVE_THREE_STRONG_INSUBORDINATE:
							UtilText.nodeContentSB.append(UtilText.parse(slave,
										" is not even bothering to pretend that [npc.sheIs] working."
									+ "</p>"));
							break;
						case NEGATIVE_ONE_DISOBEDIENT:  case NEGATIVE_TWO_UNRULY:
							UtilText.nodeContentSB.append(UtilText.parse(slave,
										" appears to be half-heartedly ordering some books."
									+ "</p>"));
							break;
						case ZERO_FREE_WILLED:
							UtilText.nodeContentSB.append(UtilText.parse(slave,
										" is currently dusting the shelves and making sure that everything is in order."
									+ "</p>"));
							break;
						case POSITIVE_ONE_AGREEABLE: case POSITIVE_TWO_OBEDIENT:
							UtilText.nodeContentSB.append(UtilText.parse(slave,
										" is currently reorganising one of the shelves."
									+ "</p>"));
							break;
						case POSITIVE_THREE_DISCIPLINED: case POSITIVE_FOUR_DUTIFUL: case POSITIVE_FIVE_SUBSERVIENT:
							UtilText.nodeContentSB.append(UtilText.parse(slave,
										" is dutifully making a catalogue of all the books available in the library."
									+ "</p>"));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Library";
				
			} else if(index==1) {
				return "Fast Travel";
				
			} else if(index==2) {
				return "Spells";
				
			} else if(index==3) {
				return "Races";
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
				
				if (index == 1) {
					if(Main.game.getCurrentDialogueNode()==ARCANE_AROUSAL) {
						return new Response("Arcane Arousal", "You are already reading this book!", null);
					}
					return new Response("Arcane Arousal", "A leather-bound tome that seems to offer an insight into how the arcane works.", ARCANE_AROUSAL) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.readBook1)) {
								Main.game.getPlayer().incrementAttribute(Attribute.SPELL_COST_MODIFIER, 1f);//TODO replace with something else
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook1, true);
						}
					};

				} else if (index == 2) {
					if(Main.game.getCurrentDialogueNode()==LILITHS_DYNASTY) {
						return new Response("Lilith's Dynasty", "You are already reading this book!", null);
					}
					return new Response("Lilith's Dynasty", "A hardback book that might give some clues as to who exactly Lilith is.", LILITHS_DYNASTY) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook2, true);
						}
					};

				} else if (index == 3) {
					if(Main.game.getCurrentDialogueNode()==DOMINION_HISTORY) {
						return new Response("Dominion's History", "You are already reading this book!", null);
					}
					return new Response("Dominion's History", "A paperback book describing the events that led to the creation of the city you currently find yourself in.", DOMINION_HISTORY) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook3, true);
						}
					};

				} else if (index == 4) {
					if(Main.game.getCurrentDialogueNode()==PREGNANCY_INFO) {
						return new Response("Knocked Up", "You are already reading this book!", null);
					}
					return new Response("Knocked Up", "A small paperback book which contains all the information you'd ever need concerning pregnancies in this world.", PREGNANCY_INFO) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook4, true);
						}
					};

				} else if (index == 5) {
					if(Main.game.getCurrentDialogueNode()==DOMINION_MAP) {
						return new Response("City Map", "You are already viewing the map of Dominion!", null);
					}
					return new Response("City Map", "A large, framed map of Dominion hangs on one wall. Take a picture of it.", DOMINION_MAP) {
						@Override
						public void effects() {
							Cell[][] grid = Main.game.getWorlds().get(WorldType.DOMINION).getGrid();
							for(int i=0; i<grid.length; i++) {
								for(int j=0; j<grid[0].length; j++) {
									grid[i][j].setDiscovered(true);
								}
							}
						}
					};
	
				} else if (index == 6 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLAVERY, Quest.SIDE_SLAVER_NEED_RECOMMENDATION)) {
					if(Main.game.getCurrentDialogueNode()==SLAVERY_HISTORY) {
						return new Response("People as Property", "You are already reading this book!", null);
					}
					return new Response("People as Property", "A thick, hardback book detailing the history and legality of slavery in Lilith's Realm.", SLAVERY_HISTORY) {
						@Override
						public void effects() {
							Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "SLAVERY_HISTORY"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBookSlavery, true);
						}
					};
	
				} else if (index == 7 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.readBookSlavery)) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("acexp_dungeon_found"))) {
						return new Response("Lilaya's dungeon",
								"Pull the thick book bearing the title 'Lilaya's Dirty Secrets' to open the secret passage down to Lilaya's dungeon.",
								DialogueManager.getDialogueFromId("acexp_dominion_lilaya_dungeon_stairsUp")) {
							@Override
							public void effects() {
								Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DUNGEON_OPENS"));
								Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("acexp/dominion/lilaya_dungeon", "DUNGEON_ENTRY"));
								Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("acexp_dungeon"), PlaceType.getPlaceTypeFromId("acexp_dungeon_stairs"), false);
							}
						};
						
					} else {
						return new Response("'Lilaya's Dirty Secrets'", "A thick book bearing the title 'Lilaya's Dirty Secrets' has caught your eye...", DUNGEON_TRIGGER) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.getDialogueFlagValueFromId("acexp_dungeon_found"), true);
							}
						};
					}
	
				} else if(index>=8 && index-8<charactersPresent.size()) {
					NPC slave = charactersPresent.get(index-8);
					return new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
						@Override
						public Colour getHighlightColour() {
							return slave.getFemininity().getColour();
						}
						@Override
						public void effects() {
							SlaveDialogue.initDialogue(slave, false);
						}
					};
				}
				
			} else if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			
			} else if(responseTab==2) {
				List<Spell> spells = Main.game.getPlayer().getSpells();
				Spell spell = null;
				
				if (index == 0) {
					if(spells.size()>=15) {
						spell = spells.get(14);
					} else {
						return null;
					}

				} else if (index < 15 && index-1 < spells.size()) {
					spell = spells.get(index-1);
					
				} else if (index >= 15 && index < spells.size()) {
					spell = spells.get(index);
				}
				
				if(spell!=null) {
					return getSpellResponse(spell);
				}
				
			} else if(responseTab==3) {
				if (index == 1) {
					return new Response("Ancient Ones", "A section of the library dedicated to books concerning demonic and angelic races.", ELDER_RACES);

				} else if (index == 2) {
					return new Response("Races of Dominion", "A section of the library dedicated to books concerning the predominant races within the city.", DOMINION_RACES);

				} else if (index == 3) {
					return new Response("Foloi Fields", "A section of the library dedicated to books about the area known as the Foloi Fields.", FIELDS_BOOKS);

				} else if (index == 4) {
					return new Response("Mountains", "A section of the library dedicated to books on the area known as the Mountains of the Moon.", MOUNTAIN_BOOKS);

				} else if (index == 5) {
					return new Response("Endless Sea", "A section of the library dedicated to books on the area known as the Endless Sea.", SEA_BOOKS);

				} else if (index == 6) {
					return new Response("The Jungle", "A section of the library dedicated to books on the area known as the Jungle.", JUNGLE_BOOKS);

				} else if (index == 7) {
					return new Response("The Desert", "A section of the library dedicated to books about the desert to the south of Lilith's Realm.", DESERT_BOOKS);
				}
			}
			
			return null;
		}
	};
	
	private static Response getSpellResponse(Spell spell) {
		return new Response(spell.getName(), "Read about the spell '"+spell.getName()+"'.", SPELL_BOOK) {
			@Override
			public void effects() {
				Main.game.getTextEndStringBuilder().append(ItemType.getSpellBookType(spell).getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 0));
			}
		};
	}

	public static final DialogueNode SPELL_BOOK = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ARCANE_AROUSAL = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "ARCANE_AROUSAL");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LILITHS_DYNASTY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "LILITHS_DYNASTY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int lore) {
			return LIBRARY.getResponse(responseTab, lore);
		}
	};
	
	public static final DialogueNode DOMINION_HISTORY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DOMINION_HISTORY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int lore) {
			return LIBRARY.getResponse(responseTab, lore);
		}
	};
	
	public static final DialogueNode PREGNANCY_INFO = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "PREGNANCY_INFO");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOMINION_MAP = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DOMINION_MAP"));
			sb.append(RenderingEngine.ENGINE.getFullMap(WorldType.DOMINION, false, false));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SLAVERY_HISTORY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DUNGEON_TRIGGER = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DUNGEON_TRIGGER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lilaya's dungeon",
						"Head down the spiral staircase to Lilaya's dungeon.",
						DialogueManager.getDialogueFromId("acexp_dominion_lilaya_dungeon_stairsUp")) {
					@Override
					public void effects() {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("acexp/dominion/lilaya_dungeon", "DUNGEON_ENTRY"));
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("acexp_dungeon"), PlaceType.getPlaceTypeFromId("acexp_dungeon_stairs"), false);
					}
				};
			} else if(index==2) {
				return new Response("Maybe later", "Decide against heading down to Lilaya's dungeon for now...", DUNGEON_TRIGGER_BACK);
			}
			return null;
		}
	};

	public static final DialogueNode DUNGEON_TRIGGER_BACK = new DialogueNode("", "", false, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DUNGEON_TRIGGER_BACK");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ELDER_RACES = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "Walking down one of the aisles, you find yourself surrounded by shelving that's been carved out of dark purple stone, and as you pass, the polished surface glistens and sparkles as it reflects the light from the overhead lamps."
						+ " You're alarmed to discover that as you touch this strange material, a sexual thrill runs through your body."
					+ "</p>"
					+ "<p>"
						+ "It looks as though the books in this particular section are concerned with demonic history and trivia, and while most of them wouldn't really be worth your time, there are a few that catch your attention..."
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.DEMON)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(ELDER_RACES, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Back", "Return to the race index.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	
	};
	
	public static final DialogueNode DOMINION_RACES = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "Walking down one of the aisles, you find yourself surrounded by heavy wooden shelves, fastened together by prominent beams of iron and steel."
						+ " A lot of the books here seem to be about local history of Dominion's many districts, and, while fascinating for a historian, they don't offer much use to you."
					+ "</p>"
					+ "<p>"
						+ "You notice that a couple of the shelves look a little different from the others."
						+ " The top-most shelf seems to be decorated to make it look as though it's crafted from twigs, just like a bird's nest."
						+ " The shelf nearest to the floor has a very smooth surface, almost like the wood as been transmuted into stone."
						+ " It looks like it would be moist to the touch."
					+ "</p>"
					+ "<p>"
						+ "Despite most of these books not being worth your time, there are a few here and there that detail the many races found within the city, and you wonder if you should give them a read..."
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.DOMINION)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(DOMINION_RACES, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Back", "Return to the race index.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	
	};
	
	public static final DialogueNode FIELDS_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "As you walk down one of the library's many aisles, you notice that the air feels somehow fresher than normal."
						+ " Realising that it must be an arcane enchantment of some sort, you take a closer look at some of the shelves around you."
					+ "</p>"
					+ "<p>"
						+ "At first glance, they look to be crafted from blocks of sod, but upon closer inspection, you see that it's actually masterfully-engraved pieces of wood."
						+ " One shelf looks like the sod is covered in snow, and you can feel the cold wafting off of it."
						+ " The books that they hold all seem to be related to the Foloi Fields and the many races found there, which would explain the pastoral scent and appearance of this particular section."
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.FIELDS)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(FIELDS_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Back", "Return to the race index.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode JUNGLE_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "The aisle you find yourself [pc.walking] down has had an arcane enchantment placed upon it, which is creating a hot, humid atmosphere."
						+ " The shelves to either side of you have been formed from huge, living pieces of dark wood, and here and there, small tropical plants are growing from its surface."
					+ "</p>"
					+ "<p>"
						+ "Thanks once again to the arcane enchantment, this environment doesn't seem to be damaging the many books which are on display, and as you pass them by, you see that they're almost exclusively related to jungle topics."
						+ " Coming to a halt half-way down the aisle, you see that some of the nearby books are related to the many races found in the northern jungles, and you wonder whether you should stop and read some of them..."
					+ "</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.JUNGLE)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(JUNGLE_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Back", "Return to the race index.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode MOUNTAIN_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.Walking] down one of the many aisles in Lilaya's library, you become aware of the fact that the air around you is significantly colder and thinner than it should be."
						+ " Realising that it must be due to an arcane enchantment of some sort, you look around at the shelves to either side of you and see that they're made out of roughly-hewn rock."
						+ " The books sitting upon them seem to all be related to mountainous topics, letting you know that the arcane enchantment must be to give a sense of atmosphere to the high-altitude theme of this area."
					+ "</p>"
					+ "<p>"
						+ "A collection of books near to hand appear to be related to the many races which call the Mountains of the Moon their home, and you wonder whether you should stop and read some of them..."
					+ "</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.MOUNTAIN)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(MOUNTAIN_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Back", "Return to the race index.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SEA_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "The faint sound of waves lapping on the shore can be heard as you [pc.walk] down one of the many aisles of books in Lilaya's library."
						+ " Breathing in the refreshing, salty smell of the sea, you realise that this section has had an arcane enchantment placed upon it."
						+ " With the shelves made out of what appears to be living coral and pieces of driftwood, you're given all the clues you need to deduce that this aisle is dedicated to books on the topic of the sea."
					+ "</p>"
					+ "<p>"
						+ "Stopping to take a closer look at some of the tomes on offer, you see that there are several related to the many races found in the Endless Sea, and you wonder whether you should take some time out to do a spot of reading..."
					+ "</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.SEA)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(SEA_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Back", "Return to the race index.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode DESERT_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.Walking] down one of the library's many aisles, you can't help but notice how the air is significantly hotter and drier than elsewhere in the room."
						+ " The arcane enchantment responsible for this effect seems to have been created to fit with the theme of the books on the shelves, as they appear to all be about topics related to the desert to the south of Lilith's Realm."
						+ " The shelves themselves are also in keeping with this theme, as they're built out of thick blocks of marble, with curious hieroglyphics carved into their surface."
					+ "</p>"
					+ "<p>"
						+ "Stopping half-way down the aisle, you look over the books and see that several of them are related to the races found in the desert."
						+ " Wondering if they may contain useful information, you think that it may be a good idea to stop and read through them..."
					+ "</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.DESERT)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(DESERT_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Back", "Return to the race index.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};
	
	private static Response bookResponse(DialogueNode nodeToReturnTo, AbstractSubspecies subspecies) {
		AbstractItemType book = ItemType.getLoreBook(subspecies);

		if(Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspecies)) {
			return new Response(book.getName(false), book.getDescription(), nodeToReturnTo) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(book.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1));
				}
			};
			
		} else {
			return new Response(book.getName(false), "You haven't discovered this book yet!", null);
		}
	}
}
