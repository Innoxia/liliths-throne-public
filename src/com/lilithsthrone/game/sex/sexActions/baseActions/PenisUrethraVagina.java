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
 * @since 0.1.82
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisUrethraVagina {
	
	public static final SexAction VAGINAL_URETHRA_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Vaginal urethra fuck";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+] and start fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly pushing forwards, sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.vaginaUrethra+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " and with a slow, steady pressure, [npc.she] gently [npc.verb(sink)] it deep into [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.vaginaUrethra+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+], "
									+ "and with a determined thrust, [npc.she] eagerly [npc.verb(sink)] it deep into [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming forwards, forcing [npc.her] [npc.cock+] deep into [npc2.her] [npc2.vaginaUrethra+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " and with a forceful thrust, [npc.she] roughly [npc.verb(slam)] it deep into [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.vaginaUrethra+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " and with a determined thrust, [npc.she] eagerly [npc.verb(sink)] it deep into [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.vaginaUrethra+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " and with a little thrust, [npc.she] [npc.verb(sink)] it deep into [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as the [npc.cock+] enters [npc2.herHim],"
										+ " before gently thrusting out [npc2.her] [npc2.hips] in order to sink it even deeper into [npc2.her] [npc2.vaginaUrethra+].",
	
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips],"
										+ " sinking [npc.namePos] [npc.cock+] even deeper into [npc2.her] [npc2.vaginaUrethra+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.cock+] enters [npc2.herHim],"
										+ " before violently thrusting out [npc2.her] [npc2.hips] in order to force it even deeper into [npc2.her] [npc2.vaginaUrethra+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.her] [npc2.hips],"
										+ " roughly forcing [npc.name] to sink [npc.her] [npc.cock+] even deeper into [npc2.her] [npc2.vaginaUrethra+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.cock+] enters [npc2.herHim],"
										+ " before eagerly bucking [npc2.her] [npc2.hips] in order to sink it even deeper into [npc2.her] [npc2.vaginaUrethra+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips],"
										+ " desperately helping to sink [npc.namePos] [npc.cock+] even deeper into [npc2.her] [npc2.vaginaUrethra+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.cock+] enters [npc2.herHim],"
										+ " before bucking [npc2.her] [npc2.hips] in order to sink it even deeper into [npc2.her] [npc2.vaginaUrethra+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.her] [npc2.hips],"
										+ " helping to sink [npc.namePos] [npc.cock+] even deeper into [npc2.her] [npc2.vaginaUrethra+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as the [npc.cock+] enters [npc2.herHim],"
										+ " and, with tears running down [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to pull out.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from the unwanted penetration,"
										+ " tears running down [npc2.her] [npc2.face] as [npc.namePos] unwelcome [npc.cock] pushes deep into [npc2.her] [npc2.vaginaUrethra+]."));
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
							" [npc2.Name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(help)] to sink [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.vaginaUrethra+].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.herHim].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out,"
									+ " eagerly begging for [npc.name] to continue fucking [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to pull out of [npc2.her] [npc2.vaginaUrethra+].",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.her] [npc2.vaginaUrethra+].",
		
							" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
									+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(help)] to sink [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.vaginaUrethra+].",
	
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.herHim].",
	
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips+] out,"
									+ " begging for [npc.name] to continue fucking [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.her] [npc2.vaginaUrethra+].",
	
							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, slowly bucking [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)] [npc.name] to carry on fucking [npc2.herHim].",
	
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.hips+] back,"
									+ " begging for [npc.name] to continue fucking [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(demand)] that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",
	
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly slamming [npc2.her] [npc2.hips] into [npc.her] groin, [npc2.she] [npc2.verb(order)] [npc.name] to carry on fucking [npc2.herHim].",
	
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips+] out into [npc.namePos] groin,"
									+ " ordering [npc.herHim] to continue fucking [npc2.herHim] as [npc2.her] movements force [npc.her] [npc.cock+] deep into [npc2.her] [npc2.vaginaUrethra+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction VAGINAL_URETHRA_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently slide your [npc.cock] in and out of [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+],"
							+ " [npc.name] [npc.verb(start)] rocking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(fuck)] [npc2.name].",

					"Slowly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+],"
							+ " [npc.name] softly [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.herHim].",

					"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.cock+] in and out of [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+],"
							+ " [npc.name] [npc.verb(start)] enthusiastically rocking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(fuck)] [npc2.name].",

					"Enthusiastically pushing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+],"
							+ " [npc.name] frantically [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(fuck)] [npc2.herHim].",

					"Thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your [npc.cock+] in and out of [npc2.namePos] [npc2.vaginaUrethra+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+],"
							+ " [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] brutally [npc.verb(fuck)] [npc2.name].",

					"Violently thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+],"
							+ " [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] forcefully [npc.verb(fuck)] [npc2.herHim].",

					"Forcefully driving [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to roughly slam [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] violently [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+],"
							+ " [npc.name] [npc.verb(start)] rocking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(fuck)] [npc2.name].",

					"Pushing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+],"
							+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.herHim].",

					"Thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly pump your [npc.cock+] in and out of [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+],"
							+ " [npc.name] [npc.verb(start)] enthusiastically rocking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(fuck)] [npc2.name].",

					"Enthusiastically pushing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+],"
							+ " [npc.name] frantically [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(fuck)] [npc2.herHim].",

					"Thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] out of [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] down and [npc2.verb(take)] a gentle hold of it, before softly forcing it back into [npc2.her] [npc2.vaginaUrethra+].",
	
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it, before gently forcing it back inside [npc2.her] [npc2.vaginaUrethra+].",
	
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] gently [npc2.verb(force)] [npc2.her] [npc2.vaginaUrethra+] down onto [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a rough hold of it, before aggressively forcing it back into [npc2.her] [npc2.vaginaUrethra+].",
	
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it, before roughly forcing it back inside [npc2.her] [npc2.vaginaUrethra+].",
	
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] roughly [npc2.verb(force)] [npc2.her] [npc2.vaginaUrethra+] down onto [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a firm hold of it, before eagerly forcing it back into [npc2.her] [npc2.vaginaUrethra+].",
	
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it, before eagerly forcing it back inside [npc2.her] [npc2.vaginaUrethra+].",
	
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.vaginaUrethra+] down onto [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [npc2.namePos] [npc2.vaginaUrethra+] and stop fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] dominantly [npc.verb(slide)] the [npc.cockTip+] up and down over [npc2.her] [npc2.labia+] one last time before pulling back.",

							"Thrusting deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(yank)] [npc.her] [npc.cock+] back out of [npc2.her] [npc2.vaginaUrethra+], putting an end to the rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(rub)] the [npc.cockTip] up and down over [npc2.her] [npc2.labia+] one last time before pulling back.",

							"Pushing deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(slide)] [npc.her] [npc.cock+] back out of [npc2.her] [npc2.vaginaUrethra+], putting an end to the fucking."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.vaginaUrethra],"
										+ " and [npc2.she] [npc2.verb(continue)] crying and protesting as [npc2.she] [npc2.verb(carry)] on weakly struggling against [npc.herHim].",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.vaginaUrethra+] away from [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out of [npc2.her] [npc2.vaginaUrethra+], eager for more of [npc.her] attention.",
	
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Vaginal urethra fucked";
		}

		@Override
		public String getActionDescription() {
			return "Slide [npc2.namePos] [npc2.cock+] into your [npc.vaginaUrethra+] and get fucked.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.cock], [npc.name] slowly [npc.verb(guide)] it up to [npc.her] [npc.labia+],"
									+ " letting out a little [npc.moan] before gently bucking [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.vaginaUrethra+].",

							"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.vaginaUrethra+],"
									+ " before slowly pushing [npc.her] [npc.hips] back and letting out a soft [npc.moan] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.cock], [npc.name] eagerly [npc.verb(guide)] it up to [npc.her] [npc.labia+],"
									+ " letting out [npc.a_moan+] before desperately bucking [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.vaginaUrethra+].",

							"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.vaginaUrethra+],"
									+ " before eagerly thrusting [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.cock], [npc.name] roughly yank it up to [npc.her] [npc.labia+],"
									+ " letting out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.vaginaUrethra+].",

							"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.vaginaUrethra+],"
									+ " before eagerly slamming [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(guide)] it up to [npc.her] [npc.labia+],"
									+ " letting out [npc.a_moan+] before bucking [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.vaginaUrethra+].",

							"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.vaginaUrethra+],"
									+ " before pushing [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " gently pushing [npc2.her] [npc2.cock] forwards as [npc2.she] [npc2.verb(start)] to fuck [npc.namePos] [npc.vaginaUrethra+].",
	
								" With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards,"
										+ " sinking it deep into [npc.namePos] [npc.vaginaUrethra+] as [npc2.she] [npc2.verb(start)] fucking [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " eagerly pushing [npc2.her] [npc2.cock] forwards as [npc2.she] [npc2.verb(start)] enthusiastically fucking [npc.namePos] [npc.vaginaUrethra+].",
	
								" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards,"
										+ " sinking it deep into [npc.namePos] [npc.vaginaUrethra+] as [npc2.she] [npc2.verb(start)] energetically fucking [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " and, seeking to remind [npc.name] who's in charge, [npc2.she] roughly slams [npc2.her] [npc2.cock] forwards and [npc2.verb(start)] to ruthlessly fuck [npc.her] [npc.vaginaUrethra+].",
	
								" With [npc2.a_moan+], [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock] forwards,"
										+ " seeking to remind [npc.name] who's in charge as [npc2.she] [npc2.verb(start)] ruthlessly fucking [npc.namePos] [npc.vaginaUrethra+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], pushing [npc2.her] [npc2.cock] forwards as [npc2.she] [npc2.verb(start)] fucking [npc.namePos] [npc.vaginaUrethra+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards, sinking it deep into [npc.namePos] [npc.vaginaUrethra+] as [npc2.she] [npc2.verb(start)] fucking [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] inside of [npc.herHim],"
										+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.cock+] free from [npc.her] [npc.vaginaUrethra+].",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] deep into [npc.her] [npc.vaginaUrethra+]."));
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
							" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.vaginaUrethra+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.vaginaUrethra+].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] as deep as possible into [npc.namePos] [npc.vaginaUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.pussy],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.vaginaUrethra+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.vaginaUrethra+],"
									+ " letting out a soft [npc2.moan] as [npc2.she] gently [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" A gentle [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.vaginaUrethra+].",
	
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.vaginaUrethra+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.vaginaUrethra+].",
	
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] as deep as possible into [npc.namePos] [npc.vaginaUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.vaginaUrethra+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.vaginaUrethra+].",
	
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.vaginaUrethra+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle urethra fucked";
		}

		@Override
		public String getActionDescription() {
			return "Gently have your [npc.vaginaUrethra+] fucked by [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a gentle thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+].",

					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.vaginaUrethra+].",

					"Slowly gyrating [npc.her] [npc.hips],"
							+ " a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Urethra fucked";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck your [npc.vaginaUrethra+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With an eager bucking of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+].",

					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.vaginaUrethra+].",

					"Energetically gyrating [npc.her] [npc.hips],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough urethra fucked";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck your [npc.vaginaUrethra+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a violent slam of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+].",

					"With [npc.a_moan+], [npc.name] roughly [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.vaginaUrethra+].",

					"Forcefully gyrating [npc.her] [npc.hips],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements roughly force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Urethra fucked";
		}

		@Override
		public String getActionDescription() {
			return "Fuck your [npc.vaginaUrethra+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a quick buck of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.vaginaUrethra+].",

					"Gyrating [npc.her] [npc.hips],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager urethra fucked";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck your [npc.vaginaUrethra+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With an eager bucking of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+].",

					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.vaginaUrethra+].",

					"Energetically gyrating [npc.her] [npc.hips],"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.vaginaUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist urethra fucked";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.vaginaUrethra+] away from [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.cock] out of [npc.her] [npc.vaginaUrethra+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.cock+] [npc2.verb(continue)] slowly sliding in and out of [npc.her] [npc.vaginaUrethra+].",

							"Trying desperately to pull [npc.her] [npc.hips] away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] gently sliding deep into [npc.her] [npc.vaginaUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.cock] out of [npc.her] [npc.vaginaUrethra+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.cock+] [npc2.verb(continue)] frantically pumping in and out of [npc.her] [npc.vaginaUrethra+].",

							"Trying desperately to pull [npc.her] [npc.hips] away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] greedily thrusting deep into [npc.her] [npc.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.cock] out of [npc.her] [npc.vaginaUrethra+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.cock+] [npc2.verb(continue)] roughly slamming in and out of [npc.her] [npc.vaginaUrethra+].",

							"Trying desperately to pull [npc.her] [npc.hips] away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] violently thrusting deep into [npc.her] [npc.vaginaUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Stop urethra fucked";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.cock] out of your [npc.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.cock] out of [npc.her] [npc.vaginaUrethra+], [npc.name] [npc.verb(let)] out a menacing growl as [npc.she] [npc.verb(command)] [npc2.name] to stop fucking [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.cock] out of [npc.her] [npc.vaginaUrethra+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.cock] out of [npc.her] [npc.vaginaUrethra+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(tell)] [npc2.name] to stop fucking [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before sliding [npc2.her] [npc2.cock] out of [npc.her] [npc.vaginaUrethra+]."));
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
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] [npc2.herHim] from fucking [npc.her] [npc.vaginaUrethra+].",
	
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue fucking [npc.namePos] [npc.vaginaUrethra+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
