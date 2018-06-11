package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.79
 * @version 0.1.97
 * @author Innoxia
 */
public class PlayerSelfFingerAnus {
	
	public static final SexAction PLAYER_SELF_FINGER_ANUS_SPREAD_ASS = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			SexAreaPenetration.FINGER,
			SexAreaOrifice.ANUS,
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING;
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
			if((Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS)
					|| (Sex.getPosition()==SexPositionType.DOGGY_AMBER && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER)) {
				return (UtilText.returnStringAtRandom(
						"Reaching back with one [pc.hand], you grab your [pc.ass+] and pull to one side, letting out [pc.a_moan+] as you present your [pc.asshole+] to [npc.name].",
						"You reach back with one [pc.hand], moaning softly as you take hold of your [pc.ass+], before invitingly pulling to one side and presenting your [pc.asshole+] to [npc.name].",
						"Sliding your fingertips over your [pc.asshole+], you let out [pc.a_moan+] as you grab one of your [pc.assSize] ass cheeks and pull to one one side in order to present your [pc.asshole+] to [npc.name].",
						"You eagerly slide your [pc.fingers] over your needy [pc.asshole], [pc.moaning+] as you use your [pc.hand] to pull your ass cheek to one side and present yourself for anal penetration."));
			} else {
				return (UtilText.returnStringAtRandom(
						"Reaching back with both [pc.hands], you grab your [pc.assSize] ass cheeks and pull them apart, letting out [pc.a_moan+] as you present your [pc.asshole+] to [npc.name].",
						"You reach back with both [pc.hands], moaning softly as you invitingly pull your [pc.assSize] ass cheeks apart and present your [pc.asshole+] to [npc.name].",
						"Sliding your fingertips over your [pc.asshole+], you let out [pc.a_moan+] as you grab your [pc.assSize] ass cheeks and pull them apart in order to present your [pc.asshole+] to [npc.name].",
						"You eagerly slide your [pc.fingers] over your needy [pc.asshole], [pc.moaning+] as you use your [pc.hands] to pull your ass cheeks aside and present yourself for anal penetration."));
			}
		}
	};
	
	public static final SexAction PLAYER_SELF_FINGER_ANUS_PENETRATION = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			SexAreaPenetration.FINGER,
			SexAreaOrifice.ANUS,
			SexParticipantType.SELF) {
		
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
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			SexAreaPenetration.FINGER,
			SexAreaOrifice.ANUS,
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE,
			null) {
		
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
					
					"Curling your [pc.fingers] up inside your [pc.asshole], you let out a little whimper as you start "+(Main.game.getPlayer().hasPenis()?"gently stroking your prostate.":"gently fingering your [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start gently pumping your [pc.fingers] in and out of your [pc.asshole+].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_FINGER_ANUS_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			SexAreaPenetration.FINGER,
			SexAreaOrifice.ANUS,
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL,
			null) {
		
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
					
					"Curling your [pc.fingers] up inside your [pc.asshole], you let out [pc.a_moan] as you start "+(Main.game.getPlayer().hasPenis()?"stroking your prostate.":"fingering your [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start pumping your [pc.fingers] in and out of your [pc.asshole+].");
		}
		
	};
	
	public static final SexAction DOM_PLAYER_SELF_FINGER_ANUS_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.FINGER,
			SexAreaOrifice.ANUS,
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH,
			null) {
		
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
							+(Main.game.getPlayer().hasPenis()
									?"roughly grinding your fingertips up against your prostate."
									:"roughly grinding your digits in and out of your [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start roughly slamming your [pc.fingers] in and out of your [pc.asshole+].");
		}

	};
	
	public static final SexAction SUB_PLAYER_SELF_FINGER_ANUS_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			SexAreaPenetration.FINGER,
			SexAreaOrifice.ANUS,
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL,
			null) {
		
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
					
					"Curling your [pc.fingers] up inside your [pc.asshole], you let out [pc.a_moan] as you start "+(Main.game.getPlayer().hasPenis()?"stroking your prostate.":"fingering your [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you start pumping your [pc.fingers] in and out of your [pc.asshole+].");
		}
		
	};
	
	public static final SexAction SUB_PLAYER_SELF_FINGER_ANUS_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.FINGER,
			SexAreaOrifice.ANUS,
			SexParticipantType.SELF,
			SexPace.SUB_EAGER,
			null) {
		
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
							+(Main.game.getPlayer().hasPenis()
									?"eagerly grinding your fingertips up against your prostate."
									:"eagerly grinding your digits in and out of your [pc.ass+]."),
					
					"Focusing on pleasuring your [pc.ass+], you eagerly start slamming your [pc.fingers] in and out of your [pc.asshole+].");
		}

	};
	
	public static final SexAction PLAYER_SELF_FINGER_ANUS_STOP_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			SexAreaPenetration.FINGER,
			SexAreaOrifice.ANUS,
			SexParticipantType.SELF) {
		
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
