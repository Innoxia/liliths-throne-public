package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class PlayerPenisUrethraPenis {
	
	public static final SexAction PLAYER_URETHRAL_FUCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Fuck cock's urethra";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [pc.cock+] into [npc.name]'s penile urethra and start fucking it.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.cock+], you let out a little [pc.moan] before slowly pushing your [pc.hips] forwards,"
									+ " sinking your [pc.cock+] into [npc.her] [npc.penisUrethra+].",
							"You position the [pc.cockHead+] of your [pc.cock] up to [npc.name]'s [npc.cock+], and with a slow, steady pressure, you gently sink your [pc.cock+] into [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [pc.cockHead+] of your [pc.cock] between [npc.name]'s [npc.cock+], you let out [pc.a_moan+] as you buck your [pc.hips] forwards, greedily sinking your [pc.cock+] into [npc.her] [npc.penisUrethra+].",
							"You position the [pc.cockHead+] of your [pc.cock] up to [npc.name]'s [npc.cock+], and with a determined thrust of your [pc.hips], you eagerly sink your [pc.cock+] into [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [pc.cockHead+] of your [pc.cock] against [npc.name]'s [npc.cock+], you let out [pc.a_moan+] before violently slamming your [pc.hips] forwards,"
									+ " forcing your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
							"You position the [pc.cockHead+] of your [pc.cock] up to [npc.name]'s [npc.cock+], and with a forceful thrust of your [pc.hips], you roughly slam your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [pc.cockHead+] of your [pc.cock] between [npc.name]'s [npc.cock+], you let out [pc.a_moan+] as you buck your [pc.hips] forwards, greedily sinking your [pc.cock+] into [npc.her] [npc.penisUrethra+].",
							"You position the [pc.cockHead+] of your [pc.cock] up to [npc.name]'s [npc.cock+], and with a determined thrust of your [pc.hips], you eagerly sink your [pc.cock+] into [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [pc.cockHead+] of your [pc.cock] between [npc.name]'s [npc.cock+], you let out [pc.a_moan+] as you buck your [pc.hips] forwards, sinking your [pc.cock+] into [npc.her] [npc.penisUrethra+].",
							"You position the [pc.cockHead+] of your [pc.cock] up to [npc.name]'s [npc.cock+], and with a thrust of your [pc.hips], you sink your [pc.cock+] into [npc.her] [npc.penisUrethra+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] as you enter [npc.herHim], gently bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.penisUrethra+].",
							" With a soft [npc.moan], [npc.she] starts gently bucking [npc.her] [npc.hips] into your crotch, sinking your [pc.cock+] even deeper into [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.penisUrethra+].",
							" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], violently thrusting [npc.her] [npc.hips] back against you as [npc.she] roughly forces your [pc.cock+] even deeper into [npc.her] [npc.penisUrethra+].",
							" With [npc.a_moan+], [npc.she] starts violently bucking [npc.her] [npc.hips] into your crotch, roughly forcing you to sink your [pc.cock+] even deeper into [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.penisUrethra+].",
							" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.penisUrethra+].",
							" With [npc.a_moan+], [npc.she] starts bucking [npc.her] [npc.hips] into your crotch, helping to sink your [pc.cock+] even deeper into [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+] as you enter [npc.herHim], and, with tears running down [npc.her] [npc.face], [npc.she] begs for you to pull out as [npc.she] weakly struggles against you.",
							" With [npc.a_sob+], [npc.she] tries, in vain, to pull away from your unwanted penetration, tears running down [npc.her] [npc.face] as your unwelcome [pc.cock] pushes deep into [npc.her] [npc.penisUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_URETHRAL_FUCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.PITCHER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Gentle urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.name]'s [npc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you then start bucking your [pc.hips], softly pressing your groin against [npc.hers] with every thrust as you slowly fuck [npc.herHim].",
					"Slowly pushing your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you gently thrust your [pc.hips] against [npc.herHim], letting out a little [pc.moan] as you fuck [npc.herHim].",
					"Sliding your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you let out a little [pc.moan] before starting to gently buck your [pc.hips], breathing in [npc.her] [npc.scent] as you slowly fuck [npc.herHim]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] out against you,"
									+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.penisUrethra+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.penisUrethra+].",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.penisUrethra+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_URETHRAL_FUCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.PITCHER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Normal urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s [npc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you then start eagerly bucking your [pc.hips], slamming into [npc.her] groin with every thrust as you enthusiastically fuck [npc.herHim].",
					"Eagerly pushing your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you start thrusting your [pc.hips] into [npc.herHim], letting out [pc.a_moan+] as you fuck [npc.herHim].",
					"Eagerly thrusting your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you let out [pc.a_moan+] as you start frantically bucking your [pc.hips], breathing in [npc.her] [npc.scent] as you fuck [npc.herHim]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] out against you,"
									+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.penisUrethra+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.penisUrethra+].",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.penisUrethra+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_URETHRAL_FUCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.PITCHER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.name]'s [npc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming your [pc.cock+] deep into [npc.name]'s [npc.penisUrethra+], you then start violently pumping your [pc.hips], grinding into [npc.her] groin with every thrust as you brutally fuck [npc.herHim].",
					"Violently thrusting your [pc.cock+] deep into [npc.name]'s [npc.penisUrethra+], you start slamming your [pc.hips] into [npc.herHim], letting out [pc.a_moan+] as you roughly fuck [npc.herHim].",
					"Ruthlessly thrusting your [pc.cock+] deep into [npc.name]'s [npc.penisUrethra+], you let out [pc.a_moan+] as you start violently thrusting your [pc.hips], breathing in [npc.her] [npc.scent] as you roughly fuck [npc.herHim]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] out against you,"
									+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to stop abusing [npc.her] [npc.penisUrethra+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.penisUrethra+].",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.penisUrethra+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_URETHRAL_FUCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.PITCHER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Normal urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s [npc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you then start bucking your [pc.hips], slamming into [npc.her] groin with every thrust as you fuck [npc.herHim].",
					"Pushing your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you start thrusting your [pc.hips] into [npc.herHim], letting out [pc.a_moan+] as you fuck [npc.herHim].",
					"Thrusting your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you let out [pc.a_moan+] as you start bucking your [pc.hips], breathing in [npc.her] [npc.scent] as you fuck [npc.herHim]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] slowly thrusts [npc.her] [npc.hips] out in response, letting out a soft [npc.moan] as [npc.she] starts gently imploring you to continue fucking [npc.herHim].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] slowly grinds [npc.herself] against you, softly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue fucking [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly thrusting [npc.her] [npc.hips] out into your groin, [npc.she] orders you to continue fucking [npc.herHim].",
							" [npc.She] roughly grinds [npc.herself] against you, ordering you to continue as [npc.her] aggressive movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] implores you to continue fucking [npc.herHim].",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] grinds [npc.herself] against you, [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_URETHRAL_FUCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.PITCHER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.name]'s [npc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you then start eagerly bucking your [pc.hips], slamming into [npc.her] groin with every thrust as you enthusiastically fuck [npc.herHim].",
					"Eagerly pushing your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you start thrusting your [pc.hips] into [npc.herHim], letting out [pc.a_moan+] as you fuck [npc.herHim].",
					"Eagerly thrusting your [pc.cock+] into [npc.name]'s [npc.penisUrethra+], you let out [pc.a_moan+] as you start frantically bucking your [pc.hips], breathing in [npc.her] [npc.scent] as you fuck [npc.herHim]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] slowly thrusts [npc.her] [npc.hips] out in response, letting out a soft [npc.moan] as [npc.she] starts gently imploring you to continue fucking [npc.herHim].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] slowly grinds [npc.herself] against you, softly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue fucking [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly thrusting [npc.her] [npc.hips] out into your groin, [npc.she] orders you to continue fucking [npc.herHim].",
							" [npc.She] roughly grinds [npc.herself] against you, ordering you to continue as [npc.her] aggressive movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] implores you to continue fucking [npc.herHim].",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] grinds [npc.herself] against you, [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_URETHRAL_FUCK_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.PITCHER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [pc.cock] away from [npc.name]'s [npc.penisUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull your [pc.cock] free from [npc.name]'s [npc.penisUrethra+], you can't help but let out [pc.a_sob+], pushing against [npc.herHim] as you weakly beg to be released.",
					" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, pleading for [npc.herHim] to get [npc.her] [npc.penisUrethra+] off your [pc.cock].",
					" [pc.Sobbing] in distress, you weakly struggle against [npc.name], pleading for [npc.herHim] to let go of your [pc.cock]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] slowly thrusts [npc.her] [npc.hips] out against you, letting out a soft [npc.moan] as [npc.she] continues gently fucking [npc.herself] on your [pc.cock+].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] gently pushes [npc.her] [npc.hips] out against your groin, before continuing to fuck [npc.herself] on your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, slowly grinding [npc.herself] against you and softly [npc.moaning] as [npc.she] sinks your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] roughly slams [npc.her] [npc.hips] out against you, letting out [npc.a_moan+] as [npc.she] continues violently fucking [npc.herself] on your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] forcefully thrusts [npc.her] [npc.hips] out against your groin, before continuing to roughly fuck [npc.herself] on your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests,"
									+ " roughly grinding [npc.herself] against you and [npc.moaning+] out loud as [npc.she] violently forces your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] eagerly thrusts [npc.her] [npc.hips] out against you, letting out [npc.a_moan+] as [npc.she] continues happily fucking [npc.herself] on your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] eagerly thrusts [npc.her] [npc.hips] out against your groin, before continuing to energetically fuck [npc.herself] on your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, eagerly grinding [npc.herself] against you and [npc.moaning+] out loud as [npc.she] forces your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_URETHRAL_FUCK_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getActionTitle() {
			return "Stop cock urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.cock+] out of [npc.name]'s [npc.penisUrethra+] and stop fucking [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking your [pc.cock+] out of [npc.name]'s [npc.penisUrethra+], you dominantly slide your [pc.cockHead] up and down between [npc.her] folds one last time before pulling your [pc.hips] back.",
							"Thrusting deep inside [npc.name] one last time, you then yank your [pc.cock] back out of [npc.her] [npc.penisUrethra+], putting an end to your rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock] out of [npc.name]'s [npc.penisUrethra+], you slide your [pc.cockHead] up and down between [npc.her] folds one last time before pulling your [pc.hips] back.",
							"Pushing deep inside [npc.name] one last time, you then slide your [pc.cock] back out of [npc.her] [npc.penisUrethra+], putting an end to your fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.sobsVerb+] as you pull out of [npc.her] [npc.penisUrethra], but [npc.she] doesn't stop crying as [npc.she] continues to weakly struggle against you.",
							" With [npc.a_sob+], [npc.name] continues to struggle against you, tears streaming down [npc.her] [npc.face] as you withdraw from [npc.her] [npc.penisUrethra+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you pull your [pc.cock+] out of [npc.her] [npc.penisUrethra+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] lust for your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_RECEIVING_URETHRAL_FUCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.CATCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Partner can only start fucking themselves on the player's cock in consensual sex or if they're the dom.
			// You can't penetrate if your partner is already fucking you, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			
			if(Sex.isPenetrationTypeFree(Sex.getActivePartner(), SexAreaPenetration.PENIS)) {
				return (Sex.isConsensual() || !Sex.isDom(Main.game.getPlayer()));
			} else {
				return false; //(Sex.isConsensual() || !Sex.isDom(Main.game.getPlayer())) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS).contains(OrificeType.URETHRA_PENIS);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Ride [pc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] slowly slides your [pc.cock+] over the [npc.penisHead] of [npc.her] [npc.cock+],"
									+ " letting out a little [npc.moan] before gently bucking [npc.her] [npc.hips] forwards and forcing you to penetrate [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] eagerly guides your [pc.cock+] up to the [npc.penisHead] of [npc.her] [npc.cock+],"
									+ " letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] forwards and forcing you to penetrate [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully grinding [npc.herself] against you, [npc.name] guides your [pc.cock+] up to the [npc.penisHead] of [npc.her] [npc.cock+],"
									+ " letting out [npc.a_moan+] before roughly slamming [npc.her] [npc.hips] forwards, forcing you to penetrate [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] eagerly guides your [pc.cock+] up to the [npc.penisHead] of [npc.her] [npc.cock+],"
									+ " letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] forwards and forcing you to penetrate [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] guides your [pc.cock+] up to the [npc.penisHead] of [npc.her] [npc.cock+],"
									+ " letting out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards and forcing you to penetrate [npc.her] [npc.penisUrethra+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as you enter [npc.herHim], gently bucking your [pc.hips] as you start to fuck [npc.her] [npc.penisUrethra+].",
							" With a soft [pc.moan], you gently thrust your [pc.hips] into [npc.her] groin, sinking your [pc.cock+] into [npc.her] [npc.penisUrethra+] as you start fucking [npc.herHim]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly bucking your [pc.hips] as you start fucking [npc.her] [npc.penisUrethra+].",
							" With [pc.a_moan+], you eagerly thrust your [pc.hips] into [npc.her] groin, sinking your [pc.cock+] into [npc.her] [npc.penisUrethra+] as you start energetically fucking [npc.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], and, seeking to remind [npc.herHim] who's in charge, you roughly slam your [pc.hips] forwards, before starting to ruthlessly fuck [npc.her] [npc.penisUrethra+].",
							" With [pc.a_moan+], you roughly slam your [pc.hips] into [npc.her] groin, seeking to remind [npc.name] who's in charge as you start ruthlessly fucking [npc.her] [npc.penisUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly bucking your [pc.hips] as you start fucking [npc.her] [npc.penisUrethra+].",
							" With [pc.a_moan+], you eagerly thrust your [pc.hips] into [npc.her] groin, sinking your [pc.cock+] into [npc.her] [npc.penisUrethra+] as you start energetically fucking [npc.herHim]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], bucking your [pc.hips] as you start fucking [npc.her] [npc.penisUrethra+].",
							" With [pc.a_moan+], you thrust your [pc.hips] into [npc.her] groin, sinking your [pc.cock+] into [npc.her] [npc.penisUrethra+] as you start fucking [npc.herHim]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.she] forces your [pc.cock] inside of [npc.herHim], and, struggling against [npc.herHim] in vain, you desperately try to pull your [pc.cock] free from [npc.her] [npc.penisUrethra].",
							" With [pc.a_sob+], you struggle against [npc.name] as [npc.she] forces your [pc.cock] deep into [npc.her] [npc.penisUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_RECEIVING_URETHRAL_FUCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.CATCHER,
			null,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle ride";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			
			return UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.hips] out against your groin, [npc.name] lets out a soft [npc.moan] as [npc.she] helps you to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
					"With a soft [npc.moan], [npc.name] gently starts bucking [npc.her] [npc.hips], forcing your [pc.cock+] ever deeper into [npc.her] [npc.penisUrethra+].",
					"Slowly thrusting [npc.her] [npc.hips] against you, [npc.name] softly [npc.moansVerb] as [npc.her] movements force your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].");
			
		}
	};
	
	public static final SexAction PARTNER_RECEIVING_URETHRAL_FUCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.CATCHER,
			null,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Normal ride";
		}

		@Override
		public String getActionDescription() {
			return "Fuck yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.hips] against you, forcing your [pc.cock+] ever deeper into [npc.her] [npc.penisUrethra+].",
					"Enthusiastically thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].");
		}
	};
	
	public static final SexAction PARTNER_RECEIVING_URETHRAL_FUCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.CATCHER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough ride";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] roughly forces your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
					"With [npc.a_moan+], [npc.name] starts aggressively thrusting [npc.her] [npc.hips] against you, roughly forcing your [pc.cock+] ever deeper into [npc.her] [npc.penisUrethra+].",
					"Roughly thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.her] forceful movements drive your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].");
		}

	};
	
	public static final SexAction PARTNER_RECEIVING_URETHRAL_FUCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.CATCHER,
			null,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Buck hips";
		}

		@Override
		public String getActionDescription() {
			return "Buck your hips against [pc.name] as [pc.her] [pc.cock] thrusts into your [npc.penisUrethra].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Thrusting [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
					"With [npc.a_moan+], [npc.name] starts bucking [npc.her] [npc.hips] against you, forcing your [pc.cock+] ever deeper into [npc.her] [npc.penisUrethra+].",
					"Thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.her] movements force your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_RECEIVING_URETHRAL_FUCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.CATCHER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly buck hips";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [pc.name] as [pc.her] [pc.cock] thrusts into your [npc.penisUrethra].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.cock+] deep into [npc.her] [npc.penisUrethra+].",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.hips] against you, forcing your [pc.cock+] ever deeper into [npc.her] [npc.penisUrethra+].",
					"Enthusiastically thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.cock+] deep into [npc.her] [npc.penisUrethra+]."));
					
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_RECEIVING_URETHRAL_FUCK_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.CATCHER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist fucking";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.penisUrethra+] away from [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.penisUrethra+] as you continue gently fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues sliding in and out of [npc.her] [npc.penisUrethra+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues gently sliding deep into [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.penisUrethra+] as you continue eagerly fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues eagerly sliding in and out of [npc.her] [npc.penisUrethra+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues eagerly sliding deep into [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.penisUrethra+] as you continue roughly fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues roughly slamming in and out of [npc.her] [npc.penisUrethra+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues roughly slamming deep into [npc.her] [npc.penisUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_RECEIVING_URETHRAL_FUCK_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.CATCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isDom(Main.game.getPlayer()); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop getting fucked";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.cock] out of your [npc.penisUrethra+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking your [pc.cock] out of [npc.her] [npc.penisUrethra+], [npc.name] growls at you as [npc.she] commands you to stop fucking [npc.herHim].",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] yanks your [pc.cock] out of [npc.her] [npc.penisUrethra+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock] out of [npc.her] [npc.penisUrethra+], [npc.name] lets out [npc.a_moan+] as [npc.she] tells you to stop fucking [npc.herHim].",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] slides your [pc.cock] out of [npc.her] [npc.penisUrethra+]."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a relieved sigh, which soon turns into [pc.a_sob+] as you realise that [npc.she] isn't finished with you yet.",
							" With [pc.a_sob+], you continue to protest and struggle against [npc.herHim] as [npc.she] holds you firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] stops you from fucking [npc.her] [npc.penisUrethra+].",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to continue fucking [npc.her] [npc.penisUrethra+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
