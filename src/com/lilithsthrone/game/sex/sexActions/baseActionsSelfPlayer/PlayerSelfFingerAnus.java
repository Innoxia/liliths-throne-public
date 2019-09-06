package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
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
 * @version 0.3.4
 * @author Innoxia
 */
public class PlayerSelfFingerAnus {
	
	public static final SexAction PLAYER_SELF_FINGER_ANUS_SPREAD_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING && Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Spread ass";
		}

		@Override
		public String getActionDescription() {
			return "Use your [pc.hands] to spread your ass.";
		}

		@Override
		public String getDescription() {
			if(Sex.isMasturbation()) {
				if(Sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.ALL_FOURS)) {
					return UtilText.returnStringAtRandom(
							"Reaching back with one [pc.hand], you grab your [pc.ass+] and pull to one side, letting out [pc.a_moan+] as you spread your [pc.asshole+].",
							"You reach back with one [pc.hand], moaning softly as you take hold of your [pc.ass+], before invitingly pulling to one side and spreading your [pc.asshole+].",
							"Sliding your fingertips over your [pc.asshole+], you let out [pc.a_moan+] as you grab one of your [pc.assSize] ass cheeks and pull to one one side in order to present your [pc.asshole+].",
							"You eagerly slide your [pc.fingers] over your needy [pc.asshole], [pc.moaning+] as you use your [pc.hand] to pull your ass cheek to one side and present yourself for anal penetration.");
				} else {
					return UtilText.returnStringAtRandom(
							"Reaching back with both [pc.hands], you grab your [pc.assSize] ass cheeks and pull them apart, letting out [pc.a_moan+] as you spread your [pc.asshole+].",
							"You reach back with both [pc.hands], moaning softly as you invitingly pull your [pc.assSize] ass cheeks apart and spread open your [pc.asshole+].",
							"Sliding your fingertips over your [pc.asshole+], you let out [pc.a_moan+] as you grab your [pc.assSize] ass cheeks and pull them apart in order to present your [pc.asshole+].",
							"You eagerly slide your [pc.fingers] over your needy [pc.asshole], [pc.moaning+] as you use your [pc.hands] to pull your ass cheeks aside and present yourself for anal penetration.");
				}
				
			} else {
				if(Sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.ALL_FOURS)) {
					return UtilText.parse(Sex.getCharacterTargetedForSexAction(this),
							UtilText.returnStringAtRandom(
							"Reaching back with one [npc.hand], you grab your [npc.ass+] and pull to one side, letting out [npc.a_moan+] as you present your [npc.asshole+] to [npc2.name].",
							"You reach back with one [npc.hand], moaning softly as you take hold of your [npc.ass+], before invitingly pulling to one side and presenting your [npc.asshole+] to [npc2.name].",
							"Sliding your fingertips over your [npc.asshole+], you let out [npc.a_moan+] as you grab one of your [npc.assSize] ass cheeks and pull to one one side in order to present your [npc.asshole+] to [npc2.name].",
							"You eagerly slide your [npc.fingers] over your needy [npc.asshole], [npc.moaning+] as you use your [npc.hand] to pull your ass cheek to one side and present yourself for anal penetration."));
				} else {
					return UtilText.parse(Sex.getCharacterTargetedForSexAction(this),
							UtilText.returnStringAtRandom(
							"Reaching back with both [npc.hands], you grab your [npc.assSize] ass cheeks and pull them apart, letting out [npc.a_moan+] as you present your [npc.asshole+] to [npc2.name].",
							"You reach back with both [npc.hands], moaning softly as you invitingly pull your [npc.assSize] ass cheeks apart and present your [npc.asshole+] to [npc2.name].",
							"Sliding your fingertips over your [npc.asshole+], you let out [npc.a_moan+] as you grab your [npc.assSize] ass cheeks and pull them apart in order to present your [npc.asshole+] to [npc2.name].",
							"You eagerly slide your [npc.fingers] over your needy [npc.asshole], [npc.moaning+] as you use your [npc.hands] to pull your ass cheeks aside and present yourself for anal penetration."));
				}
			}
			
		}
	};
	
	public static final SexAction PLAYER_SELF_FINGER_ANUS_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Start fingering your ass.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Reaching around to your [pc.ass], you tease your [pc.fingers] over the entrance to your [pc.asshole+], before pushing them inside and letting out a deep sigh.",
					
					"You probe your [pc.fingers] down over your [pc.ass], and can't help but let out a loud [pc.moan+] as you push two of your digits into your inviting [pc.asshole].",
					
					"Sliding your fingertips down over your neglected [pc.asshole], you let out a deep [pc.moan] as you push your digits inside.",
					
					"You eagerly push your [pc.fingers] into your [pc.asshole+], [pc.moaning] out loud as you start pumping your digits in and out of your [pc.ass].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_FINGER_ANUS_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger your [pc.asshole].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you slowly push your [pc.fingers] deep inside your [pc.asshole+].",
					
					"Gently pumping your [pc.fingers] in and out of your [pc.asshole+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically finger your [pc.ass].",
					
					"Curling your [pc.fingers] up inside your [pc.asshole], you let out a little whimper as you start "
							+(Main.game.getPlayer().hasPenis() && !Sex.getCharacterPerformingAction().hasVagina()?"gently stroking your prostate.":"gently fingering your [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start gently pumping your [pc.fingers] in and out of your [pc.asshole+].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_FINGER_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering your [pc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.fingers] deep inside your [pc.asshole+].",
					
					"Pumping your [pc.fingers] in and out of your [pc.asshole+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically finger your [pc.ass].",
					
					"Curling your [pc.fingers] up inside your [pc.asshole], you let out [pc.a_moan] as you start "+(Main.game.getPlayer().hasPenis() && !Sex.getCharacterPerformingAction().hasVagina()?"stroking your prostate.":"fingering your [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start pumping your [pc.fingers] in and out of your [pc.asshole+].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_FINGER_ANUS_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger your [pc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you roughly slam your [pc.fingers] deep inside your [pc.asshole+], before starting to roughly finger your [pc.ass].",
					
					"Roughly pumping your [pc.fingers] in and out of your [pc.asshole+], you find yourself letting out a series of delighted [pc.moans] as you ruthlessly finger your own [pc.ass].",
					
					"Forcefully curling your [pc.fingers] up inside your [pc.asshole], you let out [pc.a_moan] as you start "
							+(Main.game.getPlayer().hasPenis() && !Sex.getCharacterPerformingAction().hasVagina()
									?"roughly grinding your fingertips up against your prostate."
									:"roughly grinding your digits in and out of your [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start roughly slamming your [pc.fingers] in and out of your [pc.asshole+].");
		}

	};
	
	public static final SexAction SUB_PLAYER_SELF_FINGER_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering your [pc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you greedily push your [pc.fingers] deep inside your [pc.asshole+].",
					
					"Pumping your [pc.fingers] in and out of your [pc.asshole+], you find yourself letting out a series of delighted [pc.moans] as you rhythmically finger your [pc.ass].",
					
					"Curling your [pc.fingers] up inside your [pc.asshole], you let out [pc.a_moan] as you start "
							+(Main.game.getPlayer().hasPenis() && !Sex.getCharacterPerformingAction().hasVagina()?"stroking your prostate.":"fingering your [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start pumping your [pc.fingers] in and out of your [pc.asshole+].");
		}
		
	};
	
	public static final SexAction SUB_PLAYER_SELF_FINGER_ANUS_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eager anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger your [pc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[pc.A_moan+] escapes from between your [pc.lips+] as you eagerly slam your [pc.fingers] deep inside your [pc.asshole+], before starting to desperately finger your [pc.ass].",
					
					"Enthusiastically pumping your [pc.fingers] in and out of your [pc.asshole+], you find yourself letting out a series of delighted [pc.moans] as you frantically finger your own [pc.ass].",
					
					"Desperately curling your [pc.fingers] up inside your [pc.asshole], you let out [pc.a_moan] as you start "
							+(Main.game.getPlayer().hasPenis() && !Sex.getCharacterPerformingAction().hasVagina()
									?"eagerly grinding your fingertips up against your prostate."
									:"eagerly grinding your digits in and out of your [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you eagerly start slamming your [pc.fingers] in and out of your [pc.asshole+].");
		}

	};
	
	public static final SexAction PLAYER_SELF_FINGER_ANUS_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anal fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fingering your ass.";
		}

		@Override
		public String getDescription() {
			return "With [pc.a_groan+], you slide your fingers out of your [pc.asshole+].";
		}
	};
}
