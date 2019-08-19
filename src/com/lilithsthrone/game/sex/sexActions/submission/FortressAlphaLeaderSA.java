package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public class FortressAlphaLeaderSA {
	
	public static boolean isBothTargetsUsed() {
		try {
			return Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Sex.getCharacterInPosition(SexSlotBipeds.KNEELING_PERFORMING_ORAL), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0
					&& Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Sex.getCharacterInPosition(SexSlotBipeds.KNEELING_PERFORMING_ORAL_TWO), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0;
		} catch(Exception ex) {
			return true;
		}
	}
	
	public static GameCharacter getBlowjobTarget() {
		return Sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressAlphaLeader.class), SexAreaPenetration.PENIS).isEmpty()
				?null
				:Sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressAlphaLeader.class), SexAreaPenetration.PENIS).get(0);
	}
	
	private static GameCharacter getOtherTarget() {
		try {
			if(getBlowjobTarget()==null) {
				if(Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Sex.getCharacterInPosition(SexSlotBipeds.KNEELING_PERFORMING_ORAL), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0) {
					return Sex.getCharacterInPosition(SexSlotBipeds.KNEELING_PERFORMING_ORAL_TWO);
				} else {
					return Sex.getCharacterInPosition(SexSlotBipeds.KNEELING_PERFORMING_ORAL);
				}
			}
			return Sex.getSexPositionSlot(getBlowjobTarget())==SexSlotBipeds.KNEELING_PERFORMING_ORAL
					?Sex.getCharacterInPosition(SexSlotBipeds.KNEELING_PERFORMING_ORAL_TWO)
					:Sex.getCharacterInPosition(SexSlotBipeds.KNEELING_PERFORMING_ORAL);
		} catch(Exception ex) {
			return null;
		}
	}
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Round two";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc2.name] that you aren't finished yet!";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()==null
					&& (Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
						|| Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Sex.getNumberOfOrgasms(Sex.getCharacterPerformingAction())==1
					&& Sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "Grabbing the back of [npc2.namePos] head, [npc.name] roughly slams [npc.her] hips forwards and hilts [npc.her] [npc.cock+] fully down [npc2.her] throat."
					+ " Letting out a deep groan as [npc.she] feels [npc2.namePos] lips bump up against the base of [npc.her] cock, [npc.she] growls,"
					+ " [npc.speechNoEffects(I'm not finished yet, bitch! Hope you're ready for round two!)]";
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING_SWITCH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Round two (switch)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "Switch to fucking [npc.namePos] face!");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
						|| Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Sex.getNumberOfOrgasms(Sex.getCharacterPerformingAction())==1
					&& Sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					"Grabbing the sides of [npc2.namePos] head, [npc.name] pushes [npc2.herHim] back, sliding [npc.her] [npc.cock+] out from [npc2.her] mouth and leaving a thick trail of cummy saliva drooling from [npc2.her] [npc2.lips].<br/>"
					+ "Focusing [npc.her] attention on [npc3.name], [npc.name] pushes the cum-coated tip of [npc.her] [npc.cock+] up against [npc3.her] [npc3.lips+],"
						+ " before roughly slamming [npc.her] hips forwards and hilting it fully down [npc3.her] throat."
					+ " Letting out a deep groan as [npc.she] feels [npc3.namePos] [npc3.lips] bump up against the base of [npc.her] cock, [npc.she] growls,"
					+ " [npc.speechNoEffects(It's your turn, bitch! Hope you're ready for my second load!)]");
		}
		
		@Override
		public void applyEffects(){
			GameCharacter otherTarget = getOtherTarget();
			
			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.MOUTH, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.MOUTH, otherTarget, false);
			
			Sex.stopOngoingAction(
					Sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBlowjobTarget(),
					SexAreaOrifice.MOUTH);

			Sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Sex.applyOngoingAction(
					Sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.MOUTH,
					true);
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Round two";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc2.name] that you aren't finished yet!";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()==null
					&& (Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
						|| Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Sex.getNumberOfOrgasms(Sex.getCharacterPerformingAction())==1
					&& Sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "Grabbing the back of [npc2.namePos] head, [npc.name] pushes the cum-coated tip of [npc.her] [npc.cock+] up against [npc2.namePos] lips,"
						+ " before roughly slamming [npc.her] hips forwards and hilting it fully down [npc2.her] throat."
					+ " Letting out a deep groan as [npc.she] feels [npc2.namePos] lips bump up against the base of [npc.her] cock, [npc.she] growls,"
					+ " [npc.speechNoEffects(I'm not finished yet, bitch! Hope you're ready for round two!)]";
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START_SWITCH = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Round two (switch)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "Switch to fucking [npc.namePos] face!");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
							|| Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Sex.getNumberOfOrgasms(Sex.getCharacterPerformingAction())==1
					&& Sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					"Focusing [npc.her] attention on [npc3.name], [npc.name] pushes the cum-coated tip of [npc.her] [npc.cock+] up against [npc3.her] [npc3.lips+],"
						+ " before roughly slamming [npc.her] hips forwards and hilting it fully down [npc3.her] throat."
					+ " Letting out a deep groan as [npc.she] feels [npc3.namePos] [npc3.lips] bump up against the base of [npc.her] cock, [npc.she] growls,"
					+ " [npc.speechNoEffects(It's your turn, bitch! Hope you're ready for my second load!)]");
		}
		
		@Override
		public void applyEffects(){
			GameCharacter otherTarget = getOtherTarget();

			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.MOUTH, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.MOUTH, otherTarget, false);
			
			Sex.stopOngoingAction(
					Sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBlowjobTarget(),
					SexAreaOrifice.MOUTH);

			Sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Sex.applyOngoingAction(
					Sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.MOUTH,
					true);
		}
	};


	
}
