package com.lilithsthrone.game.dialogue.story;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.1
 * @author Innoxia
 */
public class PrologueDialogue {

//	public static final DialogueNodeOld INTRO = new DialogueNodeOld("", "", true) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public String getContent() {
//			return "<h1 class='specialText'>Lilith's Throne</h1>"
//					+ "<h5 class='specialText'>An erotic fantasy game by Innoxia</h5>"
//					+ "</br>"
//
//					+ "<p>"
//					+ "So, it is as you feared. The opening party for the museum's new exhibition is <i>really</i> boring."
//					+ " As you stifle a yawn, you remind yourself that you're wasting your Friday evening because you promised your aunt Lily that you'd be here to support her."
//					+ "</p>"
//
//					+ "<p>"
//					+ "You're in the lobby of the city museum, surrounded by the dullest collection of historians and professors you've ever encountered."
//					+ " Everyone's waiting here for Lily's colleague, Arthur, to show up so that he can give a speech about the new exhibit that's opening tonight."
//					+ " He's five minutes late already, and you see Lily shuffling about nervously off-stage as the crowd grows more and more impatient."
//					+ "</p>"
//
//					+ "<p>"
//					+ "You last saw Arthur offering to show some guests around the exhibit, and, knowing him, he must have got carried away and lost track of time."
//					+ " You know that if he doesn't turn up soon, Lily will really start to worry and get upset."
//					+ " Perhaps you should go and find him. After all, the sooner he gives his speech, the sooner you can leave and salvage what remains of your Friday night."
//					+ "</p>";
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Search", "Go and search the museum for Arthur.", INTRO_2);
//			} else if (index == 2) {
//				return new Response("Wait", "Wait patiently for Arthur to turn up. Surely he won't be too much longer...", INTRO_1A);
//			} else {
//				return null;
//			}
//		}
//	};
//
//	public static final DialogueNodeOld INTRO_1A = new DialogueNodeOld("", "", true, true) {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public String getContent() {
//			return UtilText.parseNPCSpeech("Oh I say, you look like the sort who's read Morrison's latest theory about trilingualism in the Akkadian Empire."
//					+ " What are your thoughts on his new research?", Femininity.MASCULINE)
//
//					+ "<p>You turn your head to see an elderly-looking man staring right at you, waiting for a response to his question."
//					+ " Several of the other guests are looking at you closely, curious to hear your thoughts on the matter.</p>"
//
//					+ "<p>"
//					+ "[pc.speech(Oh, sorry, I'm here by invite of my aunt Lily. I don't actually know anything about history or this exhibit.)]"
//					+ "</p>"
//
//					+ "<p>A smile spreads across the man's face as he realises he's found someone to lecture. You only just manage to stop yourself from letting out a"
//					+ " despairing groan as he begins his lesson, "
//					+ UtilText.parseNPCSpeech("Well, allow me to educate you then! As Morrison has so brilliantly observ-", Femininity.MASCULINE)
//					+ "</p>"
//
//					+ "<p>A lady standing close by suddenly interrupts the start of his speech, "
//					+ UtilText.parseNPCSpeech("Brilliant?! Hardly! There's no way his research is accurate, I think you'll find tha-", Femininity.FEMININE)
//					+ "</p>"
//
//					+ "<p>The man turns to her and swiftly cuts off her objections, "
//					+ UtilText.parseNPCSpeech("Bah! I bet you're the sort who actually considers Smith's twenty-volume work to be...", Femininity.MASCULINE)
//					+ "</p>"
//
//					+ "<p>Sensing your moment to flee, you step back from the bickering pair and swiftly escape from the crowd.</p>";
//		}
//		
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Search", "Go and search the museum for Arthur.", INTRO_2);
//			} else {
//				return null;
//			}
//		}
//	};
	
	private static boolean femalePrologueNPC() {
		return CharacterCreation.femalePrologueNPC();
	}
	
