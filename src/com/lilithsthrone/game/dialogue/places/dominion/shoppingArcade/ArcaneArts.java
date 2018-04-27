package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.SMVickyOverDesk;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.2.4
 * @author Innoxia
 */
public class ArcaneArts {
	
	public static final DialogueNodeOld EXTERIOR = new DialogueNodeOld("Arcane Arts (Exterior)", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in front of a rather peculiar shop."
						+ " Although there's a sign declaring 'Arcane Arts' to be open, the interior is very dimly-lit, and you notice that most of the Arcade's shoppers are giving this particular store a wide berth."
						+ " Wanting to take a closer look, you walk up to the entrance, and, looking through the glass into the shop's gloomy interior, you see signs of movement behind the counter."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Step inside Arcane Arts.", SHOP_WEAPONS);
				
			} else if (index == 6) {
				return new Response("Arcade Entrance", "Fast travel to the entrance to the arcade.", ShoppingArcadeDialogue.ENTRY){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_WEAPONS = new DialogueNodeOld("Arcane Arts", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vickyIntroduced)) {
				return "<p>"
						+ "Pushing open the door to Arcane Arts once again, your arrival is announced by the familiar sound of a little bell ringing above you."
						+ " Just like the other times you've visited, only one other person is inside; the greater wolf-girl Vicky, who's standing behind the shop's counter."
					+ "</p>"
					+ "<p>"
						+ "As the door swings shut behind you, Vicky's yellow eyes lock onto yours, and her mouth turns up into an unsettling predatory grin."
						+ " Even though she's only a few metres away, and you're the only person in the shop, she doesn't utter a single word of greeting, and you find that the shop's already-gloomy atmosphere has quickly become extremely oppressive."
						+ " As you step forwards to say hello, the wolf-girl suddenly cuts you off, "
						+ "[vicky.speech(If you aren't here to waste my time, I keep the good stuff under the counter here. Want to take a look again?)]"
					+ "</p>"
					+ "<p>"
						+ "The toothy, menacing grin on Vicky's wolf-like face is extremely worrying, but surely she isn't going to attack a customer..."
					+ "</p>";
				
			} else {
				return "<p>"
						+ "Pushing open the door, your arrival is announced by the sound of a little bell ringing above you."
						+ " There's only one other person inside; a greater wolf-girl, who's standing behind the shop's counter."
					+ "</p>"
					+ "<p>"
						+ "As the door swings shut behind you, the wolf-girl's yellow eyes lock onto yours, and her mouth turns up into an unsettling predatory grin."
						+ " Even though she's only a few metres away, and you're the only person in the shop, she doesn't utter a single word of greeting, and you find that the shop's already-gloomy atmosphere has quickly become extremely oppressive."
						+ " You glance around, seeing that there are only odd trinkets and pieces of 'enchanted' junk littering the messy shelves."
					+ "</p>"
					+ "<p>"
						+ "You start to back off towards the door, but as you do, the wolf-girl finally speaks, "
						+ "[vicky.speech(If you aren't here to waste my time, I keep the good stuff under the counter here. I'm Vicky by the way, welcome to my shop...)]"
					+ "</p>"
					+ "<p>"
						+ "You look across to the wolf-girl to see that she's beckoning you over to her."
						+ " The toothy grin on her wolf-like face is extremely worrying, but surely she isn't going to attack a customer..."
					+ "</p>";
			}
			
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseTrade("Trade with Vicky", "Walk over to the counter and see what crystals and feathers Vicky has in stock.", Main.game.getVicky()) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH)==Quest.SIDE_HYPNO_WATCH_VICKY) {
						if(Main.game.getPlayer().isInventoryFull()) {
							return new Response("Arthur's package", "You don't have enough room in your inventory for the package!", null);
							
						} else {
							return new Response("Arthur's package", "Tell Vicky that you're here to collect Arthur's package.", ARTHURS_PACKAGE) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
								}
							};
						}
						
					} else {
						return null;
					}
				} else {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina())) {
						return new ResponseSex("Offer body", "Let Vicky use your body.",
								Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVickyOverDesk(
										Util.newHashMapOfValues(new Value<>(Main.game.getVicky(), SexPositionSlot.MISSIONARY_DESK_DOM_VICKY)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_DESK_SUB_VICKY))),
								VICKY_POST_SEX,
									"<p>"
										+ "[pc.speech(I was wondering... If you'd like to use me...)]"
										+ " you say, looking sheepishly up at Vicky."
									+ "</p>"
									+ "<p>"
										+ "[vicky.speech(Sounds good to me, bitch,)]"
										+ " she growls, before leaping over the counter, roughly grabbing you by the [pc.arms], and shoving you towards the store's counter, before turning and locking the front door,"
										+ " [vicky.speech(I love fucking submissive customers like you.)]"
									+ "</p>"
									+ "<p>"
										+ "Before you can reply, Vicky bounds over towards you, before grabbing you by the waist and hurling you back onto the counter-top."
										+ " Stepping forwards, she positions herself between your legs, making a point to grind her huge erection up against your crotch as she snarls,"
										+ " [vicky.speech(I hope you like it rough!)]"
									+ "</p>");
					} else {
						return new Response("Offer body", "Vicky needs to be able to access your anus"+(Main.game.getPlayer().hasVagina()?" or vagina":"")+"!", null);
					}
				}
				
			} else if (index == 3 && Main.getProperties().hasValue(PropertyValue.nonConContent) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina())) {
					
					return new ResponseSex("Nervously leave", "Vicky is far too intimidating for you... Turn around and try to escape from her gaze. [style.boldBad(You get the feeling that this will result in non-consensual sex...)]",
							Util.newArrayListOfValues(
									new ListValue<>(Fetish.FETISH_SUBMISSIVE),
									new ListValue<>(Fetish.FETISH_NON_CON_SUB)), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							false, false,
							new SMVickyOverDesk(
									Util.newHashMapOfValues(new Value<>(Main.game.getVicky(), SexPositionSlot.MISSIONARY_DESK_DOM_VICKY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_DESK_SUB_VICKY))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							VICKY_POST_SEX_RAPE,
							"<p>"
								+ "Feeling more than a little intimidated by the overbearing wolf-girl's attitude, you try to back away towards the front door, muttering,"
								+ " [pc.speech(E-Erm.. M-Maybe I'll come back later...)]"
							+ "</p>"
							+ "<p>"
								+ "Sensing your weakness, Vicky suddenly leaps over the counter and pounces on you."
								+ " Grabbing hold of your shoulders, and, with considerable force, slamming you back against the wall, she snarls,"
								+ " [vicky.speech(So you came to waste my time, huh? I don't put up with that shit!)]"
							+ "</p>"
							+ "<p>"
								+ "You let out a startled cry as the aggressive wolf-girl suddenly throws you to the floor."
								+ " Looking up as you struggle to your feet, you see Vicky quickly locking the the front door, before she turns around and bounds over to you,"
								+ " [vicky.speech(You're just begging to be fucked!)]"
							+ "</p>"
							+ "<p>"
								+ "[pc.speech(W-Wait! No! L-Let me go! I- ~Aaah!~)]"
								+ " Your protests are cut short as Vicky grabs you by the waist and hurls you back onto the counter-top."
							+ "</p>"
							+ "<p>"
								+ "Stepping forwards, she positions herself between your legs, making a point to grind her huge erection up against your crotch as she snarls,"
								+ " [vicky.speech(Scream and cry as much as you want! I hope you like it rough, because I don't go easy with pathetic bitches like you!)]"
							+ "</p>");
					
				} else {
					return new Response("Nervously leave", "Vicky needs to be able to access your anus"+(Main.game.getPlayer().hasVagina()?" or vagina":"")+"!", null);
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Leave Arcane Arts and head back out into the arcade.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ARTHURS_PACKAGE = new DialogueNodeOld("Arcane Arts", "-", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Actually, I'm here to collect a package,)]"
						+ " you say, stepping towards the shop's front desk,"
						+ " [pc.speech(it should be addressed to 'Arthur'...)]"
					+ "</p>"
					+ "<p>"
						+ "You allow your voice to trail off as a low, rumbling growl starts to fill the room."
						+ " Vicky narrows her eyes a little, continuing to emit a menacing snarl as she allows her gaze to wander up and down your body."
						+ " Feeling more than a little concerned with the wolf-girl's behaviour, you take a step back, preparing yourself in case she decides to attack."
					+ "</p>"
					+ "<p>"
						+ "[vicky.speech(Don't worry, I don't bite,)]"
						+ " she snaps, thankfully bringing her intimidating growls to an end, before bending down to retrieve something from behind her desk."
						+ " As she crouches out of sight, you hear a faint snarl as Vicky finishes her sentence,"
						+ " [vicky.speech(much...)]"
					+ "</p>"
					+ "<p>"
						+ "After just a moment, she suddenly springs to her feet, before placing a small cardboard box down on the desk in front of her."
						+ " Looking into your eyes once more, she snarls,"
						+ " [vicky.speech(I've been holding on to this for some time, you know."
							+ " It's quite <i>frustrating</i> when people don't collect their orders immediately.)]"
					+ "</p>"
					+ "<p>"
						+ "Without warning, Vicky suddenly grabs hold of the desk, and in one swift, alarming manoeuvre, vaults over the counter-top and pounces towards you."
						+ " Caught with your back against the wall, you suddenly find yourself face-to-face with the aggressive wolf-girl."
						+ " Showing a complete lack of respect for your personal space, she roughly presses her body right up against yours, staring hungrily into your eyes as she growls,"
						+ " [vicky.speech(It's so frustrating, in fact, that I'm going to need some <i>compensation</i>."
							+ " It's a one-hundred flame fee for late collections like this. If you can't pay... I'm sure we can work <i>something</i> out...)]"
					+ "</p>"
					+ "<p>"
						+ "Vicky presses herself close to you, and you feel her hot breath falling on your neck as she starts letting out a low growl once again."
						+ " As she traps you against the wall, you suddenly feel something hard, and alarmingly-large, pressing against your leg."
						+ " Looking down, you see a huge erection tenting out the fabric of the wolf-girl's miniskirt, which she then starts to dominantly rub up against you,"
						+ " [vicky.speech(So what's it going to be? You got the flames? Or am I going to have to fuck the payment out of you?)]"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()>=100) {
					return new Response("Pay ("+UtilText.formatAsMoney(100, "span")+")", "Pay Vicky 100 flames.", ARTHURS_PACKAGE_BOUGHT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.arthursPackageObtained, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.ARTHURS_PACKAGE), false, true));
							Main.game.getPlayer().incrementMoney(-100);
						}
					};
				} else {
					return new Response("Pay ("+UtilText.formatAsMoneyUncoloured(100, "span")+")", "You don't have enough money to pay the fee!", null);	
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina())) {
					return new ResponseSex("Offer body", "Let Vicky use your body as payment for the fee.",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, CorruptionLevel.TWO_HORNY, null, null, null,
							true, false,
							new SMVickyOverDesk(
									Util.newHashMapOfValues(new Value<>(Main.game.getVicky(), SexPositionSlot.MISSIONARY_DESK_DOM_VICKY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_DESK_SUB_VICKY))),
							VICKY_POST_SEX_PACKAGE,
								"<p>"
									+ "[pc.speech(That second option doesn't sound so bad...)]"
									+ " you say, grinning up at Vicky as she roughly presses you against the wall."
								+ "</p>"
								+ "<p>"
									+ "[vicky.speech(Suits me, bitch,)]"
									+ " she growls, before grabbing you by the [pc.arms] and shoving you towards the store's counter, before turning and locking the front door,"
									+ " [vicky.speech(I love fucking submissive customers like you.)]"
								+ "</p>"
								+ "<p>"
									+ "Before you can reply, Vicky bounds over towards you, before grabbing you by the waist and hurling you back onto the counter-top."
									+ " Stepping forwards, she positions herself between your legs, making a point to grind her huge erection up against your crotch as she snarls,"
									+ " [vicky.speech(I hope you like it rough!)]"
								+ "</p>");
				} else {
					return new Response("Offer body", "Vicky needs to be able to access your anus"+(Main.game.getPlayer().hasVagina()?" or vagina":"")+"!", null);
				}
				
			} else if (index == 3 && Main.getProperties().hasValue(PropertyValue.nonConContent)) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina())) {
					
					return new ResponseSex("Weakly refuse", "You can't bring yourself to say no to such an intimidating person... Try to wriggle free and leave... [style.boldBad(You get the feeling that this will result in non-consensual sex...)]",
							Util.newArrayListOfValues(
									new ListValue<>(Fetish.FETISH_SUBMISSIVE),
									new ListValue<>(Fetish.FETISH_NON_CON_SUB)), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							false, false,
							new SMVickyOverDesk(
									Util.newHashMapOfValues(new Value<>(Main.game.getVicky(), SexPositionSlot.MISSIONARY_DESK_DOM_VICKY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_DESK_SUB_VICKY))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							VICKY_POST_SEX_RAPE_PACKAGE,
							"<p>"
								+ "Feeling more than a little intimidated by the overbearing wolf-girl's advances, you try to slip away, muttering,"
								+ " [pc.speech(E-Erm.. M-Maybe I'll come back later...)]"
							+ "</p>"
							+ "<p>"
								+ "Sensing your weakness, Vicky grabs hold of your shoulders, and, with considerable force, slams you back against the wall, snarling,"
								+ " [vicky.speech(Playing hard-to-get, huh? I don't have time for that shit!)]"
							+ "</p>"
							+ "<p>"
								+ "You let out a startled cry as the aggressive wolf-girl suddenly throws you to the floor."
								+ " Looking up as you struggle to your feet, you see Vicky quickly locking the the front door, before she turns around and bounds over to you,"
								+ " [vicky.speech(You're just begging to be fucked!)]"
							+ "</p>"
							+ "<p>"
								+ "[pc.speech(W-Wait! No! L-Let me go! I- ~Aaah!~)]"
								+ " Your protests are cut short as Vicky grabs you by the waist and hurls you back onto the counter-top."
							+ "</p>"
							+ "<p>"
								+ "Stepping forwards, she positions herself between your legs, making a point to grind her huge erection up against your crotch as she snarls,"
								+ " [vicky.speech(Scream and cry as much as you want! I hope you like it rough, because I don't go easy with pathetic bitches like you!)]"
							+ "</p>");
					
				} else {
					return new Response("Weakly refuse", "Vicky needs to be able to access your anus"+(Main.game.getPlayer().hasVagina()?" or vagina":"")+"!", null);
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Leave Arcane Arts and head back out into the arcade.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
						Main.game.getTextEndStringBuilder().append(
								"<p>"
									+ "Not at all amused by Vicky's little performance, you look her straight in the eyes, and, firmly pushing her away, you state,"
									+ " [pc.speech(I'm leaving now. I'll be back later for the package.)]"
								+ "</p>"
								+ "<p>"
									+ "Not giving the wolf-girl any time to respond, you stride over to the door, pull it open, and step out into the Shopping Arcade."
								+ "</p>");
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ARTHURS_PACKAGE_BOUGHT = new DialogueNodeOld("Arcane Arts", "-", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(I can pay the fee,)]"
						+ " you say, side-stepping the wolf-girl before producing one-hundred flames."
					+ "</p>"
					+ "<p>"
						+ "Vicky looks noticeably disappointed as you hand over the money, and, after walking back around to the correct side of the counter, she pushes the package towards you,"
						+ " [vicky.speech(Go on then, it's all yours."
							+ " If you wanted to take a look at my goods, or if you ever get curious about what you just missed out on, then let me know.)]"
					+ "</p>"
					+ "<p>"
						+ "Vicky leans back against the wall as you take the package, before fixing her gaze onto your face as she waits to see what you'll do next..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNodeOld VICKY_POST_SEX_PACKAGE = new DialogueNodeOld("Arcane Arts", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Vicky steps back, grinning down at you as she growls,"
						+ " [vicky.speech(You're a pretty good fuck."
							+ " I guess this counts as payment for your package."
							+ " If you wanted to take a look at my goods, or if you ever want to be my bitch again, then let me know.)]"
					+ "</p>"
					+ "<p>"
						+ "The wolf-girl strides over to the store's front door, barely giving you a moment to get your things in order before unlocking it and walking back behind her counter."
						+ " You quickly finish gathering your things, and, taking the package from on top of the counter, you prepare to make your next move."
						+ " Vicky leans back against the wall, fixing her gaze onto your face as she waits to see what you'll do next..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld VICKY_POST_SEX_RAPE_PACKAGE = new DialogueNodeOld("Arcane Arts", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "Vicky steps back, grinning down at you as she growls,"
					+ " [vicky.speech(You're a pretty good fuck; I love it when bitches like you try to put up a fight."
						+ " I guess this counts as payment for your package."
						+ " If you wanted to take a look at my goods, or if you ever want to be my bitch again, then let me know.)]"
				+ "</p>"
				+ "<p>"
					+ "The wolf-girl strides over to the store's front door, barely giving you a moment to get your things in order before unlocking it and walking back behind her counter."
					+ " You quickly finish gathering your things, and, taking the package from on top of the counter, you prepare to make your next move."
					+ " Vicky leans back against the wall, fixing her gaze onto your face as she waits to see what you'll do next..."
				+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNodeOld VICKY_POST_SEX = new DialogueNodeOld("Arcane Arts", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Vicky steps back, grinning down at you as she growls,"
						+ " [vicky.speech(You're a pretty good fuck."
							+ " If you wanted to take a look at my goods, or if you ever want to be my bitch again, then let me know.)]"
					+ "</p>"
					+ "<p>"
						+ "The wolf-girl strides over to the store's front door, barely giving you a moment to get your things in order before unlocking it and walking back behind her counter."
						+ " You quickly finish gathering your things, and, scrambling to your [pc.feet], you prepare to make your next move."
						+ " Vicky leans back against the wall, fixing her gaze onto your face as she waits to see what you'll do next..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld VICKY_POST_SEX_RAPE = new DialogueNodeOld("Arcane Arts", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "Vicky steps back, grinning down at you as she growls,"
					+ " [vicky.speech(You're a pretty good fuck; I love it when bitches like you try to put up a fight."
						+ " If you wanted to take a look at my goods, or if you ever want to be my bitch again, then let me know.)]"
				+ "</p>"
				+ "<p>"
					+ "The wolf-girl strides over to the store's front door, barely giving you a moment to get your things in order before unlocking it and walking back behind her counter."
					+ " You quickly finish gathering your things, and, taking the package from on top of the counter, you prepare to make your next move."
					+ " Vicky leans back against the wall, fixing her gaze onto your face as she waits to see what you'll do next..."
				+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
}
