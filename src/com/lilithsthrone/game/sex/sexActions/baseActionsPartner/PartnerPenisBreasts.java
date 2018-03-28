package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.84
 * @version 0.1.84
 * @author Innoxia
 */
public class PartnerPenisBreasts {
	
	public static final SexAction PARTNER_FORCE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.PITCHER) {
		@Override
		public String getActionTitle() {
			return "Thrust into mouth";
		}

		@Override
		public String getActionDescription() {
			return "Push forwards and force the [npc.cockHead] of your cock into [pc.name]'s mouth.";
		}

		@Override
		public ArousalIncrease getArousalGainTarget()
		{
			if(Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING)
			{
				return ArousalIncrease.ZERO_NONE;
			}
			else
			{
				return ArousalIncrease.THREE_NORMAL;
			}
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING
					&& Sex.getActivePartner().getPenisRawSizeValue()>=6
					&& Sex.isOrificeFree(Main.game.getPlayer(), OrificeType.MOUTH)
					&& Main.game.getPlayer().isBreastFuckablePaizuri()
					&& Sex.getPosition() != SexPositionType.SIXTY_NINE;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently thrusting forwards between your [pc.breasts], [npc.name] pushes [npc.her] [npc.cock+] all the way up to your mouth and forces the [npc.cockHead] past your [pc.lips].",
							"Slowly pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between your [pc.breasts+], pushing all the way until [npc.her] [npc.cockHead] presses past your [pc.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly thrusting forwards between your [pc.breasts], [npc.name] pushes [npc.her] [npc.cock+] all the way up to your mouth and forces the [npc.cockHead] past your [pc.lips].",
							"Greedily pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between your [pc.breasts+], pushing all the way until [npc.her] [npc.cockHead] presses past your [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly thrusting forwards between your [pc.breasts], [npc.name] slams [npc.her] [npc.cock+] up against your mouth and forces the [npc.cockHead] past your [pc.lips].",
							"Violently slamming [npc.her] [npc.hips] forwards, [npc.name] thrusts [npc.her] [npc.cock+] between your [pc.breasts+], pushing all the way until [npc.her] [npc.cockHead] rams past your [pc.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Thrusting forwards between your [pc.breasts], [npc.name] pushes [npc.her] [npc.cock+] all the way up to your mouth and forces the [npc.cockHead] past your [pc.lips].",
							"Pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between your [pc.breasts+], pushing all the way until [npc.her] [npc.cockHead] presses past your [pc.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly thrusting forwards between your [pc.breasts], [npc.name] pushes [npc.her] [npc.cock+] all the way up to your mouth and forces the [npc.cockHead] past your [pc.lips].",
							"Greedily pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between your [pc.breasts+], pushing all the way until [npc.her] [npc.cockHead] presses past your [pc.lips+]."));
					break;
				case SUB_RESISTING:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You grin at [npc.her] enthusiasm, and, opening your mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a loving suck, you then draw back, but not before planting a kiss on the very tip.",
							" You open your mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a hot, wet suck before drawing back to deliver a soft kiss to the very tip."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a happy [pc.moan], and, eagerly opening your mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a loving suck, you then draw back, but not before planting a wet kiss on the very tip.",
							" You eagerly open your mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a hot, wet suck before drawing back to deliver a passionate kiss to the very tip."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], and, quickly opening your mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a forceful suck, you then draw back, but not before planting a rough kiss on the very tip.",
							" You quickly open your mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a forceful suck before drawing back to deliver a rough kiss to the very tip."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a little [pc.moan], and, opening your mouth to give the [npc.cockHead] of [npc.her] [npc.cock] an obedient suck, you then draw back, but not before planting a quick kiss on the very tip.",
							" You open your mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] an obediently suck before drawing back to deliver a quick kiss to the very tip."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a happy [pc.moan], and, eagerly opening your mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a loving suck, you then draw back, but not before planting a wet kiss on the very tip.",
							" You eagerly open your mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a hot, wet suck before drawing back to deliver a passionate kiss to the very tip."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], and, trying to pull your mouth away from the [npc.cockHead] of [npc.her] [npc.cock], you desperately plead for [npc.herHim] to leave you alone.",
							" You jerk your head back, trying to push [npc.her] [npc.cock+] away from your mouth as tears start to well up in your [pc.eyes]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getActivePartner(), Main.game.getPlayer(), PenetrationType.PENIS, OrificeType.MOUTH);
		}
		
	};
	
	public static final SexAction PLAYER_TAKE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.CATCHER) {
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Paizuri into mouth";
			} else {
				return "Naizuri into mouth";
			}
		}

		@Override
		public String getActionDescription() {
			return "Push forwards and take the [npc.cockHead] of [npc.name]'s [npc.cock+] into your mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING
					&& Sex.getActivePartner().getPenisRawSizeValue()>=6
					&& Sex.isOrificeFree(Main.game.getPlayer(), OrificeType.MOUTH)
					&& Main.game.getPlayer().isBreastFuckablePaizuri()
					&& Sex.getPosition() != SexPositionType.SIXTY_NINE;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pushing your [pc.face] down towards [npc.name]'s [npc.cock] as it slides up between your [pc.breasts], you part your [pc.lips+] and take the [npc.cockHead] into your mouth.",
							"Slowly pushing your [pc.face] down, you take the [npc.cockHead] of [npc.name]'s [npc.cock+] into your mouth as [npc.she] thrusts up between your [pc.breasts+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing your [pc.face] down towards [npc.name]'s [npc.cock] as it slides up between your [pc.breasts], you greedily part your [pc.lips+] and take the [npc.cockHead] into your mouth.",
							"Eagerly pushing your [pc.face] down, you greedily take the [npc.cockHead] of [npc.name]'s [npc.cock+] into your mouth as [npc.she] thrusts up between your [pc.breasts+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] down towards [npc.name]'s [npc.cock] as it slides up between your [pc.breasts], you greedily part your [pc.lips+] and take the [npc.cockHead] into your mouth.",
							"Pushing your [pc.face] down, you greedily takes the [npc.cockHead] of [npc.name]'s [npc.cock+] into your mouth as [npc.she] thrusts up between your [pc.breasts+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] down towards [npc.name]'s [npc.cock] as it slides up between your [pc.breasts], you part your [pc.lips+] and take the [npc.cockHead] into your mouth.",
							"Pushing your [pc.face] down, you take the [npc.cockHead] of [npc.name]'s [npc.cock+] into your mouth as [npc.she] thrusts up between your [pc.breasts+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing your [pc.face] down towards [npc.name]'s [npc.cock] as it slides up between your [pc.breasts], you greedily part your [pc.lips+] and take the [npc.cockHead] into your mouth.",
							"Eagerly pushing your [pc.face] down, you greedily take the [npc.cockHead] of [npc.name]'s [npc.cock+] into your mouth as [npc.she] thrusts up between your [pc.breasts+]."));
					break;
				case SUB_RESISTING:
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] smiles at your enthusiasm, and, gently pushing [npc.her] [npc.cock] into your mouth,"
									+ " [npc.she] lets you suck and kiss the [npc.cockHead] for a moment, before pulling back and continuing to fuck your [pc.breasts+].",
							" [npc.Name] slowly pushes [npc.her] [npc.cock+] into your mouth, allowing you to give the [npc.cockHead] a hot, wet suck before drawing back and continuing to fuck your [pc.breasts+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] grins at [npc.her] enthusiasm, and, eagerly pushing [npc.her] [npc.cock] into your mouth,"
									+ " [npc.she] lets you suck and kiss the [npc.cockHead] for a moment, before pulling back and continuing to fuck your [pc.breasts+].",
							" [npc.Name] eagerly pushes [npc.her] [npc.cock+] into your mouth, allowing you to give the [npc.cockHead] a hot, wet suck before drawing back and continuing to fuck your [pc.breasts+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] grins at [npc.her] enthusiasm, and, roughly forcing [npc.her] [npc.cock] deeper into your mouth,"
									+ " [npc.she] allows you to suck and kiss the [npc.cockHead] for a moment, before pulling back and continuing to aggressively fuck your [pc.breasts+].",
							" [npc.Name] roughly forces [npc.her] [npc.cock+] into your mouth, allowing you to give the [npc.cockHead] a hot, wet suck before drawing back and continuing to aggressively fuck your [pc.breasts+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a little [npc.moan], and, pushing [npc.her] [npc.cock] into your mouth, [npc.she] gasps as you suck and kiss the [npc.cockHead] for a moment,"
									+ " before allowing [npc.herHim] to pull back and continue to fuck your [pc.breasts+].",
							" [npc.Name] pushes [npc.her] [npc.cock+] into your mouth, gasping as you give the [npc.cockHead] a hot, wet suck before allowing [npc.herHim] to pull back and continue to fuck your [pc.breasts+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [pc.a_moan+], and, eagerly pushing [npc.her] [npc.cock] into your mouth, [npc.she] gasps as you suck and kiss the [npc.cockHead] for a moment,"
									+ " before allowing [npc.herHim] to pull back and continue to happily fuck your [pc.breasts+].",
							" [npc.Name] eagerly pushes [npc.her] [npc.cock+] into your mouth, gasping as you give the [npc.cockHead] a hot, wet suck before allowing [npc.herHim] to pull back and continue to happily fuck your [pc.breasts+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [pc.a_moan+], and, trying to pull [npc.her] [npc.cock] away from your mouth, [npc.she] desperately pleads for you to leave [npc.herHim] alone.",
							" [npc.Name] tries to pull [npc.her] [npc.hips] back, but you hold [npc.herHim] in place, sucking on the [npc.cockHead] of [npc.her] [npc.cock+] for a moment before finally letting [npc.herHim] pull back."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getActivePartner(), Main.game.getPlayer(), PenetrationType.PENIS, OrificeType.MOUTH);
		}
		
	};
	
	
	public static final SexAction PARTNER_FUCKING_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.PITCHER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.ANUS) != PenetrationType.PENIS
					&& Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA) != PenetrationType.PENIS;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Start paizuri";
			} else {
				return "Start naizuri";
			}
		}

		@Override
		public ArousalIncrease getArousalGainTarget()
		{
			if(Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING)
			{
				return ArousalIncrease.ZERO_NONE;
			}
			else
			{
				return ArousalIncrease.FOUR_HIGH;
			}
		}
		
		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Sink your [npc.cock+] between [pc.name]'s [pc.breasts+] and start fucking them.";
			} else {
				return "Start grinding your [npc.cock+] over [pc.name]'s chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of your [pc.breasts+], [npc.name] gently pushes them together, lining [npc.her] [npc.cock] up to your cleavage before sliding forwards and starting to fuck your [pc.breasts]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to greedily sink [npc.her] [npc.fingers] into your [pc.breasts+], [npc.name] eagerly pushes them together,"
										+ " lining [npc.her] [npc.cock] up to your cleavage before sliding forwards and starting to enthusiastically fuck your [pc.breasts]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to roughly sink [npc.her] [npc.fingers] into your [pc.breasts+], [npc.name] forcefully pushes them together,"
										+ " lining [npc.her] [npc.cock] up to your cleavage before slamming forwards and starting to rapidly fuck your [pc.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of your [pc.breasts+], [npc.name] then pushes them together, lining [npc.her] [npc.cock] up to your cleavage before sliding forwards and starting to fuck your [pc.breasts]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to greedily sink [npc.her] [npc.fingers] into your [pc.breasts+], [npc.name] eagerly pushes them together,"
										+ " lining [npc.her] [npc.cock] up to your cleavage before sliding forwards and starting to enthusiastically fuck your [pc.breasts]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out a happy little [pc.moan] in response, reaching up to help push your [pc.breasts] together as you encourage [npc.herHim] to keep going."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] in response, quickly reaching up to help push your [pc.breasts] together as you happily encourage [npc.herHim] to keep going."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] in response, reaching up to forcefully press your [pc.breasts] together as you dominantly order [npc.herHim] to keep going."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] in response, quickly reaching up to help push your [pc.breasts] together as you happily encourage [npc.herHim] to keep going."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out a little [pc.moan] in response, before reaching up to help push your [pc.breasts] together as you encourage [npc.herHim] to keep going."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] in response, reaching up to weakly try and push [npc.herHim] away from your [pc.breasts] as you beg for [npc.herHim] to stop."));
						break;
					default:
						break;
				}
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to take hold of your [pc.breasts+], [npc.name] gently tries to push them together,"
											+ " lining [npc.her] [npc.cock] up to what little cleavage you have before sliding forwards and starting to grind down over your chest."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to squeeze and grope your [pc.breasts+], [npc.name] does [npc.her] best to try to push them together,"
											+ " lining [npc.her] [npc.cock] up to what little cleavage you have before sliding forwards and starting to eagerly grind down over your chest."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to roughly squeeze and grope your [pc.breasts+], [npc.name] does [npc.her] best to try to push them together,"
											+ " lining [npc.her] [npc.cock] up to what little cleavage you have before sliding forwards and starting to forcefully grind down over your chest."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to squeeze and grope your [pc.breasts+], [npc.name] does [npc.her] best to try to push them together,"
											+ " lining [npc.her] [npc.cock] up to what little cleavage you have before sliding forwards and starting to grind down over your chest."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to squeeze and grope your [pc.breasts+], [npc.name] does [npc.her] best to try to push them together,"
											+ " lining [npc.her] [npc.cock] up to what little cleavage you have before sliding forwards and starting to eagerly grind down over your chest."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out a happy little [pc.moan] in response, reaching up to try and help push your [pc.breastSize] [pc.breasts] together as you encourage [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, quickly reaching up to try and help push your [pc.breastSize] [pc.breasts] together as you happily encourage [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, reaching up to forcefully try and press your [pc.breastSize] [pc.breasts] together as you dominantly order [npc.herHim] to keep going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, quickly reaching up to try and help push your [pc.breastSize] [pc.breasts] together as you happily encourage [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out a little [pc.moan] in response, before reaching up to try and help push your [pc.breastSize] [pc.breasts] together as you encourage [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, reaching up to weakly try and push [npc.herHim] away from your [pc.breastSize] [pc.breasts] as you beg for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press [npc.her] [npc.hands] against your torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over your chest,"
											+ " before sliding forwards and starting to grind down against your body."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press [npc.her] [npc.hands] against your torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over your chest,"
											+ " before sliding forwards and starting to eagerly grind down against your body."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to roughly press [npc.her] [npc.hands] against your torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over your chest,"
											+ " before sliding forwards and starting to forcefully grind down against your body."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press [npc.her] [npc.hands] against your torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over your chest,"
											+ " before sliding forwards and starting to grind down against your body."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press [npc.her] [npc.hands] against your torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over your chest,"
											+ " before sliding forwards and starting to eagerly grind down against your body."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out a happy little [pc.moan] in response, pushing your chest out as you encourage [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, quickly pushing your chest out as you happily encourage [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, forcefully pushing your chest out as you dominantly order [npc.herHim] to keep going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, pushing your chest out as you happily encourage [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out a little [pc.moan] in response, pushing your chest out as you encourage [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" You let out [pc.a_moan+] in response, reaching up to weakly try and push [npc.herHim] away from you as you beg for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
				}
			}
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Gentle paizuri";
			} else {
				return "Gentle naizuri";
			}
		}

		@Override
		public ArousalIncrease getArousalGainTarget()
		{
			if(Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING)
			{
				return ArousalIncrease.ZERO_NONE;
			}
			else
			{
				return ArousalIncrease.FOUR_HIGH;
			}
		}
		
		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Gently fuck [pc.name]'s [pc.breasts+].";
			} else {
				return "Gently grind against [pc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] gently slides [npc.her] [npc.cock+] up into your cleavage, grinning as you enthusiastically press your [pc.breasts+] together while begging for [npc.herHim] to keep going.",
								"[npc.Name] slowly thrusts up between your [pc.breasts+], causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+].",
								"Thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out a soft [npc.moan] as you reach up and enthusiastically press your [pc.breasts+] together for [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] gently slides [npc.her] [npc.cock+] up into your cleavage, ignoring your desperate cries for [npc.herHim] to stop as you weakly try to push [npc.herHim] away.",
								"[npc.Name] slowly thrusts up between your [pc.breasts+], causing you to let out [pc.a_moan+] as tears start welling up in your [pc.eyes].",
								"Thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out a soft [npc.moan] as you weakly struggle and try to push [npc.herHim] away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] gently slides [npc.her] [npc.cock+] up into your cleavage, grinning as you press your [pc.breasts+] together and ask [npc.herHim] to keep going.",
								"[npc.Name] slowly thrusts up between your [pc.breasts+], causing you to let out a little [pc.moan] as you look down at [npc.her] [npc.cock+].",
								"Thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out a soft [npc.moan] as you reach up and press your [pc.breasts+] together for [npc.herHim]."));
						break;
				}
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have,"
											+ " grinning as you enthusiastically try to press your [pc.breastSize] [pc.breasts] together while begging for [npc.herHim] to keep going.",
									"[npc.Name] slowly thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+].",
									"Thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer,"
											+ " [npc.name] lets out a soft [npc.moan] as you reach up and desperately try to press your [pc.breastSize] [pc.breasts] together for [npc.herHim]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have, ignoring your desperate cries for [npc.herHim] to stop as you weakly try to push [npc.herHim] away.",
									"[npc.Name] slowly thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out [npc.a_moan+] as tears start welling up in your [pc.eyes].",
									"Thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer, [npc.name] lets out a soft [npc.moan] as you weakly struggle and try to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have, grinning as you try to press your [pc.breasts+] together and ask [npc.herHim] to keep going.",
									"[npc.Name] slowly thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out a little [pc.moan] as you look down at [npc.her] [npc.cock+].",
									"Thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer, [npc.name] lets out a soft [npc.moan] as you reach up and try to press your [pc.breasts+] together for [npc.herHim]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] against your chest, grinning as you enthusiastically beg for [npc.herHim] to keep going.",
									"[npc.Name] slowly thrusts down against your chest, causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+].",
									"Thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out a soft [npc.moan] as you desperately beg for [npc.herHim] not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] against your chest, ignoring your desperate cries for [npc.herHim] to stop as you weakly try to push [npc.herHim] away.",
									"[npc.Name] slowly thrusts down against your chest, causing you to let out [pc.a_moan+] as tears start welling up in your [pc.eyes].",
									"Thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out a soft [npc.moan] as you weakly struggle and try to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] against your chest, grinning as you softly ask for [npc.herHim] to keep going.",
									"[npc.Name] slowly thrusts down against your chest, causing you to let out a little [pc.moan] as you look down at [npc.her] [npc.cock+].",
									"Thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out a soft [npc.moan] as you meekly ask for [npc.herHim] to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public ArousalIncrease getArousalGainTarget()
		{
			if(Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING)
			{
				return ArousalIncrease.ZERO_NONE;
			}
			else
			{
				return ArousalIncrease.FOUR_HIGH;
			}
		}
		
		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Continue fucking your [pc.breasts+].";
			} else {
				return "Continue grinding against your chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into your cleavage, grinning as you enthusiastically press your [pc.breasts+] together while begging for [npc.herHim] to keep going.",
								"[npc.Name] enthusiastically thrusts up between your [pc.breasts+], causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+].",
								"Desperately thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you reach up and enthusiastically press your [pc.breasts+] together for [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into your cleavage, ignoring your desperate cries for [npc.herHim] to stop as you weakly try to push [npc.herHim] away.",
								"[npc.Name] enthusiastically thrusts up between your [pc.breasts+], causing you to let out [pc.a_moan+] as tears start welling up in your [pc.eyes].",
								"Desperately thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you weakly struggle and try to push [npc.herHim] away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into your cleavage, grinning as you press your [pc.breasts+] together and ask [npc.herHim] to keep going.",
								"[npc.Name] enthusiastically thrusts up between your [pc.breasts+], causing you to let out a little [pc.moan] as you look down at [npc.her] [npc.cock+].",
								"Desperately thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you reach up and press your [pc.breasts+] together for [npc.herHim]."));
						break;
				}
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have,"
											+ " grinning as you enthusiastically try to press your [pc.breastSize] [pc.breasts] together while begging for [npc.herHim] to keep going.",
									"[npc.Name] enthusiastically thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+].",
									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer,"
											+ " [npc.name] lets out [npc.a_moan+] as you reach up and desperately try to press your [pc.breastSize] [pc.breasts] together for [npc.herHim]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have, ignoring your desperate cries for [npc.herHim] to stop as you weakly try to push [npc.herHim] away.",
									"[npc.Name] enthusiastically thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out [npc.a_moan+] as tears start welling up in your [pc.eyes].",
									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer, [npc.name] lets out [npc.a_moan+] as you weakly struggle and try to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have, grinning as you try to press your [pc.breasts+] together and ask [npc.herHim] to keep going.",
									"[npc.Name] enthusiastically thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out a little [pc.moan] as you look down at [npc.her] [npc.cock+].",
									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer, [npc.name] lets out [npc.a_moan+] as you reach up and try to press your [pc.breasts+] together for [npc.herHim]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against your chest, grinning as you enthusiastically beg for [npc.herHim] to keep going.",
									"[npc.Name] enthusiastically thrusts down against your chest, causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+].",
									"Desperately thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you desperately beg for [npc.herHim] not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against your chest, ignoring your desperate cries for [npc.herHim] to stop as you weakly try to push [npc.herHim] away.",
									"[npc.Name] enthusiastically thrusts down against your chest, causing you to let out [pc.a_moan+] as tears start welling up in your [pc.eyes].",
									"Desperately thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you weakly struggle and try to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against your chest, grinning as you softly ask for [npc.herHim] to keep going.",
									"[npc.Name] enthusiastically thrusts down against your chest, causing you to let out a little [pc.moan] as you look down at [npc.her] [npc.cock+].",
									"Desperately thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you meekly ask for [npc.herHim] to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Rough paizuri";
			} else {
				return "Rough naizuri";
			}
		}

		@Override
		public ArousalIncrease getArousalGainTarget()
		{
			if(Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING)
			{
				return ArousalIncrease.ZERO_NONE;
			}
			else
			{
				return ArousalIncrease.FOUR_HIGH;
			}
		}
		
		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Roughly fuck your [pc.breasts+].";
			} else {
				return "Roughly grind against your chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] up into your cleavage, grinning as you enthusiastically press your [pc.breasts+] together while begging for [npc.herHim] to keep going.",
								"[npc.Name] forcefully thrusts up between your [pc.breasts+], causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+].",
								"Roughly thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you reach up and enthusiastically press your [pc.breasts+] together for [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] up into your cleavage, ignoring your desperate cries for [npc.herHim] to stop as you weakly try to push [npc.herHim] away.",
								"[npc.Name] forcefully thrusts up between your [pc.breasts+], causing you to let out [pc.a_moan+] as tears start welling up in your [pc.eyes].",
								"Roughly thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you weakly struggle and try to push [npc.herHim] away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] up into your cleavage, grinning as you press your [pc.breasts+] together and ask [npc.herHim] to keep going.",
								"[npc.Name] forcefully thrusts up between your [pc.breasts+], causing you to let out a little [pc.moan] as you look down at [npc.her] [npc.cock+].",
								"Roughly thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you reach up and press your [pc.breasts+] together for [npc.herHim]."));
						break;
				}
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] up into the small amount of cleavage that you have,"
											+ " grinning as you enthusiastically try to press your [pc.breastSize] [pc.breasts] together while begging for [npc.herHim] to keep going.",
									"[npc.Name] forcefully thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+].",
									"Roughly thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer,"
											+ " [npc.name] lets out [npc.a_moan+] as you reach up and desperately try to press your [pc.breastSize] [pc.breasts] together for [npc.herHim]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] up into the small amount of cleavage that you have, ignoring your desperate cries for [npc.herHim] to stop as you weakly try to push [npc.herHim] away.",
									"[npc.Name] forcefully thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out [npc.a_moan+] as tears start welling up in your [pc.eyes].",
									"Roughly thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer, [npc.name] lets out [npc.a_moan+] as you weakly struggle and try to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] up into the small amount of cleavage that you have, grinning as you try to press your [pc.breasts+] together and ask [npc.herHim] to keep going.",
									"[npc.Name] forcefully thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out a little [pc.moan] as you look down at [npc.her] [npc.cock+].",
									"Roughly thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer, [npc.name] lets out [npc.a_moan+] as you reach up and try to press your [pc.breasts+] together for [npc.herHim]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] against your chest, grinning as you enthusiastically beg for [npc.herHim] to keep going.",
									"[npc.Name] forcefully thrusts down against your chest, causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+].",
									"Roughly thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you desperately beg for [npc.herHim] not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] against your chest, ignoring your desperate cries for [npc.herHim] to stop as you weakly try to push [npc.herHim] away.",
									"[npc.Name] forcefully thrusts down against your chest, causing you to let out [pc.a_moan+] as tears start welling up in your [pc.eyes].",
									"Roughly thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you weakly struggle and try to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] against your chest, grinning as you softly ask for [npc.herHim] to keep going.",
									"[npc.Name] forcefully thrusts down against your chest, causing you to let out a little [pc.moan] as you look down at [npc.her] [npc.cock+].",
									"Roughly thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you meekly ask for [npc.herHim] to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Continue fucking [pc.name]'s [pc.breasts+].";
			} else {
				return "Continue grinding against [pc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) { 
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] slides [npc.her] [npc.cock+] up into your cleavage, grinning as you gently press your [pc.breasts+] together while begging for [npc.herHim] to keep going.",
								"[npc.Name] thrusts up between your [pc.breasts+], causing you to let out a soft [pc.moan] as you glance down at [npc.her] [npc.cock+] and bite your [pc.lip].",
								"Thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you reach up and gently press your [pc.breasts+] together for [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] slides [npc.her] [npc.cock+] up into your cleavage, letting out [npc.a_moan+] as you roughly press your [pc.breasts+] together and order [npc.herHim] to keep going.",
								"[npc.Name] thrusts up between your [pc.breasts+], causing you to let out [pc.a_moan+] before you regain your composure and roughly growl out that you're still in charge.",
								"Thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you forcefully push your [pc.breasts+] together and demand that [npc.she] pick up the pace."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] slides [npc.her] [npc.cock+] up into your cleavage, grinning as you eagerly press your [pc.breasts+] together and tell [npc.herHim] to keep going.",
								"[npc.Name] thrusts up between your [pc.breasts+], causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+] and bite your [pc.lip].",
								"Thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you reach up and eagerly press your [pc.breasts+] together for [npc.herHim]."));
						break;
				}
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have,"
											+ " grinning as you gently try to press your [pc.breastSize] [pc.breasts] together while begging for [npc.herHim] to keep going.",
									"[npc.Name] thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out [pc.a_moan+] as you glance down at [npc.her] [npc.cock+] and bite your [pc.lip].",
									"Thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer,"
											+ " [npc.name] lets out [npc.a_moan+] as you reach up and try to press your [pc.breastSize] [pc.breasts] together for [npc.herHim]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have,"
											+ " letting out [npc.a_moan+] as you roughly try to press your [pc.breastSize] [pc.breasts] together and order [npc.herHim] to keep going.",
									"[npc.Name] thrusts up between your [pc.breastSize] [pc.breasts],"
											+ " causing you to let out [pc.a_moan+] before you regain your composure and roughly growl out that you're still in charge.",
									"Thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer,"
											+ " [npc.name] lets out [npc.a_moan+] as you forcefully try to push your [pc.breasts+] together and demand that [npc.she] pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have,"
											+ " grinning as you gently try to press your [pc.breastSize] [pc.breasts] together while telling [npc.herHim] to keep going.",
									"[npc.Name] thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+] and bite your [pc.lip].",
									"Thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer,"
											+ " [npc.name] lets out [npc.a_moan+] as you reach up and eagerly try to press your [pc.breasts+] together for [npc.herHim]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] against your chest, grinning as you gently beg for you to keep going.",
									"[npc.Name] thrusts down against your chest, causing you to let out a soft [pc.moan] as you lustfully gaze down at [npc.her] [npc.cock+] and bite your [pc.lip].",
									"Thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you softly beg for you not to stop."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] against your chest, letting out [npc.a_moan+] as you roughly order [npc.herHim] to keep going.",
									"[npc.Name] thrusts down against your chest, causing you to let out [pc.a_moan+] before you regain your composure and roughly growl out that you're still in charge.",
									"Thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you forcefully demand that [npc.she] pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] against your chest, grinning as you eagerly [pc.moan] for [npc.herHim] to keep going.",
									"[npc.Name] thrusts down against your chest, causing you to let out [pc.a_moan+] as you look down at [npc.her] [npc.cock+] and bite your [pc.lip].",
									"Thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you eagerly beg for [npc.herHim] to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Eager paizuri";
			} else {
				return "Eager naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Eagerly fuck [pc.name]'s [pc.breasts+].";
			} else {
				return "Eagerly grind against [pc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) { 
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into your cleavage, grinning as you gently press your [pc.breasts+] together while begging for [npc.herHim] to keep going.",
								"[npc.Name] enthusiastically thrusts up between your [pc.breasts+], causing you to let out a soft [pc.moan] as you glance down at [npc.her] [npc.cock+] and bite your [pc.lip].",
								"Desperately thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you reach up and gently press your [pc.breasts+] together for [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into your cleavage, letting out [npc.a_moan+] as you roughly press your [pc.breasts+] together and order [npc.herHim] to keep going.",
								"[npc.Name] enthusiastically thrusts up between your [pc.breasts+], causing you to let out [pc.a_moan+] before you regain your composure and roughly growl out that you're still in charge.",
								"Desperately thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you forcefully push your [pc.breasts+] together and demand that [npc.she] pick up the pace."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into your cleavage, grinning as you eagerly press your [pc.breasts+] together and tell [npc.herHim] to keep going.",
								"[npc.Name] enthusiastically thrusts up between your [pc.breasts+], causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+] and bite your [pc.lip].",
								"Desperately thrusting [npc.her] [npc.cock+] between your cleavage, [npc.name] lets out [npc.a_moan+] as you reach up and eagerly press your [pc.breasts+] together for [npc.herHim]."));
						break;
				}
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have,"
											+ " grinning as you gently try to press your [pc.breastSize] [pc.breasts] together while begging for [npc.herHim] to keep going.",
									"[npc.Name] enthusiastically thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out [pc.a_moan+] as you glance down at [npc.her] [npc.cock+] and bite your [pc.lip].",
									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer,"
											+ " [npc.name] lets out [npc.a_moan+] as you reach up and try to press your [pc.breastSize] [pc.breasts] together for [npc.herHim]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have,"
											+ " letting out [npc.a_moan+] as you roughly try to press your [pc.breastSize] [pc.breasts] together and order [npc.herHim] to keep going.",
									"[npc.Name] enthusiastically thrusts up between your [pc.breastSize] [pc.breasts],"
											+ " causing you to let out [pc.a_moan+] before you regain your composure and roughly growl out that you're still in charge.",
									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer,"
											+ " [npc.name] lets out [npc.a_moan+] as you forcefully try to push your [pc.breasts+] together and demand that [npc.she] pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that you have,"
											+ " grinning as you gently try to press your [pc.breastSize] [pc.breasts] together while telling [npc.herHim] to keep going.",
									"[npc.Name] enthusiastically thrusts up between your [pc.breastSize] [pc.breasts], causing you to let out [pc.a_moan+] as you lustfully gaze down at [npc.her] [npc.cock+] and bite your [pc.lip].",
									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that you have on offer,"
											+ " [npc.name] lets out [npc.a_moan+] as you reach up and eagerly try to press your [pc.breasts+] together for [npc.herHim]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against your chest, grinning as you gently beg for you to keep going.",
									"[npc.Name] enthusiastically thrusts down against your chest, causing you to let out a soft [pc.moan] as you lustfully gaze down at [npc.her] [npc.cock+] and bite your [pc.lip].",
									"Desperately thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you softly beg for you not to stop."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against your chest, letting out [npc.a_moan+] as you roughly order [npc.herHim] to keep going.",
									"[npc.Name] enthusiastically thrusts down against your chest, causing you to let out [pc.a_moan+] before you regain your composure and roughly growl out that you're still in charge.",
									"Desperately thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you forcefully demand that [npc.she] pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against your chest, grinning as you eagerly [pc.moan] for [npc.herHim] to keep going.",
									"[npc.Name] enthusiastically thrusts down against your chest, causing you to let out [pc.a_moan+] as you look down at [npc.her] [npc.cock+] and bite your [pc.lip].",
									"Desperately thrusting [npc.her] [npc.cock+] against your chest, [npc.name] lets out [npc.a_moan+] as you eagerly beg for [npc.herHim] to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Resist paizuri";
			} else {
				return "Resist naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Try to pull your [npc.cock] away from [pc.name]'s [pc.breasts+].";
			} else {
				return "Try to pull your [npc.cock] away from [pc.name]'s chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of your cleavage, but you firmly hold [npc.herHim] in place,"
										+ " pressing your [pc.breasts+] together while gently reminding [npc.herHim] that you'll do whatever you want.",
								"[npc.Name] frantically tries to pull away from your [pc.breasts+], but you firmly hold [npc.herHim] in place, softly [pc.moaning] as you ignore [npc.her] desperate protests.",
								"Tears start to well up in [npc.name]'s [npc.eyes] as [npc.she] tries to pull out of your cleavage, but your grip is too strong,"
										+ " and you continue softly [pc.moaning] as you firmly force [npc.her] [npc.cock+] between your [pc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of your cleavage, but you roughly hold [npc.herHim] in place, pressing your [pc.breasts+] together while growling that you'll use [npc.herHim] however you want.",
								"[npc.Name] frantically tries to pull away from your [pc.breasts+], but you roughly hold [npc.herHim] in place, [npc.moaning+] as you ignore [npc.her] futile protests.",
								"Tears start to well up in [npc.name]'s [npc.eyes] as [npc.she] tries to pull out of your cleavage, but your grip is too strong,"
										+ " and you continue [pc.moaning+] as you roughly force [npc.name]'s [npc.cock+] between your [pc.breasts+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of your cleavage, but you firmly hold [npc.herHim] in place,"
										+ " pressing your [pc.breasts+] together while [pc.moaning] that you'll do whatever you want.",
								"[npc.Name] frantically tries to pull away from your [pc.breasts+], but you firmly hold [npc.herHim] in place, [pc.moaning+] as you ignore [npc.her] futile protests.",
								"Tears start to well up in [npc.name]'s [npc.eyes] as [npc.she] tries to pull out of your cleavage, but your grip is too strong,"
										+ " and you continue [pc.moaning+] as you eagerly force [npc.name]'s [npc.cock+] between your [pc.breasts+]."));
						break;
				}
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of the small amount of cleavage that you have, but you firmly hold [npc.herHim] in place,"
											+ " trying to press your [pc.breasts+] together while gently reminding [npc.herHim] that you'll do whatever you want.",
									"[npc.Name] frantically tries to pull away from your [pc.breastSize] [pc.breasts], but you firmly hold [npc.herHim] in place, softly [pc.moaning] as you ignore [npc.her] desperate protests.",
									"Tears start to well up in [npc.name]'s [npc.eyes] as [npc.she] tries to pull out of the tiny amount of cleavage that you have on offer, but your grip is too strong,"
											+ " and you continue softly [pc.moaning] as you firmly force [npc.name]'s [npc.cock+] between your [pc.breasts+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of the small amount of cleavage that you have, but you roughly hold [npc.herHim] in place,"
											+ " trying to press your [pc.breasts+] together while growling that you'll use [npc.herHim] however you want.",
									"[npc.Name] frantically tries to pull away from your [pc.breastSize] [pc.breasts], but you roughly hold [npc.herHim] in place, [npc.moaning+] as you ignore [npc.her] futile protests.",
									"Tears start to well up in [npc.name]'s [npc.eyes] as [npc.she] tries to pull out of the tiny amount of cleavage that you have on offer, but your grip is too strong,"
											+ " and you continue [pc.moaning+] as you roughly force [npc.name]'s [npc.cock+] between your [pc.breasts+]."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of the small amount of cleavage that you have, but you firmly hold [npc.herHim] in place,"
											+ " trying to press your [pc.breasts+] together while [pc.moaning] you that you'll do whatever you want.",
									"[npc.Name] frantically tries to pull away from your [pc.breastSize] [pc.breasts], but you firmly hold [npc.herHim] in place, [npc.moaning+] as you ignore [npc.her] futile protests.",
									"Tears start to well up in [npc.name]'s [npc.eyes] as [npc.she] tries to pull out of the tiny amount of cleavage that you have on offer, but your grip is too strong,"
											+ " and you continue [pc.moaning+] as you eagerly force [npc.name]'s [npc.cock+] between your [pc.breasts+]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] away from your chest, but you firmly hold [npc.herHim] in place,"
											+ " grinding against [npc.herHim] as you gently [pc.moanVerb] that you'll do whatever you want.",
									"[npc.Name] frantically tries to pull away from your chest, but you firmly hold [npc.herHim] in place, softly [pc.moaning] as you ignore [npc.her] desperate protests.",
									"Tears start to well up in [npc.name]'s [npc.eyes] as [npc.she] tries to pull away from you, but your grip is too strong,"
											+ " and you continue softly [pc.moaning] as you firmly force [npc.her] [npc.cock+] against your chest."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] away from your chest, but you roughly hold [npc.herHim] in place, forcefully grinding against [npc.herHim] as you growl out that you'll do whatever you want.",
									"[npc.Name] frantically tries to pull away from your chest, but you roughly hold [npc.herHim] in place, [pc.moaning+] as you ignore [npc.her] futile protests.",
									"Tears start to well up in [npc.name]'s [npc.eyes] as [npc.she] tries to pull away from [npc.name], but your grip is too strong,"
											+ " and you continue [pc.moaning+] as you roughly force [npc.name]'s [npc.cock+] against your chest."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] away from your chest, but you firmly hold [npc.herHim] in place, grinding against [npc.herHim] as you [pc.moanVerb] that you'll do whatever you want.",
									"[npc.Name] frantically tries to pull away from your chest, but you firmly hold [npc.herHim] in place, [pc.moaning+] as you ignore [npc.her] futile protests.",
									"Tears start to well up in [npc.name]'s [npc.eyes] as [npc.she] tries to pull away from you, but your grip is too strong,"
											+ " and you continue [pc.moaning+] as you eagerly force [npc.her] [npc.cock+] against your chest."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.PITCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual(); // Player can only stop if they're in charge (otherwise, this is the partner fucking themselves on the player's cock).
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Stop paizuri";
			} else {
				return "Stop naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Pull your [npc.cock+] away from [pc.name]'s [pc.breasts+] and stop fucking them.";
			} else {
				return "Pull your [npc.cock+] away from [pc.name]'s chest and stop grinding against [pc.herHim].";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pushing you away, [npc.name] pulls [npc.her] [npc.cock+] out from your cleavage and tells you that [npc.she]'s had enough of fucking your [pc.breasts+].",
								"Roughly pulling [npc.her] [npc.cock+] out from your cleavage, [npc.name] tells you that [npc.she]'s had enough of fucking your [pc.breasts+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] pulls [npc.her] [npc.cock+] out from your cleavage and tells you that [npc.she]'s had enough of fucking your [pc.breasts+].",
								"Pulling [npc.her] [npc.cock+] out from your cleavage, [npc.name] tells you that [npc.she]'s had enough of fucking your [pc.breasts+]."));
						break;
				}
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly pushing you away, [npc.name] pulls [npc.her] [npc.cock+] out from your tiny amount of cleavage and tells you that [npc.she]'s had enough of fucking your [pc.breastSize] [pc.breasts].",
									"Roughly pulling [npc.her] [npc.cock+] out from your tiny amount of cleavage, [npc.name] tells you that [npc.she]'s had enough of fucking your [pc.breastSize] [pc.breasts]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] pulls [npc.her] [npc.cock+] out from your tiny amount of cleavage and tells you that [npc.she]'s had enough of fucking your [pc.breastSize] [pc.breasts].",
									"Pulling [npc.her] [npc.cock+] out from your tiny amount of cleavage, [npc.name] tells you that [npc.she]'s had enough of fucking your [pc.breastSize] [pc.breasts]."));
							break;
					}
				} else {
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly pushing you away, [npc.name] takes [npc.her] [npc.cock+] away from your chest and tells you that [npc.she]'s had enough of grinding against you.",
									"Roughly pulling [npc.her] [npc.cock+] away from your chest, [npc.name] tells you that [npc.she]'s had enough of grinding against you."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] takes [npc.her] [npc.cock+] away from your chest and tells you that [npc.she]'s had enough of grinding against you.",
									"Pulling [npc.her] [npc.cock+] away from your chest, [npc.name] tells you that [npc.she]'s had enough of grinding against you."));
							break;
					}
				}
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You continue struggling against [npc.herHim], [pc.moaning+] as you beg [npc.herHim] to leave you alone.",
							" With [pc.a_moan+], you beg [npc.herHim] to leave you alone, tears welling up in your [pc.eyes] as you weakly try to push [npc.herHim] away."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You lets out [pc.a_moan+], betraying your desire for [npc.herHim] to continue.",
							" With [pc.a_moan+], you beg for [npc.herHim] to keep on using you."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PLAYER_USING_COCK_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.CATCHER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.ANUS) != PenetrationType.PENIS
					&& Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.VAGINA) != PenetrationType.PENIS;
		}

		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Perform paizuri";
			} else {
				return "Perform naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Use [npc.name]'s [npc.cock+] to fuck your [pc.breasts+].";
			} else {
				return "Use [npc.name]'s [npc.cock+] to grind against your chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently taking hold of [npc.name]'s [npc.cock+], you guide it up to your cleavage, and, sliding forwards, you press your [pc.breasts+] together and start giving [npc.herHim] a titfuck."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc.name]'s [npc.cock+], you guide it up to your cleavage, and, sliding forwards, you press your [pc.breasts+] together and start giving [npc.herHim] an enthusiastic titfuck."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grabbing hold of [npc.name]'s [npc.cock+], you pull it up to your cleavage, and, sliding forwards, you press your [pc.breasts+] together and start giving [npc.herHim] a forceful titfuck."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc.name]'s [npc.cock+], you guide it up to your cleavage, and, sliding forwards, you press your [pc.breasts+] together and start giving [npc.herHim] a titfuck."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc.name]'s [npc.cock+], you guide it up to your cleavage, and, sliding forwards, you press your [pc.breasts+] together and start giving [npc.herHim] an enthusiastic titfuck."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out a happy little [npc.moan] in response, helping to push your [pc.breasts] together as [npc.she] encourages you to keep going."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response, eagerly helping to push your [pc.breasts] together as [npc.she] enthusiastically encourages you to keep going."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response, roughly pushing your [pc.breasts] together as [npc.she] demands that you keep on going."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response, eagerly helping to push your [pc.breasts] together as [npc.she] enthusiastically encourages you to keep going."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan] in response, helping to push your [pc.breasts] together as [npc.she] meekly asks you to keep going."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response, weakly trying to push you away as [npc.she] begs for you to stop."));
						break;
					default:
						break;
				}
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Gently taking hold of [npc.name]'s [npc.cock+], you guide it up to what little cleavage you have, and, sliding forwards,"
											+ " you try your best to press your [pc.breastSize] [pc.breasts] together in order to give [npc.herHim] a titfuck."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of [npc.name]'s [npc.cock+], you guide it up to what little cleavage you have, and, sliding forwards,"
											+ " you try your best to press your [pc.breastSize] [pc.breasts] together in order to give [npc.herHim] an enthusiastic titfuck."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly grabbing hold of [npc.name]'s [npc.cock+], you guide it up to what little cleavage you have, and, sliding forwards,"
											+ " you try your best to press your [pc.breastSize] [pc.breasts] together in order to give [npc.herHim] a forceful titfuck."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Taking hold of [npc.name]'s [npc.cock+], you guide it up to what little cleavage you have, and, sliding forwards,"
											+ " you try your best to press your [pc.breastSize] [pc.breasts] together in order to give [npc.herHim] a titfuck."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of [npc.name]'s [npc.cock+], you guide it up to what little cleavage you have, and, sliding forwards,"
											+ " you try your best to press your [pc.breastSize] [pc.breasts] together in order to give [npc.herHim] an enthusiastic titfuck."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out a happy little [npc.moan] in response, gently thrusting into your chest as [npc.she] encourages you to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out [npc.a_moan+] in response, desperately thrusting into your chest as [npc.she] enthusiastically encourages you to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out [npc.a_moan+] in response, roughly thrusting into your chest as [npc.she] demands that you keep on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out [npc.a_moan+] in response, eagerly thrusting into your chest as [npc.she] enthusiastically encourages you to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out [npc.a_moan] in response, thrusting into your chest as [npc.she] meekly asks you to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out [npc.a_moan+] in response, weakly trying to push you away as [npc.she] begs for you to stop."));
							break;
						default:
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Main.game.getPlayer())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Gently taking hold of [npc.name]'s [npc.cock+], you guide it up to your flat chest, and, sliding forwards, you grind your torso against [npc.her] [npc.cock+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of [npc.name]'s [npc.cock+], you guide it up to your flat chest, and, sliding forwards, you enthusiastically grind your torso against [npc.her] [npc.cock+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly grabbing hold of [npc.name]'s [npc.cock+], you guide it up to your flat chest, and, sliding forwards, you forcefully grind your torso against [npc.her] [npc.cock+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Taking hold of [npc.name]'s [npc.cock+], you guide it up to your flat chest, and, sliding forwards, you grind your torso against [npc.her] [npc.cock+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of [npc.name]'s [npc.cock+], you guide it up to your flat chest, and, sliding forwards, you enthusiastically grind your torso against [npc.her] [npc.cock+]."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPace(Sex.getActivePartner())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out a happy little [npc.moan] in response, gently thrusting into your chest as [npc.she] encourages you to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out [npc.a_moan+] in response, desperately thrusting into your chest as [npc.she] enthusiastically encourages you to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out [npc.a_moan+] in response, roughly thrusting into your chest as [npc.she] demands that you keep on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out [npc.a_moan+] in response, eagerly thrusting into your chest as [npc.she] enthusiastically encourages you to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out [npc.a_moan] in response, thrusting into your chest as [npc.she] meekly asks you to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] lets out [npc.a_moan+] in response, weakly trying to push you away as [npc.she] begs for you to stop."));
							break;
						default:
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_PERFORMING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.CATCHER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Gentle paizuri";
			} else {
				return "Gentle naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Gently pleasure [npc.name]'s [npc.cock+] with your [pc.breasts+].";
			} else {
				return "Gently pleasure [npc.name]'s [npc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to push your [pc.breasts+] together around [npc.name]'s [npc.cock+], you gently raise and lower your torso, softly [pc.moaning] as you help [npc.herHim] to fuck your cleavage.",
						"Gently pressing your [pc.breasts+] together around [npc.name]'s [npc.cock+], you slowly lift them up and down, helping [npc.herHim] to fuck your cleavage as you [pc.moanVerb] in delight.",
						"Letting out a soft [pc.moan], you press your [pc.breasts] together, enveloping [npc.name]'s [npc.cock+] in your pillowy mounds as you give [npc.herHim] a loving titfuck."));
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to try and push your [pc.breastSize] [pc.breasts+] together around [npc.name]'s [npc.cock+], you gently raise and lower your torso,"
									+ " softly [pc.moaning] as you help [npc.herHim] to fuck what little cleavage you have.",
							"Gently trying to press your [pc.breastSize] [pc.breasts+] together around [npc.name]'s [npc.cock+], you use your [pc.fingers+] to help create a structure for [npc.herHim] to thrust into,"
									+ " before [pc.moaning] in delight as you lift yourself up and down to get [npc.herHim] to fuck your small cleavage.",
							"Letting out a soft [pc.moan], you try to press your [pc.breastSize] [pc.breasts] together, using your [pc.fingers+] to help to envelop [npc.name]'s [npc.cock+] as you give [npc.herHim] a loving titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap your [pc.fingers+] around [npc.name]'s [npc.cock+], you gently raise and lower your torso, softly [pc.moaning] as you thrust out your chest and grind against [npc.herHim].",
							"Gently wrapping your [pc.fingers+] around [npc.name]'s [npc.cock+], you lift yourself up and down, grinding your chest against [npc.herHim] as you imitate giving [npc.herHim] a titfuck.",
							"Letting out a soft [pc.moan], you wrap your [pc.fingers+] around [npc.name]'s [npc.cock+] and thrust your chest out, before lifting yourself up and down to give [npc.herHim] an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_PERFORMING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.CATCHER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Pleasure [npc.name]'s [npc.cock+] with your [pc.breasts+].";
			} else {
				return "Pleasure [npc.name]'s [npc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to eagerly push your [pc.breasts+] together around [npc.name]'s [npc.cock+], you start rapidly bucking your torso up and down, [pc.moaning+] as you help [npc.herHim] to fuck your cleavage.",
						"Eagerly pressing your [pc.breasts+] together around [npc.name]'s [npc.cock+], you start rapidly lifting them up and down, helping [npc.herHim] to fuck your cleavage as you [pc.moanVerb] in delight.",
						"Letting out [pc.a_moan+], you press your [pc.breasts] together, enveloping [npc.name]'s [npc.cock+] in your pillowy mounds as you give [npc.herHim] an enthusiastic titfuck."));
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to desperately try and push your [pc.breastSize] [pc.breasts+] together around [npc.name]'s [npc.cock+], you rapidly start bucking your torso up and down,"
									+ " [pc.moaning+] as you enthusiastically help [npc.herHim] to fuck what little cleavage you have.",
							"Eagerly trying to press your [pc.breastSize] [pc.breasts+] together around [npc.name]'s [npc.cock+], you use your [pc.fingers+] to help create a structure for [npc.herHim] to thrust into,"
									+ " before [pc.moaning] in delight as you rapidly lift yourself up and down to get [npc.herHim] to fuck your small cleavage.",
							"Letting out [pc.a_moan+], you desperately try to press your [pc.breastSize] [pc.breasts] together, using your [pc.fingers+] to help to envelop [npc.name]'s [npc.cock+] as you give [npc.herHim] an enthusiastic titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap your [pc.fingers+] around [npc.name]'s [npc.cock+], you start rapidly bucking your torso up and down, [pc.moaning+] as you thrust out your chest and eagerly grind against [npc.herHim].",
							"Eagerly wrapping your [pc.fingers+] around [npc.name]'s [npc.cock+], you rapidly lift yourself up and down, grinding your chest against [npc.herHim] as you imitate giving [npc.herHim] a titfuck.",
							"Letting out [pc.a_moan+], you wrap your [pc.fingers+] around [npc.name]'s [npc.cock+] and eagerly thrust your chest out, before lifting yourself up and down to give [npc.herHim] an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_PERFORMING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.CATCHER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Rough paizuri";
			} else {
				return "Rough naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Roughly pleasure [npc.name]'s [npc.cock+] with your [pc.breasts+].";
			} else {
				return "Roughly pleasure [npc.name]'s [npc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to desperately push your [pc.breasts+] together around [npc.name]'s [npc.cock+], you start roughly bucking your torso up and down, [pc.moaning+] as you force [npc.herHim] to fuck your cleavage.",
						"Roughly pressing your [pc.breasts+] together around [npc.name]'s [npc.cock+], you start rapidly lifting them up and down, forcing [npc.herHim] to fuck your cleavage as you [pc.moanVerb] in delight.",
						"Letting out [pc.a_moan+], you roughly press your [pc.breasts] together, enveloping [npc.name]'s [npc.cock+] in your pillowy mounds as you give [npc.herHim] a forceful titfuck."));
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to desperately try and push your [pc.breastSize] [pc.breasts+] together around [npc.name]'s [npc.cock+], you rapidly start bucking your torso up and down,"
									+ " [pc.moaning+] as you roughly force [npc.herHim] to fuck what little cleavage you have.",
							"Roughly trying to press your [pc.breastSize] [pc.breasts+] together around [npc.name]'s [npc.cock+], you use your [pc.fingers+] to help create a structure for [npc.herHim] to thrust into,"
									+ " before [pc.moaning] in delight as you rapidly lift yourself up and down to force [npc.herHim] to fuck your small cleavage.",
							"Letting out [pc.a_moan+], you roughly try to press your [pc.breastSize] [pc.breasts] together, using your [pc.fingers+] to help to envelop [npc.name]'s [npc.cock+] as you give [npc.herHim] a forceful titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to roughly wrap your [pc.fingers+] around [npc.name]'s [npc.cock+], you start rapidly bucking your torso up and down, [pc.moaning+] as you thrust out your chest and forcefully grind against [npc.herHim].",
							"Roughly wrapping your [pc.fingers+] around [npc.name]'s [npc.cock+], you rapidly lift yourself up and down, forcefully grinding your chest against [npc.herHim] as you imitate giving [npc.herHim] a titfuck.",
							"Letting out [pc.a_moan+], you roughly wrap your [pc.fingers+] around [npc.name]'s [npc.cock+] and forcefully thrust your chest out, before lifting yourself up and down to give [npc.herHim] an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_PERFORMING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.CATCHER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Pleasure [npc.name]'s [npc.cock+] with your [pc.breasts+].";
			} else {
				return "Pleasure [npc.name]'s [npc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to push your [pc.breasts+] together around [npc.name]'s [npc.cock+], you start bucking your torso up and down, [pc.moaning+] as you help [npc.herHim] to fuck your cleavage.",
						"Pressing your [pc.breasts+] together around [npc.name]'s [npc.cock+], you start lifting them up and down, helping [npc.herHim] to fuck your cleavage as you [pc.moanVerb] in delight.",
						"Letting out [pc.a_moan+], you press your [pc.breasts] together, enveloping [npc.name]'s [npc.cock+] in your pillowy mounds as you give [npc.herHim] a titfuck."));
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to try and push your [pc.breastSize] [pc.breasts+] together around [npc.name]'s [npc.cock+], you start bucking your torso up and down,"
									+ " [pc.moaning+] as you help [npc.herHim] to fuck what little cleavage you have.",
							"Trying to press your [pc.breastSize] [pc.breasts+] together around [npc.name]'s [npc.cock+], you use your [pc.fingers+] to help create a structure for [npc.herHim] to thrust into,"
									+ " before [pc.moaning] in delight as you lift yourself up and down to get [npc.herHim] to fuck your small cleavage.",
							"Letting out [pc.a_moan+], you try to press your [pc.breastSize] [pc.breasts] together, using your [pc.fingers+] to help to envelop [npc.name]'s [npc.cock+] as you give [npc.herHim] a titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap your [pc.fingers+] around [npc.name]'s [npc.cock+], you start bucking your torso up and down, [pc.moaning+] as you thrust out your chest and grind against [npc.herHim].",
							"Wrapping your [pc.fingers+] around [npc.name]'s [npc.cock+], you lift yourself up and down, grinding your chest against [npc.herHim] as you imitate giving [npc.herHim] a titfuck.",
							"Letting out [pc.a_moan+], you wrap your [pc.fingers+] around [npc.name]'s [npc.cock+] and thrust your chest out, before lifting yourself up and down to give [npc.herHim] an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_PERFORMING_COCK_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.CATCHER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Eager paizuri";
			} else {
				return "Eager naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Eagerly pleasure [npc.name]'s [npc.cock+] with your [pc.breasts+].";
			} else {
				return "Eagerly pleasure [npc.name]'s [npc.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to eagerly push your [pc.breasts+] together around [npc.name]'s [npc.cock+], you start rapidly bucking your torso up and down, [pc.moaning+] as you help [npc.herHim] to fuck your cleavage.",
						"Eagerly pressing your [pc.breasts+] together around [npc.name]'s [npc.cock+], you start rapidly lifting them up and down, helping [npc.herHim] to fuck your cleavage as you [pc.moanVerb] in delight.",
						"Letting out [pc.a_moan+], you press your [pc.breasts] together, enveloping [npc.name]'s [npc.cock+] in your pillowy mounds as you give [npc.herHim] an enthusiastic titfuck."));
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to desperately try and push your [pc.breastSize] [pc.breasts+] together around [npc.name]'s [npc.cock+], you rapidly start bucking your torso up and down,"
									+ " [pc.moaning+] as you enthusiastically help [npc.herHim] to fuck what little cleavage you have.",
							"Eagerly trying to press your [pc.breastSize] [pc.breasts+] together around [npc.name]'s [npc.cock+], you use your [pc.fingers+] to help create a structure for [npc.herHim] to thrust into,"
									+ " before [pc.moaning] in delight as you rapidly lift yourself up and down to get [npc.herHim] to fuck your small cleavage.",
							"Letting out [pc.a_moan+], you desperately try to press your [pc.breastSize] [pc.breasts] together, using your [pc.fingers+] to help to envelop [npc.name]'s [npc.cock+] as you give [npc.herHim] an enthusiastic titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap your [pc.fingers+] around [npc.name]'s [npc.cock+], you start rapidly bucking your torso up and down, [pc.moaning+] as you thrust out your chest and eagerly grind against [npc.herHim].",
							"Eagerly wrapping your [pc.fingers+] around [npc.name]'s [npc.cock+], you rapidly lift yourself up and down, grinding your chest against [npc.herHim] as you imitate giving [npc.herHim] a titfuck.",
							"Letting out [pc.a_moan+], you wrap your [pc.fingers+] around [npc.name]'s [npc.cock+] and eagerly thrust your chest out, before lifting yourself up and down to give [npc.herHim] an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.CATCHER,
			SexPace.SUB_RESISTING,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Resist paizuri";
			} else {
				return "Resist naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Try and pull your [pc.breasts+] away from [pc.name]'s [npc.cock+].";
			} else {
				return "Try and pull your chest away from [pc.name]'s [npc.cock+].";
			}
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"You let out [pc.moan+] as you try to pull your [pc.breasts+] away from [npc.name]'s [npc.cock+], before begging for [npc.herHim] to leave you alone.",
						"With [pc.a_moan+], you weakly try to pull away from [npc.name], sobbing in distress as [npc.her] [npc.cock+] continues to thrust up between your [pc.breasts+].",
						"Letting out [pc.a_moan+], you try to push [npc.name] away from you, tears running down your cheeks as [npc.she] continues thrusting [npc.her] [npc.cock+] into your cleavage."));
				
			} else {
				if(Main.game.getPlayer().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You let out [pc.moan+] as you try to pull your [pc.breastSize] [pc.breasts] away from [npc.name]'s [npc.cock+], before begging for you to leave [npc.herHim] alone.",
							"With [pc.a_moan+], you weakly try to pull away from [npc.name], sobbing in distress as [npc.her] [npc.cock+] continues to thrust up between your [pc.breastSize] [pc.breasts+].",
							"Letting out [pc.a_moan+], you try to push [npc.name] away from you, tears running down your cheeks as [npc.she] continues thrusting [npc.her] [npc.cock+] into your small cleavage."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You let out [pc.moan+] as you try to pull your chest away from [npc.name]'s [npc.cock+], before begging for you to leave [npc.herHim] alone.",
							"With [pc.a_moan+], you weakly try to pull away from [npc.name], sobbing in distress as [npc.her] [npc.cock+] continues to grind up against your chest.",
							"Letting out [pc.a_moan+], you try to push [npc.name] away from you, tears running down your cheeks as [npc.she] continues thrusting [npc.her] [npc.cock+] against your chest."));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.BREAST,
			SexParticipantType.CATCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Stop paizuri";
			} else {
				return "Stop naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isBreastFuckablePaizuri()) {
				return "Push [npc.name]'s [npc.cock] away from your [pc.breasts+].";
			} else {
				return "Push [npc.name]'s [npc.cock] away from your chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().hasBreasts()) {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly push [npc.name] away from you, and, in a menacing tone, order [npc.herHim] to stop fucking your [pc.breasts+].",
								"With a menacing growl, you roughly push [npc.name] away, and order [npc.her] to stop fucking your [pc.breasts+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You push [npc.name] away from you, and tell [npc.herHim] to stop fucking your [pc.breasts+].",
								"With one last [pc.moan], you push [npc.name] away, and tell [npc.herHim] to stop fucking your [pc.breasts+]."));
						break;
				}
			} else {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly push [npc.name] away from you, and, in a menacing tone, order [npc.herHim] to stop grinding against your chest.",
								"With a menacing growl, you roughly push [npc.name] away, and order [npc.herHim] to stop grinding against your chest."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You push [npc.name] away from you, and tell [npc.herHim] to stop grinding against your chest.",
								"With one last [pc.moan], you push [npc.name] away, and tell [npc.herHim] to stop grinding against your chest."));
						break;
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
							" [npc.Name] can't help but let out [npc.a_moan+], betraying [npc.her] desire for more of your attention.",
							" With [npc.a_moan+], [npc.she] begs for you to keep on using [npc.herHim]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
