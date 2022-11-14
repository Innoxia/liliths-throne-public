package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4
 * @author Innoxia
 */
public class ClitMouth {
	
	public static final SexAction FORCE_SUCK_CLIT = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public Map<SexAreaInterface, SexAreaInterface> getSexAreaInteractions() {
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
			} else {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH));
			}
		}
		@Override
		public SexActionType getActionType(){
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE).contains(Main.sex.getCharacterTargetedForSexAction(this))
					|| Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return SexActionType.ONGOING;
			} else {
				return SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED;
			}
		}
		@Override
		public String getActionTitle() {
			return "Clit attention";
		}
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to spend a few moments kissing and sucking on your clit.";
		}
		@Override
		public String getDescription() {
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself] in order to gently grind [npc.her] [npc.clit+] against [npc2.namePos] [npc2.lips+].",
							
							"With a quick shift of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] gently [npc.verb(press)] [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.tongue+].",
							
							"Gently pressing [npc.her] [npc.pussy+] down against [npc2.namePos] mouth,"
									+ " [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] grinding [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.lips+].");

				case SUB_EAGER:
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself] in order to eagerly grind [npc.her] [npc.clit+] against [npc2.namePos] [npc2.lips+].",
							
							"With a quick shift of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] greedily [npc.verb(press)] [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.tongue+].",
							
							"Eagerly pressing [npc.her] [npc.pussy+] down against [npc2.namePos] mouth,"
									+ " [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] grinding [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.lips+].");
					
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself] in order to roughly grind [npc.her] [npc.clit+] against [npc2.namePos] [npc2.lips+].",
							
							"With a quick shift of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(grind)] [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.tongue+].",
							
							"Aggressively pressing [npc.her] [npc.pussy+] down against [npc2.namePos] mouth,"
									+ " [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] roughly grinding [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.lips+].");
					
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself] in order to grind [npc.her] [npc.clit+] against [npc2.namePos] [npc2.lips+].",
							
							"With a quick shift of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(press)] [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.tongue+].",
							
							"Pressing [npc.her] [npc.pussy+] down against [npc2.namePos] mouth,"
									+ " [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] grinding [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.lips+].");
					
				default:
					break;
			}
			
			return "";
		}
	};
	
	public static final SexAction SUCK_CLIT = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public Map<SexAreaInterface, SexAreaInterface> getSexAreaInteractions() {
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
			} else {
				return Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT));
			}
		}
		@Override
		public SexActionType getActionType(){
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					|| Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return SexActionType.ONGOING;
			} else {
				return SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED;
			}
		}
		@Override
		public String getActionTitle() {
			return "Suck clit";
		}
		@Override
		public String getActionDescription() {
			return "Suck [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.clit+], before starting to gently suck and kiss it.",

							"With a long, slow lick, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+],"
									+ " pressing [npc.her] [npc.lips+] against it as [npc.she] gently [npc.verb(start)] kissing and sucking on [npc2.her] [npc2.clit+].",

							"Gently kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] slowly [npc.verb(make)] [npc.her] way to [npc2.her] [npc2.clit+],"
									+ " and with a series of soft licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.her] [npc2.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.clit+], before starting to greedily suck and kiss it.",

							"With a long, wet lick, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+],"
									+ " pressing [npc.her] [npc.lips+] against it as [npc.she] eagerly [npc.verb(start)] kissing and sucking on [npc2.her] [npc2.clit+].",

							"Eagerly kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] way to [npc2.her] [npc2.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.her] [npc2.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly dragging [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] forcefully [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.clit+], before starting to dominantly suck and kiss it.",

							"With a rough, wet lick, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+],"
									+ " pressing [npc.her] [npc.lips+] against it as [npc.she] [npc.verb(start)] forcefully kissing and sucking on [npc2.her] [npc2.clit+].",

							"Roughly kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] way to [npc2.her] [npc2.clit+],"
									+ " and with a series of greedy, wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.her] [npc2.clit+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.clit+], before starting to greedily suck and kiss it.",

							"With a long, wet lick, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+],"
									+ " pressing [npc.her] [npc.lips+] against it as [npc.she] eagerly [npc.verb(start)] kissing and sucking on [npc2.her] [npc2.clit+].",

							"Eagerly kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] way to [npc2.her] [npc2.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.her] [npc2.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.clit+], before starting to suck and kiss it.",

							"With a long, wet lick, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+],"
									+ " pressing [npc.her] [npc.lips+] against it as [npc.she] [npc.verb(start)] kissing and sucking on [npc2.her] [npc2.clit+].",

							"Kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] way to [npc2.her] [npc2.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.her] [npc2.clit+]."));
					break;
				default:
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], and, gently bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, gently pressing [npc2.her] [npc2.pussy] against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Gently bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] before pleading for [npc.herHim] to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, eagerly bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly pressing [npc2.her] [npc2.pussy] against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Eagerly bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, roughly grinding [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly grinding [npc2.her] [npc2.pussy] against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(command)] [npc.herHim] not to stop.",

							" Roughly grinding [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before ordering [npc.herHim] to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, eagerly bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly pressing [npc2.her] [npc2.pussy] against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Eagerly bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, pressing [npc2.her] [npc2.pussy] against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.pussy] away from [npc.namePos] [npc.face],"
									+ " letting out [npc2.a_sob+] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.herHim] alone.",

							" With tears streaming down [npc2.her] [npc2.face], [npc2.name] [npc2.verb(struggle)] against [npc.name],"
									+ " [npc2.sobbing] out loud as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.pussy] away from [npc.her] unwelcome [npc.tongue].",

							" [npc2.Sobbing] out loud, and with tears in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beg)] for [npc.name] to leave [npc2.herHim] alone,"
									+ " frantically trying to pull [npc2.her] [npc2.hips] back each time [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tongue+] running over [npc2.her] [npc2.clit+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Ongoing penetrative actions:
	

	public static final SexAction TWINTAIL_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Twintail pull";
			} else {
				return "Twin braids pull";
			}
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Grab [npc2.namePos] twintails and pull [npc2.her] forwards onto your [npc.clit+].";
			} else {
				return "Grab [npc2.namePos] twin braids and pull [npc2.her] forwards onto your [npc.clit+].";
			}
		}
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HAIR)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS || Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_BRAIDS)
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairLength().isSuitableForPulling()
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairType().isAbleToBeGrabbedInSex();
		}
		@Override
		public String getDescription() {
			
			String style = Main.sex.getCharacterTargetedForSexAction(this).getHairStyle().getName(Main.sex.getCharacterTargetedForSexAction(this));
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] "+style+", [npc.name] gently [npc.verb(pull)] [npc2.her] forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to take a gentle, but firm, grip on each of [npc2.namePos] "+style+", [npc.name] slowly [npc.verb(pull)] [npc2.herHim] into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] "+style+","
									+ " before gently pulling [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grasping [npc2.namePos] "+style+", [npc.name] violently [npc.verb(jerk)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to grab [npc2.namePos] "+style+", [npc.name] mercilessly [npc.verb(yank)] [npc2.her] head into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] "+style+","
									+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] "+style+", [npc.name] firmly [npc.verb(pull)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to take a firm grip on each of [npc2.namePos] "+style+", [npc.name] steadily [npc.verb(pull)] [npc2.herHim] into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] "+style+","
									+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
			}
			
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment gently caressing the underside of [npc.namePos] [npc.clit] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" Gently humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to softly caress the underside of [npc.namePos] [npc.clit],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.clit+] is eventually pulled free from [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Narrowing [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] up with [npc.namePos] daring move for just a moment,"
									+ " before jerking [npc2.her] head back and pointedly reminding [npc.herHim] who's in charge.",
							" The rumbling vibrations of [npc2.namePos] threatening growls, while serving to provide some extra pleasure,"
									+ " nonetheless intimidate [npc.name] into quickly letting go of [npc2.her] [npc2.hair(true)] and sliding [npc.her] [npc.clit+] free from [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Scrunching [npc2.her] [npc2.eyes] shut, [npc2.name] [npc2.verb(beat)] against #IF(npc.isPlayer())your#ELSE[npc.her] tormentor's#ENDIF thighs in a futile gesture of resistance,"
									+ " before finally being able to cry and gasp for air as [npc.name] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" The vibrations of [npc2.namePos] muffled cries and sobs only serve to give #IF(npc.isPlayer())you#ELSE[npc.her] tormentor#ENDIF some extra pleasure,"
									+ " but, after spending several seconds punching and pushing against [npc.namePos] thighs,"
									+ " [npc2.she] finally [npc2.verb(achieve)] a small victory as [npc2.her] [npc2.hair(true)] is released and [npc.namePos] [npc.clit+] is momentarily slid free from [npc2.her] throat."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment caressing the underside of [npc.namePos] [npc.clit] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" Happily humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to lovingly caress the underside of [npc.namePos] [npc.clit],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.clit+] is eventually pulled free from [npc2.her] throat."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction EAR_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Ear pull";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] [npc2.ears+] and pull [npc2.her] forwards onto your [npc.clit+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HEAD)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getEarType().isAbleToBeUsedAsHandlesInSex());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.ears+], [npc.name] gently [npc.verb(pull)] [npc2.her] forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to take a gentle, but firm, grip on each of [npc2.namePos] [npc2.ears+], [npc.name] slowly [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.ears+],"
									+ " before gently pulling [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grasping [npc2.namePos] [npc2.ears+], [npc.name] violently [npc.verb(jerk)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to grab [npc2.namePos] [npc2.ears+], [npc.name] mercilessly [npc.verb(yank)] [npc2.her] head into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] [npc2.ears+],"
									+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.ears+], [npc.name] firmly [npc.verb(pull)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to take a firm grip on each of [npc2.namePos] [npc2.ears+], [npc.name] steadily [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.ears+],"
									+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment gently caressing the underside of [npc.namePos] [npc.clit] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" Gently humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to softly caress the underside of [npc.namePos] [npc.clit],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.clit+] is eventually pulled free from [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Narrowing [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] up with [npc.namePos] daring move for just a moment,"
									+ " before jerking [npc2.her] head back and pointedly reminding [npc.herHim] who's in charge.",
							" The rumbling vibrations of [npc2.namePos] threatening growls, while serving to provide some extra pleasure,"
									+ " nonetheless intimidate [npc.name] into quickly letting go of [npc2.her] [npc2.hair(true)] and sliding [npc.her] [npc.clit+] free from [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Scrunching [npc2.her] [npc2.eyes] shut, [npc2.name] [npc2.verb(beat)] against #IF(npc.isPlayer())your#ELSE[npc.her] tormentor's#ENDIF thighs in a futile gesture of resistance,"
									+ " before finally being able to cry and gasp for air as [npc.name] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" The vibrations of [npc2.namePos] muffled cries and sobs only serve to give #IF(npc.isPlayer())you#ELSE[npc.her] tormentor#ENDIF some extra pleasure,"
									+ " but, after spending several seconds punching and pushing against [npc.namePos] thighs,"
									+ " [npc2.she] finally [npc2.verb(achieve)] a small victory as [npc2.her] [npc2.hair(true)] is released and [npc.namePos] [npc.clit+] is momentarily slid free from [npc2.her] throat."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment caressing the underside of [npc.namePos] [npc.clit] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" Happily humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to lovingly caress the underside of [npc.namePos] [npc.clit],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.clit+] is eventually pulled free from [npc2.her] throat."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GRAB_HORNS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Grab [npc2.horns]";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] [npc2.horns+] and pull [npc2.her] forwards onto your [npc.clit+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isHornsAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.horns+], [npc.name] gently [npc.verb(pull)] [npc2.her] forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to take a gentle, but firm, grip on each of [npc2.namePos] [npc2.horns+], [npc.name] slowly [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.horns+],"
									+ " before gently pulling [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grasping [npc2.namePos] [npc2.horns+], [npc.name] violently [npc.verb(jerk)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to grab [npc2.namePos] [npc2.horns+], [npc.name] mercilessly [npc.verb(yank)] [npc2.her] head into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] [npc2.horns+],"
									+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.horns+], [npc.name] firmly [npc.verb(pull)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to take a firm grip on each of [npc2.namePos] [npc2.horns+], [npc.name] steadily [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.horns+],"
									+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment gently caressing the underside of [npc.namePos] [npc.clit] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" Gently humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to softly caress the underside of [npc.namePos] [npc.clit],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.clit+] is eventually pulled free from [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Narrowing [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] up with [npc.namePos] daring move for just a moment,"
									+ " before jerking [npc2.her] head back and pointedly reminding [npc.herHim] who's in charge.",
							" The rumbling vibrations of [npc2.namePos] threatening growls, while serving to provide some extra pleasure,"
									+ " nonetheless intimidate [npc.name] into quickly letting go of [npc2.her] [npc2.hair(true)] and sliding [npc.her] [npc.clit+] free from [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Scrunching [npc2.her] [npc2.eyes] shut, [npc2.name] [npc2.verb(beat)] against #IF(npc.isPlayer())your#ELSE[npc.her] tormentor's#ENDIF thighs in a futile gesture of resistance,"
									+ " before finally being able to cry and gasp for air as [npc.name] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" The vibrations of [npc2.namePos] muffled cries and sobs only serve to give #IF(npc.isPlayer())you#ELSE[npc.her] tormentor#ENDIF some extra pleasure,"
									+ " but, after spending several seconds punching and pushing against [npc.namePos] thighs,"
									+ " [npc2.she] finally [npc2.verb(achieve)] a small victory as [npc2.her] [npc2.hair(true)] is released and [npc.namePos] [npc.clit+] is momentarily slid free from [npc2.her] throat."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment caressing the underside of [npc.namePos] [npc.clit] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" Happily humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to lovingly caress the underside of [npc.namePos] [npc.clit],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.clit+] is eventually pulled free from [npc2.her] throat."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction GRAB_ANTENNAE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Grab [npc2.antennae]";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] [npc2.antennae+] and pull [npc2.her] forwards onto your [npc.clit+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isAntennaeAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.antennae+], [npc.name] gently [npc.verb(pull)] [npc2.her] forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to take a gentle, but firm, grip on each of [npc2.namePos] [npc2.antennae+], [npc.name] slowly [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.antennae+],"
									+ " before gently pulling [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grasping [npc2.namePos] [npc2.antennae+], [npc.name] violently [npc.verb(jerk)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to grab [npc2.namePos] [npc2.antennae+], [npc.name] mercilessly [npc.verb(yank)] [npc2.her] head into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] [npc2.antennae+],"
									+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.antennae+], [npc.name] firmly [npc.verb(pull)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.clit+] all the way down to the base.",
							"Reaching down to take a firm grip on each of [npc2.namePos] [npc2.antennae+], [npc.name] steadily [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.clit+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.antennae+],"
									+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.clit+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment gently caressing the underside of [npc.namePos] [npc.clit] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" Gently humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to softly caress the underside of [npc.namePos] [npc.clit],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.clit+] is eventually pulled free from [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Narrowing [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] up with [npc.namePos] daring move for just a moment,"
									+ " before jerking [npc2.her] head back and pointedly reminding [npc.herHim] who's in charge.",
							" The rumbling vibrations of [npc2.namePos] threatening growls, while serving to provide some extra pleasure,"
									+ " nonetheless intimidate [npc.name] into quickly letting go of [npc2.her] [npc2.hair(true)] and sliding [npc.her] [npc.clit+] free from [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Scrunching [npc2.her] [npc2.eyes] shut, [npc2.name] [npc2.verb(beat)] against #IF(npc.isPlayer())your#ELSE[npc.her] tormentor's#ENDIF thighs in a futile gesture of resistance,"
									+ " before finally being able to cry and gasp for air as [npc.name] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" The vibrations of [npc2.namePos] muffled cries and sobs only serve to give #IF(npc.isPlayer())you#ELSE[npc.her] tormentor#ENDIF some extra pleasure,"
									+ " but, after spending several seconds punching and pushing against [npc.namePos] thighs,"
									+ " [npc2.she] finally [npc2.verb(achieve)] a small victory as [npc2.her] [npc2.hair(true)] is released and [npc.namePos] [npc.clit+] is momentarily slid free from [npc2.her] throat."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment caressing the underside of [npc.namePos] [npc.clit] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.clit+] from [npc2.her] throat.",
							" Happily humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to lovingly caress the underside of [npc.namePos] [npc.clit],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.clit+] is eventually pulled free from [npc2.her] throat."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	public static final SexAction THROAT_MUSCLE_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Throat control";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze your internally-muscled throat down around [npc2.namePos] [npc2.clit].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getFaceOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return UtilText.parse(performer, target,
					UtilText.returnStringAtRandom(
						"Letting out a muffled [npc.moan], [npc.name] [npc.verb(concentrate)] on squeezing the extra internal muscles within [npc.her] throat down around [npc2.namePos] [npc2.clit+].",
						"[npc.Name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(focus)] on controlling the extra muscles lining the insides of [npc.her] throat."
								+ " Gripping and squeezing them down around the [npc2.clit+] in [npc.her] mouth, [npc.name] [npc.verb(cause)] [npc2.name] to let out an involuntary cry of pleasure.",
						"[npc.Name] [npc.verb(find)] [npc.her] letting out a series of muffled [npc.moans] as [npc.she] [npc.verb(concentrate)] on squeezing the extra muscles within [npc.her] throat down around [npc2.namePos] [npc2.clit+].",
						"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on controlling the extra muscles deep within [npc.her] throat, gripping them down and massaging [npc2.namePos] [npc2.clit+]."));
		}
	};
	
	public static final SexAction CLIT_ORAL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Receive clit oral";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.clit+] into [npc2.namePos] mouth and get [npc2.herHim] to suck on it.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.clitTip+] of [npc.her] [npc.clit] up to [npc2.her] [npc2.lips+],"
										+ " before gently pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.clit+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.clitTip+] of [npc.her] [npc.clit] against [npc2.namePos] [npc2.lips+],"
										+ " before slowly bucking [npc.her] [npc.hips] into [npc2.her] [npc2.face] and gently sliding [npc.her] [npc.clit+] into [npc2.her] mouth."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.clitTip+] of [npc.her] [npc.clit] up to [npc2.her] [npc2.lips+],"
										+ " before eagerly pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.clit+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.clitTip+] of [npc.her] [npc.clit] against [npc2.namePos] [npc2.lips+],"
										+ " before eagerly bucking [npc.her] [npc.hips] into [npc2.her] [npc2.face] and sliding [npc.her] [npc.clit+] into [npc2.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.clitTip+] of [npc.her] [npc.clit] up to [npc2.her] [npc2.lips+],"
										+ " before roughly slamming [npc.her] [npc.hips] forwards and forcing [npc.her] [npc.clit+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.clitTip+] of [npc.her] [npc.clit] against [npc2.namePos] [npc2.lips+],"
										+ " before roughly slamming [npc.her] [npc.hips] into [npc2.her] [npc2.face] and forcing [npc.her] [npc.clit+] into [npc2.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.clitTip+] of [npc.her] [npc.clit] up to [npc2.her] [npc2.lips+],"
										+ " before pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.clit+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.clitTip+] of [npc.her] [npc.clit] against [npc2.namePos] [npc2.lips+],"
										+ " before bucking [npc.her] [npc.hips] into [npc2.her] [npc2.face] and sliding [npc.her] [npc.clit+] into [npc2.her] mouth."));
						break;
					default:
						break;
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] slowly [npc.verb(lower)] [npc.her] [npc.clit+] down to [npc2.namePos] mouth,"
										+ " before gently pushing the [npc.clitTip+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Gently lowering [npc.her] [npc.clit+] down to [npc2.namePos] mouth, [npc.name] slowly [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.clitTip+] past [npc2.her] [npc2.lips+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] quickly [npc.verb(drop)] [npc.her] [npc.clit+] down to [npc2.namePos] mouth,"
										+ " before eagerly pushing the [npc.clitTip+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Quickly dropping [npc.her] [npc.clit+] down to [npc2.namePos] mouth, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] eagerly forces the [npc.clitTip+] past [npc2.her] [npc2.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] roughly [npc.verb(grind)] [npc.her] [npc.clit+] down against [npc2.namePos] mouth,"
										+ " before forcefully pushing the [npc.clitTip+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Roughly grinding [npc.her] [npc.clit+] down against [npc2.namePos] mouth, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.clitTip+] past [npc2.her] [npc2.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] quickly [npc.verb(drop)] [npc.her] [npc.clit+] down to [npc2.namePos] mouth,"
										+ " before pushing the [npc.clitTip+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Quickly dropping [npc.her] [npc.clit+] down to [npc2.namePos] mouth, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.clitTip+] past [npc2.her] [npc2.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.clitTip+] of [npc.her] [npc.clit] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] gently [npc.verb(push)] [npc.her] [npc.hips] forwards and [npc.verb(slide)] [npc.her] [npc.clit+] into [npc2.her] mouth.",

								"Pushing the [npc.clitTip+] of [npc.her] [npc.clit] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] slowly [npc.verb(push)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], gently sliding [npc.her] [npc.clit+] into [npc2.her] mouth."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.clitTip+] of [npc.her] [npc.clit] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] eagerly [npc.verb(push)] [npc.her] [npc.hips] forwards and [npc.verb(slide)] [npc.her] [npc.clit+] into [npc2.namePos] mouth.",

								"Pushing the [npc.clitTip+] of [npc.her] [npc.clit] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] eagerly [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], sliding [npc.her] [npc.clit+] into [npc2.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.clitTip+] of [npc.her] [npc.clit] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] roughly slams [npc.her] [npc.hips] forwards and [npc.verb(force)] [npc.her] [npc.clit+] into [npc2.namePos] mouth.",

								"Grinding the [npc.clitTip+] of [npc.her] [npc.clit] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] roughly [npc.verb(slam)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], forcing [npc.her] [npc.clit+] into [npc2.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.clitTip+] of [npc.her] [npc.clit] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] [npc.verb(push)] [npc.her] [npc.hips] forwards and [npc.verb(slide)] [npc.her] [npc.clit+] into [npc2.her] mouth.",

								"Pushing the [npc.clitTip+] of [npc.her] [npc.clit] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], sliding [npc.her] [npc.clit+] into [npc2.her] mouth."));
						break;
					default:
						break;
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.moan], slowly sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(start)] gently sucking [npc.namePos] [npc.clit+].",

							" With a soft, muffled [npc2.moan], [npc2.name] [npc2.verb(start)] gently sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] oral."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], eagerly sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(start)] happily sucking [npc.namePos] [npc.clit+].",

							" With an eager, and very muffled [npc2.moan], [npc2.name] [npc2.verb(start)] happily sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] oral."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], roughly slamming [npc2.namePos] head forwards as [npc2.she] [npc2.verb(start)] forcing [npc.namePos] [npc.clit+] deep down [npc2.her] throat.",

							" With an eager, and very muffled [npc2.moan], [npc2.name] forcefully [npc2.verb(push)] [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] oral."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(start)] sucking [npc.namePos] [npc.clit+].",

							" With a muffled [npc2.moan], [npc2.name] [npc2.verb(start)] sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] oral."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.sob], gargling and choking on [npc.namePos] [npc.clit+] as [npc2.she] frantically [npc2.verb(try)] to pull [npc2.her] head away from [npc.her] groin.",

							" With a muffled [npc2.sob], [npc2.name] frantically [npc2.verb(try)] to pull away from [npc.namePos] [npc.clit+],"
									+ " gargling and choking as [npc2.she] [npc2.verb(squirm)] and [npc2.verb(struggle)] against [npc.herHim]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] eagerly [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] enthusiastically [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, eagerly wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+], [npc2.she] enthusiastically [npc2.verb(continue)] to give [npc.herHim] [npc2.her] oral attention.",
	
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+],"
								+ " eagerly licking and sucking [npc.her] [npc.clit] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Desperately trying, and failing, to pull back from [npc.namePos] [npc.clit],"
								+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(make)] muffled noises of protest.",
	
						" A muffled [npc2.sob] escapes from [npc2.namePos] mouth as [npc2.she] weakly [npc2.verb(try)] to pull back,"
								+ " tears streaming down [npc2.her] [npc2.face] as [npc.namePos] [npc.clit+] continues sliding back and forth past [npc2.her] [npc2.lips+].",
	
						" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
								+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name] as [npc2.she] [npc2.verb(make)] muffled noises of protest."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+], [npc2.she] [npc2.verb(continue)] to give [npc.herHim] [npc2.her] oral attention.",
	
						" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+],"
								+ " licking and sucking [npc.her] [npc.clit] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] gently [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] slowly [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, gently wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+], [npc2.she] lovingly [npc2.verb(continue)] to give [npc.herHim] [npc2.her] oral attention.",
	
						" [npc2.Moaning] in delight, [npc2.name] softly [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+],"
								+ " gently licking and sucking [npc.her] [npc.clit] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] forcefully [npc2.verb(grip)] [npc2.her] [npc2.lips+] down around [npc.namePos] [npc.clit+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] roughly [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, forcefully wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.clit+], [npc2.she] roughly [npc2.verb(continue)] to give [npc.herHim] [npc2.her] oral attention.",
	
						" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(grip)] [npc2.her] [npc2.lips+] down around [npc.namePos] [npc.clit+],"
								+ " forcefully licking and sucking [npc.her] [npc.clit] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
		}
		return "";
	}
	
	public static final SexAction CLIT_ORAL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle clit oral";
		}

		@Override
		public String getActionDescription() {
			return "Hold still and let [npc2.name] suck your [npc.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] gently [npc.verb(slide)] [npc.her] [npc.clit+] past [npc2.namePos] [npc2.lips+],"
								+ " letting out a soft [npc.moan] as [npc.she] steadily [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
						"[npc.Name] slowly [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out a soft [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.her] throat.",
						"Gently bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
								+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(continue)] receiving [npc2.her] oral attention."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Receive clit oral";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.clit+] into [npc2.namePos] face to encourage [npc2.herHim] to continue sucking on it.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] eagerly [npc.verb(thrust)] [npc.her] [npc.clit+] past [npc2.namePos] [npc2.lips+],"
								+ " letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
						"[npc.Name] desperately [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.her] throat.",
						"Enthusiastically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
								+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] happily receiving [npc2.her] oral attention."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Face fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your [npc.clit+] down [npc2.namePos] throat and give [npc2.herHim] a good face-fucking.";
		}

		@Override
		public String getDescription() {
			List<String> descriptions = new ArrayList<>();

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.STANDING)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SITTING)) {

				for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getClitorisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("With a sudden, violent thrust forwards, [npc.name] [npc.verb(bury)] [npc.her] [npc.clit+] deep down [npc2.namePos] throat."
												+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] [npc2.face],"
												+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as the barbs lining [npc.namePos] shaft repeatedly rake up the sides of [npc2.her] throat.");
							break;
						case FLARED:
							descriptions.add("With a sudden, violent thrust forwards, [npc.name] [npc.verb(bury)] [npc.her] [npc.clit+] deep down [npc2.namePos] throat."
												+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.her] [npc2.face],"
												+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as [npc.namePos] wide, flared head forces its way up and down [npc2.her] throat.");
							break;
						case KNOTTED:
							descriptions.add("With a sudden, violent thrust forwards, [npc.name] [npc.verb(bury)] [npc.her] [npc.clit+] deep down [npc2.namePos] throat."
												+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.her] [npc2.face],"
												+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as [npc.namePos] [npc.verb(slam)] [npc.her] knot repeatedly against [npc2.her] [npc2.lips+].");
							break;
						default:
							break;
					}
				}
				
				descriptions.add("[npc.Name] [npc.verb(grab)] the sides of [npc2.namePos] head, and before [npc2.she] [npc2.verb(know)] what's happening,"
									+ " [npc.sheIs] roughly slamming [npc.her] [npc.clit+] in and out of [npc2.her] facial fuck-hole.");
				descriptions.add("Letting out [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.clit+] all the way down [npc2.namePos] throat."
									+ " As [npc2.she] [npc2.verb(blink)] back tears, [npc.name] [npc.verb(start)] rapidly bucking [npc.her] [npc.hips] back and forth,"
									+ " holding [npc2.namePos] head in place with both hands as [npc.she] ruthlessly [npc.verb(fuck)] [npc2.her] [npc2.face].");
				descriptions.add("Grabbing the sides of [npc2.namePos] head, [npc.name] roughly [npc2.verb(pull)] [npc2.her] face into [npc.her] groin,"
									+ " sinking [npc.her] [npc.clit+] deep down [npc2.her] throat before starting to ruthlessly fuck [npc2.her] [npc2.face].");
				descriptions.add("With a forceful thrust, [npc.name] [npc.verb(hilt)] [npc.her] [npc.clit+] deep down [npc2.namePos] throat."
									+ " As a slimy stream of saliva "+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, LubricationType.PRECUM)?"and precum ":"")
									+"drools from the corners of [npc2.her] mouth, [npc.name] bucks back, letting [npc2.name] gasp for air for a brief moment before starting to aggressively fuck [npc2.her] [npc2.face].");
				
				return Util.randomItemFrom(descriptions);
				
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				
				for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getClitorisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc.verb(thrust)] downwards, burying [npc.her] [npc.clit+] deep down [npc2.namePos] throat."
												+ " Grinding the base against [npc2.her] [npc2.lips+] for moment, [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] face,"
												+ " causing [npc2.name] to let out a muffled [npc2.moan] as [npc2.she] [npc2.verb(struggle)] to breathe,"
												+ " and, squirming about beneath [npc.name], [npc2.she] [npc2.verb(feel)] [npc2.her] throat being raked by the series of barbs that line the sides of [npc.her] [npc.clit+].");
							break;
						case BLUNT:
							break;
						case FLARED:
							descriptions.add("Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc.verb(thrust)] downwards, burying [npc.her] [npc.clit+] deep down [npc2.namePos] throat."
												+ " Grinding the base against [npc2.her] [npc2.lips+] for moment, [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] face,"
												+ " causing [npc2.name] to let out a muffled [npc2.moan] as [npc2.she] [npc2.verb(struggle)] to breathe,"
												+ " and, squirming about beneath [npc.name], [npc2.she] [npc2.verb(feel)] [npc2.her] throat being stretched out by the wide, flared head of [npc.her] [npc.clit+].");
							break;
						case KNOTTED:
							descriptions.add("Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc.verb(thrust)] downwards, burying [npc.her] [npc.clit+] deep down [npc2.namePos] throat."
												+ " Grinding the base against [npc2.her] [npc2.lips+] for moment, [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] face,"
												+ " causing [npc2.name] to let out a muffled [npc2.moan] as [npc2.she] [npc2.verb(struggle)] to breathe,"
												+ " and, squirming about beneath [npc.name], [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.lips+] being repeatedly battered by the fat knot at the base of [npc.her] [npc.clit+].");
							break;
						case PREHENSILE:
						case RIBBED:
						case SHEATHED:
						case TAPERED:
						case TENTACLED:
						case VEINY:
						case OVIPOSITOR:
							break;
					}
				}
				
				if(Main.sex.getCharacterPerformingAction().hasLegs()) {
					descriptions.add("[npc.Name] [npc.verb(spread)] [npc.her] knees out on either side of [npc2.namePos] head, and before [npc2.she] [npc2.verb(know)] what's happening,"
							+ " [npc.nameIs] roughly slamming [npc.her] [npc.clit+] in and out of [npc2.her] facial fuck-hole.");
					
				} else {
					descriptions.add("[npc.Name] [npc.verb(push)] [npc.her] groin down onto [npc2.namePos] face, and before [npc2.she] [npc2.verb(know)] what's happening,"
							+ " [npc.nameIs] roughly slamming [npc.her] [npc.clit+] in and out of [npc2.her] facial fuck-hole.");
				}
				

				descriptions.add("Letting out [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.clit+] all the way down [npc2.namePos] throat."
									+ " As [npc2.she] [npc2.verb(blink)] back tears, [npc.name] [npc.verb(start)] rapidly slamming [npc.her] [npc.hips] up and down,"
									+ " letting out more [npc.moans+] as [npc.she] ruthlessly [npc.verb(fuck)] [npc2.her] [npc2.face].");

				descriptions.add("Dropping down onto [npc2.namePos] face, [npc.name] roughly slams [npc.her] [npc.clit+] deep down [npc2.her] throat,"
									+ " letting out [npc.a_moan+] before starting to violently slam [npc.her] [npc.hips] up and down as [npc.she] ruthlessly [npc.verb(fuck)] [npc2.her] face.");

				descriptions.add("With a forceful thrust, [npc.name] [npc.verb(hilt)] [npc.her] [npc.clit+] deep down [npc2.namePos] throat."
									+ " As a slimy stream of "+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, LubricationType.PRECUM)?"cummy ":"")
									+"saliva drools from the corners of [npc2.her] mouth, [npc.name] lifts [npc.herself] up,"
										+ " letting [npc2.name] gasp for air for a brief moment before sinking down once more and starting to aggressively fuck [npc2.her] face.");

				return Util.randomItemFrom(descriptions);
				
			} else {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly [npc.verb(slam)] [npc.her] [npc.clit+] past [npc2.namePos] [npc2.lips+],"
									+ " letting out [npc.a_moan+] as [npc.she] violently [npc.verb(thrust)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
							"[npc.Name] violently [npc.verb(slam)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] roughly [npc.verb(fuck)] [npc2.her] throat.",
							"Aggressively bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] roughly fucking [npc2.her] face."));
				
				UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
		
	};
	
	public static final SexAction CLIT_ORAL_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Resist clit oral";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull away from [npc2.namePos] [npc2.clit].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] gently, but firmly,"
								+ " [npc2.verb(hold)] [npc.herHim] in place, slowly sliding [npc2.her] [npc2.lips+] up and down the length of [npc.her] [npc.clit+] as [npc2.she]"
								+ " [npc2.verb(continue)] giving [npc.herHim] [npc2.her] unwanted oral attention.",

						"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.clit+] out of [npc2.namePos] mouth."
								+ " [npc2.Her] grip proves to be too strong, however, and [npc2.name] gently, but firmly, [npc.verb(continue)] to suck [npc.her] [npc.clit+] as [npc.she] weakly struggles against [npc2.herHim].",

						"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.clit+] out of [npc2.namePos] mouth, but [npc2.her] grip is too strong for [npc.herHim]"
								+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] [npc2.her] unwanted oral attention."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
								+ " growling in a threatening manner as [npc2.she] forcefully [npc2.verb(slide)] [npc2.her] [npc2.lips+] up and down the length of [npc.her] [npc.clit+].",

						"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.clit+] out of [npc2.namePos] mouth."
								+ " [npc2.Her] grip proves to be far too strong, however, and [npc2.name] [npc2.verb(let)] out a threatening growl as [npc2.name] [npc2.verb(continue)] to suck [npc.her] [npc.clit+].",

						"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.clit+] out of [npc2.namePos] mouth, but [npc2.her] grip is too strong for [npc.herHim]"
								+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] [npc2.her] unwanted oral attention."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
								+ " eagerly sliding [npc2.her] [npc2.lips+] up and down the length of [npc.her] [npc.clit+] as [npc2.she] [npc2.verb(continue)] giving [npc.herHim] [npc2.her] unwanted oral attention.",

						"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.clit+] out of [npc2.namePos] mouth."
								+ " [npc2.Her] grip proves to be too strong, however, and [npc2.she] eagerly [npc.verb(continue)] to suck [npc.her] [npc.clit+] as [npc.she] weakly struggles against [npc2.herHim].",

						"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.clit+] out of [npc2.namePos] mouth, but [npc2.her] grip is too strong for [npc.herHim]"
								+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] [npc2.her] unwanted oral attention."));
					break;
			}
			
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Receive clit oral";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.clit+] into [npc2.namePos] face to encourage [npc2.herHim] to continue sucking on it.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(thrust)] [npc.her] [npc.clit+] past [npc2.namePos] [npc2.lips+],"
								+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
						"[npc.Name] [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] [npc.verb(fuck)] [npc2.her] throat.",
						"Bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
								+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] happily receiving [npc2.her] oral attention."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Eager clit oral";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [npc.hips] into [npc2.namePos] face, forcing your [npc.clit+] down [npc2.her] throat.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] eagerly [npc.verb(thrust)] [npc.her] [npc.clit+] past [npc2.namePos] [npc2.lips+],"
								+ " letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
						"[npc.Name] desperately [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.her] throat.",
						"Enthusiastically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
								+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] happily receiving [npc2.her] oral attention."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_ORAL_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop clit oral";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.clit+] out of [npc2.namePos] mouth and stop receiving [npc2.her] oral attention.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				UtilText.nodeContentSB.append("Using [npc.her] knees to lift [npc.herself] up, [npc.name] [npc.verb(allow)] [npc.her] [npc.clit+] to slide up and out of [npc2.namePos] mouth."
							+ " A slimy strand of saliva links [npc2.namePos] [npc2.lips+] to the [npc.clitTip+] of [npc.her] [npc.clit] for a brief moment, before breaking to fall down over [npc2.her] face.");
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc.her] [npc.clit+] deep down [npc2.namePos] throat one last time, [npc.name] then [npc2.verb(pull)] [npc.her] [npc.hips] back,"
										+ " grinning as [npc2.name] [npc2.verb(splutter)] and [npc2.verb(gasp)] for air.",

								"Slamming [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.name] forces [npc.her] [npc.clit+] deep down [npc2.her] throat, before pulling completely back and out of [npc2.her] mouth."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc.her] [npc.clit+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] puts an end to the oral experience.",

								"With [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] [npc.hips] back, sliding [npc.her] [npc.clit+] fully out of [npc2.namePos] mouth."));
						break;
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+], struggling in desperation as [npc2.she] [npc2.verb(plead)] for [npc.name] to let [npc2.herHim] go.",

							" [npc2.Name] [npc2.verb(feel)] tears streaming down [npc2.her] cheeks as [npc2.she] [npc2.verb(try)] to struggle out of [npc.namePos] grip,"
									+ " letting out [npc2.a_sob+] before begging for [npc.herHim] to let [npc2.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], revealing [npc2.her] desire to continue sucking [npc.namePos] [npc.clit+].",

							" [npc2.Name] [npc2.moanVerb] as [npc.name] withdraws from [npc2.her] mouth, betraying [npc2.her] desperate desire to continue sucking [npc.her] [npc.clit+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction GIVING_CLIT_ORAL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Suck clit";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc2.namePos] [npc2.clit+] into your mouth and start sucking on it.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] slowly [npc.verb(lower)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " passionately kissing the [npc2.clitTip+] of [npc2.her] [npc2.clit+] before taking [npc2.herHim] into [npc.her] mouth.",

								"Gently lowering [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a long, slow lick up the length of [npc2.her] [npc2.clit+], before taking the [npc2.clitTip+] into [npc.her] mouth."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] eagerly [npc.verb(drop)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " passionately kissing the [npc2.clitTip+] of [npc2.her] [npc2.clit+] before greedily taking [npc2.herHim] into [npc.her] mouth.",

								"Eagerly dropping [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.clit+], before greedily taking the [npc2.clitTip+] into [npc.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] quickly [npc.verb(drop)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " roughly kissing the [npc2.clitTip+] of [npc2.her] [npc2.clit+] before forcefully taking [npc2.herHim] into [npc.her] mouth.",

								"Dropping [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a rough, wet lick up the length of [npc2.her] [npc2.clit+], before forcefully taking the [npc2.clitTip+] into [npc.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] [npc.verb(drop)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " kissing the [npc2.clitTip+] of [npc2.her] [npc2.clit+] before taking [npc2.herHim] into [npc.her] mouth.",

								"Dropping [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.clit+], before taking the [npc2.clitTip+] into [npc.her] mouth."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] to the [npc2.clitTip+] of [npc2.namePos] [npc2.clit],"
										+ " [npc.name] slowly [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] [npc.her] oral attention.",

								"Wrapping [npc.her] [npc.lips+] around the [npc2.clitTip+] of [npc2.namePos] [npc2.clit],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] [npc.her] oral attention."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [npc2.clitTip+] of [npc2.namePos] [npc2.clit],"
										+ " [npc.name] eagerly [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] happily [npc.verb(start)] giving [npc2.herHim] [npc.her] oral attention.",

								"Wrapping [npc.her] [npc.lips+] around the [npc2.clitTip+] of [npc2.namePos] [npc2.clit],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] eagerly [npc.verb(start)] giving [npc2.herHim] [npc.her] oral attention."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [npc2.clitTip+] of [npc2.namePos] [npc2.clit],"
										+ " [npc.name] forcefully [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] [npc.her] oral attention.",

								"Forcefully wrapping [npc.her] [npc.lips+] around the [npc2.clitTip+] of [npc2.namePos] [npc2.clit],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] [npc.her] oral attention."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [npc2.clitTip+] of [npc2.namePos] [npc2.clit],"
										+ " [npc.name] [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] [npc.her] oral attention.",

								"Wrapping [npc.her] [npc.lips+] around the [npc2.clitTip+] of [npc2.namePos] [npc2.clit],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] eagerly [npc.verb(start)] giving [npc2.herHim] [npc.her] oral attention."));
						break;
					default:
						break;
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], softly [npc2.moaning] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.clit+].",

							" With a gentle buck of [npc2.her] [npc2.hips],"
									+ " [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.clit+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] greedily [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.moaning+] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.clit+].",

							" With an energetic buck of [npc2.her] [npc2.hips],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.moaning+] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.clit+].",

							" With a rough [npc.verb(thrust)] of [npc2.her] [npc2.hips],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.moaning+] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.clit+].",

							" Bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.clit+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+], trying, and failing, to pull [npc2.her] [npc2.clit] out of [npc.namePos] mouth as [npc2.she] [npc2.verb(struggle)] against the unwanted oral attention.",

							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(struggle)] against the unwanted oral attention, [npc2.sobbing] and squirming as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to stop."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
	
	public static final SexAction GIVING_CLIT_ORAL_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT);
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Deep throat";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc2.namePos] [npc2.clit+] as deep as possible down your throat.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently wrapping [npc.her] [npc.lips+] around the [npc2.clit+] in [npc.her] mouth, [npc.name] [npc.verb(push)] [npc.her] head forwards,"
									+ " taking [npc2.namePos] [npc2.clit+] as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a soft, muffled [npc.moan], [npc.name] gently [npc.verb(lean)] forwards,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] as much of [npc2.namePos] [npc2.clit+] down [npc.her] throat as [npc.she] can.",

							"Slowly sliding [npc.her] head forwards, [npc.name] gently [npc.verb(part)] [npc.her] [npc.lips+], before taking [npc2.namePos] [npc2.clit+] deep down [npc.her] throat."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly wrapping [npc.her] [npc.lips+] around the [npc2.clit+] in [npc.her] mouth, [npc.name] quickly [npc.verb(push)] [npc.her] head forwards,"
									+ " greedily taking [npc2.namePos] [npc2.clit+] as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a muffled, [npc.moan+], [npc.name] eagerly [npc.verb(lean)] forwards,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] desperately [npc.verb(take)] as much of [npc2.namePos] [npc2.clit+] down [npc.her] throat as [npc.she] can.",

							"Greedily sliding [npc.her] head forwards, [npc.name] readily [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] [npc2.namePos] [npc2.clit+] deep down [npc.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully wrapping [npc.her] [npc.lips+] around the [npc2.clit+] in [npc.her] mouth, [npc.name] roughly [npc.verb(slam)] [npc.her] head forwards,"
									+ " forcing [npc2.namePos] [npc2.clit+] as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(lean)] forwards,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] roughly [npc.verb(force)] as much of [npc2.namePos] [npc2.clit+] down [npc.her] throat as [npc.she] can.",

							"Aggressively pushing [npc.her] head forwards, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.clit+] deep down [npc.her] throat."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Wrapping [npc.her] [npc.lips+] around the [npc2.clit+] in [npc.her] mouth, [npc.name] quickly [npc.verb(push)] [npc.her] head forwards,"
									+ " taking [npc2.namePos] [npc2.clit+] as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a muffled, [npc.moan+], [npc.name] [npc.verb(lean)] forwards,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] as much of [npc2.namePos] [npc2.clit+] down [npc.her] throat as [npc.she] can.",

							"Sliding [npc.her] head forwards, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] [npc2.namePos] [npc2.clit+] deep down [npc.her] throat."));
					break;
				default:
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			default:
				return UtilText.returnStringAtRandom(
						" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.clit+] deep down [npc.namePos] throat,"
								+ " letting out a muffled [npc2.moan] as [npc2.she] enthusiastically [npc2.verb(receive)] [npc.her] oral attention.",
						" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.clit+] deep down [npc.namePos] throat.",
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.clit+] as deep as possible into [npc.namePos] throat.");
			case SUB_RESISTING:
				return UtilText.returnStringAtRandom(
						" Failing to pull [npc2.her] [npc2.clit] away from [npc.namePos] mouth,"
								+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
						" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
								+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.clit+] deep into [npc.her] throat.",
						" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.clit] away from [npc.namePos] mouth.");
			case DOM_GENTLE:
				return UtilText.returnStringAtRandom(
						" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.clit+] deep into [npc.namePos] throat,"
								+ " letting out a soft, muffled [npc2.moan] as [npc2.she] [npc2.verb(receive)] [npc.her] oral attention.",
						" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.clit+] deep into [npc.namePos] throat.",
						" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.clit+] deep into [npc.namePos] throat.");
			case DOM_ROUGH:
				return UtilText.returnStringAtRandom(
						" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.clit+] deep into [npc.namePos] throat,"
								+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(receive)] [npc.her] oral attention.",
						" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.clit+] deep into [npc.namePos] throat.",
						" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.clit+] as deep as possible into [npc.namePos] throat.");
			case SUB_NORMAL:
				return UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.clit+] deep into [npc.namePos] throat,"
								+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(receive)] [npc.her] oral attention.",
						" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.clit+] deep into [npc.namePos] throat.",
						" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.clit+] deep into [npc.namePos] throat.");
		}
	}
	
	public static final SexAction GIVING_CLIT_ORAL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently suck clit";
		}

		@Override
		public String getActionDescription() {
			return "Gently suck [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(start)] bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] loving oral attention.",
					"With a soft, muffled [npc.moan], [npc.name] gently [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] loving oral attention.",
					"Slowly bobbing [npc.her] head up and down, [npc.name] gently [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] oral attention."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Suck clit";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly suck [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] enthusiastic oral attention.",
					"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " greedily wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] oral attention.",
					"Rapidly bobbing [npc.her] head up and down, [npc.name] desperately [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] eager oral attention."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly suck clit";
		}

		@Override
		public String getActionDescription() {
			return "Roughly suck [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Forcefully gripping [npc.her] [npc.lips+] down around [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(start)] aggressively bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] oral attention.",
					"With a muffled, [npc.moan+], [npc.name] violently [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " roughly wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] oral attention.",
					"Roughly bobbing [npc.her] head up and down, [npc.name] dominantly [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] oral attention."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist sucking clit";
		}

		@Override
		public String getActionDescription() {
			return "Try and push [npc2.namePos] [npc2.clit+] out of your mouth.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] holds [npc.herHim] in place,"
									+ " slowly sliding [npc2.her] [npc2.clit+] back and forth past [npc.her] [npc.lips+].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back,"
									+ " [npc.her] protestations coming out in gargled bursts as [npc2.name] [npc2.verb(continue)] gently pushing [npc2.her] [npc2.clit+] down [npc.her] throat.",
							"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
									+ " [npc.sobbing] in distress as [npc2.name] [npc2.verb(continue)] gently sliding [npc2.her] [npc2.clit+] in and out of [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] forcefully holds [npc.herHim] in place,"
									+ " roughly pumping [npc2.her] [npc2.clit+] back and forth past [npc.her] [npc.lips+].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back,"
									+ " [npc.her] protestations coming out in gargled bursts as [npc2.name] [npc2.verb(continue)] roughly slamming [npc2.her] [npc2.clit+] down [npc.her] throat.",
							"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
									+ " [npc.sobbing] in distress as [npc2.name] [npc2.verb(continue)] roughly thrusting [npc2.her] [npc2.clit+] in and out of [npc.her] mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] holds [npc.herHim] in place,"
									+ " eagerly sliding [npc2.her] [npc2.clit+] back and forth past [npc.her] [npc.lips+].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back,"
									+ " [npc.her] protestations coming out in gargled bursts as [npc2.name] [npc2.verb(continue)] eagerly pushing [npc2.her] [npc2.clit+] down [npc.her] throat.",
							"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
									+ " [npc.sobbing] in distress as [npc2.name] [npc2.verb(continue)] eagerly sliding [npc2.her] [npc2.clit+] in and out of [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GIVING_CLIT_ORAL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Suck clit";
		}

		@Override
		public String getActionDescription() {
			return "Continue sucking [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] oral attention.",
					"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] oral attention.",
					"Rapidly bobbing [npc.her] head up and down, [npc.name] [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] oral attention."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly suck clit";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly suck [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] enthusiastic oral attention.",
					"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " greedily wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] oral attention.",
					"Rapidly bobbing [npc.her] head up and down, [npc.name] desperately [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.clit+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] eager oral attention."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop sucking clit";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc2.namePos] [npc2.clit+] out of your mouth and stop sucking on it.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly forcing [npc2.namePos] [npc2.clit+] down [npc.her] throat one last time, [npc.name] then [npc.verb(pull)] [npc.her] head back, putting a quick end to [npc.her] oral attention.",

							"Slamming [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] [npc.verb(force)] [npc2.her] [npc2.clit+] deep down [npc.her] throat,"
									+ " before pulling completely back and letting [npc2.herHim] slip out of [npc.her] mouth."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.clit+] out of [npc.her] mouth, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(put)] an end to [npc.her] oral attention.",

							"With [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] head back, sliding [npc2.namePos] [npc2.clit+] fully out of [npc.her] mouth."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+], struggling against [npc.name] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to let [npc2.herHim] go.",

							" Tears stream down [npc2.namePos] cheeks as [npc2.she] tries to struggle out of [npc.namePos] grip, letting out [npc2.a_sob+] before begging for [npc.herHim] to let [npc2.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], revealing [npc2.her] desire for [npc.name] to continue sucking [npc2.her] [npc2.clit+].",

							" [npc2.Name] [npc2.moansVerb] as [npc.name] withdraws from [npc2.her] groin, betraying [npc2.her] desire to feel [npc.her] [npc.lips+] wrapped around [npc2.her] [npc2.clit+] once more."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
