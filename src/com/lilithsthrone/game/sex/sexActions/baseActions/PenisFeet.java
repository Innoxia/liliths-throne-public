package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.FootType;
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
 * @since 0.2.10
 * @version 0.4
 * @author Innoxia
 */
public class PenisFeet {

	// Foot tease
	
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Get [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.cock+] between [npc2.namePos] [npc2.feet] and start fucking them.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.HOOFS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(bringing)] [npc2.namePos] hard hoofs up to the sides of [npc.her] [npc.cock+], [npc.name] carefully [npc.verb(press)] the soles up against [npc.her] genitals,"
										+ " before thrusting [npc.her] [npc.hips] forwards and starting to receive a hoofjob.",
	
								"[npc.Name] [npc.verb(rub)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] hard hoofs,"
										+ " before carefully pressing [npc2.her] soles together and starting to receive a hoofjob."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.feet], and,"
											+ " with tears running down [npc2.her] [npc2.face], [npc2.she] desperately [npc2.verb(beg)] for [npc.herHim] to stop.",
		
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to pull away from [npc.name];"
											+ " tears running down [npc2.her] [npc2.face] as the unwelcome [npc.cock] slides between [npc2.her] [npc2.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.feet],"
											+ " before [npc2.eagerly] sliding them up and down around [npc.her] [npc.cock+].",
		
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly] sliding [npc2.her] [npc2.feet] up and down against [npc.namePos] [npc.cock+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TALONS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(curling)] [npc2.namePos] bird-like feet around [npc.her] [npc.cock+], [npc.name] [npc.verb(make)] sure that [npc2.her] sharp talons are pointing away from [npc.her] genitals,"
										+ " before thrusting [npc.her] [npc.hips] forwards and starting to receive a [npc2.footjob].",
	
								"[npc.Name] [npc.verb(rub)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] soles,"
										+ " before wrapping [npc2.her] bird-like feet around [npc.her] [npc.cock+] and starting to receive a [npc2.footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.feet], and,"
											+ " with tears running down [npc2.her] [npc2.face], [npc2.she] desperately [npc2.verb(beg)] for [npc.herHim] to stop.",
		
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to pull away from [npc.name];"
											+ " tears running down [npc2.her] [npc2.face] as the unwelcome [npc.cock] slides between [npc2.her] [npc2.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.feet],"
											+ " before [npc2.eagerly] sliding them up and down around [npc.her] [npc.cock+].",
		
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly] sliding [npc2.her] [npc2.feet] up and down against [npc.namePos] [npc.cock+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TENTACLE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(pushing)] [npc2.namePos] [npc2.feet+] together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against them,"
										+ " before [npc.eagerly] thrusting [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",
										
								"[npc.Name] [npc.verb(rub)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.feet+],"
										+ " before [npc.eagerly] pressing them together and starting to fuck the crevice that's formed."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.feet], and,"
											+ " with tears running down [npc2.her] [npc2.face], [npc2.she] desperately [npc2.verb(beg)] for [npc.herHim] to stop.",
		
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to pull away from [npc.name];"
											+ " tears running down [npc2.her] [npc2.face] as the unwelcome [npc.cock] slides between [npc2.her] [npc2.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.feet],"
											+ " before [npc2.eagerly] sliding them up and down around [npc.her] [npc.cock+].",
		
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly] sliding [npc2.her] [npc2.feet] up and down around [npc.namePos] [npc.cock+]."));
							break;
					}
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(pushing)] [npc2.namePos] [npc2.feet+] together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.namePos] soles,"
										+ " before [npc.eagerly] thrusting [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",
	
								"[npc.Name] [npc.verb(rub)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.feet+],"
										+ " before [npc.eagerly] pressing [npc2.her] soles together and starting to fuck the crevice that's formed."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.feet], and,"
											+ " with tears running down [npc2.her] [npc2.face], [npc2.she] desperately [npc2.verb(beg)] for [npc.herHim] to stop.",
		
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to pull away from [npc.name];"
											+ " tears running down [npc2.her] [npc2.face] as the unwelcome [npc.cock] slides between [npc2.her] [npc2.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] [npc2.feet],"
											+ " before [npc2.eagerly] sliding them up and down around [npc.her] [npc.cock+].",
		
									" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly] sliding [npc2.her] [npc2.feet] up and down around [npc.namePos] [npc.cock+]."));
							break;
					}
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
							" [npc2.Name] [npc2.verb(start)] eagerly sliding [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(give)] [npc.herHim] an enthusiastic [npc2.footjob].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] eagerly [npc2.verb(slide)] [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(slide)] [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(deliver)] an enthusiastic [npc2.footjob]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to stop using [npc2.her] [npc2.feet].",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to let go of [npc2.her] [npc2.feet].",
		
							" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
									+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] [npc2.feet]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] sliding [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(give)] [npc.herHim] [npc2.a_footjob].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(slide)] [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(deliver)] [npc2.a_footjob]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] gently sliding [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(give)] [npc.herHim] a loving [npc2.footjob].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] gently [npc2.verb(slide)] [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] in delight, [npc2.name] softly [npc2.verb(slide)] [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(deliver)] a gentle [npc2.footjob]."));
					break;
				case DOM_ROUGH:
					if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.HOOFS)) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(start)] roughly sliding [npc2.her] [npc2.feet] up and down around [npc.namePos] [npc.cock+] in response,"
										+ " not caring about the fact that using [npc2.her] hard hoofs in such a violent manner is extremely uncomfortable for [npc.herHim].",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] forcefully [npc2.verb(rub)] [npc2.her] hard hoofs up and down against [npc.namePos] [npc.cock+],"
										+ " not caring in the least about how uncomfortable such a rough [npc2.footjob] is to receive.",
			
								" [npc2.Moaning] in delight, [npc2.name] violently [npc2.verb(rub)] [npc2.her] hard hoofs up and down around [npc.namePos] [npc.cock+],"
										+ " laughing derisively at [npc.herHim] as [npc2.she] [npc2.verb(administer)] the uncomfortable, forceful [npc2.footjob]."));
						
					} else if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.TALONS)) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(start)] roughly sliding [npc2.her] [npc2.feet] up and down around [npc.namePos] [npc.cock+] in response,"
										+ " not caring about the fact that [npc2.her] sharp talons keep on coming dangerously close to cutting [npc.herHim].",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] forcefully [npc2.verb(rub)] [npc2.her] bird-like claws up and down against [npc.namePos] [npc.cock+],"
										+ " not caring in the least about how close [npc2.her] sharp talons come to cutting [npc.herHim].",
			
								" [npc2.Moaning] in delight, [npc2.name] violently [npc2.verb(rub)] [npc2.her] clawed, bird-like feet up and down around [npc.namePos] [npc.cock+],"
										+ " laughing derisively at [npc.herHim] as [npc2.she] [npc2.verb(draw)] dangerously close to cutting [npc.herHim] with [npc2.her] sharp talons."));
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(start)] roughly sliding [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+] in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(give)] [npc.herHim] a forceful [npc2.footjob].",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] forcefully [npc2.verb(slide)] [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+].",
			
							" [npc2.Moaning] in delight, [npc2.name] violently [npc2.verb(rub)] [npc2.her] [npc2.feet+] up and down around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(deliver)] a forceful [npc2.footjob]."));
					}
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] [npc2.feet].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sliding [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet],"
							+ " [npc.name] [npc.verb(start)] steadily bucking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(receive)] [npc2.a_footjob].",

					"Gently slipping [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet+],"
							+ " [npc.name] [npc.verb(start)] softly thrusting [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(receive)] [npc2.a_footjob].",

					"Softly pushing [npc2.namePos] [npc2.feet+] together, [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] slowly [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Normal [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] [npc2.feet].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet],"
							+ " [npc.name] [npc.verb(start)] energetically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(receive)] [npc2.a_footjob].",

					"Desperately pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet+],"
							+ " [npc.name] [npc.verb(start)] frantically thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(receive)] [npc2.a_footjob].",

					"Greedily pushing [npc2.namePos] [npc2.feet+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to desperately pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] energetically [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Rough [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] [npc2.feet].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet],"
							+ " [npc.name] [npc.verb(start)] dominantly pumping [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] forcefully [npc.verb(receive)] [npc2.a_footjob].",

					"Forcefully pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet+],"
							+ " [npc.name] [npc.verb(start)] violently thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] roughly [npc.verb(receive)] [npc2.a_footjob].",

					"Dominantly pushing [npc2.namePos] [npc2.feet+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to slam [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] roughly [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Normal [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] [npc2.feet].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet],"
							+ " [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(receive)] [npc2.a_footjob].",

					"Pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet+],"
							+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] happily [npc.verb(receive)] [npc2.a_footjob].",

					"Pushing [npc2.namePos] [npc2.feet+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Eager [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc2.namePos] [npc2.feet].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet],"
							+ " [npc.name] [npc.verb(start)] energetically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(receive)] [npc2.a_footjob].",

					"Desperately pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.feet+],"
							+ " [npc.name] [npc.verb(start)] frantically thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(receive)] [npc2.a_footjob].",

					"Greedily pushing [npc2.namePos] [npc2.feet+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to desperately pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] energetically  [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Resist [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] away from [npc2.namePos] [npc2.feet+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] slowly [npc2.verb(thrust)] [npc2.her] [npc2.feet] back against [npc.herHim] and [npc2.verb(continue)] gently forcing [npc.herHim] to receive [npc2.a_footjob].",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.name] firmly [npc2.verb(hold)] [npc.herHim] in place,"
									+ " gently pushing [npc2.her] [npc2.feet] against [npc.her] groin as [npc2.she] [npc2.verb(force)] [npc.herHim] to receive [npc2.a_footjob].",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.herHim] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests,"
									+ " slowly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(force)] [npc.herHim] to receive [npc2.a_footjob]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] violently [npc2.verb(thrust)] [npc2.her] [npc2.feet] back against [npc.herHim] and [npc2.verb(continue)] roughly forcing [npc.herHim] to receive [npc2.a_footjob].",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.she] dominantly hold [npc.herHim] in place,"
									+ " violently pushing [npc2.her] [npc2.feet] against [npc.her] groin as [npc2.she] roughly [npc2.verb(force)] [npc.herHim] to receive [npc2.a_footjob].",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests,"
									+ " roughly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(force)] [npc.herHim] to receive [npc2.a_footjob]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.feet] back against [npc.herHim] and [npc2.verb(continue)] rapidly forcing [npc.herHim] to receive [npc2.a_footjob].",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.she] firmly hold [npc.herHim] in place,"
									+ " eagerly pushing [npc2.her] [npc2.feet] against [npc.her] groin as [npc2.she] desperately [npc2.verb(force)] [npc.herHim] to receive [npc2.a_footjob].",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests,"
									+ " eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.she] happily [npc2.verb(force)] [npc.herHim] to receive [npc2.a_footjob]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Stop [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out from between [npc2.namePos] [npc2.feet].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out from between [npc2.namePos] [npc2.feet+],"
									+ " [npc.name] dominantly [npc.verb(slide)] the [npc.cockHead] of [npc.her] [npc.cock] up and down over [npc2.her] [npc2.toes+] one last time before pulling [npc.her] [npc.hips] back.",

							"Thrusting deep between [npc2.namePos] [npc2.feet+] one last time, [npc.name] then [npc2.verb(pull)] back, putting an end to the [npc2.footjob]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock+] out from between [npc2.namePos] [npc2.feet+],"
									+ " [npc.name] slaps the [npc.cockHead] of [npc.her] [npc.cock] against [npc2.her] [npc2.toes+] one last time before pulling [npc.her] [npc.hips] back.",

							"Pushing deep between [npc2.namePos] [npc2.feet+] one last time, [npc.name] then [npc2.verb(pull)] back, putting an end to the [npc2.footjob]."));
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
	
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Give [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc2.namePos] [npc2.cock+] between your [npc.feet] and start giving [npc2.herHim] [npc.a_footjob].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.HOOFS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] over [npc.her] hard hoofs,"
								+ " before carefully pressing them against the sides of [npc2.her] [npc2.cock] and starting to give [npc2.herHim] [npc.a_footjob].",

						"Rubbing [npc.her] hard hoofs against [npc2.namePos] [npc2.cock+], [npc.name] carefully [npc.verb(press)] them together,"
								+ " [npc.moaning+] as [npc.she] [npc.verb(start)] to give [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] in between [npc.her] [npc.feet+],"
											+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull away.",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] deep between [npc.her] [npc.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], [npc2.eagerly] bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] [npc2.eagerly] fucking [npc.her] [npc.feet].",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(slide)] [npc2.her] [npc2.cock+] forwards between [npc.namePos] [npc.feet+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TALONS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] over the soles of [npc.her] bird-like claws,"
								+ " before carefully wrapping them around [npc2.her] [npc2.cock] and starting to give [npc2.herHim] [npc.a_footjob].",

						"Rubbing [npc.her] bird-like claws against [npc2.namePos] [npc2.cock+], [npc.name] carefully [npc.verb(wrap)] them around [npc2.her] shaft,"
								+ " [npc.moaning+] as [npc.she] [npc.verb(start)] to give [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] in between [npc.her] [npc.feet+],"
											+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull away.",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] deep between [npc.her] [npc.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], [npc2.eagerly] bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] [npc2.eagerly] fucking [npc.her] [npc.feet].",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(slide)] [npc2.her] [npc2.cock+] forwards between [npc.namePos] [npc.feet+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TENTACLE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] over [npc.her] [npc.feet+],"
								+ " before [npc.eagerly] wrapping them around [npc2.her] [npc2.cock] and starting to give [npc2.herHim] [npc.a_footjob].",

						"Rubbing [npc.her] [npc.feet+] against [npc2.namePos] [npc2.cock+], [npc.name] [npc.eagerly] [npc.verb(press)] them together,"
								+ " [npc.moaning+] as [npc.she] [npc.verb(start)] to give [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] in between [npc.her] [npc.feet+],"
											+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull away.",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] deep between [npc.her] [npc.feet]"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], [npc2.eagerly] bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] [npc2.eagerly] fucking [npc.her] [npc.feet].",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(slide)] [npc2.her] [npc2.cock+] forwards between [npc.namePos] [npc.feet+]"));
							break;
					}
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] over the soles of [npc.her] [npc.feet+],"
								+ " before [npc.eagerly] pressing them together around [npc2.her] [npc2.cock] and starting to give [npc2.herHim] [npc.a_footjob].",

						"Rubbing [npc.her] [npc.feet+] against [npc2.namePos] [npc2.cock+], [npc.name] [npc.eagerly] [npc.verb(press)] them together,"
								+ " [npc.moaning+] as [npc.she] [npc.verb(start)] to give [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] in between [npc.her] [npc.feet+],"
											+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull away.",
	
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] deep between [npc.her] [npc.feet]"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], [npc2.eagerly] bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] [npc2.eagerly] fucking [npc.her] [npc.feet].",
	
									" With [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(slide)] [npc2.her] [npc2.cock+] forwards between [npc.namePos] [npc.feet+]"));
							break;
					}
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
							" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] between [npc.namePos] [npc.feet+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(receive)] [npc.a_footjob].",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] eagerly thrusting [npc2.her] [npc2.cock+] between [npc.namePos] [npc.feet].",
									
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.feet+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.feet],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] in between [npc.her] [npc.feet+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.feet+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep in between [npc.namePos] [npc.feet+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(receive)] [npc.a_footjob].",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] between [npc.namePos] [npc.feet].",
									
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.feet+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.feet+],"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(receive)] [npc.a_footjob].",
		
							" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] gently thrusting [npc2.her] [npc2.cock+] between [npc.namePos] [npc.feet].",
									
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.feet+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep in between [npc.namePos] [npc.feet+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(receive)] [npc.a_footjob].",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] roughly slamming [npc2.her] [npc2.cock+] between [npc.namePos] [npc.feet].",
									
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(thrust)] [npc2.her] [npc2.cock+] in between [npc.namePos] [npc.feet+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Gently give [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Gently use [npc2.namePos] [npc2.cock+] to fuck your [npc.feet].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sliding [npc.her] [npc.feet] up and down around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] sliding [npc.her] [npc.feet+] up and down around [npc2.namePos] [npc2.cock+].",

					"Slowly pressing [npc.her] [npc.feet+] together around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Give [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Use [npc2.namePos] [npc2.cock+] to fuck your [npc.feet].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sliding [npc.her] [npc.feet] up and down around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"With [npc.a_moan+], [npc.name] happily [npc.verb(start)] sliding [npc.her] [npc.feet+] up and down around [npc2.namePos] [npc2.cock+].",

					"Eagerly pressing [npc.her] [npc.feet+] together around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly give [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [npc2.namePos] [npc2.cock+] up and down between your [npc.feet].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly sliding [npc.her] [npc.feet+] up and down around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",
					"With [npc.a_moan+], [npc.name] [npc.verb(start)] forcefully sliding [npc.her] [npc.feet+] up and down around [npc2.namePos] [npc2.cock+].",
					"Roughly pressing [npc.her] [npc.feet+] together around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Give [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.feet+] together around [npc2.namePos] [npc2.cock] and give [npc2.herHim] [npc.a_footjob].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sliding [npc.her] [npc.feet] up and down around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"With [npc.a_moan+], [npc.name] happily [npc.verb(start)] sliding [npc.her] [npc.feet+] up and down around [npc2.namePos] [npc2.cock+].",

					"Pressing [npc.her] [npc.feet+] together around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly give [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.feet+] together around [npc2.namePos] [npc2.cock] and give [npc2.herHim] an eager [npc.footjob].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sliding [npc.her] [npc.feet] up and down around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"With [npc.a_moan+], [npc.name] happily [npc.verb(start)] sliding [npc.her] [npc.feet+] up and down around [npc2.namePos] [npc2.cock+].",

					"Eagerly pressing [npc.her] [npc.feet+] together around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist giving [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.feet+] away from [npc2.namePos] [npc2.cock+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] gently fucking [npc.her] [npc.feet].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.feet] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] slowly sliding in and out between [npc.her] [npc.feet].",

							"Trying desperately to pull [npc.her] [npc.feet] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] gently sliding between [npc.her] [npc.feet]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Y[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] eagerly fucking [npc.her] [npc.feet].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.feet] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] eagerly sliding in and out between [npc.her] [npc.feet].",

							"Trying desperately to pull [npc.her] [npc.feet] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] eagerly sliding between [npc.her] [npc.feet]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] roughly fucking [npc.her] [npc.feet].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.feet] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] roughly slamming in and out between [npc.her] [npc.feet].",

							"Trying desperately to pull [npc.her] [npc.feet] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] roughly slamming between [npc.her] [npc.feet]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Stop giving [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.cock] out from between your [npc.feet].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.cock] out from between [npc.her] [npc.feet], [npc.name] [npc.verb(let)] out a menacing growl as [npc.she] [npc.verb(command)] [npc2.herHim] to stop.",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.cock] out from between [npc.her] [npc.feet]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.cock] out from between [npc.her] [npc.feet], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(tell)] [npc2.herHim] to stop.",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before sliding [npc2.her] [npc2.cock] out from between [npc.her] [npc.feet]."));
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
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue fucking [npc.her] [npc.feet]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	

}
