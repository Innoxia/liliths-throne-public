package com.base.game.dialogue.places.dominion.lilayashome;

import com.base.game.character.attributes.Attribute;
import com.base.game.character.race.Race;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.inventory.item.ItemType;
import com.base.main.Main;

/**
 * @since 0.1.78
 * @version 0.1.82
 * @author Innoxia
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
			return "<p>"
						+ "Pushing open a heavy wooden door, you find yourself walking into Lilaya's library."
						+ " Much like her lab, the walls are covered in shelving, holding hundreds upon hundreds of books of all shapes and sizes."
						+ " Much of the room is taken up by free-standing book cases, although there's a little space on one side of the room, where a couple of comfortable leather-bound chairs flank an ornate fireplace."
					+ "</p>"
					+ "<p>"
						+ "Walking around, you scan the titles printed onto the spines of the books, but there's not really much that catches your eye."
						+ " Only a few volumes really look to be worth your time, and you wonder if you should take some time to do a spot of reading..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Arcane Arousal", "A leather-bound tome that seems to offer an insight into how the arcane works.", ARCANE_AROUSAL) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().readBook1) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f));
							Main.game.getDialogueFlags().readBook1 = true;
						}
					}
				};

			} else if (index == 2) {
				return new Response("Lilith's Dynasty", "A hardback book that might give some clues as to who exactly Lilith is.", LILITHS_DYNASTY) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().readBook2) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAttribute(Attribute.INTELLIGENCE, 0.5f));
							Main.game.getDialogueFlags().readBook2 = true;
						}
					}
				};

			} else if (index == 3) {
				return new Response("Dominion's History", "A paperback book describing the events that led to the creation of the city you currently find yourself in.", DOMINION_HISTORY) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().readBook3) {
							Main.game.getDialogueFlags().readBook3 = true;
						}
					}
				};

			} else if (index == 4) {
				return bookResponse(ItemType.BOOK_CAT_MORPH, Race.CAT_MORPH);

			} else if (index == 5) {
				return bookResponse(ItemType.BOOK_DEMON, Race.DEMON);

			} else if (index == 6) {
				return bookResponse(ItemType.BOOK_DOG_MORPH, Race.DOG_MORPH);

			} else if (index == 7) {
				return bookResponse(ItemType.BOOK_HARPY, Race.HARPY);

			} else if (index == 8) {
				return bookResponse(ItemType.BOOK_HORSE_MORPH, Race.HORSE_MORPH);

			} else if (index == 9) {
				return bookResponse(ItemType.BOOK_HUMAN, Race.HUMAN);

			} else if (index == 10) {
				return bookResponse(ItemType.BOOK_WOLF_MORPH, Race.WOLF_MORPH);

			} else if (index == 11) {
				return bookResponse(ItemType.BOOK_SQUIRREL_MORPH, Race.SQUIRREL_MORPH);

			} else if (index == 12) {
				return bookResponse(ItemType.BOOK_COW_MORPH, Race.COW_MORPH);

			} else {
				return null;
			}
		}
	};
	
	private static Response bookResponse(ItemType book, Race race) {
		if(Main.game.getPlayer().getRacesAdvancedKnowledge().contains(race)) {
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Arcane Arousal", "A leather-bound tome that seems to offer an insight into how the arcane works.", null);

			} else {
				return LIBRARY.getResponse(index);
			}
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
		public Response getResponse(int index) {
			if (index == 2) {
				return new Response("Lilith's Dynasty", "A hardback book that might give some clues as to who exactly Lilith is.", null);

			} else {
				return LIBRARY.getResponse(index);
			}
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
					+ (Main.game.getDialogueFlags().readBook3
							? ""
							: "<p>"
								+ "<b>The book is too simple to gain anything from reading it...</b>"
							+ "</p>");
		}

		@Override
		public Response getResponse(int index) {
			if (index == 3) {
				return new Response("Dominion's History", "A paperback book describing the events that led to the creation of the city you currently find yourself in.", null);

			} else {
				return LIBRARY.getResponse(index);
			}
		}
	};
}
