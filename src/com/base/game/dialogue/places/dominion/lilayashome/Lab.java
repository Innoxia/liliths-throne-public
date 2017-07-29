package com.base.game.dialogue.places.dominion.lilayashome;

import com.base.game.character.GameCharacter;
import com.base.game.character.PlayerCharacter;
import com.base.game.character.Quest;
import com.base.game.character.QuestLine;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.npc.dominion.Lilaya;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseSex;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.Rarity;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.WeaponType;
import com.base.game.sex.managers.dominion.lilaya.SMChairBottomLilaya;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;
import com.base.world.WorldType;
import com.base.world.places.LilayasHome;

/**
 * @since 0.1.75
 * @version 0.1.75
 * @author Innoxia
 */
public class Lab {
	
	public static final DialogueNodeOld LAB = new DialogueNodeOld("Lilaya's Laboratory", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getLilaya().hasStatusEffect(StatusEffect.PREGNANT_0)) {
				return "<p>"
							+ "As you approach the door to Lilaya's lab, you notice that it's been firmly pulled shut."
							+ " A little piece of paper has been stuck on it, and you see that Lilaya has left you a handwritten note:"
						+ "</p>"
						+"<p style='text-align:center;'>"
							+ "<i>"
								+ "Hey [pc.name], I thought you could do with taking some time to think about what 'pull out next time you fucking asshole' could possibly mean.</br></br>"
								+ "I'm keeping this door locked until I know if I'm pregnant or not. You'd better hope I'm not."
							+ "</i>"
						+ "</p>"
						+ "<p>"
							+ "Letting out a little sigh, you decide against risking angering her any further, and decide not to knock on the door."
							+ " It looks like you'll just have to wait until she's calmed down."
						+ "</p>";
				
			} else {
				if(Main.game.getDialogueFlags().waitingOnLilayaPregnancyResults) {
					if(Main.game.getLilaya().isVisiblyPregnant()) {
						return "<p>"
									+ "Approaching the door to Lilaya's laboratory, you see that it's been left wide open."
									+ " Quickly stepping inside, you scan the interior for any sign of life."
								+ "</p>"
								+ "<p>"
									+ "On the opposite side of the room, you see Lilaya sitting down in a chair, looking straight at you."
									+ " As soon as your eyes meet, she starts to stand up, but you notice that her movements are slightly unusual."
									+ " Stepping around the desk that she was working at, you see the cause of her clumsy motions taking the form of a clearly swollen belly."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(Yeah, that's right,)] she cries out as she approaches, [lilaya.speech(you ended up getting me pregnant!)]"
								+ "</p>"
								+ "<p>"
									+ "You gulp nervously as you expect her to fly off in another angry fit, but, sensing your unease, Lilaya lets out a resigned sigh before lowering her voice,"
									+ " [lilaya.speech(Don't worry, there's nothing that can be done about it now anyway. Being pregnant sucks, but I might have been a little too harsh on you."
									+ " After all, it's only for a week or so that I've got to carry your kids around.)]"
								+ "</p>"
								+ "<p>"
									+ "As she's been speaking, Lilaya has been gently rubbing her pregnant bump, and as she looks up at you, she notices that you're staring at her belly."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(You want a feel?)] she asks, but before you can respond, she steps forwards and grabs your [pc.arm], before guiding your [pc.hand] down to rest on her stomach."
									+ " Running your [pc.hands+] all over her round tummy, you hear Lilaya let out a happy little moan."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(You know, as inconvenient as being pregnant is, wouldn't it be fun to run some more <i>tests</i> like this? After all, it's not like you need to pull out any more...)]"
									+ " she asks, biting her lip at you."
								+ "</p>";
						
					} else {
						return "<p>"
									+ "Approaching the door to Lilaya's laboratory, you see that it's been left wide open."
									+ " Quickly stepping inside, you scan the interior for any sign of life."
								+ "</p>"
								+ "<p>"
									+ "On the opposite side of the room, you see Lilaya sitting down in a chair, looking straight at you."
									+ " As soon as your eyes meet, she stands up, and with a few quick strides, crosses the room."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(Yeah, that's right,)] she cries out as she approaches, [lilaya.speech(I'm not pregnant!)]"
								+ "</p>"
								+ "<p>"
									+ "You gulp nervously as you expect her to give you another scolding, but, sensing your unease, Lilaya lets out a resigned sigh before lowering her voice,"
									+ " [lilaya.speech(Don't worry, I'm not angry at you any more. Being pregnant sucks, but I might have been a little too harsh on you.)]"
								+ "</p>"
								+ "<p>"
									+ "As she's been speaking, Lilaya has been greedily staring at your body, and as you realise that you're being let off the hook, you start to notice the sultry tone in her voice."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(You know, I'm feeling pretty good right now about not getting pregnant..."
									+ " You want me to run some more <i>tests</i>? Just, promise to pull out this time, ok?)] she asks, biting her lip at you."
								+ "</p>";
					}
					
				} else {
					return "<p>"
								+ "You head down to Lilaya's lab, and as you arrive, you see that the door has been left wide open."
								+ " Stepping forwards into the room, you glance around to see if anyone's here."
							+ "</p>"
							+ "<p>"
								+ "On the far side of the lab, Lilaya is sitting at one of the long tables, with her back to the door."
								+ " Open books and pieces of paper litter the tabletop and floor around her, but she seems far more interested in something else at the moment."
								+ " Rose is straddling her lap, leaning in Lilaya's chest as she whispers down to her demonic mistress."
								+ " You see Lilaya's hand reaching up Rose's skirt, and from the movement of her arm, you can tell that she's fingering her cat-girl maid."
							+ "</p>"
							+ "<p>"
								+ "Rose hooks her arms around Lilaya's neck and leans back, letting out a deep moan as Lilaya's fingers continue working away between her legs."
								+ " She suddenly notices you out of the corner or her eye, and, letting out a little eek, she leaps up onto her feet,"
								+ " [rose.speech(M-Mistress! [pc.Name]'s here!)]"
							+ "</p>"
							+ "<p>"
								+ "Lilaya, upon hearing that, mirrors her maid's movements and leaps to her feet,"
								+ " [lilaya.speech([pc.Name]! E-Erm, w-we were just... Doing an experiment! Yes! Isn't that right Rose?!)]"
							+ "</p>"
							+ "<p>"
								+ "[rose.speech(Yes mistress!)] Rose squeaks, [rose.speech(That's right!)]"
							+ "</p>"
							+ "<p>"
								+ "Brushing down her skirt, Lilaya clears her throat,"
								+ " [lilaya.speech(Ahem! So, what is it you need?)]"
							+ "</p>";
				}
			}
		}
		
		@Override
		public boolean isTravelDisabled() {
			return Main.game.getDialogueFlags().waitingOnLilayaPregnancyResults && !Main.game.getLilaya().hasStatusEffect(StatusEffect.PREGNANT_0);
		}

		@Override
		public Response getResponse(int index) {
			if(Main.game.getLilaya().hasStatusEffect(StatusEffect.PREGNANT_0)) {
				return null;
				
			} else {
				if(Main.game.getDialogueFlags().waitingOnLilayaPregnancyResults) {
					if (index == 1) {
						
						if(Main.game.getLilaya().isVisiblyPregnant()) {
							return new ResponseSex("\"Tests\"",
									"Let Lilaya run her \"tests\" on you.",
									AUNT_HOME_LABORATORY_TESTING_ROMANCE,
									Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_INCEST)),
									null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
									Main.game.getLilaya(), new SMChairBottomLilaya(), Lilaya.AUNT_END_SEX,
										"<p>"
											+ "Stepping forwards, you reach up and take Lilaya's head in your hands, eagerly pressing your lips against hers as you give her a clear response to her question."
											+ " You hear her little bat-like wings fluttering in excitement, and as you carry on kissing the horny half-demon, she starts moaning into your mouth."
										+ "</p>"
										+ "<p>"
											+ "Wrapping her arms around your back, Lilaya clumsily pulls you over towards one of the lab's many chairs."
											+ " You feel her swollen belly rubbing against your stomach as she guides you across the room, and as she pushes you down onto a seat, she slowly sinks down into your lap,"
											+ " continuing her passionate kisses and moans as you both prepare for another round of \"tests\"."
										+ "</p>"){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().hadSexWithLilaya = true;
									Main.game.getDialogueFlags().waitingOnLilayaPregnancyResults = false;
									Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, LilayasHome.LILAYA_HOME_ROOM_ROSE);
								}
							};
							
						} else {
							return new ResponseSex("\"Tests\"",
									"Let Lilaya run her \"tests\" on you.",
									AUNT_HOME_LABORATORY_TESTING_ROMANCE,
									Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_INCEST)),
									null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
									Main.game.getLilaya(), new SMChairBottomLilaya(), Lilaya.AUNT_END_SEX,
										"<p>"
											+ "Stepping forwards, you reach up and take Lilaya's head in your hands, eagerly pressing your lips against hers as you give her a clear response to her question."
											+ " You hear her little bat-like wings fluttering in excitement, and as you carry on kissing the horny half-demon, she starts moaning into your mouth."
										+ "</p>"
										+ "<p>"
											+ "Wrapping her arms around your back, Lilaya pulls you over towards one of the lab's many chairs."
											+ " Spinning you around and pushing you down into the seat, she sits down in your lap, continuing her passionate kisses and moans as you both prepare for another round of \"tests\"."
										+ "</p>"){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().hadSexWithLilaya = true;
									Main.game.getDialogueFlags().waitingOnLilayaPregnancyResults = false;
									Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, LilayasHome.LILAYA_HOME_ROOM_ROSE);
								}
							};
						}
	
					} else if(index == 0) {
							return new Response("Leave", "Tell Lilaya that you don't have time, but you're glad that she's not angry with you any more.", LAB_EXIT){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().waitingOnLilayaPregnancyResults = false;
								}
							};
	
					} else {
						return null;
					}
					
				} else {
					
					if (index == 1) {
						if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_A_LILAYAS_TESTS) {
							return new Response("Tests", "Let Lilaya know that you're here to let her run her tests on you.", AUNT_HOME_LABORATORY_TESTING);
						} else {
							if (Main.game.getDialogueFlags().hadSexWithLilaya) {
								return new Response("\"Tests\"", "Let Lilaya know that you're here to let her run more of her \"tests\" on you.", AUNT_HOME_LABORATORY_TESTING_MORE_SEX);
							} else {
								return new Response("Tests", "Let Lilaya know that you're here to let her run more of her tests on you.", AUNT_HOME_LABORATORY_TESTING_REPEAT);
							}
						}
	
					} else if(index == 2) {
						if(Main.game.getPlayer().hasSideQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
							if(Main.game.getPlayer().getSideQuestProgress(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)==0) {
								if (Main.game.getPlayer().getMainQuestProgress() <= Quest.MAIN_1_A_LILAYAS_TESTS.getSortingOrder()) {
									return new Response("Essences", "You'll need to complete Lilaya's initial tests before you're able to ask her about that strange energy you absorbed.", null);
									
								} else {
									return new Response("Essences", "Ask Lilaya about that strange energy you absorbed.", LILAYA_EXPLAINS_ESSENCES);
								}
								
							} else {
								if(Main.game.getDialogueFlags().essenceExtractionKnown) {
									return new Response("Extract Essences", "Ask Lilaya if you can use her equipment to extract some essences.", ESSENCE_EXTRACTION);
								} else {
									return new Response("Extract Essences", "Ask Lilaya if there's any way to extract essences you've absorbed.", ESSENCE_EXTRACTION);
								}
							}
						}
						return null;
	
					} else if (index == 3) {
						if (Main.game.getPlayer().isVisiblyPregnant()) {
							if (Main.game.getPlayer().getMainQuestProgress() <= Quest.MAIN_1_A_LILAYAS_TESTS.getSortingOrder()) {
								return new Response("Pregnancy", "You'll need to complete Lilaya's initial tests before she'll agree to help you deal with your pregnancy.", null);
								
							} else {
								return new Response("Pregnancy", "Speak to Lilaya about your pregnancy.", LILAYA_ASSISTS_PREGNANCY){
									@Override
									public QuestLine getQuestLine() {
										if (Main.game.getPlayer().hasSideQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY))
											if (Main.game.getPlayer().getSideQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == 0)
												return QuestLine.SIDE_FIRST_TIME_PREGNANCY;
											else
												return null;
										else
											return null;
									}
									@Override
									public void effects() {
										if (!Main.game.getPlayer().hasSideQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY))
											Main.game.getPlayer().incrementQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY);
									}
								};
							}
						} else {
							return null;
						}
						
					} else if(index == 4) {
						if(Main.game.getPlayer().hasSideQuest(QuestLine.SIDE_JINXED_CLOTHING)) {
							if(Main.game.getPlayer().getSideQuestProgress(QuestLine.SIDE_JINXED_CLOTHING)==0) {
								if (Main.game.getPlayer().getMainQuestProgress() <= Quest.MAIN_1_A_LILAYAS_TESTS.getSortingOrder()) {
									return new Response("Jinxed help", "You'll need to complete Lilaya's initial tests before she'll agree to help you deal with your jinxed clothing.", null);
									
								} else {
									return new Response("Jinxed help", "Ask Lilaya for help with removing your jinxed clothing.", LILAYA_ASSISTS_JINXED){
										@Override
										public QuestLine getQuestLine() {
											return QuestLine.SIDE_JINXED_CLOTHING;
										}
									};
								}
							}
						}
						return null;
	
					} else if (index == 6) {
						return new ResponseEffectsOnly("Entrance hall", "Fast travel to the entrance hall."){
							@Override
							public void effects() {
								Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), LilayasHome.LILAYA_HOME_ENTRANCE_HALL, true);
							}
						};
			
					} else {
						return null;
					}
				}
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNodeOld LAB_EXIT = new DialogueNodeOld("Lilaya's Laboratory", "", false) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		
		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in what appears at first to be a huge library."
						+ " The walls are covered in shelving, holding what must be hundreds, if not thousands, of ancient-looking leather-bound tomes."
						+ " Most of the room, however, is occupied by a series of long tables, each covered in strange glass vials, odd-looking instruments, and bottles of glowing liquids."
					+ "</p>"
					+ "<p>"
						+ "Lilaya is hovering close by, ready to answer any questions you might have."
						+ " Her cat-girl maid, Rose, is dutifully standing in one corner of the room, ready to assist with any request her mistress might make."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return LAB.getResponse(index);
		}
	};
	
	public static final DialogueNodeOld LILAYA_EXPLAINS_ESSENCES = new DialogueNodeOld("", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		
		@Override
		public String getContent() {

//			return UtilText.parse("/res/txt/dialogue/places/dominion/aunts_home/explaining_essences.txt", AuntsHome.getContext());
			
			return "<p>"
						+ "[pc.speech(Well, I was wondering if you could help me out. You see, I recently had this weird experience, where I saw someone's arcane aura."
						+ " A piece of it broke off and shot <i>into</i> me, and I'm not sure if it needs to be removed or anything...)] your voice trails off as you see an extremely concerned look flash across Lilaya's face."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Are you sure?! You absorbed someone's essence?!)] Lilaya asks, failing to conceal the worried tone in her voice."
					+ "</p>"
					+ "<p>"
						+ "Rose quickly moves off to a corner of the room as Lilaya approaches you. Grabbing you by the [pc.arm], she quickly pulls you over to the same place where she ran her first test on you."
						+ " Instructing you to sit on the chair in the middle of the chalk square, she once more adjusts the floodlight-like pieces of arcane equipment at each corner."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Wait, I'm not going to lose my clothes or anything again, am I?)] you ask."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(No, don't worry, I fixed that little problem!)] Lilaya responds, and before you get the chance to say anything else, arcane energy suddenly crackles into life around her arm,"
						+ " before shooting out into each of the curious instruments."
						+ " A familiar bright pink flash illuminates the room, but this time, thankfully, your clothes and sight are unaffected."
					+ "</p>"
					+ "<p>"
						+ "Once again, you find yourself surrounded by the shimmering pink outline of your arcane aura."
						+ " This time, however, you see a strange ball of energy orbiting your body, which looks to be the same sort of size as the shard of energy that you remember shooting into you."
						+ " Looking up at Lilaya, you see that her face has completely drained of colour, and she's displaying an extremely worried expression."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("What's wrong?", "Ask Lilaya what's wrong.", LILAYA_EXPLAINS_ESSENCES_2);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LILAYA_EXPLAINS_ESSENCES_2 = new DialogueNodeOld("", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		
		@Override
		public String getContent() {

//			return UtilText.parse("/res/txt/dialogue/places/dominion/aunts_home/explaining_essences_2.txt", AuntsHome.getContext());
			
			return "<p>"
						+"[pc.speech(You look like you've seen a ghost, what's wrong?)] you ask."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Ah, well, it's just...)] Lilaya stumbles over her words a little as she responds, [lilaya.speech(you really, <i>really</i> shouldn't be able to do that!)]"
					+ "</p>"
					+ "<p>"
						+ "Pointing at the ball of energy that's orbiting your aura, she continues,"
						+ " [lilaya.speech(You see that? You've absorbed a piece of someone's essence! There's something seriously wrong with your aura..."
						+ " Normally only Lilin are able to do that, hell, even demons can't absorb essences directly from people!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Wait, I absorbed their <i>essence</i>? What does that mean?)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Well, basically, you absorbed a fragment of someone's aura. It's not harmful to either you or them, so don't worry about that, their aura will replenish itself over time."
						+ " You see, using essences, like the one you've absorbed, is the way enchantments are infused into objects."
						+ " Normally, demons have to buy already-extracted essences in order to absorb them into their aura, but you can <i>somehow</i> absorb them directly from a person, just like a Lilin can!"
						+ " Now that you've absorbed someone's essence, you can focus its energy into enchanting things... Look, it's better if I show you...)]"
					+ "</p>"
					+ "<p>"
						+ "Lilaya flicks a switch on one of the instruments, and the shimmering pink aura instantly vanishes from sight."
						+ " Stepping forwards, she takes you by the [pc.arm] once more, and quickly leads you over to another corner of the lab, where a long, sturdy table is covered in all sorts of alchemical-looking apparatus."
						+ " Large glass bottles filled with brightly coloured liquids bubble away without any apparent source of heat, and all manner of strange looking ingredients lie scattered over the table's surface."
						+ " Coming to a halt in front of the workspace, Lilaya turns to face you, [lilaya.speech(Right, I can explain as we go...)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Enchanting", "Let Lilaya show you how to use your stored essences in order to enchant items.", LILAYA_EXPLAINS_ESSENCES_3);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LILAYA_EXPLAINS_ESSENCES_3 = new DialogueNodeOld("", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		
		@Override
		public String getContent() {
			
//			return UtilText.parse("/res/txt/dialogue/places/dominion/aunts_home/explaining_essences_3.txt", AuntsHome.getContext());
			
			return "<p>"
				+ "[lilaya.speech(Ok, so, first thing to know is that these essences are no longer part of the person you took them from."
				+ " It's not as though you're trapping a part of their soul into enchanted items or anything like that!)] Lilaya starts explaining,"
				+ " [lilaya.speech(So don't have any qualms about using these essences to enchant whatever you want!)]"
			+ "</p>"
			+ "<p>"
				+ "As she speaks, Lilaya reaches down under the table and produces a bottle of Feline's Fancy, putting it down on the desk before turning back towards you."
			+ "</p>"
			+ "<p>"
				+ "[lilaya.speech(Right, well I should probably demonstrate! I still have a couple of cat-morph essences absorbed from some work I was doing earlier, so I might as well use them on this!)]"
				+ " she says, and you notice that her earlier look of worry has been replaced with that of excitement,"
				+ " [lilaya.speech(So, you'll need a particular type of essence to enchant different objects. Seeing as this is a drink for cat-morphs, it reacts only to cat-morph essences.)]"
			+ "</p>"
			+ "<p>"
				+ "[lilaya.speech(So, you don't need any special equipment for this, all you need is an item to enchant, and the concentration to draw the essence out from your aura,)]"
				+ " Lilaya continues to explain as she picks up the bottle of Feline's Fancy, before placing a curious little green gem down next to it,"
				+ " [lilaya.speech(So, you have your base ingredient, and that's all well and good, but you can also add a couple of elements."
				+ " Depending on what elements you choose to add, the resulting enchanted item will have different effects."
				+ " So, once you've decided on what ingredient and elements you're going to use, you just need to focus on combining them...)]"
			+ "</p>"
			+ "<p>"
				+ "Lilaya closes her eyes and lets out a deep breath, and you gasp as you suddenly see four little balls of pink energy orbiting her body."
				+ " As you watch, two of the orbs suddenly shoot down into bottle of Feline's Fancy, and with a bright pink flash and a wisp of odourless smoke, both the bottle and the little green gem disappear."
				+ " In its place, a curious little bottle of red liquid has materialised out of thin-air."
			+ "</p>"
			+ "<p>"
				+ "[lilaya.speech(So, you have to sort of feel how much essence you want to pour into the enchantment. The more you put in, the better the resulting item!)]"
				+ " Lilaya picks up the little potion and places it off to one side, [lilaya.speech(And that's all there is to it!)]"
			+ "</p>";
			
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Thank her", "Thank Lilaya for showing you how to enchant items.", LAB_EXIT){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[pc.speech(Thanks Lilaya, I understand now, but it still seems a little strange.)]"
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(The only strange thing about all this is how you're able to absorb essences like a Lilin,)]"
									+ " Lilaya responds,"
									+ " [lilaya.speech(but anyway, I've already got that arcane observation device set up in your room, so hopefully the test results will give me some idea of what's happening.)]"
								+ "</p>"
								+ "<p>"
									+ "[pc.speech(Yeah, hopefully, thanks again Lilaya.)]"
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(You're welcome!)] she answers, before stepping away and giving you some time to think."
								+ "</p>"
								+ "<p>"
									+ "<i>Innoxia's note: The enchanting mechanics are still a work in progress."
									+ " I plan on making the elements collectible items, as well as providing the functionality to enchant clothing!</i>" //TODO
								+ "</p>"
								+ Main.game.getPlayer().incrementQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY));
						
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ESSENCE_EXTRACTION = new DialogueNodeOld("Arcane Arts", "-", true, false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			StringBuilder headerSB = new StringBuilder();
			
			headerSB.append(
					"<p style='text-align:center;'>"
						+ "<b>Your essences:</b>"
					+ "</p>"
					+"<div style='text-align: center; display:block; margin:0 auto; height:48px; padding:8px 0 8px 0;'>");
			for(TFEssence essence : TFEssence.values()) {
				headerSB.append(
						"<div style='width:28px; display:inline-block; margin:0 4px 0 4px;'>"
							+ "<div class='item-inline"
										+ (essence.getRarity() == Rarity.COMMON ? " common" : "")
										+ (essence.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
										+ (essence.getRarity() == Rarity.RARE ? " rare" : "")
										+ (essence.getRarity() == Rarity.EPIC ? " epic" : "")
										+ (essence.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
										+ (essence.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
								+ essence.getSVGString()
								+ "<div class='overlay no-pointer' id='ESSENCE_"+essence.hashCode()+"'></div>"
							+ "</div>"
							+ " <div style='display:inline-block; height:20px; vertical-align: middle;'>"
								+ "<b>"+Main.game.getPlayer().getEssenceCount(essence)+"</b>"
							+ "</div>"
						+ "</div>"
						);
			}
			headerSB.append("</div>");
			
			return headerSB.toString();
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().essenceExtractionKnown) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Turning towards Lilaya, you get her attention before asking, [pc.speech(Is it ok if I extract some more essences?)]"
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(Of course!)] she responds, [lilaya.speech(Use as many vials as you'd like!)]"
						+ "</p>"
						+ "<p>"
							+ "Reaching down to pull a few of the little glass containers out from their storage, you wonder which, and how many, of your essences to extract..."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[pc.speech(You mentioned that demons can't absorb essences directly from people, so how do they do it?)]"
							+ " you ask,"
							+ " [pc.speech(Is there some way to extract the essences I've absorbed?)]"
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(Yes, actually, there is!)] Lilaya replies, beckoning you over to one of the many tables in her lab,"
							+ " [lilaya.speech(It's actually one of the easiest things about essence manipulation!)]"
						+ "</p>"
						+ "<p>"
							+ "Bending down, Lilaya pulls out a heavy cardboard box, and you hear the high-pitched tinkling sound of countless little glass vials as they knock against each other."
							+ " Picking one out of the box, Lilaya stands back up and holds it out towards you."
							+ " It's no larger than one of your fingers, and as you take it from Lilaya's outstretched hand, you see that there's a little cork stopper wedged into the top."
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(These are how ordinary arcane users, like me, absorb essences."
								+ " This one's empty at the moment, so if you take the cork out and concentrate on the sort of essence you want to extract from your aura, its enchantment will do the rest!"
								+ " One of your essences will be extracted into this vial, which then allows other people to uncork it and absorb that essence!)]"
						+ "</p>"
						+ "<p>"
							+ "Turning the little glass container over in your [pc.hands], you look up at Lilaya,"
							+ " [pc.speech(So all essences that 'ordinary' arcane users have absorbed come from little vials like this?)]"
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(That's right,)] Lilaya responds, [lilaya.speech(well, they come from a Lilin originally, then are extracted into these vials and sold on!"
								+ " The empty containers are next to worthless, so if you wanted to extract some essences, please feel free to use as many as you'd like!)]"
						+ "</p>"
						+ "<p>"
							+ "You thank Lilaya, and after she moves away to give you a little space, you start to wonder which, and how many, of your essences to extract..."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			
			if (index == 0) {
				return new Response("Back", "Stop extracting essences.", LAB_EXIT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().essenceExtractionKnown = true;
					}
				};

			} else if(index<=TFEssence.values().length) {
				if(Main.game.getPlayer().getEssenceCount(TFEssence.values()[index-1]) > 0) {
					return new Response(TFEssence.values()[index-1].getName(), "Decide to extract some of your "+TFEssence.values()[index-1].getName()+" essences.", ESSENCE_EXTRACTION_BOTTLING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().focusedEssence = TFEssence.values()[index-1];
							Main.game.getDialogueFlags().essenceExtractionKnown = true;
						}
					};
				} else {
					return new Response(TFEssence.values()[index-1].getName(), "You don't have any of these essences to extract!", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ESSENCE_EXTRACTION_BOTTLING = new DialogueNodeOld("Arcane Arts", "-", true, false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			StringBuilder headerSB = new StringBuilder();
			
			headerSB.append(
					"<p style='text-align:center;'>"
						+ "<b>Your essences:</b>"
					+ "</p>"
					+"<div style='text-align: center; display:block; margin:0 auto; height:48px; padding:8px 0 8px 0;'>");
			for(TFEssence essence : TFEssence.values()) {
				headerSB.append(
						"<div style='width:28px; display:inline-block; margin:0 4px 0 4px;'>"
							+ "<div class='item-inline"
										+ (essence.getRarity() == Rarity.COMMON ? " common" : "")
										+ (essence.getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
										+ (essence.getRarity() == Rarity.RARE ? " rare" : "")
										+ (essence.getRarity() == Rarity.EPIC ? " epic" : "")
										+ (essence.getRarity() == Rarity.LEGENDARY ? " legendary" : "")
										+ (essence.getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
								+ essence.getSVGString()
								+ "<div class='overlay no-pointer' id='ESSENCE_"+essence.hashCode()+"'></div>"
							+ "</div>"
							+ " <div style='display:inline-block; height:20px; vertical-align: middle;'>"
								+ "<b>"+Main.game.getPlayer().getEssenceCount(essence)+"</b>"
							+ "</div>"
						+ "</div>"
						);
			}
			headerSB.append("</div>");
			
			return headerSB.toString();
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Holding one of the little glass vials in your [pc.hand], you try to concentrate on the "+Main.game.getDialogueFlags().focusedEssence.getName()+" essences that are absorbed into your arcane aura..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "You currently have <b>"+Main.game.getPlayer().getEssenceCount(Main.game.getDialogueFlags().focusedEssence)
							+"</b> <b style='color:"+Main.game.getDialogueFlags().focusedEssence.getColour().toWebHexString()+";'>"+Main.game.getDialogueFlags().focusedEssence.getName()+"</b> essences."
					+ "</p>");
			
			if(Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(ItemType.generateItem(TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence)))) {
				UtilText.nodeContentSB.append(
						"<p style='text-align:center;'>"
							+ "<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You don't have any space left in your inventory, so you can't extract essences right now!</b>"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			
			if(index == 1) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(ItemType.generateItem(TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence))))) {
					if(Main.game.getPlayer().getEssenceCount(Main.game.getDialogueFlags().focusedEssence)>=1) {
						return new Response("Extract (1)", "Extract one of your "+Main.game.getDialogueFlags().focusedEssence.getName()+" essences.", ESSENCE_EXTRACTION_BOTTLING) {
							@Override
							public void effects() {
								Main.game.getPlayer().addItem(ItemType.generateItem(TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence)), false);
								
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "You pull out the little cork from the top of one of the glass vials, and, closing your [pc.eyes], you concentrate on extracting the "+Main.game.getDialogueFlags().focusedEssence.getName()+" essence."
											+ " Suddenly, you feel a strange little tugging sensation, and, opening your [pc.eyes], you see a "+Main.game.getDialogueFlags().focusedEssence.getColour().getName()+", swirling glow within the vial in your [pc.hand]."
											+ " Pushing the cork stopper back in the top, you smile as you realise that you've successfully extracted an essence; something that only a Lilin should be able to do!"
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Item added to inventory:</b> <b>" + (TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence)).getDisplayName(true) + "</b>"
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(Main.game.getDialogueFlags().focusedEssence, -1);
							}
						};
						
					} else {
						return new Response("Extract (1)", "You don't have any "+Main.game.getDialogueFlags().focusedEssence.getName()+" essences!", null);
					}
				} else {
					return new Response("Extract (1)", "You don't have any free space in your inventory!", null);
				}
				
				
			} else if(index == 2) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(ItemType.generateItem(TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence))))) {
					if(Main.game.getPlayer().getEssenceCount(Main.game.getDialogueFlags().focusedEssence)>=5) {
						return new Response("Extract (5)", "Extract five of your "+Main.game.getDialogueFlags().focusedEssence.getName()+" essences.", ESSENCE_EXTRACTION_BOTTLING) {
							@Override
							public void effects() {
								for(int i =0; i<5; i++) {
									Main.game.getPlayer().addItem(ItemType.generateItem(TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence)), false);
								}
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "You pull out the little cork from the top of one of the glass vials, and, closing your [pc.eyes], you concentrate on extracting the "+Main.game.getDialogueFlags().focusedEssence.getName()+" essence."
											+ " Suddenly, you feel a strange little tugging sensation, and, opening your [pc.eyes], you see a "+Main.game.getDialogueFlags().focusedEssence.getColour().getName()+", swirling glow within the vial in your [pc.hand]."
											+ " Pushing the cork stopper back in the top, you smile as you realise that you've successfully extracted an essence; something that only a Lilin should be able to do!"
										+ "</p>"
										+ "<p>"
											+ "Grabbing another vial, you set about repeating the process several times..."
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Items added to inventory:</b> <b>5x</b> <b>" + TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence).getDisplayName(true) + "</b>"
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(Main.game.getDialogueFlags().focusedEssence, -5);
							}
						};
						
					} else {
						return new Response("Extract (5)", "You don't have enough "+Main.game.getDialogueFlags().focusedEssence.getName()+" essences!", null);
					}
				} else {
					return new Response("Extract (5)", "You don't have any free space in your inventory!", null);
				}
				
			} else if(index == 3) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(ItemType.generateItem(TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence))))) {
					if(Main.game.getPlayer().getEssenceCount(Main.game.getDialogueFlags().focusedEssence)>=25) {
						return new Response("Extract (25)", "Extract twenty-five of your "+Main.game.getDialogueFlags().focusedEssence.getName()+" essences.", ESSENCE_EXTRACTION_BOTTLING) {
							@Override
							public void effects() {
								for(int i =0; i<25; i++) {
									Main.game.getPlayer().addItem(ItemType.generateItem(TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence)), false);
								}
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "You pull out the little cork from the top of one of the glass vials, and, closing your [pc.eyes], you concentrate on extracting the "+Main.game.getDialogueFlags().focusedEssence.getName()+" essence."
											+ " Suddenly, you feel a strange little tugging sensation, and, opening your [pc.eyes], you see a "+Main.game.getDialogueFlags().focusedEssence.getColour().getName()+", swirling glow within the vial in your [pc.hand]."
											+ " Pushing the cork stopper back in the top, you smile as you realise that you've successfully extracted an essence; something that only a Lilin should be able to do!"
										+ "</p>"
										+ "<p>"
											+ "Grabbing another vial, you set about repeating the process several times..."
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Items added to inventory:</b> <b>25x</b> <b>" + TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence).getDisplayName(true) + "</b>"
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(Main.game.getDialogueFlags().focusedEssence, -25);
							}
						};
						
					} else {
						return new Response("Extract (25)", "You don't have enough "+Main.game.getDialogueFlags().focusedEssence.getName()+" essences!", null);
					}
				} else {
					return new Response("Extract (25)", "You don't have any free space in your inventory!", null);
				}
				
			} else if(index == 4) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(ItemType.generateItem(TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence))))) {
					if(Main.game.getPlayer().getEssenceCount(Main.game.getDialogueFlags().focusedEssence)>=1) {
						return new Response("Extract (all)", "Extract all of your "+Main.game.getDialogueFlags().focusedEssence.getName()+" essences.", ESSENCE_EXTRACTION_BOTTLING) {
							@Override
							public void effects() {
								for(int i =0; i<Main.game.getPlayer().getEssenceCount(Main.game.getDialogueFlags().focusedEssence); i++) {
									Main.game.getPlayer().addItem(ItemType.generateItem(TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence)), false);
								}
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "You pull out the little cork from the top of one of the glass vials, and, closing your [pc.eyes], you concentrate on extracting the "+Main.game.getDialogueFlags().focusedEssence.getName()+" essence."
											+ " Suddenly, you feel a strange little tugging sensation, and, opening your [pc.eyes], you see a "+Main.game.getDialogueFlags().focusedEssence.getColour().getName()+", swirling glow within the vial in your [pc.hand]."
											+ " Pushing the cork stopper back in the top, you smile as you realise that you've successfully extracted an essence; something that only a Lilin should be able to do!"
										+ "</p>"
										+ "<p>"
											+ "Grabbing another vial, you set about repeating the process several times..."
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Items added to inventory:</b> <b>"+Main.game.getPlayer().getEssenceCount(Main.game.getDialogueFlags().focusedEssence)+"x</b> <b>"
												+ TFEssence.essenceToItem(Main.game.getDialogueFlags().focusedEssence).getDisplayName(true) + "</b>"
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(Main.game.getDialogueFlags().focusedEssence, -Main.game.getPlayer().getEssenceCount(Main.game.getDialogueFlags().focusedEssence));
								
							}
						};
						
					} else {
						return new Response("Extract (all)", "You don't have any "+Main.game.getDialogueFlags().focusedEssence.getName()+" essences!", null);
					}
				} else {
					return new Response("Extract (all)", "You don't have any free space in your inventory!", null);
				}
				
			} else if (index == 0) {
				return new Response("Back", "Return to the essence choice menu.", ESSENCE_EXTRACTION);

			} else {
				return null;
			}
		}
	};
	
	
	
	
	
	
	//----------------------------------

	public static final DialogueNodeOld AUNT_HOME_LABORATORY_TESTING = new DialogueNodeOld("", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		
		@Override
		public String getContent() {
//			return UtilText.parse("/res/txt/dialogue/places/dominion/aunts_home/lab_testing.txt", AuntsHome.getContext());
			
			return "<p>"
						+ "Stepping forwards, you smile at your demonic aunt,"
						+ " [pc.speech(Hi Lilaya, I'm here for those tests you mentioned.)]"
					+ "</p>"
					+ (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToPregnancyLilaya
							? "<p>"
									+ "[lilaya.speech(Excellent! Let's just... Wait... Are you <i>pregnant</i>?!"
									+ " Well, nevermind, I can help you deal with that later! For now, let's just get these tests done, come over here!)]"
								+ "</p>"
							: "<p>"
									+ "[lilaya.speech(Excellent! Let's get these tests done then, come over here!)]"
								+ "</p>")
					+ "<p>"
						+ "You do as she instructs, and make your way over to where Lilaya's standing."
						+ " Rose quickly moves out of the way, hurrying over to a corner of the room and blushing as you say hello."
						+ " Lilaya motions for you to sit down in the chair she just vacated, and as you do so, she grabs a smooth, wand-like instrument from another table."
						+ " Moving over to you, the wand suddenly starts giving off a faint pink glow, and she starts swiping it over your body, humming as she does so."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Hmmm... Yes... That's what I thought...)]"
						+ " she says, but before you have a chance to ask her what's going on, she steps back and places the wand to one side, "
						+ "[lilaya.speech(Your arcane aura hasn't shown any signs of degradation at all."
							+ " It looks like it's permanent. If you've been out in Dominion, maybe you've noticed by now, but with an aura that strong, you have the same effect on people that a demon would.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(You mean...)] you start, but Lilaya cuts you off."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Yes, I mean that anyone you encounter is going to feel extremely aroused when in your presence,)]"
						+ " she explains, "
						+ "[lilaya.speech(and if you haven't figured it out yet, that's the main power behind the arcane."
							+ " Sure, it can be harnessed to create all sorts of spells and enchantments, but at its core, it's a primitive force that feeds off people's sexual energy.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(So everyone I meet is going to want to have sex with me?)] you ask."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Sort of, but remember that they have their own, less powerful, auras that reduce the potency of yours."
							+ " If they become too weak, whether mentally or physically, they won't be able to control their aura any more, and that's when all they'll be able to think of is... well... A-anyway, the same applies right back at you!)]"
						+ " she says, starting to blush a little."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(So if someone beats me up, my own arcane aura is going to make me want to have sex with them?!)] you ask."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Well, you'll still be able to summon some resistance, but it's unlikely that you'll be able to put up much of a fight at that stage..."
							+ " Oh, and it'll also happen if you cast too many spells and get mentally fatigued!"
							+ " Anyway, enough of that for now, I need to tell you what I've discovered!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Returning home", "Ask Lilaya if she's found a way to send you back home.", AUNT_HOME_LABORATORY_TESTING_ARTHUR){
					@Override
					public QuestLine getQuestLine() {
						if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_A_LILAYAS_TESTS)
							return QuestLine.MAIN;
						else
							return null;
					}

					@Override
					public void effects() {
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToPregnancyLilaya)
							Main.game.getDialogueFlags().reactedToPregnancyLilaya = true;
					}
				};

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld AUNT_HOME_LABORATORY_TESTING_REPEAT = new DialogueNodeOld("", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			
//			return UtilText.parse("/res/txt/dialogue/places/dominion/aunts_home/lab_testing_repeat.txt", AuntsHome.getContext());

			return "<p>"
						+ "Stepping forwards, you smile at your demonic aunt,"
						+ " [pc.speech(Hi Lilaya, I'm here for some more of those tests...)]"
					+ "</p>"
					+ (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToPregnancyLilaya
							? "<p>"
									+ "[lilaya.speech(Excellent! Let's just... Wait... Are you <i>pregnant</i>?!"
									+ " Well, if you want help with that, all you have to do is ask! For now, let's just get these... Erm... <i>tests</i> done, come over here!)]"
								+ "</p>"
							: "<p>"
									+ "[lilaya.speech(Excellent! Let's get these... Erm... <i>tests</i> done then, come over here!)]"
								+ "</p>")
			+ "<p>"
				+ "Rose quickly moves out of the way, hurrying over to a corner of the room and blushing as you say hello."
				+ " Lilaya motions for you to come and sit down in the chair she just vacated."
			+ "</p>";
			
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Sit down", "You know exactly why Lilaya seems embarrassed about these 'tests'...", AUNT_HOME_LABORATORY_TESTING_ROMANCE){
					@Override
					public void effects() {
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToPregnancyLilaya)
							Main.game.getDialogueFlags().reactedToPregnancyLilaya = true;
					}
				};

			} else if (index == 2) {
				return new Response("Decline", "Tell Lilaya that you've changed your mind. While she'll probably be a little disappointed, you can always come back later to take up her offer if you should change your mind.", LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You make your excuses to Lilaya, and, while she looks a little sad that you're not going to stay with her, she doesn't openly object."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(If you change your mind, just let me know!)] she says, before backing off a little to give you some space."
								+ "</p>");
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToPregnancyLilaya) {
							Main.game.getDialogueFlags().reactedToPregnancyLilaya = true;
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld AUNT_HOME_LABORATORY_TESTING_MORE_SEX = new DialogueNodeOld("", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Stepping forwards, you smile at your demonic aunt,"
						+ " [pc.speech(Hi Lilaya, I was wondering if you wanted to run some more of those... <i>tests</i>?)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Ah! Y-Yes! I was hoping for some more of those...)] she responds, fiercely blushing."
					+ "</p>"
					+ "<p>"
						+ "Rose, understanding what's about to happen, quickly makes her exit, and hurries out of the lab."
						+ " Lilaya motions for you to come and sit down in the chair she just vacated, smiling seductively as you do as she commands."
					+ "</p>"
					+ "<p>"
						+ "You look up into her lustful [lilaya.eyes+], and before you can say anything, she takes your head in both hands and presses her lips against yours."
						+ " Her tongue slips out of her mouth, and you part your lips as you allow it to slide into yours."
						+ " Reaching up to pull her close, you passionately start making out with one another as you both give in to your lust."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"Start having sex with Lilaya.", AUNT_HOME_LABORATORY_TESTING_ROMANCE,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_INCEST)),
						null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
						Main.game.getLilaya(), new SMChairBottomLilaya(), Lilaya.AUNT_END_SEX,
						"<p>"
								+ "You briefly wonder if it's your aura that's making Lilaya so horny, but whatever it is, you're feeling the same effects."
								+ " You've never wanted someone as badly as you want her right now, and you feel your heart pounding as her soft, delicate fingers stroke over your lips."
								+ " Before you can make a move, Lilaya straightens up behind you and takes the initiative."
								+ " Stepping around to one side, she quickly throws one leg over you and slides down to sit in your lap, face-to-face."
								+ "</p>"

								+ "<p>"
								+ "You look up at her "
								+ Main.game.getLilaya().getEyeColour().getName()
								+ " snake-like eyes, and she gives you a seductive smile before taking your head"
								+ " in both hands and pressing her lips against yours."
								+ " Her tongue slips out of her mouth, and, parting your lips, you allow it to slide into yours."
								+ " You reach up and pull her close, passionately making out with one another as you both give in to your lust."
								+ "</p>"){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().hadSexWithLilaya = true;
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, LilayasHome.LILAYA_HOME_ROOM_ROSE);
					}
				};

			} else if (index == 2) {
				return new Response("Stop", "Tell Lilaya that you need to get going. While she'll definitely be disappointed that you're stopping so soon, you can always come back later if you should change your mind.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Prying her off of you, you make your excuses to Lilaya as you get up out of the chair."
									+ " While she's quite clearly upset that you're not going to let her have some fun, she doesn't openly object."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(If you change your mind, just let me know!)] she says, before backing off a little to give you some space."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AUNT_HOME_LABORATORY_TESTING_ARTHUR = new DialogueNodeOld("", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ UtilText.parsePlayerSpeech("Have you found a way to get me back home then?")
					+ " you ask."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Sorry, but no. That's what I needed to talk to you about,", Main.game.getLilaya())
					+ " Lilaya answers, "
					+ UtilText.parseSpeech("The truth is, even though I'm considered one of the top experts in all things related to the arcane, I have no idea how you've ended up here.", Main.game.getLilaya())
					+ "</p>"

					+ "<p>"
					+ "You can't help but feel completely deflated."
					+ " Although Lilaya's home isn't exactly the worst place to be living, this whole world still makes you feel uneasy."
					+ " You were rather hoping that Lilaya could have found some way to send you back to where you belong, but as she delivers this bad news, you collapse back in your chair and let out a deep sigh."
					+ "</p>"

					+ "<p>"
					+ "Seeing your reaction, Lilaya sighs before offering you a sliver of hope, "
					+ UtilText.parseSpeech("Well... I suppose there is <i>one</i> way we could find out more... but it means I'd have to talk to <i>him</i> again."
							+ " There's no way I'm going over there to ask for his help though!", Main.game.getLilaya())
					+ "</p>"

					+ "<p>"
					+ "You look up to see Lilaya's face turned up into a scowl as she continues, "
					+ UtilText.parseSpeech("If you ever want to find a way to go back home, you're going to have to get the help of Arthur... <i>that bastard!</i>", Main.game.getLilaya())
					+ " she spits, before realising how angry she's become. Lilaya closes her eyes for a moment and takes a deep breath, calming herself down before continuing, "
					+ UtilText.parseSpeech("He and I used to work together, and with the exception of Lilith herself, he probably understands more about the arcane than anyone currently living."
							+ " His apartment's over in Demon Home, but despite the name, people of all races live there. He's actually just a human, which makes his knowledge of the arcane all the more impressive."
							+ " Here's his address... So if you want his help, you're going to have to convince him to stop being such a <i>selfish bastard!</i> He'll need access to my research,"
							+ " and the only way he's going to get it is if he comes crawling to my front door, begging for my forgiveness.", Main.game.getLilaya())
					+ "</p>"

					+ "<p>"
					+ "You're quite taken aback at Lilaya's apparent hatred for Arthur."
					+ " You assume, seeing as he shares the same name, that he's this world's version of your aunt Lily's colleague from the museum."
					+ " Back there, they were close friends, and you wonder what's happened in this reality to cause Lilaya to dislike him so much."
					+ " Seeing how angry she's gotten just at the thought of him, you decide that it would be wise not to press the matter further, and instead you just agree to convince Arthur to apologise."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Good, good... Well, anyway, umm, I guess we're done here,", Main.game.getLilaya())
					+ " Lilaya says, but as you start to stand up, she nervously picks up the little wand-like"
					+ " instrument she used to test you earlier, and continues, "
					+ UtilText.parseSpeech("W-Wait, erm, I mean, t-there's some more tests I could... try on you...", Main.game.getLilaya())
					+ "</p>"

					+ "<p>"
					+ "Lilaya seems to have become quite embarrassed by something, and you find this sudden offer of 'more tests' to be pretty suspicious."
					+ " She's gripping the wand quite tightly now, and you notice her eyes keep glancing down at your body."
					+ " You're almost certain that she's just making an excuse to get you to stay here, and the sudden thought that she's trying to hit on you flashes across your mind."
					+ "</p>"

					+ "<p>"
					+ UtilText.parsePlayerThought("There's no way, she's my aunt! But then again, I suppose she doesn't see herself as that...")
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("\"Tests\"",
						"Accept Lilaya's offer of more 'tests'. You're not sure what her intentions really are, but you're confident that you'll be able to stop her if she tries any funny business.", AUNT_HOME_LABORATORY_TESTING_ROMANCE);

			} else if (index == 2) {
				return new Response("Decline", "Tell Lilaya that you're not up for this sort of thing. While she'll probably be a little disappointed, you can always come back later to take up her offer if you should change your mind.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You make your excuses to Lilaya, and, while she looks a little sad that you're not going to stay with her, she doesn't openly object."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(If you change your mind, just let me know!)] she says, before backing off a little to give you some space."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AUNT_HOME_LABORATORY_TESTING_ROMANCE = new DialogueNodeOld("", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ UtilText.parsePlayerSpeech("Well, if there are more tests you need to run, then sure,")
					+ " you say, sitting down next to Lilaya."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Excellent! Now, let me just start up here...", Main.game.getLilaya())
					+ " she exclaims, bouncing over to you, wand in hand."
					+ "</p>"

					+ "<p>"
					+ "You sit still as Lilaya steps in front of you, holding the wand out to run it over your head."
					+ " She places one hand on your shoulder and leans down, bringing her large breasts down to your eye-level."
					+ " You can't help but notice that the top few buttons of her shirt are undone, and you cast a glance at the impressive cleavage that's suddenly on display."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("I hope you aren't doing anything naughty...", Main.game.getLilaya())
					+ " Lilaya whispers in your ear, before standing back up and moving round behind you."
					+ "</p>"

					+ "<p>"
					+ UtilText.parsePlayerSpeech("What?! No, I-")
					+ " you start, but Lilaya cuts you off as she reaches round and puts a finger on your lips."
					+ "</p>"

					+ "<p>"
					+ "You blush as you feel her free hand press the wand against your inner-thigh, and, still holding her finger over your mouth, she starts to slide the arcane instrument up your body."
					+ " You look down as you feel the wand travel up and over your chest, and Lilaya reacts to your head's movement by trying to press her finger into your mouth."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Open your mouth",
						"Let Lilaya push her finger into your mouth. After all, maybe this is just part of the test?", AUNT_HOME_LABORATORY_TESTING_ROMANCE_NEXT_STEP);

			} else if (index == 2) {
				return new Response("Stop this",
						"Stand up and tell Lilaya that this is going too far. While she'll undoubtedly be upset at this sudden end to her advances, you're sure that she'd try to hit on you again if you changed your mind in the future.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You quickly stand up and make your excuses to Lilaya, and, while she looks a little sad that you're putting a stop to her advances, she doesn't openly object."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(If you change your mind, just let me know!)] she says, before backing off a little to give you some space."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AUNT_HOME_LABORATORY_TESTING_ROMANCE_NEXT_STEP = new DialogueNodeOld("", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You part your lips slightly, allowing Lilaya to slip her finger into your mouth."
						+ " As she slowly starts sliding her digit back and forth past your lips, you notice a faint, salty taste hitting your tongue."
						+ " As a second finger pushes its way in to join the first, you realise that this is the same hand she was using to finger Rose."
						+ " Before you can object, you suddenly notice that she's slipped her free hand down to your groin, and as she gives you an experimental squeeze, you find yourself bucking back against her touch."
					+ "</p>"

					+ "<p>"
					+ (Main.game.getPlayer().isFeminine() ? UtilText.parseSpeech("Good girl...", Main.game.getLilaya()) : UtilText.parseSpeech("Good boy...", Main.game.getLilaya()))
					+ " she whispers in your ear, "
					+ UtilText.parseSpeech("And just to think, I was worried that you wouldn't like this...", Main.game.getLilaya())
					+ "</p>"

					+ "<p>"
					+ "You've become so caught-up in the moment that you haven't even considered putting an end to all this, but as Lilaya draws her fingers from your mouth, you have a sudden moment of clarity."
					+ "</p>"

					+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST) ? "<p>"
							+ UtilText.parsePlayerThought("Fuck this is hot... I always wanted to do this to aunt Lily!")
							+ "</p>"
							: "<p>"
									+ UtilText.parsePlayerThought("What am I doing?! She looks exactly like aunt Lily!")
									+ "</p>")

					+ "<p>"
					+ "At that moment, Lilaya leans in to whisper one last thing in your ear, "
					+ UtilText.parseSpeech("I don't suppose you've ever seen a demon's pussy before, have you?", Main.game.getLilaya())
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseSex("Let it happen",
						"You know that this can only end one way. Although Lilaya reminds you of your aunt Lily, you always did have a crush on her...", AUNT_HOME_LABORATORY_TESTING_ROMANCE,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_INCEST)), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
						Main.game.getLilaya(), new SMChairBottomLilaya(), Lilaya.AUNT_END_SEX,
						"<p>"
								+ "You briefly wonder if it's your aura that's making Lilaya so horny, but whatever it is, you're feeling the same effects."
								+ " You've never wanted someone as badly as you want her right now, and you feel your heart pounding as her soft, delicate fingers stroke over your lips."
								+ " Before you can make a move, Lilaya straightens up behind you and takes the initiative."
								+ " Stepping around to one side, she quickly throws one leg over you and slides down to sit in your lap, face-to-face."
								+ "</p>"

								+ "<p>"
								+ "You look up at her "
								+ Main.game.getLilaya().getEyeColour().getName()
								+ " snake-like eyes, and she gives you a seductive smile before taking your head"
								+ " in both hands and pressing her lips against yours."
								+ " Her tongue slips out of her mouth, and, parting your lips, you allow it to slide into yours."
								+ " You reach up and pull her close, passionately making out with one another as you both give in to your lust."
								+ "</p>"){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().hadSexWithLilaya = true;
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, LilayasHome.LILAYA_HOME_ROOM_ROSE);
					}
				};

			} else if (index == 2) {
				return new Response("Stop this",
						"Stand up and tell Lilaya that this has gone too far. While she'll undoubtedly be upset at this sudden end, you're sure that she'd try to hit on you again if you changed your mind in the future.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You quickly stand up and make your excuses to Lilaya, and, while she looks a little sad that you're stopping just as things were about to get interesting, she doesn't openly object."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(If you change your mind, just let me know!)] she says, before backing off a little to give you some space."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};

	private static AbstractClothing jinxedClothing = null;
	private static AbstractClothing getJinxedClothingExample() {
		if(jinxedClothing==null) {
			for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
				if(c.isSealed()) {
					jinxedClothing = c;
					return c;
				}
			}
		} else {
			return jinxedClothing;
		}
		return null;
	}

	public static final DialogueNodeOld LILAYA_ASSISTS_JINXED = new DialogueNodeOld("", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
				return "<p>"
							+ "[pc.speech(There seems to be something wrong with "+(getJinxedClothingExample().getClothingType().isPlural()?"these":"this")+" "+getJinxedClothingExample().getName()+" I'm wearing,)]"
							+ " you explain, "
							+ "[pc.speech(Every time I try to take "+(getJinxedClothingExample().getClothingType().isPlural()?"them":"it")+" off, some kind of weird enchantment stops me. Can you help?)]"
						+ "</p>"
						+ "<p>"
							+ "Lilaya frowns at you for a moment, then, much to your surprise, she starts scolding you, "
							+ "[lilaya.speech(Well, what did you expect to happen if you're going to start trying on random pieces of clothing like that?!)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Well, it's not really my fau-)] you start, but Lilaya quickly interrupts you."
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(Well then, whose fault is it? Hmm? I can't be running around saving you every time you get in trouble, understood?!)]"
						+ "</p>"
						+ "<p>"
							+ "If it were anyone else, you might have argued back, but Lilaya's stern words sound exactly like the sort of lecture you used to receive from your aunt Lily."
							+ " That, combined with the fact that this is a rare display of frustration from Lilaya, leaves you muttering out an apology in the hope that you can calm her down."
							+ " Thankfully, she lets out one last exasperated sigh before taking in a deep breath and speaking in her normal tone."
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(Sorry, I didn't mean to be so stern, it's just that I don't want you to get hurt out there..."
									+ " Anyway, the solution to your little clothing problem is actually quite simple."
									+ " First, you'll need some sort of enchanted weapon. Here, I've got another demonstone lying around, you can take this one,)]"
							+ " Lilaya steps over to one of the several cluttered tables in the lab, quickly retrieving a demonstone and thrusting it into your hands before continuing,"
							+ " [lilaya.speech(Now, much like absorbing it into your body, you'll need to concentrate on letting its energy flow into the jinxed clothing."
									+ " The stored energy in an enchanted weapon will remove the jinx, however, the weapon will be destroyed in the process!"
									+ " Feel free to destroy that demonstone I gave you, it's just a common variety anyway.)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Thanks Lilaya, I really appreciate the help!)] you say, smiling at her."
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(You're welcome! And please, do try to be careful out there!)] Lilaya responds, before moving off a little to give you a chance to remove your jinxed clothing."
						+ "</p>"
						+ "<p>"
							+ "<i>You can now remove jinxes from clothing!"
							+ " To do so, open your inventory, click on a non-equipped weapon, and choose the 'Remove jinx' option."
							+ " <b>Remember</b>, removing a jinx will destroy the weapon used!</i>"
						+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Now to see about removing this jinxed clothing...", LAB_EXIT){
					@Override
					public void effects() {
						Main.game.getPlayer().addWeapon(WeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE), false);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld LILAYA_ASSISTS_PREGNANCY = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			PlayerCharacter player = Main.game.getPlayer();
			GameCharacter aunt = Main.game.getLilaya();
			
			UtilText.nodeContentSB.setLength(0);
			
			if (player.getSideQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == 0) {
				
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Stepping forwards, you smile at your demonic aunt, before looking down and rubbing your belly,"
							+ " [pc.speech(Erm... Lilaya...)]"
						+ "</p>"
						+"<p>"
							+ "[lilaya.speech(Wait... Are you <i>pregnant</i>?!)] she asks."
						+ "</p>"
						+"<p>"
							+ "You aren't quite sure what to say, and look sheepishly at the floor as you hear Lilaya's footsteps drawing near."
							+ " Before you know what's happening, her hands are rubbing all over your belly, and you let out a little gasp at the sudden feeling of someone else touching your pregnant bump."
						+ "</p>");
				
				// Player has had sex with Lilaya before:
				if(player.getStats().getSexPartnerStats(aunt) != null) {
					if(player.getPotentialPartnersAsMother().stream().anyMatch(x -> x.getFather() == aunt)) {
						if(player.getPotentialPartnersAsMother().stream().anyMatch(x -> x.getFather() != aunt)) {
							// Lilaya might be the 'father':
							UtilText.nodeContentSB.append(
								"<p>"
									+"[pc.speech(Erm... Lilaya? Just so you know, you <i>might</i> be the one who got me pregnant...)] you explain, looking down at your demonic aunt as she carries on feeling your stomach."
								+ "</p>"
								+ "<p>"
									+"[lilaya.speech(Well, you're the one who wanted me to grow a cock, remember?)] Lilaya laughs, clearly not at all fazed by the fact that she might be the other parent to your children."
								+ "</p>"
								+"<p>"
									+ "You feel very conscious of the fact that you just admitted to having had other sexual partners to Lilaya."
									+ " As you glance over to the nearby chair that you both had sex on, the feeling of guilt in the back of your mind causes you to start explaining yourself to your demonic aunt, "
									+ UtilText.parsePlayerSpeech("Well... You don't mind do you? I mean, I still like you and everything...")
								+ "</p>"
								+ "<p>"
									+ "You look up to see a puzzled expression on Lilaya's face, and she momentarily stops feeling your stomach, leaving her hands to rest on your belly as she replies, "
									+ UtilText.parseSpeech("Mind about what? Oh!", aunt)
									+ " Her cheeks go red as she starts to blush, "
									+ UtilText.parseSpeech("D-Don't worry about it! I-I mean... I still like you too... It's just that Rose and I have this special sort of relationship, y-you see?", aunt)
								+ "</p>"
								+ "<p>"
									+ "For a moment, you're not quite sure what she's talking about, but then you realise that she completely misinterpreted what you were saying."
									+ " She seems to have thought that you were apologising about interrupting her and Rose, and you see that she isn't even thinking twice about you having sex with other people."
									+ " After all, you suppose, she's quite happy to carry on pursuing her relationship with Rose, and it puts your mind at ease knowing that whatever you've got going with Lilaya isn't an exclusive deal."
								+ "</p>");
							
						} else {
							// Lilaya is definitely the 'father':
							UtilText.nodeContentSB.append(
									"<p>"
										+"[pc.speech(Erm... Lilaya? Just so you know, you're definitely the one that got me pregnant...)] you explain, looking down at your demonic aunt as she carries on feeling your stomach."
									+ "</p>"
									+ "<p>"
									+"[lilaya.speech(Well, you're the one who wanted me to grow a cock, remember?)] Lilaya laughs, clearly not at all fazed by the fact that she's the \"father\" to your children."
								+ "</p>");
						}
						
					} else {
						// Lilaya is definitely not the 'father':
						UtilText.nodeContentSB.append(
								"<p>"
									+ "You feel very conscious of the fact that you're presenting clear evidence of having other sexual partners to Lilaya."
									+ " As you glance over to the nearby chair that you both had sex on, the feeling of guilt in the back of your mind causes you to start explaining yourself to your demonic aunt, "
									+ UtilText.parsePlayerSpeech("Erm... Lilaya... You don't mind do you? I mean, I still like you and everything...")
								+ "</p>"
								+ "<p>"
									+ "You look up to see a puzzled expression on Lilaya's face, and she momentarily stops feeling your stomach, leaving her hands to rest on your belly as she replies, "
									+ UtilText.parseSpeech("Mind about what? Oh!", aunt)
									+ " Her cheeks go red as she starts to blush, "
									+ UtilText.parseSpeech("D-Don't worry about it! I-I mean... I still like you too... It's just that Rose and I have this special sort of relationship, y-you see?", aunt)
								+ "</p>"
								+ "<p>"
									+ "For a moment, you're not quite sure what she's talking about, but then you realise that she completely misinterpreted what you were saying."
									+ " She seems to have thought that you were apologising about interrupting her and Rose, and you see that she isn't even thinking twice about you having sex with other people."
									+ " After all, you suppose, she's quite happy to carry on pursuing her relationship with Rose, and it puts your mind at ease knowing that whatever you've got going with Lilaya isn't an exclusive deal."
								+ "</p>");
					}
					
				}

				UtilText.nodeContentSB.append(
						"<p>"
							+ "She suddenly breaks off from fondling your abdomen, and, grabbing your wrist, starts pulling you into her lab."
							+ " You look around to see that Rose has vacated the area, leaving you alone with your demonic aunt."
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(This is so exciting! I wonder how your body's going to react, what with you coming from another universe and everything!)]"
							+ " she cries, pushing you down into a chair before running off to rummage around in some nearby boxes. As she does so, she shouts back to you over her shoulder, "
							+ "[lilaya.speech(I'm guessing you're not aware of how pregnancies work here, huh?"
									+ " Well, seeing as the arcane has a massive effect on it, I'm guessing it's pretty different than it is in your world!"
									+ " Hmm... Where did it go?)]"
						+ "</p>"
						+ "<p>"
							+ "Lilaya stops talking for a moment as she concentrates on searching through the boxes."
							+ " You aren't given much time to think about what she's saying before you see her straighten up and turn around, and you see that she's holding another of those wand-like instruments in one hand as she"
								+ " starts walking back over towards you."
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(So, I'm guessing you've already realised that the arcane has a big influence on pregnancy speed around here,)]"
							+ " she laughs, stroking your belly once more before stepping back and tinkering with some small dials on the instrument she's just retrieved, "
							+ UtilText.parseSpeech("I've actually done a fair bit of research on the arcane's influence on pregnancies, so I know that without its presence, pregnancies should really be taking about nine months...", aunt)
						+ "</p>"
						+ "<p>"
							+ "As you try to assimilate the information Lilaya's giving you, the instrument in her hand starts giving off a faint pink glow, and, letting out a satisfied hum, she brings it down to your pregnant bump."
							+ " Running the little wand up and down over your swollen belly, she starts making thoughtful humming sounds, and just as you're starting to worry about the puzzled expression that's covering her face,"
								+ " the instrument suddenly releases a bright flash of pink light, and Lilaya lets out a relieved sigh."
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech(Well, that's a relief! Oh, right, I need to explain what's going on!)]"
							+ " she says, putting the instrument down on a nearby table before turning back to face you, "
							+ "[lilaya.speech(So, it turns out that your body has completely adapted to this world, and you're going to be obeying the same sort of rules for pregnancy as the rest of us!"
									+ " What I'm trying to say is that you're not going to be pregnant for the better part of a year; it's going to be more like one or two weeks."
									+ " Oh, and also, the sort of children you're going to give birth to is dependent on what sort of vagina and reproductive system you've got."
									+ " Human reproduction is kind of special, in that they always give birth to children of whatever race the father was."
									+ " Otherwise, you'll give birth to children of the same race as your reproductive system."
									+ " Pretty exciting, huh?!)]"
						+ "</p>"
						+ "<p>"
							+ "You look down at your pregnant belly, stroking it absent-mindedly as Lilaya's words sink in."
							+ " As your thoughts turn to the process of bringing up a child in this strange world, you feel yourself starting to become overwhelmed by emotion, and you blurt out to Lilaya, "
							+ "[pc.speech(B-but what do I do?! How am I meant to raise a child here?! A-And, what about the whole part of actually giving birth?! This is too much!)]"
						+ "</p>"
						+ "<p>"
							+ "Much to your surprise, Lilaya's face momentarily scrunches up into a look of confusion, as though she doesn't quite know what you're talking about."
							+ " Thankfully, after just a moment, she lets out an understanding sigh, and quickly moves to put your fears to rest, "
							+ "[lilaya.speech(Aah, of course, the arcane is the thing responsible for the rapid growth as well..."
									+ " I'm sure this will come as a major relief, but you don't have to worry about raising your children here; thanks to the arcane, they'll reach full maturity in just a couple of hours."
									+ " The arcane grants them their mother's general knowledge about things, so they get all the information they need to make their own way in the world."
									+ " There's no need for any 'raising' or anything, don't worry."
									+ " Honestly, I don't even know how people would be able to cope without the arcane!)]"
						+ "</p>"
						+ "<p>"
							+ "As Lilaya finishes speaking, a huge wave of relief washes over you."
							+ " You sink back into the chair, sighing deeply, before you remember your last concern about this whole process; the part about actually giving birth."
							+ " Before you can ask Lilaya about it again, she continues, "
						+ (player.hasStatusEffect(StatusEffect.PREGNANT_3)
								? "[lilaya.speech(Oh, and don't worry about the whole giving birth part."
										+ " I see that your belly has already finished growing, so I can help you to give birth whenever you're ready!)]"
									+ "</p>"
									+ "<p>"
										+ "With your final question answered, you stand up from the chair, and thank Lilaya for all her help."
										+ " She tells you not to worry about it, and to remember to come back to see her when you're ready to give birth."
									+ "</p>"

								: "[lilaya.speech(Oh, and don't worry about the whole giving birth part."
										+ " When you're ready, just come back here and I'll talk you through it!)]"
									+ "</p>"
									+ "<p>"
										+ "With your final question answered, you stand up from the chair, and thank Lilaya for all her help."
										+ " She tells you not to worry about it, and to remember to come back to see her once your belly has finished growing."
									+ "</p>"));
						
				return UtilText.nodeContentSB.toString();
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Stepping forwards, you smile at your demonic aunt, before looking down and rubbing your belly,"
							+ " [pc.speech(Erm... Lilaya...)]"
						+ "</p>");

				if(player.isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToPregnancyLilaya) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[lilaya.speech(Wait... Are you <i>pregnant</i>?!)] she asks."
							+ "</p>");
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[lilaya.speech(So, you're ready to do something about your little pregnancy problem, huh?)] she asks."
							+ "</p>");
				}
				
				UtilText.nodeContentSB.append(
						"<p>"
							+ "You aren't quite sure what to say, and look sheepishly at the floor as you hear Lilaya's footsteps drawing near."
							+ " Before you know what's happening, her hands are rubbing all over your belly, and you let out a little gasp at the sudden feeling of someone else touching your pregnant bump."
						+ "</p>");

				if(player.hasStatusEffect(StatusEffect.PREGNANT_3)) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[lilaya.speech(I see your belly's already finished growing, so I can help you to give birth whenever you're ready!)]"
								+ " she says, smiling at you as she carries on fondling your swollen bump."
							+ "</p>");
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[lilaya.speech(You're not quite ready to give birth just yet... Come back when your belly's really nice and round!)]"
								+ " she says, smiling at you as she carries on fondling your swollen bump."
							+ "</p>");
				}
				
				return UtilText.nodeContentSB.toString();
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if (Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
					return new Response("Give birth", "Tell Lilaya that you're ready to give birth", LILAYA_DETECTS_BIRTHING_TYPE){
						@Override
						public void effects() {
							if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToPregnancyLilaya)
								Main.game.getDialogueFlags().reactedToPregnancyLilaya = true;
						}
					};
				} else {
					return new Response("Give birth", "You need to wait until your belly has finished growing before you're able to give birth.", null){
						@Override
						public void effects() {
							if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToPregnancyLilaya)
								Main.game.getDialogueFlags().reactedToPregnancyLilaya = true;
						}
					};
				}

			} else if (index == 0) {
				return new Response("Back", "Tell Lilaya that you need a moment to think.", LAB_EXIT){
					@Override
					public void effects() {
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToPregnancyLilaya) {
							Main.game.getDialogueFlags().reactedToPregnancyLilaya = true;
						}
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[pc.speech(Thanks for your help Lilaya, but can I just have a moment to think?)] you ask, smiling at your demonic aunt."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(Sure, just let me know if you need anything else!)] she says, before backing off a little to give you some space."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LILAYA_DETECTS_BIRTHING_TYPE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Giving birth";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Cradling your swollen belly with both hands, you look up at Lilaya, "
						+ "[pc.speech(I'm ready to give birth now...)]"
					+ "</p>");

			if (!Main.game.getPlayer().isSideQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "A delighted smile crosses her face, but despite her reassuring look, as well as knowing that you're in competent hands, you're still incredibly nervous about what comes next."
							+ " Lilaya must have noticed the worried look on your face, as she moves up close to you and makes a soothing hushing sound."
							+ " Her hands move down to gently stroke over your pregnant bump, and you start blushing at the thought of what's about to happen."
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "A delighted smile crosses her face, and despite the fact that you've done this before, you're still pretty nervous about what comes next."
							+ " She must have noticed the worried look on your face, as she suddenly steps close to you and makes a soothing hushing sound."
							+ " Her hands stroke over your pregnant bump, and you start blushing at the thought of what's about to happen."
						+ "</p>");
			}

			UtilText.nodeContentSB.append(
					"<p>"
						+ "[lilaya.speech(Don't worry, I'm here for you,)]"
						+ " she says, pulling you into a loving hug for a moment before stepping back,"
						+ " [lilaya.speech(but first, I need to know what sort of race you're going to be giving birth to.)]"
					+ "</p>"
						
					+ "<p>"
						+ (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)
							? "As she speaks, Lilaya starts stripping off your clothes, and as she gets access to your vagina, she lets out a little humming noise, "
							: "Your vagina is already exposed, and, after looking down at it for a moment, Lilaya lets out a little humming noise, "));
			
			switch(Main.game.getPlayer().getVaginaType()) {
				case HARPY:
					UtilText.nodeContentSB.append("[lilaya.speech(Ooh, alright, you're going to be laying some eggs, how exciting!"
							+ " I'm sure you're already feeling it, but some incredibly strong maternal instincts are going to be kicking in pretty soon, and you're only going to feel comfortable doing this in a very personal area."
							+ " I think using your room would be the best bet, follow me!)]"
							+ "</p>");
					break;
				default:
					UtilText.nodeContentSB.append("[lilaya.speech(Alright, so you're going to be giving birth to live young."
							+ " I've got a room set up for just that purpose, follow me!)]"
							+ "</p>");
					break;
			}
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				switch(Main.game.getPlayer().getVaginaType()) {
					case HARPY:
						return new Response("Follow Lilaya", "Allow Lilaya to lead you up to your room.", LILAYA_ASSISTS_EGG_LAYING) {
							@Override
							public void effects() {
								Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, LilayasHome.LILAYA_HOME_ROOM_PLAYER);
								Main.game.setActiveWorld(
										Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
										Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(LilayasHome.LILAYA_HOME_ROOM_PLAYER),
										false);
							}
						};
					default:
						return new Response("Follow Lilaya", "Allow Lilaya to lead you to the birthing room.", LILAYA_ASSISTS_BIRTHING) {
							@Override
							public void effects() {
								Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_BIRTHING_ROOM);
								Main.game.setActiveWorld(
										Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR),
										Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getPlacesOfInterest().get(LilayasHome.LILAYA_HOME_BIRTHING_ROOM),
										false);
							}
						};
				}
				

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LILAYA_ASSISTS_BIRTHING = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Birthing Room";
		}

		@Override
		public String getContent() {
			if (!Main.game.getPlayer().isSideQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				return "<p>"
							+ "Taking your hands in hers, she leads you out of the lab and down the hallway."
							+ " You soon find yourself standing outside one of the many doors that line the corridor, and Lilaya steps forwards and holds it open for you."
							+ " Stepping inside, you find yourself in a specially-purposed birthing room."
							+ " Instead of being carpeted, like most of the other rooms in this house, clean white tiles have been chosen for this room's flooring."
							+ " A surprisingly modern-looking birthing bed is sitting against one wall of the room, but apart from that, there isn't much else in the way of medical equipment."
							+ " A few comfortable chairs have been placed around the edges of the room, but other than those, and a drinks cabinet that's sitting in one corner, the room is devoid of any other furniture."
						+ "</p>"

						+ "<p>"
							+ "As Lilaya leads you over to the bed, she explains the situation, "
							+ UtilText.parseSpeech("Ok, so I know you haven't done anything like this before, so I'll quickly explain what's going to happen."
									+ " In order for you to give birth, I need to focus a special kind of arcane spell into your nice little bump here."
									+ " Although you won't feel any pain, a lot of the arcane power I'll be using is going to be drawn directly from your aura, and there's a good chance that you're going to pass out from it.", Main.game.getLilaya())
						+ "</p>"

						+ "<p>"
							+ "You glance across worriedly at Lilaya as she says this, but she squeezes your hand in hers and reassures you, "
							+ UtilText.parseSpeech("Don't worry, you don't need to stay conscious during this."
									+ " Now, after I've delivered your children, they're going to grow pretty quickly, so don't be alarmed!"
									+ " They're also going to be pretty hungry, but don't worry if you're not producing any milk, we've got everything they'll need in that cabinet there."
									+ " Oh, and also, they're probably going to want to leave straight away."
									+ " All children are born with a strong desire to go out and become independent as soon as possible, so don't take it personally if they've already left by the time you wake up.", Main.game.getLilaya())
						+ "</p>"

						+ "<p>"
							+ (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)
									? "As she's speaking, Lilaya starts stripping off your clothes, and as she gets access to your vagina, she gently pushes you down onto the bed."
									: "Your vagina is already exposed, so Lilaya starts by gently pushing you back onto the bed.")
							+ " After following a few more of her instructions, you find yourself lying back with your legs held up and spread apart by a pair of sturdy metal supports."
						+ "</p>"

						+ "<p>"
							+ UtilText.parseSpeech("Ok, all set?", Main.game.getLilaya())
						+ "</p>";
			} else {
				return "<p>"
							+ "Taking your hands in hers, she leads you out of the lab and down the hallway."
							+ " You soon find yourself standing outside one of the many doors that line the corridor, and Lilaya steps forwards and holds it open for you."
							+ " Stepping inside, you once again find yourself standing in the specially-purposed birthing room."
							+ " Instead of being carpeted, like most of the other rooms in this house, clean white tiles have been chosen for this room's flooring."
							+ " A surprisingly modern-looking birthing bed is sitting against one wall of the room, but apart from that, there isn't much else in the way of medical equipment."
							+ " A few comfortable chairs have been placed around the edges of the room, but other than those, and a drinks cabinet that's sitting in one corner, the room is devoid of any other furniture."
						+ "</p>"

						+ "<p>"
							+ "As Lilaya leads you over to the bed, she reminds you of what's about to happen, "
							+ UtilText.parseSpeech("Ok, so I know you've done this before, but I'll quickly go over what's going to happen."
									+ " In order for you to give birth, I need to focus a special kind of arcane spell into your nice little bump here."
									+ " Although you won't feel any pain, a lot of the arcane power I'll be using is going to be drawn directly from your aura, and there's a good chance that you're going to pass out from it.", Main.game.getLilaya())
						+ "</p>"

						+ "<p>"
							+ "You let out a sigh she says this, but she squeezes your hand in hers and reassures you, "
							+ UtilText.parseSpeech("Don't worry, remember, you don't need to stay conscious during this."
									+ " Now, just remember that after I've delivered your children, they're going to grow pretty quickly!"
									+ " They're also going to be hungry, but don't worry if you're not producing any milk, we've got everything they'll need in that cabinet there."
									+ " Oh, and also, they're probably going to want to leave straight away."
									+ " All children are born with a strong desire to go out and become independent as soon as possible, so don't take it personally if they've already left by the time you wake up.", Main.game.getLilaya())
						+ "</p>"

						+ "<p>"
							+ (Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)
									? "As she's speaking, Lilaya starts stripping off your clothes, and as she gets access to your vagina, she gently pushes you down onto the bed."
									: "Your vagina is already exposed, so Lilaya starts by gently pushing you back onto the bed.")
							+ " After following a few more of her instructions, you find yourself lying back with your legs held up and spread apart by a pair of sturdy metal supports."
						+ "</p>"

						+ "<p>"
							+ UtilText.parseSpeech("Ok, all set?", Main.game.getLilaya())
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Start", "Tell Lilaya that you're ready to give birth now.", LILAYA_ASSISTS_BIRTHING_DELIVERS){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().reactedToPregnancyLilaya = false;
						Main.game.getPlayer().endPregnancy(true);
						Main.game.getPlayer().setMana(0);
						Main.game.getPlayer().setStamina(0);

						Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
						Main.game.getPlayer().incrementVaginaCapacity((Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaElasticity().getCapacityIncreaseModifier());
					}
				};

			} else if (index == 2) {
				return new Response("Knock out", "Ask Lilaya if she could give you something to knock you out. After all, she said you didn't need to be conscious for this.", LILAYA_ASSISTS_BIRTHING_KNOCK_OUT){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().reactedToPregnancyLilaya = false;
						Main.game.getPlayer().endPregnancy(true);
						Main.game.getPlayer().setMana(0);
						Main.game.getPlayer().setStamina(0);

						Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
						Main.game.getPlayer().incrementVaginaCapacity((Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaElasticity().getCapacityIncreaseModifier());
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld LILAYA_ASSISTS_BIRTHING_DELIVERS = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
					+ "You nod to Lilaya, "
					+ UtilText.parsePlayerSpeech("I'm ready...")
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Right, here we go!", Main.game.getLilaya())
					+ " she says, and you see sparks of purple energy start to run down her arms."
					+ "</p>"

					+ "<p>"
						+ "You strain your neck to look down, and see Lilaya bring her hands up to hover over your pregnant belly."
						+ " A huge wave of exhaustion crashes over you, and you remember what she said about how she was going to draw out your aura for this."
						+ " You struggle to keep your eyes open, but as you feel an intense pushing sensation deep within your vagina, you're almost glad that you can't see what's happening."
						+ " You start to slip in and out of consciousness, and you find yourself having a strange dream."
					+ "</p>"

					+ "<p>"
						+ "<i>"
						+ "You hear Lilaya speaking from somewhere beneath you, but you can't make out what she's saying..."
						+ "</br></br>");
			
			if(Main.game.getPlayer().getBreastRawLactationValue() > 0) {
				UtilText.nodeContentSB.append("You feel a desperate suckling at your nipples, and you're vaguely aware of something greedily drinking down mouthfuls of your [pc.milk]...");
			} else {
				UtilText.nodeContentSB.append("You feel a weight on your chest, and you're vaguely aware of something greedily drinking a bottle of milk as you cradle it in your arms...");
			}
			
			
			if(Main.game.getPlayer().getLastLitterBirthed().getSonsFromFather() > 0) {
				switch(Main.game.getPlayer().getLastLitterBirthed().getFatherRace()) {
					case ANGEL:
						UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing an androgynous figure bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
						break;
					case CAT_MORPH:
						UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a strangely familiar cat-boy bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
						break;
					case DEMON:
						UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing an excitable imp flutter up to plant a kiss on your cheek."
								+ " You hear Lilaya shouting somewhere in the background, and the imp lets out a little laugh before darting off out of the room...");
						break;
					case DOG_MORPH:
						UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a strangely familiar dog-boy bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
						break;
					case HARPY:
						UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a very pretty harpy bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
						break;
					case HORSE_MORPH:
						UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a handsome horse-boy bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
						break;
					case HUMAN:
						UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a young man bending down over you, planting a kiss on your cheek and muttering something in your ear before walking out the door...");
						break;
					case SLIME:
						UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a slime-boy bending down over you, who plants a wet kiss on your cheek and mutters something in your ear before walking out the door...");
						break;
					case WOLF_MORPH:
						UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a very masculine wolf-boy bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
						break;
					default:
						UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a strange young man bending down over you, planting a kiss on your cheek and muttering something in your ear before walking out the door...");
						break;
				}
			} else {
				if(Main.game.getPlayer().getLastLitterBirthed().getSonsFromMother() > 0) {
					switch(Main.game.getPlayer().getLastLitterBirthed().getMotherRace()) {
						case ANGEL:
							UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing an androgynous figure bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
							break;
						case CAT_MORPH:
							UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a strangely familiar cat-boy bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
							break;
						case DEMON:
							UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing an excitable imp flutter up to plant a kiss on your cheek."
									+ " You hear Lilaya shouting somewhere in the background, and the imp lets out a little laugh before darting off out of the room...");
							break;
						case DOG_MORPH:
							UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a strangely familiar dog-boy bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
							break;
						case HARPY:
							UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a very pretty harpy bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
							break;
						case HORSE_MORPH:
							UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a handsome horse-boy bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
							break;
						case HUMAN:
							UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a young man bending down over you, planting a kiss on your cheek and muttering something in your ear before walking out the door...");
							break;
						case SLIME:
							UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a slime-boy bending down over you, who plants a wet kiss on your cheek and mutters something in your ear before walking out the door...");
							break;
						case WOLF_MORPH:
							UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a very masculine wolf-boy bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
							break;
						default:
							UtilText.nodeContentSB.append("</br></br>Some time later, you imagine seeing a strange young man bending down over you, planting a kiss on your cheek and muttering something in your ear before walking out the door...");
							break;
					}
				}
			}
			if(Main.game.getPlayer().getLastLitterBirthed().getDaughtersFromFather() > 0) {
				switch(Main.game.getPlayer().getLastLitterBirthed().getFatherRace()) {
					case ANGEL:
						UtilText.nodeContentSB.append("</br></br>An androgynous figure bends down over you."
								+ " They sit down next to you on the bed, and lean in to give you a loving hug and a stroke of your head before departing...");
						break;
					case CAT_MORPH:
						UtilText.nodeContentSB.append("</br></br>A pretty cat-girl sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
						break;
					case DEMON:
						break;
					case DOG_MORPH:
						UtilText.nodeContentSB.append("</br></br>A dog-girl sporting a playful smile sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
						break;
					case HARPY:
						UtilText.nodeContentSB.append("</br></br>A stunningly beautiful harpy sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
						break;
					case HORSE_MORPH:
						UtilText.nodeContentSB.append("</br></br>A pretty horse-girl sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
						break;
					case HUMAN:
						UtilText.nodeContentSB.append("</br></br>A young woman, who roughly looks to be about your age, sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
						break;
					case SLIME:
						UtilText.nodeContentSB.append("</br></br>A gooey slime-girl slides down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
						break;
					case WOLF_MORPH:
						UtilText.nodeContentSB.append("</br></br>A grinning wolf-girl sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
						break;
					default:
						UtilText.nodeContentSB.append("</br></br>A young woman, who roughly looks to be about your age, sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
						break;
				}
			} else {
				if(Main.game.getPlayer().getLastLitterBirthed().getDaughtersFromMother() > 0) {
					switch(Main.game.getPlayer().getLastLitterBirthed().getMotherRace()) {
						case ANGEL:
							UtilText.nodeContentSB.append("</br></br>An androgynous figure bends down over you."
									+ " They sit down next to you on the bed, and lean in to give you a loving hug and a stroke of your head before departing...");
							break;
						case CAT_MORPH:
							UtilText.nodeContentSB.append("</br></br>A pretty cat-girl sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
							break;
						case DEMON:
							break;
						case DOG_MORPH:
							UtilText.nodeContentSB.append("</br></br>A dog-girl sporting a playful smile sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
							break;
						case HARPY:
							UtilText.nodeContentSB.append("</br></br>A stunningly beautiful harpy sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
							break;
						case HORSE_MORPH:
							UtilText.nodeContentSB.append("</br></br>A pretty horse-girl sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
							break;
						case HUMAN:
							UtilText.nodeContentSB.append("</br></br>A young woman, who roughly looks to be about your age, sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
							break;
						case SLIME:
							UtilText.nodeContentSB.append("</br></br>A gooey slime-girl slides down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
							break;
						case WOLF_MORPH:
							UtilText.nodeContentSB.append("</br></br>A grinning wolf-girl sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
							break;
						default:
							UtilText.nodeContentSB.append("</br></br>A young woman, who roughly looks to be about your age, sits down next to you on the bed, and gives you a loving hug and a stroke of your head before departing...");
							break;
					}
				}
			}
			
			UtilText.nodeContentSB.append("</i>"
					+ "</p>");
					
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Pass out", "You have no energy left, and can't stay conscious any longer...", LILAYA_ASSISTS_BIRTHING_FINISHED){
					@Override
					public QuestLine getQuestLine() {
						if (Main.game.getPlayer().getSideQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == 1)
							return QuestLine.SIDE_FIRST_TIME_PREGNANCY;
						else
							return null;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
						Main.game.getPlayer().setStamina(Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM));

						Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_LAB);
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(LilayasHome.LILAYA_HOME_ROOM_PLAYER),
								false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LILAYA_ASSISTS_BIRTHING_KNOCK_OUT = new DialogueNodeOld("Your room", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ UtilText.parsePlayerSpeech("Have you got anything to drink? You know, so maybe I don't have to be awake for this...")
					+ " you ask, looking up at Lilaya hopefully."
					+ "</p>"

					+ "<p>"
					+ "She laughs and walks over to the drinks cabinet, "
					+ UtilText.parseSpeech("You know, I thought you might ask for that! I prepared this for you earlier!", Main.game.getLilaya())
					+ "</p>"

					+ "<p>"
					+ " She hands you a glass of what looks like ordinary sparkling water, but as you bring it to your lips and quickly down the liquid, you notice that it has a strong taste of peppermint."
					+ " Almost instantly, you feel your eyelids grow heavy, and as you lie back on the bed, you vaguely see Lilaya bringing her hands up to hover over your pregnant belly."
					+ " Sparks of purple energy start to run down her arms, and as you start to feel an intense pushing sensation deep within your vagina, you're glad that you're not going to be awake for this."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Pass out", "The drink Lilaya gave you goes straight to your head, and you collapse back onto the bed as you lose consciousness.", LILAYA_ASSISTS_BIRTHING_FINISHED){
					@Override
					public QuestLine getQuestLine() {
						if (Main.game.getPlayer().getSideQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == 1)
							return QuestLine.SIDE_FIRST_TIME_PREGNANCY;
						else
							return null;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
						Main.game.getPlayer().setStamina(Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM));

						Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_LAB);
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(LilayasHome.LILAYA_HOME_ROOM_PLAYER),
								false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LILAYA_ASSISTS_EGG_LAYING = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Your room";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
							+ "Taking your hands in hers, Lilaya leads you out of the lab and up to your room."
							+ " Following her in as she pushes open the door, you see that Rose has laid a soft protective covering over your bed, and a few bottles of milk have been placed on your bedside cabinet."
						+ "</p>");

			if (!Main.game.getPlayer().isSideQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				UtilText.nodeContentSB.append("<p>"
								+ "As Lilaya leads you over to the bed, she explains the situation, "
								+ "[lilaya.speech(Ok, so I know you haven't done anything like this before, so I'll quickly explain what's going to happen."
										+ " In order for you to start laying your eggs, I need to focus a special kind of arcane spell into your nice little bump here."
										+ " This isn't as intense as giving birth to live young, so you're going to stay conscious through all of this.)]"
							+ "</p>");
			} else {
				UtilText.nodeContentSB.append("<p>"
						+ "As Lilaya leads you over to the bed, she explains the situation, "
						+ "[lilaya.speech(Ok, so I know we've done this before, but I'll just quickly remind you of what's going to happen."
								+ " In order for you to start laying your eggs, I need to focus a special kind of arcane spell into your nice little bump here."
								+ " This isn't as intense as giving birth to live young, so you're going to stay conscious through all of this.)]"
					+ "</p>");
			}

			UtilText.nodeContentSB.append("<p>"
							+ "You glance across worriedly at Lilaya as she says this, but she squeezes your hand in hers and reassures you, "
							+ "[lilaya.speech(Don't worry, it doesn't hurt or anything!"
									+ " Now, after I've helped you lay your eggs, you're probably going to get aggressive if I hang around for too long, so I'll leave as quickly as possible."
									+ " Oh, and don't worry about your behaviour, I understand that your maternal instinct is going to get pretty intense.)]"
						+ "</p>"
						+ "<p>"
							+ "You fidget about uncomfortably a little, but Lilaya makes a soothing shushing noise as she pushes you down into the middle of your bed, before shuffling around to sit behind you, "
							+ "[lilaya.speech(It's alright, don't worry!"
								+ " So, after you've laid your eggs, you're going to incubate them for roughly twenty-four hours, during which time you'll find that you'll be unable to sleep or get any rest."
								+ " Once the eggs start hatching, all that built-up tiredness will suddenly wash over you, and you might collapse from exhaustion."
								+ " I'll be waiting to come in and help your children, so don't worry if you end up passing out!"
								+ " Oh, and remember that they're going to grow pretty rapidly once they've hatched, so they might have already flown the nest by the time you wake up.)]"
						+ "</p>"

						+ "<p>"
							+ "As she's been speaking, Lilaya has been shuffling around into a comfortable position behind you."
							+ " Kneeling down on the bed, she presses herself into your back, before reaching around to rub her hands up and down over your belly."
							+ " Moving down to apply a gentle pressure to your inner-thighs, Lilaya gets you to spread your legs, so that you're ready to start laying your eggs."
						+ "</p>"

						+ "<p>"
							+ "[lilaya.speech(Ok, all set?)]"
						+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Lay eggs", "Tell Lilaya that you're ready to lay your eggs now.", LILAYA_ASSISTS_EGG_LAYING_DELIVERS){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().reactedToPregnancyLilaya = false;
						Main.game.getPlayer().endPregnancy(true);
						Main.game.getPlayer().setMana(0);
						Main.game.getPlayer().setStamina(0);

						Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
						Main.game.getPlayer().incrementVaginaCapacity((Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaElasticity().getCapacityIncreaseModifier());
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LILAYA_ASSISTS_EGG_LAYING_DELIVERS = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You nod as you look down at Lilaya's hands resting on your swollen stomach, "
						+ "[pc.speech(I'm ready...)]"
					+ "</p>"

					+ "<p>"
						+ "[lilaya.speech(Right, just try and relax...)]"
						+ " she whispers into your ear, and as she does so, you see a soft pink glow start to run down her arms."
					+ "</p>"

					+ "<p>"
						+ "Sliding her hands up and down over your pregnant belly, you feel her start to apply a gentle pressure with her thumbs, as though she's trying to push out the eggs within you."
						+ " As she does this, you start to feel an intense pressure building up deep within your vagina, and, collapsing back into Lilaya, you spread your legs and let out a desperate whine."
					+ "</p>"
						
					+ "<p>"
						+ "[lilaya.speech(That's right, now push... Go on, you can do it...)]"
						+ " Lilaya softly speaks into your ear, and, obeying her instructions, you try to push the pressure out from between your legs."
					+ "</p>"
					
					+ "<p>"
						+ "Looking down, you see the tip of your first egg peeking out from between your legs, and with a determined cry, you push with all your might."
						+ " You feel the egg stretching you out as you continue to apply pressure, but just as you feel that you can't keep going any longer, the egg pops free and slides out onto the bed beneath you."
					+ "</p>"
						
					+ "<p>"
						+ "[pc.speech(I-I did it,)]"
						+ " you sigh, looking down at the egg you've just laid."
						+ " It looks to be about the same size as an emu's egg, and as you marvel at its pale pink colouring and smooth shell, you feel yourself subconsciously reaching out to pick it up and cradle in your arms."
					+ "</p>"
						
					+ "<p>"
						+ "[lilaya.speech(Good [pc.girl], [pc.name]! But wait, just leave it for a moment, you've still got more to lay!)]"
						+ " Lilaya pulls you back into her, continuing to apply the gentle pressure on your stomach as she makes soothing shushing noises into your ear."
					+ "</p>"
						
					+ "<p>"
						+ "Doing as Lilaya says, you allow her to pull you back, but as she does so, a strange feeling suddenly washes over you,"
							+ " and it takes all of your willpower to prevent yourself from pushing Lilaya away and attempting to grab your egg."
						+ " Almost as though she sensed what you were thinking, Lilaya reminds you to try and overcome your maternal instincts, at least until you've finished laying all your eggs."
						+ " After promising to do your best, you set about pushing out the rest of your eggs, and within half an hour, a complete clutch of "
							+Util.intToString(Main.game.getPlayer().getLastLitterBirthed().getTotalLitterCount())+" eggs lie between your legs."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Protect the eggs!", "Why is Lilaya sitting so close behind you?! Maybe she wants to take your eggs for herself!", LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS) {
					@Override
					public void effects() {
						Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_LAB);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
						+ "As you look down at your clutch of eggs, you find yourself burning with an overwhelming need to protect them at all costs."
						+ " Throwing Lilaya's arms from off of you, you collapse forwards, wrapping your [pc.arms] around your precious brood as you turn your head back and shout,"
						+ " [pc.speech(Get away! Don't hurt them!)]"
					+ "</p>"
					+ "<p>"
						+ "Lilaya instantly stands up and starts backing off towards the door,"
						+ " [lilaya.speech(Ok, ok, don't worry, I'm leaving. You'll be safe in here...)]"
					+ "</p>"
					+ "<p>"
						+ "Still making soothing shushing noises, she opens the door to your room and slips out, before closing it firmly behind her."
						+ " Having driven off the only possible threat to your nest, you shuffle around into a more comfortable position, pressing the heat of your body up against your eggs as you continue to cover them with your [pc.arms]."
						+ " You make sure to position yourself so that you're looking at the only entrance to your room, and, once satisfied that you're in a good position, you settle down to your vigilant watch..."
					+ "</p>"
					+ "<p>"
						+ "For the next twenty hours, you barely move a muscle, but despite the fact that you're lying on a warm, comfortable bed, you don't even consider for a moment to get any rest."
						+ " Your eyes only occasionally drift away from the door, but only to check your surroundings for any sign of danger."
					+ "</p>"
					+ "<p>"
						+ "As you enter the twenty-first hour of your incubation, you feel one of your eggs start to move."
						+ " Letting out a delighted cry, you shift position slightly to look down at your clutch."
						+ " A feeling of overwhelming joy rushes through you as you see each and every one of your eggs trembling and shifting about."
					+ "</p>"
					+ "<p>"
						+ "You watch, fascinated, as the first egg starts to crack.");
			
			if(Main.game.getPlayer().getLastLitterBirthed().getDaughtersFromMother()>0) {
				UtilText.nodeContentSB.append(
						" Within moments, a little head bursts through the top, and your eyes open wide as you see a tiny "+Main.game.getPlayer().getLastLitterBirthed().getMotherRace().getOffspringFemaleNameSingular()+" crawling out."
						+ " A little egg-tooth is still attached to her forehead, but after a quick shake, she drops it off onto the bed beneath her.");
				
			} else if(Main.game.getPlayer().getLastLitterBirthed().getSonsFromMother()>0) {
				UtilText.nodeContentSB.append(
						" Within moments, a little head bursts through the top, and your eyes open wide as you see a tiny "+Main.game.getPlayer().getLastLitterBirthed().getMotherRace().getOffspringMaleNameSingular()+" crawling out."
						+ " A little egg-tooth is still attached to his forehead, but after a quick shake, he drops it off onto the bed beneath him.");
				
			} else if(Main.game.getPlayer().getLastLitterBirthed().getDaughtersFromFather()>0) {
				UtilText.nodeContentSB.append(
						" Within moments, a little head bursts through the top, and your eyes open wide as you see a tiny "+Main.game.getPlayer().getLastLitterBirthed().getFatherRace().getOffspringFemaleNameSingular()+" crawling out."
						+ " A little egg-tooth is still attached to her forehead, but after a quick shake, she drops it off onto the bed beneath her.");
				
			} else {
				UtilText.nodeContentSB.append(
						" Within moments, a little head bursts through the top, and your eyes open wide as you see a tiny "+Main.game.getPlayer().getLastLitterBirthed().getFatherRace().getOffspringMaleNameSingular()+" crawling out."
								+ " A little egg-tooth is still attached to his forehead, but after a quick shake, he drops it off onto the bed beneath him.");
				
			}
						
			UtilText.nodeContentSB.append(" As all the other eggs start cracking in turn, you feel a wave of exhaustion washing over you, and with what little strength you have left, you feebly call out for Lilaya."
					+ "</p>"
					+ "<p>"
						+ "As your head collapses down onto the bed, you vaguely see Lilaya rushing into the room."
						+ " A delighted smile breaks out across her face as she sees that your eggs are hatching, and just before you pass out, you see her bending down in order to start taking care of your children."
					+ "</p>");
			
			
				if(Main.game.getPlayer().getBreastRawLactationValue() > 0) {
					UtilText.nodeContentSB.append("<p>"
								+ "Some time later, you drift back into consciousness for a moment as you feel a desperate suckling at your nipples,"
									+ " and you're vaguely aware that it's your children who are eagerly drinking down mouthfuls of your [pc.milk]."
								+ " Letting your eyelids close again, you soon drift off into an exhausted sleep."
							+ "</p>");
				} else {
					UtilText.nodeContentSB.append("<p>"
								+ "Some time later, you drift back into consciousness for a moment as you feel a weight on your chest,"
									+ " and you're vaguely aware of cradling your children in your arms as they eagerly drink down bottle after bottle of the milk that Lilaya has provided for you."
								+ " Letting your eyelids close again, you soon drift off into an exhausted sleep."
							+ "</p>");
				}
			 
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Some time later", "You eventually wake up from your exhausted slumber...", LILAYA_ASSISTS_BIRTHING_FINISHED){
					@Override
					public QuestLine getQuestLine() {
						if (Main.game.getPlayer().getSideQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == 1)
							return QuestLine.SIDE_FIRST_TIME_PREGNANCY;
						else
							return null;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
						Main.game.getPlayer().setStamina(Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	
	private static StringBuilder litterSB;
	public static final DialogueNodeOld LILAYA_ASSISTS_BIRTHING_FINISHED = new DialogueNodeOld("Your room", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			litterSB = new StringBuilder();
			litterSB.append(
					"<p>"
						+ "You wake up some time later, lying in your bed."
						+ " With a groan, you sit up, pushing the covers back as you rub sleep from your eyes."
					+ "</p>"
					+ "<p>"
						+ "The first thing you notice is how easy it is to move, and you let out a little gasp as you start running your hands over your now-flat stomach."
						+ " As you do so, an unexpected twinge of sadness runs through you, and for a moment you find yourself deeply missing the reassuring feeling of having a big swollen belly."
					+ "</p>"
					+ "<p>"
						+ "As you move, you discover the second thing that's changed, and you let out a surprised gasp at the feeling between your legs."
						+ " Quickly moving your fingers down to your groin, you discover that your vagina has been stretched out considerably, and you let out an uncomfortable groan as you become aware of the dull ache within your "
						+ Capacity.getCapacityFromValue(Main.game.getPlayer().getVaginaStretchedCapacity()).getDescriptor()
						+ " pussy."
					+ "</p>"
					+ "<p>"
						+ "Glancing across to your bedside cabinet, the final thing you discover is a note from Lilaya."
						+ " Picking it up, you glance over the contents:"
					+ "</p>"

					+ "<p style='text-align:center;'>"
						+ "<i>"
						+ "Hey [pc.name], congratulations! Everything went perfectly!</br>"
						+ "I hope you don't mind, but I collected a lot of data about your aura while we were doing this!"
						+ " I had to start analysing it straight away, and you were fast asleep anyway, so I hope you don't mind me leaving you there to rest."
						+ "</br></br>"
						+ "If you need anything, or perhaps if you'd like some more \"tests\", then you know where to find me!"
						+ "</br></br>"
						+ (Main.game.getPlayer().getLastLitterBirthed().getTotalLitterCount() > 1
								?"P.S. I managed to get your kids together for a picture before they left, it's under this note!"
								:"P.S. I got a picture of your kid before they left, it's under this note!")
						+ "</i>"
					+ "</p>"

					+ "<p>"
						+ "You put down the piece of paper and see the picture lying where Lilaya said it would be."
						+ " Picking it up, you feel tears welling up in your eyes as you see the result of your pregnancy smiling back at you."
					+ "</p>"
					+ "<p>"
					+ "In the picture you see:");

			if (Main.game.getPlayer().getLastLitterBirthed().getSonsFromFather() > 0) {
				litterSB.append("</br><b>"
						+ Util.capitaliseSentence(Util.intToString(Main.game.getPlayer().getLastLitterBirthed().getSonsFromFather()))
						+ "</b>");

				switch (Main.game.getPlayer().getLastLitterBirthed().getFatherRace()) {
					case ANGEL:
						litterSB.append(" radiant");
						break;
					case CAT_MORPH:
						litterSB.append(" good-looking");
						break;
					case DEMON:
						litterSB.append(" mischievous");
						break;
					case DOG_MORPH:
						litterSB.append(" smiling");
						break;
					case HARPY:
						litterSB.append(" feminine");
						break;
					case HORSE_MORPH:
						litterSB.append(" strong");
						break;
					case HUMAN:
						litterSB.append(" smiling");
						break;
					case SLIME:
						litterSB.append(" bubbly");
						break;
					case WOLF_MORPH:
						litterSB.append(" powerful");
						break;
				}
				litterSB.append(" <b style='color:"+ Colour.MASCULINE.toWebHexString()+ ";'>"+ (Main.game.getPlayer().getLastLitterBirthed().getSonsFromFather() > 1
								? Main.game.getPlayer().getLastLitterBirthed().getFatherRace().getOffspringMaleName()+ "</b>, who have their father's features."
								: Main.game.getPlayer().getLastLitterBirthed().getFatherRace().getOffspringMaleNameSingular()+ "</b>, who has his father's features."));
			}
			
			if (Main.game.getPlayer().getLastLitterBirthed().getSonsFromMother() > 0) {
				litterSB.append("</br><b>"
						+ Util.capitaliseSentence(Util.intToString(Main.game.getPlayer().getLastLitterBirthed().getSonsFromMother()))
						+ "</b>");

				switch (Main.game.getPlayer().getLastLitterBirthed().getMotherRace()) {
					case ANGEL:
						litterSB.append(" radiant");
						break;
					case CAT_MORPH:
						litterSB.append(" good-looking");
						break;
					case DEMON:
						litterSB.append(" mischievous");
						break;
					case DOG_MORPH:
						litterSB.append(" smiling");
						break;
					case HARPY:
						litterSB.append(" feminine");
						break;
					case HORSE_MORPH:
						litterSB.append(" strong");
						break;
					case HUMAN:
						litterSB.append(" smiling");
						break;
					case SLIME:
						litterSB.append(" bubbly");
						break;
					case WOLF_MORPH:
						litterSB.append(" powerful");
						break;
				}
				litterSB.append(" <b style='color:"+ Colour.MASCULINE.toWebHexString()+ ";'>"+ (Main.game.getPlayer().getLastLitterBirthed().getSonsFromMother() > 1
								? Main.game.getPlayer().getLastLitterBirthed().getMotherRace().getOffspringMaleName()+ "</b>, who have your features."
								: Main.game.getPlayer().getLastLitterBirthed().getMotherRace().getOffspringMaleNameSingular()+ "</b>, who has your features."));
			}
			
			if (Main.game.getPlayer().getLastLitterBirthed().getDaughtersFromFather() > 0) {
				litterSB.append("</br><b>"
						+ Util.capitaliseSentence(Util.intToString(Main.game.getPlayer().getLastLitterBirthed().getDaughtersFromFather()))
						+ "</b>");

				switch (Main.game.getPlayer().getLastLitterBirthed().getFatherRace()) {
					case ANGEL:
						litterSB.append(" radiant");
						break;
					case CAT_MORPH:
						litterSB.append(" pretty");
						break;
					case DEMON:
						litterSB.append(" cheeky");
						break;
					case DOG_MORPH:
						litterSB.append(" playful");
						break;
					case HARPY:
						litterSB.append(" feminine");
						break;
					case HORSE_MORPH:
						litterSB.append(" confident");
						break;
					case HUMAN:
						litterSB.append(" smiling");
						break;
					case SLIME:
						litterSB.append(" bubbly");
						break;
					case WOLF_MORPH:
						litterSB.append(" grinning");
						break;
				}
				litterSB.append(" <b style='color:"+ Colour.FEMININE.toWebHexString()+ ";'>"+ (Main.game.getPlayer().getLastLitterBirthed().getDaughtersFromFather() > 1
							? Main.game.getPlayer().getLastLitterBirthed().getFatherRace().getOffspringFemaleName()+ "</b>, who have their father's features."
							: Main.game.getPlayer().getLastLitterBirthed().getFatherRace().getOffspringFemaleNameSingular()+ "</b>, who has her father's features."));
			}
			
			if (Main.game.getPlayer().getLastLitterBirthed().getDaughtersFromMother() > 0) {
				litterSB.append("</br><b>"
						+ Util.capitaliseSentence(Util.intToString(Main.game.getPlayer().getLastLitterBirthed().getDaughtersFromMother()))
						+ "</b>");

				switch (Main.game.getPlayer().getLastLitterBirthed().getMotherRace()) {
					case ANGEL:
						litterSB.append(" radiant");
						break;
					case CAT_MORPH:
						litterSB.append(" pretty");
						break;
					case DEMON:
						litterSB.append(" cheeky");
						break;
					case DOG_MORPH:
						litterSB.append(" playful");
						break;
					case HARPY:
						litterSB.append(" feminine");
						break;
					case HORSE_MORPH:
						litterSB.append(" confident");
						break;
					case HUMAN:
						litterSB.append(" smiling");
						break;
					case SLIME:
						litterSB.append(" bubbly");
						break;
					case WOLF_MORPH:
						litterSB.append(" grinning");
						break;
				}
				litterSB.append(" <b style='color:"+ Colour.FEMININE.toWebHexString()+ ";'>"+ (Main.game.getPlayer().getLastLitterBirthed().getDaughtersFromMother() > 1
							? Main.game.getPlayer().getLastLitterBirthed().getMotherRace().getOffspringFemaleName()+ "</b>, who have your features."
							: Main.game.getPlayer().getLastLitterBirthed().getMotherRace().getOffspringFemaleNameSingular()+ "</b>, who has your features."));
			}

			litterSB.append("</p>"
					+ "<p>"
					+ "After taking a minute to get your emotions under control, you put the picture away for safe-keeping, and think about what to do next."
					+ "</p>");

			return litterSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Get up", "Get out of bed, ready for a new day.", RoomPlayer.ROOM);

			} else {
				return null;
			}
		}
	};
}
