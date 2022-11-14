package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.3
 * @author Innoxia
 */
public class DoggyStyle {
	
	public static final SexAction PULL_HAIR = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Pull hair";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] hair and pull [npc2.her] head back.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_TWO) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_TWO
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_TWO;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_THREE) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_THREE
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_THREE;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_FOUR) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_FOUR
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_FOUR;
			}
			
			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairLength().isSuitableForPulling()
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairType().isAbleToBeGrabbedInSex();
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.hair(true)], and, while continuing to rhythmically slide [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] slowly [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.hair(true)] in one [npc.hand], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while burying [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" softly giggling":" smirking")+" to [npc.herself] as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to slowly sink [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.hair(true)],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.hair(true)], before slowly pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.hair(true)] in one [npc.hand], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" softly [npc.verb(giggle)]":" [npc.verb(smirk)]")
									+" to [npc.herself] as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.hair(true)],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] a handful of [npc2.namePos] [npc2.hair(true)], and, while continuing to forcefully slam [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] sharply [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing a fistful of [npc2.namePos] [npc2.hair(true)], [npc.name] simultaneously [npc.verb(yank)] [npc2.her] head back while slamming [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" maniacally giggling":" deeply grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to roughly slam [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards and [npc.verb(grab)] a fistful of [npc2.her] [npc2.hair(true)],"
										+ " before violently yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] a handful of [npc2.namePos] [npc2.hair(true)], before sharply yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing a fistful of [npc2.namePos] [npc2.hair(true)], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" maniacally [npc.verb(giggle)]":" deeply [npc.verb(grunt)]")
									+" as [npc.she] violently [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Groping and pawing [npc.her] way up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards and [npc.verb(grab)] a fistful of [npc2.her] [npc2.hair(true)],"
										+ " before roughly tugging [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.hair(true)], and, while continuing to rhythmically pump [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] steadily [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.hair(true)] in one [npc.hand], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while thrusting [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" happily giggling":" grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to energetically thrust [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards and [npc.verb(take)] a firm hold of [npc2.her] [npc2.hair(true)],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.hair(true)], before steadily pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.hair(true)] in one [npc.hand], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" happily [npc.verb(giggle)]":" [npc.verb(chuckle)]")
									+" in pleasure as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards and [npc.verb(take)] a firm hold of [npc2.her] [npc2.hair(true)],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
			}
		}
		
	};
	
	public static final SexAction PULL_EARS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Pull ears";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] [npc2.ears+] and pull [npc2.her] head back.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_TWO) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_TWO
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_TWO;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_THREE) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_THREE
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_THREE;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_FOUR) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_FOUR
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_FOUR;
			}
			
			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).getEarType().isAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.ears+] in each [npc.hand], and, while continuing to rhythmically slide [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] slowly [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while burying [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" softly giggling":" smirking")+" to [npc.herself] as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to slowly sink [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.ears+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.ears+] in each [npc.hand], before slowly pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" softly [npc.verb(giggle)]":" [npc.verb(smirk)]")
									+" to [npc.herself] as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.ears+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.ears+], and, while continuing to forcefully slam [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] sharply [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] simultaneously [npc.verb(yank)] [npc2.her] head back while slamming [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" maniacally giggling":" deeply grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to roughly slam [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(grab)] [npc2.her] [npc2.ears+],"
										+ " before violently yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.ears+] in each [npc.hand], before sharply yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" maniacally [npc.verb(giggle)]":" deeply [npc.verb(grunt)]")
									+" as [npc.she] violently [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Groping and pawing [npc.her] way up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(grab)] [npc2.her] [npc2.ears+],"
										+ " before roughly tugging [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.ears+] in each [npc.hand], and, while continuing to rhythmically pump [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] steadily [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while thrusting [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" happily giggling":" grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to energetically thrust [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a firm hold of [npc2.her] [npc2.ears+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.ears+] in each [npc.hand], before steadily pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" happily [npc.verb(giggle)]":" [npc.verb(chuckle)]")
									+" in pleasure as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a firm hold of [npc2.her] [npc2.ears+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
			}
		}
	};
	
	public static final SexAction GRAB_HORNS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Grab [npc2.horns]";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] [npc2.horns+] and pull [npc2.her] head back.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_TWO) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_TWO
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_TWO;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_THREE) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_THREE
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_THREE;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_FOUR) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_FOUR
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_FOUR;
			}

			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).isHornsAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.horns+] in each [npc.hand], and, while continuing to rhythmically slide [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] slowly [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.horns+] with both [npc.hands], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while burying [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" softly giggling":" smirking")+" to [npc.herself] as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to slowly sink [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.horns+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.horns+] in each [npc.hand], before slowly pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.horns+] with both [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" softly [npc.verb(giggle)]":" [npc.verb(smirk)]")
									+" to [npc.herself] as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.horns+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.horns+], and, while continuing to forcefully slam [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] sharply [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing [npc2.namePos] [npc2.horns+] with both [npc.hands], [npc.name] simultaneously [npc.verb(yank)] [npc2.her] head back while slamming [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" maniacally giggling":" deeply grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to roughly slam [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(grab)] [npc2.her] [npc2.horns+],"
										+ " before violently yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.horns+] in each [npc.hand], before sharply yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing [npc2.namePos] [npc2.horns+] with both [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" maniacally [npc.verb(giggle)]":" deeply [npc.verb(grunt)]")
									+" as [npc.she] violently [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Groping and pawing [npc.her] way up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(grab)] [npc2.her] [npc2.horns+],"
										+ " before roughly tugging [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.horns+] in each [npc.hand], and, while continuing to rhythmically pump [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] steadily [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.horns+] with both [npc.hands], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while thrusting [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" happily giggling":" grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to energetically thrust [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a firm hold of [npc2.her] [npc2.horns+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.horns+] in each [npc.hand], before steadily pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.horns+] with both [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" happily [npc.verb(giggle)]":" [npc.verb(chuckle)]")
									+" in pleasure as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a firm hold of [npc2.her] [npc2.horns+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
			}
		}
		
	};
	

	public static final SexAction GRAB_ANTENNAE = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Grab [npc2.antennae]";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] [npc2.antennae+] and pull [npc2.her] head back.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_TWO) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_TWO
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_TWO;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_THREE) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_THREE
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_THREE;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotAllFours.ALL_FOURS_FOUR) {
				suitableSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.BEHIND_FOUR
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotAllFours.HUMPING_FOUR;
			}

			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).isAntennaeAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.antennae+] in each [npc.hand], and, while continuing to rhythmically slide [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] slowly [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.antennae+] with both [npc.hands], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while burying [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" softly giggling":" smirking")+" to [npc.herself] as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to slowly sink [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.antennae+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.antennae+] in each [npc.hand], before slowly pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.antennae+] with both [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" softly [npc.verb(giggle)]":" [npc.verb(smirk)]")
									+" to [npc.herself] as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.antennae+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.antennae+], and, while continuing to forcefully slam [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] sharply [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing [npc2.namePos] [npc2.antennae+] with both [npc.hands], [npc.name] simultaneously [npc.verb(yank)] [npc2.her] head back while slamming [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" maniacally giggling":" deeply grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to roughly slam [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(grab)] [npc2.her] [npc2.antennae+],"
										+ " before violently yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.antennae+] in each [npc.hand], before sharply yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing [npc2.namePos] [npc2.antennae+] with both [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" maniacally [npc.verb(giggle)]":" deeply [npc.verb(grunt)]")
									+" as [npc.she] violently [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Groping and pawing [npc.her] way up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(grab)] [npc2.her] [npc2.antennae+],"
										+ " before roughly tugging [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.antennae+] in each [npc.hand], and, while continuing to rhythmically pump [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] steadily [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.antennae+] with both [npc.hands], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while thrusting [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" happily giggling":" grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to energetically thrust [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a firm hold of [npc2.her] [npc2.antennae+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.antennae+] in each [npc.hand], before steadily pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.antennae+] with both [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" happily [npc.verb(giggle)]":" [npc.verb(chuckle)]")
									+" in pleasure as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a firm hold of [npc2.her] [npc2.antennae+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
			}
		}
		
	};

	public static final SexAction LOOK_BACK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			SexSlot slot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction());
			SexSlot targetedSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this));
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& (slot.hasTag(SexSlotTag.ALL_FOURS))
					&& (targetedSlot.hasTag(SexSlotTag.BEHIND_ALL_FOURS));
		}
		
		@Override
		public String getActionTitle() {
			return "Seductive look";
		}
		@Override
		public String getActionDescription() {
			return "Turn your head back and give [npc2.name] a seductive look.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().getSubspecies()==Subspecies.getSubspeciesFromId("innoxia_raptor_subspecies_owl")
					&& Main.sex.getCharacterPerformingAction().getFaceType().getRace()==Race.getRaceFromId("innoxia_raptor")) {
				return UtilText.returnStringAtRandom(
						"In a rather creepy and alarming move, [npc.name] [npc.verb(use)] the mobility of [npc.her] owl-like neck to twist [npc.her] head a full one hundred and eighty degrees,"
								+ " before looking up at [npc2.name] and putting on [npc.her] most seductive look as [npc.she] [npc.verb(entice)] [npc2.herHim] to use [npc.herHim].",
						"Fully twisting [npc.her] owl-like neck around, [npc.name] [npc.verb(end)] up looking straight up at [npc2.name] as [npc2.she] [npc2.verb(tower)] over [npc.her] [npc.ass+]."
								+ " Hoping that this alarming and slightly creepy move won't prove to be a turn-off, [npc.name] [npc.verb(put)] on a seductive look,"
								+ " [npc.moaning] in delight as [npc.she] [npc.verb(entice)] [npc2.name] into using [npc.her] body.",
						"Making use of the large range of movement which [npc.her] owl-like neck grants [npc.herHim], [npc.name] [npc.verb(turn)] [npc.her] head fully around so that [npc.sheIs] facing [npc2.name] without moving [npc.her] body,"
								+ " before doing [npc.her] best to look as seductive as possible.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"Turning [npc.her] head back, [npc.name] [npc.verb(look)] up at [npc2.name] and [npc.verb(bite)] [npc.her] [npc.lip], putting on [npc.her] most seductive look as [npc.she] [npc.verb(entice)] [npc2.herHim] to use [npc.herHim].",
						"Looking back at [npc2.name] as [npc2.she] [npc2.verb(tower)] over [npc.her] [npc.ass+], [npc.name] [npc.verb(put)] on a seductive look,"
								+ " [npc.moaning] in delight as [npc.she] [npc.verb(entice)] [npc2.herHim] into using [npc.her] body.",
						"[npc.Name] [npc.verb(turn)] [npc.her] head and [npc.verb(bite)] [npc.her] [npc.lip] at [npc2.name], doing [npc.her] best to look as seductive as possible.",
						"Looking back, [npc.name] [npc.verb(put)] on a seductive look for [npc2.name], feeling extremely pleased with [npc.herself] as [npc.she] [npc.verb(see)] [npc2.herHim] gazing hungrily down at [npc.herHim] in return.");
			}
		}
	};
	
	// Orgasms:
	
	public static final SexAction DOGGY_DOMINANT_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.MOUTH)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getDominantParticipants(false).size()==1
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_ALL_FOURS))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<=0);
//					&& (Main.sex.getCharacterPerformingAction().isPlayer() || !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isNegative());
		}
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Math.random()<0.75f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			return "Ass-to-mouth";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
				return "Roughly fuck [npc2.name] into the floor before filling your condom with your cum. Then slide it off and use [npc2.her] mouth to clean yourself off.";
			}
			return "Roughly fuck [npc2.name] into the floor before filling [npc2.her] [npc2.ass] with your cum. Then use [npc2.her] mouth to clean yourself off.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"As [npc.name] [npc.verb(feel)] [npc2.namePos] [npc2.asshole+] squeezing down around [npc.her] [npc.cock+], [npc.she] [npc.verb(decide)] to show [npc2.herHim] how"
						+ (Main.sex.getCharacterPerformingAction().getRace()==Race.WOLF_MORPH || Main.sex.getCharacterPerformingAction().getRace()==Race.DOG_MORPH
							?" an alpha treats their submissive little beta."
							:" a real dom treats their submissive bitch.")
					+ " Letting out [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.ass+], grinning devilishly as [npc2.she] [npc2.verb(let)] out [npc2.a_moan+]."
					+ "<br/><br/>"
					+ "Reaching down, [npc.she] then [npc.verb(grab)] [npc2.namePos] shoulders, before pushing [npc.her] weight down onto [npc2.her] back as [npc.she] roughly [npc.verb(mount)] [npc2.herHim]."
					+ " With [npc.namePos] weight now on top of [npc2.herHim], [npc2.name] [npc2.verb(collapse)] to the floor with [npc2.a_moan+]."
					+ " Bending down, and with [npc.her] throbbing [npc.cock] still hilted in [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(growl)] menacingly in [npc2.her] [npc2.ear], "
							+ "[npc.speech(You little bitch! All you're good for is being my slutty cock-sleeve!)]"
					+"<br/><br/>"
					+ "Upon hearing those degrading words, [npc2.Name] [npc2.verb(let)] out another [npc2.moan+], which is enough to send [npc.name] over the edge."
					+ " As [npc.she] [npc.verb(grind)] [npc2.namePos] [npc2.face+] into the floor, [npc.she] [npc.verb(reach)] [npc.her] climax, and as [npc.her] [npc.balls+] tense up"
					);
			
			switch (Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", [npc.she] [npc.verb(realise)] that [npc.she] [npc.is]n't able to produce even one drop of cum, somewhat lessening the impact of [npc.her] dominant display.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", a small trickle of [npc.cum+] squirts");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", a small amount of [npc.cum+] squirts");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] shoots");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] shoots");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", [npc.her] load pours out");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", [npc.her] huge load pours out");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", [npc.her] enormous load pours out");
					break;
			}
			
			if(Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE) {
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append(" into the condom that [npc.sheIs] wearing.");
				} else {
					UtilText.nodeContentSB.append(" deep into [npc2.namePos] [npc2.asshole+].");
				}
			}

			if (Main.sex.getCharacterPerformingAction().getVaginaType() != VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As [npc.name] [npc.verb(slide)] [npc.her] still-throbbing shaft out from [npc2.namePos] well-used [npc2.ass], [npc.she] [npc.verb(feel)] a second orgasm building deep in [npc.her] groin."
						+ " Grabbing [npc2.namePos] hips to brace [npc.herself], [npc.name] [npc.verb(start)] to go weak at the knees before clenching [npc.her] thighs together as [npc.her] [npc.ass+] shudders and quivers."
						+ " A mind-splitting orgasm washes through [npc.herHim], and [npc.name] [npc.moanVerb] in delight as [npc.her] feminine sex joins in on the fun.");
				
			} else {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As [npc.name] [npc.verb(slide)] [npc.her] still-throbbing shaft out from [npc2.namePos] well-used [npc2.ass], [npc.she] [npc.verb(look)] down, grinning, at the mess [npc.sheHas] made of [npc2.herHim].");
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "Panting heavily, [npc.name] suddenly [npc.verb(remember)] what [npc.she] had planned, and [npc.verb(shuffle)] around to where [npc2.namePos] face is still collapsed down against the floor."
					+ (Main.sex.getCharacterTargetedForSexAction(this).hasHair()
							?" Reaching down, [npc.she] roughly [npc.verb(grab)] a fistful of [npc2.namePos] [npc2.hair+],"
							:" Reaching down, [npc.she] roughly [npc.verb(grab)] [npc2.namePos] neck,")
					+" and before [npc2.she] [npc2.has] a chance to react, [npc.she] [npc.verb(shove)] [npc2.her] [npc2.face+] down onto [npc.her] [npc.cock+]."
					+ " [npc2.Name] [npc2.moansVerb] and [npc2.verb(squirm)] as "+(Main.sex.getCharacterPerformingAction().isPlayer()?"you":"[npc2.her] dominant partner")+" [npc.verb(give)] [npc2.herHim] a taste of [npc2.her] own [npc2.ass],"
						+ " and, holding [npc2.herHim] tightly in position, [npc.she] [npc.moansVerb+] as [npc2.her] frantic [npc2.tongue] cleans [npc.herHim] off."
					+ "<br/><br/>"
					+ "After a minute of using [npc2.name] in this manner, [npc.name] finally [npc.verb(release)] [npc2.herHim], and, with a deep gasp, [npc2.she] [npc2.verb(collapse)] to the floor, completely exhausted from the dominant treatment.");

			return UtilText.nodeContentSB.toString();
		}
		

		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(SexAreaOrifice.ANUS);
			} else {
				return null;
			}
		}

		@Override
		public void applyEffects() {
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
		
	};
	
	public static final SexAction DOGGY_DOMINANT_ORGASM_PUSSY = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.MOUTH)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getDominantParticipants(false).size()==1
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_ALL_FOURS))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<=0);
//					&& (Main.sex.getCharacterPerformingAction().isPlayer() || !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isNegative());
		}

		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Math.random()<0.75f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			return "Pussy-to-mouth";
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
				return "Roughly fuck [npc2.name] into the floor before filling your condom with your cum. Then slide it off and use [npc2.her] mouth to clean yourself off.";
			}
			return "Roughly fuck [npc2.name] into the floor before filling [npc2.her] [npc2.pussy] with your cum. Then use [npc2.her] mouth to clean yourself off.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"As [npc.name] [npc.verb(feel)] [npc2.namePos] [npc2.pussy+] squeezing down around [npc.her] [npc.cock+], [npc.she] [npc.verb(decide)] to show [npc2.herHim] how"
						+ (Main.sex.getCharacterPerformingAction().getRace()==Race.WOLF_MORPH || Main.sex.getCharacterPerformingAction().getRace()==Race.DOG_MORPH
							?" an alpha treats their submissive little beta."
							:" a real dom treats their submissive bitch.")
					+ " Letting out [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], grinning devilishly as [npc2.she] [npc2.verb(let)] out [npc2.a_moan+]."
					+ "<br/><br/>"
					+ "Reaching down, [npc.she] then [npc.verb(grab)] [npc2.namePos] shoulders, before pushing [npc.her] weight down onto [npc2.her] back as [npc.she] roughly [npc.verb(mount)] [npc2.herHim]."
					+ " With [npc.namePos] weight now on top of [npc2.herHim], [npc2.name] [npc2.verb(collapse)] to the floor with [npc2.a_moan+]."
					+ " Bending down, and with [npc.her] throbbing [npc.cock] still hilted in [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(growl)] menacingly in [npc2.her] [npc2.ear], "
							+ "[npc.speech(You little bitch! All you're good for is being my slutty cock-sleeve!)]"
					+"<br/><br/>"
					+ "Upon hearing those degrading words, [npc2.Name] [npc2.verb(let)] out another [npc2.moan+], which is enough to send [npc.name] over the edge."
					+ " As [npc.she] [npc.verb(grind)] [npc2.namePos] [npc2.face+] into the floor, [npc.she] [npc.verb(reach)] [npc.her] climax, and as [npc.her] [npc.balls+] tense up"
					);
			
			switch (Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", [npc.she] [npc.verb(realise)] that [npc.she] [npc.is]n't able to produce even one drop of cum, somewhat lessening the impact of [npc.her] dominant display.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", a small trickle of [npc.cum+] squirts");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", a small amount of [npc.cum+] squirts");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] shoots");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] shoots");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", [npc.her] load pours out");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", [npc.her] huge load pours out");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", [npc.her] enormous load pours out");
					break;
			}
			
			if(Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE) {
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append(" into the condom that [npc.sheIs] wearing.");
				} else {
					UtilText.nodeContentSB.append(" deep into [npc2.namePos] [npc2.pussy+].");
				}
			}

			if (Main.sex.getCharacterPerformingAction().getVaginaType() != VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As [npc.name] [npc.verb(slide)] [npc.her] still-throbbing shaft out from [npc2.namePos] well-used [npc2.pussy], [npc.she] [npc.verb(feel)] a second orgasm building deep in [npc.her] groin."
						+ " Grabbing [npc2.namePos] hips to brace [npc.herself], [npc.name] [npc.verb(start)] to go weak at the knees before clenching [npc.her] thighs together as [npc.her] [npc.pussy+] shudders and quivers."
						+ " A mind-splitting orgasm washes through [npc.herHim], and [npc.name] [npc.moanVerb] in delight as [npc.her] feminine sex joins in on the fun.");
				
			} else {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As [npc.name] [npc.verb(slide)] [npc.her] still-throbbing shaft out from [npc2.namePos] well-used [npc2.pussy], [npc.she] [npc.verb(look)] down, grinning, at the mess [npc.sheHas] made of [npc2.herHim].");
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "Panting heavily, [npc.name] suddenly [npc.verb(remember)] what [npc.she] had planned, and [npc.verb(shuffle)] around to where [npc2.namePos] face is still collapsed down against the floor."
					+ (Main.sex.getCharacterTargetedForSexAction(this).hasHair()
							?" Reaching down, [npc.she] roughly [npc.verb(grab)] a fistful of [npc2.namePos] [npc2.hair+],"
							:" Reaching down, [npc.she] roughly [npc.verb(grab)] [npc2.namePos] neck,")
					+" and before [npc2.she] [npc2.has] a chance to react, [npc.she] [npc.verb(shove)] [npc2.her] [npc2.face+] down onto [npc.her] [npc.cock+]."
					+ " [npc2.Name] [npc2.moansVerb] and [npc2.verb(squirm)] as "+(Main.sex.getCharacterPerformingAction().isPlayer()?"you":"[npc2.her] dominant partner")+" [npc.verb(give)] [npc2.herHim] a taste of [npc2.her] own [npc2.pussy],"
						+ " and, holding [npc2.herHim] tightly in position, [npc.she] [npc.moansVerb+] as [npc2.her] frantic [npc2.tongue] cleans [npc.herHim] off."
					+ "<br/><br/>"
					+ "After a minute of using [npc2.name] in this manner, [npc.name] finally [npc.verb(release)] [npc2.herHim], and, with a deep gasp, [npc2.she] [npc2.verb(collapse)] to the floor, completely exhausted from the dominant treatment.");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(SexAreaOrifice.VAGINA);
			} else {
				return null;
			}
		}

		@Override
		public void applyEffects() {
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
	};

}
