package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public class PartnerPenisThighs {
	
	public static final SexAction PARTNER_THIGH_SEX_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// NPC can't penetrate if PC is already fucking them, due to physical limitations.
			return Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS_PLAYER)==null;
		}
		
		@Override
		public String getActionTitle() {
			return "Thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.cock+] between [pc.name]'s thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of your [pc.hips+], [npc.name] slowly moves [npc.her] grip down to gently press your legs together, before teasing the [npc.cockHead+] of [npc.her] [npc.cock] up against your thighs and thrusting forwards between them.",
							"Slowly pushing your [pc.legs+] together, [npc.name] gently teases the [npc.cockHead+] of [npc.her] [npc.cock] up against your thighs, before letting out [npc.a_moan+] and thrusting forwards between them."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of your [pc.hips+], [npc.name] eagerly moves [npc.her] grip down to press your legs together,"
									+ " before teasing the [npc.cockHead+] of [npc.her] [npc.cock] up against your thighs and desperately thrusting forwards between them.",
							"Eagerly pushing your [pc.legs+] together, [npc.name] teases the [npc.cockHead+] of [npc.her] [npc.cock] up against your thighs, before letting out [npc.a_moan+] and greedily thrusting forwards between them."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly taking hold of your [pc.hips+], [npc.name] quickly moves [npc.her] grip down to force your legs together,"
									+ " before dominantly teasing the [npc.cockHead+] of [npc.her] [npc.cock] up against your thighs and violently thrusting forwards between them.",
							"Forcefully pushing your [pc.legs+] together, [npc.name] roughly teases the [npc.cockHead+] of [npc.her] [npc.cock] up against your thighs, before letting out [npc.a_moan+] and slamming forwards between them."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of your [pc.hips+], [npc.name] eagerly moves [npc.her] grip down to press your legs together,"
									+ " before teasing the [npc.cockHead+] of [npc.her] [npc.cock] up against your thighs and desperately thrusting forwards between them.",
							"Eagerly pushing your [pc.legs+] together, [npc.name] teases the [npc.cockHead+] of [npc.her] [npc.cock] up against your thighs, before letting out [npc.a_moan+] and greedily thrusting forwards between them."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of your [pc.hips+], [npc.name] moves [npc.her] grip down to press your legs together, before teasing the [npc.cockHead+] of [npc.her] [npc.cock] up against your thighs and thrusting forwards between them.",
							"Pushing your [pc.legs+] together, [npc.name] teases the [npc.cockHead+] of [npc.her] [npc.cock] up against your thighs, before letting out [npc.a_moan+] and thrusting forwards between them."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] in response, gently bucking your [pc.hips] against [npc.name] as you squeeze your thighs down around [npc.her] [npc.cock+].",
							" With a soft [pc.moan], you start gently bucking your [pc.hips] against [npc.herHim], squeezing your thighs down as you help to stimulate [npc.her] [npc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, eagerly bucking your [pc.hips] against [npc.name] as you enthusiastically squeeze your thighs down around [npc.her] [npc.cock+].",
							" With [pc.a_moan+], you start rapidly bucking your [pc.hips] against [npc.herHim], eagerly squeezing your thighs down as you help to stimulate [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, violently bucking your [pc.hips] against [npc.name] as you roughly squeeze your thighs down around [npc.her] [npc.cock+].",
							" With [pc.a_moan+], you start violently bucking your [pc.hips] against [npc.herHim], roughly squeezing your thighs down as you forcefully stimulate [npc.her] [npc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, eagerly bucking your [pc.hips] against [npc.name] as you enthusiastically squeeze your thighs down around [npc.her] [npc.cock+].",
							" With [pc.a_moan+], you start rapidly bucking your [pc.hips] against [npc.herHim], eagerly squeezing your thighs down as you help to stimulate [npc.her] [npc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, bucking your [pc.hips] against [npc.name] as you squeeze your thighs down around [npc.her] [npc.cock+].",
							" With [pc.a_moan+], you start bucking your [pc.hips] against [npc.herHim], squeezing your thighs down as you help to stimulate [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.name] starts using you, and, with tears running down your [pc.face], you beg for [npc.name] to stop as you weakly struggles against [npc.herHim].",
							" With [pc.a_sob+], you try, in vain, to pull away from [npc.name]'s [npc.cock+], tears running down your [pc.face] as you beg for [npc.herHim] to leave you alone."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			null,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [pc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.cock+] between your thighs, [npc.name] then starts bucking [npc.her] [npc.hips] forwards, softly bumping [npc.her] groin against you with every thrust.",
					"Slowly pushing [npc.her] [npc.cock+] between your [pc.legs+], [npc.name] gently thrusts [npc.her] [npc.hips] against you, letting out a little [npc.moan] as [npc.she] fucks your thighs.",
					"Sliding [npc.her] [npc.cock+] between your [pc.legs+], [npc.name] lets out a little [npc.moan] before starting to gently buck [npc.her] [npc.hips] against you, breathing in your [pc.scent] as [npc.she] slowly fucks your thighs."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] against [npc.herHim], letting out [pc.a_moan+] as you enthusiastically grip your thighs down around [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] against [npc.herHim], you beg for [npc.name] to carry on fucking your thighs.",
							" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.herHim],"
									+ " eagerly begging for [npc.name] to continue fucking your thighs as you squeeze your [pc.legs+] around [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.her] groin, you let out [pc.a_sob+], tears streaming down your face as you weakly beg for [npc.name] to leave you alone.",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your face as you plead for [npc.herHim] to stop.",
							" [pc.Sobbing] in distress, and with tears running down your cheeks, you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you grip your thighs down around [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, thrusting your [pc.hips] against [npc.herHim], you beg for [npc.name] to carry on fucking your thighs.",
							" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking your thighs as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			null,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Normal thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] between your thighs, [npc.name] then starts eagerly bucking [npc.her] [npc.hips] forwards, slamming [npc.her] groin against you with every thrust.",
					"Eagerly pushing [npc.her] [npc.cock+] between your [pc.legs+], [npc.name] starts thrusting [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] fucks your thighs.",
					"Eagerly thrusting [npc.her] [npc.cock+] between your [pc.legs+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically bucking [npc.her] [npc.hips] against you,"
							+ " breathing in your [pc.scent] as [npc.she] greedily fuck your thighs."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] against [npc.herHim], letting out [pc.a_moan+] as you enthusiastically grip your thighs down around [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] against [npc.herHim], you beg for [npc.name] to carry on fucking your thighs.",
							" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.herHim],"
									+ " eagerly begging for [npc.name] to continue fucking your thighs as you squeeze your [pc.legs+] around [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.her] groin, you let out [pc.a_sob+], tears streaming down your face as you weakly beg for [npc.name] to leave you alone.",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your face as you plead for [npc.herHim] to stop.",
							" [pc.Sobbing] in distress, and with tears running down your cheeks, you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you grip your thighs down around [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, thrusting your [pc.hips] against [npc.herHim], you beg for [npc.name] to carry on fucking your thighs.",
							" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking your thighs as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.cock+] between your thighs, [npc.name] then starts violently pumping [npc.her] [npc.hips] forwards, brutally grinding [npc.her] groin against you with every thrust.",
					"Violently thrusting [npc.her] [npc.cock+] between your [pc.legs+], [npc.name] starts roughly slamming [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] brutally fucks your thighs.",
					"Ruthlessly thrusting [npc.her] [npc.cock+] between your [pc.legs+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts violently slamming [npc.her] [npc.hips] against you,"
							+ " breathing in your [pc.scent] as [npc.she] roughly fucks your thighs."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] against [npc.herHim], letting out [pc.a_moan+] as you enthusiastically grip your thighs down around [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] against [npc.herHim], you beg for [npc.name] to carry on fucking your thighs.",
							" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.herHim],"
									+ " eagerly begging for [npc.name] to continue fucking your thighs as you squeeze your [pc.legs+] around [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.her] groin, you let out [pc.a_sob+], tears streaming down your face as you weakly beg for [npc.name] to leave you alone.",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your face as you plead for [npc.herHim] to stop.",
							" [pc.Sobbing] in distress, and with tears running down your cheeks, you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you grip your thighs down around [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, thrusting your [pc.hips] against [npc.herHim], you beg for [npc.name] to carry on fucking your thighs.",
							" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking your thighs as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_THIGH_SEX_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			null,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Normal thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.cock+] between your thighs, [npc.name] then starts bucking [npc.her] [npc.hips] forwards, slamming [npc.her] groin against you with every thrust.",
					"Pushing [npc.her] [npc.cock+] between your [pc.legs+], [npc.name] starts thrusting [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] fucks your thighs.",
					"Thrusting [npc.her] [npc.cock+] between your [pc.legs+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts bucking [npc.her] [npc.hips] against you, breathing in your [pc.scent] as [npc.she] fucks your thighs."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly thrust your [pc.hips] out in response, letting out a soft [pc.moan] as you gently implore [npc.herHim] to continue.",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your [pc.hips] out against [npc.her] groin, you beg for [npc.herHim] to continue.",
							" [pc.Moaning] in delight, you slowly grind yourself against [npc.name], softly [pc.moaning] for [npc.herHim] to continue as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently thrust your [pc.hips] out in response, letting out [pc.a_moan+] as you roughly demand that [npc.name] continues.",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, roughly thrusting your [pc.hips] out against [npc.her] groin, you order [npc.name] to continue.",
							" You roughly grind yourself against [npc.name], ordering [npc.herHim] to continue as you aggressively squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] out in response, letting out [pc.a_moan+] as you implore [npc.name] to continue.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, pushing your [pc.hips] out against [npc.her] groin, you beg for [npc.name] to continue.",
							" [pc.Moaning] in delight, you grind yourself against [npc.name], [pc.moaning] for [npc.herHim] to continue as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] between your thighs, [npc.name] then starts eagerly bucking [npc.her] [npc.hips] forwards, slamming [npc.her] groin against you with every thrust.",
					"Eagerly pushing [npc.her] [npc.cock+] between your [pc.legs+], [npc.name] starts thrusting [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] fucks your thighs.",
					"Eagerly thrusting [npc.her] [npc.cock+] between your [pc.legs+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically bucking [npc.her] [npc.hips] against you, breathing in your [pc.scent] as [npc.she] fucks your thighs."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly thrust your [pc.hips] out in response, letting out a soft [pc.moan] as you gently implore [npc.herHim] to continue.",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your [pc.hips] out against [npc.her] groin, you beg for [npc.herHim] to continue.",
							" [pc.Moaning] in delight, you slowly grind yourself against [npc.name], softly [pc.moaning] for [npc.herHim] to continue as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently thrust your [pc.hips] out in response, letting out [pc.a_moan+] as you roughly demand that [npc.name] continues.",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, roughly thrusting your [pc.hips] out against [npc.her] groin, you order [npc.name] to continue.",
							" You roughly grind yourself against [npc.name], ordering [npc.herHim] to continue as you aggressively squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] out in response, letting out [pc.a_moan+] as you implore [npc.name] to continue.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, pushing your [pc.hips] out against [npc.her] groin, you beg for [npc.name] to continue.",
							" [pc.Moaning] in delight, you grind yourself against [npc.name], [pc.moaning] for [npc.herHim] to continue as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_THIGH_SEX_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.her] [npc.cock] away from [npc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately trying, and failing, to pull [npc.her] [npc.cock] out from between your thighs, [npc.name] can't help but let out [npc.a_sob+] as [npc.she] weakly begs to be released.",
					"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, pleading for you to leave [npc.herHim] alone.",
					"[npc.Sobbing] in distress, [npc.name] weakly struggles against you, pleading for you to let go of [npc.her] [npc.cock]."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you slowly thrust your [pc.hips] out against [npc.herHim], letting out a soft [pc.moan] as you gently squeeze your [pc.legs+] down around [npc.her] [npc.cock+].",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, totally ignoring [npc.her] protests,"
									+ " you gently push your [pc.hips] out against [npc.name]'s groin, before squeezing your [pc.legs+] down around [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, slowly grinding yourself against [npc.herHim] and softly [pc.moaning] as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you roughly slam your [pc.hips] out against [npc.name], letting out [pc.a_moan+] as you continue violently squeezing your [pc.legs+] down around [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, totally ignoring [npc.her] protests,"
									+ " you forcefully thrust your [pc.hips] out against [npc.name]'s groin, before continuing to roughly squeeze your [pc.legs+] down around [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.her] protests,"
									+ " roughly grinding yourself against [npc.name] and [pc.moaning+] out loud as you violently squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you eagerly thrust your [pc.hips] out against [npc.name], letting out [pc.a_moan+] as you continue squeezing your [pc.legs+] down around [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, totally ignoring [npc.her] protests,"
									+ " you eagerly thrust your [pc.hips] out against [npc.name]'s groin, before continuing to energetically squeeze your [pc.legs+] down around [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, eagerly grinding yourself against [npc.herHim] and [pc.moaning+] out loud as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_THIGH_SEX_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getActionTitle() {
			return "Stop thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out from between [pc.name]'s legs and stop fucking [pc.her] thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out from between your legs, [npc.name] dominantly slides [npc.her] [npc.cockHead] up and down over your thighs one last time before pulling [npc.her] [npc.hips] back.",
							"Thrusting deep between your [pc.legs+] one last time, [npc.name] then yanks [npc.her] [npc.cock] back out from between your thighs."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out from between your legs, [npc.name] slides [npc.her] [npc.cockHead] up and down over your thighs one last time before pulling [npc.her] [npc.hips] back.",
							"Pushing deep between your [pc.legs+] one last time, [npc.name] then slides [npc.her] [npc.cock] back out from between your thighs."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You [pc.sobVerb+] as [npc.name] pull back, and find yourself unable to stop crying as you continue to weakly struggle against [npc.herHim].",
							" With [pc.a_sob+], you continue to struggle against [npc.herHim], tears streaming down your [pc.face] as [npc.name] stops using your thighs."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.name] pulls [npc.her] [npc.cock+] out from between your thighs.",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your lust for [npc.name]'s [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_THIGH_SEX_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS_PLAYER)==null) {
				return (Sex.isSubHasEqualControl() || Sex.isDom(Main.game.getPlayer()));
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
			return "Force [npc.name]'s [npc.cock+] between your thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you slowly slide [npc.her] [npc.cock+] between your [pc.legs+],"
									+ " letting out a little [pc.moan] before gently bucking your [pc.hips] against [npc.herHim] and forcing [npc.her] [npc.cock+] between your thighs.",
							"Lining [npc.name]'s [npc.cock+] up between your [pc.legs], you slowly push your [pc.hips] against [npc.herHim],"
									+ " softly [pc.moaning] as you gently force [npc.her] [npc.cock+] between your thighs."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you eagerly slide [npc.her] [npc.cock+] between your [pc.legs+],"
									+ " letting out [pc.a_moan+] before suddenly bucking your [pc.hips] against [npc.herHim] and forcing [npc.her] [npc.cock+] between your thighs.",
							"Lining [npc.name]'s [npc.cock+] up between your [pc.legs], you eagerly push your [pc.hips] against [npc.herHim],"
									+ " [pc.moaning+] as you greedily force [npc.her] [npc.cock+] between your thighs."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully grinding yourself against [npc.name], you roughly position [npc.her] [npc.cock+] between your [pc.legs+],"
									+ " letting out [pc.a_moan+] before violently slamming your [pc.hips] against [npc.herHim] and forcing [npc.her] [npc.cock+] between your thighs.",
							"Lining [npc.name]'s [npc.cock+] up between your [pc.legs], you violently slam your [pc.hips] against [npc.herHim],"
									+ " [pc.moaning+] as you roughly force [npc.her] [npc.cock+] between your thighs."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you eagerly slide [npc.her] [npc.cock+] between your [pc.legs+],"
									+ " letting out [pc.a_moan+] before suddenly bucking your [pc.hips] against [npc.herHim] and forcing [npc.her] [npc.cock+] between your thighs.",
							"Lining [npc.name]'s [npc.cock+] up between your [pc.legs], you eagerly push your [pc.hips] against [npc.herHim],"
									+ " [pc.moaning+] as you greedily force [npc.her] [npc.cock+] between your thighs."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing yourself against [npc.name], you slide [npc.her] [npc.cock+] between your [pc.legs+],"
									+ " letting out [pc.a_moan+] before suddenly bucking your [pc.hips] against [npc.herHim] and forcing [npc.her] [npc.cock+] between your thighs.",
							"Lining [npc.name]'s [npc.cock+] up between your [pc.legs], you push your [pc.hips] against [npc.herHim],"
									+ " [pc.moaning+] as you force [npc.her] [npc.cock+] between your thighs."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a soft [npc.moan] in response, gently bucking [npc.her] [npc.hips] into you as [npc.she] starts to fuck your thighs.",
							" With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] between your thighs as [npc.she] starts gently fucking them."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] in response, eagerly bucking [npc.her] [npc.hips] into you as [npc.she] starts to fuck your thighs.",
							" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] between your thighs as [npc.she] starts energetically fucking them."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] in response, and, seeking to remind you who's in charge, [npc.she] roughly slams [npc.her] [npc.hips] into you as [npc.she] starts to ruthlessly fuck your thighs.",
							" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] between your thighs as [npc.she] starts ruthlessly fucking them."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] in response, eagerly bucking [npc.her] [npc.hips] into you as [npc.she] starts to fuck your thighs.",
							" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] between your thighs as [npc.she] starts energetically fucking them."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] in response, bucking [npc.her] [npc.hips] into you as [npc.she] starts to fuck your thighs.",
							" With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] between your thighs as [npc.she] starts fucking them."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_sob+] as you force [npc.her] [npc.cock] between your thighs, and, struggling against you in vain, [npc.she] desperately begs you to stop.",
							" With [npc.a_sob+], [npc.name] struggles against you as you force [npc.her] [npc.cock] deep between your thighs."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_THIGH_SEX_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Gently force [npc.name]'s [npc.cock+] between your thighs.";
		}

		@Override
		public String getDescription() {
		
			return UtilText.returnStringAtRandom(
					"Gently pushing your [pc.hips] out against [npc.name]'s groin, you let out a soft [pc.moan] as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+].",
					"With a soft [pc.moan], you gently start bucking your [pc.hips] against [npc.name], squeezing your [pc.legs+] down around [npc.her] [npc.cock+] as [npc.she] fucks your thighs.",
					"Slowly thrusting your [pc.hips] against [npc.name], you softly [pc.moanVerb] as you squeeze your [pc.legs+] down around [npc.her] [npc.cock+].");
			
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			SexPace.DOM_NORMAL,
			null) {
		
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
			return "Force [npc.name]'s [npc.cock+] between your thighs.";
		}

		@Override
		public String getDescription() {

			return UtilText.returnStringAtRandom(
					"Eagerly thrusting your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you greedily squeeze your [pc.legs+] down around [npc.her] [npc.cock+].",
					"With [pc.a_moan+], you energetically start bucking your [pc.hips] against [npc.name], eagerly squeezing your [pc.legs+] down around [npc.her] [npc.cock+] as [npc.she] fucks your thighs.",
					"Enthusiastically thrusting your [pc.hips] against [npc.name], you [pc.moanVerb+] as you greedily squeeze your [pc.legs+] down around [npc.her] [npc.cock+].");
			
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [npc.name]'s [npc.cock+] between your thighs.";
		}

		@Override
		public String getDescription() {

			return UtilText.returnStringAtRandom(
					"Violently slamming your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you roughly squeeze your [pc.legs+] down around [npc.her] [npc.cock+].",
					"With [pc.a_moan+], you aggressively start bucking your [pc.hips] against [npc.name], roughly squeezing your [pc.legs+] down around [npc.her] [npc.cock+] as [npc.she] fucks your thighs.",
					"Roughly thrusting your [pc.hips] against [npc.name], you [pc.moanVerb+] as you forcefully squeezes your [pc.legs+] down around [npc.her] [npc.cock+].");
			
		}

	};
	
	public static final SexAction PLAYER_THIGH_SEX_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			SexPace.SUB_NORMAL,
			null) {
		
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
			return "Buck your hips against [npc.name] as [npc.her] [npc.cock] thrusts between your [pc.thighs].";
		}

		@Override
		public String getDescription() {
			
			return UtilText.returnStringAtRandom(
					"Thrusting your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep between your thighs.",
					"With [pc.a_moan+], you start bucking your [pc.hips] against [npc.name], forcing [npc.her] [npc.cock+] ever deeper between your thighs.",
					"Thrusting your [pc.hips] against [npc.name], you [pc.moanVerb+] as your movements force [npc.her] [npc.cock+] deep between your thighs.");
			
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eager thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [npc.name] as [npc.her] [npc.cock] thrusts between your [pc.thighs].";
		}

		@Override
		public String getDescription() {

			return UtilText.returnStringAtRandom(
					"Eagerly thrusting your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you greedily squeeze your [pc.legs+] down around [npc.her] [npc.cock+].",
					"With [pc.a_moan+], you energetically start bucking your [pc.hips] against [npc.name], eagerly squeezing your [pc.legs+] down around [npc.her] [npc.cock+] as [npc.she] fucks your thighs.",
					"Enthusiastically thrusting your [pc.hips] against [npc.name], you [pc.moanVerb+] as you greedily squeeze your [pc.legs+] down around [npc.her] [npc.cock+].");
					
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER,
			SexPace.SUB_RESISTING,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your thighs away from [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in your [pc.eyes], before you suddenly let out [pc.a_sob+], weakly trying to pull [npc.name]'s [npc.cock] out from between your [pc.legs] as [npc.she] continues gently fucking your thighs.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] away from [npc.name],"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues sliding in and out between your thighs.",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sobVerb] in distress as [npc.her] [npc.cock+] continues gently sliding in and out between your thighs."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in your [pc.eyes], before you suddenly lets out [pc.a_sob+], weakly trying to pull [npc.name]'s [npc.cock] out from between your [pc.legs] as [npc.she] continues eagerly fucking your thighs.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] away from [npc.name],"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues eagerly sliding in and out between your thighs.",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sobVerb] in distress as [npc.her] [npc.cock+] continues eagerly sliding in and out between your thighs."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in your [pc.eyes], before you suddenly lets out [pc.a_sob+], weakly trying to pull [npc.name]'s [npc.cock] out from between your [pc.legs] as [npc.she] continues roughly fucking your thighs.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] away from [npc.name],"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues roughly slamming in and out between your thighs.",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sobVerb] in distress as [npc.her] [npc.cock+] continues roughly slamming in and out between your thighs."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_THIGH_SEX_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.THIGHS_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isSubHasEqualControl() || Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Stop thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out from between your thighs.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.name]'s [npc.cock] out from between your [pc.legs], you growl at [npc.name] as you command [npc.herHim] to stop fucking your thighs.",
							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before you suddenly yank [npc.her] [npc.cock] out from between your thighs."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.name]'s [npc.cock] out from between your [pc.legs], you let out [pc.a_moan+] as you tell [npc.herHim] to stop fucking your thighs.",
							"You lean into [npc.name], causing [npc.herHim] to inhale your [pc.scent] before you slide [npc.her] [npc.cock] out from between your thighs."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that you haven't finished with [npc.herHim] yet.",
							" With [npc.a_sob+], [npc.name] continues to protest and struggle against you as you hold [npc.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you stop [npc.herHim] from fucking your thighs.",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking your thighs."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
