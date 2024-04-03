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
 * @since 0.1.90
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisThighs {
	
	public static final SexAction THIGH_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Start intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.cock+] between [npc2.namePos] thighs and start fucking them.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly pushing [npc2.namePos] [npc2.legs+] together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.namePos] thighs,"
									+ " before slowly thrusting [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] [npc.verb(position)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.legs+], and with a slow, steady pressure,"
									+ " [npc.she] gently [npc.verb(press)] [npc2.her] thighs together, before starting to fuck the crevice that's formed."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc2.namePos] [npc2.legs+] together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.namePos] thighs,"
									+ " before greedily pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] [npc.verb(position)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.legs+],"
									+ " before eagerly pressing [npc2.her] thighs together and starting to fuck the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly pushing [npc2.namePos] [npc2.legs+] together, [npc.name] forcefully [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.namePos] thighs,"
									+ " before violently pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] [npc.verb(position)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.legs+],"
									+ " before roughly squeezing [npc2.her] thighs together and starting to aggressively fuck the crevice that's formed."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc2.namePos] [npc2.legs+] together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.namePos] thighs,"
									+ " before pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] [npc.verb(position)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.legs+],"
									+ " before pressing [npc2.her] thighs together and starting to fuck the crevice that's formed."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.legs],"
										+ " gently bucking back against [npc.herHim] as [npc2.she] [npc2.verb(help)] to sink the [npc.cock+] even deeper between [npc2.her] thighs.",
	
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking back against [npc.namePos] crotch,"
										+ " sinking [npc.her] [npc.cock+] even deeper between [npc2.her] thighs."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.legs],"
										+ " eagerly bucking back against [npc.herHim] as [npc2.she] [npc2.verb(help)] to sink the [npc.cock+] even deeper between [npc2.her] thighs.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking back against [npc.namePos] crotch,"
										+ " desperately helping to sink [npc.her] [npc.cock+] even deeper between [npc2.her] thighs."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.legs],"
										+ " violently thrusting back against [npc.herHim] as [npc2.she] roughly [npc2.verb(force)] the [npc.cock+] even deeper between [npc2.her] thighs.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking back against [npc.namePos] crotch,"
										+ " roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper between [npc2.her] thighs."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.legs],"
										+ " eagerly bucking back against [npc.herHim] as [npc2.she] [npc2.verb(help)] to sink the [npc.cock+] even deeper between [npc2.her] thighs.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking back against [npc.namePos] crotch,"
										+ " desperately helping to sink [npc.her] [npc.cock+] even deeper between [npc2.her] thighs."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.legs],"
										+ " bucking back against [npc.herHim] as [npc2.she] [npc2.verb(help)] to sink the [npc.cock+] even deeper between [npc2.her] thighs.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking back against [npc.namePos] crotch,"
										+ " helping to sink [npc.her] [npc.cock+] even deeper between [npc2.her] thighs."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.legs], and,"
										+ " with tears running down [npc2.her] [npc2.face], [npc2.she] desperately [npc2.verb(beg)] for [npc.herHim] to stop.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to pull away from [npc.name];"
										+ " tears running down [npc2.her] [npc2.face] as the unwelcome [npc.cock] pushes deep between [npc2.her] thighs."));
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
							" [npc2.Name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.legs+] back in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+] to slide in and out between [npc2.her] thighs.",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly gyrating [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] in and out between [npc2.her] [npc2.legs+].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips+] back and forth,"
									+ " begging for [npc.name] to continue fucking [npc2.her] [npc2.legs] as [npc2.her] movements help to slide [npc.her] [npc.cock+] up and down between [npc2.her] thighs."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to pull out of [npc2.her] thighs.",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.her] thighs.",
		
							" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
									+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.her] thighs."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] bucking [npc2.her] [npc2.legs+] back in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+] to slide in and out between [npc2.her] thighs.",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, gyrating [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] in and out between [npc2.her] [npc2.legs+].",
		
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(buck)] [npc2.her] [npc2.hips+] back and forth,"
									+ " begging for [npc.name] to continue fucking [npc2.her] [npc2.legs] as [npc2.her] movements help to slide [npc.her] [npc.cock+] up and down between [npc2.her] thighs."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.legs+] back in response,"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+] to slide in and out between [npc2.her] thighs.",
		
							" A gentle [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, slowly gyrating [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] in and out between [npc2.her] [npc2.legs+].",
		
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips+] back and forth,"
									+ " begging for [npc.name] to continue fucking [npc2.her] [npc2.legs] as [npc2.her] movements help to slide [npc.her] [npc.cock+] up and down between [npc2.her] thighs."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] violently bucking [npc2.her] [npc2.legs+] back in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+] to pump in and out between [npc2.her] thighs.",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly gyrating [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(force)] [npc.namePos] [npc.cock+] in and out between [npc2.her] [npc2.legs+].",
		
							" [npc2.Moaning] in delight, [npc2.name] dominantly [npc2.verb(buck)] [npc2.her] [npc2.hips+] back and forth,"
									+ " ordering [npc.name] to continue fucking [npc2.her] [npc2.legs] as [npc2.her] movements help to slide [npc.her] [npc.cock+] up and down between [npc2.her] thighs."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction THIGH_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] thighs.";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sliding [npc.her] [npc.cock+] between [npc2.namePos] thighs,"
							+ " [npc.name] [npc.verb(start)] steadily bucking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(fuck)] [npc2.her] [npc2.legs].",

					"Gently slipping [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+],"
							+ " [npc.name] [npc.verb(start)] softly thrusting [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.her] thighs.",

					"Softly pushing [npc2.namePos] [npc2.legs+] together, [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] slowly [npc.verb(fuck)] [npc2.her] thighs."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Normal intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] thighs.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.cock+] between [npc2.namePos] thighs,"
							+ " [npc.name] [npc.verb(start)] energetically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] [npc2.legs].",

					"Desperately pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+],"
							+ " [npc.name] [npc.verb(start)] frantically thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(fuck)] [npc2.her] thighs.",

					"Greedily pushing [npc2.namePos] [npc2.legs+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to desperately pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] energetically [npc.verb(fuck)] [npc2.her] thighs."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] thighs.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly thrusting [npc.her] [npc.cock+] between [npc2.namePos] thighs,"
							+ " [npc.name] [npc.verb(start)] dominantly pumping [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] forcefully [npc.verb(fuck)] [npc2.her] [npc2.legs].",

					"Forcefully pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+],"
							+ " [npc.name] [npc.verb(start)] violently thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] roughly [npc.verb(fuck)] [npc2.her] thighs.",

					"Dominantly pushing [npc2.namePos] [npc2.legs+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to slam [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] roughly [npc.verb(fuck)] [npc2.her] thighs."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Normal intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] thighs.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc.her] [npc.cock+] between [npc2.namePos] thighs,"
							+ " [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] [npc2.legs].",

					"Pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+],"
							+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] happily [npc.verb(fuck)] [npc2.her] thighs.",

					"Pushing the thighs of [npc2.namePos] [npc2.legs+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(fuck)] [npc2.her] thighs."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc2.namePos] thighs.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.cock+] between [npc2.namePos] thighs,"
							+ " [npc.name] [npc.verb(start)] energetically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] [npc2.legs].",

					"Desperately pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+],"
							+ " [npc.name] [npc.verb(start)] frantically thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(fuck)] [npc2.her] thighs.",

					"Greedily pushing [npc2.namePos] [npc2.legs+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to desperately pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] energetically  [npc.verb(fuck)] [npc2.her] thighs."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] away from [npc2.namePos] [npc2.legs+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] slowly [npc2.verb(thrust)] [npc2.her] [npc2.legs] back against [npc.herHim] and [npc2.verb(continue)] gently forcing [npc.her] [npc.cock+] between [npc2.her] thighs.",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.name] firmly [npc2.verb(hold)] [npc.herHim] in place,"
									+ " gently pushing [npc2.her] [npc2.legs] against [npc.her] groin as [npc2.she] [npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.her] thighs.",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.herHim] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests,"
									+ " slowly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(sink)] [npc.her] [npc.cock+] deep between [npc2.her] thighs."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] violently [npc2.verb(thrust)] [npc2.her] [npc2.legs] back against [npc.herHim] and [npc2.verb(continue)] roughly forcing [npc.her] [npc.cock+] between [npc2.her] thighs.",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.she] dominantly hold [npc.herHim] in place,"
									+ " violently pushing [npc2.her] [npc2.legs] against [npc.her] groin as [npc2.she] roughly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] thighs.",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests,"
									+ " roughly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.her] thighs."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.legs] back against [npc.herHim] and [npc2.verb(continue)] rapidly forcing [npc.her] [npc.cock+] between [npc2.her] thighs.",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.she] firmly hold [npc.herHim] in place,"
									+ " eagerly pushing [npc2.her] [npc2.legs] against [npc.her] groin as [npc2.she] desperately [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] thighs.",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests,"
									+ " eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.she] happily [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.her] thighs."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out from between [npc2.namePos] thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out from between [npc2.namePos] [npc2.legs+],"
									+ " [npc.name] dominantly [npc.verb(slide)] the [npc.cockHead] of [npc.her] [npc.cock] up and down over [npc2.her] thighs one last time before pulling [npc.her] [npc.hips] back.",

							"Thrusting deep between [npc2.namePos] [npc2.legs+] one last time, [npc.name] then [npc2.verb(pull)] back, putting an end to the intercrural sex."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock+] out from between [npc2.namePos] [npc2.legs+],"
									+ " [npc.name] slaps the [npc.cockHead] of [npc.her] [npc.cock] against [npc2.her] thighs one last time before pulling [npc.her] [npc.hips] back.",

							"Pushing deep between [npc2.namePos] [npc2.legs+] one last time, [npc.name] then [npc2.verb(pull)] back, putting an end to the intercrural sex."));
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
	
	
	public static final SexAction USING_COCK_BETWEEN_THIGHS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Get intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc2.namePos] [npc2.cock+] between your thighs.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a gentle [npc.moan], [npc.name] slowly [npc.verb(slide)] [npc2.namePos] [npc2.cock+] over [npc.her] [npc.legs+],"
									+ " before softly pressing [npc.her] thighs together and forcing [npc2.her] [npc2.cock] into the resulting cleavage.",

							"Lining [npc.her] [npc.legs+] up to [npc2.namePos] [npc2.cock+], [npc.name] slowly [npc.verb(push)] [npc.her] thighs together,"
									+ " softly [npc.moaning] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] into the resulting cleavage."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(slide)] [npc2.namePos] [npc2.cock+] over [npc.her] [npc.legs+],"
									+ " before desperately pressing [npc.her] thighs together and forcing [npc2.her] [npc2.cock] into the resulting cleavage.",

							"Lining [npc.her] [npc.legs+] up to [npc2.namePos] [npc2.cock+], [npc.name] eagerly [npc.verb(push)] [npc.her] thighs together,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] into the resulting cleavage."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With [npc.a_moan+], [npc.name] roughly [npc.verb(force)] [npc2.namePos] [npc2.cock+] over [npc.her] [npc.legs+],"
									+ " before greedily pressing [npc.her] thighs together and violently forcing [npc2.her] [npc2.cock] into the resulting cleavage.",

							"Lining [npc.her] [npc.legs+] up to [npc2.namePos] [npc2.cock+], [npc.name] roughly [npc.verb(push)] [npc.her] thighs together,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] into the resulting cleavage."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With [npc.a_moan+], [npc.name] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] over [npc.her] [npc.legs+],"
									+ " before desperately pressing [npc.her] thighs together and forcing [npc2.her] [npc2.cock] into the resulting cleavage.",

							"Lining [npc.her] [npc.legs+] up to [npc2.namePos] [npc2.cock+], [npc.name] eagerly [npc.verb(push)] [npc.her] thighs together,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] into the resulting cleavage."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], gently bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] to fuck [npc.her] thighs.",
	
								" With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(sink)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.legs+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], eagerly bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] enthusiastically fucking [npc.her] thighs.",
	
								" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(sink)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.legs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, seeking to remind [npc.name] who's in charge,"
										+ " roughly [npc2.verb(slam)] [npc2.her] [npc2.hips] forwards, before starting to ruthlessly fuck [npc.her] thighs.",
	
								" With [npc2.a_moan+], [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips] forwards,"
										+ " seeking to remind [npc.name] who's in charge as [npc2.she] [npc2.verb(start)] ruthlessly fucking [npc.her] thighs."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] enthusiastically fucking [npc.her] thighs.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(sink)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.legs+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] in between [npc.her] [npc.legs+],"
										+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull away.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] deep between [npc.her] thighs."));
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
							" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep in between [npc.namePos] [npc.legs+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(fuck)] [npc.her] thighs.",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep between [npc.namePos] thighs.",
									
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.legs+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.legs],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] in between [npc.her] [npc.legs+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.legs+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.legs+],"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(fuck)] [npc.her] thighs.",
		
							" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] gently thrusting [npc2.her] [npc2.cock+] between [npc.namePos] thighs.",
									
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.legs+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep in between [npc.namePos] [npc.legs+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(fuck)] [npc.her] thighs.",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] roughly slamming [npc2.her] [npc2.cock+] deep between [npc.namePos] thighs.",
									
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.legs+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep in between [npc.namePos] [npc.legs+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(fuck)] [npc.her] thighs.",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep between [npc.namePos] thighs.",
									
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.legs+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently receive intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Gently use [npc2.namePos] [npc2.cock+] to fuck your thighs.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently bucking [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep between [npc.her] [npc.legs+].",

					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] pushing [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.cock+] in and out between [npc.her] thighs.",

					"Slowly thrusting [npc.her] [npc.hips] back,"
							+ " a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements drive [npc2.namePos] [npc2.cock+] in between [npc.her] [npc.legs+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Use [npc2.namePos] [npc2.cock+] to fuck your thighs.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly bucking [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep between [npc.her] [npc.legs+].",

					"With [npc.a_moan+], [npc.name] greedily [npc.verb(start)] pushing [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.cock+] in and out between [npc.her] thighs.",

					"Desperately thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(drive)] [npc2.namePos] [npc2.cock+] in between [npc.her] [npc.legs+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly receive intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [npc2.namePos] [npc2.cock+] up and down between your thighs.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently thrusting [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep between [npc.her] [npc.legs+].",

					"With [npc.a_moan+], [npc.name] roughly [npc.verb(start)] slamming [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.cock+] in and out between [npc.her] thighs.",

					"Roughly thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.she] dominantly [npc.verb(drive)] [npc2.namePos] [npc2.cock+] in between [npc.her] [npc.legs+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.legs+] out against [npc2.name] in order to force [npc2.her] [npc2.cock] between your thighs.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Bucking [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep between [npc.her] [npc.legs+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(start)] pushing [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.cock+] in and out between [npc.her] thighs.",

					"Thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(drive)] [npc2.namePos] [npc2.cock+] in between [npc.her] [npc.legs+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly receive intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly push your [npc.legs+] out against [npc2.name] as [npc2.her] [npc2.cock] thrusts between your thighs.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly bucking [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep between [npc.her] [npc.legs+].",

					"With [npc.a_moan+], [npc.name] greedily [npc.verb(start)] pushing [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.cock+] in and out between [npc.her] thighs.",

					"Desperately thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(drive)] [npc2.namePos] [npc2.cock+] in between [npc.her] [npc.legs+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_BETWEEN_THIGHS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist receiving intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.legs+] away from [npc2.namePos] [npc2.cock+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] gently fucking [npc.her] thighs.",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.legs] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] slowly sliding in and out between [npc.her] thighs.",

							"Trying desperately to pull [npc.her] [npc.legs] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] gently sliding deep between [npc.her] thighs."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Y[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] eagerly fucking [npc.her] thighs.",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.legs] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] eagerly sliding in and out between [npc.her] thighs.",

							"Trying desperately to pull [npc.her] [npc.legs] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] eagerly sliding deep between [npc.her] thighs."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] roughly fucking [npc.her] thighs.",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.legs] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] roughly slamming in and out between [npc.her] thighs.",

							"Trying desperately to pull [npc.her] [npc.legs] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] roughly slamming deep between [npc.her] thighs."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop intercrural";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.cock] out from between your thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.cock] out from between [npc.her] thighs, [npc.name] [npc.verb(let)] out a menacing growl as [npc.she] [npc.verb(command)] [npc2.herHim] to stop.",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.cock] out from between [npc.her] thighs."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.cock] out from between [npc.her] thighs, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(tell)] [npc2.herHim] to stop.",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before sliding [npc2.her] [npc2.cock] out from between [npc.her] thighs."));
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
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue fucking [npc.her] thighs."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
