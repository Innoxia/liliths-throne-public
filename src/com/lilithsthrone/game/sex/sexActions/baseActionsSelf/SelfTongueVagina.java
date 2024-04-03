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
public class SelfTongueVagina {

	public static final SexAction SELF_CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isTaur()
					&& Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
							|| Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}
		@Override
		public String getActionTitle() {
			return "Start autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Slide your [npc.tongue] into your [npc.pussy+] and start performing cunnilingus on yourself.";
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
							"[npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] own [npc.pussy]."
							+ " Planting a series of soft kisses on [npc.her] [npc.labia+], [npc.she] slowly, but firmly, [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.pussy+].",

							"[npc.verb(plant)] a series of soft kisses on [npc.her] [npc.labia+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.pussy+] a long, wet lick, before gently pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"eagerly [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] own [npc.pussy]."
							+ " Planting a series of passionate kisses on [npc.her] [npc.labia+], [npc.she] desperately [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.pussy+].",

							"[npc.verb(plant)] a series of passionate kisses on [npc.her] [npc.labia+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.pussy+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"roughly [npc.verb(grind)] [npc.her] [npc.lips+] against [npc.her] own [npc.pussy]."
							+ " Planting a series of forceful kisses on [npc.her] [npc.labia+], [npc.she] greedily [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.pussy+].",

							"[npc.verb(plant)] a series of forceful kisses on [npc.her] [npc.labia+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.pussy+] a rough lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"eagerly [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] own [npc.pussy]."
							+ " Planting a series of passionate kisses on [npc.her] [npc.labia+], [npc.she] desperately [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.pussy+].",

							"[npc.verb(plant)] a series of passionate kisses on [npc.her] [npc.labia+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.pussy+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] own [npc.pussy]."
							+ " Planting a series of kisses on [npc.her] [npc.labia+], [npc.she] [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.pussy+].",

							"[npc.verb(plant)] a series of kisses on [npc.her] [npc.labia+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.pussy+] a wet lick, before pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Gently lick your own [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.pussy+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.labia+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc.her] [npc.labia+],"
							+ " before pressing forwards and slowly sliding [npc.her] [npc.tongue] into [npc.her] [npc.pussy+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] slowly kissing and nuzzling against [npc.her] [npc.labia+],"
							+ " before once again gently thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into your [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.pussy+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.labia+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc.her] [npc.labia+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] into [npc.her] [npc.pussy+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc.her] [npc.labia+],"
							+ " before once again enthusiastically thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Roughly thrust your tongue into your own [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly thrusting [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.pussy+],"
							+ " [npc.name] [npc.verb(grind)] [npc.her] [npc.lips+] against [npc.her] [npc.labia+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] to roughly kiss and lick [npc.her] [npc.labia+],"
							+ " before pressing forwards and violently thrusting [npc.her] [npc.tongue] into [npc.her] [npc.pussy+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] forcefully kissing and licking [npc.her] [npc.labia+],"
							+ " before once again roughly thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.pussy+]."));
		
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into your own [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.pussy+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.labia+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] to kiss and lick [npc.her] [npc.labia+],"
							+ " before pressing forwards and sliding [npc.her] [npc.tongue] into [npc.her] [npc.pussy+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] kissing and nuzzling against [npc.her] [npc.labia+],"
							+ " before once again thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [npc.tongue] into your own [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.pussy+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.labia+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc.her] [npc.labia+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] into [npc.her] [npc.pussy+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc.her] [npc.labia+],"
							+ " before once again enthusiastically thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Stop autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Pull your [npc.tongue] out of your [npc.pussy+] and stop performing autocunnilingus.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.pussy+].",
	
							"Giving [npc.her] [npc.labia+] a final, rough kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.pussy+].",
	
							"Giving [npc.her] [npc.labia+] a final, wet kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
