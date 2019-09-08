package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.List;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.SlaveJob;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.78
 * @version 0.3
 * @author Innoxia, Rfpnj
 */
public class Library {
	
	public static final DialogueNode LIBRARY = new DialogueNode("", "", false) {

		@Override
		public String getLabel() {
			return "Library";
		}

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
				return "Spells";
				
			} else if(index==2) {
				return "Races";
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
				
				if (index == 1) {
					return new Response("Arcane Arousal", "A leather-bound tome that seems to offer an insight into how the arcane works.", ARCANE_AROUSAL) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.readBook1)) {
								Main.game.getPlayer().incrementAttribute(Attribute.SPELL_COST_MODIFIER, 1f);
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.readBook1);
							}
						}
					};

				} else if (index == 2) {
					return new Response("Lilith's Dynasty", "A hardback book that might give some clues as to who exactly Lilith is.", LILITHS_DYNASTY) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.readBook2)) {
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.readBook2);
							}
						}
					};

				} else if (index == 3) {
					return new Response("Dominion's History", "A paperback book describing the events that led to the creation of the city you currently find yourself in.", DOMINION_HISTORY) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.readBook3)) {
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.readBook3);
							}
						}
					};

				} else if (index == 4) {
					return new Response("Knocked up", "A small paperback book which contains all the information you'd ever need concerning pregnancies in this world.", PREGNANCY_INFO) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.readBook4)) {
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.readBook4);
							}
						}
					};

				} else if (index == 5) {
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
	
				} else if(index>=6 && index-6<charactersPresent.size()) {
					NPC character = charactersPresent.get(index-6);
					
					return new Response(UtilText.parse(character, "[npc.Name]"), UtilText.parse(character, "Interact with [npc.name]."), SlaveDialogue.SLAVE_START) {
						@Override
						public void effects() {
							SlaveDialogue.initDialogue(character, false);
						}
					};
				}
				
			} else if(responseTab==1) {
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
				
			} else if(responseTab==2) {
				if (index == 1) {
					return new Response("Ancient Ones", "A section of the library dedicated to books concerning the elder races.", ELDER_RACES);

				} else if (index == 2) {
					return new Response("Races of Dominion", "A section of the library dedicated to books concerning the predominate races within the city.", DOMINION_RACES);

				} else if (index == 3) {
					return new Response("Foloi Fields", "A section of the library dedicated to books about the area known as the Foloi Fields.", FIELDS_BOOKS);

				} else if (index == 4) {
					return new Response("Endless Sea", "A section of the library dedicated to books on the area known as the Endless Sea. (Not yet implemented.)", null);

				} else if (index == 5) {
					return new Response("The Jungle", "A section of the library dedicated to books on the area known as the Jungle. (Not yet implemented.)", null);

				} else if (index == 6) {
					return new Response("The Desert", "A section of the library dedicated to books on the area known as the Desert. (Not yet implemented.)", null);
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
		public String getLabel() {
			return "Library";
		}

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
						+ "Finally, you skim over the book's last few chapters, which define 'arcane essences'."
						+ " These essences are described as being physical, gaseous manifestations of arcane power, and are able to be infused into clothing and items of food in order to create enchanted items and potions."
						+ " People who have a very high level of arcane power are able to absorb arcane essences from out of the aura of people orgasming or being defeated in combat near them,"
							+ " with lilin even being able to passively absorb essences in a radius of several thousand metres."
						+ " This does <i>not</i> diminish the aura of the orgasming person, but it does cause their aura to strengthen, preventing additional essence absorption for a few hours."
					+ "</p>"
					+ "<p>"
						+ "The book finishes by noting that, due to the sexual pleasure derived from beating others, powerful arcane users who are sadists are able to absorb essences from those they abuse or beat in fights."
						+ " Conversely, powerful arcane users who are masochists absorb essences from being beaten."
					+ "</p>";
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
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You take the hardback book out of the bookshelf and walk over to sit down on one of the library's comfortable chairs."
						+ " Opening the cover and flipping through the pages reveals that most of this book is taken up by descriptions of trivial events and tedious demon politics."
						+ " In general, however, it does provide a good insight into how this world is ruled, and you spend a short while reading through the pages as you familiarise yourself with Lilith's dynasty."
					+ "</p>"
					+ "<p>"
						+ "You already knew that the demon queen Lilith was the reigning monarch of this world, but what you find interesting is that there is no record of her predecessor."
						+ " In fact, from what you're reading, it sounds as though Lilith has ruled this world since the beginning of recorded history."
						+ " You wonder what sort of immense power she must possess in order to keep control for such a length of time."
					+ "</p>"
					+ "<p>"
						+ "Your question is half-answered as you turn the next few pages."
						+ " It seems as though Lilith's offspring, referred to as lilin, act as regional rulers, and are unquestionably loyal to their mother."
						+ " Each Lilin appears to be an immensely powerful demon in her own right, and you realise that with an army of these subordinates ruling over her domain, Lilith's power base is incredibly secure."
					+ "</p>"
					+ "<p>"
						+ "Apparently, all of the lilin in the world were once common races, who were corrupted by Lilith into their new forms."
						+ " The book makes a prominent mention that there are seven exceptions to this; those being the seven of Lilith's natural-born daughters, who are referred to as the 'elder lilin'."
						+ " In order of seniority, these seven elders are:<br/>"
						+ "<i>Lirecea, Lovienne, Lasielle, Lyssieth, Lunette, Lyxias, and Lisophia.</i>"
					+ "</p>"
					+ "<p>"
						+ "The final part of the introduction describes how, in a way similar to their mother's, lilin usually corrupt their partners into demons before sex, so that the resulting offspring will be pure demons."
						+ " Half-demons are considered to be lower in social standing than pure demons, with half-demons being born of a lilin and human being regarded as particularly shameful."
					+ "</p>"
					+ "<p>"
						+ "Apparently, although many Lilin end up giving birth to hundreds, if not thousands, of demons, it's incredibly rare for a lilin to publicly acknowledge any of her demonic children as her own."
						+ " Recognised half-demons seem to be almost unheard of, with just a handful of mentions throughout the entire book."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "In the book's centre-fold, there's a table which shows how demonic breeding works:<br/><br/>"
						+ "<table style='margin: 0px auto;'>"
						+ "<tr style='font-weight:bold; text-align:left; color:"+Colour.MASCULINE.toWebHexString()+";'>"
							+ "<td>[style.boldFeminine(Mother)]/[style.boldMasculine(Father)]</td><td>Lilin</td><td>Demon</td><td>Half-demon</td><td>Human half-demon</td><td>Non-demon</td><td>Human</td><td>Imp</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td style='font-weight:bold; color:"+Colour.FEMININE.toWebHexString()+";'>Lilin</td><td>[style.boldtfGreater(Ln)]</td><td>[style.boldtfLesser(Dn)]</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldtfPartial(Hhdn)]</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldtfPartial(Hhdn)]</td><td>[style.boldBad(Ip)]</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td style='font-weight:bold; color:"+Colour.FEMININE.toWebHexString()+";'>Demon</td><td>[style.boldtfLesser(Dn)]</td><td>[style.boldtfLesser(Dn)]</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldtfPartial(Hhdn)]</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldtfPartial(Hhdn)]</td><td>[style.boldBad(Ip)]</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td style='font-weight:bold; color:"+Colour.FEMININE.toWebHexString()+";'>Half-demon</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldtfPartial(Hhdn)]</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldBad(Ip)]</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td style='font-weight:bold; color:"+Colour.FEMININE.toWebHexString()+";'>Human half-demon</td><td>[style.boldtfPartial(Hhdn)]</td><td>[style.boldtfPartial(Hhdn)]</td><td>[style.boldtfPartial(Hhdn)]</td><td>[style.boldBad(Ip)]</td><td>[style.boldBad(Ip)]</td><td>[style.boldBad(Ip)]</td><td>[style.boldBad(Ip)]</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td style='font-weight:bold; color:"+Colour.FEMININE.toWebHexString()+";'>Non-demon</td><td>[style.boldtfMinor(Hdn)]</td</td><td>[style.boldtfMinor(Hdn)]</td</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldBad(Ip)]</td><td>[style.boldtfGeneric(Nd)]</td><td>[style.boldtfGeneric(Nd)]</td><td>[style.boldBad(Ip)]</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td style='font-weight:bold; color:"+Colour.FEMININE.toWebHexString()+";'>Human</td><td>[style.boldtfPartial(Hhdn)]</td</td><td>[style.boldtfPartial(Hhdn)]</td</td><td>[style.boldtfMinor(Hdn)]</td><td>[style.boldBad(Ip)]</td><td>[style.boldtfGeneric(Nd)]</td><td>[style.boldHuman(Hn)]</td><td>[style.boldBad(Ip)]</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td style='font-weight:bold; color:"+Colour.FEMININE.toWebHexString()+";'>Imp</td><td>[style.boldBad(Ip)]</td><td>[style.boldBad(Ip)]</td><td>[style.boldBad(Ip)]</td><td>[style.boldBad(Ip)]</td><td>[style.boldBad(Ip)]</td><td>[style.boldBad(Ip)]</td><td>[style.boldBad(Ip)]</td>"
						+ "</tr>"
						+ "</table>"
						+ "<br/><br/>"
						+ "[style.boldtfGreater(Ln)] = Lilin&nbsp;&nbsp;&nbsp;&nbsp;"
						+ "[style.boldtfLesser(Dn)] = Demon&nbsp;&nbsp;&nbsp;&nbsp;"
						+ "[style.boldtfMinor(Hdn)] = Half-demon&nbsp;&nbsp;&nbsp;&nbsp;"
						+ "[style.boldtfPartial(Hhdn)] = Human half-demon</br>"
						+ "[style.boldtfGeneric(Nd)] = Non-demon (non-human)&nbsp;&nbsp;&nbsp;&nbsp;"
						+ "[style.boldHuman(Hn)] = Human&nbsp;&nbsp;&nbsp;&nbsp;"
						+ "[style.boldBad(Ip)] = Imp"
					+ "</p>"
					+ "<p>"
						+ "After this, the book goes on to describe human half-demons as being particularly despised in the demon's social hierarchy."
						+ " It explains that this is due to the fact that they are the only demon-kind to produce imp offspring, and imps are considered to be the lowest of all (even below humans)."
						+ " While still of a higher social standing than any non-demon, human half-demons are typically treated very poorly by their 'purer' relatives,"
							+ " who will often insult them with terms such as 'imp-breeder', 'imp-factory', 'imp-fucker', or other such profanities."
					+ "</p>"
					+ "<p>"
						+ "Your eyes widen as you read that Lilaya is one of only two recorded human half-demons in history to be publicly recognised by their lilin mother (who, in Lilaya's case, is the elder lilin, Lyssieth)."
						+ (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonBossEncountered)
								?" The other half-demon mentioned is Lilaya's sister, [siren.name]."
								:" The other half-demon to be mentioned is someone by the name of [siren.name], who is another of Lyssieth's daughters.")
						+ " After reading about this, you can understand why Lilaya is extremely sensitive about her human half-demon form."
					+ "</p>";
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
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "As you slide the history book out from the bookshelf and flip through the pages, you see that there's not much content in it, and most of the pages are taken up by large illustrations."
						+ " Looking at the front cover again, you see that the title is in fact 'A horse-morph's guide to Dominion's history'."
						+ " Tutting at the author's apparent assumption about the intelligence of horse-morphs, you nevertheless have a quick read through the book's pages."
					+ "</p>"
					+ "<p>"
						+ "There isn't really much useful information inside, and you quickly finish the book from cover to cover."
						+ " There's an interesting passage about the construction of Dominion by Lilith and the demons many centuries ago, but it doesn't really go into any detail."
					+ "</p>"
					+ "<p>"
						+ " Apart from that, the only other part of the book that piques your interest is a small section justifying the practice of slavery as a necessary evil."
						+ " The passage explains how demons, not including half-demons, are the only race which are almost never seen to be enslaved."
						+ " Apparently, this is due to a non-demon owning a demonic slave being considered completely unacceptable in Dominion's society, and requires a very hard-to-obtain license."
						+ " It also mentions that Enforcers are very hesitant to sign off on any demon enslavement order,"
							+ " as for all they know, the demon in question could turn out to be the favourite offspring of a lilin, who would be sure to bring their wrath down upon them..."
					+ "</p>"
					+ "<p>"
						+ "Other than that snippet of trivia, the book doesn't contain anything useful."
					+ "</p>";
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
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You slide the small paperback book out from the shelf, and, turning it over in your [pc.hands], you take a look at the front cover."
						+ " On it, the title 'Knocked up' is displayed in bold pink lettering, and beneath that, there's a picture of a heavily-pregnant rabbit-girl cradling her swollen belly."
						+ " A little speech-bubble is drawn coming from her mouth, and in it, you read the words 'All you need to know about being a parent!'."
					+ "</p>"
					+ "<p>"
						+ "Opening its pages, you find that the information contained within the book is laid out in a very neat and easy-to-read format, and it only takes you a few minutes to read through the entire thing."
						+ " One of the most striking facts is that in this world, the full cycle of pregnancy only lasts for a few weeks."
						+ " What's more, once the mother is ready to give birth, she's able to stay in that state indefinitely, until such time as she feels comfortable giving birth."
					+ "<p>"
					+ "<p>"
						+ "The second alarming fact contained within these pages is related to the development of children."
						+ " It only takes a few minutes for new-borns to develop and mature into adults, and will inherit all of the common knowledge held by their parents."
						+ " This doesn't include specific memories, so for example, if a child's mother knows how to read and write, they will too, but they won't have the memory of their mother learning this information."
					+ "</p>"
					+ "<p>"
						+ "After reaching full maturity within a matter of hours, the vast majority of children will immediately leave their parents in order to strike out for themselves and become fully independent."
						+ " Despite this almost-immediate separation, a parent will always share special maternal or paternal bonds with their children, and, whether due to the arcane or some natural intuition,"
							+ " a parent and child, as well as siblings, will always recognise each other at first sight."
					+ "</p>";
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
		public String getLabel() {
			return "Library";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
						+ "Hanging on one of the walls of the library, a huge map of Dominion is displayed in a wooden frame."
						+ " Using your phone, you take a picture of it for future reference."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.italicsExcellent(Dominion Map fully revealed!)]"
					+ "</p>"
					+ RenderingEngine.ENGINE.getFullMap(WorldType.DOMINION, false, false));
			
			return UtilText.nodeContentSB.toString();
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
		public String getLabel() {
			return "Library";
		}

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
			if(responseTab==2) {
				if (index == 1) {
					return bookResponse(Subspecies.ANGEL);
	
				} else if (index == 2) {
					return bookResponse(Subspecies.ELDER_LILIN);
	
				} else if (index == 3) {
					return bookResponse(Subspecies.LILIN);
	
				} else if (index == 4) {
					return bookResponse(Subspecies.DEMON);
	
				} else if (index == 5) {
					return bookResponse(Subspecies.HALF_DEMON);
	
				} else if (index == 0) {
					return new Response("Back", "Return to the race index.", LIBRARY);
					
				} else {
					return null;
				}
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	
	};
	
	public static final DialogueNode DOMINION_RACES = new DialogueNode("", "", false) {

		@Override
		public String getLabel() {
			return "Library";
		}

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
			if(responseTab==2) {
				if (index == 1) {
					return bookResponse(Subspecies.HARPY);
	
				} else if (index == 2) {
					return bookResponse(Subspecies.DOG_MORPH);
	
				} else if (index == 3) {
					return bookResponse(Subspecies.CAT_MORPH);
	
				} else if (index == 4) {
					return bookResponse(Subspecies.HORSE_MORPH);
	
				} else if (index == 5) {
					return bookResponse(Subspecies.WOLF_MORPH);
	
				} else if (index == 6) {
					return bookResponse(Subspecies.HUMAN);
	
				} else if (index == 7) {
					return bookResponse(Subspecies.ALLIGATOR_MORPH);
	
				} else if (index == 8) {
					return bookResponse(Subspecies.BAT_MORPH);
	
				} else if (index == 9) {
					return bookResponse(Subspecies.IMP);
	
				} else if (index == 10) {
					return bookResponse(Subspecies.SLIME);
	
				} else if (index == 11) {
					return bookResponse(Subspecies.RAT_MORPH);
	
				} else if (index == 0) {
					return new Response("Back", "Return to the race index.", LIBRARY);
					
				} else {
					return null;
				}
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	
	};
	
	public static final DialogueNode FIELDS_BOOKS = new DialogueNode("", "", false) {

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
						+ " One shelf looks like the sod is covered in snow, you can feel the cold wafting off of it"
						+ " The books that they hold all seem to be related to the Foloi Fields and the many races found there, which would explain the pastoral scent and appearance of this particular section."
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==2) {
				if (index == 1) {
					return bookResponse(Subspecies.SQUIRREL_MORPH);
	
				} else if (index == 2) {
					return bookResponse(Subspecies.COW_MORPH);
	
				} else if (index == 3) {
					return bookResponse(Subspecies.RABBIT_MORPH);
	
				} else if (index == 4) {
					return bookResponse(Subspecies.FOX_MORPH);
	
				} else if (index == 5) {
					return bookResponse(Subspecies.REINDEER_MORPH);
	
				} else if (index == 0) {
					return new Response("Back", "Return to the race index.", LIBRARY);
					
				} else {
					return null;
				}
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	
	};
	
	private static Response bookResponse(Subspecies subspecies) {
		AbstractItemType book = ItemType.getLoreBook(subspecies);
		
		if(Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspecies)) {
			return new Response(book.getName(false), book.getDescription(), LIBRARY) {
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
