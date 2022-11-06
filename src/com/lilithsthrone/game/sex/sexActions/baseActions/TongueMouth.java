package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.3.5
 * @author Innoxia
 */
public class TongueMouth {
	
	public static final SexAction KISS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isOrificeNonSelfOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
		
		@Override
		public String getActionTitle() {
			return "Start kissing";
		}

		@Override
		public String getActionDescription() {
			return "Press your [npc.lips] against [npc2.namePos] mouth and start making out with [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.COWGIRL)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.COWGIRL_REVERSE)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SITTING_IN_LAP)) {
				
				if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(sink)] down onto [npc2.namePos] [npc2.penis], letting out [npc.a_moan+] as [npc.her] [npc.asshole+] grips down around [npc2.her] [npc2.penisGirth] [npc2.penis+]."
								+ " As [npc.her] [npc.moan] trails off, [npc.name] [npc.verb(lean)] down and [npc.verb(grab)] [npc2.namePos] head in both hands,"
									+ " before pressing [npc.her] [npc.lips+] against [npc2.hers] and delivering a passionate kiss.",
									
							"With [npc.a_moan+], [npc.name] [npc.verb(slide)] down onto [npc2.namePos] [npc2.penis+], before leaning down and pressing [npc.herself] against [npc2.her] [npc2.breasts]."
									+ " Breathing in [npc2.namePos] [npc2.scent+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.hers] and [npc.verb(start)] to passionately kiss [npc2.herHim].",
							
							"[npc.Name] [npc.verb(let)] [npc2.namePos] [npc2.penis+] slide even deeper into [npc.her] [npc.asshole+] as [npc.she] [npc.verb(lean)] down and [npc.verb(pull)] [npc2.herHim] into a desperate kiss.",
							
