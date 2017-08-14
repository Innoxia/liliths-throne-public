package com.base.game.sex.sexActions.baseActionsPlayer;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * Designed for kissing when the player is dom.
 * 
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PlayerTongueMouth {
	
	public static SexAction PLAYER_KISS_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Start kissing";
		}

		@Override
		public String getActionDescription() {
			return "Press your [pc.lips] against [npc.name]'s mouth and start making out with [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.FACING_WALL_PARTNER) {// Face-to-wall penetration descriptions:
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to take hold of [npc.name]'s chin, you turn [npc.her] head to one side, pressing your [pc.lips+] against [npc.hers] as you pull [npc.herHim] back into a gentle kiss.",
								"You lean forwards, pressing into [npc.name]'s back and breathing in [npc.her] [npc.scent] as you turn [npc.her] head to one side and gently press your [pc.lips+] against [npc.hers].",
								"You lean into [npc.name]'s back, turning [npc.her] head to one side before gently pulling [npc.herHim] into a soft kiss."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to take hold of [npc.name]'s chin, you turn [npc.her] head to one side, eagerly pressing your [pc.lips+] against [npc.hers] as you pull [npc.herHim] back into a passionate kiss.",
								"You lean forwards, pressing into [npc.name]'s back and breathing in [npc.her] [npc.scent] as you turn [npc.her] head to one side and eagerly press your [pc.lips+] against [npc.hers].",
								"You lean into [npc.name]'s back, turning [npc.her] head to one side before eagerly pulling [npc.herHim] into a passionate kiss."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to roughly grab [npc.name]'s chin, you yank [npc.her] head to one side, forcing your [pc.lips+] against [npc.hers] as you pull [npc.herHim] back into a forceful kiss.",
								"You lean forwards, pressing into [npc.name]'s back and breathing in [npc.her] [npc.scent] as you yank [npc.her] head to one side and roughly press your [pc.lips+] against [npc.hers].",
								"You lean into [npc.name]'s back, yanking [npc.her] head to one side before roughly forcing [npc.herHim] into a passionate kiss."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] happily pushes [npc.her] [npc.tongue] deep into your mouth,"
										+ " eagerly pressing [npc.her] [npc.lips+] back against yours and [npc.moaning] in delight as [npc.she] greedily returns your display of affection.",
								" With an eager [npc.moan], [npc.she] desperately grinds back against you, muffling [npc.her] [npc.moans] into your mouth as [npc.she] desperately thrusts [npc.her] [npc.tongue] past your [pc.lips+].",
								" [npc.Moaning] in delight, [npc.she] desperately grinds [npc.herself] back against you,"
										+ " eagerly pressing [npc.her] [npc.lips+] firmly against yours as [npc.she] happily pushes [npc.her] [npc.tongue] into your mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] pushes [npc.her] [npc.tongue] into your mouth, pressing [npc.her] [npc.lips+] back against yours as [npc.she] eagerly returns your display of affection.",
								" With [npc.a_moan], [npc.she] leans back against you, muffling [npc.her] [npc.moans] into your mouth as [npc.she] eagerly thrusts [npc.her] [npc.tongue] past your [pc.lips+].",
								" [npc.Moaning] in delight, [npc.she] leans back against you, pressing [npc.her] [npc.lips+] against yours as [npc.she] happily slides [npc.her] [npc.tongue] into your mouth."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] tries to pull away, [npc.sobbing] and squirming in discomfort as you force yourself on [npc.herHim].",
								" [npc.Name]'s [npc.sob+] is muffled into your mouth as [npc.she] tries to pull away, squirming in discomfort as you force yourself on [npc.herHim].",
								" With [npc.a_sob+], [npc.name] tries, in vain, to pull away from you, protesting and squirming in discomfort as you force your [pc.tongue] past [npc.her] reluctant [npc.lips]."));
						break;
					default:
						break;
				}
				
			} else { // Default penetration descriptions:
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Looking into [npc.name]'s [npc.eyes], you lean forwards, pressing your [pc.lips+] against [npc.hers] as you pull [npc.herHim] into a gentle kiss.",
								"You lean forwards, breathing in [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you gently press your [pc.lips+] against [npc.hers].",
								"You lean in against [npc.name]'s [npc.breasts+], tilting your head slightly to one side before gently pulling [npc.herHim] into a loving kiss."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Looking into [npc.name]'s [npc.eyes], you lean forwards, pressing your [pc.lips+] against [npc.hers] as you pull [npc.herHim] into a passionate kiss.",
								"You lean forwards, breathing in [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you eagerly press your [pc.lips+] against [npc.hers].",
								"You lean in against [npc.name]'s [npc.breasts+], tilting your head slightly to one side as you pull [npc.herHim] into a passionate kiss."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Looking into [npc.name]'s [npc.eyes], you lean forwards, grinding your [pc.lips+] against [npc.hers] as you pull [npc.herHim] into a rough kiss.",
								"You lean forwards, breathing in [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you roughly grind your [pc.lips+] against [npc.hers].",
								"You lean in against [npc.name]'s [npc.breasts+], violently pulling [npc.herHim] into a rough kiss as you grind your body against [npc.hers]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Looking into [npc.name]'s [npc.eyes], you lean forwards, eagerly pressing your [pc.lips+] against [npc.hers] as you let out lewd little [pc.moaning] noises,"
										+ " encouraging [npc.herHim] to pull you into a passionate kiss.",
								"You lean forwards, taking in a deep breath of [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you eagerly press your [pc.lips+] against [npc.hers].",
								"You lean in against [npc.name]'s [npc.breasts+], tilting your head slightly to one side before planting a desperate, passionate kiss on [npc.her] [npc.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Looking into [npc.name]'s [npc.eyes], you lean forwards, pressing your [pc.lips+] against [npc.hers] as you let out a series of soft [pc.moans], encouraging [npc.herHim] to pull you into a passionate kiss.",
								"You lean forwards, breathing in [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you eagerly press your [pc.lips+] against [npc.hers].",
								"You lean in against [npc.name]'s [npc.breasts+], tilting your head slightly to one side before planting a passionate kiss on [npc.her] [npc.lips+]."));
						break;
					default:
						break;
				}
				
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] slowly pushes [npc.her] [npc.tongue] into your mouth, reaching up to gently caress your [pc.face] as [npc.she] happily returns your display of affection.",
								" With an approving hum, [npc.she] leans into you, muffling your [pc.moans] with [npc.her] [npc.lips+] as [npc.she] gently pushes [npc.her] [npc.tongue] into your mouth.",
								" [npc.Moaning] in approval, [npc.she] leans into you, pressing [npc.her] [npc.lips+] back gently against yours as [npc.she] slowly slides [npc.her] [npc.tongue] into your mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] pushes [npc.her] [npc.tongue] into your mouth, greedily pressing [npc.her] [npc.lips+] back against yours as [npc.she] eagerly returns your display of affection.",
								" With an approving [npc.moan], [npc.she] leans into you, muffling your [pc.moans] with [npc.her] [npc.lips+] as [npc.she] eagerly thrusts [npc.her] [npc.tongue] into your mouth.",
								" [npc.Moaning] in approval, [npc.she] leans into you, pressing [npc.her] [npc.lips+] firmly against yours as [npc.she] eagerly slides [npc.her] [npc.tongue] into your mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] forces [npc.her] [npc.tongue] deep into your mouth, roughly pressing [npc.her] [npc.lips+] back against yours as [npc.she] greedily returns your display of affection.",
								" With an approving [npc.moan], [npc.she] forcefully grinds up against you, muffling your [pc.moans] with [npc.her] [npc.lips+] as [npc.she] roughly thrusts [npc.her] [npc.tongue] deep into your mouth.",
								" [npc.Moaning] in approval, [npc.she] grinds [npc.herself] up against you, forcefully pressing [npc.her] [npc.lips+] firmly against yours as [npc.she] roughly pushes [npc.her] [npc.tongue] deep into your mouth."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] happily pushes [npc.her] [npc.tongue] deep into your mouth,"
										+ " eagerly pressing [npc.her] [npc.lips+] back against yours and [npc.moaning] in delight as [npc.she] greedily returns your display of affection.",
								" With an eager [npc.moan], [npc.she] desperately grinds up against you, muffling [npc.her] [npc.moans] into your mouth as [npc.she] desperately thrusts [npc.her] [npc.tongue] past your [pc.lips+].",
								" [npc.Moaning] in delight, [npc.she] desperately grinds [npc.herself] up against you,"
										+ " eagerly pressing [npc.her] [npc.lips+] firmly against yours as [npc.she] happily pushes [npc.her] [npc.tongue] into your mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] pushes [npc.her] [npc.tongue] into your mouth, pressing [npc.her] [npc.lips+] back against yours as [npc.she] eagerly returns your display of affection.",
								" With [npc.a_moan], [npc.she] leans into you, muffling [npc.her] [npc.moans] into your mouth as [npc.she] eagerly thrusts [npc.her] [npc.tongue] past your [pc.lips+].",
								" [npc.Moaning] in delight, [npc.she] leans into you, pressing [npc.her] [npc.lips+] against yours as [npc.she] happily slides [npc.her] [npc.tongue] into your mouth."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] tries to pull away, [npc.sobbing] and squirming in discomfort as you force yourself on [npc.herHim].",
								" [npc.Name]'s [npc.sob+] is muffled into your mouth as [npc.she] tries to pull away, squirming in discomfort as you force yourself on [npc.herHim].",
								" With [npc.a_sob+], [npc.name] tries, in vain, to pull away from you, protesting and squirming in discomfort as you force your [pc.tongue] past [npc.her] reluctant [npc.lips]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static SexAction PLAYER_KISS_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public String getActionTitle() {
			return "Gentle kiss";
		}

		@Override
		public String getActionDescription() {
			return "Gently kiss [npc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.FACING_WALL_PARTNER) {// Face-to-wall penetration descriptions:
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Still leaning into [npc.name]'s back, you gently press your [pc.lips+] against [npc.hers], before planting a series of soft kisses on [npc.her] mouth.",
						"You gently lean into [npc.name]'s back, breathing in [npc.her] [npc.scent] as you plant a series of soft kisses on [npc.her] [npc.lips+].",
						"You slowly grind into [npc.name]'s back, softly pressing [npc.herHim] against the wall as you lean in over [npc.her] shoulder to gently kiss [npc.her] [npc.lips+]."));
				
			} else { // Default penetration descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently pressing your [pc.lips+] against [npc.name]'s, you plant a series of soft kisses on [npc.her] mouth.",
						"You gently lean in against [npc.name], breathing in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you plant a series of soft kisses on [npc.her] [npc.lips+].",
						"You gently press up against [npc.name]'s [npc.breasts+], tilting your head slightly to one side as you softly kiss [npc.her] [npc.lips+]."));
				
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out an eager [npc.moan] in response,"
									+ " enthusiastically pressing [npc.herself] against you and thrusting [npc.her] [npc.tongue] into your mouth as [npc.she] eagerly returns your display of affection.",
							" With a delighted [npc.moan], [npc.she] eagerly leans into you, pushing [npc.her] [npc.tongue] past your [pc.lips] as [npc.she] desperately pulls you into a frantic kiss.",
							" Letting out [npc.a_moan] in delight, [npc.name] presses [npc.herself] against you, enthusiastically returning your kiss as [npc.she] grinds [npc.her] [npc.lips+] against your mouth."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+] in response, trying [npc.herHis] hardest to push you away as [npc.she] writhes about in discomfort.",
							" With [npc.a_sob+], [npc.she] tries to push you away, struggling in vain as you continue to plant gentle kisses on [npc.her] unwilling [npc.lips].",
							" [npc.She] desperately tries to pull away, struggling and pushing back as you continue to molest [npc.herHim], drawing a muffled [npc.sob] from between [npc.her] [npc.lips] with each of your unwanted kisses."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan] in response, pressing [npc.herself] against you and happily pushing [npc.her] [npc.tongue] into your mouth as [npc.she] returns your display of affection.",
							" With [npc.a_moan], [npc.she] leans into you, pushing [npc.her] [npc.tongue] past your [pc.lips] as [npc.she] pulls you into an eager kiss.",
							" Letting out [npc.a_moan], [npc.name] leans into you, returning your kiss as [npc.she] presses [npc.her] [npc.lips+] against your mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static SexAction PLAYER_KISS_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Kiss [npc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Continue kissing [npc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.FACING_WALL_PARTNER) {// Face-to-wall penetration descriptions:
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Still leaning into [npc.name]'s back, you eagerly press your [pc.lips+] against [npc.hers], before planting a series of desperate kisses on [npc.her] mouth.",
						"You eagerly lean into [npc.name]'s back, breathing in [npc.her] [npc.scent] as you plant a series of enthusiastic kisses on [npc.her] [npc.lips+].",
						"You enthusiastically press into [npc.name]'s back, pinning [npc.herHim] against the wall as you lean in over [npc.her] shoulder to eagerly kiss [npc.her] [npc.lips+]."));
				
			} else { // Default penetration descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [pc.a_moan+], you plant a series of passionate kisses on [npc.name]'s mouth, before sliding your [pc.tongue] past [npc.her] [npc.lips+].",
						"You lean in against [npc.name], breathing in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you slide your [pc.tongue] past [npc.her] [npc.lips+],"
								+ " pulling [npc.herHim] into a passionate kiss.",
						"You press up against [npc.name]'s [npc.breasts+], tilting your head slightly to one side before pushing your [pc.tongue] past [npc.her] [npc.lips+]."));
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out an eager [npc.moan] in response, enthusiastically pressing [npc.herself] against you and thrusting [npc.her] [npc.tongue] into your mouth as [npc.she] eagerly returns your display of affection.",
							" With a delighted [npc.moan], [npc.she] eagerly leans into you, pushing [npc.her] [npc.tongue] past your [pc.lips] as [npc.she] desperately pulls you into a frantic kiss.",
							" Letting out [npc.a_moan] in delight, [npc.name] presses [npc.herself] against you, enthusiastically returning your kiss as [npc.she] grinds [npc.her] [npc.lips+] against your mouth."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+] in response, trying [npc.herHis] hardest to push you away as [npc.she] writhes about in discomfort.",
							" With [npc.a_sob+], [npc.she] tries to push you away, struggling in vain as you continue to slide your [pc.tongue] past [npc.her] unwilling [npc.lips].",
							" [npc.She] frantically tries to pull away, [npc.her] [npc.sobs+] being muffled into your mouth as [npc.she] struggles and pushes back against your unwanted advances."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan] in response, pressing [npc.herself] against you and happily pushing [npc.her] [npc.tongue] into your mouth as [npc.she] returns your display of affection.",
							" With [npc.a_moan], [npc.she] leans into you, pushing [npc.her] [npc.tongue] past your [pc.lips] as [npc.she] pulls you into an eager kiss.",
							" Letting out [npc.a_moan], [npc.name] leans into you, returning your kiss as [npc.she] presses [npc.her] [npc.lips+] against your mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static SexAction PLAYER_KISS_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public String getActionTitle() {
			return "Rough snog";
		}

		@Override
		public String getActionDescription() {
			return "Roughly snog [npc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.FACING_WALL_PARTNER) {// Face-to-wall penetration descriptions:
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Roughly grinding into [npc.name]'s back, you violently press your [pc.lips+] against [npc.her] mouth before greedily thrusting your [pc.tongue] down [npc.her] throat.",
						"You grind into [npc.name]'s back,"
								+ " taking in a deep breath of [npc.her] [npc.scent] as you force your [pc.tongue] past [npc.her] [npc.lips+], violently pinning [npc.herHim] up against the wall as you greedily tongue-fuck [npc.her] mouth.",
						"You roughly grind into [npc.name]'s back, pinning [npc.herHim] against the wall as you lean in over [npc.her] shoulder to forcefully grind your [pc.lips+] against [npc.her] mouth,"
								+ " before thrusting your [pc.tongue] deep down [npc.her] throat."));
				
			} else { // Default penetration descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [pc.a_moan+], you roughly grind yourself up against [npc.name], violently pressing your [pc.lips+] against [npc.her] mouth before greedily thrusting your [pc.tongue] down [npc.her] throat.",
						"You grind yourself against [npc.name], taking in a deep breath of [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")
							+" as you force your [pc.tongue] past [npc.her] [npc.lips+], violently pulling [npc.herHim] up against you as you greedily tongue-fuck [npc.her] mouth.",
						"You press up against [npc.name]'s [npc.breasts+], leaning in to roughly grind your [pc.lips+] against [npc.her] mouth, before thrusting your [pc.tongue] deep down [npc.her] throat."));
			
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out an eager [npc.moan] in response, enthusiastically pressing [npc.herself] against you as [npc.she] willingly submits to your rough treatment.",
							" With a delighted [npc.moan], [npc.she] eagerly leans into you, grinding back against your body as [npc.she] presses [npc.her] [npc.lips] against yours, all too eager to submit to your dominant treatment.",
							" Letting out [npc.a_moan] in delight, [npc.name] presses [npc.herself] against you, enthusiastically returning your rough kiss as [npc.she] grinds [npc.her] [npc.lips+] against your mouth."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a muffled [npc.sob] into your mouth, choking back tears as you violently force your [pc.tongue] into [npc.her] unwilling mouth.",
							" With [npc.a_sob+], [npc.she] attempts to push you away, but [npc.her] struggles prove to be in vain as you roughly continue to thrust your [pc.tongue] past [npc.her] unwilling [npc.lips].",
							" [npc.She] frantically tries to pull away, [npc.her] [npc.sobs+] being muffled into your mouth as [npc.she] struggles and pushes back against the violent thrusts of your [pc.tongue]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan] in response, surrendering [npc.herself] to your rough treatment as you continue mercilessly tongue-fucking [npc.her] throat.",
							" With [npc.a_moan], [npc.she] leans into you, grinding back against your body as [npc.she] surrenders [npc.her] [npc.lips] to your dominant treatment.",
							" Letting out [npc.a_moan], [npc.name] presses [npc.herself] against you, surrendering [npc.herself] to your rough kiss as [npc.she] presses [npc.her] [npc.lips+] back against your mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static SexAction PLAYER_KISS_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Kiss [npc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Continue kissing [npc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With [pc.a_moan+], you lean in against [npc.name]'s [npc.breasts+], before planting a series of passionate kisses on [npc.her] [npc.lips+].",
					"You lean in against [npc.name], breathing in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")
						+" as you press your [pc.lips+] against [npc.hers], encouraging [npc.herHim] to pull you into a passionate kiss.",
					"You press up against [npc.name]'s [npc.breasts+], tilting your head slightly to one side before pressing your [pc.lips+] against [npc.hers]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] in response, taking a moment to plant a series of gentle kisses on your [pc.lips+], before gently sliding [npc.her] tongue into your mouth.",
							" With a soft [npc.moan], [npc.she] leans into you, gently pushing [npc.her] [npc.tongue] past your [pc.lips+] as [npc.she] returns your display of affection.",
							" Letting out a gentle [npc.moan], [npc.she] presses [npc.herself] against you, muffling your [pc.moans] against [npc.her] [npc.lips+] as [npc.she] slowly slides [npc.her] [npc.tongue] into your mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a growl, roughly grinding [npc.her] [npc.lips] against your mouth before violently thrusting [npc.her] tongue deep down your throat.",
							" With a menacing growl, [npc.she] violently grinds [npc.herself] up against you, concerned solely with [npc.her] own pleasure as [npc.she] greedily thrusts [npc.her] [npc.tongue] deep down your throat.",
							" [npc.She] grins in response to your display of affection, and with a violent growl, [npc.she] forcefully grinds [npc.her] [npc.lips] against yours,"
									+ " [npc.moaning] into your mouth as [npc.she] greedily tongue-fucks your throat."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan] in response, taking a moment to plant a passionate kiss on your [pc.lips+], before eagerly sliding [npc.her] tongue into your mouth.",
							" With [npc.a_moan], [npc.she] leans into you, pushing [npc.her] [npc.tongue] past your [pc.lips+] as [npc.she] happily returns your display of affection.",
							" Letting out [npc.a_moan], [npc.she] presses [npc.herself] against you, muffling your [pc.moans] against [npc.her] [npc.lips+] as [npc.she] eagerly slides [npc.her] [npc.tongue] into your mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static SexAction PLAYER_KISS_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public String getActionTitle() {
			return "Eager kiss";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly kiss [npc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With [pc.a_moan+], you press yourself against [npc.name]'s [npc.breasts+], before planting a series of frantic, passionate kisses on [npc.her] [npc.lips+].",
					"You eagerly lean in against [npc.name], taking a deep breath of [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")
						+" as you press your [pc.lips+] against [npc.hers], encouraging [npc.herHim] to pull you into a passionate kiss.",
					"You eagerly lean in against [npc.name]'s [npc.breasts+], tilting your head slightly to one side before frantically pressing your [pc.lips+] against [npc.hers]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] in response, taking a moment to plant a series of gentle kisses on your [pc.lips+], before gently sliding [npc.her] tongue into your mouth.",
							" With a soft [npc.moan], [npc.she] leans into you, gently pushing [npc.her] [npc.tongue] past your [pc.lips+] as [npc.she] returns your display of affection.",
							" Letting out a gentle [npc.moan], [npc.she] presses [npc.herself] against you, muffling your [pc.moans] against [npc.her] [npc.lips+] as [npc.she] slowly slides [npc.her] [npc.tongue] into your mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a growl, roughly grinding [npc.her] [npc.lips] against your mouth before violently thrusting [npc.her] tongue deep down your throat.",
							" With a menacing growl, [npc.she] violently grinds [npc.herself] up against you, concerned solely with [npc.her] own pleasure as [npc.she] greedily thrusts [npc.her] [npc.tongue] deep down your throat.",
							" [npc.She] grins in response to your display of affection, and with a violent growl, [npc.she] forcefully grinds [npc.her] [npc.lips] against yours,"
									+ " [npc.moaning] into your mouth as [npc.she] greedily tongue-fucks your throat."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan] in response, taking a moment to plant a passionate kiss on your [pc.lips+], before eagerly sliding [npc.her] tongue into your mouth.",
							" With [npc.a_moan], [npc.she] leans into you, pushing [npc.her] [npc.tongue] past your [pc.lips+] as [npc.she] happily returns your display of affection.",
							" Letting out [npc.a_moan], [npc.she] presses [npc.herself] against you, muffling your [pc.moans] against [npc.her] [npc.lips+] as [npc.she] eagerly slides [npc.her] [npc.tongue] into your mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static SexAction PLAYER_KISS_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Stop kissing";
		}

		@Override
		public String getActionDescription() {
			return "Pull away from [npc.name]'s mouth and stop making out with [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling back, you roughly push [npc.name] away from you as you put an end to your kiss.",
							"Taking in one last breath of [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+", you push [npc.her] away from you, bringing an end to your kiss.",
							"You pull back from [npc.name]'s [npc.breasts+], pushing [npc.her] away from you as you break off your kiss."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Looking into [npc.name]'s [npc.eyes], you pull back, taking your [pc.lips+] away from [npc.hers] as you put an end to your kiss.",
							"Taking in one last breath of [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+", you pull back, bringing an end to your kiss.",
							"You pull back from [npc.name]'s [npc.breasts+], taking your [pc.lips+] away from [npc.hers] as you break off your kiss."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] breathes a little sigh of relief, before continuing to [npc.sob] and struggle against you as [npc.she] realises that you aren't finished just yet.",
							" With [npc.a_sob+], [npc.name] tries, in vain, to push you even further away from [npc.herHim], protesting and squirming in discomfort as you hold [npc.herHim] in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a little whine as you pull back, clearly desperate for more attention.",
							" A desperate little whine escapes from between [npc.her] [npc.lips], betraying [npc.her] desire for more of your attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner reactions:
	
	
	public static SexAction PARTNER_KISS_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.MOUTH_PLAYER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle kiss";
		}

		@Override
		public String getActionDescription() {
			return "Gently kiss [pc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pressing [npc.her] [npc.lips+] against yours, [npc.name] plants a series of soft kisses on your mouth.",
					"[npc.Name] gently leans in against you, and you find yourself breathing in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as [npc.she] plants a series of soft kisses on your [pc.lips+].",
					"[npc.Name] gently presses up against your [pc.breasts+], tilting [npc.her] head slightly to one side as [npc.she] softly kisses your [pc.lips+]."));
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out an eager [pc.moan] in response, enthusiastically pressing yourself against [npc.herHim] and thrusting your [pc.tongue] into [npc.her] mouth as you eagerly return [npc.her] display of affection.",
							" With a delighted [pc.moan], you eagerly lean into [npc.herHim], pushing your [pc.tongue] past [npc.her] [npc.lips] as you desperately pull [npc.herHim] into a frantic kiss.",
							" Letting out [pc.a_moan] in delight, you press yourself against [npc.herHim], enthusiastically returning [npc.her] kiss as you grind your [pc.lips+] against [npc.her] mouth."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] in response, trying your hardest to push [npc.herHim] away as you writhe about in discomfort.",
							" With [pc.a_sob+], you try to push [npc.herHim] away, struggling in vain as [npc.she] continues to plant gentle kisses on your unwilling [pc.lips].",
							" You desperately try to pull away, struggling and pushing back as [npc.name] continues to molest you, drawing a muffled [pc.sob] from between your [pc.lips] at the feel of each of [npc.her] unwanted kisses."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan] in response, pressing yourself against [npc.herHim] and happily pushing your [pc.tongue] into [npc.her] mouth as you returns [npc.herHis] display of affection.",
							" With [pc.a_moan], you lean into [npc.herHim], pushing your [pc.tongue] past [npc.her] [npc.lips] as you pull [npc.herHim] into an eager kiss.",
							" Letting out [pc.a_moan], you lean into [npc.herHim], returning [npc.her] kiss as you press your [pc.lips+] against [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static SexAction PARTNER_KISS_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.MOUTH_PLAYER,
			null,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Kiss [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Continue kissing [pc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With [npc.a_moan+], [npc.name] plants a series of passionate kisses on your mouth, before sliding [npc.her] [npc.tongue] past your [pc.lips+].",
					"[npc.Name] leans in against you, and you find yourself breathing in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")
						+" as [npc.she] slides [npc.her] [npc.tongue] past your [pc.lips+], pulling you into a passionate kiss.",
					"[npc.Name] presses up against your [pc.breasts+], tilting [npc.her] head slightly to one side before pushing [npc.her] [npc.tongue] past your [pc.lips+]."));
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out an eager [pc.moan] in response, enthusiastically pressing yourself against [npc.herHim] and thrusting your [pc.tongue] into [npc.her] mouth as you eagerly return [npc.her] display of affection.",
							" With a delighted [pc.moan], you eagerly lean into [npc.herHim], pushing your [pc.tongue] past [npc.her] [npc.lips] as you desperately pull [npc.herHim] into a frantic kiss.",
							" Letting out [pc.a_moan] in delight, you press yourself against [npc.herHim], enthusiastically returning [npc.her] kiss as you grind your [pc.lips+] against [npc.her] mouth."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] in response, trying your hardest to push [npc.herHim] away, and writhing about in discomfort as you feel [npc.her] [npc.tongue] sliding into your mouth.",
							" With [pc.a_sob+], you try to push [npc.herHim] away, struggling in vain as [npc.she] continues to slide [npc.her] [npc.tongue] past your unwilling [pc.lips].",
							" You frantically try to pull away, your [pc.sobs+] being muffled into [npc.her] mouth as you struggle and push back against [npc.her] unwanted advances."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan] in response, pressing yourself against [npc.herHim] and happily pushing your [pc.tongue] into [npc.her] mouth as you returns [npc.herHis] display of affection.",
							" With [pc.a_moan], you lean into [npc.herHim], pushing your [pc.tongue] past [npc.her] [npc.lips] as you pull [npc.herHim] into an eager kiss.",
							" Letting out [pc.a_moan], you lean into [npc.herHim], returning [npc.her] kiss as you press your [pc.lips+] against [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static SexAction PARTNER_KISS_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.MOUTH_PLAYER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough snog";
		}

		@Override
		public String getActionDescription() {
			return "Roughly snog [pc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With [npc.a_moan+], [npc.name] roughly grinds [npc.herself] up against you, violently pressing [npc.her] [npc.lips+] against your mouth before greedily thrusting [npc.her] [npc.tongue] deep down your throat.",
					"[npc.Name] grinds [npc.herself] against you, and you find yourself taking in a deep breath of [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")
						+" as [npc.she] forces [npc.her] [npc.tongue] past your [pc.lips+], roughly pulling you up against [npc.herHim] as [npc.she] greedily tongue-fucks your mouth.",
					"[npc.Name] presses up against your [pc.breasts+], leaning in to roughly grind [npc.her] [npc.lips+] against your mouth, before thrusting [npc.her] [npc.tongue] deep down your throat."));
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out an eager [pc.moan] in response, enthusiastically pressing yourself against [npc.herHim] as you willingly submit to [npc.her] rough treatment.",
							" With a delighted [pc.moan], you desperately lean into [npc.herHim], grinding back against [npc.her] body as you press your [pc.lips] against [npc.hers], all too eager to submit to [npc.her] dominant treatment.",
							" Letting out [npc.a_moan] in delight, [npc.name] presses [npc.herself] against you, enthusiastically returning your rough kiss as [npc.she] grinds [npc.her] [npc.lips+] against your mouth."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a muffled [npc.sob] into [npc.her] mouth, choking back tears as [npc.she] violently forces [npc.her] [npc.tongue] into your unwilling mouth.",
							" With [pc.a_sob+], you attempt to push [npc.herHim] away, but your struggles prove to be in vain as [npc.she] firmly holds you in place, continuing to ruthlessly thrust [npc.her] [npc.tongue] past your unwilling [pc.lips].",
							" You frantically try to pull away, your [pc.sobs+] being muffled into [npc.name]'s mouth as you struggle and push back against the violent thrusts of [npc.her] [npc.tongue]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan] in response, surrendering yourself to [npc.her] rough treatment as [npc.she] continues mercilessly tongue-fucking your throat.",
							" With [pc.a_moan], you lean into [npc.herHim], grinding back against [npc.her] body as you surrender your [pc.lips] to [npc.her] dominant treatment.",
							" Letting out [pc.a_moan], you press yourself against [npc.herHim], surrendering yourself to [npc.her] rough kisses as you obediently press your [pc.lips+] back against [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static SexAction PARTNER_KISS_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist kiss";
		}

		@Override
		public String getActionDescription() {
			return "Resist [npc.name]'s kiss.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Tears start welling up in [npc.name]'s eyes, and with [npc.a_sob+], [npc.she] tries to push back against you in response to your unwanted kisses.",
					"[npc.Name] lets out a muffled [npc.sob], desperately trying to push you off of [npc.herHim] as you continue assaulting [npc.her] mouth with your [pc.lips] and [pc.tongue].",
					"[npc.Name] desperately tries to push you away, [npc.sobbing] in distress as you continue kissing and grinding up against [npc.herHim]."));
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soothing [pc.moan] in response, ignoring [npc.her] protests as you try to calm [npc.herHim] down by continuing to gently kiss [npc.her] sealed [npc.lips].",
							" With a soft [pc.moan], you lean into [npc.herHim], ignoring [npc.her] [npc.sobs] as you gently, but firmly, push your [pc.tongue] past [npc.her] unwilling [npc.lips] and into [npc.her] mouth.",
							" Letting out a soothing [pc.moan], you press yourself against [npc.herHim], muffling [npc.her] [npc.sobs] against your [pc.lips+] as you continues to kiss [npc.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out an angry growl in response to [npc.her] protests, roughly grinding your [pc.lips] against [npc.her] mouth before violently thrusting your [pc.tongue] deep down [npc.her] throat.",
							" With a furious growl, you violently grind yourself up against [npc.herHim], ignoring [npc.her] [npc.sobs] as you thrust your [pc.tongue] deep down [npc.her] throat.",
							" You ignore [npc.her] protests, and with a violent growl, you forcefully grind your [pc.lips] against [npc.hers], [pc.moaning] into [npc.her] mouth as you ruthlessly tongue-fuck [npc.her] reluctant throat."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan] in response, ignoring [npc.her] protests as you continue to plant passionate kisses on [npc.her] [npc.lips+].",
							" With [pc.a_moan+], you lean into [npc.herHim], ignoring [npc.her] [npc.sobs] as you firmly push your [pc.tongue] past [npc.her] unwilling [npc.lips] and into [npc.her] mouth.",
							" Letting out [pc.a_moan+], you press yourself against [npc.herHim], muffling [npc.her] [npc.sobs] against your [pc.lips+] as you continue passionately kissing [npc.herHim]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static SexAction PARTNER_KISS_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Kiss [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Continue kissing [pc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.FACING_WALL_PARTNER) {// Face-to-wall penetration descriptions:

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [npc.a_moan+], [npc.name] leans back against your [pc.breasts+], before planting a series of passionate kisses on your [pc.lips+].",
						"[npc.Name] leans back against you, and you catch a breath of [npc.her] [npc.scent] as [npc.she] presses [npc.her] [npc.lips+] against yours, encouraging you to pull [npc.herHim] into a passionate kiss.",
						"[npc.Name] grinds back against your [pc.breasts+], tilting [npc.her] head slightly to one side before passionately pressing [npc.her] [npc.lips+] against yours."));
				
			} else { // Default penetration descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [npc.a_moan+], [npc.name] leans in against your [pc.breasts+], before planting a series of passionate kisses on your [pc.lips+].",
						"[npc.Name] leans in against you, and you catch a breath of [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")
							+" as [npc.she] presses [npc.her] [npc.lips+] against yours, encouraging you to pull [npc.herHim] into a passionate kiss.",
						"[npc.Name] grinds up against your [pc.breasts+], tilting [npc.her] head slightly to one side before passionately pressing [npc.her] [npc.lips+] against yours."));
			
			}
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] in response, taking a moment to plant a series of gentle kisses on [npc.her] [npc.lips+], before gently sliding your [pc.tongue] into [npc.her] mouth.",
							" With a soft [pc.moan], you lean into [npc.herHim], gently pushing your [pc.tongue] past [npc.her] [npc.lips+] as you return [npc.herHis] display of affection.",
							" Letting out a gentle [pc.moan], you press yourself against [npc.herHim], muffling [npc.her] [npc.moans] against your [pc.lips+] as you slowly slide your [pc.tongue] into [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a growl, roughly grinding your [pc.lips] against [npc.her] mouth before violently thrusting your tongue deep down [npc.her] throat.",
							" With a menacing growl, you violently grind yourself up against [npc.herHim], concerned solely with your own pleasure as you greedily thrust your [pc.tongue] deep down [npc.her] throat.",
							" You grin in response to [npc.her] display of affection, and with a violent growl, you forcefully grind your [pc.lips] against [npc.hers],"
									+ " [pc.moaning] into [npc.her] mouth as you greedily tongue-fuck [npc.her] throat."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan] in response, taking a moment to plant a passionate kiss on [npc.her] [npc.lips+], before eagerly sliding your [pc.tongue] into [npc.her] mouth.",
							" With [pc.a_moan], you lean into [npc.herHim], pushing your [pc.tongue] past [npc.her] [npc.lips+] as you happily return [npc.her] display of affection.",
							" Letting out [pc.a_moan], you press yourself against [npc.herHim], muffling [npc.her] [npc.moans] against your [pc.lips+] as you eagerly slide your [pc.tongue] into [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static SexAction PARTNER_KISS_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager kiss";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly kiss [pc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.FACING_WALL_PARTNER) {// Face-to-wall penetration descriptions:

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [npc.a_moan+], [npc.name] presses [npc.herself] back against your [pc.breasts+], before planting a series of frantic, passionate kisses on your [pc.lips+].",
						"[npc.Name] eagerly leans back against you, and you find yourself breathing in [npc.her] [npc.scent] as [npc.she] presses [npc.her] [npc.lips+] against yours, encouraging you to pull [npc.herHim] into a passionate kiss.",
						"[npc.Name] eagerly leans back against your [pc.breasts+], tilting [npc.her] head slightly to one side before frantically pressing [npc.her] [npc.lips+] against yours."));
				
			} else { // Default penetration descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [npc.a_moan+], [npc.name] presses [npc.herself] against your [pc.breasts+], before planting a series of frantic, passionate kisses on your [pc.lips+].",
						"[npc.Name] eagerly leans in against you, and you find yourself breathing in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")
							+" as [npc.she] presses [npc.her] [npc.lips+] against yours, encouraging you to pull [npc.herHim] into a passionate kiss.",
						"[npc.Name] eagerly leans in against your [pc.breasts+], tilting [npc.her] head slightly to one side before frantically pressing [npc.her] [npc.lips+] against yours."));
			
			}
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] in response, taking a moment to plant a series of gentle kisses on [npc.her] [npc.lips+], before gently sliding your [pc.tongue] into [npc.her] mouth.",
							" With a soft [pc.moan], you lean into [npc.herHim], gently pushing your [pc.tongue] past [npc.her] [npc.lips+] as you return [npc.her] display of affection.",
							" Letting out a gentle [pc.moan], you press yourself against [npc.herHim], muffling [npc.her] [npc.moans] against your [pc.lips+] as you slowly slide your [pc.tongue] into [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a growl, roughly grinding your [pc.lips] against [npc.her] mouth before violently thrusting your tongue deep down [npc.her] throat.",
							" With a menacing growl, you violently grind yourself up against [npc.herHim], concerned solely with your own pleasure as you greedily thrust your [pc.tongue] deep down [npc.her] throat.",
							" You grin in response to [npc.her] display of affection, and with a violent growl, you forcefully grind your [pc.lips] against [npc.hers],"
									+ " [pc.moaning] into [npc.her] mouth as you greedily tongue-fuck [npc.her] throat."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan] in response, taking a moment to plant a passionate kiss on [npc.her] [npc.lips+], before eagerly sliding your [pc.tongue] into [npc.her] mouth.",
							" With [pc.a_moan], you lean into [npc.herHim], pushing your [pc.tongue] past [npc.her] [npc.lips+] as you happily return [npc.her] display of affection.",
							" Letting out [pc.a_moan], you press yourself against [npc.herHim], muffling [npc.her] [npc.moans] against your [pc.lips+] as you eagerly slide your [pc.tongue] into [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static SexAction PARTNER_KISS_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.MOUTH_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop kissing";
		}

		@Override
		public String getActionDescription() {
			return "Pull away from [pc.name]'s mouth and stop making out with [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling back, [npc.name] roughly pushes you away from [npc.herHim] as [npc.she] puts an end to [npc.her] kiss.",
							"As you take in a deep breath of [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+", [npc.she] suddenly, and roughly, pushes you away from [npc.herHim], bringing an end to [npc.her] kiss.",
							"[npc.Name] pulls back from your [pc.breasts+], roughly pushing you away from [npc.herHim] as [npc.she] breaks off [npc.her] kiss."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Looking into your [pc.eyes], [npc.name] pulls back, taking [npc.her] [npc.lips+] away from yours as [npc.she] puts an end to [npc.her] kiss.",
							"As you take in a deep breath of [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+", [npc.she] suddenly pulls back, bringing an end to [npc.her] kiss.",
							"[npc.Name] pulls back from your [pc.breasts+], taking [npc.her] [npc.lips+] away from yours as [npc.she] breaks off [npc.her] kiss."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You breathe out a little sigh of relief, before continuing to [pc.sob] and struggle against [npc.name] as you try to break free from [npc.her] grasp.",
							" With [pc.a_sob+], you try, in vain, to push [npc.name] even away from you, protesting and squirming in discomfort as [npc.she] holds you firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a little whine as [npc.she] pulls back, signalling your desperate need for more attention.",
							" A desperate little whine escapes from between your [pc.lips], betraying your desire for more of [npc.her] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
