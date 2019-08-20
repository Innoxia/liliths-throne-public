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
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotBipeds;
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
public class FortressMalesLeaderSA {
	
	public static boolean isBothTargetsUsed() {
		try {
			return Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_ON_BACK), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0
					&& Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_ON_BACK_SECOND), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0;
		} catch(Exception ex) {
			return true;
		}
	}
	
	public static GameCharacter getBreedingTarget() {
		return Sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressMalesLeader.class), SexAreaPenetration.PENIS).isEmpty()
				?null
				:Sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressMalesLeader.class), SexAreaPenetration.PENIS).get(0);
	}
	
	private static GameCharacter getOtherTarget() {
		try {
			GameCharacter otherTarget = null;
			if(getBreedingTarget()==null) {
				if(Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_ON_BACK), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0) {
					otherTarget = Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_ON_BACK_SECOND);
				} else {
					otherTarget = Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_ON_BACK);
				}
				
			} else {
				otherTarget = Sex.getSexPositionSlot(getBreedingTarget())==SexSlotBipeds.MISSIONARY_ON_BACK
						?Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_ON_BACK_SECOND)
						:Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_ON_BACK);
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
					&& (Sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Sex.getNumberOfOrgasms(Sex.getCharacterPerformingAction())==1
					&& Sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
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
					+ "[npc2.Name] soon [npc2.verb(realise)] what the the potion [npc.name] just drank was for, as each time the [npc.race]'s balls slap against [npc2.her] [npc2.assSkin], they feel noticeably heavier and heavier."
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
					&& (Sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Sex.getNumberOfOrgasms(Sex.getCharacterPerformingAction())==1
					&& Sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					"Grabbing [npc2.namePos] [npc2.hips+], [npc.name] pulls back and slides [npc.her] [npc.cock+] out of [npc2.her] cum-stuffed cunt."
					+(getOtherTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
							?" Shuffling to one side, [npc.she] then quickly removes the clothing blocking [npc3.namePos] [npc3.pussy], before starting to rub the head of [npc.her] cock up and down between [npc3.her] [npc3.labia+]."
							:" Shuffling to one side, [npc.she] then quickly lines [npc.her] [npc.cock+] up to [npc3.namePos] [npc3.pussy], before starting to rub the head up and down between [npc3.her] [npc3.labia+].")
					+" Suddenly thrusting [npc.her] [npc.hips] forwards, [npc.she] sinks [npc.her] [npc.cock+] deep into [npc3.namePos] [npc2.pussy+], letting out a deep growl as [npc.she] penetrates [npc3.herHim],"
					+ " [npc.speechNoEffects(Don't think I forgot about you!)]</br>"
					+ "Keeping [npc.her] [npc.cock+] hilted in [npc3.namePos] [npc3.pussy], [npc.name] then produces a small bottle filled with a light blue liquid."
					+ " Pulling out the stopper with [npc.her] teeth, [npc.she] quickly downs the potion, before throwing the now-empty vial to one side.</br>"
					+ "Pulling [npc.her] [npc.hips] back, [npc.name] then starts to rhythmically fuck [npc3.name],"
						+ " with each of [npc.her] thrusts easily sliding in and out, thanks to the cum and juices from [npc2.namePos] pussy lubricating [npc.her] thick shaft.</br>"
					+ "[npc3.Name] soon [npc3.verb(realise)] what the the potion [npc.name] just drank was for, as each time the [npc.race]'s balls slap against [npc3.her] [npc3.assSkin], they feel noticeably heavier and heavier."
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
			
			Sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.VAGINA, otherTarget, false);
			
			Sex.stopOngoingAction(
					Sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBreedingTarget(),
					SexAreaOrifice.VAGINA);

			Sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Sex.applyOngoingAction(
					Sex.getCharacterPerformingAction(),
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
					&& (Sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Sex.getNumberOfOrgasms(Sex.getCharacterPerformingAction())==1
					&& Sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
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
					+ "[npc2.Name] soon [npc2.verb(realise)] what the the potion [npc.name] just drank was for, as each time the [npc.race]'s balls slap against [npc2.her] [npc2.assSkin], they feel noticeably heavier and heavier."
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
					&& (Sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
							|| Sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Sex.getNumberOfOrgasms(Sex.getCharacterPerformingAction())==1
					&& Sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					(getOtherTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
							?" Shuffling to one side, [npc.she] quickly removes the clothing blocking [npc3.namePos] [npc3.pussy], before starting to rub the head of [npc.her] cock up and down between [npc3.her] [npc3.labia+]."
							:" Shuffling to one side, [npc.she] quickly lines [npc.her] [npc.cock+] up to [npc3.namePos] [npc3.pussy], before starting to rub the head up and down between [npc3.her] [npc3.labia+].")
					+" Suddenly thrusting [npc.her] [npc.hips] forwards, [npc.she] sinks [npc.her] [npc.cock+] deep into [npc3.namePos] [npc2.pussy+], letting out a deep growl as [npc.she] penetrates [npc3.herHim],"
					+ " [npc.speechNoEffects(Don't think I forgot about you!)]</br>"
					+ "Keeping [npc.her] [npc.cock+] hilted in [npc3.namePos] [npc3.pussy], [npc.name] then produces a small bottle filled with a light blue liquid."
					+ " Pulling out the stopper with [npc.her] teeth, [npc.she] quickly downs the potion, before throwing the now-empty vial to one side.</br>"
					+ "Pulling [npc.her] [npc.hips] back, [npc.name] then starts to rhythmically fuck [npc3.name],"
						+ " with each of [npc.her] thrusts easily sliding in and out of [npc3.her] [npc3.pussy+], thanks to the cum and juices from [npc2.namePos] pussy lubricating [npc.her] thick shaft.</br>"
					+ "[npc3.Name] soon [npc3.verb(realise)] what the the potion [npc.name] just drank was for, as each time the [npc.race]'s balls slap against [npc3.her] [npc3.assSkin], they feel noticeably heavier and heavier."
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
			
			Sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.VAGINA, otherTarget, false);
			
			Sex.stopOngoingAction(
					Sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBreedingTarget(),
					SexAreaOrifice.VAGINA);

			Sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Sex.applyOngoingAction(
					Sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.VAGINA,
					true);
		}
	};


	
}
