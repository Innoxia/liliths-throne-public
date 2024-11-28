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
public class PenisArmpit {

	public static final SexAction ARMPIT_SEX_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Fuck armpit";
		}
		@Override
		public String getActionDescription() {
			return "Rub your [npc.cock] up and down against [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Eagerly(pulling)] [npc2.namePos] [npc2.arm(true)] up, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.her] now-exposed armpit,"
								+ " before [npc.eagerly] starting to rub [npc.her] [npc.cock+] up and down against it.",

					"[npc.Name] [npc.verb(pull)] [npc2.namePos] [npc2.arm(true)] up, before rubbing the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.her] [npc2.armpit+]."));

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.armpit],"
										+ " and with tears running down [npc2.her] [npc2.face], [npc2.she] desperately [npc2.verb(beg)] for [npc.herHim] to stop.",
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to pull away from [npc.name];"
										+ " tears run down [npc2.her] [npc2.face] as the unwelcome [npc.cock] slides against [npc2.her] [npc2.armpit]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.armpit],"
										+ " before [npc2.eagerly] pushing back and helping to rub [npc.her] [npc.cock+] over it.",
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly] sliding [npc2.her] [npc2.armpit] up and down against [npc.namePos] [npc.cock+]."));
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
							" [npc2.Name] [npc2.verb(start)] eagerly pushing [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.do] so.",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] eagerly [npc2.verb(slide)] [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(slide)] [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull [npc2.her] [npc2.arm(true)] away from [npc.namePos] [npc.cock],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to stop using [npc2.her] [npc2.armpit].",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to get away from [npc2.her] [npc2.armpit].",
		
							" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
									+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] [npc2.armpit]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] sliding [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.do] so.",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(slide)] [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] gently sliding [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.do] so.",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] gently [npc2.verb(slide)] [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] in delight, [npc2.name] softly [npc2.verb(slide)] [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] roughly sliding [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.do] so.",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] forcefully [npc2.verb(slide)] [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] in delight, [npc2.name] violently [npc2.verb(rub)] [npc2.her] [npc2.armpit+] up and down against [npc.namePos] [npc.cock+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ARMPIT_SEX_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle armpit fuck";
		}
		@Override
		public String getActionDescription() {
			return "Gently continue sliding your [npc.cock+] up and down against [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sliding [npc.her] [npc.cock+] up and down against [npc2.namePos] [npc2.armpit],"
							+ " [npc.name] [npc.verb(start)] steadily bucking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Gently rubbing [npc.her] [npc.cock+] up and down against [npc2.namePos] [npc2.armpit],"
							+ " [npc.name] [npc.verb(start)] softly thrusting [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Softly pushing [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] slowly [npc.verb(fuck)] [npc2.her] [npc2.armpit+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Armpit fuck";
		}
		@Override
		public String getActionDescription() {
			return "Continue sliding your [npc.cock+] up and down against [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.cock+] up and down against [npc2.namePos] [npc2.armpit],"
							+ " [npc.name] [npc.verb(start)] energetically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Desperately pushing [npc.her] [npc.cock+] up and down against [npc2.namePos] [npc2.armpit],"
							+ " [npc.name] [npc.verb(start)] frantically thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Greedily pushing [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to desperately pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] energetically [npc.verb(fuck)] [npc2.her] [npc2.armpit+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough armpit fuck";
		}
		@Override
		public String getActionDescription() {
			return "Continue roughly sliding your [npc.cock+] up and down against [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly thrusting [npc.her] [npc.cock+] up and down against [npc2.namePos] [npc2.armpit],"
							+ " [npc.name] [npc.verb(start)] dominantly pumping [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] forcefully [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Forcefully pushing [npc.her] [npc.cock+] up and down against [npc2.namePos] [npc2.armpit],"
							+ " [npc.name] [npc.verb(start)] violently thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] roughly [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Dominantly pushing [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to slam [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] roughly [npc.verb(fuck)] [npc2.her] [npc2.armpit+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Armpit fuck";
		}
		@Override
		public String getActionDescription() {
			return "Continue sliding your [npc.cock+] up and down against [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc.her] [npc.cock+] up and down against [npc2.namePos] [npc2.armpit],"
							+ " [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Pushing [npc.her] [npc.cock+] up and down against [npc2.namePos] [npc2.armpit],"
							+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] happily [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Pushing [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(fuck)] [npc2.her] [npc2.armpit+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager armpit fuck";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly continue sliding your [npc.cock+] up and down against [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.cock+] up and down against [npc2.namePos] [npc2.armpit],"
							+ " [npc.name] [npc.verb(start)] energetically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Desperately pushing [npc.her] [npc.cock+] up and down against [npc2.namePos] [npc2.armpit],"
							+ " [npc.name] [npc.verb(start)] frantically thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Greedily pushing [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to desperately pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] energetically [npc.verb(fuck)] [npc2.her] [npc2.armpit+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist armpit fuck";
		}
		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] away from [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] slowly, yet firmly, [npc2.verb(push)] [npc2.her] [npc2.armpit] against [npc.her] [npc.cock+].",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.name] firmly [npc2.verb(hold)] [npc.herHim] in place, gently pushing [npc2.her] [npc2.armpit] against [npc.her] [npc.cock+].",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.herHim] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests, slowly grinding [npc2.her] [npc2.armpit] against [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] violently [npc2.verb(grind)] [npc2.her] [npc2.armpit] against [npc.her] [npc.cock+].",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.she] dominantly hold [npc.herHim] in place,"
									+ " violently pushing [npc2.her] [npc2.armpit] against [npc.her] [npc.cock+].",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests, roughly grinding [npc2.her] [npc2.armpit] against [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.armpit] against [npc.her] [npc.cock+].",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.she] firmly hold [npc.herHim] in place, eagerly pushing [npc2.her] [npc2.armpit] against [npc.her] [npc.cock+].",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests, eagerly grinding [npc2.her] [npc2.armpit] against [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop armpit fuck";
		}
		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] away from [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] away from [npc2.namePos] [npc2.armpit+],"
									+ " [npc.name] dominantly [npc.verb(slide)] the [npc.cockHead] of [npc.her] [npc.cock] up and down against [npc2.her] [npc2.arm+(true)] one last time before pulling [npc.her] [npc.hips] back.",

							"Thrusting up against [npc2.namePos] [npc2.armpit+] one last time, [npc.name] then [npc2.verb(pull)] back, putting an end to the armpit fuck."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock+] away from [npc2.namePos] [npc2.armpit+],"
									+ " [npc.name] slaps the [npc.cockHead] of [npc.her] [npc.cock] against [npc2.her] [npc2.arm+(true)] one last time before pulling [npc.her] [npc.hips] back.",

							"Pushing up against [npc2.namePos] [npc2.armpit+] one last time, [npc.name] then [npc2.verb(pull)] back, putting an end to the armpit fuck."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] can't help but let out [npc2.sob+] as [npc.name] moves away,"
										+ " and [npc2.she] [npc2.verb(continue)] crying and protesting as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc.herHim] alone.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle against [npc.name], tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(beg)] to be left alone."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] back, eager for more of [npc.her] attention.",
	
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] lust for [npc.namePos] [npc.cock+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction ARMPIT_SEX_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Armpit fucked";
		}
		@Override
		public String getActionDescription() {
			return "Press your [npc.armpit] against [npc2.namePos] [npc2.cock+] and get [npc2.herHim] to start fucking it.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With [npc.a_moan+], [npc.name] [npc.verb(lift)] [npc.her] [npc.arm(true)] and [npc.eagerly] [npc.verb(press)] [npc.her] [npc.armpit+] against [npc2.namePos] [npc2.cock+],"
							+ " before [npc.eagerly] rubbing it up and down against [npc2.her] [npc2.cockHead+].",

					"Lifting up [npc.her] [npc.arm(true)], [npc.name] [npc.verb(bring)] [npc.her] [npc.armpit+] down to [npc2.namePos] groin, before [npc.eagerly] pressing [npc.her] [npc.armpit+] against [npc2.her] [npc2.cock+]."));

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc.her] [npc.armpit] against [npc2.her] [npc2.cock+],"
										+ " and struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull away.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc.her] [npc.armpit] against [npc2.her] [npc2.cock]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], [npc2.eagerly] bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] [npc2.eagerly] grinding against [npc.her] [npc.armpit].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(press)] [npc2.her] [npc2.cock+] against [npc.namePos] [npc.armpit+]."));
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
							" [npc2.Name] greedily [npc2.verb(grind)] [npc2.her] [npc2.cock+] against [npc.namePos] [npc.armpit+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.do] so.",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] eagerly thrusting [npc2.her] [npc2.cock+] against [npc.namePos] [npc.armpit].",
									
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(press)] [npc2.her] [npc2.cock+] against [npc.namePos] [npc.armpit+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.armpit],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] in against [npc.her] [npc.armpit+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.armpit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] against [npc.namePos] [npc.armpit+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.do] so.",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] against [npc.namePos] [npc.armpit].",
									
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(press)] [npc2.her] [npc2.cock+] up against [npc.namePos] [npc.armpit+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] against [npc.namePos] [npc.armpit+],"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.do] so.",
		
							" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] gently thrusting [npc2.her] [npc2.cock+] against [npc.namePos] [npc.armpit].",
									
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] up against [npc.namePos] [npc.armpit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] against [npc.namePos] [npc.armpit+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.do] so.",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] roughly grinding [npc2.her] [npc2.cock+] against [npc.namePos] [npc.armpit].",
									
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(thrust)] [npc2.her] [npc2.cock+] up against [npc.namePos] [npc.armpit+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ARMPIT_SEX_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle armpit fucked";
		}
		@Override
		public String getActionDescription() {
			return "Gently continue sliding your [npc.armpit+] up and down against [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] gently [npc.verb(slide)] [npc.her] [npc.armpit] up and down against [npc2.namePos] [npc2.cock+].",

					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] sliding [npc.her] [npc.armpit+] up and down against [npc2.namePos] [npc2.cock+].",

					"Slowly pressing [npc.her] [npc.armpit+] against [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(get)] [npc2.herHim] to fuck it."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Armpit fucked";
		}
		@Override
		public String getActionDescription() {
			return "Continue sliding your [npc.armpit+] up and down against [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] eagerly [npc.verb(slide)] [npc.her] [npc.armpit] up and down against [npc2.namePos] [npc2.cock+].",

					"With [npc.a_moan+], [npc.name] happily [npc.verb(start)] sliding [npc.her] [npc.armpit+] up and down against [npc2.namePos] [npc2.cock+].",

					"Eagerly pressing [npc.her] [npc.armpit+] down against [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(get)] [npc2.herHim] to fuck it."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough armpit fucked";
		}
		@Override
		public String getActionDescription() {
			return "Roughly continue sliding your [npc.armpit+] up and down against [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(grind)] [npc.her] [npc.armpit] up and down against [npc2.namePos] [npc2.cock+].",
					"With [npc.a_moan+], [npc.name] [npc.verb(start)] forcefully sliding [npc.her] [npc.armpit+] up and down against [npc2.namePos] [npc2.cock+].",
					"Roughly pressing [npc.her] [npc.armpit+] down against [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(get)] [npc2.herHim] to fuck it."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Armpit fucked";
		}
		@Override
		public String getActionDescription() {
			return "Continue sliding your [npc.armpit+] up and down against [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(slide)] [npc.her] [npc.armpit] up and down against [npc2.namePos] [npc2.cock+].",

					"With [npc.a_moan+], [npc.name] happily [npc.verb(start)] sliding [npc.her] [npc.armpit+] up and down against [npc2.namePos] [npc2.cock+].",

					"Pressing [npc.her] [npc.armpit+] down against [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(get)] [npc2.herHim] to fuck it."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager armpit fucked";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly continue sliding your [npc.armpit+] up and down against [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] eagerly [npc.verb(slide)] [npc.her] [npc.armpit] up and down against [npc2.namePos] [npc2.cock+].",

					"With [npc.a_moan+], [npc.name] happily [npc.verb(start)] sliding [npc.her] [npc.armpit+] up and down against [npc2.namePos] [npc2.cock+].",

					"Eagerly pressing [npc.her] [npc.armpit+] down against [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(get)] [npc2.herHim] to fuck it."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist armpit fucked";
		}
		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.armpit+] away from [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] gently humping [npc.her] [npc.armpit].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.armpit] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] slowly humping [npc.her] [npc.armpit].",

							"Trying desperately to pull [npc.her] [npc.armpit] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] gently sliding against [npc.her] [npc.armpit]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Y[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] eagerly humping [npc.her] [npc.armpit].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.armpit] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] eagerly humping [npc.her] [npc.armpit].",

							"Trying desperately to pull [npc.her] [npc.armpit] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] eagerly sliding against [npc.her] [npc.armpit]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] roughly humping [npc.her] [npc.armpit].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.armpit] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] roughly humping [npc.her] [npc.armpit].",

							"Trying desperately to pull [npc.her] [npc.armpit] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] roughly grinding against [npc.her] [npc.armpit]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop armpit fucked";
		}
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.cock+] away against your [npc.armpit+].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking [npc.her] [npc.armpit] away from [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out a menacing growl as [npc.she] [npc.verb(command)] [npc2.herHim] to leave [npc.her] [npc.arm(true)] alone.",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before quickly taking [npc.her] [npc.armpit] away from [npc2.her] [npc2.cock+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking [npc.her] [npc.armpit] away from [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(tell)] [npc2.herHim] to leave [npc.her] [npc.arm(true)] alone.",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before taking [npc.her] [npc.armpit] away from [npc2.her] [npc2.cock+]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.name] [npc.has]n't finished with [npc2.herHim] just yet.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to protest and struggle against [npc.name] as [npc.she] [npc.verb(hold)] [npc2.herHim] firmly in place."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] escapes from against [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue humping [npc.her] [npc.armpit]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
