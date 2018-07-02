package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

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
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.84
 * @version 0.1.84
 * @author Innoxia
 */
public class PartnerPenisBreasts {
	
	public static final SexAction PARTNER_FORCE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "[npc2.verb(thrust)] into mouth";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(push)] forwards and [npc2.verb(force)] the [npc.cockHead] of [npc2.namePos] cock into [npc2.namePos] mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Sex.getCharacterPerformingAction().getPenisRawSizeValue()>=6
					&& Sex.isOrificeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)
					&& Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()
					&& Sex.getPosition() != SexPositionType.SIXTY_NINE;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently thrusting forwards between [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(push)] [npc.her] [npc.cock+] all the way up to [npc2.namePos] mouth and forces the [npc.cockHead] past [npc2.namePos] [npc2.lips].",

							"Slowly pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+], pushing all the way until [npc.her] [npc.cockHead] [npc.verb(press)] past [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly thrusting forwards between [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(push)] [npc.her] [npc.cock+] all the way up to [npc2.namePos] mouth and forces the [npc.cockHead] past [npc2.namePos] [npc2.lips].",

							"Greedily pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+], pushing all the way until [npc.her] [npc.cockHead] [npc.verb(press)] past [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly thrusting forwards between [npc2.namePos] [npc2.breasts], [npc.name] slams [npc.her] [npc.cock+] up against [npc2.namePos] mouth and forces the [npc.cockHead] past [npc2.namePos] [npc2.lips].",

							"Violently slamming [npc.her] [npc.hips] forwards, [npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+], pushing all the way until [npc.her] [npc.cockHead] rams past [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"thrusting forwards between [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(push)] [npc.her] [npc.cock+] all the way up to [npc2.namePos] mouth and forces the [npc.cockHead] past [npc2.namePos] [npc2.lips].",

							"Pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+], pushing all the way until [npc.her] [npc.cockHead] [npc.verb(press)] past [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly thrusting forwards between [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(push)] [npc.her] [npc.cock+] all the way up to [npc2.namePos] mouth and forces the [npc.cockHead] past [npc2.namePos] [npc2.lips].",

							"Greedily pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+], pushing all the way until [npc.her] [npc.cockHead] [npc.verb(press)] past [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_RESISTING:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] grin at [npc.her] enthusiasm, and, opening [npc2.namePos] mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a loving suck, [npc2.name] then draw back, but not before planting a kiss on the very tip.",

							" [npc2.Name] open [npc2.namePos] mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a hot, wet suck before drawing back to deliver a soft kiss to the very tip."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a happy [npc2.moan], and, eagerly opening [npc2.namePos] mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a loving suck, [npc2.name] then draw back, but not before planting a wet kiss on the very tip.",

							" [npc2.Name] eagerly open [npc2.namePos] mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a hot, wet suck before drawing back to deliver a passionate kiss to the very tip."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, quickly opening [npc2.namePos] mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a forceful suck, [npc2.name] then draw back, but not before planting a rough kiss on the very tip.",

							" [npc2.Name] quickly open [npc2.namePos] mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a forceful suck before drawing back to deliver a rough kiss to the very tip."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan], and, opening [npc2.namePos] mouth to give the [npc.cockHead] of [npc.her] [npc.cock] an obedient suck, [npc2.name] then draw back, but not before planting a quick kiss on the very tip.",

							" [npc2.Name] open [npc2.namePos] mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] an obediently suck before drawing back to deliver a quick kiss to the very tip."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a happy [npc2.moan], and, eagerly opening [npc2.namePos] mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a loving suck, [npc2.name] then draw back, but not before planting a wet kiss on the very tip.",

							" [npc2.Name] eagerly open [npc2.namePos] mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a hot, wet suck before drawing back to deliver a passionate kiss to the very tip."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, trying to pull [npc2.namePos] mouth away from the [npc.cockHead] of [npc.her] [npc.cock], [npc2.name] desperately [npc2.verb(plead)] for [npc.herHim] to leave [npc2.name] alone.",

							" [npc2.Name] jerk [npc2.namePos] head back, trying to push [npc.her] [npc.cock+] away from [npc2.namePos] mouth as tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH);
		}
		
	};
	
	public static final SexAction PLAYER_TAKE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Paizuri into mouth";
			} else {
				return "Naizuri into mouth";
			}
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(push)] forwards and take the [npc.cockHead] of [npc.namePos] [npc.cock+] into [npc2.namePos] mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
					&& Sex.getCharacterPerformingAction().getPenisRawSizeValue()>=6
					&& Sex.isOrificeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)
					&& Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()
					&& Sex.getPosition() != SexPositionType.SIXTY_NINE;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pushing [npc2.namePos] [npc2.face] down towards [npc.namePos] [npc.cock] as it slides up between [npc2.namePos] [npc2.breasts], [npc2.name] part [npc2.namePos] [npc2.lips+] and take the [npc.cockHead] into [npc2.namePos] mouth.",

							"Slowly pushing [npc2.namePos] [npc2.face] down, [npc2.name] take the [npc.cockHead] of [npc.namePos] [npc.cock+] into [npc2.namePos] mouth as [npc.she] [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc2.namePos] [npc2.face] down towards [npc.namePos] [npc.cock] as it slides up between [npc2.namePos] [npc2.breasts], [npc2.name] greedily part [npc2.namePos] [npc2.lips+] and take the [npc.cockHead] into [npc2.namePos] mouth.",

							"Eagerly pushing [npc2.namePos] [npc2.face] down, [npc2.name] greedily take the [npc.cockHead] of [npc.namePos] [npc.cock+] into [npc2.namePos] mouth as [npc.she] [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc2.namePos] [npc2.face] down towards [npc.namePos] [npc.cock] as it slides up between [npc2.namePos] [npc2.breasts], [npc2.name] greedily part [npc2.namePos] [npc2.lips+] and take the [npc.cockHead] into [npc2.namePos] mouth.",

							"Pushing [npc2.namePos] [npc2.face] down, [npc2.name] greedily takes the [npc.cockHead] of [npc.namePos] [npc.cock+] into [npc2.namePos] mouth as [npc.she] [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc2.namePos] [npc2.face] down towards [npc.namePos] [npc.cock] as it slides up between [npc2.namePos] [npc2.breasts], [npc2.name] part [npc2.namePos] [npc2.lips+] and take the [npc.cockHead] into [npc2.namePos] mouth.",

							"Pushing [npc2.namePos] [npc2.face] down, [npc2.name] take the [npc.cockHead] of [npc.namePos] [npc.cock+] into [npc2.namePos] mouth as [npc.she] [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc2.namePos] [npc2.face] down towards [npc.namePos] [npc.cock] as it slides up between [npc2.namePos] [npc2.breasts], [npc2.name] greedily part [npc2.namePos] [npc2.lips+] and take the [npc.cockHead] into [npc2.namePos] mouth.",

							"Eagerly pushing [npc2.namePos] [npc2.face] down, [npc2.name] greedily take the [npc.cockHead] of [npc.namePos] [npc.cock+] into [npc2.namePos] mouth as [npc.she] [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+]."));
					break;
				case SUB_RESISTING:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] smiles at [npc2.namePos] enthusiasm, and, gently pushing [npc.her] [npc.cock] into [npc2.namePos] mouth,"
									+ " [npc.she] [npc.verb(let)] [npc2.name] suck and kiss the [npc.cockHead] for a moment, before pulling back and continuing to fuck [npc2.namePos] [npc2.breasts+].",

							" [npc.Name] slowly [npc.verb(push)] [npc.her] [npc.cock+] into [npc2.namePos] mouth, allowing [npc2.name] to give the [npc.cockHead] a hot, wet suck before drawing back and continuing to fuck [npc2.namePos] [npc2.breasts+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] grins at [npc.her] enthusiasm, and, eagerly pushing [npc.her] [npc.cock] into [npc2.namePos] mouth,"
									+ " [npc.she] [npc.verb(let)] [npc2.name] suck and kiss the [npc.cockHead] for a moment, before pulling back and continuing to fuck [npc2.namePos] [npc2.breasts+].",

							" [npc.Name] eagerly [npc.verb(push)] [npc.her] [npc.cock+] into [npc2.namePos] mouth, allowing [npc2.name] to give the [npc.cockHead] a hot, wet suck before drawing back and continuing to fuck [npc2.namePos] [npc2.breasts+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] grins at [npc.her] enthusiasm, and, roughly forcing [npc.her] [npc.cock] deeper into [npc2.namePos] mouth,"
									+ " [npc.she] allows [npc2.name] to suck and kiss the [npc.cockHead] for a moment, before pulling back and continuing to aggressively fuck [npc2.namePos] [npc2.breasts+].",

							" [npc.Name] roughly forces [npc.her] [npc.cock+] into [npc2.namePos] mouth, allowing [npc2.name] to give the [npc.cockHead] a hot, wet suck before drawing back and continuing to aggressively fuck [npc2.namePos] [npc2.breasts+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out a little [npc.moan], and, pushing [npc.her] [npc.cock] into [npc2.namePos] mouth, [npc.she] gasps as [npc2.name] suck and kiss the [npc.cockHead] for a moment,"
									+ " before allowing [npc.herHim] to pull back and [npc.verb(continue)] to fuck [npc2.namePos] [npc2.breasts+].",

							" [npc.Name] [npc.verb(push)] [npc.her] [npc.cock+] into [npc2.namePos] mouth, gasping as [npc2.name] give the [npc.cockHead] a hot, wet suck before allowing [npc.herHim] to pull back and [npc.verb(continue)] to fuck [npc2.namePos] [npc2.breasts+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc2.a_moan+], and, eagerly pushing [npc.her] [npc.cock] into [npc2.namePos] mouth, [npc.she] gasps as [npc2.name] suck and kiss the [npc.cockHead] for a moment,"
									+ " before allowing [npc.herHim] to pull back and [npc.verb(continue)] to happily fuck [npc2.namePos] [npc2.breasts+].",

							" [npc.Name] eagerly [npc.verb(push)] [npc.her] [npc.cock+] into [npc2.namePos] mouth, gasping as [npc2.name] give the [npc.cockHead] a hot, wet suck before allowing [npc.herHim] to pull back and [npc.verb(continue)] to happily fuck [npc2.namePos] [npc2.breasts+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc2.a_moan+], and, trying to pull [npc.her] [npc.cock] away from [npc2.namePos] mouth, [npc.she] desperately [npc2.verb(plead)]s for [npc2.name] to leave [npc.herHim] alone.",

							" [npc.Name] tries to pull [npc.her] [npc.hips] back, but [npc2.name] hold [npc.herHim] in place, sucking on the [npc.cockHead] of [npc.her] [npc.cock+] for a moment before finally letting [npc.herHim] [npc2.verb(pull)] back."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH);
		}
		
	};
	
	
	public static final SexAction PARTNER_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Start paizuri";
			} else {
				return "Start naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Sink [npc.namePos] [npc.cock+] between [npc2.namePos] [npc2.breasts+] and [npc2.verb(start)] fucking them.";
			} else {
				return "Start grinding [npc.namePos] [npc.cock+] over [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of [npc2.namePos] [npc2.breasts+], [npc.name] gently [npc.verb(push)] them together, lining [npc.her] [npc.cock] up to [npc2.namePos] cleavage before sliding forwards and starting to fuck [npc2.namePos] [npc2.breasts]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to greedily [npc.verb(sink)] [npc.her] [npc.fingers] into [npc2.namePos] [npc2.breasts+], [npc.name] eagerly [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.namePos] cleavage before sliding forwards and starting to enthusiastically fuck [npc2.namePos] [npc2.breasts]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to roughly [npc.verb(sink)] [npc.her] [npc.fingers] into [npc2.namePos] [npc2.breasts+], [npc.name] forcefully [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.namePos] cleavage before slamming forwards and starting to rapidly fuck [npc2.namePos] [npc2.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of [npc2.namePos] [npc2.breasts+], [npc.name] then [npc.verb(push)] them together, lining [npc.her] [npc.cock] up to [npc2.namePos] cleavage before sliding forwards and starting to fuck [npc2.namePos] [npc2.breasts]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to greedily [npc.verb(sink)] [npc.her] [npc.fingers] into [npc2.namePos] [npc2.breasts+], [npc.name] eagerly [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.namePos] cleavage before sliding forwards and starting to enthusiastically fuck [npc2.namePos] [npc2.breasts]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, reaching up to help [npc2.verb(push)] [npc2.namePos] [npc2.breasts] together as [npc2.name] [npc2.verb(encourage)] [npc.herHim] to keep going."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly reaching up to help [npc2.verb(push)] [npc2.namePos] [npc2.breasts] together as [npc2.name] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, reaching up to forcefully press [npc2.namePos] [npc2.breasts] together as [npc2.name] dominantly [npc2.verb(order)] [npc.herHim] to keep going."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly reaching up to help [npc2.verb(push)] [npc2.namePos] [npc2.breasts] together as [npc2.name] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan] in response, before reaching up to help [npc2.verb(push)] [npc2.namePos] [npc2.breasts] together as [npc2.name] [npc2.verb(encourage)] [npc.herHim] to keep going."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, reaching up to weakly [npc2.verb(try)] and [npc2.verb(push)] [npc.herHim] away from [npc2.namePos] [npc2.breasts] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to stop."));
						break;
					default:
						break;
				}
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to take hold of [npc2.namePos] [npc2.breasts+], [npc.name] gently tries to push them together,"
											+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.name] have before sliding forwards and starting to grind down over [npc2.namePos] chest."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to squeeze and grope [npc2.namePos] [npc2.breasts+], [npc.name] does [npc.her] best to try to push them together,"
											+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.name] have before sliding forwards and starting to eagerly [npc2.verb(grind)] down over [npc2.namePos] chest."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to roughly squeeze and grope [npc2.namePos] [npc2.breasts+], [npc.name] does [npc.her] best to try to push them together,"
											+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.name] have before sliding forwards and starting to forcefully [npc2.verb(grind)] down over [npc2.namePos] chest."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to squeeze and grope [npc2.namePos] [npc2.breasts+], [npc.name] does [npc.her] best to try to push them together,"
											+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.name] have before sliding forwards and starting to grind down over [npc2.namePos] chest."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to squeeze and grope [npc2.namePos] [npc2.breasts+], [npc.name] does [npc.her] best to try to push them together,"
											+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.name] have before sliding forwards and starting to eagerly [npc2.verb(grind)] down over [npc2.namePos] chest."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, reaching up to try and [npc2.verb(help)] [npc2.verb(push)] [npc2.namePos] [npc2.breastSize] [npc2.breasts] together as [npc2.name] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly reaching up to try and [npc2.verb(help)] [npc2.verb(push)] [npc2.namePos] [npc2.breastSize] [npc2.breasts] together as [npc2.name] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, reaching up to forcefully [npc2.verb(try)] and press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together as [npc2.name] dominantly [npc2.verb(order)] [npc.herHim] to keep going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly reaching up to try and [npc2.verb(help)] [npc2.verb(push)] [npc2.namePos] [npc2.breastSize] [npc2.breasts] together as [npc2.name] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan] in response, before reaching up to try and [npc2.verb(help)] [npc2.verb(push)] [npc2.namePos] [npc2.breastSize] [npc2.breasts] together as [npc2.name] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, reaching up to weakly [npc2.verb(try)] and [npc2.verb(push)] [npc.herHim] away from [npc2.namePos] [npc2.breastSize] [npc2.breasts] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.namePos] chest,"
											+ " before sliding forwards and starting to grind down against [npc2.namePos] body."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.namePos] chest,"
											+ " before sliding forwards and starting to eagerly [npc2.verb(grind)] down against [npc2.namePos] body."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to roughly press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.namePos] chest,"
											+ " before sliding forwards and starting to forcefully [npc2.verb(grind)] down against [npc2.namePos] body."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.namePos] chest,"
											+ " before sliding forwards and starting to grind down against [npc2.namePos] body."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.namePos] chest,"
											+ " before sliding forwards and starting to eagerly [npc2.verb(grind)] down against [npc2.namePos] body."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, pushing [npc2.namePos] chest out as [npc2.name] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly pushing [npc2.namePos] chest out as [npc2.name] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, forcefully pushing [npc2.namePos] chest out as [npc2.name] dominantly [npc2.verb(order)] [npc.herHim] to keep going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, pushing [npc2.namePos] chest out as [npc2.name] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan] in response, pushing [npc2.namePos] chest out as [npc2.name] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, reaching up to weakly [npc2.verb(try)] and [npc2.verb(push)] [npc.herHim] away from [npc2.name] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to stop."));
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
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Gentle paizuri";
			} else {
				return "Gentle naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Gently fuck [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Gently [npc2.verb(grind)] against [npc2.namePos] chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] gently slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, grinning as [npc2.name] enthusiastically press [npc2.namePos] [npc2.breasts+] together while begging for [npc.herHim] to keep going.",

								"[npc.Name] slowly [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+].",

								"thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc2.name] reach up and enthusiastically press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] gently slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, ignoring [npc2.namePos] desperate cries for [npc.herHim] to stop as [npc2.name] weakly [npc2.verb(try)] to push [npc.herHim] away.",

								"[npc.Name] slowly [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out [npc2.a_moan+] as tears [npc2.verb(start)] welling up in [npc2.namePos] [npc2.eyes].",

								"thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc2.name] weakly struggle and [npc2.verb(try)] to push [npc.herHim] away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] gently slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, grinning as [npc2.name] press [npc2.namePos] [npc2.breasts+] together and ask [npc.herHim] to keep going.",

								"[npc.Name] slowly [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out a little [npc2.moan] as [npc2.name] look down at [npc.her] [npc.cock+].",

								"thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc2.name] reach up and press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
						break;
				}
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have,"
											+ " grinning as [npc2.name] enthusiastically [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together while begging for [npc.herHim] to keep going.",

									"[npc.Name] slowly [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+].",

									"thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer,"
											+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc2.name] reach up and desperately [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together for [npc.herHim]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have, ignoring [npc2.namePos] desperate cries for [npc.herHim] to stop as [npc2.name] weakly [npc2.verb(try)] to push [npc.herHim] away.",

									"[npc.Name] slowly [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out [npc.a_moan+] as tears [npc2.verb(start)] welling up in [npc2.namePos] [npc2.eyes].",

									"thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc2.name] weakly struggle and [npc2.verb(try)] to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have, grinning as [npc2.name] [npc2.verb(try)] to press [npc2.namePos] [npc2.breasts+] together and ask [npc.herHim] to keep going.",

									"[npc.Name] slowly [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out a little [npc2.moan] as [npc2.name] look down at [npc.her] [npc.cock+].",

									"thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc2.name] reach up and [npc2.verb(try)] to press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] against [npc2.namePos] chest, grinning as [npc2.name] enthusiastically [npc2.verb(beg)] for [npc.herHim] to keep going.",

									"[npc.Name] slowly [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+].",

									"thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc2.name] desperately [npc2.verb(beg)] for [npc.herHim] not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] against [npc2.namePos] chest, ignoring [npc2.namePos] desperate cries for [npc.herHim] to stop as [npc2.name] weakly [npc2.verb(try)] to push [npc.herHim] away.",

									"[npc.Name] slowly [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out [npc2.a_moan+] as tears [npc2.verb(start)] welling up in [npc2.namePos] [npc2.eyes].",

									"thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc2.name] weakly struggle and [npc2.verb(try)] to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] gently slides [npc.her] [npc.cock+] against [npc2.namePos] chest, grinning as [npc2.name] softly ask for [npc.herHim] to keep going.",

									"[npc.Name] slowly [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out a little [npc2.moan] as [npc2.name] look down at [npc.her] [npc.cock+].",

									"thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc2.name] meekly ask for [npc.herHim] to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.breasts+].";
			} else {
				return "[npc.verb(continue)] grinding against [npc2.namePos] chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, grinning as [npc2.name] enthusiastically press [npc2.namePos] [npc2.breasts+] together while begging for [npc.herHim] to keep going.",

								"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+].",

								"Desperately thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and enthusiastically press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, ignoring [npc2.namePos] desperate cries for [npc.herHim] to stop as [npc2.name] weakly [npc2.verb(try)] to push [npc.herHim] away.",

								"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out [npc2.a_moan+] as tears [npc2.verb(start)] welling up in [npc2.namePos] [npc2.eyes].",

								"Desperately thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] weakly struggle and [npc2.verb(try)] to push [npc.herHim] away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, grinning as [npc2.name] press [npc2.namePos] [npc2.breasts+] together and ask [npc.herHim] to keep going.",

								"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out a little [npc2.moan] as [npc2.name] look down at [npc.her] [npc.cock+].",

								"Desperately thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
						break;
				}
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have,"
											+ " grinning as [npc2.name] enthusiastically [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together while begging for [npc.herHim] to keep going.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+].",

									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer,"
											+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and desperately [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together for [npc.herHim]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have, ignoring [npc2.namePos] desperate cries for [npc.herHim] to stop as [npc2.name] weakly [npc2.verb(try)] to push [npc.herHim] away.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out [npc.a_moan+] as tears [npc2.verb(start)] welling up in [npc2.namePos] [npc2.eyes].",

									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] weakly struggle and [npc2.verb(try)] to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have, grinning as [npc2.name] [npc2.verb(try)] to press [npc2.namePos] [npc2.breasts+] together and ask [npc.herHim] to keep going.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out a little [npc2.moan] as [npc2.name] look down at [npc.her] [npc.cock+].",

									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and [npc2.verb(try)] to press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against [npc2.namePos] chest, grinning as [npc2.name] enthusiastically [npc2.verb(beg)] for [npc.herHim] to keep going.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+].",

									"Desperately thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] desperately [npc2.verb(beg)] for [npc.herHim] not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against [npc2.namePos] chest, ignoring [npc2.namePos] desperate cries for [npc.herHim] to stop as [npc2.name] weakly [npc2.verb(try)] to push [npc.herHim] away.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out [npc2.a_moan+] as tears [npc2.verb(start)] welling up in [npc2.namePos] [npc2.eyes].",

									"Desperately thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] weakly struggle and [npc2.verb(try)] to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against [npc2.namePos] chest, grinning as [npc2.name] softly ask for [npc.herHim] to keep going.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out a little [npc2.moan] as [npc2.name] look down at [npc.her] [npc.cock+].",

									"Desperately thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] meekly ask for [npc.herHim] to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Rough paizuri";
			} else {
				return "Rough naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Roughly fuck [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Roughly [npc2.verb(grind)] against [npc2.namePos] chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}


		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, grinning as [npc2.name] enthusiastically press [npc2.namePos] [npc2.breasts+] together while begging for [npc.herHim] to keep going.",

								"[npc.Name] forcefully [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+].",

								"Roughly thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and enthusiastically press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, ignoring [npc2.namePos] desperate cries for [npc.herHim] to stop as [npc2.name] weakly [npc2.verb(try)] to push [npc.herHim] away.",

								"[npc.Name] forcefully [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out [npc2.a_moan+] as tears [npc2.verb(start)] welling up in [npc2.namePos] [npc2.eyes].",

								"Roughly thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] weakly struggle and [npc2.verb(try)] to push [npc.herHim] away."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, grinning as [npc2.name] press [npc2.namePos] [npc2.breasts+] together and ask [npc.herHim] to keep going.",

								"[npc.Name] forcefully [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out a little [npc2.moan] as [npc2.name] look down at [npc.her] [npc.cock+].",

								"Roughly thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
						break;
				}
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have,"
											+ " grinning as [npc2.name] enthusiastically [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together while begging for [npc.herHim] to keep going.",

									"[npc.Name] forcefully [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+].",

									"Roughly thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer,"
											+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and desperately [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together for [npc.herHim]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have, ignoring [npc2.namePos] desperate cries for [npc.herHim] to stop as [npc2.name] weakly [npc2.verb(try)] to push [npc.herHim] away.",

									"[npc.Name] forcefully [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out [npc.a_moan+] as tears [npc2.verb(start)] welling up in [npc2.namePos] [npc2.eyes].",

									"Roughly thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] weakly struggle and [npc2.verb(try)] to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have, grinning as [npc2.name] [npc2.verb(try)] to press [npc2.namePos] [npc2.breasts+] together and ask [npc.herHim] to keep going.",

									"[npc.Name] forcefully [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out a little [npc2.moan] as [npc2.name] look down at [npc.her] [npc.cock+].",

									"Roughly thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and [npc2.verb(try)] to press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] against [npc2.namePos] chest, grinning as [npc2.name] enthusiastically [npc2.verb(beg)] for [npc.herHim] to keep going.",

									"[npc.Name] forcefully [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+].",

									"Roughly thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] desperately [npc2.verb(beg)] for [npc.herHim] not to stop."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] against [npc2.namePos] chest, ignoring [npc2.namePos] desperate cries for [npc.herHim] to stop as [npc2.name] weakly [npc2.verb(try)] to push [npc.herHim] away.",

									"[npc.Name] forcefully [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out [npc2.a_moan+] as tears [npc2.verb(start)] welling up in [npc2.namePos] [npc2.eyes].",

									"Roughly thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] weakly struggle and [npc2.verb(try)] to push [npc.herHim] away."));
							break;
						default: // SUB_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] roughly slams [npc.her] [npc.cock+] against [npc2.namePos] chest, grinning as [npc2.name] softly ask for [npc.herHim] to keep going.",

									"[npc.Name] forcefully [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out a little [npc2.moan] as [npc2.name] look down at [npc.her] [npc.cock+].",

									"Roughly thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] meekly ask for [npc.herHim] to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.breasts+].";
			} else {
				return "[npc.verb(continue)] grinding against [npc2.namePos] chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) { 
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, grinning as [npc2.name] gently press [npc2.namePos] [npc2.breasts+] together while begging for [npc.herHim] to keep going.",

								"[npc.name] [npc.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out a soft [npc2.moan] as [npc2.name] glance down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

								"thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and gently press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, letting out [npc.a_moan+] as [npc2.name] roughly press [npc2.namePos] [npc2.breasts+] together and [npc2.verb(order)] [npc.herHim] to keep going.",

								"[npc.name] [npc.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out [npc2.a_moan+] before [npc2.name] regain [npc2.namePos] composure and roughly growl out that you're still in charge.",

								"thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] forcefully [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] together and demand that [npc.she] pick up the pace."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, grinning as [npc2.name] eagerly press [npc2.namePos] [npc2.breasts+] together and tell [npc.herHim] to keep going.",

								"[npc.name] [npc.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

								"thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and eagerly press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
						break;
				}
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have,"
											+ " grinning as [npc2.name] gently [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together while begging for [npc.herHim] to keep going.",

									"[npc.name] [npc.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] glance down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

									"thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer,"
											+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together for [npc.herHim]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have,"
											+ " letting out [npc.a_moan+] as [npc2.name] roughly [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together and [npc2.verb(order)] [npc.herHim] to keep going.",

									"[npc.name] [npc.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts],"
											+ " causing [npc2.name] to let out [npc2.a_moan+] before [npc2.name] regain [npc2.namePos] composure and roughly growl out that you're still in charge.",

									"thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer,"
											+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] forcefully [npc2.verb(try)] to push [npc2.namePos] [npc2.breasts+] together and demand that [npc.she] pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have,"
											+ " grinning as [npc2.name] gently [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together while telling [npc.herHim] to keep going.",

									"[npc.name] [npc.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

									"thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer,"
											+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and eagerly [npc2.verb(try)] to press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] against [npc2.namePos] chest, grinning as [npc2.name] gently [npc2.verb(beg)] for [npc2.name] to keep going.",

									"[npc.name] [npc.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out a soft [npc2.moan] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

									"thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] softly [npc2.verb(beg)] for [npc2.name] not to stop."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] against [npc2.namePos] chest, letting out [npc.a_moan+] as [npc2.name] roughly [npc2.verb(order)] [npc.herHim] to keep going.",

									"[npc.name] [npc.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out [npc2.a_moan+] before [npc2.name] regain [npc2.namePos] composure and roughly growl out that you're still in charge.",

									"thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] forcefully demand that [npc.she] pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] slides [npc.her] [npc.cock+] against [npc2.namePos] chest, grinning as [npc2.name] eagerly [npc2.moan] for [npc.herHim] to keep going.",

									"[npc.name] [npc.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] look down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

									"thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] eagerly [npc2.verb(beg)] for [npc.herHim] to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Eager paizuri";
			} else {
				return "Eager naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Eagerly fuck [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Eagerly [npc2.verb(grind)] against [npc2.namePos] chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) { 
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, grinning as [npc2.name] gently press [npc2.namePos] [npc2.breasts+] together while begging for [npc.herHim] to keep going.",

								"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out a soft [npc2.moan] as [npc2.name] glance down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

								"Desperately thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and gently press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, letting out [npc.a_moan+] as [npc2.name] roughly press [npc2.namePos] [npc2.breasts+] together and [npc2.verb(order)] [npc.herHim] to keep going.",

								"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out [npc2.a_moan+] before [npc2.name] regain [npc2.namePos] composure and roughly growl out that you're still in charge.",

								"Desperately thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] forcefully [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] together and demand that [npc.she] pick up the pace."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into [npc2.namePos] cleavage, grinning as [npc2.name] eagerly press [npc2.namePos] [npc2.breasts+] together and tell [npc.herHim] to keep going.",

								"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breasts+], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

								"Desperately thrusting [npc.her] [npc.cock+] between [npc2.namePos] cleavage, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and eagerly press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
						break;
				}
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have,"
											+ " grinning as [npc2.name] gently [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together while begging for [npc.herHim] to keep going.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] glance down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer,"
											+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together for [npc.herHim]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have,"
											+ " letting out [npc.a_moan+] as [npc2.name] roughly [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together and [npc2.verb(order)] [npc.herHim] to keep going.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts],"
											+ " causing [npc2.name] to let out [npc2.a_moan+] before [npc2.name] regain [npc2.namePos] composure and roughly growl out that you're still in charge.",

									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer,"
											+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] forcefully [npc2.verb(try)] to push [npc2.namePos] [npc2.breasts+] together and demand that [npc.she] pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] up into the small amount of cleavage that [npc2.name] have,"
											+ " grinning as [npc2.name] gently [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together while telling [npc.herHim] to keep going.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] up between [npc2.namePos] [npc2.breastSize] [npc2.breasts], causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

									"Desperately thrusting [npc.her] [npc.cock+] between the tiny amount of cleavage that [npc2.name] have on offer,"
											+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] reach up and eagerly [npc2.verb(try)] to press [npc2.namePos] [npc2.breasts+] together for [npc.herHim]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against [npc2.namePos] chest, grinning as [npc2.name] gently [npc2.verb(beg)] for [npc2.name] to keep going.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out a soft [npc2.moan] as [npc2.name] lustfully gaze down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

									"Desperately thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] softly [npc2.verb(beg)] for [npc2.name] not to stop."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against [npc2.namePos] chest, letting out [npc.a_moan+] as [npc2.name] roughly [npc2.verb(order)] [npc.herHim] to keep going.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out [npc2.a_moan+] before [npc2.name] regain [npc2.namePos] composure and roughly growl out that you're still in charge.",

									"Desperately thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] forcefully demand that [npc.she] pick up the pace."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] eagerly slides [npc.her] [npc.cock+] against [npc2.namePos] chest, grinning as [npc2.name] eagerly [npc2.moan] for [npc.herHim] to keep going.",

									"[npc.Name] enthusiastically [npc2.verb(thrust)] down against [npc2.namePos] chest, causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] look down at [npc.her] [npc.cock+] and bite [npc2.namePos] [npc2.lip].",

									"Desperately thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] eagerly [npc2.verb(beg)] for [npc.herHim] to continue."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Resist paizuri";
			} else {
				return "Resist naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Try to pull [npc.namePos] [npc.cock] away from [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Try to pull [npc.namePos] [npc.cock] away from [npc2.namePos] chest.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of [npc2.namePos] cleavage, but [npc2.name] firmly hold [npc.herHim] in place,"
										+ " pressing [npc2.namePos] [npc2.breasts+] together while gently reminding [npc.herHim] that you'll do whatever [npc2.name] want.",

								"[npc.Name] frantically tries to pull away from [npc2.namePos] [npc2.breasts+], but [npc2.name] firmly hold [npc.herHim] in place, softly [npc2.moaning] as [npc2.name] ignore [npc.her] desperate protests.",

								"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes] as [npc.she] tries to pull out of [npc2.namePos] cleavage, but [npc2.namePos] grip is too strong,"
										+ " and [npc2.name] [npc.verb(continue)] softly [npc2.moaning] as [npc2.name] firmly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of [npc2.namePos] cleavage, but [npc2.name] roughly hold [npc.herHim] in place, pressing [npc2.namePos] [npc2.breasts+] together while growling that you'll use [npc.herHim] however [npc2.name] want.",

								"[npc.Name] frantically tries to pull away from [npc2.namePos] [npc2.breasts+], but [npc2.name] roughly hold [npc.herHim] in place, [npc.moaning+] as [npc2.name] ignore [npc.her] futile protests.",

								"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes] as [npc.she] tries to pull out of [npc2.namePos] cleavage, but [npc2.namePos] grip is too strong,"
										+ " and [npc2.name] [npc.verb(continue)] [npc2.moaning+] as [npc2.name] roughly [npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.namePos] [npc2.breasts+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of [npc2.namePos] cleavage, but [npc2.name] firmly hold [npc.herHim] in place,"
										+ " pressing [npc2.namePos] [npc2.breasts+] together while [npc2.moaning] that you'll do whatever [npc2.name] want.",

								"[npc.Name] frantically tries to pull away from [npc2.namePos] [npc2.breasts+], but [npc2.name] firmly hold [npc.herHim] in place, [npc2.moaning+] as [npc2.name] ignore [npc.her] futile protests.",

								"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes] as [npc.she] tries to pull out of [npc2.namePos] cleavage, but [npc2.namePos] grip is too strong,"
										+ " and [npc2.name] [npc.verb(continue)] [npc2.moaning+] as [npc2.name] eagerly [npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.namePos] [npc2.breasts+]."));
						break;
				}
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of the small amount of cleavage that [npc2.name] have, but [npc2.name] firmly hold [npc.herHim] in place,"
											+ " trying to press [npc2.namePos] [npc2.breasts+] together while gently reminding [npc.herHim] that you'll do whatever [npc2.name] want.",

									"[npc.Name] frantically tries to pull away from [npc2.namePos] [npc2.breastSize] [npc2.breasts], but [npc2.name] firmly hold [npc.herHim] in place, softly [npc2.moaning] as [npc2.name] ignore [npc.her] desperate protests.",

									"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes] as [npc.she] tries to pull out of the tiny amount of cleavage that [npc2.name] have on offer, but [npc2.namePos] grip is too strong,"
											+ " and [npc2.name] [npc.verb(continue)] softly [npc2.moaning] as [npc2.name] firmly [npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.namePos] [npc2.breasts+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of the small amount of cleavage that [npc2.name] have, but [npc2.name] roughly hold [npc.herHim] in place,"
											+ " trying to press [npc2.namePos] [npc2.breasts+] together while growling that you'll use [npc.herHim] however [npc2.name] want.",

									"[npc.Name] frantically tries to pull away from [npc2.namePos] [npc2.breastSize] [npc2.breasts], but [npc2.name] roughly hold [npc.herHim] in place, [npc.moaning+] as [npc2.name] ignore [npc.her] futile protests.",

									"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes] as [npc.she] tries to pull out of the tiny amount of cleavage that [npc2.name] have on offer, but [npc2.namePos] grip is too strong,"
											+ " and [npc2.name] [npc.verb(continue)] [npc2.moaning+] as [npc2.name] roughly [npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.namePos] [npc2.breasts+]."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] out of the small amount of cleavage that [npc2.name] have, but [npc2.name] firmly hold [npc.herHim] in place,"
											+ " trying to press [npc2.namePos] [npc2.breasts+] together while [npc2.moaning] [npc2.name] that you'll do whatever [npc2.name] want.",

									"[npc.Name] frantically tries to pull away from [npc2.namePos] [npc2.breastSize] [npc2.breasts], but [npc2.name] firmly hold [npc.herHim] in place, [npc.moaning+] as [npc2.name] ignore [npc.her] futile protests.",

									"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes] as [npc.she] tries to pull out of the tiny amount of cleavage that [npc2.name] have on offer, but [npc2.namePos] grip is too strong,"
											+ " and [npc2.name] [npc.verb(continue)] [npc2.moaning+] as [npc2.name] eagerly [npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.namePos] [npc2.breasts+]."));
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] away from [npc2.namePos] chest, but [npc2.name] firmly hold [npc.herHim] in place,"
											+ " grinding against [npc.herHim] as [npc2.name] gently [npc2.moanVerb] that you'll do whatever [npc2.name] want.",

									"[npc.Name] frantically tries to pull away from [npc2.namePos] chest, but [npc2.name] firmly hold [npc.herHim] in place, softly [npc2.moaning] as [npc2.name] ignore [npc.her] desperate protests.",

									"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes] as [npc.she] tries to pull away from [npc2.herHim], but [npc2.namePos] grip is too strong,"
											+ " and [npc2.name] [npc.verb(continue)] softly [npc2.moaning] as [npc2.name] firmly [npc2.verb(force)] [npc.her] [npc.cock+] against [npc2.namePos] chest."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] away from [npc2.namePos] chest, but [npc2.name] roughly hold [npc.herHim] in place, forcefully grinding against [npc.herHim] as [npc2.name] growl out that you'll do whatever [npc2.name] want.",

									"[npc.Name] frantically tries to pull away from [npc2.namePos] chest, but [npc2.name] roughly hold [npc.herHim] in place, [npc2.moaning+] as [npc2.name] ignore [npc.her] futile protests.",

									"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes] as [npc.she] tries to pull away from [npc.name], but [npc2.namePos] grip is too strong,"
											+ " and [npc2.name] [npc.verb(continue)] [npc2.moaning+] as [npc2.name] roughly [npc2.verb(force)] [npc.namePos] [npc.cock+] against [npc2.namePos] chest."));
							break;
						default: // DOM_NORMAL and in case anything goes wrong:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] desperately tries to pull [npc.her] [npc.cock+] away from [npc2.namePos] chest, but [npc2.name] firmly hold [npc.herHim] in place, grinding against [npc.herHim] as [npc2.name] [npc2.moanVerb] that you'll do whatever [npc2.name] want.",

									"[npc.Name] frantically tries to pull away from [npc2.namePos] chest, but [npc2.name] firmly hold [npc.herHim] in place, [npc2.moaning+] as [npc2.name] ignore [npc.her] futile protests.",

									"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes] as [npc.she] tries to pull away from [npc2.herHim], but [npc2.namePos] grip is too strong,"
											+ " and [npc2.name] [npc.verb(continue)] [npc2.moaning+] as [npc2.name] eagerly [npc2.verb(force)] [npc.her] [npc.cock+] against [npc2.namePos] chest."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual(); // Player can only stop if they're in charge (otherwise, this is the partner fucking themselves on the player's cock).
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Stop paizuri";
			} else {
				return "Stop naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Pull [npc.namePos] [npc.cock+] away from [npc2.namePos] [npc2.breasts+] and stop fucking them.";
			} else {
				return "Pull [npc.namePos] [npc.cock+] away from [npc2.namePos] chest and stop grinding against [npc2.herHim].";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pushing [npc2.name] away, [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.namePos] cleavage and tells [npc2.name] that [npc.she]'s had enough of fucking [npc2.namePos] [npc2.breasts+].",

								"Roughly pulling [npc.her] [npc.cock+] out from [npc2.namePos] cleavage, [npc.name] tells [npc2.name] that [npc.she]'s had enough of fucking [npc2.namePos] [npc2.breasts+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.namePos] cleavage and tells [npc2.name] that [npc.she]'s had enough of fucking [npc2.namePos] [npc2.breasts+].",

								"Pulling [npc.her] [npc.cock+] out from [npc2.namePos] cleavage, [npc.name] tells [npc2.name] that [npc.she]'s had enough of fucking [npc2.namePos] [npc2.breasts+]."));
						break;
				}
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly pushing [npc2.name] away, [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.namePos] tiny amount of cleavage and tells [npc2.name] that [npc.she]'s had enough of fucking [npc2.namePos] [npc2.breastSize] [npc2.breasts].",

									"Roughly pulling [npc.her] [npc.cock+] out from [npc2.namePos] tiny amount of cleavage, [npc.name] tells [npc2.name] that [npc.she]'s had enough of fucking [npc2.namePos] [npc2.breastSize] [npc2.breasts]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.namePos] tiny amount of cleavage and tells [npc2.name] that [npc.she]'s had enough of fucking [npc2.namePos] [npc2.breastSize] [npc2.breasts].",

									"Pulling [npc.her] [npc.cock+] out from [npc2.namePos] tiny amount of cleavage, [npc.name] tells [npc2.name] that [npc.she]'s had enough of fucking [npc2.namePos] [npc2.breastSize] [npc2.breasts]."));
							break;
					}
				} else {
					switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly pushing [npc2.name] away, [npc.name] takes [npc.her] [npc.cock+] away from [npc2.namePos] chest and tells [npc2.name] that [npc.she]'s had enough of grinding against [npc2.herHim].",

									"Roughly pulling [npc.her] [npc.cock+] away from [npc2.namePos] chest, [npc.name] tells [npc2.name] that [npc.she]'s had enough of grinding against [npc2.herHim]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.Name] takes [npc.her] [npc.cock+] away from [npc2.namePos] chest and tells [npc2.name] that [npc.she]'s had enough of grinding against [npc2.herHim].",

									"Pulling [npc.her] [npc.cock+] away from [npc2.namePos] chest, [npc.name] tells [npc2.name] that [npc.she]'s had enough of grinding against [npc2.herHim]."));
							break;
					}
				}
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc.verb(continue)] struggling against [npc.herHim], [npc2.moaning+] as [npc2.name] [npc2.verb(beg)] [npc.herHim] to leave [npc2.name] alone.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(beg)] [npc.herHim] to leave [npc2.name] alone, tears welling up in [npc2.namePos] [npc2.eyes] as [npc2.name] weakly [npc2.verb(try)] to push [npc.herHim] away."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc.verb(let)] out [npc2.a_moan+], betraying [npc2.namePos] desire for [npc.herHim] to continue.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to keep on using [npc2.herHim]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PLAYER_USING_COCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Perform paizuri";
			} else {
				return "Perform naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Use [npc.namePos] [npc.cock+] to fuck [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Use [npc.namePos] [npc.cock+] to grind against [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to [npc2.namePos] cleavage, and, sliding forwards, [npc2.name] press [npc2.namePos] [npc2.breasts+] together and [npc2.verb(start)] giving [npc.herHim] a titfuck."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to [npc2.namePos] cleavage, and, sliding forwards, [npc2.name] press [npc2.namePos] [npc2.breasts+] together and [npc2.verb(start)] giving [npc.herHim] an enthusiastic titfuck."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grabbing hold of [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(pull)] it up to [npc2.namePos] cleavage, and, sliding forwards, [npc2.name] press [npc2.namePos] [npc2.breasts+] together and [npc2.verb(start)] giving [npc.herHim] a forceful titfuck."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to [npc2.namePos] cleavage, and, sliding forwards, [npc2.name] press [npc2.namePos] [npc2.breasts+] together and [npc2.verb(start)] giving [npc.herHim] a titfuck."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to [npc2.namePos] cleavage, and, sliding forwards, [npc2.name] press [npc2.namePos] [npc2.breasts+] together and [npc2.verb(start)] giving [npc.herHim] an enthusiastic titfuck."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out a happy little [npc.moan] in response, helping to push [npc2.namePos] [npc2.breasts] together as [npc.she] [npc2.verb(encourage)]s [npc2.name] to keep going."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, eagerly helping to push [npc2.namePos] [npc2.breasts] together as [npc.she] enthusiastically [npc2.verb(encourage)]s [npc2.name] to keep going."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, roughly pushing [npc2.namePos] [npc2.breasts] together as [npc.she] demands that [npc2.name] keep on going."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, eagerly helping to push [npc2.namePos] [npc2.breasts] together as [npc.she] enthusiastically [npc2.verb(encourage)]s [npc2.name] to keep going."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan] in response, helping to push [npc2.namePos] [npc2.breasts] together as [npc.she] meekly asks [npc2.name] to keep going."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, weakly trying to push [npc2.name] away as [npc.she] [npc2.verb(beg)]s for [npc2.name] to stop."));
						break;
					default:
						break;
				}
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Gently taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to what little cleavage [npc2.name] have, and, sliding forwards,"
											+ " [npc2.Name] [npc2.verb(try)] [npc2.namePos] best to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together in [npc2.verb(order)] to give [npc.herHim] a titfuck."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to what little cleavage [npc2.name] have, and, sliding forwards,"
											+ " [npc2.Name] [npc2.verb(try)] [npc2.namePos] best to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together in [npc2.verb(order)] to give [npc.herHim] an enthusiastic titfuck."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly grabbing hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to what little cleavage [npc2.name] have, and, sliding forwards,"
											+ " [npc2.Name] [npc2.verb(try)] [npc2.namePos] best to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together in [npc2.verb(order)] to give [npc.herHim] a forceful titfuck."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to what little cleavage [npc2.name] have, and, sliding forwards,"
											+ " [npc2.Name] [npc2.verb(try)] [npc2.namePos] best to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together in [npc2.verb(order)] to give [npc.herHim] a titfuck."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to what little cleavage [npc2.name] have, and, sliding forwards,"
											+ " [npc2.Name] [npc2.verb(try)] [npc2.namePos] best to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together in [npc2.verb(order)] to give [npc.herHim] an enthusiastic titfuck."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out a happy little [npc.moan] in response, gently thrusting into [npc2.namePos] chest as [npc.she] [npc2.verb(encourage)]s [npc2.name] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, desperately thrusting into [npc2.namePos] chest as [npc.she] enthusiastically [npc2.verb(encourage)]s [npc2.name] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, roughly thrusting into [npc2.namePos] chest as [npc.she] demands that [npc2.name] keep on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, eagerly thrusting into [npc2.namePos] chest as [npc.she] enthusiastically [npc2.verb(encourage)]s [npc2.name] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out [npc.a_moan] in response, thrusting into [npc2.namePos] chest as [npc.she] meekly asks [npc2.name] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, weakly trying to push [npc2.name] away as [npc.she] [npc2.verb(beg)]s for [npc2.name] to stop."));
							break;
						default:
							break;
					}
					
				} else {
					switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Gently taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to [npc2.namePos] flat chest, and, sliding forwards, [npc2.name] [npc2.verb(grind)] [npc2.namePos] torso against [npc.her] [npc.cock+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to [npc2.namePos] flat chest, and, sliding forwards, [npc2.name] enthusiastically [npc2.verb(grind)] [npc2.namePos] torso against [npc.her] [npc.cock+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Roughly grabbing hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to [npc2.namePos] flat chest, and, sliding forwards, [npc2.name] forcefully [npc2.verb(grind)] [npc2.namePos] torso against [npc.her] [npc.cock+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to [npc2.namePos] flat chest, and, sliding forwards, [npc2.name] [npc2.verb(grind)] [npc2.namePos] torso against [npc.her] [npc.cock+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"Eagerly taking hold of [npc.namePos] [npc.cock+], [npc2.name] guide it up to [npc2.namePos] flat chest, and, sliding forwards, [npc2.name] enthusiastically [npc2.verb(grind)] [npc2.namePos] torso against [npc.her] [npc.cock+]."));
							break;
						default:
							break;
					}
					switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out a happy little [npc.moan] in response, gently thrusting into [npc2.namePos] chest as [npc.she] [npc2.verb(encourage)]s [npc2.name] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, desperately thrusting into [npc2.namePos] chest as [npc.she] enthusiastically [npc2.verb(encourage)]s [npc2.name] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, roughly thrusting into [npc2.namePos] chest as [npc.she] demands that [npc2.name] keep on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, eagerly thrusting into [npc2.namePos] chest as [npc.she] enthusiastically [npc2.verb(encourage)]s [npc2.name] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out [npc.a_moan] in response, thrusting into [npc2.namePos] chest as [npc.she] meekly asks [npc2.name] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, weakly trying to push [npc2.name] away as [npc.she] [npc2.verb(beg)]s for [npc2.name] to stop."));
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
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Gentle paizuri";
			} else {
				return "Gentle naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Gently pleasure [npc.namePos] [npc.cock+] with [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Gently pleasure [npc.namePos] [npc.cock+] with [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to push [npc2.namePos] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] gently raise and lower [npc2.namePos] torso, softly [npc2.moaning] as [npc2.name] [npc2.verb(help)] [npc.herHim] to fuck [npc2.namePos] cleavage.",

						"Gently pressing [npc2.namePos] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] slowly lift them up and down, helping [npc.herHim] to fuck [npc2.namePos] cleavage as [npc2.name] [npc2.moanVerb] in delight.",

						"Letting out a soft [npc2.moan], [npc2.name] press [npc2.namePos] [npc2.breasts] together, enveloping [npc.namePos] [npc.cock+] in [npc2.namePos] pillowy mounds as [npc2.name] give [npc.herHim] a loving titfuck."));
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to try and [npc2.verb(push)] [npc2.namePos] [npc2.breastSize] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] gently raise and lower [npc2.namePos] torso,"
									+ " softly [npc2.moaning] as [npc2.name] [npc2.verb(help)] [npc.herHim] to fuck what little cleavage [npc2.name] have.",

							"Gently trying to press [npc2.namePos] [npc2.breastSize] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] use [npc2.namePos] [npc2.fingers+] to help create a structure for [npc.herHim] to thrust into,"
									+ " before [npc2.moaning] in delight as [npc2.name] lift [npc2.herself] up and down to get [npc.herHim] to fuck [npc2.namePos] small cleavage.",

							"Letting out a soft [npc2.moan], [npc2.name] [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together, using [npc2.namePos] [npc2.fingers+] to help to envelop [npc.namePos] [npc.cock+] as [npc2.name] give [npc.herHim] a loving titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+], [npc2.name] gently raise and lower [npc2.namePos] torso, softly [npc2.moaning] as [npc2.name] [npc2.verb(thrust)] out [npc2.namePos] chest and [npc2.verb(grind)] against [npc.herHim].",

							"Gently wrapping [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+], [npc2.name] lift [npc2.herself] up and down, grinding [npc2.namePos] chest against [npc.herHim] as [npc2.name] imitate giving [npc.herHim] a titfuck.",

							"Letting out a soft [npc2.moan], [npc2.name] wrap [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+] and [npc2.verb(thrust)] [npc2.namePos] chest out, before lifting [npc2.herself] up and down to give [npc.herHim] an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_PERFORMING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Pleasure [npc.namePos] [npc.cock+] with [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Pleasure [npc.namePos] [npc.cock+] with [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to eagerly [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] rapidly bucking [npc2.namePos] torso up and down, [npc2.moaning+] as [npc2.name] [npc2.verb(help)] [npc.herHim] to fuck [npc2.namePos] cleavage.",

						"Eagerly pressing [npc2.namePos] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] rapidly lifting them up and down, helping [npc.herHim] to fuck [npc2.namePos] cleavage as [npc2.name] [npc2.moanVerb] in delight.",

						"Letting out [npc2.a_moan+], [npc2.name] press [npc2.namePos] [npc2.breasts] together, enveloping [npc.namePos] [npc.cock+] in [npc2.namePos] pillowy mounds as [npc2.name] give [npc.herHim] an enthusiastic titfuck."));
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to desperately [npc2.verb(try)] and [npc2.verb(push)] [npc2.namePos] [npc2.breastSize] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] rapidly [npc2.verb(start)] bucking [npc2.namePos] torso up and down,"
									+ " [npc2.moaning+] as [npc2.name] enthusiastically [npc2.verb(help)] [npc.herHim] to fuck what little cleavage [npc2.name] have.",

							"Eagerly trying to press [npc2.namePos] [npc2.breastSize] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] use [npc2.namePos] [npc2.fingers+] to help create a structure for [npc.herHim] to thrust into,"
									+ " before [npc2.moaning] in delight as [npc2.name] rapidly lift [npc2.herself] up and down to get [npc.herHim] to fuck [npc2.namePos] small cleavage.",

							"Letting out [npc2.a_moan+], [npc2.name] desperately [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together, using [npc2.namePos] [npc2.fingers+] to help to envelop [npc.namePos] [npc.cock+] as [npc2.name] give [npc.herHim] an enthusiastic titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] rapidly bucking [npc2.namePos] torso up and down, [npc2.moaning+] as [npc2.name] [npc2.verb(thrust)] out [npc2.namePos] chest and eagerly [npc2.verb(grind)] against [npc.herHim].",

							"Eagerly wrapping [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+], [npc2.name] rapidly lift [npc2.herself] up and down, grinding [npc2.namePos] chest against [npc.herHim] as [npc2.name] imitate giving [npc.herHim] a titfuck.",

							"Letting out [npc2.a_moan+], [npc2.name] wrap [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+] and eagerly [npc2.verb(thrust)] [npc2.namePos] chest out, before lifting [npc2.herself] up and down to give [npc.herHim] an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_PERFORMING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Rough paizuri";
			} else {
				return "Rough naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Roughly pleasure [npc.namePos] [npc.cock+] with [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Roughly pleasure [npc.namePos] [npc.cock+] with [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to desperately [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] roughly bucking [npc2.namePos] torso up and down, [npc2.moaning+] as [npc2.name] [npc2.verb(force)] [npc.herHim] to fuck [npc2.namePos] cleavage.",

						"Roughly pressing [npc2.namePos] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] rapidly lifting them up and down, forcing [npc.herHim] to fuck [npc2.namePos] cleavage as [npc2.name] [npc2.moanVerb] in delight.",

						"Letting out [npc2.a_moan+], [npc2.name] roughly press [npc2.namePos] [npc2.breasts] together, enveloping [npc.namePos] [npc.cock+] in [npc2.namePos] pillowy mounds as [npc2.name] give [npc.herHim] a forceful titfuck."));
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to desperately [npc2.verb(try)] and [npc2.verb(push)] [npc2.namePos] [npc2.breastSize] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] rapidly [npc2.verb(start)] bucking [npc2.namePos] torso up and down,"
									+ " [npc2.moaning+] as [npc2.name] roughly [npc2.verb(force)] [npc.herHim] to fuck what little cleavage [npc2.name] have.",

							"Roughly trying to press [npc2.namePos] [npc2.breastSize] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] use [npc2.namePos] [npc2.fingers+] to help create a structure for [npc.herHim] to thrust into,"
									+ " before [npc2.moaning] in delight as [npc2.name] rapidly lift [npc2.herself] up and down to force [npc.herHim] to fuck [npc2.namePos] small cleavage.",

							"Letting out [npc2.a_moan+], [npc2.name] roughly [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together, using [npc2.namePos] [npc2.fingers+] to help to envelop [npc.namePos] [npc.cock+] as [npc2.name] give [npc.herHim] a forceful titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to roughly wrap [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] rapidly bucking [npc2.namePos] torso up and down, [npc2.moaning+] as [npc2.name] [npc2.verb(thrust)] out [npc2.namePos] chest and forcefully [npc2.verb(grind)] against [npc.herHim].",

							"Roughly wrapping [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+], [npc2.name] rapidly lift [npc2.herself] up and down, forcefully grinding [npc2.namePos] chest against [npc.herHim] as [npc2.name] imitate giving [npc.herHim] a titfuck.",

							"Letting out [npc2.a_moan+], [npc2.name] roughly wrap [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+] and forcefully [npc2.verb(thrust)] [npc2.namePos] chest out, before lifting [npc2.herself] up and down to give [npc.herHim] an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_PERFORMING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Pleasure [npc.namePos] [npc.cock+] with [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Pleasure [npc.namePos] [npc.cock+] with [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to push [npc2.namePos] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] torso up and down, [npc2.moaning+] as [npc2.name] [npc2.verb(help)] [npc.herHim] to fuck [npc2.namePos] cleavage.",

						"Pressing [npc2.namePos] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] lifting them up and down, helping [npc.herHim] to fuck [npc2.namePos] cleavage as [npc2.name] [npc2.moanVerb] in delight.",

						"Letting out [npc2.a_moan+], [npc2.name] press [npc2.namePos] [npc2.breasts] together, enveloping [npc.namePos] [npc.cock+] in [npc2.namePos] pillowy mounds as [npc2.name] give [npc.herHim] a titfuck."));
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to try and [npc2.verb(push)] [npc2.namePos] [npc2.breastSize] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] torso up and down,"
									+ " [npc2.moaning+] as [npc2.name] [npc2.verb(help)] [npc.herHim] to fuck what little cleavage [npc2.name] have.",

							"Trying to press [npc2.namePos] [npc2.breastSize] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] use [npc2.namePos] [npc2.fingers+] to help create a structure for [npc.herHim] to thrust into,"
									+ " before [npc2.moaning] in delight as [npc2.name] lift [npc2.herself] up and down to get [npc.herHim] to fuck [npc2.namePos] small cleavage.",

							"Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together, using [npc2.namePos] [npc2.fingers+] to help to envelop [npc.namePos] [npc.cock+] as [npc2.name] give [npc.herHim] a titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] torso up and down, [npc2.moaning+] as [npc2.name] [npc2.verb(thrust)] out [npc2.namePos] chest and [npc2.verb(grind)] against [npc.herHim].",

							"Wrapping [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+], [npc2.name] lift [npc2.herself] up and down, grinding [npc2.namePos] chest against [npc.herHim] as [npc2.name] imitate giving [npc.herHim] a titfuck.",

							"Letting out [npc2.a_moan+], [npc2.name] wrap [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+] and [npc2.verb(thrust)] [npc2.namePos] chest out, before lifting [npc2.herself] up and down to give [npc.herHim] an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_PERFORMING_COCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Eager paizuri";
			} else {
				return "Eager naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Eagerly pleasure [npc.namePos] [npc.cock+] with [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Eagerly pleasure [npc.namePos] [npc.cock+] with [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to eagerly [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] rapidly bucking [npc2.namePos] torso up and down, [npc2.moaning+] as [npc2.name] [npc2.verb(help)] [npc.herHim] to fuck [npc2.namePos] cleavage.",

						"Eagerly pressing [npc2.namePos] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] rapidly lifting them up and down, helping [npc.herHim] to fuck [npc2.namePos] cleavage as [npc2.name] [npc2.moanVerb] in delight.",

						"Letting out [npc2.a_moan+], [npc2.name] press [npc2.namePos] [npc2.breasts] together, enveloping [npc.namePos] [npc.cock+] in [npc2.namePos] pillowy mounds as [npc2.name] give [npc.herHim] an enthusiastic titfuck."));
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to desperately [npc2.verb(try)] and [npc2.verb(push)] [npc2.namePos] [npc2.breastSize] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] rapidly [npc2.verb(start)] bucking [npc2.namePos] torso up and down,"
									+ " [npc2.moaning+] as [npc2.name] enthusiastically [npc2.verb(help)] [npc.herHim] to fuck what little cleavage [npc2.name] have.",

							"Eagerly trying to press [npc2.namePos] [npc2.breastSize] [npc2.breasts+] together around [npc.namePos] [npc.cock+], [npc2.name] use [npc2.namePos] [npc2.fingers+] to help create a structure for [npc.herHim] to thrust into,"
									+ " before [npc2.moaning] in delight as [npc2.name] rapidly lift [npc2.herself] up and down to get [npc.herHim] to fuck [npc2.namePos] small cleavage.",

							"Letting out [npc2.a_moan+], [npc2.name] desperately [npc2.verb(try)] to press [npc2.namePos] [npc2.breastSize] [npc2.breasts] together, using [npc2.namePos] [npc2.fingers+] to help to envelop [npc.namePos] [npc.cock+] as [npc2.name] give [npc.herHim] an enthusiastic titfuck."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(start)] rapidly bucking [npc2.namePos] torso up and down, [npc2.moaning+] as [npc2.name] [npc2.verb(thrust)] out [npc2.namePos] chest and eagerly [npc2.verb(grind)] against [npc.herHim].",

							"Eagerly wrapping [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+], [npc2.name] rapidly lift [npc2.herself] up and down, grinding [npc2.namePos] chest against [npc.herHim] as [npc2.name] imitate giving [npc.herHim] a titfuck.",

							"Letting out [npc2.a_moan+], [npc2.name] wrap [npc2.namePos] [npc2.fingers+] around [npc.namePos] [npc.cock+] and eagerly [npc2.verb(thrust)] [npc2.namePos] chest out, before lifting [npc2.herself] up and down to give [npc.herHim] an imitation titfuck"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Resist paizuri";
			} else {
				return "Resist naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Try and [npc2.verb(pull)] [npc2.namePos] [npc2.breasts+] away from [npc.namePos] [npc.cock+].";
			} else {
				return "Try and [npc2.verb(pull)] [npc2.namePos] chest away from [npc.namePos] [npc.cock+].";
			}
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"You [npc2.verb(let)] out [npc2.moan+] as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] [npc2.breasts+] away from [npc.namePos] [npc.cock+], before begging for [npc.herHim] to leave [npc2.name] alone.",

						"With [npc2.a_moan+], [npc2.name] weakly [npc2.verb(try)] to pull away from [npc.name], sobbing in distress as [npc.her] [npc.cock+] [npc.verb(continue)] to thrust up between [npc2.namePos] [npc2.breasts+].",

						"Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(try)] to push [npc.name] away from [npc2.herHim], tears running down [npc2.namePos] cheeks as [npc.she] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] into [npc2.namePos] cleavage."));
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(let)] out [npc2.moan+] as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] [npc2.breastSize] [npc2.breasts] away from [npc.namePos] [npc.cock+], before begging for [npc2.name] to leave [npc.herHim] alone.",

							"With [npc2.a_moan+], [npc2.name] weakly [npc2.verb(try)] to pull away from [npc.name], sobbing in distress as [npc.her] [npc.cock+] [npc.verb(continue)] to thrust up between [npc2.namePos] [npc2.breastSize] [npc2.breasts+].",

							"Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(try)] to push [npc.name] away from [npc2.herHim], tears running down [npc2.namePos] cheeks as [npc.she] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] into [npc2.namePos] small cleavage."));
							
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(let)] out [npc2.moan+] as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] chest away from [npc.namePos] [npc.cock+], before begging for [npc2.name] to leave [npc.herHim] alone.",

							"With [npc2.a_moan+], [npc2.name] weakly [npc2.verb(try)] to pull away from [npc.name], sobbing in distress as [npc.her] [npc.cock+] [npc.verb(continue)] to grind up against [npc2.namePos] chest.",

							"Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(try)] to push [npc.name] away from [npc2.herHim], tears running down [npc2.namePos] cheeks as [npc.she] [npc.verb(continue)] thrusting [npc.her] [npc.cock+] against [npc2.namePos] chest."));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Stop paizuri";
			} else {
				return "Stop naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "[npc2.verb(push)] [npc.namePos] [npc.cock] away from [npc2.namePos] [npc2.breasts+].";
			} else {
				return "[npc2.verb(push)] [npc.namePos] [npc.cock] away from [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly [npc2.verb(push)] [npc.name] away from [npc2.herHim], and, in a menacing tone, [npc2.verb(order)] [npc.herHim] to stop fucking [npc2.namePos] [npc2.breasts+].",

								"With a menacing growl, [npc2.name] roughly [npc2.verb(push)] [npc.name] away, and [npc2.verb(order)] [npc.her] to stop fucking [npc2.namePos] [npc2.breasts+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You [npc2.verb(push)] [npc.name] away from [npc2.herHim], and tell [npc.herHim] to stop fucking [npc2.namePos] [npc2.breasts+].",

								"With one last [npc2.moan], [npc2.name] [npc2.verb(push)] [npc.name] away, and tell [npc.herHim] to stop fucking [npc2.namePos] [npc2.breasts+]."));
						break;
				}
			} else {
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly [npc2.verb(push)] [npc.name] away from [npc2.herHim], and, in a menacing tone, [npc2.verb(order)] [npc.herHim] to stop grinding against [npc2.namePos] chest.",

								"With a menacing growl, [npc2.name] roughly [npc2.verb(push)] [npc.name] away, and [npc2.verb(order)] [npc.herHim] to stop grinding against [npc2.namePos] chest."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You [npc2.verb(push)] [npc.name] away from [npc2.herHim], and tell [npc.herHim] to stop grinding against [npc2.namePos] chest.",

								"With one last [npc2.moan], [npc2.name] [npc2.verb(push)] [npc.name] away, and tell [npc.herHim] to stop grinding against [npc2.namePos] chest."));
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] [npc.verb(continue)] struggling against [npc2.herHim], [npc.moaning+] as [npc.she] [npc2.verb(beg)]s [npc2.name] to leave [npc.herHim] alone.",

							" With [npc.a_moan+], [npc.she] [npc2.verb(beg)]s [npc2.name] to leave [npc.herHim] alone, tears welling up in [npc.her] [npc.eyes] as [npc.she] weakly tries to push [npc2.name] away."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc.a_moan+], betraying [npc.her] desire for more of [npc2.namePos] attention.",

							" With [npc.a_moan+], [npc.she] [npc2.verb(beg)]s for [npc2.name] to keep on using [npc.herHim]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
