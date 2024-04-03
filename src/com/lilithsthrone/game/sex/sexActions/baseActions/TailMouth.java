package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
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
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class TailMouth {
	
	// Character performing action is receiving the face-fucking:
	
	public static final SexAction TAIL_THROAT_FUCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Get tail sucked";
		}
		@Override
		public String getActionDescription() {
			return "Slide your [npc.tail+(true)] down [npc2.namePos] throat and get [npc2.herHim] to perform oral on it.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.tailTip+] of [npc.her] [npc.tail(true)] over [npc2.namePos] [npc2.lips+],"
									+ " [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly pushing forwards, sinking [npc.her] [npc.tail+(true)] down [npc2.her] throat.",
							"[npc.Name] [npc.verb(position)] the [npc.tailTip+] of [npc.her] [npc.tail(true)] between [npc2.namePos] [npc2.lips+],"
									+ " and with a slow, steady pressure, [npc.she] gently [npc.verb(sink)] it deep down [npc2.her] throat."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.tailTip+] of [npc.her] [npc.tail(true)] over [npc2.namePos] [npc2.lips+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.tail+(true)] into [npc2.her] throat.",
							"[npc.Name] [npc.verb(position)] the [npc.tailTip+] of [npc.her] [npc.tail(true)] between [npc2.namePos] [npc2.lips+], "
									+ "and with a determined thrust, [npc.she] eagerly [npc.verb(sink)] it deep down [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.tailTip+] of [npc.her] [npc.tail(true)] over [npc2.namePos] [npc2.lips+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming forwards, forcing [npc.her] [npc.tail+(true)] deep into [npc2.her] throat.",
							"[npc.Name] [npc.verb(position)] the [npc.tailTip+] of [npc.her] [npc.tail(true)] between [npc2.namePos] [npc2.lips+],"
									+ " and with a forceful thrust, [npc.she] roughly [npc.verb(slam)] it deep down [npc2.her] throat."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.tailTip+] of [npc.her] [npc.tail(true)] over [npc2.namePos] [npc2.lips+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.tail+(true)] into [npc2.her] throat.",
							"[npc.Name] [npc.verb(position)] the [npc.tailTip+] of [npc.her] [npc.tail(true)] between [npc2.namePos] [npc2.lips+],"
									+ " and with a determined thrust, [npc.she] eagerly [npc.verb(sink)] it deep down [npc2.her] throat."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.tailTip+] of [npc.her] [npc.tail(true)] over [npc2.namePos] [npc2.lips+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, sinking [npc.her] [npc.tail+(true)] into [npc2.her] throat.",
							"[npc.Name] [npc.verb(position)] the [npc.tailTip+] of [npc.her] [npc.tail(true)] between [npc2.namePos] [npc2.lips+],"
									+ " and with a little thrust, [npc.she] [npc.verb(sink)] it deep down [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(get)] a taste of [npc.namePos] [npc.tail+(true)],"
										+ " before gently pushing [npc2.her] head forwards in order to sink it even deeper down [npc2.her] throat.",
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently pushing [npc2.her] head forwards,"
										+ " sinking [npc.namePos] [npc.tail+(true)] even deeper down [npc2.her] throat."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(get)] a taste of [npc.namePos] [npc.tail+(true)],"
										+ " before violently pushing [npc2.her] head forwards in order to force it even deeper down [npc2.her] throat.",
								" With a gargled [npc2.moan], [npc2.name] [npc2.verb(push)] [npc2.her] head forwards,"
										+ " forcing [npc.namePos] [npc.tail+(true)] even deeper down [npc2.her] throat."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(get)] a taste of [npc.namePos] [npc.tail+(true)],"
										+ " before eagerly pushing [npc2.her] head forwards in order to sink it even deeper down [npc2.her] throat.",
								" With a gargled [npc2.moan], [npc2.name] eagerly [npc2.verb(bob)] [npc2.her] head forwards,"
										+ " pushing [npc.namePos] [npc.tail+(true)] even deeper down [npc2.her] throat."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(get)] a taste of [npc.namePos] [npc.tail+(true)],"
										+ " before pushing [npc2.her] head forwards in order to sink it even deeper down [npc2.her] throat.",
								" With a gargled [npc2.moan], [npc2.name] [npc2.verb(bob)] [npc2.her] head forwards,"
										+ " pushing [npc.namePos] [npc.tail+(true)] even deeper down [npc2.her] throat."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] [npc2.verb(get)] a taste of [npc.namePos] [npc.tail(true)],"
										+ " and, with tears running down [npc2.her] [npc2.face], [npc2.she] [npc2.verb(try)] in vain to pull away from the unwanted penetration.",
								" [npc2.Name] [npc2.verb(try)] in vain to pull away from the unwanted penetration,"
										+ " and with tears running down [npc2.her] [npc2.face] [npc2.she] [npc2.verb(let)] out a gargled, distressed cry."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] head forwards in response,"
									+ " letting out a muffled [npc2.moan] as [npc2.she] enthusiastically [npc2.verb(help)] to sink [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat.",
							" A gargled [npc2.moan] escapes from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly pushing [npc2.her] head forwards, [npc2.she] [npc2.verb(help)] to sink [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat.",
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] head forwards in order to help force [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to twist away from [npc.namePos] [npc.tail(true)],"
									+ " [npc2.name] [npc2.verb(let)] out a gargled [npc2.sob], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(pull)] [npc2.her] head backwards.",
							" A muffled [npc2.sob] escapes from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " tears streaming down [npc2.her] [npc2.face] as [npc2.her] throat is violated by [npc.namePos] [npc.tail+(true)].",
							" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
									+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name] in an attempt to pull [npc.her] [npc.tail(true)] out of [npc2.her] throat."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] head forwards in response,"
									+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(help)] to sink [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat.",
							" A gargled [npc2.moan] escapes from between [npc2.namePos] [npc2.lips+],"
									+ " and, pushing [npc2.her] head forwards, [npc2.she] [npc2.verb(help)] to sink [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat.",
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(thrust)] [npc2.her] head forwards in order to help force [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly [npc2.verb(push)] [npc2.her] head forwards in response,"
									+ " letting out a muffled [npc2.moan] as [npc2.she] gently [npc2.verb(help)] to sink [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat.",
							" A soft, gargled [npc2.moan] escapes from between [npc2.namePos] [npc2.lips+],"
									+ " and, slowly pushing [npc2.her] head forwards, [npc2.she] [npc2.verb(help)] to sink [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat.",
							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(push)] [npc2.her] head forwards in order to help sink [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(push)] [npc2.her] head forwards in response,"
									+ " letting out a muffled [npc2.moan] as [npc2.she] dominantly [npc2.verb(force)] [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat.",
							" A gargled [npc2.moan] escapes from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly pushing [npc2.her] head forwards, [npc2.she] forcefully [npc2.verb(drive)] [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat.",
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(thrust)] [npc2.her] head forwards in order to force [npc.namePos] [npc.tail+(true)] deep down [npc2.her] throat."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TAIL_THROAT_FUCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle tail sucked";
		}
		@Override
		public String getActionDescription() {
			return "Gently slide your [npc.tail(true)] in and out of [npc2.namePos] throat.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Softly pushing [npc.her] [npc.tail+(true)] deep down [npc2.namePos] throat,"
							+ " [npc.name] [npc.verb(start)] sliding it in and out, letting out a little [npc.moan] with every one of [npc.her] gentle thrusts.",
					"Slowly pushing [npc.her] [npc.tail+(true)] down [npc2.namePos] throat,"
							+ " [npc.name] softly [npc2.verb(thrust)] it in and out, letting out a little [npc.moan] as [npc.she] gently [npc.verb(receive)] oral from [npc2.herHim].",
					"Sliding [npc.her] [npc.tail+(true)] down [npc2.namePos] throat,"
							+ " [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly [npc.verb(tail-peg)] [npc2.herHim]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_THROAT_FUCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Tail sucked";
		}
		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tail+(true)] in and out of [npc2.namePos] throat.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.tail+(true)] deep into [npc2.namePos] throat,"
							+ " [npc.name] [npc.verb(start)] enthusiastically pumping it in and out, letting out [npc.a_moan+] with each one of [npc.her] energetic thrusts.",
					"Enthusiastically pushing [npc.her] [npc.tail+(true)] down [npc2.namePos] throat,"
							+ " [npc.name] enthusiastically [npc2.verb(thrust)] it in and out, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(receive)] oral from [npc2.herHim].",
					"Thrusting [npc.her] [npc.tail+(true)] deep down [npc2.namePos] throat,"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pump it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(tail-fuck)] [npc2.her] face."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough tail sucked";
		}
		@Override
		public String getActionDescription() {
			return "Roughly thrust your [npc.tail+(true)] in and out of [npc2.namePos] throat.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.tail+(true)] deep into [npc2.namePos] throat,"
							+ " [npc.name] [npc.verb(start)] roughly pumping it in and out, letting out [npc.a_moan+] with each one of [npc.her] brutal thrusts.",
					"Violently thrusting [npc.her] [npc.tail+(true)] down [npc2.namePos] throat,"
							+ " [npc.name] roughly [npc2.verb(thrust)] it in and out, letting out [npc.a_moan+] as [npc.she] forcefully [npc.verb(receive)] oral from [npc2.herHim].",
					"Forcefully driving [npc.her] [npc.tail+(true)] deep down [npc2.namePos] throat,"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to roughly slam it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] violently [npc.verb(tail-fuck)] [npc2.her] face."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Tail sucked";
		}
		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] throat.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.tail+(true)] deep into [npc2.namePos] throat,"
							+ " [npc.name] [npc.verb(start)] pumping it in and out, letting out [npc.a_moan+] with each one of [npc.her] thrusts.",
					"Pushing [npc.her] [npc.tail+(true)] down [npc2.namePos] throat,"
							+ " [npc.name] [npc2.verb(thrust)] it in and out, letting out [npc.a_moan+] as [npc.she] [npc.verb(receive)] oral from [npc2.herHim].",
					"Thrusting [npc.her] [npc.tail+(true)] deep down [npc2.namePos] throat,"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] [npc.verb(tail-fuck)] [npc2.her] face."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager tail sucked";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly pump your [npc.tail+(true)] in and out of [npc2.namePos] throat.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.tail+(true)] deep into [npc2.namePos] throat,"
							+ " [npc.name] [npc.verb(start)] enthusiastically pumping it in and out, letting out [npc.a_moan+] with each one of [npc.her] energetic thrusts.",
					"Enthusiastically pushing [npc.her] [npc.tail+(true)] down [npc2.namePos] throat,"
							+ " [npc.name] enthusiastically [npc2.verb(thrust)] it in and out, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(receive)] oral from [npc2.herHim].",
					"Thrusting [npc.her] [npc.tail+(true)] deep down [npc2.namePos] throat,"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pump it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(tail-fuck)] [npc2.her] face."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist tail sucked";
		}
		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.tail(true)] out of [npc2.namePos] throat.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.tail(true)] out of [npc2.namePos] mouth,"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(take)] a gentle hold of it, before softly forcing it back down [npc2.her] throat.",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.tail(true)] away from [npc2.name],"
									+ " but [npc2.she] quickly [npc2.verb(grab)] it and gently [npc2.verb(force)] it back down [npc2.her] throat.",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.tail(true)] away from [npc2.namePos] [npc2.lips+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] gently [npc2.verb(force)] [npc.her] [npc.tail+(true)] back down [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.tail(true)] out of [npc2.namePos] mouth,"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(take)] a rough hold of it, before aggressively forcing it back down [npc2.her] throat.",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.tail(true)] away from [npc2.name],"
									+ " but [npc2.she] quickly [npc2.verb(grab)] it and roughly [npc2.verb(force)] it back down [npc2.her] throat.",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.tail(true)] away from [npc2.namePos] [npc2.lips+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] roughly [npc2.verb(force)] [npc.her] [npc.tail+(true)] back down [npc2.her] throat."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.tail(true)] out of [npc2.namePos] mouth,"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(take)] a firm hold of it, before eagerly forcing it back down [npc2.her] throat.",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.tail(true)] away from [npc2.name],"
									+ " but [npc2.she] quickly [npc2.verb(grab)] it and eagerly [npc2.verb(force)] it back down [npc2.her] throat.",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.tail(true)] away from [npc2.namePos] [npc2.lips+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(force)] [npc.her] [npc.tail+(true)] back down [npc2.her] throat."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop tail sucked";
		}
		@Override
		public String getActionDescription() {
			return "Pull your [npc.tail+(true)] out of [npc2.namePos] throat and stop receiving oral from [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.tail+(true)] out of [npc2.namePos] mouth,"
									+ " [npc.name] dominantly [npc.verb(slide)] the [npc.tailTip+] up and down over [npc2.her] [npc2.lips+] one last time before pulling back.",
							"Thrusting deep down [npc2.name] throat one last time, [npc.name] then [npc.verb(yank)] [npc.her] [npc.tail+(true)] back out of [npc2.her] mouth, putting an end to the rough face-fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tail(true)] out of [npc2.namePos] mouth,"
									+ " [npc.name] [npc.verb(rub)] the [npc.tailTip] up and down over [npc2.her] [npc2.lips+] one last time before pulling back.",
							"Pushing deep down [npc2.name] throat one last time, [npc.name] then [npc.verb(slide)] [npc.her] [npc.tail+(true)] back out of [npc2.her] mouth, putting an end to the face-fucking."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.her] throat,"
										+ " and [npc2.she] [npc2.verb(continue)] crying and protesting as [npc2.she] weakly [npc2.verb(struggle)] against [npc.herHim].",
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] [npc2.her] face away from [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.tail+(true)] out of [npc2.her] throat, eager for more of [npc.her] attention.",
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Character performing action is doing the face-fucking:
	
	public static final SexAction USING_TAIL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Suck tail";
		}
		@Override
		public String getActionDescription() {
			return "Slide [npc2.namePos] [npc2.tail+(true)] into your throat and perform oral on it.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.tail(true)], [npc.name] slowly [npc.verb(guide)] it up to [npc.her] [npc.lips+],"
									+ " letting out a little [npc.moan] before opening wide and gently taking it deep down [npc.her] throat.",
							"Grabbing [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(line)] it up to [npc.her] parted [npc.lips],"
									+ " before slowly moving [npc.her] head forwards and gently taking it deep down [npc.her] throat."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.tail(true)], [npc.name] eagerly [npc.verb(guide)] it up to [npc.her] [npc.lips+],"
									+ " letting out [npc.a_moan+] before opening wide and greedily taking it deep down [npc.her] throat.",
							"Grabbing [npc2.namePos] [npc2.tail(true)], [npc.name] impatiently [npc.verb(line)] it up to [npc.her] parted [npc.lips],"
									+ " before quickly bobbing [npc.her] head forwards and eagerly taking it deep down [npc.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.tail(true)], [npc.name] roughly [npc.verb(yank)] it up to [npc.her] [npc.lips+],"
									+ " letting out [npc.a_moan+] before opening wide and forcefully taking it deep down [npc.her] throat.",
							"Grabbing [npc2.namePos] [npc2.tail(true)], [npc.name] violently [npc.verb(yank)] it up to [npc.her] parted [npc.lips],"
									+ " before quickly bobbing [npc.her] head forwards and roughly forcing it deep down [npc.her] throat."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(guide)] it up to [npc.her] [npc.lips+],"
									+ " letting out [npc.a_moan+] before opening wide and taking it deep down [npc.her] throat.",
							"Grabbing [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(line)] it up to [npc.her] parted [npc.lips],"
									+ " before bobbing [npc.her] head forwards and taking it deep down [npc.her] throat."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response,"
										+ " before gently pushing [npc2.her] [npc2.tail(true)] forwards and starting to rhythmically tail-fuck [npc.namePos] throat.",
								" With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.tail(true)] forwards,"
										+ " sinking it deep down [npc.namePos] throat as [npc2.she] [npc2.verb(start)] rhythmically tail-fucking [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
										+ " before eagerly pushing [npc2.her] [npc2.tail(true)] forwards and starting to rhythmically tail-fuck [npc.namePos] throat.",
								" With [npc2.a_moan+], [npc2.name] happily [npc2.verb(thrust)] [npc2.her] [npc2.tail(true)] forwards,"
										+ " sinking it deep down [npc.namePos] throat as [npc2.she] [npc2.verb(start)] rhythmically tail-fucking [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
										+ " before seeking to remind [npc.name] who's in charge by violently thrusting [npc2.her] [npc2.tail(true)] forwards and starting to roughly tail-fuck [npc.her] throat.",
								" With [npc2.a_moan+], [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.tail(true)] forwards,"
										+ " sinking it deep down [npc.namePos] throat as [npc2.she] [npc2.verb(start)] forcefully tail-fucking [npc.her] face."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
										+ " before pushing [npc2.her] [npc2.tail(true)] forwards and starting to rhythmically tail-fuck [npc.namePos] throat.",
								" With [npc2.a_moan+], [npc2.name] happily [npc2.verb(thrust)] [npc2.her] [npc2.tail(true)] forwards,"
										+ " sinking it deep down [npc.namePos] throat as [npc2.she] [npc2.verb(start)] rhythmically tail-fucking [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.tail(true)] inside of [npc.herHim],"
										+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.tail+(true)] free from [npc.her] throat.",
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.tail(true)] deep into [npc.her] throat."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] deep down [npc.namePos] throat,"
									+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(tail-fuck)] [npc.her] face.",
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth as [npc2.she] [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] deep down [npc.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.tail+(true)] as deep as possible down [npc.namePos] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.tail(true)] away from [npc.namePos] [npc.lips],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.tail+(true)] deep into [npc.her] throat.",
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.tail(true)] away from [npc.namePos] throat."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.tail+(true)] deep down [npc.namePos] throat,"
									+ " letting out a soft [npc2.moan] as [npc2.she] gently [npc2.verb(tail-fuck)] [npc.her] face.",
							" [npc2.A_moan+] drifts out from [npc2.namePos] mouth as [npc2.she] [npc2.verb(slide)] [npc2.her] [npc2.tail+(true)] deep down [npc.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.tail+(true)] as deep as possible down [npc.namePos] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] deep down [npc.namePos] throat,"
									+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(tail-fuck)] [npc.her] face.",
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth as [npc2.she] violently [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] deep down [npc.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] forcefully [npc2.verb(drive)] [npc2.her] [npc2.tail+(true)] as deep as possible down [npc.namePos] throat."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] deep down [npc.namePos] throat,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(tail-fuck)] [npc.her] face.",
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth as [npc2.she] [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] deep down [npc.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.tail+(true)] as deep as possible down [npc.namePos] throat."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction SUCKING_TAIL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gently suck tail";
		}
		@Override
		public String getActionDescription() {
			return "Gently suck on [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] head forwards, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.tail+(true)] deep down [npc.her] throat.",
					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] pushing [npc.her] head forwards, forcing [npc2.namePos] [npc2.tail+(true)] ever deeper down [npc.her] throat.",
					"Slowly pushing [npc.her] head forwards,"
							+ " a muffled [npc.moan] escapes from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] deep down [npc.her] throat."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TAIL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Suck tail";
		}
		@Override
		public String getActionDescription() {
			return "Suck on [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] head forwards, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.tail+(true)] deep down [npc.her] throat.",
					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] pushing [npc.her] head forwards, forcing [npc2.namePos] [npc2.tail+(true)] ever deeper down [npc.her] throat.",
					"Energetically pushing [npc.her] head forwards,"
							+ " [npc.a_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] happily [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] deep down [npc.her] throat."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TAIL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Roughly suck tail";
		}
		@Override
		public String getActionDescription() {
			return "Roughly suck on [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] head forwards, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] deep down [npc.her] throat.",
					"With [npc.a_moan+], [npc.name] [npc.verb(start)] roughly pushing [npc.her] head forwards, forcing [npc2.namePos] [npc2.tail+(true)] ever deeper down [npc.her] throat.",
					"Forcefully pushing [npc.her] head forwards,"
							+ " [npc.a_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] violently [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] deep down [npc.her] throat."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SUCKING_TAIL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Suck tail";
		}
		@Override
		public String getActionDescription() {
			return "Suck on [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] head forwards, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.tail+(true)] deep down [npc.her] throat.",
					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] pushing [npc.her] head forwards, forcing [npc2.namePos] [npc2.tail+(true)] ever deeper down [npc.her] throat.",
					"Energetically pushing [npc.her] head forwards,"
							+ " [npc.a_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] happily [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] deep down [npc.her] throat."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TAIL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eagerly suck tail";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly fuck your throat on [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] head forwards, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.tail+(true)] deep down [npc.her] throat.",
					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] pushing [npc.her] head forwards, forcing [npc2.namePos] [npc2.tail+(true)] ever deeper down [npc.her] throat.",
					"Energetically pushing [npc.her] head forwards,"
							+ " [npc.a_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] happily [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] deep down [npc.her] throat."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist tail sucking";
		}
		@Override
		public String getActionDescription() {
			return "Try and pull away from [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.tail(true)] out of [npc.her] throat.",
							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] head away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.tail+(true)] [npc2.verb(continue)] slowly sliding in and out of [npc.her] throat.",
							"Trying desperately to pull [npc.her] head away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.tail+(true)] [npc2.verb(continue)] gently sliding deep into [npc.her] throat."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.tail(true)] out of [npc.her] throat.",
							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] head away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.tail+(true)] [npc2.verb(continue)] frantically pumping in and out of [npc.her] throat.",
							"Trying desperately to pull [npc.her] head away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.tail+(true)] [npc2.verb(continue)] greedily thrusting deep into [npc.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.tail(true)] out of [npc.her] throat.",
							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] head away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.tail+(true)] [npc2.verb(continue)] roughly slamming in and out of [npc.her] throat.",
							"Trying desperately to pull [npc.her] head away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.tail+(true)] [npc2.verb(continue)] violently thrusting deep into [npc.her] throat."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop sucking tail";
		}
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.tail(true)] out of your throat.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.tail(true)] out of [npc.her] throat, [npc.name] [npc.verb(let)] out a menacing growl to [npc.verb(remind)] [npc2.name] that [npc.sheIs] the one in charge.",
							"[npc.Name] [npc.verb(lean)] into [npc2.name], taking a moment to inhale [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.tail(true)] out of [npc.her] throat."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.tail(true)] out of [npc.her] throat, [npc.name] [npc.verb(let)] out [npc.a_moan+] to let [npc2.name] know that [npc.she] enjoyed the experience.",
							"[npc.Name] [npc.verb(lean)] into [npc2.name], taking a moment to inhale [npc2.her] [npc2.scent] before sliding [npc2.her] [npc2.tail(true)] out of [npc.her] throat."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.nameIsFull]n't finished with [npc2.herHim] just yet.",
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to protest and struggle against [npc.name] as [npc.she] [npc.verb(hold)] [npc2.herHim] firmly in place."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] [npc2.herHim] from tail-fucking [npc.her] throat.",
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue tail-fucking [npc.namePos] throat."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction THROAT_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Throat control";
		}
		@Override
		public String getActionDescription() {
			return "Squeeze your internally-muscled throat down around [npc2.namePos] [npc2.tail(true)].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getFaceOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] [npc.verb(concentrate)] on squeezing the extra internal muscles within [npc.her] throat down around [npc2.namePos] [npc2.tail+(true)].",
					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(focus)] on controlling the extra muscles lining the insides of [npc.her] throat."
							+ " Gripping and squeezing them down around [npc2.namePos] [npc2.tail+(true)], [npc.name] [npc.verb(cause)] [npc2.herHim] to let out an involuntary cry of pleasure."
						:""),
					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans] falling into a steady rhythm as [npc.she] [npc.verb(concentrate)]"
							+ " on squeezing the extra muscles within [npc.her] throat down around [npc2.namePos] [npc2.tail+(true)].",
					"With [npc.a_moan+], [npc.name] [npc.verb(focus)] on controlling the extra muscles deep within [npc.her] throat,"
							+ " gripping them down and massaging [npc2.namePos] [npc2.tail+(true)] as [npc.she] [npc.verb(squeal)] in pleasure.");
		}
	};
}
