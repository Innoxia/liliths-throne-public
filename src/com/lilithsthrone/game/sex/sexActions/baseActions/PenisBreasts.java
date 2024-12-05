package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
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
 * @since 0.1.84
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisBreasts {
	
	public static final SexAction FORCE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Thrust into mouth";
		}

		@Override
		public String getActionDescription() {
			return "Thrust your [npc.hips] forwards and force the [npc.cockHead] of your [npc.cock+] into [npc2.namePos] mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Special check for NPCs, as this action can hit penis, breast, and oral fetishes.
			// Positive penis + breast desires can outweigh a negative oral one and thus make NPCs use this action, even though it makes no sense if they hate the oral fetish
			if(!Main.sex.getCharacterPerformingAction().isPlayer()) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isNegative()) {
					return false;
				}
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterPerformingAction().getPenisRawSizeValue()>=6
					&& Main.sex.isOrificeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)
//					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently thrusting forwards between [npc2.namePos] [npc2.breasts],"
									+ " [npc.name] [npc.verb(push)] [npc.her] [npc.cock+] all the way up to [npc2.her] mouth and forces the [npc.cockHead] past [npc2.her] [npc2.lips].",

							"Slowly pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+],"
									+ " pushing all the way until the [npc.cockHead+] pushes past [npc2.her] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly thrusting forwards between [npc2.namePos] [npc2.breasts],"
									+ " [npc.name] [npc.verb(push)] [npc.her] [npc.cock+] all the way up to [npc2.her] mouth and forces the [npc.cockHead] past [npc2.her] [npc2.lips].",

							"Greedily pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+],"
									+ " pushing all the way until the [npc.cockHead+] pushes past [npc2.her] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly thrusting forwards between [npc2.namePos] [npc2.breasts],"
									+ " [npc.name] slams [npc.her] [npc.cock+] up against [npc2.her] mouth and forces the [npc.cockHead] past [npc2.her] [npc2.lips].",

							"Violently slamming [npc.her] [npc.hips] forwards, [npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+],"
									+ " pushing all the way until the [npc.cockHead+] rams past [npc2.her] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Thrusting forwards between [npc2.namePos] [npc2.breasts],"
									+ " [npc.name] [npc.verb(push)] [npc.her] [npc.cock+] all the way up to [npc2.her] mouth and forces the [npc.cockHead] past [npc2.her] [npc2.lips].",

							"Pushing [npc.her] [npc.hips] forwards, [npc.name] forces [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+],"
									+ " pushing all the way until the [npc.cockHead+] pushes past [npc2.her] [npc2.lips+]."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(grin)] at [npc.her] enthusiasm, and, opening [npc2.her] mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a loving suck, [npc2.name] then [npc2.verb(draw)] back,"
										+ " but not before planting a kiss on the very tip.",
	
								" [npc2.Name] [npc2.verb(open)] [npc2.her] mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a hot, wet suck before drawing back to deliver a soft kiss to the very tip."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a happy [npc2.moan], and, eagerly opening [npc2.her] mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a loving suck,"
										+ " [npc2.name] then [npc2.verb(draw)] back, but not before planting a wet kiss on the very tip.",
	
								" [npc2.Name] eagerly [npc2.verb(open)] [npc2.her] mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a hot, wet suck before drawing back to deliver a passionate kiss to the very tip."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, quickly opening [npc2.her] mouth to give the [npc.cockHead] of [npc.her] [npc.cock] a forceful suck,"
										+ " [npc2.name] then [npc2.verb(draw)] back, but not before planting a rough kiss on the very tip.",
	
								" [npc2.Name] quickly [npc2.verb(open)] [npc2.her] mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] a forceful suck before drawing back to deliver a rough kiss to the very tip."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan], and, opening [npc2.her] mouth to give the [npc.cockHead] of [npc.her] [npc.cock] an obedient suck,"
										+ " [npc2.name] then [npc2.verb(draw)] back, but not before planting a quick kiss on the very tip.",
	
								" [npc2.Name] [npc2.verb(open)] [npc2.her] mouth to accept [npc.her] [npc.cock+], giving the [npc.cockHead] an obediently suck before drawing back to deliver a quick kiss to the very tip."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, trying to pull [npc2.her] mouth away from the [npc.cockHead] of [npc.her] [npc.cock],"
										+ " [npc2.name] desperately [npc2.verb(plead)] for [npc.herHim] to leave [npc2.name] alone.",
	
								" [npc2.Name] [npc2.verb(jerk)] [npc2.her] head back, trying to push [npc.her] [npc.cock+] away from [npc2.her] mouth as tears [npc2.verb(start)] to well up in [npc2.her] [npc2.eyes]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH);
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
			if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
			}
			return null;
		}
		
	};
	
	public static final SexAction TAKE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Paizuri into mouth";
			} else {
				return "Naizuri into mouth";
			}
		}

		@Override
		public String getActionDescription() {
			return "Push your head forwards and take the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] into your mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Special check for NPCs, as this action can hit penis, breast, and oral fetishes.
			// Positive penis + breast desires can outweigh a negative oral one and thus make NPCs use this action, even though it makes no sense if they hate the oral fetish
			if(!Main.sex.getCharacterPerformingAction().isPlayer()) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ORAL_GIVING).isNegative()) {
					return false;
				}
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).getPenisRawSizeValue()>=6
					&& Main.sex.isOrificeFree(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)
