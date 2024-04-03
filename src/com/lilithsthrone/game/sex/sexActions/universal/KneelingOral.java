package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.4.2
 * @author Innoxia
 */
public class KneelingOral {
	
	public static final SexAction ORGASM_THIGH_SQUEEZE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)
					&& Main.sex.getCharacterPerformingAction().hasLegs()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			return super.getPriority();
		}
		
		@Override
		public String getActionTitle() {
			return "Thigh squeeze";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze your thighs around [npc2.namePos] head and collapse down onto [npc2.her] face as you orgasm.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"[npc.Name] [npc.verb(feel)] an overwhelming wave of burning arousal start to build up deep within [npc.her] groin, and with [npc.a_moan+],"
							+ " [npc.she] [npc.verb(clamp)] [npc.her] thighs down hard around [npc2.namePos] head."
					+ " [npc.Her] [npc.legs] start to shake and give out from under [npc.herHim], and with another [npc.a_moan+], [npc.she] [npc.verb(start)] collapsing forwards."
					+ " [npc2.NameIsFull] quickly slammed to the floor, and suddenly [npc2.verb(find)] [npc.name] sitting on [npc2.her] face,"
						+ " screaming in ecstasy as [npc.her] [npc.pussy+] spasms and clenches down around the [npc2.tongue+] that's being forced deep into [npc.her] soft folds.");
			
			if(Main.sex.getCharacterPerformingAction().hasPenis()) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ GenericOrgasms.getGenericPenisOrgasmDescription(
							this,
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this),
							OrgasmCumTarget.HAIR,
							this.getCondomFailure(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)),
							false));
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "After a few moments of grinding down on [npc2.namePos] face, [npc.namePos] overwhelming orgasm starts to fade, and [npc.she] [npc.verb(stand)] up on shaky [npc.legs],"
							+ " grinning down at [npc2.name] as [npc.she] [npc.verb(feel)] a slick stream of saliva and [npc.girlCum] drooling down from [npc.her] [npc.pussy+].");
			
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false, UtilText.nodeContentSB.toString()).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), true).applyEffects();
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false).isEndsSex();
		}
	};
	
}
