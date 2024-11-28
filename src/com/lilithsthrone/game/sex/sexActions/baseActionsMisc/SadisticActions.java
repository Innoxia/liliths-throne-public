package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ArousalIncrease;
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
 * @since 0.3.4
 * @version 0.4
 * @author Innoxia
 */
public class SadisticActions {
	
	/**
	 * A non-sadistic version of 'slap ass'.
	 */
	public static final SexAction SPANK_ASS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return SLAP_ASS.isBaseRequirementsMet()
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST);
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getActionTitle() {
			return "Spank [npc2.herHim]";
		}
		@Override
		public String getActionDescription() {
			return "Start playfully spanking [npc2.namePos] [npc2.ass+].";
		}
		@Override
		public String getDescription() {
			String tailSpecial1 = "", tailSpecial2 = "";
			
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] down and playfully [npc.verb(grope)] [npc2.her] [npc2.ass+],"
							+ " before starting to deliver a series of firm spanks to [npc2.her] exposed cheeks.";
				} else {
					tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], [npc.name] playfully [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(pull)] upwards,"
									+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of firm spanks to [npc2.her] exposed cheeks.";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.pussy+], [npc.name] [npc.moansVerb] that [npc.sheIs] going to put [npc2.name] in [npc2.her] place,"
							+ " before starting to make good on [npc.her] threat by playfully slapping [npc2.her] exposed ass cheeks.";
				} else {
					tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.pussy+], [npc.name] [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+] in one [npc.hand],"
										+ " before playfully pulling [npc2.her] [npc2.ass+] up high in the air and starting to firmly spank [npc2.her] exposed cheeks.";
				}

				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(use)] one [npc.hand] to hold [npc2.herHim] still,"
									+ " while using [npc.her] other to deliver a series of firm spanks to [npc2.her] exposed ass cheeks.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] playfully spanking [npc2.her] [npc2.ass+] in time with [npc.her] thrusts into [npc2.her] [npc2.pussy+].",
							isTargetedCharacterInanimate()?null
								:"While [npc.name] [npc.verb(continue)] pounding away at [npc2.namePos] [npc2.pussy+], [npc.she] [npc.verb(reach)] down and [npc.verb(start)] to playfully slap [npc2.her] [npc2.ass+],"
									+ " which causes [npc2.herHim] to [npc2.verb(squirm)] and [npc2.verb(squeal)] in response.");
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] down and playfully [npc.verb(grope)] [npc2.her] [npc2.ass+],"
							+ " before starting to deliver a series of firm spanks to [npc2.her] exposed cheeks.";
				} else {
					tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+], [npc.name] playfully [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(pull)] upwards,"
									+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of firm spanks to [npc2.her] exposed cheeks.";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.asshole+], [npc.name] [npc.moansVerb] that [npc.sheIs] going to put [npc2.name] in [npc2.her] place,"
							+ " before starting to make good on [npc.her] threat by playfully slapping [npc2.her] exposed ass cheeks.";
				} else {
					tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.asshole+], [npc.name] [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+] in one [npc.hand],"
										+ " before playfully pulling [npc2.her] [npc2.ass+] up high in the air and starting to firmly spank [npc2.her] exposed cheeks.";
				}
				
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole], [npc.name] [npc.verb(use)] one [npc.hand] to hold [npc2.herHim] still,"
								+ " while using [npc.her] other to deliver a series of firm spanks to [npc2.her] exposed ass cheeks.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] playfully spanking [npc2.her] [npc2.ass+] in time with [npc.her] thrusts into [npc2.her] [npc2.asshole+].",
							isTargetedCharacterInanimate()?null
								:"While [npc.name] [npc.verb(continue)] pounding away at [npc2.namePos] [npc2.asshole+], [npc.she] [npc.verb(reach)] down and [npc.verb(start)] to playfully slap [npc2.her] [npc2.ass+],"
									+ " which causes [npc2.herHim] to [npc2.verb(squirm)] and [npc2.verb(squeal)] in response.");
				
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Letting out [npc.a_moan+], [npc.name] [npc.verb(reach)] down and [npc.verb(grab)] [npc2.namePos] waist, using one [npc.hand(true)] to hold [npc2.herHim] still,"
										+ " while using [npc.her] other to deliver a series of firm spanks to [npc2.her] [npc2.ass+].";
				} else {
					tailSpecial1 = "Letting out [npc.a_moan+], [npc.name] playfully [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(pull)] upwards,"
								+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of firm spanks to [npc2.her] exposed cheeks.";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "[npc.Name] [npc.verb(reach)] down and [npc.verb(grab)] [npc2.namePos] waist with one hand,"
							+ " holding [npc2.herHim] firmly in [npc.her] grip as [npc.she] [npc.verb(start)] to playfully slap [npc2.her] exposed cheeks.";
				} else {
					tailSpecial2 = isTargetedCharacterInanimate()?null
									:"[npc.Name] [npc.verb(reach)] down and [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+], causing [npc2.herHim] to let out a surprised yelp as [npc.she] playfully [npc.verb(pull)] upwards,"
											+ " forcing [npc2.herHim] to push [npc2.her] [npc2.ass+] up high in the air as [npc.name] [npc.verb(start)] to firmly spank [npc2.her] exposed cheeks.";
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc.Name] [npc.verb(use)] one [npc.hand] to hold [npc2.name] still, while using [npc.her] other to deliver a series of firm spanks to [npc2.her] exposed ass cheeks.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] playfully spanking [npc2.her] [npc2.ass+].",
							isTargetedCharacterInanimate()?null
								:"[npc.Name] [npc.verb(reach)] down and [npc.verb(start)] to playfully spank [npc2.her] [npc2.ass+], which causes [npc2.herHim] to [npc2.verb(squirm)] and [npc2.verb(squeal)] in response.");
			}
		}
	};
	
	public static final SexAction SLAP_ASS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean assFinger = false;
			boolean assFingerReversed = false;
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.LYING_DOWN)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.BACK_TO_WALL)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.OVER_DESK_BACK)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.SITTING)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.SITTING_IN_LAP)) {
				return false;
			}
			try {
				assFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(target))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.ASS);
			} catch(Exception ex) {
				// No available finger-ass actions, so can't reach ass
			}
			try {
				assFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(target)).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.ASS).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-ass actions, so can't reach ass
			}
			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(target)
					&& (assFinger || assFingerReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
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
			
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] down and roughly [npc.verb(grope)] [npc2.her] [npc2.ass+],"
							+ " before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
				} else {
					tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], [npc.name] roughly [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(yank)] upwards,"
									+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.pussy+], [npc.name] [npc.verb(growl)] down that [npc.sheIs] going to put [npc2.name] in [npc2.her] place,"
							+ " before starting to make good on [npc.her] threat by aggressively slapping [npc2.her] exposed ass cheeks.";
				} else {
					tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.pussy+], [npc.name] [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+] in one [npc.hand],"
										+ " roughly yanking [npc2.her] [npc2.ass+] up high in the air before starting to aggressively slap [npc2.her] exposed cheeks.";
				}
				
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(use)] one [npc.hand] to hold [npc2.herHim] still,"
								+ " while using [npc.her] other to deliver a series of stinging slaps to [npc2.her] exposed ass cheeks.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] slapping [npc2.her] [npc2.ass+] in time with [npc.her] powerful thrusts into [npc2.her] [npc2.pussy+].",
							isTargetedCharacterInanimate()?null
								:"While [npc.name] [npc.verb(continue)] pounding away at [npc2.namePos] [npc2.pussy+], [npc.she] [npc.verb(reach)] down and [npc.verb(start)] to roughly slap [npc2.her] [npc2.ass+],"
									+ " growling in glee as [npc2.she] [npc2.verb(squirm)] and [npc2.verb(squeal)] under [npc.her] stinging blows.");
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] down and roughly [npc.verb(grope)] [npc2.her] [npc2.ass+],"
							+ " before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
				} else {
					tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+], [npc.name] roughly [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(yank)] upwards,"
										+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.asshole+], [npc.name] [npc.verb(growl)] down that [npc.sheIs] going to put [npc2.name] in [npc2.her] place,"
							+ " before starting to make good on [npc.her] threat by aggressively slapping [npc2.her] exposed ass cheeks.";
				} else {
					tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.asshole+], [npc.name] [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+] in one [npc.hand],"
										+ " roughly yanking [npc2.her] [npc2.ass+] up high in the air before starting to aggressively slap [npc2.her] exposed cheeks.";
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole], [npc.name] [npc.verb(use)] one [npc.hand] to hold [npc2.herHim] still,"
								+ " while using [npc.her] other to deliver a series of stinging slaps to [npc2.her] exposed ass cheeks.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] slapping [npc2.her] [npc2.ass+] in time with [npc.her] powerful thrusts into [npc2.her] [npc2.asshole+].",
							isTargetedCharacterInanimate()?null
								:"While [npc.name] [npc.verb(continue)] pounding away at [npc2.namePos] [npc2.asshole+], [npc.she] [npc.verb(reach)] down and [npc.verb(start)] to roughly slap [npc2.her] [npc2.ass+],"
									+ " growling in glee as [npc2.she] [npc2.verb(squirm)] and [npc2.verb(squeal)] under [npc.her] stinging blows.");
			
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Growling down into [npc2.namePos] [npc2.ear+], [npc.name] [npc.verb(reach)] down and [npc.verb(grab)] [npc2.her] waist, using one hand to hold [npc2.herHim] still,"
										+ " while using [npc.her] other to deliver a series of stinging slaps to [npc2.her] [npc2.ass+].";
				} else {
					tailSpecial1 = "Growling down into [npc2.namePos] [npc2.ear], [npc.name] roughly [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(yank)] upwards,"
								+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "[npc.Name] [npc.verb(reach)] down and [npc.verb(grab)] [npc2.namePos] waist with one hand,"
							+ " holding [npc2.herHim] firmly in [npc.her] grip as [npc.she] [npc.verb(start)] to aggressively slap [npc2.her] exposed cheeks.";
				} else {
					tailSpecial2 = isTargetedCharacterInanimate()?null
							:"[npc.Name] [npc.verb(reach)] down and [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+], causing [npc2.herHim] to let out a surprised yelp as [npc.she] roughly [npc.verb(yank)] upwards,"
										+ " forcing [npc2.herHim] to push [npc2.her] [npc2.ass+] up high in the air as [npc.name] [npc.verb(start)] to aggressively slap [npc2.her] exposed cheeks.";
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] roughly slapping [npc2.her] [npc2.ass+],"
									+ " and [npc.name] [npc.verb(find)] [npc.herself] grinning in glee as [npc.she] [npc.verb(watch)] [npc2.herHim] squirm and cry out beneath [npc.her] stinging blows.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] out a startled wail as [npc.name] [npc.verb(start)] to roughly slap [npc2.her] [npc2.ass+],"
									+ " and [npc.name] quickly [npc.verb(find)] [npc.herself] grinning in glee as [npc.she] [npc.verb(watch)] [npc2.herHim] squirm and wail beneath [npc.her] relentless blows.",
							isTargetedCharacterInanimate()?null
								:"[npc.Name] [npc.verb(growl)] down that [npc.sheIs] going to put [npc2.name] in [npc2.her] place, before starting to aggressively slap [npc2.her] [npc2.ass+],"
									+ " smirking down at [npc2.her] submissive form as [npc2.she] squeals and cries out beneath [npc.her] relentless blows.");
			}
		}
	};
	
	public static final SexAction SLAP_FACE = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthFinger || mouthFingerReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
		}
		
		@Override
		public String getActionTitle() {
			return "Slap face";
		}

		@Override
		public String getActionDescription() {
			return "Slap [npc2.namePos] face to put [npc2.herHim] in [npc2.her] place.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Wanting to put [npc2.name] in [npc2.her] place, [npc.name] [npc.verb(lift)] [npc.her] [npc.hand], before swinging down to deliver a sharp, stinging slap to [npc2.her] face.",
					"Lifting [npc.her] [npc.hand], [npc.name] [npc.verb(swing)] down, delivering a sharp slap to [npc2.namePos] face in order to put [npc2.herHim] in [npc2.her] place.",
					"Seeking to remind [npc2.name] who's in charge, [npc.name] [npc.verb(raise)] [npc.her] [npc.hand], before swiping down and delivering a sharp slap to [npc2.her] face."));
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" A clearly aroused yelp escapes from [npc2.namePos] mouth at the moment of contact, letting [npc.name] know that [npc2.sheIs] deriving masochistic pleasure from being abused in such a fashion.",
							" The horny squeal that escapes [npc2.namePos] mouth is enough to let anyone realise that [npc2.sheIs] getting turned on from being treated in such a degrading manner.",
							" Instead of a painful cry, [npc2.name] [npc2.verb(let)] out a horny moan, letting [npc.name] know that [npc2.sheIs] a masochist who's getting turned on by being abused like this."));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								" A protesting yelp escapes from [npc2.namePos] mouth at the moment of contact,"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" which is precisely the reaction you were looking for."
												:" and from the wicked grin that settles on [npc.namePos] face, this was just the reaction [npc.she] [npc.was] looking for."),
								" The pained squeal which immediately escapes from [npc2.namePos] mouth is exactly what [npc.name] [npc.was] looking for, and [npc.she] can't help but grin as [npc.she] [npc.verb(see)] tears well up in"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" your [npc2.eyes+]."
												:" [npc.her] submissive bitch's [npc2.eyes+]."),
								" After letting out a shocked cry, tears start to well up in [npc2.namePos] [npc2.eyes], letting [npc.name] know that [npc.her] abuse is having the exact effect [npc.she] [npc.was] looking for."));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								" A protesting shout escapes from [npc2.namePos] mouth at the moment of contact,"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" which is precisely the reaction you were looking for."
												:" and from the wicked grin that settles on [npc.namePos] face, this was just the reaction [npc.she] [npc.was] looking for."),
								" The pained exclamation which immediately escapes from [npc2.namePos] mouth is exactly what [npc.name] [npc.was] looking for, and [npc.she] can't help but grin as [npc.she] [npc.verb(see)] the shocked look in"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" your [npc2.eyes+]."
												:" [npc.her] submissive bitch's [npc2.eyes+]."),
								" [npc2.Name] immediately [npc2.verb(let)] out a shocked cry, revealing to [npc.name] that [npc.her] abuse is having the exact effect [npc.she] [npc.was] looking for."));	
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction SPIT_FACE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			Map<InventorySlot, List<AbstractClothing>> concealedMap = Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction());
			if(concealedMap.containsKey(InventorySlot.MOUTH) && concealedMap.containsKey(InventorySlot.EYES)) {
				return false; // If mouth and eyes are concealed, treat face as being concealed and so unavailable
			}
			
			boolean mouthTongue = false;
			boolean mouthTongueReversed = false;
			try {
				mouthTongue = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.TONGUE).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthTongueReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.TONGUE);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthTongue || mouthTongueReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		
		@Override
		public String getActionTitle() {
			return "Spit in face";
		}

		@Override
		public String getActionDescription() {
			return "Spit in [npc2.namePos] face show [npc2.herHim] that [npc2.sheIs] worthless.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Wanting to show [npc2.name] that [npc2.sheIs] a worthless bitch, [npc.name] [npc.verb(lean)] in towards [npc2.herHim], before spitting on [npc2.her] face.",
					"Leaning in towards [npc2.name], [npc.name] [npc.verb(purse)] [npc.her] [npc.lips+], before spitting directly on [npc2.her] face.",
					"Seeking to remind [npc2.name] of how worthless [npc2.sheIsFull], [npc.name] [npc.verb(lean)] in towards [npc2.herHim], before pursing [npc.her] [npc.lips+] and spitting directly on [npc2.her] face."));

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" A clearly aroused [npc2.moan] escapes from [npc2.namePos] mouth as the ball of saliva splatters onto [npc2.her] cheek, letting [npc.name] know that [npc2.sheIs] deriving masochistic pleasure from being abused in such a fashion.",
							" The horny squeal that escapes [npc2.namePos] mouth is enough to let anyone realise that [npc2.sheIs] getting turned on from being treated in such a degrading manner.",
							" Instead of a disgusted cry, [npc2.name] [npc2.verb(let)] out a horny [npc2.moan], letting [npc.name] know that [npc2.sheIs] a masochist who's getting turned on by being abused like this."));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								" A disgusted cry escapes from [npc2.namePos] mouth as the ball of saliva splatters onto [npc2.her] cheek,"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" which is precisely the reaction you were looking for."
												:" and from the wicked grin that settles on [npc.namePos] face, this was just the reaction [npc.she] [npc.was] looking for."),
								" The horrified protestations which [npc2.name] immediately [npc2.verb(make)] is exactly what [npc.name] [npc.was] looking for, and [npc.she] can't help but grin as [npc.she] [npc.verb(see)] tears well up in"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" your [npc2.eyes+]."
												:" [npc.her] submissive bitch's [npc2.eyes+]."),
								" After letting out a shocked cry, tears start to well up in [npc2.namePos] [npc2.eyes], letting [npc.name] know that [npc.her] abuse is having the exact effect [npc.she] [npc.was] looking for."));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								" A disgusted cry escapes from [npc2.namePos] mouth as the ball of saliva splatters onto [npc2.her] cheek,"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" which is precisely the reaction you were looking for."
												:" and from the wicked grin that settles on [npc.namePos] face, this was just the reaction [npc.she] [npc.was] looking for."),
								" The horrified protestations which [npc2.name] immediately [npc2.verb(make)] is exactly what [npc.name] [npc.was] looking for, and [npc.she] can't help but grin as [npc.she] [npc.verb(see)] the shocked look in"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" your [npc2.eyes+]."
												:" [npc.her] submissive bitch's [npc2.eyes+]."),
								" [npc2.Name] immediately [npc2.verb(let)] out a shocked cry, revealing to [npc.name] that [npc.her] abuse is having the exact effect [npc.she] [npc.was] looking for."));	
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction CHOKE = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}

			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthFinger || mouthFingerReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
		}
		
		@Override
		public String getActionTitle() {
			return "Choke";
		}

		@Override
		public String getActionDescription() {
			return "Grab and squeeze [npc2.namePos] neck in order to choke [npc2.herHim].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterPerformingAction())) {
				sb.append(UtilText.returnStringAtRandom(
						"Continuing to ram [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] up to grab [npc2.her] neck, before squeezing down and choking [npc2.herHim].",
						"[npc.Name] [npc.verb(reach)] up to grab [npc2.namePos] neck, and, while still forcefully pounding [npc2.her] [npc2.pussy+], [npc.she] roughly [npc.verb(squeeze)] down in order to choke [npc2.herHim].",
						"Slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] up and [npc.verb(grab)] [npc2.her] neck,"
								+ " squeezing down and choking [npc2.herHim] as [npc.she] [npc.verb(pull)] back and [npc.verb(continue)] roughly fucking [npc2.herHim]."));
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterPerformingAction())) {
				sb.append(UtilText.returnStringAtRandom(
						"Continuing to ram [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] up to grab [npc2.her] neck, before squeezing down and choking [npc2.herHim].",
						"[npc.Name] [npc.verb(reach)] up to grab [npc2.namePos] neck, and, while still forcefully pounding [npc2.her] [npc2.asshole+], [npc.she] roughly [npc.verb(squeeze)] down in order to choke [npc2.herHim].",
						"Slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] up and [npc.verb(grab)] [npc2.her] neck,"
								+ " squeezing down and choking [npc2.herHim] as [npc.she] [npc.verb(pull)] back and [npc.verb(continue)] roughly fucking [npc2.herHim]."));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"With a sadistic grin on [npc.her] face, [npc.name] [npc.verb(reach)] up to grab [npc2.name] by [npc2.her] neck, before squeezing down and choking [npc2.herHim].",
						"[npc.Name] [npc.verb(reach)] up to grab [npc2.namePos] neck, and, letting out a sadistic laugh, [npc.she] roughly [npc.verb(squeeze)] down in order to choke [npc2.herHim].",
						"With a cruel laugh, [npc.name] [npc.verb(reach)] up and [npc.verb(grab)] [npc2.name] by [npc2.her] neck, before sadistically squeezing down and choking [npc2.herHim]."));
			}

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" The gargled spluttering sounds which [npc2.name] [npc2.verb(start)] to make in response have a distinctly lewd quality to them, letting [npc.name] know that [npc2.sheIs] being turned on by this abuse.",
							" The horny, gargled [npc2.moans] which [npc2.name] immediately [npc2.verb(start)] to produce are more than enough to let anyone realise that [npc2.sheIs] a masochist, and is getting aroused by this poor treatment.",
							" The spluttering gasps which [npc2.name] [npc2.verb(begin)] to emit are intermingled with several horny [npc2.moans], letting [npc.name] know that [npc2.sheIs] deriving masochistic pleasure from being abused in such a fashion."));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								" A series of gargled spluttering noises escape from [npc2.namePos] mouth,"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" which is precisely the reaction you were looking for."
												:" and from the wicked grin that settles on [npc.namePos] face, this was just the reaction [npc.she] [npc.was] looking for."),
								" The distressed, gargled cries which [npc2.name] [npc2.verb(start)] to make are exactly what [npc.name] [npc.was] looking for, and [npc.she] can't help but grin as [npc.she] [npc.verb(see)] tears well up in"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" your [npc2.eyes+]."
												:" [npc.her] submissive bitch's [npc2.eyes+]."),
								" Letting out a series of spluttering gasps, tears quickly start to well up in [npc2.namePos] [npc2.eyes], letting [npc.name] know that [npc.her] abuse is having the exact effect [npc.she] [npc.was] looking for."));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								" A series of gargled spluttering noises escape from [npc2.namePos] mouth,"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" which is precisely the reaction you were looking for."
												:" and from the wicked grin that settles on [npc.namePos] face, this was just the reaction [npc.she] [npc.was] looking for."),
								" The distressed, gargled cries which [npc2.name] immediately [npc2.verb(make)] is exactly what [npc.name] [npc.was] looking for, and [npc.she] can't help but grin as [npc.she] [npc.verb(see)] the shocked look in"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" your [npc2.eyes+]."
												:" [npc.her] submissive bitch's [npc2.eyes+]."),
								" [npc2.Name] immediately [npc2.verb(let)] out a spluttering gasp, revealing to [npc.name] that [npc.her] abuse is having the exact effect [npc.she] [npc.was] looking for."));	
					}
				}
			}
			
			return sb.toString();
		}
	};
	
}
