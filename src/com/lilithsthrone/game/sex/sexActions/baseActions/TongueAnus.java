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
 * @since 0.1.88
 * @version 0.2.8
 * @author Innoxia
 */
public class TongueAnus {

	public static final SexAction ANILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Start anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.tongue] into [npc2.namePos] [npc2.asshole+] and start performing anilingus.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(plant)] a series soft kisses on [npc2.her] cheeks,"
									+ " before slowly, but firmly, sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.asshole+].",

							"Planting a series of soft kisses on [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.asshole+] a long, wet lick, before gently pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(plant)] a series of passionate kisses on [npc2.her] cheeks,"
									+ " before desperately sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.asshole+].",

							"Planting a series of passionate kisses on [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.asshole+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding [npc.her] [npc.lips+] against [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(plant)] a series of forceful kisses on [npc2.her] cheeks,"
									+ " before greedily sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.asshole+].",

							"Planting a series of forceful kisses on [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.asshole+] a rough lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(plant)] a series of passionate kisses on [npc2.her] cheeks,"
									+ " before desperately sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.asshole+].",

							"Planting a series of passionate kisses on [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.asshole+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(plant)] a series of kisses on [npc2.her] cheeks, before sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.asshole+].",

							"Planting a series of kisses on [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.asshole+] a wet lick, before pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response, gently bucking [npc2.her] [npc2.assCloaca] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",
	
								" Gently bucking [npc2.her] [npc2.assCloaca] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly bucking [npc2.her] [npc2.assCloaca] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",
	
								" Desperately bucking [npc2.her] [npc2.assCloaca] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, roughly thrusting [npc2.her] [npc2.assCloaca] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(order)] [npc.herHim] to keep going.",
	
								" Roughly thrusting [npc2.her] [npc2.assCloaca] out against [npc.namePos] [npc.face] in an eager response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, commanding [npc.herHim] to continue."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly bucking [npc2.her] [npc2.assCloaca] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",
	
								" Desperately bucking [npc2.her] [npc2.assCloaca] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, bucking [npc2.her] [npc2.assCloaca] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",
	
								" Bucking [npc2.her] [npc2.assCloaca] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] frantically [npc2.verb(try)] to wriggle away from [npc.namePos] unwanted oral attention, [npc2.sobbing] and squirming as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.herHim] alone.",
	
								" [npc2.A_sob+] bursts out from [npc2.namePos] mouth, and, struggling against [npc.name], [npc2.she] [npc2.verb(beg)] for [npc.herHim] to take [npc.her] [npc.tongue] away from [npc2.her] asshole."));
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
							" [npc2.Name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips] back in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.asshole+] against [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly bucking [npc2.her] [npc2.hips] back into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue servicing [npc2.her] [npc2.assCloaca].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.her] [npc2.assCloaca+] down against [npc.namePos] [npc.face],"
									+ " before eagerly begging [npc.herHim] to drive [npc.her] [npc.tongue+] as deep as possible into [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil [npc2.her] [npc2.assCloaca] away from [npc.namePos] unwelcome [npc.tongue],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.she] [npc.verb(continue)] to lick and kiss [npc2.her] [npc2.asshole+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to recoil away from [npc.namePos] [npc.tongue],"
									+ " struggling against [npc.herHim] as [npc.she] [npc.verb(continue)] to orally service [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips] back in response,"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.asshole+] against [npc.namePos] [npc.lips+].",
		
							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, gently bucking [npc2.her] [npc2.hips] back into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue servicing [npc2.her] [npc2.assCloaca].",
		
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(press)] [npc2.her] [npc2.assCloaca+] down against [npc.namePos] [npc.face],"
									+ " before eagerly begging [npc.herHim] to drive [npc.her] [npc.tongue+] as deep as possible into [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.hips] back in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.asshole+] against [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly slamming [npc2.her] [npc2.hips] back into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.name] to continue servicing [npc2.her] [npc2.assCloaca].",
		
							" [npc2.Moaning] in delight, [npc2.name] forcefully [npc2.verb(press)] [npc2.her] [npc2.assCloaca+] down against [npc.namePos] [npc.face],"
									+ " before aggressively commanding [npc.herHim] to drive [npc.her] [npc.tongue+] as deep as possible into [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] back in response,"
									+ " letting out [npc2.a_moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.asshole+] against [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, bucking [npc2.her] [npc2.hips] back into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue servicing [npc2.her] [npc2.assCloaca].",
		
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.assCloaca+] down against [npc.namePos] [npc.face],"
									+ " before begging [npc.herHim] to drive [npc.her] [npc.tongue+] as deep as possible into [npc2.her] [npc2.asshole+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ANILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently lick [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently driving [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.assCloaca+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.asshole+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Withdrawing [npc.her] [npc.tongue+] from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc2.namePos] [npc2.assCloaca+],"
							+ " before pressing forwards and slowly sliding [npc.her] [npc.tongue] into [npc2.her] [npc2.asshole+] once more.",

					"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] slowly kissing and nuzzling against [npc2.namePos] [npc2.assCloaca+],"
							+ " before leaning forwards and gently thrusting [npc.her] [npc.tongue] deep into [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.assCloaca+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.asshole+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Withdrawing [npc.her] [npc.tongue+] from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc2.namePos] [npc2.assCloaca+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] into [npc2.her] [npc2.asshole+] once more.",

					"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc2.namePos] [npc2.assCloaca+],"
							+ " before leaning forwards and enthusiastically thrusting [npc.her] [npc.tongue] deep into [npc2.her] [npc2.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your tongue into [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly thrusting [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.assCloaca+],"
							+ " [npc.name] [npc.verb(grind)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.asshole+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Withdrawing [npc.her] [npc.tongue+] from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to roughly kiss and lick [npc2.namePos] [npc2.assCloaca+],"
							+ " before pressing forwards and violently thrusting [npc.her] [npc.tongue] into [npc2.her] [npc2.asshole+] once more.",

					"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] forcefully kissing and licking [npc2.namePos] [npc2.assCloaca+],"
							+ " before leaning forwards and roughly thrusting [npc.her] [npc.tongue] deep into [npc2.her] [npc2.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.tongue] out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] [npc2.verb(continue)] gently pressing [npc2.her] [npc2.asshole+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] quickly [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.her] [npc2.assCloaca+],"
									+ " gently grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",

							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.assCloaca+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.asshole+] against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.asshole+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] violently [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] violently [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.her] [npc2.assCloaca+],"
									+ " roughly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",

							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.assCloaca+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] roughly [npc2.verb(grind)] [npc2.her] [npc2.asshole+] against [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] [npc2.verb(continue)] eagerly thrusting [npc2.her] [npc2.asshole+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] quickly [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.her] [npc2.assCloaca+],"
									+ " eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",

							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.assCloaca+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.asshole+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Driving [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.assCloaca+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.asshole+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Withdrawing [npc.her] [npc.tongue+] from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to kiss and lick [npc2.namePos] [npc2.assCloaca+],"
							+ " before pressing forwards and sliding [npc.her] [npc.tongue] into [npc2.her] [npc2.asshole+] once more.",

					"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] kissing and nuzzling against [npc2.namePos] [npc2.assCloaca+],"
							+ " before leaning forwards and thrusting [npc.her] [npc.tongue] deep into [npc2.her] [npc2.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [npc.tongue] into [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.assCloaca+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.asshole+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Withdrawing [npc.her] [npc.tongue+] from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc2.namePos] [npc2.assCloaca+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] into [npc2.her] [npc2.asshole+] once more.",

					"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc2.namePos] [npc2.assCloaca+],"
							+ " before leaning forwards and enthusiastically thrusting [npc.her] [npc.tongue] deep into [npc2.her] [npc2.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.tongue] out of [npc2.namePos] [npc2.asshole+] and stop performing anilingus.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.asshole+].",
	
							"Giving [npc2.namePos] [npc2.asshole+] a final, rough kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.her] [npc2.assCloaca+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.asshole+].",
	
							"Giving [npc2.namePos] [npc2.asshole+] a final, wet kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.her] [npc2.assCloaca+]."));
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
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] takes [npc.her] [npc.tongue+] away from [npc2.her] [npc2.assCloaca+].",
								
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] [npc.verb(stop)] pleasuring [npc2.her] [npc2.asshole+], betraying [npc2.her] desire for more of [npc.namePos] attention."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_ANILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to start licking your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pressing [npc.her] [npc.assCloaca+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] slowly grinding [npc.her] [npc.asshole+] down on [npc2.her] [npc2.lips+].",

							"Shifting [npc.her] [npc.hips] so that [npc2.namePos] [npc2.face] is forced into [npc.her] [npc.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] gently pressing [npc.her] [npc.asshole+] down against [npc2.her] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.assCloaca+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically grinding [npc.her] [npc.asshole+] down on [npc2.her] [npc2.lips+].",

							"Shifting [npc.her] [npc.hips] so that [npc2.namePos] [npc2.face] is forced into [npc.her] [npc.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly pressing [npc.her] [npc.asshole+] down against [npc2.her] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming [npc.her] [npc.assCloaca+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] forcefully grinding [npc.her] [npc.asshole+] down on [npc2.her] [npc2.lips+].",

							"Shifting [npc.her] [npc.hips] so that [npc2.namePos] [npc2.face] is forced into [npc.her] [npc.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly grinding [npc.her] [npc.asshole+] down against [npc2.her] [npc2.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.assCloaca+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically grinding [npc.her] [npc.asshole+] down on [npc2.her] [npc2.lips+].",

							"Shifting [npc.her] [npc.hips] so that [npc2.namePos] [npc2.face] is forced into [npc.her] [npc.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly pressing [npc.her] [npc.asshole+] down against [npc2.her] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.assCloaca+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] grinding [npc.her] [npc.asshole+] down on [npc2.her] [npc2.lips+].",

							"Shifting [npc.her] [npc.hips] so that [npc2.namePos] [npc2.face] is forced into [npc.her] [npc.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] pressing [npc.her] [npc.asshole+] down against [npc2.her] [npc2.lips+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] slowly [npc2.verb(slide)] [npc2.her] [npc2.tongue+] into [npc.namePos] [npc.asshole+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] gently licking and kissing [npc.her] [npc.assCloaca+].",
	
								" Gently sliding [npc2.her] [npc2.tongue] out to deliver a long, slow lick to the [npc.assCloaca+] pressing down against [npc2.her] [npc2.lips+],"
										+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of tender kisses on [npc.namePos] [npc.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(slide)] [npc2.her] [npc2.tongue+] into [npc.namePos] [npc.asshole+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] happily licking and kissing [npc.her] [npc.assCloaca+].",
	
								" Greedily sliding [npc2.her] [npc2.tongue] out to deliver a long, wet lick to the [npc.assCloaca+] pressing down against [npc2.her] [npc2.lips+],"
										+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of passionate kisses on [npc.namePos] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] forcefully [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] into [npc.namePos] [npc.asshole+],"
										+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] roughly licking and kissing [npc.her] [npc.assCloaca+].",
	
								" Greedily thrusting [npc2.her] [npc2.tongue] out to deliver a rough, wet lick to the [npc.assCloaca+] pressing down against [npc2.her] [npc2.lips+],"
										+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of forceful kisses on [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(slide)] [npc2.her] [npc2.tongue+] into [npc.namePos] [npc.asshole+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] happily licking and kissing [npc.her] [npc.assCloaca+].",
	
								" Greedily sliding [npc2.her] [npc2.tongue] out to deliver a long, wet lick to the [npc.assCloaca+] pressing down against [npc2.her] [npc2.lips+],"
										+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of passionate kisses on [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(slide)] [npc2.her] [npc2.tongue+] into [npc.namePos] [npc.asshole+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] licking and kissing [npc.her] [npc.assCloaca+].",
	
								" Sliding [npc2.her] [npc2.tongue] out to deliver a long, wet lick to the [npc.assCloaca+] pressing down against [npc2.her] [npc2.lips+],"
										+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of kisses on [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Struggling in desperation, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc.her] [npc.asshole+] down against [npc2.her] [npc2.lips+].",
	
								" [npc2.Sobbing] and struggling in distress, [npc2.namePos] protests prove to be in vain as [npc.name] [npc.verb(press)] [npc.her] [npc.assCloaca+] down over [npc2.her] [npc2.face]."));
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
							" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.asshole+],"
									+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca+].",
		
							" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth,"
									+ " and, eagerly pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca+], [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.asshole+].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca],"
									+ " before eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.face] away from [npc.namePos] [npc.assCloaca],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.she] [npc.verb(continue)] to force [npc.her] [npc.assCloaca+] down against [npc2.her] [npc2.face+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to recoil away from [npc.namePos] [npc.assCloaca],"
									+ " struggling against [npc.herHim] as [npc.she] [npc.verb(continue)] to press [npc.her] [npc.asshole+] against [npc2.her] [npc2.lips+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.asshole+],"
									+ " letting out a soft, muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca+].",
		
							" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
									+ " and, gently pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca+], [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.asshole+].",
		
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca],"
									+ " before gently sliding [npc2.her] [npc2.tongue+] as deep as possible into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.asshole+],"
									+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca+].",
		
							" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
									+ " and, roughly grinding [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca+], [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.asshole+].",
		
							" [npc2.Moaning] in delight, [npc2.name] forcefully [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca],"
									+ " before aggressively thrusting [npc2.her] [npc2.tongue+] as deep as possible into [npc.her] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.asshole+],"
									+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca+].",
		
							" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth,"
									+ " and, pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca+], [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.asshole+].",
		
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.assCloaca],"
									+ " before driving [npc2.her] [npc2.tongue+] as deep as possible into [npc.her] [npc.asshole+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_ANILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently receive anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently press your [npc.assCloaca+] down over [npc2.namePos] face in order to drive [npc2.her] [npc2.tongue+] into your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pressing [npc.her] [npc.assCloaca+] down over [npc2.namePos] [npc2.face+],"
							+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] firmly [npc.verb(plant)] [npc.her] [npc.asshole+] down over [npc2.her] [npc2.lips+].",

					"With a soft [npc.moan], [npc.name] [npc.verb(press)] [npc.her] [npc.assCloaca+] against [npc2.namePos] [npc2.face+], before gently grinding [npc.her] [npc.asshole+] against [npc2.her] [npc2.lips+].",

					"Letting out a soft [npc.moan], [npc.name] gently [npc.verb(grind)] [npc.her] [npc.asshole+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly press your [npc.assCloaca+] down over [npc2.namePos] face in order to drive [npc2.her] [npc2.tongue+] into your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pressing [npc.her] [npc.assCloaca+] down over [npc2.namePos] [npc2.face+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] firmly [npc.verb(plant)] [npc.her] [npc.asshole+] down over [npc2.her] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.assCloaca+] against [npc2.namePos] [npc2.face+], before greedily grinding [npc.her] [npc.asshole+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] eagerly [npc.verb(grind)] [npc.her] [npc.asshole+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly receive anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Roughly grind your [npc.asshole+] down against [npc2.namePos] [npc2.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly pressing [npc.her] [npc.assCloaca+] down over [npc2.namePos] [npc2.face+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] violently [npc.verb(slam)] [npc.her] [npc.asshole+] down over [npc2.her] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] roughly [npc.verb(slam)] [npc.her] [npc.assCloaca+] against [npc2.namePos] [npc2.face+], before forcefully grinding [npc.her] [npc.asshole+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] roughly [npc.verb(grind)] [npc.her] [npc.asshole+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist receiving anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.asshole+] away from [npc2.namePos] [npc2.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.tongue] deep into [npc.her] [npc.asshole+].",

							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.asshole+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] [npc2.verb(plant)] a soft kiss on [npc.her] [npc.assCloaca+],"
									+ " before gently sliding [npc2.her] [npc2.tongue+] deep into [npc.her] [npc.asshole+].",

							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.assCloaca+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] gently thrusting [npc2.her] [npc2.tongue] into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.name] roughly [npc2.verb(slide)] [npc2.her] [npc2.tongue] deep into [npc.her] [npc.asshole+].",

							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.asshole+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] [npc2.verb(plant)] a wet kiss on [npc.her] [npc.assCloaca+],"
									+ " before roughly thrusting [npc2.her] [npc2.tongue+] deep into [npc.her] [npc.asshole+].",

							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.assCloaca+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] firmly holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] roughly thrusting [npc2.her] [npc2.tongue] into [npc.her] [npc.asshole+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.name] greedily [npc2.verb(slide)] [npc2.her] [npc2.tongue] deep into [npc.her] [npc.asshole+].",

							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.asshole+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] [npc2.verb(plant)] a passionate kiss on [npc.her] [npc.assCloaca+],"
									+ " before greedily sliding [npc2.her] [npc2.tongue+] deep into [npc.her] [npc.asshole+].",

							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.assCloaca+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] eagerly thrusting [npc2.her] [npc2.tongue] into [npc.her] [npc.asshole+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_ANILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Press your [npc.asshole+] down against [npc2.namePos] [npc2.face+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pressing [npc.her] [npc.assCloaca+] down over [npc2.namePos] [npc2.face+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] firmly [npc.verb(plant)] [npc.her] [npc.asshole+] down over [npc2.her] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] [npc.assCloaca+] against [npc2.namePos] [npc2.face+], before grinding [npc.her] [npc.asshole+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] [npc.verb(grind)] [npc.her] [npc.asshole+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly receive anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly grind your [npc.asshole+] down against [npc2.namePos] [npc2.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pressing [npc.her] [npc.assCloaca+] down over [npc2.namePos] [npc2.face+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] firmly [npc.verb(plant)] [npc.her] [npc.asshole+] down over [npc2.her] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.assCloaca+] against [npc2.namePos] [npc2.face+], before greedily grinding [npc.her] [npc.asshole+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] eagerly [npc.verb(grind)] [npc.her] [npc.asshole+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop receiving anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.tongue+] out of your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc2.namePos] head away from [npc.her] [npc.asshole+], [npc.name] [npc.verb(order)] [npc2.herHim] to stop performing anilingus on [npc.herHim].",

							"Roughly grinding [npc.her] [npc.asshole+] into [npc2.namePos] [npc2.face] one last time, [npc.name] then [npc.verb(pull)] [npc.her] [npc.assCloaca] away, putting an end to the ongoing anilingus."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling [npc2.namePos] head away from [npc.her] [npc.asshole+], [npc.name] [npc.verb(tell)] [npc2.herHim] to stop performing anilingus on [npc.herHim].",

							"Pressing [npc.her] [npc.asshole+] into [npc2.namePos] [npc2.face] one last time, [npc.name] then [npc.verb(pull)] [npc.her] [npc.assCloaca] away, putting an end to the ongoing anilingus."));
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
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to give [npc.namePos] [npc.asshole+] more of [npc2.her] oral attention.",
	
								" Still hungry for more, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(move)] away from [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
