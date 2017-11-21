package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.List;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.78
 * @version 0.1.87
 * @author Innoxia, Rfpnj
 */
public class Library {
	
	public static final DialogueNodeOld LIBRARY = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			
			UtilText.nodeContentSB.append("<p>"
					+ "Pushing open the heavy wooden door, you find yourself walking into Lilaya's library."
					+ " Much like her lab, all four walls are covered in shelving; stacked full of what must be thousands of books of all shapes and sizes."
					+ " Much of the room is taken up by free-standing book cases, although there's a little space on one side of the room, where a couple of comfortable leather-bound chairs flank an ornate fireplace."
				+ "</p>");
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append("<p>"
							+ "The library is deserted at the moment..."
						+ "</p>");
			} else {
				for(NPC slave : charactersPresent) {
					switch(slave.getObedience()) {
					case NEGATIVE_FIVE_REBELLIOUS: case NEGATIVE_FOUR_DEFIANT: case NEGATIVE_THREE_STRONG_INSUBORDINATE:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "Although <b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.name]</b> is here, [npc.she]'s not even bothering to pretend that [npc.she]'s working."
								+ "</p>"));
						break;
					case NEGATIVE_ONE_DISOBEDIENT:  case NEGATIVE_TWO_UNRULY:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> appears to be half-heartedly ordering some books."
								+ "</p>"));
						break;
					case ZERO_FREE_WILLED:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> is dusting the shelves and making sure that everything is in order."
								+ "</p>"));
						break;
					case POSITIVE_ONE_AGREEABLE: case POSITIVE_TWO_OBEDIENT:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> is reorganising one of the shelves."
								+ "</p>"));
						break;
					case POSITIVE_THREE_DISCIPLINED: case POSITIVE_FOUR_DUTIFUL: case POSITIVE_FIVE_SUBSERVIENT:
						UtilText.nodeContentSB.append(UtilText.parse(slave,
								"<p>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> is dutifully making a catalogue of all the books available in the library."
								+ "</p>"));
						break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index==0) {
				return null;
			 
			} else if (index == 1) {
				return new Response("Browse the Shelves", "Read one of the many books available in the library.", BROWSE_BOOKS);

			} else if(index-2<Main.game.getCharactersPresent().size()) {
				return new Response(UtilText.parse(Main.game.getCharactersPresent().get(index-2), "[npc.Name]"), UtilText.parse(Main.game.getCharactersPresent().get(index-2), "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public void effects() {
						Main.game.setActiveNPC(Main.game.getCharactersPresent().get(index-2));
					}
				};
					
			} else {
				return null;
			}
		}
	};
	

	public static final DialogueNodeOld BROWSE_BOOKS = new DialogueNodeOld("", "", false) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Walking down one of the aisles, you see a great deal of organisation has gone into the design of the room, and upon closer inspection, you see that the shelves are immaculately clean;"
						+ " evidence that a lot of care goes into its maintenance."
					+ " As you walk, you scan the titles printed onto the spines of the books, but there's not really much that catches your eye."
					+ " Only a few shelves really look to be of any interest, and you wonder if you should take some time to do a spot of reading..."
				+ "</p>";
							
		}

		@Override
		public Response getResponse(int responseTab, int books) {
			if (books == 1) {
				return new Response("General Knowledge", "A section of the library dedicated to books on common subjects.", LORE_BOOKS);

			}  else if (books == 2) {
				return new Response("Races of Dominion", "A section of the library dedicated to books concerning the predominate races within the city.", DOMINION_RACES) {
				};

			} else if (books == 3) {
				return new Response("The Fields", "A section of the library dedicated to books about the area known as the Foloi Fields.", FIELDS_BOOKS) {
				};
//
//			} else if (books == 4) {
//				return new Response("The Seas", "A section of the library dedicated to books on the area known as the Endless Sea.", SEA_BOOKS) {
//				};
//
//			} else if (books == 5) {
//				return new Response("The Jungle", "A section of the library dedicated to books on the area known as the Jungle.", JUNGLE_BOOKS) {
//				};
//
//			} else if (books == 6) {
//				return new Response("The Desert", "A section of the library dedicated to books on the area known as the Desert.", DESERT_BOOKS) {
//				};

			} else if (books == 0) {
				return new Response("Back", "Return to the main library menu.", LIBRARY);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LORE_BOOKS = new DialogueNodeOld("", "", false) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Walking down one of the aisles, you find that the bookshelves around you are giving off a faint purple glow; indicative of some sort of arcane enchantment."
						+ " You concentrate on the arcane power, stopping in your tracks as you sense traces of arcane essence lingering in the air around you."
						+ " For a moment, your concentration lingers on a particularly large tome, and your [pc.eyes] open wide as you see it pull itself out of the shelf a little."
					+ "</p>"
					+ "<p>"
						+ "Despite the curious enchantment placed upon these aisles (by, you assume, a bored Lilaya), the actual books themselves don't seem to be anything out of the ordinary."
						+ " Most of them seem to be concerned with general knowledge or other mundane topics, but there are a few that catch your eye..."
				+ "</p>";
							
		}

		@Override

		public Response getResponse(int responseTab, int lore) {
			if (lore == 1) {
				return new Response("Arcane Arousal", "A leather-bound tome that seems to offer an insight into how the arcane works.", ARCANE_AROUSAL) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.readBook1)) {
							Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f);
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.readBook1);
						}
					}
				};

			} else if (lore == 2) {
				return new Response("Lilith's Dynasty", "A hardback book that might give some clues as to who exactly Lilith is.", LILITHS_DYNASTY) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.readBook2)) {
							Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f);
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.readBook2);
						}
					}
				};

			} else if (lore == 3) {
				return new Response("Dominion's History", "A paperback book describing the events that led to the creation of the city you currently find yourself in.", DOMINION_HISTORY) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.readBook3)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.readBook3);
						}
					}
				};

			} else if (lore == 0) {
				return new Response("Back", "Return to browsing the shelves.", BROWSE_BOOKS);

			} else {
				return null;
			}
		}
	
	};
	public static final DialogueNodeOld ARCANE_AROUSAL = new DialogueNodeOld("", "", false) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Sliding the large leather-bound tome out from the bookshelf, you then walk over to sit down on one of the library's comfortable chairs."
						+ " The spine creaks as you open the cover, and as you start reading, you quickly discover that this book is way above your level of understanding."
						+ " You decide to persist, and by flipping through the pages and skimming over some of the more intelligible passages, you do manage to discover some things."
					+ "</p>"
					+ "<p>"
						+ "For one, it seems as though arcane power is found all throughout this world, and although it's mostly concentrated in people's arcane auras, you discover that there are some places out in the wilderness"
						+ " where the arcane condenses into little micro-storms of activity."
						+ " These places allow even the most novice of arcane users to harness its power, but for the most part, a person can only use arcane spells if they train their aura to become strong enough."
						+ " This training process appears to take several years, and you realise how fortunate you are to have an aura with demon-like strength."
					+ "</p>"
					+ "<p>"
						+ "After reading through these technical aspects of the arcane, you move on to the nature of the arcane itself."
						+ " You realise that this section is what must have given the book its title; 'Arcane Arousal'."
						+ " It appears as though the arcane is some kind of primal force that feeds on a person's sexual energy."
						+ " Although a person may normally be able to easily resist their aura's influence, when they get fatigued, their own aura will amplify their sexual desires, causing them to become obsessed with sex."
						+ " This is the power that's behind the arcane storms that often erupt over Dominion, and you once again reflect on how lucky you are to have an aura powerful enough to cancel out the storm's potent effects."
					+ "</p>"
					+ "<p>"
						+ "The last section of the book covers some of the social impacts of the arcane."
						+ " The most notable of these is that while under the arcane's influence, a person's sexuality will shift to being bi-sexual."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int lore) {
			return LIBRARY.getResponse(0, lore);
		}
		
	};
	
	public static final DialogueNodeOld LILITHS_DYNASTY = new DialogueNodeOld("", "", false) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You take the hardback book out of the bookshelf and walk over to sit down on one of the library's comfortable chairs."
						+ " Opening the cover and flipping through the pages reveals that most of this book is taken up by descriptions of trivial events and boring laws."
						+ " The introduction, however, offers a good insight into how this world is ruled, and you spend a short while reading through the pages as you familiarise yourself with Lilith's dynasty."
					+ "</p>"
					+ "<p>"
						+ "You already knew that the demon queen Lilith was the reigning monarch of this world, but what you find interesting is that there is no record of her predecessor."
						+ " In fact, from what you're reading, it sounds as though Lilith has ruled this world since the beginning of recorded history."
						+ " You wonder what sort of immense power she must possess in order to keep control for such a length of time."
					+ "</p>"
					+ "<p>"
						+ "Your question is half-answered as you turn the next few pages."
						+ " It seems as though Lilith's children, referred to as Lilin, act as regional rulers, and are unquestionably loyal to their mother."
						+ " Each Lilin appears to be an immensely powerful demon in her own right, and you realise that with an army of these subordinate children ruling over her domain, Lilith's power base is incredibly secure."
					+ "</p>"
					+ "<p>"
						+ "The final part of the introduction describes how Lilin corrupt their partners before being impregnated, resulting in the birth of demons."
						+ " Apparently, although many Lilin end up giving birth to hundreds, if not thousands, of demons, it's incredibly rare for a Lilin to publicly acknowledge any of her children as her own."
					+ "</p>"
					+ "<p>"
						+ " The book goes on to describe half-demons (born from a Lilin and an un-corrupted partner) as the lowest of the low in the demon's social hierarchy."
						+ " Your eyes widen as you read Lilaya's name as the only recorded half-demon in history to be publicly recognised by her Lilin mother, Lyssieth."
						+ " You think that's it's probably for the best that you don't mention this to Lilaya, after all, she's extremely sensitive about her half-demon form."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int lore) {
			return LIBRARY.getResponse(0, lore);
		}
	};
	
	public static final DialogueNodeOld DOMINION_HISTORY = new DialogueNodeOld("", "", false) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "As you slide the history book out from the bookshelf and flip through the pages, you see that there's not much content in it, and most of the pages are filled with large illustrations."
						+ " Looking at the front cover again, you see that the title is in fact 'A horse-morph's guide to Dominion's history'."
						+ " Tutting at the author's apparent assumption about the intelligence of horse-morphs, you nevertheless have a quick read through the book's pages."
					+ "</p>"
					+ "<p>"
						+ "There isn't really much useful information inside, and you quickly finish the book from cover to cover."
						+ " There's an interesting passage about the construction of Dominion by Lilith and the demons many centuries ago, but it doesn't really go into much detail."
						+ " Apart from that, the only other part of the book that piques your interest is a small section justifying the practice of slavery as a necessary evil."
						+ " The passage explains how it's customary for a new slave to be kissed one last time by their new owner before their enslavement."
						+ " It seems as though it's quite taboo for a slave to be kissed."
					+ "</p>"
					+ "<p>"
						+ "Other than that snippet of trivia, the book doesn't contain anything useful."
					+ "</p>"
					+ (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.readBook3)
							? ""
							: "<p>"
								+ "<b>The book is too simple to gain anything from reading it...</b>"
							+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int lore) {
			return LIBRARY.getResponse(0, lore);
		}
	};
	public static final DialogueNodeOld DOMINION_RACES = new DialogueNodeOld("", "", false) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			return "<p>"

						+ "Walking down one of the aisles, you find yourself surrounded by heavy wooden shelves, fastened together by prominent beams of iron and steel."
						+ " A lot of the books here seem to be about local history of Dominion's many districts, and, while fascinating for a historian, they don't offer much use to you."
						+ " Despite most of the books not being worth your time, there are a few here and there that detail the many races found within the city, and you wonder if you should give them a read..."
					+ "</p>";
							
		}

		@Override
		public Response getResponse(int responseTab, int city) {
			if (city == 1) {
				return bookResponse(ItemType.BOOK_CAT_MORPH, Race.CAT_MORPH);

			} else if (city == 2) {
				return bookResponse(ItemType.BOOK_DEMON, Race.DEMON);

			} else if (city == 3) {
				return bookResponse(ItemType.BOOK_DOG_MORPH, Race.DOG_MORPH);

			} else if (city == 4) {
				return bookResponse(ItemType.BOOK_HARPY, Race.HARPY);

			} else if (city == 5) {
				return bookResponse(ItemType.BOOK_HORSE_MORPH, Race.HORSE_MORPH);

			} else if (city == 6) {
				return bookResponse(ItemType.BOOK_HUMAN, Race.HUMAN);

			} else if (city == 7) {
				return bookResponse(ItemType.BOOK_WOLF_MORPH, Race.WOLF_MORPH);

			} else if (city == 8) {
				return bookResponse(ItemType.BOOK_GATOR_MORPH, Race.GATOR_MORPH);

			} else if (city == 0) {
				return new Response("Back", "Return to browsing the shelves.", BROWSE_BOOKS);
			} else {
				return null;
			}
		}
	
	};
	
	public static final DialogueNodeOld FIELDS_BOOKS = new DialogueNodeOld("", "", false) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "As you walk down one of the library's many aisles, you notice that the air feels somehow fresher than normal."
						+ " Realising that it must be an arcane enchantment of some sort, you take a closer look at some of the shelves around you."
					+ "</p>"
					+ "<p>"
						+ "At first glance, they look to be crafted from blocks of sod, but upon closer inspection, you see that it's actually masterfully-engraved pieces of wood."
						+ " The books that they hold all seem to be related to the Foloi Fields and the many races found there, which would explain the pastoral scent and appearance of this particular section."
					+ "</p>";
							
		}

		@Override
		public Response getResponse(int responseTab, int field) {
			if (field == 1) {
				return bookResponse(ItemType.BOOK_SQUIRREL_MORPH, Race.SQUIRREL_MORPH);

			} else if (field == 2) {
				return bookResponse(ItemType.BOOK_COW_MORPH, Race.COW_MORPH);

			}  else if (field == 0) {
				return new Response("Back to the shelves", "Return to strolling the shelves.", BROWSE_BOOKS);


			} else {
				return null;
			}
		}
	
	};
	

