package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.5.5
 * @author Innoxia
 */
public class PixShower {
	
	// Player:

	public static final SexAction PLAYER_KISS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
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
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isOrificeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH) && Main.sex.isOrificeFree(Main.game.getPlayer(), SexAreaOrifice.MOUTH);
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
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_TRY_TO_FEEL_BREASTS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Pinch nipples";
		}

		@Override
		public String getActionDescription() {
			return "Reach up and pinch your [npc.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.game.getPlayer().hasBreasts();
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
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_TRY_TO_MASTURBATE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
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
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
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
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_BREAK_FREE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
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
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			}
		}
	};
	
	public static final SexAction PLAYER_SUBMIT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
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
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			}
		}
	};
	
	public static final SexAction FINGERED_GRIND_DOWN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Grind down";
		}

		@Override
		public String getActionDescription() {
			return "Grind your [npc.pussy] down against Pix's hand.";
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
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
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
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
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
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.game.getPlayer().hasBreasts();
		}
		
		@Override
		public String getActionTitle() {
			return "Grope breasts";
		}

		@Override
		public String getActionDescription() {
			return "";
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
			
			switch (Main.game.getPlayer().getBreastStoredMilk()) {
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
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.game.getPlayer().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction PARTNER_MASTURBATE_PLAYER_COCK = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(null, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPenetrationTypeFree(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER);
		}
		
		@Override
		public String getActionTitle() {
			return "Stroke player's cock";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Main.sex.getCharacterPerformingAction(),
					UtilText.returnStringAtRandom(
					"Reaching around between your legs, Pix suddenly grabs your [pc.cock+], and as she starts stroking up and down its length, you find yourself letting out [pc.a_moan+] and leaning back into her.",
					
					"With one of her [pix.hands+], Pix reaches around between your [pc.legs+], and, taking hold of your [pc.cock+], she starts rapidly jerking you off under the running water of the shower.",
					
					"You can't help but let out [pc.a_moan+] and lean back into Pix as she reaches around to grab your [pc.cock+], and, with long, deliberate strokes, starts jerking you off.",
					
					"You let out a series of [pc.moans+] as Pix reaches around and grabs hold of your [pc.cock+]."
							+ " As she growls softly into your ear, she starts eagerly jerking you off, causing your [pc.moans] to steadily increase in volume and intensity."));
		}
	};
	
	public static final SexAction FINGERING_PLAYER_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Start fingering";
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
			return UtilText.parse(Main.sex.getCharacterPerformingAction(),
					"You feel Pix press heavily into your back, and as she growls menacingly into your ear, she reaches around with [pix.a_hand+] to start stroking and probing at your outer labia."
						+ " With [pc.a_moan+], you lean back into her, and as the sound of falling water echoes off the walls all around you, she suddenly thrusts her [pix.fingers+] up, penetrating your [pc.pussy+] in one swift strike.");
		}
	};
	
	public static final SexAction FINGERING_PLAYER_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
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
	
	public static final SexAction FINGERING_PLAYER_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
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
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			}
		}
	};
	
	public static final SexAction FINGERING_PLAYER_CLIT_PLAY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
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
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !SexFlags.pixDemandedPromise
					&& Main.game.getPlayer().getArousal()>=ArousalLevel.FOUR_PASSIONATE.getMinimumValue();
		}
		
		@Override
		public String getActionTitle() {
			return "Demand promise";
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
			return "Pix suddenly slams you up against the wall, and, leaning in over your shoulder, she starts softly growling into your ear, "
						+"[npc.speechNoEffects(Y'know, I've got a little deal for you, so hear me out, ok?"
						+ " So, like, what's going to happen here, is that when you feel close to getting off, you're gonna promise me that you're gonna come back and do more training in the future, ok?"
						+ " And I mean a <i>real</i> promise!)]"
					+ "<br/><br/>"
					+ "You aren't really in any position to interrupt, so as Pix continues to press you up against the wall, you listen to her as she offers you her 'deal', "
					+"[npc.speechNoEffects(Y'know, I can keep doing this all day, so when you're ready to cum for me, you're gonna promise that you're gonna come back to train, ok?!"
					+ " And if you don't promise, I'm not gonna let you get off!"
					+ " I sure hope you're listening, 'cause I'm like, totally serious here!)]"
					+ "<br/><br/>"
					+ "You try to respond, but Pix quickly slams one of her [pix.hands+] over your mouth, reminding you that you're only allowed to speak when you're about to orgasm.";
		}

		@Override
		public void applyEffects() {
			SexFlags.pixDemandedPromise = true;
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>=1;
		}
		
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
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


	// Player:
	
	public static final SexAction PLAYER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			return "You find yourself unable to make a move as Pix presses herself against your back; pinning you to the wall of the shower as she prepares to orgasm.";
		}
	};
	
	public static final SexAction PLAYER_ORGASM_PROMISE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Promise";
		}

		@Override
		public String getActionDescription() {
			return "Promise Pix that you'll come back again.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("You can't take any more of Pix's teasing, and as a desperate heat starts to build in your groin, you let out [pc.a_moan+] and do as she asks, "
										+ "[pc.speech(Ok! I promise to come back and train again! Please, just let me get off!)]."
										+ "<br/><br/>"
										+ "Pix lets out a delighted cry in response, and before you know what's happening, she's darting both of her hands down between your legs and softly growling into your ear, "
										+ "[pix.speech(Good [pc.girl]! Y'know, I think you deserve a little reward for being such a good little thing!)]");
			
			UtilText.nodeContentSB.append("<br/><br/>");
			// PENIS ORGASM:
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()){
				
				UtilText.nodeContentSB.append("You feel one of Pix's [pix.hands+] wrap around your [pc.cock+], and as she starts furiously jerking you off, she carries on telling you what a good [pc.girl] you've been.");
				
				// Describe cum amount:
				UtilText.nodeContentSB.append(" You don't last more than a few seconds under her expert touch, and as you feel your [pc.balls+] tense up");
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you let out [pc.a_moan+] as you realise that you're not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", you feel a small trickle of [pc.cum+] squirting");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", you feel a small amount of [pc.cum+] squirting");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] squirting");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] shooting");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Main.game.getPlayer().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE){
					UtilText.nodeContentSB.append(" out all over the wall in front of you. ");
				}
				
				UtilText.nodeContentSB.append("<br/><br/>");
			}
			
			// VAGINA ORGASM:
			
			if (Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				
				UtilText.nodeContentSB.append("As Pix presses her [pix.breastRows] [pix.breasts+] into your back, you feel a desperate, shuddering heat crashing up from your [pc.pussy+]."
						+ " With a little giggle, Pix suddenly thrusts two of her [pix.fingers+] deep into your [pc.pussy+], and you cry out in ecstasy as she uses her other hand to press down and rub against your sensitive little clit."
						+ " With a final, ear-splitting scream, your climax crashes over you, and you find yourself collapsing back into Pix as you feel your legs giving out from under you.");
			}
			
			// MOUND ORGASM:
			if (!Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				UtilText.nodeContentSB.append("With an ear-splitting scream and trembling legs, a crashing wave of pure ecstasy suddenly washes through you."
						+ " The muscles within your genderless mound start to spasm and contract, and you let out a desperate scream as Pix reaches down and starts rubbing and flicking at your extremely sensitive crotch."
						+ " With a final, ear-splitting scream, your climax crashes over you, and you find yourself collapsing back into Pix as you feel your legs giving out from under you.");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PLAYER_ORGASM_REFUSE = new SexAction(
			SexActionType.ORGASM_DENIAL,
			ArousalIncrease.NEGATIVE_MAJOR,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Refuse";
		}

		@Override
		public String getActionDescription() {
			return "Stay quiet in the hopes that Pix doesn't notice you're about to reach your climax.";
		}

		@Override
		public String getDescription() {
			return "You feel a desperate heat rising in your groin, but, not wanting to promise Pix that you'll come back and see her again, you decide to keep quiet and orgasm without her help."
					+ " As your climax builds and builds, you suddenly find yourself letting out a little [pc.moan]."
					+ "<br/><br/>"
					+ "Betrayed by your inability to keep quiet, Pix realises what's going on, and with an angry growl, she grabs your [pc.arms], pinning them behind your back as she slams you into the wall, "
					+ "[pix.speech(What did I say?! You're <i>not</i> orgasming until you promise to come back! Y'know, I can do this all day!)]"
					+ "<br/><br/>"
					+"She holds you there, pinned up against the wall, for several minutes, and try as you might, you're completely unable to break free."
					+ " You even try desperately rubbing your crotch up against the wall of the shower, but it's no use, and you feel your climax slowly ebbing away, leaving you incredibly frustrated and horny."
					+ " Only once it's clear that you've calmed down, does Pix finally release you, and, sliding her hands down over your body, she prepares to bring you to the edge once more...";
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			}
		}
	};
	
	// Pix orgasm:
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			return "Pix suddenly presses you up against the wall, and, leaning in over your shoulder, she growls into your ear, "
					+"[npc.speech(Aww, you ready to cum? Promise that you're gonna come back to train, or else you're never gonna get off!)]";
		}
	};
	
	public static final SexAction PARTNER_ORGASM_PIX_REWARD = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "You hear Pix letting out desperate little whines, and you realise that she's getting off on preventing you from orgasming."
					+ " Sensing your chance to break free, you try pushing her off of you, but that only seems to increase the intensity of her moans,"
					+ " and with an ear-splitting scream, she slams you forwards against the wall, grinding her naked [pix.pussy] up against your [pc.ass] as her orgasm washes over her."
					+ "<br/><br/>"
					+ "After a few moments, she finally recovers, and, straightening up, she happily sighs into your ear, "
					+ "[pix.speech(Fuck... That was good!)]";
		}

		@Override
		public void applyEffects() {
		}
	};
	


}
