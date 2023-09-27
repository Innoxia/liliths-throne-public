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
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL) && Main.sex.getCharacterPerformingAction().hasLegs();
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
//				UtilText.nodeContentSB.append("<br/><br/>"
//						+ "As [npc.she] [npc.verb(grind)] [npc.her] [npc.pussy+] down against [npc2.namePos] [npc2.lips], [npc.name] [npc.verb(feel)] [npc.her] other sexual organ start to react to [npc.her] climax.");
//				
//				if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
//					UtilText.nodeContentSB.append(" [npc.She] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] the knot at the base of [npc.her] [npc.cock+] swelling up as [npc.she] [npc.verb(prepare)] to cum,");
//					
//				} else if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.FLARED)) {
//					UtilText.nodeContentSB.append(" [npc.She] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] the wide, flared head of [npc.her] [npc.cock+] swelling up as [npc.she] [npc.verb(prepare)] to cum,");
//					
//				} else {
//					UtilText.nodeContentSB.append(" [npc.She] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] [npc.her] [npc.cock+] twitch and throb as [npc.she] [npc.verb(prepare)] to cum,");
//				}
//		
//				// Describe cum amount:
//				UtilText.nodeContentSB.append(" and as [npc.her] [npc.balls+] tense up");
//				
//				switch (Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()) {
//					case ZERO_NONE:
//						UtilText.nodeContentSB.append(", [npc.she] [npc.verb(realise)] that [npc.sheIs] not able to produce even one drop of cum.");
//						break;
//					case ONE_TRICKLE:
//						UtilText.nodeContentSB.append(", a small trickle of [npc.cum+] squirts out onto the floor above [npc2.namePos] head.");
//						break;
//					case TWO_SMALL_AMOUNT:
//						UtilText.nodeContentSB.append(", a small amount of [npc.cum+] squirts out onto the floor above [npc2.namePos] head.");
//						break;
//					case THREE_AVERAGE:
//						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] squirts out onto the floor above [npc2.namePos] head.");
//						break;
//					case FOUR_LARGE:
//						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] shoots out onto the floor above [npc2.namePos] head.");
//						break;
//					case FIVE_HUGE:
//						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out onto the floor above [npc2.namePos] head.");
//						break;
//					case SIX_EXTREME:
//						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out onto the floor above [npc2.namePos] head.");
//						break;
//					case SEVEN_MONSTROUS:
//						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out onto the floor above [npc2.namePos] head.");
//						break;
//					default:
//						break;
//				}
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