							"Dropping down, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(bury)] [npc2.namePos] [npc2.penis+] in [npc.her] [npc.asshole+],"
							+ " before leaning down and pressing [npc.her] [npc.lips+] against [npc2.hers]."));
					
				} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(sink)] down onto [npc2.namePos] [npc2.penis], letting out [npc.a_moan+] as [npc.her] [npc.vagina+] grips down around [npc2.her] [npc2.penisGirth] [npc2.penis+]."
								+ " As [npc.her] [npc.moan] trails off, [npc.name] [npc.verb(lean)] down and [npc.verb(grab)] [npc2.namePos] head in both hands,"
									+ " before pressing [npc.her] [npc.lips+] against [npc2.hers] and delivering a passionate kiss.",
									
							"With [npc.a_moan+], [npc.name] [npc.verb(slide)] down onto [npc2.namePos] [npc2.penis+], before leaning down and pressing [npc.herself] against [npc2.her] [npc2.breasts]."
									+ " Breathing in [npc2.namePos] [npc2.scent+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.hers] and [npc.verb(start)] to passionately kiss [npc2.herHim].",
							
							"[npc.Name] [npc.verb(let)] [npc2.namePos] [npc2.penis+] [npc2.verb(slide)] even deeper into [npc.her] [npc.vagina+] as [npc.she] [npc.verb(lean)] down and [npc.verb(pull)] [npc2.herHim] into a desperate kiss.",
							
							"Dropping down, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(bury)] [npc2.namePos] [npc2.penis+] in [npc.her] [npc.vagina+],"
							+ " before leaning down and pressing [npc.her] [npc.lips+] against [npc2.hers]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(lean)] down, pressing [npc.her] [npc.lips+] against [npc2.namePos] mouth as [npc.she] [npc.verb(deliver)] a passionate kiss.",
							
							"With a grin, [npc.name] [npc.verb(lean)] down into [npc2.namePos] [npc2.breasts], breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.hers].",
							
							"Leaning down, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] and [npc.verb(start)] to eagerly kiss [npc2.herHim]."));
				}

				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.tongue] into [npc.namePos] mouth,"
									+ " pressing [npc2.her] [npc2.lips+] against [npc.hers] as [npc2.she] eagerly [npc2.verb(return)] [npc.namePos] display of affection.",
							
							" With [npc2.a_moan], [npc2.name] [npc2.verb(lean)] into [npc.name],"
									+ " muffling [npc2.her] [npc2.moans] into [npc.her] mouth as [npc2.she] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.tongue] past [npc.her] [npc.lips+].",
							
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(lean)] into [npc.name],"
									+ " pressing [npc2.her] [npc2.lips+] against [npc.nameHers] as [npc2.she] happily [npc2.verb(slide)] [npc2.her] [npc2.tongue] into [npc.her] mouth."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(try)] to pull away, [npc2.sobbing] and squirming in discomfort as [npc.name] [npc.verb(force)] [npc.herself] on [npc2.herHim].",
							
							" [npc2.NamePos] [npc2.sob+] is muffled into [npc.namePos] mouth as [npc2.she] [npc2.verb(try)] to pull away, squirming in discomfort as [npc.name] [npc.verb(force)] [npc.herself] on [npc2.herHim].",
							
							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from [npc.name],"
									+ " protesting and squirming in discomfort as [npc.name] [npc.verb(force)] [npc.her] [npc.tongue] past [npc2.her] reluctant [npc2.lips]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] happily [npc2.verb(push)] [npc2.her] [npc2.tongue] deep into [npc.namePos] mouth,"
									+ " eagerly pressing [npc2.her] [npc2.lips+] back against [npc.hers] and [npc2.moaning] in delight as [npc2.she] greedily [npc2.verb(return)] [npc.her] display of affection.",
							
							" With an eager [npc2.moan], [npc2.name] desperately [npc2.verb(grind)] back against [npc.name],"
									+ " muffling [npc2.her] [npc2.moans] into [npc.her] mouth as [npc2.she] greedily [npc2.verb(thrust)] [npc2.her] [npc2.tongue] past [npc.her] [npc.lips+].",
							
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(press)] [npc2.her] [npc2.lips+] firmly against [npc.nameHers] as [npc2.she] happily [npc2.verb(push)] [npc2.her] [npc2.tongue] into [npc.her] mouth."));
						break;
				}
			
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {// Face-to-wall penetration descriptions: TODO
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to take hold of [npc2.namePos] chin, [npc.name] [npc.verb(tilt)] [npc2.her] head to one side as [npc.she] [npc.verb(lean)] forwards,"
									+ " pressing [npc.her] [npc.lips+] against [npc2.hers] as [npc.she] [npc.verb(pull)] [npc2.herHim] into a gentle kiss.",
							
							"[npc.Name] [npc.verb(lean)] forwards, pressing into [npc2.namePos] back and breathing in [npc2.her] [npc2.scent+]."
									+ " Reaching up, [npc.she] [npc.verb(turn)] [npc2.namePos] head to one side and gently [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] into [npc2.namePos] back, turning [npc2.her] head slightly to one side before gently pulling [npc2.herHim] into a soft kiss."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to roughly grab [npc2.namePos] chin, [npc.name] [npc.verb(yank)] [npc2.her] head to one side,"
									+ " before grinding [npc.her] [npc.lips+] against [npc2.hers] as [npc.she] [npc.verb(pull)] [npc2.herHim] into a forceful kiss.",

							"[npc.Name] [npc.verb(lean)] forwards, pressing into [npc2.namePos] back and breathing in [npc2.her] [npc2.scent+]."
									+ " Reaching up, [npc.she] roughly [npc.verb(yank)] [npc2.namePos] head to one side before forcefully [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.hers].",
											
							"[npc.Name] [npc.verb(lean)] into [npc2.namePos] back, yanking [npc2.her] head to one side before roughly forcing [npc.her] [npc.tongue+] down [npc2.her] throat."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to take hold of [npc2.her] chin, [npc.name] [npc.verb(lean)] forwards,"
									+ " eagerly pressing [npc.her] [npc.lips+] against [npc2.hers] as [npc.she] [npc.verb(pull)] [npc2.herHim] into a passionate kiss.",

							"[npc.Name] [npc.verb(lean)] forwards, pressing into [npc2.namePos] back and breathing in [npc2.her] [npc2.scent+]."
									+ " Reaching up, [npc.she] [npc.verb(turn)] [npc2.namePos] head to one side and passionately [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.hers].",
									
							"[npc.Name] [npc.verb(lean)] into [npc2.namePos] back, turning [npc2.her] head slightly to one side before eagerly pulling [npc2.herHim] into a passionate kiss."));
						break;
				}
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.tongue] into [npc.name] mouth,"
									+ " pressing [npc2.her] [npc2.lips+] firmly against [npc.hers] as [npc2.she] eagerly [npc2.verb(return)] [npc.her] display of affection.",
							
							" With [npc2.a_moan], [npc2.name] [npc2.verb(lean)] back against [npc.name],"
									+ " muffling [npc2.her] [npc2.moans] into [npc.her] mouth as [npc2.she] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.tongue] past [npc.her] [npc.lips+].",
							
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(lean)] back against [npc.name],"
									+ " pressing [npc2.her] [npc2.lips+] against [npc.hers] as [npc2.she] happily [npc2.verb(slide)] [npc2.her] [npc2.tongue] into [npc.her] mouth."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(try)] to pull away, [npc2.sobbing] and squirming in discomfort as [npc.name] [npc.verb(force)] [npc.herself] on [npc2.herHim].",
							
							" [npc2.NamePos] [npc2.sob+] is muffled into [npc.namePos] mouth as [npc2.she] [npc2.verb(try)] to pull away, squirming in discomfort as [npc.name] [npc.verb(force)] [npc.herself] on [npc2.herHim].",
							
							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from [npc.name],"
									+ " protesting and squirming in discomfort as [npc.name] [npc.verb(force)] [npc.her] [npc.tongue] past [npc2.her] reluctant [npc2.lips]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] happily [npc2.verb(push)] [npc2.her] [npc2.tongue] deep into [npc.namePos] mouth,"
									+ " eagerly pressing [npc2.her] [npc2.lips+] back against [npc.hers] and [npc2.moaning] in delight as [npc2.she] greedily [npc2.verb(return)] [npc.her] display of affection.",
							
							" With an eager [npc2.moan], [npc2.name] desperately [npc2.verb(grind)] back against [npc.name],"
									+ " muffling [npc2.her] [npc2.moans] into [npc.her] mouth as [npc2.she] greedily [npc2.verb(thrust)] [npc2.her] [npc2.tongue] past [npc.her] [npc.lips+].",
						
							" [npc2.Moaning] in delight, [npc2.name] desperately [npc2.verb(grind)] [npc2.herself] back against [npc.name],"
									+ " eagerly pressing [npc2.her] [npc2.lips+] firmly against [npc.hers] as [npc2.she] happily [npc2.verb(push)] [npc2.her] [npc2.tongue] into [npc.her] mouth."));
						break;
				}
				
			} else { // Default penetration descriptions:
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gazing into [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(lean)] forwards, pressing [npc.her] [npc.lips+] against [npc2.hers] as [npc.she] [npc.verb(pull)] [npc2.herHim] into a gentle kiss.",
							
							"[npc.Name] [npc.verb(lean)] into [npc2.name], breathing in [npc2.her] [npc2.scent+] as [npc.she] gently [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] in against [npc2.namePos] [npc2.breasts+], tilting [npc.her] head slightly to one side before gently pulling [npc2.herHim] into a loving kiss."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gazing into [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(lean)] forwards, pressing [npc.her] [npc.lips+] against [npc2.hers] as [npc.she] [npc.verb(pull)] [npc2.herHim] into a passionate kiss.",
							
							"[npc.Name] [npc.verb(lean)] into [npc2.name], breathing in [npc2.her] [npc2.scent+] as [npc.she] eagerly [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] in against [npc2.namePos] [npc2.breasts+], tilting [npc.her] head slightly to one side before eagerly pulling [npc2.herHim] into a passionate kiss."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gazing into [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(lean)] forwards, roughly pressing [npc.her] [npc.lips+] against [npc2.hers] as [npc.she] [npc.verb(pull)] [npc2.herHim] into a forceful kiss.",
							
							"[npc.Name] [npc.verb(lean)] into [npc2.name], breathing in [npc2.her] [npc2.scent+] as [npc.she] roughly [npc.verb(grind)] [npc.her] [npc.lips+] against [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] in against [npc2.namePos] [npc2.breasts+], tilting [npc.her] head to one side before violently pulling [npc2.herHim] into a rough kiss."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gazing into [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(lean)] forwards, eagerly pressing [npc.her] [npc.lips+] against [npc2.hers] and delivering a passionate kiss.",
							
							"[npc.Name] [npc.verb(lean)] into [npc2.name], breathing in [npc2.her] [npc2.scent+] as [npc.she] eagerly [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] in against [npc2.namePos] [npc2.breasts+], tilting [npc.her] head slightly to one side before planting a desperate, passionate kiss on [npc2.her] [npc2.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gazing into [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(lean)] forwards, before pressing [npc.her] [npc.lips+] against [npc2.hers] and delivering a passionate kiss.",
							
							"[npc.Name] [npc.verb(lean)] into [npc2.name], breathing in [npc2.her] [npc2.scent+] as [npc.she] happily [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] in against [npc2.namePos] [npc2.breasts+], tilting [npc.her] head slightly to one side before planting a wet kiss on [npc2.her] [npc2.lips+]."));
						break;
					default:
						break;
				}
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly [npc2.verb(push)] [npc2.her] [npc2.tongue] into [npc.namePos] mouth,"
									+ " reaching up to gently caress [npc.her] [npc.face] as [npc2.she] happily [npc2.verb(return)] [npc.her] display of affection.",
							
							" With an approving hum, [npc2.name] [npc2.verb(lean)] into [npc.name],"
									+ " muffling [npc.her] [npc.moans] with [npc2.her] [npc2.lips+] as [npc2.she] gently [npc2.verb(push)] [npc2.her] [npc2.tongue] into [npc.her] mouth.",
							
							" [npc2.Moaning] in approval, [npc2.name] [npc2.verb(lean)] into [npc.name],"
									+ " pressing [npc2.her] [npc2.lips+] gently against [npc.hers] as [npc2.she] slowly [npc2.verb(slide)] [npc2.her] [npc2.tongue] into [npc.her] mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.tongue] into [npc.namePos] mouth,"
									+ " greedily pressing [npc2.her] [npc2.lips+] back against [npc.hers] as [npc2.she] eagerly [npc2.verb(return)] [npc.her] display of affection.",
							
							" With an approving [npc2.moan], [npc2.name] [npc2.verb(lean)] into [npc.name],"
									+ " muffling [npc.her] [npc.moans] with [npc2.her] [npc2.lips+] as [npc2.she] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.tongue] into [npc.her] mouth.",
									
							" [npc2.Moaning] in approval, [npc2.name] [npc2.verb(lean)] into [npc.name],"
									+ " pressing [npc2.her] [npc2.lips+] firmly against [npc.hers] as [npc2.she] eagerly [npc2.verb(slide)] [npc2.her] [npc2.tongue] into [npc.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(force)] [npc2.her] [npc2.tongue] deep into [npc.namePos] mouth,"
									+ " roughly pressing [npc2.her] [npc2.lips+] against [npc.hers] as [npc2.she] greedily [npc2.verb(return)] [npc.her] display of affection.",
							
							" With an approving [npc2.moan], [npc2.name] forcefully [npc2.verb(grind)] up against [npc.herHim],"
									+ " muffling [npc.her] [npc.moans] with [npc2.her] [npc2.lips+] as [npc2.she] roughly [npc2.verb(thrust)] [npc2.her] [npc2.tongue] deep into [npc.her] mouth.",
							
							" [npc2.Moaning] in approval, [npc2.name] [npc2.verb(grind)] [npc2.herself] up against [npc.name],"
									+ " forcefully pressing [npc2.her] [npc2.lips+] against [npc.hers] as [npc2.she] roughly [npc2.verb(push)] [npc2.her] [npc2.tongue] deep into [npc.her] mouth."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] happily [npc2.verb(push)] [npc2.her] [npc2.tongue] deep into [npc.namePos] mouth,"
									+ " eagerly pressing [npc2.her] [npc2.lips+] against [npc.hers] and [npc2.moaning] in delight as [npc2.she] greedily [npc2.verb(return)] [npc.her] display of affection.",
							
							" With an eager [npc2.moan], [npc2.name] desperately [npc2.verb(grind)] up against [npc.name],"
									+ " muffling [npc2.her] [npc2.moans] into [npc.her] mouth as [npc2.she] greedily [npc2.verb(thrust)] [npc2.her] [npc2.tongue] past [npc.her] [npc.lips+].",
							
							" [npc2.Moaning] in delight, [npc2.name] desperately [npc2.verb(grind)] [npc2.herself] up against [npc.name],"
									+ " eagerly pressing [npc2.her] [npc2.lips+] firmly against [npc.hers] as [npc2.she] happily [npc2.verb(push)] [npc2.her] [npc2.tongue] into [npc.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.tongue] into [npc.namePos] mouth,"
									+ " pressing [npc2.her] [npc2.lips+] against [npc.hers] as [npc2.she] eagerly [npc2.verb(return)] [npc.her] display of affection.",
							
							" With [npc2.a_moan], [npc2.name] [npc2.verb(lean)] into [npc.name],"
									+ " muffling [npc2.her] [npc2.moans] into [npc.her] mouth as [npc2.she] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.tongue] past [npc.her] [npc.lips+].",
							
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(lean)] into [npc.name],"
									+ " pressing [npc2.her] [npc2.lips+] against [npc.hers] as [npc2.she] happily [npc2.verb(slide)] [npc2.her] [npc2.tongue] into [npc.her] mouth."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(try)] to pull away, [npc2.sobbing] and squirming in discomfort as [npc.name] [npc.verb(force)] [npc.herself] on [npc2.herHim].",
							
							" [npc2.NamePos] [npc2.sob+] is muffled into [npc.namePos] mouth as [npc2.she] [npc2.verb(try)] to pull away, squirming in discomfort as [npc.name] [npc.verb(force)] [npc.herself] on [npc2.herHim].",
							
							" With [npc2.a_sob+], [npc2.she] [npc2.verb(try)] in vain to pull away from [npc.name],"
									+ " protesting and squirming in discomfort as [npc.name] [npc.verb(force)] [npc.her] [npc.tongue] past [npc2.her] reluctant [npc2.lips]."));
						break;
					default:
						break;
				}
			
			}
			
			//TODO describe tongue modifiers
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
				return(UtilText.returnStringAtRandom(
					" [npc2.Name] [npc2.verb(let)] out an eager [npc2.moan] in response,"
							+ " enthusiastically pressing [npc2.herself] against [npc.name] and thrusting [npc2.her] [npc2.tongue] into [npc.her] mouth as [npc2.she] eagerly [npc2.verb(return)] [npc.her] display of affection.",
					
					" With a delighted [npc2.moan], [npc2.name] eagerly [npc2.verb(lean)] into [npc.name], pushing [npc2.her] [npc2.tongue] past [npc.her] [npc.lips] as [npc2.she] desperately pull [npc.herHim] into a frantic kiss.",
				
					" Letting out [npc2.a_moan] in delight, [npc2.name] press [npc2.herself] against [npc.name],"
							+ " enthusiastically returning [npc.her] kiss as [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.lips+] against [npc.her] mouth."));
			case SUB_RESISTING:
				return(UtilText.returnStringAtRandom(
					" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] in response, trying [npc2.her] hardest to [npc2.verb(push)] [npc.name] away as [npc2.she] [npc2.verb(writhe)] about in discomfort.",
					
					" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to [npc2.verb(push)] [npc.name] away, struggling in vain as [npc.she] [npc.verb(continue)] to plant gentle kisses on [npc2.her] unwilling [npc2.lips].",
					
					" [npc2.Name] desperately [npc2.verb(try)] to pull away, struggling and pushing back as [npc.name] [npc.verb(continue)] to molest [npc2.herHim],"
							+ " drawing a muffled [npc2.sob] from between [npc2.her] [npc2.lips] at the feel of each of [npc.her] unwanted kisses."));
			case DOM_GENTLE:
				return(UtilText.returnStringAtRandom(
					" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response, taking a moment to plant a series of gentle kisses on [npc.namePos] [npc.lips+], before gently sliding [npc2.her] [npc2.tongue] into [npc.her] mouth.",
					
					" With a soft [npc2.moan], [npc2.name] [npc2.verb(lean)] into [npc.name], gently pushing [npc2.her] [npc2.tongue] past [npc.her] [npc.lips+] as [npc2.she] [npc2.verb(return)] [npc.herHis] display of affection.",
					
					" Letting out a gentle [npc2.moan], [npc2.name] [npc2.verb(press)] [npc2.herself] against [npc.name],"
							+ " muffling [npc2.her] [npc2.moans] against [npc.her] [npc.lips+] as [npc2.she] slowly [npc2.verb(slide)] [npc2.her] [npc2.tongue] into [npc.her] mouth."));
			case DOM_ROUGH:
				return(UtilText.returnStringAtRandom(
					" [npc2.Name] [npc2.verb(let)] out a growl, roughly grinding [npc2.her] [npc2.lips] against [npc.her] mouth as [npc2.she] violently [npc2.verb(thrust)] [npc2.her] tongue deep down [npc.her] throat.",
					
					" With a menacing growl, [npc2.name] violently [npc2.verb(grind)] up against [npc.name],"
							+ " concerned solely with [npc2.her] own pleasure as [npc2.she] greedily [npc2.verb(thrust)] [npc2.her] [npc2.tongue] deep down [npc.her] throat.",
					
					" [npc2.Name] grin in response to [npc.namePos] display of affection, and with a violent growl, [npc2.she] forcefully [npc2.verb(grind)] [npc2.her] [npc2.lips] against [npc.hers],"
							+ " [npc2.moaning] into [npc.her] mouth as [npc2.she] greedily tongue-[npc2.verb(fuck)] [npc.her] throat."));
			default:
				return(UtilText.returnStringAtRandom(
					" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan] in response,"
							+ " pressing [npc2.herself] against [npc.name] and happily pushing [npc2.her] [npc2.tongue] into [npc.her] mouth as [npc2.she] returns [npc.herHis] display of affection.",
					
					" With [npc2.a_moan], [npc2.name] [npc2.verb(lean)] into [npc.name], pushing [npc2.her] [npc2.tongue] past [npc.her] [npc.lips] as [npc2.she] [npc2.verb(pull)] [npc.herHim] into an eager kiss.",
					
					" Letting out [npc2.a_moan], [npc2.name] [npc2.verb(lean)] into [npc.name], returning [npc.her] kiss as [npc2.she] press [npc2.her] [npc2.lips+] against [npc.her] mouth."));
		}
	}
	
	public static final SexAction KISS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle kiss";
		}

		@Override
		public String getActionDescription() {
			return "Gently kiss [npc2.name].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Still leaning into [npc2.namePos] back, [npc.name] gently [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.nameHers], before planting a series of soft kisses on [npc2.her] mouth.",
					
					"[npc.Name] gently [npc.verb(lean)] into [npc2.namePos] back, breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(plant)] a series of soft kisses on [npc2.her] [npc2.lips+].",
					
					"[npc.Name] slowly [npc.verb(press)] into [npc2.namePos] back, gently pinning [npc2.herHim] against the wall as [npc.she] [npc.verb(lean)] in over [npc2.her] shoulder to gently kiss [npc2.her] [npc2.lips+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pressing [npc.her] [npc.lips+] against [npc2.nameHers], [npc.name] [npc.verb(plant)] a series of soft kisses on [npc2.her] mouth.",
					
					"[npc.Name] gently [npc.verb(lean)] in against [npc2.name], breathing in [npc2.her] [npc2.scent+] as [npc.she] [npc.verb(plant)] a series of soft kisses on [npc2.her] [npc2.lips+].",
					
					"[npc.Name] gently [npc.verb(press)] against [npc2.namePos] [npc2.breasts+], before tilting [npc.her] head slightly to one side as [npc.she] softly [npc.verb(kiss)] [npc2.her] [npc2.lips+]."));
			
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Kiss [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Continue kissing [npc2.name].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Still leaning into [npc2.namePos] back, [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.nameHers], before planting a series of passionate kisses on [npc2.her] mouth.",
					
					"[npc.Name] eagerly [npc.verb(lean)] into [npc2.namePos] back, breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(plant)] a series of passionate kisses on [npc2.her] [npc2.lips+].",
					
					"[npc.Name] eagerly [npc.verb(press)] into [npc2.namePos] back, pinning [npc2.herHim] against the wall as [npc.she] [npc.verb(lean)] in over [npc2.her] shoulder to passionately kiss [npc2.her] [npc2.lips+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pressing [npc.her] [npc.lips+] against [npc2.nameHers], [npc.name] [npc.verb(plant)] a series of passionate kisses on [npc2.her] mouth.",
					
					"[npc.Name] eagerly [npc.verb(lean)] in against [npc2.name], breathing in [npc2.her] [npc2.scent+] as [npc.she] [npc.verb(plant)] a series of soft kisses on [npc2.her] [npc2.lips+].",
					
					"[npc.Name] eagerly [npc.verb(press)] against [npc2.namePos] [npc2.breasts+], before tilting [npc.her] head slightly to one side as [npc.she] passionately [npc.verb(kiss)] [npc2.her] [npc2.lips+]."));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough kiss";
		}

		@Override
		public String getActionDescription() {
			return "Roughly kiss [npc2.name].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently grinding into [npc2.namePos] back, [npc.name] forcefully [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.nameHers], before greedily thrusting [npc.her] [npc.tongue] deep down [npc2.her] throat.",
					
					"[npc.Name] roughly [npc.verb(press)] into [npc2.namePos] back, breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(plant)] a series of passionate kisses on [npc2.her] [npc2.lips+].",
					
					"[npc.Name] roughly [npc.verb(press)] into [npc2.namePos] back,"
							+ " forcefully pinning [npc2.herHim] against the wall as [npc.she] [npc.verb(lean)] in over [npc2.her] shoulder to thrust [npc.her] [npc.tongue+] deep down [npc2.her] throat."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Forcefully pressing [npc.her] [npc.lips+] against [npc2.nameHers], [npc.name] greedily [npc.verb(thrust)] [npc.her] [npc.tongue] deep down [npc2.her] throat.",
					
					"[npc.Name] roughly [npc.verb(grind)] against [npc2.name], breathing in [npc2.her] [npc2.scent+] as [npc.she] greedily tongue-[npc.verb(fuck)] [npc2.her] mouth.",
					
					"[npc.Name] roughly [npc.verb(press)] against [npc2.namePos] [npc2.breasts+], before thrusting [npc.her] [npc.tongue] deep down [npc2.her] throat."));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction KISS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Kiss [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Continue kissing [npc2.name].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Still leaning into [npc2.namePos] back, [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.nameHers], before planting a series of passionate kisses on [npc2.her] mouth.",
					
					"[npc.Name] [npc.verb(lean)] into [npc2.namePos] back, breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(plant)] a series of passionate kisses on [npc2.her] [npc2.lips+].",
					
					"[npc.Name] [npc.verb(press)] into [npc2.namePos] back, pinning [npc2.herHim] against the wall as [npc.she] [npc.verb(lean)] in over [npc2.her] shoulder to passionately kiss [npc2.her] [npc2.lips+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pressing [npc.her] [npc.lips+] against [npc2.nameHers], [npc.name] [npc.verb(plant)] a series of passionate kisses on [npc2.her] mouth.",
					
					"[npc.Name] [npc.verb(lean)] in against [npc2.name], breathing in [npc2.her] [npc2.scent+] as [npc.she] [npc.verb(plant)] a series of soft kisses on [npc2.her] [npc2.lips+].",
					
					"[npc.Name] [npc.verb(press)] against [npc2.namePos] [npc2.breasts+], before tilting [npc.her] head slightly to one side as [npc.she] passionately [npc.verb(kiss)] [npc2.her] [npc2.lips+]."));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager kiss";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly kiss [npc2.name].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Still leaning into [npc2.namePos] back, [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.nameHers], before planting a series of passionate kisses on [npc2.her] mouth.",
					
					"[npc.Name] eagerly [npc.verb(lean)] into [npc2.namePos] back, breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(plant)] a series of passionate kisses on [npc2.her] [npc2.lips+].",
					
					"[npc.Name] eagerly [npc.verb(press)] into [npc2.namePos] back, pinning [npc2.herHim] against the wall as [npc.she] [npc.verb(lean)] in over [npc2.her] shoulder to passionately kiss [npc2.her] [npc2.lips+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pressing [npc.her] [npc.lips+] against [npc2.nameHers], [npc.name] [npc.verb(plant)] a series of passionate kisses on [npc2.her] mouth.",
					
					"[npc.Name] eagerly [npc.verb(lean)] in against [npc2.name], breathing in [npc2.her] [npc2.scent+] as [npc.she] [npc.verb(plant)] a series of soft kisses on [npc2.her] [npc2.lips+].",
					
					"[npc.Name] eagerly [npc.verb(press)] against [npc2.namePos] [npc2.breasts+], before tilting [npc.her] head slightly to one side as [npc.she] passionately [npc.verb(kiss)] [npc2.her] [npc2.lips+]."));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist kiss";
		}

		@Override
		public String getActionDescription() {
			return "Resist [npc2.namePos] kiss.";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Feeling tears welling up in [npc.her] eyes, [npc.name] [npc.verb(let)] out [npc.a_sob+], trying to push back against [npc2.name] in response to [npc2.her] unwanted kisses.",
					
					"With [npc2.namePos] [npc2.scent+] overwhelming [npc.her] senses, [npc.name] [npc.verb(let)] out a muffled [npc.sob],"
							+ " before desperately trying to push [npc2.herHim] off of [npc.herHim] in a futile attempt to stop [npc2.her] continued assault on [npc.her] mouth.",
					
					"[npc.Name] desperately [npc.verb(try)] to push [npc2.name] away, [npc.sobbing] in distress as [npc2.she] [npc2.verb(continue)] kissing and grinding up against [npc.herHim]."));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] out a soothing [npc2.moan] in response, ignoring [npc.namePos] protests as [npc2.she] tries to calm [npc.herHim] down by continuing to gently kiss [npc.her] [npc.lips+].",
						
						" With a soft [npc2.moan], [npc2.name] [npc2.verb(lean)] into [npc.name],"
								+ " ignoring [npc.her] [npc.sobs] as [npc2.she] gently, but firmly, pushes [npc2.her] [npc2.tongue] past [npc.her] unwilling [npc.lips] and into [npc.her] mouth.",
						
						" Letting out a soothing [npc2.moan], [npc2.name] [npc2.verb(press)] [npc2.herself] against [npc.name],"
								+ " muffling [npc.her] [npc.sobs] against [npc2.her] [npc2.lips+] as [npc2.she] [npc2.verb(continue)] to kiss [npc.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] out an angry growl in response to [npc.her] protests,"
								+ " roughly grinding [npc2.her] [npc2.lips] against [npc.her] mouth before violently thrusting [npc2.her] tongue deep down [npc.her] throat.",
						
						" With a furious growl, [npc2.name] violently grinds [npc2.herself] up against [npc.name], ignoring [npc.her] [npc.sobs] as [npc2.she] [npc2.verb(thrust)] [npc2.her] [npc2.tongue] deep down [npc.her] throat.",
						
						" [npc2.Name] ignores [npc.her] protests, and with a rough growl, [npc2.she] [npc2.verb(continue)] violently tongue-fucking [npc.her] reluctant throat."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan] in response, ignoring [npc.namePos] protests as [npc2.she] [npc2.verb(continue)] to plant passionate kisses on [npc.her] [npc.lips+].",
						
						" With [npc2.a_moan+], [npc2.name] [npc2.verb(lean)] into [npc.name],"
								+ " ignoring [npc.her] [npc.sobs] as [npc2.she] firmly pushes [npc2.her] [npc2.tongue] past [npc.her] unwilling [npc.lips] and into [npc.her] mouth.",
						
						" Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(press)] [npc2.herself] against [npc.name], continuing to passionately kiss [npc.herHim]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop kissing";
		}

		@Override
		public String getActionDescription() {
			return "Pull away from [npc2.namePos] and stop kissing [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Pulling back, [npc.name] roughly pushes [npc2.name] away from [npc.herHim] as [npc.she] puts an end to [npc.her] kiss.",
						
						"[npc.Name] suddenly, and roughly, [npc.verb(push)] [npc2.name] away from [npc.herHim], bringing an end to [npc.her] kiss.",
						
						"[npc.Name] [npc.verb(pull)] back from [npc2.name], before roughly pushing [npc2.herHim] away from [npc.herHim] and breaking off [npc.her] kiss."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gazing into [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(grin)] as [npc.she] [npc.verb(pull)] back, putting an end to [npc.her] kiss.",
						
						"[npc.Name] suddenly [npc.verb(pull)] back, bringing an end to [npc.her] kiss.",
						
						"[npc.Name] [npc.verb(pull)] back from [npc2.name], taking [npc.her] [npc.lips+] away from [npc2.hers] as [npc.she] breaks off [npc.her] kiss."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(breathe)] a small sigh of relief, before quickly continuing to [npc2.sob] and struggle against [npc.name] as [npc2.she] [npc2.verb(try)] to break free from [npc.her] grasp.",
						
						" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to [npc2.verb(push)] [npc.name] even further away, protesting and squirming in discomfort as [npc.she] [npc.verb(hold)] [npc2.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] out a little whine as [npc.name] [npc.verb(pull)] back, signalling [npc2.her] desperate need for more attention.",
						
						" A desperate whine escapes from between [npc2.namePos] [npc2.lips], betraying [npc2.her] desire for more of [npc.namePos] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
