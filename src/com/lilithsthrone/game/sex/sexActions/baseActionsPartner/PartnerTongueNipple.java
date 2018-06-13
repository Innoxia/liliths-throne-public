package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

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
 * @since 0.1.88
 * @version 0.2.1
 * @author Innoxia
 */
public class PartnerTongueNipple {

	public static final SexAction PARTNER_SUCKLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Suckle";
		}

		@Override
		public String getActionDescription() {
			return "Wrap your [npc.lips] around [pc.name]'s [pc.nipple+] and start drinking [pc.her] [pc.milk].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING
					&& Main.game.getPlayer().getBreastRawStoredMilkValue()>0;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] slowly presses [npc.her] [npc.lips+] against one of your [pc.breasts+], before starting to gently suck and kiss your [pc.nipple+].",
							"Gently pressing [npc.her] [npc.lips+] up against your [pc.breast], [npc.name] starts slowly sucking and kissing your [pc.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly presses [npc.her] [npc.lips+] against one of your [pc.breasts+], before starting to greedily suck and kiss your [pc.nipple+].",
							"Eagerly pressing [npc.her] [npc.lips+] up against your [pc.breast], [npc.name] starts greedily sucking and kissing your [pc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] forcefully presses [npc.her] [npc.lips+] against one of your [pc.breasts+], before starting to roughly suck and nipple your [pc.nipple+].",
							"Roughly pressing [npc.her] [npc.lips+] up against your [pc.breast], [npc.name] starts forcefully sucking and nibbling your [pc.nipple+]."));
					break;
				default:
					break;
			}
			
			switch (Main.game.getPlayer().getBreastStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("You feel no [pc.milk+] coming from your [pc.breasts+],"); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
				case TWO_SMALL_AMOUNT:
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before your [pc.milk+] is trickling out into [npc.her] mouth,",
							" You feel your [pc.milk+] squirting out into [npc.her] mouth,"));
					break;
				case FOUR_LARGE_AMOUNT:
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before your [pc.milk+] is flowing out into [npc.her] mouth,",
							" You feel your [pc.milk+] streaming out into [npc.her] mouth,"));
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before your [pc.milk+] is pouring out into [npc.her] mouth,",
							" You feel your [pc.milk+] gushing out into [npc.her] mouth,"));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and you can't help but let out a soft sigh in response to the deeply satisfying feeling.",
							" and, gently pulling [npc.her] head into your [pc.breast+], you softly encourage [npc.herHim] to keep on suckling on your [pc.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and you can't help but let out [pc.a_moan+] in response to the deeply satisfying feeling.",
							" and, happily pulling [npc.her] head into your [pc.breast+], you readily encourage [npc.herHim] to keep on suckling on your [pc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and you can't help but let out [pc.a_moan+] in response to the deeply satisfying feeling.",
							" and, roughly forcing [npc.her] head into your [pc.breast+], you order [npc.herHim] to keep on suckling on your [pc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and you can't help but let out [pc.a_moan+] as you frantically try to push [npc.herHim] away from you.",
							" and, desperately trying to push [npc.her] head away from your [pc.breast+], you plead with [npc.herHim] to leave you alone."));
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getActivePartner().ingestFluid(Main.game.getPlayer(), Main.game.getPlayer().getMilkType(), SexAreaOrifice.MOUTH, Main.game.getPlayer().getBreastRawMilkStorageValue()/5, Main.game.getPlayer().getMilk().getFluidModifiers())
					+ Main.game.getPlayer().incrementBreastStoredMilk(-Main.game.getPlayer().getBreastRawMilkStorageValue()/5);
		}
	};
	
	public static final SexAction PLAYER_BREASTFEED = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.CATCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Breastfeed";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.name]'s face into your [pc.breasts] and get [npc.herHim] to start drinking your [pc.milk].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING
					&& Main.game.getPlayer().getBreastRawStoredMilkValue()>0;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc.name]'s head, you slowly guide [npc.her] [npc.lips+] up to one of your [pc.breasts+], before gently encouraging [npc.herHim] to suck and kiss your [pc.nipple+].",
							"Taking hold of [npc.name]'s head, you gently pull [npc.herHim] into your [pc.breasts+], before softly encouraging [npc.herHim] to suck and kiss your [pc.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc.name]'s head, you eagerly guide [npc.her] [npc.lips+] up to one of your [pc.breasts+], before desperately encouraging [npc.herHim] to suck and kiss your [pc.nipple+].",
							"Taking hold of [npc.name]'s head, you desperately pull [npc.herHim] into your [pc.breasts+], before eagerly encouraging [npc.herHim] to suck and kiss your [pc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc.name]'s head, you roughly force [npc.her] [npc.lips+] up against one of your [pc.breasts+], before dominantly ordering [npc.herHim] to suck and kiss your [pc.nipple+].",
							"Taking hold of [npc.name]'s head, you forcefully pull [npc.herHim] into your [pc.breasts+], before roughly ordering [npc.herHim] to suck and kiss your [pc.nipple+]."));
					break;
				default:
					break;
			}
			
			switch (Main.game.getPlayer().getBreastStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("You feel no [pc.milk+] coming from your [pc.breasts+],"); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
				case TWO_SMALL_AMOUNT:
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before your [pc.milk+] is trickling out into [npc.her] mouth,",
							" You feel your [pc.milk+] squirting out into [npc.her] mouth,"));
					break;
				case FOUR_LARGE_AMOUNT:
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before your [pc.milk+] is flowing out into [npc.her] mouth,",
							" You feel your [pc.milk+] streaming out into [npc.her] mouth,"));
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before your [pc.milk+] is pouring out into [npc.her] mouth,",
							" You feel your [pc.milk+] gushing out into [npc.her] mouth,"));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a soft, muffled sigh, [npc.she] buries [npc.her] face in your [pc.breasts+] and continues drinking your [pc.milk].",
							" and, gently pressing [npc.her] [npc.face] into your [pc.breast+], [npc.she] continues softly suckling on your [pc.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a muffled, [npc.moan+], [npc.she] buries [npc.her] face in your [pc.breasts+] and continues drinking your [pc.milk].",
							" and, happily pressing [npc.her] [npc.face] into your [pc.breast+], [npc.she] continues eagerly suckling on your [pc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a muffled, [npc.moan+], [npc.she] roughly buries [npc.her] face in your [pc.breasts+] and continues greedily drinking your [pc.milk].",
							" and, forcefully pressing [npc.her] [npc.face] into your [pc.breast+], [npc.she] continues roughly suckling on your [pc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc.she] can't help but let out [npc.a_moan+] as [npc.she] frantically tries to push you away from [npc.herHim].",
							" and, desperately trying to pull away from your [pc.breasts+], [npc.she] pleads with you to leave [npc.herHim] alone."));
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getActivePartner().ingestFluid(Main.game.getPlayer(), Main.game.getPlayer().getMilkType(), SexAreaOrifice.MOUTH, Main.game.getPlayer().getBreastRawMilkStorageValue()/5, Main.game.getPlayer().getMilk().getFluidModifiers())
					+ Main.game.getPlayer().incrementBreastStoredMilk(-Main.game.getPlayer().getBreastRawMilkStorageValue()/5);
		}
		
	};
}
