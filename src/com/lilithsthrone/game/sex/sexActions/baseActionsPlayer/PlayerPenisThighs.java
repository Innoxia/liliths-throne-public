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
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public class PlayerPenisThighs {
	
	public static final SexAction PLAYER_THIGH_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// You can't penetrate if your partner is already fucking you, due to physical limitations.
			return Sex.isPenetrationTypeFree(Sex.getActivePartner(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [pc.cock+] between [npc.name]'s thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc.name]'s [npc.hips+], you slowly move your grip down to gently press [npc.her] legs together, before teasing the [pc.cockHead+] of your [pc.cock] up against [npc.her] thighs and thrusting forwards between them.",
							"Slowly pushing [npc.name]'s [npc.legs+] together, you gently tease the [pc.cockHead+] of your [pc.cock] up against [npc.her] thighs, before letting out [pc.a_moan+] and thrusting forwards between them."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc.name]'s [npc.hips+], you eagerly move your grip down to press [npc.her] legs together,"
									+ " before teasing the [pc.cockHead+] of your [pc.cock] up against [npc.her] thighs and desperately thrusting forwards between them.",
							"Eagerly pushing [npc.name]'s [npc.legs+] together, you tease the [pc.cockHead+] of your [pc.cock] up against [npc.her] thighs, before letting out [pc.a_moan+] and greedily thrusting forwards between them."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly taking hold of [npc.name]'s [npc.hips+], you quickly move your grip down to force [npc.her] legs together,"
									+ " before dominantly teasing the [pc.cockHead+] of your [pc.cock] up against [npc.her] thighs and violently thrusting forwards between them.",
							"Forcefully pushing [npc.name]'s [npc.legs+] together, you roughly tease the [pc.cockHead+] of your [pc.cock] up against [npc.her] thighs, before letting out [pc.a_moan+] and slamming forwards between them."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc.name]'s [npc.hips+], you eagerly move your grip down to press [npc.her] legs together,"
									+ " before teasing the [pc.cockHead+] of your [pc.cock] up against [npc.her] thighs and desperately thrusting forwards between them.",
							"Eagerly pushing [npc.name]'s [npc.legs+] together, you tease the [pc.cockHead+] of your [pc.cock] up against [npc.her] thighs, before letting out [pc.a_moan+] and greedily thrusting forwards between them."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc.name]'s [npc.hips+], you move your grip down to press [npc.her] legs together, before teasing the [pc.cockHead+] of your [pc.cock] up against [npc.her] thighs and thrusting forwards between them.",
							"Pushing [npc.name]'s [npc.legs+] together, you tease the [pc.cockHead+] of your [pc.cock] up against [npc.her] thighs, before letting out [pc.a_moan+] and thrusting forwards between them."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] in response, gently bucking [npc.her] [npc.hips] against you as [npc.she] squeezes [npc.her] thighs down around your [pc.cock+].",
							" With a soft [npc.moan], [npc.she] starts gently bucking [npc.her] [npc.hips] against you, squeezing [npc.her] thighs down as [npc.she] helps to stimulate your [pc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, eagerly bucking [npc.her] [npc.hips] against you as [npc.she] enthusiastically squeezes [npc.her] thighs down around your [pc.cock+].",
							" With [npc.a_moan+], [npc.she] starts rapidly bucking [npc.her] [npc.hips] against you, eagerly squeezing [npc.her] thighs down as [npc.she] helps to stimulate your [pc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, violently bucking [npc.her] [npc.hips] against you as [npc.she] roughly squeezes [npc.her] thighs down around your [pc.cock+].",
							" With [npc.a_moan+], [npc.she] starts violently bucking [npc.her] [npc.hips] against you, roughly squeezing [npc.her] thighs down as [npc.she] forcefully stimulates your [pc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, eagerly bucking [npc.her] [npc.hips] against you as [npc.she] enthusiastically squeezes [npc.her] thighs down around your [pc.cock+].",
							" With [npc.a_moan+], [npc.she] starts rapidly bucking [npc.her] [npc.hips] against you, eagerly squeezing [npc.her] thighs down as [npc.she] helps to stimulate your [pc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, bucking [npc.her] [npc.hips] against you as [npc.she] squeezes [npc.her] thighs down around your [pc.cock+].",
							" With [npc.a_moan+], [npc.she] starts bucking [npc.her] [npc.hips] against you, squeezing [npc.her] thighs down as [npc.she] helps to stimulate your [pc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+] as you start using [npc.herHim], and, with tears running down [npc.her] [npc.face], [npc.she] begs for you to stop as [npc.she] weakly struggles against you.",
							" With [npc.a_sob+], [npc.she] tries, in vain, to pull away from your [pc.cock+], tears running down [npc.her] [npc.face] as [npc.she] begs for you to leave [npc.herHim] alone."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Gentle thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.name]'s thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking your [pc.cock+] between [npc.name]'s thighs, you then start bucking your [pc.hips] forwards, softly bumping your groin against [npc.herHim] with every thrust.",
					"Slowly pushing your [pc.cock+] between [npc.name]'s [npc.legs+], you gently thrust your [pc.hips] against [npc.herHim], letting out a little [pc.moan] as you fuck [npc.her] thighs.",
					"Sliding your [pc.cock+] between [npc.name]'s [npc.legs+], you let out a little [pc.moan] before starting to gently buck your [pc.hips] against [npc.herHim], breathing in [npc.her] [npc.scent] as you slowly fuck [npc.her] thighs."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically grips [npc.her] thighs down around your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] against you, [npc.she] begs for you to carry on fucking [npc.her] thighs.",
							" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] against you,"
									+ " eagerly begging for you to continue fucking [npc.her] thighs as [npc.she] squeezes [npc.her] [npc.legs+] around your [pc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to leave [npc.herHim] alone.",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to stop.",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] grips [npc.her] thighs down around your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] against you, [npc.she] begs for you to carry on fucking [npc.her] thighs.",
							" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] against you, begging for you to continue fucking [npc.her] thighs as [npc.she] squeezes [npc.her] [npc.legs+] around your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Normal thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking your [pc.cock+] between [npc.name]'s thighs, you then start eagerly bucking your [pc.hips] forwards, slamming your groin against [npc.herHim] with every thrust.",
					"Eagerly pushing your [pc.cock+] between [npc.name]'s [npc.legs+], you start thrusting your [pc.hips] against [npc.herHim], letting out [pc.a_moan+] as you fuck [npc.her] thighs.",
					"Eagerly thrusting your [pc.cock+] between [npc.name]'s [npc.legs+], you let out [pc.a_moan+] as you start frantically bucking your [pc.hips] against [npc.herHim], breathing in [npc.her] [npc.scent] as you greedily fuck [npc.her] thighs."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically grips [npc.her] thighs down around your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] against you, [npc.she] begs for you to carry on fucking [npc.her] thighs.",
							" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] against you,"
									+ " eagerly begging for you to continue fucking [npc.her] thighs as [npc.she] squeezes [npc.her] [npc.legs+] around your [pc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to leave [npc.herHim] alone.",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to stop.",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] grips [npc.her] thighs down around your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] against you, [npc.she] begs for you to carry on fucking [npc.her] thighs.",
							" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] against you, begging for you to continue fucking [npc.her] thighs as [npc.she] squeezes [npc.her] [npc.legs+] around your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.name]'s thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming your [pc.cock+] between [npc.name]'s thighs, you then start violently pumping your [pc.hips] forwards, brutally grinding your groin against [npc.herHim] with every thrust.",
					"Violently thrusting your [pc.cock+] between [npc.name]'s [npc.legs+], you start roughly slamming your [pc.hips] against [npc.herHim], letting out [pc.a_moan+] as you brutally fuck [npc.her] thighs.",
					"Ruthlessly thrusting your [pc.cock+] between [npc.name]'s [npc.legs+], you let out [pc.a_moan+] as you start violently slamming your [pc.hips] against [pc.herHim], breathing in [npc.her] [npc.scent] as you roughly fuck [npc.her] thighs."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically grips [npc.her] thighs down around your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] against you, [npc.she] begs for you to carry on fucking [npc.her] thighs.",
							" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] against you,"
									+ " eagerly begging for you to continue fucking [npc.her] thighs as [npc.she] squeezes [npc.her] [npc.legs+] around your [pc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to leave [npc.herHim] alone.",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to stop.",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] grips [npc.her] thighs down around your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] against you, [npc.she] begs for you to carry on fucking [npc.her] thighs.",
							" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] against you, begging for you to continue fucking [npc.her] thighs as [npc.she] squeezes [npc.her] [npc.legs+] around your [pc.cock+]."));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_THIGH_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Normal thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking your [pc.cock+] between [npc.name]'s thighs, you then start bucking your [pc.hips] forwards, slamming your groin against [npc.herHim] with every thrust.",
					"Pushing your [pc.cock+] between [npc.name]'s [npc.legs+], you start thrusting your [pc.hips] against [npc.herHim], letting out [pc.a_moan+] as you fuck [npc.her] thighs.",
					"Thrusting your [pc.cock+] between [npc.name]'s [npc.legs+], you let out [pc.a_moan+] as you start bucking your [pc.hips] against [npc.herHim], breathing in [npc.her] [npc.scent] as you fuck [npc.her] thighs."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] slowly thrusts [npc.her] [npc.hips] out in response, letting out a soft [npc.moan] as [npc.she] gently implores you to continue.",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue.",
							" [npc.Moaning] in delight, [npc.she] slowly grinds [npc.herself] against you, softly [npc.moaning] for you to continue as [npc.she] squeezes [npc.her] [npc.legs+] down around your [pc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue.",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly thrusting [npc.her] [npc.hips] out against your groin, [npc.she] orders you to continue.",
							" [npc.She] roughly grinds [npc.herself] against you, ordering you to continue as [npc.she] aggressively squeezes [npc.her] [npc.legs+] down around your [pc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] implores you to continue.",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue.",
							" [npc.Moaning] in delight, [npc.she] grinds [npc.herself] against you, [npc.moaning] for you to continue as [npc.she] squeezes [npc.her] [npc.legs+] down around your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.name]'s thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking your [pc.cock+] between [npc.name]'s thighs, you then start eagerly bucking your [pc.hips] forwards, slamming your groin against [npc.herHim] with every thrust.",
					"Eagerly pushing your [pc.cock+] between [npc.name]'s [npc.legs+], you start thrusting your [pc.hips] against [npc.herHim], letting out [pc.a_moan+] as you fuck [npc.her] thighs.",
					"Eagerly thrusting your [pc.cock+] between [npc.name]'s [npc.legs+], you let out [pc.a_moan+] as you start frantically bucking your [pc.hips] against [npc.herHim], breathing in [npc.her] [npc.scent] as you fuck [npc.her] thighs."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] slowly thrusts [npc.her] [npc.hips] out in response, letting out a soft [npc.moan] as [npc.she] gently implores you to continue.",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue.",
							" [npc.Moaning] in delight, [npc.she] slowly grinds [npc.herself] against you, softly [npc.moaning] for you to continue as [npc.she] squeezes [npc.her] [npc.legs+] down around your [pc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue.",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly thrusting [npc.her] [npc.hips] out against your groin, [npc.she] orders you to continue.",
							" [npc.She] roughly grinds [npc.herself] against you, ordering you to continue as [npc.she] aggressively squeezes [npc.her] [npc.legs+] down around your [pc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] implores you to continue.",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue.",
							" [npc.Moaning] in delight, [npc.she] grinds [npc.herself] against you, [npc.moaning] for you to continue as [npc.she] squeezes [npc.her] [npc.legs+] down around your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_THIGH_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [pc.cock] away from [npc.name]'s thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull your [pc.cock] free from [npc.name]'s thighs, you can't help but let out [pc.a_sob+] as you weakly beg to be released.",
					" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, pleading for [npc.herHim] to leave you alone.",
					" [pc.Sobbing] in distress, you weakly struggle against [npc.name], pleading for [npc.herHim] to let go of your [pc.cock]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] slowly thrusts [npc.her] [npc.hips] out against you, letting out a soft [npc.moan] as [npc.she] gently squeezes [npc.her] [npc.legs+] down around your [pc.cock+].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] gently pushes [npc.her] [npc.hips] out against your groin, before squeezing [npc.her] [npc.legs+] down around your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, slowly grinding [npc.herself] against you and softly [npc.moaning] as [npc.she] squeezes [npc.her] [npc.legs+] down around your [pc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] roughly slams [npc.her] [npc.hips] out against you, letting out [npc.a_moan+] as [npc.she] continues violently squeezing [npc.her] [npc.legs+] down around your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] forcefully thrusts [npc.her] [npc.hips] out against your groin, before continuing to roughly squeeze [npc.her] [npc.legs+] down around your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests,"
									+ " roughly grinding [npc.herself] against you and [npc.moaning+] out loud as [npc.she] violently squeezes [npc.her] [npc.legs+] down around your [pc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] eagerly thrusts [npc.her] [npc.hips] out against you, letting out [npc.a_moan+] as [npc.she] continues squeezing [npc.her] [npc.legs+] down around your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] eagerly thrusts [npc.her] [npc.hips] out against your groin, before continuing to energetically squeeze [npc.her] [npc.legs+] down around your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, eagerly grinding [npc.herself] against you and [npc.moaning+] out loud as [npc.she] squeezes [npc.her] [npc.legs+] down around your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_THIGH_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL) {
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
			return "Stop thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.cock+] out from between [npc.name]'s legs and stop fucking [npc.her] thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking your [pc.cock+] out from between [npc.name]'s legs, you dominantly slide your [pc.cockHead] up and down over [npc.her] thighs one last time before pulling your [pc.hips] back.",
							"Thrusting deep between [npc.name]'s [npc.legs+] one last time, you then yank your [pc.cock] back out from between [npc.her] thighs."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock] out from between [npc.name]'s legs, you slide your [pc.cockHead] up and down over [npc.her] thighs one last time before pulling your [pc.hips] back.",
							"Pushing deep between [npc.name]'s [npc.legs+] one last time, you then slide your [pc.cock] back out from between [npc.her] thighs."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.sobsVerb+] as you pull back, and doesn't stop crying as [npc.she] continues to weakly struggle against you.",
							" With [npc.a_sob+], [npc.name] continues to struggle against you, tears streaming down [npc.her] [npc.face] as you stop using [npc.her] thighs."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you pull your [pc.cock+] out from between [npc.her] thighs.",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] lust for your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_THIGH_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.isPenetrationTypeFree(Sex.getActivePartner(), SexAreaPenetration.PENIS)) {
				return (Sex.isSubHasEqualControl() || !Sex.isDom(Main.game.getPlayer()));
			} else {
				return false;
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Receive thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Force [pc.name]'s [pc.cock+] between your thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] slowly slides your [pc.cock+] between [npc.her] [npc.legs+],"
									+ " letting out a little [npc.moan] before gently bucking [npc.her] [npc.hips] against you and forcing your [pc.cock+] between [npc.her] thighs.",
							"Lining your [pc.cock+] up between [npc.her] [npc.legs], [npc.name] slowly pushes [npc.her] [npc.hips] against you,"
									+ " softly [npc.moaning] as [npc.she] gently forces your [pc.cock+] between [npc.her] thighs."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] eagerly slides your [pc.cock+] between [npc.her] [npc.legs+],"
									+ " letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] against you and forcing your [pc.cock+] between [npc.her] thighs.",
							"Lining your [pc.cock+] up between [npc.her] [npc.legs], [npc.name] eagerly pushes [npc.her] [npc.hips] against you,"
									+ " [npc.moaning+] as [npc.she] greedily forces your [pc.cock+] between [npc.her] thighs."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully grinding [npc.herself] against you, [npc.name] roughly positions your [pc.cock+] between [npc.her] [npc.legs+],"
									+ " letting out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] against you and forcing your [pc.cock+] between [npc.her] thighs.",
							"Lining your [pc.cock+] up between [npc.her] [npc.legs], [npc.name] violently slams [npc.her] [npc.hips] against you,"
									+ " [npc.moaning+] as [npc.she] roughly forces your [pc.cock+] between [npc.her] thighs."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] eagerly slides your [pc.cock+] between [npc.her] [npc.legs+],"
									+ " letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] against you and forcing your [pc.cock+] between [npc.her] thighs.",
							"Lining your [pc.cock+] up between [npc.her] [npc.legs], [npc.name] eagerly pushes [npc.her] [npc.hips] against you,"
									+ " [npc.moaning+] as [npc.she] greedily forces your [pc.cock+] between [npc.her] thighs."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] slides your [pc.cock+] between [npc.her] [npc.legs+],"
									+ " letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] against you and forcing your [pc.cock+] between [npc.her] thighs.",
							"Lining your [pc.cock+] up between [npc.her] [npc.legs], [npc.name] pushes [npc.her] [npc.hips] against you,"
									+ " [npc.moaning+] as [npc.she] forces your [pc.cock+] between [npc.her] thighs."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] in response, gently bucking your [pc.hips] into [npc.herHim] as you start to fuck [npc.her] thighs.",
							" With a soft [pc.moan], you gently thrust your [pc.hips] forwards, sinking your [pc.cock+] between [npc.her] thighs as you start gently fucking them."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, eagerly bucking your [pc.hips] into [npc.herHim] as you start to fuck [npc.her] thighs.",
							" With [pc.a_moan+], you eagerly thrust your [pc.hips] forwards, sinking your [pc.cock+] between [npc.her] thighs as you start energetically fucking them."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, and, seeking to remind [npc.herHim] who's in charge, you roughly slam your [pc.hips] into [npc.herHim] as you start to ruthlessly fuck [npc.her] thighs.",
							" With [pc.a_moan+], you roughly slam your [pc.hips] forwards, sinking your [pc.cock+] between [npc.her] thighs as you start ruthlessly fucking them."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, eagerly bucking your [pc.hips] into [npc.herHim] as you start to fuck [npc.her] thighs.",
							" With [pc.a_moan+], you eagerly thrust your [pc.hips] forwards, sinking your [pc.cock+] between [npc.her] thighs as you start energetically fucking them."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, bucking your [pc.hips] into [npc.herHim] as you start to fuck [npc.her] thighs.",
							" With [pc.a_moan+], you thrust your [pc.hips] forwards, sinking your [pc.cock+] between [npc.her] thighs as you start fucking them."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.she] forces your [pc.cock] between [npc.her] thighs, and, struggling against [npc.herHim] in vain, you desperately beg [npc.herHim] to stop.",
							" With [pc.a_sob+], you struggle against [npc.name] as [npc.she] forces your [pc.cock] deep between [npc.her] thighs."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_THIGH_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
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
			return "Gentle thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Gently force [pc.name]'s [pc.cock+] between your thighs.";
		}

		@Override
		public String getDescription() {
		
			return UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.hips] out against your groin, [npc.name] lets out a soft [npc.moan] as [npc.she] squeezes [npc.her] [npc.legs+] down around your [pc.cock+].",
					"With a soft [npc.moan], [npc.name] gently starts bucking [npc.her] [npc.hips] against you, squeezing [npc.her] [npc.legs+] down around your [pc.cock+] as you fuck [npc.her] thighs.",
					"Slowly thrusting [npc.her] [npc.hips] against you, [npc.name] softly [npc.moansVerb] as [npc.she] squeezes [npc.her] [npc.legs+] down around your [pc.cock+].");
			
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
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
			return "Normal thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Force [pc.name]'s [pc.cock+] between your thighs.";
		}

		@Override
		public String getDescription() {

			return UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] greedily squeezes [npc.her] [npc.legs+] down around your [pc.cock+].",
					"With [npc.a_moan+], [npc.name] energetically starts bucking [npc.her] [npc.hips] against you, eagerly squeezing [npc.her] [npc.legs+] down around your [pc.cock+] as you fuck [npc.her] thighs.",
					"Enthusiastically thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.she] greedily squeezes [npc.her] [npc.legs+] down around your [pc.cock+].");
			
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
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
			return "Rough thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [pc.name]'s [pc.cock+] between your thighs.";
		}

		@Override
		public String getDescription() {

			return UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] roughly squeezes [npc.her] [npc.legs+] down around your [pc.cock+].",
					"With [npc.a_moan+], [npc.name] aggressively starts bucking [npc.her] [npc.hips] against you, roughly squeezing [npc.her] [npc.legs+] down around your [pc.cock+] as you fuck [npc.her] thighs.",
					"Roughly thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.she] forcefully squeezes [npc.her] [npc.legs+] down around your [pc.cock+].");
			
		}

	};
	
	public static final SexAction PARTNER_THIGH_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
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
			return "Normal thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Buck your hips against [pc.name] as [pc.her] [pc.cock] thrusts between your [npc.thighs].";
		}

		@Override
		public String getDescription() {
			
			return UtilText.returnStringAtRandom(
					"Thrusting [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep between [npc.her] thighs.",
					"With [npc.a_moan+], [npc.name] starts bucking [npc.her] [npc.hips] against you, forcing your [pc.cock+] ever deeper between [npc.her] thighs.",
					"Thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.her] movements force your [pc.cock+] deep between [npc.her] thighs.");
			
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
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
			return "Eager thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [pc.name] as [pc.her] [pc.cock] thrusts between your [npc.thighs].";
		}

		@Override
		public String getDescription() {

			return UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] greedily squeezes [npc.her] [npc.legs+] down around your [pc.cock+].",
					"With [npc.a_moan+], [npc.name] energetically starts bucking [npc.her] [npc.hips] against you, eagerly squeezing [npc.her] [npc.legs+] down around your [pc.cock+] as you fuck [npc.her] thighs.",
					"Enthusiastically thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.she] greedily squeezes [npc.her] [npc.legs+] down around your [pc.cock+].");
					
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
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
			return "Resist thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your thighs away from [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out from between [npc.her] [npc.legs] as you continue gently fucking [npc.her] thighs.",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] away from you,"
									+ " struggling in desperation as your [pc.cock+] continues sliding in and out between [npc.her] thighs.",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues gently sliding in and out between [npc.her] thighs."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out from between [npc.her] [npc.legs] as you continue eagerly fucking [npc.her] thighs.",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] away from you,"
									+ " struggling in desperation as your [pc.cock+] continues eagerly sliding in and out between [npc.her] thighs.",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues eagerly sliding in and out between [npc.her] thighs."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out from between [npc.her] [npc.legs] as you continue roughly fucking [npc.her] thighs.",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] away from you,"
									+ " struggling in desperation as your [pc.cock+] continues roughly slamming in and out between [npc.her] thighs.",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues roughly slamming in and out between [npc.her] thighs."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_THIGH_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isSubHasEqualControl() || !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Stop thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.cock] out from between your thighs.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking your [pc.cock] out from between [npc.her] [npc.legs], [npc.name] growls at you as [npc.she] commands you to stop fucking [npc.her] thighs.",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] suddenly yanks your [pc.cock] out from between [npc.her] thighs."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock] out from between [npc.her] [npc.legs], [npc.name] lets out [npc.a_moan+] as [npc.she] tells you to stop fucking [npc.her] thighs.",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] slides your [pc.cock] out from between [npc.her] thighs."));
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
							" You let out [pc.a_moan+] as [npc.she] stops you from fucking [npc.her] thighs.",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to continue fucking [npc.her] thighs."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
