package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
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
 * @since 0.4.8.5
 * @version 0.4.8.5
 * @author Sightglass
 */
public class PenisPenisCloaca {
	private static boolean hasCloaca(GameCharacter gc) {
		return gc.getGenitalArrangement() == GenitalArrangement.CLOACA ||  gc.getGenitalArrangement() == GenitalArrangement.CLOACA_BEHIND;
	}
	
	public static final SexAction SLIT_PENETRATION_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
					    
		@Override
		public String getActionTitle() {
			return "Start slit penetration";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
				&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom());
		}

		@Override
		public String getActionDescription() {
			return "Start sliding [npc.her] [npc.cock] past [npc2.namePos] [npc2.cock] and into [npc2.her] genital slit.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching down, [npc.name] [npc.verb(adjust)] [npc.her] [npc.cock] to line it up with [npc2.hers], before slipping past it, thrusting gently into [npc.her] genital slit.",
							"Carefully adjusting [npc.her] [npc.hips+], [npc.name] [npc.verb(press)] [npc.her] [npc.cock] along the base of [npc2.her] [npc2.cock], slipping the tip into [npc2.her] slit.",
							"With [npc.a_moan+], [npc.name] slowly [npc.verb(press)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to tease [npc.her] [npc.cock] against the opening of [npc2.her] cloaca."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a rough buck of [npc.her] [npc.hips+], [npc.name] forcefully [npc.verb(grind)] [npc.her] [npc.cock] against [npc2.namePos] genital slit, .",
							"With a growl, [npc.name] roughly [npc.verb(pull)] [npc2.namePos] crotch into place around [npc.her] [npc.legs], lining up [npc.her] [npc.cock] against [npc2.hers] and starting to thrust them against each other.",
							"With [npc.a_moan+], [npc.name] roughly [npc.verb(grind)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to forcefully grind [npc.her] [npc.cock] up and down over [npc2.hers]."));
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Happily bucking [npc2.her] own [npc2.hips] in response,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and readily [npc2.verb(rub)] [npc2.her] [npc2.cock] against [npc.her] body as [npc.name] [npc.verb(thrust)] into [npc2.her] slit.",
							" Responding with a happy buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to frantically drive [npc.her] [npc.cock] deeper into [npc2.her] cloaca."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Bucking [npc2.her] own [npc2.hips] in response,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and [npc2.verb(rub)] [npc2.her] [npc2.cock] against [npc.her] body as [npc.name] [npc.verb(thrust)] into [npc2.her] slit.",
							" Responding with a buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to drive [npc.her] [npc.cock] deeper into [npc2.her] cloaca."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying to pull away,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(plead)] for [npc.name] to get away from [npc2.herHim] and pull out.",
							" Responding by frantically recoiling from [npc.namePos] unwanted advance, [npc2.name] [npc2.verb(start)] pleading to be left alone, all the while trying to pull [npc2.her] [npc2.cock] away from [npc.namePos]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] enthusiastically [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(rub)] [npc2.her] own [npc2.cock] up and down over [npc.name].",
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.cock] against [npc.name].",
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out, using [npc.namePos] movements to help grind [npc2.her] own [npc2.cock] against [npc.name]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock], [npc2.name] desperately [npc2.verb(beg)] for [npc.name] to get away from [npc2.him].",
						" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away from [npc2.her] groin.",
						" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
								+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to get away from [npc2.her] groin."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(respond)] in kind, and [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(rub)] [npc2.her] own [npc2.cock] up and down over [npc.her].",
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.cock] against [npc.her].",
						" [npc2.Moaning] in pleasure, [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out, using [npc.namePos] movements to help grind [npc2.her] own [npc2.cock+] against [npc.her]."));
				break;
		}
		return "";
	}
	
	public static final SexAction SLIT_PENETRATION_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle slit penetration";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
				&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom());
		}
		
		@Override
		public String getActionDescription() {
			return "Gently thrust [npc.her] [npc.cock] into [npc2.namePos] genital slit.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out a little [npc.moan] with every thrust of [npc.her] [npc.hips], [npc.name] gently [npc.verb(thrust)] [npc.her] [npc.cock+] into [npc2.her] genital slit.",
					"[npc.Name] [npc.verb(let)] out a series of soft sighs as [npc.she] gently [npc.verb(slip)] [npc.her] [npc.cock+] in and out of [npc2.her] cloaca.",
					"Sliding [npc.her] [npc.cock+] along [npc2.namePos] [npc2.cock+] and into [npc2.her] slit, [npc.name] [npc.verb(let)] out a little [npc.moan] with each pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SLIT_PENETRATION_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Slit penetration";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
				&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom());
		}
		
		@Override
		public String getActionDescription() {
			return "Thrust [npc.her] [npc.cock] into [npc2.namePos] genital slit.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+] with each eager thrust of [npc.her] [npc.hips], [npc.name] passionately [npc.verb(thrust)] [npc.her] [npc.cock+] into [npc2.her] genital slit.",
					"[npc.Name] [npc.verb(let)] out a series of lewd [npc.moans] as [npc.she] desperately [npc.verb(grind)] [npc.her] [npc.cock+] along the base of [npc2.namePos] [npc2.cock+], enveloped within [npc2.her] cloaca.",
					"Eagerly grinding [npc.her] [npc.clit+] into the snug slit holding [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(let)] out [npc.a_moan+] with each frantic pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SLIT_PENETRATION_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough slit penetration";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
				&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom());
		}

		@Override
		public String getActionDescription() {
			return "Continue roughly grinding your [npc.cock+] into the slit holding [npc2.namePos] [npc2.cock+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+] with each forceful thrust of [npc.her] [npc.hips], [npc.name] roughly [npc.verb(grind)] [npc.her] [npc.cock+] into the slit holding [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] out a series of lewd [npc.moans] as [npc.she] roughly [npc.verb(press)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.cock+] and into [npc2.her] genital slit.",
					"Violently grinding [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock+] and into [npc2.her] cloaca, [npc.name] [npc.verb(let)] out [npc.a_moan+] with each forceful pump of [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SLIT_PENETRATION_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop slit penetration";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
				&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom());
		}
		
		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [npc2.namePos] genital slit.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough thrust, [npc.name] [npc.verb(pull)] [npc.her] groin out of [npc2.namePos] genital slit.",
							"Roughly grinding [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock] one last time, [npc.name] then [npc.verb(pull)] back, pulling out of [npc2.namePos] cloaca."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last buck of [npc.her] [npc.hips], [npc.name] [npc.verb(pull)] [npc.her] groin out from [npc2.namePos] genital slit.",
							"Rubbing [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock] one last time, [npc.name] then [npc.verb(pull)] back, pulling out of [npc2.namePos] cloaca."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Although happy to have [npc2.her] slit released, [npc2.name] [npc2.verb(continue)] crying and weakly struggling against [npc.name] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.herHim] alone.",
							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] [npc2.her] groin away from [npc.name]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] back, signalling [npc2.her] desire for more attention.",
							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SLIT_PENETRATION_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterToBeCreampied() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeCreampied() {
			return SexAreaPenetration.PENIS;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			if(!performer.hasPenisIgnoreDildo()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			boolean dicksTouching = Main.sex.getCharacterOngoingSexArea(performer, SexAreaPenetration.PENIS).contains(target) 
				&& Main.sex.getCharacterOngoingSexArea(target, SexAreaPenetration.PENIS).contains(performer);
			if (!(dicksTouching && hasCloaca(target))) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)!=OrgasmBehaviour.CREAMPIE
					&& !performer.isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(performer) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(performer)>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			boolean knotRequestObeyed = false;
			for(GameCharacter knotRequester : Main.sex.getCharactersRequestingKnot()) {
				if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterPerformingAction(), knotRequester)) {
					knotRequestObeyed = true; // If there is a knot requester who they're listening to, give priority to knotting
					break;
				}
			}
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			
			if(Math.random()<0.66f
					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "Slit creampie! (forced)";
					
				} else if(bodypart == Arm.class) {
					return "Hug-locked slit creampie!";
					
				} else if(bodypart == Leg.class) {
					return "Leg-locked  slit creampie!";
					
				} else if(bodypart == Tail.class) {
					return "Tail-locked  slit creampie!";
					
				} else if(bodypart == Wing.class) {
					return "Wing-locked  slit creampie!";
					
				} else if(bodypart == Tentacle.class) {
					return "Tentacle-locked slit creampie!";
				}
			}
			return "Slit creampie!";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull] using [npc.her] advantageous position to force you to grind your penis into [npc.her] cloaca as your orgasm! As you're on the very brink of orgasm, you have no time to try and push [npc.herHim] away!");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.arms+] around your lower body, thereby forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.legs+] around your lower body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] "+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+" around your lower body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.wingSize] [npc.wings] around your body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.tentacles+] around your lower body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			String returnString = "You've reached your climax, and can't hold back your orgasm any longer. Cum inside [npc2.name], grinding your penis into [npc.her] cloaca.";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
				
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& ((cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumTarget).isEmpty())
						|| (cumTarget.equals(cumProvider) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumProvider).isEmpty()))) {

				return Util.newArrayListOfValues(
						CoverableArea.PENIS,
						CoverableArea.VAGINA);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).isEndsSex();
		}
	};
	
	public static final SexAction MUTUAL_SLIT_PENETRATION = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Mutual slit penetration";
		}

		@Override
		public String getActionDescription() {
			return "Slip [npc2.namePos] [npc2.cock+] into your genital slit while you fuck [npc2.hers].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			return hasCloaca(performer) && hasCloaca(target) && Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom();
		}

		@Override
		public String getDescription() {
			String start = UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] [npc.verb(slip)] the tip of [npc2.her] [npc2.cock] into the slit surrounding [npc.namePos] [npc.cock+], circling it and teasing the sensitive skin inside.",
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] down to [npc2.namePos] [npc2.cock+]."
							+ " Guiding it to [npc.namePos] [npc.cock+], [npc.name] [npc.verb(grin)] as [npc.she] then [npc.verb(slip)] it it past [npc.her] [npc.cock+] and into [npc.her] genital slit, causing [npc2.herHim] to let out [npc2.a_moan+].",
					"[npc.Name] [npc.verb(grin)] in delight as [npc.she] [npc.verb(guide)] [npc2.namePos] [npc2.cock] to the opening of [npc.namePos] cloaca before slipping it in. [npc2.NamePos] [npc2.cock] fits snuggly next to [npc.hers], and [npc.name] [npc.verb(start)] to slowly thrust with rhythmic undulations.",
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(slip)] [npc2.her] [npc2.cock] into [npc.namePos] genital slit, teasing it with slow circles before slipping in further.");
			String middle1 = UtilText.returnStringAtRandom(
				"[npc.Name] slowly [npc.verb(work)] [npc2.her] [npc2.cock] deeper into [npc.her] slit, while [npc.she] also [npc.verb(slip)] deeper into [npc2.hers].",
				"Rather than impaling [npc.himself] deeply onto [npc2.her] [npc.cock], [npc.name] instead circle [npc.her] hips, grinding both shafts against each other in their slits.",
				"With both cocks nestled snugly against each other, each of [npc.her] thrusts drive both deeper along the base of the other."
			);
			String middle2 = UtilText.returnStringAtRandom(
				"Overwhelmed by the stimulation from [npc2.her] [npc2.cock] in [npc.her] snug slit while [npc.name] [npc.verb(fuck)] [npc2.hers], all [npc2.name] [npc2.is] able to do is shudder and [npc2.moan].",
				"The inner surface of [npc.namePos] cloaca is incredibly sensitive, and [npc.name] [npc.verb(feel)] every shudder and twitch as [npc2.her] [npc2.cock] twitches in response tight embrace. Since [npc.her] [npc.cock] is wedged against the base of [npc2.her] [npc2.cock+], [npc.name] [npc.verb(feel)] every shudder and twitch in response.",
				"With a lusty [npc2.moan], [npc2.name] [npc2.verb(press)] in closer to [npc.name], humping gently to try to get more of [npc2.her] [npc2.cock+] inside [npc.her] slit, driving [npc.name] deeper into [npc2.hers] as well."
			);
			String end = UtilText.returnStringAtRandom(
				"After a few last thrusts, [npc.name] carefully [npc.verb(pull)] [npc2.name] [npc2.namePos] out, [npc.name] playfully [npc.verb(slap)] [npc2.her] [npc2.cock] against [npc2.her] belly before starting to thrust against into [npc2.her] slit again.",
				"With a sigh and a few more thrusts, [npc.name] [npc.verb(pull)] back so [npc2.namePos] [npc2.cock+] flops out of [npc.her] cloaca."
			);
			return String.join(" ",start, middle1, middle2,end);
		}
	};
}
