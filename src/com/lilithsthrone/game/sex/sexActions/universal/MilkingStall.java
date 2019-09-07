package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.1
 * @author Innoxia
 */
public class MilkingStall {

	public static final SexAction SWITCH_TO_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_STOCKS)
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Sex.isDom(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Move behind";
		}

		@Override
		public String getActionDescription() {
			return "Move around behind [npc2.name] and get ready to fuck [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to fuck [npc2.name] while [npc2.sheIs] locked in the stocks,"
						+ " [npc.name] [npc.verb(step)] up behind [npc2.herHim] and [npc.verb(start)] grinding [npc.her] groin up against [npc2.her] [npc2.ass+]."
					+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moanVerb], "
					+ "[npc.speech(Be a good [npc2.girl] and hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS)
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Sex.isDom(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Perform oral";
		}

		@Override
		public String getActionDescription() {
			return "Kneel behind [npc2.name] and perform oral on [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to perform oral on [npc2.name], [npc.name] [npc.verb(kneel)]down behind [npc2.herHim]."
					+ " Bringing [npc.her] mouth up to [npc2.her] groin, [npc.she] [npc.moanVerb],"
					+ " [npc.speech(You're going to love this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexSlotMilkingStall.PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};
	
	public static final SexAction SWITCH_TO_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPositionChangingAllowed(Sex.getCharacterPerformingAction())
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.RECEIVING_ORAL_STOCKS)
					&& !Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Sex.isDom(Sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Move to front";
		}

		@Override
		public String getActionDescription() {
			return "Decide to use [npc2.namePos] mouth.";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to use [npc2.namePos] mouth, [npc.name] [npc.verb(step)] back, before moving around in front of [npc2.her] [npc2.face]."
					+ " Bringing [npc.her] groin up to [npc2.her] mouth, [npc.she] [npc.moanVerb],"
					+ " [npc.speech(You're going to love this!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterPerformingAction(), SexSlotMilkingStall.RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};

	public static final SexAction SLAP_ASS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterPerformingAction())
					&& Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_STOCKS);
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public String getActionTitle() {
			return "Slap ass";
		}

		@Override
		public String getActionDescription() {
			return "Start slapping [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public String getDescription() {
			String tailSpecial1 = "", tailSpecial2 = "";
			
			if (Sex.getAllContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] down and roughly [npc.verb(grope)] [npc2.her] [npc2.ass+],"
								+ " before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
						break;
					default:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], [npc.name] roughly [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(yank)] upwards,"
											+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
						break;
				}
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.pussy+], [npc.name] [npc.verb(growl)] down that [npc.sheIs] going to put [npc2.name] in [npc2.her] place,"
								+ " before starting make good on [npc.her] threat by aggressively slapping [npc2.her] exposed ass cheeks.";
						break;
					default:
						tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.pussy+], [npc.name] [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+] in one [npc.hand],"
											+ " roughly yanking [npc2.her] [npc2.ass+] up high in the air before starting to aggressively slap [npc2.her] exposed cheeks.";
						break;
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] slapping [npc2.her] [npc2.ass+] in time with [npc.her] powerful thrusts into [npc2.her] [npc2.pussy+].",
							
							"Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(use)] one [npc.hand] to hold [npc2.herHim] still,"
								+ " while using [npc.her] other to deliver a series of stinging slaps to [npc2.her] exposed ass cheeks.",
							
							"While [npc.name] [npc.verb(continue)] pounding away at [npc2.namePos] [npc2.pussy+], [npc.she] [npc.verb(reach)] down and [npc.verb(start)] to roughly slap [npc2.her] [npc2.ass+],"
									+ " growling in glee as [npc2.she] [npc2.verb(squirm)] and [npc2.verb(squeal)] under [npc.her] stinging blows.");
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] down and roughly [npc.verb(grope)] [npc2.her] [npc2.ass+],"
								+ " before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
						break;
					default:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+], [npc.name] roughly [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(yank)] upwards,"
											+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
						break;
				}
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.asshole+], [npc.name] [npc.verb(growl)] down that [npc.sheIs] going to put [npc2.name] in [npc2.her] place,"
								+ " before starting make good on [npc.her] threat by aggressively slapping [npc2.her] exposed ass cheeks.";
						break;
					default:
						tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.asshole+], [npc.name] [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+] in one [npc.hand],"
											+ " roughly yanking [npc2.her] [npc2.ass+] up high in the air before starting to aggressively slap [npc2.her] exposed cheeks.";
						break;
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] slapping [npc2.her] [npc2.ass+] in time with [npc.her] powerful thrusts into [npc2.her] [npc2.asshole+].",
							
							"Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole], [npc.name] [npc.verb(use)] one [npc.hand] to hold [npc2.herHim] still,"
								+ " while using [npc.her] other to deliver a series of stinging slaps to [npc2.her] exposed ass cheeks.",
							
							"While [npc.name] [npc.verb(continue)] pounding away at [npc2.namePos] [npc2.asshole+], [npc.she] [npc.verb(reach)] down and [npc.verb(start)] to roughly slap [npc2.her] [npc2.ass+],"
									+ " growling in glee as [npc2.she] [npc2.verb(squirm)] and [npc2.verb(squeal)] under [npc.her] stinging blows.");
			
			} else {
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial1 = "Growling down into [npc2.namePos] [npc2.ear+], [npc.name] [npc.verb(reach)] down and [npc.verb(grab)] [npc2.her] waist, using one hand to hold [npc2.herHim] still,"
											+ " while using [npc.her] other to deliver a series of stinging slaps to [npc2.her] [npc2.ass+].";
						break;
					default:
						tailSpecial1 = "Growling down into [npc2.namePos] [npc2.ear], [npc.name] roughly [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(yank)] upwards,"
									+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
						break;
				}
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial2 = "[npc.Name] [npc.verb(reach)] down and [npc.verb(grab)] [npc2.namePos] waist with one hand,"
								+ " holding [npc2.herHim] firmly in [npc.her] grip as [npc.she] [npc.verb(start)] to aggressively slap [npc2.her] exposed cheeks.";
						break;
					default:
						tailSpecial2 = "[npc.Name] [npc.verb(reach)] down and [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+], causing [npc2.herHim] to let out a surprised yelp as [npc.she] roughly [npc.verb(yank)] upwards,"
											+ " forcing [npc2.herHim] to push [npc2.her] [npc2.ass+] up high in the air as [npc.name] [npc.verb(start)] to aggressively slap [npc2.her] exposed cheeks.";
						break;
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] roughly slapping [npc2.her] [npc2.ass+],"
									+ " and [npc.name] [npc.verb(find)] [npc.herself] grinning in glee as [npc.she] [npc.verb(watch)] [npc2.herHim] squirm and cry out beneath [npc.her] stinging blows.",
							
							"[npc2.Name] [npc2.verb(let)] out a startled wail as [npc.name] [npc.verb(start)] to roughly slap [npc2.her] [npc2.ass+],"
									+ " and [npc.name] quickly [npc.verb(find)] [npc.herself] grinning in glee as [npc.she] [npc.verb(watch)] [npc2.herHim] squirm and wail beneath [npc.her] relentless blows.",
							
							"[npc.Name] [npc.verb(growl)] down that [npc.sheIs] going to put [npc2.name] in [npc2.her] place, before starting to aggressively slap [npc2.her] [npc2.ass+],"
									+ " smirking down at [npc2.her] submissive form as [npc2.she] squeals and cries out beneath [npc.her] relentless blows.");
			}
		}
		
	};
}