//	public static final DialogueNodeOld SEA_BOOKS = new DialogueNodeOld("", "", false) {
//		/**
//		 */
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public String getLabel() {
//			return "Library";
//		}
//
//		@Override
//		public String getContent() {
//			return "<p>"
//					+ "Walking down one of the aisles, you see that these shelves have an oceanic look to them. The shelves look like they haven been crafted from coral and empty shells."
//					+ "These books have the general feeling of being about the sea and its people."
//					+ "</p>";
//							
//		}
//
//		@Override
//		public Response getResponse(int sea) {
//			if (sea == 1) {
//				return bookResponse(ItemType.BOOK_SQUIRREL_MORPH, Race.SQUIRREL_MORPH);
//
//			} else if (city == 2) {
//				return bookResponse(ItemType.BOOK_COW_MORPH, Race.COW_MORPH);
//
//			}  else if (sea == 0) {
//				return new Response("Back to the shelves", "Return to strolling the stacks.", BROWSE_BOOKS);
//
//			} else {
//				return null;
//			}
//		}
//	
//	};
//	
//	public static final DialogueNodeOld JUNGLE_BOOKS = new DialogueNodeOld("", "", false) {
//		/**
//		 */
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public String getLabel() {
//			return "Library";
//		}
//
//		@Override
//		public String getContent() {
//			return "<p>"
//					+ "Walking down one of the aisles, you see that these shelves have an tropic look to them. The shelves look like they haven been crafted from plaited vines, and broad palms."
//					+ "These books have the general feeling of being about the jungle and its people."
//					+ "</p>";
//							
//		}
//
//		@Override
//		public Response getResponse(int jungle) {
//			if (jungle == 1) {
//				return bookResponse(ItemType.BOOK_SQUIRREL_MORPH, Race.SQUIRREL_MORPH);
//
//			} else if (jungle == 2) {
//				return bookResponse(ItemType.BOOK_COW_MORPH, Race.COW_MORPH);
//
//			}  else if (jungle == 0) {
//				return new Response("Back to the shelves", "Return to strolling the stacks.", BROWSE_BOOKS);
//
//			} else {
//				return null;
//			}
//		}
//	
//	};
//	
//	public static final DialogueNodeOld DESERT_BOOKS = new DialogueNodeOld("", "", false) {
//		/**
//		 */
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public String getLabel() {
//			return "Library";
//		}
//
//		@Override
//		public String getContent() {
//			return "<p>"
//					+ "Walking down one of the aisles, you see that these shelves have an earthy look to them. The shelves look like they haven been crafted from blocks of compressed sand, inter-spaced with cacti."
//					+ "These books have the general feeling of being about the desert and its people."
//					+ "</p>";
//							
//		}
//
//		@Override
//		public Response getResponse(int desert) {
//			if (desert == 1) {
//				return bookResponse(ItemType.BOOK_SQUIRREL_MORPH, Race.SQUIRREL_MORPH);
//
//			} else if (desert == 2) {
//				return bookResponse(ItemType.BOOK_COW_MORPH, Race.COW_MORPH);
//
//			}  else if (desert == 0) {
//				return new Response("Back to the shelves", "Return to strolling the stacks.", BROWSE_BOOKS.);
//
//			} else {
//				return null;
//			}
//		}
//	
//	};
	
	private static Response bookResponse(AbstractItemType book, Race race) {
		if(Main.getProperties().isAdvancedRaceKnowledgeDiscovered(race)) {
			return new Response(book.getName(false), book.getDescription(), LIBRARY) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(book.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
				}
			};
		} else {
			return new Response(book.getName(false), "You haven't discovered this book yet!", null);
		}
	}
}
