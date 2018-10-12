package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Lionna
 */
public class OverTheKnee
{
	public static final SexAction SPANKING_LIGHT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL)
	{
		@Override
		public boolean isBaseRequirementsMet()
		{
			return Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.OVER_THE_KNEE_SITTING;
		}

		@Override
		public String getActionTitle()
		{
			return "Spank lightly";
		}

		@Override
		public String getActionDescription()
		{
			return "Lightly spank [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public String getDescription()
		{
			if (!Sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.SPANKED_NORMAL) && !Sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.SPANKED_ROUGH))
			{
				Sex.getCharacterTargetedForSexAction(this).addStatusEffect(StatusEffect.SPANKED_LIGHT, (60 * 60));
			}
			return UtilText.returnStringAtRandom
			(
						"[npc1.NamePos] [npc1.hand] slowly circles [npc2.namePos] [npc2.ass+], drawing a [npc2.moan] from [npc2.namePos]"
					+ 	" [npc2.lips] as each smack warms its surface.",

						"[npc2.Name] [npc2.verb(let)] out a [npc2.moan+] as [npc1.namePos] [npc1.hand] finds it's mark on [npc2.her] [npc2.ass+],"
					+	 " the stinging sensation from the first still lingering as another lands on [npc2.her] other cheek. This repetive switching"
					+	 " drags on until finally [npc1.name] [npc1.verb(grabs)] one of [npc1.namePos] cheeks, jiggling it up and down before giving"
					+  " it a parting slap.",

						"[npc2.NamePos] [npc2.hips] wiggle in the air as they adjust to a more comfortable position in [npc1.namePos] lap."
					+ 	" Turned on by [npc2.namePos] unintentionally erotic movements, some additional stimulation from [npc1.her] [npc1.hand]"
					+ 	" encourages [npc2.her] dance to continue. Each spank renewing [npc2.her] flailing before a climatic wap leaves [npc2.namePos]"
					+ 	" [npc2.legs] bent and hugged to [npc2.her] behind, reflexively trying to protect [npc2.her] abused [npc2.ass] from futher harm."
			);
		}

	};

	public static final SexAction SPANKING_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL)
	{
		@Override
		public boolean isBaseRequirementsMet()
		{
			return Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.OVER_THE_KNEE_SITTING
				&&(Sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.SPANKED_LIGHT)
				|| Sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.SPANKED_NORMAL)
				|| Sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.SPANKED_ROUGH));
		}

		@Override
		public String getActionTitle()
		{
			return "Spank firmly";
		}

		@Override
		public String getActionDescription()
		{
			return "Firmly spank [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public String getDescription()
		{
			if (!Sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.SPANKED_ROUGH))
			{
				Sex.getCharacterTargetedForSexAction(this).removeStatusEffect(StatusEffect.SPANKED_LIGHT);
				Sex.getCharacterTargetedForSexAction(this).addStatusEffect(StatusEffect.SPANKED_NORMAL, (60 * 60));
			}
			return UtilText.returnStringAtRandom
			(
						"[npc1.NamePos] [npc1.hand+] administers a heavy slap on [npc2.namePos] [npc2.ass+], sending shockwaves throughout"
					+	  " [npc2.her] body.",

						"The air is filled with the sound of music as [npc1.namePos] [npc1.hand] plays [npc2.namePos] [npc2.ass] like a drum,"
					+ 	" [npc2.her] [npc2.moans] dutifully keeping in line with [npc2.her] percussion section's steady beat.",

						"Abruptly stopping in the middle of a blow to [npc2.namePos] [npc2.ass+], [npc1.namePos] [npc1.eyes] wander down to [npc2.her] groin."
					+ 	" [npc1.speech(Are you getting turned on by this?)] Failing to suppress a [npc2.moan] as [npc1.namePos] [npc1.hand] taps [npc2.her]"
					+   " crotch, [npc2.namePos] [npc2.face] turns scarlet. [npc1.speech(What a little pervert,)] [npc1.name] [npc1.verb(laugh)] to [npc1.herHim]self"
					+   " as [npc1.her] [npc1.hand] gets back to working on [npc2.namePos] [npc2.ass]."
			);
		}
	};

	public static final SexAction SPANKING_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL)
	{
		@Override
		public boolean isBaseRequirementsMet()
		{
			return Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.OVER_THE_KNEE_SITTING
				&&(Sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.SPANKED_NORMAL)
				|| Sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.SPANKED_ROUGH));
		}

		@Override
		public String getActionTitle()
		{
			return "Spank roughly";
		}

		@Override
		public String getActionDescription()
		{
			return "Roughly spank [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public String getDescription()
		{
			Sex.getCharacterTargetedForSexAction(this).removeStatusEffect(StatusEffect.SPANKED_LIGHT);
			Sex.getCharacterTargetedForSexAction(this).removeStatusEffect(StatusEffect.SPANKED_NORMAL);
			Sex.getCharacterTargetedForSexAction(this).addStatusEffect(StatusEffect.SPANKED_ROUGH, (60 * 60));

			return UtilText.returnStringAtRandom
					(
						"[npc1.NamePos] vigorous spanking of [npc2.namePos] [npc2.ass+] fills [npc2.her] [npc2.eyes] with tears. Despite [npc2.her] effort"
					+ 	" to suppress [npc2.her] emotions, the next blow from [npc1.namePos] [npc1.hand] proves too much for [npc2.herHim]. Snot and tears"
					+ 	" burst from [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(let)] out [npc2.a_moan+] in pain.",

						"Silence follows the sounds of flesh on flesh as [npc1.namePos] assault on [npc2.namePos] [npc2.ass] begins to lose steam."
					+ 	" [npc1.NamePos] eager efforts require [npc1.herHim] to pause, taking the time to wipe the sweat from [npc1.her] brow. During"
					+ 	" this lull in activity, the stinging sensation in [npc2.namePos] [npc2.ass] overwhelms [npc2.herHim], drawing a [npc2.moan]"
					+ 	" to escape from [npc2.her] [npc2.lips].",

						" [npc2.Name] can only turn [npc2.her] head before a burning sensation engulfs [npc2.her] [npc2.ass], the last thing"
					+ 	" [npc2.she] can see as tears envelop [npc2.her] [npc2.eyes] is [npc1.namePos] fiery [npc1.hand] coming for another blow."
					);
		}
	};
}
