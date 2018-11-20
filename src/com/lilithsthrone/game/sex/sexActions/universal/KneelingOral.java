package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.11
 * @author Innoxia
 */
public class KneelingOral {

	
	public static final SexAction TWINTAIL_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Twintail pull";
			} else {
				return "Twin braids pull";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Grab one of [npc2.namePos] twintails in each [npc.hand] and pull [npc2.her] forwards onto your [npc.cock+].";
			} else {
				return "Grab one of [npc2.namePos] twin braids in each [npc.hand] and pull [npc2.her] forwards onto your [npc.cock+].";
			}
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& (Sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS
							|| Sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_BRAIDS)
					&& (Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_RECEIVING_ORAL
						|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_RECEIVING_ORAL_TWO
						|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_RECEIVING_ORAL_THREE
						|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND
						|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_TWO
						|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_RECEIVING_ORAL_SECOND_THREE);
		}

		@Override
		public String getDescription() {
			
			String style = Sex.getCharacterTargetedForSexAction(this).getHairStyle().getName();
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of one of [npc2.namePos] "+style+" in each [npc.hand], [npc.name] gently [npc.verb(pull)] [npc2.her] forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
							"Reaching down to take a gentle, but firm, grip on each of [npc2.namePos] "+style+", [npc.name] slowly [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] "+style+","
									+ " before gently pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grasping [npc2.namePos] "+style+" in each [npc.hand], [npc.name] violently [npc.verb(jerk)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
							"Reaching down to grab [npc2.namePos] "+style+" in each fist, [npc.name] mercilessly [npc.verb(yank)] [npc2.her] head into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] "+style+","
									+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of one of [npc2.namePos] "+style+" in each [npc.hand], [npc.name] firmly [npc.verb(pull)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
							"Reaching down to take a firm grip on each of [npc2.namePos] "+style+", [npc.name] steadily [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] "+style+","
									+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
									+(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Looking up with big, teary [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment gently caressing the underside of [npc.namePos] [npc.cock] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" Gently humming and shuffling forwards on [npc2.her] knees, [npc2.name] [npc2.verb(reach)] up to softly caress and play with [npc.namePos] [npc.balls+],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.cock+] is eventually pulled free from [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Narrowing [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] up with [npc.namePos] daring move for just a moment,"
									+ " before jerking [npc2.her] head back and pointedly reminding [npc.name] who's in charge.",
							" The rumbling vibrations of [npc2.namePos] threatening growls, while serving to provide some extra pleasure,"
									+ " nonetheless intimidate [npc.name] into quickly letting go of [npc.her] [npc.hair] and sliding [npc.her] [npc.cock+] free from [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Scrunching [npc2.her] [npc2.eyes] shut, [npc2.name] [npc2.verb(beat)] against [npc.her] tormentor's thighs in a futile gesture of resistance,"
									+ " before finally being able to cry and gasp for air as [npc.name] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" The vibrations of [npc2.namePos] muffled cries and sobs only serve to provide extra pleasure to [npc2.her] tormentor,"
									+ " but, after spending several seconds punching and pushing against [npc.namePos] thighs,"
									+ " [npc2.she] finally [npc2.verb(achieve)] a small victory as [npc2.her] [npc2.hair] is released and [npc.namePos] [npc.cock+] is momentarily slid free from [npc2.her] throat."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Looking up with big, teary [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment caressing the underside of [npc.namePos] [npc.cock] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" Happily humming and shuffling forwards on [npc2.her] knees, [npc2.name] [npc2.verb(reach)] up to lovingly caress and play with [npc.namePos] [npc.balls+],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.cock+] is eventually pulled free from [npc2.her] throat."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
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
							+ " grinning down at [npc2.name] as [npc.she] [npc.verb(feel)] a slick stream of saliva and [npc.girlCum] drooling down from [npc.her] [npc.pussy+].");
			
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
						+ " and [npc2.name] [npc2.verb(shuffle)] around on [npc2.her] knees as it quickly swells up, locking you to one another.");
				
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
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(SexAreaOrifice.MOUTH);
			} else {
				return null;
			}
		}
		
	};
	
}
