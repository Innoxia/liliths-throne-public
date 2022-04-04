package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
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
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.3.2
 * @version 0.4.3.2
 * @author Innoxia
 */
public class LovingActions {
	
	public static final SexAction CARESS_CHEEK = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			Map<InventorySlot, List<AbstractClothing>> concealedMap = Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction());
			if(concealedMap.containsKey(InventorySlot.MOUTH) && concealedMap.containsKey(InventorySlot.EYES)) {
				return false; // If mouth and eyes are concealed, treat face as being concealed and so unavailable
			}
			
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
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (Main.sex.getCharacterPerformingAction().getAffectionLevel(Main.sex.getCharacterTargetedForSexAction(this)).isGreaterThan(AffectionLevel.POSITIVE_TWO_LIKE)
									&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST)));
		}
		
		@Override
		public String getActionTitle() {
			return "Caress face";
		}

		@Override
		public String getActionDescription() {
			return "Gently caress [npc2.namePos] face to make [npc2.herHim] feel loved.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Wanting to make [npc2.name] feel loved, [npc.name] [npc.verb(lift)] [npc.her] [npc.hand], before gently stroking [npc.her] [npc.fingers+] over [npc2.her] face.",
					"Lifting [npc.her] [npc.hand], [npc.name] softly [npc.verb(trace)] [npc.her] [npc.fingers+] over [npc2.namePos] cheek in order to make [npc2.herHim] feel loved.",
					"Seeking to put [npc2.name] at ease, [npc.name] [npc.verb(raise)] [npc.her] [npc.hand], before using [npc.her] [npc.fingers+] to lovingly caress [npc2.her] face."));
			
			if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						" A happy [npc2.moan] escapes from [npc2.namePos] mouth in response to this contact, causing [npc.name] to smile in delight.",
						" The happy sigh which escapes [npc2.namePos] mouth is more than enough to let [npc.name] know that [npc.her] gentle caress has had the desired effect.",
						" In response to this, [npc2.name] [npc2.verb(let)] out a happy [npc2.moan], letting [npc.name] know that [npc.her] gesture is much appreciated."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						" A protesting yelp escapes from [npc2.namePos] mouth as [npc2.she] [npc2.verb(continue)] to resist [npc.namePos] advances.",
						" The frustrated exclamation which immediately escapes from [npc2.namePos] mouth is not what [npc.name] [npc.was] looking for, and [npc.she] can't help but sigh as [npc2.name] [npc2.verb(continue)] to resist.",
						" After letting out a shocked cry, tears start to well up in [npc2.namePos] [npc2.eyes], letting [npc.name] know that [npc.her] advances are not having the effect which [npc.she] [npc.was] looking for."));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction KISS_CHEEK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public boolean isLovingAction() {
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
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (Main.sex.getCharacterPerformingAction().getAffectionLevel(Main.sex.getCharacterTargetedForSexAction(this)).isGreaterThan(AffectionLevel.POSITIVE_TWO_LIKE)
									&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST)));
		}
		
		@Override
		public String getActionTitle() {
			return "Kiss cheek";
		}

		@Override
		public String getActionDescription() {
			return "Plant a loving kiss on [npc2.namePos] cheek.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Wanting to show [npc2.name] that [npc2.sheIs] loved, [npc.name] [npc.verb(lean)] in towards [npc2.herHim], before planting a gentle kiss on [npc2.her] cheek.",
					"Leaning in towards [npc2.name], [npc.name] [npc.verb(purse)] [npc.her] [npc.lips+], before planting a loving kiss on [npc2.her] cheek.",
					"Seeking to remind [npc2.name] of how much [npc2.sheIsFull] loved, [npc.name] [npc.verb(lean)] in towards [npc2.herHim], before pursing [npc.her] [npc.lips+] and kissing [npc2.her] cheek."));
			
			if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						" A happy [npc2.moan] escapes from [npc2.namePos] mouth in response to this contact, causing [npc.name] to smile in delight.",
						" The happy sigh which escapes [npc2.namePos] mouth is more than enough to let [npc.name] know that [npc.her] kiss has had the desired effect.",
						" In response to this, [npc2.name] [npc2.verb(let)] out a happy [npc2.moan], letting [npc.name] know that [npc.her] gesture is much appreciated."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						" A protesting yelp escapes from [npc2.namePos] mouth as [npc2.she] [npc2.verb(continue)] to resist [npc.namePos] advances.",
						" The frustrated exclamation which immediately escapes from [npc2.namePos] mouth is not what [npc.name] [npc.was] looking for, and [npc.she] can't help but sigh as [npc2.name] [npc2.verb(continue)] to resist.",
						" After letting out a shocked cry, tears start to well up in [npc2.namePos] [npc2.eyes], letting [npc.name] know that [npc.her] advances are not having the effect which [npc.she] [npc.was] looking for."));
			}
			
			return sb.toString();
		}
	};
	
}
