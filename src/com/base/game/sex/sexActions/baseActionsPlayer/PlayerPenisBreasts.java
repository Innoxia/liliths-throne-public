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
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.84
 * @version 0.1.84
 * @author Innoxia
 */
public class PlayerPenisBreasts {
	
	public static final SexAction PLAYER_FORCE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Thrust into mouth";
		}

		@Override
		public String getActionDescription() {
			return "Push forwards and force the [pc.cockHead] of your cock into [npc.name]'s mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING && Main.game.getPlayer().getPenisRawSizeValue()>=6 && Sex.isPartnerFreeMouth() && Sex.getPartner().isBreastFuckablePaizuri();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently thrusting forwards between [npc.name]'s [npc.breasts], you push your [pc.cock+] all the way up to [npc.her] mouth and force the [pc.cockHead] past [npc.her] [npc.lips].",
							"Slowly pushing your [pc.hips] forwards, you force your [pc.cock+] between [npc.name]'s [npc.breasts+], pushing all the way until your [pc.cockHead] presses past [npc.her] [npc.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly thrusting forwards between [npc.name]'s [npc.breasts], you push your [pc.cock+] all the way up to [npc.her] mouth and force the [pc.cockHead] past [npc.her] [npc.lips].",
							"Greedily pushing your [pc.hips] forwards, you force your [pc.cock+] between [npc.name]'s [npc.breasts+], pushing all the way until your [pc.cockHead] presses past [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly thrusting forwards between [npc.name]'s [npc.breasts], you slam your [pc.cock+] up against [npc.her] mouth and force the [pc.cockHead] past [npc.her] [npc.lips].",
							"Violently slamming your [pc.hips] forwards, you thrust your [pc.cock+] between [npc.name]'s [npc.breasts+], pushing all the way until your [pc.cockHead] rams past [npc.her] [npc.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Thrusting forwards between [npc.name]'s [npc.breasts], you push your [pc.cock+] all the way up to [npc.her] mouth and force the [pc.cockHead] past [npc.her] [npc.lips].",
							"Pushing your [pc.hips] forwards, you force your [pc.cock+] between [npc.name]'s [npc.breasts+], pushing all the way until your [pc.cockHead] presses past [npc.her] [npc.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly thrusting forwards between [npc.name]'s [npc.breasts], you push your [pc.cock+] all the way up to [npc.her] mouth and force the [pc.cockHead] past [npc.her] [npc.lips].",
							"Greedily pushing your [pc.hips] forwards, you force your [pc.cock+] between [npc.name]'s [npc.breasts+], pushing all the way until your [pc.cockHead] presses past [npc.her] [npc.lips+]."));
					break;
				case SUB_RESISTING:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] grins at your enthusiasm, and, opening [npc.her] mouth to give the [pc.cockHead] of your [pc.cock] a loving suck, [npc.she] then draws back, but not before planting a kiss on the very tip.",
							" [npc.She] opens [pc.her] mouth to accept your [pc.cock+], giving the [pc.cockHead] a hot, wet suck before drawing back to deliver a soft kiss to the very tip."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a happy [npc.moan], and, eagerly opening [npc.her] mouth to give the [pc.cockHead] of your [pc.cock] a loving suck, [npc.she] then draws back, but not before planting a wet kiss on the very tip.",
							" [npc.She] eagerly opens [pc.her] mouth to accept your [pc.cock+], giving the [pc.cockHead] a hot, wet suck before drawing back to deliver a passionate kiss to the very tip."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], and, quickly opening [npc.her] mouth to give the [pc.cockHead] of your [pc.cock] a forceful suck, [npc.she] then draws back, but not before planting a rough kiss on the very tip.",
							" [npc.She] quickly opens [pc.her] mouth to accept your [pc.cock+], giving the [pc.cockHead] a forceful suck before drawing back to deliver a rough kiss to the very tip."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a little [npc.moan], and, opening [npc.her] mouth to give the [pc.cockHead] of your [pc.cock] an obedient suck, [npc.she] then draws back, but not before planting a quick kiss on the very tip.",
							" [npc.She] opens [pc.her] mouth to accept your [pc.cock+], giving the [pc.cockHead] an obediently suck before drawing back to deliver a quick kiss to the very tip."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a happy [npc.moan], and, eagerly opening [npc.her] mouth to give the [pc.cockHead] of your [pc.cock] a loving suck, [npc.she] then draws back, but not before planting a wet kiss on the very tip.",
							" [npc.She] eagerly opens [pc.her] mouth to accept your [pc.cock+], giving the [pc.cockHead] a hot, wet suck before drawing back to deliver a passionate kiss to the very tip."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], and, trying to pull [npc.her] mouth away from the [pc.cockHead] of your [pc.cock], [npc.she] desperately pleads for you to leave [npc.herHim] alone.",
							" [npc.She] jerks [pc.her] head back, trying to push your [pc.cock+] away from [npc.her] mouth as tears start to well up in [npc.her] [npc.eyes]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Main.game.getPlayer(), Sex.getPartner(), PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING), new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_TAKE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER) {
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Paizuri into mouth";
			} else {
				return "Naizuri into mouth";
			}
		}

		@Override
		public String getActionDescription() {
			return "Push forwards and take the [pc.cockHead] of [pc.name]'s cock into your mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePartner()!=SexPace.SUB_RESISTING && Main.game.getPlayer().getPenisRawSizeValue()>=6 && Sex.isPartnerFreeMouth() && Sex.getPartner().isBreastFuckablePaizuri();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pushing [npc.her] [npc.face] down towards your [pc.cock] as it slides up between [npc.her] [npc.breasts], [npc.name] parts [npc.her] [npc.lips+] and takes the [pc.cockHead] into [npc.her] mouth.",
							"Slowly pushing [npc.her] [pc.face] down, [npc.name] takes the [pc.cockHead] of your [pc.cock+] into [npc.her] mouth as you thrust up between [npc.her] [npc.breasts+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc.her] [npc.face] down towards your [pc.cock] as it slides up between [npc.her] [npc.breasts], [npc.name] greedily parts [npc.her] [npc.lips+] and takes the [pc.cockHead] into [npc.her] mouth.",
							"Eagerly pushing [npc.her] [pc.face] down, [npc.name] greedily takes the [pc.cockHead] of your [pc.cock+] into [npc.her] mouth as you thrust up between [npc.her] [npc.breasts+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] down towards your [pc.cock] as it slides up between [npc.her] [npc.breasts], [npc.name] greedily parts [npc.her] [npc.lips+] and takes the [pc.cockHead] into [npc.her] mouth.",
							"Pushing [npc.her] [pc.face] down, [npc.name] greedily takes the [pc.cockHead] of your [pc.cock+] into [npc.her] mouth as you thrust up between [npc.her] [npc.breasts+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] down towards your [pc.cock] as it slides up between [npc.her] [npc.breasts], [npc.name] parts [npc.her] [npc.lips+] and takes the [pc.cockHead] into [npc.her] mouth.",
							"Pushing [npc.her] [pc.face] down, [npc.name] takes the [pc.cockHead] of your [pc.cock+] into [npc.her] mouth as you thrust up between [npc.her] [npc.breasts+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc.her] [npc.face] down towards your [pc.cock] as it slides up between [npc.her] [npc.breasts], [npc.name] greedily parts [npc.her] [npc.lips+] and takes the [pc.cockHead] into [npc.her] mouth.",
							"Eagerly pushing [npc.her] [pc.face] down, [npc.name] greedily takes the [pc.cockHead] of your [pc.cock+] into [npc.her] mouth as you thrust up between [npc.her] [npc.breasts+]."));
					break;
				case SUB_RESISTING:
					break;
			}
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You smile at [npc.her] enthusiasm, and, gently pushing your [pc.cock] into [npc.her] mouth,"
									+ " you let [npc.herHim] suck and kiss the [pc.cockHead] for a moment, before pulling back and continuing to fuck [npc.her] [npc.breasts+].",
							" You slowly push your [pc.cock+] into [npc.her] mouth, allowing [npc.herHim] to give the [pc.cockHead] a hot, wet suck before drawing back and continuing to fuck [npc.her] [npc.breasts+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You grin at [npc.her] enthusiasm, and, eagerly pushing your [pc.cock] into [npc.her] mouth,"
									+ " you let [npc.herHim] suck and kiss the [pc.cockHead] for a moment, before pulling back and continuing to fuck [npc.her] [npc.breasts+].",
							" You eagerly push your [pc.cock+] into [npc.her] mouth, allowing [npc.herHim] to give the [pc.cockHead] a hot, wet suck before drawing back and continuing to fuck [npc.her] [npc.breasts+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You grin at [npc.her] enthusiasm, and, roughly forcing your [pc.cock] deeper into [npc.her] mouth,"
									+ " you allow [npc.herHim] suck and kiss the [pc.cockHead] for a moment, before pulling back and continuing to aggressively fuck [npc.her] [npc.breasts+].",
							" You roughly force your [pc.cock+] into [npc.her] mouth, allowing [npc.herHim] to give the [pc.cockHead] a hot, wet suck before drawing back and continuing to aggressively fuck [npc.her] [npc.breasts+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a little [pc.moan], and, pushing your [pc.cock] into [npc.her] mouth, you gasp as [npc.she] sucks and kisses the [pc.cockHead] for a moment,"
									+ " before allowing you to pull back and continue to fuck [npc.her] [npc.breasts+].",
							" You push your [pc.cock+] into [npc.her] mouth, gasping as [npc.she] gives the [pc.cockHead] a hot, wet suck before allowing you to pull back and continue to fuck [npc.her] [npc.breasts+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], and, eagerly pushing your [pc.cock] into [npc.her] mouth, you gasp as [npc.she] sucks and kisses the [pc.cockHead] for a moment,"
									+ " before allowing you to pull back and continue to happily fuck [npc.her] [npc.breasts+].",
							" You eagerly push your [pc.cock+] into [npc.her] mouth, gasping as [npc.she] gives the [pc.cockHead] a hot, wet suck before allowing you to pull back and continue to happily fuck [npc.her] [npc.breasts+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], and, trying to pull your [pc.cock] away from [npc.her] mouth, you desperately plead for [npc.herHim] to leave you alone.",
							" You try to pull your [pc.hips] back, but [npc.she] holds you in place, sucking on the [pc.cockHead] of your [pc.cock+] for a moment before finally letting you pull back."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Main.game.getPlayer(), Sex.getPartner(), PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING), new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	
	public static final SexAction PLAYER_FUCKING_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER) {
		
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Start paizuri";
			} else {
				return "Start naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Sink your [pc.cock+] between [npc.name]'s [npc.breasts+] and start fucking them.";
			} else {
				return "Start grinding your [pc.cock+] over [npc.name]'s chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of [npc.name]'s [npc.breasts+], you gently push them together, lining your [pc.cock] up to [npc.her] cleavage before sliding forwards and starting to fuck [npc.her] [npc.breasts]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to greedily sink your [pc.fingers] into [npc.name]'s [npc.breasts+], you eagerly push them together,"
										+ " lining your [pc.cock] up to [npc.her] cleavage before sliding forwards and starting to enthusiastically fuck [npc.her] [npc.breasts]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to roughly sink your [pc.fingers] into [npc.name]'s [npc.breasts+], you forcefully push them together,"
										+ " lining your [pc.cock] up to [npc.her] cleavage before slamming forwards and starting to rapidly fuck [npc.her] [npc.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of [npc.name]'s [npc.breasts+], you then push them together, lining your [pc.cock] up to [npc.her] cleavage before sliding forwards and starting to fuck [npc.her] [npc.breasts]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to greedily sink your [pc.fingers] into [npc.name]'s [npc.breasts+], you eagerly push them together,"
										+ " lining your [pc.cock] up to [npc.her] cleavage before sliding forwards and starting to enthusiastically fuck [npc.her] [npc.breasts]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out a happy little [npc.moan] in response, reaching up to help push [npc.her] [npc.breasts] together as [npc.she] encourages you to keep going."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] in response, quickly reaching up to help push [npc.her] [npc.breasts] together as [npc.she] happily encourages you to keep going."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] in response, reaching up to forcefully press [npc.her] [npc.breasts] together as [npc.she] dominantly orders you to keep going."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] in response, quickly reaching up to help push [npc.her] [npc.breasts] together as [npc.she] happily encourages you to keep going."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out a little [npc.moan] in response, before reaching up to help push [npc.her] [npc.breasts] together as [npc.she] encourages you to keep going."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] in response, reaching up to weakly try and push you away from [npc.her] [npc.breasts] as [npc.she] begs for you to stop."));
						break;
					default:
						break;
				}
				
			} else {
				if(Sex.getPartner().hasBreasts()) {
					switch(Sex.getSexPacePlayer()) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to take hold of [npc.name]'s [npc.breasts+], you gently try to push them together,"
											+ " lining your [pc.cock] up to what little cleavage [npc.she] has before sliding forwards and starting to grind down over [npc.her] chest."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to squeeze and grope [npc.name]'s [npc.breasts+], you do your best to try to push them together,"
											+ " lining your [pc.cock] up to what little cleavage [npc.she] has before sliding forwards and starting to eagerly grind down over [npc.her] chest."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to roughly squeeze and grope [npc.name]'s [npc.breasts+], you do your best to try to push them together,"
											+ " lining your [pc.cock] up to what little cleavage [npc.she] has before sliding forwards and starting to forcefully grind down over [npc.her] chest."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to squeeze and grope [npc.name]'s [npc.breasts+], you do your best to try to push them together,"
											+ " lining your [pc.cock] up to what little cleavage [npc.she] has before sliding forwards and starting to grind down over [npc.her] chest."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to squeeze and grope [npc.name]'s [npc.breasts+], you do your best to try to push them together,"
											+ " lining your [pc.cock] up to what little cleavage [npc.she] has before sliding forwards and starting to eagerly grind down over [npc.her] chest."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPacePartner()) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out a happy little [npc.moan] in response, reaching up to try and help push [npc.her] [npc.breastSize] [npc.breasts] together as [npc.she] encourages you to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out [npc.a_moan+] in response, quickly reaching up to try and help push [npc.her] [npc.breastSize] [npc.breasts] together as [npc.she] happily encourages you to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out [npc.a_moan+] in response, reaching up to forcefully try and press [npc.her] [npc.breastSize] [npc.breasts] together as [npc.she] dominantly orders you to keep going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out [npc.a_moan+] in response, quickly reaching up to try and help push [npc.her] [npc.breastSize] [npc.breasts] together as [npc.she] happily encourages you to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out a little [npc.moan] in response, before reaching up to try and help push [npc.her] [npc.breastSize] [npc.breasts] together as [npc.she] encourages you to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out [npc.a_moan+] in response, reaching up to weakly try and push you away from [npc.her] [npc.breastSize] [npc.breasts] as [npc.she] begs for you to stop."));
							break;
						default:
							break;
					}
					
				} else {
					switch(Sex.getSexPacePlayer()) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press your [pc.hands] against [npc.name]'s torso, you reposition yourself to line your [pc.cock] up over [npc.her] chest,"
											+ " before sliding forwards and starting to grind down against [npc.her] body."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press your [pc.hands] against [npc.name]'s torso, you reposition yourself to line your [pc.cock] up over [npc.her] chest,"
											+ " before sliding forwards and starting to eagerly grind down against [npc.her] body."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to roughly press your [pc.hands] against [npc.name]'s torso, you reposition yourself to line your [pc.cock] up over [npc.her] chest,"
											+ " before sliding forwards and starting to forcefully grind down against [npc.her] body."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press your [pc.hands] against [npc.name]'s torso, you reposition yourself to line your [pc.cock] up over [npc.her] chest,"
											+ " before sliding forwards and starting to grind down against [npc.her] body."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press your [pc.hands] against [npc.name]'s torso, you reposition yourself to line your [pc.cock] up over [npc.her] chest,"
											+ " before sliding forwards and starting to eagerly grind down against [npc.her] body."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPacePartner()) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out a happy little [npc.moan] in response, pushing [npc.her] chest out as [npc.she] encourages you to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out [npc.a_moan+] in response, quickly pushing [npc.her] chest out as [npc.she] happily encourages you to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out [npc.a_moan+] in response, forcefully pushing [npc.her] chest out as [npc.she] dominantly orders you to keep going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out [npc.a_moan+] in response, pushing [npc.her] chest out as [npc.she] happily encourages you to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out a little [npc.moan] in response, pushing [npc.her] chest out as [npc.she] encourages you to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.She] lets out [npc.a_moan+] in response, reaching up to weakly try and push you away from [npc.herHim] as [npc.she] begs for you to stop."));
							break;
						default:
							break;
					}
				}
			}
				
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Gentle paizuri";
			} else {
				return "Gentle naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Gently fuck [npc.name]'s [npc.breasts+].";
			} else {
				return "Gently grind against [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You gently slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] enthusiastically presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You slowly thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out a soft [pc.moan] as [npc.she] reaches up and enthusiastically presses [npc.her] [npc.breasts+] together for you."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You gently slide your [pc.cock+] up into [npc.name]'s cleavage, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
								"You slowly thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out a soft [pc.moan] as [npc.she] weakly struggles and tries to push you away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You gently slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] presses [npc.her] [npc.breasts+] together and asks you to keep going.",
								"You slowly thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out a soft [pc.moan] as [npc.she] reaches up and presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getPartner().hasBreasts()) {
					switch(Sex.getSexPacePartner()) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] enthusiastically tries to press [npc.her] [npc.breastSize] [npc.breasts+] together while begging for you to keep going.",
									"You slowly thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out a soft [pc.moan] as [npc.she] reaches up and desperately tries to press [npc.her] [npc.breastSize] [npc.breasts+] together for you."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You slowly thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer, you let out a soft [pc.moan] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has, grinning as [npc.she] tries to press [npc.her] [npc.breasts+] together and asks you to keep going.",
									"You slowly thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer, you let out a soft [pc.moan] as [npc.she] reaches up and tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPacePartner()) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] enthusiastically begs for you to keep going.",
									"You slowly thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out a soft [pc.moan] as [npc.she] desperately begs for you not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] against [npc.name]'s chest, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You slowly thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out a soft [pc.moan] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] softly asks for you to keep going.",
									"You slowly thrust down against [npc.name]'s chest, causing [npc.her] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out a soft [pc.moan] as [npc.she] meekly asks for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Continue fucking [npc.name]'s [npc.breasts+].";
			} else {
				return "Continue grinding against [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] enthusiastically presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and enthusiastically presses [npc.her] [npc.breasts+] together for you."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] presses [npc.her] [npc.breasts+] together and asks you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getPartner().hasBreasts()) {
					switch(Sex.getSexPacePartner()) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] enthusiastically tries to press [npc.her] [npc.breastSize] [npc.breasts+] together while begging for you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and desperately tries to press [npc.her] [npc.breastSize] [npc.breasts+] together for you."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has, grinning as [npc.she] tries to press [npc.her] [npc.breasts+] together and asks you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPacePartner()) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] enthusiastically begs for you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] desperately begs for you not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] softly asks for you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.her] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] meekly asks for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Rough paizuri";
			} else {
				return "Rough naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Roughly fuck [npc.name]'s [npc.breasts+].";
			} else {
				return "Roughly grind against [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}


		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly slam your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] enthusiastically presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You forcefully thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
								"Roughly thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and enthusiastically presses [npc.her] [npc.breasts+] together for you."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly slam your [pc.cock+] up into [npc.name]'s cleavage, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
								"You forcefully thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
								"Roughly thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly slam your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] presses [npc.her] [npc.breasts+] together and asks you to keep going.",
								"You forcefully thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
								"Roughly thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getPartner().hasBreasts()) {
					switch(Sex.getSexPacePartner()) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] enthusiastically tries to press [npc.her] [npc.breastSize] [npc.breasts+] together while begging for you to keep going.",
									"You forcefully thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Roughly thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breastSize] [npc.breasts+] together for you."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] up into the small amount of cleavage that [npc.name] has, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You forcefully thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Roughly thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] up into the small amount of cleavage that [npc.name] has, grinning as [npc.she] tries to press [npc.her] [npc.breasts+] together and asks you to keep going.",
									"You forcefully thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Roughly thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer, you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPacePartner()) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] enthusiastically begs for you to keep going.",
									"You forcefully thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Roughly thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] desperately begs for you not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] against [npc.name]'s chest, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You forcefully thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Roughly thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] softly asks for you to keep going.",
									"You forcefully thrust down against [npc.name]'s chest, causing [npc.her] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Roughly thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] meekly asks for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Continue fucking [npc.name]'s [npc.breasts+].";
			} else {
				return "Continue grinding against [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] gently presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out a soft [npc.moan] as [npc.she] glances down at your [pc.cock+] and bites [npc.her] [npc.lip].",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and gently presses [npc.her] [npc.breasts+] together for you."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You slide your [pc.cock+] up into [npc.name]'s cleavage, letting out [pc.a_moan+] as [npc.she] roughly presses [npc.her] [npc.breasts+] together and orders you to keep going.",
								"You thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.she]'s still in charge.",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] forcefully pushes [npc.her] [npc.breasts+] together and demands that you pick up the pace."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] eagerly presses [npc.her] [npc.breasts+] together and tells you to keep going.",
								"You thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip].",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and eagerly presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getPartner().hasBreasts()) {
					switch(Sex.getSexPacePartner()) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] gently tries to press [npc.her] [npc.breastSize] [npc.breasts+] together while begging for you to keep going.",
									"You thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] glances down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breastSize] [npc.breasts+] together for you."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " letting out [pc.a_moan+] as [npc.she] roughly tries to press [npc.her] [npc.breastSize] [npc.breasts+] together and orders you to keep going.",
									"You thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+],"
											+ " causing [npc.her] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.she]'s still in charge.",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] forcefully tries to push [npc.her] [npc.breasts+] together and demands that you pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] gently tries to press [npc.her] [npc.breastSize] [npc.breasts+] together while telling you to keep going.",
									"You thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip+].",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and eagerly tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPacePartner()) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] gently begs for you to keep going.",
									"You thrust down against [npc.name]'s chest, causing [npc.her] to let out a soft [npc.moan] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] softly begs for you not to stop."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] against [npc.name]'s chest, letting out [pc.a_moan+] as [npc.she] roughly orders you to keep going.",
									"You thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.she]'s still in charge.",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] forcefully demands that you pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] eagerly [npc.moans] for you to keep going.",
									"You thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] as [npc.she] looks down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] eagerly begs for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Eager paizuri";
			} else {
				return "Eager naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Eagerly fuck [npc.name]'s [npc.breasts+].";
			} else {
				return "Eagerly grind against [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] gently presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out a soft [npc.moan] as [npc.she] glances down at your [pc.cock+] and bites [npc.her] [npc.lip].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and gently presses [npc.her] [npc.breasts+] together for you."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, letting out [pc.a_moan+] as [npc.she] roughly presses [npc.her] [npc.breasts+] together and orders you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.she]'s still in charge.",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] forcefully pushes [npc.her] [npc.breasts+] together and demands that you pick up the pace."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] eagerly presses [npc.her] [npc.breasts+] together and tells you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and eagerly presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getPartner().hasBreasts()) {
					switch(Sex.getSexPacePartner()) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] gently tries to press [npc.her] [npc.breastSize] [npc.breasts+] together while begging for you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] glances down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breastSize] [npc.breasts+] together for you."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " letting out [pc.a_moan+] as [npc.she] roughly tries to press [npc.her] [npc.breastSize] [npc.breasts+] together and orders you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+],"
											+ " causing [npc.her] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.she]'s still in charge.",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] forcefully tries to push [npc.her] [npc.breasts+] together and demands that you pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] gently tries to press [npc.her] [npc.breastSize] [npc.breasts+] together while telling you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip+].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and eagerly tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPacePartner()) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] gently begs for you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.her] to let out a soft [npc.moan] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] softly begs for you not to stop."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, letting out [pc.a_moan+] as [npc.she] roughly orders you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.she]'s still in charge.",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] forcefully demands that you pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] eagerly [npc.moans] for you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] as [npc.she] looks down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] eagerly begs for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Resist paizuri";
			} else {
				return "Resist naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Try to pull your [pc.cock] away from [npc.name]'s [npc.breasts+].";
			} else {
				return "Try to pull your [pc.cock] away from [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPartner().isBreastFuckablePaizuri()) { //TODO
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] gently presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out a soft [npc.moan] as [npc.she] glances down at your [pc.cock+] and bites [npc.her] [npc.lip].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and gently presses [npc.her] [npc.breasts+] together for you."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, letting out [pc.a_moan+] as [npc.she] roughly presses [npc.her] [npc.breasts+] together and orders you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.she]'s still in charge.",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] forcefully pushes [npc.her] [npc.breasts+] together and demands that you pick up the pace."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] eagerly presses [npc.her] [npc.breasts+] together and tells you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and eagerly presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getPartner().hasBreasts()) {
					switch(Sex.getSexPacePartner()) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] gently tries to press [npc.her] [npc.breastSize] [npc.breasts+] together while begging for you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] glances down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breastSize] [npc.breasts+] together for you."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " letting out [pc.a_moan+] as [npc.she] roughly tries to press [npc.her] [npc.breastSize] [npc.breasts+] together and orders you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+],"
											+ " causing [npc.her] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.she]'s still in charge.",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] forcefully tries to push [npc.her] [npc.breasts+] together and demands that you pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] gently tries to press [npc.her] [npc.breastSize] [npc.breasts+] together while telling you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts+], causing [npc.her] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip+].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and eagerly tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPacePartner()) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] gently begs for you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.her] to let out a soft [npc.moan] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] softly begs for you not to stop."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, letting out [pc.a_moan+] as [npc.she] roughly orders you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.she]'s still in charge.",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] forcefully demands that you pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] eagerly [npc.moans] for you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.her] to let out [npc.a_moan+] as [npc.she] looks down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] eagerly begs for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF) ,new ListValue<>(Fetish.FETISH_DOMINANT),new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom() || Sex.getSexManager().isConsensualSex(); // Player can only stop if they're in charge (otherwise, this is the partner fucking themselves on the player's cock).
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Stop paizuri";
			} else {
				return "Stop naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Pull your [pc.cock+] away from [npc.name]'s [npc.breasts+] and stop fucking them.";
			} else {
				return "Pull your [pc.cock+] away from [npc.name]'s chest and stop grinding against [npc.herHim].";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" "));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" "));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_USING_COCK_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER) {

		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Perform paizuri";
			} else {
				return "Perform naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Use [pc.name]'s [pc.cock+] to fuck your [npc.breasts+].";
			} else {
				return "Use [pc.name]'s [pc.cock+] to grind against your chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" "));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" "));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" ."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" ."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" ."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" ."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Gentle paizuri";
			} else {
				return "Gentle naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Gently pleasure [pc.name]'s [pc.cock+] with your [npc.breasts+].";
			} else {
				return "Gently pleasure [pc.name]'s [pc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					""));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Pleasure [pc.name]'s [pc.cock+] with your [npc.breasts+].";
			} else {
				return "Pleasure [pc.name]'s [pc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					""));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Rough paizuri";
			} else {
				return "Rough naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Roughly pleasure [pc.name]'s [pc.cock+] with your [npc.breasts+].";
			} else {
				return "Roughly pleasure [pc.name]'s [pc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					""));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Pleasure [pc.name]'s [pc.cock+] with your [npc.breasts+].";
			} else {
				return "Pleasure [pc.name]'s [pc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					""));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Eager paizuri";
			} else {
				return "Eager naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Eagerly pleasure [pc.name]'s [pc.cock+] with your [npc.breasts+].";
			} else {
				return "Eagerly pleasure [pc.name]'s [pc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					""));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER,
			null,
			SexPace.SUB_RESISTING) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Resist paizuri";
			} else {
				return "Resist naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Try and pull your [npc.breasts+] away from [pc.name]'s [pc.cock+].";
			} else {
				return "Try and pull your chest away from [pc.name]'s [pc.cock+].";
			}
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PARTNER_FUCKED_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.BREAST_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Stop paizuri";
			} else {
				return "Stop naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getPartner().isBreastFuckablePaizuri()) {
				return "Push [pc.name]'s [pc.cock] away from your [npc.breasts+].";
			} else {
				return "Push [pc.name]'s [pc.cock] away from your chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							""));
					break;
			}
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" "));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" "));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
