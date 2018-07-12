package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.8
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
			
			if(Sex.getCharacterPerformingAction().hasPenis()) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As [npc.she] [npc.verb(grind)] [npc.her] [npc.pussy+] down against [npc2.namePos] [npc2.lips], [npc.name] [npc.verb(feel)] [npc.her] other sexual organ start to react to [npc.her] climax.");
				
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					UtilText.nodeContentSB.append(" [npc.She] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] the knot at the base of [npc.her] [npc.cock+] swelling up as [npc.she] [npc.verb(prepare)] to cum,");
					
				} else if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.FLARED)) {
					UtilText.nodeContentSB.append(" [npc.She] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] the wide, flared head of [npc.her] [npc.cock+] swelling up as [npc.she] [npc.verb(prepare)] to cum,");
					
				} else {
					UtilText.nodeContentSB.append(" [npc.She] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] [npc.her] [npc.cock+] twitch and throb as [npc.she] [npc.verb(prepare)] to cum,");
				}
		
				// Describe cum amount:
				UtilText.nodeContentSB.append(" and as [npc.her] [npc.balls+] tense up");
				
				switch (Sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", [npc.she] [npc.verb(realise)] that [npc.sheIs] not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", a small trickle of [npc.cum+] squirts out onto the floor above [npc2.namePos] head.");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", a small amount of [npc.cum+] squirts out onto the floor above [npc2.namePos] head.");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] squirts out onto the floor above [npc2.namePos] head.");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] shoots out onto the floor above [npc2.namePos] head.");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out onto the floor above [npc2.namePos] head.");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out onto the floor above [npc2.namePos] head.");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out onto the floor above [npc2.namePos] head.");
						break;
					default:
						break;
				}
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "After a few moments of grinding down on [npc2.namePos] face, [npc.namePos] overwhelming orgasm starts to fade, and [npc.she] [npc.verb(stand)] up on shaky [npc.legs],"
							+ " grinning down at [npc.her] partner as [npc.she] [npc.verb(feel)] a slick stream of saliva and [npc.girlCum] drooling down from [npc.her] [npc.pussy+].");
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	public static final SexAction ORGASM_COCK_DEEPTHROAT = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Deep throat";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.cock+] deep down [npc2.namePos] throat and fill [npc2.herHim] with [npc2.cum+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getCharacterPerformingAction().getPenisRawSizeValue() >= PenisSize.TWO_AVERAGE.getMedianValue()
					&& Sex.getCharacterPerformingAction().hasPenisIgnoreDildo();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.Name] [npc.verb(feel)] a desperate heat building in [npc.her] groin, and with a lewd cry, [npc.she] [npc.verb(slam)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat.");
			
			if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				UtilText.nodeContentSB.append(" [npc.She] [npc.verb(force)] the knot at the base of [npc.her] [npc.cock] past [npc2.her] [npc2.lips+],"
						+ " and [npc2.name] [npc.verb(shuffle)] around on [npc2.her] knees as it quickly swells up, locking you to one another.");
				
			} else if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.FLARED)) {
				UtilText.nodeContentSB.append(" [npc.She] [npc.verb(feel)] it start to twitch and throb,"
						+ " and [npc.she] [npc.verb(reach)] down to hold [npc2.her] head in position as [npc.her] wide, flared head swells up in [npc2.her] throat.");
				
			} else if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.BARBED)) {
				UtilText.nodeContentSB.append(" [npc.She] [npc.verb(feel)] it start to twitch and throb,"
						+ " and [npc.she] start making little thrusting motions into [npc2.her] [npc2.face], raking [npc.her] barbs up and down [npc2.her] throat as [npc.she] [npc.verb(bring)] [npc.herself] to a climax.");
				
			} else {
				UtilText.nodeContentSB.append(" [npc.She] [npc.verb(feel)] it start to twitch and throb, and [npc.she] [npc.verb(reach)] down to hold [npc2.her] head in position as [npc.she] [npc.verb(prepare)] for [npc.her] climax.");
			}

			UtilText.nodeContentSB.append(" [npc.Her] [npc.balls+] tense up, and as [npc.she] let out [npc.a_moan+]");
			switch (Sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", [npc.she] [npc.verb(realise)] that [npc.sheIs] not able to produce even one drop of cum.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", [npc.she] [npc.verb(feel)] a small trickle of [npc.cum+] squirting down [npc2.namePos] throat into [npc2.her] stomach.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", [npc.she] [npc.verb(feel)] a small amount of [npc.cum+] squirting down [npc2.namePos] throat into [npc2.her] stomach.");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", [npc.she] [npc.verb(feel)] [npc.her] [npc.cum+] squirting down [npc2.namePos] throat into [npc2.her] stomach.");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", [npc.she] [npc.verb(feel)] [npc.her] [npc.cum+] shooting down [npc2.namePos] throat into [npc2.her] stomach.");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", [npc.she] [npc.verb(feel)] [npc.her] [npc.cum+] pouring down [npc2.namePos] throat into [npc2.her] stomach.");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", [npc.she] [npc.verb(feel)] [npc.her] [npc.cum+] pouring down [npc2.namePos] throat into [npc2.her] stomach.");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", [npc.she] [npc.verb(feel)] [npc.her] [npc.cum+] pouring down [npc2.namePos] throat into [npc2.her] stomach.");
					break;
				default:
					break;
			}
			
			if(Sex.getCharacterPerformingAction().hasVagina()){
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "A desperate, shuddering heat suddenly crashes up from [npc.her] neglected feminine sex, and [npc.she] [npc.verb(let)] out a manic scream as a blinding wave of pure ecstasy washes over [npc.herHim].");
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "After a few moments, [npc.her] [npc.balls+] have completely emptied themselves,"
						+ " and [npc.she] [npc.verb(take)] a moment to catch [npc.her] breath, grinning down at [npc2.name] as [npc.she] [npc.verb(keep)] [npc.her] [npc.cock+] hilted down [npc2.her] throat.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<SexAreaOrifice> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(SexAreaOrifice.MOUTH);
			} else {
				return null;
			}
		}
		
	};
	
}
