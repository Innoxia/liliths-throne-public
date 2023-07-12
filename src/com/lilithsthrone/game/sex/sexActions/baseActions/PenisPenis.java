package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.8.5
 * @version 0.4.8.5
 * @author Sightglass
 */
public class PenisPenis {

	private static boolean hasSmallPenis(GameCharacter gc) {
		PenisLength pl = gc.getPenisSize();
		return (pl == PenisLength.ONE_TINY || pl == PenisLength.ZERO_MICROSCOPIC);
	}
	
	private static boolean notDildoPenis(GameCharacter gc) {
		AbstractPenisType pt = gc.getPenisType();
		return pt != PenisType.DILDO;
	}
	
	public static final SexAction FROTTING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Start frotting";
		}

		@Override
		public String getActionDescription() {
			return "Start grinding your [npc.cock] up and down against [npc2.namePos] [npc2.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			return notDildoPenis(target) && notDildoPenis(target);
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down, [npc.name] [npc.verb(adjust)] [npc.her] [npc.cock] to line it up with [npc2.hers], before starting to slowly thrust, rubbing [npc.her] shaft against [npc2.hers].",
							"Carefully adjusting [npc.her] [npc.hips+], [npc.name] [npc.verb(press)] [npc.her] [npc.cock] against [npc2.namePos] [npc2.cock], before starting to rhythmically grind back and forth.",
							"With [npc.a_moan+], [npc.name] slowly [npc.verb(press)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to tease [npc.her] [npc.cock] up and down over [npc2.hers]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a rough buck of [npc.her] [npc.hips+], [npc.name] forcefully [npc.verb(grind)] [npc.her] [npc.cock] against [npc2.namePos] [npc2.cock].",
							"With a growl, [npc.name] roughly [npc.verb(pull)] [npc2.namePos] crotch into place around [npc.her] [npc.legs], lining up [npc.her] [npc.cock] against [npc2.hers] and starting to thrust them against each other.",
							"With [npc.a_moan+], [npc.name] roughly [npc.verb(grind)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to forcefully grind [npc.her] [npc.cock] up and down over [npc2.hers]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(draw)] [npc2.name] in close, opening [npc.her] legs to align [npc.her] groin with [npc2.hers]. [npc.Name] [npc.verb(pull)] [npc2.him] in eagerly, coaxing [npc2.name] to thrust and grind [npc2.her] [npc2.cock] against [npc.hers].",
							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] groin in between [npc.namePos] [npc.legs], before starting to frantically grind [npc.her] [npc.cock] up against [npc2.hers]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(draw)] [npc2.name] in close, opening [npc.her] legs to align [npc.her] groin with [npc2.hers]. [npc.Name] [npc.verb(pull)] [npc2.him] in coyly, coaxing [npc2.name] to thrust and grind [npc2.her] [npc2.cock] against [npc.hers].",
							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(guide)] [npc2.namePos] groin in between [npc.namePos] [npc.legs], before starting tease [npc.her] [npc.cock] up against [npc2.hers]."));
					break;
				case SUB_RESISTING:
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Gently bucking [npc2.her] own [npc2.hips] in response,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and softly [npc2.verb(rub)] [npc2.her] [npc2.cock] against [npc.hers].",
							" Responding with a gentle buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to softly rub [npc2.her] [npc2.cock] up and down over [npc.hers]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Roughly bucking [npc2.her] own [npc2.hips] in response,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and forcefully [npc2.verb(grind)] [npc2.her] [npc2.cock] against [npc.hers].",
							" Responding with a violent buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to roughly grind [npc2.her] [npc2.cock] up and down over [npc.hers]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Happily bucking [npc2.her] own [npc2.hips] in response,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and readily [npc2.verb(rub)] [npc2.her] [npc2.cock] against [npc.hers].",
							" Responding with a happy buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to frantically rub [npc2.her] [npc2.cock] up and down over [npc.hers]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Bucking [npc2.her] own [npc2.hips] in response,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and [npc2.verb(rub)] [npc2.her] [npc2.cock] against [npc.hers].",
							" Responding with a buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to rub [npc2.her] [npc2.cock] up and down over [npc.hers]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying to pull away,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(plead)] for [npc.name] to get away from [npc2.herHim] and leave [npc2.her] [npc2.cock] alone.",
							" Responding by frantically recoiling from [npc.namePos] unwanted advance, [npc2.name] [npc2.verb(start)] pleading to be left alone, all the while trying to pull [npc2.her] [npc2.cock] away from [npc.namePos]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] enthusiastically [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(rub)] [npc2.her] own [npc2.cock] up and down over [npc.namePos].",
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.cock] against [npc.namePos].",
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out, using [npc.namePos] movements to help grind [npc2.her] own [npc2.cock] against [npc.hers]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock], [npc2.name] desperately [npc2.verb(beg)] for [npc.name] to get away from [npc2.him].",
						" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away from [npc2.her] groin.",
						" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
								+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] groin."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(rub)] [npc2.her] own [npc2.cock] up and down over [npc.hers].",
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.cock] against [npc.hers].",
						" [npc2.Moaning] in pleasure, [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out, using [npc.namePos] movements to help grind [npc2.her] own [npc2.cock+] against [npc.hers]."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] happily [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] softly [npc2.verb(rub)] [npc2.her] own [npc2.cock] up and down over [npc.namePos] groin.",
						" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, gently thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.cock] against [npc.namePos].",
						" [npc2.Moaning] in pleasure, [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.hips+] out, using [npc.namePos] movements to help grind [npc2.her] own [npc2.cock+] against [npc.hers]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] forcefully [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(grind)] [npc2.her] own [npc2.cock] up and down over [npc.namePos] [npc.cock+].",
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting out [npc2.her] [npc2.hips], [npc2.she] forcefully [npc2.verb(grind)] [npc2.her] [npc2.cock] against [npc.hers]",
						" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out, using [npc.namePos] movements to help force [npc2.her] own [npc2.cock+] against [npc.her] groin."));
				break;
		}
		return "";
	}
	
	public static final SexAction FROTTING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle frotting";
		}

		@Override
		public String getActionDescription() {
			return "Gently slide your [npc.cock] up and down over [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out a little [npc.moan] with every thrust of [npc.her] [npc.hips], [npc.name] gently [npc.verb(rub)] [npc.her] [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] out a series of soft sighs as [npc.she] gently [npc.verb(rub)] [npc.her] [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].",
					"Sliding [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out a little [npc.moan] with each pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FROTTING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Frotting";
		}

		@Override
		public String getActionDescription() {
			return "Continue grinding your [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+] with each eager thrust of [npc.her] [npc.hips], [npc.name] passionately [npc.verb(rub)] [npc.her] [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] out a series of lewd [npc.moans] as [npc.she] desperately [npc.verb(grind)] [npc.her] [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].",
					"Eagerly grinding [npc.her] [npc.clit+] over [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] with each frantic pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FROTTING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough frotting";
		}

		@Override
		public String getActionDescription() {
			return "Continue roughly grinding your [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+] with each forceful thrust of [npc.her] [npc.hips], [npc.name] roughly [npc.verb(grind)] [npc.her] [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] out a series of lewd [npc.moans] as [npc.she] roughly [npc.verb(grind)] [npc.her] [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].",
					"Violently grinding [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] with each forceful pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FROTTING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Frotting";
		}

		@Override
		public String getActionDescription() {
			return "Continue rubbing your [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+] with each thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(continue)] to rub [npc.her] [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] out a series of lewd [npc.moans] as [npc.she] [npc.verb(rub)] [npc.her] [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].",
					"Rubbing [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] with each pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FROTTING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager frotting";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly grind your [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+] with each eager thrust of [npc.her] [npc.hips], [npc.name] passionately [npc.verb(rub)] [npc.her] [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] out a series of lewd [npc.moans] as [npc.she] desperately [npc.verb(grind)] [npc.her] [npc.cock+] up and down over [npc2.namePos] [npc2.cock+].",
					"Eagerly grinding [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] with each frantic pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FROTTING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist frotting";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull away from [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] groin away from [npc2.nameHers],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, keeping [npc2.her] [npc2.cock+] gently pressed against [npc.her] [npc.cock+].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name],"
									+ " but [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, before continuing to gently rub [npc2.her] [npc2.cock+] against [npc.her] [npc.cock+].",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.nameHers],"
									+ " but [npc2.she] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] gently [npc2.verb(rub)] [npc2.her] [npc2.cock+] up against [npc2.hers]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] groin away from [npc2.nameHers],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.she] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips] forwards, keeping [npc2.her] [npc2.cock+] forcefully ground against [npc.her] [npc.cock+].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name],"
									+ " but [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, before continuing to roughly grind [npc2.her] [npc2.cock+] against [npc.her] [npc.cock+].",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.nameHers],"
									+ " but [npc2.she] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] forcefully [npc2.verb(grind)] [npc2.her] [npc2.cock+] up against [npc2.hers]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] groin away from [npc2.nameHers],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, keeping [npc2.her] [npc2.cock+] pressed against [npc.her] [npc.cock+].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name],"
									+ " but [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, before continuing to eagerly rub [npc2.her] [npc2.cock+] against [npc.her] [npc.cock+].",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.nameHers],"
									+ " but [npc2.she] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] enthusiastically [npc2.verb(rub)] [npc2.her] [npc2.cock+] up against [npc2.hers]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FROTTING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop frotting";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] away from [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough thrust, [npc.name] [npc.verb(pull)] [npc.her] groin away from [npc2.namePos] [npc2.cock+], putting an end to the frotting.",
							"Roughly grinding [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock] one last time, [npc.name] then [npc.verb(pull)] back, putting an end to the rough frotting."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last buck of [npc.her] [npc.hips], [npc.name] [npc.verb(pull)] [npc.her] groin away from [npc2.namePos] [npc2.cock+], putting an end to the frotting.",
							"Rubbing [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock] one last time, [npc.name] then [npc.verb(pull)] back, putting an end to the frotting."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Although happy to have [npc2.her] [npc2.cock] released, [npc2.name] [npc2.verb(continue)] crying and weakly struggling against [npc.name] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.herHim] alone.",
							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.cock+] away from [npc.name]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] back, signalling [npc2.her] desire for more attention.",
							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PREHENSILE_FROTTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Coiled frotting";
		}

		@Override
		public String getActionDescription() {
			return "Use your prehensile cock to wrap around and massage [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.PREHENSILE) && Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.DOM_ROUGH;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] [npc.verb(concentrate)] on using the prehensility of [npc.her] [npc.cock] to entwine it around [npc2.namePos] [npc2.cock+], before coiling it up to massage and squeeze down on it.",
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(focus)] on controlling [npc.her] prehensile [npc.cock]."
							+ " Wrapping it around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(grin)] as [npc.she] then [npc.verb(coil)] it up to massage [npc2.her] [npc2.cock+], causing [npc2.herHim] to let out [npc2.a_moan+].",
					"[npc.Name] [npc.verb(grin)] in delight as [npc.she] [npc.verb(use)] [npc.her] prehensile cock to massage and stroke [npc2.namePos] [npc2.cock+], tugging at it with rhythmic undulations.",
					"With [npc.a_moan+], [npc.name] [npc.verb(focus)] on controlling [npc.her] prehensile [npc.cock], wrapping it around [npc2.namePos] [npc2.cock+], trapping and milking it in the coils of [npc.her] constricting [npc.cock].].");
		}
	};

	public static final SexAction SHEATH_FROTTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Sheath docking";
		}

		@Override
		public String getActionDescription() {
			return "Use your cock to tease and penetrate the sheath holding [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).hasPenisModifier(PenetrationModifier.SHEATHED) && Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.DOM_ROUGH;
		}

		@Override
		public String getDescription() {
			String start = UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] [npc.verb(slip)] the tip of [npc.her] [npc.cock] into the sheath surrounding [npc2.namePos] [npc2.cock+], circling it and teasing the sensitive skin inside the sheath.",
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] down to [npc2.namePos] [npc2.cock+]."
							+ " Guiding it to [npc.namePos] [npc.cock+], [npc.name] [npc.verb(grin)] as [npc.she] then [npc.verb(slip)] it it past [npc2.her] [npc2.cock+] and into [npc2.her] sheath, causing [npc2.herHim] to let out [npc2.a_moan+].",
					"[npc.Name] [npc.verb(grin)] in delight as [npc.she] [npc.verb(guide)] [npc.her] cock to the opening of [npc2.namePos] sheath before slipping it in. [npc.NamePos] [npc.cock] fits snuggly next to [npc2.hers], and [npc.name] [npc.verb(start)] to massage and stroke both cocks, tugging at the sheath with rhythmic undulations.",
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(slip)] [npc.her] [npc.cock] into [npc2.namePos] shealth, teasing it with slow circles before slipping in further.");
			String middle1 = UtilText.returnStringAtRandom(
				"[npc.Name] slowly [npc.verb(work)] [npc.her] [npc.cock] deeper into the sheath, sliding it around the base of [npc2.her] [npc2.cock+].",
				"Rather than thrust deeply into [npc2.her] sheath, [npc.name] instead circle [npc.her] cock around [npc2.hers].",
				"Reaching down, [npc.name] firmly [npc.verb(grasp)] [npc2.her] sheath, before using it as a sleeve to jack [npc.herself] off.",
				"Reaching down, [npc.name] firmly [npc.verb(grasp)] [npc2.her] sheath, before sliding it deeper over [npc.her] [npc.cock]. Holding still instead of thrusting, [npc.name] instead slowly rub and massage [npc2.namePos] cock."
			);
			String middle2 = UtilText.returnStringAtRandom(
				"Overwhelmed by the stimulation from [npc2.her] sensitive sheath, all [npc2.name] [npc2.is] able to do is shudder and [npc2.moan].",
				"The inner surface of [npc2.namePos] sheath is incredibly sensitive, and [npc.name] [npc.verb(feel)] every shudder and twitch as [npc2.her] [npc2.cock] twitches in response to the sensation.",
				"With a lusty [npc2.moan], [npc2.name] [npc2.verb(press)] in closer to [npc.name], humping gently to try to get more of [npc.her] [npc.cock+] inside [npc2.her] sheath."
			);
			String end = UtilText.returnStringAtRandom(
				"After a few last thrusts, [npc.name] carefully [npc.verb(pull)] out of [npc2.namePos] sheath, [npc.name] playfully [npc.verb(slap)] [npc.her] [npc.cock] against [npc2.hers] before starting to frot and thrust against it again.",
				"With a sigh and a few more strokes inside [npc2.her] sheath, [npc.name] [npc.verb(pull)] out of [npc2.namePos] sheath and [npc.verb(resume)] frotting.",
				"After a little while, the sensation gets too intense to handle. With a [npc.moan], [npc.name] [npc.verb(pull)] [npc.her] [npc.cock] back out of the sheath surrounding [npc2.namePos] [npc.cock+]. [npc.Name] [npc.verb(give)] it a few strokes before lining up [npc.hers] up to rub and hump against it.",
				"Giving [npc2.namePos] sheath a few last stokes over [npc.her] [npc.cock], [npc.she] carefully [npc.verb(tug)] out of the snug sheath and [npc.verb(realign)] [npc.her] [npc.cock+] to resume frotting."
			);
			return String.join(" ",start, middle1, middle2,end);
		}
	};
	
	public static final SexAction PREHENSILE_FROTTING_DOUBLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Double-coiled frotting";
		}

		@Override
		public String getActionDescription() {
			return "Intertwine your prehensile cock with [npc2.hers].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).hasPenisModifier(PenetrationModifier.PREHENSILE) && Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.PREHENSILE) && Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.DOM_ROUGH;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] [npc.verb(concentrate)] on using the prehensility of [npc.her] [npc.cock] to entwine it around [npc2.namePos] [npc2.cock+]. [npc2.Name] [npc2.verb(match)] [npc.name], coiling [npc2.her] [npc2.cock] to match [npc.hers], before [npc.name] [npc.verb(start)] to slowly massage and rub at it.",
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(focus)] on controlling [npc.her] prehensile [npc.cock]."
							+ " Wrapping it around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(grin)] as [npc.she] then [npc.verb(entice)] [npc2.namePos] [npc2.cock] by stroking and curling around it with [npc.her] own. [npc2.Name] eagerly [npc2.verb(wrap)] [npc2.her] own prehensile [npc2.cock] around [npc.hers], returning the favor with teasing squeezes of [npc2.her] own.",
					"[npc.Name] [npc.verb(grin)] in delight as [npc.she] [npc.verb(use)] [npc.her] prehensile cock to massage and stroke [npc2.namePos] [npc2.cock+], and [npc2.name] [npc2.verb(use)] [npc2.hers] to wrap around [npc.hers]. Tugging at it with [npc.her] undulating [npc.cock], [npc.name] [npc.verb(settle)] on a comfortable rhythm with [npc2.name].",
					"With [npc.a_moan+], [npc.name] [npc.verb(focus)] on controlling [npc.her] prehensile [npc.cock], wrapping it around [npc2.namePos] [npc2.cock+]. Both with prehensile penises, [npc.name] and [npc2.name] try to trap and envelope the other cock, squeezing and milking it in constricting coils.");
		}
	};
	
	public static final SexAction FROTTING_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeCreampied() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeCreampied() {
			return SexAreaPenetration.PENIS;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			if(!performer.hasPenisIgnoreDildo()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			boolean dicksTouching = Main.sex.getCharacterOngoingSexArea(performer, SexAreaPenetration.PENIS).contains(target) 
				&& Main.sex.getCharacterOngoingSexArea(target, SexAreaPenetration.PENIS).contains(performer);
			if (!dicksTouching) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)!=OrgasmBehaviour.CREAMPIE
					&& !performer.isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(performer) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(performer)>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			boolean knotRequestObeyed = false;
			for(GameCharacter knotRequester : Main.sex.getCharactersRequestingKnot()) {
				if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterPerformingAction(), knotRequester)) {
					knotRequestObeyed = true; // If there is a knot requester who they're listening to, give priority to knotting
					break;
				}
			}
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if((Math.random()<0.66f
					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0)
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "Forced frotting!";
					
				} else if(bodypart == Arm.class) {
					return "Hug-locked frotting!";
					
				} else if(bodypart == Leg.class) {
					return "Leg-locked frotting!";
					
				} else if(bodypart == Tail.class) {
					return "Tail-locked frotting!";
					
				} else if(bodypart == Wing.class) {
					return "Wing-locked frotting!";
					
				} else if(bodypart == Tentacle.class) {
					return "Tentacle-locked frotting!";
				}
			}
			return "Frotting finish!";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull] using [npc.her] advantageous position to force you to grind your penis against [npc.herHim] as your orgasm! As you're on the very brink of orgasm, you have no time to try and push [npc.herHim] away!");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.arms+] around your lower body, thereby forcing you to grind against [npc.herHim] as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.legs+] around your lower body, forcing you to grind against [npc.herHim] as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] "+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+" around your lower body, forcing you to grind against [npc.herHim] as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.wingSize] [npc.wings] around your body, forcing you to grind against [npc.herHim] as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.tentacles+] around your lower body, forcing you to grind against [npc.herHim] as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			String returnString = "You've reached your climax, and can't hold back your orgasm any longer. Cum against [npc2.name], grinding your penis against [npc2.hers].";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
				
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& ((cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumTarget).isEmpty())
						|| (cumTarget.equals(cumProvider) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumProvider).isEmpty()))) {

				return Util.newArrayListOfValues(
						CoverableArea.PENIS,
						CoverableArea.VAGINA);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).isEndsSex();
		}
	};
	
	public static final SexAction FROTTING_SPH_SMALL = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL
		) {
		
		@Override
		public String getActionTitle() {
			return "Small Penis Humiliation (Frotting)";
		}

		@Override
		public boolean isSadisticAction() {
			return true;
		}

		@Override
		public String getActionDescription() {
			return "Mock [npc2.name] over [npc2.her] small member.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			boolean dicksTouching = Main.sex.getCharacterOngoingSexArea(actor, SexAreaPenetration.PENIS).contains(target) 
				&& Main.sex.getCharacterOngoingSexArea(target, SexAreaPenetration.PENIS).contains(actor);
			return hasSmallPenis(target) && notDildoPenis(target) && !hasSmallPenis(actor) && dicksTouching;
		}

		@Override
		public String getDescription() {
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			String intro = UtilText.returnStringAtRandom(
					"Lining up [npc.her] [npc.cock+] against [npc2.hers], [npc2.namePos] [npc2.cock+] comes up obviously short.",
					"[npc.Name] [npc.verb(pause)] to lay [npc.her] [npc.cock+] against [npc2.namePos] much smaller [npc2.cock].",
					"[npc.Name] [npc.verb(take)] the time to make an extra-long thrust rubbing the entire length of [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock+]"
			);
			String mid = UtilText.returnStringAtRandom(
					"Sneering at [npc2.her] pathetic [npc2.cock], [npc.name] [npc.verb(growl)], ",
					"Continuing to rub [npc.her] [npc.cock] over [npc2.her] pathetic [npc2.cock], [npc.name] [npc.verb(decide)] to really [npc.verb(rub)] it in, saying ",
					"[npc.Name] [npc.verb(give)] [npc2.him] a mocking chuckle, saying "
			);
			String quote;
			switch (target.getPenisSize()) {
				case ZERO_MICROSCOPIC:
					quote =  UtilText.returnStringAtRandom(
						"That thing's practically a clit!",
						"I wasn't expecting much, but this is just pathetic!",
						"How's it feel, to have your little clit crushed against a real dick?",
						"I can barely feel that pathetic little bump.",
						"You should feel lucky, to get to feel a <i>real</i> cock for once."
					);
					break;
				case ONE_TINY:
				default:
					quote =  UtilText.returnStringAtRandom(
						"Nice try, little "+ (target.isFeminine()?"lady":"guy") + ", but that thing just doesn't measure up.",
						"<i>That?</i> That's just disappointing.",
						"You should feel lucky, to get to feel a <i>real</i> cock for once.",
						"Is that it?",
						"I wasn't expecting much, but this is just pathetic!",
						"'Fun Size', huh?", 
						"I bet you love seeing your little dick dominated like this!"
					);
					break;
			}
			quote = "[npc.speech(" + quote + ")]";
			return String.join(" ", intro, mid, quote);
		}
	};
	
	public static final SexAction FROTTING_SPH_NORMAL = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Size Humiliation (Frotting)";
		}

		@Override
		public boolean isSadisticAction() {
			return true;
		}

		@Override
		public String getActionDescription() {
			return "Humiliate [npc2.name] with your larger cock.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			boolean dicksTouching = Main.sex.getCharacterOngoingSexArea(actor, SexAreaPenetration.PENIS).contains(target) 
				&& Main.sex.getCharacterOngoingSexArea(target, SexAreaPenetration.PENIS).contains(actor);
			float penisSizeRatio = (float)actor.getPenisRawSizeValue() / (float)target.getPenisRawSizeValue();
			int penisEnumSizeDifference = actor.getPenisSize().ordinal() - target.getPenisSize().ordinal();
			return (penisEnumSizeDifference > 0 || penisSizeRatio > 1.25)
				&& penisSizeRatio > 1.15
				&& !hasSmallPenis(target) && !hasSmallPenis(actor) 
				&& notDildoPenis(target) && dicksTouching;
		}

		@Override
		public String getDescription() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			String intro = UtilText.returnStringAtRandom(
					"Lining up [npc.her] [npc.cock+] against [npc2.hers], [npc2.namePos] [npc2.cock+] comes up comparitively short.",
					"[npc.Name] [npc.verb(pause)] to lay [npc.her] [npc.cock+] against [npc2.namePos] [npc2.cock], making the size difference apparent.",
					"[npc.Name] [npc.verb(take)] the time to make an extra-long thrust rubbing the entire length of [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock+]"
			);
			String mid = UtilText.returnStringAtRandom(
					"Seeing the size difference between [npc.her] [npc.cock+] and [npc2.her], [npc.name] [npc.verb(laughs)], saying ",
					"Continuing to rub [npc.her] [npc.cock] over [npc2.her] [npc2.cock], [npc.name] [npc.verb(decide)] to [npc.verb(rub)] it in, saying ",
					"[npc.Name] [npc.verb(give)] [npc2.him] a chuckle, saying "
			);
			String quote;
			switch (target.getPenisSize()) {
				case ZERO_MICROSCOPIC:
					quote =  UtilText.returnStringAtRandom(
						"That thing's practically a clit!",
						"I wasn't expecting much, but this is just pathetic!",
						"How's it feel, to have your little clit crushed against a real dick?",
						"I can barely feel that pathetic little bump.",
						"You should feel lucky, to get to feel a <i>real</i> cock for once."
					);
					break;
				case ONE_TINY:
					quote =  UtilText.returnStringAtRandom(
						"Nice try, little "+ (target.isFeminine()?"lady":"guy") + ", but that thing just doesn't measure up.",
						"<i>That?</i> That's just disappointing.",
						"You should feel lucky, to get to feel a <i>real</i> cock for once.",
						"Is that it?",
						"I wasn't expecting much, but this is just pathetic!",
						"'Fun Size', huh?", 
						"I bet you love seeing your little dick dominated like this!"
					);
					break;
				case TWO_AVERAGE:
					quote =  UtilText.returnStringAtRandom(
						"Not bad, but you're playing with the big "+(actor.isFeminine()?"girls":"boys")+" now.",
						"That's nothing special, take good look at <i>this</i>!",
						"Take a good look at a real "+(actor.isFeminine()?"woman's":"man's")+" cock!",
						"You should feel lucky, to get to feel a <i>real</i> cock for once.",
						"'Average' isn't anything to brag about.",
						"Is that it?",
						"'Average'? I see bigger cocks at on imps."
					);
					break;
				case FIVE_ENORMOUS:
				case SIX_GIGANTIC:
				case SEVEN_STALLION:
					quote = UtilText.returnStringAtRandom(
						"Not bad, but you're playing with the big "+(actor.isFeminine()?"girls":"boys")+" now.",
						"No matter how big you are, there's always someone bigger!",
						"No matter how big you are, but there's always someone better!",
						"No matter how big you are, but I'm bigger!",
						"Not bad, but I've seen bigger.",
						"You don't often see someone bigger than you, do you?"
					);
					break;
				case THREE_LARGE:
				case FOUR_HUGE:
				default:
					quote =  UtilText.returnStringAtRandom(
						"Not bad, but you're playing with the big "+(actor.isFeminine()?"girls":"boys")+" now.",
						"You're pretty big, but there's always someone bigger!",
						"You're pretty big, but there's always someone better!",
						"You're pretty big, but I'm bigger!",
						"Not bad, but I've seen bigger.",
						"Nice cock, "+(actor.isFeminine()?"filly":"colt")+", but you've got a ways to go."
					);
					break;
			}
			quote = "[npc.speech(" + quote + ")]";
			return String.join(" ", intro, mid, quote);
		}
	};
}
