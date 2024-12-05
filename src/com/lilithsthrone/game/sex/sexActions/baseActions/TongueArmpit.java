package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class TongueArmpit {
	
	public static final SexAction ARMPIT_LICKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Lick armpit";
		}
		@Override
		public String getActionDescription() {
			return "Slide your [npc.tongue] over [npc2.namePos] [npc2.armpit+] and start licking it.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently lifting up [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.armpit+], and after planting a series of soft kisses on it,"
									+ " [npc.she] [npc.verb(start)] sliding [npc.her] [npc.tongue+] over [npc2.her] exposed pit.",

							"Lifting up [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(plant)] a series of soft kisses on [npc2.namePos] [npc2.armpit+],"
									+ " before [npc.moaning+] as [npc.she] [npc.verb(start)] gently sliding [npc.her] [npc.tongue+] over [npc2.her] exposed pit."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly lifting up [npc2.namePos] [npc2.arm+(true)], [npc.name] desperately [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.armpit+], and after planting a series of passionate kisses on it,"
									+ " [npc.she] [npc.verb(start)] greedily sliding [npc.her] [npc.tongue+] over [npc2.her] exposed pit.",

							"Impatiently lifting up [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(plant)] a series of passionate kisses on [npc2.namePos] [npc2.armpit+],"
									+ " before [npc.moaning+] as [npc.she] [npc.verb(start)] greedily sliding [npc.her] [npc.tongue+] over [npc2.her] exposed pit."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking up [npc2.namePos] [npc2.arm+(true)], [npc.name] forcefully [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.armpit+], and after planting a series of dominant kisses on it,"
									+ " [npc.she] [npc.verb(start)] greedily sliding [npc.her] [npc.tongue+] over [npc2.her] exposed pit.",

							"Violently yanking up [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(plant)] a series of forceful kisses on [npc2.namePos] [npc2.armpit+],"
									+ " before [npc.moaning+] as [npc.she] [npc.verb(start)] dominantly sliding [npc.her] [npc.tongue+] over [npc2.her] exposed pit."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lifting up [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.armpit+], and after planting a series of kisses on it,"
									+ " [npc.she] [npc.verb(start)] sliding [npc.her] [npc.tongue+] over [npc2.her] exposed pit.",

							"Lifting up [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(plant)] a series of kisses on [npc2.namePos] [npc2.armpit+],"
									+ " before [npc.moaning+] as [npc.she] [npc.verb(start)] sliding [npc.her] [npc.tongue+] over [npc2.her] exposed pit."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response, gently pushing [npc2.her] [npc2.underarm] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",
	
								" Gently pushing [npc2.her] [npc2.underarm] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly pushing [npc2.her] [npc2.underarm] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",
	
								" Desperately pushing [npc2.her] [npc2.underarm] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, roughly thrusting [npc2.her] [npc2.underarm] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(order)] [npc.herHim] to keep going.",
	
								" Roughly thrusting [npc2.her] [npc2.underarm] out against [npc.namePos] [npc.face] in an eager response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, commanding [npc.herHim] to continue."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly pushing [npc2.her] [npc2.underarm] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",
	
								" Desperately pushing [npc2.her] [npc2.underarm] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, pushing [npc2.her] [npc2.underarm] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",
	
								" Bucking [npc2.her] [npc2.underarm] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] frantically [npc2.verb(try)] to wriggle away from [npc.namePos] unwanted oral attention, [npc2.sobbing] and squirming as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.herHim] alone.",
	
								" [npc2.A_sob+] bursts out from [npc2.namePos] mouth, and, struggling against [npc.name], [npc2.she] [npc2.verb(beg)] for [npc.herHim] to take [npc.her] [npc.tongue] away from [npc2.her] armpit."));
						break;
					default:
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
							" [npc2.Name] eagerly [npc2.verb(push)] back in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.armpit+] against [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly pushing back against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue servicing [npc2.her] [npc2.underarm].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.her] [npc2.underarm+] down against [npc.namePos] [npc.face],"
									+ " before eagerly begging [npc.herHim] to continue sliding [npc.her] [npc.tongue+] over [npc2.her] [npc2.armpit+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil away from [npc.namePos] unwelcome [npc.tongue],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.she] [npc.verb(continue)] to lick and kiss [npc2.her] [npc2.armpit+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to recoil away from [npc.namePos] [npc.tongue],"
									+ " struggling against [npc.herHim] as [npc.she] [npc.verb(continue)] to orally service [npc2.her] [npc2.armpit+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(push)] back in response,"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.armpit+] against [npc.namePos] [npc.lips+].",
		
							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, gently pushing back against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue servicing [npc2.her] [npc2.underarm].",
		
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(press)] [npc2.her] [npc2.underarm+] down against [npc.namePos] [npc.face],"
									+ " before eagerly begging [npc.herHim] to continue sliding [npc.her] [npc.tongue+] over [npc2.her] [npc2.armpit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(push)] back in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.armpit+] against [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly grinding against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.name] to continue servicing [npc2.her] [npc2.underarm].",
		
							" [npc2.Moaning] in delight, [npc2.name] forcefully [npc2.verb(press)] [npc2.her] [npc2.underarm+] down against [npc.namePos] [npc.face],"
									+ " before aggressively commanding [npc.herHim] to continue sliding [npc.her] [npc.tongue+] over [npc2.her] [npc2.armpit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] back in response,"
									+ " letting out [npc2.a_moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.armpit+] against [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, pushing back against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue servicing [npc2.her] [npc2.underarm].",
		
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.underarm+] down against [npc.namePos] [npc.face],"
									+ " before begging [npc.herHim] to continue sliding [npc.her] [npc.tongue+] over [npc2.her] [npc2.armpit+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ARMPIT_LICKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle armpit licking";
		}
		@Override
		public String getActionDescription() {
			return "Gently lick [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sliding [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.underarm+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.armpit+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Pulling [npc.her] [npc.tongue+] back into [npc.her] mouth, [npc.name] [npc.verb(start)] to gently kiss [npc2.namePos] [npc2.underarm+],"
							+ " before pressing forwards and slowly sliding [npc.her] [npc.tongue] over [npc2.her] [npc2.armpit+] once more.",

					"Sliding [npc.her] [npc.tongue+] back into [npc.her] mouth, [npc.name] [npc.verb(start)] slowly kissing and nuzzling against [npc2.namePos] [npc2.underarm+],"
							+ " before leaning forwards and gently continuing to lick [npc2.her] pit."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Armpit licking";
		}
		@Override
		public String getActionDescription() {
			return "Continue sliding your [npc.tongue] over [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sliding [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.underarm+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.armpit+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Pulling [npc.her] [npc.tongue+] back into [npc.her] mouth, [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc2.namePos] [npc2.underarm+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] over [npc2.her] [npc2.armpit+] once more.",

					"Sliding [npc.her] [npc.tongue+] back into [npc.her] mouth, [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc2.namePos] [npc2.underarm+],"
							+ " before leaning forwards and enthusiastically continuing to lick [npc2.her] pit."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough armpit licking";
		}
		@Override
		public String getActionDescription() {
			return "Roughly slide your tongue over [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly sliding [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.underarm+],"
							+ " [npc.name] [npc.verb(grind)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.armpit+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Pulling [npc.her] [npc.tongue+] back into [npc.her] mouth, [npc.name] [npc.verb(start)] to roughly kiss and lick [npc2.namePos] [npc2.underarm+],"
							+ " before pressing forwards and forcefully sliding [npc.her] [npc.tongue] over [npc2.her] [npc2.armpit+] once more.",

					"Sliding [npc.her] [npc.tongue+] back into [npc.her] mouth, [npc.name] [npc.verb(start)] forcefully kissing and licking [npc2.namePos] [npc2.underarm+],"
							+ " before leaning forwards and continuing to roughly lick [npc2.her] pit."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist armpit licking";
		}
		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.tongue] away from [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] [npc2.verb(continue)] gently pressing [npc2.her] [npc2.armpit+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] quickly [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.her] [npc2.underarm+],"
									+ " gently grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",

							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.underarm+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.armpit+] against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.armpit+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] violently [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] violently [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.her] [npc2.underarm+],"
									+ " roughly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",

							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.underarm+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] roughly [npc2.verb(grind)] [npc2.her] [npc2.armpit+] against [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] [npc2.verb(continue)] eagerly thrusting [npc2.her] [npc2.armpit+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] quickly [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.her] [npc2.underarm+],"
									+ " eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",

							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.underarm+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.armpit+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Armpit licking";
		}
		@Override
		public String getActionDescription() {
			return "Continue sliding your [npc.tongue] over [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Running [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.underarm+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.armpit+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Pulling [npc.her] [npc.tongue+] back into [npc.her] mouth, [npc.name] [npc.verb(start)] to kiss and lick [npc2.namePos] [npc2.underarm+],"
							+ " before pressing forwards and sliding [npc.her] [npc.tongue] over [npc2.her] [npc2.armpit+] once more.",

					"Sliding [npc.her] [npc.tongue+] back into [npc.her] mouth, [npc.name] [npc.verb(start)] kissing and nuzzling against [npc2.namePos] [npc2.underarm+],"
							+ " before leaning forwards and continuing to lick [npc2.her] pit."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager armpit licking";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly slide your [npc.tongue] over [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sliding [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.underarm+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.armpit+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Pulling [npc.her] [npc.tongue+] back into [npc.her] mouth, [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc2.namePos] [npc2.underarm+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] over [npc2.her] [npc2.armpit+] once more.",

					"Sliding [npc.her] [npc.tongue+] back into [npc.her] mouth, [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc2.namePos] [npc2.underarm+],"
							+ " before leaning forwards and enthusiastically continuing to lick [npc2.her] pit."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop armpit licking";
		}
		@Override
		public String getActionDescription() {
			return "Pull your [npc.tongue] away from [npc2.namePos] [npc2.armpit+] and stop licking it.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.armpit+].",
	
							"Giving [npc2.namePos] [npc2.armpit+] a final, rough kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.her] pit."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.armpit+].",
	
							"Giving [npc2.namePos] [npc2.armpit+] a final, wet kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.her] pit."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)] to struggle against [npc.name], [npc2.sobbing] and squirming in discomfort as [npc2.she] realise that [npc.name] isn't completely finished with [npc2.herHim] just yet.",
		
								" Realising that [npc.sheHasFull]n't completely finished with [npc2.herHim] just yet, [npc2.name] [npc2.verb(continue)] struggling and [npc2.sobbing],"
										+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.name] to let [npc2.herHim] go."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] takes [npc.her] [npc.tongue+] away from [npc2.her] [npc2.underarm+].",
								
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] [npc.verb(stop)] pleasuring [npc2.her] [npc2.armpit+], betraying [npc2.her] desire for more of [npc.namePos] attention."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Have armpit licked";
		}
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to start licking your [npc.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently lifting [npc.her] [npc.arm(true)] and pressing [npc.her] [npc.underarm+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] slowly grinding [npc.her] [npc.armpit+] over [npc2.her] [npc2.lips+].",

							"Lifting [npc.her] [npc.arm(true)], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] gently pressing [npc.her] [npc.armpit+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly lifting [npc.her] [npc.arm(true)] and pressing [npc.her] [npc.underarm+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically grinding [npc.her] [npc.armpit+] over [npc2.her] [npc2.lips+].",

							"Lifting [npc.her] [npc.arm(true)], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly pressing [npc.her] [npc.armpit+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly lifting [npc.her] [npc.arm(true)] and slamming [npc.her] [npc.underarm+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] forcefully grinding [npc.her] [npc.armpit+] over [npc2.her] [npc2.lips+].",
									
							"Lifting [npc.her] [npc.arm(true)], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly grinding [npc.her] [npc.armpit+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly lifting [npc.her] [npc.arm(true)] and pressing [npc.her] [npc.underarm+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically grinding [npc.her] [npc.armpit+] over [npc2.her] [npc2.lips+].",

							"Lifting [npc.her] [npc.arm(true)], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly pressing [npc.her] [npc.armpit+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lifting [npc.her] [npc.arm(true)] and pressing [npc.her] [npc.underarm+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] grinding [npc.her] [npc.armpit+] over [npc2.her] [npc2.lips+].",

							"Lifting [npc.her] [npc.arm(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] pressing [npc.her] [npc.armpit+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] slowly [npc2.verb(slide)] [npc2.her] [npc2.tongue+] over [npc.namePos] [npc.armpit+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] gently licking and kissing [npc.her] [npc.underarm+].",
	
								" Gently sliding [npc2.her] [npc2.tongue] out to deliver a long, slow lick to the [npc.underarm+] pressing down against [npc2.her] [npc2.lips+],"
										+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of tender kisses on [npc.namePos] [npc.armpit+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(slide)] [npc2.her] [npc2.tongue+] over [npc.namePos] [npc.armpit+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] happily licking and kissing [npc.her] [npc.underarm+].",
	
								" Greedily sliding [npc2.her] [npc2.tongue] out to deliver a long, wet lick to the [npc.underarm+] pressing down against [npc2.her] [npc2.lips+],"
										+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of passionate kisses on [npc.namePos] [npc.armpit+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] forcefully [npc2.verb(slide)] [npc2.her] [npc2.tongue+] over [npc.namePos] [npc.armpit+],"
										+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] roughly licking and kissing [npc.her] [npc.underarm+].",
	
								" Greedily thrusting [npc2.her] [npc2.tongue] out to deliver a rough, wet lick to the [npc.underarm+] pressing down against [npc2.her] [npc2.lips+],"
										+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of forceful kisses on [npc.namePos] [npc.armpit+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(slide)] [npc2.her] [npc2.tongue+] over [npc.namePos] [npc.armpit+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] happily licking and kissing [npc.her] [npc.underarm+].",
	
								" Greedily sliding [npc2.her] [npc2.tongue] out to deliver a long, wet lick to the [npc.underarm+] pressing down against [npc2.her] [npc2.lips+],"
										+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of passionate kisses on [npc.namePos] [npc.armpit+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(slide)] [npc2.her] [npc2.tongue+] over [npc.namePos] [npc.armpit+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] licking and kissing [npc.her] [npc.underarm+].",
	
								" Sliding [npc2.her] [npc2.tongue] out to deliver a long, wet lick to the [npc.underarm+] pressing down against [npc2.her] [npc2.lips+],"
										+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of kisses on [npc.namePos] [npc.armpit+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Struggling in desperation, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc.her] [npc.armpit+] down against [npc2.her] [npc2.lips+].",
	
								" [npc2.Sobbing] and struggling in distress, [npc2.namePos] protests prove to be in vain as [npc.name] [npc.verb(press)] [npc.her] [npc.underarm+] down over [npc2.her] [npc2.face]."));
						break;
					default:
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
							" [npc2.Name] greedily [npc2.verb(slide)] [npc2.her] [npc2.tongue+] over [npc.namePos] [npc.armpit+],"
									+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.her] pit.",
		
							" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth,"
									+ " and, eagerly pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm+], [npc2.she] [npc2.verb(start)] greedily sliding [npc2.her] [npc2.tongue+] over [npc.her] pit.",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm],"
									+ " before eagerly sliding [npc.her] [npc.tongue+] over [npc.her] [npc.armpit+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.face] away from [npc.namePos] [npc.underarm],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.she] [npc.verb(continue)] to force [npc.her] [npc.underarm+] against [npc2.her] [npc2.face+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to recoil away from [npc.namePos] [npc.underarm],"
									+ " struggling against [npc.herHim] as [npc.she] [npc.verb(continue)] to press [npc.her] [npc.armpit+] against [npc2.her] [npc2.lips+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.tongue+] over [npc.namePos] [npc.armpit+],"
									+ " letting out a soft, muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm+].",
		
							" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
									+ " and, gently pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm+], [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.tongue+] over [npc.her] pit.",
		
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm],"
									+ " before gently sliding [npc2.her] [npc2.tongue+] over [npc.her] [npc.armpit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] forcefully [npc2.verb(run)] [npc2.her] [npc2.tongue+] over [npc.namePos] [npc.armpit+],"
									+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm+].",
		
							" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
									+ " and, roughly grinding [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm+], [npc2.she] [npc2.verb(start)] violently sliding [npc2.her] [npc2.tongue+] over [npc.her] pit.",
		
							" [npc2.Moaning] in delight, [npc2.name] forcefully [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm],"
									+ " before aggressively sliding [npc2.her] [npc2.tongue+] over [npc.her] [npc.armpit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(slide)] [npc2.her] [npc2.tongue+] over [npc.namePos] [npc.armpit+],"
									+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm+].",
		
							" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth,"
									+ " and, pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm+], [npc2.she] [npc2.verb(start)] sliding [npc2.her] [npc2.tongue+] over [npc.her] pit.",
		
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.underarm],"
									+ " before sliding [npc2.her] [npc2.tongue+] over [npc.her] [npc.armpit+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle armpit licked";
		}
		@Override
		public String getActionDescription() {
			return "Gently press your [npc.underarm+] against [npc2.namePos] face in order to force [npc2.her] [npc2.tongue+] over your [npc.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] gently, yet firmly, [npc.verb(plant)] [npc.her] [npc.armpit+] down over [npc2.namePos] [npc2.lips+].",

					"With a soft [npc.moan], [npc.name] [npc.verb(press)] [npc.her] [npc.underarm+] against [npc2.namePos] [npc2.face+], before gently grinding [npc.her] [npc.armpit+] against [npc2.her] [npc2.lips+].",

					"Letting out a soft [npc.moan], [npc.name] gently [npc.verb(grind)] [npc.her] [npc.armpit+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Armpit licked";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly press your [npc.underarm+] down over [npc2.namePos] face in order to drive [npc2.her] [npc2.tongue+] over your [npc.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] eagerly [npc.verb(plant)] [npc.her] [npc.armpit+] down over [npc2.namePos] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.underarm+] against [npc2.namePos] [npc2.face+], before greedily grinding [npc.her] [npc.armpit+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] eagerly [npc.verb(grind)] [npc.her] [npc.armpit+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough armpit licked";
		}
		@Override
		public String getActionDescription() {
			return "Roughly grind your [npc.armpit+] down against [npc2.namePos] [npc2.tongue+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(grind)] [npc.her] [npc.armpit+] down over [npc2.namePos] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] roughly [npc.verb(force)] [npc.her] [npc.underarm+] against [npc2.namePos] [npc2.face+], before dominantly grinding [npc.her] [npc.armpit+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] roughly [npc.verb(grind)] [npc.her] [npc.armpit+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist armpit licked";
		}
		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.armpit+] away from [npc2.namePos] [npc2.tongue+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.underarm] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.tongue] over [npc.her] [npc.armpit+].",

							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.armpit+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] [npc2.verb(plant)] a soft kiss on [npc.her] [npc.underarm+],"
									+ " before gently sliding [npc2.her] [npc2.tongue+] over [npc.her] [npc.armpit+].",

							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.underarm+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] gently sliding [npc2.her] [npc2.tongue] over [npc.her] [npc.armpit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.underarm] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.name] roughly [npc2.verb(slide)] [npc2.her] [npc2.tongue] over [npc.her] [npc.armpit+].",

							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.armpit+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] [npc2.verb(plant)] a wet kiss on [npc.her] [npc.underarm+],"
									+ " before roughly sliding [npc2.her] [npc2.tongue+] over [npc.her] [npc.armpit+].",

							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.underarm+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] firmly holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] roughly sliding [npc2.her] [npc2.tongue] over [npc.her] [npc.armpit+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.underarm] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.name] greedily [npc2.verb(slide)] [npc2.her] [npc2.tongue] over [npc.her] [npc.armpit+].",

							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.armpit+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] [npc2.verb(plant)] a passionate kiss on [npc.her] [npc.underarm+],"
									+ " before greedily sliding [npc2.her] [npc2.tongue+] over [npc.her] [npc.armpit+].",

							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.underarm+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] eagerly sliding [npc2.her] [npc2.tongue] over [npc.her] [npc.armpit+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Armpit licked";
		}
		@Override
		public String getActionDescription() {
			return "Press your [npc.armpit+] down against [npc2.namePos] [npc2.face+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] firmly [npc.verb(plant)] [npc.her] [npc.armpit+] down over [npc2.namePos] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] [npc.underarm+] against [npc2.namePos] [npc2.face+], before grinding [npc.her] [npc.armpit+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] [npc.verb(grind)] [npc.her] [npc.armpit+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager armpit licked";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly grind your [npc.armpit+] down against [npc2.namePos] [npc2.tongue+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] eagerly [npc.verb(plant)] [npc.her] [npc.armpit+] down over [npc2.namePos] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.underarm+] against [npc2.namePos] [npc2.face+], before greedily grinding [npc.her] [npc.armpit+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] eagerly [npc.verb(grind)] [npc.her] [npc.armpit+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop armpit licked";
		}
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to take [npc2.her] [npc2.tongue+] away from your [npc.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc2.namePos] head away from [npc.her] [npc.armpit+], [npc.name] [npc.verb(order)] [npc2.herHim] to stop orally servicing [npc.herHim].",

							"Roughly grinding [npc.her] [npc.armpit+] over [npc2.namePos] [npc2.face] one last time, [npc.name] then [npc.verb(pull)] [npc.her] [npc.underarm] away."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling [npc2.namePos] head away from [npc.her] [npc.armpit+], [npc.name] [npc.verb(tell)] [npc2.herHim] to stop orally servicing [npc.herHim].",

							"Pressing [npc.her] [npc.armpit+] over [npc2.namePos] [npc2.face] one last time, [npc.name] then [npc.verb(pull)] [npc.her] [npc.underarm] away."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" With tears streaming down [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.nameIsFull]n't finished with [npc2.herHim] just yet.",
	
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] [npc2.verb(continue)] struggling against [npc.name], begging for [npc.herHim] to let [npc2.herHim] go."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to give [npc.namePos] [npc.armpit+] more of [npc2.her] oral attention.",
	
								" Still hungry for more, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(move)] away from [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
