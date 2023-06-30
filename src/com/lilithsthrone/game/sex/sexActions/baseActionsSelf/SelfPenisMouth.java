package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.8.5
 * @author Innoxia
 */
public class SelfPenisMouth {
	
	public static final SexAction SELF_BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isTaur()
					&& Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY);
		}
		@Override
		public String getActionTitle() {
			return "Start autofellatio";
		}
		@Override
		public String getActionDescription() {
			return "Bend over and take your [npc.cock+] into your mouth, before starting to give yourself a blowjob.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"In a display of impressive flexibility, [npc.name] [npc.verb(bend)] down and ",
					"Showing off how flexibile [npc.sheIsFull], [npc.name] [npc.verb(double)] over and ",
					"Putting [npc.her] flexibility to the test, [npc.name] [npc.verb(bend)] down and "));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(bring)] [npc.her] [npc.lips+] to the [npc.cockHead+] of [npc.her] [npc.cock]."
									+ " Slowly taking [npc.her] [npc.cock] into [npc.her] mouth, [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc.herself] a blowjob.",

							"[npc.verb(wrap)] [npc.her] [npc.lips+] around the [npc.cockHead+] of [npc.her] [npc.cock]."
									+ " Letting out a muffled [npc.moan], [npc.she] [npc.verb(start)] giving [npc.herself] a gentle blowjob."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(bring)] [npc.her] [npc.lips+] to the [npc.cockHead+] of [npc.her] [npc.cock]."
									+ " Eagerly taking [npc.her] [npc.cock] into [npc.her] mouth, [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] happily [npc.verb(start)] giving [npc.herself] a blowjob.",

							"[npc.verb(wrap)] [npc.her] [npc.lips+] around the [npc.cockHead+] of [npc.her] [npc.cock]."
									+ " Letting out a muffled [npc.moan], [npc.she] eagerly [npc.verb(start)] giving [npc.herself] an enthusiastic blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(bring)] [npc.her] [npc.lips+] to the [npc.cockHead+] of [npc.her] [npc.cock]."
									+ " Hungrily taking [npc.her] [npc.cock] into [npc.her] mouth, [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc.herself] a blowjob.",

							"[npc.verb(wrap)] [npc.her] [npc.lips+] around the [npc.cockHead+] of [npc.her] [npc.cock]."
									+ " Letting out a muffled [npc.moan], [npc.she] greedily [npc.verb(start)] giving [npc.herself] a rough blowjob."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(bring)] [npc.her] [npc.lips+] to the [npc.cockHead+] of [npc.her] [npc.cock]."
									+ " Taking [npc.her] [npc.cock] into [npc.her] mouth, [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc.herself] a blowjob.",

							"[npc.verb(wrap)] [npc.her] [npc.lips+] around the [npc.cockHead+] of [npc.her] [npc.cock]."
									+ " Letting out a muffled [npc.moan], [npc.she] [npc.verb(start)] giving [npc.herself] a blowjob."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
	};
	
	// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
	
	public static final SexAction SELF_BLOWJOB_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Deep throat";
		}
		@Override
		public String getActionDescription() {
			return "Take your [npc.cock+] as deep as possible down your throat.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently wrapping [npc.her] [npc.lips+] around [npc.her] own [npc.cock], [npc.name] [npc.verb(push)] [npc.her] head forwards,"
									+ " taking it as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a soft, muffled [npc.moan], [npc.name] gently [npc.verb(bend)] down as far as [npc.she] can,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] as much of [npc.her] [npc.cock+] down [npc.her] throat as possible.",

							"Slowly sliding [npc.her] head forwards, [npc.name] gently [npc.verb(part)] [npc.her] [npc.lips+], before taking [npc.her] [npc.cock+] deep down [npc.her] throat."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly wrapping [npc.her] [npc.lips+] around [npc.her] own [npc.cock], [npc.name] quickly [npc.verb(push)] [npc.her] head forwards,"
									+ " greedily taking it as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a muffled, [npc.moan+], [npc.name] eagerly [npc.verb(bend)] down as far as [npc.she] can,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] desperately [npc.verb(take)] as much of [npc.her] [npc.cock+] down [npc.her] throat as possible.",

							"Greedily sliding [npc.her] head forwards, [npc.name] readily [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] [npc.her] [npc.cock+] deep down [npc.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully wrapping [npc.her] [npc.lips+] around [npc.her] own [npc.cock], [npc.name] roughly [npc.verb(slam)] [npc.her] head forwards,"
									+ " forcing it as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(bend)] down as far as [npc.she] can,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] roughly [npc.verb(force)] as much of [npc.her] [npc.cock+] down [npc.her] throat as possible.",

							"Aggressively pushing [npc.her] head forwards, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(force)] [npc.her] [npc.cock+] deep down [npc.her] throat."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Wrapping [npc.her] [npc.lips+] around [npc.her] own [npc.cock], [npc.name] quickly [npc.verb(push)] [npc.her] head forwards,"
									+ " taking it as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a muffled, [npc.moan+], [npc.name] [npc.verb(bend)] down as far as [npc.she] can,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] as much of [npc.her] [npc.cock+] down [npc.her] throat as possible.",

							"Sliding [npc.her] head forwards, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] [npc.her] [npc.cock+] deep down [npc.her] throat."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle autofellatio";
		}
		@Override
		public String getActionDescription() {
			return "Gently suck your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently wrapping [npc.her] [npc.lips+] around [npc.her] own [npc.cock+], [npc.name] [npc.verb(start)] bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc.herself] a loving blowjob.",
					"With a soft, muffled [npc.moan], [npc.name] gently [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " wrapping [npc.her] [npc.lips+] around [npc.her] [npc.cock+] as [npc.she] [npc.verb(give)] [npc.herself] head.",
					"Slowly bobbing [npc.her] head up and down, [npc.name] gently [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc.her] [npc.cock+] as [npc.she] [npc.verb(give)] [npc.herself] a blowjob."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Autofellatio";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly suck your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly wrapping [npc.her] [npc.lips+] around [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc.herself] an enthusiastic blowjob.",
					"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " greedily wrapping [npc.her] [npc.lips+] around [npc.her] [npc.cock+] as [npc.she] [npc.verb(give)] [npc.herself] head.",
					"Rapidly bobbing [npc.her] head up and down, [npc.name] desperately [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc.her] [npc.cock+] as [npc.she] [npc.verb(give)] [npc.herself] an eager blowjob."));
		
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough autofellatio";
		}
		@Override
		public String getActionDescription() {
			return "Roughly suck your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Forcefully gripping [npc.her] [npc.lips+] down around [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] aggressively bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc.herself] a rough blowjob.",
					"With a muffled, [npc.moan+], [npc.name] violently [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " roughly wrapping [npc.her] [npc.lips+] around [npc.her] [npc.cock+] as [npc.she] [npc.verb(give)] [npc.herself] head.",
					"Roughly bobbing [npc.her] head up and down, [npc.name] dominantly [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc.her] [npc.cock+] as [npc.she] [npc.verb(give)] [npc.herself] a forceful blowjob."));
		
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Autofellatio";
		}
		@Override
		public String getActionDescription() {
			return "Continue sucking your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Wrapping [npc.her] [npc.lips+] around [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc.herself] a blowjob.",
					"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " wrapping [npc.her] [npc.lips+] around [npc.her] [npc.cock+] as [npc.she] [npc.verb(give)] [npc.herself] head.",
					"Rapidly bobbing [npc.her] head up and down, [npc.name] [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc.her] [npc.cock+] as [npc.she] [npc.verb(give)] [npc.herself] a blowjob."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager autofellatio";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly suck your [npc.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly wrapping [npc.her] [npc.lips+] around [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc.herself] an enthusiastic blowjob.",
					"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " greedily wrapping [npc.her] [npc.lips+] around [npc.her] [npc.cock+] as [npc.she] [npc.verb(give)] [npc.herself] head.",
					"Rapidly bobbing [npc.her] head up and down, [npc.name] desperately [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc.her] [npc.cock+] as [npc.she] [npc.verb(give)] [npc.herself] an eager blowjob."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Stop autofellatio";
		}
		@Override
		public String getActionDescription() {
			return "Take your [npc.cock+] out of your mouth and stop giving yourself a blowjob.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly forcing [npc.her] [npc.cock+] down [npc.her] throat one last time, [npc.name] then [npc.verb(pull)] [npc.her] head back, putting a quick end to [npc.her] autofellatio.",

							"Slamming [npc.her] [npc.face] into [npc.her] own groin, [npc.name] [npc.verb(force)] [npc.her] [npc.cock+] deep down [npc.her] throat,"
									+ " before pulling completely back and putting an end to [npc.her] autofellatio."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock+] out of [npc.her] mouth, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(put)] an end to [npc.her] autofellatio.",

							"With [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] head back, sliding [npc.her] [npc.cock+] fully out of [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