//					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.SIXTY_NINE);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pushing [npc.her] [npc.face] down towards [npc2.namePos] [npc2.cock] as it slides up between [npc.her] [npc.breasts],"
									+ " [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] and [npc.verb(take)] the [npc2.cockHead] into [npc.her] mouth.",

							"Slowly pushing [npc.her] [npc.face] down,"
									+ " [npc.name] [npc.verb(take)] the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] into [npc.her] mouth as [npc2.she] [npc2.verb(thrust)] up between [npc.her] [npc.breasts+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc.her] [npc.face] down towards [npc2.namePos] [npc2.cock] as it slides up between [npc.her] [npc.breasts],"
									+ " [npc.name] greedily [npc.verb(part)] [npc.her] [npc.lips+] and [npc.verb(take)] the [npc2.cockHead] into [npc.her] mouth.",

							"Eagerly pushing [npc.her] [npc.face] down,"
									+ " [npc.name] greedily [npc.verb(take)] the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] into [npc.her] mouth as [npc2.she] [npc2.verb(thrust)] up between [npc.her] [npc.breasts+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] down towards [npc2.namePos] [npc2.cock] as it slides up between [npc.her] [npc.breasts],"
									+ " [npc.name] greedily [npc.verb(part)] [npc.her] [npc.lips+] and [npc.verb(take)] the [npc2.cockHead] into [npc.her] mouth.",

							"Pushing [npc.her] [npc.face] down,"
									+ " [npc.name] greedily [npc.verb(take)] the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] into [npc.her] mouth as [npc2.she] [npc2.verb(thrust)] up between [npc.her] [npc.breasts+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] down towards [npc2.namePos] [npc2.cock] as it slides up between [npc.her] [npc.breasts],"
									+ " [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] and [npc.verb(take)] the [npc2.cockHead] into [npc.her] mouth.",

							"Pushing [npc.her] [npc.face] down,"
									+ " [npc.name] [npc.verb(take)] the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] into [npc.her] mouth as [npc2.she] [npc2.verb(thrust)] up between [npc.her] [npc.breasts+]."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(smile)] at [npc.her] enthusiasm, and, gently pushing [npc2.her] [npc2.cock] into [npc.her] mouth,"
										+ " [npc2.she] [npc2.verb(let)] [npc.herHim] suck and kiss the [npc2.cockHead] for a moment, before pulling back and continuing to fuck [npc.her] [npc.breasts+].",
	
								" [npc2.Name] slowly [npc2.verb(push)] [npc2.her] [npc2.cock+] into [npc.her] mouth,"
										+ " allowing [npc.herHim] to give the [npc2.cockHead] a hot, wet suck before drawing back and continuing to fuck [npc.her] [npc.breasts+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(grin)] at [npc2.her] enthusiasm, and, eagerly pushing [npc2.her] [npc2.cock] into [npc.namePos] mouth,"
										+ " [npc2.she] [npc2.verb(let)] [npc.herHim] suck and kiss the [npc2.cockHead] for a moment, before pulling back and continuing to fuck [npc.her] [npc.breasts+].",
	
								" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] [npc2.cock+] into [npc.her] mouth,"
										+ " allowing [npc.herHim] to give the [npc2.cockHead] a hot, wet suck before drawing back and continuing to fuck [npc.her] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(grin)] at [npc2.her] enthusiasm, and, roughly forcing [npc2.her] [npc2.cock] deeper into [npc.namePos] mouth,"
										+ " [npc2.she] allows [npc.herHim] to suck and kiss the [npc2.cockHead] for a moment, before pulling back and continuing to aggressively fuck [npc.her] [npc.breasts+].",
	
								" [npc2.Name] roughly [npc2.verb(force)] [npc2.her] [npc2.cock+] into [npc.her] mouth,"
										+ " allowing [npc.herHim] to give the [npc2.cockHead] a hot, wet suck before drawing back and continuing to aggressively fuck [npc.namePos] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan], and, pushing [npc2.her] [npc2.cock] into [npc.namePos] mouth, [npc2.she] gasps as [npc.name] suck and kiss the [npc2.cockHead] for a moment,"
										+ " before allowing [npc2.herHim] to pull back and [npc2.verb(continue)] to fuck [npc.her] [npc.breasts+].",
	
								" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.cock+] into [npc.her] mouth,"
										+ " gasping as [npc.she] [npc.verb(give)] the [npc2.cockHead] a hot, wet suck before allowing [npc2.herHim] to pull back and [npc2.verb(continue)] to fuck [npc.her] [npc.breasts+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc.a_moan+], and, trying to pull [npc2.her] [npc2.cock] away from [npc.namePos] mouth,"
										+ " [npc2.she] desperately [npc.verb(plead)] for [npc.herHim] to leave [npc2.herHim] alone.",
	
								" [npc2.Name] [npc2.verb(try)] to pull [npc2.her] [npc2.hips] back, but [npc.name] [npc.verb(hold)] [npc2.herHim] in place,"
										+ " sucking on the [npc2.cockHead] of [npc2.her] [npc2.cock+] for a moment before finally letting [npc2.herHim] pull away."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
			}
			if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
			return null;
		}
		
	};
	
	
	public static final SexAction FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Start paizuri";
			} else {
				return "Start naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Slide your [npc.cock+] between [npc2.namePos] [npc2.breasts+] and start fucking them.";
			} else {
				return "Start grinding your [npc.cock+] over [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of [npc2.namePos] [npc2.breasts+], [npc.name] gently [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.her] cleavage before sliding forwards and starting to fuck [npc2.her] [npc2.breasts]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to greedily sink [npc.her] [npc.fingers] into [npc2.namePos] [npc2.breasts+], [npc.name] eagerly [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.her] cleavage before sliding forwards and starting to enthusiastically fuck [npc2.her] [npc2.breasts]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to roughly sink [npc.her] [npc.fingers] into [npc2.namePos] [npc2.breasts+], [npc.name] forcefully [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.her] cleavage before slamming forwards and starting to rapidly fuck [npc2.her] [npc2.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of [npc2.namePos] [npc2.breasts+], [npc.name] then [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.her] cleavage before sliding forwards and starting to fuck [npc2.her] [npc2.breasts]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to greedily sink [npc.her] [npc.fingers] into [npc2.namePos] [npc2.breasts+], [npc.name] eagerly [npc.verb(push)] them together,"
										+ " lining [npc.her] [npc.cock] up to [npc2.her] cleavage before sliding forwards and starting to enthusiastically fuck [npc2.her] [npc2.breasts]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response,"
											+ " reaching up to help push [npc2.her] [npc2.breasts] together as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " quickly reaching up to help push [npc2.her] [npc2.breasts] together as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " reaching up to forcefully press [npc2.her] [npc2.breasts] together as [npc2.she] dominantly [npc2.verb(order)] [npc.herHim] to keep going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " quickly reaching up to help push [npc2.her] [npc2.breasts] together as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan] in response,"
											+ " before reaching up to help push [npc2.her] [npc2.breasts] together as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " reaching up to weakly try and push [npc.herHim] away from [npc2.her] [npc2.breasts] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to take hold of [npc2.namePos] [npc2.breasts+], [npc.name] gently [npc.verb(try)] to push them together,"
										+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.she] [npc2.has] before sliding forwards and starting to grind down over [npc2.her] chest."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to squeeze and grope [npc2.namePos] [npc2.breasts+], [npc.name] [npc.do] [npc.her] best to try to push them together,"
										+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.she] [npc2.has] before sliding forwards and starting to eagerly grind down over [npc2.her] chest."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to roughly squeeze and grope [npc2.namePos] [npc2.breasts+], [npc.name] [npc.do] [npc.her] best to try to push them together,"
										+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.she] [npc2.has] before sliding forwards and starting to forcefully grind down over [npc2.her] chest."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to squeeze and grope [npc2.namePos] [npc2.breasts+], [npc.name] [npc.do] [npc.her] best to try to push them together,"
										+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.she] [npc2.has] before sliding forwards and starting to grind down over [npc2.her] chest."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to squeeze and grope [npc2.namePos] [npc2.breasts+], [npc.name] [npc.do] [npc.her] best to try to push them together,"
										+ " lining [npc.her] [npc.cock] up to what little cleavage [npc2.she] [npc2.has] before sliding forwards and starting to eagerly grind down over [npc2.her] chest."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, reaching up to try and help push [npc2.her] [npc2.breastSize] [npc2.breasts] together"
											+ " as [npc2.she] [npc2.verb(encourage)] [npc.name] to fuck [npc2.her] breasts."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly reaching up to try and help push [npc2.her] [npc2.breastSize] [npc2.breasts] together"
											+ " as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to fuck [npc2.her] breasts."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, reaching up to forcefully try and [npc.verb(press)] [npc2.her] [npc2.breastSize] [npc2.breasts] together"
											+ " as [npc2.she] dominantly [npc2.verb(order)] [npc.herHim] to fuck [npc2.her] breasts."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly reaching up to try and help push [npc2.her] [npc2.breastSize] [npc2.breasts] together"
											+ " as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to fuck [npc2.her] breasts."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan] in response, before reaching up to try and help push [npc2.her] [npc2.breastSize] [npc2.breasts] together"
											+ " as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to fuck [npc2.her] breasts."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " reaching up to weakly try and push [npc.herHim] away from [npc2.her] [npc2.breastSize] [npc2.breasts] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to stop."));
							break;
						default:
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.her] chest,"
										+ " before sliding forwards and starting to grind down against [npc2.her] body."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.her] chest,"
										+ " before sliding forwards and starting to eagerly grind down against [npc2.her] body."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to roughly [npc.verb(press)] [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.her] chest,"
										+ " before sliding forwards and starting to forcefully grind down against [npc2.her] body."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.her] chest,"
										+ " before sliding forwards and starting to grind down against [npc2.her] body."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to press [npc.her] [npc.hands] against [npc2.namePos] torso, [npc.name] repositions [npc.herself] to line [npc.her] [npc.cock] up over [npc2.her] chest,"
										+ " before sliding forwards and starting to eagerly grind down against [npc2.her] body."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, pushing [npc2.her] chest out as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly pushing [npc2.her] chest out as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, forcefully pushing [npc2.her] chest out as [npc2.she] dominantly [npc2.verb(order)] [npc.herHim] to keep going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, pushing [npc2.her] chest out as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan] in response, pushing [npc2.her] chest out as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
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
			if(Main.sex.getCharacterTargetedForSexAction(action).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] [npc2.breasts+] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking [npc2.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, eagerly pushing [npc2.her] [npc2.breastSize] [npc2.breasts] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(wrap)] [npc2.her] [npc2.breasts+] around [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] pillowy mounds."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to leave [npc2.her] [npc2.breasts] alone.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.her] [npc2.breasts] alone.",
			
								" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
										+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] [npc2.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.breasts+] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking [npc2.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, pushing [npc2.her] [npc2.breastSize] [npc2.breasts] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(wrap)] [npc2.her] [npc2.breasts+] around [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] pillowy mounds."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(push)] [npc2.her] [npc2.breasts+] together in response,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking [npc2.her] cleavage.",
			
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, gently pushing [npc2.her] [npc2.breastSize] [npc2.breasts] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(wrap)] [npc2.her] [npc2.breasts+] around [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] pillowy mounds."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] roughly [npc2.verb(press)] [npc2.her] [npc2.breasts+] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(order)] [npc.name] to continue fucking [npc2.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, forcefully pressing [npc2.her] [npc2.breastSize] [npc2.breasts] together,"
										+ " [npc2.she] [npc2.verb(order)] [npc.name] to continue thrusting [npc.her] [npc.cock+] up and down between [npc2.her] cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] dominantly [npc2.verb(wrap)] [npc2.her] [npc2.breasts+] around [npc.namePos] [npc.cock+],"
										+ " before ordering [npc.herHim] to continue fucking [npc2.her] pillowy mounds."));
						break;
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(action).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(attempt)] to push [npc2.her] tiny [npc2.breasts] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking what little cleavage [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, eagerly attempting to push [npc2.her] [npc2.breastSize] [npc2.breasts] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] tiny cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(push)] [npc2.her] [npc2.breasts+] against the sides of [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] diminutive cleavage."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to leave [npc2.her] [npc2.breasts] alone.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.her] [npc2.breasts] alone.",
			
								" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
										+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] [npc2.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)] to push [npc2.her] [npc2.breasts+] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking what little cleavage [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, pushing [npc2.her] [npc2.breastSize] [npc2.breasts] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] tiny cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.her] [npc2.breasts+] against the sides of [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] diminutive cleavage."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)] to gently push [npc2.her] [npc2.breasts+] together in response,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue fucking what little cleavage [npc2.she] [npc2.has].",
			
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, gently pushing [npc2.her] [npc2.breastSize] [npc2.breasts] together,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down between [npc2.her] tiny cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.breasts+] against the sides of [npc.namePos] [npc.cock+],"
										+ " before begging for [npc.herHim] to continue fucking [npc2.her] diminutive cleavage."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)] to roughly push [npc2.verb(press)] [npc2.her] [npc2.breasts+] together in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(order)] [npc.name] to continue fucking what little cleavage [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, forcefully pressing [npc2.her] [npc2.breastSize] [npc2.breasts] together,"
										+ " [npc2.she] [npc2.verb(order)] [npc.name] to continue thrusting [npc.her] [npc.cock+] up and down between [npc2.her] tiny cleavage.",
			
								" [npc2.Moaning] in delight, [npc2.name] dominantly [npc2.verb(press)] [npc2.her] [npc2.breasts+] against the sides of [npc.namePos] [npc.cock+],"
										+ " before ordering [npc.herHim] to continue fucking [npc2.her] diminutive cleavage."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] flat chest out in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue humping [npc2.her] torso.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, eagerly pushing [npc2.her] flat chest out,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down over [npc2.her] torso.",
			
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.does] [npc2.her] best to try and [npc2.verb(wrap)] [npc2.her] flat chest around [npc.namePos] [npc.cock+],"
										+ " before giving up and begging for [npc.herHim] to continue humping [npc2.her] torso."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to leave [npc2.her] [npc2.breasts] alone.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.her] [npc2.breasts] alone.",
			
								" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
										+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] [npc2.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(push)] [npc2.her] flat chest out in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue humping [npc2.her] torso.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, pushing [npc2.her] flat chest out,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down over [npc2.her] torso.",
	
								" [npc2.Moaning] in delight, [npc2.name] [npc2.does] [npc2.her] best to try and [npc2.verb(wrap)] [npc2.her] flat chest around [npc.namePos] [npc.cock+],"
										+ " before giving up and begging for [npc.herHim] to continue humping [npc2.her] torso."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(push)] [npc2.her] flat chest out in response,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(encourage)] [npc.name] to continue humping [npc2.her] torso.",
			
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, gently pushing [npc2.her] flat chest out,"
										+ " [npc2.she] [npc2.verb(encourage)] [npc.name] to continue sliding [npc.her] [npc.cock+] up and down over [npc2.her] torso.",
	
								" [npc2.Moaning] in delight, [npc2.name] [npc2.does] [npc2.her] best to try and gently [npc2.verb(wrap)] [npc2.her] flat chest around [npc.namePos] [npc.cock+],"
										+ " before giving up and begging for [npc.herHim] to just continue humping [npc2.her] torso."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] roughly [npc2.verb(thrust)] [npc2.her] flat chest out in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(order)] [npc.name] to continue humping [npc2.her] torso.",
			
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, forcefully pushing [npc2.her] flat chest out,"
										+ " [npc2.she] [npc2.verb(order)] [npc.name] to continue thrusting [npc.her] [npc.cock+] up and down over [npc2.her] torso.",
	
								" [npc2.Moaning] in delight, [npc2.name] [npc2.does] [npc2.her] best to try and roughly [npc2.verb(wrap)] [npc2.her] flat chest around [npc.namePos] [npc.cock+],"
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Gentle paizuri";
			} else {
				return "Gentle naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Gently fuck [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Gently grind your [npc.cock+] against [npc2.namePos] flat chest.";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently sliding [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] steadily bucking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(fuck)] [npc2.her] cleavage.",

						"Gently pushing [npc.her] [npc.cock+] between the cleavage formed between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] softly thrusting [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

						"Softly pushing [npc2.namePos] [npc2.breasts+] together, [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] slowly [npc.verb(fuck)] [npc2.her] cleavage."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently sliding [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.breasts],"
								+ " [npc.name] [npc.verb(start)] steadily bucking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Gently pushing [npc.her] [npc.cock+] between the tiny amount of cleavage formed between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] softly thrusting [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(grind)] up and down over [npc2.her] chest.",

						"Softly trying to push [npc2.namePos] [npc2.breastSize] [npc2.breasts] together, [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] slowly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] chest."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently sliding [npc.her] [npc.cock+] over [npc2.namePos] flat chest,"
								+ " [npc.name] [npc.verb(start)] steadily bucking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(grind)] against [npc2.her] torso.",

						"Gently pushing [npc.her] [npc.cock+] down against [npc2.namePos] flat [npc2.breasts],"
								+ " [npc.name] [npc.verb(start)] softly thrusting [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(grind)] up and down over [npc2.her] chest.",

						"Softly groping [npc2.namePos] flat chest, [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Continue fucking [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Continue grinding against [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] cleavage.",

						"Desperately pushing [npc.her] [npc.cock+] between the cleavage formed between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

						"Greedily pushing [npc2.namePos] [npc2.breasts+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] eagerly [npc.verb(fuck)] [npc2.her] cleavage."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.breasts],"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Desperately pushing [npc.her] [npc.cock+] between the tiny amount of cleavage formed between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] chest.",

						"Greedily trying to push [npc2.namePos] [npc2.breastSize] [npc2.breasts] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] eagerly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] chest."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] over [npc2.namePos] flat chest,"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(grind)] against [npc2.her] torso.",

						"Desperately pushing [npc.her] [npc.cock+] down against [npc2.namePos] flat [npc2.breasts],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] chest.",

						"Greedily groping [npc2.namePos] flat chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Rough paizuri";
			} else {
				return "Rough naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Roughly fuck [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Roughly grind your [npc.cock+] against [npc2.namePos] flat chest.";
			}
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Roughly slamming [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] violently bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] forcefully [npc.verb(fuck)] [npc2.her] cleavage.",

						"Violently pushing [npc.her] [npc.cock+] between the cleavage formed between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] roughly thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] dominantly [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

						"Greedily pushing [npc2.namePos] [npc2.breasts+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to dominantly pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] roughly [npc.verb(fuck)] [npc2.her] cleavage."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Roughly slamming [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.breasts],"
								+ " [npc.name] [npc.verb(start)] violently bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] forcefully [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Violently pushing [npc.her] [npc.cock+] between the tiny amount of cleavage formed between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] roughly thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] dominantly [npc.verb(grind)] up and down over [npc2.her] chest.",

						"Greedily trying to push [npc2.namePos] [npc2.breastSize] [npc2.breasts] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to dominantly pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] roughly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] chest."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Roughly pushing [npc.her] [npc.cock+] over [npc2.namePos] flat chest,"
								+ " [npc.name] [npc.verb(start)] violently bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] forcefully [npc.verb(grind)] against [npc2.her] torso.",

						"Violently pushing [npc.her] [npc.cock+] down against [npc2.namePos] flat [npc2.breasts],"
								+ " [npc.name] [npc.verb(start)] roughly thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] dominantly [npc.verb(grind)] up and down over [npc2.her] chest.",

						"Greedily groping [npc2.namePos] flat chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to dominantly pump [npc.her] [npc.hips] back and forth,"
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Paizuri";
			} else {
				return "Naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Continue fucking [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Continue grinding against [npc2.namePos] flat chest.";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Sliding [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] [npc.verb(fuck)] [npc2.her] cleavage.",

						"Pushing [npc.her] [npc.cock+] between the cleavage formed between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

						"Pushing [npc2.namePos] [npc2.breasts+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(fuck)] [npc2.her] cleavage."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Sliding [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.breasts],"
								+ " [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Pushing [npc.her] [npc.cock+] between the tiny amount of cleavage formed between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] chest.",

						"Trying to push [npc2.namePos] [npc2.breastSize] [npc2.breasts] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] chest."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Sliding [npc.her] [npc.cock+] over [npc2.namePos] flat chest,"
								+ " [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] [npc.verb(grind)] against [npc2.her] torso.",

						"Pushing [npc.her] [npc.cock+] down against [npc2.namePos] flat [npc2.breasts],"
								+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] chest.",

						"Groping [npc2.namePos] flat chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Eager paizuri";
			} else {
				return "Eager naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Eagerly fuck [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Eagerly grind against [npc2.namePos] flat chest.";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] cleavage.",

						"Desperately pushing [npc.her] [npc.cock+] between the cleavage formed between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

						"Greedily pushing [npc2.namePos] [npc2.breasts+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] eagerly [npc.verb(fuck)] [npc2.her] cleavage."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.breasts],"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Desperately pushing [npc.her] [npc.cock+] between the tiny amount of cleavage formed between [npc2.namePos] [npc2.breasts+],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] chest.",

						"Greedily trying to push [npc2.namePos] [npc2.breastSize] [npc2.breasts] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.her] [npc2.scent] as [npc.she] eagerly [npc.verb(grind)] [npc.her] [npc.cock+] over [npc2.her] chest."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sliding [npc.her] [npc.cock+] over [npc2.namePos] flat chest,"
								+ " [npc.name] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(grind)] against [npc2.her] torso.",

						"Desperately pushing [npc.her] [npc.cock+] down against [npc2.namePos] flat [npc2.breasts],"
								+ " [npc.name] [npc.verb(start)] energetically thrusting [npc.her] [npc.hips] forwards, letting out [npc.moans+] as [npc.she] happily [npc.verb(grind)] up and down over [npc2.her] chest.",

						"Greedily groping [npc2.namePos] flat chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to frantically pump [npc.her] [npc.hips] back and forth,"
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Resist giving paizuri";
			} else {
				return "Resist giving naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Try to pull your [npc.cock] away from [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Try to pull your [npc.cock] away from [npc2.namePos] chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] cleavage, but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " pressing [npc2.her] [npc2.breasts+] together while gently reminding [npc.herHim] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.breasts+], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " softly [npc2.moaning] as [npc2.she] [npc2.verb(ignore)] [npc.her] desperate protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of [npc2.namePos] cleavage, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] softly [npc2.moaning] as [npc2.she] firmly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] cleavage, but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " pressing [npc2.her] [npc2.breasts+] together while growling that [npc2.she]'ll use [npc.herHim] however [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.breasts+], but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of [npc2.namePos] cleavage, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] roughly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.breasts+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] cleavage, but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " pressing [npc2.her] [npc2.breasts+] together while [npc2.moaning] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.breasts+], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of [npc2.namePos] cleavage, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] eagerly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.breasts+]."));
						break;
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of the small amount of cleavage that [npc2.name] [npc2.has], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " trying to press [npc2.her] [npc2.breasts+] together while gently reminding [npc.herHim] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.breastSize] [npc2.breasts], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " softly [npc2.moaning] as [npc2.she] [npc2.verb(ignore)] [npc.her] desperate protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of the tiny amount of cleavage that [npc2.name] have on offer, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] softly [npc2.moaning] as [npc2.she] firmly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of the small amount of cleavage that [npc2.name] [npc2.has], but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " trying to press [npc2.her] [npc2.breasts+] together while growling that [npc2.she]'ll use [npc.herHim] however [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.breastSize] [npc2.breasts], but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of the tiny amount of cleavage that [npc2.name] have on offer, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] roughly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.breasts+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] out of the small amount of cleavage that [npc2.name] [npc2.has], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " trying to press [npc2.her] [npc2.breasts+] together while [npc2.moaning] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] [npc2.breastSize] [npc2.breasts], but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull out of the tiny amount of cleavage that [npc2.name] have on offer, but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] eagerly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] [npc2.breasts+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] away from [npc2.namePos] flat chest, but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " grinding against [npc.herHim] as [npc2.she] gently [npc2.moanVerb] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] chest, but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " softly [npc2.moaning] as [npc2.she] [npc2.verb(ignore)] [npc.her] desperate protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull away from [npc2.name], but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] softly [npc2.moaning] as [npc2.she] firmly [npc2.verb(force)] [npc.her] [npc.cock+] against [npc2.her] chest."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] away from [npc2.namePos] chest, but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " forcefully grinding against [npc.herHim] as [npc2.she] [npc2.verb(growl)] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] chest, but [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull away from [npc2.name], but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] roughly [npc2.verb(force)] [npc.namePos] [npc.cock+] against [npc2.her] chest."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.cock+] away from [npc2.namePos] chest, but [npc2.name] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " grinding against [npc.herHim] as [npc2.she] [npc2.moanVerb] that [npc2.she]'ll do whatever [npc2.she] [npc2.verb(want)].",

								"[npc.Name] frantically [npc.verb(try)] to pull away from [npc2.namePos] chest, but [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
										+ " [npc2.moaning+] as [npc2.she] [npc2.verb(ignore)] [npc.her] futile protests.",

								"Tears start to well up in [npc.namePos] [npc.eyes] as [npc.she] [npc.verb(try)] to pull away from [npc2.name], but [npc2.her] grip is too strong,"
										+ " and [npc2.she] [npc2.verb(continue)] [npc2.moaning+] as [npc2.she] eagerly [npc2.verb(force)] [npc.her] [npc.cock+] against [npc2.her] chest."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Stop receiving paizuri";
			} else {
				return "Stop receiving naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Pull your [npc.cock+] away from [npc2.namePos] [npc2.breasts+] and stop fucking them.";
			} else {
				return "Pull your [npc.cock+] away from [npc2.namePos] chest and stop grinding against [npc2.herHim].";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pushing [npc2.name] away,"
										+ " [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.her] cleavage and [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.breasts+].",

								"Roughly pulling [npc.her] [npc.cock+] out from [npc2.namePos] cleavage, [npc.name] [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.breasts+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.namePos] cleavage and [npc.verb(tell)] [npc2.name] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.breasts+].",

								"Pulling [npc.her] [npc.cock+] out from [npc2.namePos] cleavage, [npc.name] [npc.verb(tell)] [npc2.name] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.breasts+]."));
						break;
				}
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pushing [npc2.name] away, [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.her] tiny amount of cleavage"
										+ " and [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.breastSize] [npc2.breasts].",

								"Roughly pulling [npc.her] [npc.cock+] out from [npc2.namePos] tiny amount of cleavage,"
										+ " [npc.name] [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.breastSize] [npc2.breasts]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from [npc2.namePos] tiny amount of cleavage"
										+ " and [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.breastSize] [npc2.breasts].",

								"Pulling [npc.her] [npc.cock+] out from [npc2.namePos] tiny amount of cleavage,"
										+ " [npc.name] [npc.verb(tell)] [npc2.name] that [npc.sheHas] had enough of fucking [npc2.her] [npc2.breastSize] [npc2.breasts]."));
						break;
				}
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pushing [npc2.name] away, [npc.name] takes [npc.her] [npc.cock+] away from [npc2.her] chest and [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of grinding against [npc2.herHim].",

								"Roughly pulling [npc.her] [npc.cock+] away from [npc2.namePos] chest, [npc.name] [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of grinding against [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] takes [npc.her] [npc.cock+] away from [npc2.namePos] chest and [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of grinding against [npc2.herHim].",

								"Pulling [npc.her] [npc.cock+] away from [npc2.namePos] chest, [npc.name] [npc.verb(tell)] [npc2.herHim] that [npc.sheHas] had enough of grinding against [npc2.herHim]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Perform paizuri";
			} else {
				return "Perform naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Use [npc2.namePos] [npc2.cock+] to fuck [npc.namePos] [npc.breasts+].";
			} else {
				return "Use [npc2.namePos] [npc2.cock+] to grind against your chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] cleavage, and, sliding forwards,"
								+ " [npc.she] [npc.verb(press)] [npc.her] [npc.breasts+] together and [npc.verb(start)] giving [npc2.herHim] a titfuck."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] cleavage, and, sliding forwards,"
								+ " [npc.she] [npc.verb(press)] [npc.her] [npc.breasts+] together and [npc.verb(start)] giving [npc2.herHim] an enthusiastic titfuck."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grabbing hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(pull)] it up to [npc.her] cleavage, and, sliding forwards,"
								+ " [npc.she] [npc.verb(press)] [npc.her] [npc.breasts+] together and [npc.verb(start)] giving [npc2.herHim] a forceful titfuck."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] cleavage, and, sliding forwards,"
								+ " [npc.she] [npc.verb(press)] [npc.her] [npc.breasts+] together and [npc.verb(start)] giving [npc2.herHim] a titfuck."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] cleavage, and, sliding forwards,"
								+ " [npc.she] [npc.verb(press)] [npc.her] [npc.breasts+] together and [npc.verb(start)] giving [npc2.herHim] an enthusiastic titfuck."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response,"
											+ " helping to push [npc.namePos] [npc.breasts] together as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " eagerly helping to push [npc.namePos] [npc.breasts] together as [npc2.she] enthusiastically [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " roughly pushing [npc.namePos] [npc.breasts] together as [npc2.she] [npc2.verb(demand)] that [npc.she] keep on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response,"
											+ " eagerly helping to push [npc.namePos] [npc.breasts] together as [npc2.she] enthusiastically [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan] in response,"
											+ " helping to push [npc.namePos] [npc.breasts] together as [npc2.she] meekly [npc2.verb(ask)] [npc.herHim] to keep going."));
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
				
			} else if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to what little cleavage [npc.she] [npc.has], and, sliding forwards,"
										+ " [npc.she] [npc.verb(try)] [npc.her] best to press [npc.her] [npc.breastSize] [npc.breasts] together in order to give [npc2.herHim] a titfuck."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to what little cleavage [npc.she] [npc.has], and, sliding forwards,"
										+ " [npc.she] [npc.verb(try)] [npc.her] best to press [npc.her] [npc.breastSize] [npc.breasts] together in order to give [npc2.herHim] an enthusiastic titfuck."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grabbing hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to what little cleavage [npc.she] [npc.has], and, sliding forwards,"
										+ " [npc.she] [npc.verb(try)] [npc.her] best to press [npc.her] [npc.breastSize] [npc.breasts] together in order to give [npc2.herHim] a forceful titfuck."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to what little cleavage [npc.she] [npc.has], and, sliding forwards,"
										+ " [npc.she] [npc.verb(try)] [npc.her] best to press [npc.her] [npc.breastSize] [npc.breasts] together in order to give [npc2.herHim] a titfuck."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to what little cleavage [npc.she] [npc.has], and, sliding forwards,"
										+ " [npc.she] [npc.verb(try)] [npc.her] best to press [npc.her] [npc.breastSize] [npc.breasts] together in order to give [npc2.herHim] an enthusiastic titfuck."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, gently thrusting into [npc.namePos] chest as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, desperately thrusting into [npc.namePos] chest as [npc2.she] enthusiastically [npc.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, roughly thrusting into [npc.namePos] chest as [npc2.she] [npc2.verb(demand)] that [npc.herHim] keep on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly thrusting into [npc.namePos] chest as [npc2.she] enthusiastically [npc.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan] in response, thrusting into [npc.namePos] chest as [npc2.she] meekly [npc2.verb(ask)] [npc.herHim] to keep going."));
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
								"Gently taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] flat chest, and, sliding forwards,"
										+ " [npc.she] [npc.verb(grind)] [npc.her] torso against [npc2.her] [npc2.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] flat chest, and, sliding forwards,"
										+ " [npc.she] enthusiastically [npc.verb(grind)] [npc.her] torso against [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grabbing hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] flat chest, and, sliding forwards,"
										+ " [npc.she] forcefully [npc.verb(grind)] [npc.her] torso against [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] flat chest, and, sliding forwards,"
										+ " [npc.she] [npc.verb(grind)] [npc.her] torso against [npc2.her] [npc2.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly taking hold of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] it up to [npc.her] flat chest, and, sliding forwards,"
										+ " [npc.she] enthusiastically [npc.verb(grind)] [npc.her] torso against [npc2.her] [npc2.cock+]."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a happy little [npc2.moan] in response, gently thrusting into [npc.namePos] chest as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, desperately thrusting into [npc.namePos] chest as [npc2.she] enthusiastically [npc.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, roughly thrusting into [npc.namePos] chest as [npc2.she] [npc2.verb(demand)] that [npc.herHim] keep on going."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly thrusting into [npc.namePos] chest as [npc2.she] enthusiastically [npc.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan] in response, thrusting into [npc.namePos] chest as [npc2.she] meekly [npc2.verb(ask)] [npc.herHim] to keep going."));
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
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.breasts+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(fuck)] [npc.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.breasts+].",
										
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] in and out between [npc.namePos] [npc.breasts+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.breasts+],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] back and forth between [npc.her] [npc.breasts+].",
			
								" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.breasts+]."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] between [npc.namePos] [npc.breasts+],"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(fuck)] [npc.her] cleavage.",
			
								" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] gently thrusting [npc2.her] [npc2.cock+] between [npc.namePos] [npc.breasts+].",
										
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] back and forth between [npc.namePos] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.breasts+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(fuck)] [npc.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] roughly slamming [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.breasts+].",
										
								" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] back and forth between [npc.namePos] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.breasts+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(fuck)] [npc.her] cleavage.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep between [npc.namePos] [npc.breasts+].",
										
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] back and forth between [npc.namePos] [npc.breasts+]."));
						break;
				}
				
			} else if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] into what little cleavage [npc.name] [npc.has] to offer,"
										+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(fuck)] [npc.her] [npc.breasts+].",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] between [npc.namePos] [npc.breastSize] [npc.breasts].",
										
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] in and out of the diminutive cleavage formed between [npc.namePos] [npc.breasts+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.breasts+],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] back and forth between [npc.her] [npc.breasts+].",
			
								" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.breasts+]."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] into what little cleavage [npc.name] [npc.has] to offer,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(fuck)] [npc.her] [npc.breasts+].",
			
								" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] gently thrusting [npc2.her] [npc2.cock+] between [npc.namePos]  [npc.breastSize] [npc.breasts].",
										
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] in and out of the diminutive cleavage formed between [npc.namePos] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] into what little cleavage [npc.name] [npc.has] to offer,"
										+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(fuck)] [npc.her] [npc.breasts+].",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] roughly slamming [npc2.her] [npc2.cock+] between  [npc.breastSize] [npc.breasts].",
										
								" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] in and out of the diminutive cleavage formed between [npc.namePos] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] into what little cleavage [npc.name] [npc.has] to offer,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(fuck)] [npc.her] [npc.breasts+].",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] between  [npc.breastSize] [npc.breasts].",
										
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] in and out of the diminutive cleavage formed between [npc.namePos] [npc.breasts+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] over [npc.namePos] flat chest,"
										+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(grind)] against [npc.her] torso.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] up and down against [npc.namePos] flat chest.",
										
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.her] [npc2.cock+] over [npc.namePos] flat chest."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] flat chest,"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
			
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] back and forth over [npc.her] flat chest.",
			
								" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] flat chest."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] over [npc.namePos] flat chest,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(grind)] against [npc.her] torso.",
			
								" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] gently thrusting [npc2.her] [npc2.cock+] up and down against [npc.namePos] flat chest.",
										
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(grind)] [npc2.her] [npc2.cock+] over [npc.namePos] flat chest."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] over [npc.namePos] flat chest,"
										+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(grind)] against [npc.her] torso.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] roughly slamming [npc2.her] [npc2.cock+] up and down against [npc.namePos] flat chest.",
										
								" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(grind)] [npc2.her] [npc2.cock+] over [npc.namePos] flat chest."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] over [npc.namePos] flat chest,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(grind)] against [npc.her] torso.",
			
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] up and down against [npc.namePos] flat chest.",
										
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.cock+] over [npc.namePos] flat chest."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Gently perform paizuri";
			} else {
				return "Gently perform naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Gently pleasure [npc2.namePos] [npc2.cock+] with your [npc.breasts+].";
			} else {
				return "Gently pleasure [npc2.namePos] [npc2.cock+] with your chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to push [npc.her] [npc.breasts+] together around [npc2.namePos] [npc2.cock+],"
								+ " [npc.name] gently [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, softly [npc.moaning] as [npc.she] [npc.verb(use)] [npc.her] cleavage.",

						"Gently wrapping [npc.her] [npc.breasts+] around [npc2.namePos] [npc2.cock+], [npc.name] slowly [npc.verb(lift)] them up and down,"
								+ " letting out a soft [npc.moan] as [npc.she] lovingly [npc.verb(give)] [npc2.herHim] a titfuck.",

						"Letting out a soft [npc.moan], [npc.name] [npc.verb(push)] [npc.her] [npc.breasts+] together,"
								+ " enveloping [npc2.namePos] [npc2.cock+] in [npc.her] pillowy mounds as [npc.she] [npc.verb(give)] [npc2.herHim] a loving titfuck."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to push [npc.her] [npc.breasts+] against the sides of [npc2.namePos] [npc2.cock+],"
									+ " [npc.name] gently [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, softly [npc.moaning] as [npc.she] [npc.verb(try)] [npc.her] best to use what little cleavage [npc.she] [npc.has].",

							"Gently pressing [npc.her] [npc.breastSize] [npc.breasts] against the sides of [npc2.namePos] [npc2.cock+], [npc.name] slowly [npc.verb(lift)] them up and down,"
									+ " letting out a soft [npc.moan] as [npc.she] lovingly [npc.verb(attempt)] to give [npc2.herHim] a titfuck.",

							"Letting out a soft [npc.moan], [npc.name] [npc.verb(push)] [npc.her] [npc.breasts+] together,"
									+ " trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to wrap [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] gently [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso,"
									+ " softly [npc.moaning] as [npc.she] [npc.verb(thrust)] out [npc.her] flat chest and [npc.verb(grind)] against [npc2.herHim].",

							"Gently wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] [npc.her] torso up and down,"
									+ " grinding [npc.her] flat chest against [npc2.herHim] as [npc.she] [npc.verb(try)] to imitate giving [npc2.herHim] a titfuck.",

							"Letting out a soft [npc.moan], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+],"
									+ " before thrusting [npc.her] flat chest out and giving [npc2.herHim] an imitation titfuck"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Perform paizuri";
			} else {
				return "Perform naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Pleasure [npc2.namePos] [npc2.cock+] with your [npc.breasts+].";
			} else {
				return "Pleasure [npc2.namePos] [npc2.cock+] with your flat chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to happily push [npc.her] [npc.breasts+] together around [npc2.namePos] [npc2.cock+],"
								+ " [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] cleavage to give [npc2.herHim] an eager titfuck.",

						"Eagerly wrapping [npc.her] [npc.breasts+] around [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] them up and down,"
								+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] an enthusiastic titfuck.",

						"Letting out [npc.a_moan+], [npc.name] happily [npc2.verb(push)] [npc.her] [npc.breasts+] together,"
								+ " enveloping [npc2.namePos] [npc2.cock+] in [npc.her] pillowy mounds as [npc.she] [npc.verb(give)] [npc2.herHim] an eager titfuck."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily push [npc.her] [npc.breasts+] against the sides of [npc2.namePos] [npc2.cock+],"
									+ " [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(try)] [npc.her] best to use what little cleavage [npc.she] [npc.has].",

							"Eagerly pressing [npc.her] [npc.breastSize] [npc.breasts] against the sides of [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] them up and down,"
									+ " letting out [npc.a_moan+] as [npc.she] enthusiastically [npc.verb(attempt)] to give [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] happily [npc2.verb(push)] [npc.her] [npc.breasts+] together,"
									+ " trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily wrap [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(thrust)] out [npc.her] flat chest to desperately grind against [npc2.herHim].",

							"Eagerly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] [npc.her] torso up and down,"
									+ " grinding [npc.her] flat chest against [npc2.herHim] as [npc.she] [npc.verb(try)] to imitate giving [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] happily [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+],"
									+ " before thrusting [npc.her] flat chest out and eagerly giving [npc2.herHim] an imitation titfuck"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Roughly perform paizuri";
			} else {
				return "Roughly perform naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Roughly pleasure [npc2.namePos] [npc2.cock+] with your [npc.breasts+].";
			} else {
				return "Roughly pleasure [npc2.namePos] [npc2.cock+] with your flat chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to roughly force [npc.her] [npc.breasts+] together around [npc2.namePos] [npc2.cock+],"
								+ " [npc.name] rapidly [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] cleavage to give [npc2.herHim] a dominant titfuck.",

						"Dominantly wrapping [npc.her] [npc.breasts+] around [npc2.namePos] [npc2.cock+], [npc.name] roughly [npc.verb(bounce)] them up and down,"
								+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] a forceful titfuck.",

						"Letting out [npc.a_moan+], [npc.name] forcefully [npc2.verb(push)] [npc.her] [npc.breasts+] together,"
								+ " enveloping [npc2.namePos] [npc2.cock+] in [npc.her] pillowy mounds as [npc.she] [npc.verb(give)] [npc2.herHim] a dominant titfuck."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to roughly force [npc.her] [npc.breasts+] against the sides of [npc2.namePos] [npc2.cock+],"
									+ " [npc.name] rapidly [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(try)] [npc.her] best to use what little cleavage [npc.she] [npc.has].",

							"Dominantly pressing [npc.her] [npc.breastSize] [npc.breasts] against the sides of [npc2.namePos] [npc2.cock+], [npc.name] roughly [npc.verb(lift)] them up and down,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(attempt)] to give [npc2.herHim] a forceful titfuck.",

							"Letting out [npc.a_moan+], [npc.name] forcefully [npc2.verb(push)] [npc.her] [npc.breasts+] together,"
									+ " trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to roughly wrap [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] violently [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(thrust)] out [npc.her] flat chest to dominantly grind against [npc2.herHim].",

							"Dominantly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] roughly [npc.verb(lift)] [npc.her] torso up and down,"
									+ " grinding [npc.her] flat chest against [npc2.herHim] as [npc.she] [npc.verb(try)] to imitate giving [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] forcefully [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+],"
									+ " before thrusting [npc.her] flat chest out and roughly giving [npc2.herHim] an imitation titfuck"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Perform paizuri";
			} else {
				return "Perform naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Pleasure [npc2.namePos] [npc2.cock+] with your [npc.breasts+].";
			} else {
				return "Pleasure [npc2.namePos] [npc2.cock+] with your flat chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to push [npc.her] [npc.breasts+] together around [npc2.namePos] [npc2.cock+],"
								+ " [npc.name] [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] cleavage to give [npc2.herHim] a titfuck.",

						"Wrapping [npc.her] [npc.breasts+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] them up and down,"
								+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] a titfuck.",

						"Letting out [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.breasts+] together,"
								+ " enveloping [npc2.namePos] [npc2.cock+] in [npc.her] pillowy mounds as [npc.she] [npc.verb(give)] [npc2.herHim] a titfuck."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily push [npc.her] [npc.breasts+] against the sides of [npc2.namePos] [npc2.cock+],"
									+ " [npc.name] [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(try)] [npc.her] best to use what little cleavage [npc.she] [npc.has].",

							"Pressing [npc.her] [npc.breastSize] [npc.breasts] against the sides of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] them up and down,"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(attempt)] to give [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.breasts+] together,"
									+ " trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily wrap [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(thrust)] out [npc.her] flat chest to grind against [npc2.herHim].",

							"Wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] [npc.her] torso up and down,"
									+ " grinding [npc.her] flat chest against [npc2.herHim] as [npc.she] [npc.verb(try)] to imitate giving [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+],"
									+ " before thrusting [npc.her] flat chest out and giving [npc2.herHim] an imitation titfuck"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Eagerly perform paizuri";
			} else {
				return "Eagerly perform naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Eagerly pleasure [npc2.namePos] [npc2.cock+] with your [npc.breasts+].";
			} else {
				return "Eagerly pleasure [npc2.namePos] [npc2.cock+] with your flat chest.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Reaching up to happily push [npc.her] [npc.breasts+] together around [npc2.namePos] [npc2.cock+],"
								+ " [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] cleavage to give [npc2.herHim] an eager titfuck.",

						"Eagerly wrapping [npc.her] [npc.breasts+] around [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] them up and down,"
								+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(give)] [npc2.herHim] an enthusiastic titfuck.",

						"Letting out [npc.a_moan+], [npc.name] happily [npc2.verb(push)] [npc.her] [npc.breasts+] together,"
								+ " enveloping [npc2.namePos] [npc2.cock+] in [npc.her] pillowy mounds as [npc.she] [npc.verb(give)] [npc2.herHim] an eager titfuck."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily push [npc.her] [npc.breasts+] against the sides of [npc2.namePos] [npc2.cock+],"
									+ " [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso, [npc.moaning+] as [npc.she] [npc.verb(try)] [npc.her] best to use what little cleavage [npc.she] [npc.has].",

							"Eagerly pressing [npc.her] [npc.breastSize] [npc.breasts] against the sides of [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] them up and down,"
									+ " letting out [npc.a_moan+] as [npc.she] enthusiastically [npc.verb(attempt)] to give [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] happily [npc2.verb(push)] [npc.her] [npc.breasts+] together,"
									+ " trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to happily wrap [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] enthusiastically [npc.verb(raise)] and [npc.verb(lower)] [npc.her] torso,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(thrust)] out [npc.her] flat chest to desperately grind against [npc2.herHim].",

							"Eagerly wrapping [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+], [npc.name] energetically [npc.verb(lift)] [npc.her] torso up and down,"
									+ " grinding [npc.her] flat chest against [npc2.herHim] as [npc.she] [npc.verb(try)] to imitate giving [npc2.herHim] a titfuck.",

							"Letting out [npc.a_moan+], [npc.name] happily [npc.verb(wrap)] [npc.her] [npc.fingers+] around [npc2.namePos] [npc2.cock+],"
									+ " before thrusting [npc.her] flat chest out and eagerly giving [npc2.herHim] an imitation titfuck"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Resist performing paizuri";
			} else {
				return "Resist performing naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Try and pull your [npc.breasts+] away from [npc2.namePos] [npc2.cock+].";
			} else {
				return "Try and pull your flat chest away from [npc2.namePos] [npc2.cock+].";
			}
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] out [npc.moan+] as [npc.she] [npc.verb(try)] to pull [npc.her] [npc.breasts+] away from [npc2.namePos] [npc2.cock+],"
								+ " before begging for [npc2.herHim] to leave [npc.herHim] alone.",

						"With [npc.a_moan+], [npc.name] weakly [npc.verb(try)] to pull away from [npc2.name],"
								+ " sobbing in distress as [npc2.her] [npc2.cock+] continues to thrust up between [npc.her] [npc.breasts+].",

						"Letting out [npc.a_moan+], [npc.name] [npc.verb(try)] to push [npc2.name] away from [npc.herHim],"
								+ " tears running down [npc.her] cheeks as [npc2.she] [npc2.verb(continue)] thrusting [npc2.her] [npc2.cock+] into [npc.her] cleavage."));
				
			} else if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] out [npc.moan+] as [npc.she] [npc.verb(try)] to pull [npc.her] [npc.breastSize] [npc.breasts] away from [npc2.namePos] [npc2.cock+],"
								+ " before begging for [npc.herHim] to leave [npc2.herHim] alone.",

						"With [npc.a_moan+], [npc.name] weakly [npc.verb(try)] to pull away from [npc2.name],"
								+ " sobbing in distress as [npc2.her] [npc2.cock+] continue to thrust up between [npc.her] [npc.breastSize] [npc.breasts+].",

						"Letting out [npc.a_moan+], [npc.name] [npc.verb(try)] to push [npc2.name] away from [npc.herHim],"
								+ " tears running down [npc.her] cheeks as [npc2.she] [npc2.verb(continue)] thrusting [npc2.her] [npc2.cock+] into [npc.her] small cleavage."));
						
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] out [npc.moan+] as [npc.she] [npc.verb(try)] to pull [npc.her] flat chest away from [npc2.namePos] [npc2.cock+],"
								+ " before begging for [npc.herHim] to leave [npc2.herHim] alone.",

						"With [npc.a_moan+], [npc.name] weakly [npc.verb(try)] to pull away from [npc2.name],"
								+ " sobbing in distress as [npc2.her] [npc2.cock+] continues to grind up against [npc.her] flat chest.",

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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Stop performing paizuri";
			} else {
				return "Stop performing naizuri";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Push [npc2.namePos] [npc2.cock] away from your [npc.breasts+].";
			} else {
				return "Push [npc2.namePos] [npc2.cock] away from your flat chest.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly [npc.verb(push)] [npc2.name] away from [npc.herHim], and, in a menacing tone, [npc.verb(order)] [npc2.herHim] to stop fucking [npc.her] [npc.breasts+].",

								"With a menacing growl, [npc.name] roughly [npc.verb(push)] [npc2.name] away, and [npc.verb(order)] [npc2.herHim] to stop fucking [npc.her] [npc.breasts+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc2.name] away from [npc.herHim], and [npc.verb(tell)] [npc2.herHim] to stop fucking [npc.her] [npc.breasts+].",

								"With one last [npc.moan], [npc.name] [npc.verb(push)] [npc2.name] away, and [npc.verb(tell)] [npc2.herHim] to stop fucking [npc.her] [npc.breasts+]."));
						break;
				}
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly [npc.verb(push)] [npc2.name] away from [npc.herHim], and, in a menacing tone, [npc.verb(order)] [npc2.herHim] to stop grinding against [npc.her] chest.",

								"With a menacing growl, [npc.name] roughly [npc.verb(push)] [npc2.name] away, and [npc.verb(order)] [npc2.herHim] to stop grinding against [npc.her] chest."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc2.name] away from [npc.herHim], and [npc.verb(tell)] [npc2.herHim] to stop grinding against [npc.her] chest.",

								"With one last [npc.moan], [npc.name] [npc.verb(push)] [npc2.name] away, and [npc.verb(tell)] [npc2.herHim] to stop grinding against [npc.her] chest."));
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
