package com.lilithsthrone.game.sex.sexActions.dominion.pix;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * Main positions:</br>
 * Pix reach around.</br>
 * Pix denying everything the player attempts to do.</br>
 * Pix denies orgasm until the player promises to come back and train again.
 * 
 * @since 0.1.69.9
 * @version 0.1.78
 * @author Innoxia
 */
public class PixShowerTime {
	
	// Player:

	public static final SexAction PLAYER_KISS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Kiss";
		}

		@Override
		public String getActionDescription() {
			return "Turn your head back and kiss Pix.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isOrificeFree(Sex.getActivePartner(), OrificeType.MOUTH_PARTNER) && Sex.isOrificeFree(Main.game.getPlayer(), OrificeType.MOUTH_PLAYER);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You turn your head to one side and try to kiss Pix. For a brief moment, you manage to press your [pc.lips] against hers, but with a little giggle, she pulls back, leaving you kissing mid-air.",
					"Looking back at Pix, you let out a desperate whine as you lean back to try to kiss her, but she pulls back just out of your reach, leaving you flushed and frustrated as your [pc.lips] meet nothing but empty air.",
					"Leaning back into Pix, you turn your head to try and kiss her. Seeing what you're trying to do, she lets out a little laugh as she leans away from you, preventing your [pc.lips] from even coming close to hers.",
					"Turning your head back to look at Pix, you try to kiss her, but as she sees what you're doing, she dodges away, laughing and leaving you feeling extremely foolish.");
		}

		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	public static final SexAction PLAYER_TRY_TO_FEEL_BREASTS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Pinch nipples";
		}

		@Override
		public String getActionDescription() {
			return "Reach up and pinch your [pc.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Feeling extremely frustrated, you start to reach up to your chest, hoping to give your [pc.nipples+] some attention."
							+ " Pix sees what you're doing, however, and angrily swats your hands away from your breasts, causing you to let out a desperate whine.",
							
					"You try to reach up to play with your [pc.nipples+], but, due to how tired you are, your movements are extremely sluggish, and Pix quickly pulls your hands away from your chest before you're even half-way there.",
					
					"Letting out a desperate whine, you try to reach up and grope your [pc.breasts], but Pix has other plans, and firmly yanks your hands away from your chest.",
					
					"Desperately reaching up to try and play with your [pc.nipples+], your [pc.hands] don't even make it half-way to your chest before Pix firmly pulls your [pc.arms] back down to your sides.");
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	public static final SexAction PLAYER_TRY_TO_MASTURBATE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Masturbate";
		}

		@Override
		public String getActionDescription() {
			return "Reach down between your legs and start masturbating.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Flushed and frustrated, you try to dart your [pc.hands] down between your [pc.legs], but Pix is far too quick for you in your current state,"
							+ " and instantly pulls your arms behind your back, preventing you from stimulating yourself.",
							
					"You try to reach down between your legs in order to masturbate, but Pix quickly grabs your wrists and pulls your arms behind your back.",
					
					"With a desperate whine, you attempt to move your hands down between your legs."
							+ " Letting out a little giggle as she sees what you're trying to do, Pix roughly grabs your wrists, yanking your hands away from your crotch before growling menacingly into your ear.",
					
					"Desperately reaching down to try and stimulate yourself, your hands don't even get half-way to your crotch before Pix is pulling them away and giggling into your ear.");
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Dirty talk";
		}

		@Override
		public String getActionDescription() {
			return "Talk dirty to Pix.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"As you open your mouth to try and say something to Pix, she darts one of her [pix.hands+] up and presses it over your mouth, giggling into your ear as she tells you to stay quiet.",
							
					"You try to say something to Pix, but your words are instantly muffled as she darts a hand up to cover your mouth. Laughing over your shoulder, she tells you to stay quiet and be a good [pc.girl].",
					
					"Opening your mouth, you don't even get the chance to say a single word before Pix clasps one of her [pix.hands+] over your [pc.lips+].",
					
					"As you try to speak back to Pix, she quickly clasps [pix.a_hand+] over your mouth, laughing into your ear as she prevents you from talking.");
		}

		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	public static final SexAction PLAYER_BREAK_FREE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Struggle";
		}

		@Override
		public String getActionDescription() {
			return "Try to struggle out of Pix's grasp.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Having had enough of Pix's 'fun', you desperately try to wriggle out of her grasp."
							+ " Unfortunately, you're still completely exhausted from her workout, and she easily slams you back up against the wall of the shower,"
							+ " playfully growling into your ear as you find your escape attempt coming to an abrupt end.",
							
					"Summoning what little energy you have left, you desperately try to push Pix away from you."
							+ " Letting out a little giggle, she easily overpowers you, pressing you up against the wall of the shower before softly growling into your ear.",
					
					"Twisting this way and that, you desperately try to push Pix away from you."
							+ " Laughing at your somewhat feeble attempt, she easily slams you back up against the wall of the shower, softly growling over your shoulder as you pant from exertion.",
					
					"Pushing yourself off of the wall in front of you, you try to throw Pix off your back."
							+ " With the state you're in, all you're able to do is make her stagger back a little, and with a threatening growl, she quickly slams you back up against the wall.");
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
			}
		}
	};
	
	public static final SexAction PLAYER_SUBMIT = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Submit";
		}

		@Override
		public String getActionDescription() {
			return "Let Pix do what she wants with you.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Realising that you're in no state to resist Pix's 'fun', you gently lean back into the dominant dog-girl, allowing her to use you as she sees fit.",
							
					"You decide to just let Pix use you however she wants, and gently lean back against her as she lets out an approving growl.",
					
					"Leaning forwards against the wall of the shower, you surrender yourself to Pix, allowing her to do whatever she wants with you.",
					
					"Coming to the realisation that Pix is in full control of the situation, you lean forwards against the wall of the shower, deciding to let her do whatever she wants with you.");
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
			}
		}
	};
	
	public static final SexAction FINGER_PLAYERED_GRIND_DOWN = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Grind down";
		}

		@Override
		public String getActionDescription() {
			return "Grind your [pc.pussy] down against Pix's hand.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Pushing your hips down against Pix's [pix.hand+], the dominant dog-girl lets out an enthusiastic [pix.moan], digging her [pix.fingers+] deep into your [pc.pussy+] as you gasp and shudder under the shower's running water.",
					
					"Leaning back into Pix for support, you grind your [pc.pussy+] down against her [pix.hand+], squealing in delight as she responds by digging her [pix.fingers+] deep into your [pc.pussy].",
					
					"Gently dropping your hips down onto Pix's hand, you try to encourage her to push a little deeper, and, much to your delight, you feel her respond by curling her [pix.fingers+] up before eagerly stroking your vaginal walls.",
					
					"Pushing your [pc.pussy] down against Pix's hand, she responds by letting out an eager growl before curling her [pix.fingers] up inside of you."
							+ " As she starts eagerly stroking in a 'come-hither' fashion, you lean back into the dominant dog-girl, moaning and shuddering as the hot water from the shower cascades all around you.");
		}
	};
	
	// Partner on player:
	
	public static final SexAction PARTNER_KISS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.MOUTH_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Kiss";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Pix lets out a little giggle as she leans in over your shoulder, playfully biting down and nibbling at your ear as she continues to press you firmly against the wall of the shower.",
					
					"With a playful laugh, Pix roughly pushes you up against the wall before leaning in over your shoulder to bite and nibble on your ear.",
					
					"As the hot water continues to rain down on both of you, Pix presses her [pix.face+] up against your neck, and, with a little [pix.moan], she starts using her [pix.tongue+] to playfully lick all over your shoulder.",
					
					"Leaning in over your shoulder, Pix starts using her [pix.tongue+] to playfully lick and lap at your neck and ears.");
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Dirty talk";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
				
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pressing you up against the wall, Pix softly growls into your ear, ",
					"Firmly pressing into your back, Pix leans in over your shoulder and growls, "));
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.speech(Good [pc.girl]! I hope you're enjoying your reward as much as I am!)]",
					"[npc.speech(Aww, you seem pretty tired! Funny the way that kinda worked itself out, y'know?)]",
					"[npc.speech(Y'know, you're struggling far less than some of the guys I've rewarded in the past!)]",
					"[npc.speech(Isn't this, like, super fun and stuff?! Well, it is for me!)]"));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_GROPE_BREASTS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Grope breasts";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB = new StringBuilder();
			
			UtilText.nodeContentSB.append(
					UtilText.returnStringAtRandom(
					"Pix lifts [pix.a_hand+] up to your chest, and as she squeezes down on one of your [pc.breasts+], you hear her letting out a soft little giggle.",
					
					"As water from the still-running shower falls down onto your naked body, Pix lifts [pix.a_hand+] up to your chest, and with a delighted growl, she starts roughly groping and squeezing at your [pc.breastRows] [pc.breasts].",
					
					"Your [pc.breasts+], wet from the still-running shower, are the sudden focus of Pix's attention."
					+ " Reaching one of her [pix.hands+] up to your chest, she eagerly starts squeezing and groping your mounds of flesh, growling softly into your ear as you fail to contain [pc.a_moan+].",
					
					"You let out [pc.a_moan+] as Pix reaches around and starts to grope and squeeze each of your [pc.breasts+] in turn."));
			
			switch (Main.game.getPlayer().getBreastLactation()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of [pc.milk] leaks out onto her fingertips as she squeezes down on your [pc.nipples+].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of [pc.milk] leaks out onto her fingertips as she squeezes down on your [pc.nipples+].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of [pc.milk] runs out over her fingertips as she squeezes down on your [pc.nipples+].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts to flow out over her [pix.fingers+], mixing with the shower's still-running water to run down over your [pc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts drooling out over her [pix.fingers+], mixing with the shower's still-running water to run down over your [pc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out over her [pix.fingers+], mixing with the shower's still-running water to run down over your [pc.breasts+]");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a heavy flow over her [pix.fingers+], mixing with the shower's still-running water to run down over your [pc.breasts+].");
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
			}
		}
		
	};
	
	public static final SexAction PARTNER_MASTURBATE_PLAYER_COCK = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			null) {
		@Override
		public String getActionTitle() {
			return "Stroke player's cock";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPenetrationTypeFree(Sex.getActivePartner(), PenetrationType.FINGER_PARTNER);
		}

		@Override
		public String getDescription() {
			return UtilText.genderParsing(Sex.getActivePartner(),
					UtilText.returnStringAtRandom(
					"Reaching around between your legs, Pix suddenly grabs your [pc.cock+], and as she starts stroking up and down its length, you find yourself letting out [pc.a_moan+] and leaning back into her.",
					
					"With one of her [pix.hands+], Pix reaches around between your [pc.legs+], and, taking hold of your [pc.cock+], she starts rapidly jerking you off under the running water of the shower.",
					
					"You can't help but let out [pc.a_moan+] and lean back into Pix as she reaches around to grab your [pc.cock+], and, with long, deliberate strokes, starts jerking you off.",
					
					"You let out a series of [pc.moans+] as Pix reaches around and grabs hold of your [pc.cock+]."
							+ " As she growls softly into your ear, she starts eagerly jerking you off, causing your [pc.moans] to steadily increase in volume and intensity."));
		}
	};
	
	public static final SexAction FINGER_PARTNERING_PLAYER_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Start fingering the player";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}

		@Override
		public String getDescription() {
			return UtilText.genderParsing(Sex.getActivePartner(),
					"You feel Pix press heavily into your back, and as she growls menacingly into your ear, she reaches around with [pix.a_hand+] to start stroking and probing at your outer labia."
						+ " With [pc.a_moan+], you lean back into her, and as the sound of falling water echoes off the walls all around you, she suddenly thrusts her [pix.fingers+] up, penetrating your [pc.pussy+] in one swift strike.");
		}
	};
	
	public static final SexAction FINGER_PARTNERING_PLAYER_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Gentle fingering";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Pix pushes her [pix.fingers+] up into your [pc.pussy+], curling them back and stroking gently in a 'come-hither' motion as you softly moan and pant against the wall.",
					
					"Pushing her [pix.fingers+] up into your [pc.pussy+], Pix starts to gently pump back and forth, eliciting a series of soft moans from your [pc.lips] as you lean back into her for support.",
					
					"You let out [pc.a_moan+] as Pix gently pushes her [pix.fingers+] deep into your [pc.pussy+]."
							+ " As you feel her palm push up against your outer labia, she curls her [pix.fingers] up, stroking your vaginal walls and causing you to let out another [pc.moan+].",
					
					"You let out [pc.a_moan+] and lean back into Pix for support as she starts to focus her efforts on fingering your [pc.pussy+]."
							+ " As the sound of cascading water echoes all around, Pix curls her [pix.fingers+] up inside of you and starts eagerly stroking your vaginal walls.");
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction FINGER_PARTNERING_PLAYER_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Rough fingering";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Pix violently thrusts her [pix.fingers+] up into your [pc.pussy+], curling them back and roughly stroking in a 'come-hither' motion as you let out a series of [pc.moans+].",
					
					"Roughly pushing her [pix.fingers+] up into your [pc.pussy+], Pix starts to violently pump back and forth, eliciting a series of [pc.moans+] from your [pc.lips] as you collapse back into her for support.",
					
					"You let out [pc.a_moan+] as Pix roughly thrusts her [pix.fingers+] deep into your [pc.pussy+]."
							+ " As you feel her palm slam up against your outer labia, she curls her [pix.fingers] up, desperately stroking your vaginal walls and causing you to let out another [pc.moan+].",
					
					"You let out [pc.a_moan+] and collapse back into Pix for support as she slams her [pix.fingers] deep inside your [pc.pussy+]."
							+ " As the sound of cascading water echoes all around, Pix roughly curls her [pix.fingers+] up inside of you and starts rapidly stroking your vaginal walls.");
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
			}
		}
	};
	
	public static final SexAction FINGER_PARTNERING_PLAYER_CLIT_PLAY = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Clit play";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Sinking her fingers into your [pc.pussy+], Pix lifts her thumb up to your clit."
							+ " As she presses down and start rubbing your sensitive little button, you let out [pc.a_moan+] and lean back against her for support.",
					
					"Lifting her thumb up to your delicate clit, Pix presses down and starts rubbing at your little button."
							+ " With a desperate cry, you lean back into her, your [pc.moans+] almost being drowned out by the sound of running water as she carries on playing with your feminine nub.",
					
					"You gasp and buck back against Pix's hand as you feel her thumb press down on your clit."
							+ " Leaning back into the dominant dog-girl, you grind yourself down against her hand, letting out [pc.moans+] as she carries on playing with your sensitive button.",
					
					"You squeal and lean back against Pix as she presses her thumb up against your sensitive little clit."
							+ " As she carries on pumping her fingers in and out of your [pc.pussy+], you feel her focusing her efforts on rubbing at your sensitive button, and within moments, you're letting out a series of [pc.moans+].");
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PARTNER_ASK_FOR_PROMISE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Demand promise";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.pixDemandedPromise
					&& Main.game.getPlayer().getArousal()>=ArousalLevel.FOUR_PASSIONATE.getMinimumValue();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}

		@Override
		public String getDescription() {
			return "Pix suddenly slams you up against the wall, and, leaning in over your shoulder, she starts softly growling into your ear, "
						+"[npc.speech(Y'know, I've got a little deal for you, so hear me out, ok?"
						+ " So, like, what's going to happen here, is that when you feel close to getting off, you're gonna promise me that you're gonna come back and do more training in the future, ok?"
						+ " And I mean a <i>real</i> promise!)]"
					+ "</br></br>"
					+ "You aren't really in any position to interrupt, so as Pix continues to press you up against the wall, you listen to her as she offers you her 'deal', "
					+"[npc.speech(Y'know, I can keep doing this all day, so when you're ready to cum for me, you're gonna promise that you're gonna come back to train, ok?!"
					+ " And if you don't promise, I'm not gonna let you get off!"
					+ " I sure hope you're listening, 'cause I'm like, totally serious here!)]"
					+ "</br></br>"
					+ "You try to respond, but Pix quickly slams one of her [pix.hands+] over your mouth, reminding you that you're only allowed to speak when you're about to orgasm.";
		}

		@Override
		public void applyEffects() {
			SexFlags.pixDemandedPromise = true;
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getNumberOfOrgasms(Main.game.getPlayer())>=1;
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "With a satisfied sigh, Pix finally releases you, and you find yourself collapsing back into her arms, totally exhausted.";
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
