package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class HandHolding {
	
	public static final SexAction PLAYER_HAND_MASSAGE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Hand massage";
		}
		@Override
		public String getActionDescription() {
			return "Massage Rose's hands.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Taking Rose's hand in one of yours, you gently press the [pc.fingers+] of your other hand down against the back of hers, rubbing and massaging her delicate skin.",
					"You take hold of Rose's hand, and with slow, deliberate movements, start massaging giving her a gentle massage.",
					"Holding Rose's hand in yours, you start to apply a gentle pressure, revelling in the feeling of her perfect, unblemished skin as you give her a loving massage.",
					"With a soft, tender care, you start massaging Rose's hands, letting out little [pc.moans] as you feel the softness of her angelic skin.");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_INTERLOCKING_FINGERS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Interlock fingers";
		}
		@Override
		public String getActionDescription() {
			return "Interlock your fingers with Rose's.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Taking Rose's hand in yours, you intertwine your fingers between hers, and with a gentle pressure, you lewdly hold her hand.",
					"In a single, deliberate movement, you grasp Rose's hand in yours, sliding your fingers between hers as you fully penetrate the inviting gaps between her digits.",
					"Letting out a determined [pc.moan], you slide your fingers between Rose's, squeezing down on her hand as you show her just how naughty you are.",
					"With a lewd thrust of your [pc.arm], you grasp Rose's perfect hand in yours, quickly interlocking your fingers with hers as you struggle to contain a desperate gasp.");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_NAIL_FOCUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Nail rub";
		}
		@Override
		public String getActionDescription() {
			return "Gently rub Rose's nails.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Rose's nails are painted a very soft shade of pink, and you find yourself unable to resist sliding your fingertips up over their flawless surface.",
					"In an incredible display of lewdness, you slide each of your fingertips over the corresponding fingernail on Rose's hand, sighing contentedly to yourself as you feel her perfect manicure.",
					"Rose wasn't lying when she said she takes good care of her hands, and as you slide your fingertips over her perfectly-manicured nails, you wonder just how long it took her to get them looking so good.",
					"The soft shade of pink that Rose has painted her fingernails perfectly compliments the pale tone of her skin, and as you rub your fingers up and down their smooth lengths, you marvel at just how perfect they are.");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction FINGER_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stroke fingers";
		}
		@Override
		public String getActionDescription() {
			return "Gently stroke Rose's fingers.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Grasping one of Rose's slender, feminine fingers in your hand, you slowly slide your fingertips up and down its length, marvelling at how soft her perfect, unblemished skin is.",
					"With a determined thrust of your hand, you grasp Rose's fingers, sliding your own digits up and down over their lengths as you fail to contain an ecstatic [pc.moan].",
					"Sliding your fingers over Rose's, you let out a little whimper in excitement as you start stroking her soft, angelic skin.",
					"Running your fingertips up and down the length of each of Rose's slender, feminine fingers, you struggle to comprehend just how perfectly formed her hands truly are.");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_LICK_PALM = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Lick palms";
		}
		@Override
		public String getActionDescription() {
			return "Lick Rose's palms.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isOrificeFree(Main.game.getPlayer(), SexAreaOrifice.MOUTH);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Lifting one of Rose's hands up to your mouth, you press your [pc.lips+] against the delicate skin of her palm, before sliding your [pc.tongue] up over her soft skin.",
					"Raising Rose's hand to your mouth, you deliver a long, slow lick to her soft palm, letting out a lewd [pc.moan] as you get a faint taste of salty sweat mixed in with her feminine scent.",
					"Taking hold of Rose's hand, you lift it up to your mouth before softly licking and lapping at her delicate palm. The gentle taste of sweat and feminine perfume hits your [pc.tongue], and you fail to contain a shuddering [pc.moan].",
					"With a little [pc.moan], you raise Rose's angelic hand to your [pc.lips+], before delivering a slow, loving lick to her soft palm.");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_START_SUCKING = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Suck fingers";
		}
		@Override
		public String getActionDescription() {
			return "Start sucking Rose's fingers.";
		}
		@Override
		public String getDescription() {
			return "In an incredible display of the sort of extreme lewdness usually confined to only the most outrageous of online-publications, you resolutely commit to taking things to the next level."
					+ " Grasping Rose's angelic, feminine hand in yours, you slowly lift it to your mouth."
					+ " Sensing what you're about to do, and in sheer anticipation of witnessing the result of the outrageous course of action you've embarked upon, Rose collapses against the wall for support,"
						+ " moaning and panting as her fingers draw ever nearer to your cavernous maw."
					+ "<br/><br/>"
					+ "As your hot breath falls on her sensitive fingertips, you briefly ponder if perhaps you've taken this a little too far, but there seems to be no easy way out now,"
						+ " and you resolutely thrust Rose's fingers past your lips and into your mouth."
					+ " Thus relegating yourself to the ranks of the lewdest of sexual deviants, and knowing that you'll never quite be the same person as you once were, you proceed to then start sucking on Rose's fingers.";
		}
	};
	
	public static final SexAction PLAYER_GENTLE_SUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Gentle suck";
		}
		@Override
		public String getActionDescription() {
			return "Gently suck Rose's fingers.";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You gently suck and kiss Rose's feminine digits, desperately running your tongue over her slender fingers as you taste her gentle combination of sweat and feminine perfume.",
					"Sucking gently on Rose's perfect fingers, you run your tongue around each of her digits in turn, letting out [pc.moans+] as you orally pleasure her hand.",
					"Determined to give Rose a good time, you gently suck and kiss at each of her feminine fingers, revelling in the feeling of being able to experience such a devious pleasure.",
					"Wrapping your [pc.lips+] around Rose's angelic fingers, you gently suck and kiss at her perfectly-formed digits, making little [pc.moaning] noises as you notice the faint taste of her sweat hitting the back of your tongue.");
		}
	};
	
	public static final SexAction PLAYER_INTENSE_SUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Intense suck";
		}
		@Override
		public String getActionDescription() {
			return "Intensely suck Rose's fingers.";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Before you know what you're doing, you've lost yourself to the devious pleasure of sucking Rose's fingers, and with a lewd cry, you desperately pick up the pace of your intense digit-sucking.",
					"With an intense sucking motion, you press your [pc.lips+] down around Rose's fingers before starting to frantically kiss and lick at her delicate digits.",
					"You decide to pick up the pace of your lewd finger-sucking, and with a desperate cry, you greedily swirl your tongue around each of her digits in turn.",
					"Rose's fingers are soft and warm in your mouth, and, not being able to hold yourself back any longer, you frantically start sucking and kissing her perfectly-formed digits.");
		}
	};
	
	public static final SexAction PLAYER_STOP_SUCKING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop sucking";
		}
		@Override
		public String getActionDescription() {
			return "Stop sucking Rose's fingers.";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Not being able to cope with just how lewd your finger-sucking has become, you slide Rose's soft, feminine digits out of your mouth."
					+ " A solitary strand of slimy saliva slowly slides out from your mouth, connecting your lips to Rose's fingertips for brief moment before breaking and falling to the floor beneath you.");
		}
	};
	
	// Rose:
	
	public static final SexAction PARTNER_MOAN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Moans";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Rose lets out a desperate cry, ",
					"With an extremely lewd moan, Rose cries out, ",
					"Desperately moaning, Rose locks her [rose.eyes+] with yours, ",
					"With a desperate, shuddering moan, Rose cries out, "));
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[rose.speech(~Aah!~ Yes! Don't stop!)]",
					"[rose.speech(Yes! Yes! ~Aah!~ Take me!)]",
					"[rose.speech(Oh yes! ~Aah!~ Keep going!)]",
					"[rose.speech(Oh! ~Aah!~ Keep going! Yes!)]",
					"[rose.speech(~Aah!~ Yes, Yes, Yes!!!)]",
					"[rose.speech(~Aah!~ Oh yes! Keep going! ~Aah!~)]"));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_PANTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Panting";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Rose seems unable to do anything but pant and [rose.moan] as you carry on stimulating her hands.",
					"Your expert hand-holding skills prove to be too much for Rose, who's unable to do anything but pant from sheer arousal.",
					"Rose's eyes roll up into the back of her head for a moment, and as her tongue lolls out of her mouth, she lets out a long, desperate moan.",
					"With a desperate [rose.moan], Rose starts panting and sighing, and you see her [rose.tongue+] lolling out of her mouth as she struggles to deal with the amount of pleasure you're giving to her.");
		}
	};
	
	public static final SexAction PARTNER_TABLE_BRACE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Brace";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Rose partially collapses against the table-top next to her, desperately bracing herself as she starts to go weak at the knees.",
					"With a little [rose.moan], Rose leans against the wall next to her, bracing herself as she struggles to cope with her intense arousal.",
					"Rose rests her back against the wall next to her, clearly unable to stand of her own volition as she lets out a shuddering [rose.moan].",
					"Leaning against the nearby table-top, Rose uses the piece of furniture for support, clearly struggling to remain upright due to the overwhelming pleasure she's receiving from your expert hand-holding skills.");
		}
	};
	
	public static final SexAction PARTNER_SLIDE_FINGERS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Slide fingers";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"With a lewd [rose.moan], Rose starts sliding her fingering in and out of your mouth, causing you to experience levels of lewdness you couldn't imagine even in your wildest dreams.",
					"Rose starts slowly sliding her fingers in and out of your mouth, and you let out a desperate whine as you feel her soft skin rubbing over your [pc.lips+].",
					"Sliding her fingers in and out of your mouth, Rose swirls them around your tongue a little before letting out a desperate [rose.moan].",
					"In an unparallelled display of outrageous sexual deviancy, Rose starts slowly sliding her fingers in and out of your mouth, allowing you to feel her unbelievably soft skin rubbing back and forth past your [pc.lips+].");
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
		public boolean isBaseRequirementsMet() {
			return Sex.getNumberOfOrgasms(Sex.getCharacterPerformingAction())>=1 && Sex.getNumberOfOrgasms(Main.game.getPlayer())>=1;
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "With a satisfied sigh, Rose disentangles herself from your clutches, and, staring lovingly into your eyes, excuses herself,"
					+ " [rose.speech(I don't think I can take any more right now! I need to get some rest, but we definitely need to do this again some time!)]";
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
