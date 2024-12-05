package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
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
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class PenisBreastsCrotch {
	
	private static String getPaizuriTitle(GameCharacter character) {
		if(character.isBreastCrotchFuckablePaizuri()) {
			if(character.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "udder-paizuri";
			} else {
				return "crotch-boob-paizuri";
			}
		} else {
			if(character.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "udder-naizuri";
			} else {
				return "crotch-boob-naizuri";
			}
		}
	}
	
	public static final SexAction FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch();
		}
		
		@Override
		public String getActionTitle() {
			return "Start "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Slide your [npc.cock+] between [npc2.namePos] [npc2.crotchBoobs+] and start fucking them.";
			} else {
				return "Start grinding your [npc.cock+] over [npc2.namePos] stomach.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of [npc2.namePos] [npc2.crotchBoobs+], [npc.name] gently [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.her] cleavage before sliding forwards and starting to fuck [npc2.her] [npc2.crotchBoobs]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to greedily [npc.verb(sink)] [npc.her] [npc.fingers] into [npc2.namePos] [npc2.crotchBoobs+], [npc.name] eagerly [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.her] cleavage before sliding forwards and starting to enthusiastically fuck [npc2.her] [npc2.crotchBoobs]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to roughly [npc.verb(sink)] [npc.her] [npc.fingers] into [npc2.namePos] [npc2.crotchBoobs+], [npc.name] forcefully [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.her] cleavage before slamming forwards and starting to rapidly fuck [npc2.her] [npc2.crotchBoobs]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of [npc2.namePos] [npc2.crotchBoobs+], [npc.name] then [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.her] cleavage before sliding forwards and starting to fuck [npc2.her] [npc2.crotchBoobs]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to greedily [npc.verb(sink)] [npc.her] [npc.fingers] into [npc2.namePos] [npc2.crotchBoobs+], [npc.name] eagerly [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.her] cleavage before sliding forwards and starting to enthusiastically fuck [npc2.her] [npc2.crotchBoobs]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response,"
											+ " reaching up to help push [npc2.her] [npc2.crotchBoobs] together as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " quickly reaching up to help push [npc2.her] [npc2.crotchBoobs] together as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " reaching up to forcefully press [npc2.her] [npc2.crotchBoobs] together as [npc2.she] dominantly [npc2.verb(order)] [npc.herHim] to keep going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " quickly reaching up to help push [npc2.her] [npc2.crotchBoobs] together as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan] in response,"
											+ " before reaching up to help push [npc2.her] [npc2.crotchBoobs] together as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " reaching up to weakly try and push [npc.herHim] away from [npc2.her] [npc2.crotchBoobs] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of [npc2.namePos] [npc2.crotchBoobs+], [npc.name] gently [npc.verb(try)] to push them together,"
										+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.she] [npc2.has] before sliding forwards and starting to grind down over [npc2.her] lower abdomen."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to squeeze and grope [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.do] [npc.her] best to try to push them together,"
										+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.she] [npc2.has] before sliding forwards and starting to eagerly grind down over [npc2.her] lower abdomen."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to roughly squeeze and grope [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.do] [npc.her] best to try to push them together,"
										+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.she] [npc2.has] before sliding forwards and starting to forcefully grind down over [npc2.her] lower abdomen."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to squeeze and grope [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.do] [npc.her] best to try to push them together,"
										+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.she] [npc2.has] before sliding forwards and starting to grind down over [npc2.her] lower abdomen."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to squeeze and grope [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.do] [npc.her] best to try to push them together,"
										+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.she] [npc2.has] before sliding forwards and starting to eagerly grind down over [npc2.her] lower abdomen."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, reaching up to try and help push [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together"
											+ " as [npc2.she] [npc2.verb(encourage)] [npc.name] to fuck [npc2.her] breasts."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly reaching up to try and help push [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together"
											+ " as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to fuck [npc2.her] breasts."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, reaching up to forcefully try and [npc.verb(press)] [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together"
											+ " as [npc2.she] dominantly [npc2.verb(order)] [npc.herHim] to fuck [npc2.her] breasts."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly reaching up to try and help push [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together"
											+ " as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to fuck [npc2.her] breasts."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan] in response, before reaching up to try and help push [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together"
											+ " as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to fuck [npc2.her] breasts."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " reaching up to weakly try and push [npc.herHim] away from [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.her] lower abdomen,"
										+ " before sliding forwards and starting to grind down against [npc2.her] body."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.her] lower abdomen,"
										+ " before sliding forwards and starting to eagerly grind down against [npc2.her] body."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to roughly [npc.verb(press)] [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.her] lower abdomen,"
										+ " before sliding forwards and starting to forcefully grind down against [npc2.her] body."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.her] lower abdomen,"
										+ " before sliding forwards and starting to grind down against [npc2.her] body."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.her] lower abdomen,"
										+ " before sliding forwards and starting to eagerly grind down against [npc2.her] body."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, pushing [npc2.her] stomach out as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly pushing [npc2.her] stomach out as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, forcefully pushing [npc2.her] stomach out as [npc2.she] dominantly [npc2.verb(order)] [npc.herHim] to keep going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, pushing [npc2.her] stomach out as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan] in response, pushing [npc2.her] stomach out as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " reaching up to weakly try and [npc2.verb(push)] [npc.name] away from [npc2.herHim] as [npc2.she] [npc2.verb(beg)] to be left alone."));
							break;
						default:
							break;
					}
				}
			}
				
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(Main.sex.getCharacterTargetedForSexAction(action).isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking [npc2.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, eagerly pushing [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(wrap)] [npc2.her] [npc2.crotchBoobs+] around [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] pillowy mounds."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to leave [npc2.her] [npc2.crotchBoobs] alone.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.her] [npc2.crotchBoobs] alone.",
			
								" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
										+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] [npc2.crotchBoobs]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking [npc2.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, pushing [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(wrap)] [npc2.her] [npc2.crotchBoobs+] around [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] pillowy mounds."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] together in response,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking [npc2.her] cleavage.",
			
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, gently pushing [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(wrap)] [npc2.her] [npc2.crotchBoobs+] around [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] pillowy mounds."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] roughly [npc2.verb(press)] [npc2.her] [npc2.crotchBoobs+] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(order)] [npc.name] to continue fucking [npc2.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, forcefully pressing [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together,"
										+ " [npc2.she] [npc2.verb(order)] [npc.name] to continue thrusting [npc.her] [npc.cock+] up and down between [npc2.her] cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] dominantly [npc2.verb(wrap)] [npc2.her] [npc2.crotchBoobs+] around [npc.namePos] [npc.cock+],"
										+ " before ordering [npc.herHim] to continue fucking [npc2.her] pillowy mounds."));
						break;
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(action).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(attempt)] to push [npc2.her] tiny [npc2.crotchBoobs] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking what little cleavage [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, eagerly attempting to push [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] tiny cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] against the sides of [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] diminutive cleavage."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to leave [npc2.her] [npc2.crotchBoobs] alone.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.her] [npc2.crotchBoobs] alone.",
			
								" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
										+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] [npc2.crotchBoobs]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)] to push [npc2.her] [npc2.crotchBoobs+] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking what little cleavage [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, pushing [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] tiny cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] against the sides of [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] diminutive cleavage."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)] to gently push [npc2.her] [npc2.crotchBoobs+] together in response,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking what little cleavage [npc2.she] [npc2.has].",
			
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, gently pushing [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] tiny cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] against the sides of [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] diminutive cleavage."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)] to roughly push [npc2.verb(press)] [npc2.her] [npc2.crotchBoobs+] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(order)] [npc.name] to continue fucking what little cleavage [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, forcefully pressing [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] together,"
										+ " [npc2.she] [npc2.verb(order)] [npc.name] to continue thrusting [npc.her] [npc.cock+] up and down between [npc2.her] tiny cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] dominantly [npc2.verb(press)] [npc2.her] [npc2.crotchBoobs+] against the sides of [npc.namePos] [npc.cock+],"
										+ " before ordering [npc.herHim] to continue fucking [npc2.her] diminutive cleavage."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] flat stomach out in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue humping [npc2.her] torso.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, eagerly pushing [npc2.her] flat stomach out,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down over [npc2.her] torso.",
			
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.does] [npc2.her] best to try and [npc2.verb(wrap)] [npc2.her] flat stomach around [npc.namePos] [npc.cock+],"
										+ " before giving up and begging for [npc.herHim] to continue humping [npc2.her] torso."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to leave [npc2.her] [npc2.crotchBoobs] alone.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.her] [npc2.crotchBoobs] alone.",
			
								" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
										+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] [npc2.crotchBoobs]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(push)] [npc2.her] flat stomach out in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue humping [npc2.her] torso.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, pushing [npc2.her] flat stomach out,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down over [npc2.her] torso.",
	
								" [npc2.Moaning] in delight, [npc2.name] [npc2.does] [npc2.her] best to try and [npc2.verb(wrap)] [npc2.her] flat stomach around [npc.namePos] [npc.cock+],"
										+ " before giving up and begging for [npc.herHim] to continue humping [npc2.her] torso."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(push)] [npc2.her] flat stomach out in response,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue humping [npc2.her] torso.",
			
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, gently pushing [npc2.her] flat stomach out,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down over [npc2.her] torso.",
	
								" [npc2.Moaning] in delight, [npc2.name] [npc2.does] [npc2.her] best to try and gently [npc2.verb(wrap)] [npc2.her] flat stomach around [npc.namePos] [npc.cock+],"
										+ " before giving up and begging for [npc.herHim] to just continue humping [npc2.her] torso."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] roughly [npc2.verb(thrust)] [npc2.her] flat stomach out in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(order)] [npc.name] to continue humping [npc2.her] torso.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, forcefully pushing [npc2.her] flat stomach out,"
										+ " [npc2.she] [npc2.verb(order)] [npc.name] to continue thrusting [npc.her] [npc.cock+] up and down over [npc2.her] torso.",
	
								" [npc2.Moaning] in delight, [npc2.name] [npc2.does] [npc2.her] best to try and roughly [npc2.verb(wrap)] [npc2.her] flat stomach around [npc.namePos] [npc.cock+],"
										+ " before giving up and ordering [npc.herHim] to just continue humping [npc2.her] torso."));
						break;
				}
			}
		}
		return "";
	}
	

	public static final SexAction FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Gently fuck [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Gently grind your [npc.cock+] against [npc2.namePos] flat stomach.";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently sliding [npc.her] [npc.cock+] between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] steadily bucking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(fuck)] [npc2.her] cleavage.",

						"Gently pushing [npc.her] [npc.cock+] between the cleavage formed between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] softly thrusting [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

						"Softly pushing [npc2.namePos] [npc2.crotchBoobs+] together, [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] slowly [npc.verb(fuck)] [npc2.her] cleavage."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently sliding [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.crotchBoobs],"
								+ " [npc.name] [npc.verb(start)] steadily bucking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Gently pushing [npc.her] [npc.cock+] between the tiny amount of cleavage formed between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] softly thrusting [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(grind)] up and down over [npc2.her] lower abdomen.",

						"Softly trying to push [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs] together, [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] slowly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] lower abdomen."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently sliding [npc.her] [npc.cock+] over [npc2.namePos] flat lower abdomen,"
								+ " [npc.name] [npc.verb(start)] steadily bucking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(grind)] against [npc2.her] torso.",

						"Gently pushing [npc.her] [npc.cock+] down against [npc2.namePos] flat [npc2.crotchBoobs],"
								+ " [npc.name] [npc.verb(start)] softly thrusting [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(grind)] up and down over [npc2.her] lower abdomen.",

						"Softly groping [npc2.namePos] flat lower abdomen, [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] slowly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] torso."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this)));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Continue fucking [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Continue grinding against [npc2.namePos] stomach.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] cleavage.",

						"Desperately pushing [npc.her] [npc.cock+] between the cleavage formed between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

						"Greedily pushing [npc2.namePos] [npc2.crotchBoobs+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] eagerly [npc.verb(fuck)] [npc2.her] cleavage."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.crotchBoobs],"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Desperately pushing [npc.her] [npc.cock+] between the tiny amount of cleavage formed between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] lower abdomen.",

						"Greedily trying to push [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] eagerly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] lower abdomen."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] over [npc2.namePos] flat lower abdomen,"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(grind)] against [npc2.her] torso.",

						"Desperately pushing [npc.her] [npc.cock+] down against [npc2.namePos] flat [npc2.crotchBoobs],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] lower abdomen.",

						"Greedily groping [npc2.namePos] flat lower abdomen, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] eagerly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] torso."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Roughly fuck [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Roughly grind your [npc.cock+] against [npc2.namePos] flat stomach.";
			}
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Roughly slamming [npc.her] [npc.cock+] between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] violently bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] forcefully [npc.verb(fuck)] [npc2.her] cleavage.",

						"Violently pushing [npc.her] [npc.cock+] between the cleavage formed between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] roughly thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] dominantly [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

						"Greedily pushing [npc2.namePos] [npc2.crotchBoobs+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to dominantly pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] roughly [npc.verb(fuck)] [npc2.her] cleavage."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Roughly slamming [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.crotchBoobs],"
								+ " [npc.name] [npc.verb(start)] violently bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] forcefully [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Violently pushing [npc.her] [npc.cock+] between the tiny amount of cleavage formed between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] roughly thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] dominantly [npc.verb(grind)] up and down over [npc2.her] lower abdomen.",

						"Greedily trying to push [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to dominantly pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] roughly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] lower abdomen."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Roughly pushing [npc.her] [npc.cock+] over [npc2.namePos] flat lower abdomen,"
								+ " [npc.name] [npc.verb(start)] violently bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] forcefully [npc.verb(grind)] against [npc2.her] torso.",

						"Violently pushing [npc.her] [npc.cock+] down against [npc2.namePos] flat [npc2.crotchBoobs],"
								+ " [npc.name] [npc.verb(start)] roughly thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] dominantly [npc.verb(grind)] up and down over [npc2.her] lower abdomen.",

						"Greedily groping [npc2.namePos] flat lower abdomen, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to dominantly pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] roughly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] torso."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this)));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Continue fucking [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Continue grinding against [npc2.namePos] flat stomach.";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Sliding [npc.her] [npc.cock+] between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] [npc.verb(fuck)] [npc2.her] cleavage.",

						"Pushing [npc.her] [npc.cock+] between the cleavage formed between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

						"Pushing [npc2.namePos] [npc2.crotchBoobs+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(fuck)] [npc2.her] cleavage."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Sliding [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.crotchBoobs],"
								+ " [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Pushing [npc.her] [npc.cock+] between the tiny amount of cleavage formed between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] lower abdomen.",

						"Trying to push [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] lower abdomen."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Sliding [npc.her] [npc.cock+] over [npc2.namePos] flat lower abdomen,"
								+ " [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] [npc.verb(grind)] against [npc2.her] torso.",

						"Pushing [npc.her] [npc.cock+] down against [npc2.namePos] flat [npc2.crotchBoobs],"
								+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] lower abdomen.",

						"Groping [npc2.namePos] flat lower abdomen, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] torso."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Eagerly fuck [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Eagerly grind against [npc2.namePos] flat stomach.";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] cleavage.",

						"Desperately pushing [npc.her] [npc.cock+] between the cleavage formed between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

						"Greedily pushing [npc2.namePos] [npc2.crotchBoobs+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] eagerly [npc.verb(fuck)] [npc2.her] cleavage."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.crotchBoobs],"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Desperately pushing [npc.her] [npc.cock+] between the tiny amount of cleavage formed between [npc2.namePos] [npc2.crotchBoobs+],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] lower abdomen.",

						"Greedily trying to push [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] eagerly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] lower abdomen."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] over [npc2.namePos] flat lower abdomen,"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(grind)] against [npc2.her] torso.",

						"Desperately pushing [npc.her] [npc.cock+] down against [npc2.namePos] flat [npc2.crotchBoobs],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] lower abdomen.",

						"Greedily groping [npc2.namePos] flat lower abdomen, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] eagerly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] torso."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist giving "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Try to pull your [npc.cock] away from [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Try to pull your [npc.cock] away from [npc2.namePos] stomach.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] cleavage, but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " pressing [npc2.her] [npc2.crotchBoobs+] together while gently reminding [npc.herHim] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.crotchBoobs+], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " softly [npc2.moaning] as [npc2.she] [npc2.verb(ignore)] [npc.her] desperate protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of [npc2.namePos] cleavage, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] softly [npc2.moaning] as [npc2.she] firmly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] cleavage, but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " pressing [npc2.her] [npc2.crotchBoobs+] together while growling that [npc2.she]'ll use [npc.herHim] however [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.crotchBoobs+], but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of [npc2.namePos] cleavage, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] roughly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.crotchBoobs+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] cleavage, but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " pressing [npc2.her] [npc2.crotchBoobs+] together while [npc2.moaning] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.crotchBoobs+], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of [npc2.namePos] cleavage, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] eagerly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.crotchBoobs+]."));
						break;
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of the small amount of cleavage that [npc2.name] [npc2.has], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " trying to press [npc2.her] [npc2.crotchBoobs+] together while gently reminding [npc.herHim] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " softly [npc2.moaning] as [npc2.she] [npc2.verb(ignore)] [npc.her] desperate protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of the tiny amount of cleavage that [npc2.name] have on offer, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] softly [npc2.moaning] as [npc2.she] firmly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of the small amount of cleavage that [npc2.name] [npc2.has], but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " trying to press [npc2.her] [npc2.crotchBoobs+] together while growling that [npc2.she]'ll use [npc.herHim] however [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs], but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of the tiny amount of cleavage that [npc2.name] have on offer, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] roughly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.crotchBoobs+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of the small amount of cleavage that [npc2.name] [npc2.has], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " trying to press [npc2.her] [npc2.crotchBoobs+] together while [npc2.moaning] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of the tiny amount of cleavage that [npc2.name] have on offer, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] eagerly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.crotchBoobs+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] away from [npc2.namePos] flat lower abdomen, but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " grinding against [npc.herHim] as [npc2.she] gently [npc2.moanVerb] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] lower abdomen, but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " softly [npc2.moaning] as [npc2.she] [npc2.verb(ignore)] [npc.her] desperate protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull away from [npc2.name], but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] softly [npc2.moaning] as [npc2.she] firmly [npc2.verb(force)] [npc.her] [npc.cock+] against [npc2.her] lower abdomen."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] away from [npc2.namePos] lower abdomen, but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " forcefully grinding against [npc.herHim] as [npc2.she] [npc2.verb(growl)] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] lower abdomen, but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull away from [npc2.name], but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] roughly [npc2.verb(force)] [npc.namePos] [npc.cock+] against [npc2.her] lower abdomen."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] away from [npc2.namePos] lower abdomen, but [npc2.name] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " grinding against [npc.herHim] as [npc2.she] [npc2.moanVerb] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] lower abdomen, but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull away from [npc2.name], but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] eagerly [npc2.verb(force)] [npc.her] [npc.cock+] against [npc2.her] lower abdomen."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop receiving "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Pull your [npc.cock+] away from [npc2.namePos] [npc2.crotchBoobs+] and stop fucking them.";
			} else {
				return "Pull your [npc.cock+] away from [npc2.namePos] lower abdomen and stop grinding against [npc2.herHim].";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pushing [npc2.name] away,"
										+ " [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.her] cleavage and [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.crotchBoobs+].",

								"Roughly pulling [npc.her] [npc.cock+] out from [npc2.namePos] cleavage, [npc.name] [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.crotchBoobs+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.namePos] cleavage and [npc.verb(tell)] [npc2.name] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.crotchBoobs+].",

								"Pulling [npc.her] [npc.cock+] out from [npc2.namePos] cleavage, [npc.name] [npc.verb(tell)] [npc2.name] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.crotchBoobs+]."));
						break;
				}
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pushing [npc2.name] away, [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.her] tiny amount of cleavage"
										+ " and [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs].",

								"Roughly pulling [npc.her] [npc.cock+] out from [npc2.namePos] tiny amount of cleavage,"
										+ " [npc.name] [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.namePos] tiny amount of cleavage"
										+ " and [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs].",

								"Pulling [npc.her] [npc.cock+] out from [npc2.namePos] tiny amount of cleavage,"
										+ " [npc.name] [npc.verb(tell)] [npc2.name] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs]."));
						break;
				}
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pushing [npc2.name] away, [npc.name] takes [npc.her] [npc.cock+] away from [npc2.her] lower abdomen and [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of grinding against [npc2.herHim].",

								"Roughly pulling [npc.her] [npc.cock+] away from [npc2.namePos] lower abdomen, [npc.name] [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of grinding against [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] takes [npc.her] [npc.cock+] away from [npc2.namePos] lower abdomen and [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of grinding against [npc2.herHim].",

								"Pulling [npc.her] [npc.cock+] away from [npc2.namePos] lower abdomen, [npc.name] [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of grinding against [npc2.herHim]."));
						break;
				}
			}
			

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)] struggling against [npc.herHim], [npc2.moaning+] as [npc2.she] [npc2.verb(beg)] [npc.herHim] to leave [npc2.herHim] alone.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(beg)] [npc.herHim] to leave [npc2.herHim] alone, tears welling up in [npc2.her] [npc2.eyes] as [npc2.she] weakly [npc2.verb(try)] to push [npc.herHim] away."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], betraying [npc2.her] desire for [npc.herHim] to continue.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to keep on using [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction USING_COCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreastsCrotch()
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs();
		}
		
		@Override
		public String getActionTitle() {
			return "Perform "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Use [npc2.namePos] [npc2.cock+] to fuck your [npc.crotchBoobs+].";
			} else {
				return "Use [npc2.namePos] [npc2.cock+] to grind against your lower abdomen.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] cleavage, and, sliding forwards,"
								+ " [npc.she] [npc.verb(press)] [npc.her] [npc.crotchBoobs+] together and [npc.verb(start)] giving [npc2.herHim] a titfuck."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] cleavage, and, sliding forwards,"
								+ " [npc.she] [npc.verb(press)] [npc.her] [npc.crotchBoobs+] together and [npc.verb(start)] giving [npc2.herHim] an enthusiastic titfuck."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grabbing hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(pull)] it up to [npc.her] cleavage, and, sliding forwards,"
								+ " [npc.she] [npc.verb(press)] [npc.her] [npc.crotchBoobs+] together and [npc.verb(start)] giving [npc2.herHim] a forceful titfuck."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] cleavage, and, sliding forwards,"
								+ " [npc.she] [npc.verb(press)] [npc.her] [npc.crotchBoobs+] together and [npc.verb(start)] giving [npc2.herHim] a titfuck."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] cleavage, and, sliding forwards,"
								+ " [npc.she] [npc.verb(press)] [npc.her] [npc.crotchBoobs+] together and [npc.verb(start)] giving [npc2.herHim] an enthusiastic titfuck."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response,"
											+ " helping to push [npc.namePos] [npc.crotchBoobs] together as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " eagerly helping to push [npc.namePos] [npc.crotchBoobs] together as [npc2.she] enthusiastically [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " roughly pushing [npc.namePos] [npc.crotchBoobs] together as [npc2.she] [npc2.verb(demand)] that [npc.she] keep on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " eagerly helping to push [npc.namePos] [npc.crotchBoobs] together as [npc2.she] enthusiastically [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan] in response,"
											+ " helping to push [npc.namePos] [npc.crotchBoobs] together as [npc2.she] meekly [npc2.verb(ask)] [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
											+ " weakly trying to push [npc.name] away as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to what little cleavage [npc.she] [npc.has], and, sliding forwards,"
										+ " [npc.she] [npc.verb(try)] [npc.her] best to press [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] together in order to give [npc2.herHim] a titfuck."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to what little cleavage [npc.she] [npc.has], and, sliding forwards,"
										+ " [npc.she] [npc.verb(try)] [npc.her] best to press [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] together in order to give [npc2.herHim] an enthusiastic titfuck."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grabbing hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to what little cleavage [npc.she] [npc.has], and, sliding forwards,"
										+ " [npc.she] [npc.verb(try)] [npc.her] best to press [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] together in order to give [npc2.herHim] a forceful titfuck."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to what little cleavage [npc.she] [npc.has], and, sliding forwards,"
										+ " [npc.she] [npc.verb(try)] [npc.her] best to press [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] together in order to give [npc2.herHim] a titfuck."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to what little cleavage [npc.she] [npc.has], and, sliding forwards,"
										+ " [npc.she] [npc.verb(try)] [npc.her] best to press [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] together in order to give [npc2.herHim] an enthusiastic titfuck."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, gently thrusting into [npc.namePos] lower abdomen as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, desperately thrusting into [npc.namePos] lower abdomen as [npc2.she] enthusiastically [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, roughly thrusting into [npc.namePos] lower abdomen as [npc2.she] [npc2.verb(demand)] that [npc.herHim] keep on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly thrusting into [npc.namePos] lower abdomen as [npc2.she] enthusiastically [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan] in response, thrusting into [npc.namePos] lower abdomen as [npc2.she] meekly [npc2.verb(ask)] [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, weakly trying to push [npc.name] away as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] flat lower abdomen, and, sliding forwards,"
										+ " [npc.she] [npc.verb(grind)] [npc.her] torso against [npc2.her] [npc2.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] flat lower abdomen, and, sliding forwards,"
										+ " [npc.she] enthusiastically [npc.verb(grind)] [npc.her] torso against [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grabbing hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] flat lower abdomen, and, sliding forwards,"
										+ " [npc.she] forcefully [npc.verb(grind)] [npc.her] torso against [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] flat lower abdomen, and, sliding forwards,"
										+ " [npc.she] [npc.verb(grind)] [npc.her] torso against [npc2.her] [npc2.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] flat lower abdomen, and, sliding forwards,"
										+ " [npc.she] enthusiastically [npc.verb(grind)] [npc.her] torso against [npc2.her] [npc2.cock+]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, gently thrusting into [npc.namePos] lower abdomen as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, desperately thrusting into [npc.namePos] lower abdomen as [npc2.she] enthusiastically [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, roughly thrusting into [npc.namePos] lower abdomen as [npc2.she] [npc2.verb(demand)] that [npc.herHim] keep on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly thrusting into [npc.namePos] lower abdomen as [npc2.she] enthusiastically [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan] in response, thrusting into [npc.namePos] lower abdomen as [npc2.she] meekly [npc2.verb(ask)] [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, weakly trying to push [npc.name] away as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.crotchBoobs+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(fuck)] [npc.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.crotchBoobs+].",
										
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] in and out between [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.crotchBoobs+],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] back and forth between [npc.her] [npc.crotchBoobs+].",
			
								" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] between [npc.namePos] [npc.crotchBoobs+],"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(fuck)] [npc.her] cleavage.",
			
								" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] gently thrusting [npc2.her] [npc2.cock+] between [npc.namePos] [npc.crotchBoobs+].",
										
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] back and forth between [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.crotchBoobs+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(fuck)] [npc.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] roughly slamming [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.crotchBoobs+].",
										
								" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] back and forth between [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.crotchBoobs+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(fuck)] [npc.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.crotchBoobs+].",
										
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] back and forth between [npc.namePos] [npc.crotchBoobs+]."));
						break;
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] into what little cleavage [npc.name] [npc.has] to offer,"
										+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(fuck)] [npc.her] [npc.crotchBoobs+].",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] between [npc.namePos] [npc.crotchBoobSize] [npc.crotchBoobs].",
										
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] in and out of the diminutive cleavage formed between [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.crotchBoobs+],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] back and forth between [npc.her] [npc.crotchBoobs+].",
			
								" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] into what little cleavage [npc.name] [npc.has] to offer,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(fuck)] [npc.her] [npc.crotchBoobs+].",
			
								" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] gently thrusting [npc2.her] [npc2.cock+] between [npc.namePos]  [npc.crotchBoobSize] [npc.crotchBoobs].",
										
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] in and out of the diminutive cleavage formed between [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] into what little cleavage [npc.name] [npc.has] to offer,"
										+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(fuck)] [npc.her] [npc.crotchBoobs+].",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] roughly slamming [npc2.her] [npc2.cock+] between  [npc.crotchBoobSize] [npc.crotchBoobs].",
										
								" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] in and out of the diminutive cleavage formed between [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] into what little cleavage [npc.name] [npc.has] to offer,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(fuck)] [npc.her] [npc.crotchBoobs+].",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] between  [npc.crotchBoobSize] [npc.crotchBoobs].",
										
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] in and out of the diminutive cleavage formed between [npc.namePos] [npc.crotchBoobs+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] over [npc.namePos] flat stomach,"
										+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(grind)] against [npc.her] torso.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] up and down against [npc.namePos] flat stomach.",
										
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.her] [npc2.cock+] over [npc.namePos] flat stomach."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] flat stomach,"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] back and forth over [npc.her] flat stomach.",
			
								" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] flat stomach."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] over [npc.namePos] flat stomach,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(grind)] against [npc.her] torso.",
			
								" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] gently thrusting [npc2.her] [npc2.cock+] up and down against [npc.namePos] flat stomach.",
										
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(grind)] [npc2.her] [npc2.cock+] over [npc.namePos] flat stomach."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] over [npc.namePos] flat stomach,"
										+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(grind)] against [npc.her] torso.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] roughly slamming [npc2.her] [npc2.cock+] up and down against [npc.namePos] flat stomach.",
										
								" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(grind)] [npc2.her] [npc2.cock+] over [npc.namePos] flat stomach."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] over [npc.namePos] flat stomach,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(grind)] against [npc.her] torso.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] up and down against [npc.namePos] flat stomach.",
										
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.cock+] over [npc.namePos] flat stomach."));
						break;
				}
			}
		}
		return "";
	}
	
	public static final SexAction PERFORMING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently perform "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Gently pleasure [npc2.namePos] [npc2.cock+] with your [npc.crotchBoobs+].";
			} else {
				return "Gently pleasure [npc2.namePos] [npc2.cock+] with your stomach.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to push [npc.her] [npc.crotchBoobs+] together around [npc2.namePos] [npc2.cock+],"
								+ " [npc.name] gently [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, softly [npc.moaning] as [npc.she] [npc.verb(use)] [npc.her] cleavage.",

						"Gently wrapping [npc.her] [npc.crotchBoobs+] around [npc2.namePos] [npc2.cock+], [npc.name] slowly [npc.verb(lift)] them up and down,"
								+ " letting out a soft [npc.moan] as [npc.she] lovingly [npc.verb(give)] [npc2.herHim] a titfuck.",

						"Letting out a soft [npc.moan], [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs+] together,"
								+ " enveloping [npc2.namePos] [npc2.cock+] in [npc.her] pillowy mounds as [npc.she] [npc.verb(give)] [npc2.herHim] a loving titfuck."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to push [npc.her] [npc.crotchBoobs+] against the sides of [npc2.namePos] [npc2.cock+],"
									+ " [npc.name] gently [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, softly [npc.moaning] as [npc.she] [npc.verb(try)] [npc.her] best to use what little cleavage [npc.she] [npc.has].",

							"Gently pressing [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] against the sides of [npc2.namePos] [npc2.cock+], [npc.name] slowly [npc.verb(lift)] them up and down,"
									+ " letting out a soft [npc.moan] as [npc.she] lovingly [npc.verb(attempt)] to give [npc2.herHim] a titfuck.",

							"Letting out a soft [npc.moan], [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs+] together,"
									+ " trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] gently [npc.verb(raise)] and [npc.verb(lower)] [npc.namePos] torso,"
									+ " softly [npc.moaning] as [npc.she] [npc.verb(thrust)] out [npc.her] flat lower abdomen and [npc.verb(grind)] against [npc2.herHim].",

							"Gently wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] [npc.her] torso up and down,"
									+ " grinding [npc.her] flat lower abdomen against [npc2.herHim] as [npc.she] [npc.verb(try)] to imitate giving [npc2.herHim] a titfuck.",

							"Letting out a soft [npc.moan], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+],"
									+ " before thrusting [npc.her] flat lower abdomen out and giving [npc2.herHim] an imitation titfuck"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Perform "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Pleasure [npc2.namePos] [npc2.cock+] with your [npc.crotchBoobs+].";
			} else {
				return "Pleasure [npc2.namePos] [npc2.cock+] with your flat stomach.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to happily push [npc.her] [npc.crotchBoobs+] together around [npc2.namePos] [npc2.cock+],"
								+ " [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] cleavage to give [npc2.herHim] an eager titfuck.",

						"Eagerly wrapping [npc.her] [npc.crotchBoobs+] around [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] them up and down,"
								+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] an enthusiastic titfuck.",

						"Letting out [npc.a_moan+], [npc.name] happily [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] together,"
								+ " enveloping [npc2.namePos] [npc2.cock+] in [npc.her] pillowy mounds as [npc.she] [npc.verb(give)] [npc2.herHim] an eager titfuck."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily push [npc.her] [npc.crotchBoobs+] against the sides of [npc2.namePos] [npc2.cock+],"
									+ " [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(try)] [npc.her] best to use what little cleavage [npc.she] [npc.has].",

							"Eagerly pressing [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] against the sides of [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] them up and down,"
									+ " letting out [npc.a_moan+] as [npc.she] enthusiastically [npc.verb(attempt)] to give [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] happily [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] together,"
									+ " trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily wrap [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.namePos] torso,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(thrust)] out [npc.her] flat lower abdomen to desperately grind against [npc2.herHim].",

							"Eagerly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] [npc.her] torso up and down,"
									+ " grinding [npc.her] flat lower abdomen against [npc2.herHim] as [npc.she] [npc.verb(try)] to imitate giving [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] happily [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+],"
									+ " before thrusting [npc.her] flat lower abdomen out and eagerly giving [npc2.herHim] an imitation titfuck"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly perform "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Roughly pleasure [npc2.namePos] [npc2.cock+] with your [npc.crotchBoobs+].";
			} else {
				return "Roughly pleasure [npc2.namePos] [npc2.cock+] with your flat stomach.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to roughly force [npc.her] [npc.crotchBoobs+] together around [npc2.namePos] [npc2.cock+],"
								+ " [npc.name] rapidly [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] cleavage to give [npc2.herHim] a dominant titfuck.",

						"Dominantly wrapping [npc.her] [npc.crotchBoobs+] around [npc2.namePos] [npc2.cock+], [npc.name] roughly [npc.verb(bounce)] them up and down,"
								+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] a forceful titfuck.",

						"Letting out [npc.a_moan+], [npc.name] forcefully [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] together,"
								+ " enveloping [npc2.namePos] [npc2.cock+] in [npc.her] pillowy mounds as [npc.she] [npc.verb(give)] [npc2.herHim] a dominant titfuck."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to roughly force [npc.her] [npc.crotchBoobs+] against the sides of [npc2.namePos] [npc2.cock+],"
									+ " [npc.name] rapidly [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(try)] [npc.her] best to use what little cleavage [npc.she] [npc.has].",

							"Dominantly pressing [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] against the sides of [npc2.namePos] [npc2.cock+], [npc.name] roughly [npc.verb(lift)] them up and down,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(attempt)] to give [npc2.herHim] a forceful titfuck.",

							"Letting out [npc.a_moan+], [npc.name] forcefully [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] together,"
									+ " trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to roughly wrap [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] violently [npc.verb(raise)] and [npc.verb(lower)] [npc.namePos] torso,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(thrust)] out [npc.her] flat lower abdomen to dominantly grind against [npc2.herHim].",

							"Dominantly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] roughly [npc.verb(lift)] [npc.her] torso up and down,"
									+ " grinding [npc.her] flat lower abdomen against [npc2.herHim] as [npc.she] [npc.verb(try)] to imitate giving [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] forcefully [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+],"
									+ " before thrusting [npc.her] flat lower abdomen out and roughly giving [npc2.herHim] an imitation titfuck"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Perform "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Pleasure [npc2.namePos] [npc2.cock+] with your [npc.crotchBoobs+].";
			} else {
				return "Pleasure [npc2.namePos] [npc2.cock+] with your flat stomach.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to push [npc.her] [npc.crotchBoobs+] together around [npc2.namePos] [npc2.cock+],"
								+ " [npc.name] [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] cleavage to give [npc2.herHim] a titfuck.",

						"Wrapping [npc.her] [npc.crotchBoobs+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] them up and down,"
								+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] a titfuck.",

						"Letting out [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs+] together,"
								+ " enveloping [npc2.namePos] [npc2.cock+] in [npc.her] pillowy mounds as [npc.she] [npc.verb(give)] [npc2.herHim] a titfuck."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily push [npc.her] [npc.crotchBoobs+] against the sides of [npc2.namePos] [npc2.cock+],"
									+ " [npc.name] [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(try)] [npc.her] best to use what little cleavage [npc.she] [npc.has].",

							"Pressing [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] against the sides of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] them up and down,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(attempt)] to give [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs+] together,"
									+ " trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily wrap [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(raise)] and [npc.verb(lower)] [npc.namePos] torso,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(thrust)] out [npc.her] flat lower abdomen to grind against [npc2.herHim].",

							"Wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] [npc.her] torso up and down,"
									+ " grinding [npc.her] flat lower abdomen against [npc2.herHim] as [npc.she] [npc.verb(try)] to imitate giving [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+],"
									+ " before thrusting [npc.her] flat lower abdomen out and giving [npc2.herHim] an imitation titfuck"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly perform "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Eagerly pleasure [npc2.namePos] [npc2.cock+] with your [npc.crotchBoobs+].";
			} else {
				return "Eagerly pleasure [npc2.namePos] [npc2.cock+] with your flat stomach.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to happily push [npc.her] [npc.crotchBoobs+] together around [npc2.namePos] [npc2.cock+],"
								+ " [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] cleavage to give [npc2.herHim] an eager titfuck.",

						"Eagerly wrapping [npc.her] [npc.crotchBoobs+] around [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] them up and down,"
								+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] an enthusiastic titfuck.",

						"Letting out [npc.a_moan+], [npc.name] happily [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] together,"
								+ " enveloping [npc2.namePos] [npc2.cock+] in [npc.her] pillowy mounds as [npc.she] [npc.verb(give)] [npc2.herHim] an eager titfuck."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily push [npc.her] [npc.crotchBoobs+] against the sides of [npc2.namePos] [npc2.cock+],"
									+ " [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(try)] [npc.her] best to use what little cleavage [npc.she] [npc.has].",

							"Eagerly pressing [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] against the sides of [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] them up and down,"
									+ " letting out [npc.a_moan+] as [npc.she] enthusiastically [npc.verb(attempt)] to give [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] happily [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] together,"
									+ " trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily wrap [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.namePos] torso,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(thrust)] out [npc.her] flat lower abdomen to desperately grind against [npc2.herHim].",

							"Eagerly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] [npc.her] torso up and down,"
									+ " grinding [npc.her] flat lower abdomen against [npc2.herHim] as [npc.she] [npc.verb(try)] to imitate giving [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] happily [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+],"
									+ " before thrusting [npc.her] flat lower abdomen out and eagerly giving [npc2.herHim] an imitation titfuck"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist performing "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Try and pull your [npc.crotchBoobs+] away from [npc2.namePos] [npc2.cock+].";
			} else {
				return "Try and pull your flat stomach away from [npc2.namePos] [npc2.cock+].";
			}
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] out [npc.moan+] as [npc.she] [npc.verb(try)] to pull [npc.her] [npc.crotchBoobs+] away from [npc2.namePos] [npc2.cock+],"
								+ " before begging for [npc2.herHim] to leave [npc.herHim] alone.",

						"With [npc.a_moan+], [npc.name] weakly [npc.verb(try)] to pull away from [npc2.name],"
								+ " sobbing in distress as [npc2.her] [npc2.cock+] continues to thrust up between [npc.her] [npc.crotchBoobs+].",

						"Letting out [npc.a_moan+], [npc.name] [npc.verb(try)] to push [npc2.name] away from [npc.herHim],"
								+ " tears running down [npc.her] cheeks as [npc2.she] [npc2.verb(continue)] thrusting [npc2.her] [npc2.cock+] into [npc.her] cleavage."));
				
			} else if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] out [npc.moan+] as [npc.she] [npc.verb(try)] to pull [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] away from [npc2.namePos] [npc2.cock+],"
								+ " before begging for [npc.herHim] to leave [npc2.herHim] alone.",

						"With [npc.a_moan+], [npc.name] weakly [npc.verb(try)] to pull away from [npc2.name],"
								+ " sobbing in distress as [npc2.her] [npc2.cock+] continue to thrust up between [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs+].",

						"Letting out [npc.a_moan+], [npc.name] [npc.verb(try)] to push [npc2.name] away from [npc.herHim],"
								+ " tears running down [npc.her] cheeks as [npc2.she] [npc2.verb(continue)] thrusting [npc2.her] [npc2.cock+] into [npc.her] small cleavage."));
						
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] out [npc.moan+] as [npc.she] [npc.verb(try)] to pull [npc.her] flat lower abdomen away from [npc2.namePos] [npc2.cock+],"
								+ " before begging for [npc.herHim] to leave [npc2.herHim] alone.",

						"With [npc.a_moan+], [npc.name] weakly [npc.verb(try)] to pull away from [npc2.name],"
								+ " sobbing in distress as [npc2.her] [npc2.cock+] continues to grind up against [npc.her] flat lower abdomen.",

						"Letting out [npc.a_moan+], [npc.name] [npc.verb(try)] to push [npc2.name] away from [npc.herHim],"
								+ " tears running down [npc.her] cheeks as [npc2.she] [npc2.verb(continue)] thrusting [npc2.her] [npc2.cock+] against [npc.her] torso."));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop performing "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Push [npc2.namePos] [npc2.cock] away from your [npc.crotchBoobs+].";
			} else {
				return "Push [npc2.namePos] [npc2.cock] away from your flat stomach.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly [npc.verb(push)] [npc2.name] away from [npc.herHim], and, in a menacing tone, [npc.verb(order)] [npc2.herHim] to stop fucking [npc.her] [npc.crotchBoobs+].",

								"With a menacing growl, [npc.name] roughly [npc.verb(push)] [npc2.name] away, and [npc.verb(order)] [npc2.herHim] to stop fucking [npc.her] [npc.crotchBoobs+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc2.name] away from [npc.herHim], and [npc.verb(tell)] [npc2.herHim] to stop fucking [npc.her] [npc.crotchBoobs+].",

								"With one last [npc.moan], [npc.name] [npc.verb(push)] [npc2.name] away, and [npc.verb(tell)] [npc2.herHim] to stop fucking [npc.her] [npc.crotchBoobs+]."));
						break;
				}
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly [npc.verb(push)] [npc2.name] away from [npc.herHim], and, in a menacing tone, [npc.verb(order)] [npc2.herHim] to stop grinding against [npc.her] lower abdomen.",

								"With a menacing growl, [npc.name] roughly [npc.verb(push)] [npc2.name] away, and [npc.verb(order)] [npc2.herHim] to stop grinding against [npc.her] lower abdomen."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc2.name] away from [npc.herHim], and [npc.verb(tell)] [npc2.herHim] to stop grinding against [npc.her] lower abdomen.",

								"With one last [npc.moan], [npc.name] [npc.verb(push)] [npc2.name] away, and [npc.verb(tell)] [npc2.herHim] to stop grinding against [npc.her] lower abdomen."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)] struggling against [npc.herHim], [npc2.moaning+] as [npc2.she] [npc2.verb(beg)] [npc.name] to leave [npc2.herHim] alone.",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(beg)] [npc.name] to leave [npc2.herHim] alone, tears welling up in [npc2.her] [npc2.eyes] as [npc2.she] weakly [npc2.verb(try)] to push [npc.herHim] away."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] can't [npc2.verb(help)] but let out [npc2.a_moan+], betraying [npc2.her] desire for more of [npc.namePos] attention.",
	
								" With [npc2.a_moan+], [npc2.she] [npc2.verb(beg)] for [npc.name] to keep on using [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
