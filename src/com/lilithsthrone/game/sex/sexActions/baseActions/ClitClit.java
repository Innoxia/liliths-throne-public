package com.lilithsthrone.game.sex.sexActions.baseActions;

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
 * @since 0.3.3
 * @version 0.3.3
 * @author Innoxia
 */
public class ClitClit {
	
	public static final SexAction TRIBBING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Start tribbing";
		}

		@Override
		public String getActionDescription() {
			return "Start grinding your [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a slow thrust of [npc.her] [npc.hips+], [npc.name] [npc.verb(press)] [npc.her] [npc.clit+] against [npc2.namePos] [npc2.pussy+], before starting to rhythmically grind up and down against [npc2.her] [npc2.labia+].",
							"With [npc.a_moan+], [npc.name] slowly [npc.verb(press)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to grind [npc.her] [npc.clit+] up and down over [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a rough buck of [npc.her] [npc.hips+], [npc.name] [npc.verb(slam)] [npc.her] [npc.clit+] against [npc2.namePos] [npc2.pussy+], before starting to forcefully grind up and down against [npc2.her] [npc2.labia+].",
							"With [npc.a_moan+], [npc.name] roughly [npc.verb(slam)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to forcefully grind [npc.her] [npc.clit+] up and down over [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With an eager thrust of [npc.her] [npc.hips+], [npc.name] [npc.verb(bump)] [npc.her] [npc.clit+] against [npc2.namePos] [npc2.pussy+], before starting to enthusiastically grind up and down against [npc2.her] [npc2.labia+].",
							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to frantically grind [npc.her] [npc.clit+] up and down over [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a thrust of [npc.her] [npc.hips+], [npc.name] [npc.verb(bump)] [npc.her] [npc.clit+] against [npc2.namePos] [npc2.pussy+], before starting to grind up and down against [npc2.her] [npc2.labia+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to grind [npc.her] [npc.clit+] up and down over [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Gently bucking [npc2.her] own [npc2.hips] in response,"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and softly [npc2.verb(rub)] [npc2.her] [npc2.clit+] against [npc.her] [npc.pussy+].",
								" Responding with a gentle buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to softly rub [npc2.her] [npc2.clit+] up and down over [npc.her] [npc.labia+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Roughly bucking [npc2.her] own [npc2.hips] in response,"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and forcefully [npc2.verb(grind)] [npc2.her] [npc2.clit+] against [npc.her] [npc.pussy+].",
								" Responding with a violent buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to roughly grind [npc2.her] [npc2.clit+] up and down over [npc.her] [npc.labia+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Happily bucking [npc2.her] own [npc2.hips] in response,"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and readily [npc2.verb(rub)] [npc2.her] [npc2.clit+] against [npc.her] [npc.pussy+].",
								" Responding with a happy buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to frantically rub [npc2.her] [npc2.clit+] up and down over [npc.her] [npc.labia+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Bucking [npc2.her] own [npc2.hips] in response,"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and [npc2.verb(rub)] [npc2.her] [npc2.clit+] against [npc.her] [npc.pussy+].",
								" Responding with a buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to rub [npc2.her] [npc2.clit+] up and down over [npc.her] [npc.labia+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying to pull away,"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(plead)] for [npc.name] to get away from [npc2.herHim] and leave [npc2.her] [npc2.pussy] alone.",
								" Responding by frantically recoiling from [npc.namePos] unwanted advance, [npc2.name] [npc2.verb(start)] pleading to be left alone, all the while trying to pull [npc2.her] [npc2.pussy] away from [npc.namePos]."));
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
							" [npc2.Name] enthusiastically [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(rub)] [npc2.her] own [npc2.clit] up and down over [npc.namePos] [npc.pussy+].",
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.pussy] against [npc.namePos].",
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out, using [npc.namePos] movements to help grind [npc2.her] own [npc2.clit+] against [npc.her] [npc.vagina+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] [npc.pussy], [npc2.name] desperately [npc2.verb(beg)] for [npc.name] to get away from [npc2.her] [npc2.vagina+].",
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away from [npc2.her] [npc2.pussy].",
							" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
									+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] [npc2.vagina+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(rub)] [npc2.her] own [npc2.clit] up and down over [npc.namePos] [npc.pussy+].",
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.pussy] against [npc.namePos].",
							" [npc2.Moaning] in pleasure, [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out, using [npc.namePos] movements to help grind [npc2.her] own [npc2.clit+] against [npc.her] [npc.vagina+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] happily [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] softly [npc2.verb(rub)] [npc2.her] own [npc2.clit] up and down over [npc.namePos] [npc.pussy+].",
							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, gently thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.pussy] against [npc.namePos].",
							" [npc2.Moaning] in pleasure, [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.hips+] out, using [npc.namePos] movements to help grind [npc2.her] own [npc2.clit+] against [npc.her] [npc.vagina+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] forcefully [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(grind)] [npc2.her] own [npc2.clit] up and down over [npc.namePos] [npc.pussy+].",
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting out [npc2.her] [npc2.hips], [npc2.she] forcefully [npc2.verb(grind)] [npc2.her] [npc2.pussy] against [npc.namePos].",
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out, using [npc.namePos] movements to help force [npc2.her] own [npc2.clit+] against [npc.her] [npc.vagina+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TRIBBING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle tribbing";
		}

		@Override
		public String getActionDescription() {
			return "Gently slide your [npc.clit] up and down over [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out a little [npc.moan] with every thrust of [npc.her] [npc.hips], [npc.name] gently [npc.verb(rub)] [npc.her] [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].",
					"[npc.Name] [npc.verb(let)] out a series of soft sighs as [npc.she] gently [npc.verb(rub)] [npc.her] [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].",
					"Sliding [npc.her] [npc.clit+] over [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] out a little [npc.moan] with each pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tribbing";
		}

		@Override
		public String getActionDescription() {
			return "Continue grinding your [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+] with each eager thrust of [npc.her] [npc.hips], [npc.name] passionately [npc.verb(rub)] [npc.her] [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].",
					"[npc.Name] [npc.verb(let)] out a series of lewd [npc.moans] as [npc.she] desperately [npc.verb(grind)] [npc.her] [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].",
					"Eagerly grinding [npc.her] [npc.clit+] over [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] out [npc.a_moan+] with each frantic pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough tribbing";
		}

		@Override
		public String getActionDescription() {
			return "Continue roughly grinding your [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+] with each forceful thrust of [npc.her] [npc.hips], [npc.name] roughly [npc.verb(grind)] [npc.her] [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].",
					"[npc.Name] [npc.verb(let)] out a series of lewd [npc.moans] as [npc.she] roughly [npc.verb(grind)] [npc.her] [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].",
					"Violently grinding [npc.her] [npc.clit+] over [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] out [npc.a_moan+] with each forceful pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tribbing";
		}

		@Override
		public String getActionDescription() {
			return "Continue rubbing your [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+] with each thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(continue)] to rub [npc.her] [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].",
					"[npc.Name] [npc.verb(let)] out a series of lewd [npc.moans] as [npc.she] [npc.verb(rub)] [npc.her] [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].",
					"Rubbing [npc.her] [npc.clit+] over [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] out [npc.a_moan+] with each pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager tribbing";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly grind your [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+] with each eager thrust of [npc.her] [npc.hips], [npc.name] passionately [npc.verb(rub)] [npc.her] [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].",
					"[npc.Name] [npc.verb(let)] out a series of lewd [npc.moans] as [npc.she] desperately [npc.verb(grind)] [npc.her] [npc.clit+] up and down over [npc2.namePos] [npc2.vagina+].",
					"Eagerly grinding [npc.her] [npc.clit+] over [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] out [npc.a_moan+] with each frantic pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TRIBBING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist tribbing";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.pussy] away from [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] to pull [npc.her] groin away from [npc2.nameHers],"
										+ " but [npc.her] efforts prove to be in vain as [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, keeping [npc2.her] [npc2.clit+] gently pressed against [npc.her] [npc.pussy+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.nameHers],"
										+ " but [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, before continuing to gently rub [npc2.her] [npc2.clit+] against [npc.her] [npc.vagina+].",
								"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.nameHers],"
										+ " but [npc2.she] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] gently [npc2.verb(rub)] [npc2.her] [npc2.vagina+] up against [npc2.hers]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] to pull [npc.her] groin away from [npc2.nameHers],"
										+ " but [npc.her] efforts prove to be in vain as [npc2.she] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips] forwards, keeping [npc2.her] [npc2.clit+] forcefully ground against [npc.her] [npc.pussy+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.nameHers],"
										+ " but [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, before continuing to roughly grind [npc2.her] [npc2.clit+] against [npc.her] [npc.vagina+].",
								"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.nameHers],"
										+ " but [npc2.she] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] forcefully [npc2.verb(grind)] [npc2.her] [npc2.vagina+] up against [npc2.hers]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] to pull [npc.her] groin away from [npc2.nameHers],"
										+ " but [npc.her] efforts prove to be in vain as [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, keeping [npc2.her] [npc2.clit+] pressed against [npc.her] [npc.pussy+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.nameHers],"
										+ " but [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, before continuing to eagerly rub [npc2.her] [npc2.clit+] against [npc.her] [npc.vagina+].",
								"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.nameHers],"
										+ " but [npc2.she] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] enthusiastically [npc2.verb(rub)] [npc2.her] [npc2.vagina+] up against [npc2.hers]."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] to pull [npc.her] groin away from [npc2.nameHers],"
								+ " but [npc.her] efforts prove to be in vain as [npc2.she] [npc2.verb(move)] forwards, keeping [npc2.her] [npc2.clit+] pressed against [npc.her] [npc.pussy+].",
						"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.nameHers],"
								+ " but [npc2.she] quickly [npc2.verb(buck)] [npc2.her] [npc2.hips] forwards, causing [npc2.her] [npc2.clit+] to rub against [npc.her] [npc.vagina+].",
						"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.nameHers],"
								+ " but [npc2.she] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(keep)] [npc2.her] [npc2.vagina+] pressed against [npc2.hers]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TRIBBING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop tribbing";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.clit+] away from [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough thrust, [npc.name] [npc.verb(pull)] [npc.her] groin away from [npc2.namePos] [npc2.pussy+], putting an end to the tribbing.",
							"Roughly grinding [npc.her] [npc.clit+] over [npc2.namePos] [npc2.pussy] one last time, [npc.name] then [npc.verb(pull)] back, putting an end to the rough tribbing."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last buck of [npc.her] [npc.hips], [npc.name] [npc.verb(pull)] [npc.her] groin away from [npc2.namePos] [npc2.pussy+], putting an end to the tribbing.",
							"Rubbing [npc.her] [npc.clit+] over [npc2.namePos] [npc2.pussy] one last time, [npc.name] then [npc.verb(pull)] back, putting an end to the tribbing."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Although happy to have [npc2.her] [npc2.pussy] released, [npc2.name] [npc2.verb(continue)] crying and weakly struggling against [npc.name] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.herHim] alone.",
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.vagina+] away from [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] back, signalling [npc2.her] desire for more attention.",
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PUSSY_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tribbing massage";
		}

		@Override
		public String getActionDescription() {
			return "Use your prehensile clit to massage and rub at [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.PREHENSILE) && Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.DOM_ROUGH;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] [npc.verb(concentrate)] on using the prehensility of [npc.her] [npc.clit] to rub at [npc2.namePos] [npc2.pussy+], before moving up to massage and squeeze down on [npc2.her] own [npc2.clit+].",
					isTargetedCharacterInanimate()
						?null
						:"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(focus)] on controlling [npc.her] prehensile [npc.clit]."
							+ " Rubbing and stroking [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(grin)] as [npc.she] then [npc.verb(move)] up to massage [npc2.her] own [npc2.clit+], causing [npc2.herHim] to let out [npc2.a_moan+].",
					"[npc.Name] [npc.verb(grin)] in delight as [npc.she] [npc.verb(use)] [npc.her] prehensile clit to massage and stroke [npc2.namePos] [npc2.labia+], before moving up to squeeze and rub at [npc2.her] own [npc2.clit+].",
					"With [npc.a_moan+], [npc.name] [npc.verb(focus)] on controlling [npc.her] prehensile [npc.clit], pressing down and massaging [npc2.namePos] [npc2.labia+], before moving up to rub at [npc2.her] [npc2.clit+].");
		}
	};
}
