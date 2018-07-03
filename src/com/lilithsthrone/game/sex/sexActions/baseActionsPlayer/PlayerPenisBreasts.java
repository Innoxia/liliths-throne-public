package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.84
 * @version 0.1.84
 * @author Innoxia
 */
public class PlayerPenisBreasts {
	
	public static final SexAction PLAYER_FORCE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
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
			return Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING
					&& Main.game.getPlayer().getPenisRawSizeValue()>=6
					&& Sex.isOrificeFree(Sex.getActivePartner(), SexAreaOrifice.MOUTH)
					&& Sex.getActivePartner().isBreastFuckablePaizuri()
					&& Sex.getPosition() != SexPositionType.SIXTY_NINE;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
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
			switch(Sex.getSexPace(Sex.getActivePartner())) {
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
			Sex.transferLubrication(Main.game.getPlayer(), SexAreaPenetration.PENIS, Sex.getActivePartner(), SexAreaOrifice.MOUTH);
		}
		
	};
	
	public static final SexAction PARTNER_TAKE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
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
			return Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING
					&& Main.game.getPlayer().getPenisRawSizeValue()>=6
					&& Sex.isOrificeFree(Sex.getActivePartner(), SexAreaOrifice.MOUTH)
					&& Sex.getActivePartner().isBreastFuckablePaizuri()
					&& Sex.getPosition() != SexPositionType.SIXTY_NINE;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
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
			switch(Sex.getSexPace(Main.game.getPlayer())) {
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
			Sex.transferLubrication(Main.game.getPlayer(), SexAreaPenetration.PENIS, Sex.getActivePartner(), SexAreaOrifice.MOUTH);
		}
		
	};
	
	
	public static final SexAction PLAYER_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Start paizuri";
			} else {
				return "Start naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Sink your [pc.cock+] between [npc.name]'s [npc.breasts+] and start fucking them.";
			} else {
				return "Start grinding your [pc.cock+] over [npc.name]'s chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
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
				switch(Sex.getSexPace(Sex.getActivePartner())) {
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
				if(Sex.getActivePartner().hasBreasts()) {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
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
					switch(Sex.getSexPace(Sex.getActivePartner())) {
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
					switch(Sex.getSexPace(Main.game.getPlayer())) {
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
					switch(Sex.getSexPace(Sex.getActivePartner())) {
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
		
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Gentle paizuri";
			} else {
				return "Gentle naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Gently fuck [npc.name]'s [npc.breasts+].";
			} else {
				return "Gently grind against [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You gently slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] enthusiastically presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You slowly thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out a soft [pc.moan] as [npc.she] reaches up and enthusiastically presses [npc.her] [npc.breasts+] together for you."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You gently slide your [pc.cock+] up into [npc.name]'s cleavage, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
								"You slowly thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out a soft [pc.moan] as [npc.she] weakly struggles and tries to push you away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You gently slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] presses [npc.her] [npc.breasts+] together and asks you to keep going.",
								"You slowly thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out a soft [pc.moan] as [npc.she] reaches up and presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] enthusiastically tries to press [npc.her] [npc.breastSize] [npc.breasts] together while begging for you to keep going.",
									"You slowly thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out a soft [pc.moan] as [npc.she] reaches up and desperately tries to press [npc.her] [npc.breastSize] [npc.breasts] together for you."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You slowly thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer, you let out a soft [pc.moan] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has, grinning as [npc.she] tries to press [npc.her] [npc.breasts+] together and asks you to keep going.",
									"You slowly thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer, you let out a soft [pc.moan] as [npc.she] reaches up and tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] enthusiastically begs for you to keep going.",
									"You slowly thrust down against [npc.name]'s chest, causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out a soft [pc.moan] as [npc.she] desperately begs for you not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] against [npc.name]'s chest, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You slowly thrust down against [npc.name]'s chest, causing [npc.herHim] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out a soft [pc.moan] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You gently slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] softly asks for you to keep going.",
									"You slowly thrust down against [npc.name]'s chest, causing [npc.herHim] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out a soft [pc.moan] as [npc.she] meekly asks for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Continue fucking [npc.name]'s [npc.breasts+].";
			} else {
				return "Continue grinding against [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] enthusiastically presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and enthusiastically presses [npc.her] [npc.breasts+] together for you."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] presses [npc.her] [npc.breasts+] together and asks you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] enthusiastically tries to press [npc.her] [npc.breastSize] [npc.breasts] together while begging for you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and desperately tries to press [npc.her] [npc.breastSize] [npc.breasts] together for you."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has, grinning as [npc.she] tries to press [npc.her] [npc.breasts+] together and asks you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] enthusiastically begs for you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] desperately begs for you not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.herHim] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] softly asks for you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.herHim] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] meekly asks for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Rough paizuri";
			} else {
				return "Rough naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Roughly fuck [npc.name]'s [npc.breasts+].";
			} else {
				return "Roughly grind against [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly slam your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] enthusiastically presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You forcefully thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
								"Roughly thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and enthusiastically presses [npc.her] [npc.breasts+] together for you."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly slam your [pc.cock+] up into [npc.name]'s cleavage, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
								"You forcefully thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
								"Roughly thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly slam your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] presses [npc.her] [npc.breasts+] together and asks you to keep going.",
								"You forcefully thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
								"Roughly thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] enthusiastically tries to press [npc.her] [npc.breastSize] [npc.breasts] together while begging for you to keep going.",
									"You forcefully thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Roughly thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breastSize] [npc.breasts] together for you."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] up into the small amount of cleavage that [npc.name] has, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You forcefully thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Roughly thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] up into the small amount of cleavage that [npc.name] has, grinning as [npc.she] tries to press [npc.her] [npc.breasts+] together and asks you to keep going.",
									"You forcefully thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Roughly thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer, you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] enthusiastically begs for you to keep going.",
									"You forcefully thrust down against [npc.name]'s chest, causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+].",
									"Roughly thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] desperately begs for you not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] against [npc.name]'s chest, ignoring [npc.her] desperate cries for you to stop as [npc.she] weakly tries to push you away.",
									"You forcefully thrust down against [npc.name]'s chest, causing [npc.herHim] to let out [npc.a_moan+] as tears start welling up in [npc.her] [npc.eyes].",
									"Roughly thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] weakly struggles and tries to push you away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You roughly slam your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] softly asks for you to keep going.",
									"You forcefully thrust down against [npc.name]'s chest, causing [npc.herHim] to let out a little [npc.moan] as [npc.she] looks down at your [pc.cock+].",
									"Roughly thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] meekly asks for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Continue fucking [npc.name]'s [npc.breasts+].";
			} else {
				return "Continue grinding against [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] gently presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out a soft [npc.moan] as [npc.she] glances down at your [pc.cock+] and bites [npc.her] [npc.lip].",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and gently presses [npc.her] [npc.breasts+] together for you."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You slide your [pc.cock+] up into [npc.name]'s cleavage, letting out [pc.a_moan+] as [npc.she] roughly presses [npc.her] [npc.breasts+] together and orders you to keep going.",
								"You thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.sheIs] still in charge.",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] forcefully pushes [npc.her] [npc.breasts+] together and demands that you pick up the pace."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] eagerly presses [npc.her] [npc.breasts+] together and tells you to keep going.",
								"You thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip].",
								"Thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and eagerly presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] gently tries to press [npc.her] [npc.breastSize] [npc.breasts] together while begging for you to keep going.",
									"You thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] glances down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breastSize] [npc.breasts] together for you."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " letting out [pc.a_moan+] as [npc.she] roughly tries to press [npc.her] [npc.breastSize] [npc.breasts] together and orders you to keep going.",
									"You thrust up between [npc.name]'s [npc.breastSize] [npc.breasts],"
											+ " causing [npc.herHim] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.sheIs] still in charge.",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] forcefully tries to push [npc.her] [npc.breasts+] together and demands that you pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] gently tries to press [npc.her] [npc.breastSize] [npc.breasts] together while telling you to keep going.",
									"You thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip+].",
									"Thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and eagerly tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] gently begs for you to keep going.",
									"You thrust down against [npc.name]'s chest, causing [npc.herHim] to let out a soft [npc.moan] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] softly begs for you not to stop."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] against [npc.name]'s chest, letting out [pc.a_moan+] as [npc.she] roughly orders you to keep going.",
									"You thrust down against [npc.name]'s chest, causing [npc.herHim] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.sheIs] still in charge.",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] forcefully demands that you pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] eagerly [npc.moans] for you to keep going.",
									"You thrust down against [npc.name]'s chest, causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] looks down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] eagerly begs for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Eager paizuri";
			} else {
				return "Eager naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Eagerly fuck [npc.name]'s [npc.breasts+].";
			} else {
				return "Eagerly grind against [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] gently presses [npc.her] [npc.breasts+] together while begging for you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out a soft [npc.moan] as [npc.she] glances down at your [pc.cock+] and bites [npc.her] [npc.lip].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and gently presses [npc.her] [npc.breasts+] together for you."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, letting out [pc.a_moan+] as [npc.she] roughly presses [npc.her] [npc.breasts+] together and orders you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.sheIs] still in charge.",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] forcefully pushes [npc.her] [npc.breasts+] together and demands that you pick up the pace."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You eagerly slide your [pc.cock+] up into [npc.name]'s cleavage, grinning as [npc.she] eagerly presses [npc.her] [npc.breasts+] together and tells you to keep going.",
								"You enthusiastically thrust up between [npc.name]'s [npc.breasts+], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip].",
								"Desperately thrusting your [pc.cock+] between [npc.name]'s cleavage, you let out [pc.a_moan+] as [npc.she] reaches up and eagerly presses [npc.her] [npc.breasts+] together for you."));
						break;
				}
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] gently tries to press [npc.her] [npc.breastSize] [npc.breasts] together while begging for you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] glances down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and tries to press [npc.her] [npc.breastSize] [npc.breasts] together for you."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " letting out [pc.a_moan+] as [npc.she] roughly tries to press [npc.her] [npc.breastSize] [npc.breasts] together and orders you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts],"
											+ " causing [npc.herHim] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.sheIs] still in charge.",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] forcefully tries to push [npc.her] [npc.breasts+] together and demands that you pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] up into the small amount of cleavage that [npc.name] has,"
											+ " grinning as [npc.she] gently tries to press [npc.her] [npc.breastSize] [npc.breasts] together while telling you to keep going.",
									"You enthusiastically thrust up between [npc.name]'s [npc.breastSize] [npc.breasts], causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip+].",
									"Desperately thrusting your [pc.cock+] between the tiny amount of cleavage that [npc.name] has on offer,"
											+ " you let out [pc.a_moan+] as [npc.she] reaches up and eagerly tries to press [npc.her] [npc.breasts+] together for you."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] gently begs for you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.herHim] to let out a soft [npc.moan] as [npc.she] lustfully gazes down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] softly begs for you not to stop."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, letting out [pc.a_moan+] as [npc.she] roughly orders you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.herHim] to let out [npc.a_moan+] before [npc.she] regains [npc.her] composure and roughly growls out that [npc.sheIs] still in charge.",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] forcefully demands that you pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You eagerly slide your [pc.cock+] against [npc.name]'s chest, grinning as [npc.she] eagerly [npc.moans] for you to keep going.",
									"You enthusiastically thrust down against [npc.name]'s chest, causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] looks down at your [pc.cock+] and bites [npc.her] [npc.lip].",
									"Desperately thrusting your [pc.cock+] against [npc.name]'s chest, you let out [pc.a_moan+] as [npc.she] eagerly begs for you to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Resist paizuri";
			} else {
				return "Resist naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Try to pull your [pc.cock] away from [npc.name]'s [npc.breasts+].";
			} else {
				return "Try to pull your [pc.cock] away from [npc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You desperately try to pull your [pc.cock+] out of [npc.name]'s cleavage, but [npc.she] firmly holds you in place,"
										+ " pressing [npc.her] [npc.breasts+] together while gently reminding you that [npc.she]'ll do whatever [npc.she] wants.",
								"You frantically try to pull away from [npc.name]'s [npc.breasts+], but [npc.she] firmly holds you in place, softly [npc.moaning] as [npc.she] ignores your desperate protests.",
								"You feel tears welling up in your eyes as you try to pull out of [npc.name]'s cleavage, but [npc.her] grip is too strong,"
										+ " and [npc.she] continues softly [npc.moaning] as [npc.she] firmly forces your [pc.cock+] between [npc.her] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You desperately try to pull your [pc.cock+] out of [npc.name]'s cleavage, but [npc.she] roughly holds you in place, pressing [npc.her] [npc.breasts+] together while growling that [npc.she]'ll use you however [npc.she] wants.",
								"You frantically try to pull away from [npc.name]'s [npc.breasts+], but [npc.she] roughly holds you in place, [npc.moaning+] as [npc.she] ignores your futile protests.",
								"You feel tears welling up in your eyes as you try to pull out of [npc.name]'s cleavage, but [npc.her] grip is too strong,"
										+ " and [npc.she] continues [npc.moaning+] as [npc.she] roughly forces your [pc.cock+] between [npc.her] [npc.breasts+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You desperately try to pull your [pc.cock+] out of [npc.name]'s cleavage, but [npc.she] firmly holds you in place,"
										+ " pressing [npc.her] [npc.breasts+] together while [npc.moaning] that [npc.she]'ll do whatever [npc.she] wants.",
								"You frantically try to pull away from [npc.name]'s [npc.breasts+], but [npc.she] firmly holds you in place, [npc.moaning+] as [npc.she] ignores your futile protests.",
								"You feel tears welling up in your eyes as you try to pull out of [npc.name]'s cleavage, but [npc.her] grip is too strong,"
										+ " and [npc.she] continues [npc.moaning+] as [npc.she] eagerly forces your [pc.cock+] between [npc.her] [npc.breasts+]."));
						break;
				}
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You desperately try to pull your [pc.cock+] out of the small amount of cleavage that [npc.name] has, but [npc.she] firmly holds you in place,"
											+ " trying to press [npc.her] [npc.breasts+] together while gently reminding you that [npc.she]'ll do whatever [npc.she] wants.",
									"You frantically try to pull away from [npc.name]'s [npc.breastSize] [npc.breasts], but [npc.she] firmly holds you in place, softly [npc.moaning] as [npc.she] ignores your desperate protests.",
									"You feel tears welling up in your eyes as you try to pull out of the tiny amount of cleavage that [npc.name] has on offer, but [npc.her] grip is too strong,"
											+ " and [npc.she] continues softly [npc.moaning] as [npc.she] firmly forces your [pc.cock+] between [npc.her] [npc.breasts+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You desperately try to pull your [pc.cock+] out of the small amount of cleavage that [npc.name] has, but [npc.she] roughly holds you in place,"
											+ " trying to press [npc.her] [npc.breasts+] together while growling that [npc.she]'ll use you however [npc.she] wants.",
									"You frantically try to pull away from [npc.name]'s [npc.breastSize] [npc.breasts], but [npc.she] roughly holds you in place, [npc.moaning+] as [npc.she] ignores your futile protests.",
									"You feel tears welling up in your eyes as you try to pull out of the tiny amount of cleavage that [npc.name] has on offer, but [npc.her] grip is too strong,"
											+ " and [npc.she] continues [npc.moaning+] as [npc.she] roughly forces your [pc.cock+] between [npc.her] [npc.breasts+]."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You desperately try to pull your [pc.cock+] out of the small amount of cleavage that [npc.name] has, but [npc.she] firmly holds you in place,"
											+ " trying to press [npc.her] [npc.breasts+] together while  that [npc.she]'ll do whatever [npc.she] wants.",
									"You frantically try to pull away from [npc.name]'s [npc.breastSize] [npc.breasts], but [npc.she] firmly holds you in place, [npc.moaning+] as [npc.she] ignores your futile protests.",
									"You feel tears welling up in your eyes as you try to pull out of the tiny amount of cleavage that [npc.name] has on offer, but [npc.her] grip is too strong,"
											+ " and [npc.she] continues [npc.moaning+] as [npc.she] eagerly forces your [pc.cock+] between [npc.her] [npc.breasts+]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You desperately try to pull your [pc.cock+] away from [npc.name]'s chest, but [npc.she] firmly holds you in place, grinding against you as [npc.she] gently [npc.moansVerb] that [npc.she]'ll do whatever [npc.she] wants.",
									"You frantically try to pull away from [npc.name]'s chest, but [npc.she] firmly holds you in place, softly [npc.moaning] as [npc.she] ignores your desperate protests.",
									"You feel tears welling up in your eyes as you try to pull away from [npc.name], but [npc.her] grip is too strong,"
											+ " and [npc.she] continues softly [npc.moaning] as [npc.she] firmly forces your [pc.cock+] against [npc.her] chest."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You desperately try to pull your [pc.cock+] away from [npc.name]'s chest, but [npc.she] roughly holds you in place,"
											+ " forcefully grinding against you as [npc.she] [npc.moansVerb] that [npc.she]'ll do whatever [npc.she] wants.",
									"You frantically try to pull away from [npc.name]'s chest, but [npc.she] roughly holds you in place, [npc.moaning+] as [npc.she] ignores your futile protests.",
									"You feel tears welling up in your eyes as you try to pull away from [npc.name], but [npc.her] grip is too strong,"
											+ " and [npc.she] continues [npc.moaning+] as [npc.she] roughly forces your [pc.cock+] against [npc.her] chest."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You desperately try to pull your [pc.cock+] away from [npc.name]'s chest, but [npc.she] firmly holds you in place, grinding against you as [npc.she] [npc.moansVerb] that [npc.she]'ll do whatever [npc.she] wants.",
									"You frantically try to pull away from [npc.name]'s chest, but [npc.she] firmly holds you in place, [npc.moaning+] as [npc.she] ignores your futile protests.",
									"You feel tears welling up in your eyes as you try to pull away from [npc.name], but [npc.her] grip is too strong,"
											+ " and [npc.she] continues [npc.moaning+] as [npc.she] eagerly forces your [pc.cock+] against [npc.her] chest."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual(); // Player can only stop if they're in charge (otherwise, this is the partner fucking themselves on the player's cock).
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Stop paizuri";
			} else {
				return "Stop naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Pull your [pc.cock+] away from [npc.name]'s [npc.breasts+] and stop fucking them.";
			} else {
				return "Pull your [pc.cock+] away from [npc.name]'s chest and stop grinding against [npc.herHim].";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pushing [npc.name] away, you pull your [pc.cock+] out from [npc.her] cleavage and tell [npc.herHim] that you've had enough of fucking [npc.her] [npc.breasts+].",
								"Roughly pulling your [pc.cock+] out from [npc.name]'s cleavage, you tell [npc.herHim] that you've had enough of fucking [npc.her] [npc.breasts+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You pull your [pc.cock+] out from [npc.name]'s cleavage and tell [npc.herHim] that you've had enough of fucking [npc.her] [npc.breasts+].",
								"Pulling your [pc.cock+] out from [npc.name]'s cleavage, you tell [npc.herHim] that you've had enough of fucking [npc.her] [npc.breasts+]."));
						break;
				}
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly pushing [npc.name] away, you pull your [pc.cock+] out from [npc.her] tiny amount of cleavage and tell [npc.herHim] that you've had enough of fucking [npc.her] [npc.breastSize] [npc.breasts].",
									"Roughly pulling your [pc.cock+] out from [npc.name]'s tiny amount of cleavage, you tell [npc.herHim] that you've had enough of fucking [npc.her] [npc.breastSize] [npc.breasts]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You pull your [pc.cock+] out from [npc.name]'s tiny amount of cleavage and tell [npc.herHim] that you've had enough of fucking [npc.her] [npc.breastSize] [npc.breasts].",
									"Pulling your [pc.cock+] out from [npc.name]'s tiny amount of cleavage, you tell [npc.herHim] that you've had enough of fucking [npc.her] [npc.breastSize] [npc.breasts]."));
							break;
					}
				} else {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly pushing [npc.name] away, you take your [pc.cock+] away from [npc.her] chest and tell [npc.herHim] that you've had enough of grinding against [npc.herHim].",
									"Roughly pulling your [pc.cock+] away from [npc.name]'s chest, you tell [npc.herHim] that you've had enough of grinding against [npc.herHim]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"You take your [pc.cock+] away from [npc.name]'s chest and tell [npc.herHim] that you've had enough of grinding against [npc.herHim].",
									"Pulling your [pc.cock+] away from [npc.name]'s chest, you tell [npc.herHim] that you've had enough of grinding against [npc.herHim]."));
							break;
					}
				}
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] continues struggling against you, [npc.moaning+] as [npc.she] begs you to leave [npc.herHim] alone.",
							" With [npc.a_moan+], [npc.she] begs you to leave [npc.herHim] alone, tears welling up in [npc.her] [npc.eyes] as [npc.she] weakly tries to push you away."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], betraying [npc.her] desire for you to continue.",
							" With [npc.a_moan+], [npc.she] begs for you to keep on using [npc.herHim]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_USING_COCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Perform paizuri";
			} else {
				return "Perform naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Use [pc.name]'s [pc.cock+] to fuck your [npc.breasts+].";
			} else {
				return "Use [pc.name]'s [pc.cock+] to grind against your chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently taking hold of your [pc.cock+], [npc.name] guides it up to [npc.her] cleavage, and, sliding forwards, [npc.she] presses [npc.her] [npc.breasts+] together and starts giving you a titfuck."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of your [pc.cock+], [npc.name] guides it up to [npc.her] cleavage, and, sliding forwards, [npc.she] presses [npc.her] [npc.breasts+] together and starts giving you an enthusiastic titfuck."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grabbing hold of your [pc.cock+], [npc.name] pulls it up to [npc.her] cleavage, and, sliding forwards, [npc.she] presses [npc.her] [npc.breasts+] together and starts giving you a forceful titfuck."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of your [pc.cock+], [npc.name] guides it up to [npc.her] cleavage, and, sliding forwards, [npc.she] presses [npc.her] [npc.breasts+] together and starts giving you a titfuck."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of your [pc.cock+], [npc.name] guides it up to [npc.her] cleavage, and, sliding forwards, [npc.she] presses [npc.her] [npc.breasts+] together and starts giving you an enthusiastic titfuck."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out a happy little [pc.moan] in response, helping to push [npc.her] [npc.breasts] together as you encourage [npc.herHim] to keep going."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] in response, eagerly helping to push [npc.her] [npc.breasts] together as you enthusiastically encourage [npc.herHim] to keep going."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] in response, roughly pushing [npc.her] [npc.breasts] together as you demand that [npc.she] keeps on going."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] in response, eagerly helping to push [npc.her] [npc.breasts] together as you enthusiastically encourage [npc.herHim] to keep going."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan] in response, helping to push [npc.her] [npc.breasts] together as you meekly ask [npc.herHim] to keep going."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] in response, weakly trying to push [npc.herHim] away as you beg for [npc.herHim] to stop."));
						break;
					default:
						break;
				}
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Gently taking hold of your [pc.cock+], [npc.name] guides it up to what little cleavage [npc.she] has, and, sliding forwards,"
											+ " [npc.she] tries [npc.her] best to press [npc.her] [npc.breastSize] [npc.breasts] together in order to give you a titfuck."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of your [pc.cock+], [npc.name] guides it up to what little cleavage [npc.she] has, and, sliding forwards,"
											+ " [npc.she] tries [npc.her] best to press [npc.her] [npc.breastSize] [npc.breasts] together in order to give you an enthusiastic titfuck."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly grabbing hold of your [pc.cock+], [npc.name] guides it up to what little cleavage [npc.she] has, and, sliding forwards,"
											+ " [npc.she] tries [npc.her] best to press [npc.her] [npc.breastSize] [npc.breasts] together in order to give you a forceful titfuck."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Taking hold of your [pc.cock+], [npc.name] guides it up to what little cleavage [npc.she] has, and, sliding forwards,"
											+ " [npc.she] tries [npc.her] best to press [npc.her] [npc.breastSize] [npc.breasts] together in order to give you a titfuck."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of your [pc.cock+], [npc.name] guides it up to what little cleavage [npc.she] has, and, sliding forwards,"
											+ " [npc.she] tries [npc.her] best to press [npc.her] [npc.breastSize] [npc.breasts] together in order to give you an enthusiastic titfuck."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out a happy little [pc.moan] in response, gently thrusting into [npc.her] chest as you encourage [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, desperately thrusting into [npc.her] chest as you enthusiastically encourage [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, roughly thrusting into [npc.her] chest as you demand that [npc.she] keeps on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, eagerly thrusting into [npc.her] chest as you enthusiastically encourage [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan] in response, thrusting into [npc.her] chest as you meekly ask [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, weakly trying to push [npc.herHim] away as you beg for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Gently taking hold of your [pc.cock+], [npc.name] guides it up to [npc.her] flat chest, and, sliding forwards, [npc.she] grinds [npc.her] torso against your [pc.cock+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of your [pc.cock+], [npc.name] guides it up to [npc.her] flat chest, and, sliding forwards, [npc.she] enthusiastically grinds [npc.her] torso against your [pc.cock+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly grabbing hold of your [pc.cock+], [npc.name] guides it up to [npc.her] flat chest, and, sliding forwards, [npc.she] forcefully grinds [npc.her] torso against your [pc.cock+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Taking hold of your [pc.cock+], [npc.name] guides it up to [npc.her] flat chest, and, sliding forwards, [npc.she] grinds [npc.her] torso against your [pc.cock+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of your [pc.cock+], [npc.name] guides it up to [npc.her] flat chest, and, sliding forwards, [npc.she] enthusiastically grinds [npc.her] torso against your [pc.cock+]."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out a happy little [pc.moan] in response, gently thrusting into [npc.her] chest as you encourage [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, desperately thrusting into [npc.her] chest as you enthusiastically encourage [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, roughly thrusting into [npc.her] chest as you demand that [npc.she] keeps on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, eagerly thrusting into [npc.her] chest as you enthusiastically encourage [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan] in response, thrusting into [npc.her] chest as you meekly ask [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, weakly trying to push [npc.herHim] away as you beg for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_PERFORMING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Gentle paizuri";
			} else {
				return "Gentle naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Gently pleasure [pc.name]'s [pc.cock+] with your [npc.breasts+].";
			} else {
				return "Gently pleasure [pc.name]'s [pc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to push [npc.her] [npc.breasts+] together around your [pc.cock+], [npc.name] gently raises and lowers [npc.her] torso, softly [npc.moaning] as [npc.she] helps you to fuck [npc.her] cleavage.",
						"Gently pressing [npc.her] [npc.breasts+] together around your [pc.cock+], [npc.name] slowly lifts them up and down, helping you to fuck [npc.her] cleavage as [npc.she] [npc.moansVerb] in delight.",
						"Letting out a soft [npc.moan], [npc.name] presses [npc.her] [npc.breasts] together, enveloping your [pc.cock+] in [npc.her] pillowy mounds as [npc.she] gives you a loving titfuck."));
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to try and push [npc.her] [npc.breastSize] [npc.breasts+] together around your [pc.cock+], [npc.name] gently raises and lowers [npc.her] torso,"
									+ " softly [npc.moaning] as [npc.she] helps you to fuck what little cleavage [npc.she] has.",
							"Gently trying to press [npc.her] [npc.breastSize] [npc.breasts+] together around your [pc.cock+], [npc.name] uses [npc.her] [npc.fingers+] to help create a structure for you to thrust into,"
									+ " before [npc.moaning] in delight as [npc.she] lifts [npc.herself] up and down to get you to fuck [npc.her] small cleavage.",
							"Letting out a soft [npc.moan], [npc.name] tries to press [npc.her] [npc.breastSize] [npc.breasts] together, using [npc.her] [npc.fingers+] to help to envelop your [pc.cock+] as [npc.she] gives you a loving titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap [npc.her] [npc.fingers+] around your [pc.cock+], [npc.name] gently raises and lowers [npc.her] torso, softly [npc.moaning] as [npc.she] thrusts out [npc.her] chest and grinds against you.",
							"Gently wrapping [npc.her] [npc.fingers+] around your [pc.cock+], [npc.name] lifts [npc.herself] up and down, grinding [npc.her] chest against you as [npc.she] imitates giving you a titfuck.",
							"Letting out a soft [npc.moan], [npc.name] wraps [npc.her] [npc.fingers+] around your [pc.cock+] and thrusts [npc.her] chest out, before lifting [npc.herself] up and down to give you an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_PERFORMING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Pleasure [pc.name]'s [pc.cock+] with your [npc.breasts+].";
			} else {
				return "Pleasure [pc.name]'s [pc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to eagerly push [npc.her] [npc.breasts+] together around your [pc.cock+], [npc.name] rapidly starts bucking [npc.her] torso up and down, [npc.moaning+] as [npc.she] helps you to fuck [npc.her] cleavage.",
						"Eagerly pressing [npc.her] [npc.breasts+] together around your [pc.cock+], [npc.name] rapidly starts lifting them up and down, helping you to fuck [npc.her] cleavage as [npc.she] [npc.moansVerb] in delight.",
						"Letting out [npc.a_moan+], [npc.name] eagerly presses [npc.her] [npc.breasts] together, enveloping your [pc.cock+] in [npc.her] pillowy mounds as [npc.she] gives you an enthusiastic titfuck."));
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to desperately try and push [npc.her] [npc.breastSize] [npc.breasts+] together around your [pc.cock+], [npc.name] rapidly starts bucking [npc.her] torso up and down,"
									+ " [npc.moaning+] as [npc.she] enthusiastically helps you to fuck what little cleavage [npc.she] has.",
							"Eagerly trying to press [npc.her] [npc.breastSize] [npc.breasts+] together around your [pc.cock+], [npc.name] uses [npc.her] [npc.fingers+] to help create a structure for you to thrust into,"
									+ " before [npc.moaning] in delight as [npc.she] rapidly lifts [npc.herself] up and down to get you to fuck [npc.her] small cleavage.",
							"Letting out [npc.a_moan+], [npc.name] desperately tries to press [npc.her] [npc.breastSize] [npc.breasts] together,"
									+ " using [npc.her] [npc.fingers+] to help to envelop your [pc.cock+] as [npc.she] gives you an enthusiastic titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap [npc.her] [npc.fingers+] around your [pc.cock+], [npc.name] rapidly starts bucking [npc.her] torso up and down, [npc.moaning+] as [npc.she] thrusts [npc.her] chest out and eagerly grinds against you.",
							"Eagerly wrapping [npc.her] [npc.fingers+] around your [pc.cock+], [npc.name] rapidly lifts [npc.herself] up and down, grinding [npc.her] chest against you as [npc.she] imitates giving you a titfuck.",
							"Letting out [npc.a_moan+], [npc.name] wraps [npc.her] [npc.fingers+] around your [pc.cock+] and eagerly thrusts [npc.her] chest out, before lifting [npc.herself] up and down to give you an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_PERFORMING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Rough paizuri";
			} else {
				return "Rough naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Roughly pleasure [pc.name]'s [pc.cock+] with your [npc.breasts+].";
			} else {
				return "Roughly pleasure [pc.name]'s [pc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to desperately push [npc.her] [npc.breasts+] together around your [pc.cock+], [npc.name] starts roughly bucking [npc.her] torso up and down, [npc.moaning+] as [npc.she] forces you to fuck [npc.her] cleavage.",
						"Roughly pressing [npc.her] [npc.breasts+] together around your [pc.cock+], [npc.name] starts rapidly lifting them up and down, forcing you to fuck [npc.her] cleavage as [npc.she] [npc.moansVerb] in delight.",
						"Letting out [npc.a_moan+], [npc.name] roughly presses [npc.her] [npc.breasts] together, enveloping your [pc.cock+] in [npc.her] pillowy mounds as [npc.she] gives you a forceful titfuck."));
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to desperately try and push [npc.her] [npc.breastSize] [npc.breasts+] together around your [pc.cock+], [npc.name] starts rapidly bucking [npc.her] torso up and down,"
									+ " [npc.moaning+] as [npc.she] roughly forces you to fuck what little cleavage [npc.she] has.",
							"Roughly trying to press [npc.her] [npc.breastSize] [npc.breasts+] together around your [pc.cock+], [npc.name] uses [npc.her] [npc.fingers+] to help create a structure for you to thrust into,"
									+ " before [npc.moaning] in delight as [npc.she] rapidly lifts [npc.herself] up and down to force you to fuck [npc.her] small cleavage.",
							"Letting out [npc.a_moan+], [npc.name] roughly tries to press [npc.her] [npc.breastSize] [npc.breasts] together,"
									+ " using [npc.her] [npc.fingers+] to help to envelop your [pc.cock+] as [npc.she] gives you a forceful titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to roughly wrap [npc.her] [npc.fingers+] around your [pc.cock+], [npc.name] rapidly starts bucking [npc.her] torso up and down,"
									+ " [npc.moaning+] as [npc.she] thrusts [npc.her] chest out and forcefully grinds against you.",
							"Roughly wrapping [npc.her] [npc.fingers+] around your [pc.cock+], [npc.name] rapidly lifts [npc.herself] up and down, forcefully grinding [npc.her] chest against you as [npc.she] imitates giving you a titfuck.",
							"Letting out [npc.a_moan+], [npc.name] roughly wraps [npc.her] [npc.fingers+] around your [pc.cock+] and forcefully thrusts [npc.her] chest out, before lifting [npc.herself] up and down to give you an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_PERFORMING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Pleasure [pc.name]'s [pc.cock+] with your [npc.breasts+].";
			} else {
				return "Pleasure [pc.name]'s [pc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to push [npc.her] [npc.breasts+] together around your [pc.cock+], [npc.name] starts bucking [npc.her] torso up and down, [npc.moaning+] as [npc.she] helps you to fuck [npc.her] cleavage.",
						"Pressing [npc.her] [npc.breasts+] together around your [pc.cock+], [npc.name] starts lifting them up and down, helping you to fuck [npc.her] cleavage as [npc.she] [npc.moansVerb] in delight.",
						"Letting out [npc.a_moan+], [npc.name] presses [npc.her] [npc.breasts] together, enveloping your [pc.cock+] in [npc.her] pillowy mounds as [npc.she] gives you a titfuck."));
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to try and push [npc.her] [npc.breastSize] [npc.breasts+] together around your [pc.cock+], [npc.name] starts bucking [npc.her] torso up and down,"
									+ " [npc.moaning+] as [npc.she] helps you to fuck what little cleavage [npc.she] has.",
							"Trying to press [npc.her] [npc.breastSize] [npc.breasts+] together around your [pc.cock+], [npc.name] uses [npc.her] [npc.fingers+] to help create a structure for you to thrust into,"
									+ " before [npc.moaning] in delight as [npc.she] lifts [npc.herself] up and down to get you to fuck [npc.her] small cleavage.",
							"Letting out [npc.a_moan+], [npc.name] tries to press [npc.her] [npc.breastSize] [npc.breasts] together, using [npc.her] [npc.fingers+] to help to envelop your [pc.cock+] as [npc.she] gives you a titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap [npc.her] [npc.fingers+] around your [pc.cock+], [npc.name] starts bucking [npc.her] torso up and down, [npc.moaning+] as [npc.she] thrusts [npc.her] chest out and grinds against you.",
							"Wrapping [npc.her] [npc.fingers+] around your [pc.cock+], [npc.name] lifts [npc.herself] up and down, grinding [npc.her] chest against you as [npc.she] imitates giving you a titfuck.",
							"Letting out [npc.a_moan+], [npc.name] wraps [npc.her] [npc.fingers+] around your [pc.cock+] and thrusts [npc.her] chest out, before lifting [npc.herself] up and down to give you an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_PERFORMING_COCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Eager paizuri";
			} else {
				return "Eager naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Eagerly pleasure [pc.name]'s [pc.cock+] with your [npc.breasts+].";
			} else {
				return "Eagerly pleasure [pc.name]'s [pc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to eagerly push [npc.her] [npc.breasts+] together around your [pc.cock+], [npc.name] rapidly starts bucking [npc.her] torso up and down, [npc.moaning+] as [npc.she] helps you to fuck [npc.her] cleavage.",
						"Eagerly pressing [npc.her] [npc.breasts+] together around your [pc.cock+], [npc.name] rapidly starts lifting them up and down, helping you to fuck [npc.her] cleavage as [npc.she] [npc.moansVerb] in delight.",
						"Letting out [npc.a_moan+], [npc.name] eagerly presses [npc.her] [npc.breasts] together, enveloping your [pc.cock+] in [npc.her] pillowy mounds as [npc.she] gives you an enthusiastic titfuck."));
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to desperately try and push [npc.her] [npc.breastSize] [npc.breasts+] together around your [pc.cock+], [npc.name] rapidly starts bucking [npc.her] torso up and down,"
									+ " [npc.moaning+] as [npc.she] enthusiastically helps you to fuck what little cleavage [npc.she] has.",
							"Eagerly trying to press [npc.her] [npc.breastSize] [npc.breasts+] together around your [pc.cock+], [npc.name] uses [npc.her] [npc.fingers+] to help create a structure for you to thrust into,"
									+ " before [npc.moaning] in delight as [npc.she] rapidly lifts [npc.herself] up and down to get you to fuck [npc.her] small cleavage.",
							"Letting out [npc.a_moan+], [npc.name] desperately tries to press [npc.her] [npc.breastSize] [npc.breasts] together,"
									+ " using [npc.her] [npc.fingers+] to help to envelop your [pc.cock+] as [npc.she] gives you an enthusiastic titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap [npc.her] [npc.fingers+] around your [pc.cock+], [npc.name] rapidly starts bucking [npc.her] torso up and down, [npc.moaning+] as [npc.she] thrusts [npc.her] chest out and eagerly grinds against you.",
							"Eagerly wrapping [npc.her] [npc.fingers+] around your [pc.cock+], [npc.name] rapidly lifts [npc.herself] up and down, grinding [npc.her] chest against you as [npc.she] imitates giving you a titfuck.",
							"Letting out [npc.a_moan+], [npc.name] wraps [npc.her] [npc.fingers+] around your [pc.cock+] and eagerly thrusts [npc.her] chest out, before lifting [npc.herself] up and down to give you an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Resist paizuri";
			} else {
				return "Resist naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Try and pull your [npc.breasts+] away from [pc.name]'s [pc.cock+].";
			} else {
				return "Try and pull your chest away from [pc.name]'s [pc.cock+].";
			}
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] lets out [npc.moan+] as [npc.she] tries to pull [npc.her] [npc.breasts+] away from your [pc.cock+], before begging for you to leave [npc.herHim] alone.",
						"With [npc.a_moan+], [npc.name] weakly tries to pull away from you, sobbing in distress as your [pc.cock+] continues to thrust up between [npc.her] [npc.breasts+].",
						"Letting out [npc.a_moan+], [npc.name] tries to push you away from [npc.herHim], tears running down [npc.her] cheeks as you continue thrusting your [pc.cock+] into [npc.her] cleavage."));
				
			} else {
				if(Sex.getActivePartner().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] lets out [npc.moan+] as [npc.she] tries to pull [npc.her] [npc.breastSize] [npc.breasts] away from your [pc.cock+], before begging for you to leave [npc.herHim] alone.",
							"With [npc.a_moan+], [npc.name] weakly tries to pull away from you, sobbing in distress as your [pc.cock+] continues to thrust up between [npc.her] [npc.breastSize] [npc.breasts+].",
							"Letting out [npc.a_moan+], [npc.name] tries to push you away from [npc.herHim], tears running down [npc.her] cheeks as you continue thrusting your [pc.cock+] into [npc.her] small cleavage."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] lets out [npc.moan+] as [npc.she] tries to pull [npc.her] chest away from your [pc.cock+], before begging for you to leave [npc.herHim] alone.",
							"With [npc.a_moan+], [npc.name] weakly tries to pull away from you, sobbing in distress as your [pc.cock+] continues to grind up against [npc.her] chest.",
							"Letting out [npc.a_moan+], [npc.name] tries to push you away from [npc.herHim], tears running down [npc.her] cheeks as you continue thrusting your [pc.cock+] against [npc.her] chest."));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isDom(Main.game.getPlayer()); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Stop paizuri";
			} else {
				return "Stop naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getActivePartner().isBreastFuckablePaizuri()) {
				return "Push [pc.name]'s [pc.cock] away from your [npc.breasts+].";
			} else {
				return "Push [pc.name]'s [pc.cock] away from your chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getActivePartner().hasBreasts()) {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly pushes you away from [npc.herHim], and, in a menacing tone, orders you to stop fucking [npc.her] [npc.breasts+].",
								"With a menacing growl, [npc.name] roughly pushes you away, and orders you to stop fucking [npc.her] [npc.breasts+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] pushes you away from [npc.herHim], and tells you to stop fucking [npc.her] [npc.breasts+].",
								"With one last [npc.moan], [npc.name] pushes you away, and tells you to stop fucking [npc.her] [npc.breasts+]."));
						break;
				}
			} else {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly pushes you away from [npc.herHim], and, in a menacing tone, orders you to stop grinding against [npc.her] chest.",
								"With a menacing growl, [npc.name] roughly pushes you away, and orders you to stop grinding against [npc.her] chest."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] pushes you away from [npc.herHim], and tells you to stop grinding against [npc.her] chest.",
								"With one last [npc.moan], [npc.name] pushes you away, and tells you to stop grinding against [npc.her] chest."));
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You continue struggling against [npc.herHim], [pc.moaning+] as you beg [npc.herHim] to leave you alone.",
							" With [pc.a_moan+], you beg [npc.herHim] to leave you alone, tears welling up in your [pc.eyes] as you weakly try to push [npc.herHim] away."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You can't help but let out [pc.a_moan+], betraying your desire for more of [npc.her] attention.",
							" With [pc.a_moan+], you beg for [npc.herHim] to keep on using you."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
