package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.11
 * @version 0.3.4
 * @author Innoxia
 */
public class FortressMalesLeaderSA {
	
	public static boolean isBothTargetsUsed() {
		try {
			return Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0
					&& Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN_TWO), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0;
		} catch(Exception ex) {
			return true;
		}
	}
	
	public static GameCharacter getBreedingTarget() {
		return Main.sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressMalesLeader.class), SexAreaPenetration.PENIS).isEmpty()
				?null
				:Main.sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressMalesLeader.class), SexAreaPenetration.PENIS).get(0);
	}
	
	private static GameCharacter getOtherTarget() {
		try {
			GameCharacter otherTarget = null;
			if(getBreedingTarget()==null) {
				if(Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0) {
					otherTarget = Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN_TWO);
				} else {
					otherTarget = Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN);
				}
				
			} else {
				otherTarget = Main.sex.getSexPositionSlot(getBreedingTarget())==SexSlotLyingDown.LYING_DOWN
						?Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN_TWO)
						:Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN);
			}
			
			if(!otherTarget.hasVagina() || !otherTarget.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				return null;
			}
			return otherTarget;
			
		} catch(Exception ex) {
			return null;
		}
	}
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
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
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "Grabbing [npc2.namePos] [npc2.hips+], [npc.name] thrusts forwards and sinks [npc.her] [npc.cock+] deep into [npc2.her] cum-stuffed cunt."
					+ " Letting out a deep groan as [npc.she] feels [npc2.namePos] [npc2.labia+] bump against the base of [npc.her] cock, [npc.she] growls,"
					+ " [npc.speechNoEffects(Let's give you a double stuffing! I want to be sure I'm going to knock you up!)]</br>"
					+ "Keeping [npc.her] [npc.cock+] hilted in [npc2.namePos] [npc2.pussy], [npc.name] produces a small bottle filled with a light blue liquid."
					+ " Pulling out the stopper with [npc.her] teeth, [npc.she] quickly downs the potion, before throwing the now-empty vial to one side.</br>"
					+ "Pulling [npc.her] [npc.hips] back, [npc.name] then starts to rhythmically fuck [npc2.name] once more,"
						+ " with each of [npc.her] thrusts being accompanied by a lewd squelching sound as the cum filling [npc2.namePos] pussy drools out around [npc.her] thick shaft.</br>"
					+ "[npc2.Name] soon [npc2.verb(realise)] what the potion [npc.name] just drank was for, as each time the [npc.race]'s balls slap against [npc2.her] [npc2.assSkin], they feel noticeably heavier and heavier."
					+ " To confirm [npc2.her] suspicions, [npc.name] grunts, [npc.speech(Fuck, my balls feel so full again! Your womb's going to be pumped full of my seed!)]";
		}

		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING_SWITCH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Round two (switch)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "Switch to fucking [npc.name].");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					"Grabbing [npc2.namePos] [npc2.hips+], [npc.name] pulls back and slides [npc.her] [npc.cock+] out of [npc2.her] cum-stuffed cunt."
					+(getOtherTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
							?" Shuffling to one side, [npc.she] then quickly removes the clothing blocking [npc3.namePos] [npc3.pussy], before starting to rub the head of [npc.her] cock up and down between [npc3.her] [npc3.labia+]."
							:" Shuffling to one side, [npc.she] then quickly lines [npc.her] [npc.cock+] up to [npc3.namePos] [npc3.pussy], before starting to rub the head up and down between [npc3.her] [npc3.labia+].")
					+" Suddenly thrusting [npc.her] [npc.hips] forwards, [npc.she] sinks [npc.her] [npc.cock+] deep into [npc3.namePos] [npc2.pussy+], letting out a deep growl as [npc.she] penetrates [npc3.herHim],"
					+ " [npc.speechNoEffects(Don't think I forgot about you!)]</br></br>"
					+ "Keeping [npc.her] [npc.cock+] hilted in [npc3.namePos] [npc3.pussy], [npc.name] then produces a small bottle filled with a light blue liquid."
					+ " Pulling out the stopper with [npc.her] teeth, [npc.she] quickly downs the potion, before throwing the now-empty vial to one side.</br></br>"
					+ "Pulling [npc.her] [npc.hips] back, [npc.name] then starts to rhythmically fuck [npc3.name],"
						+ " with each of [npc.her] thrusts easily sliding in and out, thanks to the cum and juices from [npc2.namePos] pussy lubricating [npc.her] thick shaft.</br>"
					+ "[npc3.Name] soon [npc3.verb(realise)] what the potion [npc.name] just drank was for, as each time the [npc.race]'s balls slap against [npc3.her] [npc3.assSkin], they feel noticeably heavier and heavier."
					+ " To confirm [npc3.her] suspicions, [npc.name] grunts, [npc.speech(Fuck, my balls feel so full again! Your womb's going to be pumped full of my seed!)]");
		}
		
		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
			GameCharacter otherTarget = getOtherTarget();
			
			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.VAGINA, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Main.sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.VAGINA, otherTarget, false);
			
			Main.sex.stopOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBreedingTarget(),
					SexAreaOrifice.VAGINA);

			Main.sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Main.sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Main.sex.applyOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.VAGINA,
					true);
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
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
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "Grabbing [npc2.namePos] [npc2.hips+], [npc.name] lines [npc.her] [npc.cock+] up to [npc2.her] [npc2.pussy+], before thrusting forwards and sinking [npc.her] [npc.cock+] deep into [npc2.her] cum-stuffed cunt."
					+ " Letting out a deep groan as [npc.she] feels [npc2.namePos] [npc2.labia+] bump against the base of [npc.her] cock, [npc.she] growls,"
					+ " [npc.speechNoEffects(Let's give you a double stuffing! I want to be sure I'm going to knock you up!)]</br>"
					+ "Keeping [npc.her] [npc.cock+] hilted in [npc2.namePos] [npc2.pussy], [npc.name] produces a small bottle filled with a light blue liquid."
					+ " Pulling out the stopper with [npc.her] teeth, [npc.she] quickly downs the potion, before throwing the now-empty vial to one side.</br>"
					+ "Pulling [npc.her] [npc.hips] back, [npc.name] then starts to rhythmically fuck [npc2.name] once more,"
						+ " with each of [npc.her] thrusts being accompanied by a lewd squelching sound as the cum filling [npc2.namePos] pussy drools out around [npc.her] thick shaft.</br>"
					+ "[npc2.Name] soon [npc2.verb(realise)] what the potion [npc.name] just drank was for, as each time the [npc.race]'s balls slap against [npc2.her] [npc2.assSkin], they feel noticeably heavier and heavier."
					+ " To confirm [npc2.her] suspicions, [npc.name] grunts, [npc.speech(Fuck, my balls feel so full again! Your womb's going to be pumped full of my seed!)]";
		}

		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START_SWITCH = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Round two (switch)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "Switch to fucking [npc.name].");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
							|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					(getOtherTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
							?" Shuffling to one side, [npc.she] quickly removes the clothing blocking [npc3.namePos] [npc3.pussy], before starting to rub the head of [npc.her] cock up and down between [npc3.her] [npc3.labia+]."
							:" Shuffling to one side, [npc.she] quickly lines [npc.her] [npc.cock+] up to [npc3.namePos] [npc3.pussy], before starting to rub the head up and down between [npc3.her] [npc3.labia+].")
					+" Suddenly thrusting [npc.her] [npc.hips] forwards, [npc.she] sinks [npc.her] [npc.cock+] deep into [npc3.namePos] [npc2.pussy+], letting out a deep growl as [npc.she] penetrates [npc3.herHim],"
					+ " [npc.speechNoEffects(Don't think I forgot about you!)]</br></br>"
					+ "Keeping [npc.her] [npc.cock+] hilted in [npc3.namePos] [npc3.pussy], [npc.name] then produces a small bottle filled with a light blue liquid."
					+ " Pulling out the stopper with [npc.her] teeth, [npc.she] quickly downs the potion, before throwing the now-empty vial to one side.</br></br>"
					+ "Pulling [npc.her] [npc.hips] back, [npc.name] then starts to rhythmically fuck [npc3.name],"
						+ " with each of [npc.her] thrusts easily sliding in and out of [npc3.her] [npc3.pussy+], thanks to the cum and juices from [npc2.namePos] pussy lubricating [npc.her] thick shaft.</br>"
					+ "[npc3.Name] soon [npc3.verb(realise)] what the potion [npc.name] just drank was for, as each time the [npc.race]'s balls slap against [npc3.her] [npc3.assSkin], they feel noticeably heavier and heavier."
					+ " To confirm [npc3.her] suspicions, [npc.name] grunts, [npc.speech(Fuck, my balls feel so full again! Your womb's going to be pumped full of my seed!)]");
		}
		
		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
			GameCharacter otherTarget = getOtherTarget();

			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.VAGINA, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Main.sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.VAGINA, otherTarget, false);
			
			Main.sex.stopOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBreedingTarget(),
					SexAreaOrifice.VAGINA);

			Main.sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Main.sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Main.sex.applyOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.VAGINA,
					true);
		}
	};


	
}
