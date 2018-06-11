package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.88
 * @version 0.2.1
 * @author Innoxia
 */
public class PlayerTongueNipple {
	
	public static final SexAction PLAYER_SUCKLE = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.NIPPLE,
			SexParticipantType.PITCHER) {
		
		@Override
		public String getActionTitle() {
			return "Suckle";
		}

		@Override
		public String getActionDescription() {
			return "Wrap your [pc.lips] around [npc.name]'s [npc.nipple+] and start drinking [npc.her] [npc.milk].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING
					&& Sex.getActivePartner().getBreastRawStoredMilkValue()>0;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You slowly press your [pc.lips+] against one of [npc.name]'s [npc.breasts+], before starting to gently suck and kiss [npc.her] [npc.nipple+].",
							"Gently pressing your [pc.lips+] up against [npc.name]'s [npc.breast], you start slowly sucking and kissing [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly press your [pc.lips+] against one of [npc.name]'s [npc.breasts+], before starting to greedily suck and kiss [npc.her] [npc.nipple+].",
							"Eagerly pressing your [pc.lips+] up against [npc.name]'s [npc.breast], you start greedily sucking and kissing [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You forcefully press your [pc.lips+] against one of [npc.name]'s [npc.breasts+], before starting to roughly suck and nipple [npc.her] [npc.nipple+].",
							"Roughly pressing your [pc.lips+] up against [npc.name]'s [npc.breast], you start forcefully sucking and nibbling [npc.her] [npc.nipple+]."));
					break;
				default:
					break;
			}
			
			switch (Sex.getActivePartner().getBreastStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("You feel no [npc.milk+] coming from [npc.her] [npc.breasts+],"); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
				case TWO_SMALL_AMOUNT:
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.her] [npc.milk+] is trickling out into your mouth,",
							" You feel [npc.her] [npc.milk+] squirting out into your mouth,"));
					break;
				case FOUR_LARGE_AMOUNT:
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.her] [npc.milk+] is flowing out into your mouth,",
							" You feel [npc.her] [npc.milk+] streaming out into your mouth,"));
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.her] [npc.milk+] is pouring out into your mouth,",
							" You feel [npc.her] [npc.milk+] gushing out into your mouth,"));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc.she] can't help but let out a soft sigh as you bury your face in [npc.her] [npc.breasts+] and continue drinking [npc.her] [npc.milk].",
							" and, gently pulling your head into [npc.her] [npc.breast+], [npc.name] softly encourages you to keep on suckling on [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc.she] can't help but let out [npc.a_moan+] as you bury your face in [npc.her] [npc.breasts+] and continue drinking [npc.her] [npc.milk].",
							" and, happily pulling your head into [npc.her] [npc.breast+], [npc.name] readily encourages you to keep on suckling on [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc.she] can't help but let out [npc.a_moan+] as you bury your face in [npc.her] [npc.breasts+] and continue drinking [npc.her] [npc.milk].",
							" and, roughly forcing your head into [npc.her] [npc.breast+], [npc.name] orders you to keep on suckling on [npc.her] [npc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and [npc.she] can't help but let out [pc.a_moan+] as [npc.she] frantically tries to push you away from [npc.herHim].",
							" and, desperately trying to push your head away from [npc.her] [npc.breast+], [npc.name] pleads with you to leave [npc.herHim] alone."));
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.game.getPlayer().ingestFluid(Sex.getActivePartner(), Sex.getActivePartner().getMilkType(), SexAreaOrifice.MOUTH, Sex.getActivePartner().getBreastRawMilkStorageValue()/5, Sex.getActivePartner().getMilk().getFluidModifiers())
					+ Sex.getActivePartner().incrementBreastStoredMilk(-Sex.getActivePartner().getBreastRawMilkStorageValue()/5);
		}
	};
	
	public static final SexAction PARTNER_BREASTFEED = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.NIPPLE,
			SexParticipantType.CATCHER) {
		
		@Override
		public String getActionTitle() {
			return "Breastfeed";
		}

		@Override
		public String getActionDescription() {
			return "Pull [pc.name]'s face into your [npc.breasts] and get [pc.herHim] to start drinking your [npc.milk].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING
					&& Sex.getActivePartner().getBreastRawStoredMilkValue()>0;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of your head, [npc.name] slowly guides your [pc.lips+] up to one of [npc.her] [npc.breasts+], before gently encouraging you to suck and kiss [npc.her] [npc.nipple+].",
							"Taking hold of your head, [npc.name] gently pulls you into [npc.her] [npc.breasts+], before softly encouraging you to suck and kiss [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of your head, [npc.name] eagerly guides your [pc.lips+] up to one of [npc.her] [npc.breasts+], before desperately encouraging you to suck and kiss [npc.her] [npc.nipple+].",
							"Taking hold of your head, [npc.name] desperately pulls you into [npc.her] [npc.breasts+], before eagerly encouraging you to suck and kiss [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of your head, [npc.name] roughly forces your [pc.lips+] up against one of [npc.her] [npc.breasts+], before dominantly ordering you to suck and kiss [npc.her] [npc.nipple+].",
							"Taking hold of your head, [npc.name] forcefully pulls you into [npc.her] [npc.breasts+], before roughly ordering you to suck and kiss [npc.her] [npc.nipple+]."));
					break;
				default:
					break;
			}
			
			switch (Sex.getActivePartner().getBreastStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("You feel no [npc.milk+] coming from [npc.her] [npc.breasts+],"); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
				case TWO_SMALL_AMOUNT:
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.her] [npc.milk+] is trickling out into your mouth,",
							" You feel [npc.her] [npc.milk+] squirting out into your mouth,"));
					break;
				case FOUR_LARGE_AMOUNT:
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.her] [npc.milk+] is flowing out into your mouth,",
							" You feel [npc.her] [npc.milk+] streaming out into your mouth,"));
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" It only takes a moment before [npc.her] [npc.milk+] is pouring out into your mouth,",
							" You feel [npc.her] [npc.milk+] gushing out into your mouth,"));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a soft, muffled sigh, you bury your face in [npc.her] [npc.breasts+] and continue drinking [npc.her] [npc.milk].",
							" and, gently pressing your [pc.face] into [npc.her] [npc.breast+], you continue softly suckling on [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a muffled, [npc.moan+], you bury your face in [npc.her] [npc.breasts+] and continue drinking [npc.her] [npc.milk].",
							" and, happily pressing your [pc.face] into [npc.her] [npc.breast+], you continue eagerly suckling on [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and, with a muffled, [npc.moan+], you roughly bury your face in [npc.her] [npc.breasts+] and continue drinking [npc.her] [npc.milk].",
							" and, forcefully pressing your [pc.face] into [npc.her] [npc.breast+], you continue roughly suckling on [npc.her] [npc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" and you can't help but let out [pc.a_moan+] as you frantically try to push [npc.herHim] away from you.",
							" and, desperately trying to pull away from [npc.her] [npc.breasts+], you plead with [npc.name] to leave you alone."));
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.game.getPlayer().ingestFluid(Sex.getActivePartner(), Sex.getActivePartner().getMilkType(), SexAreaOrifice.MOUTH, Sex.getActivePartner().getBreastRawMilkStorageValue()/5, Sex.getActivePartner().getMilk().getFluidModifiers())
					+ Sex.getActivePartner().incrementBreastStoredMilk(-Sex.getActivePartner().getBreastRawMilkStorageValue()/5);
		}
		
	};
}
