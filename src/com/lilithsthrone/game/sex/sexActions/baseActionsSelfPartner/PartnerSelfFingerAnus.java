package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.3.5.5
 * @author Innoxia
 */
public class PartnerSelfFingerAnus {
	
	public static final SexAction PARTNER_SELF_FINGER_ANUS_SPREAD_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Spread ass";
		}

		@Override
		public String getActionDescription() {
			return "Use your [npc.hands] to spread your ass.";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
						UtilText.returnStringAtRandom(
							"Reaching back with one [npc.hand], [npc.name] grabs [npc.her] [npc.ass+] and pulls to one side, letting out [npc.a_moan+] as [npc.she] presents [npc.her] [npc.asshole+] to [npc2.name].",
							"[npc.Name] reaches back with one [npc.hand], moaning softly as [npc.she] grabs hold of [npc.her] [npc.ass+], before invitingly pulling to one side and presenting [npc.her] [npc.asshole+] to [npc2.name].",
							"Sliding [npc.her] fingertips over [npc.her] [npc.asshole+],"
									+ " [npc.name] lets out [npc.a_moan+] as [npc.she] grabs one of [npc.her] [npc.assSize] ass cheeks and pulls to one one side in order to present [npc.her] [npc.asshole+] to [npc2.name].",
							"[npc.Name] eagerly slides [npc.her] [npc.fingers] over [npc.her] needy [npc.asshole],"
									+ " [npc.moaning+] as [npc.she] uses [npc.her] [npc.hand] to pull [npc.her] ass cheek to one side and present [npc.herself] for anal penetration."));
			} else {
				return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
						UtilText.returnStringAtRandom(
							"Reaching back with both [npc.hands], [npc.name] grabs [npc.her] [npc.assSize] ass cheeks and pulls them apart, letting out [npc.a_moan+] as [npc.she] presents [npc.her] [npc.asshole+] to [npc2.name].",
							"[npc.Name] reaches back with both [npc.hands], moaning softly as [npc.she] invitingly pulls [npc.her] [npc.assSize] ass cheeks apart and presents [npc.her] [npc.asshole+] to [npc2.name].",
							"Sliding [npc.her] fingertips over [npc.her] [npc.asshole+],"
									+ " [npc.name] lets out [npc.a_moan+] as [npc.she] grabs [npc.her] [npc.assSize] ass cheeks and pulls them apart in order to present [npc.her] [npc.asshole+] to [npc2.name].",
							"[npc.Name] eagerly slides [npc.her] [npc.fingers] over [npc.her] needy [npc.asshole], [npc.moaning+] as [npc.she] uses [npc.her] [npc.hands] to pull [npc.her] ass cheeks aside and present [npc.herself] for anal penetration."));
			}
		}
	};
	
	public static final SexAction PARTNER_SELF_FINGER_ANUS_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Start fingering [npc.her] ass.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Reaching around to [npc.her] [npc.ass], [npc.name] teases [npc.her] fingers over the entrance to [npc.her] [npc.asshole+], before pushing them inside and letting out [npc.a_moan+].",
					
					"[npc.Name] probes [npc.her] fingers down over [npc.her] [npc.ass], [npc.moaning+] as [npc.she] pushes two of [npc.her] digits into [npc.her] inviting [npc.asshole].",
					
					"Sliding [npc.her] fingertips over [npc.her] neglected [npc.asshole], [npc.name] lets out a [npc.groan+] as [npc.she] pushes [npc.her] digits inside.",
					
					"[npc.Name] eagerly pushes [npc.her] fingers into [npc.her] needy [npc.asshole], [npc.moaning+] as [npc.she] starts pumping [npc.her] digits in and out of [npc.her] [npc.ass].");
		}
		
	};
	
	public static final SexAction DOM_PARTNER_SELF_FINGER_ANUS_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc.her] [npc.asshole].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.asshole+].",
					
					"Gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.her] [npc.ass].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.asshole], [npc.name] lets out a little whimper as [npc.she] starts "
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()?"gently stroking [npc.her] prostate.":"gently fingering [npc.her] [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+].");
		}
		
	};
	
	public static final SexAction DOM_PARTNER_SELF_FINGER_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.her] [npc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.asshole+].",
					
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.her] [npc.ass].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] starts "
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()?"stroking [npc.her] prostate.":"fingering [npc.her] [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+].");
		}
		
	};
	
	public static final SexAction DOM_PARTNER_SELF_FINGER_ANUS_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Rough anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc.her] [npc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] roughly [npc.verb(slam)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.asshole+], before starting to roughly finger [npc.her] [npc.ass].",
					
					"Roughly pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] ruthlessly fingers [npc.her] own [npc.ass].",
					
					"Forcefully curling [npc.her] [npc.fingers] up inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] [npc.verb(start)] "
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
									?"roughly grinding [npc.her] fingertips up against [npc.her] prostate."
									:"roughly grinding [npc.her] digits in and out of [npc.her] [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+].");
		}

	};
	
	public static final SexAction SUB_PARTNER_SELF_FINGER_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.her] [npc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily pushes [npc.her] [npc.fingers] deep inside [npc.her] [npc.asshole+].",
					
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] rhythmically fingers [npc.her] [npc.ass].",
					
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] starts "
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()?"stroking [npc.her] prostate.":"fingering [npc.her] [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] starts pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+].");
		}
		
	};
	
	public static final SexAction SUB_PARTNER_SELF_FINGER_ANUS_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Eager anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc.her] [npc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly slams [npc.her] [npc.fingers] deep inside [npc.her] [npc.asshole+], before starting to desperately finger [npc.her] [npc.ass].",
					
					"Enthusiastically pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+], [npc.name] starts letting out a series of delighted [npc.moans] as [npc.she] frantically fingers [npc.her] own [npc.ass].",
					
					"Desperately curling [npc.her] [npc.fingers] up inside [npc.her] [npc.asshole], [npc.name] lets out [npc.a_moan] as [npc.she] starts "
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
									?"eagerly grinding [npc.her] fingertips up against [npc.her] prostate."
									:"eagerly grinding [npc.her] digits in and out of [npc.her] [npc.ass+]."),
					
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] eagerly starts slamming [npc.her] [npc.fingers] in and out of [npc.her] [npc.asshole+].");
		}

	};
	
	public static final SexAction PARTNER_SELF_FINGER_ANUS_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fingering [npc.her] ass.";
		}

		@Override
		public String getDescription() {
			return "With [npc.a_groan+], [npc.name] slides [npc.her] fingers out of [npc.her] [npc.asshole+].";
		}
	};
}