	public static final DialogueNodeOld INTRO = new DialogueNodeOld("In the Museum", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(femalePrologueNPC()) {
				return "<p>"
							+ "As the two of you talk about your sexual backgrounds, you find yourself increasingly unable to control your arousal, and from the desperate look in [prologueFemale.name]'s eyes, she's having the same problem as you are."
							+ " Stepping forwards, she half-speaks, half-moans,"
							+ " [prologueFemale.speech(All this talk's got me pretty turned on... I don't usually do things like this, but everyone's busy around the stage right now. I'm sure one of the rooms around here is empty, if you want to have some fun...)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.Name] is right; everyone who's present for the event seems to be clustered around the stage on the far side of the room."
							+ " There'd be nothing stopping you and her from sneaking off and having a little fun in one of the empty rooms..."
						+ "</p>";
				
			} else {
				return "<p>"
							+ "As the two of you talk about your sexual backgrounds, you find yourself increasingly unable to control your arousal, and from the desperate look in [prologueMale.name]'s eyes, he's having the same problem as you are."
							+ " Stepping forwards, he half-speaks, half-groans,"
							+ " [prologueMale.speech(All this talk's got me pretty turned on... I don't usually do things like this, but everyone's busy around the stage right now. I'm sure one of the rooms around here is empty, if you want to have some fun...)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.Name] is right; everyone who's present for the event seems to be clustered around the stage on the far side of the room."
							+ " There'd be nothing stopping you and her from sneaking off and having a little fun in one of the empty rooms..."
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Agree", "Overwhelmed with arousal, you decide to agree to go and have some fun.", INTRO_EMPTY_ROOM);
			} else if (index == 2) {
				return new Response("Say No", "You don't think it's a good idea to sneak off and have sex when you're supposed to be here to see your aunt Lily. Say no.", INTRO_NO);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTRO_EMPTY_ROOM = new DialogueNodeOld("In the Museum", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(femalePrologueNPC()) {
				return "<p>"
							+ "Grinning at [prologueFemale.name], you reach down and take hold of her hand, before leading her off towards a quiet, shaded doorway nearby,"
							+ " [pc.speech(This looks like it'll be empty...)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.Name] giggles at your observation, before skipping forwards and moaning into your ear,"
							+ " [prologueFemale.speech(~Mmm!~ This is going to be good!)]"
						+ "</p>"
						+ "<p>"
							+ "Ignoring the sign which reads 'Staff Only', you push the heavy oaken door ajar and peer inside, smiling to yourself as you discover that the room beyond is pitch black; proof that it's completely empty."
							+ " Pulling [prologueFemale.name] inside, you flick the lights on as you close the door behind you."
						+ "</p>"
						+ "<p>"
							+ "The room that you find yourself in appears to be some sort of office,"
								+ " and from the numerous ceiling-height bookshelves lining the walls, the rich mahogany desk, and the pair of expensive-looking leather sofas, you guess that it belongs to someone very important."
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.speech(I've never been fucked in an office as nice as this one before,)]"
							+ " [prologueFemale.name] giggles again, before stepping forwards and pressing her full, plump lips against yours."
							+ " Instantly, she slides her tongue into your mouth, reaching up to take hold of your head as you passionately start to kiss one another..."
						+ "</p>";
				
			} else {
				return "<p>"
							+ "Grinning at [prologueMale.name], you allow him to reach down and take hold of your hand, before being led off towards a quiet, shaded doorway nearby,"
							+ " [prologueMale.speech(This looks like it'll be empty...)]"
						+ "</p>"
						+ "<p>"
							+ "You can't help but smile at his observation, before skipping forwards and [pc.moaning] into his ear,"
							+ " [pc.speech(~Mmm!~ This is going to be good!)]"
						+ "</p>"
						+ "<p>"
							+ "Ignoring the sign which reads 'Staff Only', [prologueMale.name] pushes the heavy oaken door ajar and peers inside, smiling to himself as you both see that the room beyond is pitch black; proof that it's completely empty."
							+ " Pulling you inside, he flicks the lights on before closing the door behind you."
						+ "</p>"
						+ "<p>"
							+ "The room that you find yourself in appears to be some sort of office,"
								+ " and from the numerous ceiling-height bookshelves lining the walls, the rich mahogany desk, and the pair of expensive-looking leather sofas, you guess that it belongs to someone very important."
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.speech(I've never fucked anyone in an office as nice as this one before,)]"
							+ " [prologueMale.name] grins, before stepping forwards and pressing his lips against yours."
							+ " Instantly, he pushes his tongue into your mouth, reaching around to squeeze your [pc.ass+] as you passionately start to kiss one another..."
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(femalePrologueNPC()) {
					return new ResponseSex("Sex", "Give in to your lust and start having sex with [prologueFemale.name]...",
							null, null, null,
							null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPrologueFemale(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX,
							(Main.game.getPlayer().hasPenis()
								?"<p>"
									+ "You pull [prologueFemale.name] into your [pc.breasts], desperately [pc.moaning] into her mouth as you dominantly grope and fondle [npc.her] body."
									+ " Breaking off the kiss for a moment, she pulls something out of her purse, before pushing it into your hands,"
									+ " [prologueFemale.speech(I'd rather you didn't, but if you want to, you can wear this.)]"
								+ "</p>"
								+ "<p>"
									+ "You look down to see that you're holding a little square packet - a condom."
								+ "</p>"
								+"<div class='container-full-width' style='text-align:center;'>"
									+ "<i>If you'd like to use the condom, open your inventory (by pressing 'i') and select it from the menu.</br>"
									+ "(Your penis will need to be exposed before it can be used!)</i>"
								+ "</div>"
								:"<p>"
									+ "You pull [prologueFemale.name] into your [pc.breasts], desperately [pc.moaning] into her mouth as you dominantly grope and fondle [npc.her] body..."
								+ "</p>")
							+"<div class='container-full-width' style='text-align:center;'>"
								+ "<i>You can click on both your and [prologueFemale.name]'s clothing (in the bottom-left and bottom-right of the screen) to manage clothing during sex!</i>"
							+ "</div>") {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPenis()) {
								Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.PENIS_CONDOM, Colour.CLOTHING_BLACK, false), false);
							}
						}
					};
					
				} else {
					return new ResponseSex("Sex", "Give in to your lust and start having sex with [prologueMale.name]...",
							null, null, null,
							null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPrologueMale(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX,
							"<p>"
								+ "You allow [prologueMale.name] to pull you into his strong, masculine torso, desperately moaning into his mouth as he dominantly gropes and fondles your body."
								+ " Breaking off the kiss for a moment, he pulls something out of his pocket, before pushing it into your hands,"
								+ " [prologueMale.speech(I'd rather not, but if you want me to, I can wear this.)]"
							+ "</p>"
							+ "<p>"
								+ (Main.game.getPlayer().hasPenis()
										?"You look down to see that you're holding a couple of little square packets - a pair of condoms."
										:"You look down to see that you're holding a little square packet - a condom.")
							+ "</p>"
							+"<div class='container-full-width' style='text-align:center;'>"
								+ "<i>If you'd like to use the condom, open your inventory (by pressing 'i') and select it from the menu.</br>"
									+ "([prologueMale.name]'s penis will need to be exposed before it can be used!)</i>"
							+ "</div>"
							+"<div class='container-full-width' style='text-align:center;'>"
								+ "<i>You can click on both your and [prologueMale.name]'s clothing (in the bottom-left and bottom-right of the screen) to manage clothing during sex!</i>"
							+ "</div>") {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPenis()) {
								Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.PENIS_CONDOM, Colour.CLOTHING_BLACK, false), false);
							}
							Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.PENIS_CONDOM, Colour.CLOTHING_BLACK, false), false);
						}
					};
				}
				
			} else if (index == 2) {
				return new Response("Second Thoughts", "Decide that this is a bad idea after all, and put an end to this.", INTRO_SECOND_THOUGHTS);
				
			} else {
				return null;
			}
		}
	};
	

	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("In the Museum", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				if(Sex.getNumberOfOrgasms(Main.game.getPrologueFemale())>0) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[prologueFemale.Name] sinks down onto one of the leather sofas with a sigh,"
								+ " [prologueFemale.speech(Fuck... That was good... I'm going to need some time to rest...)]"
							+ "</p>"
							+ "<p>"
								+ "Grinning down at her as her breasts heave up and down from exertion, you reply,"
								+ " [pc.speech(Yeah, that was good. I'll go on ahead, I'll catch up with you later!)]"
							+ "</p>");
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[prologueFemale.Name] sinks down onto one of the leather sofas with a sigh,"
								+ " [prologueFemale.speech(Fuck... I didn't even get to orgasm... I'm going to finish myself off...)]"
							+ "</p>"
							+ "<p>"
								+ "Grinning down at her as she starts frantically fingering herself, you reply,"
								+ " [pc.speech(Yeah, sure. I'll catch up with you later!)]"
							+ "</p>");
				}
				
			} else {
				if(Sex.getNumberOfOrgasms(Main.game.getPrologueMale())>0) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[prologueMale.Name] sinks down onto one of the leather sofas with a sigh,"
								+ " [prologueMale.speech(Fuck... That was good... I'm going to need some time to rest...)]"
							+ "</p>"
							+ "<p>"
								+ "Grinning down at him as his chest heaves up and down from exertion, you reply,"
								+ " [pc.speech(Yeah, that was good. I'll go on ahead, I'll catch up with you later!)]"
							+ "</p>");
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[prologueMale.Name] sinks down onto one of the leather sofas with a sigh,"
								+ " [prologueMale.speech(Fuck... I didn't even get to cum... I'm going to finish myself off...)]"
							+ "</p>"
							+ "<p>"
								+ "Grinning down at him as he starts frantically masturbating, you reply,"
								+ " [pc.speech(Yeah, sure. I'll catch up with you later!)]"
							+ "</p>");
				}
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Leaving your partner behind, you step out of the office, before heading off towards the stage."
						+ " You don't even manage to get close, however, as after you've taken no more than ten steps, you see your aunt Lily wandering about in front of you."
						+ " From the way she's frantically wringing her hands and glancing this way and that, you can tell that she's been flustered by something."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Hi Lily!)]"
						+ " You call out, walking towards her."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech([pc.Name]! There you are! I've been looking everywhere for you,)]"
						+ " your aunt calls out, bounding over towards you and sighing with relief,"
						+ " [lilaya.speech(I really need your help!)]"
					+ "</p>"
					+ "<p>"
						+ "Giving her a reassuring smile, you reply,"
						+ " [pc.speech(Don't worry Lily, I can help. Is this about the opening being delayed?)]"
					+ "</p>"
					+ "<p>"
						+ " [lilaya.speech(Yes! This is to do with the delay! Arthur's gone missing, and I can't start without him! Everyone's crowding me asking questions, so I can't go out and look for him myself."
							+ " Please, please <i>please</i> can you go and find him for me?)]"
					+ "</p>"
					+ "<p>"
						+ "Lily's puppy-dog eyes always were too much for you to say no to, and you find yourself agreeing to do as she asks,"
						+ " [pc.speech(Ok Lily, I'll find him for you, don't worry.)]"
					+ "</p>"
					+ "<p>"
						+ "As Lily starts thanking you, someone calls out from behind her, asking her to return to the stage."
						+ " Thanking you once more, she then rushes off, leaving you to do as you said you would..."
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Search", "Go and search the museum for Arthur.", INTRO_2) {
					@Override
					public void effects() {
						CharacterCreation.moveNPCOutOfPlayerTile();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNodeOld INTRO_SECOND_THOUGHTS = new DialogueNodeOld("In the Museum", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "In a sudden moment of clarity, you realise that what you're about to do isn't a very good idea, and you step away from [prologueFemale.name],"
							+ " [pc.speech(I'm sorry, but on second thoughts, I don't think this a good idea. Anyway, I'm sure the presentation will be starting soon...)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.speech(Eugh... Fine...)]"
							+ " [prologueFemale.name] sighs, before collapsing down onto one of the leather sofas,"
							+ " [prologueFemale.speech(You go on ahead then, I need a moment to calm down.)]"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "In a sudden moment of clarity, you realise that what you're about to do isn't a very good idea, and you step away from [prologueMale.name],"
							+ " [pc.speech(I'm sorry, but on second thoughts, I don't think this a good idea. Anyway, I'm sure the presentation will be starting soon...)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.speech(Eugh... Fine...)]"
							+ " [prologueMale.name] sighs, before collapsing down onto one of the leather sofas,"
							+ " [prologueMale.speech(You go on ahead then, I need a moment to calm down.)]"
						+ "</p>");
			}

			UtilText.nodeContentSB.append(
					"<p>"
						+ "Leaving your partner behind, you step out of the office, before heading off towards the stage."
						+ " You don't even manage to get close, however, as after you've taken no more than ten steps, you see your aunt Lily wandering about in front of you."
						+ " From the way she's frantically wringing her hands and glancing this way and that, you can tell that she's been flustered by something."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Hi Lily!)]"
						+ " You call out, walking towards her."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech([pc.Name]! There you are! I've been looking everywhere for you,)]"
						+ " your aunt calls out, bounding over towards you and sighing with relief,"
						+ " [lilaya.speech(I really need your help!)]"
					+ "</p>"
					+ "<p>"
						+ "Giving her a reassuring smile, you reply,"
						+ " [pc.speech(Don't worry Lily, I can help. Is this about the opening being delayed?)]"
					+ "</p>"
					+ "<p>"
						+ " [lilaya.speech(Yes! This is to do with the delay! Arthur's gone missing, and I can't start without him! Everyone's crowding me asking questions, so I can't go out and look for him myself."
							+ " Please, please <i>please</i> can you go and find him for me?)]"
					+ "</p>"
					+ "<p>"
						+ "Lily's puppy-dog eyes always were too much for you to say no to, and you find yourself agreeing to do as she asks,"
						+ " [pc.speech(Ok Lily, I'll find him for you, don't worry.)]"
					+ "</p>"
					+ "<p>"
						+ "As Lily starts thanking you, someone calls out from behind her, asking her to return to the stage."
						+ " Thanking you once more, she then rushes off, leaving you to do as you said you would..."
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Search", "Go and search the museum for Arthur.", INTRO_2) {
					@Override
					public void effects() {
						CharacterCreation.moveNPCOutOfPlayerTile();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTRO_NO = new DialogueNodeOld("In the Museum", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Keeping your growing arousal under control, you step away from [prologueFemale.name],"
							+ " [pc.speech(I don't think that's a good idea. Anyway, I'm sure the presentation will be starting soo-)]"
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech([pc.Name]! There you are! I've been looking everywhere for you,)]"
							+ " a very familiar voice calls out, cutting your sentence short,"
							+ " [lilaya.speech(sorry if I'm interrupting, but I really need your help!)]"
						+ "</p>"
						+ "<p>"
							+ "Turning towards the unmistakable voice of your aunt Lily, you give her a reassuring smile,"
							+ " [pc.speech(Don't worry Lily, you're not interrupting."
								+ " Is this about the opening being delayed?)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.speech(I see you're going to be busy, perhaps I'll catch you later,)]"
							+ " [prologueFemale.name] says, before walking off and leaving you alone with your aunt."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Keeping your growing arousal under control, you step away from [prologueMale.name],"
							+ " [pc.speech(I don't think that's a good idea. Anyway, I'm sure the presentation will be starting soo-)]"
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech([pc.Name]! There you are! I've been looking everywhere for you,)]"
							+ " a very familiar voice calls out, cutting your sentence short,"
							+ " [lilaya.speech(sorry if I'm interrupting, but I really need your help!)]"
						+ "</p>"
						+ "<p>"
							+ "Turning towards the unmistakable voice of your aunt Lily, you give her a reassuring smile,"
							+ " [pc.speech(Don't worry Lily, you're not interrupting."
								+ " Is this about the opening being delayed?)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.speech(I see you're going to be busy, perhaps I'll catch you later,)] [prologueMale.name] says, before walking off and leaving you alone with your aunt."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "[lilaya.speech(Erm... goodbye!)]"
						+ " Lily awkwardly calls out, completely oblivious as to what was going on, before turning back to you,"
						+ " [lilaya.speech(Yes! This is to do with the delay! Arthur's gone missing, and I can't start without him! Everyone's crowding me asking questions, so I can't go out and look for him myself."
							+ " Please, please <i>please</i> can you go and find him for me?)]"
					+ "</p>"
					+ "<p>"
						+ "Lily's puppy-dog eyes always were too much for you to say no to, and you find yourself agreeing to do as she asks,"
						+ " [pc.speech(Ok Lily, I'll find him for you, don't worry.)]"
					+ "</p>"
					+ "<p>"
						+ "As Lily starts thanking you, someone calls out from behind her, asking her to return to the stage."
						+ " Thanking you once more, she then rushes off, leaving you to do as you said you would..."
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Search", "Go and search the museum for Arthur.", INTRO_2);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_2 = new DialogueNodeOld("In the Museum", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Having agreed to help Lily by finding Arthur, you set off into the museum."
						+ " The steady hum of voices from the entrance hall quickly fades away into silence as you delve deep into the museum's deserted rooms and corridors."
						+ " It's a lot larger in here than you anticipated, and before long you realise that you've become hopelessly lost."
						+ " As you step into yet another cavernous hall lined with glass cabinets and ancient stonework, you thankfully see a map of the museum on the far side of the room."
						+ " Muttering thanks to whoever put that there, you quickly make your way towards it, hoping to find a route out of here."
					+ "</p>"

					+ "<p>The sound of your footfalls echoing off the walls are suddenly interrupted as a seductive female voice sounds in your ear, "
					+ UtilText.parseNPCSpeech("Looking for something?", Femininity.FEMININE_STRONG)
					+ "</p>"

					+ "<p>You let out a startled cry and spin around to face the direction of the voice, almost jumping out of your skin as you end up face-to-face with"
					+ " your own reflection."
					+ " A gigantic ceiling-height mirror stands against the wall, its silver edges finely decorated with hundreds of tiny figures."
					+ " The woman who spoke must be hiding behind it, as there's nothing else nearby that could conceal a person.</p>"

					+ "<p>"
					+ UtilText.parseNPCSpeech("Aww, did I scare the poor little thing?", Femininity.FEMININE_STRONG)
					+ " The woman speaks again, her voice definitely coming from behind the mirror. She seems to be putting on her most sultry tone as she continues, "
					+ UtilText.parseNPCSpeech("Perhaps you should come back here so I can calm you down...", Femininity.FEMININE_STRONG)
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Agree", "Go and look behind the mirror to find out who's there.", INTRO_3A);
			} else if (index == 2) {
				return new Response("Nope", "This is the most obvious trap you've ever seen.", INTRO_3B);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_3A = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>You aren't entirely sure that it's the right thing to do, but you decide to accept the mysterious woman's invitation."
					+ " You boldly stroll towards the mirror, duly impressed with your confident reflection as you close in on her hiding place.</p>"

					+ "<p>"
					+ UtilText.parseNPCSpeech("That's right darling, come back here and see what I've got for you.", Femininity.FEMININE_STRONG)
					+ "</p>"

					+ "<p>You vaguely consider the possibility that this could be a prank being played at your expense, but you find yourself too curious to turn back now."
					+ " Reaching the mirror, you step over the rope barrier and lean forwards to take a look behind.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("The horror!", "Aaaa!", INTRO_4);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_3B = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>A deserted museum after dark, an ominous mirror, and an unknown person hiding in the shadows?"
					+ " You distinctly remember seeing this horror movie before. You turn back the way you came and start running.</p>"

					+ "<p>"
					+ UtilText.parseNPCSpeech("Where do you think <i>you're</i> going?", Femininity.FEMININE_STRONG)
					+ "</p>"

					+ "<p>"
					+ "You're only a few steps away as the door slams shut, and as you reach for the handle, you hear the distinct sound of a key turning in the lock."
					+ " You knew it! You're the "
					+ (Main.game.getPlayer().isFeminine() ? "girl" : "guy")
					+ " who dies first!"
					+ " On the edge of despair, your mind suddenly shifts into a different gear."
					+ " Nobody ever expects the victim to go on the attack!"
					+ " You turn back towards the mirror and start to run at it, certain that the would-be murderer would never expect your bold move."
					+ " You quickly close the distance across the room, steeling yourself for the worst as you valiantly leap over the rope barrier and look behind the mirror."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("The horror!", "Aaaa!", INTRO_4);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_4 = new DialogueNodeOld("The horror!", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>You reel back in shock as you unwittingly step straight into a thick cobweb."
					+ " You desperately pull the sticky webbing from your face, opening your eyes to find...</p>"

					+ "<p>"
					+ "<i>There's nobody there.</i>"
					+ "</p>"

					+ "<p>Your over-active imagination runs wild, and you feel your heart hammering as you break out in a cold sweat."
					+ " A shiver runs down your spine, and before any more signs of your unease present themselves, the woman suddenly breaks the silence.</p>"
					+ "<p>"
					+ UtilText.parseNPCSpeech("GOTCHA!", Femininity.FEMININE_STRONG)
					+ "</p>"
					+ "<p>"
					+ "You physically jump into the air as she shouts from right behind you!"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Panic", "Now would be a good time to panic.", INTRO_5);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_5 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "You feel that the time to panic has definitely arrived, and you let out a scream of sheer terror."
					+ " As your startled wail echoes around the room, you dart forwards, spinning around to face your elusive tormentor."
					+ " Once again, there's nobody there."
					+ " With your back to the mirror, you scan the room once more before reaching for your phone."
					+ " You need to call for help, right now."
					+ "</p>"

					+ "<p>"
					+ "Before you're even halfway to your pocket, the room in front of you is suddenly illuminated by a flash of purple light."
					+ " As you see your shadow being cast onto the wall before you, you feel a pair of slender hands grab your shoulders."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseNPCSpeech("Time to come play with Lilith!", Femininity.FEMININE_STRONG)
					+ "</p>"

					+ "<p>"
					+ "Before you can react, you're violently pulled backwards."
					+ " Instead of crashing into the mirror behind you, you feel yourself falling, and as your head hits the hard ground, your vision cuts to black..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Wake up", "You slowly start to regain consciousness.", INTRO_NEW_WORLD_1){
					@Override
					public void effects() {
						
						Main.game.setWeather(Weather.MAGIC_STORM, 300);

						Main.game.setRenderMap(true);
						
						Main.mainController.updateUI();
						
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.DOMINION),
								PlaceType.DOMINION_AUNTS_HOME,
								false);
					}
				};
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_1 = new DialogueNodeOld("A new world", "", true, false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ UtilText.parsePlayerThought("Euuuugh...")
					+ "</p>"
					+ "<p>"
						+ "You groan and reach up to rub your throbbing head."
						+ " As you do, you become aware that you're lying on something cold and hard."
						+ " Pushing yourself up into a sitting position, you quickly scrunch your eyes shut as jolts of pain shoot through your skull."
						+ " As you hold your head, trying to stop it from spinning, you start to hear voices speaking all around you."
					+ "</p>"
					+ "<p>"
						+ UtilText.parseNPCSpeech("Oh shit, [pc.she]'s alive!", Femininity.MASCULINE_STRONG)
						+ " someone says."
					+ "</p>"
					+ "<p>"
						+ UtilText.parseNPCSpeech("You idiot, don't get that close! Didn't you see [pc.herHim] just fall out of mid-air?!"
								+ " [pc.She]'s obviously an arcane user! We need to get out of here!", Femininity.MASCULINE)
						+ " a rather worried-sounding man replies."
					+ "</p>"

					+ "<p>The harsh tone of a woman's voice cuts them off, "
					+ UtilText.parseNPCSpeech("Shut up, both of you! Honestly, I don't know why I bring you two along... Anyway, take a look at [pc.herHim],"
							+ " don't you see something wrong with what you just said?", Femininity.FEMININE_STRONG)
					+ "</p>"

					+ "<p>"
					+ UtilText.parseNPCSpeech("Uuuuum...", Femininity.MASCULINE_STRONG)
					+ " the two voices say in unison.</p>"

					+ "<p>"
					+ UtilText.parseNPCSpeech("You two don't have a brain between you... [pc.She]'s human, get it? Since when could a human use that kind of arcane power?"
							+ " Hell, only a Lilin could just teleport themselves out of thin air like that. This little runt obviously pissed off some demon, or messed with some artifact [pc.she] didn't understand."
							+ " In other words, [pc.she]'s fair game, so just hold [pc.herHim] still until the storm starts.", Femininity.FEMININE_STRONG)
					+ "</p>"

					+ "<p>"
						+ "As the people reach the end of their conversation, you're surprised to find that most of the pain in your head has already faded away."
						+ " You open your eyes to see what's going on, but as you do, you're suddenly grabbed by two pairs of strong hands."
					+ "</p>"

					+ "<p>"
						+ "[pc.speech(Hey, what the hell?! Let me g- ~mrph~!)]"
						+ " you cry, but your objections are cut short as the woman presses her hand firmly down over your mouth."
					+ "</p>"

					+ "<p>"
						+ "You kick and squirm as you desperately try to wriggle out of the strange people's grasp."
						+ " As you struggle, you catch brief glimpses of your three tormentors, and as you do, your eyes open wide in shock."
						+ " You stop squirming and go limp, your mind completely unable to process what it's seeing."
					+ "</p>"

					+ "<p>"
						+ "While the men looked to be normal people on first glance, as you take a closer look, you see that something's not quite right."
						+ " Their arms and legs are covered in a layer of coarse hair, and instead of feet, they each have a pair of horse-like hooves."
						+ " Their ears are also similar to that of a horse's, and sit high up on their heads, and a horse-like tail swishes behind each of them."
					+ "</p>"

					+ "<p>"
						+ "As alarming as the two men's appearances are, they're nothing compared to the woman in front of you."
						+ " While the men are in possession of horse-like features, the woman could better be described as a cat possessing human-like features."
						+ " She's completely covered in a fine layer of black fur, and every part of her body, including her face, has somehow transformed into that of an anthropomorphic cat-girl."
						+ " Her long tail swishes excitedly behind her, and as she brushes a strand of loose fur-like hair behind her upright cat-ears, she looks down at you with an evil grin on her face."
					+ "</p>"
						;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Struggle", "Try to struggle out of their grip.", INTRO_NEW_WORLD_1_STRUGGLE);
				
			} else if (index == 2) {
				return new Response("Furries?! Yes!",
						"Furries are real?! You <b>love</b> furries!</br>"
						+ "<b>This will set all of your starting furry preferences to </b><b style='color:"+ RaceStage.GREATER.getColour().toWebHexString()+ ";'>"+FurryPreference.MAXIMUM.getName()+"</b><b>."
						+ " This can be changed at any time from the options menu.</b>", 
						INTRO_NEW_WORLD_1_BY_THE_POWER_OF_LOVING_FURRIES){
					@Override
					public void effects(){
						for(Subspecies r : Subspecies.values()) {
							Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.MAXIMUM);
							Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.MAXIMUM);
						}
						Main.saveProperties();
					}
				};
				
			} else if (index == 3) {
				return new Response("Furries?! No!",
						"Why are furries real?! You <b>hate</b> furries! Channel your rage and try to break free.</br>"
						+ "<b>This will set all of your starting furry preferences to </b><b style='color:"+ RaceStage.HUMAN.getColour().toWebHexString()+ ";'>"+FurryPreference.HUMAN.getName()+"</b><b>."
						+ " This can be changed at any time from the options menu.</b>", 
						INTRO_NEW_WORLD_1_BY_THE_POWER_OF_HATING_FURRIES){
					@Override
					public void effects(){
						for(Subspecies r : Subspecies.values()) {
							Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.HUMAN);
							Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.HUMAN);
						}
						Main.saveProperties();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld INTRO_NEW_WORLD_1_STRUGGLE = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "You start to struggle again, kicking and thrashing wildly as you try to free yourself."
					+ " The cat-girl simply laughs, and in one swift movement she pounces forwards, slamming you to the floor as she straddles your chest.</p>"

					+ "<p>She leans down, her cat-like pupils dilating as she pushes your head to the ground and delivers a long, slow lick up your cheek, "
					+ (Main.game.getPlayer().getVaginaType() != VaginaType.NONE
							? UtilText.parseNPCSpeech("Mmmm, you taste good, my little prey. We'll just wait for the thunder to start, and then you'll be begging for my boys here to fuck your tight little pussy.", Femininity.FEMININE_STRONG)
							: UtilText.parseNPCSpeech("Mmmm, you taste good, my little prey. We'll just wait for the thunder to start, and then you'll be begging for my boys here to fuck your tight little ass.", Femininity.FEMININE_STRONG))
					+ "</p>"

					+ "<p>"
					+ "The cat-girl sits back up, still holding her paw-like hands over your mouth, and points up to the sky."
					+ " The two horse-boys have completely immobilised you, and being unable to do anything about your current situation, you look up to see what the cat-girl is pointing at."
					+ "</p>"

					+ "<p>"
					+ "High above you, a roiling mass of dark black storm clouds are blotting out the evening sky."
					+ " There's a strange pink tint around the edge of each one, and as the cat-girl stares down at you, you see a flash of the same pink tint glowing deep within her cat-like pupils."
					+ " She seems content to simply hold you still, waiting for the storm to break, and seeing no way to gain your freedom, you take a moment to glance around and take in your surroundings.</p>"

					+ "<p>"
					+ "You're lying in the middle of a deserted street, and a series of old-fashioned street lamps are illuminating the area around you in a soft amber glow."
					+ " You notice that the entire road is paved with clean, whitish-blue paving stones, and with the numerous wooden benches and small, leafy trees that are dotted about the place, you realise that this street is entirely pedestrianised."
					+ " There's no sign of any cars or passersby that could come to your aid, and the imposing, neo-classical frontages of the surrounding buildings offer no hope of any help, as all their windows and curtains are tightly shut."
					+ "</p>"

					+ "<p>"
					+ UtilText.parsePlayerThought("How am I going to get out of this...")
					+ "</p>"

					+ "<p>"
					+ "Just as you're on the edge of losing all hope, an oddly-familiar voice suddenly rings out from somewhere behind you."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Do you <i>really</i> have to play your silly little games right here in the middle of the street?"
							+ " You know, some people don't really care for that sort of thing, and by the looks of it, your little friend under there may just be one of them.", Main.game.getLilaya())
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Someone's come to save you!", INTRO_NEW_WORLD_2){
					@Override
					public void effects() {
						Main.game.getLilaya().setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTRO_NEW_WORLD_1_BY_THE_POWER_OF_HATING_FURRIES = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ UtilText.parsePlayerThought("Fucking furries! Why do they always have to ruin everything?!")
					+ "</p>"

					+ "<p>"
					+ "You violently jerk your arm away from the horse-boy's grip, and, leaning to one side, use it to deliver a punch square in the jaw of the other one."
					+ " He falls back, dazed, as you quickly turn back and strike the first horse-boy with all your might, right in the solar plexus."
					+ " Like his friend, he instantly falls back, the wind completely knocked out of him."
					+ "</p>"

					+ "<p>"
					+ "Having dealt with the two guys holding you down, you turn to whom you assume is their ringleader."
					+ " Unfortunately, it seems as though you chose the wrong people to target first."
					+ " As you turn to face the grinning cat-girl, she lets out a mocking laugh, and in one swift movement she pounces forwards, slamming you to the floor as she straddles your chest."
					+ "</p>"

					+ "<p>"
					+ UtilText.parsePlayerThought("Shit, she's fast!")
					+ "</p>"

					+ "<p>"
					+ "Before you have the chance to strike back at her, she quickly pins your arms to the floor with her knees, and you suddenly feel a row of sharp claws pressing against your neck."
					+ " You realise that you have no chance against that, and as you feel the little daggers gently pressing into your skin, you stop struggling."
					+ "</p>"

					+ "<p>"
					+ "Seeing that you've surrendered, she grins and leans down, her cat-like pupils dilating as she pushes your head to the ground and delivers a long, slow lick up your cheek, "
					+ (Main.game.getPlayer().getVaginaType() != VaginaType.NONE
							? UtilText.parseNPCSpeech("Mmmm, you taste good, my little prey. We'll just wait for the thunder to start, and then you can beg for my boys here to forgive you as they fuck your tight little pussy."
									+ " Look, not long to wait now!", Femininity.FEMININE_STRONG)
							: UtilText.parseNPCSpeech("Mmmm, you taste good, my little prey. We'll just wait for the thunder to start, and then you can beg for my boys here to forgive you as they fuck your tight little ass."
									+ " Look, not long to wait now!", Femininity.FEMININE_STRONG))
					+ "</p>"

					+ "<p>"
					+ "The cat-girl sits back, still holding her claws against your throat, and points up to the sky."
					+ " As she does this, you see that the horse-boys have recovered and moved back to either side of you."
					+ " The cat-girl has you pinned down and completely immobilised, and, being unable to do anything about your current situation, you look up to see what she's pointing at."
					+ "</p>"

					+ "<p>"
					+ "High above you, a roiling mass of dark black storm clouds are blotting out the evening sky."
					+ " There's a strange pink tint around the edge of each one, and as the cat-girl stares down at you, you see a flash of the same pink tint glowing deep within her cat-like pupils."
					+ " She seems content to simply hold you still, waiting for the storm to break, and seeing no way to gain your freedom, you take a moment to glance around and take in your surroundings.</p>"

					+ "<p>"
					+ "You're lying in the middle of a deserted street, and a series of old-fashioned street lamps are illuminating the area around you in a soft amber glow."
					+ " You notice that the entire road is paved with clean, whitish-blue paving stones, and with the numerous wooden benches and small, leafy trees that are dotted about the place, you realise that this street is entirely pedestrianised."
					+ " There's no sign of any cars or passersby that could come to your aid, and the imposing, neo-classical frontages of the surrounding buildings offer no hope of any help, as all their windows and curtains are tightly shut."
					+ "</p>"

					+ "<p>"
					+ UtilText.parsePlayerThought("How am I going to get out of this...")
					+ "</p>"

					+ "<p>"
					+ "Just as you're on the edge of losing all hope, an oddly-familiar voice suddenly rings out from somewhere behind you."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Do you <i>really</i> have to play your silly little games right here in the middle of the street?"
							+ " You know, some people don't really care for that sort of thing, and by the looks of it, your little friend under there may just be one of them.", Main.game.getLilaya())
					+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Someone's come to save you!", INTRO_NEW_WORLD_2){
					@Override
					public void effects() {
						Main.game.getLilaya().setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTRO_NEW_WORLD_1_BY_THE_POWER_OF_LOVING_FURRIES = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ UtilText.parsePlayerThought("Furries are real?! This is amazing!")
					+ "</p>"

					+ "<p>"
						+ "Despite your excitement upon discovering that furries are no longer simply a fantasy, you're still not entirely comfortable with what's going on."
						+ " As you come to your senses, you start to struggle again, kicking and thrashing wildly as you try to free yourself."
						+ " The cat-girl simply laughs, and in one swift movement she pounces forwards, slamming you to the floor as she straddles your chest."
					+ "</p>"

					+ "<p>She leans down, her cat-like pupils dilating as she pushes your head to the ground and delivers a long, slow lick up your cheek, "
					+ (Main.game.getPlayer().getVaginaType() != VaginaType.NONE
							? UtilText.parseNPCSpeech("Mmmm, you taste good, my little prey. We'll just wait for the thunder to start, and then you'll be begging for my boys here to fuck your tight little pussy.", Femininity.FEMININE_STRONG)
							: UtilText.parseNPCSpeech("Mmmm, you taste good, my little prey. We'll just wait for the thunder to start, and then you'll be begging for my boys here to fuck your tight little ass.", Femininity.FEMININE_STRONG))
					+ "</p>"

					+ "<p>"
					+ "The cat-girl sits back up, still holding her paw-like hands over your mouth, and points up to the sky."
					+ " The two horse-boys have completely immobilised you, and being unable to do anything about your current situation, you look up to see what the cat-girl is pointing at."
					+ "</p>"

					+ "<p>"
					+ "High above you, a roiling mass of dark black storm clouds are blotting out the evening sky."
					+ " There's a strange pink tint around the edge of each one, and as the cat-girl stares down at you, you see a flash of the same pink tint glowing deep within her cat-like pupils."
					+ " She seems content to simply hold you still, waiting for the storm to break, and seeing no way to gain your freedom, you take a moment to glance around and take in your surroundings.</p>"

					+ "<p>"
					+ "You're lying in the middle of a deserted street, and a series of old-fashioned street lamps are illuminating the area around you in a soft amber glow."
					+ " You notice that the entire road is paved with clean, whitish-blue paving stones, and with the numerous wooden benches and small, leafy trees that are dotted about the place, you realise that this street is entirely pedestrianised."
					+ " There's no sign of any cars or passersby that could come to your aid, and the imposing, neo-classical frontages of the surrounding buildings offer no hope of any help, as all their windows and curtains are tightly shut."
					+ "</p>"

					+ "<p>"
					+ UtilText.parsePlayerThought("How am I going to get out of this...")
					+ "</p>"

					+ "<p>"
					+ "Just as you're on the edge of losing all hope, an oddly-familiar voice suddenly rings out from somewhere behind you."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Do you <i>really</i> have to play your silly little games right here in the middle of the street?"
							+ " You know, some people don't really care for that sort of thing, and by the looks of it, your little friend under there may just be one of them.", Main.game.getLilaya())
					+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Someone's come to save you!", INTRO_NEW_WORLD_2){
					@Override
					public void effects() {
						Main.game.getLilaya().setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTRO_NEW_WORLD_2 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "The cat-girl spins around, turning to see who's talking."
					+ " Her claws press against your throat, preventing you from using this opportunity to attempt to escape."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseNPCSpeech("Well, perhaps <i>some</i> people would like to come on out and join in. There's plenty of roo-", Femininity.FEMININE_STRONG)
					+ " the cat-girl's voice cuts off as you hear the mysterious woman's footfalls getting closer."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseNPCSpeech("Oh shit! Run!", Femininity.MASCULINE)
					+ " the horse-boys quickly dart off, abandoning their leader."
					+ "</p>"

					+ "<p>"
					+ "The cat-girl quickly starts stuttering out excuses as you hear the mysterious woman walking closer, "
					+ UtilText.parseNPCSpeech("L-look, w-we were only playing! Anyway, if [pc.she] didn't want to have some fun, why's [pc.she] outside just before a storm's about to break?!", Femininity.FEMININE_STRONG)
					+ "</p>"

					+ "<p>"
					+ "[lilaya.speech(Well, it's really none of my business, but now that your little friends have run off, what's to stop <i>[pc.herHim]</i> from having a little fun with <i>you</i>?)]"
					+ " the woman replies, and this time, you're certain that you recognise her voice."
					+ "</p>"

					+ "<p>"
					+ "You feel the cat-girl's grip loosening around your throat, and, taking advantage of the fact that she's been distracted, you pull your arms out from under her knees, grab her wrists and throw her off of you."
					+ " Quickly jumping to your feet, you turn to face your tormentor, only to see her running off down a nearby alleyway."
					+ "</p>"

					+ "<p>"
					+ "[lilaya.speech(You're welcome...)]"
					+ " you hear your saviour say as she starts to walk off."
					+ "</p>"

					+ "<p>"
					+ UtilText.parsePlayerThought("Wait... That voice... Lily?!")
					+ "</p>"

					+ "<p>"
					+ "You spin around, certain that your mysterious benefactor is none other than your aunt Lily."
					+ " Even as she walks away, you can tell that it's her."
					+ " The way she walks, her long, black hair, even her usual attire are all how they should be."
					+ " Even though you've often criticised her for never wearing anything other than her plain black skirt, white shirt and black heels, right now you've never been happier to see them."
					+ "</p>"

					+ "<p>"
					+ "[pc.speech(Lily! Oh Lily, I'm so glad you're here! Those... <i>people</i> ...were about to... well... I'm just glad you're here now!)]"
					+ " you run to catch up with your aunt, wrapping your arms around her and pulling her into a loving hug."
					+ " As you press into her back, you notice what feels to be a lumpy backpack digging into your chest, but due to the dim street-light, you didn't see it."
					+ "</p>"

					+ "<p>"
					+ "[lilaya.speech(W-What the hell are you doing?!)]"
					+ " Lily exclaims, wriggling free as she pushes you away from her, "
					+ "[lilaya.speech(I-It's not like I was <i>trying</i> to save you! J-Just say thanks and go find some cover before the storm breaks!)]"
					+ "</p>"

					+ "<p>"
					+ "You don't quite understand her reaction."
					+ " She usually loves it when you hug her like that, but as Lily turns around, you realise that something is wrong."
					+ " It's still Lily, sort of, but she's got a few extra parts that you certainly don't remember her having before."
					+ " As she steps back from you, she ends up directly beneath the glow of one of the street lights, and as you get a good look at her, you feel your jaw dropping."
					+ "</p>"

					+ "<p>"
					+ "Her face is no doubt Lily's, but it's the face you've seen when looking at her old photos from when she was back at university."
					+ " What's more worrying than her sudden youth, however, is that fact that there are a pair of low, swept-back horns growing out from her forehead."
					+ " A thin, spaded tail that you hadn't noticed in the dark curls round behind her, rising up to draw attention to a pair of small, bat-like wings that suddenly unfurl from her back."
					+ " Her chest is another area that's different, and you see that Lily has a massive pair of E-cup breasts, quite unlike the tiny B-cup ones she used to have."
					+ "</p>"

					+ "<p>"
					+ "[lilaya.speech(Stop staring!)]"
					+ " Lily commands, "
					+ "[lilaya.speech(I-I'll banish you to another dimension! Or... um... Freeze you and leave you lying out here! What do you want?!)]"
					+ "</p>"

					+ "<p>"
					+ "She clearly doesn't recognise you, and seems quite shocked by your actions."
					+ " You notice that she's blushing profusely, and you wonder if that's the result of your sudden hug or the way you've been staring at her."
					+ " As you try to think of something to say, Lily raises her hand, and as she does so, a swirling vortex of snow and ice materialises around her arm."
					+ "</p>"

					+ "<p>"
					+ "You don't really want to be frozen by this demonic version of your aunt, so you quickly blurt out everything you know about what's just happened."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Explain", "Quickly explain to Lily what happened back at the museum.", INTRO_NEW_WORLD_2_A);
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_2_A = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "You quickly describe all the events at the museum, finishing with how you woke up here just moments before she arrived."
					+ " When you mention that the mysterious woman in the mirror called herself 'Lilith', you see Lily frown and shuffle about on her feet."
					+ " Apart from that minor display of emotion, she listens to your tale with a stony expression on her face."
					+ " As you explain to her that she's supposed to be your aunt, she lowers her hand and banishes the swirling vortex that she'd summoned around her arm."
					+ "</p>"

					+ "<p>"
					+ "[lilaya.speech(But that's just a theory...)]"
					+ " she mutters, barely loud enough to be audible. She then raises her voice as she continues, "
					+ UtilText.parseSpeech("From what you've said, it sounds as though you're claiming to be from an alternate universe."
							+ " While <i>theoretically</i> possible, there's never been a recorded case of inter-dimensional travel actually working."
							+ " As unlikely as your story sounds, there <i>is</i> one way we can test the truth, right here, right now."
							+ " If you're from another universe, you shouldn't be infused with the arcane, so when this storm finally breaks...", Main.game.getLilaya())
					+ "</p>"

					+ "<p>"
					+ "Suddenly, the entire street is illuminated in a bright flash of pink as a bolt of lightning streaks across the sky."
					+ " Lily grins as she finishes her sentence, "
					+ "[lilaya.speech(You shouldn't be affected by the arcane thunder!)]"
					+ "</p>"

					+ "<p>"
					+ "A moment later, the deep rumble of thunder starts echoing down the street."
					+ " At first, it sounds just like any old thunder strike that you've heard a hundred times before, but instead of fading away into silence, it builds and builds until the very ground itself seems to shake from its intensity."
					+ " Just as you're wondering how long this is going to last, the rumbling sound suddenly bursts into a cacophony of moans and squeals, as though a thousand women are suddenly brought to a crashing orgasm all around you."
					+ " The sound is so unexpected and so sudden, that you forget for a moment that it's caused by this strange arcane thunder, and you turn this way and that, looking around for the source of the sound."
					+ "</p>"

					+ "<p>"
					+ "After a few seconds, the noise starts to fade away, quickly dropping to a whisper before silence envelops the dimly-lit street once again."
					+ " You turn back to Lily and see that a very concerned expression has fallen over her face."
					+ "</p>"

					+ "<p>"
					+ "[lilaya.speech(This is impossible... Well... Not <i>impossible</i>, but there's no way...)]"
					+ " she whispers, before addressing you in her normal voice, "
					+ "[lilaya.speech(So you don't have any sudden desire to try and fuck me?)]"
					+ " she asks."
					+ "</p>"

					+ "<p>"
					+ "[pc.speech(Uuuum... That's a bit of a weird question, but... no?)]"
					+ " you answer, before suddenly realising that you might have offended this demonic version of your aunt, "
					+ "[pc.speech(I mean, you're obviously extremely attractive and anyone would be lucky to have you, but you're my aunt! Well, sort of, anyway...)]"
					+ "</p>"

					+ "<p>"
					+ "You see Lily blushing at your flattering, and, looking back on it, slightly inappropriate, response. "
					+ "[lilaya.speech(Y-You're not meant to say that sort of thing! C-Can't you see that I'm a... Eugh!)]"
					+ " she turns around, stomping her foot on the ground as she starts walking away, "
					+ "[lilaya.speech(Just come with me, and don't embarrass yourself any more!)]"
					+ " she says, motioning for you to follow her."
					+ "</p>"

					+ "<p>"
					+ "You do as she says, and quickly rush forwards to her side."
					+ " You glance across to see that her cheeks are still burning red, and you think that you're embarrassing her far more than you are yourself."
					+ " You keep that thought to yourself, however, and let Lily lead the way, ignoring several more crashes of arcane thunder as you thank your lucky stars that she was there to save you."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Follow", "Follow Lily as she leads you back to her house.", INTRO_NEW_WORLD_3){
					@Override
					public void effects() {
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getPlacesOfInterest().get(new GenericPlace(PlaceType.LILAYA_HOME_ENTRANCE_HALL)),
								false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_3 = new DialogueNodeOld("Lilaya's Home", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "<p>"
					+ "Nothing ever seemed to embarrass your real aunt, but it seems like this version of Lily is the complete polar opposite."
					+ " Almost everything you've said to her so far has resulted in her blushing or stuttering over her words, and as you let her lead you through the unfamiliar streets of this strange city, you decide that you won't be the first to"
					+ " speak and risk saying the wrong thing yet again."
					+ " As a result, the rest of the walk proceeds in silence, allowing you to observe several differences between this city and the one you used to call home."
					+ "</p>"

					+ "<p>"
					+ "Every single street seems to be pedestrianised, and there isn't single car to be seen anywhere."
					+ " The street lights are curiously different from the ones you're used to seeing, and instead of producing the usual dull fluorescent glow, tiny little balls of energetic light dance within glass globes, casting a steady,"
					+ " albeit slightly flickering, orange glow over the street's clean paving stones."
					+ " The buildings on either side all adhere to old-fashioned designs that look very similar to pictures you've seen of Victorian architecture."
					+ " When combined with the style of street-lights, a casual observer could easily be confused into believing that they'd been transported back"
					+ " to an alternate version of mid-nineteenth century central London."
					+ " As you look closer, however, the fashion trends being displayed in shop-fronts and on advertisement billboards make it quite clear that this 'alternate dimension' must be of the same time period that you're familiar with."
					+ "</p>"

					+ "<p>"
					+ "[lilaya.speech(Right, here we are,)]"
					+ " Lily suddenly says, snapping you out of your trance, "
					+ "[lilaya.speech(You're going to come inside and let me run a few little tests on you, ok? After all, I <i>did</i> save you back there,)]"
					+ " she finishes, walking up a short external staircase to a rather imposing-looking front door."
					+ "</p>"

					+ "<p>"
					+ "You decide not to mention the fact that Lily said earlier that she 'wasn't trying to save you', and instead answer as you always do when your aunt holds you to ransom like this, "
					+ "[pc.speech(Ok Lily, I guess you're right,)]"
					+ " you sigh, following her up the steps, "
					+ "[pc.speech(I hope these 'little tests' aren't anything I should worry about...)]"
					+ "</p>"

					+ "<p>"
					+ "Lily knocks on the door and turns back to you, sighing, "
					+ "[lilaya.speech(Of course they're nothing to worry about,)]"
					+ " she says, before frowning down at you, "
					+ UtilText.parseSpeech("and stop calling me 'Lily'. Maybe back in <i>your</i> world, I was your aunt and called that, but <i>here</i>, I'm not your aunt, and my name is 'Lilaya'."
							+ " S-So call me that!", Main.game.getLilaya())
					+ " she suddenly exclaims, quickly turning back round to face the door."
					+ "</p>"
					
					+ "<p>"
					+ UtilText.parsePlayerThought("Damn, she's even getting embarrassed about telling me her own name? Does she never speak to people or something?")
					+ "</p>"

					+ "<p>"
					+ "You don't have to see her face to know that she's blushing again, and, not wanting to make matters worse for her, decide to try and put her at ease by introducing yourself,"
					+ " [pc.speech(Well, I'm [pc.name], by the way. It seems quite strange to be telling you that, I mean, I've known you forever!)]"
					+ "</p>"

					+ "<p>"
					+ "Before you get to hear Lilaya's response, the door before you suddenly opens, casting a warm glow out onto the street behind you."
					+ " Whoever opened the door must have moved to one side, and as Lilaya enters, you follow in her footsteps, looking to greet the person who's let you inside."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Welcome home Mistress!", Main.game.getRose())
					+ " a young woman's voice sounds from behind the door as you enter, "
					+ UtilText.parseSpeech("Did you have a nice evenin- ~eek!~ A stranger! Mistress?!", Main.game.getRose())
					+ "</p>"

					+ "<p>"
					+ "As you step inside, you see a rather panic-stricken girl, no older than you, desperately looking at Lilaya for guidance."
					+ " She's wearing a complete French-Maid's uniform, and you're rather shocked that this world's version of your aunt can afford to hire a personal maid."
					+ " Like the other people you've seen so far, she seems to be some kind of human-animal hybrid, and has a pair of sleek, black cat-ears sitting amongst her fur-like hair."
					+ " A black cat-like tail is sticking straight up behind her, and her cat-like eyes glance nervously between you and Lilaya as she hops back and forth on her feet."
					+ "</p>"

					+ "<p>"
						+ "Lilaya makes a soothing hushing sound before patting her on the head, "
						+ "[lilaya.speech(Shh, it's alright Rose, [pc.she]'s a guest of mine, you can calm down,)]"
						+ " she says, before pulling Rose into a surprisingly intimate hug, "
						+ "[lilaya.speech(Why don't you go make us some tea, you can bring it down to us in the lab.)]"
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Yes Mistress! Right away Mistress!", Main.game.getRose())
					+ " Rose replies, and as Lilaya releases her, she quickly closes the door behind you and rushes off to another part of the house."
					+ "</p>"

					+ "<p>"
					+ "As the excitable maid makes her exit, you find yourself standing in the most extravagantly furnished town-house that you've ever seen."
					+ " A huge crystal chandelier hangs from the double-height ceiling, casting its warm light over a grand, red-carpeted staircase that leads to the upper floor."
					+ " Fine paintings and marble busts line the walls of the entrance hall, and you find your jaw dropping for the second time this evening."
					+ "</p>"

					+ "<p>"
					+ "[lilaya.speech(So I take it that your real aunt doesn't live in a place like this?)]"
					+ " Lilaya asks, smirking at your stunned expression, "
					+ "[lilaya.speech(Anyway, come with me. My lab's this way,)]"
					+ " she says, walking over to a side-corridor."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("To the lab", "Follow Lilaya to her lab.", INTRO_NEW_WORLD_4){
					@Override
					public void effects() {
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getLilaya().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getPlacesOfInterest().get(new GenericPlace(PlaceType.LILAYA_HOME_LAB)),
								false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_4 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "You hurry to follow Lilaya as she leads the way to her lab."
					+ " As you walk down a long corridor at her side, you get the chance to ask her the question at the forefront of your mind."
					+ "</p>"

					+ "<p>"
					+ "[pc.speech(So, Lilaya, do you have any idea what's going on?)]"
					+ "</p>"

					+ "<p>"
					+ "You see her face scrunch up into a thoughtful frown as she answers, "
					+ "[lilaya.speech(Well, after seeing your immunity to the arcane thunder, I'm convinced that you're telling the truth, but...)]"
					+ " Lilaya stops and turns to face you, "
					+ "[lilaya.speech(Inter-dimensional travel is far above and beyond any arcane skill I've ever heard of. Perhaps with some tests, we can discover more...)]"
					+ " she turns around and opens the door behind her, stepping through into her lab."
					+ "</p>"

					+ "<p>"
					+ "You follow Lilaya into what appears at first to be a huge library."
					+ " The walls are covered in shelving, holding what must be hundreds, if not thousands, of ancient-looking leather-bound tomes."
					+ " Most of the room, however, is occupied by a series of long tables, each covered in strange glass vials, odd-looking instruments, and bottles of glowing liquids."
					+ " Lilaya makes her way over to one side of the room, where there's an empty square section marked out on the floor in white chalk."
					+ " She produces a pair of safety goggles from under a desk and slips them on before motioning for you to come over to her."
					+ "</p>"

					+ "<p>"
					+ "[pc.speech(Wow, so what is it you do here? You -well, my other aunt, I mean- used to be a researcher at the city museum, but it doesn't look like you share her profession.)]"
					+ "</p>"

					+ "<p>"
					+ "Lilaya drags a chair into the middle of the chalked-out square and starts fiddling with a series of strange-looking spotlights at each corner."
					+ " As she adjusts her instruments, she motions for you to sit and answers your question, "
					+ "[lilaya.speech(So, the other 'me' is a researcher too? I spend most of my time in here, finding new ways to harness the arcane and infuse it into every-day objects. My mother provides-)]"
					+ " Lilaya cuts her sentence short, obviously saying more than she meant to, before quickly trying to change the topic, "
					+ "[lilaya.speech(L-Look, just sit down there and keep still while I calibrate these!)]"
					+ "</p>"

					+ "<p>"
					+ "You do as she says, not wanting to press the matter and give her another cause to feel embarrassed."
					+ " As she works, you see her little spaded tail working like a third limb, rising up to twist dials and slide levers as she busily finishes calibrating the strange instruments."
					+ "</p>"

					+ "<p>"
					+ "After a few moments she seems to be satisfied with her adjustments, and steps out of the chalk square, turning to smile at you, "
					+ UtilText.parseSpeech("Right, all set! Don't worry, this doesn't hurt or anything, it's just going to let me know a little bit more about you."
							+ " Are you ready?", Main.game.getLilaya())
					+ " she asks, and you quickly answer in the affirmative."
					+ "</p>"

					+ "<p>"
					+ "Lilaya brings her hand up, conjuring a swirling vortex of strange pink lights around her arm as she prepares to run her test."
					+ " Magic quite clearly exists in this world, and you make a mental note to ask Lilaya more about it after she's finished her test, but for now, you sit still like she asked."
					+ " The pink lights rapidly grow stronger, and with a sudden thrust forwards, they shoot from Lilaya's arm, darting towards the four floodlight-like instruments and causing them to emit a blinding pink flash."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Blinded", "The pink flash was so bright that you're left temporarily blinded!", INTRO_NEW_WORLD_5){
					@Override
					public void effects() {
						// Remove clothing:
						List<AbstractClothing> tempList = new ArrayList<>();
						tempList.addAll(Main.game.getPlayer().getClothingCurrentlyEquipped());

						for (AbstractClothing c : tempList) {
							Main.game.getPlayer().unequipClothingOntoFloor(c, true, Main.game.getPlayer());
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_5 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You blink once, then twice, then try rubbing your eyes."
						+ " No matter what you do, all you can see is a pulsating pink glow."
						+ " You start to panic as you realise that you've been blinded by Lilaya's test."
					+ "</p>"

					+ "<p>"
						+ "[pc.speech(Umm, Lilaya! I can't see!)] you call out."
					+ "</p>"

					+ "<p>"
						+ "Thankfully, Lilaya quickly responds with some good news,"
						+ " [lilaya.speech(O-Oh, s-sorry! I should have m-mentioned that! E-Erm... Don't worry, it'll pass in j-just a few moments!)]"
						+ " She sounds incredibly nervous, and you wonder what's happened to embarrass her this time."
					+ "</p>"

					+ "<p>"
						+ "True to her word, after a few moments the blindness starts to recede."
						+ " Slowly at first, you start to make out the figure of Lilaya standing in front of you."
						+ " After a few more blinks, your vision is completely back to normal, and you see that you're surrounded by a faint pink mist."
						+ " You look up to see that Lilaya is holding her hands up at arms-length in front of her, as though she's trying to block part of her view of you."
						+ " Her cheeks look like they're about to burst into flames, so red they've gone, and it's at that moment you notice a cool breeze blowing over your chest."
					+ "</p>"

					+ "<p>"
						+ "As you look down, Lilaya makes a little startled cry and somehow manages to blush even more,"
						+ " [lilaya.speech(~Eek!~ S-sorry! I-I didn't know that would happen!)]"
					+ "</p>"

					+ "<p>"
						+ "Now it's your turn to blush, as you look down to see that all of your clothes have disappeared."
						+ " You quickly push your legs together, moving your hands to cover yourself up as you try to hide your naked body from Lilaya's sight."
						+ " It's at this moment that the door to the lab swings open, and you and Lilaya turn to see Rose entering the room, carrying a tray with a couple of cups of tea on it."
						+ " She looks across at the two of you, and suddenly there are three blushing people in the room."
					+ "</p>"

					+ "<p>"
						+ "[lilaya.speech(Rose! Quick, d-do something!)] Lilaya shouts, frantically hopping up and down on the spot, [lilaya.speech([pc.Name]'s c-clothes have gone!)]"
					+ "</p>"
						
					+ "<p>"
						+ "Rose quickly places the tray of tea down on one of the tables, and, obeying her mistress's command, points up at one of the room's many chandeliers."
						+ " Immediately, you and Lilaya both turn your heads up to look at what the cat-girl maid is pointing at."
						+ " There, hanging from the light-fixture above you, you see some of the clothes that you were wearing just moments ago."
						+ " Glancing around, you see the other pieces of your outfit similarly scattered throughout the lab."
					+ "</p>"

					+ "<p>"
						+ "[rose.speech(I'll retrieve your clothing now,)] Rose states, casting her gaze back down at the floor as her cheeks continue to burn bright red, [rose.speech(this will only take a moment.)]"
					+ "</p>"
						
					+ "<p>"
						+ "As Rose hurries to fetch a ladder, Lilaya turns her gaze back towards you,"
						+ "[lilaya.speech(This is amazing...)]"
						+ " she says, looking at the strange pink mist that's hovering all around you, "
						+ " [lilaya.speech(I thought you wouldn't have any arcane aura at all, but... well... yours is easily as powerful as a demon's!)]"
					+ "</p>"

					+ "<p>"
						+ "[pc.speech(Uum... So is that good?)]"
						+ " you venture, shifting uncomfortably as you try to conceal more of your naked body."
					+ "</p>"

					+ "<p>"
						+ "[lilaya.speech(Well, only people with weak auras are affected by the arcane thunder, so having one that you'd normally find on a demon is kind of good in that regard.)]"
						+ " she explains, "
						+ "[lilaya.speech(But if you've got an aura like a demon... Then you should be able to harness the arcane like a demon!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("I'm a demon?!", "Lilaya keeps using the word 'Demon' to describe your 'aura'. You're starting to worry that something must have changed deep inside of you...", INTRO_NEW_WORLD_6){
					@Override
					public void effects() {
						// Equip clothing:
						List<AbstractClothing> tempList = new ArrayList<>();
						tempList.addAll(Main.game.getPlayerCell().getInventory().getAllClothingInInventory());

						for (AbstractClothing c : tempList) {
							if(!c.getClothingType().equals(ClothingType.SCIENTIST_EYES_SAFETY_GOGGLES)) {
								Main.game.getPlayer().equipClothingFromGround(c, true, Main.game.getPlayer());
							}
						}
						
						Main.game.getPlayer().equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE, DamageType.FIRE));
						
						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_6 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(So I'm a... <i>demon</i>?! Like you?!)]"
						+ " you ask, not quite believing what Lilaya's saying."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(W-What do you mean, l-like me? C-Can't you see that I'm only a... a <i>half</i>-demon?)]"
						+ " Lilaya says, sounding more than a little upset. Before you can offer any sort of apology, she waves it off with one hand before turning around to search a nearby desk for something."
						+ " She shouts back at you as she pulls open drawers and shifts piles of paperwork aside, "
						+ UtilText.parseSpeech("Anyway, you're not an <i>actual</i> demon. I mean, not having horns or anything is kind of a giveaway there!"
								+ " You mentioned that Lilith had something to do with this, and if that's the case, then she's probably the one who's given you an aura this strong."
								+ " Oh, I guess you don't know, but Lilith is the ruler of this world."
								+ " She's the most powerful demon to ever have existed, so if she's involved, this could be interesting!"
								+ " Anyway, seeing as you've got this aura, I want to test to see if you're able to use the arcane like a demon can. ~Aha! Found it!~", Main.game.getLilaya())
					+ "</p>"
					+ "<p>"
						+ "Just as Lilaya finds what she's looking for, Rose similarly finishes with her task, and, climbing back down the ladder she erected to retrieve your clothing, she starts moving towards you."
						+ " Averting her gaze from your naked form, she folds your clothing neatly in her arms, before bowing her head a little and presenting them to you."
						+ " As you take back your clothing, you groan as you see that there's something terribly wrong."
						+ " Although all of your clothing is accounted for, the arcane power that caused them to teleport up onto the chandelier appears to have also shrunk them."
						+ " Holding them up in your hands, they look to be at least several sizes too small."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Erm... Lilaya? Your test has kind of... well, shrunk everything...)] you groan."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(So? Just put it on! I'm not looking, promise!)] Lilaya responds, barely reacting to your predicament."
					+ "</p>"
					+ "<p>"
						+ "Sensing that you don't know what to do, Rose bows her head a little once more, and addresses you,"
						+ " [rose.speech("+(Main.game.getPlayer().isFeminine()?"Ma'am":"Sir")+", please forgive the interruption, but I assume that you're not aware of the fact that the arcane causes clothing to adjust to fit to your body."
								+ " If you start pulling them on, your clothes will fit themselves to you.)]"
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(T-Thank you Rose,)]"
						+ " Lilaya says, clearly flustered by her inability to identify the problem you were having,"
						+ " [lilaya.speech(if you could just wait by the door for a few moments, I'll need you again in a minute.)]"
					+ "</p>"
					+ "<p>"
						+ "As the two women turn their backs on you, you start to quickly pull on your clothes again."
						+ " Just like Rose said, they shift to fit to your body, and you wonder what else is magical in this world."
						+ " Once you're done, you inform your host that you've finished, and she turns around, smiling, before quickly moving over to you."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Right, so lets try out this demonstone! Just hold out your hand, yes, like that,)]"
						+ " Lilaya instructs,"
						+ " [lilaya.speech(Now, when I place it on your palm, just let the energy flow into you. Don't worry, you can draw it back out at any time, but for now, just let it in...)]"
					+ "</p>"
					+ "<p>"
						+ "Lilaya flicks a switch on one of the spotlights, and the pink mist vanishes from sight."
						+ " Stepping forwards, you see that she's holding a strange shard of crystal, and you look on in wonder as you see its surface glittering and swirling with images of flames."
						+ " She takes hold of your wrist with one hand, and with the other, places this 'demonstone' into your open palm."
						+ " As its surprisingly cool surface comes into contact with your skin, you feel a gentle pulsing force pushing against you, trying to push its way in."
						+ " You do as Lilaya instructed, and with surprising ease, you allow the demonstone to melt down into arcane energy and enter your body."
					+ "</p>"
					+ "<p>"
						+ "As a soothing warmth spreads up your arm, it feels like you've done this a thousand times before."
						+ " You somehow know that you're able to harness its power into what you'd once have called magic."
						+ " You know that its the same sort of arcane power that's found in that strange thunderstorm, and, indeed, all throughout this world."
						+ " And, somewhat disturbingly, you know that this 'arcane' is strongly connected to your sexual desires, and if you lose control of it, has the power to corrupt both your mind and body."
						+ " Putting such dark thoughts aside for now, you twist your hand, eager to try out this new power, and a swirling vortex of flame obediently materialises around your arm."
						+ " With another motion, you dispel it, and look up to see Lilaya beaming at you in excitement."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Magic!", "Thanks to your powerful aura, you can harness the arcane!", INTRO_NEW_WORLD_7){
					@Override
					public String getTitle() {
						if (!Main.game.getPlayer().isFeminine())
							return "You're a wizard!";
						else
							return "You're a witch!";
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_7 = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[lilaya.speech(Haha! Yes, I <i>knew</i> it!)]"
						+ " Lilaya exclaims, "
						+ "[lilaya.speech(If you had contact with Lilith, then your aura... Well... Where's that book gone?!)]"
					+ "</p>"
					+ "<p>"
						+ "Lilaya rushes off to one of the bookcases, pulling out old tomes and piling them up on one of the tables."
						+ " You're left standing there, feeling more than a little alarmed with everything that's going on."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Erm... Did you want this thing back?)]"
						+ " you ask, knowing that if you concentrate, you can draw the energy back out of your arm to re-form the demonstone that Lilaya gave you."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(What? Oh, no that's fine, you keep it!)]"
						+ " Lilaya exclaims, still piling books up on the table."
						+ " [lilaya.speech(You should be able to cast Fireball while you've got that absorbed!"
							+ " If you want to permanently learn how to cast spells, you'll need to reference a spell tome."
							+ " I know I've got a tome for Ice Shard somewhere around here."
							+ " I'll get Rose to deliver it to you once I find it!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Ok, thanks Lilaya. So... What now?)]"
						+ " You ask."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(This is all just the beginning!)]"
						+ " Lilaya calls out, stopping what she's doing and turning to face you."
						+ " [lilaya.speech(Look, once I've got through all this, we'll need to run more tests! Inter-dimensional travel! And you've somehow been <i>given</i> a demon's aura!"
								+ " You're the most amazing discovery since, well, <i>ever</i>! I'd like it if you'd stay here so I can keep an eye on you. After all, it's not like you have anywhere else to go, right?)]"
					+ "</p>"
					+ "<p>"
						+ "For the first time since arriving in this strange new world, you realise that you know nobody here, you have no money on you, and you're mostly clueless as to how this whole place works."
						+ " Thankfully, Lilaya seems to be just as kind to you as your real aunt was, and you happily accept her offer of letting you stay with her."
					+ "</p>"
					+ "<p>"
						+ "[lilaya.speech(Rose? You can show [pc.name] to a suitable room now.)]"
						+ " Lilaya says, before sitting down to consult the books she's gathered."
					+ "</p>"
					+ "<p>"
						+ "[rose.speech(Yes Mistress. Please come with me.)]"
						+ " Rose says, and you hurry to follow her as she leaves the lab."
					+ "</p>"
					+ "<p>"
						+ "You turn back to thank Lilaya once more, but you see that she's already busy flipping through pages and taking notes, and you decide against disturbing her."
						+ " Falling into line behind Rose, you let her lead the way through the house as she takes you up to your new room."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Your room", "You follow Rose as she leads you up to your new room.", INTRO_NEW_WORLD_8){
					@Override
					public void effects() {
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(new GenericPlace(PlaceType.LILAYA_HOME_ROOM_PLAYER)),
								false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_8 = new DialogueNodeOld("Your room", "You follow Rose as she leads you up to your new room.", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "As you follow Rose up to your new room, she starts filling you in on some important details about this world."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Mistress obviously wants you to stay safe while you're here, and from what I understand, you know nothing about this world."
							+ " While staying here, you're free to come and go as you please, but there are certain things you should know about Dominion.", Main.game.getRose())
					+ "</p>"

					+ "<p>"
					+ "[pc.speech(Dominion?)]"
					+ " you ask."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("That's the name of this city. I think you've already seen that it's a bad idea to go outside during an arcane storm."
							+ " While <i>you</i> might not be affected, everyone else <i>is</i>. Well, that is, apart from demons and those who've trained to harness the arcane."
							+ " So anyway, all those other people turn into sex-crazed maniacs during an arcane storm, and they won't care if you're immune or not, they'll still try to fuck you.", Main.game.getRose())
					+ "</p>"

					+ "<p>"
					+ "[pc.speech(Err... Don't the police do anything about that?)]"
					+ " you ask, slightly taken aback at Rose's warning."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("You mean the enforcers? Why would they? Once the arcane seeps into a person's mind, they'll happily consent to anything,", Main.game.getRose())
					+ " Rose replies, "
					+ UtilText.parseSpeech("So if you don't like it, then don't go outside during a storm. Oh, and don't wander off down alleyways. Although the main streets are safe, the less-travelled parts of Dominion"
							+ " are often home to the more... unsavoury sorts of people.", Main.game.getRose())
					+ "</p>"

					+ "<p>"
					+ "By this time, Rose has led you up the grand staircase in the entrance hall and down a long corridor."
					+ " She suddenly stops by one of the doors, and, opening it, steps through."
					+ " You follow her inside, and find yourself standing in a lavishly decorated bedroom."
					+ " A king-sized bed sits on side of the room, flanked by a pair of finely-crafted drawers."
					+ " An impressively-sized wardrobe and bookcase stand in the opposite corners, and between them, there's another door."
					+ " On the far side of the room, a row of windows overlook a well-tended garden courtyard, and as you see a pink flash illuminate the neatly clipped hedgerows and colourful flower beds,"
					+ " you notice that no sound of the accompanying thunder manages to penetrate into the room."
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("This is your room,", Main.game.getRose())
					+ " Rose says, before walking over to the door next to the wardrobe, "
					+ UtilText.parseSpeech("and in here is your bathroom. If you leave your dirty clothes out here when you take a shower or bath, I'll have them cleaned by the time you're finished.", Main.game.getRose())
					+ "</p>"

					+ "<p>"
					+ "[pc.speech(Thanks,)]"
					+ " you say, not really knowing quite how to react to the fact that you're now living in what looks like a palace, "
					+ "[pc.speech(so, Lilaya's pretty important then, huh? I mean, this place is huge! Does she share these gardens with those other houses?)]"
					+ "</p>"

					+ "<p>"
					+ "Rose sees you looking out the windows at the courtyard garden, "
					+ UtilText.parseSpeech("Everything you can see from here is owned by Mistress. And yes, seeing as you're completely unaware of who she is, Lilaya is the daughter of the Lilin Lyssieth.", Main.game.getRose())
					+ "</p>"

					+ "<p>"
					+ "Seeing the blank expression on your face, Rose sighs and continues, "
					+ UtilText.parseSpeech("The Lilin are daughters of Lilith herself, so being a daughter of a Lilin is kind of a big deal. Oh, and I heard you call Lilaya a demon earlier. Please don't say anything like that"
							+ " to her again, she's very sensitive about her half-demon form. While it's not usually a slave's place to speak to someone like this, I hope you understand that I'm only telling you what Lilaya"
							+ " would want you to know. If there's nothing else, I shall return to my duties now. I'll be back in about half an hour to give you any further instructions that Lilaya might have.", Main.game.getRose())
					+ "</p>"

					+ "<p>"
					+ "[pc.speech(Wait, did you just say you're a <i>slave</i>?)]"
					+ "</p>"

					+ "<p>"
					+ UtilText.parseSpeech("Mistress allows me to perform my duties without wearing a slave collar. Sorry if there was any confusion.", Main.game.getRose())
					+ " Rose curtsies to you and exits the room, leaving you in a state of shock."
					+ "</p>"

					+ "<p>"
					+ UtilText.parsePlayerThought("They still have slavery in this world?! This is unbelievable...")
					+ "</p>"

					+ "<p>"
					+ "You walk over to the bed and fall back onto it."
					+ " The events of the past few hours finally have a chance to catch up with you, and you sink down onto the comfortable mattress, letting out a deep sigh as you try to sort out in your mind what's going on."
					+ " Just as you feel yourself drifting off to sleep, a sudden knock at the door brings you back to your senses."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Knocking", "Rose said she'd be back in about half an hour, so that must be her knocking at your door.", INTRO_NEW_WORLD_9){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(500);
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS));
						AbstractItem spellBook = AbstractItemType.generateItem(ItemType.getSpellBookType(Spell.ICE_SHARD));
						Main.game.getPlayerCell().getInventory().addItem(spellBook);
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>[style.boldExcellent("+spellBook.getName()+")] added to your room's storage!</p>");
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld INTRO_NEW_WORLD_9 = new DialogueNodeOld("Knocking", "Rose said she'd be back in about half an hour, so that must be her knocking at your door.", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Come in!)]"
						+ " You shout, and Rose quickly opens the door and steps inside."
					+ "</p>"
					+ "<p>"
						+ "She curtsies to you before stepping back to pick something up she'd left outside."
						+ " A moment later, she steps back into your room, carrying one of those strange floodlight-like instruments from Lilaya's lab."
					+ "</p>"
					+ "<p>"
						+ UtilText.parseSpeech("Good evening, Mistress would like to monitor your aura as you sleep, so she needs to put this in here,", Main.game.getRose())
						+ " Rose explains, setting up the instrument in one corner of your room as she continues speaking, "
						+ UtilText.parseSpeech("Mistress also said that you can come back to the lab whenever you're ready for your next test."
								+ " There's no rush, so if you wanted to go out and get some other clothing, or explore Dominion, then you can do all that first.", Main.game.getRose())
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Ah, well, I can't really go shopping... I don't think my credit card's going to work here...)]"
						+ " You explain."
					+ "</p>"
					+ "<p>"
						+ UtilText.parseSpeech("Well, I don't know what a credit card is, but if you're trying to say you've got no money, then Mistress already thought of that.", Main.game.getRose())
					+ "</p>"
					+ "<p>"
						+ "Rose finishes installing the equipment and walks over to you, and you see that she's carrying a small purse in her hand."
						+ " She holds it out for you to take, and as she places it into your palm, you notice that it's heavier than you expected, and you can hear the sound of metal coins clinking over one another as you"
						+ " place the purse to one side."
					+ "</p>"
					+ "<p>"
						+ UtilText.parseSpeech("There are five-hundred flames in there, which should be more than enough to get some new clothes. Mistress advises that you rest through this storm and go out shopping tomorrow."
								+ " Oh, and please remember what I said about the alleyways being dangerous."
								, Main.game.getRose())
					+ "</p>"
					+ "<p>"
						+"Rose turns and steps out of the room once more, and you see her bending down to retrieve something else that she's brought up with her."
						+ " As she enters the room, you see that she's carrying a large, leather-bound book, with a pile of papers balanced on the top."
						+ " [rose.speech(Here's the spell tome that Mistress said she'd give to you."
							+ " These papers are copies of her research notes, which she said should give you all the information you'd need about arcane spells.)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Thank you Rose.)]"
						+ " You say, smiling at the cat-girl as she places the items down on your bedside table."
						+ " Rose turns towards you and curtsies, before making her exit once again."
					+ "</p>"
					+ "<p>"
						+ "You spend the next half-hour copying down Lilaya's research notes into your phone, and once that's finished, you wonder what you should do next."
						+ " The arcane storm seems to still be raging outside, and you think that it'd probably be best to get a good night's rest before wandering back out into the streets of Dominion..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Freedom!", "Decide what you want to do next.", RoomPlayer.ROOM){
					@Override
					public void effects() {
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(), true);
					}
				};
				
			} else {
				return null;
			}
		}
	};

}
